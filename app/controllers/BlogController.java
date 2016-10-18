package controllers;

import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
//import com.mongodb.MongoCon

import java.io.IOException;
import  services.BlogPost;
import  services.Session;
import  services.User;
import java.util.List;

public class BlogController {

    private final BlogPost blogPost;
    private final User user;
    private final Session session;
    public final DB blogDatabase;


    public BlogController() throws IOException {
        //final MongoClient mongoClient = new MongoClient(new MongoClientURI(mongoURIString));
        final  MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
        blogDatabase = mongoClient.getDB("play-blog");

        System.out.println("Database created");
        blogPost = new BlogPost(blogDatabase);
        user = new User(blogDatabase);
        session = new Session(blogDatabase);

    }

    /*public  Result find() {

        List<DBObject> results = MongoController.find(MongoController.getLocalConnection("play-blog"), "posts");
        if (results == null) {
            //redirect("/");
            System.out.println("No posts found in database");
        }

        return ok(find_job.render(results));
    }*/
}
