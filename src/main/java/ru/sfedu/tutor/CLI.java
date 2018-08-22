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
import java.util.Scanner;

/**
 *
 * @author BORYAN
 */
public class CLI {
    
    
      public static IDataProvider choseDataSource(String[] commands){
        IDataProvider dataProvider;
        switch (commands[1]) {
            case "db":
                dataProvider = new DataProviderJDBC();
                dataProvider.initDataSource();
                System.out.println("Data source - db");
                break;
            case "xml":
                dataProvider = new DataProviderXML();
                dataProvider.initDataSource(); 
                System.out.println("Data source - xml");
                break;
            case "csv":
                dataProvider = new DataProviderCSV();
                dataProvider.initDataSource();
                System.out.println("Data source - csv");
                break;
            default:
                dataProvider = null;
                System.out.println("Choose db, csv or xml");
        }
        return dataProvider;
    }
      
    public static void addTeacher(IDataProvider dataProvider){
        Scanner scan = new Scanner(System.in);
        Teacher t = new Teacher();
        System.out.println("Input teacher: id, name, number, age");
        System.out.print("teacher: ");
        String temp = scan.nextLine();
        String[] teacherData = temp.split(",");
        if (teacherData.length < 4 ) {
            System.out.println("Input data is invalid");
            return;
        }
        t.setId(Integer.valueOf(teacherData[0]));
        t.setName(teacherData[1]);
        t.setNumber(teacherData[2]);
        t.setAge(Integer.valueOf(teacherData[3]));
        ListClass lc = new ListClass();
        lc.addList(t);
        System.out.println(dataProvider.saveRecord(lc.getList(), EnumOfEntities.TEACHER).getStatus());
    }
    
    
    
    public static void addPupil(IDataProvider dataProvider){
        Scanner scan = new Scanner(System.in);
        Pupil p = new Pupil();
        System.out.println("Input pupil: id, name, number, age");
        System.out.print("pupil: ");
        String temp = scan.nextLine();
        String[] pupilData = temp.split(",");
        if (pupilData.length < 4 ) {
            System.out.println("Input data is invalid");
            return;
        }
        p.setId(Integer.valueOf(pupilData[0]));
        p.setName(pupilData[1]);
        p.setNumber(pupilData[2]);
        p.setAge(Integer.valueOf(pupilData[3]));
        ListClass lc = new ListClass();
        lc.addList(p);
        System.out.println(dataProvider.saveRecord(lc.getList(), EnumOfEntities.PUPIL).getStatus());
    }
    
    
    
    public static void addLessons(IDataProvider dataProvider){
        Scanner scan = new Scanner(System.in);
        Lessons l = new Lessons();
        System.out.println("Input lesson: id, skypeLesson or not, subject, count of lessons, duration of lesson, price of lesson");
        System.out.print("lesson: ");
        String temp = scan.nextLine();
        String[] lessonData = temp.split(",");
        if (lessonData.length < 6) {
            System.out.println("Input data is invalid");
            return;
        }
        l.setId_lesson(Integer.valueOf(lessonData[0]));
        l.setSkypeLessons(Boolean.valueOf(lessonData[1]));
        l.setSubject(lessonData[2]);
        l.setCount_of_lessons(Integer.valueOf(lessonData[3]));
        l.setDuration_of_lesson(Integer.valueOf(lessonData[4]));
        l.setPrice_of_lesson(Integer.valueOf(lessonData[5]));
        ListClass lc = new ListClass();
        lc.addList(l);
        System.out.println(dataProvider.saveRecord(lc.getList(), EnumOfEntities.LESSONS).getStatus());
    }
    
    
    
    public static void addContract(IDataProvider dataProvider){
        Scanner scan = new Scanner(System.in);
        Contract c = new Contract();
        System.out.println("Input contract: id, id of pupil, id of teacher, id of lesson, compleate or not");
        System.out.print("contract: ");
        String temp = scan.nextLine();
        String[] contractData = temp.split(",");
        if (contractData.length < 5) {
            System.out.println("Input data is invalid");
            return;
        }
        c.setId_contract(Integer.valueOf(contractData[0]));
        c.setPupil_id(Integer.valueOf(contractData[1]));
        c.setTeacher_id(Integer.valueOf(contractData[2]));
        c.setLessons_id(Integer.valueOf(contractData[3]));
        c.setCompletion(Boolean.valueOf(contractData[4]));
        ListClass lc = new ListClass();
        lc.addList(c);
        System.out.println(dataProvider.saveRecord(lc.getList(), EnumOfEntities.CONTRACT).getStatus());
    }
    
    public static void updateTeacher(IDataProvider dataProvider){
        String temp;
        int id;
        Scanner scan = new Scanner(System.in);
        Teacher t = new Teacher();
        System.out.print("Input teacher id: ");
        temp = scan.nextLine();
        id = Integer.valueOf(temp);
        Result r = dataProvider.getRecordById(id, EnumOfEntities.TEACHER);
        System.out.println(r.getData());
        System.out.println("Input teacher: id, name, number, age");
        System.out.print("teacher: ");
        temp = scan.nextLine();
        String[] teacherData = temp.split(",");
        if (teacherData.length < 4 ) {
            System.out.println("Input data is invalid");
            return;
        }
        t.setId(Integer.valueOf(teacherData[0]));
        t.setName(teacherData[1]);
        t.setNumber(teacherData[2]);
        t.setAge(Integer.valueOf(teacherData[3]));
        ListClass lc = new ListClass();
        lc.addList(t);
        System.out.println(dataProvider.updateRecord(lc.getList(), EnumOfEntities.TEACHER).getStatus());
    }

    public static void updatePupil(IDataProvider dataProvider) {
        String temp;
        int id;
        Scanner scan = new Scanner(System.in);
        Pupil p = new Pupil();
        System.out.print("Input pupil id: ");
        temp = scan.nextLine();
        id = Integer.valueOf(temp);
        Result r = dataProvider.getRecordById(id, EnumOfEntities.PUPIL);
        System.out.println(r.getData());
        System.out.println("Input pupil: id, name, number, age");
        System.out.print("pupil: ");
        temp = scan.nextLine();
        String[] teacherData = temp.split(",");
        if (teacherData.length < 4 ) {
            System.out.println("Input data is invalid");
            return;
        }
        p.setId(Integer.valueOf(teacherData[0]));
        p.setName(teacherData[1]);
        p.setNumber(teacherData[2]);
        p.setAge(Integer.valueOf(teacherData[3]));
        ListClass lc = new ListClass();
        lc.addList(p);
        System.out.println(dataProvider.updateRecord(lc.getList(), EnumOfEntities.PUPIL).getStatus());
    }
    
    public static void updateLessons(IDataProvider dataProvider) {
        String temp;
        int id;
        Scanner scan = new Scanner(System.in);
        Lessons l = new Lessons();
        System.out.print("Input lesson id: ");
        temp = scan.nextLine();
        id = Integer.valueOf(temp);
        Result r = dataProvider.getRecordById(id, EnumOfEntities.LESSONS);
        System.out.println(r.getData());
        System.out.println("Input lesson: id, skypeLesson or not, subject, count of lessons, duration of lesson, price of lesson");
        System.out.print("lesson: ");
        temp = scan.nextLine();
        String[] lessonData = temp.split(",");
        if (lessonData.length < 6 ) {
            System.out.println("Input data is invalid");
            return;
        }
        l.setId_lesson(Integer.valueOf(lessonData[0]));
        l.setSkypeLessons(Boolean.valueOf(lessonData[1]));
        l.setSubject(lessonData[2]);
        l.setCount_of_lessons(Integer.valueOf(lessonData[3]));
        l.setDuration_of_lesson(Integer.valueOf(lessonData[4]));
        l.setPrice_of_lesson(Integer.valueOf(lessonData[5]));
        ListClass lc = new ListClass();
        lc.addList(l);
        System.out.println(dataProvider.updateRecord(lc.getList(), EnumOfEntities.LESSONS).getStatus());
    }
    
    public static void updateContract(IDataProvider dataProvider) {
        String temp;
        int id;
        Scanner scan = new Scanner(System.in);
        Contract c = new Contract();
        System.out.print("Input contract id: ");
        temp = scan.nextLine();
        id = Integer.valueOf(temp);
        Result r = dataProvider.getRecordById(id, EnumOfEntities.CONTRACT);
        System.out.println(r.getData());
        System.out.println("Input contract: id, id of pupil, id of teacher, id of lesson, compleate or not");
        System.out.print("contract: ");
        temp = scan.nextLine();
        String[] contractData = temp.split(",");
        if (contractData.length < 5 ) {
            System.out.println("Input data is invalid");
            return;
        }
        c.setId_contract(Integer.valueOf(contractData[0]));
        c.setPupil_id(Integer.valueOf(contractData[1]));
        c.setTeacher_id(Integer.valueOf(contractData[2]));
        c.setLessons_id(Integer.valueOf(contractData[3]));
        c.setCompletion(Boolean.valueOf(contractData[4]));
        ListClass lc = new ListClass();
        lc.addList(c);
        System.out.println(dataProvider.updateRecord(lc.getList(), EnumOfEntities.CONTRACT).getStatus());
    }
    
    public static void deleteRec(IDataProvider dataProvider, EnumOfEntities type){
        int id;
        Scanner scan = new Scanner(System.in);
        //System.out.println("Entry list:");
        //selectEntry(dataProvider, type, true);
        System.out.print("Enter entry id to delete: ");
        String temp = scan.nextLine();
        try {
             id = Integer.valueOf(temp);
        } catch (NumberFormatException e){
            System.out.println("Invalid id");
            return;
        }
        System.out.println(dataProvider.deleteRecord(id, type).getStatus());
    }
    
    
    public static void select(IDataProvider dataProvider, String type){
        switch(type){
            case "teacher":
                //System.out.println("sdf");
                dataProvider.selectRecords(EnumOfEntities.TEACHER).getData().stream().forEach(r -> {System.out.println("\n"+r+"\n");});
                
            break;
            case "pupil":
                dataProvider.selectRecords(EnumOfEntities.PUPIL).getData().stream().forEach(r -> {System.out.println("\n"+r+"\n");});
            break;
            case "lessons":
                dataProvider.selectRecords(EnumOfEntities.LESSONS).getData().stream().forEach(r -> {System.out.println("\n"+r+"\n");});
            break;
            case "contract":
                dataProvider.selectRecords(EnumOfEntities.CONTRACT).getData().stream().forEach(r -> {System.out.println("\n"+r+"\n");});
            break;
        }
        
    }
    
    
    public static void help(){
        System.out.println("Choose db:                  use db/xml/csv");
        System.out.println("Select record:              select teacher/pupil/lessons/contract");
        System.out.println("Create record:              create teacher/pupil/lessons/contract");
        System.out.println("Delete record:              delete teacher/pupil/lessons/contract");
        System.out.println("Update record:              update teacher/pupil/lessons/contract");
        System.out.println("Exit:                       q");
     
      
    }
    
    
}
