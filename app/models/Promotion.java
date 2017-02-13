package models;

import javax.persistence.*;
import play.data.validation.Constraints;
import play.data.format.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Entity
public class Promotion {
	public static String TABLE = "Promotion";
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Integer idPromotion;

    @Constraints.Required
    public String Title;
    
    public Promotion(){}
    public Promotion(int id,String title){
    	this.idPromotion = id;
    	this.Title = title;
    }
   
}
