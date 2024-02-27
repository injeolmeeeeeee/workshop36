package vttp.nus.workshop36.models;

import org.bson.Document;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public record Post (String postId, String comments, String url) {
    

    public JsonObject toJson() {
		return Json.createObjectBuilder()
			.add("is", postId)
			.add("comments", comments)
			.add("picture", url)
			.build();
	}

	public Post updateUrl(String url) {
		return new Post(postId, comments, url);
	}

    public Document toDocument() {
		Document doc = new Document();
		doc.put("title", comments);
		doc.put("url", url);
		return doc;
	}
    
}
