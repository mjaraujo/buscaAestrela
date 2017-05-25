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
public class Agente extends Entidade{
    int passoX = 1;
    int passoY = 1;
    int lastUpd= 64;

    /**
     * @return the direcao
     */
    public enumDirecao getDirecao() {
        return direcao;
    }

    /**
     * @param direcao the direcao to set
     */
    public void setDirecao(enumDirecao direcao) {
        this.direcao = direcao;
    }
    
    public enum enumDirecao{
        cima,
        esquerda,
        baixo,
        direita
    }
    
    
    private enumDirecao direcao;
    private Terreno terreno;

    private final int tamCaminhada = 64;
    private int caminhada = tamCaminhada;
    
    public void mover(){
        switch(getDirecao()){
            case cima:
                this.getRetangulo().setLocation(this.getRetangulo().x, this.getRetangulo().y-1);
                break;
            case esquerda:
                this.getRetangulo().setLocation(this.getRetangulo().x-1, this.getRetangulo().y);
                break;
            case direita:
                this.getRetangulo().setLocation(this.getRetangulo().x+1, this.getRetangulo().y);
                break;
            case baixo:
                this.getRetangulo().setLocation(this.getRetangulo().x, this.getRetangulo().y+1);
                break;
        }
        caminhada--;
        System.out.println("caminhada: " + caminhada);
        if(caminhada == 0){
            estado = Estados.PARAR;
            caminhada = tamCaminhada;
        }
    }
    
    /**
     * @return the terreno
     */
    public Terreno getTerreno() {
        return terreno;
    }

    /**
     * @param terreno the terreno to set
     */
    public void setTerreno(Terreno terreno) {
        this.terreno = terreno;
    }

    /**
     * @return the estado
     */
    public Estados getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(Estados estado) {
        this.estado = estado;
    }
    public enum Estados{
        PARAR,
        ANDAR,
        GIRAR
    }
    
    
    
    
    
    
    private Estados estado = Estados.PARAR;
    
    public Agente(Rectangle retangulo, boolean legendado, boolean informado) {
        super(retangulo, legendado, informado);
        BufferedImageLoader bil = new BufferedImageLoader();
        
        setImage(bil.carregaImagem("men"));
        sheet = new SpriteSheet(getImage());
        setImage(sheet.grabImage(3, 2, retangulo.width, retangulo.height));        
    }
    
    public void update(){
        
        switch(estado){
            case ANDAR:
                mover();
                break;
           
        }
        
        
        
        
        if((getEstado()==Estados.ANDAR)&&(lastUpd>=12)){
            lastUpd=0;
            passoX=passoX%5;
            if(passoX%5==0){
                passoY=passoY%3;
                passoY++;
            }
            passoX++;
        }    
        lastUpd++;
    }
    
    @Override
    public void render(Graphics2D g2d){
        
        
        
        if(getRetangulo().y<-64){
            getRetangulo().setLocation(getRetangulo().x, 640);
        }
        if(getRetangulo().y>640){
            getRetangulo().setLocation(getRetangulo().x, -64);
        }
        if(getRetangulo().x<-64){
            getRetangulo().setLocation(640,getRetangulo().y );
        }
        if(getRetangulo().y>640){
            getRetangulo().setLocation(-64, getRetangulo().y);
        }
        setImage(sheet.grabImage(passoX, passoY, getRetangulo().width, getRetangulo().height));        
        g2d.drawImage(getImage(), (int)getRetangulo().x,(int)getRetangulo().y,(int)getRetangulo().width,(int)getRetangulo().height, null);
        
        
    }
    
    
}
