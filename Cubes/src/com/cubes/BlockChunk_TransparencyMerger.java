/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cubes;

import com.cubes.Block.Face;

/**
 *
 * @author Carl
 */
public class BlockChunk_TransparencyMerger implements BlockChunk_MeshMerger{

    private BlockChunk_TransparencyMerger(boolean isGeometryTransparent){
        this.isGeometryTransparent = isGeometryTransparent;
    }
    public static final BlockChunk_TransparencyMerger OPAQUE = new BlockChunk_TransparencyMerger(false);
    public static final BlockChunk_TransparencyMerger TRANSPARENT = new BlockChunk_TransparencyMerger(true);
    private boolean isGeometryTransparent;
    
    @Override
    public boolean shouldFaceBeAdded(BlockChunkControl chunk, Vector3Int location, Face face){
        BlockType block = chunk.getBlock(location);
        if(block.getSkin().isTransparent() == isGeometryTransparent){
            BlockType neighborBlock = chunk.getNeighborBlock_Local(location, face);
            if(neighborBlock != null){
                if(block.getSkin().isTransparent() != neighborBlock.getSkin().isTransparent()){
                    return true;
                }
                return false;
            }
            return true;
        }
        return false;
    }
}
