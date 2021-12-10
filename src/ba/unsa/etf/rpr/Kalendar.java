package ba.unsa.etf.rpr;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Kalendar implements Pretrazivanje{
    private List<Dogadjaj> dogadjaji = new ArrayList<>();

    public List<Dogadjaj> dajKalendar(){
        return dogadjaji;
    }

    public void zakaziDogadjaj(Dogadjaj dogadjaj){
        dogadjaji.add(dogadjaj);
    }

    public void otkaziDogadjaj(Dogadjaj dogadjaj){
        if(dogadjaji.contains(dogadjaj)) dogadjaji.remove(dogadjaj);
    }

    public void zakaziDogadjaje(List<Dogadjaj> dogadjaji){
        for(var jedan : dogadjaji)
            this.dogadjaji.add(jedan);
    }

    public void otkaziDogadjaje(List<Dogadjaj> dogadjaji){
        for(var jedan : dogadjaji)
            if(this.dogadjaji.contains(jedan))this.dogadjaji.remove(jedan);
    }

    public void otkaziDogadjaje(Predicate<Dogadjaj> f){
        dogadjaji = dogadjaji.stream().filter(x -> !f.test(x)).collect(Collectors.toList());
    }

    public Map<LocalDate, List<Dogadjaj>> dajKalendarPoDanima(){
        Map<LocalDate, List<Dogadjaj>> rez = new HashMap<>();
        for(var jedan : dogadjaji){
            List<Dogadjaj> proba = rez.get(LocalDate.from(jedan.getPocetak()));
            if(proba == null) {
                proba = new ArrayList<>();
                proba.add(jedan);
                rez.put(LocalDate.from(jedan.getPocetak()), proba);
            }
            else {
                proba.add(jedan);
                rez.put(LocalDate.from(jedan.getPocetak()), proba);
            }
        }
        return rez;
    }
    public Dogadjaj dajSljedeciDogadjaj(LocalDateTime usporedbeni) throws IllegalArgumentException{
        var opcija = dogadjaji.stream().filter(x -> x.getPocetak().isAfter(usporedbeni)).findFirst();
        if(opcija.isPresent()) return opcija.get();
        throw new IllegalArgumentException("Nemate događaja nakon navedenog datuma");
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner("\n");
        for (Dogadjaj dogadjaj : dogadjaji) {
            joiner.add(dogadjaj.toString());
        }
        return (String) joiner.toString();
    }

    @Override
    public List<Dogadjaj> filtrirajPoKriteriju(Predicate<Dogadjaj> f) {
        return dogadjaji.stream().filter(f).collect(Collectors.toList());
    }

    @Override
    public List<Dogadjaj> dajDogadjajeZaDan(LocalDateTime dan) {
        List<Dogadjaj> rez = new ArrayList<>();
        var pom = dajKalendarPoDanima().get(LocalDate.from(dan));
        if(pom!= null) rez = pom;
        pom = null;
        return rez;
    }

    @Override
    public List<Dogadjaj> dajSortiraneDogadjaje(BiFunction<Dogadjaj, Dogadjaj, Integer> f) {
        return dogadjaji.stream().sorted((x,y) -> f.apply(x,y)).collect(Collectors.toList());
    }

    @Override
    public Set<Dogadjaj> dajSortiranePoPrioritetu() {
        return dogadjaji.stream().sorted((x,y) -> {
            if((x instanceof DogadjajSrednjegPrioriteta || x instanceof DogadjajNiskogPrioriteta) && y instanceof DogadjajVisokogPrioriteta) return -1;
            else if(x instanceof DogadjajNiskogPrioriteta && (y instanceof DogadjajVisokogPrioriteta || y instanceof DogadjajSrednjegPrioriteta)) return -1;
            return 0;
        }).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public boolean daLiSamSlobodan(LocalDateTime dan) {
        if(dajKalendarPoDanima().containsKey(LocalDate.from(dan))) {
            var opcija = dajKalendarPoDanima().get(LocalDate.from(dan));
            return opcija.isEmpty();
        }
        return true;
//        for(var danp : dogadjaji){ // radi i ovo
//            if(danp.getPocetak().isAfter(dan)) return false;
//        }
//        return true;
    }

    @Override
    public boolean daLiSamSlobodan(LocalDateTime prvi, LocalDateTime drugi) throws IllegalArgumentException {
        if(prvi.isAfter(drugi)) throw new IllegalArgumentException("Neipsravni podaci o početku i kraju");
        var pom = dajKalendarPoDanima();
        while(prvi.isBefore(drugi)){
            prvi = prvi.plusDays(1);
            if(prvi.isEqual(drugi)) return true;
            if(!daLiSamSlobodan(prvi)) return false;
        }
        return true;
    }
}
