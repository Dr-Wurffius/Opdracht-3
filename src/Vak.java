/**
 * Created by Martijn on 13-06-16.
 */
public class Vak
{
    private String  modulecode;
    private int     cijfer;
    private int     jaar;


    public Vak(String modulecode, int cijfer, int jaar)
    {
        this.modulecode = modulecode;
        this.cijfer = cijfer;
        this.jaar = jaar;
    }



    public void printVak()
    {
        System.out.println("modulecode: " + modulecode);
        System.out.println("cijfer:     " + cijfer);
        System.out.println("jaar:       " + jaar);
    }


    public String getModulecode()
    {
        return modulecode;
    }

    public void setModulecode(String modulecode)
    {
        this.modulecode = modulecode;
    }

    public int getCijfer()
    {
        return cijfer;
    }

    public void setCijfer(int cijfer)
    {
        this.cijfer = cijfer;
    }

    public int getJaar()
    {
        return jaar;
    }

    public void setJaar(int jaar)
    {
        this.jaar = jaar;
    }


}
