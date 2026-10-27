package j2;

import java.security.MessageDigest;
import k2.j;
import n1.f;

/* JADX INFO: compiled from: ObjectKey.java */
/* JADX INFO: loaded from: classes.dex */
public final class d implements f {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Object f7149b;

    public d(Object obj) {
        this.f7149b = j.d(obj);
    }

    @Override // n1.f
    public void a(MessageDigest messageDigest) {
        messageDigest.update(this.f7149b.toString().getBytes(f.f8504a));
    }

    @Override // n1.f
    public boolean equals(Object obj) {
        if (obj instanceof d) {
            return this.f7149b.equals(((d) obj).f7149b);
        }
        return false;
    }

    @Override // n1.f
    public int hashCode() {
        return this.f7149b.hashCode();
    }

    public String toString() {
        return "ObjectKey{object=" + this.f7149b + '}';
    }
}
