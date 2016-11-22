package com.SemNomeAindaCartolaFC.DB;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class DAO<T extends DataIdentifiable> {


    private String fileName;
    private List<T> content = new ArrayList<T>();

    public DAO(String fileName) {
        this.fileName = fileName + ".db";
    }

    public void setAll(T[] fullContent) throws Exception {

        content.clear();

        for(int i = 0; i < fullContent.length; i++) {
            content.add(fullContent[i]);
        }

        this.setCurrent(content);
    }

    public void insert(T obj) throws Exception {

        content = this.getCurrent();
        content.add(obj);
        this.setCurrent(content);
    }

    public void updateById(Integer id, T obj) throws Exception {
        T found = this.findById(id);
        if (found != null) {
            content = this.getCurrent();
            Integer foundIndex = content.indexOf(found);
            Field[] fields = found.getClass().getFields();

            for (int i = 0; i < fields.length; i++) {
                Field f = fields[i];
                Object newValue = f.get(obj);
                f.set(found, newValue);

                System.out.println(f.getName() + " " + newValue.toString() + " " + f.get(found).toString());
            }

            content.set(foundIndex, found);

            this.setCurrent(content);
        }
    }

    public T findById(Integer id) {
        try{

            content = this.getCurrent();

            for(int i = 0; i < content.size(); i++) {
                T item = content.get(i);
                if (item.getId() == id) {
                    return item;
                }
            }
        }
        catch(Exception e) {
            System.out.println(e.getCause());
        }

        return null;
    }

    public T findByName(String name) {
        try{

            content = this.getCurrent();

            for(int i = 0; i < content.size(); i++) {
                T item = content.get(i);
                if (item.getName().matches(".*" + name + ".*")) {
                    return item;
                }
            }
        }
        catch(Exception e) {
            System.out.println(e.getCause());
        }

        return null;
    }

    public List<T> getMax(int max) throws Exception{
        content = getCurrent();

        List<T> temp = new ArrayList<T>();

        for(int i = 0; i < max && i < content.size(); i++) {
            temp.add(content.get(i));
        }

        return temp;
    }

    public List<T> getCurrent() throws IOException, ClassNotFoundException {

        if (!Files.exists(Paths.get(this.fileName))){
            this.setCurrent(this.content);
        }

        byte[] allBytes = Files.readAllBytes(Paths.get(this.fileName));
        return (ArrayList<T>) Serializer.deserialize(allBytes);
    }

    private void setCurrent(List<T> current) throws IOException {
        byte[] allBytes = Serializer.serialize(current);
        Files.write(Paths.get(this.fileName), allBytes);
    }
}