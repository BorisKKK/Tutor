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
@Root(name="Teacher")
public class Teacher extends PersonalData{
//    @CsvBindByPosition(position = 0)
//    private int id;
//    @CsvBindByPosition(position = 1)
//    private String name;   
//    @CsvBindByPosition(position = 2)
//    private String number;
//    @CsvBindByPosition(position = 3)
//    private int age;
    
    public Teacher() {  
         
    }
    public Teacher(int id, String name, String number, int age) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.age = age;
    }
    @Attribute(name="id")
    public void setId(int id){
        this.id = id;
    }
    @Attribute(name="id")
    public int getId(){
       return  id;
    }
    @Attribute(name="teacher_name")
    public void setName(String name){
        this.name = name;
    }
    @Attribute(name="teacher_name")
    public String getName(){
       return  name;
    }
    @Attribute(name="teacher_number")
    public void setNumber(String number){
        this.number = number;
    }
    @Attribute(name="teacher_number")
    public String getNumber(){
       return  number;
    }
    @Attribute(name="teacher_age")
    public void setAge(int age){
        this.age = age;
    }
    @Attribute(name="teacher_age")
    public int getAge(){
    return  age;
    }
    
    public String toString() {
        return "id - " + id 
                + " teacher_name - " + name 
                + " teacher_number - " + number
                + " teacher_age - " + age;
    }
    
}
