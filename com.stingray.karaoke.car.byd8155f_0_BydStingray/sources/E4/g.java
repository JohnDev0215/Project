package e4;

import android.text.TextUtils;

/* JADX INFO: loaded from: classes.dex */
public final class g {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final C8.d f14431e = new C8.d(19);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f14432a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final f f14433b;
    public final String c;
    public volatile byte[] d;

    public g(String str, Object obj, f fVar) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Must not be null or empty");
        }
        this.c = str;
        this.f14432a = obj;
        this.f14433b = fVar;
    }

    public static g a(Object obj, String str) {
        return new g(str, obj, f14431e);
    }

    public final boolean equals(Object obj) {
        if (obj instanceof g) {
            return this.c.equals(((g) obj).c);
        }
        return false;
    }

    public final int hashCode() {
        return this.c.hashCode();
    }

    public final String toString() {
        return android.hardware.bydauto.ac.tsimpl.a.l(new StringBuilder("Option{key='"), this.c, "'}");
    }
}
