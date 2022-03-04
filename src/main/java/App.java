//import Squad;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;
//import Hero;
public class App {
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }

    public static void main(String[] args) {

        port(getHerokuAssignedPort());
        staticFileLocation("/public");

        //Read heroes
        get("/",(request, response) -> {
            Map<String, ArrayList> model = new HashMap<>();

            ArrayList myHeroes = Hero.getAll();
            model.put("hero",myHeroes);

            ArrayList mySquad = Squad.getAllData();
            model.put("squad",mySquad);
            System.out.println(mySquad);
            boolean len = Squad.getSize();
            String sessionData = request.session().attribute("user");
            return new ModelAndView(model,"index.hbs");
        },new HandlebarsTemplateEngine());

        //Create heroes
        post("/heroes",(request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String name = request.queryParams ("name");
            String age = request.queryParams ("age");
            Integer myAge = Integer.valueOf(age);
            String power = request.queryParams ("power");
            String weakness = request.queryParams ("weakness");
            Hero hero = new Hero(1,name,myAge,power,weakness);
            String squad = request.queryParams("squad");
            if (squad.equals("squadA")){
                Squad squad1 = new Squad(3,"The Fighters","Fighting crime",hero);
            } else if(squad.equals("squadB")){
                Squad squad1 = new Squad(3,"The winners","Helping the needy",hero);
            }else if(squad.equals("squadC")){
                Squad squad1 = new Squad(3,"The Foodies","Fighting hunger",hero);
            }else {
                System.out.println("No squad data is selected");
            }
            model.put("hero",hero);
            request.session().attribute("user",name);
            return new ModelAndView(model,"success.hbs");
        },new HandlebarsTemplateEngine());

        // delete all heroes
        get("/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            Hero.clearAllHeroes();
            Squad.clearAllSquads();
            response.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());


    }
}















