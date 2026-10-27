package e.d0.i;

import okio.ByteString;

/* JADX INFO: compiled from: Header.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ByteString f3427a = ByteString.encodeUtf8(":");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final ByteString f3428b = ByteString.encodeUtf8(":status");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final ByteString f3429c = ByteString.encodeUtf8(":method");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final ByteString f3430d = ByteString.encodeUtf8(":path");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final ByteString f3431e = ByteString.encodeUtf8(":scheme");

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final ByteString f3432f = ByteString.encodeUtf8(":authority");

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final ByteString f3433g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final ByteString f3434h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f3435i;

    public a(String str, String str2) {
        this(ByteString.encodeUtf8(str), ByteString.encodeUtf8(str2));
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return this.f3433g.equals(aVar.f3433g) && this.f3434h.equals(aVar.f3434h);
    }

    public int hashCode() {
        return ((527 + this.f3433g.hashCode()) * 31) + this.f3434h.hashCode();
    }

    public String toString() {
        return e.d0.c.l("%s: %s", this.f3433g.utf8(), this.f3434h.utf8());
    }

    public a(ByteString byteString, String str) {
        this(byteString, ByteString.encodeUtf8(str));
    }

    public a(ByteString byteString, ByteString byteString2) {
        this.f3433g = byteString;
        this.f3434h = byteString2;
        this.f3435i = byteString.size() + 32 + byteString2.size();
    }
}
