package fr.afcepf.al26.qualite.data.api;

import fr.afcepf.al26.qualite.entities.Message;
import fr.afcepf.al26.qualite.entities.Personne;
import fr.afcepf.al26.qualite.exception.SocialException;

import java.util.List;

/**
 * interface contenant les définitions de
 * l'acces au données du message de l'application.
 */
public interface IDaoMessage {
    /**
     * Methode permettant l'ajout d'un message dans la
     * base de données.
     *
     * @param paramMessage le message à enregistrer.
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
    Message ajouterMessage(Message paramMessage) throws SocialException;

    /**
     * Methode permettant de rechercher une liste de {@link Message}
     * ayant en parametre une {@link Personne}.
     *
     * @param paramPersonne la personne en parametre.
     * @return la liste des messages de cette personne.
     */
    List<Message> rechercher(Personne paramPersonne);
}
