package fr.afcepf.al26.qualite.business.impl;

import fr.afcepf.al26.qualite.business.api.ISocialBusiness;
import fr.afcepf.al26.qualite.entities.Personne;
import org.junit.BeforeClass;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Classe test pour le service de {@link ISocialBusiness}.
 * @author Stagiaire.
 */
public class TestBusiness {
    /**
     * Service a tester.
     */
    private static ISocialBusiness business;
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
    public  static void init(){
        try {
            personneNominale = new Personne(null,
                    "user", "user", "user@afcepf.fr", "user",
                    sdf.parse("21/12/2012"));
            personneNomNull = new Personne(null,
                    null, "user", "user@afcepf.fr", "user",
                    sdf.parse("21/12/2012"));
            personneMailTropLong = new Personne(null,
                    "user", "user", "user@afcepf.fr", "user",
                    sdf.parse("21/12/2012"));
            for (int i = 0; i < LAST_ID; i++) {
                personneMailTropLong.setMail(
                        personneMailTropLong.getMail()
                        +personneMailTropLong.getMail()
                );
            }
            personneMailExisteDeja = new Personne(null,
                    "user", "user", "user@afcepf.fr", "user",
                    sdf.parse("21/12/2012"));
            personneRetour = new Personne(LAST_ID,
                    "user", "user", "user@afcepf.fr", "user",
                    sdf.parse("21/12/2012"));
        } catch (ParseException paramE) {
            paramE.printStackTrace();
        }
    }
}
