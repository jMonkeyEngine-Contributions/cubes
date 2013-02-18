/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cubes;

/**
 *
 * @author Carl
 */
public class BlockType{

    public BlockType(byte type, BlockSkin skin){
        this.type = type;
        this.skin = skin;
    }
    private byte type;
    private BlockSkin skin;

    public byte getType(){
        return type;
    }

    public BlockSkin getSkin(){
        return skin;
    }
}
