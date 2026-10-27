package spi;

import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class sd implements ig {
    public final je b;

    @Nullable
    public final URL c;

    @Nullable
    public final String d;

    @Nullable
    public String e;

    @Nullable
    public URL f;

    @Nullable
    public volatile byte[] g;
    public int h;

    public sd(String str) {
        this(str, je.a);
    }

    public sd(String str, je jeVar) {
        this.c = null;
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Must not be null or empty");
        }
        this.d = str;
        Objects.requireNonNull(jeVar, "Argument must not be null");
        this.b = jeVar;
    }

    public sd(URL url) {
        this(url, je.a);
    }

    @Override // spi.ig
    public final void a(@NonNull MessageDigest messageDigest) {
        if (this.g == null) {
            this.g = c().getBytes(ig.a);
        }
        messageDigest.update(this.g);
    }

    public final String c() {
        String str = this.d;
        if (str != null) {
            return str;
        }
        URL url = this.c;
        Objects.requireNonNull(url, "Argument must not be null");
        return url.toString();
    }

    public final String d() {
        if (TextUtils.isEmpty(this.e)) {
            String string = this.d;
            if (TextUtils.isEmpty(string)) {
                URL url = this.c;
                Objects.requireNonNull(url, "Argument must not be null");
                string = url.toString();
            }
            this.e = Uri.encode(string, "@#&=*+-_.,:!?()/~'%;$");
        }
        return this.e;
    }

    @Override // spi.ig
    public final boolean equals(Object obj) {
        if (!(obj instanceof sd)) {
            return false;
        }
        sd sdVar = (sd) obj;
        return c().equals(sdVar.c()) && this.b.equals(sdVar.b);
    }

    @Override // spi.ig
    public final int hashCode() {
        if (this.h == 0) {
            int iHashCode = c().hashCode();
            this.h = iHashCode;
            this.h = this.b.hashCode() + (iHashCode * 31);
        }
        return this.h;
    }

    public final String toString() {
        return c();
    }

    public sd(URL url, je jeVar) {
        Objects.requireNonNull(url, "Argument must not be null");
        this.c = url;
        this.d = null;
        Objects.requireNonNull(jeVar, "Argument must not be null");
        this.b = jeVar;
    }
}
