package fr.afcepf.al26.qualite.entities;

import java.util.Date;

/**
 * Classe entitée representant l'entitée message dans le serveur de données.
 */
public class Message {
    /**
     * L'identifiant du {@link Message}.
     */
    private Integer id;
    /**
     * la date de creation du {@link Message}.
     */
    private Date dateCreation;
    /**
     * le contenu du {@link Message}.
     */
    private String contenu;
    /**
     * l'expediteur du {@link Message}.
     */
    private Personne expediteur;
    /**
     * le destinataire du {@link Message}.
     */
    private Personne destinataire;

    /**
     * constructeur par defaut.
     */
    public Message() {
    }

    /**
     * constructeur with params.
     * @param paramId id.
     * @param paramDateCreation creation.
     * @param paramContenu contenu.
     * @param paramExpediteur expediteur.
     * @param paramDestinataire destinataire.
     */
    public Message(Integer paramId,
                   Date paramDateCreation, String paramContenu,
                   Personne paramExpediteur, Personne paramDestinataire) {
        id = paramId;
        dateCreation = paramDateCreation;
        contenu = paramContenu;
        expediteur = paramExpediteur;
        destinataire = paramDestinataire;
    }

    /**
     * le getter.
     * @return id.
     */
    public Integer getId() {
        return id;
    }

    /**
     * le setter.
     * @param paramId id.
     */
    public void setId(Integer paramId) {
        id = paramId;
    }

    /**
     * le getter.
     * @return date.
     */
    public Date getDateCreation() {
        return dateCreation;
    }

    /**
     * le setter.
     * @param paramDateCreation date.
     */
    public void setDateCreation(Date paramDateCreation) {
        dateCreation = paramDateCreation;
    }

    /**
     * le getter.
     * @return contenu.
     */
    public String getContenu() {
        return contenu;
    }

    /**
     * le setter.
     * @param paramContenu contenu.
     */
    public void setContenu(String paramContenu) {
        contenu = paramContenu;
    }

    /**
     * le getter.
     * @return expediteur.
     */
    public Personne getExpediteur() {
        return expediteur;
    }

    /**
     * le setter.
     * @param paramExpediteur expediteur.
     */
    public void setExpediteur(Personne paramExpediteur) {
        expediteur = paramExpediteur;
    }

    /**
     * le getter.
     * @return destinataire.
     */
    public Personne getDestinataire() {
        return destinataire;
    }

    /**
     * le setter.
     * @param paramDestinataire destinataire.
     */
    public void setDestinataire(Personne paramDestinataire) {
        destinataire = paramDestinataire;
    }
}
