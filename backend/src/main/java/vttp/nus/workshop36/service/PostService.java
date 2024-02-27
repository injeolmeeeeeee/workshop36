package vttp.nus.workshop36.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import vttp.nus.workshop36.models.Post;
import vttp.nus.workshop36.repo.CommentRepository;
import vttp.nus.workshop36.repo.ImageRepository;

import java.util.UUID;

@Service
public class PostService {

    @Autowired
	private ImageRepository imageRepo;

    @Autowired
    private CommentRepository commentRepository;

    public Post savePost(Post post, MultipartFile imageFile) throws Exception {

        		String imageId = UUID.randomUUID().toString().substring(0, 8);
        
        		String url = imageRepo.save(imageId, imageFile.getContentType()
        				, imageFile.getInputStream(), imageFile.getSize());
        
        		post = post.updateUrl(url);
        
        		commentRepository.savePost(post);
        
        		return post;
    }
}

// @Autowired
// 	private ImageRepository imageRepo;

// 	@Autowired
// 	private ArticleRepository articleRepo;

// 	public Article saveArticle(Article article, MultipartFile imageFile) throws Exception {

// 		String imageId = UUID.randomUUID().toString().substring(0, 8);

// 		String url = imageRepo.save(imageId, imageFile.getContentType()
// 				, imageFile.getInputStream(), imageFile.getSize());

// 		article = article.updateUrl(url);

// 		articleRepo.saveArticle(article);

// 		return article;