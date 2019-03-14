package bmicalculator.alireza.com.bmicalculator;

import java.util.Date;

/**
 * Created by Ali Reza on 1/22/2017.
 */
public class Record {
    Date date;  // need a format better - dd-mm-yyyy
    float weight; // need a unit better - pound


    public Record() {
    }

    public Record(Date date, float weight) {
        this.date = date;
        this.weight = weight;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
