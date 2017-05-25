/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstjobfsi.model;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author ALUNO
 */
public class Entidade {
    private Rectangle retangulo;
    private BufferedImage image;
    private boolean exibirLegenda;
    private boolean exibirInfo;
    private TextoTela legenda;
    private TextoTela info;
    private Point Localizacao;
    
    
    protected SpriteSheet sheet;

    /**
     * @return the retangulo
     */
    public Rectangle getRetangulo() {
        return retangulo;
    }

    /**
     * @param retangulo the retangulo to set
     */
    public void setRetangulo(Rectangle retangulo) {
        this.retangulo = retangulo;
    }

    
    public Entidade(Rectangle retangulo, boolean legendado, boolean informado){
        this.retangulo = retangulo;
        this.exibirLegenda = legendado;
        this.exibirInfo = informado;
    }

    /**
     * @return the image
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void render(Graphics2D g2d){
        g2d.drawImage(image, (int)retangulo.x,(int)retangulo.y,(int)retangulo.width,(int)retangulo.height, null);
        if(exibirLegenda){
            legenda.render(g2d);
        }
    }

    /**
     * @return the exibirLegenda
     */
    public boolean isExibirLegenda() {
        return exibirLegenda;
    }

    /**
     * @param exibirLegenda the exibirLegenda to set
     */
    public void setExibirLegenda(boolean exibirLegenda) {
        this.exibirLegenda = exibirLegenda;
    }

    /**
     * @return the exibirInfo
     */
    public boolean isExibirInfo() {
        return exibirInfo;
    }

    /**
     * @param exibirInfo the exibirInfo to set
     */
    public void setExibirInfo(boolean exibirInfo) {
        this.exibirInfo = exibirInfo;
    }

    /**
     * @return the legenda
     */
    public TextoTela getLegenda() {
        return legenda;
    }

    /**
     * @param legenda the legenda to set
     */
    public void setLegenda(TextoTela legenda) {
        this.legenda = legenda;
    }

    /**
     * @return the info
     */
    public TextoTela getInfo() {
        return info;
    }

    /**
     * @param info the info to set
     */
    public void setInfo(TextoTela info) {
        this.info = info;
    }

    /**
     * @return the Localizacao
     */
    public Point getLocalizacao() {
        return Localizacao;
    }

    /**
     * @param Localizacao the Localizacao to set
     */
    public void setLocalizacao(Point Localizacao) {
        this.Localizacao = Localizacao;
    }
}
