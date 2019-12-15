package com.alsjava.challenge.memegenerator.ui;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;

/**
 * Created by aluis on 12/14/19.
 */
public class Menu extends VerticalLayout {

    public Menu() {
        RouterLink MemeCreator = new RouterLink("MemeCreator", MemeCreator.class);
        RouterLink gallery = new RouterLink("Gallery", Gallery.class);
        add(MemeCreator, gallery);
    }
}
