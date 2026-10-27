package okhttp3.internal.http2;

import okio.ByteString;
import p.qo;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class Header {
    public static final ByteString d;
    public static final ByteString e;
    public static final ByteString f;
    public static final ByteString g;
    public static final ByteString h;
    public static final ByteString i;
    public final ByteString a;
    public final ByteString b;
    public final int c;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(int i) {
            this();
        }
    }

    static {
        new Companion(0);
        ByteString byteString = ByteString.k;
        d = qo.p(":");
        e = qo.p(":status");
        f = qo.p(":method");
        g = qo.p(":path");
        h = qo.p(":scheme");
        i = qo.p(":authority");
    }

    public Header(ByteString byteString, ByteString byteString2) {
        v03.j(byteString, "name");
        v03.j(byteString2, "value");
        this.a = byteString;
        this.b = byteString2;
        this.c = byteString2.e() + byteString.e() + 32;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Header)) {
            return false;
        }
        Header header = (Header) obj;
        return v03.b(this.a, header.a) && v03.b(this.b, header.b);
    }

    public final int hashCode() {
        return this.b.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return this.a.s() + ": " + this.b.s();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public Header(String str, String str2) {
        this(qo.p(str), qo.p(str2));
        v03.j(str, "name");
        v03.j(str2, "value");
        ByteString byteString = ByteString.k;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public Header(ByteString byteString, String str) {
        this(byteString, qo.p(str));
        v03.j(byteString, "name");
        v03.j(str, "value");
        ByteString byteString2 = ByteString.k;
    }
}
