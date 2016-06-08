/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ui;

import Data.Carreau;
import Data.CarreauAchetable;
import Data.Evenement;
import Data.Joueur;
import Jeu.Controleur;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author perrinan
 */
public class IhmGraph extends JFrame implements Observateur {
    
    private IhmMenu ihmMenu;
    private IhmInscription ihmInscription;
    
    private IhmPlateau plateau;
    private JPanel listeJoueurs;
    private IhmDe de1;
    private IhmDe de2;
    
    private Controleur controleur;

    
    //initListeJoueur
    private JPanel panel_joueur,panel_joueur1,panel_joueur2,panel_joueur3,panel_joueur4,panel_joueur5,panel_joueur6;
    //informationTour
    private JPanel panel_information,panel_de,panel_tour;
    JButton lancer_des,fin_du_tour;

    public IhmGraph(Controleur controleur){
        super("Monopoly");
        this.setLayout(new BorderLayout());

        
        this.controleur = controleur;
        controleur.setObservateur(this);
        
        this.ihmMenu = new IhmMenu(this);

        
        this.ihmInscription = new IhmInscription(ihmMenu,this);

        
        JPanel jeu = new JPanel(new BorderLayout());
          //liste des joueurs
        JPanel listeJoueurs = new JPanel(new GridBagLayout());

        //partie plateau a remplir
        IhmPlateau plateau = new IhmPlateau();
        
        this.add(plateau, BorderLayout.CENTER);
        

        
        
        informationTour();
        initListeJoueur(/*controleur.getJoueurs()*/);
        
    }

    public Controleur getControleur() {
        return controleur;
    }
    public void setJoueurs(ArrayList<Joueur> jTemp){
        controleur.setJoueur(jTemp);
    }
    
    public void notifier(Evenement e){
    //liste des evènements à gerer
    
       
        switch(e.getType()){
            
            case 1: //1--initialisation du jeu(Afficher menu et lancer le jeu)
                ihmMenu.afficherMenu();
                //controleur.inscrireJoueur(ihmInscription.inscrireJoueur()); //devra renvoyer une arrayList de joueurs
                //this.afficherJeu();
                //de1.animation(5);
                //de2.animation(5);
                break;
            case 2:
                ihmInscription.inscrireJoueur();
                break;
            case 3:
                this.afficherJeu();
                break;
            case 4:
                ihmInscription.inscrireJoueurEr();
        }
    }


    
    public void afficherJeu() {
        setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        setSize(1350, 940);
        setVisible(true); 
        
    }

    public void menuInscription(){
        controleur.inscrireJoueur();
    }
    
    public void initListeJoueur(/*ArrayList<Joueur> joueurs*/){

            panel_joueur = new JPanel();
            panel_joueur.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            panel_joueur1 = new JPanel();
            panel_joueur1.setLayout(new GridBagLayout());
            panel_joueur2 = new JPanel();
            panel_joueur2.setLayout(new GridBagLayout());
            panel_joueur3 = new JPanel();
            panel_joueur3.setLayout(new GridBagLayout());;
            panel_joueur4 = new JPanel();
            panel_joueur4.setLayout(new GridBagLayout());
            panel_joueur5 = new JPanel();
            panel_joueur5.setLayout(new GridBagLayout());
            panel_joueur6 = new JPanel(); 
            panel_joueur6.setLayout(new GridBagLayout());
            
            HashMap<Integer,JPanel> listPanel = new HashMap();
            listPanel.put(0, panel_joueur1);
            listPanel.put(1, panel_joueur2);
            listPanel.put(2, panel_joueur3);
            listPanel.put(3, panel_joueur4);
            listPanel.put(4, panel_joueur5);
            listPanel.put(5, panel_joueur6);
            
            
            /*for (int i=0; i<5*2 ; i++){
               
                c.gridx=1;
                c.gridy=i+1;
                listPanel.get(i/2).add(new JLabel("nom joueur"+((i+1)/2))); 
                listPanel.get(i/2).setBorder(BorderFactory.createTitledBorder(""));
                panel_joueur.add(listPanel.get(i),c);
                this.add(panel_joueur,BorderLayout.WEST);
                
                
                c.gridx=1;
                c.gridy=i+2;
                c.ipady=5;
                panel_joueur.add(new JLabel("  "),c);
                        
                this.add(panel_joueur,BorderLayout.WEST);
                i=i+1;
            }*/
            
            
            
            for (int i=0; i<5 /*controleur.getJoueurs().size()*/ ; i++){
                c.gridx=1;
                c.gridy=i+1;
                listPanel.get(i).add(new JLabel("nom joueur"+(i+1))); 
                listPanel.get(i).setBorder(BorderFactory.createTitledBorder(""));
                panel_joueur.add(listPanel.get(i),c);
                this.add(panel_joueur,BorderLayout.WEST);
                
            }
            
            
        /*for (int i=0; i<5*2 ; i++){
               
             if ((i % 2) == 0){   
                c.gridx=1;
                c.gridy=i+1;
                listPanel.get(i/2).add(new JLabel("nom joueur"+((i+1)/2))); 
                listPanel.get(i/2).setBorder(BorderFactory.createTitledBorder(""));
                panel_joueur.add(listPanel.get(i),c);
                this.add(panel_joueur,BorderLayout.WEST);
            }
            else{
                
                c.gridx=1;
                c.gridy=i+1;
                c.ipady=5;
                panel_joueur.add(new JLabel("  "),c);
                        
                this.add(panel_joueur,BorderLayout.WEST);
                i=i+1;
            }
        } */   
            
    }
    
    public void informationTour(){
        panel_information = new JPanel();
        panel_information.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        panel_de = new JPanel();
        panel_de.setLayout(new GridBagLayout());
        GridBagConstraints c1 = new GridBagConstraints();
        
        panel_tour= new JPanel();
        panel_tour.setLayout(new GridBagLayout());
        GridBagConstraints c2 = new GridBagConstraints();
        


        //*****dès*****
        
        de1 = new IhmDe();
        de2 = new IhmDe();
        
        //de1
        c1.gridx=1;
        c1.gridy=1;
        panel_de.add(de1,c1);
        
        //espace entre de1 et de2
        c1.gridx=2;
        c1.gridy=1;
        c.ipadx=15;
        panel_de.add(new JLabel("  "),c1);
        
        //de2
        c1.gridx=3;
        c1.gridy=1;
        panel_de.add(de2,c1);
        
        //bouton lancer dès
        c1.gridx=1;
        c1.gridy=2;
        
        lancer_des = new JButton ("lancer les dès");
        panel_de.add(lancer_des,c1);
        
        panel_information.add(panel_de);
        
        //*****tour*****
        
        //info_tour
        c2.gridx=1;
        c2.gridy=1;
        panel_tour.add(new JLabel("info tour"));
        
        //bouton fin du tour
        c2.gridx=1;
        c2.gridy=2;
        fin_du_tour = new JButton ("fin du tour");
        panel_tour.add(fin_du_tour,c2);
   
        
        //mettre tous dans panel_information
        c.gridx=1;
        c.gridy=1;
        panel_information.add(panel_de,c);
        
        c.gridx=1;
        c.gridy=2;
        panel_information.add(panel_tour,c);
    
        this.add(panel_information,BorderLayout.EAST);
    
    
    
    
    
    
    
    }
}

    
