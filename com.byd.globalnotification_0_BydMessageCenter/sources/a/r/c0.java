package a.r;

import android.view.View;
import android.view.WindowId;

/* JADX INFO: loaded from: classes.dex */
public class c0 implements d0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final WindowId f1316a;

    public c0(View view) {
        this.f1316a = view.getWindowId();
    }

    public boolean equals(Object obj) {
        return (obj instanceof c0) && ((c0) obj).f1316a.equals(this.f1316a);
    }

    public int hashCode() {
        return this.f1316a.hashCode();
    }
}
