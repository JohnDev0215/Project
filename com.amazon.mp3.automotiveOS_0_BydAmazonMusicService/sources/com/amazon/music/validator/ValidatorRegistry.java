package com.amazon.music.validator;

import java.util.HashMap;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes4.dex */
public final class ValidatorRegistry {
    private static ValidatorRegistry instance;
    private HashMap<String, Validator> validators = new HashMap<>();

    public static synchronized ValidatorRegistry getInstance() {
        if (instance == null) {
            instance = new ValidatorRegistry();
        }
        return instance;
    }

    public synchronized ValidatorWrapper getValidator(@Nonnull String str) {
        return new ValidatorWrapper(this.validators.get(str));
    }

    private ValidatorRegistry() {
    }
}
