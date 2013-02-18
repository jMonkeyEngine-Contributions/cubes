/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cubes;

/**
 *
 * @author Carl
 */
public class Block{

    public Block(){
        type = BlockManager.getType(getClass());
    }
    public static enum Face{
        Top, Bottom, Left, Right, Front, Back
    };
    private BlockType type;

    public BlockType getType(){
        return type;
    }
}
