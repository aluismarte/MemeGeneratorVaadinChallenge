package com.alsjava.challenge.memegenerator.ui;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.server.StreamResource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UncheckedIOException;

/**
 * Created by aluis on 12/15/19.
 */
@Tag("meme-generator")
@JsModule("./meme-generator.js")
public class MemeGenerator extends Component {

    public MemeGenerator() {
    }

    public void setTopText(String topText) {
        getElement().setAttribute("topText", topText);
    }

    public void setBottomText(String bottomText) {
        getElement().setAttribute("bottomText", bottomText);
    }

    public void setSrc(File file) {
        if (file == null) {
            getElement().removeAttribute("src");
            return;
        }
        getElement().setAttribute("src", new StreamResource(file.getName(), () -> {
            try {
                return new FileInputStream(file);
            } catch (FileNotFoundException ex) {
                throw new UncheckedIOException(ex);
            }
        }));
    }
}
