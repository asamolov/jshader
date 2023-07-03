package com.github.asamolov.jshader;

import com.github.asamolov.jshader.lib.Greeter;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        var greeter = new Greeter();
        var user = System.getProperty("user.name");
        System.out.println(greeter.greet(user));
    }
}
