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
public class Rochoso extends Terreno {

    public Rochoso(Rectangle retangulo, boolean legendado, boolean informado) {
        super(retangulo, legendado, informado);
        BufferedImageLoader bil = new BufferedImageLoader();
        setG(10);
        setImage(bil.carregaImagem("tudo"));
        sheet = new SpriteSheet(getImage());
        setImage(sheet.grabImage(1, 2, retangulo.width, retangulo.height));
        setLegenda(new TextoTela("Rochoso: " + getG(), new Point(retangulo.getLocation().x, retangulo.y + retangulo.height + 10), Color.red));

    }

}
