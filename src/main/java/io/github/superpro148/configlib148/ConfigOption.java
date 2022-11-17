package io.github.superpro148.configlib148;

public class ConfigOption<T> {
    private Class<T> classType;
    private T value;
    private String jsonKey;

    public ConfigOption(String jsonKey, Class<T> optionClassType, T defaultValue) {
        this.jsonKey = jsonKey;
        this.classType = optionClassType;
        this.value = defaultValue;
    }

    public Class<T> getClassType() {
        return this.classType;
    }

    public T getValue() {
        return this.value;
    }

    public void setValue(T newValue) {
        this.value = newValue;
    }

    public String getKey() {
        return this.jsonKey;
    }
}
