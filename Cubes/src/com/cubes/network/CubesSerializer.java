/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cubes.network;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import com.cubes.BlockTerrainControl;

/**
 *
 * @author Carl
 */
public class CubesSerializer{
    
    public static byte[] writeToBytes(BlockTerrainControl blockTerrain){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BitOutputStream bitOutputStream = new BitOutputStream(byteArrayOutputStream);
        blockTerrain.write(bitOutputStream);
        bitOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }
    
    public static void readFromBytes(BlockTerrainControl blockTerrain, byte[] bytes){
        BitInputStream bitInputStream = new BitInputStream(new ByteArrayInputStream(bytes));
        try{
            blockTerrain.read(bitInputStream);
        }catch(IOException ex){
            System.out.println("Error while unserializing terrain.");
            ex.printStackTrace();
        }
    }
}
