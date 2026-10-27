package o0;

import android.content.Context;
import android.os.Build;
import android.view.PointerIcon;

/* JADX INFO: compiled from: PointerIconCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Object f6698a;

    public r(Object obj) {
        this.f6698a = obj;
    }

    public static r b(Context context, int i8) {
        return Build.VERSION.SDK_INT >= 24 ? new r(PointerIcon.getSystemIcon(context, i8)) : new r(null);
    }

    public Object a() {
        return this.f6698a;
    }
}
