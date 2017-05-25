/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstjobfsi.view;

import firstjobfsi.control.MouseInput;
import firstjobfsi.model.Agente;
import firstjobfsi.model.Arenoso;
import firstjobfsi.model.Buraco;
import firstjobfsi.model.Entidade;
import firstjobfsi.model.Pantano;
import firstjobfsi.model.Plano;
import firstjobfsi.model.Rochoso;
import firstjobfsi.model.TextoTela;
import firstjobfsi.model.Terreno;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;
import javax.swing.JPanel;

/**
 *
 * @author ALUNO
 */

public class Jogo extends JPanel implements Runnable{
    private BufferedImage image;
    int tamanho = 10;
    private Agente agente;        
    private Buraco buraco;        
    private MouseInput mouseInput = new MouseInput();
    private boolean achouCaminho;
    private int custoCaminho;
    
    ArrayList<Terreno> listaAberta= new ArrayList<>();
    ArrayList<Terreno> listaFechada= new ArrayList<>(); 
    Stack<Agente.enumDirecao> pilhaCaminho = new Stack<>();
    
    @Override
    public synchronized void addMouseListener(MouseListener l) {
        super.addMouseListener(l); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Jogo() {
        setPreferredSize(new Dimension(Janela.LARGURA,Janela.ALTURA));
        setFocusable(true);
        requestFocus();
        this.entidades = new ArrayList<>();
        this.terrenos = new ArrayList<>();
        this.textos = new ArrayList<>();
        this.addMouseListener(mouseInput);
    }
    private void tick(){}
    private boolean rodando;
    private int tempo = 0;
    private long targetTime;
    private Thread thread;
    private Graphics2D g2d;
    
    ArrayList<Entidade> entidades;
    ArrayList<Terreno> terrenos;
    ArrayList<TextoTela> textos;
    
    private void setFPS(int fps){
            targetTime = 1000/fps;
    }
    
    
    private void associaVizinhos(){
        int i=0;
        for(Terreno e:terrenos){
            //System.out.println("I:" + i);
            if(i-10>=0){
                //System.out.println("CIMA");
                e.setVizinhoCima(terrenos.get(i-10));
            }
            if(i%10>0){
                //System.out.println("ESQUERDA");
                e.setVizinhoEsquerda(terrenos.get(i-1));
            }
            if((i+1)%10>0){
                //System.out.println("DIREITA");
                
                e.setVizinhoDireita(terrenos.get(i+1));
            }
            if(i+10<=99){
                //System.out.println("BAIXO");
                
                e.setVizinhoBaixo(terrenos.get(i+10));
            }
            
            i++;
        }
    }
    
    
    private void init(){
        image = new BufferedImage(Janela.LARGURA,Janela.ALTURA, BufferedImage.TYPE_INT_ARGB);
        g2d = image.createGraphics();
        criaCenario();
        associaVizinhos();
        calcularH();
        for(Terreno t:terrenos){
            if(t.isAgente()){
                //agente.setEstado(Agente.Estados.ANDAR);
//s                buscarBuraco(t);
                
                break;
            }
            
        }
        rodando  = true;

        

    }
    
    private void calcularH(){
        int xBuraco=0;
        int yBuraco=0;
        
        //encontra buraco
        for(Terreno t:terrenos){
            if(t.isBuraco()){
                xBuraco=t.getRetangulo().x/64;
                yBuraco=t.getRetangulo().y/64;
                break;
            }
        }
        //calcula H's
        for(Terreno t:terrenos){
            if(!t.isBuraco()){
                t.setH(Math.abs(t.getRetangulo().x/64-xBuraco)+Math.abs(t.getRetangulo().y/64-yBuraco));
            }
        }
        
    }
    private void criaCenario(){
        Random gerador = new Random();
        int lin = 0;
        int col = 0;
        entidades.clear();
        terrenos.clear();
        pilhaCaminho.clear();
        listaAberta.clear();
        listaFechada.clear();
        for(int i =0;i<(tamanho*tamanho);i++){
            int numero =Math.abs( gerador.nextInt())%4; // [0,1[
            if(i%tamanho==0 && i>0) {
                lin++;
                col=0;
            }
            
            
            switch(numero){
                case 0:
                    terrenos.add(new Arenoso(new Rectangle(64*col, lin*64, 64, 64), false, false));
                    break;
                case 1:
                    terrenos.add(new Plano(new Rectangle(64*col, lin*64, 64, 64), false, false));
                    break;
                 
                case 2:
                    terrenos.add(new Rochoso(new Rectangle(64*col, lin*64, 64, 64), false, false));
                    break;
                 
                case 3:
                    terrenos.add(new Pantano(new Rectangle(64*col, lin*64, 64, 64), false, false));
                    break;
            }
            col++;
            
            
        }
        int x= gerador.nextInt(10);
        int y = gerador.nextInt(10);
        agente = new Agente(new Rectangle(64*x,64*y,64,64), false, false);
        agente.setTerreno(terrenos.get(10*y+x));
        entidades.add(agente);
        terrenos.get(10*y+x).setAgente(true);
        
              
        
        for(int i=0;i<1;i++){
            x= gerador.nextInt(10);
            y = gerador.nextInt(10);
            buraco = new Buraco(new Rectangle(64*x,64*y,64,64), false, false);
            entidades.add(buraco);
            terrenos.get(10*y+x).setBuraco(true);
            
        }
        
        
        int margemEsq = 670;
        int distY = 84;
        int espacoY = 20;
        
        entidades.add(new Arenoso(new Rectangle(margemEsq, espacoY, 64, 64), true, false));
        espacoY=espacoY+distY;
        
        entidades.add(new Plano(new Rectangle(margemEsq, espacoY,64, 64), true, false));
        espacoY=espacoY+distY;
        
        entidades.add(new Rochoso(new Rectangle(margemEsq,espacoY, 64, 64), true, false));
        espacoY=espacoY+distY;
        
        entidades.add(new Pantano(new Rectangle(margemEsq, espacoY, 64, 64), true, false));
        espacoY=espacoY+distY;
        /*
        for(int i=0;i<15;i++){
                
            x = gerador.nextInt(10);
            y = gerador.nextInt(10);
            entidades.add(new Moeda(new Rectangle(64*x+10,64*y+10,44,40), false, false));
            terrenos.get(10*y+x).setPremio(10);
        }
        */
       // calcularH();
        
    }
    @Override
    public void addNotify(){
            super.addNotify();
            thread = new Thread(this);
            thread.start();
    }
    
    

    
    private void atualizaTerrenos(){
        boolean taNaFechada=false;
        boolean taNaAberta=false;
        Terreno aux = null;
        ArrayList<Terreno> tAuxRemover = new ArrayList<>();
        ArrayList<Terreno> tAuxAdicionar = new ArrayList<>();
        
        if(listaFechada.isEmpty()){
            agente.getTerreno().setG(0);
            agente.getTerreno().setF(0);
            agente.getTerreno().setH(0);
            
            listaFechada.add(agente.getTerreno());
            if(agente.getTerreno().getVizinhoBaixo()!=null){
                agente.getTerreno().getVizinhoBaixo().setPai(agente.getTerreno());
                listaAberta.add(agente.getTerreno().getVizinhoBaixo());
            }
            if(agente.getTerreno().getVizinhoCima()!=null){
                agente.getTerreno().getVizinhoCima().setPai(agente.getTerreno());
                listaAberta.add(agente.getTerreno().getVizinhoCima());
            }
            if(agente.getTerreno().getVizinhoDireita()!=null){
                agente.getTerreno().getVizinhoDireita().setPai(agente.getTerreno());
                listaAberta.add(agente.getTerreno().getVizinhoDireita());
            }
            if(agente.getTerreno().getVizinhoEsquerda()!=null){
                agente.getTerreno().getVizinhoEsquerda().setPai(agente.getTerreno());
                listaAberta.add(agente.getTerreno().getVizinhoEsquerda());
            }
        }else{
            int menorCusto = 10000;
            Terreno tMenorCusto= null;
            for(Terreno t:listaAberta)    {
                for(Terreno u:listaFechada){
                    taNaFechada=false;
                    if((t==u)){
                        taNaFechada = true;
                        break;
                    }
                }
                if(taNaFechada)continue;
                if(t.getF()<menorCusto){
                    tMenorCusto = t;
                    menorCusto = t.getF();
                }
            }
                listaFechada.add(tMenorCusto);
                listaAberta.remove(tMenorCusto);
                for(int i=0;i<4;i++){
                    
                    switch(i){
                        case 0: aux=tMenorCusto.getVizinhoBaixo();break;
                        case 1: aux=tMenorCusto.getVizinhoCima();break;
                        case 2: aux=tMenorCusto.getVizinhoDireita();break;
                        case 3: aux=tMenorCusto.getVizinhoEsquerda();break;
                    }
                    if(aux==null)continue;
                    if(aux.isBuraco()){
                        System.out.println("Achou porrraaaaa!!!!");
                        aux.setPai(tMenorCusto);
                        achouCaminho=true;
                        Agente.enumDirecao dir=null;
                        custoCaminho=0;
                        do{
                            dir =  (aux.getPai()==aux.getVizinhoBaixo())?Agente.enumDirecao.cima:dir;
                            dir =  (aux.getPai()==aux.getVizinhoCima())?Agente.enumDirecao.baixo:dir;
                            dir =  (aux.getPai()==aux.getVizinhoDireita())?Agente.enumDirecao.esquerda:dir;
                            dir =  (aux.getPai()==aux.getVizinhoEsquerda())?Agente.enumDirecao.direita:dir;
                            custoCaminho+=aux.getG();
                            
                            pilhaCaminho.push(dir);
                            aux=aux.getPai();
                        }while(aux!=null);
                        
                    }
                    for(Terreno u:listaFechada){
                        taNaFechada=false;
                        if((aux==u)){
                            taNaFechada = true;
                            break;
                        }
                    }
                    if(taNaFechada)continue;
                    if(aux==null)continue;    
                        taNaAberta=false;
                        for(Terreno u:listaAberta){
                            if(aux==u){
                                taNaAberta = true;
                                break;
                            }
                        }
                        if(!taNaAberta)tAuxAdicionar.add(aux);
                        
                        //reparent
                        if(tMenorCusto.getPai()!=null && tMenorCusto.getG()+aux.getG()<aux.getG()){
                            tMenorCusto.setPai(aux);
                        }else{
                            aux.setPai(tMenorCusto);
                        }
            }
            for(Terreno t: tAuxAdicionar){
                listaAberta.add(t);
            }
            
        }
        
        
        
    }
    private void atualiza(){
        if(mouseInput.isBotaoEsquerdo()){
            atualizaTerrenos();
            int i=0;
            
            
            
            
          
            
            for(Terreno t:terrenos){
                if(t.isBuraco()){
                    int x = i%10;
                    int y = i/10;
                    System.out.printf("\nburaco está em (%d , %d)",x,y );
                }
                if(t.isAgente()){
                    int x = i%10;
                    int y = i/10;
                    System.out.printf("\nAgente está em (%d , %d)",x,y );
                }
                if(t.getPremio()>0){
                    int x = i%10;
                    int y = i/10;
                    System.out.printf("\ntem moeda em (%d , %d)",x,y );
                }
                i++;
                
            }
            
            mouseInput.setBotaoEsquerdo(false);
        }else if (mouseInput.isBotaoDireito()){
            init();
            mouseInput.setBotaoDireito(false);
        }
    }
    
    public void run() {
            if (rodando)return;
            init();
            // TODO Auto-generated catch block

            long startTime;
            long elapsed;
            long wait;
            while(rodando){
                    startTime=System.nanoTime();
                    update();
                    if(!rodando)break;
                    // TODO Auto-generated catch block
                    requestRender();
                    atualiza();
                    elapsed = System.nanoTime()-startTime;
                    wait = targetTime - elapsed / 1000000;
                    if(wait>0){
                            try {
                                    Thread.sleep(wait);
                            } catch (InterruptedException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                            }
                    }

            }


    }
    private void requestRender() {
        render(g2d);
        Graphics g = getGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
    }
    
    public void render(Graphics2D g2d) {
        g2d.clearRect(0, 0, Janela.LARGURA, Janela.ALTURA);
        for(Terreno t: terrenos) 	{
            g2d.setColor(Color.red);
            t.render(g2d);
            g2d.drawString("H: " + String.valueOf(t.getH()), t.getRetangulo().x, t.getRetangulo().y+10);
            g2d.drawString("G: "+ String.valueOf(t.getG()), t.getRetangulo().x+10, t.getRetangulo().y+20);
            g2d.drawString("F: "+ String.valueOf(t.getG()+t.getH()), t.getRetangulo().x+10, t.getRetangulo().y+30);
             
            
            if(t.getPai()!=null){
                if(t.getPai()==t.getVizinhoCima()){
                    g2d.drawString("o", t.getRetangulo().x + t.getRetangulo().width/2, t.getRetangulo().y+10);
                }
                if(t.getPai()==t.getVizinhoBaixo()){
                    g2d.drawString("o", t.getRetangulo().x + t.getRetangulo().width/2, t.getRetangulo().y+t.getRetangulo().height-10);
                }
                if(t.getPai()==t.getVizinhoEsquerda()){
                    g2d.drawString("o", t.getRetangulo().x +5, t.getRetangulo().height/2+t.getRetangulo().y);
                }
                if(t.getPai()==t.getVizinhoDireita()){
                    g2d.drawString("o", t.getRetangulo().x +t.getRetangulo().width-10, t.getRetangulo().height/2+t.getRetangulo().y);
                }
            }
            
            
            
        }
        g2d.drawString("Custo do caminho: "+ String.valueOf(custoCaminho), 630,400);
        if(achouCaminho){
            Terreno aux = null;
            for(Terreno t: terrenos){
                if(t.isBuraco()){
                    aux = t;
                }
            }

            do{
                if(aux.getVizinhoBaixo()!=null && aux.getPai()==aux.getVizinhoBaixo()){
                    g2d.drawLine(aux.getRetangulo().x+aux.getRetangulo().width/2,aux.getRetangulo().y+aux.getRetangulo().height/2, 
                            aux.getRetangulo().x+aux.getRetangulo().width/2, (int) (aux.getRetangulo().y+(aux.getRetangulo().height*1.5)));
                }
                if(aux.getVizinhoCima()!=null && aux.getPai()==aux.getVizinhoCima()){
                    g2d.drawLine(aux.getRetangulo().x+aux.getRetangulo().width/2,aux.getRetangulo().y+aux.getRetangulo().height/2, 
                            aux.getRetangulo().x+aux.getRetangulo().width/2, (int) (aux.getRetangulo().y-aux.getRetangulo().height *0.5));
                }
                if(aux.getVizinhoDireita()!=null && aux.getPai()==aux.getVizinhoDireita()){
                    g2d.drawLine(aux.getRetangulo().x+aux.getRetangulo().width/2,aux.getRetangulo().y+aux.getRetangulo().height/2, 
                            (int)(aux.getRetangulo().x+aux.getRetangulo().width*1.5),aux.getRetangulo().y+aux.getRetangulo().height/2);
                }
                if(aux.getVizinhoEsquerda()!=null && aux.getPai()==aux.getVizinhoEsquerda()){
                    g2d.drawLine(aux.getRetangulo().x+aux.getRetangulo().width/2,aux.getRetangulo().y+aux.getRetangulo().height/2, 
                            (int) (aux.getRetangulo().x-aux.getRetangulo().width*0.5),aux.getRetangulo().y+aux.getRetangulo().height/2);
                }
                aux=aux.getPai();
            }while(aux!=null);

        }
        
        for(Entidade e: entidades) 	{
            e.render(g2d);
        }
        
        
    }

    
    
    private void update() {
        
        if(agente.getEstado()==Agente.Estados.PARAR)
            agente.update();
        
     // TODO Auto-generated method stub
    }

}
