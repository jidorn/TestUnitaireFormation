package fr.afcepf.al26.qualite.util;

import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.ResourceBundle;

/**
 * Classe permettant la connection a la base de données.
 */
public class SocialDataSource implements DataSource {
    /**
     * login de connection a la bdd.
     */
    private static String login;
    /**
     * mdp de connection a la bdd.
     */
    private static String mdp;
    /**
     * url de connection a la bdd.
     */
    private static String url;
    /**
     * driver de connection a la bdd.
     */
    private static String driver;
    /**
     * logger de connection a la bdd.
     */
    private static Logger log = Logger.getLogger(SocialDataSource.class);

    /**
     * config de connection a la bdd.
     */
    static {
        ResourceBundle rb = ResourceBundle.
                getBundle("fr.afcepf.al26.qualite.resources.socialDS");
        url = rb.getString("url");
        mdp = rb.getString("mdp");
        login = rb.getString("login");
        driver = rb.getString("driver");
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException paramE) {
            paramE.printStackTrace();
        }
        log.info("datasource ready");
    }

    @Override
    public Connection getConnection() throws SQLException {
        Connection cnx = DriverManager.getConnection(url, login, mdp);
        log.info("Connexion OK");
        return cnx;
    }

    @Override
    public Connection getConnection(String username, String password)
            throws SQLException {
        return null;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public java.util.logging.Logger getParentLogger()
            throws SQLFeatureNotSupportedException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }
}
