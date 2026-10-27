package n1;

import java.security.MessageDigest;

/* JADX INFO: compiled from: Options.java */
/* JADX INFO: loaded from: classes.dex */
public final class i implements f {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final n.a<h<?>, Object> f8511b = new k2.b();

    /* JADX WARN: Multi-variable type inference failed */
    private static <T> void f(h<T> hVar, Object obj, MessageDigest messageDigest) {
        hVar.g(obj, messageDigest);
    }

    @Override // n1.f
    public void a(MessageDigest messageDigest) {
        for (int i7 = 0; i7 < this.f8511b.size(); i7++) {
            f(this.f8511b.k(i7), this.f8511b.o(i7), messageDigest);
        }
    }

    public <T> T c(h<T> hVar) {
        return this.f8511b.containsKey(hVar) ? (T) this.f8511b.get(hVar) : hVar.c();
    }

    public void d(i iVar) {
        this.f8511b.l(iVar.f8511b);
    }

    public <T> i e(h<T> hVar, T t6) {
        this.f8511b.put(hVar, t6);
        return this;
    }

    @Override // n1.f
    public boolean equals(Object obj) {
        if (obj instanceof i) {
            return this.f8511b.equals(((i) obj).f8511b);
        }
        return false;
    }

    @Override // n1.f
    public int hashCode() {
        return this.f8511b.hashCode();
    }

    public String toString() {
        return "Options{values=" + this.f8511b + '}';
    }
}
