package ba.unsa.etf.rpr;

import java.time.LocalDateTime;

public class DogadjajVisokogPrioriteta extends Dogadjaj {
    public DogadjajVisokogPrioriteta(String s, LocalDateTime of, LocalDateTime of1) throws NeispravanFormatDogadjaja {
        super(s, of, of1);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DogadjajVisokogPrioriteta) return super.equals(obj);
        else return false;
    }
    @Override
    public String toString() {
        return super.getNaziv() + " (visok prioritet) " + super.toString();
    }
}
