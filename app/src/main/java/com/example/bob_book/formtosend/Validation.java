package com.example.bob_book.formtosend;

import android.media.MediaCodec;
import android.net.Uri;
import android.widget.Toast;

import java.util.regex.Pattern;

/**
 * Created by bob-book on 1/9/2018.
 */

public class Validation {

    Uri photo;
    String email;
    String phone;
    String changePhone;
    String password;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String passwordPatternLetters = "[a-zA-Z]+";
    String passwordPatternNumbers = "[0-9]+";
    int status = 0;

    public void photoValid(Uri photo) {
        if (Uri.EMPTY.equals(photo)) {
            status=1;
        }
    }

    public void emailValid(String email) {
        email.trim();
        if (email.matches(emailPattern) && email.length() > 0) {
            return;
        } else {
            status = 2;
        }
    }

    public void phoneValid(String phone) {
        if (phone.length() > 9) {
            changePhoneNumber(phone);
            return;
        } else
            status = 3;
    }

    public void passwordValid(String password) {
        if (password.length() < 6) {
            status = 4;
        }
        if (password.matches(passwordPatternLetters)) {
            status = 4;
        }
        if (password.matches(passwordPatternNumbers)) {
            status = 4;
        }
    }

    public void changePhoneNumber(String p){
        changePhone=p;
        if(p.startsWith("7")&& p.length()>10){
            p=p.replaceFirst("7","+7");
            changePhone = p.substring(0, 2) + "(" + p.substring(2, 5) + ")" + p.substring(5, 8) + " " + p.substring(8, 10) + " " + p.substring(10);
        }
        if(p.startsWith("8")&& p.length()>10){
            p=p.replaceFirst("8","+7");
            changePhone = p.substring(0, 2) + "(" + p.substring(2, 5) + ")" + p.substring(5, 8) + " " + p.substring(8, 10) + " " + p.substring(10);
        }
        if(p.length()==10){
            p="+7"+p;
            changePhone = p.substring(0, 2) + "(" + p.substring(2, 5) + ")" + p.substring(5, 8) + " " + p.substring(8, 10) + " " + p.substring(10);
        }
    }

    public Validation(Uri photo, String email, String phone, String password) {
        status = 0;
        photoValid(photo);
        emailValid(email);
        phoneValid(phone);
        passwordValid(password);

        this.photo = photo;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public Uri getPhoto() {
        return photo;
    }

    public void setPhoto(Uri photo) {
        this.photo = photo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getChangePhone() {
        return changePhone;
    }

    public void setChangePhone(String changePhone) {
        this.changePhone = changePhone;
    }

}
