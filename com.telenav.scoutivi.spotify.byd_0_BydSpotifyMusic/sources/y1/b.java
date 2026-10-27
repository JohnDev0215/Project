package y1;

import k2.j;
import q1.v;

/* JADX INFO: compiled from: BytesResource.java */
/* JADX INFO: loaded from: classes.dex */
public class b implements v<byte[]> {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final byte[] f9960e;

    public b(byte[] bArr) {
        this.f9960e = (byte[]) j.d(bArr);
    }

    @Override // q1.v
    public void a() {
    }

    @Override // q1.v
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public byte[] get() {
        return this.f9960e;
    }

    @Override // q1.v
    public int c() {
        return this.f9960e.length;
    }

    @Override // q1.v
    public Class<byte[]> e() {
        return byte[].class;
    }
}
