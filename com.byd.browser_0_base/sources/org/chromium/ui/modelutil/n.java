package org.chromium.ui.modelutil;

import defpackage.im2;
import defpackage.p81;
import org.chromium.ui.modelutil.o;

/* JADX INFO: compiled from: SimpleRecyclerViewMcp.java */
/* JADX INFO: loaded from: classes3.dex */
public final class n<T, VH> extends o<T, VH, Void> {

    /* JADX INFO: compiled from: SimpleRecyclerViewMcp.java */
    public interface a<T, VH> {
        void h(VH vh, T t);
    }

    public n(p81<T> p81Var, o.a<T> aVar, a<T, VH> aVar2) {
        super(aVar, new im2(aVar2), p81Var);
    }
}
