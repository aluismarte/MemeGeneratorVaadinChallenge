package com.alsjava.challenge.memegenerator.ui;

import com.alsjava.challenge.memegenerator.utils.Languages;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;

/**
 * Created by aluis on 12/14/19.
 */
public class Menu extends VerticalLayout {

    public Menu() {
        RouterLink MemeCreator = new RouterLink(Languages.get().i18n("link.meme.generator"), MemeGenerator.class);
        RouterLink gallery = new RouterLink(Languages.get().i18n("link.gallery"), Gallery.class);
        add(MemeCreator, gallery);
    }
}
