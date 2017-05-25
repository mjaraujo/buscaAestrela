/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstjobfsi.control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


/**
 *
 * @author ALUNO
 */
public class MouseInput implements MouseListener{

    private boolean botaoEsquerdo;
    private boolean botaoDireito;
    private boolean botaoMeio;
    
    @Override
    public void mouseClicked(MouseEvent e) {
        return;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        return;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch(e.getButton()){
            case 1:
                botaoEsquerdo =true;
                break;
            case 3:
                botaoDireito =true;
                break;                        
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * @return the botaoEsquerdo
     */
    public boolean isBotaoEsquerdo() {
        return botaoEsquerdo;
    }

    /**
     * @param botaoEsquerdo the botaoEsquerdo to set
     */
    public void setBotaoEsquerdo(boolean botaoEsquerdo) {
        this.botaoEsquerdo = botaoEsquerdo;
    }

    /**
     * @return the botaoDireito
     */
    public boolean isBotaoDireito() {
        return botaoDireito;
    }

    /**
     * @param botaoDireito the botaoDireito to set
     */
    public void setBotaoDireito(boolean botaoDireito) {
        this.botaoDireito = botaoDireito;
    }

    /**
     * @return the botaoMeio
     */
    public boolean isBotaoMeio() {
        return botaoMeio;
    }

    /**
     * @param botaoMeio the botaoMeio to set
     */
    public void setBotaoMeio(boolean botaoMeio) {
        this.botaoMeio = botaoMeio;
    }


}
