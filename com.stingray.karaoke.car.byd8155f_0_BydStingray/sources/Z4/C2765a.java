package z4;

import A4.q;
import e4.e;
import java.nio.ByteBuffer;
import java.security.MessageDigest;

/* JADX INFO: renamed from: z4.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2765a implements e {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f24403b;
    public final e c;

    public C2765a(int i7, e eVar) {
        this.f24403b = i7;
        this.c = eVar;
    }

    @Override // e4.e
    public final void b(MessageDigest messageDigest) {
        this.c.b(messageDigest);
        messageDigest.update(ByteBuffer.allocate(4).putInt(this.f24403b).array());
    }

    @Override // e4.e
    public final boolean equals(Object obj) {
        if (!(obj instanceof C2765a)) {
            return false;
        }
        C2765a c2765a = (C2765a) obj;
        return this.f24403b == c2765a.f24403b && this.c.equals(c2765a.c);
    }

    @Override // e4.e
    public final int hashCode() {
        return q.h(this.f24403b, this.c);
    }
}
