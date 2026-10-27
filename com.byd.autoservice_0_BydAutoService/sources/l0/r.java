package l0;

import android.content.Context;
import android.os.Build;
import android.view.PointerIcon;

/* JADX INFO: compiled from: PointerIconCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Object f3650a;

    public r(Object obj) {
        this.f3650a = obj;
    }

    public static r b(Context context, int i3) {
        return Build.VERSION.SDK_INT >= 24 ? new r(PointerIcon.getSystemIcon(context, i3)) : new r(null);
    }

    public Object a() {
        return this.f3650a;
    }
}
