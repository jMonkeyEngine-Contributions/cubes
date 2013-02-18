/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cubes;

/**
 *
 * @author Carl
 */
public interface BlockChunk_MeshMerger{
    
    public abstract boolean shouldFaceBeAdded(BlockChunkControl chunk, Vector3Int location, Block.Face face);
}
