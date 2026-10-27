package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import defpackage.cc;
import defpackage.ec;
import defpackage.zb;

/* JADX INFO: loaded from: classes.dex */
public class ReflectiveGenericLifecycleObserver implements cc {
    public final Object a;
    public final zb.a b;

    public ReflectiveGenericLifecycleObserver(Object obj) {
        this.a = obj;
        this.b = zb.a.c(obj.getClass());
    }

    @Override // defpackage.cc
    public void d(ec ecVar, Lifecycle.Event event) {
        this.b.a(ecVar, event, this.a);
    }
}
