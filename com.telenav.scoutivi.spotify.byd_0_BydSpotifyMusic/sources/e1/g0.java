package e1;

import android.view.View;
import android.view.WindowId;

/* JADX INFO: compiled from: WindowIdApi18.java */
/* JADX INFO: loaded from: classes.dex */
class g0 implements h0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final WindowId f6114a;

    g0(View view) {
        this.f6114a = view.getWindowId();
    }

    public boolean equals(Object obj) {
        return (obj instanceof g0) && ((g0) obj).f6114a.equals(this.f6114a);
    }

    public int hashCode() {
        return this.f6114a.hashCode();
    }
}
