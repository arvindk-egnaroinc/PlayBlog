package controllers;

import play.mvc.*;
import services.BlogPost;
import services.User;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;

import views.html.*;

import java.io.IOException;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class Application extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        return ok(blog.render());		
    }
	
	public Result login(){
		return ok(login.render());
	}
	
	public Result signup(){
		return ok(signup.render());
	}

	public Result doSignUp() throws IOException{
		JsonNode jsonData = request().body().asJson();
		String username = jsonData.get("Username").asText();
		String password = jsonData.get("Password").asText();
		String email    = jsonData.get("EmailId").asText();
		System.out.println(username + " "+password+" "+email);
		BlogController bc = new BlogController();
		User user = new User(bc.blogDatabase);
		user.addUser(username,password,email);
		return ok();
	}
	public Result doLogin() throws IOException{
		JsonNode jsonData = request().body().asJson();
		if(jsonData == null)
			System.out.println("Jsondata is null");
		else{
			String username = jsonData.get("name").asText();
			String password = jsonData.get("password").asText();
			BlogController bc = new BlogController();
			User user = new User(bc.blogDatabase);
			System.out.println("In doLogin");
			user.validateLogin(username,password);
		}
		return ok();
	}
	public Result doBlogPost() throws IOException{
		System.out.println("Do blog post");
		JsonNode jsonData = request().body().asJson();
		System.out.println("Json Data"+ jsonData);
		if(jsonData == null)
			System.out.println("Jsondata is null");
		else{
			String tittle = jsonData.get("Title").asText();
			System.out.println(tittle);
			String content = jsonData.get("Content").asText();
			System.out.println(content);
			//ArrayList<String> value = jsonData.getJSONArray("fields").getString(fieldIndex);
			List tag = new ArrayList();
			tag.add("tech");
			tag.add("fashion");
			tag.add("health");
			BlogController bc = new BlogController();
			BlogPost bp = new BlogPost(bc.blogDatabase);
			System.out.println("In Blog Post");
			bp.addPost(tittle,content,tag,"arvinds");
			System.out.println("Do blog post");

		}
		return ok();
	}

	
	public Result technology(){
		return ok(technology.render());
	}
	
	public Result travel(){
		return ok(travel.render());
	}
	
	public Result philoshphy(){
		return ok(philoshphy.render());
	}
	
	public Result about(){
		return ok(about.render());
	}
	
	public Result contact(){
		return ok(contact.render());
	}
	
	public Result newpost(){
		return ok(newpost.render());
	}

}
