/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstjobfsi.view;

import static com.sun.glass.ui.Cursor.setVisible;
import java.awt.Dimension;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;

/**
 *
 * @author ALUNO
 */
public class Janela extends JFrame{
	public static int LARGURA = 840;
	public static int ALTURA = 640;
	
	
	public Janela(){
                Jogo painel = new Jogo();
		exibePainel(painel);
				
	}
	public void exibePainel(JPanel painel){
		
		this.setTitle("FSI");
		this.setContentPane(painel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		pack();
		
		setPreferredSize(new Dimension(LARGURA, ALTURA));
		setLocationRelativeTo(null);
		setVisible(true);
		painel.requestFocus();
	}
}