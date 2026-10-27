package f.k0.k;

import okio.ByteString;

/* JADX INFO: compiled from: Header.java */
/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ByteString f5281a = ByteString.i(":");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final ByteString f5282b = ByteString.i(":status");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final ByteString f5283c = ByteString.i(":method");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final ByteString f5284d = ByteString.i(":path");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final ByteString f5285e = ByteString.i(":scheme");

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final ByteString f5286f = ByteString.i(":authority");

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final ByteString f5287g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final ByteString f5288h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    final int f5289i;

    public c(String str, String str2) {
        this(ByteString.i(str), ByteString.i(str2));
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        return this.f5287g.equals(cVar.f5287g) && this.f5288h.equals(cVar.f5288h);
    }

    public int hashCode() {
        return ((527 + this.f5287g.hashCode()) * 31) + this.f5288h.hashCode();
    }

    public String toString() {
        return f.k0.e.p("%s: %s", this.f5287g.x(), this.f5288h.x());
    }

    public c(ByteString byteString, String str) {
        this(byteString, ByteString.i(str));
    }

    public c(ByteString byteString, ByteString byteString2) {
        this.f5287g = byteString;
        this.f5288h = byteString2;
        this.f5289i = byteString.s() + 32 + byteString2.s();
    }
}
