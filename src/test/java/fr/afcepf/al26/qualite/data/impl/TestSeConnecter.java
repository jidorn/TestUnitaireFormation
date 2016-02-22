package fr.afcepf.al26.qualite.data.impl;

import fr.afcepf.al26.qualite.data.api.IDaoPersonne;
import fr.afcepf.al26.qualite.entities.Personne;
import fr.afcepf.al26.qualite.exception.SocialException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Classe pour tester la connection au niveau du DAO.
 */
public class TestSeConnecter {
    /**
     * mail valide dans la BDD.
     */
    private String mailValide = "user@afcepf.fr";
    /**
     * mdp valide dans la BDD.
     */
    private String mdpValide = "user";
    /**
     * mail invalide dans la BDD.
     */
    private String mailInvalide = "userInvalide@afcepf.fr";
    /**
     * retour attendu pour le test de la connexion nominal.
     */
    private Personne retourConnexionNominal;
    /**
     * DateFormat pour les comparaisons de dates et affectations.
     */
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    /**
     * identifiant du user de la base.
     */
    private final int idPersNominale = 5;

    /**
     * Default constructor
     */
    public TestSeConnecter() {
        try {
            retourConnexionNominal = new Personne(idPersNominale, "user", "user",
                    "user@afcepf.fr", "user", sdf.parse("21/12/2012"));
        } catch (ParseException paramE) {
            paramE.printStackTrace();
        }
        /**
         * Mon mock
         */
        daoPersonne = new DaoPersonne() {
            @Override
            public Personne seConnecter(String mail, String mdp) throws SocialException {
                if (mail.equals(mailInvalide)) {
                    throw new SocialException(SocialException.ErrorCode.CA_MARCHE_PAS);
                } else
                    return retourConnexionNominal;
            }
        };
    }

    /**
     * methode d'initialisation avant chaque test.
     * Regénération de la base de donnees pour la coherence des tests.
     */
    @Before
    public void setUp() {
        String pathDotBat = Thread.currentThread()
                .getContextClassLoader()
                .getResource("creeBase.bat")
                .getPath();//URL encode %20= espace
        try {
            Process process = Runtime.getRuntime().exec(pathDotBat);
            process.waitFor();
        } catch (IOException | InterruptedException paramE) {
            paramE.printStackTrace();
        }
    }

    /**
     * Classe de service à tester.
     */
    private IDaoPersonne daoPersonne;

    /**
     * test du cas nominal. personne existe.
     */
    @Test
    public void testConnectionNominale() {
        try {
            Personne retour = daoPersonne.seConnecter(mailValide, mdpValide);
            Assert.assertNotNull(retour);
            Assert.assertNotNull(retour.getId());
            Assert.assertNotNull(retour.getNom());
            Assert.assertNotNull(retour.getPrenom());
            Assert.assertNotNull(retour.getMdp());
            Assert.assertNotNull(retour.getMail());
            Assert.assertNotNull(retour.getDateNaissance());
            Assert.assertEquals(retourConnexionNominal.getId(), retour.getId());
            Assert.assertEquals(retourConnexionNominal.getNom(), retour.getNom());
            Assert.assertEquals(retourConnexionNominal.getPrenom(), retour.getPrenom());
            Assert.assertEquals(retourConnexionNominal.getMdp(), retour.getMdp());
            Assert.assertEquals(retourConnexionNominal.getMail(), retour.getMail());
            Assert.assertEquals(sdf.format(retourConnexionNominal.getDateNaissance()),
                    sdf.format(retour.getDateNaissance()));
        } catch (SocialException paramE) {
            Assert.fail("Bien tenté mais...non : " + paramE.getMessage());
        }
    }

    /**
     * test du cas d'echec. la personne n'existe pas.
     */
    @Test(expected = SocialException.class)
    public void testConnectionException() throws SocialException {
        daoPersonne.seConnecter(mailInvalide, mdpValide);
    }
}
