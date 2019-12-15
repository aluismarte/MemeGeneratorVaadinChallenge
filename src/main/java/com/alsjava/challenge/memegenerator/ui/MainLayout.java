package com.alsjava.challenge.memegenerator.ui;

import com.alsjava.challenge.memegenerator.utils.Constants;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by aluis on 12/10/19.
 */
@Push
@PWA(name = "Meme Generator On Vaadin", shortName = "Meme Generator")
public class MainLayout extends AppLayout implements RouterLayout {

    private final Div contentWrapper = new Div();

    public MainLayout() {
        setPrimarySection(Section.DRAWER);
        addToNavbar(true, new DrawerToggle(), new Label(Constants.get().APP_NAME));
        addToDrawer(new Menu());
        setContent(contentWrapper);
    }

    @Override
    public void showRouterLayoutContent(HasElement content) {
        contentWrapper.getElement().appendChild(content.getElement());
    }
}
