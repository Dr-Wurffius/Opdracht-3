import java.util.LinkedList;
import java.util.stream.Stream;

/**
 * Created by Martijn on 13-06-16.
 */
public class App
{
    public static void main(String[] args)
    {
        Administratie administratie = new Administratie();

        Student a = new Student("1","Martijn","m","ti1b","ti");
        Student b = new Student("2", "Pietertje", "m", "ti1a", "ti");

        Vak wiskA = new Vak("wisk1", 8, 2016);
        Vak wiskB = new Vak("wisk1", 5, 2016);

        Vak netwerkA = new Vak("netwerk1", 5, 2016);
        Vak netwerkB = new Vak("netwerk1", 8, 2016);

        Vak progA = new Vak("programeren1", 8, 2016);
        Vak progB = new Vak("programeren1", 5, 2016);


        a.addVak(wiskA);
        a.addVak(netwerkA);
        a.addVak(progA);

        b.addVak(wiskB);
        b.addVak(netwerkB);
        b.addVak(progB);



        administratie.getStudent().put(a.getNaam(),a);
        administratie.getStudent().put(b.getNaam(),b);

        administratie.printStudentenVakGehaald(netwerkA);
        System.out.println();
        System.out.println("het gemiddelde van het vak " + wiskA.getModulecode() + " is:");
        System.out.println(administratie.getGemiddeldeVak(wiskA));
        System.out.println();
        System.out.println("Student " + a.getStudentnummer() + " heeft de volgende vakken behaald: ");
        System.out.println(administratie.getBehaaldeVakken(a));
        System.out.println();
        System.out.println("Student " + b.getStudentnummer() + " moet de volgende vakken nog halen: ");
        System.out.println(administratie.getTeBehalenVakken(b));
        System.out.println();
        System.out.println("Het cumulatieve cijfer van student " + a.getStudentnummer() + "is: ");
        System.out.println(administratie.getCumulatiefCijfer(a));
        System.out.println();
        System.out.println("De standaard deviatie van student " + b.getStudentnummer() + " is:");
        System.out.println(administratie.getStandaardDeviatie(b));
        System.out.println();
        System.out.println("De variantie van het vak " + wiskB.getModulecode() + " is:");
        System.out.println(administratie.getvakVariantie(wiskB));
        System.out.println();
        System.out.println("Het gemidddelde van de mannen is: ");
        System.out.println(administratie.getGemiddeldeMan(netwerkA));
        System.out.println();
        System.out.println("Het gemidddelde van de vrouwen is: ");
        System.out.println(administratie.getGemiddeldeVrouwn(netwerkA));
        System.out.println();
        System.out.println("totaal aantal studenten: " + administratie.totaalAantalStudenten());




    }


}
