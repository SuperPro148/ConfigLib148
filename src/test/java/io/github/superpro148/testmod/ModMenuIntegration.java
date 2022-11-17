package io.github.superpro148.testmod;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

public class ModMenuIntegration implements ModMenuApi {

    // Mod Menu integration for YetAnotherConfigLib
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> TestConfig.createGui(parent);
    }
}
