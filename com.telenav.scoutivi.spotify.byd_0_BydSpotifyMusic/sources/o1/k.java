package o1;

import java.io.InputStream;
import o1.e;
import x1.u;

/* JADX INFO: compiled from: InputStreamRewinder.java */
/* JADX INFO: loaded from: classes.dex */
public final class k implements e<InputStream> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final u f8662a;

    /* JADX INFO: compiled from: InputStreamRewinder.java */
    public static final class a implements e.a<InputStream> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final r1.b f8663a;

        public a(r1.b bVar) {
            this.f8663a = bVar;
        }

        @Override // o1.e.a
        public Class<InputStream> a() {
            return InputStream.class;
        }

        @Override // o1.e.a
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public e<InputStream> b(InputStream inputStream) {
            return new k(inputStream, this.f8663a);
        }
    }

    k(InputStream inputStream, r1.b bVar) {
        u uVar = new u(inputStream, bVar);
        this.f8662a = uVar;
        uVar.mark(5242880);
    }

    @Override // o1.e
    public void b() {
        this.f8662a.A();
    }

    @Override // o1.e
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public InputStream a() {
        this.f8662a.reset();
        return this.f8662a;
    }
}
