package fr.afcepf.al26.qualite.test;

import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * Test d'execution de la re-generation de la  BDD pour les tests.
 * @author Stagiaire.
 */
public class TestDotBat {
    /**
     * default constructor.
     */
    private TestDotBat(){}

    /**
     * Logger for syso.
     */
    private static Logger log = Logger.getLogger(TestDotBat.class);

    /**
     * application entry point.
     * @param args arguments.
     */
    public static void main (String[] args){
        log.info("Debut du test...");
        String pathDotBat = Thread.currentThread()
                .getContextClassLoader()
                .getResource("creeBase.bat")
                .getPath();//URL encode %20= espace
        log.info(pathDotBat);
        try {
            Process process = Runtime.getRuntime().exec(pathDotBat);
            process.waitFor();
        } catch (IOException | InterruptedException paramE) {
            paramE.printStackTrace();
            log.error("Pas bon !");
        }
        log.info("Fin du test...");
    }
}
