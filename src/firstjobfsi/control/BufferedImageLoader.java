package firstjobfsi.control;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BufferedImageLoader {
	private BufferedImage imagem;
	

	public BufferedImage carregaImagem(String caminho){
			try {
				imagem = ImageIO.read(getClass().getResourceAsStream("/resources/" + caminho + ".png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return imagem;
	}
}
