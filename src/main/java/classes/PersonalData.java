/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import com.opencsv.bean.CsvBindByName;
import org.simpleframework.xml.Attribute;

/**
 *
 * @author BORYAN
 */
public abstract class PersonalData {
    
    @CsvBindByName
    public int id;
    @CsvBindByName
    public String name;
    @CsvBindByName
    public String number;
    @CsvBindByName
    public int age;
    
    public abstract void setId(int id);
    public abstract int getId();
    
    public abstract void setName(String name);
    public abstract String getName();
    
    public abstract void setNumber(String number);
    public abstract String getNumber();
    
    public abstract void setAge(int age);
    public abstract int getAge();
    
}
