package b.q;

import android.view.View;
import android.view.WindowId;

/* JADX INFO: compiled from: WindowIdApi18.java */
/* JADX INFO: loaded from: classes.dex */
public class k0 implements l0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final WindowId f1488a;

    public k0(View view) {
        this.f1488a = view.getWindowId();
    }

    public boolean equals(Object obj) {
        return (obj instanceof k0) && ((k0) obj).f1488a.equals(this.f1488a);
    }

    public int hashCode() {
        return this.f1488a.hashCode();
    }
}
