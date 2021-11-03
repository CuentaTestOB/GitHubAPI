package com.GitHub;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        ArrayList<String> repositories = new ArrayList<>();
        ArrayList<String> commits = new ArrayList<>();

        GitHub gitHub = new GitHub();

        try{
            gitHub.getRepositories("elodin19");
        } catch (Exception e){}

    }
}
