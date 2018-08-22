/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.tutor;

import classes.Teacher;
import classes.TestTable;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author BORYAN
 */
public class TutorClient {
    
    private static Logger log = Logger.getLogger(TutorClient.class);
    private static IDataProvider dataProvider;
    
    TutorClient(){
        log.debug("<Your constructor name>[0]: starting application.........");
    }
    
    public void logBasicSystemInfo() {
    log.info("Launching the application...");
    log.info(
    "Operating System: " + System.getProperty("os.name") + " "
    + System.getProperty("os.version")
    );
    log.info("JRE: " + System.getProperty("java.version"));
    log.info("Java Launched From: " + System.getProperty("java.home"));
    log.info("Class Path: " + System.getProperty("java.class.path"));
    log.info("Library Path: " + System.getProperty("java.library.path"));
    log.info("User Home Directory: " + System.getProperty("user.home"));
    log.info("User Working Directory: " + System.getProperty("user.dir"));
    log.info("Test INFO logging.");
    }
    public static void main(String[] args) throws SQLException {
        
        
        
        
        dataProvider = null;
        Scanner scan = new Scanner(System.in);
        while (true){
            String temp = scan.nextLine();
            if (temp.equals("q") || temp.equals("quit")){
                break;
            }
            String[] commands = temp.split("[ ]+");
            if (commands.length < 2 && !commands[0].equals("help")) {
                System.out.println("Command not exist");
                continue;
            }
            switch (commands[0]) {
                case "use":
                    dataProvider = CLI.choseDataSource(commands);
                    break;
                case "select":
                    if (dataProvider == null) {
                        System.out.println("You should select data provider");
                        continue;
                    }
                        CLI.select(dataProvider,commands[1]);
                    break;
                case "create":
                    if (dataProvider == null) {
                        System.out.println("You should select data provider");
                        continue;
                    }
                    switch (commands[1]) {
                        case "teacher":
                            CLI.addTeacher(dataProvider);
                            break;
                        case "pupil":
                            CLI.addPupil(dataProvider);
                            break;
                        case "lesson":
                            CLI.addLessons(dataProvider);
                            break;
                        case "contract":
                            CLI.addContract(dataProvider);
                            break;
                        default:
                            System.out.println("Entry invalid");
                    }
                    break;
                    
                case "update":
                    if (dataProvider == null) {
                        System.out.println("You should select data provider");
                        continue;
                    }
                    switch (commands[1]) {
                        case "teacher":
                            CLI.updateTeacher(dataProvider);
                            break;
                        case "pupil":
                            CLI.updatePupil(dataProvider);
                            break;
                        case "lessons":
                            CLI.updateLessons(dataProvider);
                            break;
                        case "contract":
                            CLI.updateContract(dataProvider);
                            break;
                        default:
                            System.out.println("Entry invalid");
                    }
                    break;
                case "delete":
                    if (dataProvider == null) {
                        System.out.println("You should select data provider");
                        continue;
                    }
                    switch (commands[1]) {
                        case "teacher":
                            CLI.deleteRec(dataProvider, EnumOfEntities.TEACHER);
                            break;
                        case "pupil":
                            CLI.deleteRec(dataProvider, EnumOfEntities.PUPIL);
                            break;
                        case "lessons":
                            CLI.deleteRec(dataProvider, EnumOfEntities.LESSONS);
                            break;
                        case "contract":
                            CLI.deleteRec(dataProvider, EnumOfEntities.CONTRACT);
                            break;
                        default:
                            System.out.println("Entry invalid");
                    }
                    break;
                    case "help":
                        CLI.help();
                    break;
            }
            
    }
}
}