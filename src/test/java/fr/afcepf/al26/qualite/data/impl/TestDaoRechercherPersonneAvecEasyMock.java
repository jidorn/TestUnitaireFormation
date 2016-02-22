package fr.afcepf.al26.qualite.data.impl;

import fr.afcepf.al26.qualite.data.api.IDaoPersonne;
import fr.afcepf.al26.qualite.entities.Personne;

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
    private IDaoPersonne dao;

    /**
     * pour la recherche qui retourne une valeur.
     */
    private String nomExiste;
    /**
     * pour la recherche qui ne retourne rien.
     */
    private String nomExistePas;
    /**
     * retour pour la recherche qui retourne une valeur.
     */
    private List<Personne> retourNominal = new ArrayList<>();
    /**
     * retour pour la recherche qui ne retourne rien.
     */
    private List<Personne> retourAlternatif;

    /**
     * Pour la comparaison de Date.
     */
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

}
