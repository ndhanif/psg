package ie.naveed.p_s_g.model;

/**
 * Created by barryoreilly on 28/06/16.
 */
public class Player {

    private String id;
    private String name;
    private String team;
    private String position;
    private Boolean choosen;

    public Player(Boolean choosen, String position, String team, String name, String id) {
        this.choosen = choosen;
        this.position = position;
        this.team = team;
        this.name = name;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Boolean getChoosen() {
        return choosen;
    }

    public void setChoosen(Boolean choosen) {
        this.choosen = choosen;
    }
}
