package com.ts.car.input;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class Configuration {
    private List<KeyCodeCombine> keyCodeCombine = new ArrayList();
    private HashMap<String, String> properties = new HashMap<>();

    public Configuration addProperty(String key, String value) {
        this.properties.put(key, value);
        return this;
    }

    public HashMap<String, String> getPropertiesMap() {
        return this.properties;
    }

    public Configuration addKeyCodeCombine(List<KeyCodeCombine> list) {
        for (KeyCodeCombine k : list) {
            this.keyCodeCombine.add(k);
        }
        return this;
    }

    public List<KeyCodeCombine> getKeyCodeCombineList() {
        return Collections.unmodifiableList(this.keyCodeCombine);
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(String.format("keyCodeCombine={%s}", this.keyCodeCombine));
        return sb.toString();
    }
}
