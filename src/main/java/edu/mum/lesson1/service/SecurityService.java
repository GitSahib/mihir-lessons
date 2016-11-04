package edu.mum.lesson1.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autologin(String username, String password);

	boolean authenticat(String username, String password);
}
