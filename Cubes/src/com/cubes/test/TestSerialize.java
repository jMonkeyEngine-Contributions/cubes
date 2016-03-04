package com.cubes.test;

import java.util.logging.Level;
import java.util.logging.Logger;
import com.jme3.app.SimpleApplication;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.system.AppSettings;
import com.cubes.*;
import com.cubes.network.CubesSerializer;

public class TestSerialize extends SimpleApplication{

    public static void main(String[] args){
        Logger.getLogger("").setLevel(Level.SEVERE);
        TestSerialize app = new TestSerialize();
        app.start();
    }

    public TestSerialize(){
        settings = new AppSettings(true);
        settings.setWidth(1280);
        settings.setHeight(720);
        settings.setTitle("Cubes Demo - Original Serialize");
    }

    @Override
    public void simpleInitApp(){
        CubesTestAssets.registerBlocks();
        
        BlockTerrainControl blockTerrain = new BlockTerrainControl(CubesTestAssets.getSettings(this), new Vector3Int(1, 1, 1));
        blockTerrain.setBlocksFromNoise(new Vector3Int(0, 0, 0), new Vector3Int(16, 10, 16), 0.5f, CubesTestAssets.BLOCK_GRASS);
        Node terrainNode = new Node();
        terrainNode.addControl(blockTerrain);
        terrainNode.setLocalTranslation(40, 0, 0);
        rootNode.attachChild(terrainNode);
        
        
        BlockTerrainControl blockTerrainClone = new BlockTerrainControl(CubesTestAssets.getSettings(this), new Vector3Int());
        
        
        Node terrainNodeClone = new Node();

        terrainNodeClone.setLocalTranslation(-40, 0, 0);

        rootNode.attachChild(terrainNodeClone);

        terrainNodeClone.addControl(blockTerrainClone);
        
        byte[] serializedBlockTerrain = CubesSerializer.writeToBytes(blockTerrain);
        CubesSerializer.readFromBytes(blockTerrainClone, serializedBlockTerrain);

        terrainNodeClone.removeControl(blockTerrainClone);
        terrainNodeClone.addControl(blockTerrainClone);

        


        cam.setLocation(new Vector3f(23.5f, 46, -103));
        cam.lookAtDirection(new Vector3f(0, -0.25f, 1), Vector3f.UNIT_Y);
        flyCam.setMoveSpeed(300);


        /*    
        // Create 'Original' block terrain
        BlockTerrainControl blockTerrain = new BlockTerrainControl(CubesTestAssets.getSettings(this), new Vector3Int(1, 1, 1));
        blockTerrain.setBlocksFromNoise(new Vector3Int(0, 0, 0), new Vector3Int(16, 10, 16), 0.5f, CubesTestAssets.BLOCK_GRASS);
        Node terrainNode = new Node();
        terrainNode.addControl(blockTerrain);
        terrainNode.setLocalTranslation(40, 0, 0);
        rootNode.attachChild(terrainNode);
        
        // Create 'target' block terrain
        BlockTerrainControl blockTerrainClone = new BlockTerrainControl(CubesTestAssets.getSettings(this), new Vector3Int());
        Node terrainNodeClone = new Node();
        terrainNodeClone.addControl(blockTerrainClone);
        terrainNodeClone.setLocalTranslation(-40, 0, 0);
        rootNode.attachChild(terrainNodeClone);
        
        // Put player in a useful place to see both terrains
        cam.setLocation(new Vector3f(23.5f, 46, -103));
        cam.lookAtDirection(new Vector3f(0, -0.25f, 1), Vector3f.UNIT_Y);
        flyCam.setMoveSpeed(300);
        // Ask original terrain to write blocks to a buffer        
        byte[] serializedBlockTerrain = CubesSerializer.writeToBytes(blockTerrain);
        
        // Ask target terrain to read blocks from a buffer
        CubesSerializer.readFromBytes(blockTerrainClone, serializedBlockTerrain);
        */
        
        
    }    
}
