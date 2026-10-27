package com.byd.data.common.entiry.extension;

import com.byd.data.common.base.gson.CustomJsonAdapter;
import com.google.gson.u.b;

/* JADX INFO: loaded from: classes.dex */
public class ExtensionItem implements Comparable<ExtensionItem> {
    public byte[] array;

    @b(CustomJsonAdapter.class)
    public short canId;
    public byte id;
    public byte size;
    public byte subId;

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(ExtensionItem extensionItem) {
        short s = this.canId;
        short s2 = extensionItem.canId;
        return s == s2 ? this.subId - extensionItem.subId : s - s2;
    }
}
