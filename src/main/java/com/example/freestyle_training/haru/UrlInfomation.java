package com.example.freestyle_training.haru;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.context.ApplicationContext;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.servlet.ServletException;

import org.springframework.ui.Model;

@Controller

public class UrlInfomation {
    String url;
    String name;
    // List<String> tag = new ArrayList<String>();
}
