package b.n;

import android.view.View;
import android.view.WindowId;

/* JADX INFO: compiled from: WindowIdApi18.java */
/* JADX INFO: loaded from: classes.dex */
class l0 implements m0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final WindowId f2158a;

    l0(View view) {
        this.f2158a = view.getWindowId();
    }

    public boolean equals(Object obj) {
        return (obj instanceof l0) && ((l0) obj).f2158a.equals(this.f2158a);
    }

    public int hashCode() {
        return this.f2158a.hashCode();
    }
}
