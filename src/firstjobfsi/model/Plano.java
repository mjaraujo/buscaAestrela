/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstjobfsi.model;

import firstjobfsi.control.BufferedImageLoader;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * @author ALUNO
 */
public class Plano extends Terreno{
    
    public Plano(Rectangle retangulo, boolean legendado, boolean informado) {
        super(retangulo, legendado, informado);
        BufferedImageLoader bil = new BufferedImageLoader();
        setG(1);
        setImage(bil.carregaImagem("tudo"));
        sheet = new SpriteSheet(getImage());
        setImage(sheet.grabImage(6, 1, retangulo.width, retangulo.height));  
        setLegenda(new TextoTela("Plano: " + getG(), new Point(retangulo.getLocation().x,retangulo.y+retangulo.height-30), Color.red));
    }
    
    
}
