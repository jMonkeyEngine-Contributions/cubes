/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cubes;

import com.jme3.material.Material;
import com.jme3.material.RenderState.BlendMode;
import com.jme3.texture.Texture;

/**
 *
 * @author Carl
 */
public class BlockChunk_Material extends Material{

    public BlockChunk_Material(){
        super(CubesSettings.ASSET_MANAGER, "Common/MatDefs/Misc/Unshaded.j3md");
        Texture texture = CubesSettings.ASSET_MANAGER.loadTexture(CubesSettings.BLOCK_TEXTURE_PATH);
        texture.setMagFilter(Texture.MagFilter.Nearest);
        texture.setMinFilter(Texture.MinFilter.NearestNoMipMaps);
        setTexture("ColorMap", texture);
        getAdditionalRenderState().setBlendMode(BlendMode.Alpha);
    }
}
