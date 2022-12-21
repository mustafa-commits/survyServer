package com.postsurvey.demo;

import com.postsurvey.demo.model.Answers;
import com.postsurvey.demo.model.survyQuestion;
import com.postsurvey.demo.model.users;
import com.postsurvey.demo.repositories.answerRepo;
import com.postsurvey.demo.repositories.questionRepo;
import com.postsurvey.demo.repositories.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class mainController {

    @Autowired
    userRepo userRepo;
    @Autowired
    questionRepo questionRepo;

    @Autowired
    answerRepo answerRepo;


    @PostMapping("V1/createUser")
    public Long adduser(@RequestBody users users){

       users u= userRepo.save(users);
return u.getUserid();
    }

    @ResponseBody
    @CrossOrigin
    @GetMapping(value = "V1/hello")
    public List<users> getmessage() {
        System.out.println("request");
     //   userRepo.save(new users(1L,"mustafa","077","",null,null));
//        questionRepo.save(new survyQuestion(1L,"question1","title","1","1",null,null,null));
//        answerRepo.save(new Answers(1L,"1",null,null,null,
//                new users(1L,"question1","title","1",null,null)
//                ,new users(1L,"mustafa","077","",null,null)));

        return userRepo.findAll();
    }

    @PostMapping("V1/Create")
    @CrossOrigin
    public String createU(@RequestBody users users_que){

        //userRepo.save(new users(1L,"mustafa","077","",null,null);
      //  questionRepo.save(new survyQuestion(1L,"question1","title","1","1",null,null,null));
       // answerRepo.save(new Answers(1L,"1",null,null,null,null,new users(1L,"mustafa","077","",null,null)));
        return "created";
    }

    @GetMapping("V1/getuser")
    public List<users> getuser(){
        return userRepo.findAll();
    }
    @GetMapping("V1/getquestion")
    public List<survyQuestion> getquestion(){
        List<survyQuestion> suv=questionRepo.findAll();
        for (int i=0;i<suv.size();i++) {
            survyQuestion x= suv.get(i);
            if (x.getIsactive()=="true"){

            }
        }
        return questionRepo.findAll();
    }

    @GetMapping("V1/getanswer")
    public List<Answers> getanswers(){
        return answerRepo.findAll();
    }

    @ResponseBody
    @PostMapping(value = "/V1/addAnswer", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addAnswer(@RequestBody Map<String,String> body){
        System.out.println("recive");
        answerRepo.save(new Answers(null,body.get("quesid"),body.get("note"),body.get("answer"),body.get("userid"),null));
        System.out.println("send answer");




    }



}
