package defpackage;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/* JADX INFO: renamed from: Kk, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0190Kk extends WeakReference {
    public final int a;

    public C0190Kk(Throwable th, ReferenceQueue referenceQueue) {
        super(th, referenceQueue);
        if (th == null) {
            throw new NullPointerException("The referent cannot be null");
        }
        this.a = System.identityHashCode(th);
    }

    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != C0190Kk.class) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        C0190Kk c0190Kk = (C0190Kk) obj;
        return this.a == c0190Kk.a && get() == c0190Kk.get();
    }

    public int hashCode() {
        return this.a;
    }
}
