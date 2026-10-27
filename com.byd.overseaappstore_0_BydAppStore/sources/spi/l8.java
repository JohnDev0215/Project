package spi;

import androidx.annotation.NonNull;
import java.security.MessageDigest;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class l8 implements ig {
    public final Object b;
    public final int c;
    public final int d;
    public final Class<?> e;
    public final Class<?> f;
    public final ig g;
    public final Map<Class<?>, qt<?>> h;
    public final lk i;
    public int j;

    public l8(Object obj, ig igVar, int i, int i2, Map<Class<?>, qt<?>> map, Class<?> cls, Class<?> cls2, lk lkVar) {
        Objects.requireNonNull(obj, "Argument must not be null");
        this.b = obj;
        Objects.requireNonNull(igVar, "Signature must not be null");
        this.g = igVar;
        this.c = i;
        this.d = i2;
        Objects.requireNonNull(map, "Argument must not be null");
        this.h = map;
        Objects.requireNonNull(cls, "Resource class must not be null");
        this.e = cls;
        Objects.requireNonNull(cls2, "Transcode class must not be null");
        this.f = cls2;
        Objects.requireNonNull(lkVar, "Argument must not be null");
        this.i = lkVar;
    }

    @Override // spi.ig
    public final void a(@NonNull MessageDigest messageDigest) {
        throw new UnsupportedOperationException();
    }

    @Override // spi.ig
    public final boolean equals(Object obj) {
        if (!(obj instanceof l8)) {
            return false;
        }
        l8 l8Var = (l8) obj;
        return this.b.equals(l8Var.b) && this.g.equals(l8Var.g) && this.d == l8Var.d && this.c == l8Var.c && this.h.equals(l8Var.h) && this.e.equals(l8Var.e) && this.f.equals(l8Var.f) && this.i.equals(l8Var.i);
    }

    @Override // spi.ig
    public final int hashCode() {
        if (this.j == 0) {
            int iHashCode = this.b.hashCode();
            this.j = iHashCode;
            int iHashCode2 = ((((this.g.hashCode() + (iHashCode * 31)) * 31) + this.c) * 31) + this.d;
            this.j = iHashCode2;
            int iHashCode3 = this.h.hashCode() + (iHashCode2 * 31);
            this.j = iHashCode3;
            int iHashCode4 = this.e.hashCode() + (iHashCode3 * 31);
            this.j = iHashCode4;
            int iHashCode5 = this.f.hashCode() + (iHashCode4 * 31);
            this.j = iHashCode5;
            this.j = this.i.hashCode() + (iHashCode5 * 31);
        }
        return this.j;
    }

    public final String toString() {
        StringBuilder sbK = e.k("EngineKey{model=");
        sbK.append(this.b);
        sbK.append(", width=");
        sbK.append(this.c);
        sbK.append(", height=");
        sbK.append(this.d);
        sbK.append(", resourceClass=");
        sbK.append(this.e);
        sbK.append(", transcodeClass=");
        sbK.append(this.f);
        sbK.append(", signature=");
        sbK.append(this.g);
        sbK.append(", hashCode=");
        sbK.append(this.j);
        sbK.append(", transformations=");
        sbK.append(this.h);
        sbK.append(", options=");
        sbK.append(this.i);
        sbK.append('}');
        return sbK.toString();
    }
}
