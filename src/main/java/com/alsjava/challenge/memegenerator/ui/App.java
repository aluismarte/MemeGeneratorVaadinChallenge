package com.alsjava.challenge.memegenerator.ui;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

/**
 * Created by aluis on 12/10/19.
 */
@Push
@Route("")
@PWA(name = "Meme Generator On Vaadin", shortName = "Meme Generator")
public class App extends AppLayout {

    public App() {
        // Prepare base layout
    }
}
