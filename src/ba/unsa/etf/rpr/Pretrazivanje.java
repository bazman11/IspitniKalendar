package ba.unsa.etf.rpr;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public interface Pretrazivanje {
    public List<Dogadjaj> filtrirajPoKriteriju(Predicate<Dogadjaj> f);
    public List<Dogadjaj> dajDogadjajeZaDan(LocalDateTime dan);
    public List<Dogadjaj> dajSortiraneDogadjaje(BiFunction<Dogadjaj, Dogadjaj, Integer> f);
    public Set<Dogadjaj> dajSortiranePoPrioritetu();
    public boolean daLiSamSlobodan(LocalDateTime dan);
    public boolean daLiSamSlobodan(LocalDateTime prvi, LocalDateTime drugi);

}
