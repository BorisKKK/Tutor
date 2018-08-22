/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;


import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 *
 * @author BORYAN
 */
@Root(name="Lessons")
public class Lessons {
    @CsvBindByPosition(position = 0)
    private int id_lesson;
    @CsvBindByPosition(position = 1)
    private boolean skypeLessons; 
    @CsvBindByPosition(position = 2)
    private String subject;
    @CsvBindByPosition(position = 3)
    private int count_of_lessons;
    @CsvBindByPosition(position = 4)
    private int duration_of_lesson;
    @CsvBindByPosition(position = 5)
    private int price_of_lesson;
    
    public Lessons() {  
         
    }
    
     public Lessons(int id, boolean skype, String subject, int count, int duration, int price) {
        this.id_lesson = id;
        this.skypeLessons = skype;
        this.subject = subject;
        this.count_of_lessons = count;
        this.duration_of_lesson = duration;
        this.price_of_lesson = price;
    }
    @Attribute(name="id")
    public void setId_lesson(int id){
        this.id_lesson = id;
    }
    @Attribute(name="id")
    public int getId_lesson(){
       return  id_lesson;
    }
    @Attribute(name="SkypeLessons")
    public void setSkypeLessons(boolean skype){
        this.skypeLessons = skype;
    }
    @Attribute(name="SkypeLessons")
    public boolean getSkypeLessons(){
       return  skypeLessons;
    }
    @Attribute(name="Subject")
    public void setSubject(String sbj){
        this.subject = sbj;
    }
    @Attribute(name="Subject")
    public String getSubject(){
       return  subject;
    }
    @Attribute(name="Count_of_lessons")
    public void setCount_of_lessons(int count){
        this.count_of_lessons = count;
    }
    @Attribute(name="Count_of_lessons")
    public int getCount_of_lessons(){
       return  count_of_lessons;
    }
    @Attribute(name="Duration_of_lesson")
    public void setDuration_of_lesson(int dur){
        this.duration_of_lesson = dur;
    }
    @Attribute(name="Duration_of_lesson")
    public int getDuration_of_lesson(){
       return  duration_of_lesson;
    }
    @Attribute(name="Price_of_lesson")
    public void setPrice_of_lesson(int price){
        this.price_of_lesson = price;
    }
    @Attribute(name="Price_of_lesson")
    public int getPrice_of_lesson(){
       return  price_of_lesson;
    }
     public String toString() {
        return "id - " + id_lesson 
                + " is_skype_lessons - " + skypeLessons 
                + " subject - " + subject
                + " count_of_lessons - " + count_of_lessons
                + " duration_of_lessons - " + duration_of_lesson
                + " price_of_lesson - " + price_of_lesson;
    }
}
