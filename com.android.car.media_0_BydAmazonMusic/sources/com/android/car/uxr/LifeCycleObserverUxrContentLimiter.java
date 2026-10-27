package com.android.car.uxr;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import spi.c2;
import spi.jc;
import spi.w2;

/* JADX INFO: loaded from: classes2.dex */
public class LifeCycleObserverUxrContentLimiter implements DefaultLifecycleObserver {
    public final jc a;

    public LifeCycleObserverUxrContentLimiter(jc jcVar) {
        this.a = jcVar;
    }

    public final void a(w2 w2Var) {
        jc jcVar = this.a;
        jcVar.a = w2Var;
        int iC = w2Var.c();
        if (jcVar.d.a.containsKey(Integer.valueOf(iC))) {
            c2.b bVar = jcVar.d.a.get(Integer.valueOf(iC));
            jcVar.b = bVar;
            Integer num = bVar.c;
            if (num != null) {
                jcVar.a.b(num.intValue());
            }
        }
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
    public final void onStart(@NonNull LifecycleOwner lifecycleOwner) {
        jc jcVar = this.a;
        jcVar.c.register(jcVar.e);
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
    public final void onStop(@NonNull LifecycleOwner lifecycleOwner) {
        jc jcVar = this.a;
        jcVar.c.unregister(jcVar.e);
    }
}
