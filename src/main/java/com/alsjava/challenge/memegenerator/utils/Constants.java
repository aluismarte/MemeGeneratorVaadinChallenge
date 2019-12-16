package com.alsjava.challenge.memegenerator.utils;

import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Value;

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
}
