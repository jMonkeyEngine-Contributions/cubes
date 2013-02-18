package com.cubes.test;

import java.util.logging.Level;
import java.util.logging.Logger;
import com.jme3.app.SimpleApplication;
import com.jme3.math.Vector3f;
import com.jme3.system.AppSettings;
import com.cubes.*;
import com.cubes.test.blocks.*;
import com.jme3.scene.Node;

public class TestTutorial extends SimpleApplication{

    public static void main(String[] args){
        Logger.getLogger("").setLevel(Level.SEVERE);
        TestTutorial app = new TestTutorial();
        app.start();
    }

    public TestTutorial(){
        settings = new AppSettings(true);
        settings.setWidth(1280);
        settings.setHeight(720);
        settings.setTitle("Cubes Demo - Tutorial");
    }

    @Override
    public void simpleInitApp(){
        CubesSettings.ASSET_MANAGER = assetManager;
        CubesTestAssets.registerBlocks();
        
        //This is your terrain, it contains the whole
        //block world and offers methods to modify it
        BlockTerrainControl blockTerrain = new BlockTerrainControl(new Vector3Int(1, 1, 1));

        //To set a block, just specify the location and the block object
        //(Existing blocks will be replaced)
        blockTerrain.setBlock(new Vector3Int(0, 0, 0), Block_Wood.class); 
        blockTerrain.setBlock(new Vector3Int(0, 0, 1), Block_Wood.class);
        blockTerrain.setBlock(new Vector3Int(1, 0, 0), Block_Wood.class);
        blockTerrain.setBlock(new Vector3Int(1, 0, 1), Block_Stone.class);
        blockTerrain.setBlock(0, 0, 0, Block_Grass.class); //For the lazy users :P

        //You can place whole areas of blocks too: setBlockArea(location, size, block)
        //(The specified block will be cloned each time)
        //The following line will set 3 blocks on top of each other
        //({1,1,1}, {1,2,3} and {1,3,1})
        blockTerrain.setBlockArea(new Vector3Int(1, 1, 1), new Vector3Int(1, 3, 1), Block_Stone.class);

        //Removing a block works in a similar way
        blockTerrain.removeBlock(new Vector3Int(1, 2, 1));
        blockTerrain.removeBlock(new Vector3Int(1, 3, 1));

        //The terrain is a jME-Control, you can add it
        //to a node of the scenegraph to display it
        Node terrainNode = new Node();
        terrainNode.addControl(blockTerrain);
        rootNode.attachChild(terrainNode);
        
        cam.setLocation(new Vector3f(-10, 10, 16));
        cam.lookAtDirection(new Vector3f(1, -0.56f, -1), Vector3f.UNIT_Y);
        flyCam.setMoveSpeed(50);
    }
}