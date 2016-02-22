package fr.afcepf.al26.qualite.data.impl;

import fr.afcepf.al26.qualite.data.api.IDaoMessage;
import fr.afcepf.al26.qualite.entities.Message;
import fr.afcepf.al26.qualite.entities.Personne;
import fr.afcepf.al26.qualite.exception.SocialException;

import java.util.List;

/**
 * Classe implémentant les methodes définie par l'interface
 * de l'acces aux données.
 */
public class DaoMessage implements IDaoMessage {
    @Override
    public Message ajouterMessage(Message paramMessage) throws SocialException {
        return null;
    }

    @Override
    public List<Message> rechercher(Personne paramPersonne) {
        return null;
    }
}
