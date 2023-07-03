package com.github.asamolov.jshader.lib;

import java.util.Optional;

/**
 * Wrapper
 */
public class Greeter {

    private final GreeterWrapped wrapped;

    public Greeter() {
        var libVariant = System.getProperty("lib.variant");
        if ("b".equalsIgnoreCase(libVariant)) {
            wrapped = new WrapperB();
        } else {
            wrapped = new WrapperA();
        }
    }

    public String greet(String name) {
        return wrapped.greet(name);
    }

    private interface GreeterWrapped {
        String greet(String name);
    }

    private static class WrapperA implements GreeterWrapped {
        private final a.com.github.asamolov.jshader.lib.Greeter obj;

        private WrapperA() {
            this.obj = new a.com.github.asamolov.jshader.lib.Greeter();
        }

        public String greet(String name) {
            return this.obj.greet(name);
        }
    }

    private static class WrapperB implements GreeterWrapped {
        private final b.com.github.asamolov.jshader.lib.Greeter obj;

        private WrapperB() {
            this.obj = new b.com.github.asamolov.jshader.lib.Greeter();
        }

        public String greet(String name) {
            return this.obj.greet(name);
        }
    }
}
