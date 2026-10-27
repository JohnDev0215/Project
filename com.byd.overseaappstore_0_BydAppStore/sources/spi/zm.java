package spi;

import androidx.annotation.NonNull;
import java.nio.ByteBuffer;
import java.security.MessageDigest;

/* JADX INFO: loaded from: classes.dex */
public final class zm implements ig {
    public static final gh<Class<?>, byte[]> j = new gh<>(50);
    public final m0 b;
    public final ig c;
    public final ig d;
    public final int e;
    public final int f;
    public final Class<?> g;
    public final lk h;
    public final qt<?> i;

    public zm(m0 m0Var, ig igVar, ig igVar2, int i, int i2, qt<?> qtVar, Class<?> cls, lk lkVar) {
        this.b = m0Var;
        this.c = igVar;
        this.d = igVar2;
        this.e = i;
        this.f = i2;
        this.i = qtVar;
        this.g = cls;
        this.h = lkVar;
    }

    @Override // spi.ig
    public final void a(@NonNull MessageDigest messageDigest) {
        byte[] bArr = (byte[]) this.b.e();
        ByteBuffer.wrap(bArr).putInt(this.e).putInt(this.f).array();
        this.d.a(messageDigest);
        this.c.a(messageDigest);
        messageDigest.update(bArr);
        qt<?> qtVar = this.i;
        if (qtVar != null) {
            qtVar.a(messageDigest);
        }
        this.h.a(messageDigest);
        gh<Class<?>, byte[]> ghVar = j;
        byte[] bArrF = ghVar.f(this.g);
        if (bArrF == null) {
            bArrF = this.g.getName().getBytes(ig.a);
            ghVar.i(this.g, bArrF);
        }
        messageDigest.update(bArrF);
        this.b.c(bArr);
    }

    @Override // spi.ig
    public final boolean equals(Object obj) {
        if (!(obj instanceof zm)) {
            return false;
        }
        zm zmVar = (zm) obj;
        return this.f == zmVar.f && this.e == zmVar.e && tu.b(this.i, zmVar.i) && this.g.equals(zmVar.g) && this.c.equals(zmVar.c) && this.d.equals(zmVar.d) && this.h.equals(zmVar.h);
    }

    @Override // spi.ig
    public final int hashCode() {
        int iHashCode = ((((this.d.hashCode() + (this.c.hashCode() * 31)) * 31) + this.e) * 31) + this.f;
        qt<?> qtVar = this.i;
        if (qtVar != null) {
            iHashCode = (iHashCode * 31) + qtVar.hashCode();
        }
        return this.h.hashCode() + ((this.g.hashCode() + (iHashCode * 31)) * 31);
    }

    public final String toString() {
        StringBuilder sbK = e.k("ResourceCacheKey{sourceKey=");
        sbK.append(this.c);
        sbK.append(", signature=");
        sbK.append(this.d);
        sbK.append(", width=");
        sbK.append(this.e);
        sbK.append(", height=");
        sbK.append(this.f);
        sbK.append(", decodedResourceClass=");
        sbK.append(this.g);
        sbK.append(", transformation='");
        sbK.append(this.i);
        sbK.append('\'');
        sbK.append(", options=");
        sbK.append(this.h);
        sbK.append('}');
        return sbK.toString();
    }
}
