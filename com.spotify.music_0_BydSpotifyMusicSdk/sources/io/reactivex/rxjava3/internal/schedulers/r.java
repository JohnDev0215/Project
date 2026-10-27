package io.reactivex.rxjava3.internal.schedulers;

import p.he1;

/* JADX INFO: loaded from: classes.dex */
public abstract class r {
    public static final boolean a;

    static {
        boolean zEquals = true;
        try {
            String str = (String) new io.reactivex.rxjava3.internal.functions.d(1).apply("rx3.purge-enabled");
            if (str != null) {
                zEquals = "true".equals(str);
            }
        } catch (Throwable th) {
            he1.R(th);
        }
        a = zEquals;
    }
}
