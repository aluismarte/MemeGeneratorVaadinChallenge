package com.alsjava.challenge.memegenerator.ui;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

/**
 * Created by aluis on 12/14/19.
 */
@Route(value = "gallery", layout = MainLayout.class)
public class Gallery extends Div {

    // List memes available

    public Gallery() {
        add(new Text("Gallery"));
    }
}
