/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.tutor;
import java.util.List;
/**
 *
 * @author BORYAN
 */
public interface IDataProvider {
    public void initDataSource();
    public <T> Result saveRecord (List <T> beans, EnumOfEntities typeOfEntity);
    public Result deleteRecord (int id, EnumOfEntities typeOfEntity);
    public <T> T getRecordById (int id, EnumOfEntities typeOfEntity);
    public <T> Result updateRecord (List<T> beans, EnumOfEntities typeOfEntity);
    public Result selectRecords(EnumOfEntities typeOfEntity);
}
