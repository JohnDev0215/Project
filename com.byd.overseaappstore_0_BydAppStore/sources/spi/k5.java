package spi;

import androidx.annotation.NonNull;
import java.security.MessageDigest;

/* JADX INFO: loaded from: classes.dex */
public final class k5 implements ig {
    public final ig b;
    public final ig c;

    public k5(ig igVar, ig igVar2) {
        this.b = igVar;
        this.c = igVar2;
    }

    @Override // spi.ig
    public final void a(@NonNull MessageDigest messageDigest) {
        this.b.a(messageDigest);
        this.c.a(messageDigest);
    }

    @Override // spi.ig
    public final boolean equals(Object obj) {
        if (!(obj instanceof k5)) {
            return false;
        }
        k5 k5Var = (k5) obj;
        return this.b.equals(k5Var.b) && this.c.equals(k5Var.c);
    }

    @Override // spi.ig
    public final int hashCode() {
        return this.c.hashCode() + (this.b.hashCode() * 31);
    }

    public final String toString() {
        StringBuilder sbK = e.k("DataCacheKey{sourceKey=");
        sbK.append(this.b);
        sbK.append(", signature=");
        sbK.append(this.c);
        sbK.append('}');
        return sbK.toString();
    }
}
