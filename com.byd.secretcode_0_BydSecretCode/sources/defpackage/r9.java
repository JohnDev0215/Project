package defpackage;

import android.content.Context;
import android.os.Build;
import android.view.PointerIcon;

/* JADX INFO: compiled from: PointerIconCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class r9 {
    public Object a;

    public r9(Object obj) {
        this.a = obj;
    }

    public static r9 b(Context context, int i) {
        return Build.VERSION.SDK_INT >= 24 ? new r9(PointerIcon.getSystemIcon(context, i)) : new r9(null);
    }

    public Object a() {
        return this.a;
    }
}
