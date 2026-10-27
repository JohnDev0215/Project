package com.sun.xml.bind.v2.model.core;

/* JADX INFO: loaded from: classes3.dex */
public enum PropertyKind {
    VALUE(true, false, Integer.MAX_VALUE),
    ATTRIBUTE(false, false, Integer.MAX_VALUE),
    ELEMENT(true, true, 0),
    REFERENCE(false, true, 1),
    MAP(false, true, 2);

    public final boolean canHaveXmlMimeType;
    public final boolean isOrdered;
    public final int propertyIndex;

    PropertyKind(boolean z, boolean z2, int i) {
        this.canHaveXmlMimeType = z;
        this.isOrdered = z2;
        this.propertyIndex = i;
    }
}
