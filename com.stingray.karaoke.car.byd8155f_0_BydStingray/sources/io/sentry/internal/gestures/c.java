package io.sentry.internal.gestures;

import android.view.View;
import h6.e;
import java.lang.ref.WeakReference;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final WeakReference f16571a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f16572b;
    public final String c;
    public final String d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f16573e;

    public c(View view, String str, String str2, String str3, String str4) {
        this.f16571a = new WeakReference(view);
        this.f16572b = str;
        this.c = str2;
        this.d = str3;
        this.f16573e = str4;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || c.class != obj.getClass()) {
            return false;
        }
        c cVar = (c) obj;
        return e.i(this.f16572b, cVar.f16572b) && e.i(this.c, cVar.c) && e.i(this.d, cVar.d);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f16571a, this.c, this.d});
    }
}
