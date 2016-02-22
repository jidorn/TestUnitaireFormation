package fr.afcepf.al26.qualite.business.impl;

import fr.afcepf.al26.qualite.business.api.ISocialBusiness;
import fr.afcepf.al26.qualite.data.api.IDaoPersonne;
import fr.afcepf.al26.qualite.data.impl.DaoPersonne;
import fr.afcepf.al26.qualite.entities.Message;
import fr.afcepf.al26.qualite.entities.Personne;
import fr.afcepf.al26.qualite.exception.SocialException;

import java.util.List;

/**
 * Classe implémentant les methodes définie par l'interface
 * de la couche metier.
 */
public class SocialBusiness implements ISocialBusiness {
    /**
     * dépendence vers la couche DATA.
     */
    private IDaoPersonne dao = new DaoPersonne();

    /**
     * getter.
     *
     * @return dao.
     */
    public IDaoPersonne getDao() {
        return dao;
    }

    /**
     * setter.
     *
     * @param paramDao dao.
     */
    public void setDao(IDaoPersonne paramDao) {
        dao = paramDao;
    }

    @Override
    public Personne ajouter(Personne paramPersonne) throws SocialException {
        if (!dao.verifMail(paramPersonne.getMail())) {
            paramPersonne = dao.ajouter(paramPersonne);
        } else {
            throw new SocialException("mail existe deja",
                    SocialException.ErrorCode.MAIL_EXISTE_DEJA);
        }
        return paramPersonne;
    }

    @Override
    public Personne seConnecter(String mail, String mdp)
            throws SocialException {
        return dao.seConnecter(mail, mdp);
    }

    @Override
    public List<Personne> rechercher(String nom) {
        return dao.rechercher(nom);
    }

    @Override
    public List<Message> consulterMessage(Personne paramPersonne) {
        return null;
    }

    @Override
    public Message envoyer(Message paramMessage) throws SocialException {
        return null;
    }
}
