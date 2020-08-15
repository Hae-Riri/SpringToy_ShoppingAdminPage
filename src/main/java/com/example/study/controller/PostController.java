package com.example.study.controller;

import com.example.study.model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PostController {

    //HTML <form>
    //ajax 검색
    // http통신할 때 post body -> data
    //포스트방식은 json, xml, multipart=form/ text-plain 여러가지 형태를 지원해

    @PostMapping(value = "/postMethod")
    public SearchParam postMethod(@RequestBody SearchParam searchParam){
        return searchParam;
    }

    @PutMapping("/putMethod")
    public void put(){

    }
    @PatchMapping("/patchMethod")
    public void patch(){

    }
    @DeleteMapping("/deleteMethod")
    public void delete(){

    }

}
