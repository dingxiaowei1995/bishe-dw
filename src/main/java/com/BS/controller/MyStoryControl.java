package com.BS.controller;

import com.BS.service.ArticleService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Describe: 我的故事Controller
 */
@RestController
public class MyStoryControl {

    @Autowired
    ArticleService articleService;

    @GetMapping("/getMyStory")
    public JSONObject getMyStory(@RequestParam("rows") String rows,
                                 @RequestParam("pageNum") String pageNum){
        return articleService.findArticleByCategory("我的故事",Integer.parseInt(rows), Integer.parseInt(pageNum));
    }

}