package spi;

import androidx.annotation.NonNull;
import java.security.MessageDigest;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class wj implements ig {
    public final Object b;

    public wj(@NonNull Object obj) {
        Objects.requireNonNull(obj, "Argument must not be null");
        this.b = obj;
    }

    @Override // spi.ig
    public final void a(@NonNull MessageDigest messageDigest) {
        messageDigest.update(this.b.toString().getBytes(ig.a));
    }

    @Override // spi.ig
    public final boolean equals(Object obj) {
        if (obj instanceof wj) {
            return this.b.equals(((wj) obj).b);
        }
        return false;
    }

    @Override // spi.ig
    public final int hashCode() {
        return this.b.hashCode();
    }

    public final String toString() {
        StringBuilder sbK = e.k("ObjectKey{object=");
        sbK.append(this.b);
        sbK.append('}');
        return sbK.toString();
    }
}
