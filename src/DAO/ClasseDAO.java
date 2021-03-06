package DAO;

import Model.AnneeScolaire;
import Model.Classe;
import Model.Ecole;
import Model.Personne;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import static Constants.ConstConnexion.urlDatabase;

/**
 * <b>DAO Classe permettant d'acceder a la bdd de Classe</b>
 * @author Thomas
 * @version 1.0
 */

//CTRL + SHIFT + O pour générer les imports
public class ClasseDAO extends com.sdz.dao.DAO<Classe> {
    public int taille;

    /**
     * <b>Constructeur par defaut</b>
     */
    public ClasseDAO() {
        super();
        try {
            // chargement driver "com.mysql.jdbc.Driver"
            Class.forName("com.mysql.jdbc.Driver");

            //création d'une connexion JDBC à la base
            this.connect = DriverManager.getConnection(urlDatabase, "root", "");

            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT COUNT(*) AS taille FROM Classe");
            result.first();
            this.taille = result.getInt("taille");

            this.connect.close();
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * <b>Methode de maj de classe</b>
     * @param obj objet class contenant les information a maj
     */
    public void update(Classe obj) {

    }

    /**
     * <b>Methode permettant de recuperer les donnees de la bdd Classe et de retourner un objet de type Classe</b>
     * @param id ID permettant de selectionner dans la table la classe voulue
     * @return objet de type Classe
     * @throws SQLException exception
     */
    @Override
    public Classe Connection(int id) throws SQLException {
        Classe Classe = new Classe();

        try {
            // chargement driver "com.mysql.jdbc.Driver"
            Class.forName("com.mysql.jdbc.Driver");


            //création d'une connexion JDBC à la base
            this.connect = DriverManager.getConnection(urlDatabase, "root", "");

            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Classe WHERE ID_Classe =" + id);
            if (result.first())
                Classe = new Classe(result.getInt("ID_Classe"),
                        result.getString("Niveau")
                );

            result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Eleve WHERE ID_Classe =" + Classe.getId_classe());
            while (result.next()) {
                Classe.get_effectif().add(new Personne(result.getInt("ID_Eleve"),
                        "eleve",
                        result.getString("Nom"),
                        result.getString("Prenom"),
                        result.getString("Login"),
                        result.getString("Mdp"))
                );
            }

                result = this.connect.createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Classe WHERE ID_Classe =" + Classe.getId_classe());
                if (result.first()) {
                    com.sdz.dao.DAO<AnneeScolaire> anneeScolaireDAO = new AnneeScolaireDAO();
                    Classe.setAnneeScolaire(anneeScolaireDAO.Connection(result.getInt("ID_AnneeScolaire")));
                }

                result = this.connect.createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Classe WHERE ID_Classe =" + Classe.getId_classe());
                if (result.first()) {
                    com.sdz.dao.DAO<Ecole> ecoleDAO = new EcoleDAO();
                    Classe.set_ecole(ecoleDAO.Connection(result.getInt("ID_Ecole")));
                }

            this.connect.close();
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return Classe;
    }

}