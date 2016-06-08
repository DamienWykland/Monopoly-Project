package Jeu;
import Ui.IHM;
import Data.*;
import Ui.IhmGraph;
import Ui.Observateur;
import java.util.ArrayList;



public class Controleur {
    
    //Attributs
    private IHM ihm;
    private Monopoly monopoly;
    private int c;
    
    private Observateur ihmGraph;
    
    public void setObservateur(Observateur ob){
        this.ihmGraph = ob;
    }
    
    //Constructeurs
    public Controleur() throws InterruptedException {
       
        ihm = new IHM(this);
        
        ihmGraph = new IhmGraph(this);
            
        
        
        monopoly = new Monopoly();
        c = 1;
        
        //ihmGraph.notifier(new Evenement(1));
        //while( c != 3) { 
            //c = ihm.afficherMenu();
           
            //if (c == 1) {
                //this.inscrireJoueur();
           //}
            //else if (c == 2) {
                //if (monopoly.getJoueurs().size()>=2 && monopoly.getJoueurs().size()<=6) {
                    this.lancerPartie();
                //}
    //else {
                   // ihm.nbJoueursMauvais();
                //}
                
            //}
       // }
         
    }
    
    //Méthodes
    public void inscrireJoueur() {
        monopoly.inscrireJoueur(ihm.inscrireJoueur());
    }
    
    
    public void lancerPartie() {
        ihmGraph.notifier(new Evenement(1));
        //essayer de pouvoir relance la partie
        
        //ihmGraph.afficherJeu(); ///test graph
        
        int joueursVivants;
        do{
            joueursVivants=0;
        
            for(Joueur j: monopoly.getJoueurs()){
                if(!j.getPerdu()){
                    joueursVivants++;
                }
            }
            
            for(Joueur j : monopoly.getJoueurs()) {
                if(!j.getPerdu()){
                    do {    
                        ihm.lancerDes(j);
                        jouerUnCoup(j);
                            if(j.getCash() < 0){
                                j.setD1(1);//on assure la sortie de la boucle
                                j.setD2(0);
                                j.perdu();
                                ihm.joueurPerdu(j);//retire droit de proprio et indique que le joueur a perdu
                            }
                    }while(j.getD1() == j.getD2());
                }
            }
            
        }while(joueursVivants > 1);
        ihm.partiePerdue();
    }
    public Carreau lancerDesAvancer(Joueur j){
         ihm.afficherDebutTour(j);
          
        
                //System.out.println(j.getPositionCourante().getNumero());
                //j.getPositionCourante().getNumero();
                j.setD1(roll());
                j.setD2(roll());
                ihm.afficherDe(j.getD1(),j.getD2());
                
                //En cas de double, on sort le joueur de prison (inutile si le jouer était déjà libre)
                if (j.getD1() == j.getD2()) {
                    j.setPrison(false);
                }
                //si au troisième tour toujours pas de double, le joueur paie et peut partir
                if(j.enPrison() && j.getCompteurPrison() == 2) {
                    j.setPrison(false);
                    j.payerLoyer(50);
                }
                
                //Un joueur en prison n'avance pas
                if(!j.enPrison()) {
                
                    //si on passe par la case départ
                    if(j.getPositionCourante().getNumero()-1 + j.getD1() + j.getD2() >= 40){
                        passageDepart(j);
                        ihm.passageDepart(j);
                        j.setCarreau(monopoly.getCarreaux().get(  (j.getPositionCourante().getNumero()-1 + j.getD1()+ j.getD2()) - 40 ));
                        //System.out.println("pos: "+j.getPositionCourante().getNumero());
                        //System.out.println("cash :" + j.getCash());

                    }
                    //coup normal
                    else {
                        j.setCarreau(monopoly.getCarreaux().get(j.getPositionCourante().getNumero()-1 + j.getD1() + j.getD2()));
                        //System.out.println("pos: "+j.getPositionCourante().getNumero());
                    }
                }
                //On compte le nombre de tour que le joueur passe en prison
                else if(j.enPrison()) {
                    j.setCompteurPrison(j.getCompteurPrison()+1);
                }
                return j.getPositionCourante();
    }
    
    
    
    
    
    
    
    
    
    
    
    public void jouerUnCoup(Joueur j){
        Carreau c = lancerDesAvancer(j);
        //si le joueur arrive sur un carreau achetable
        if(c instanceof CarreauAchetable){
            Evenement e = ((CarreauAchetable) c).action(j);
            //reponse = false n'a pas voulu acheter
            //reponse = true a voulu acheter
            switch (e.getType()) {
                case 1:
                    boolean reponse = ihm.afficherProposition(e);
                    if (reponse){
                        ((CarreauAchetable) c).setProprio(j);
                        j.payerLoyer(((CarreauAchetable) c).getPrixAchat());
                        //ajout de la propriete dans la bonne liste
                        if(c instanceof Gare){
                            j.ajouterGare((Gare) c);
                        }
                        else if(c instanceof Compagnie){
                            j.ajouterCompagnie((Compagnie) c);
                        }
                        else if(c instanceof ProprieteAConstruire){
                            j.ajouterPropriete((ProprieteAConstruire) c);
                        }
                        ihm.afficherAchat(e);
                        //System.out.println("Argent joueur:"+ j.getCash());//test
                        //System.out.println(c.getNom() +":"+((CarreauAchetable) c).getProprietaire().getName());//test
                    }   break;
                case 2:
                    //deduction du loyer

                        if(j.getCash()> ((CarreauAchetable) c).calculLoyer(j.getD1(),j.getD2())){ //si le joueur peux payer

                            j.payerLoyer(((CarreauAchetable) c).calculLoyer(j.getD1(),j.getD2())); //le joueur paye
                            ((CarreauAchetable) c).getProprietaire().recevoirLoyer(((CarreauAchetable) c).calculLoyer(j.getD1(),j.getD2())); //le propriétaire recoit le loyer
                        }
                        else{
                            ((CarreauAchetable) c).getProprietaire().recevoirLoyer(j.getCash()); //le propriétaire recoit l'argent restant du joueur
                            e.setLoyerCase(j.getCash());
                            j.payerLoyer(((CarreauAchetable) c).calculLoyer(j.getD1(),j.getD2()));
                        }
        
                    ihm.afficherDebit(e);
                    break;
                case 3:
                    //j == proprio
                    ihm.afficherJproprio(e);
                    break;
                case 4:
                    ihm.afficherAchatImp(e);
                default:
                    break;
            }
            
        }
        else if(c instanceof ImpotsEtTaxes){
            //Si on tombe sur la case Impots ou sur la case Taxes, le joueur paie le montant indiqué
            j.payerLoyer(((ImpotsEtTaxes) c).getMontant());
            
            //COMMUNICATION AVEC IHM
            Evenement e = new Evenement(c.getNom());
            ihm.afficherPassage(e);
            System.out.println("Vous avez payé des impots ou des taxes");      
                    
        }
        else if(c instanceof AllerEnPrison){
            //Si le joueur tombe sur cette case, il va en Prison
            j.setPrison(true);
            j.setCompteurPrison(0);
            ((Prison)monopoly.getCarreaux().get(10)).addJoueurEnPrison(j);
            j.setCarreau((Prison)monopoly.getCarreaux().get(10));
            
            
            //COMMUNICATION AVEC IHM
            Evenement e = new Evenement(c.getNom());
            ihm.afficherPassage(e);
            System.out.println("Vous êtes en prison");
            
        }
        else {
            Evenement e = new Evenement(c.getNom());
            ihm.afficherPassage(e);
        }
    }
    
    
    private int roll() {
        return (int) (Math.random() * (7 - 1)) + 1;
    }
    
    private void passageDepart(Joueur j) {
        j.gain(((Depart)monopoly.getCarreaux().get(0)).getGainDepart());
    }

    public ArrayList<Joueur> getJoueurs() {
        return monopoly.getJoueurs();
    }
    
  
}
