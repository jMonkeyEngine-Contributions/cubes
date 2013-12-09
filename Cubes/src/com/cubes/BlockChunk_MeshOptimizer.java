/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cubes;

import java.util.ArrayList;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Mesh;
import com.jme3.scene.VertexBuffer.Type;
import com.jme3.util.BufferUtils;

/**
 *
 * @author Carl
 */
public class BlockChunk_MeshOptimizer{

    private static int[] indices;
    private static Vector3f[] vertices;
    private static Vector2f[] textureCoordinates;
    private static float[] normals;

    public static Mesh generateOptimizedMesh(BlockChunkControl blockChunk, BlockChunk_MeshMerger meshMerger){
        loadMeshData(blockChunk, meshMerger);
        return generateMesh();
    }

    private static void loadMeshData(BlockChunkControl chunk, BlockChunk_MeshMerger meshMerger){
        ArrayList<Vector3f> verticeList = new ArrayList<Vector3f>();
        ArrayList<Vector2f> textureCoordinateList = new ArrayList<Vector2f>();
        ArrayList<Integer> indicesList = new ArrayList<Integer>();
        ArrayList<Float> normalsList = new ArrayList<Float>();
        BlockTerrainControl blockTerrain = chunk.getTerrain();
        Vector3Int tmpLocation = new Vector3Int();
        for(int x=0;x<blockTerrain.getSettings().getChunkSizeX();x++){
            for(int y=0;y<blockTerrain.getSettings().getChunkSizeY();y++){
                for(int z=0;z<blockTerrain.getSettings().getChunkSizeZ();z++){
                    tmpLocation.set(x, y, z);
                    BlockType block = chunk.getBlock(tmpLocation);
                    if(block != null){
                        BlockSkin blockSkin = block.getSkin();
                        Vector3f blockLocation = new Vector3f(x, y, z);

                        Vector3f faceLoc_Bottom_TopLeft = blockLocation.add(new Vector3f(0, 0, 0)).mult(blockTerrain.getSettings().getBlockSize());
                        Vector3f faceLoc_Bottom_TopRight = blockLocation.add(new Vector3f(1, 0, 0)).mult(blockTerrain.getSettings().getBlockSize());
                        Vector3f faceLoc_Bottom_BottomLeft = blockLocation.add(new Vector3f(0, 0, 1)).mult(blockTerrain.getSettings().getBlockSize());
                        Vector3f faceLoc_Bottom_BottomRight = blockLocation.add(new Vector3f(1, 0, 1)).mult(blockTerrain.getSettings().getBlockSize());
                        Vector3f faceLoc_Top_TopLeft = blockLocation.add(new Vector3f(0, 1, 0)).mult(blockTerrain.getSettings().getBlockSize());
                        Vector3f faceLoc_Top_TopRight = blockLocation.add(new Vector3f(1, 1, 0)).mult(blockTerrain.getSettings().getBlockSize());
                        Vector3f faceLoc_Top_BottomLeft = blockLocation.add(new Vector3f(0, 1, 1)).mult(blockTerrain.getSettings().getBlockSize());
                        Vector3f faceLoc_Top_BottomRight = blockLocation.add(new Vector3f(1, 1, 1)).mult(blockTerrain.getSettings().getBlockSize());

                        if(meshMerger.shouldFaceBeAdded(chunk, tmpLocation, Block.Face.Top)){
                            addVerticeIndexes(verticeList, indicesList);
                            verticeList.add(faceLoc_Top_BottomLeft);
                            verticeList.add(faceLoc_Top_BottomRight);
                            verticeList.add(faceLoc_Top_TopLeft);
                            verticeList.add(faceLoc_Top_TopRight);
                            addBlockTextureCoordinates(textureCoordinateList, blockSkin.getTextureLocation(chunk, tmpLocation, Block.Face.Top));
                            addSquareNormals(normalsList, new float[]{0, 1, 0});
                        }
                        if(meshMerger.shouldFaceBeAdded(chunk, tmpLocation, Block.Face.Bottom)){
                            addVerticeIndexes(verticeList, indicesList);
                            verticeList.add(faceLoc_Bottom_BottomRight);
                            verticeList.add(faceLoc_Bottom_BottomLeft);
                            verticeList.add(faceLoc_Bottom_TopRight);
                            verticeList.add(faceLoc_Bottom_TopLeft);
                            addBlockTextureCoordinates(textureCoordinateList, blockSkin.getTextureLocation(chunk, tmpLocation, Block.Face.Bottom));
                            addSquareNormals(normalsList, new float[]{0, -1, 0});
                        }
                        if(meshMerger.shouldFaceBeAdded(chunk, tmpLocation, Block.Face.Left)){
                            addVerticeIndexes(verticeList, indicesList);
                            verticeList.add(faceLoc_Bottom_TopLeft);
                            verticeList.add(faceLoc_Bottom_BottomLeft);
                            verticeList.add(faceLoc_Top_TopLeft);
                            verticeList.add(faceLoc_Top_BottomLeft);
                            addBlockTextureCoordinates(textureCoordinateList, blockSkin.getTextureLocation(chunk, tmpLocation, Block.Face.Left));
                            addSquareNormals(normalsList, new float[]{-1, 0, 0});
                        }
                        if(meshMerger.shouldFaceBeAdded(chunk, tmpLocation, Block.Face.Right)){
                            addVerticeIndexes(verticeList, indicesList);
                            verticeList.add(faceLoc_Bottom_BottomRight);
                            verticeList.add(faceLoc_Bottom_TopRight);
                            verticeList.add(faceLoc_Top_BottomRight);
                            verticeList.add(faceLoc_Top_TopRight);
                            addBlockTextureCoordinates(textureCoordinateList, blockSkin.getTextureLocation(chunk, tmpLocation, Block.Face.Right));
                            addSquareNormals(normalsList, new float[]{1, 0, 0});
                        }
                        if(meshMerger.shouldFaceBeAdded(chunk, tmpLocation, Block.Face.Front)){
                            addVerticeIndexes(verticeList, indicesList);
                            verticeList.add(faceLoc_Bottom_BottomLeft);
                            verticeList.add(faceLoc_Bottom_BottomRight);
                            verticeList.add(faceLoc_Top_BottomLeft);
                            verticeList.add(faceLoc_Top_BottomRight);
                            addBlockTextureCoordinates(textureCoordinateList, blockSkin.getTextureLocation(chunk, tmpLocation, Block.Face.Front));
                            addSquareNormals(normalsList, new float[]{0, 0, 1});
                        }
                        if(meshMerger.shouldFaceBeAdded(chunk, tmpLocation, Block.Face.Back)){
                            addVerticeIndexes(verticeList, indicesList);
                            verticeList.add(faceLoc_Bottom_TopRight);
                            verticeList.add(faceLoc_Bottom_TopLeft);
                            verticeList.add(faceLoc_Top_TopRight);
                            verticeList.add(faceLoc_Top_TopLeft);
                            addBlockTextureCoordinates(textureCoordinateList, blockSkin.getTextureLocation(chunk, tmpLocation, Block.Face.Back));
                            addSquareNormals(normalsList, new float[]{0, 0, -1});
                        }
                    }
                }
            }
        }
        indices = new int[indicesList.size()];
        for (int i = 0; i < indicesList.size(); i++) {
            indices[i] = indicesList.get(i);
        }
        vertices = verticeList.toArray(new Vector3f[0]);
        textureCoordinates = textureCoordinateList.toArray(new Vector2f[0]);
        normals = new float[normalsList.size()];
        for (int i = 0; i < normalsList.size(); i++) {
            normals[i] = normalsList.get(i);
        }
    }

    private static void addVerticeIndexes(ArrayList<Vector3f> verticeList, ArrayList<Integer> indexesList){
        int verticesCount = verticeList.size();
        indexesList.add(verticesCount + 2);
        indexesList.add(verticesCount + 0);
        indexesList.add(verticesCount + 1);
        indexesList.add(verticesCount + 1);
        indexesList.add(verticesCount + 3);
        indexesList.add(verticesCount + 2);
    }

    private static void addBlockTextureCoordinates(ArrayList<Vector2f> textureCoordinatesList, BlockSkin_TextureLocation textureLocation){
        textureCoordinatesList.add(getTextureCoordinates(textureLocation, 0, 0));
        textureCoordinatesList.add(getTextureCoordinates(textureLocation, 1, 0));
        textureCoordinatesList.add(getTextureCoordinates(textureLocation, 0, 1));
        textureCoordinatesList.add(getTextureCoordinates(textureLocation, 1, 1));
    }

    private static Vector2f getTextureCoordinates(BlockSkin_TextureLocation textureLocation, int xUnitsToAdd, int yUnitsToAdd){
        float textureCount = 16;
        float textureUnit = 1f / textureCount;
        float x = (((textureLocation.getColumn() + xUnitsToAdd) * textureUnit));
        float y = ((((-1 * textureLocation.getRow()) + (yUnitsToAdd - 1)) * textureUnit) + 1);
        return new Vector2f(x, y);
    }
    
    private static void addSquareNormals(ArrayList<Float> normalsList, float[] normal){
        for(int i=0;i<4;i++){
            normalsList.add(normal[0]);
            normalsList.add(normal[1]);
            normalsList.add(normal[2]);
        }
    }

    private static Mesh generateMesh(){
        Mesh mesh = new Mesh();
        mesh.setBuffer(Type.Position, 3, BufferUtils.createFloatBuffer(vertices));
        mesh.setBuffer(Type.TexCoord, 2, BufferUtils.createFloatBuffer(textureCoordinates));
        mesh.setBuffer(Type.Index, 1, BufferUtils.createIntBuffer(indices));
        mesh.setBuffer(Type.Normal, 3, BufferUtils.createFloatBuffer(normals));
        mesh.updateBound();
        return mesh;
    }
}
