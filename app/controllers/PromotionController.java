package controllers;

import java.sql.ResultSet;
import java.sql.SQLException;

import play.libs.Json;
import play.mvc.*;
import play.db.Database;
import play.db.NamedDatabase;
import play.db.jpa.JPAApi;
import models.Promotion;
import java.util.ArrayList;
import java.util.List;
/**
 * Controller to handle request related to promotion actions
 * @author luisf
 *
 */
public class PromotionController extends Controller{
	
	private final JPAApi jpaApi;

	@javax.inject.Inject
	public PromotionController(JPAApi jpaApi){
		this.jpaApi = jpaApi;
		
	}
	/**
	 * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/new-promotion</code>.
	 */
	public Result newPromotion(){
		return super.ok(views.html.new_promotion.render());
	}
	
	@play.db.jpa.Transactional
	public Result get(int id){

		List<Promotion> list = new ArrayList<Promotion>();

		Promotion prom =  (Promotion) jpaApi.em().find(Promotion.class, id);
		list.add(new Promotion(3,"3"));
		list.add(new Promotion(4,"4"));
                list.add(new Promotion(5,"five"));
		list.add(prom);
		

		return super.ok(Json.toJson(list)).as("application/json; charset=utf-8");
	}
	
	
 
}
