package com.fasterxml.jackson.databind;

import p.qw;

/* JADX INFO: loaded from: classes.dex */
public enum MapperFeature implements qw {
    /* JADX INFO: Fake field, exist only in values array */
    EF1(true),
    /* JADX INFO: Fake field, exist only in values array */
    EF2(true),
    /* JADX INFO: Fake field, exist only in values array */
    EF3(false),
    c(true),
    e(true),
    k(true),
    l(true),
    m(true),
    /* JADX INFO: Fake field, exist only in values array */
    EF9(false),
    /* JADX INFO: Fake field, exist only in values array */
    EF10(true),
    /* JADX INFO: Fake field, exist only in values array */
    EF11(true),
    /* JADX INFO: Fake field, exist only in values array */
    EF12(true),
    /* JADX INFO: Fake field, exist only in values array */
    EF13(false),
    /* JADX INFO: Fake field, exist only in values array */
    EF14(true),
    /* JADX INFO: Fake field, exist only in values array */
    EF0(true),
    /* JADX INFO: Fake field, exist only in values array */
    EF0(false),
    /* JADX INFO: Fake field, exist only in values array */
    EF0(false),
    /* JADX INFO: Fake field, exist only in values array */
    EF0(true),
    /* JADX INFO: Fake field, exist only in values array */
    EF0(true),
    /* JADX INFO: Fake field, exist only in values array */
    EF0(false),
    /* JADX INFO: Fake field, exist only in values array */
    EF0(true),
    /* JADX INFO: Fake field, exist only in values array */
    EF0(false),
    /* JADX INFO: Fake field, exist only in values array */
    EF0(false),
    /* JADX INFO: Fake field, exist only in values array */
    EF0(false),
    /* JADX INFO: Fake field, exist only in values array */
    EF0(false),
    /* JADX INFO: Fake field, exist only in values array */
    EF0(false),
    /* JADX INFO: Fake field, exist only in values array */
    EF0(false),
    n(true),
    /* JADX INFO: Fake field, exist only in values array */
    EF320(true),
    /* JADX INFO: Fake field, exist only in values array */
    EF331(true),
    /* JADX INFO: Fake field, exist only in values array */
    EF343(false),
    /* JADX INFO: Fake field, exist only in values array */
    EF355(true);

    private final boolean _defaultState;
    private final long _mask = 1 << ordinal();

    MapperFeature(boolean z) {
        this._defaultState = z;
    }

    public static long c() {
        long j = 0;
        for (MapperFeature mapperFeature : values()) {
            if (mapperFeature._defaultState) {
                j |= mapperFeature._mask;
            }
        }
        return j;
    }

    @Override // p.qw
    public final boolean a() {
        return this._defaultState;
    }

    @Override // p.qw
    public final int b() {
        return (int) this._mask;
    }

    public final long d() {
        return this._mask;
    }
}
