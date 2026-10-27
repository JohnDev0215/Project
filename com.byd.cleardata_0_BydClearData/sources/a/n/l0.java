package a.n;

import android.view.View;
import android.view.WindowId;

/* JADX INFO: loaded from: classes.dex */
class l0 implements m0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final WindowId f566a;

    l0(View view) {
        this.f566a = view.getWindowId();
    }

    public boolean equals(Object obj) {
        return (obj instanceof l0) && ((l0) obj).f566a.equals(this.f566a);
    }

    public int hashCode() {
        return this.f566a.hashCode();
    }
}
