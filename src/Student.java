import java.util.LinkedList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Martijn on 13-06-16.
 */
public class Student
{
    private String          studentnummer;
    private String          naam;
    private String          geslacht;
    private String          klas;
    private String          Studierichting;
    private LinkedList<Vak> vakkenLijst = new LinkedList();

    public Student(String studentnummer, String naam, String geslacht, String klas, String studierichting)
    {
        this.studentnummer = studentnummer;
        this.naam = naam;
        this.geslacht = geslacht;
        this.klas = klas;
        Studierichting = studierichting;
    }


    /////////////methoden/////////////

    public LinkedList getVakken()
    {
        Stream<Vak> v = vakkenLijst.stream();

        LinkedList Vakken = v
                .map(e -> e.getModulecode())
                .collect(Collectors.toCollection(LinkedList::new));

        return Vakken;


    }



    public LinkedList getVakken(int jaar)
    {

        Stream<Vak> v = vakkenLijst.stream();

        if (jaar == 0)
        {
            LinkedList Vakken = v
                    .filter(e -> e.getCijfer() > 6)
                    .map(e -> e.getModulecode())
                    .collect(Collectors.toCollection(LinkedList::new));

            return Vakken;
        }

        else
        {
            LinkedList Vakken = v
                    .filter(e -> e.getJaar() == jaar)
                    .filter(e -> e.getCijfer() > 6)
                    .map(e -> e.getModulecode())
                    .collect(Collectors.toCollection(LinkedList::new));

            return Vakken;
        }






    }

    public void addVak(Vak v)
    {
        vakkenLijst.push(v);


    }


    public OptionalDouble gemiddelde()
    {

        Stream<Vak> v = vakkenLijst.stream();

        OptionalDouble gemiddelde = v
                .filter(e -> e.getCijfer() > 6)
                .mapToDouble(e->e.getCijfer())
                .average();

        return gemiddelde;

    }



    /////////////getters&setters///////////

    public String getStudentnummer()
    {
        return studentnummer;
    }

    public void setStudentnummer(String studentnummer)
    {
        this.studentnummer = studentnummer;
    }

    public String getNaam()
    {
        return naam;
    }

    public void setNaam(String naam)
    {
        this.naam = naam;
    }

    public String getGeslacht()
    {
        return geslacht;
    }

    public void setGeslacht(String geslacht)
    {
        this.geslacht = geslacht;
    }

    public String getKlas()
    {
        return klas;
    }

    public void setKlas(String klas)
    {
        this.klas = klas;
    }

    public String getStudierichting()
    {
        return Studierichting;
    }

    public void setStudierichting(String studierichting)
    {
        Studierichting = studierichting;
    }

    public LinkedList<Vak> getVakkenLijst()
    {
        return vakkenLijst;
    }

    public void setVakkenLijst(LinkedList<Vak> vakkenLijst)
    {
        this.vakkenLijst = vakkenLijst;
    }

}
