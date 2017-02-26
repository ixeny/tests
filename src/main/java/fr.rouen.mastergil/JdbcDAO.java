package fr.rouen.mastergil;

/**
 * Created by rudy on 26/02/17.
 */
import java.sql.Connection;

public interface JdbcDAO {
    Connection setUpConnection();

    Money getSolde();

    int saveMoney(Money solde);

    void creerCompte();

    void creerCompte(int montant, Devise devise);
}
