package g.a.b;

import java.security.SecureRandom;

/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public SecureRandom f4835a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f4836b;

    public byte[] a() {
        byte[] bArr = new byte[this.f4836b];
        this.f4835a.nextBytes(bArr);
        return bArr;
    }

    public void b(r rVar) {
        this.f4835a = rVar.a();
        this.f4836b = (rVar.b() + 7) / 8;
    }
}
