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
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import ru.sfedu.tutor.utils.ConfigurationUtil;
import org.apache.log4j.Logger;
import static ru.sfedu.tutor.EnumOfEntities.LESSONS;
import static ru.sfedu.tutor.EnumOfEntities.PUPIL;
import static ru.sfedu.tutor.EnumOfEntities.TEACHER;

/**
 *
 * @author BORYAN
 */
public class DataProviderXML implements IDataProvider{

    private static Logger log = Logger.getLogger(TutorClient.class);
    
    @Override
    public void initDataSource() {
        selectRecords(EnumOfEntities.CONTRACT);
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
        List<Teacher> savedTeachers;
        List<String> messages = new ArrayList<>();
        try {
            savedTeachers = readFromXML(ConfigurationUtil.getConfigurationEntry(
                    Constance.PATH_TO_XML_TEACHER), Teacher.class);
        } catch (IOException ex) {
            return new Result(EnumOfStatus.ERROR, null, Arrays.asList(ex.getMessage()));
        }
         beans.stream().forEach(bean -> {
            switch (getRecordById(bean.getId(), EnumOfEntities.TEACHER).getStatus()){
                case SUCCESS:
                    savedTeachers.add(bean);
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
       
        try {
            writeToXML(ConfigurationUtil.getConfigurationEntry(Constance.PATH_TO_XML_TEACHER), savedTeachers);
        } catch (IOException ex) {
           return new Result(EnumOfStatus.ERROR, null, Arrays.asList(ex.getMessage()));
        }
        
        return new Result(EnumOfStatus.SUCCESS, null, Arrays.asList("Records successfully added."));
    }

    
    
    
    
    
    
    
    private Result addPupil(List<Pupil> beans) {
        List<Pupil> savedPupils;
        List<String> messages = new ArrayList<>();
        try {
            savedPupils = readFromXML(ConfigurationUtil.getConfigurationEntry(
                    Constance.PATH_TO_XML_PUPIL), Pupil.class);
        } catch (IOException ex) {
            return new Result(EnumOfStatus.ERROR, null, Arrays.asList(ex.getMessage()));
        }
      
          beans.stream().forEach(bean -> {
            switch (getRecordById(bean.getId(), EnumOfEntities.PUPIL).getStatus()){
                case SUCCESS:
                    savedPupils.add(bean);
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
        try {
            writeToXML(ConfigurationUtil.getConfigurationEntry(Constance.PATH_TO_XML_PUPIL), savedPupils);
        } catch (IOException ex) {
            return new Result(EnumOfStatus.ERROR, null, Arrays.asList(ex.getMessage()));
        }
        return new Result(EnumOfStatus.SUCCESS, null, Arrays.asList("Records successfully added."));
    }

    
    
    
    
    
    private Result addContract(List<Contract> beans) {
         List<Contract> savedContract;
         List<String> messages = new ArrayList<>();
        try {
            savedContract = readFromXML(ConfigurationUtil.getConfigurationEntry(
                    Constance.PATH_TO_XML_CONTRACT), Contract.class);
        } catch (IOException ex) {
             return new Result(EnumOfStatus.ERROR, null, Arrays.asList(ex.getMessage()));
        }
        //for(Contract p: list) {
        //    if(getRecordById(p.getId_contract(), EnumOfEntities.CONTRACT) == null) {
        //        savedContract.add(p);
        //    } else {
        //        log.error("The entry was not added. Id must be unique.");
        //    }
       // }
        beans.stream().forEach(bean -> {
            
            switch(getRecordById(bean.getId_contract(), EnumOfEntities.CONTRACT).getStatus()) {
                case SUCCESS: 
                    break;
                case ERROR:
                            messages.add("Record " + bean + " was not added. Id must be unique.");
                            break;
            }
            switch(getRecordById(bean.getTeacher_id(), EnumOfEntities.TEACHER).getStatus()) {
                case SUCCESS:
                             messages.add("Record " + bean + " was not added. There is no teacher with this id.");
                    break;
                case ERROR:
                             break;
            }
            switch(getRecordById(bean.getPupil_id(), EnumOfEntities.PUPIL).getStatus()) {
                case SUCCESS:
                             messages.add("Record " + bean + " was not added. There is no pupil with this id.");
                    break;
                case ERROR:
                             break;
            }
            switch(getRecordById(bean.getLessons_id(), EnumOfEntities.LESSONS).getStatus()) {
                case SUCCESS:
                             messages.add("Record " + bean + " was not added. There is no lessons with this id.");
                    break;
                case ERROR:
                             break;
            }
        });
        if(!messages.isEmpty()) {
            messages.add("Records were not added!");
            return new Result(EnumOfStatus.ERROR, null, messages);
        }
        beans.stream().forEach(bean -> {savedContract.add(bean);});
        try {
            writeToXML(ConfigurationUtil.getConfigurationEntry(Constance.PATH_TO_XML_CONTRACT), savedContract);
        } catch (IOException ex) {
           return new Result(EnumOfStatus.ERROR, null, Arrays.asList(ex.getMessage()));
        }
        return new Result(EnumOfStatus.SUCCESS, null, Arrays.asList("Records successfully added."));
    }

    
    
    
    
    
    
    
    private Result addLessons(List<Lessons> beans) {
        List<Lessons> savedLessons;
        List<String> messages = new ArrayList<>();
        try {
            savedLessons = readFromXML(ConfigurationUtil.getConfigurationEntry(
                    Constance.PATH_TO_XML_LESSONS), Lessons.class);
        } catch (IOException ex) {
            return new Result(EnumOfStatus.ERROR, null, Arrays.asList(ex.getMessage()));
        }
        
            beans.stream().forEach(bean -> {
            switch (getRecordById(bean.getId_lesson(), EnumOfEntities.LESSONS).getStatus()){
                case SUCCESS:
                    savedLessons.add(bean);
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
         
        try {
            writeToXML(ConfigurationUtil.getConfigurationEntry(Constance.PATH_TO_XML_LESSONS), savedLessons);
        } catch (IOException ex) {
           return new Result(EnumOfStatus.ERROR, null, Arrays.asList(ex.getMessage()));
        }
        return new Result(EnumOfStatus.SUCCESS, null, Arrays.asList("Records successfully added."));

    }


    

    
    

    @Override
    public Result deleteRecord(int id, EnumOfEntities typeOfEntity) {
        List<Contract> savedContracts;
         switch (typeOfEntity) {
            case TEACHER:       
                List<Teacher> savedTeachers = null;
                //List<Contract> savedContracts = null;
                try {
                    savedTeachers = readFromXML(ConfigurationUtil.getConfigurationEntry(
                            Constance.PATH_TO_XML_TEACHER), Teacher.class);
                } catch (IOException ex) {
                    return new Result(EnumOfStatus.ERROR, null, Arrays.asList(ex.getMessage()));
                }
                try {
                    savedContracts = readFromXML(ConfigurationUtil.getConfigurationEntry(
                            Constance.PATH_TO_XML_CONTRACT), Contract.class);
                } catch (IOException ex) {
                    return new Result(EnumOfStatus.ERROR, null, Arrays.asList(ex.getMessage()));
                }
                savedTeachers.removeIf(t -> t.getId() == id);
                savedContracts.removeIf(c -> c.getTeacher_id() == id);
                //for(Teacher teacher: savedTeachers) {
                //    if(teacher.getId_teacher() == id) {
                //        savedTeachers.remove(teacher);
                //        break;
                //    }
                
                try {
                    writeToXML(ConfigurationUtil.getConfigurationEntry(
                            Constance.PATH_TO_XML_TEACHER), savedTeachers);
                } catch (IOException ex) {
                    return new Result(EnumOfStatus.ERROR, null, Arrays.asList(ex.getMessage()));
                }
                try {
                    writeToXML(ConfigurationUtil.getConfigurationEntry(
                            Constance.PATH_TO_XML_CONTRACT), savedContracts);
                } catch (IOException ex) {
                    return new Result(EnumOfStatus.ERROR, null, Arrays.asList(ex.getMessage()));
                }
                break;
                
            case CONTRACT:
                //List<Contract> savedContracts;
                try {
                    savedContracts = readFromXML(ConfigurationUtil.getConfigurationEntry(
                            Constance.PATH_TO_XML_CONTRACT), Contract.class);
                } catch (IOException ex) {
                    return new Result(EnumOfStatus.ERROR, null, Arrays.asList(ex.getMessage()));
                }
                savedContracts.removeIf(c -> c.getId_contract() == id);
                try {
                    writeToXML(ConfigurationUtil.getConfigurationEntry(
                            Constance.PATH_TO_XML_CONTRACT), savedContracts);
                } catch (IOException ex) {
                    return new Result(EnumOfStatus.ERROR, null, Arrays.asList(ex.getMessage()));
                }
                break;
            case PUPIL:
                List<Pupil> savedPupil = null;
                try {
                    savedPupil = readFromXML(ConfigurationUtil.getConfigurationEntry(
                            Constance.PATH_TO_XML_PUPIL), Pupil.class);
                } catch (IOException ex) {
                    return new Result(EnumOfStatus.ERROR, null, Arrays.asList(ex.getMessage()));
                }
                try {
                    savedContracts = readFromXML(ConfigurationUtil.getConfigurationEntry(
                            Constance.PATH_TO_XML_CONTRACT), Contract.class);
                } catch (IOException ex) {
                    return new Result(EnumOfStatus.ERROR, null, Arrays.asList(ex.getMessage()));
                }
                savedPupil.removeIf(p -> p.getId() == id);
                savedContracts.removeIf(c -> c.getPupil_id() == id);
                try {
                    //ListClass lc = new ListClass();
                    //lc.Equal(savedPupil);
                    writeToXML(ConfigurationUtil.getConfigurationEntry(
                            Constance.PATH_TO_XML_PUPIL), savedPupil);
                } catch (IOException ex) {
                    return new Result(EnumOfStatus.ERROR, null, Arrays.asList(ex.getMessage()));
                }
                try {
                    writeToXML(ConfigurationUtil.getConfigurationEntry(
                            Constance.PATH_TO_XML_CONTRACT), savedContracts);
                } catch (IOException ex) {
                    return new Result(EnumOfStatus.ERROR, null, Arrays.asList(ex.getMessage()));
                }
                break;
                
            case LESSONS:
                List<Lessons> savedLessons = null;
                try {
                    savedLessons = readFromXML(ConfigurationUtil.getConfigurationEntry(
                            Constance.PATH_TO_XML_LESSONS), Lessons.class);
                } catch (IOException ex) {
                    return new Result(EnumOfStatus.ERROR, null, Arrays.asList(ex.getMessage()));
                }
                try {
                    savedContracts = readFromXML(ConfigurationUtil.getConfigurationEntry(
                            Constance.PATH_TO_XML_CONTRACT), Contract.class);
                } catch (IOException ex) {
                    return new Result(EnumOfStatus.ERROR, null, Arrays.asList(ex.getMessage()));
                }
                savedLessons.removeIf(l -> l.getId_lesson() == id);
                savedContracts.removeIf(c -> c.getLessons_id() == id);
                try {
                    writeToXML(ConfigurationUtil.getConfigurationEntry(
                            Constance.PATH_TO_XML_LESSONS), savedLessons);
                } catch (IOException ex) {
                    return new Result(EnumOfStatus.ERROR, null, Arrays.asList(ex.getMessage()));
                }
                try {
                    writeToXML(ConfigurationUtil.getConfigurationEntry(
                            Constance.PATH_TO_XML_CONTRACT), savedContracts);
                } catch (IOException ex) {
                    return new Result(EnumOfStatus.ERROR, null, Arrays.asList(ex.getMessage()));
                }
                break;
        }
        return new Result(EnumOfStatus.SUCCESS, null, Arrays.asList("Record successfully deleted."));
    }

    
    
    
    @Override
    public Result getRecordById(int id, EnumOfEntities typeOfEntity) {                   // <T> T
           switch (typeOfEntity) {
            case TEACHER:
                List<Teacher> beansT = null;
                try {
                    beansT = readFromXML(ConfigurationUtil.getConfigurationEntry(
                            Constance.PATH_TO_XML_TEACHER), Teacher.class);
                } catch (IOException ex) {
                    log.error(ex);
                }
                
                Teacher t = beansT.stream().filter(bean -> bean.getId() == id).findFirst().orElse(null);
                if(t != null){
                    return new Result(EnumOfStatus.ERROR, Arrays.asList(t),Arrays.asList("The record was successfully found."));
                }
                 break;
            
            case PUPIL:
                List<Pupil> beansP = null;
                try {
                    beansP = readFromXML(ConfigurationUtil.getConfigurationEntry(
                            Constance.PATH_TO_XML_PUPIL), Pupil.class);
                } catch (IOException ex) {
                    log.error(ex);
                }
                Pupil p = beansP.stream().filter(bean -> bean.getId() == id).findFirst().orElse(null);
                if(p != null){
                    return new Result(EnumOfStatus.ERROR, Arrays.asList(p),Arrays.asList("The record was successfully found."));
                }
                
                //for(Pupil x: beansP) {
                //    if(x.getId_pupil() == id) {
                //        return (T) x;
                //    }
                //}
                break;
            case LESSONS:
                List<Lessons> beansL = null;
                try {
                    beansL = readFromXML(ConfigurationUtil.getConfigurationEntry(
                            Constance.PATH_TO_XML_LESSONS), Lessons.class);
                } catch (IOException ex) {
                    log.error(ex);
                }
                Lessons l = beansL.stream().filter(bean -> bean.getId_lesson() == id).findFirst().orElse(null);
                if(l != null){
                    return new Result(EnumOfStatus.ERROR, Arrays.asList(l),Arrays.asList("The record was successfully found."));
                }
                break;
            
            case CONTRACT:
                List<Contract> beansC = null;
                try {
                    
                    beansC = readFromXML(ConfigurationUtil.getConfigurationEntry(
                            Constance.PATH_TO_XML_CONTRACT), Contract.class);
                   
                } catch (IOException ex) {
                    log.error(ex);
                }
                Contract c = beansC.stream().filter(bean -> bean.getId_contract() == id).findFirst().orElse(null);
                if(c != null){
                    return new Result(EnumOfStatus.ERROR, Arrays.asList(c),Arrays.asList("The record was successfully found."));
                }
                
                //for(Contract x: beansC) {
                //    if(x.getId_contract() == id) {
                //        return (T) x;
                //    }
                //}
                break;               
    }
            return new Result(EnumOfStatus.SUCCESS, null, Arrays.asList("The record was not found."));
    }

    
    
    
        private <T> List<T> readFromXML(String path, Class classInfo) {
        List<T> beans = null;
        File source = new File(path);
        Serializer serializer = new Persister();
            ListClass example;
        try {
            example = serializer.read(ListClass.class, source);
            beans = example.getList();
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(DataProviderXML.class.getName()).log(Level.SEVERE, null, ex);
        }

        return (List<T>) beans;
    } 
        
        
        
        private <T> void writeToXML(String path, List<T> beans) throws IOException   {
                   ListClass lc = new ListClass();
                   //lc.addList(beans);
                   lc.Equal(beans);
                   Serializer serializer = new Persister();
                   FileWriter writer = new FileWriter(path);            
        try {
            serializer.write(lc, writer);
        } catch (Exception ex) {
            
            java.util.logging.Logger.getLogger(DataProviderXML.class.getName()).log(Level.SEVERE, null, ex);
        }   
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

    private Result updateTeacher(List<Teacher> beans) {
        List<Teacher> savedTeachers;
        List<String> messages = new ArrayList<>();
        try {
            savedTeachers = readFromXML(ConfigurationUtil.getConfigurationEntry(
                    Constance.PATH_TO_XML_TEACHER), Teacher.class);
        } catch (IOException ex) {
            return new Result(EnumOfStatus.ERROR, null, Arrays.asList(ex.getMessage()));
        }
        
        
        savedTeachers.stream().forEach(savedT -> {
            beans.stream().forEach(bean -> {
                if(savedT.getId()== bean.getId()) {
                    savedTeachers.set(savedTeachers.indexOf(savedT), bean);
                    messages.add("Record with id = " + savedT.getId() + " updated.");
                }
                
            });
        });
        
        if(messages.isEmpty()) {
            messages.add("There is no such record. Record not updeted.");
            return new Result(EnumOfStatus.ERROR, null, messages);
        }
        
        try {
            writeToXML(ConfigurationUtil.getConfigurationEntry(
                    Constance.PATH_TO_XML_TEACHER), savedTeachers);
        } catch (Exception ex) {
            return new Result(EnumOfStatus.ERROR, null, Arrays.asList(ex.getMessage()));
        }
        messages.add(" Records successfully updated.");
        return new Result(EnumOfStatus.SUCCESS, null, messages);
    }

    
    
    
    private Result updatePupil(List<Pupil> beans) {
        List<Pupil> savedPupils;
        List<String> messages = new ArrayList<>();
        try {
            savedPupils = readFromXML(ConfigurationUtil.getConfigurationEntry(
                    Constance.PATH_TO_XML_PUPIL), Pupil.class);
        } catch (IOException ex) {
            return new Result(EnumOfStatus.ERROR, null, Arrays.asList(ex.getMessage()));
        }
        
        
        savedPupils.stream().forEach(savedP -> {
            beans.stream().forEach(bean -> {
                if(savedP.getId()== bean.getId()) {
                    savedPupils.set(savedPupils.indexOf(savedP), bean);
                    messages.add("Record with id = " + savedP.getId() + " updated.");
                }
                
            });
        });
        
        if(messages.isEmpty()) {
            messages.add("There is no such record. Record not updeted.");
            return new Result(EnumOfStatus.ERROR, null, messages);
        }
        
        try {
            writeToXML(ConfigurationUtil.getConfigurationEntry(
                    Constance.PATH_TO_XML_PUPIL), savedPupils);
        } catch (Exception ex) {
            return new Result(EnumOfStatus.ERROR, null, Arrays.asList(ex.getMessage()));
        }
        messages.add(" Records successfully updated.");
        return new Result(EnumOfStatus.SUCCESS, null, messages);
    }

    
    
    
    
    private Result updateLessons(List<Lessons> beans) {
        List<Lessons> savedLessons;
        List<String> messages = new ArrayList<>();
        try {
            savedLessons = readFromXML(ConfigurationUtil.getConfigurationEntry(
                    Constance.PATH_TO_XML_LESSONS), Lessons.class);
        } catch (IOException ex) {
            return new Result(EnumOfStatus.ERROR, null, Arrays.asList(ex.getMessage()));
        }
        
        
        savedLessons.stream().forEach(savedL -> {
            beans.stream().forEach(bean -> {
                if(savedL.getId_lesson()== bean.getId_lesson()) {
                    savedLessons.set(savedLessons.indexOf(savedL), bean);
                    messages.add("Record with id = " + savedL.getId_lesson() + " updated.");
                }
                
            });
        });
        
        if(messages.isEmpty()) {
            messages.add("There is no such record. Record not updeted.");
            return new Result(EnumOfStatus.ERROR, null, messages);
        }
        
        try {
            writeToXML(ConfigurationUtil.getConfigurationEntry(
                    Constance.PATH_TO_XML_LESSONS), savedLessons);
        } catch (Exception ex) {
            return new Result(EnumOfStatus.ERROR, null, Arrays.asList(ex.getMessage()));
        }
        messages.add(" Records successfully updated.");
        return new Result(EnumOfStatus.SUCCESS, null, messages);
    }
    
    
    
    
    
    
    private Result updateContract(List<Contract> beans) {
        List<Contract> savedContract;
        List<String> messages = new ArrayList<>();
        List<String> checkLink = new ArrayList<>();
        try {
            savedContract = readFromXML(ConfigurationUtil.getConfigurationEntry(
                    Constance.PATH_TO_XML_CONTRACT), Contract.class);
        } catch (IOException ex) {
            return new Result(EnumOfStatus.ERROR, null, Arrays.asList(ex.getMessage()));
        }
        beans.stream().forEach(bean -> {
            
            switch(getRecordById(bean.getTeacher_id(), EnumOfEntities.TEACHER).getStatus()) {
                case SUCCESS:
                    checkLink.add("Record " + bean + " was not updated.There is no pupil with this id.");
                    
                case ERROR:
                    //ex.getMessage()
                    //"Record " + bean + " was not updated. There is no teacher with this id."
                    break;
            }
            
            switch(getRecordById(bean.getPupil_id(), EnumOfEntities.PUPIL).getStatus()) {
                case SUCCESS:
                    checkLink.add("Record " + bean + " was not updated.There is no pupil with this id.");
                    break;
                case ERROR:
                    
                    break;
            }
            
            switch(getRecordById(bean.getLessons_id(), EnumOfEntities.LESSONS).getStatus()) {
                case SUCCESS:
                    checkLink.add("Record " + bean + " was not updated. There is no lessons with this id.");
                    break;
                case ERROR:
                    
                    break;
            }
        });
        
        if(!checkLink.isEmpty()) {
            //messages.add("There is no such record. Record not updeted.");
            return new Result(EnumOfStatus.ERROR, null, checkLink);
        }
        
        savedContract.stream().forEach(savedC -> {
            beans.stream().forEach(bean -> {
                if(savedC.getId_contract() == bean.getId_contract()) {
                    savedContract.set(savedContract.indexOf(savedC), bean);
                    messages.add("Record with id = " + savedC.getId_contract() + " updated.");
                }
                
            });
        });
        
        if(messages.isEmpty()) {
            messages.add("There is no such record. Record not updeted.");
            return new Result(EnumOfStatus.ERROR, null, messages);
        }
        
        try {
            writeToXML(ConfigurationUtil.getConfigurationEntry(
                    Constance.PATH_TO_XML_CONTRACT), savedContract);
        } catch (Exception ex) {
            return new Result(EnumOfStatus.ERROR, null, Arrays.asList(ex.getMessage()));
        }
        messages.add(" Records successfully updated.");
        return new Result(EnumOfStatus.SUCCESS, null, messages);
    }

    @Override
    public Result selectRecords(EnumOfEntities typeOfEntity) {
        switch(typeOfEntity){
            case TEACHER:
                List<Teacher> savedTeachers;
                try {
                    savedTeachers = readFromXML(ConfigurationUtil.getConfigurationEntry(
                            Constance.PATH_TO_XML_TEACHER), Teacher.class);
                } catch (IOException ex) {
                    return new Result(EnumOfStatus.ERROR, null, Arrays.asList(ex.getMessage()));
                }
                return new Result(EnumOfStatus.SUCCESS, savedTeachers, null);
            case PUPIL:
                List<Pupil> savedPupils;
                try {
                    savedPupils = readFromXML(ConfigurationUtil.getConfigurationEntry(
                            Constance.PATH_TO_XML_PUPIL), Pupil.class);
                } catch (IOException ex) {
                    return new Result(EnumOfStatus.ERROR, null, Arrays.asList(ex.getMessage()));
                }
                return new Result(EnumOfStatus.SUCCESS, savedPupils, null);
            case LESSONS:
                List<Lessons> savedLessons;
                try {
                    savedLessons = readFromXML(ConfigurationUtil.getConfigurationEntry(
                            Constance.PATH_TO_XML_LESSONS), Lessons.class);
                } catch (IOException ex) {
                    return new Result(EnumOfStatus.ERROR, null, Arrays.asList(ex.getMessage()));
                }
                return new Result(EnumOfStatus.SUCCESS, savedLessons, null);
            case CONTRACT:
                List<Contract> savedContract;
                try {
                    savedContract = readFromXML(ConfigurationUtil.getConfigurationEntry(
                            Constance.PATH_TO_XML_CONTRACT), Contract.class);
                } catch (IOException ex) {
                    return new Result(EnumOfStatus.ERROR, null, Arrays.asList(ex.getMessage()));
                }
                savedContract.removeIf(p -> getRecordById(p.getTeacher_id(),TEACHER).getStatus() == EnumOfStatus.SUCCESS);
                savedContract.removeIf(p -> getRecordById(p.getPupil_id(),PUPIL).getStatus() == EnumOfStatus.SUCCESS);
                savedContract.removeIf(p -> getRecordById(p.getLessons_id(),LESSONS).getStatus() == EnumOfStatus.SUCCESS);
                
                try {
                writeToXML(ConfigurationUtil.getConfigurationEntry(Constance.PATH_TO_XML_CONTRACT), savedContract);
                } catch (IOException ex) {
                     return new Result(EnumOfStatus.ERROR, null, Arrays.asList(ex.getMessage()));
                }
                
                
                return new Result(EnumOfStatus.SUCCESS, savedContract, null);
        }
        return new Result(EnumOfStatus.ERROR, null, Arrays.asList("There is no records"));
        
    }

    
    
}


        
