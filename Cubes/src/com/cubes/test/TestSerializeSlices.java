package com.cubes.test;

import java.util.logging.Level;
import java.util.logging.Logger;
import com.jme3.app.SimpleApplication;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.system.AppSettings;
import com.cubes.*;
import com.cubes.network.BitInputStream;
import com.cubes.network.CubesSerializer;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class TestSerializeSlices extends SimpleApplication{

    public static void main(String[] args){
        Logger.getLogger("").setLevel(Level.SEVERE);
        TestSerializeSlices app = new TestSerializeSlices();
        app.start();
    }

    public TestSerializeSlices(){
        settings = new AppSettings(true);
        settings.setWidth(1280);
        settings.setHeight(720);
        settings.setTitle("Cubes Demo - Serialize Slices");
    }

    @Override
    public void simpleInitApp(){
        CubesTestAssets.registerBlocks();
        
        // Create 'Original' block terrain
        BlockTerrainControl blockTerrain = new BlockTerrainControl(CubesTestAssets.getSettings(this), new Vector3Int(1, 1, 1));
        blockTerrain.setBlocksFromNoise(new Vector3Int(0, 0, 0), new Vector3Int(16, 10, 16), 0.5f, CubesTestAssets.BLOCK_GRASS);
        Node terrainNode = new Node();
        terrainNode.addControl(blockTerrain);
        terrainNode.setLocalTranslation(40, 0, 0);
        rootNode.attachChild(terrainNode);
        
        // Create full target block terrain
        BlockTerrainControl blockTerrainClone = new BlockTerrainControl(CubesTestAssets.getSettings(this), new Vector3Int());
        Node terrainNodeClone = new Node();
        terrainNodeClone.addControl(blockTerrainClone);
        terrainNodeClone.setLocalTranslation(-20, 0, 0);
        rootNode.attachChild(terrainNodeClone);

        // Create slice target block terrain
        BlockTerrainControl blockTerrainSliceClone = new BlockTerrainControl(CubesTestAssets.getSettings(this), new Vector3Int());
        Node terrainSliceNodeClone = new Node();
        terrainSliceNodeClone.addControl(blockTerrainSliceClone);
        terrainSliceNodeClone.setLocalTranslation(-80, 0, 0);
        rootNode.attachChild(terrainSliceNodeClone);
        
        // Put player in a useful place to see both terrains
        cam.setLocation(new Vector3f(23.5f, 46, -103));
        cam.lookAtDirection(new Vector3f(0, -0.25f, 1), Vector3f.UNIT_Y);
        flyCam.setMoveSpeed(300);

        
        // Ask original terrain to write blocks to a buffer        
        byte[] serializedBlockTerrain = CubesSerializer.writeToBytes(blockTerrain);
        ArrayList<byte[]> slices = blockTerrain.writeChunkPartials(new Vector3Int(0,0,0));
        
        // Ask target terrain to read blocks from a buffer
        CubesSerializer.readFromBytes(blockTerrainClone, serializedBlockTerrain);
        //CubesSerializer.readFromBytes(blockTerrainSliceClone, serializedBlockTerrain);
        for (int i = slices.size()-1; i >= 0; --i) {
            blockTerrainSliceClone.readChunkPartial(slices.get(i));
        }
                
        terrainNodeClone.removeControl(blockTerrainClone);
        terrainNodeClone.addControl(blockTerrainClone);
        terrainSliceNodeClone.removeControl(blockTerrainSliceClone);
        terrainSliceNodeClone.addControl(blockTerrainSliceClone);

        // Verify
        byte[] serializedBlockTargetTerrain = CubesSerializer.writeToBytes(blockTerrainClone);
        byte[] serializedBlockSliceTerrain = CubesSerializer.writeToBytes(blockTerrainSliceClone);
        for (int compareI = 0; compareI < serializedBlockTargetTerrain.length; ++compareI) {
            if (serializedBlockTargetTerrain[compareI] != serializedBlockSliceTerrain[compareI]) {
                System.err.println(" Coordinate does not match " + compareI );
            }
        }
        
    }    
}
