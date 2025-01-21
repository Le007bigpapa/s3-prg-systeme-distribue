package ca.usherbrooke.fgen.api.business;

public class Tache
{
    public Integer idTache;
    public Integer idTacheParent;
    public Integer idProjet;
    public String description;
    public String date_modifier;
    public String titre;
    public String cipCreateur;
    private String commencerA;
    public String cipUsager;
    private String finiA;

    public String getCommencerA()
    {
        return commencerA;
    }

    public void setCommencerA(String commencerA)
    {
        this.commencerA = commencerA;
    }

    public String getFiniA()
    {
        return finiA;
    }

    public void setFiniA(String finiA)
    {
        this.finiA = finiA;
    }
}