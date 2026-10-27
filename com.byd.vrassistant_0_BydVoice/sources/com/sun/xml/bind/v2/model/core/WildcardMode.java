package com.sun.xml.bind.v2.model.core;

/* JADX INFO: loaded from: classes3.dex */
public enum WildcardMode {
    STRICT(false, true),
    SKIP(true, false),
    LAX(true, true);

    public final boolean allowDom;
    public final boolean allowTypedObject;

    WildcardMode(boolean z, boolean z2) {
        this.allowDom = z;
        this.allowTypedObject = z2;
    }
}
