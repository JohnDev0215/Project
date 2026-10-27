package com.amazon.music.runtimePreferences;

import java.util.HashMap;

/* JADX INFO: loaded from: classes4.dex */
public final class PreferenceLoaderRegistry {
    private static PreferenceLoaderRegistry instance;
    private HashMap<String, PreferenceLoader> registry = new HashMap<>();

    public static PreferenceLoaderRegistry getInstance() {
        if (instance == null) {
            instance = new PreferenceLoaderRegistry();
        }
        return instance;
    }

    synchronized PreferenceLoader getPreferenceLoader(String str) {
        return this.registry.get(str);
    }

    private PreferenceLoaderRegistry() {
    }
}
