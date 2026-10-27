package p;

import coil.decode.ExifOrientationPolicy;

/* JADX INFO: loaded from: classes.dex */
public final class am {
    public final ExifOrientationPolicy a;
    public final kotlinx.coroutines.sync.a b;

    public am(int i, ExifOrientationPolicy exifOrientationPolicy) {
        this.a = exifOrientationPolicy;
        int i2 = fb2.a;
        this.b = new kotlinx.coroutines.sync.a(i, 0);
    }

    public final boolean equals(Object obj) {
        return obj instanceof am;
    }

    public final int hashCode() {
        return am.class.hashCode();
    }
}
