package View.Bulletin;

import Constants.Colors;
import Model.Personne;
import View.HomeScreen.ModelsTable.ModelTablePersonne;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

/**
 * <b>JPanel du JFrame ajoutPersonne</b>
 * @author Leonard
 * @version 1.0
 */
public class formBulletin extends JPanel {
    ArrayList<Personne> personnes = new ArrayList<>();
    //Attributs
    JButton valider;
    JTextField nom;
    JComboBox prenom;
    JTextField moyenneMatiere;
    JTextField appreciationMatiere;
    ModelTablePersonne modele;
    Personne personne;
    int numBulletin;




    /**
     * Constructeur surcharge
     * @param personneSelect valeur attribuer a personne
     * @param numBulletin_ valeur attribuer a numBulletin
     */
    public formBulletin(Personne personneSelect, int numBulletin_) {
        numBulletin=numBulletin_;
        personne=personneSelect;
        init();
    }

    /**
     * <b>Methode d'initialisation des composants</b>
     */
    public void init(){
        appreciationMatiere = new JTextField();
        String sType[]={"Ensignants","Eleves"};
        moyenneMatiere = new JTextField();
        appreciationMatiere = new JTextField();
        moyenneMatiere = new JTextField();
        valider = new JButton("Ajouter");


        appreciationMatiere.setPreferredSize(new Dimension(300,30));
        moyenneMatiere.setPreferredSize(new Dimension(300,30));
        appreciationMatiere.setPreferredSize(new Dimension(300,30));
        moyenneMatiere.setPreferredSize(new Dimension(300,30));

        JLabel nomLabel = new JLabel("Nom : ");
        JLabel prenomLabel = new JLabel("Prénom : ");
        JLabel title = new JLabel("Bulletin officiel ");
        JLabel appreciationMatiereLabel = new JLabel("Appreciation : ");
        nomLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        prenomLabel.setFont(new Font("Verdana", Font.BOLD, 18));
        title.setFont(new Font("Verdana", Font.BOLD, 28));
        appreciationMatiereLabel.setFont(new Font("Verdana", Font.BOLD, 18));

        // Layout
        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.weighty = 0.25;
        constraints.ipadx = 80;

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        this.add(title, constraints);
        //on affiche le nom et prénom de l'élève
        constraints.gridx = 0;
        constraints.gridy = 1;
        this.add(nomLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 1;
        this.add(new JLabel(personne.getNom()), constraints);
        constraints.gridx = 2;
        constraints.gridy = 1;
        this.add(prenomLabel, constraints);
        constraints.gridx = 3;
        constraints.gridy = 1;
        this.add(new JLabel(personne.getPrenom()), constraints);

        //boucle pour afficher les i matières du bulletin
        int i=2;
        for(i=2;i<personne.getBulletins().get(numBulletin).getMatieres().size();i++)
        {
            constraints.gridx = 0;
            constraints.gridy = i;
            this.add(new JLabel("ID : "), constraints);
            constraints.gridx = 1;
            constraints.gridy = i;
            this.add(new JLabel(Integer.toString(personne.getBulletins().get(numBulletin).getMatieres().get(i).getId_detailBulletin())), constraints);
            constraints.gridx = 2;
            constraints.gridy = i;
            this.add(new JLabel("Moyenne : "), constraints);
            constraints.gridx = 3;
            constraints.gridy = i;
            this.add(new JLabel(Float.toString(personne.getBulletins().get(numBulletin).getMatieres().get(i).getMoyenne_matiere())+"/20"), constraints);
            constraints.gridx = 4;
            constraints.gridy = i;
            this.add(new JLabel("Appreciation : "), constraints);
            constraints.gridx = 5;
            constraints.gridy = i;
            this.add(new JLabel(personne.getBulletins().get(numBulletin).getMatieres().get(i).getAppreciation_detailBulletin()), constraints);
        }
        //on programme la moyenne et l'appréciation général
        constraints.gridx=0;
        constraints.gridy=i+1;
        this.add(new JLabel("Moyenne général : "), constraints);
        constraints.gridx=1;
        constraints.gridy=i+1;
        this.add(new JLabel(Float.toString(personne.getBulletins().get(numBulletin).getMoyenne())+"/20"), constraints);
        constraints.gridx=2;
        constraints.gridy=i+1;
        this.add(new JLabel("Appréciation général : "), constraints);
        constraints.gridx=3;
        constraints.gridy=i+1;
        this.add(new JLabel(personne.getBulletins().get(numBulletin).getAppreciation_bulletin()), constraints);

        //on programme le bouton de validation après les i matières
        constraints.gridx=0;
        constraints.gridy=i+2;
        this.add(valider, constraints);

        setBorder(new EmptyBorder(80, 80,80,80));
        setBackground(Colors.white);
    }

    /**
     * Getteur du bouton de validation du formulaire
     * @return le bouton valider
     */
    public JButton getValider() {
        return valider;
    }

    /**
     * Getteur du Jtextfield qui recupère l'ID
     * @return le JTextfield de l'ID
     */
    public JTextField getAppreciationMatiere() {
        return appreciationMatiere;
    }
    /**
     * Setteur du Jtextfield qui recupère l'ID
     * @param appreciationMatiere nouvelle valeur appreciationmMatiere
     */
    public void setAppreciationMatiere(JTextField appreciationMatiere) {
        this.appreciationMatiere = appreciationMatiere;
    }
    /**
     * Getteur du JComboBox qui recupère le moyenneMatiere
     * @return le JComboBox
     */
    public JTextField getMoyenneMatiere() {
        return moyenneMatiere;
    }
    /**
     * Getteur du Jtextfield qui recupère le appreciationMatiere
     * @return le JTextfield du appreciationMatiere
     */
    public JTextField getNom() {
        return appreciationMatiere;
    }
    /**
     * Getteur du Jtextfield qui recupère le moyenneMatiere
     * @return le JTextfield du moyenneMatiere
     */
    public JTextField getPrenom() {
        return moyenneMatiere;
    }

}
