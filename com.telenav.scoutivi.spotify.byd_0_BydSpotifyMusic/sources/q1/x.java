package q1;

import java.nio.ByteBuffer;
import java.security.MessageDigest;

/* JADX INFO: compiled from: ResourceCacheKey.java */
/* JADX INFO: loaded from: classes.dex */
final class x implements n1.f {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private static final k2.g<Class<?>, byte[]> f9126j = new k2.g<>(50);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final r1.b f9127b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final n1.f f9128c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final n1.f f9129d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final int f9130e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final int f9131f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final Class<?> f9132g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final n1.i f9133h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final n1.m<?> f9134i;

    x(r1.b bVar, n1.f fVar, n1.f fVar2, int i7, int i8, n1.m<?> mVar, Class<?> cls, n1.i iVar) {
        this.f9127b = bVar;
        this.f9128c = fVar;
        this.f9129d = fVar2;
        this.f9130e = i7;
        this.f9131f = i8;
        this.f9134i = mVar;
        this.f9132g = cls;
        this.f9133h = iVar;
    }

    private byte[] c() {
        k2.g<Class<?>, byte[]> gVar = f9126j;
        byte[] bArrG = gVar.g(this.f9132g);
        if (bArrG != null) {
            return bArrG;
        }
        byte[] bytes = this.f9132g.getName().getBytes(n1.f.f8504a);
        gVar.k(this.f9132g, bytes);
        return bytes;
    }

    @Override // n1.f
    public void a(MessageDigest messageDigest) {
        byte[] bArr = (byte[]) this.f9127b.c(8, byte[].class);
        ByteBuffer.wrap(bArr).putInt(this.f9130e).putInt(this.f9131f).array();
        this.f9129d.a(messageDigest);
        this.f9128c.a(messageDigest);
        messageDigest.update(bArr);
        n1.m<?> mVar = this.f9134i;
        if (mVar != null) {
            mVar.a(messageDigest);
        }
        this.f9133h.a(messageDigest);
        messageDigest.update(c());
        this.f9127b.d(bArr);
    }

    @Override // n1.f
    public boolean equals(Object obj) {
        if (!(obj instanceof x)) {
            return false;
        }
        x xVar = (x) obj;
        return this.f9131f == xVar.f9131f && this.f9130e == xVar.f9130e && k2.k.c(this.f9134i, xVar.f9134i) && this.f9132g.equals(xVar.f9132g) && this.f9128c.equals(xVar.f9128c) && this.f9129d.equals(xVar.f9129d) && this.f9133h.equals(xVar.f9133h);
    }

    @Override // n1.f
    public int hashCode() {
        int iHashCode = (((((this.f9128c.hashCode() * 31) + this.f9129d.hashCode()) * 31) + this.f9130e) * 31) + this.f9131f;
        n1.m<?> mVar = this.f9134i;
        if (mVar != null) {
            iHashCode = (iHashCode * 31) + mVar.hashCode();
        }
        return (((iHashCode * 31) + this.f9132g.hashCode()) * 31) + this.f9133h.hashCode();
    }

    public String toString() {
        return "ResourceCacheKey{sourceKey=" + this.f9128c + ", signature=" + this.f9129d + ", width=" + this.f9130e + ", height=" + this.f9131f + ", decodedResourceClass=" + this.f9132g + ", transformation='" + this.f9134i + "', options=" + this.f9133h + '}';
    }
}
