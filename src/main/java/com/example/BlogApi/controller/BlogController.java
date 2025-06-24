package com.example.BlogApi.controller;


import com.example.BlogApi.entity.BlogEntity;
import com.example.BlogApi.entity.UserEntity;
import com.example.BlogApi.service.BlogService;
import com.example.BlogApi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth/blog")
public class BlogController {

    @Autowired
    BlogService service;
    @Autowired
    UserService userService;

    @PostMapping("/add")
    public BlogEntity addBlog(@RequestBody BlogEntity entity) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userService.findByUsername(username);
        entity.setUser(user);
        return service.saveBlog(entity);
    }


    @GetMapping("/showblogs")
    public Page<BlogEntity> getAllBlogs(@RequestParam (defaultValue = "0") int page ,
                                        @RequestParam(defaultValue = "5")int size,
                                        @RequestParam(defaultValue = "id") String sortBy,
                                        @RequestParam(defaultValue = "true") boolean ascending){
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size,sort);
        return service.getAllBlogs(pageable);
    }



@Transactional
@DeleteMapping("/deleteall")
    public String deleteAllBlogsByCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userService.findByUsername(username);

        service.deleteAllBlogsByUser(user);
        return "All blogs deleted for user: " + username;
    }




    @DeleteMapping("/delete/id/{id}")
    public String deleteById(@PathVariable Integer id) {
        Optional<BlogEntity> optionalBlog = service.findById(id);
        if (optionalBlog.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Blog not found");
        }

        BlogEntity blog = optionalBlog.get();
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();

        if (!blog.getUser().getUsername().equals(currentUsername)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not allowed to delete this blog");
        }


        service.deleteById(id);
        return "Blog deleted successfully";

    }
    @PutMapping("/update/{id}")
    public BlogEntity updateBlog(@PathVariable Integer id, @RequestBody BlogEntity updatedBlog) {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<BlogEntity> optionalBlog = service.findById(id);

        if (optionalBlog.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Blog not found");
        }

        BlogEntity existingBlog = optionalBlog.get();

        if (!existingBlog.getUser().getUsername().equals(currentUsername)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You can only update your own blog");
        }

        existingBlog.setTitle(updatedBlog.getTitle());
        existingBlog.setContent(updatedBlog.getContent());

        return service.saveBlog(existingBlog);
    }

    @GetMapping("/get/title/{title}")
    public List<BlogEntity> searchByTite(@PathVariable String title){
        return service.findbytitle(title);
    }

    @GetMapping("/search/category/{category}")
    public List<BlogEntity> searchByCategory(@PathVariable String category) {
        return service.searchByCategoryName(category);
    }

}
