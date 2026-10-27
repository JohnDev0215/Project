package b.h.m;

import android.content.Context;
import android.os.Build;
import android.view.PointerIcon;

/* JADX INFO: compiled from: PointerIconCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Object f2643a;

    public r(Object obj) {
        this.f2643a = obj;
    }

    public static r b(Context context, int i2) {
        return Build.VERSION.SDK_INT >= 24 ? new r(PointerIcon.getSystemIcon(context, i2)) : new r(null);
    }

    public Object a() {
        return this.f2643a;
    }
}
