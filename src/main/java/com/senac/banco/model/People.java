package com.senac.banco.model;

public class People {
    private static int counter = 1;
    private String name;
    private String cpf;
    private String email;

    public People(String name, String cPF, String email){
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        counter += 1;
    }
    public String getName(){
        return name;
    }
    public String getCPF(){
        return cpf;

    }
    public void setCPF(String cpf){
        cpf = cpf;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public String toString(){
        return "\nName: " + this.getName() +
                "\nCPF: " + this.getCPF() +
                "\nEmail: " + this.getEmail();
    }

}
