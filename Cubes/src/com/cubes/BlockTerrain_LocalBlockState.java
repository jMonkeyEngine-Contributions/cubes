/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cubes;

/**
 *
 * @author Carl
 */
public class BlockTerrain_LocalBlockState{

    public BlockTerrain_LocalBlockState(BlockChunkControl chunk, Vector3Int localBlockLocation){
        this.chunk = chunk;
        this.localBlockLocation = localBlockLocation;
    }
    private BlockChunkControl chunk;
    private Vector3Int localBlockLocation;

    public BlockChunkControl getChunk(){
        return chunk;
    }

    public Vector3Int getLocalBlockLocation(){
        return localBlockLocation;
    }

    public BlockType getBlock(){
        return chunk.getBlock(localBlockLocation);
    }
    
    public void setBlock(Class<? extends Block> blockClass){
        chunk.setBlock(localBlockLocation, blockClass);
    }
    
    public void removeBlock(){
        chunk.removeBlock(localBlockLocation);
    }
}
