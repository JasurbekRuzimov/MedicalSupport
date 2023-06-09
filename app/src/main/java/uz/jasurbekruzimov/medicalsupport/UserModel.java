package uz.jasurbekruzimov.medicalsupport;

import androidx.annotation.NonNull;

public class UserModel {
    String name, phone, password;

    public UserModel(String name, String phone, String password) {
        this.name = name;
        this.phone = phone;
        this.password = password;

    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NonNull
    @Override
    public String toString() {
        return "UserModel{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                '}';
    }




}
