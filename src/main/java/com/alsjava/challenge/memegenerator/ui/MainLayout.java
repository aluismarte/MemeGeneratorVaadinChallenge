package com.alsjava.challenge.memegenerator.ui;

import com.alsjava.challenge.memegenerator.utils.Languages;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

import java.io.File;

/**
 * Created by aluis on 12/10/19.
 */
@SuppressWarnings("unused")
@Push
@Route("")
@PWA(name = "Meme Generator Vaadin", shortName = "Meme Generator")
public class MainLayout extends VerticalLayout {

    private final MemeGenerator memeGenerator = new MemeGenerator();

    public MainLayout() {
        setSizeFull();

        TextField tfTopText = new TextField(Languages.get().i18n("action.top.text"));
        tfTopText.setValueChangeMode(ValueChangeMode.EAGER);
        tfTopText.addValueChangeListener(event -> memeGenerator.setTopText(event.getValue()));
        TextField tfBottomText = new TextField(Languages.get().i18n("action.bottom.text"));
        tfBottomText.setValueChangeMode(ValueChangeMode.EAGER);
        tfBottomText.addValueChangeListener(event -> memeGenerator.setBottomText(event.getValue()));
        Button btnGenerate = new Button(Languages.get().i18n("action.generate"));
        btnGenerate.addClickListener(event -> memeGenerator.generateMeme());
        ComboBox<String> cbTextColor = new ComboBox<>(); // Use a color picker to get more colors
        cbTextColor.setItems("red", "black", "white", "blue");
        cbTextColor.addValueChangeListener(event -> memeGenerator.setTextColor(event.getValue()));
        cbTextColor.setValue("white");

        HorizontalLayout hlMenu = new HorizontalLayout(tfTopText, tfBottomText, cbTextColor, btnGenerate);
        hlMenu.setWidthFull();
        hlMenu.setDefaultVerticalComponentAlignment(Alignment.END);
        add(hlMenu);

        Grid<File> grid = new Grid<>();
        grid.addColumn(File::getName).setHeader("File");
        grid.addSelectionListener(event -> memeGenerator.setSrc(event.getFirstSelectedItem().orElse(null)));

        File dir = new File("./memes");
        File[] memes = dir.listFiles();
        if (memes != null && memes.length > 0) {
            grid.setItems(memes);
            memeGenerator.setSrc(memes[0]);
        }

        VerticalLayout mainLayout = new VerticalLayout(hlMenu, grid);
        mainLayout.setWidthFull();

        SplitLayout splitLayout = new SplitLayout(mainLayout, memeGenerator);
        splitLayout.setSizeFull();
        add(splitLayout);
    }
}
