package org.chromium.components.page_info;

import defpackage.lp0;
import defpackage.qp;
import org.chromium.components.omnibox.AutocompleteSchemeClassifier;

/* JADX INFO: compiled from: PageInfoControllerDelegate.java */
/* JADX INFO: loaded from: classes3.dex */
public abstract class c {
    public final AutocompleteSchemeClassifier a;
    public final boolean b;
    public final boolean c;
    public int d = 1;
    public String e = null;

    public c(qp qpVar, boolean z, boolean z2) {
        this.a = qpVar;
        this.b = z;
        this.c = z2;
    }

    public abstract lp0 a();

    public abstract String b();

    public abstract boolean c();
}
