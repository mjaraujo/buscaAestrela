/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstjobfsi.model;

import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author marcio
 */
public class Terreno extends Entidade{
    private Terreno vizinhoCima =null;
    private Terreno vizinhoEsquerda =null;
    private Terreno vizinhoBaixo =null;
    private Terreno vizinhoDireita =null;
    private int G;
    private int H;
    private int F = 0;
    private int premio;
    private boolean buraco;
    private boolean agente;
    private Terreno pai;

    /**
     * @return the PosPai
     */
    public posPai getPosPai() {
        return PosPai;
    }

    /**
     * @param PosPai the PosPai to set
     */
    public void setPosPai(posPai PosPai) {
        this.PosPai = PosPai;
    }
  
    private enum posPai{
        C,
        E,
        B,
        D
    }
    
    private posPai PosPai;
    
    
    public Terreno(Rectangle retangulo, boolean legendado, boolean informado) {
        super(retangulo, legendado, informado);
    }

    /**
     * @return the G
     */
    public int getG() {
        return G;
    }

    /**
     * @param custo the G to set
     */
    public void setG(int custo) {
        this.G = custo;
    }

    /**
     * @return the premio
     */
    public int getPremio() {
        return premio;
    }

    /**
     * @param premio the premio to set
     */
    public void setPremio(int premio) {
        this.premio = premio;
    }

    /**
     * @return the buraco
     */
    public boolean isBuraco() {
        return buraco;
    }

    /**
     * @param buraco the buraco to set
     */
    public void setBuraco(boolean buraco) {
        this.buraco = buraco;
    }

    /**
     * @return the agente
     */
    public boolean isAgente() {
        return agente;
    }

    /**
     * @param agente the agente to set
     */
    public void setAgente(boolean agente) {
        this.agente = agente;
    }

    /**
     * @return the vizinhoCima
     */
    public Terreno getVizinhoCima() {
        return vizinhoCima;
    }

    /**
     * @param vizinhoCima the vizinhoCima to set
     */
    public void setVizinhoCima(Terreno vizinhoCima) {
        this.vizinhoCima = vizinhoCima;
    }

    /**
     * @return the vizinhoEsquerda
     */
    public Terreno getVizinhoEsquerda() {
        return vizinhoEsquerda;
    }

    /**
     * @param vizinhoEsquerda the vizinhoEsquerda to set
     */
    public void setVizinhoEsquerda(Terreno vizinhoEsquerda) {
        this.vizinhoEsquerda = vizinhoEsquerda;
    }

    /**
     * @return the vizinhoBaixo
     */
    public Terreno getVizinhoBaixo() {
        return vizinhoBaixo;
    }

    /**
     * @param vizinhoBaixo the vizinhoBaixo to set
     */
    public void setVizinhoBaixo(Terreno vizinhoBaixo) {
        this.vizinhoBaixo = vizinhoBaixo;
    }

    /**
     * @return the vizinhoDireita
     */
    public Terreno getVizinhoDireita() {
        return vizinhoDireita;
    }

    /**
     * @param vizinhoDireita the vizinhoDireita to set
     */
    public void setVizinhoDireita(Terreno vizinhoDireita) {
        this.vizinhoDireita = vizinhoDireita;
    }

    /**
     * @return the pai
     */
    public Terreno getPai() {
        return pai;
    }

    /**
     * @param pai the pai to set
     */
    public void setPai(Terreno pai) {
        this.pai = pai;
    }

    /**
     * @return the H
     */
    public int getH() {
        return H;
    }

    /**
     * @param H the H to set
     */
    public void setH(int H) {
        this.H = H;
    }

    /**
     * @return the F
     */
    public int getF() {
        return H+G;
    }

    /**
     * @param G the F to set
     */
    public void setF(int F) {
        this.F = F;
    }
    
}
