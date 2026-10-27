package s1;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import k2.k;
import l2.a;

/* JADX INFO: compiled from: SafeKeyGenerator.java */
/* JADX INFO: loaded from: classes.dex */
public class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final k2.g<n1.f, String> f9493a = new k2.g<>(1000);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final h0.e<b> f9494b = l2.a.d(10, new a());

    /* JADX INFO: compiled from: SafeKeyGenerator.java */
    class a implements a.d<b> {
        a() {
        }

        @Override // l2.a.d
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public b a() {
            try {
                return new b(MessageDigest.getInstance("SHA-256"));
            } catch (NoSuchAlgorithmException e7) {
                throw new RuntimeException(e7);
            }
        }
    }

    /* JADX INFO: compiled from: SafeKeyGenerator.java */
    private static final class b implements a.f {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final MessageDigest f9496e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private final l2.c f9497g = l2.c.a();

        b(MessageDigest messageDigest) {
            this.f9496e = messageDigest;
        }

        @Override // l2.a.f
        public l2.c d() {
            return this.f9497g;
        }
    }

    private String a(n1.f fVar) {
        b bVar = (b) k2.j.d(this.f9494b.b());
        try {
            fVar.a(bVar.f9496e);
            return k.t(bVar.f9496e.digest());
        } finally {
            this.f9494b.a(bVar);
        }
    }

    public String b(n1.f fVar) {
        String strG;
        synchronized (this.f9493a) {
            strG = this.f9493a.g(fVar);
        }
        if (strG == null) {
            strG = a(fVar);
        }
        synchronized (this.f9493a) {
            this.f9493a.k(fVar, strG);
        }
        return strG;
    }
}
