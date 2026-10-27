package a.g.k;

import android.content.Context;
import android.os.Build;
import android.view.PointerIcon;

/* JADX INFO: loaded from: classes.dex */
public final class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Object f422a;

    private r(Object obj) {
        this.f422a = obj;
    }

    public static r b(Context context, int i) {
        return Build.VERSION.SDK_INT >= 24 ? new r(PointerIcon.getSystemIcon(context, i)) : new r(null);
    }

    public Object a() {
        return this.f422a;
    }
}
