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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static ru.sfedu.tutor.DataProviderCSVTest.setEntitys;

/**
 *
 * @author BORYAN
 */
public class DataProviderJDBCTest {
    
    
    private static int pupilId;
        private static String pupilName;
        private static String pupilNumber;
        private static int pupilAge;
        
        private static int teacherId;
        private static String teacherName;
        private static String teacherNumber;
        private static int teacherAge;
        
        private static int lessonId;
        private static boolean skypeLessons; 
        private static String subject;
        private static int count_of_lessons;
        private static int duration_of_lesson;
        private static int price_of_lesson;
        
        private static int contractId;
        private static int pupil_id;
        private static int teacher_id;
        private static int lessons_id;
        private static boolean completion;
        
        private static Pupil p;
        private static Lessons l;
        private static Teacher t;
        private static Contract c;
        private static DataProviderJDBC dp;
    
        
        public static void setRandom(){
        String nameArr[] = {"Jhon Wood","Sam Jhons","Kate Shede","Thomas Edison","Norman Rood","Jason Muse","Jack Deison","Peter Samerly"};
        String numberArr[] = {"89185043423","89234332343","89234323245","89543432367","89556732367","89578932367","89343432367","89345672367"};
        int ageArr[] = {21,31,35,55,58,60,34,51};
        boolean boolArr[] = {true, false};
        String sbjArr[] = {"Math","Astronomy","English","China language","Engeneering","Literature","Phisics","Philosofy"};
        int countArr[] = {10,15,20,25,30,35,40,45};
        int durationtArr[] = {10,15,20,25,30,35,40,45};
        int pricetArr[] = {350,450,400,800,700,500,550,850};
        
        Random random = new Random();
        int num = random.nextInt(7);
        pupilName = nameArr[num];
        pupilNumber = numberArr[num];
        pupilAge = ageArr[num];
        num = random.nextInt(7);
        teacherName = nameArr[num];
        teacherNumber = numberArr[num];
        teacherAge = ageArr[num];
        num = random.nextInt(7);
        subject = sbjArr[num];
        count_of_lessons = countArr[num];
        duration_of_lesson = durationtArr[num]; 
        price_of_lesson = pricetArr[num];
        num = random.nextInt(1);
        skypeLessons = boolArr[num];
        completion = boolArr[num];
       
       
    }
        
    
    public static void setId(){
        int idArr[] = {71,71,73,74,75,76,77,78};
        Random random = new Random();
        int num = random.nextInt(7);
        pupilId = idArr[num];
        pupil_id = idArr[num];
        teacherId = idArr[num];
        teacher_id = idArr[num];
        lessonId = idArr[num]; 
        lessons_id = idArr[num];
        contractId = idArr[num];
    }
        
    
    public static void setEntitys(){
         p = new Pupil(pupilId,pupilName,pupilNumber,pupilAge);
         l = new Lessons(lessonId,skypeLessons,subject,count_of_lessons,duration_of_lesson,price_of_lesson);
         t = new Teacher(teacherId,teacherName,teacherNumber,teacherAge);
         c = new Contract(contractId,pupil_id,teacher_id,lessons_id,completion);
    }
    
    
    public DataProviderJDBCTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        dp = new DataProviderJDBC();
        dp.initDataSource();
        setId();
        setRandom();
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of initDataSource method, of class DataProviderJDBC.
     */
    @Test
    public void testInitDataSource() {
       
    }

    /**
     * Test of saveRecord method, of class DataProviderJDBC.
     */
    @Test
    public void tesA() {
        System.out.println("saveRecord");
        setEntitys();
        List<Teacher> listT = new ArrayList();
        List<Pupil> listP = new ArrayList();
        List<Lessons> listL = new ArrayList();
        List<Contract> listC = new ArrayList();
        listT.add(t);
        listP.add(p);
        listL.add(l);
        listC.add(c);
        
        Result r1 = dp.saveRecord(listT, EnumOfEntities.TEACHER);   
        Result r2 = dp.saveRecord(listP, EnumOfEntities.PUPIL);
        Result r3 = dp.saveRecord(listL, EnumOfEntities.LESSONS);
        Result r4 = dp.saveRecord(listC, EnumOfEntities.CONTRACT);
        
        Assert.assertEquals(r1.getStatus(),EnumOfStatus.SUCCESS);
        Assert.assertEquals(r2.getStatus(),EnumOfStatus.SUCCESS);
        Assert.assertEquals(r3.getStatus(),EnumOfStatus.SUCCESS);
        Assert.assertEquals(r4.getStatus(),EnumOfStatus.SUCCESS);
        
    }
    
    
     /**
     * Test of getRecordById method, of class DataProviderJDBC.
     */
    @Test
    public void testB() {
        System.out.println("getRecordById");
        
        
        Result r1 = dp.getRecordById(t.getId(), EnumOfEntities.TEACHER);
        Result r2 = dp.getRecordById(p.getId(), EnumOfEntities.PUPIL);
        Result r3 = dp.getRecordById(l.getId_lesson(), EnumOfEntities.LESSONS);
        Result r4 = dp.getRecordById(c.getId_contract(), EnumOfEntities.CONTRACT);
        
        Assert.assertEquals(r1.getStatus(),EnumOfStatus.ERROR);
        Assert.assertEquals(r2.getStatus(),EnumOfStatus.ERROR);
        Assert.assertEquals(r3.getStatus(),EnumOfStatus.ERROR);
        Assert.assertEquals(r4.getStatus(),EnumOfStatus.ERROR);
    }
    
    
    /**
     * Test of updateRecord method, of class DataProviderJDBC.
     */
    @Test
    public void testC() {
        System.out.println("updateRecord");
        setRandom();
        setEntitys();
        List<Teacher> listT = new ArrayList();
        List<Pupil> listP = new ArrayList();
        List<Lessons> listL = new ArrayList();
        List<Contract> listC = new ArrayList();
        listT.add(t);
        listP.add(p);
        listL.add(l);
        listC.add(c);
        
        Result r1 = dp.updateRecord(listT, EnumOfEntities.TEACHER);
        Result r2 = dp.updateRecord(listP, EnumOfEntities.PUPIL);
        Result r3 = dp.updateRecord(listL, EnumOfEntities.LESSONS);
        Result r4 = dp.updateRecord(listC, EnumOfEntities.CONTRACT);
        
//        Assert.assertEquals(r1.getStatus(),EnumOfStatus.SUCCESS);
//        Assert.assertEquals(r2.getStatus(),EnumOfStatus.SUCCESS);
//        Assert.assertEquals(r3.getStatus(),EnumOfStatus.SUCCESS);
//        Assert.assertEquals(r4.getStatus(),EnumOfStatus.SUCCESS);
    }
    
    

    /**
     * Test of deleteRecord method, of class DataProviderJDBC.
     */
    @Test
    public void testD() {
        System.out.println("deleteRecord");
        
        Result r1 = dp.deleteRecord(t.getId(), EnumOfEntities.TEACHER);
        Result r2 = dp.deleteRecord(p.getId(), EnumOfEntities.PUPIL);
        Result r3 = dp.deleteRecord(l.getId_lesson(), EnumOfEntities.LESSONS);
        Result r4 = dp.deleteRecord(c.getId_contract(), EnumOfEntities.CONTRACT);
        
        Assert.assertEquals(r1.getStatus(),EnumOfStatus.SUCCESS);
        Assert.assertEquals(r2.getStatus(),EnumOfStatus.SUCCESS);
        Assert.assertEquals(r3.getStatus(),EnumOfStatus.SUCCESS);
        Assert.assertEquals(r4.getStatus(),EnumOfStatus.SUCCESS);
    }

   

    
    
}
