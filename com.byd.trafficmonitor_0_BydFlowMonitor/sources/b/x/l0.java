package b.x;

import android.view.View;
import android.view.WindowId;

/* JADX INFO: compiled from: WindowIdApi18.java */
/* JADX INFO: loaded from: classes.dex */
public class l0 implements m0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final WindowId f3285a;

    public l0(View view) {
        this.f3285a = view.getWindowId();
    }

    public boolean equals(Object obj) {
        return (obj instanceof l0) && ((l0) obj).f3285a.equals(this.f3285a);
    }

    public int hashCode() {
        return this.f3285a.hashCode();
    }
}
