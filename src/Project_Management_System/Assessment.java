/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project_Management_System;

/**
 *
 * @author User
 */
// no use for now
public class Assessment {
    private String id;
    private String name;
    private String lecturer;

    public Assessment(String id, String name, String lecturer) {
        this.id = id;
        this.name = name;
        this.lecturer = lecturer;
      
    }
        public String getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getlecturer() {
        return lecturer;
    }

}
