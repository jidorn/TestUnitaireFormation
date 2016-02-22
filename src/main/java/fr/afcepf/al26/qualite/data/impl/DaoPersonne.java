package fr.afcepf.al26.qualite.data.impl;

import fr.afcepf.al26.qualite.data.api.IDaoPersonne;
import fr.afcepf.al26.qualite.entities.Personne;
import fr.afcepf.al26.qualite.exception.SocialException;
import fr.afcepf.al26.qualite.util.SocialDataSource;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

/**
 * Classe implémentant les methodes définie par l'interface
 * de l'acces aux données.
 */
public class DaoPersonne implements IDaoPersonne {
    /**
     * Indice du parametre nom.
     */
    private final int indiceUneRequete = 1;
    /**
     * Indice du parametre prenom.
     */
    private final int indiceDeuxRequete = 2;
    /**
     * Indice du parametre mail.
     */
    private final int indiceTroisRequete = 3;
    /**
     * Indice du parametre mdp.
     */
    private final int indiceQuatreRequete = 4;
    /**
     * Indice du parametre Naissance.
     */
    private final int indiceCinqRequete = 5;
    /**
     * indice de la cle dans le tableau de cles generees.
     */
    private final int indiceCleGenere = 5;
    /**
     * pour la connexion.
     */
    private Connection cnx;
    /**
     * pour la dataSource.
     */
    private DataSource dataSource = new SocialDataSource();
    /**
     * requete d'ajout de {@link Personne} dans la BDD.
     */
    private final String requeteAjout = "INSERT INTO personne"
            + "(nom,prenom,mail,mdp,date_naissance) values(?,?,?,?,?)";

    /**
     * Logger pour le cheminement.
     */
    private Logger log = Logger.getLogger(getClass());

    @Override
    public Personne ajouter(Personne paramPersonne) throws SocialException {
        SocialException se = null;
        try {
            log.info("début de la methode ajout");
            cnx = dataSource.getConnection();
            PreparedStatement preparedStatement = cnx
                    .prepareStatement(requeteAjout,
                            Statement.RETURN_GENERATED_KEYS);
            preparedStatement.
                    setString(indiceUneRequete, paramPersonne.getNom());
            preparedStatement.
                    setString(indiceDeuxRequete, paramPersonne.getPrenom());
            preparedStatement.
                    setString(indiceTroisRequete, paramPersonne.getMail());
            preparedStatement.
                    setString(indiceQuatreRequete, paramPersonne.getMdp());
            preparedStatement.
                    setDate(indiceCinqRequete, new Date(paramPersonne
                            .getDateNaissance().getTime()));
            int nb = preparedStatement.executeUpdate();
            log.info("le nombre : " + nb);
            if (nb == 1) {
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                resultSet.next();
                int idPers = resultSet.getInt(1);
                paramPersonne.setId(idPers);
            }
        } catch (SQLException paramE) {
            log.debug("erreur lors de l'ajout : " + paramE.getMessage());
            se = new SocialException("erreur lors de l'ajout",
                    SocialException.ErrorCode.CA_MARCHE_PAS);
        } finally {
            try {
                cnx.close();
            } catch (SQLException paramE) {
                log.debug("erreur lors de l'ajout");
                se = new SocialException("erreur lors de l'ajout",
                        SocialException.ErrorCode.CA_MARCHE_PAS);
            }
        }
        if (se != null) {
            throw se;
        }
        log.info("le pers : " + paramPersonne.getId());
        log.info("le pers : " + paramPersonne.getNom());
        return paramPersonne;
    }

    @Override
    public Boolean verifMail(String mail) {
        SocialException se = null;
        int nb = 1;
        String requete = "SELECT COUNT(id) FROM personne WHERE mail LIKE ?";
        try {
            cnx = dataSource.getConnection();
            PreparedStatement pstmt = cnx.prepareStatement(requete);
            pstmt.setString(indiceUneRequete, mail);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            nb = rs.getInt(indiceCleGenere);
        } catch (SQLException e) {
            e.printStackTrace();
            se = new SocialException("probleme lors de la verif mail", SocialException.ErrorCode.CA_MARCHE_PAS);
        }
        if (se != null) {
            try {
                throw se;
            } catch (SocialException paramE) {
                paramE.printStackTrace();
            }
        }
        return nb != 0;
    }

    @Override
    public Personne seConnecter(String mail, String mdp)
            throws SocialException {
        return null;
    }

    @Override
    public List<Personne> rechercher(String nom) {
        return null;
    }
}
