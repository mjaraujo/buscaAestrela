/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstjobfsi.model;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import utils.managers.TextureManager;

/**
 *
 * @author Marcio
 */
public class Texture {
    private final static Map<String, TextureManager> texMap = new HashMap<String, TextureManager>();
    
    
    private TextureManager manager;
    private String fileName;
    protected int posX=0;
    protected int posY=0;
    
    public Texture(String fileName) throws IOException{
        this.fileName = fileName;
                
        TextureManager oldTexture = texMap.get(fileName);
        if(oldTexture != null){
            manager = oldTexture;
            manager.addReference();
        }else{
            System.out.println("Carregando textura: " + fileName);
            manager = new TextureManager( ImageIO.read(new File("./resources/textures/" + fileName +".png")));  
            texMap.put(fileName, manager);
        }
        
    }
    
    @Override
    protected void finalize() throws Throwable{
        if(manager.removeReference() && !fileName.isEmpty()) texMap.remove(fileName);
            texMap.remove(fileName);
            System.out.println("removendo textura da memória " + fileName);
            super.finalize();
    };
    
    public void render(Graphics g, double x, double y){
        g.drawImage(manager.getImage(), (int) x,(int) y ,null);
        
    }
    
    public void render(Graphics2D g){
        g.drawImage(manager.getImage(), (int) getPosX(),(int) getPosY() ,null);
        
    }
    
    public BufferedImage getImage(){
        return manager.getImage();
    }

    /**
     * @return the posX
     */
    public int getPosX() {
        return posX;
    }

    /**
     * @param posX the posX to set
     */
    public void setPosX(int posX) {
        this.posX = posX;
    }

    /**
     * @return the posY
     */
    public int getPosY() {
        return posY;
    }

    /**
     * @param posY the posY to set
     */
    public void setPosY(int posY) {
        this.posY = posY;
    }
}
