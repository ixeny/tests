package fr.rouen.mastergil;

/**
 * Created by rudy on 26/02/17.
 */

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.MessageFormat;

public class BankAccountWithDAO {
    JdbcDAO bankDao;

    public void creerCompte() throws SQLException, ConnectException {
        setupConnection();
        bankDao.creerCompte();
    }

    public void creerCompte(int montant, Devise devise) throws SQLException, ConnectException {
        setupConnection();
        bankDao.creerCompte(montant, devise);
    }

    public String consulterSolde() throws SQLException, ConnectException {
        setupConnection();
        Money solde = bankDao.getSolde();
        return MessageFormat.format("Votre solde actuel est de {0} {1}", solde.getMontant(), solde.getDevise().name());
    }

    public Money deposerArgent(int montant) throws SQLException, ConnectException {
        setupConnection();
        Money solde = bankDao.getSolde();
        solde.setMontant(solde.getMontant() + montant);
        bankDao.saveMoney(solde);
        return solde;
    }

    public Money retirerArgent(int montant) throws SQLException, ConnectException {
        setupConnection();
        Money solde = bankDao.getSolde();
        solde.setMontant(solde.getMontant() - montant);
        bankDao.saveMoney(solde);
        return solde;
    }

    public boolean isSoldePositif() throws SQLException, ConnectException {
        setupConnection();
        Money solde = bankDao.getSolde();
        return solde.isPositif();
    }

    private void setupConnection() throws SQLException, ConnectException {
        Connection connection = bankDao.setUpConnection();
        connection.setAutoCommit(true);
        if (connection.isReadOnly() || connection.isClosed()) {
            throw new ConnectException("Impossible d'avoir accès à  la base de donnÃ©es");
        }
    }
}
