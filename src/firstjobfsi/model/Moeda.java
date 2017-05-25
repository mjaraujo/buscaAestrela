/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstjobfsi.model;

import firstjobfsi.control.BufferedImageLoader;
import firstjobfsi.control.MouseInput;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author ALUNO
 */
public class Moeda extends Entidade{

    
    int passoX = 1;
    int passoY = 1;
    int lastUpd= 25;
    
    public enum Estados{
        PARADO,
        GIRANDO,
        MORTO
    }
    
    
    
    private Estados estado = Estados.GIRANDO;
    
    public Moeda(Rectangle retangulo, boolean legendado, boolean informado) {
        super(retangulo, legendado, informado);
        BufferedImageLoader bil = new BufferedImageLoader();
        setImage(bil.carregaImagem("moeda"));
        sheet = new SpriteSheet(getImage());
        setImage(sheet.grabImage(1, 1, retangulo.width, retangulo.height));        
    }
    @Override
    public void render(Graphics2D g2d){
        
        if((estado==Estados.GIRANDO)&&(lastUpd>=17)){
            
            lastUpd=0;
            passoX=passoX%10;
            passoX++;
        }    
        lastUpd++;
            
        setImage(sheet.grabImage(passoX, 1, getRetangulo().width, getRetangulo().height));        
        g2d.drawImage(getImage(), (int)getRetangulo().x,(int)getRetangulo().y,(int)getRetangulo().width,(int)getRetangulo().height, null);
    }
    
    
}
