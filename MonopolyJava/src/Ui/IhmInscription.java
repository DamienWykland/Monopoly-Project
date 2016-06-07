/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ui;

import Data.Joueur;
import static Ui.IHM.window2;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author dandelr
 */
public class IhmInscription {
    private static JFrame window2;
    private JPanel panel_principal,panel_joueur,retour_Demarrer;
    private IhmMenu ihmMenu;
    
    public IhmInscription(IhmMenu ihmMenu){
        this.ihmMenu=ihmMenu;
    }
    
    public ArrayList<Joueur> inscrireJoueur() {
        ArrayList<Joueur> jTemp = new ArrayList<>();  
        
 
        window2 = new JFrame("Inscrire joueur");
        window2.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);     
        window2.setSize(900, 700);
        
        
        panel_principal  = new JPanel();
        panel_principal.setLayout(new GridBagLayout());
        GridBagConstraints c0 = new GridBagConstraints();
        
  
        panel_joueur = new JPanel();
        panel_joueur.setLayout(new GridBagLayout());
        GridBagConstraints c1 = new GridBagConstraints();
        
        retour_Demarrer = new JPanel();
        retour_Demarrer.setLayout(new GridBagLayout());
        GridBagConstraints c2 = new GridBagConstraints();
        

        
        
            //****joueur****
            
                    //joueur 1
                    c1.gridx=1;
                    c1.gridy=1;
                    JLabel ljoueur1 = new JLabel("joueur 1:");
                    panel_joueur.add(ljoueur1,c1);
                    
                    c1.gridx=2;
                    c1.gridy=1;
                    TextField joueur1 = new TextField(20);
                    panel_joueur.add(joueur1,c1);
        
                    //joueur 2        
                    c1.gridx=1;
                    c1.gridy=2;
                    JLabel ljoueur2 = new JLabel("joueur 2:");
                    panel_joueur.add(ljoueur2,c1);
                    
                    c1.gridx=2;
                    c1.gridy=2;
                    TextField joueur2 = new TextField(20);
                    panel_joueur.add(joueur2,c1);
                    
                    //joueur 3                   
                    c1.gridx=1;
                    c1.gridy=3;
                    JLabel ljoueur3 = new JLabel("joueur 3:");
                    panel_joueur.add(ljoueur3,c1);
                    
                    c1.gridx=2;
                    c1.gridy=3;
                    TextField joueur3 = new TextField(20);
                    panel_joueur.add(joueur3,c1);
                    
                    //joueur 4
                    c1.gridx=1;
                    c1.gridy=4;
                    JLabel ljoueur4 = new JLabel("joueur 4:");
                    panel_joueur.add(ljoueur4,c1);
                    
                    c1.gridx=2;
                    c1.gridy=4;
                    TextField joueur4 = new TextField(20);
                    panel_joueur.add(joueur4,c1);
                    
                    //joueur 5
                    c1.gridx=1;
                    c1.gridy=5;
                    JLabel ljoueur5 = new JLabel("joueur 5:");
                    panel_joueur.add(ljoueur5,c1);
                    
                    c1.gridx=2;
                    c1.gridy=5;
                    TextField joueur5 = new TextField(20);
                    panel_joueur.add(joueur5,c1);
                    
                    //joueur 6
                    c1.gridx=1;
                    c1.gridy=6;
                    JLabel ljoueur6 = new JLabel("joueur 6:");
                    panel_joueur.add(ljoueur6,c1);
                    
                    c1.gridx=2;
                    c1.gridy=6;
                    TextField joueur6 = new TextField(20);
                    panel_joueur.add(joueur6,c1);
                    
                    
                    
                    
            //****Bouton****
                    
             //retour
            c2.gridx=1;
            c2.gridy=1;
            c2.ipadx=50;
            JButton retour = new JButton ("retour");
            retour_Demarrer.add(retour,c2);      
            
            //espace entre retour et demarer
            c2.gridx=2;
            c2.gridy=1;
            c2.ipadx=300;
            retour_Demarrer.add(new JLabel("  "),c2);        
                    
                    
            //demarer        
            c2.gridx=3;
            c2.gridy=1;
            c2.ipadx=50;
            JButton demarrer = new JButton ("demarrer");
            retour_Demarrer.add(demarrer,c2);        
                    
            
            //****assemblage****
            
            //image
            c0.gridx=1;
            c0.gridy=1;
            JLabel image = new JLabel(new ImageIcon("image_debut.png"));
            panel_principal.add(image,c0);
            
            //espace entre image et joueur
            c0.gridx=1;
            c0.gridy=2;
            c0.ipady=80;
            panel_principal.add(new JLabel("  "),c0);
            
            //joueur
            c0.gridx=1;
            c0.gridy=3;
            panel_principal.add(panel_joueur,c0);
            
            
            //espace entre joueur et bouton
            c0.gridx=1;
            c0.gridy=4;
            c0.ipady=80;
            panel_principal.add(new JLabel("  "),c0);
            
            //bouton
            c0.gridx=1;
            c0.gridy=5;
            panel_principal.add(retour_Demarrer,c0);
            
        window2.add(panel_principal);
        
        
        
        //boutton retour
        retour.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    window2.setVisible(false);
                    ihmMenu.afficherMenu();
                    
                }
             });
        
        window2.setVisible(true);            
                    

        
        
        
  /*      String s;
        Scanner sc = new Scanner(System.in);
        System.out.println("Nom de joueur: ");
        String n = sc.nextLine();

        jTemp.add(new Joueur(n));

        do {

            System.out.println("Nom de joueur: ");
            n = sc.nextLine();
            jTemp.add(new Joueur(n));

            System.out.println("Nouveau joueur? (o/n)");
            s = sc.nextLine();

        } while (s.equals("o") && jTemp.size() < 6);
*/
        return jTemp;

    }
}
