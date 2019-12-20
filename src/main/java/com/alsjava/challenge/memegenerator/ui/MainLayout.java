package com.alsjava.challenge.memegenerator.ui;

import com.alsjava.challenge.memegenerator.utils.Languages;
import com.github.juchar.colorpicker.ColorPickerField;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

import java.awt.*;
import java.io.File;
import java.util.List;

/**
 * Created by aluis on 12/10/19.
 */
@SuppressWarnings("unused")
@Push
@Route("")
@PWA(name = "Meme Generator Vaadin", shortName = "Meme Generator")
public class MainLayout extends VerticalLayout {

    private final List<String> FONTS = List.of("Arial", "Helvetica", "Gill Sans", "Lucida", "Helvetica Narrow", "sans-serif", "Times", "Times New Roman", "Palatino", "Bookman", "New Century Schoolbook", "serif", "Andale Mono", "Courier New", "Courier", "Lucidatypewriter", "Fixed", "monospace", "Comic Sans", "Comic Sans MS", "Zapf Chancery", "Coronetscript", "Florence", "Parkavenue", "cursive", "Impact", "Arnoldboecklin", "Oldtown", "Blippo", "Brushstroke", "fantasy");

    private final MemeGenerator memeGenerator = new MemeGenerator();

    public MainLayout() {
        setSizeFull();

        File dir = new File("./memes");
        File[] memes = dir.listFiles();
        if (memes == null || memes.length == 0) {
            return;
        }

        memeGenerator.setSizeFull();

        Grid<File> grid = new Grid<>();
        grid.addColumn(File::getName).setHeader("File");
        grid.addSelectionListener(event -> memeGenerator.setSrc(event.getFirstSelectedItem().orElse(null)));
        grid.setItems(memes);
        memeGenerator.setSrc(memes[0]);

        VerticalLayout mainLayout = new VerticalLayout(createFormMeme(), grid);
        mainLayout.setWidthFull();

        SplitLayout splitLayout = new SplitLayout(mainLayout, memeGenerator);
        splitLayout.setSizeFull();
        add(splitLayout);
    }

    private FormLayout createFormMeme() {
        NumberField nfMemeWidth = new NumberField(Languages.get().i18n("action.meme.width"));
        nfMemeWidth.setValueChangeMode(ValueChangeMode.EAGER);
        nfMemeWidth.addValueChangeListener(event -> memeGenerator.setMemeWidth(event.getValue()));
        nfMemeWidth.setValue(400d);

        NumberField nfMemeHeight = new NumberField(Languages.get().i18n("action.meme.height"));
        nfMemeHeight.setValueChangeMode(ValueChangeMode.EAGER);
        nfMemeHeight.addValueChangeListener(event -> memeGenerator.setMemeHeight(event.getValue()));
        nfMemeHeight.setValue(300d);

        TextField tfTopText = new TextField(Languages.get().i18n("action.top.text"));
        tfTopText.setValueChangeMode(ValueChangeMode.EAGER);
        tfTopText.addValueChangeListener(event -> memeGenerator.setTopText(event.getValue()));

        TextField tfBottomText = new TextField(Languages.get().i18n("action.bottom.text"));
        tfBottomText.setValueChangeMode(ValueChangeMode.EAGER);
        tfBottomText.addValueChangeListener(event -> memeGenerator.setBottomText(event.getValue()));

        Button btnGenerate = new Button(Languages.get().i18n("action.generate"));
        btnGenerate.addClickListener(event -> memeGenerator.generateMeme());

        ColorPickerField cpTextColor = new ColorPickerField(Languages.get().i18n("action.font.color"));
        cpTextColor.addValueChangeListener(event -> memeGenerator.setTextColor("#" + Integer.toHexString(event.getValue().getRGB() & 0xffffff)));
        cpTextColor.setValue(Color.RED);

        ComboBox<String> cbFontFamily = new ComboBox<>(Languages.get().i18n("action.font.family"));
        cbFontFamily.setItems(FONTS);
        cbFontFamily.addValueChangeListener(event -> memeGenerator.setFontFamily(event.getValue()));
        cbFontFamily.setValue(FONTS.get(0));

        TextField tfFontSize = new TextField(Languages.get().i18n("action.font.size"));
        tfFontSize.setValueChangeMode(ValueChangeMode.EAGER);
        tfFontSize.addValueChangeListener(event -> memeGenerator.setFontSize(event.getValue()));
        tfFontSize.setValue("2em");

        tfTopText.setValue("Hello");
        tfBottomText.setValue("Meme!");

        FormLayout formLayout = new FormLayout(nfMemeWidth, nfMemeHeight, tfTopText, tfBottomText, tfFontSize, cpTextColor, cbFontFamily, btnGenerate);
        formLayout.setWidthFull();

        return formLayout;
    }
}
