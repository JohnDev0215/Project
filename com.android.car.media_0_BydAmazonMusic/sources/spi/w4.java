package spi;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Size;
import java.util.Objects;

/* JADX INFO: compiled from: ImageKey.java */
/* JADX INFO: loaded from: classes.dex */
public final class w4 {
    public final Uri a;
    public final Size b;

    public w4(Uri uri, Size size) {
        if (uri == null || TextUtils.isEmpty(uri.toString())) {
            throw new IllegalArgumentException("Empty uri!");
        }
        this.a = uri;
        this.b = size;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || w4.class != obj.getClass()) {
            return false;
        }
        w4 w4Var = (w4) obj;
        return this.a.equals(w4Var.a) && this.b.equals(w4Var.b);
    }

    public final int hashCode() {
        return Objects.hash(this.a, this.b);
    }

    public final String toString() {
        StringBuilder sbK = c.k("ImageKey{mImageUri=");
        sbK.append(this.a);
        sbK.append(", mMaxImageSize=");
        sbK.append(this.b);
        sbK.append('}');
        return sbK.toString();
    }
}
