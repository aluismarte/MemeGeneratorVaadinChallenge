package com.alsjava.challenge.memegenerator.utils;

import java.net.URL;

/**
 * Created by aluis on 12/14/19.
 */
public class Constants {

    private static Constants instance = null;

    public static final String APP_NAME = "Meme Generator Vaadin";

    private Constants() {
    }

    public static Constants get() {
        Constants result = instance;
        if (result == null) {
            synchronized (Constants.class) {
                if (instance == null) {
                    instance = result = new Constants();
                }
            }
        }
        return result;
    }

    public URL isValidURL(String urlString) {
        try {
            return new URL(urlString);
        } catch (Exception exception) {
            return null;
        }
    }
}
