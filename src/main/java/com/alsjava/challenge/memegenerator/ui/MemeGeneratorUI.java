package com.alsjava.challenge.memegenerator.ui;

import com.alsjava.challenge.memegenerator.utils.Languages;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

import java.io.File;

/**
 * Created by aluis on 12/14/19.
 */
@Route(value = "creator", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class MemeGeneratorUI extends VerticalLayout {

    private final MemeGenerator memeGenerator = new MemeGenerator();

    private final File memesDir = new File("./memes");

    public MemeGeneratorUI() {
        TextField tfTopText = new TextField(Languages.get().i18n("action.top.text"));
        tfTopText.setValueChangeMode(ValueChangeMode.EAGER);
        tfTopText.addValueChangeListener(event -> memeGenerator.setTopText(event.getValue()));
        TextField tfBottomText = new TextField(Languages.get().i18n("action.bottom.text"));
        tfBottomText.setValueChangeMode(ValueChangeMode.EAGER);
        tfBottomText.addValueChangeListener(event -> memeGenerator.setBottomText(event.getValue()));
        Button btnGenerate = new Button(Languages.get().i18n("action.generate"));
        btnGenerate.addClickListener(event -> {
            // Create image.
        });

        HorizontalLayout hlMenu = new HorizontalLayout(tfTopText, tfBottomText, btnGenerate);
        hlMenu.setDefaultVerticalComponentAlignment(Alignment.END);
        add(hlMenu);

        memeGenerator.setTopText("Its");
        memeGenerator.setBottomText("Working!");
        memeGenerator.setSrc(new File(memesDir, "Monkey.jpg"));
        add(memeGenerator);
    }
}
