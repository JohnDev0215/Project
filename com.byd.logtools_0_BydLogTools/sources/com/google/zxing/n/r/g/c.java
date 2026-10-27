package com.google.zxing.n.r.g;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: ExpandedRow.java */
/* JADX INFO: loaded from: classes.dex */
final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List<b> f4376a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f4377b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final boolean f4378c;

    c(List<b> list, int i, boolean z) {
        this.f4376a = new ArrayList(list);
        this.f4377b = i;
        this.f4378c = z;
    }

    List<b> a() {
        return this.f4376a;
    }

    int b() {
        return this.f4377b;
    }

    boolean c(List<b> list) {
        return this.f4376a.equals(list);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        return this.f4376a.equals(cVar.a()) && this.f4378c == cVar.f4378c;
    }

    public int hashCode() {
        return this.f4376a.hashCode() ^ Boolean.valueOf(this.f4378c).hashCode();
    }

    public String toString() {
        return "{ " + this.f4376a + " }";
    }
}
