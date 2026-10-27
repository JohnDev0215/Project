package com.sun.xml.bind.v2.runtime;

/* JADX INFO: loaded from: classes3.dex */
public final class NameList {
    public final String[] localNames;
    public final String[] namespaceURIs;
    public final boolean[] nsUriCannotBeDefaulted;
    public final int numberOfAttributeNames;
    public final int numberOfElementNames;

    public NameList(String[] strArr, boolean[] zArr, String[] strArr2, int i, int i2) {
        this.namespaceURIs = strArr;
        this.nsUriCannotBeDefaulted = zArr;
        this.localNames = strArr2;
        this.numberOfElementNames = i;
        this.numberOfAttributeNames = i2;
    }
}
