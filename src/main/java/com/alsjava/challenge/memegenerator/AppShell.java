package com.alsjava.challenge.memegenerator;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.server.PWA;

@Push
@PWA(name = "Meme Generator Vaadin", shortName = "Meme Generator")
public class AppShell implements AppShellConfigurator {
}
