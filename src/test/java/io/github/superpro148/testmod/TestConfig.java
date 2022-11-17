package io.github.superpro148.testmod;

import dev.isxander.yacl.api.ConfigCategory;
import dev.isxander.yacl.api.Option;
import dev.isxander.yacl.api.YetAnotherConfigLib;
import dev.isxander.yacl.gui.controllers.BooleanController;
import dev.isxander.yacl.gui.controllers.cycling.EnumController;
import dev.isxander.yacl.gui.controllers.slider.DoubleSliderController;
import dev.isxander.yacl.gui.controllers.slider.FloatSliderController;
import dev.isxander.yacl.gui.controllers.slider.IntegerSliderController;
import dev.isxander.yacl.gui.controllers.slider.LongSliderController;
import io.github.superpro148.configlib148.ConfigList;
import io.github.superpro148.configlib148.ConfigOption;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class TestConfig {

    // Initializing the ConfigList. The config file will be found in ./minecraft/config/ and named <fileName>.json.
    public static ConfigList CONFIG_LIST = new ConfigList("testconfig");

    // Registering the ConfigOptions. Format for registering is:
    // public static ConfigOption<OptionType> OPTION_NAME = CONFIG_LIST.register(new ConfigOption<>("jsonKey", OptionType.class, defaultValue));
    // OptionType can be the following classes: Boolean, Integer, Long, Float, Double
    // OptionType can also be any custom Enum class.
    // Please ensure that every ConfigOption has a unique jsonKey, to prevent conflicts while reading and writing the config file.
    public static ConfigOption<Boolean> TEST_BOOL = CONFIG_LIST.register(new ConfigOption<>("bool", Boolean.class, true));
    public static ConfigOption<Integer> TEST_INT = CONFIG_LIST.register(new ConfigOption<>("int", Integer.class, 5));
    public static ConfigOption<Long> TEST_LONG = CONFIG_LIST.register(new ConfigOption<>("long", Long.class, 10l));
    public static ConfigOption<Float> TEST_FLOAT = CONFIG_LIST.register(new ConfigOption<>("float", Float.class, 2.0f));
    public static ConfigOption<Double> TEST_DOUBLE = CONFIG_LIST.register(new ConfigOption<>("double", Double.class, 0.5));
    public static ConfigOption<TestEnum> TEST_ENUM = CONFIG_LIST.register(new ConfigOption<>("enum", TestEnum.class, TestEnum.A));

    // save method for YetAnotherConfigLib
    public static void save() {
        // Remember to add CONFIG_LIST.saveConfig() here to ensure the config is saved to the file.
        CONFIG_LIST.saveConfig();
    }

    // YetAnotherConfigLib screen generation.
    // I created this mod to complement YACL, which does not include a way to save configs between sessions.
    // My mod does not include screen generation, I recommend you to use YACL for this.
    public static Screen createGui(Screen parent) {
        return YetAnotherConfigLib.createBuilder()
                .title(Text.of("tester"))
                .category(ConfigCategory.createBuilder()
                        .name(Text.of("test"))
                        .option(Option.createBuilder(Boolean.class)
                                .name(Text.of("booler"))
                                .binding(
                                        // YACL bindings, remember to use CONFIG_OPTION.getValue() and CONFIG_OPTION.setValue(newValue) for the getter and setter respectively.
                                        true,
                                        () -> TEST_BOOL.getValue(),
                                        newValue -> TEST_BOOL.setValue(newValue)
                                )
                                .controller(BooleanController::new)
                                .build())
                        .option(Option.createBuilder(Integer.class)
                                .name(Text.of("intg"))
                                .binding(
                                        5,
                                        () -> TEST_INT.getValue(),
                                        (newValue) -> TEST_INT.setValue(newValue)
                                )
                                .controller(option -> new IntegerSliderController(option, 0, 100, 1))
                                .build())
                        .option(Option.createBuilder(Long.class)
                                .name(Text.of("lon"))
                                .binding(
                                        10l,
                                        () -> TEST_LONG.getValue(),
                                        (newValue) -> TEST_LONG.setValue(newValue)
                                )
                                .controller(option -> new LongSliderController(option, 0, 100, 1))
                                .build())
                        .option(Option.createBuilder(Float.class)
                                .name(Text.of("flot"))
                                .binding(
                                        2.0f,
                                        () -> TEST_FLOAT.getValue(),
                                        (newValue) -> TEST_FLOAT.setValue(newValue)
                                )
                                .controller(option -> new FloatSliderController(option, 0, 10, 0.1f))
                                .build())
                        .option(Option.createBuilder(Double.class)
                                .name(Text.of("dub"))
                                .binding(
                                        0.5,
                                        () -> TEST_DOUBLE.getValue(),
                                        (newValue) -> TEST_DOUBLE.setValue(newValue)
                                )
                                .controller(option -> new DoubleSliderController(option, 0, 1, 0.01))
                                .build())
                        .option(Option.createBuilder(TestEnum.class)
                                .name(Text.of("enm"))
                                .binding(
                                        TestEnum.A,
                                        () -> TEST_ENUM.getValue(),
                                        (newValue) -> TEST_ENUM.setValue(newValue)
                                )
                                .controller(option -> new EnumController<TestEnum>(option))
                                .build())
                        .build())
                // YACL save method, see above
                .save(TestConfig::save)
                .build()
                .generateScreen(parent);
    }
}
