package com.alsjava.challenge.memegenerator.ui;

import com.alsjava.challenge.memegenerator.utils.Languages;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.vaadin.addons.tatu.ColorPicker;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URI;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aluis on 12/10/19.
 */
@Route("")
public class MainLayout extends VerticalLayout {

    private final List<String> FONTS = List.of("Arial", "Helvetica", "Gill Sans", "Lucida", "Helvetica Narrow", "sans-serif", "Times", "Times New Roman", "Palatino", "Bookman", "New Century Schoolbook", "serif", "Andale Mono", "Courier New", "Courier", "Lucidatypewriter", "Fixed", "monospace", "Comic Sans", "Comic Sans MS", "Zapf Chancery", "Coronetscript", "Florence", "Parkavenue", "cursive", "Impact", "Arnoldboecklin", "Oldtown", "Blippo", "Brushstroke", "fantasy");

    private final MemeGenerator memeGenerator = new MemeGenerator();

    private ListDataProvider<File> dataProvider;

    public MainLayout() {
        setSizeFull();

        File dir = new File("./memes");
        File[] memes = dir.listFiles();
        if (memes == null || memes.length == 0) {
            return;
        }

        memeGenerator.setSizeFull();

        dataProvider = DataProvider.ofItems(memes);

        Grid<File> grid = new Grid<>();
        grid.setWidthFull();
        grid.setMinHeight("300px");
        grid.addColumn(File::getName).setHeader("File");
        grid.addSelectionListener(event -> memeGenerator.setSrc(event.getFirstSelectedItem().orElse(null)));
        grid.setDataProvider(dataProvider);
        memeGenerator.setSrc(memes[0]);

        TextField tfSearchMemeOnGrid = new TextField();
        tfSearchMemeOnGrid.setValueChangeMode(ValueChangeMode.EAGER);
        tfSearchMemeOnGrid.setPlaceholder(Languages.get().i18n("action.search.on.grid"));
        tfSearchMemeOnGrid.setPrefixComponent(VaadinIcon.SEARCH.create());
        tfSearchMemeOnGrid.setWidthFull();
        tfSearchMemeOnGrid.addValueChangeListener(event -> dataProvider.setFilter(file -> file.getName().contains(event.getValue())));

        VerticalLayout mainLayout = new VerticalLayout(createFormMeme(), tfSearchMemeOnGrid, grid);
        mainLayout.setWidthFull();

        SplitLayout splitLayout = new SplitLayout(mainLayout, memeGenerator);
        splitLayout.setWidthFull();
        add(splitLayout);
    }

    private FormLayout createFormMeme() {
        NumberField nfMemeWidth = new NumberField(Languages.get().i18n("action.meme.width"));
        nfMemeWidth.setValueChangeMode(ValueChangeMode.EAGER);
        nfMemeWidth.addValueChangeListener(event -> memeGenerator.setMemeWidth(event.getValue()));
        nfMemeWidth.setValue(400d);

        TextField tfUploadMemeURL = new TextField(Languages.get().i18n("action.upload.meme"));
        tfUploadMemeURL.setValueChangeMode(ValueChangeMode.EAGER);
        tfUploadMemeURL.addValueChangeListener(event -> {
            try {
                File file = Files.createTempFile("meme", "internet").toFile();
                URL url = URI.create(event.getValue()).toURL();
                url.toURI(); // Valid is a good URL
                ReadableByteChannel readableByteChannel = Channels.newChannel(url.openStream());
                try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
                    fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
                }
                memeGenerator.setSrc(file);
            } catch (Exception ignored) {
                tfUploadMemeURL.setErrorMessage(Languages.get().i18n("error.not.a.url"));
            }
        });

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

        ColorPicker cpTextColor = new ColorPicker();
        cpTextColor.setNoClear(false);
        List<Color> colorList = List.of(
                Color.WHITE, Color.LIGHT_GRAY, Color.GRAY, Color.DARK_GRAY, Color.BLACK, Color.RED, Color.PINK,
                Color.ORANGE, Color.YELLOW, Color.GREEN, Color.MAGENTA, Color.CYAN, Color.BLUE
        );
        List<ColorPicker.ColorPreset> colors = new ArrayList<>();
        for (Color color : colorList) {
            String hexValue = String.format("#%06x", 0xFFFFFF & color.getRGB());
            String colorName = "[r=" + color.getRed() + ",g=" + color.getGreen() + ",b=" + color.getBlue() + "]";
            colors.add(new ColorPicker.ColorPreset(hexValue, colorName, ColorPicker.CaptionMode.HTML));
        }
        cpTextColor.setNoClear(true);
        cpTextColor.setPresets(colors);
        cpTextColor.setLabel(Languages.get().i18n("action.font.color"));
        cpTextColor.addValueChangeListener(event -> memeGenerator.setTextColor(event.getValue()));
        cpTextColor.setValue(colors.getFirst().getColor());

        ComboBox<String> cbFontFamily = new ComboBox<>(Languages.get().i18n("action.font.family"));
        cbFontFamily.setItems(FONTS);
        cbFontFamily.addValueChangeListener(event -> memeGenerator.setFontFamily(event.getValue()));
        cbFontFamily.setValue(FONTS.getFirst());

        TextField tfFontSize = new TextField(Languages.get().i18n("action.font.size"));
        tfFontSize.setValueChangeMode(ValueChangeMode.EAGER);
        tfFontSize.addValueChangeListener(event -> memeGenerator.setFontSize(event.getValue()));
        tfFontSize.setValue("2em");

        tfTopText.setValue("Hello");
        tfBottomText.setValue("Meme!");

        FormLayout formLayout = new FormLayout(tfUploadMemeURL, nfMemeWidth, nfMemeHeight, tfTopText, tfBottomText, tfFontSize, cpTextColor, cbFontFamily, btnGenerate);
        formLayout.setWidthFull();

        return formLayout;
    }
}
