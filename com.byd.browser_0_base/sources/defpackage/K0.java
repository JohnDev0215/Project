package defpackage;

import java.io.Serializable;

/* JADX INFO: compiled from: chromium-webapk8.dex-default-8 */
/* JADX INFO: loaded from: assets/webapk8.dex */
public final class K0 implements Serializable {
    public final Throwable c;

    public K0(Throwable th) {
        this.c = th;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof K0) {
            if (C0186g0.a(this.c, ((K0) obj).c)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.c.hashCode();
    }

    public final String toString() {
        return "Failure(" + this.c + ')';
    }
}
