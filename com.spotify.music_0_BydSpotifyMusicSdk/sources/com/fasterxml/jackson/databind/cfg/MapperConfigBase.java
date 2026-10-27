package com.fasterxml.jackson.databind.cfg;

import com.fasterxml.jackson.annotation.JsonSetter$Value;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.cfg.MapperConfigBase;
import p.qw;

/* JADX INFO: loaded from: classes.dex */
public abstract class MapperConfigBase<CFG extends qw, T extends MapperConfigBase<CFG, T>> extends MapperConfig<T> {
    static {
        MapperFeature.c();
        MapperFeature.e.d();
        MapperFeature.k.d();
        MapperFeature.l.d();
        MapperFeature.m.d();
        MapperFeature.c.d();
    }

    public abstract JsonSetter$Value b();
}
