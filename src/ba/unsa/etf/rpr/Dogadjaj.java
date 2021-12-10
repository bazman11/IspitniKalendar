package ba.unsa.etf.rpr;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Dogadjaj {
    private String naziv;
    private LocalDateTime datumPocetak;
    private LocalDateTime datumKraj;

    public Dogadjaj(String naziv, LocalDateTime datumPocetak, LocalDateTime datumKraj) throws NeispravanFormatDogadjaja {
        this.naziv = naziv;
        setPocetak(datumPocetak);
        setKraj(datumKraj);
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public LocalDateTime getPocetak() {
        return datumPocetak;
    }

    public void setPocetak(LocalDateTime datumPocetak) throws NeispravanFormatDogadjaja {
        if(this.getKraj() == null) this.datumPocetak = datumPocetak;
        else if(datumPocetak.isAfter(this.getKraj())) throw new NeispravanFormatDogadjaja("Neispravan format početka i kraja događaja");
        else this.datumPocetak = datumPocetak;
    }

    public LocalDateTime getKraj() {
        return datumKraj;
    }

    public void setKraj(LocalDateTime datumKraj) throws NeispravanFormatDogadjaja {
        if(this.getPocetak() == null) this.datumKraj = datumKraj;
        else if(datumKraj.isBefore(this.getPocetak())) throw new NeispravanFormatDogadjaja("Neispravan format početka i kraja događaja");
        else this.datumKraj = datumKraj;
    }

    @Override
    public boolean equals(Object obj) {
        Dogadjaj d = (Dogadjaj) obj;
        return naziv.equals(d.getNaziv()) && datumPocetak.isEqual(d.getPocetak()) && datumKraj.isEqual(d.getKraj());
    }

    @Override
    public String toString() {
        return "- početak: " + datumPocetak.format(DateTimeFormatter.ofPattern("dd/MM/yyyy (HH:mm)")) + ", kraj: " + datumKraj.format(DateTimeFormatter.ofPattern("dd/MM/yyyy (HH:mm)"));
    }
}
