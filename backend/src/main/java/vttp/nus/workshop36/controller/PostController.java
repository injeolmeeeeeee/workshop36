package vttp.nus.workshop36.controller;

import java.util.Date;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import vttp.nus.workshop36.models.Post;
import vttp.nus.workshop36.service.PostService;

@Controller
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200/")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping(path = "/picture", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public ResponseEntity<String> postPicture(@RequestPart MultipartFile picture) {
        /*
         * <input type="file" name="image">
         */

        Document doc;

        System.out.printf(">>> picture: %s", picture.getContentType());

        Post post = new Post(
                "Upload from Angular",
                "Uploaded on %s".formatted((new Date()).toString()), "");

        try {
            post = postService.savePost(post, picture);
        } catch (Exception ex) {
            doc = new Document("Error", ex.getMessage());
            return ResponseEntity.status(500).body(doc.toJson());
        }

        doc = new Document("url", post.url());
        return ResponseEntity.ok(doc.toJson());
    }

    @PostMapping(path = "/post", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ModelAndView postPost(@RequestPart MultipartFile picture,
            @RequestPart String comments) {

        ModelAndView mav;

        Post post = new Post("", comments, "");

        try {
            System.out.println("Received comments: " + comments);
            System.out.println("Received picture: " + picture.getOriginalFilename());
    
            post = postService.savePost(post, picture);
            mav = new ModelAndView("post");
            mav.addObject("post", post);
            mav.setStatus(HttpStatusCode.valueOf(200));

        } catch (Exception ex) {
            System.err.println("Error occurred while saving post: " + ex.getMessage());
    
            mav = new ModelAndView("error");
            mav.addObject("error", ex.getMessage());
            mav.setStatus(HttpStatusCode.valueOf(500));
        }

        return mav;
    }
}
