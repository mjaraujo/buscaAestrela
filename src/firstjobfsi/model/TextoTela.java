/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstjobfsi.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 *
 * @author ALUNO
 */
public class TextoTela{
    private String texto;
    private Point posicao;
    private Color cor;

    /**
     * @return the texto
     */
    public String getTexto() {
        return texto;
    }

    /**
     * @param texto the texto to set
     */
    public void setTexto(String texto) {
        this.texto = texto;
    }

    /**
     * @return the posicao
     */
    public Point getPosicao() {
        return posicao;
    }

    /**
     * @param posicao the posicao to set
     */
    public void setPosicao(Point posicao) {
        this.posicao = posicao;
    }

    /**
     * @return the cor
     */
    public Color getCor() {
        return cor;
    }

    /**
     * @param cor the cor to set
     */
    public void setCor(Color cor) {
        this.cor = cor;
    }
    
    public TextoTela(){
    }

    public TextoTela(String texto, Point posicao, Color cor) {
        this.texto = texto;
        this.posicao = posicao;
        this.cor = cor;
    }
    
    
    public void render(Graphics2D g2d){
        g2d.setColor(cor);
        g2d.drawString(texto, posicao.x, posicao.y);
    }
}
