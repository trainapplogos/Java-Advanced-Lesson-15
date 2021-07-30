package ua.lviv.trainapplogos.one_to_many;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Application {
	public static void main(String[] args) {
		Configuration configuration = new Configuration();
		configuration.configure("/META-INF/hibernate.cfg.xml");
		
		SessionFactory factory = configuration.buildSessionFactory();
		Session session = factory.openSession();
		
		Post post = new Post("Hi there!");

		Comment comment1 = new Comment("Whats up!");
		comment1.setAuthorName("Mary Land");
		comment1.setPost(post);
		
		Comment comment2 = new Comment("Yo!");
		comment2.setAuthorName("Cris Isaak");
		comment2.setPost(post);
		
		Set<Comment> comments = new HashSet<>();
		comments.add(comment1);
		comments.add(comment2);
		
		post.setCommetns(comments);
		
		//save to DB
		Transaction transaction = session.beginTransaction();
		session.save(post);
		transaction.commit();
	
		
		/*
		//read from DB
		Post postFromDB = session.get(Post.class, 1);
		System.out.println("> " + postFromDB + " ---> " + postFromDB.getCommetns());
		Comment commentDB1 = session.get(Comment.class, 1);
		Comment commentDB2 = session.get(Comment.class, 1);
		*/
		
		session.close();
		factory.close();
		
	}
}
