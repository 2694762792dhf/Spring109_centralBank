package com.model;

public class Account {
    private String name;

    private String code;

    private String home;

    private Double money;

    private Double BankMoney;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Double getBankMoney() {
        return BankMoney;
    }

    public void setBankMoney(Double bankMoney) {
        BankMoney = bankMoney;
    }

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", home='" + home + '\'' +
                ", money=" + money +
                ", BankMoney=" + BankMoney +
                '}';
    }
}
