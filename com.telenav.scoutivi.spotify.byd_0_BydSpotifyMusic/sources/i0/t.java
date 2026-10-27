package i0;

import android.content.Context;
import android.view.PointerIcon;

/* JADX INFO: compiled from: PointerIconCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Object f6884a;

    private t(Object obj) {
        this.f6884a = obj;
    }

    public static t b(Context context, int i7) {
        return new t(PointerIcon.getSystemIcon(context, i7));
    }

    public Object a() {
        return this.f6884a;
    }
}
