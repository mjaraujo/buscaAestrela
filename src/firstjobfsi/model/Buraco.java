/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstjobfsi.model;

import firstjobfsi.control.BufferedImageLoader;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author ALUNO
 */
public class Buraco extends Entidade{

    
     
    public enum Estados{
        PARADO,
        GIRANDO,
        MORTO
    }
    
    private Estados estado = Estados.GIRANDO;
    
    public Buraco(Rectangle retangulo, boolean legendado, boolean informado) {
        super(retangulo, legendado, informado);
        BufferedImageLoader bil = new BufferedImageLoader();
        setImage(bil.carregaImagem("buraco"));
        sheet = new SpriteSheet(getImage());
        setImage(sheet.grabImage(1, 1, retangulo.width, retangulo.height));        
    }
    @Override
    public void render(Graphics2D g2d){
        setImage(sheet.grabImage(1, 1, getRetangulo().width, getRetangulo().height));        
        g2d.drawImage(getImage(), (int)getRetangulo().x,(int)getRetangulo().y,(int)getRetangulo().width,(int)getRetangulo().height, null);
    }
}
