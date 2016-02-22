package fr.afcepf.al26.qualite.business.api;

import fr.afcepf.al26.qualite.entities.Message;
import fr.afcepf.al26.qualite.entities.Personne;
import fr.afcepf.al26.qualite.exception.SocialException;

import java.util.List;

/**
 * Interface contenant les definitions des méthodes utilisées pour la couche
 * metier du reseau social.
 */
public interface ISocialBusiness {
    /**
     * Methode permettant d'efectuer un ajout
     * de {@link Personne}.
     *
     * @param paramPersonne la personne.
     * @return la personne.
     * @throws SocialException <code>
     *                         <ul>
     *                         <li>Si le mail existe deja.</li>
     *                         <li>si le nom, prenom, mail, mdp, ou
     *                         date de naissance de la personne
     *                         n'est pas renseignés.</li>
     *                         <li>si le nom, prenom, mail,
     *                         mdp, ou date de naissance de la personne
     *                         n'ont pas le bon format.</li>
     *                         <li>si le serveur de données
     *                         ne répond pas.</li>
     *                         </ul>
     *                         </code>
     */
    Personne ajouter(Personne paramPersonne) throws SocialException;

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

    /**
     * Methode permettant de rechercher une liste de {@link Message}
     * ayant en parametre une {@link Personne}.
     *
     * @param paramPersonne la personne en parametre.
     * @return la liste des messages de cette personne.
     */
    List<Message> consulterMessage(Personne paramPersonne);

    /**
     * Methode permettant d'envoyer d'un {@link Message}.
     *
     * @param paramMessage le message à envoyer.
     * @return le message.
     * @throws SocialException <code>
     *                         <ul>
     *                         <li>si le message existe deja</li>
     *                         <li>si le message est mal écrit</li>
     *                         <li>si le message est mal renseigné</li>
     *                         <li>si le message est vide</li>
     *                         </ul>
     *                         </code>
     */
    Message envoyer(Message paramMessage) throws SocialException;
}
