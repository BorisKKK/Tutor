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
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.logging.Log;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import static ru.sfedu.tutor.DataProviderCSVTest.setEntitys;
import ru.sfedu.tutor.utils.ConfigurationUtil;

/**
 *
 * @author BORYAN
 */
public class DataProviderXMLTest {
    
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
    
    public DataProviderXMLTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        DataProviderXML dp = new DataProviderXML();
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
     * Test of initDataSource method, of class DataProviderXML.
     */
    @Test
    public void testInitDataSource() {
       
    }

    /**
     * Test of saveRecord method, of class DataProviderXML.
     */
    @Test
    public void testA()  {
        System.out.println("saveRecord");
        DataProviderXML dp = new DataProviderXML();
        setEntitys();
        
        ListClass lc1 = new ListClass();
        ListClass lc2 = new ListClass();
        ListClass lc3 = new ListClass();
        ListClass lc4 = new ListClass();
        
        lc1.addList(t);
        lc2.addList(p);
        lc3.addList(l);
        lc4.addList(c);
        
        Result r1 = dp.saveRecord(lc1.getList(), EnumOfEntities.TEACHER);   
        Result r2 = dp.saveRecord(lc2.getList(), EnumOfEntities.PUPIL);
        Result r3 = dp.saveRecord(lc3.getList(), EnumOfEntities.LESSONS);
        Result r4 = dp.saveRecord(lc4.getList(), EnumOfEntities.CONTRACT);
        
        Assert.assertEquals(r1.getStatus(),EnumOfStatus.SUCCESS);
        Assert.assertEquals(r2.getStatus(),EnumOfStatus.SUCCESS);
        Assert.assertEquals(r3.getStatus(),EnumOfStatus.SUCCESS);
        Assert.assertEquals(r4.getStatus(),EnumOfStatus.SUCCESS);
 
    }

    
    /**
     * Test of getRecordById method, of class DataProviderXML.
     */
    @Test
    public void testB() {
        System.out.println("getRecordById");
        DataProviderXML dp = new DataProviderXML();
        
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
     * Test of updateRecord method, of class DataProviderXML.
     */
    @Test
    public void testC() {
        System.out.println("updateRecord");
        setRandom();
        setEntitys();
        DataProviderXML dp = new DataProviderXML();
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
        
        Assert.assertEquals(r1.getStatus(),EnumOfStatus.SUCCESS);
        Assert.assertEquals(r2.getStatus(),EnumOfStatus.SUCCESS);
        Assert.assertEquals(r3.getStatus(),EnumOfStatus.SUCCESS);
        Assert.assertEquals(r4.getStatus(),EnumOfStatus.SUCCESS);
    }
    
    
    /**
     * Test of deleteRecord method, of class DataProviderXML.
     */
    @Test
    public void testD() {
        System.out.println("deleteRecord");
        DataProviderXML dp = new DataProviderXML();
        
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
