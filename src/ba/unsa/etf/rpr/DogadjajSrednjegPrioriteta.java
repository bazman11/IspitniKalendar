package ba.unsa.etf.rpr;

import java.time.LocalDateTime;

public class DogadjajSrednjegPrioriteta extends Dogadjaj {
    public DogadjajSrednjegPrioriteta(String naziv, LocalDateTime pocetak, LocalDateTime kraj) throws NeispravanFormatDogadjaja {
        super(naziv, pocetak, kraj);
    }
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof DogadjajSrednjegPrioriteta) return super.equals(obj);
        else return false;
    }
    @Override
    public String toString() {
        return super.getNaziv() + " (sredji prioritet) " + super.toString();
    }
}
