package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import defpackage.bc;
import defpackage.cc;
import defpackage.ec;

/* JADX INFO: loaded from: classes.dex */
public class SingleGeneratedAdapterObserver implements cc {
    public final bc a;

    public SingleGeneratedAdapterObserver(bc bcVar) {
        this.a = bcVar;
    }

    @Override // defpackage.cc
    public void d(ec ecVar, Lifecycle.Event event) {
        this.a.a(ecVar, event, false, null);
        this.a.a(ecVar, event, true, null);
    }
}
