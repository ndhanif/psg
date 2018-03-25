package ie.naveed.p_s_g;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * Created by Diogo on 21/03/2018.
 */

public class ItemData implements Serializable {

    private String heading,team1,team2,url,scoreteam1,scoreteam2;
    private  String head;
    //private int scoreteam1,scoreteam2;
    private Drawable mainimage,imageteam1,imageteam2,newimage;

    public ItemData(String heading, String team1, String team2, String url, String scoreteam1, String scoreteam2, Drawable mainimage, Drawable imageteam1, Drawable imageteam2) {
        this.heading = heading;
        this.team1 = team1;
        this.team2 = team2;
        this.url=url;
        this.scoreteam1 = scoreteam1;
        this.scoreteam2 = scoreteam2;
        this.mainimage = mainimage;
        this.imageteam1 = imageteam1;
        this.imageteam2 = imageteam2;
    }

    public ItemData(String heading, Drawable sportimage) {

        this.head=heading;
        this.newimage=sportimage;


    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public Drawable getNewimage() {
        return newimage;
    }

    public void setNewimage(Drawable newimage) {
        this.newimage = newimage;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getScoreteam1() {
        return scoreteam1;
    }

    public void setScoreteam1(String scoreteam1) {
        this.scoreteam1 = scoreteam1;
    }

    public String getScoreteam2() {
        return scoreteam2;
    }

    public void setScoreteam2(String scoreteam2) {
        this.scoreteam2 = scoreteam2;
    }

    public Drawable getMainimage() {
        return mainimage;
    }

    public void setMainimage(Drawable mainimage) {
        this.mainimage = mainimage;
    }

    public Drawable getImageteam1() {
        return imageteam1;
    }

    public void setImageteam1(Drawable imageteam1) {
        this.imageteam1 = imageteam1;
    }

    public Drawable getImageteam2() {
        return imageteam2;
    }

    public void setImageteam2(Drawable imageteam2) {
        this.imageteam2 = imageteam2;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
