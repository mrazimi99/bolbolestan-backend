package com.ie.bolbolestan.services;

import com.ie.bolbolestan.model.BolbolestanApplication;
import com.ie.bolbolestan.model.Student;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class ResetService {

    @RequestMapping(value = "/reset", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void reset(@RequestAttribute("id") String email) throws SQLException {
        BolbolestanApplication app = BolbolestanApplication.getInstance();
        Student student = app.getStudent(email);
        student.reset();
    }
}
