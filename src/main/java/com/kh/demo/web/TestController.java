package com.kh.demo.web;

import com.kh.demo.test.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/test")          // http://localhost/test
public class TestController {

  @GetMapping("/t1")              // get http://localhost/test/t1
  public String t1( Model model){

    model.addAttribute("key1","KH인재교육원1");
    model.addAttribute("key2","KH인재교육원2");
    model.addAttribute("key3","KH인재교육원3");

    List<Person> persons = new ArrayList<>();
    Person p1 = new Person("홍길동", 30);
    Person p2 = new Person("홍길순", 20);
    Person p3 = new Person("홍길남", 40);

    persons.add(p1);
    persons.add(p2);
    persons.add(p3);

    model.addAttribute("persons",persons);
    return "test/test1";
  }
}
