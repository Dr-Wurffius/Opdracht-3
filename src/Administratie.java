import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.HashMap;
import java.util.stream.Stream;

/**
 * Created by Martijn on 13-06-16.
 */
public class Administratie
{
    private HashMap student = new HashMap<String, Student>();
    private int     totaalAantalStudenten;


    public void printStudentenVakGehaald(Vak v)
    {

        Iterator<Map.Entry<String,Student>> iterator = student.entrySet().iterator();
        System.out.println("De volgende studenten hebben vak " + v.getModulecode() + " gehaald:");

        while(iterator.hasNext())
        {
            Map.Entry<String, Student> studentEntry = iterator.next();
            Student tempStudent = studentEntry.getValue();
            LinkedList linkedList = tempStudent.getVakkenLijst();
            ListIterator listIterator = linkedList.listIterator();

            while (listIterator.hasNext())
            {
                Vak tempVak = (Vak) listIterator.next();

                if (tempVak.getModulecode().equals(v.getModulecode()) && tempVak.getCijfer() >= 6)
                {
                    System.out.println("naam: " + tempStudent.getNaam());

                }

            }
        }

    }

    public double getGemiddeldeVak(Vak v)
    {
        double  gemiddelde       = 0;
        int     count            = 0;
        double  cumulatiefCijfer = 0;



        Iterator<Map.Entry<String,Student>> iterator = student.entrySet().iterator();


        while(iterator.hasNext())
        {
            Map.Entry<String, Student> studentEntry = iterator.next();
            Student tempStudent = studentEntry.getValue();
            LinkedList linkedList = tempStudent.getVakkenLijst();
            ListIterator listIterator = linkedList.listIterator();

            while (listIterator.hasNext())
            {
                Vak tempVak = (Vak) listIterator.next();

                if (tempVak.getModulecode().equals(v.getModulecode()))
                {
                    cumulatiefCijfer += (double) tempVak.getCijfer();

                    count++;
                }

            }

        }

        gemiddelde = (cumulatiefCijfer/count);
        return gemiddelde;

    }

    public ArrayList getBehaaldeVakken(Student s)
    {

        ArrayList behaaldeVakken = new ArrayList();

        Student tempStudent = (Student) student.get(s.getNaam());
        LinkedList linkedList = tempStudent.getVakkenLijst();
        ListIterator listIterator = linkedList.listIterator();

        while (listIterator.hasNext())
        {
            Vak tempVak = (Vak) listIterator.next();

            if (tempVak.getCijfer()>= 6)
            {

                behaaldeVakken.add(tempVak.getModulecode());

            }
        }

        return behaaldeVakken;



    }

    public ArrayList getTeBehalenVakken(Student s)
    {

        ArrayList teBehalenVakken = new ArrayList();

        Student tempStudent = (Student) student.get(s.getNaam());
        LinkedList linkedList = tempStudent.getVakkenLijst();
        ListIterator listIterator = linkedList.listIterator();

        while (listIterator.hasNext())
        {
            Vak tempVak = (Vak) listIterator.next();

            if (tempVak.getCijfer()<= 6)
            {


                teBehalenVakken.add(tempVak.getModulecode());

            }
        }

        return teBehalenVakken;



    }


    public double getCumulatiefCijfer(Student s)
    {
        Student tempStudent = (Student) student.get(s.getNaam());

        LinkedList<Vak> vLijst = s.getVakkenLijst();

        Stream<Vak> v = vLijst.stream();

        double cumulatief = v
                .mapToDouble(Vak::getCijfer)
                .sum();

        return cumulatief;

    }

    public double getStandaardDeviatie(Student s)
    {
        Student tempStudent = (Student) student.get(s.getNaam());
        LinkedList<Vak> vakkenLijst = tempStudent.getVakkenLijst();

        double gemiddelde = vakkenLijst.stream()
                .mapToDouble(Vak::getCijfer)
                .average()
                .getAsDouble();

        DoubleStream stream = vakkenLijst.stream()
                .mapToDouble(Vak::getCijfer);

        double variantie = stream.map((d)-> Math.pow((d-gemiddelde),2)).average().getAsDouble();

        return Math.sqrt(variantie);
    }

    public double getvakVariantie(Vak v)
    {
        Supplier<LinkedList<Vak>> supplier = () -> new LinkedList<Vak>();

        LinkedList<Student> linkedList = new LinkedList<>(student.values());

        LinkedList<Vak> courses = (LinkedList<Vak>) linkedList.stream()
                .flatMap(e -> e.getVakkenLijst().stream())
                .collect(Collectors.toCollection(supplier));

        double average = courses.stream()
                .filter(e -> e.getModulecode().equals(v.getModulecode()))
                .mapToDouble(Vak::getCijfer)
                .average()
                .getAsDouble();

        DoubleStream stream = courses.stream()
                .filter(e -> e.getModulecode().equals(v.getModulecode()))
                .mapToDouble(Vak::getCijfer);

        double variantie = stream.map((d)-> Math.pow((d-average),2)).average().getAsDouble();

        return variantie;

    }

    public double getGemiddeldeMan(Vak v)
    {
        Supplier<LinkedList<Vak>> supplier = () -> new LinkedList<Vak>();
        LinkedList<Student> linkedList = new LinkedList<>(student.values());
        LinkedList<Vak> lijstMetMannen = (LinkedList<Vak>) linkedList.stream()
                .filter(e-> e.getGeslacht().equals("m"))
                .flatMap(e -> e.getVakkenLijst().stream())
                .collect(Collectors.toCollection(supplier));

        double gemiddelde = lijstMetMannen.stream()
                .filter(e -> e.getModulecode().equals(v.getModulecode()))
                .mapToDouble(Vak::getCijfer)
                .average()
                .getAsDouble();

        return gemiddelde;


    }



    public double getGemiddeldeVrouwn(Vak v)
    {
        try
        {
            Supplier<LinkedList<Vak>> supplier = () -> new LinkedList<Vak>();
            LinkedList<Student> linkedList = new LinkedList<>(student.values());
            LinkedList<Vak> lijstMetVrouwen = (LinkedList<Vak>) linkedList.stream()
                    .filter(e -> e.getGeslacht().equals("v"))
                    .flatMap(e -> e.getVakkenLijst().stream())
                    .collect(Collectors.toCollection(supplier));

            double gemiddelde = lijstMetVrouwen.stream()
                    .filter(e -> e.getModulecode().equals(v.getModulecode()))
                    .mapToDouble(Vak::getCijfer)
                    .average()
                    .getAsDouble();

            return gemiddelde;
        }
        catch (Exception e)
        {
            System.out.println("er zijn geen vrouwen");
            return 0.0;
        }


    }

    public int totaalAantalStudenten()
    {

        int count = 0;

        Iterator<Map.Entry<String,Student>> iterator = student.entrySet().iterator();
        while (iterator.hasNext())
        {

        }

        return count;

    }


    public HashMap getStudent()
    {
        return student;
    }

    public void setStudent(HashMap student)
    {
        this.student = student;
    }

    public int getTotaalAantalStudenten()
    {
        return totaalAantalStudenten;
    }

    public void setTotaalAantalStudenten(int totaalAantalStudenten)
    {
        this.totaalAantalStudenten = totaalAantalStudenten;
    }
}
