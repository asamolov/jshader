package com.github.asamolov.jshader.lib;

import java.util.Optional;

/**
 * Hello world!
 */
public class Greeter {
    public String greet(String name) {
        return String.format("Howdy, %s!", Optional.ofNullable(name).orElse("%username%"));
    }
}
