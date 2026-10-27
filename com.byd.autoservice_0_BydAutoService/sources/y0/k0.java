package y0;

import android.view.View;
import android.view.WindowId;

/* JADX INFO: compiled from: WindowIdApi18.java */
/* JADX INFO: loaded from: classes.dex */
public class k0 implements l0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final WindowId f5933a;

    public k0(View view) {
        this.f5933a = view.getWindowId();
    }

    public boolean equals(Object obj) {
        return (obj instanceof k0) && ((k0) obj).f5933a.equals(this.f5933a);
    }

    public int hashCode() {
        return this.f5933a.hashCode();
    }
}
