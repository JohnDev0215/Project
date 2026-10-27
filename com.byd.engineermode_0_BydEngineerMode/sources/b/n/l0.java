package b.n;

import android.view.View;
import android.view.WindowId;

/* JADX INFO: compiled from: WindowIdApi18.java */
/* JADX INFO: loaded from: classes.dex */
class l0 implements m0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final WindowId f1990a;

    l0(View view) {
        this.f1990a = view.getWindowId();
    }

    public boolean equals(Object obj) {
        return (obj instanceof l0) && ((l0) obj).f1990a.equals(this.f1990a);
    }

    public int hashCode() {
        return this.f1990a.hashCode();
    }
}
