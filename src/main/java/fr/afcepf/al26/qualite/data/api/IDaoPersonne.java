package fr.afcepf.al26.qualite.data.api;

import fr.afcepf.al26.qualite.entities.Personne;
import fr.afcepf.al26.qualite.exception.SocialException;

import java.util.List;

/**
 * interface contenant les methodes d'accès
 * aux données pour la personne de l'application.
 */
public interface IDaoPersonne {
    /**
     * Methode permettant d'efectuer un ajout
     * de {@link Personne} dans le serveur de données.
     *
     * @param paramPersonne la {@link Personne} à ajouter.
     * @return la {@link Personne} ajouté.
     * @throws SocialException <code>
     *                         <ul>
     *                         <li>si le nom, prenom, mail, mdp, ou
     *                         date de naissance de la personne
     *                         n'est pas renseignés.</li>
     *                         <li>si le nom, prenom, mail,
     *                         mdp, ou date de naissance de la personne
     *                         n'ont pas le bon format.</li>
     *                         <li>si le serveur de
     *                         données ne répond pas.</li>
     *                         </ul>
     *                         </code>
     */
    Personne ajouter(Personne paramPersonne) throws SocialException;

    /**
     * Méthode permettant de vérifier l'éxistance
     * d'un mail dans la base de données.
     *
     * @param mail le mail à vérifier.
     * @return <code>
     * <ol type='I'>
     * <li>
     * <strong>true</strong> si il existe.
     * </li>
     * <li>
     * <strong>false</strong> si il n'existe pas.
     * </li>
     * </ol>
     * </code>
     */
    Boolean verifMail(String mail);

    /**
     * Méthode permettant à une {@link Personne}
     * de se connecter en verifiant qu'elle
     * existe via son mail et son mot de passe.
     *
     * @param mail le mail a vérifier.
     * @param mdp  le mot de passe a verifier.
     * @return la {@link Personne} ayant ce mail et ce mot de passe.
     * @throws SocialException <code>
     *                         <b>une {@link Exception} social si il n'est
     *                         pas enregistré dans le systeme.</b>
     *                         </code>
     */
    Personne seConnecter(String mail, String mdp) throws SocialException;

    /**
     * Methode permettant de rechercher toutes les {@link Personne}
     * dont le nom contient le paramètre nom envoyé à cette
     * methode.
     *
     * @param nom le nom a rechercher.
     * @return la liste des personnes qui contiennent ce nom.
     */
    List<Personne> rechercher(String nom);
}
