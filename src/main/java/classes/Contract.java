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
@Root(name="Contract")
public class Contract {
    @CsvBindByPosition(position = 0)
    private int id_contract;
    @CsvBindByPosition(position = 1)
    private int pupil_id;
    @CsvBindByPosition(position = 2)
    private int teacher_id;
    @CsvBindByPosition(position = 3)
    private int lessons_id;
    @CsvBindByPosition(position = 4)
    private boolean completion;
    
    public Contract() {  
         
    }
    
      public Contract(int id, int pupil, int teacher, int lessons, boolean completion) {
        this.id_contract = id;
        this.pupil_id = pupil;
        this.teacher_id = teacher;
        this.lessons_id = lessons;
        this.completion = completion;
    }
    @Attribute(name="id")
    public void setId_contract(int id_contract){
        this.id_contract = id_contract;
    }
    @Attribute(name="id")
    public int getId_contract(){
       return  id_contract;
    }
    @Attribute(name="Pupil")
    public void setPupil_id(int id_pupil){
        this.pupil_id = id_pupil;
    }
    @Attribute(name="Pupil")
    public int getPupil_id(){
       return  pupil_id;
    }
    @Attribute(name="Teacher")
    public void setTeacher_id(int id_teacher){
        this.teacher_id = id_teacher;
    }
    @Attribute(name="Teacher")
    public int getTeacher_id(){
       return  teacher_id;
    }
    @Attribute(name="Lessons")
    public void setLessons_id(int id_lessons){
        this.lessons_id = id_lessons;
    }
    @Attribute(name="Lessons")
     public int getLessons_id(){
       return  lessons_id;
    }
     @Attribute(name="Completion")
    public void setCompletion(boolean comp){
        this.completion = comp;
    }
    @Attribute(name="Completion")
    public boolean getCompletion(){
       return  completion;      
    }
    public String toString() {
        return "id - " + id_contract 
                + " pupil_id - " + pupil_id
                + " teacher_id - " +teacher_id
                + " lessons_id - " + lessons_id
                + " completion - " + completion;
    }
 
}
