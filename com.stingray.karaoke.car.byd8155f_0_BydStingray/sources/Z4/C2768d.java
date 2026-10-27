package z4;

import A4.h;
import e4.e;
import java.security.MessageDigest;

/* JADX INFO: renamed from: z4.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2768d implements e {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f24406b;

    public C2768d(Object obj) {
        h.c(obj, "Argument must not be null");
        this.f24406b = obj;
    }

    @Override // e4.e
    public final void b(MessageDigest messageDigest) {
        messageDigest.update(this.f24406b.toString().getBytes(e.f14430a));
    }

    @Override // e4.e
    public final boolean equals(Object obj) {
        if (obj instanceof C2768d) {
            return this.f24406b.equals(((C2768d) obj).f24406b);
        }
        return false;
    }

    @Override // e4.e
    public final int hashCode() {
        return this.f24406b.hashCode();
    }

    public final String toString() {
        return "ObjectKey{object=" + this.f24406b + '}';
    }
}
