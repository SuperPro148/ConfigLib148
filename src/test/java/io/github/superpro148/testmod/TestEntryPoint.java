package io.github.superpro148.testmod;

import net.fabricmc.api.ClientModInitializer;

public class TestEntryPoint implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        // Remember to include this to ensure the config file gets loaded at initialization.
        TestConfig.CONFIG_LIST.readConfig();
    }
}
