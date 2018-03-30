package com.example.user.myapplication;

/**
 * Created by user on 03/03/2018.
 */

public class Exercise {

    private int id;
    private String name;
    private String instructions;
    private String image;


    public Exercise() {
    }

    public Exercise(int id, String name, String instructions, String image) {
        this.id = id;
        this.name = name;
        this.instructions = instructions;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", instructions='" + instructions + '\'' +
                ", image='" + image + '\'' +
                '}';
    }


}
