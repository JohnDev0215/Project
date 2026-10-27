package defpackage;

import android.view.View;
import android.view.WindowId;

/* JADX INFO: compiled from: WindowIdApi18.java */
/* JADX INFO: loaded from: classes.dex */
public final class mt3 implements nt3 {
    public final WindowId a;

    public mt3(View view) {
        this.a = view.getWindowId();
    }

    public final boolean equals(Object obj) {
        return (obj instanceof mt3) && ((mt3) obj).a.equals(this.a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }
}
