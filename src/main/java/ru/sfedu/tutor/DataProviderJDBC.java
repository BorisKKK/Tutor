/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.tutor;

import classes.Contract;
import classes.Lessons;
import classes.Pupil;
import classes.Teacher;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import static ru.sfedu.tutor.EnumOfStatus.SUCCESS;

/**
 *
 * @author BORYAN
 */
public class DataProviderJDBC implements IDataProvider {

    
    private static Logger log = Logger.getLogger(TutorClient.class);
    private Connection con;
    private Statement statement;

 
    
    @Override
    public void initDataSource() {
        
        ConnectDB c = new ConnectDB();
        con = c.Connect();
    }

    @Override
    public <T> Result saveRecord(List<T> beans, EnumOfEntities typeOfEntity) {
           switch (typeOfEntity) {
            case TEACHER:
                        return addTeacher((List<Teacher>) beans);       
            case PUPIL:
                        return addPupil((List<Pupil>) beans);
            case CONTRACT:
                        return addContract((List<Contract>) beans);
            case LESSONS:
                        return addLessons((List<Lessons>) beans);
        }
        return null;
    }

    
    private Result addTeacher(List<Teacher> beans) {
        List<String> messages = new ArrayList<>();
                   
            beans.stream().forEach(bean -> {
            switch (getRecordById(bean.getId(), EnumOfEntities.TEACHER).getStatus()){
                case SUCCESS:
            {
                try {
                  
                statement = (Statement) con.createStatement();
                } catch (SQLException ex) {
                    messages.add("Create statement error.");
                }
                try {
                    String sql = "INSERT INTO teacher(id_teacher, teacherName, teacherNumber, teacherAge) VALUES"+"("+bean.getId()+",\'"+bean.getName()+"\',\'"+ bean.getNumber()+"\',\'"+bean.getAge()+"\');";
                    statement.executeUpdate(sql);
                } catch (SQLException ex) {
                    messages.add("Insert error.");
                }
                
            }       
                    break;
                case ERROR:
                    messages.add("Record " + bean + " was not added. Id must be unique");
                    break;
                }
        });
            
            if(!messages.isEmpty()) {
            messages.add("Records were not added!");
            return new Result(EnumOfStatus.ERROR, null, messages);
        }
                      
        return new Result(EnumOfStatus.SUCCESS, null, Arrays.asList("Records successfully added."));
    }

    
    
    
    private Result addPupil(List<Pupil> beans) {
        List<String> messages = new ArrayList<>();
                   
            beans.stream().forEach(bean -> {
            switch (getRecordById(bean.getId(), EnumOfEntities.PUPIL).getStatus()){
                case SUCCESS:
            {
                try {
                  
                statement = (Statement) con.createStatement();
                } catch (SQLException ex) {
                    messages.add("Create statement error.");
                }
                try {
                    String sql = "INSERT INTO pupil(id_pupil, pupilName, pupilNumber, pupilAge) VALUES"+"("+bean.getId()+",\'"+bean.getName()+"\',\'"+ bean.getNumber()+"\',\'"+bean.getAge()+"\');";
                    statement.executeUpdate(sql);
                } catch (SQLException ex) {
                    messages.add("Insert error.");
                }
                
            }       
                    break;
                case ERROR:
                    messages.add("Record " + bean + " was not added. Id must be unique");
                    break;
                }
        });
            
            if(!messages.isEmpty()) {
            messages.add("Records were not added!");
            return new Result(EnumOfStatus.ERROR, null, messages);
        }
                      
        return new Result(EnumOfStatus.SUCCESS, null, Arrays.asList("Records successfully added."));
    }


    private Result addLessons(List<Lessons> beans) {
        List<String> messages = new ArrayList<>();
                   
            beans.stream().forEach(bean -> {
            switch (getRecordById(bean.getId_lesson(), EnumOfEntities.LESSONS).getStatus()){
                case SUCCESS:
            {
                try {
                  
                statement = (Statement) con.createStatement();
                } catch (SQLException ex) {
                    messages.add("Create statement error.");
                }
                try {
                    String sql = "INSERT INTO lessons(id_lesson, skypeLessons, subject, count_of_lessons, duration_of_lesson, price_of_lesson) VALUES"+"("+bean.getId_lesson()+","+bean.getSkypeLessons()+",\'"+ bean.getSubject()+"\',\'"+bean.getCount_of_lessons()+"\',\'"+bean.getDuration_of_lesson()+"\',\'"+bean.getPrice_of_lesson()+"');";
                    //System.out.println(sql);
                    statement.executeUpdate(sql);
                } catch (SQLException ex) {
                    messages.add("Insert error.");
                }
            }       
                    break;
                case ERROR:
                    messages.add("Record " + bean + " was not added. Id must be unique");
                    break;
                }
        });
            
            if(!messages.isEmpty()) {
            messages.add("Records were not added!");
            return new Result(EnumOfStatus.ERROR, null, messages);
        }
                      
        return new Result(EnumOfStatus.SUCCESS, null, Arrays.asList("Records successfully added."));
    }
    
    
     private Result addContract(List<Contract> beans) {
        List<String> messages = new ArrayList<>();
                   
            beans.stream().forEach(bean -> {
            switch (getRecordById(bean.getId_contract(), EnumOfEntities.CONTRACT).getStatus()){
                case SUCCESS:
            {
                try {
                  
                statement = (Statement) con.createStatement();
                } catch (SQLException ex) {
                    messages.add("Create statement error.");
                }
                try {
                    String sql = "INSERT INTO contract(id_contract, pupil_id, teacher_id, lessons_id, completion) VALUES"+"("+bean.getId_contract()+","+bean.getPupil_id()+","+ bean.getTeacher_id()+","+bean.getLessons_id()+","+bean.getCompletion()+");";
                    //System.out.println(sql);
                    statement.executeUpdate(sql);
                } catch (SQLException ex) {
                    messages.add("Insert error.");
                }
            }       
                    break;
                case ERROR:
                    messages.add("Record " + bean + " was not added. Id must be unique");
                    break;
                }
        });
            
            if(!messages.isEmpty()) {
            messages.add("Records were not added!");
            return new Result(EnumOfStatus.ERROR, null, messages);
        }
                      
        return new Result(EnumOfStatus.SUCCESS, null, Arrays.asList("Records successfully added."));
    }
    
     
     
     
     
    @Override
    public Result deleteRecord(int id, EnumOfEntities typeOfEntity) {
          
        try {
            statement = (Statement) con.createStatement();
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DataProviderJDBC.class.getName()).log(Level.SEVERE, null, ex);
        } 
        switch(typeOfEntity){
            case TEACHER:                     
                        try {
                        statement.executeUpdate("DELETE FROM teacher WHERE id_teacher = "+id);
                        } catch (SQLException ex) {
                            return new Result(EnumOfStatus.ERROR, null,  Arrays.asList("Delete error."));
                        }
                    return new Result(EnumOfStatus.SUCCESS, null, Arrays.asList("Records successfully deleted."));
            case PUPIL:                     
                        try {
                        statement.executeUpdate("DELETE FROM pupil WHERE id_pupil = "+id);
                        } catch (SQLException ex) {
                            return new Result(EnumOfStatus.ERROR, null,  Arrays.asList("Delete error."));
                        }
                    return new Result(EnumOfStatus.SUCCESS, null, Arrays.asList("Records successfully deleted."));
               
            case LESSONS:                     
                        try {
                        statement.executeUpdate("DELETE FROM lessons WHERE id_lesson = "+id);
                        } catch (SQLException ex) {
                            return new Result(EnumOfStatus.ERROR, null,  Arrays.asList("Delete error."));
                        }
                    return new Result(EnumOfStatus.SUCCESS, null, Arrays.asList("Records successfully deleted."));
                    
            case CONTRACT:                     
                        try {
                        statement.executeUpdate("DELETE FROM contract WHERE id_contract = "+id);
                        } catch (SQLException ex) {
                            return new Result(EnumOfStatus.ERROR, null,  Arrays.asList("Delete error."));
                        }
                    return new Result(EnumOfStatus.SUCCESS, null, Arrays.asList("Records successfully deleted."));
        }
        
        
        return null;
                   
 
}
              
                    
            @Override
            public <T> Result updateRecord(List<T> beans, EnumOfEntities typeOfEntity) {
                switch (typeOfEntity) {
            case TEACHER:
                        return updateTeacher((List<Teacher>) beans);       
            case PUPIL:
                        return updatePupil((List<Pupil>) beans);
            case CONTRACT:
                        return updateContract((List<Contract>) beans);
            case LESSONS:
                        return updateLessons((List<Lessons>) beans);
        }
        return null;
            
    }
                
       
                    
    

    @Override
    public Result getRecordById(int id, EnumOfEntities typeOfEntity) {
        PreparedStatement statement;
        switch (typeOfEntity) {
            case TEACHER:
                List<Teacher> beanT = new ArrayList();
                try {
                    statement = con.prepareStatement("SELECT * FROM teacher WHERE id_teacher = "+id);
                    ResultSet rs = statement.executeQuery();
                    
                    
                    
//                    List<Teacher> l = new ArrayList();
//                    while(rs.next()){
//                        int i = rs.getInt("id_teacher");
//                        String result = rs.getString("teacherName");
//                        String r = rs.getString("teacherNumber");
//                        int p = rs.getInt("teacherAge");
//                        //System.out.println("\n"+" " + rs+"\n");
//                        Teacher t1 = new Teacher(i, result,r,p);
//                        l.add(t1);
//                    }
//                    System.out.println("\n"+" " + l +"\n");
                    
                    
                    if (rs.next()){
                        int idSaved = rs.getInt("id_teacher");
                        String nameSaved = rs.getString("teacherName");
                        String pathNumber = rs.getString("teacherNumber");
                        int pathAge = rs.getInt("teacherAge");
                        Teacher t = new Teacher(idSaved, nameSaved,pathNumber,pathAge);
                        beanT.add(t);                               
                        return new Result(EnumOfStatus.ERROR, Arrays.asList(t),Arrays.asList("The record was successfully found."));
                    }else{
                        return new Result(EnumOfStatus.SUCCESS, null,Arrays.asList("unique id."));
                    }
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DataProviderJDBC.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
        
            case PUPIL:
                List<Pupil> beanP = new ArrayList();
                try {
                    statement = con.prepareStatement("SELECT * FROM pupil WHERE id_pupil = "+id);
                    ResultSet rs = statement.executeQuery();
                    if (rs.next()){
                        int idSaved = rs.getInt("id_pupil");
                        String nameSaved = rs.getString("pupilName");
                        String pathNumber = rs.getString("pupilNumber");
                        int pathAge = rs.getInt("pupilAge");
                        Pupil t = new Pupil(idSaved, nameSaved,pathNumber,pathAge);
                        beanP.add(t);                               
                        return new Result(EnumOfStatus.ERROR, Arrays.asList(t),Arrays.asList("The record was successfully found."));
                    }else{
                        return new Result(EnumOfStatus.SUCCESS, null,Arrays.asList("unique id."));
                    }
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DataProviderJDBC.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case LESSONS:
                List<Lessons> beanL = new ArrayList();
                try {
                    statement = con.prepareStatement("SELECT * FROM lessons WHERE id_lesson = "+id);
                    ResultSet rs = statement.executeQuery();
                    if (rs.next()){
                        int idSaved = rs.getInt("id_lesson");
                        Boolean skypeLessons = rs.getBoolean("skypeLessons");
                        String nameSaved = rs.getString("subject");
                        int pathNumber = rs.getInt("count_of_lessons");
                        int duration = rs.getInt("duration_of_lesson");
                        int price = rs.getInt("price_of_lesson");
                        //int pathAge = rs.getInt("pupilAge");
                        Lessons t = new Lessons(idSaved, skypeLessons,nameSaved,pathNumber,duration,price);
                        beanL.add(t);                               
                        return new Result(EnumOfStatus.ERROR, Arrays.asList(t),Arrays.asList("The record was successfully found."));
                    }else{
                        return new Result(EnumOfStatus.SUCCESS, null,Arrays.asList("unique id."));
                    }
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DataProviderJDBC.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case CONTRACT:
                List<Contract> beanC = new ArrayList();
                try {
                    statement = con.prepareStatement("SELECT * FROM contract WHERE id_contract = "+id);
                    ResultSet rs = statement.executeQuery();
                    if (rs.next()){
                        int idSaved = rs.getInt("id_contract");
                        int idPupil = rs.getInt("pupil_id");
                        int idTeacher = rs.getInt("teacher_id");
                        int idLesson = rs.getInt("lessons_id");
                        Boolean completion = rs.getBoolean("completion");
                        
                      
                        Contract c = new Contract(idSaved, idPupil,idTeacher,idLesson,completion);
                        beanC.add(c);                               
                        return new Result(EnumOfStatus.ERROR, Arrays.asList(c),Arrays.asList("The record was successfully found."));
                    }else{
                        return new Result(EnumOfStatus.SUCCESS, null,Arrays.asList("unique id."));
                    }
                } catch (SQLException ex) {
                    java.util.logging.Logger.getLogger(DataProviderJDBC.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
    }
     return new Result(EnumOfStatus.SUCCESS, null,Arrays.asList("Record not found."));
    }

    private Result updateTeacher(List<Teacher> beans) {
        List<String> messages = new ArrayList<>();
                   
            beans.stream().forEach(bean -> {
            switch (getRecordById(bean.getId(), EnumOfEntities.TEACHER).getStatus()){
                case SUCCESS:
            
                messages.add("Record " + bean + " not exist.");
                break;
                case ERROR:
                {
                try {
                  
                statement = (Statement) con.createStatement();
                } catch (SQLException ex) {
                    messages.add("Create statement error.");
                }
                try {
                    String sql = "UPDATE teacher SET teacherName =" + "\'"+bean.getName()+"\' , teacherNumber = " + "\'"+bean.getNumber()+"\', teacherAge = " + bean.getAge() + " WHERE id_teacher = "+ bean.getId()+";";
                    //System.out.println(sql);
                    statement.executeUpdate(sql);
                } catch (SQLException ex) {
                    messages.add("Update error.");
                }
                }
            }       
                
        });
            
            if(!messages.isEmpty()) {
            messages.add("Record not updeted.");
            return new Result(EnumOfStatus.ERROR, null, messages);
        }
                      
        return new Result(EnumOfStatus.SUCCESS, null, Arrays.asList("Record updated."));
    }

    private Result updatePupil(List<Pupil> beans) {
        List<String> messages = new ArrayList<>();
                   
            beans.stream().forEach(bean -> {
            switch (getRecordById(bean.getId(), EnumOfEntities.PUPIL).getStatus()){
                case SUCCESS:
            
                messages.add("Record " + bean + " not exist.");
                break;
                case ERROR:
                {
                try {
                  
                statement = (Statement) con.createStatement();
                } catch (SQLException ex) {
                    messages.add("Create statement error.");
                }
                try {
                    String sql = "UPDATE pupil SET pupilName =" + "\'"+bean.getName()+"\' , pupilNumber = " + "\'"+bean.getNumber()+"\', pupilAge = " + bean.getAge() + " WHERE id_pupil = "+ bean.getId()+";";
                    //System.out.println(sql);
                    statement.executeUpdate(sql);
                } catch (SQLException ex) {
                    messages.add("Update error.");
                }
                }
            }       
                
        });
            
            if(!messages.isEmpty()) {
            messages.add("Record not updeted.");
            return new Result(EnumOfStatus.ERROR, null, messages);
        }
                      
        return new Result(EnumOfStatus.SUCCESS, null, Arrays.asList("Record updated."));
    }

    

    private Result updateLessons(List<Lessons> beans) {
        List<String> messages = new ArrayList<>();
                   
            beans.stream().forEach(bean -> {
            switch (getRecordById(bean.getId_lesson(), EnumOfEntities.LESSONS).getStatus()){
                case SUCCESS:
            
                messages.add("Record " + bean + " not exist.");
                break;
                case ERROR:
                {
                try {
                  
                statement = (Statement) con.createStatement();
                } catch (SQLException ex) {
                    messages.add("Create statement error.");
                }
                try {
                    String sql = "UPDATE lessons SET skypeLessons =" + bean.getSkypeLessons()+" , subject = " + "\'"+bean.getSubject()+"\', count_of_lessons = " + bean.getCount_of_lessons() + " ,duration_of_lesson = " + bean.getDuration_of_lesson() + " ,price_of_lesson = " + bean.getPrice_of_lesson()+" WHERE id_lesson = "+ bean.getId_lesson()+";";
                    //System.out.println(sql);
                    statement.executeUpdate(sql);
                } catch (SQLException ex) {
                    messages.add("Update error.");
                }
                }
            }       
                
        });
            
            if(!messages.isEmpty()) {
            messages.add("Record not updeted.");
            return new Result(EnumOfStatus.ERROR, null, messages);
        }
                      
        return new Result(EnumOfStatus.SUCCESS, null, Arrays.asList("Record updated."));
    }
    
    
    private Result updateContract(List<Contract> beans) {
        List<String> messages = new ArrayList<>();
                   
            beans.stream().forEach(bean -> {
            switch (getRecordById(bean.getId_contract(), EnumOfEntities.CONTRACT).getStatus()){
                case SUCCESS:
            
                messages.add("Record " + bean + " not exist.");
                break;
                case ERROR:
                {
                try {
                  
                statement = (Statement) con.createStatement();
                } catch (SQLException ex) {
                    messages.add("Create statement error.");
                }
                try {
                    String sql = "UPDATE contract SET pupil_id =" + bean.getPupil_id() + " , teacher_id = " +bean.getTeacher_id()+", lessons_id = " + bean.getLessons_id() +", completion = " + bean.getCompletion() +  " WHERE id_contract = "+ bean.getId_contract()+";";
                    //System.out.println(sql);
                    statement.executeUpdate(sql);
                } catch (SQLException ex) {
                    messages.add("Update error.");
                }
                }
            }       
                
        });
            
            if(!messages.isEmpty()) {
            messages.add("Record not updeted.");
            return new Result(EnumOfStatus.ERROR, null, messages);
        }
                      
        return new Result(EnumOfStatus.SUCCESS, null, Arrays.asList("Record updated."));
    }

    @Override
    public Result selectRecords(EnumOfEntities typeOfEntity) {
        PreparedStatement statement;
        ResultSet rs;
        switch (typeOfEntity) {
            case TEACHER:
                List<Teacher> beanT = new ArrayList();
                try {
                        statement = con.prepareStatement("SELECT * FROM teacher");
                        rs = statement.executeQuery();
                        //List<Teacher> l = new ArrayList();
                        while(rs.next()){
                            int i = rs.getInt("id_teacher");
                            String result = rs.getString("teacherName");
                            String r = rs.getString("teacherNumber");
                            int p = rs.getInt("teacherAge");
                            //System.out.println("\n"+" " + rs+"\n");
                            Teacher t = new Teacher(i, result,r,p);
                            beanT.add(t);
                        }
                        //System.out.println("\n"+" " + beanT +"\n");
                    }
                catch (SQLException ex) {
                        java.util.logging.Logger.getLogger(DataProviderJDBC.class.getName()).log(Level.SEVERE, null, ex);
                    }
                return new Result(EnumOfStatus.SUCCESS, beanT, null);
            case PUPIL:
                List<Pupil> beanP = new ArrayList();
                try {
                        statement = con.prepareStatement("SELECT * FROM pupil");
                        rs = statement.executeQuery();
                        //List<Teacher> l = new ArrayList();
                        while(rs.next()){
                            int i = rs.getInt("id_pupil");
                            String result = rs.getString("pupilName");
                            String r = rs.getString("pupilNumber");
                            int p = rs.getInt("pupilAge");
                            //System.out.println("\n"+" " + rs+"\n");
                            Pupil t = new Pupil(i, result,r,p);
                            beanP.add(t);
                        }
                        //System.out.println("\n"+" " + beanT +"\n");
                    }
                catch (SQLException ex) {
                        java.util.logging.Logger.getLogger(DataProviderJDBC.class.getName()).log(Level.SEVERE, null, ex);
                    }
                return new Result(EnumOfStatus.SUCCESS, beanP, null);
            case LESSONS:
                List<Lessons> beanL = new ArrayList();
                try {
                        statement = con.prepareStatement("SELECT * FROM lessons");
                        rs = statement.executeQuery();
                        //List<Teacher> l = new ArrayList();
                        while(rs.next()){
                            int idSaved = rs.getInt("id_lesson");
                            Boolean skypeLessons = rs.getBoolean("skypeLessons");
                            String nameSaved = rs.getString("subject");
                            int pathNumber = rs.getInt("count_of_lessons");
                            int duration = rs.getInt("duration_of_lesson");
                            int price = rs.getInt("price_of_lesson");
                            Lessons t = new Lessons(idSaved, skypeLessons,nameSaved,pathNumber,duration,price);
                            beanL.add(t);
                        }
                        //System.out.println("\n"+" " + beanT +"\n");
                    }
                catch (SQLException ex) {
                        java.util.logging.Logger.getLogger(DataProviderJDBC.class.getName()).log(Level.SEVERE, null, ex);
                    }
                return new Result(EnumOfStatus.SUCCESS, beanL, null);
            case CONTRACT:
                List<Contract> beanC = new ArrayList();
                try {
                        statement = con.prepareStatement("SELECT * FROM contract");
                        rs = statement.executeQuery();
                        //List<Teacher> l = new ArrayList();
                        while(rs.next()){
                        int idSaved = rs.getInt("id_contract");
                        int idPupil = rs.getInt("pupil_id");
                        int idTeacher = rs.getInt("teacher_id");
                        int idLesson = rs.getInt("lessons_id");
                        Boolean completion = rs.getBoolean("completion");
                        Contract c = new Contract(idSaved, idPupil,idTeacher,idLesson,completion);
                        beanC.add(c);
                        }
                        //System.out.println("\n"+" " + beanT +"\n");
                    }
                catch (SQLException ex) {
                        java.util.logging.Logger.getLogger(DataProviderJDBC.class.getName()).log(Level.SEVERE, null, ex);
                    }
                return new Result(EnumOfStatus.SUCCESS, beanC, null);
        }
        return new Result(EnumOfStatus.ERROR, null, Arrays.asList("There is no records"));
    }
}
