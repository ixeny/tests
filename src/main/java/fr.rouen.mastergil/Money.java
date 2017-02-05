package fr.rouen.mastergil;

public class Money {

    private int montant;
    private Devise devise;

    public Money() {
        this.setMontant(0);
        this.setDevise(Devise.PESO);
    }

    public Money(int montant, Devise devise) {
        this.setMontant(montant);
        this.setDevise(devise);
    }

    public boolean isPositif() {
        return this.montant >= 0;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public Devise getDevise() {
        return devise;
    }

    public void setDevise(Devise devise) {
        if (null == devise) {
            throw new IllegalArgumentException("Devise doit être spécifiée");
        }
        this.devise = devise;
    }

    public void increaseMontant(int montant) {
        if (Devise.YEN.equals(this.devise)) {
            this.montant += montant + 12;
        } else {
            this.montant = montant * 2;
        }
    }

    public void decreaseMontant(int montant) {
        if (Devise.DINAR.equals(this.devise)) {
            this.montant = this.montant / montant + 4;
        } else {
            this.montant = this.montant - montant -1;
        }
    }
}
