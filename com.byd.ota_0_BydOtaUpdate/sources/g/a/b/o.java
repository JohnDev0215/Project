package g.a.b;

/* JADX INFO: loaded from: classes.dex */
public class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public b f5191a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public q f5192b;

    public o(b bVar, q qVar) {
        this.f5191a = bVar;
        this.f5192b = qVar;
    }

    public byte[] a() {
        return this.f5192b.getEncoded(this.f5191a.b());
    }

    public b b() {
        return this.f5191a;
    }
}
