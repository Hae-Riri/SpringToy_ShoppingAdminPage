package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Category;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

public class CategoryRepositoryTest extends StudyApplicationTests {

    @Autowired
    private CategoryRepository categoryRepostory;

    @Test
    public void create(){
        String type = "COMPUTER";
        String title= "컴퓨터";
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy ="AdminServer";

        //lombok @Data 땜에 생성자 자동 만들어진 상태
        Category category = new Category();
        category.setType(type);
        category.setTitle(title);
        category.setCreatedAt(createdAt);
        category.setCreatedBy(createdBy);

        Category newCategory = categoryRepostory.save(category);
        Assert.assertNotNull(newCategory);
        Assert.assertEquals(newCategory.getType(),type);
        Assert.assertEquals(newCategory.getTitle(),title);

    }

    @Test
    public void read(){
        String type = "COMPUTER";
        Optional<Category> optionalCategory =categoryRepostory.findByType(type);

        //select * from category where type='COMPUTER'

        optionalCategory.ifPresent(c ->{
            Assert.assertEquals(c.getType(),type);
            System.out.println(c.getId());
            System.out.println(c.getType());
            System.out.println(c.getTitle());
        });

    }

}
