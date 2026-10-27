package b.g.k;

import android.content.Context;
import android.os.Build;
import android.view.PointerIcon;

/* JADX INFO: compiled from: PointerIconCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Object f2355a;

    private r(Object obj) {
        this.f2355a = obj;
    }

    public static r b(Context context, int i) {
        return Build.VERSION.SDK_INT >= 24 ? new r(PointerIcon.getSystemIcon(context, i)) : new r(null);
    }

    public Object a() {
        return this.f2355a;
    }
}
