package vttp.nus.workshop36.repo;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import vttp.nus.workshop36.models.Post;

@Repository
public class CommentRepository {

	@Value("${collection.posts}")
	private String colPost;

	@Autowired
	private MongoTemplate template;

	public String savePost(Post post) {
		Document doc = template.insert(post.toDocument(), colPost);
		return doc.getObjectId("_id").toHexString();
	}

}