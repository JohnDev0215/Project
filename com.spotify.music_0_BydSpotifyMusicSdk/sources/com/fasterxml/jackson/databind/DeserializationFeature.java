package com.fasterxml.jackson.databind;

import p.qw;

/* JADX INFO: loaded from: classes.dex */
public enum DeserializationFeature implements qw {
    c(false),
    e(false),
    k(false),
    /* JADX INFO: Fake field, exist only in values array */
    EF4(false),
    /* JADX INFO: Fake field, exist only in values array */
    EF5(true),
    l(false),
    /* JADX INFO: Fake field, exist only in values array */
    EF7(false),
    /* JADX INFO: Fake field, exist only in values array */
    EF8(true),
    /* JADX INFO: Fake field, exist only in values array */
    EF9(false),
    /* JADX INFO: Fake field, exist only in values array */
    EF10(false),
    /* JADX INFO: Fake field, exist only in values array */
    EF11(true),
    /* JADX INFO: Fake field, exist only in values array */
    EF12(false),
    /* JADX INFO: Fake field, exist only in values array */
    EF13(false),
    /* JADX INFO: Fake field, exist only in values array */
    EF0(true),
    /* JADX INFO: Fake field, exist only in values array */
    EF0(false),
    /* JADX INFO: Fake field, exist only in values array */
    EF0(true),
    /* JADX INFO: Fake field, exist only in values array */
    EF0(false),
    m(false),
    /* JADX INFO: Fake field, exist only in values array */
    EF0(false),
    /* JADX INFO: Fake field, exist only in values array */
    EF0(false),
    n(false),
    /* JADX INFO: Fake field, exist only in values array */
    EF241(true),
    /* JADX INFO: Fake field, exist only in values array */
    EF253(false),
    /* JADX INFO: Fake field, exist only in values array */
    EF264(false),
    /* JADX INFO: Fake field, exist only in values array */
    EF275(false),
    /* JADX INFO: Fake field, exist only in values array */
    EF287(true),
    /* JADX INFO: Fake field, exist only in values array */
    EF298(true),
    /* JADX INFO: Fake field, exist only in values array */
    EF309(true);

    private final boolean _defaultState;
    private final int _mask = 1 << ordinal();

    DeserializationFeature(boolean z) {
        this._defaultState = z;
    }

    @Override // p.qw
    public final boolean a() {
        return this._defaultState;
    }

    @Override // p.qw
    public final int b() {
        return this._mask;
    }
}
