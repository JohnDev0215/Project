package w0;

import A7.m;
import android.view.KeyEvent;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final KeyEvent f23165a;

    public final boolean equals(Object obj) {
        if (obj instanceof b) {
            return m.a(this.f23165a, ((b) obj).f23165a);
        }
        return false;
    }

    public final int hashCode() {
        return this.f23165a.hashCode();
    }

    public final String toString() {
        return "KeyEvent(nativeKeyEvent=" + this.f23165a + ')';
    }
}
