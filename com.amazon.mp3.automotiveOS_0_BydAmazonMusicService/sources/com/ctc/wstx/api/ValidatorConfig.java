package com.ctc.wstx.api;

/* JADX INFO: loaded from: classes5.dex */
public final class ValidatorConfig extends CommonConfig {
    static final ValidatorConfig sInstance = new ValidatorConfig(null);

    private ValidatorConfig(ValidatorConfig validatorConfig) {
        super(validatorConfig);
    }

    public static ValidatorConfig createDefaults() {
        return sInstance;
    }
}
