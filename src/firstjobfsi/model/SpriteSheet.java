/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstjobfsi.model;

import java.awt.image.BufferedImage;

/**
 *
 * @author marcio
 */
public class SpriteSheet {
	private BufferedImage image;
	
	public SpriteSheet(BufferedImage imagem) {
		// TODO Auto-generated method stub
		this.image = imagem;

	}
	public BufferedImage grabImage(int col, int lin, int largura, int altura){
		BufferedImage img = image.getSubimage((col*largura)-largura, (lin*altura)-altura,largura, altura);
		return img;	
	}
}
