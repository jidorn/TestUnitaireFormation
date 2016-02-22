package fr.afcepf.al26.qualite.entities;

import java.util.Date;

/**
 * Classe entitée representant l'entitée personne dans le serveur de données.
 *
 * @author Stagiaire.
 */
public class Personne {
    /**
     * L'identifiant unique de la {@link Personne}.
     */
    private Integer id;
    /**
     * le nom de la {@link Personne}.
     */
    private String nom;
    /**
     * le prenom de la {@link Personne}.
     */
    private String prenom;
    /**
     * le mail de la {@link Personne}.
     */
    private String mail;
    /**
     * le mot de passe de la {@link Personne}.
     */
    private String mdp;
    /**
     * la date de naissance de la {@link Personne}.
     */
    private Date dateNaissance;

    /**
     * le constructeur par defaut de la {@link Personne}.
     */
    public Personne() {
    }

    /**
     * Le constructeur de la {@link Personne} avec ses parametres.
     *
     * @param paramId            l'identifiant.
     * @param paramNom           le nom.
     * @param paramPrenom        le prenom.
     * @param paramMail          le mail.
     * @param paramMdp           le mot de passe.
     * @param paramDateNaissance la date de naissance.
     */
    public Personne(Integer paramId, String paramNom,
                    String paramPrenom, String paramMail,
                    String paramMdp, Date paramDateNaissance) {
        id = paramId;
        nom = paramNom;
        prenom = paramPrenom;
        mail = paramMail;
        mdp = paramMdp;
        dateNaissance = paramDateNaissance;
    }

    /**
     * le getter de l'identifiant.
     *
     * @return l'identifiant.
     */
    public Integer getId() {
        return id;
    }

    /**
     * le setter.
     *
     * @param paramId id.
     */
    public void setId(int paramId) {
        id = paramId;
    }

    /**
     * le getter du nom.
     *
     * @return le nom.
     */
    public String getNom() {
        return nom;
    }

    /**
     * le setter.
     *
     * @param paramNom nom.
     */
    public void setNom(String paramNom) {
        nom = paramNom;
    }

    /**
     * le getter du prenom.
     *
     * @return le prenom.
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * le setter.
     *
     * @param paramPrenom prenom.
     */
    public void setPrenom(String paramPrenom) {
        prenom = paramPrenom;
    }

    /**
     * le getter du mail.
     *
     * @return le mail.
     */
    public String getMail() {
        return mail;
    }

    /**
     * le setter.
     *
     * @param paramMail mail.
     */
    public void setMail(String paramMail) {
        mail = paramMail;
    }

    /**
     * le getter du mdp.
     *
     * @return le mdp.
     */
    public String getMdp() {
        return mdp;
    }

    /**
     * le setter.
     *
     * @param paramMdp mdp.
     */
    public void setMdp(String paramMdp) {
        mdp = paramMdp;
    }

    /**
     * le getter du date.
     *
     * @return le date.
     */
    public Date getDateNaissance() {
        return dateNaissance;
    }

    /**
     * le setter.
     *
     * @param paramDateNaissance date.
     */
    public void setDateNaissance(Date paramDateNaissance) {
        dateNaissance = paramDateNaissance;
    }
}
