package org.chromium.chrome.browser.historyreport;

import defpackage.gq2;

/* JADX INFO: loaded from: classes.dex */
public final class DeltaFileEntry {
    public final long a;
    public final String b;
    public final String c;
    public final String d;
    public final String e;

    public DeltaFileEntry(long j, String str, String str2, String str3, String str4) {
        this.a = j;
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = str4;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("DeltaFileEntry[");
        sb.append(this.a);
        sb.append(", ");
        sb.append(this.b);
        sb.append(", ");
        sb.append(this.c);
        sb.append(", ");
        sb.append(this.d);
        sb.append(", ");
        return gq2.i(sb, this.e, "]");
    }
}
