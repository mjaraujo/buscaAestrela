/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.managers;

import java.awt.image.BufferedImage;

/**
 *
 * @author Marcio
 */
public class TextureManager extends ResourceManager {
   
    private BufferedImage image;
    
    public TextureManager(BufferedImage image){
        this.image = image;
    };
    
    public BufferedImage getImage(){
        return image;
    }
}
