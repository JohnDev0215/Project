package j2;

import android.content.Context;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import k2.k;
import n1.f;

/* JADX INFO: compiled from: AndroidResourceSignature.java */
/* JADX INFO: loaded from: classes.dex */
public final class a implements f {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f7145b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final f f7146c;

    private a(int i7, f fVar) {
        this.f7145b = i7;
        this.f7146c = fVar;
    }

    public static f c(Context context) {
        return new a(context.getResources().getConfiguration().uiMode & 48, b.c(context));
    }

    @Override // n1.f
    public void a(MessageDigest messageDigest) {
        this.f7146c.a(messageDigest);
        messageDigest.update(ByteBuffer.allocate(4).putInt(this.f7145b).array());
    }

    @Override // n1.f
    public boolean equals(Object obj) {
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return this.f7145b == aVar.f7145b && this.f7146c.equals(aVar.f7146c);
    }

    @Override // n1.f
    public int hashCode() {
        return k.n(this.f7146c, this.f7145b);
    }
}
