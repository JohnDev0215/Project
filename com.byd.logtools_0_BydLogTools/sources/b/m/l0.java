package b.m;

import android.view.View;
import android.view.WindowId;

/* JADX INFO: compiled from: WindowIdApi18.java */
/* JADX INFO: loaded from: classes.dex */
class l0 implements m0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final WindowId f2740a;

    l0(View view) {
        this.f2740a = view.getWindowId();
    }

    public boolean equals(Object obj) {
        return (obj instanceof l0) && ((l0) obj).f2740a.equals(this.f2740a);
    }

    public int hashCode() {
        return this.f2740a.hashCode();
    }
}
