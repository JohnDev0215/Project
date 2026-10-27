package org.chromium.base;

import android.os.Handler;
import android.os.Looper;
import defpackage.aj3;
import defpackage.u80;
import defpackage.zi3;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import org.chromium.base.ThreadUtils;

/* JADX INFO: compiled from: UnownedUserDataHost.java */
/* JADX INFO: loaded from: classes.dex */
public final class c {
    public final ThreadUtils.a a;
    public final u80 b;
    public Handler c;
    public HashMap<aj3<?>, WeakReference<? extends zi3>> d;

    public c() {
        Looper looperMyLooper = Looper.myLooper();
        if (looperMyLooper == null) {
            throw new IllegalStateException();
        }
        Handler handler = new Handler(looperMyLooper);
        this.a = new ThreadUtils.a();
        this.b = new u80();
        this.d = new HashMap<>();
        this.c = handler;
    }

    public final <T extends zi3> T a(aj3<T> aj3Var) {
        this.a.getClass();
        this.b.getClass();
        WeakReference<? extends zi3> weakReference = this.d.get(aj3Var);
        if (weakReference == null) {
            return null;
        }
        zi3 zi3Var = weakReference.get();
        if (zi3Var != null) {
            return aj3Var.a.cast(zi3Var);
        }
        aj3Var.c(this);
        return null;
    }
}
