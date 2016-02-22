package fr.afcepf.al26.qualite.business.impl;

import fr.afcepf.al26.qualite.business.api.ISocialBusiness;
import fr.afcepf.al26.qualite.data.api.IDaoPersonne;
import fr.afcepf.al26.qualite.entities.Personne;
import fr.afcepf.al26.qualite.exception.SocialException;
import org.easymock.EasyMock;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Classe test pour le service de {@link ISocialBusiness}.
 *
 * @author Stagiaire.
 */
public class TestBusiness {
    /**
     * Service a tester.
     */
    private static ISocialBusiness business;
    /**
     * couche à mocker
     */
    private static IDaoPersonne mockData;
    /**
     * Personne pour le test personne nominal.
     */
    private static Personne personneNominale;
    /**
     * Personne pour le test personne nom null.
     */
    private static Personne personneNomNull;
    /**
     * Personne pour le test personne mail trop long.
     */
    private static Personne personneMailTropLong;
    /**
     * Personne pour le test personne mail existe deja en bdd.
     */
    private static Personne personneMailExisteDeja;
    /**
     * Retour Personne pour le test personne nominal.
     */
    private static Personne personneRetour;
    /**
     * Retour Personne pour le test personne nom null
     */
    private static Personne personneRetourNomNull;
    /**
     * Retour Personne pour le test personne mail trop long
     */
    private static Personne personneRetourMailTropLong;
    /**
     * Retour Personne pour le test personne mail existant
     */
    private static Personne personneRetourMailExistant;
    /**
     * Pour la gestion des dates
     */
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    /**
     * Dernier identifiant de la personne pour le cas nominal/
     */
    private static final int LAST_ID = 6;

    /**
     * init des tests
     */
    @BeforeClass
    public static void init() {
        try {
            personneNominale = new Personne(null,
                    "user", "user", "usermko@afcepf.fr", "user",
                    sdf.parse("21/12/2012"));
            personneNomNull = new Personne(null,
                    null, "user", "userklm@afcepf.fr", "user",
                    sdf.parse("21/12/2012"));
            personneMailTropLong = new Personne(null,
                    "user", "user", "userm@afcepf.fr", "user",
                    sdf.parse("21/12/2012"));
            for (int i = 0; i < LAST_ID; i++) {
                personneMailTropLong.setMail(
                        personneMailTropLong.getMail()
                                + personneMailTropLong.getMail()
                );
            }
            personneMailExisteDeja = new Personne(null,
                    "user", "user", "user@afcepf.fr", "user",
                    sdf.parse("21/12/2012"));
            personneRetour = new Personne(LAST_ID,
                    "user", "user", "user@afcepf.fr", "user",
                    sdf.parse("21/12/2012"));
            business = new SocialBusiness();
            mockData = EasyMock.createMock(IDaoPersonne.class);
            EasyMock.expect(mockData.ajouter(personneNominale))
                    .andReturn(personneRetour);
            EasyMock.expect(mockData.ajouter(personneNomNull))
                    .andThrow(new SocialException("pas bon",
                            SocialException.ErrorCode.PERSONNE_INCOMPLETE));
            EasyMock.expect(mockData.ajouter(personneMailTropLong))
                    .andThrow(new SocialException("pas bon",
                            SocialException.ErrorCode.CA_MARCHE_PAS));
            EasyMock.expect(mockData.verifMail(personneNominale.getMail()))
                    .andReturn(false);
            EasyMock.expect(mockData.verifMail(personneNomNull.getMail()))
                    .andReturn(false);
            EasyMock.expect(mockData.verifMail(personneMailTropLong.getMail()))
                    .andReturn(false);
            EasyMock.expect(mockData.verifMail(personneMailExisteDeja.getMail()))
                    .andReturn(true);
            ((SocialBusiness) business).setDao(mockData);
            EasyMock.replay(mockData);
        } catch (ParseException | SocialException paramE) {
            paramE.printStackTrace();
        }
    }

    /**
     * Apres tous les tests
     */
    @AfterClass
    public static void finDesTest() {
        EasyMock.verify(mockData);
    }

    @Test
    public void testAjoutNominal() {
        try {
            Personne retour = business.ajouter(personneNominale);
            Assert.assertNotNull(retour);
            Assert.assertNotNull(retour.getId());
            Assert.assertNotNull(retour.getNom());
            Assert.assertNotNull(retour.getPrenom());
            Assert.assertNotNull(retour.getMdp());
            Assert.assertNotNull(retour.getMail());
            Assert.assertNotNull(retour.getDateNaissance());
            Assert.assertEquals(LAST_ID, retour.getId().intValue());
            Assert.assertEquals(personneRetour.getNom(), retour.getNom());
            Assert.assertEquals(personneRetour.getPrenom(), retour.getPrenom());
            Assert.assertEquals(personneRetour.getMdp(), retour.getMdp());
            Assert.assertEquals(personneRetour.getMail(), retour.getMail());
            Assert.assertEquals(personneRetour.getDateNaissance(), retour.getDateNaissance());
        } catch (SocialException paramE) {
            Assert.fail("erreur qui ne devrait pas arrive car cas nominal : " + paramE.getMessage());
        }
    }

    @Test(expected = SocialException.class)
    public void testAjoutNomNull() throws SocialException {
        Personne retour = business.ajouter(personneNomNull);
    }

    @Test(expected = SocialException.class)
    public void testAjoutMailTropLong() throws SocialException {
        Personne retour = business.ajouter(personneMailTropLong);
    }

    @Test(expected = SocialException.class)
    public void testAjoutMailExitant() throws SocialException {
        Personne retour = business.ajouter(personneMailExisteDeja);
    }
}
