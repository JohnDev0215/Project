package f.b0.i;

import okio.ByteString;

/* JADX INFO: compiled from: Header.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ByteString f5119a = ByteString.encodeUtf8(":");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final ByteString f5120b = ByteString.encodeUtf8(":status");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final ByteString f5121c = ByteString.encodeUtf8(":method");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final ByteString f5122d = ByteString.encodeUtf8(":path");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final ByteString f5123e = ByteString.encodeUtf8(":scheme");

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final ByteString f5124f = ByteString.encodeUtf8(":authority");

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final ByteString f5125g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final ByteString f5126h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f5127i;

    public a(String str, String str2) {
        this(ByteString.encodeUtf8(str), ByteString.encodeUtf8(str2));
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return this.f5125g.equals(aVar.f5125g) && this.f5126h.equals(aVar.f5126h);
    }

    public int hashCode() {
        return ((527 + this.f5125g.hashCode()) * 31) + this.f5126h.hashCode();
    }

    public String toString() {
        return f.b0.c.l("%s: %s", this.f5125g.utf8(), this.f5126h.utf8());
    }

    public a(ByteString byteString, String str) {
        this(byteString, ByteString.encodeUtf8(str));
    }

    public a(ByteString byteString, ByteString byteString2) {
        this.f5125g = byteString;
        this.f5126h = byteString2;
        this.f5127i = byteString.size() + 32 + byteString2.size();
    }
}
