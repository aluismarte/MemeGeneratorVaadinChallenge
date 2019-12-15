package com.alsjava.challenge.memegenerator.ui;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

/**
 * Created by aluis on 12/14/19.
 */
@Route(value = "creator", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class MemeGenerator extends VerticalLayout {

    public MemeGenerator() {
        add(new Text("Meme creator"));
    }
}
