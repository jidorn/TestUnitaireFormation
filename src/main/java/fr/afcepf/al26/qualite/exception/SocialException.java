package fr.afcepf.al26.qualite.exception;

/**
 * Exception pour notre reseau social.
 *
 * @author Stagiaire.
 */
public class SocialException extends Exception {
    /**
     * version pour la sérialisation.
     * car la classe {@link Exception} implement.
     * {@link java.io.Serializable}.
     */
    private static final long serialVersionUID = 1L;

    /**
     * code de l'exception.
     */
    private ErrorCode codeErreur = ErrorCode.CA_MARCHE_PAS;

    /**
     * Liste des codes d'erreurs de l'application.
     */
    public enum ErrorCode {
        /**
         * erreur generique.
         */
        CA_MARCHE_PAS,
        /**
         * Erreur lors de l'ajout d'une personne si le mail existe deja.
         */
        MAIL_EXISTE_DEJA,
        /**
         * Si il manque des informations pour ajouter une personne.
         */
        PERSONNE_INCOMPLETE,
        /**
         * Si il manque des informations pour ajouter un message.
         */
        MERSSAGE_INCOMPLET,
    }

    /**
     * Constructeur d'erreur avec juste le message.
     * @param paramCodeErreur le {@link ErrorCode} de l'{@link Exception}
     */
    public SocialException(ErrorCode paramCodeErreur) {
        codeErreur = paramCodeErreur;
    }

    /**
     * Constructeur d'erreur avec le code et le message.
     * @param message le message.
     * @param paramCodeErreur le {@link ErrorCode} de l'{@link Exception}
     */
    public SocialException(String message, ErrorCode paramCodeErreur) {
        super(message);
        codeErreur = paramCodeErreur;
    }
}
