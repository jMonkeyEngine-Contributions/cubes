/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cubes;

import com.jme3.asset.AssetManager;

/**
 *
 * @author Carl
 */
public class CubesSettings{
    
    public static AssetManager ASSET_MANAGER;
    public static float BLOCK_SIZE = 3;
    public static int CHUNK_SIZE_X = 16;
    public static int CHUNK_SIZE_Y = 256;
    public static int CHUNK_SIZE_Z = 16;
    public static String BLOCK_TEXTURE_PATH = "Textures/cubes/terrain.png";
    public static BlockChunk_Material BLOCK_MATERIAL;
}
