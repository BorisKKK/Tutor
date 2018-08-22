/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.tutor;

import classes.TestTable;
import classes.University;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ru.sfedu.tutor.model.JoinTableStrategy.*;
import ru.sfedu.tutor.model.MappedSuperClassStrategy.*;
import ru.sfedu.tutor.model.SingleTable.Account;
import ru.sfedu.tutor.model.SingleTable.CreditAccount;
import ru.sfedu.tutor.model.SingleTable.DebitAccount;
import ru.sfedu.tutor.model.SingleTable.SingleTableStrategy;
import ru.sfedu.tutor.model.TablePerClass.*;

/**
 *
 * @author BORYAN
 */
public class HibernateDataProviderTest {
    private static Logger log = Logger.getLogger(TutorClient.class);
    public HibernateDataProviderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
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

    @Test
    public void testSomeMethod() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        
//        Transaction tx = session.beginTransaction();
//        SQLQuery query = session.createSQLQuery("select id_teacher, teacherName, teacherNumber, teacherAge from teacher");
//        List<Object[]> rows = query.list();
//        for(Object[] row : rows){
//            Teacher emp = new Teacher();
//            emp.setId(Integer.parseInt(row[0].toString()));
//            emp.setName(row[1].toString());
//            emp.setNumber(row[2].toString());
//            emp.setAge(Integer.parseInt(row[3].toString()));
//            System.out.println(emp);
//        }
//        




//        NativeQuery q = session.getNamedNativeQuery("GET_TABLES");
//        q.getResultList().forEach(table -> log.info(table));
//        NativeQuery w = session.getNamedNativeQuery("GET_DB");
//        w.getResultList().forEach(table -> log.info(table));
//        NativeQuery e = session.getNamedNativeQuery("GET_TEST");
//        e.getResultList().forEach(table -> log.info(table));
//        NativeQuery r = session.getNamedNativeQuery("GET_COL");
//        r.getResultList().forEach(table -> log.info(table));
//        session.beginTransaction();
//        University u = new University("SFEDU","Польша",1969);
//        University us = new University("DGTU","Ростов на Дону",1969);
//        TestTable test = new TestTable();
//        test.setId(12);
//        test.setName("BBB");
//        test.setAge(23);
//        test.setUniver(u);
//        test.setSecond_univer(us);
//        session.save(test);
//        TestTable t = session.get(TestTable.class, 12);
//        System.out.println("\n\n\n" + t.toString() + "\n\n\n");
        
        ///session.getTransaction().commit();
        
        ///NativeQuery y = session.getNamedNativeQuery("GET_ALL_RECORDS");
        //List<TestTable> list = (List<TestTable>)y.list();
        //log.info(list.get(0));
        //TestTable testRecord = (TestTable) y.getResultList().get(0);
        //log.info(testRecord.toString());
        //List<TestTable> list;
        //y.getResultList().forEach(record -> list.add((TestTable)record));
        ///log.info(y.getResultList());
        
                //NativeQuery y = session.getNamedNativeQuery("GET_ALL_RECORDS");
                //TestTable testRecord = (TestTable) y.getResultList().get(0);
                //log.info(testRecord.toString());
                // y.getResultList().forEach(record -> log.info(record));
;
       
       // y.getResultList().forEach(record -> record);
       /// session.close();

//        SingleTableStrategy strategy1 = new SingleTableStrategy();
//        Account a  = new Account();
//        a.setId(1);
//        a.setOwner("Owner1");
//        a.setInterestRate(3);
//        a.setBalance(4);
//        CreditAccount c = new CreditAccount();
//        c.setId(2);
//        c.setCreditLimit(5);
//        DebitAccount d = new DebitAccount();
//        d.setId(7);
//        d.setOverdraftFee(7);
//        strategy1.addRecord(a);
//        strategy1.addRecord(d);
//        strategy1.addRecord(c);
//        strategy1.getRecord(7);
//        strategy1.delete(a);
//        strategy1.getRecord(7);
        
        


          
//          TablePerClassStrategy strategy2 = new TablePerClassStrategy();
//          Strat2Account a2 = new Strat2Account();
//          a2.setId(1);
//          a2.setOwner("Owner2");
//          a2.setInterestRate(3);
//          a2.setBalance(4);
//          Strat2CreditAccount c2 = new Strat2CreditAccount();
//          c2.setId(2);
//          c2.setCreditLimit(5);
//          Strat2DebitAccount d2 = new Strat2DebitAccount();
//          d2.setId(12);
//          d2.setOverdraftFee(8);
//          strategy2.addRecord(a2);
//          strategy2.addRecord(c2);
//          strategy2.addRecord(d2);
//          strategy2.getRecord(12);
          
          MappedSuperClassStrategy strategy3 = new MappedSuperClassStrategy();
          Strat3Account a3 = new Strat3Account();
          a3.setId(1);
          a3.setOwner("Owner3");
          a3.setInterestRate(3);
          a3.setBalance(4);
          Strat3CreditAccount c3 = new Strat3CreditAccount();
          c3.setId(2);
          c3.setCreditLimit(5);
          Strat3DebitAccount d3 = new Strat3DebitAccount();
          d3.setId(12);
          d3.setOverdraftFee(8);
          //strategy3.addRecord(a3);
          strategy3.addRecord(c3);
          strategy3.addRecord(d3);
         // strategy3.getRecord(12);
          
//          JointTableStrategy strategy4 = new JointTableStrategy();
//          Strat4Account a4 = new Strat4Account();
//          a4.setId(1);
//          a4.setOwner("Owner4");
//          a4.setInterestRate(3);
//          a4.setBalance(4);
//          Strat4CreditAccount c4 = new Strat4CreditAccount();
//          c4.setId(2);
//          c4.setCreditLimit(5);
//          Strat4DebitAccount d4 = new Strat4DebitAccount();
//          d4.setId(12);
//          d4.setOverdraftFee(8);
//          strategy4.addRecord(a4);
//          strategy4.addRecord(c4);
//          strategy4.addRecord(d4);
//          strategy4.getRecord(12);
          
    }
}
