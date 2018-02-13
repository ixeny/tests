
import fr.rouen.mastergil.BankAccount;
import fr.rouen.mastergil.Devise;
import  org.assertj.core.util.FailureMessages;
import  org.assertj.core.api.Assertions.*;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.*;


public class BankAccountTest {


    @Test
    public void creerCompte() {
        //Given
        BankAccount account = new BankAccount();

        //While
        account.creerCompte();

        //Then
        assertThat(account).hasFieldOrProperty("solde");


    }

    @Test
    public void creerCompte2() {
        //Given

        BankAccount account = new BankAccount();

        //While

        account.creerCompte(19, Devise.LIVRE);

        //Then

        assertThat(account).hasFieldOrProperty("solde");
        assertThat(account.solde.getDevise()).isEqualTo(Devise.LIVRE);
        assertThat(account.solde.getMontant()).isEqualTo(19);

    }

    @Test
    public void deposerArgent() throws Exception {

        //Given

        BankAccount account = new BankAccount();
        account.creerCompte(20, Devise.PESO);

        //While

        account.deposerArgent(13);

        //Then

        assertThat(account.solde.getDevise()).isEqualTo(Devise.PESO);
        assertThat(account.solde.getMontant()).isEqualTo(33);

    }

    @Test
    public void consulterSolde() {

        //Given

        BankAccount account = new BankAccount();
        account.creerCompte(20, Devise.PESO);

        //While

        String str = account.consulterSolde();

        //Then

        assertThat(str).isEqualTo("Votre solde actuel est de 20 PESO");

    }

    @Test
    public void retirerArgent() {


        //Given

        BankAccount account = new BankAccount();
        account.creerCompte(20, Devise.YEN);

        //While

        account.retirerArgent(19);

        //Then

        assertThat(account.solde.getMontant()).isEqualTo(1);

    }

    @Test
    public void isSoldePositif() {
        //Given

        BankAccount account = new BankAccount();
        BankAccount account2 = new BankAccount();
        account.creerCompte(15, Devise.EURO);
        account2.creerCompte(-15, Devise.EURO);


        //While

        boolean solde1 = account.isSoldePositif();
        boolean solde2 = account2.isSoldePositif();

        //Then

        assertThat(solde1).isEqualTo(true);
        assertThat(solde2).isEqualTo(false);


    }

    @Test
    public void transfererArgent() {
        //Given

        BankAccount account = new BankAccount();
        BankAccount account2 = new BankAccount();
        account.creerCompte(15, Devise.EURO);
        account2.creerCompte(405, Devise.EURO);

        //While

        account.transfererArgent(account2, 3);

        //Then
        assertThat(account.solde.getMontant()).isEqualTo(12);
        assertThat(account2.solde.getMontant()).isEqualTo(408);

    }

    @Test
    public void testException() {

        //Given while

        Throwable thrown = catchThrowable(() -> {
            throw new IllegalArgumentException("Le compte ou le montant est invalide");
        });

        //Then

        assertThat(thrown).isInstanceOf(IllegalArgumentException.class).hasMessage("Le compte ou le montant est invalide");
    }

}
