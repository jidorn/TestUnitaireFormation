package fr.afcepf.al26.qualite.data.impl;

import fr.afcepf.al26.qualite.data.api.IDaoPersonne;
import fr.afcepf.al26.qualite.entities.Personne;
import org.easymock.EasyMock;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe pour tester la recherche de {@link Personne}.
 */
public class TestDaoRechercherPersonneAvecEasyMock {
    /**
     * service a tester
     */
    private static IDaoPersonne dao;

    /**
     * pour la recherche qui retourne une valeur.
     */
    private static String nomExiste;
    /**
     * pour la recherche qui ne retourne rien.
     */
    private static String nomExistePas;
    /**
     * retour pour la recherche qui retourne une valeur.
     */
    private static List<Personne> retourNominal = new ArrayList<>();
    /**
     * retour pour la recherche qui ne retourne rien.
     */
    private static List<Personne> retourAlternatif;

    /**
     * Pour la comparaison de Date.
     */
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * taille de la liste retour nominal.
     */
    private static int tailleListeNominal = 1;
    /**
     * taille de la liste retour alternative.
     */
    private static int tailleListeAlternative = 0;
    /**
     * {@link Personne} de la liste nominale.
     */
    private static Personne personneNominale;
    /**
     * identifiant de la personne nominale
     */
    private static final int idPersonneNominale = 5;

    /**
     * Initilisation des Tests
     */
    @BeforeClass
    public static void initialisationDesTest() {
        try {
            nomExiste = "user";
            nomExistePas = "userpasexiste";
            personneNominale = new Personne(idPersonneNominale,
                    "user", "user", "user@afcepf.fr", "user",
                    sdf.parse("21/12/2012"));
            retourNominal.add(personneNominale);
            retourAlternatif = new ArrayList<>();
            dao = EasyMock.createMock(IDaoPersonne.class);
            EasyMock.expect(dao.rechercher(nomExiste))
                    .andReturn(retourNominal);
            EasyMock.expect(dao.rechercher(nomExistePas))
                    .andReturn(retourAlternatif);
            EasyMock.replay(dao);
        } catch (ParseException paramE) {
            paramE.printStackTrace();
        }
    }

    /**
     * Apres tous les tests.
     */
    @AfterClass
    public static void finDesTests() {
        EasyMock.verify(dao);
    }

    /**
     * Test d'une recherche qui retourne une valeur
     */
    @Test
    public void testRecherchePersonneNominal() {
        List<Personne> retour = dao.rechercher(nomExiste);
        Assert.assertNotNull(retour);
        Assert.assertEquals(tailleListeNominal, retour.size());
        Personne p = retour.get(0);
        Assert.assertNotNull(p);
        Assert.assertNotNull(p.getId());
        Assert.assertNotNull(p.getNom());
        Assert.assertNotNull(p.getPrenom());
        Assert.assertNotNull(p.getMail());
        Assert.assertNotNull(p.getMdp());
        Assert.assertNotNull(p.getDateNaissance());
        Assert.assertEquals(personneNominale.getId(), p.getId());
        Assert.assertEquals(personneNominale.getNom(), p.getNom());
        Assert.assertEquals(personneNominale.getPrenom(), p.getPrenom());
        Assert.assertEquals(personneNominale.getMail(), p.getMail());
        Assert.assertEquals(personneNominale.getMdp(), p.getMdp());
        Assert.assertEquals(sdf.format(personneNominale.getDateNaissance()), sdf.format(p.getDateNaissance()));
    }

    /**
     * Test d'une recherche qui ne retourne rien
     */
    @Test
    public void testRecherchePersonneAlternative() {
        List<Personne> retour = dao.rechercher(nomExistePas);
        Assert.assertNotNull(retour);
        Assert.assertEquals(tailleListeAlternative, retour.size());
    }
}
