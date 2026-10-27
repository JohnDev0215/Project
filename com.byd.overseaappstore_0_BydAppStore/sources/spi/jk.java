package spi;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.security.MessageDigest;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class jk<T> {
    public static final b<Object> e = new a();
    public final T a;
    public final b<T> b;
    public final String c;
    public volatile byte[] d;

    public class a implements b<Object> {
        @Override // spi.jk.b
        public final void a(@NonNull byte[] bArr, @NonNull Object obj, @NonNull MessageDigest messageDigest) {
        }
    }

    public interface b<T> {
        void a(@NonNull byte[] bArr, @NonNull T t, @NonNull MessageDigest messageDigest);
    }

    private jk(@NonNull String str, @Nullable T t, @NonNull b<T> bVar) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Must not be null or empty");
        }
        this.c = str;
        this.a = t;
        Objects.requireNonNull(bVar, "Argument must not be null");
        this.b = bVar;
    }

    @NonNull
    public static <T> jk<T> a(@NonNull String str, @Nullable T t, @NonNull b<T> bVar) {
        return new jk<>(str, t, bVar);
    }

    @NonNull
    public static <T> jk<T> b(@NonNull String str) {
        return new jk<>(str, null, e);
    }

    @NonNull
    public static <T> jk<T> c(@NonNull String str, @NonNull T t) {
        return new jk<>(str, t, e);
    }

    public final boolean equals(Object obj) {
        if (obj instanceof jk) {
            return this.c.equals(((jk) obj).c);
        }
        return false;
    }

    public final int hashCode() {
        return this.c.hashCode();
    }

    public final String toString() {
        StringBuilder sbK = e.k("Option{key='");
        sbK.append(this.c);
        sbK.append('\'');
        sbK.append('}');
        return sbK.toString();
    }
}
