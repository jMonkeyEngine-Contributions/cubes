/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cubes;

/**
 *
 * @author Carl
 */
public class BlockSkin{

    public BlockSkin(BlockSkin_TextureLocation textureLocation, boolean isTransparent){
        this(new BlockSkin_TextureLocation[]{textureLocation}, isTransparent);
    }
    
    public BlockSkin(BlockSkin_TextureLocation[] textureLocations, boolean isTransparent){
        this.textureLocations = textureLocations;
        this.isTransparent = isTransparent;
    }
    private BlockSkin_TextureLocation[] textureLocations;
    private boolean isTransparent;

    public BlockSkin_TextureLocation getTextureLocation(BlockChunkControl chunk, Vector3Int blockLocation, Block.Face face){
        return textureLocations[getTextureLocationIndex(chunk, blockLocation, face)];
    }

    protected int getTextureLocationIndex(BlockChunkControl chunk, Vector3Int blockLocation, Block.Face face){
        if(textureLocations.length == 6){
            return face.ordinal();
        }
        return 0;
    }

    public boolean isTransparent(){
        return isTransparent;
    }
}
