/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import javax.persistence.*;






/**
 *
 * @author BORYAN
 */
@Entity
@Table(name = "test")
public class TestTable {
    
        
        //@GeneratedValue(strategy = GenerationType.IDENTITY)  //SEQUENCE
        @Id
        @Column(name = "test_Id")
        private Integer id;
        @Column(name = "testName")
        private String name;
        @Column(name = "testAge")
        private Integer age;
        @Embedded 
        @AttributeOverrides({
            @AttributeOverride(name="name",
                    column =  @Column(name="UNIVER_NAME") ), 
            @AttributeOverride(name ="country",
                     column = @Column(name="UNIVER_COUNTRY") ), 
            @AttributeOverride(name = "year", 
                    column = @Column(name="UNIVER_YEAR") )
        }) 
        private University univer;  
        @Embedded 
        @AttributeOverrides({
            @AttributeOverride(name="name",
                    column =  @Column(name="SECOND_UNIVER_NAME") ), 
            @AttributeOverride(name ="country",
                     column = @Column(name="SECOND_UNIVER_COUNTRY") ), 
            @AttributeOverride(name = "year", 
                    column = @Column(name="SECOND_UNIVER_YEAR") )
        })
        private University second_univer;
        
        public TestTable(){
            
        }
        
        
        
        public Integer getId(){
            return id;
        }
        
        public void setId(Integer id){
            this.id = id;
        }
        
        public void setName(String name){
            this.name = name;
        }
        
        public String getName(){
            return name;
        }
        
        public void setAge(Integer age){
            this.age = age;
        }
        
        public Integer getAge(){
            return age;
        }
        
        public void setUniver(University u){
            this.univer = u;
        }
        
        public University getUniver(){
            return univer;
        }
        
        public void setSecond_univer(University u){
            this.second_univer = u;
        }
        
        public University getSecond_univer(){
            return second_univer;
        }
        
        public String toString(){
            return "id - " + id 
                + " teacher_name - " + name 
                + " univer - " + univer.getName()
                + " univer_country - " + univer.getCountry()
                + " second_univer - " + second_univer.getName()
                + " second_univer_country - " + second_univer.getCountry()
                + " teacher_age - " + age;
        }
        
        
        
}
