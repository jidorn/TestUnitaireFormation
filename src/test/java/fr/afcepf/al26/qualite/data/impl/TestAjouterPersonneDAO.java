package fr.afcepf.al26.qualite.data.impl;

import fr.afcepf.al26.qualite.data.api.IDaoPersonne;
import fr.afcepf.al26.qualite.entities.Personne;
import fr.afcepf.al26.qualite.exception.SocialException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;

/**
 * Classe de test pour tester l'ajout d'une entitée {@link Personne} dans la bdd.
 */
public class TestAjouterPersonneDAO {
    /**
     * constante d'instance pour la date des {@link Personne}à tester.
     */
    private final Date datePourTest = new Date();
    /**
     * {@link Personne} pout l'envoi à l'ajout Nominal.
     */
    private Personne personneNominal = new Personne(null,"nom","prenom","mail@mail.fr","mdp",datePourTest);
    /**
     * {@link Personne} pout l'envoi à l'ajout nom null
     */
    private Personne personneNomNull= new Personne(null,null,"prenom","mail@mail.fr","mdp",datePourTest);
    /**
     * {@link Personne} pout l'envoi à l'ajout nom trop long
     */
    private Personne personneNomTropLong = new Personne(null,"quelconque","prenom","mail@mail.fr","mdp",datePourTest);
    /**
     * Prochain identifiant par rapport à la BDD de tests.
     */
    private final Integer lastId=6;
    /**
     * Classe à test.
     * Penser a definir son implementation pour la suite.
     */
    private IDaoPersonne dao = new DaoPersonne();
    /**
     * compteur pour les tests
     */
    private static final int DIX = 10;
    /**
     * Personne attendue apres l'ajout nominal.
     */
    private Personne personneRetourNominal = new Personne(null,"nom","prenom","mail@mail.fr","mdp",datePourTest);
    /**
     * methode d'initialisation avant chaque test.
     * Regénération de la base de donnees pour la coherence des tests
     *
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
        for (int i = 0; i < DIX; i++) {
            personneNomTropLong.setNom(personneNomTropLong.getNom()+personneNomTropLong.getNom());
        }
    }

    /**
     * test cas nominal
     */
    @Test
    public void testAjoutNominal() {
        try {
            Personne retour = dao.ajouter(personneNominal);
            Assert.assertNotNull(retour);
            Assert.assertNotNull(retour.getId());
            Assert.assertNotNull(retour.getNom());
            Assert.assertNotNull(retour.getPrenom());
            Assert.assertNotNull(retour.getMdp());
            Assert.assertNotNull(retour.getMail());
            Assert.assertNotNull(retour.getDateNaissance());
            Assert.assertEquals(lastId,retour.getId());
            Assert.assertEquals(personneRetourNominal.getNom(),retour.getNom());
            Assert.assertEquals(personneRetourNominal.getPrenom(),retour.getPrenom());
            Assert.assertEquals(personneRetourNominal.getMdp(),retour.getMdp());
            Assert.assertEquals(personneRetourNominal.getMail(),retour.getMail());
            Assert.assertEquals(personneRetourNominal.getDateNaissance(),retour.getDateNaissance());
        } catch (SocialException paramE) {
            Assert.fail("erreur qui ne devrait pas arrive car cas nominal : " + paramE.getMessage());
        }
    }

    /**
     * test cas nom trop long
     */
    @Test
    public void testAjoutNomTropLong() {
        try {
            Personne pers = dao.ajouter(personneNomTropLong);
            Assert.fail("ca doit pas fonctionner");
        } catch (SocialException paramE) {
            Assert.assertTrue("ca plante donc ca fonctionne",true);
        }
    }

    /**
     * test cas nom null.
     * @throws SocialException exception attendue.
     */
    @Test(expected = SocialException.class)
    public void testAjoutNomNull() throws SocialException{
        dao.ajouter(personneNomNull);
    }
}
