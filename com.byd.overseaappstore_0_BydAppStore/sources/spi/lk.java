package spi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.ArrayMap;
import androidx.collection.SimpleArrayMap;
import com.bumptech.glide.util.CachedHashCodeArrayMap;
import java.security.MessageDigest;
import spi.jk;

/* JADX INFO: loaded from: classes.dex */
public final class lk implements ig {
    public final ArrayMap<jk<?>, Object> b = new CachedHashCodeArrayMap();

    @Override // spi.ig
    public final void a(@NonNull MessageDigest messageDigest) {
        for (int i = 0; i < this.b.size(); i++) {
            jk<?> jkVarKeyAt = this.b.keyAt(i);
            Object objValueAt = this.b.valueAt(i);
            jk.b<?> bVar = jkVarKeyAt.b;
            if (jkVarKeyAt.d == null) {
                jkVarKeyAt.d = jkVarKeyAt.c.getBytes(ig.a);
            }
            bVar.a(jkVarKeyAt.d, objValueAt, messageDigest);
        }
    }

    @Nullable
    public final <T> T c(@NonNull jk<T> jkVar) {
        return this.b.containsKey(jkVar) ? (T) this.b.get(jkVar) : jkVar.a;
    }

    public final void d(@NonNull lk lkVar) {
        this.b.putAll((SimpleArrayMap<? extends jk<?>, ? extends Object>) lkVar.b);
    }

    @Override // spi.ig
    public final boolean equals(Object obj) {
        if (obj instanceof lk) {
            return this.b.equals(((lk) obj).b);
        }
        return false;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.collection.ArrayMap<spi.jk<?>, java.lang.Object>, com.bumptech.glide.util.CachedHashCodeArrayMap] */
    @Override // spi.ig
    public final int hashCode() {
        return this.b.hashCode();
    }

    public final String toString() {
        StringBuilder sbK = e.k("Options{values=");
        sbK.append(this.b);
        sbK.append('}');
        return sbK.toString();
    }
}
