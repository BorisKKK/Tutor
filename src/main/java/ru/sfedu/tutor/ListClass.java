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
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

/**
 *
 * @author BORYAN
 */

public class ListClass<T> {
    
    private List<T> list;
    
    
    public ListClass(){
        list = new ArrayList();
       
    }
   
   @XmlElement()
   public void addList(T beans){
    list.add(beans);
    }
   //@ElementList()
   @XmlElement()
   public List<T> getList(){
       return list;
   }
   
   public void Equal(List<T> b){
       list = b;
   }
   public String toString(){
        return ""+list;
       
   }
    
}
