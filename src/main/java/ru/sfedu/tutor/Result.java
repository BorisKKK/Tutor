/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.tutor;

import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author BORYAN
 */
public class Result <T> {
    private EnumOfStatus status;
    private List<T> data;
    private List<String> message;
    private static Logger log = Logger.getLogger(TutorClient.class);
    
    public Result() {
        
    }
    
    public Result(EnumOfStatus status, List<T> data, List<String> messages) {
        this.status = status;
        this.data = data;
        this.message = messages;
        log.info(message);
    }

    
    public EnumOfStatus getStatus() {
        return status;
    }

    public void setStatus(EnumOfStatus status) {
        this.status = status;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public List<String> getMessages() {
        return message;
    }

    public void setMessages(List<String> messages) {
        this.message = messages;
    }
}
