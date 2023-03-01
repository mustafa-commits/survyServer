package com.postsurvey.demo;

import com.postsurvey.demo.model.Answers;
import com.postsurvey.demo.model.survyQuestion;
import com.postsurvey.demo.model.users;
import com.postsurvey.demo.repositories.answerRepo;
import com.postsurvey.demo.repositories.questionRepo;
import com.postsurvey.demo.repositories.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
public class mainController {

    @Autowired
    userRepo userRepo;
    @Autowired
    questionRepo questionRepo;

    @Autowired
    answerRepo answerRepo;

    @Autowired
    JdbcTemplate jdbcTemplate;


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

    @PostMapping("V1/get/query")
    List<RequestObject> getquery(@RequestBody Map<String,String>x){
        return createRequestObjects(x.get("d1"),x.get("d2"));
    }


    public List<Answermodel> getAnswers(String d1, String d2) {
        String query = "SELECT question_l,choice,answer,answerdate,group_l,title,answers.user_id ,(select name from users where answers.user_id=userid) as username,\n" +
                "(select phone from users where answers.user_id=userid) as phone,\n" +
                "(select specification from users where answers.user_id=userid) as specification " +
                "FROM answers CROSS JOIN survy_question " +
                "ON ques_id = question_id " +
                "WHERE answerdate BETWEEN ? AND ?";
        Date startDate = Date.valueOf(d1);
        Date endDate = Date.valueOf(d2);
        return jdbcTemplate.query(query, new Object[] {startDate, endDate}, new AnswerRowMapper());
    }

    public Map<Integer, List<Answermodel>> groupAnswersByUserId(String d1,String d2) {
        List<Answermodel> answers = getAnswers(d1,d2);
        return answers.stream().collect(Collectors.groupingBy(Answermodel::getUserId));
    }


    public List<RequestObject> createRequestObjects(String d1,String d2) {
        Map<Integer, List<Answermodel>> groupedAnswers = groupAnswersByUserId(d1,d2);
        List<RequestObject> requestObjects = new ArrayList<>();
        for (Map.Entry<Integer, List<Answermodel>> entry : groupedAnswers.entrySet()) {
            RequestObject requestObject = new RequestObject();
            requestObject.setUserId(entry.getKey());
            requestObject.setAnswers(entry.getValue());
            requestObjects.add(requestObject);
        }
        return requestObjects;
    }


    class AnswerRowMapper implements RowMapper<Answermodel> {
        public Answermodel mapRow(ResultSet rs, int rowNum) throws SQLException {
            Answermodel answer = new Answermodel();
            answer.setQuestion(rs.getString("question_l"));
            answer.setChoice(rs.getString("choice"));
            answer.setAnswer(rs.getString("answer"));
            answer.setAnswerDate(rs.getDate("answerdate").toLocalDate());
            answer.setGroup(rs.getString("group_l"));
            answer.setTitle(rs.getString("title"));
            answer.setUserId(rs.getInt("user_id"));
            answer.setUsername(rs.getString("username"));
            answer.setPhone(rs.getString("phone"));
            answer.setSpecification(rs.getString("specification"));
            return answer;
        }
    }
}




class RequestObject {
    Integer userId;
    List<Answermodel> answers;
    // getters and setters

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<Answermodel> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answermodel> answers) {
        this.answers = answers;
    }
}

class Answermodel{
    String question;
    String choice;
    String answer;
    LocalDate AnswerDate;
    String Group;
    String Title;
    Integer UserId;
    String username;
    String phone;
    String specification;

    public Answermodel() {
    }

    public Answermodel(String question, String choice, String answer, LocalDate answerDate, String group, String title, Integer userId, String username, String phone, String specification) {
        this.question = question;
        this.choice = choice;
        this.answer = answer;
        AnswerDate = answerDate;
        Group = group;
        Title = title;
        UserId = userId;
        this.username = username;
        this.phone = phone;
        this.specification = specification;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public LocalDate getAnswerDate() {
        return AnswerDate;
    }

    public void setAnswerDate(LocalDate answerDate) {
        AnswerDate = answerDate;
    }

    public String getGroup() {
        return Group;
    }

    public void setGroup(String group) {
        Group = group;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public Integer getUserId() {
        return UserId;
    }

    public void setUserId(Integer userId) {
        UserId = userId;
    }
}