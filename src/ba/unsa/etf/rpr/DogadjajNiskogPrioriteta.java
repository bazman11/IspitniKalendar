package ba.unsa.etf.rpr;

import java.time.LocalDateTime;

public class DogadjajNiskogPrioriteta extends Dogadjaj {
    public DogadjajNiskogPrioriteta(String moj_rođendan, LocalDateTime of, LocalDateTime of1) throws NeispravanFormatDogadjaja {
        super(moj_rođendan, of, of1);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof DogadjajNiskogPrioriteta) return super.equals(obj);
        else return false;
    }
    @Override
    public String toString() {
        return super.getNaziv() + " (nizak prioritet) " + super.toString();
    }
}
