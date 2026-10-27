package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.cfg.MapperConfigBase;

/* JADX INFO: loaded from: classes.dex */
public abstract class DeserializationConfig extends MapperConfigBase<DeserializationFeature, DeserializationConfig> {
    private static final long serialVersionUID = 2;

    static {
        MapperConfig.a(DeserializationFeature.class);
    }
}
