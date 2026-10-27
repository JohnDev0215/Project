package okhttp3.internal.http2;

import com.spotify.connectivity.httpwebgate.WebgateHelper;
import com.spotify.cosmos.cosmos.Request;
import com.spotify.webapi.service.models.Search;
import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import okhttp3.internal.Util;
import okhttp3.internal.http2.Http2Reader;
import okhttp3.internal.http2.Huffman;
import okio.ByteString;
import p.fo;
import p.rb;
import p.v03;
import p.w02;

/* JADX INFO: loaded from: classes.dex */
public final class Hpack {
    public static final Hpack a = new Hpack();
    public static final Header[] b;
    public static final Map c;

    public static final class Reader {
        public final w02 d;
        public int g;
        public int h;
        public final int a = 4096;
        public int b = 4096;
        public final ArrayList c = new ArrayList();
        public Header[] e = new Header[8];
        public int f = 7;

        public Reader(Http2Reader.ContinuationSource continuationSource) {
            this.d = v03.d(continuationSource);
        }

        public final int a(int i) {
            int i2;
            int i3 = 0;
            if (i > 0) {
                int length = this.e.length;
                while (true) {
                    length--;
                    i2 = this.f;
                    if (length < i2 || i <= 0) {
                        break;
                    }
                    Header header = this.e[length];
                    v03.h(header);
                    int i4 = header.c;
                    i -= i4;
                    this.h -= i4;
                    this.g--;
                    i3++;
                }
                Header[] headerArr = this.e;
                int i5 = i2 + 1;
                System.arraycopy(headerArr, i5, headerArr, i5 + i3, this.g);
                this.f += i3;
            }
            return i3;
        }

        public final ByteString b(int i) throws IOException {
            if (i >= 0) {
                Hpack hpack = Hpack.a;
                hpack.getClass();
                Header[] headerArr = Hpack.b;
                if (i <= headerArr.length - 1) {
                    hpack.getClass();
                    return headerArr[i].a;
                }
            }
            Hpack.a.getClass();
            int length = this.f + 1 + (i - Hpack.b.length);
            if (length >= 0) {
                Header[] headerArr2 = this.e;
                if (length < headerArr2.length) {
                    Header header = headerArr2[length];
                    v03.h(header);
                    return header.a;
                }
            }
            throw new IOException("Header index too large " + (i + 1));
        }

        public final void c(Header header) {
            this.c.add(header);
            int i = this.b;
            int i2 = header.c;
            if (i2 > i) {
                rb.o0(this.e, null);
                this.f = this.e.length - 1;
                this.g = 0;
                this.h = 0;
                return;
            }
            a((this.h + i2) - i);
            int i3 = this.g + 1;
            Header[] headerArr = this.e;
            if (i3 > headerArr.length) {
                Header[] headerArr2 = new Header[headerArr.length * 2];
                System.arraycopy(headerArr, 0, headerArr2, headerArr.length, headerArr.length);
                this.f = this.e.length - 1;
                this.e = headerArr2;
            }
            int i4 = this.f;
            this.f = i4 - 1;
            this.e[i4] = header;
            this.g++;
            this.h += i2;
        }

        public final ByteString d() {
            int i;
            w02 w02Var = this.d;
            byte b = w02Var.readByte();
            byte[] bArr = Util.a;
            int i2 = b & 255;
            int i3 = 0;
            boolean z = (b & 128) == 128;
            long jE = e(i2, 127);
            if (!z) {
                return w02Var.v(jE);
            }
            fo foVar = new fo();
            Huffman.a.getClass();
            v03.j(w02Var, "source");
            Huffman.Node node = Huffman.d;
            Huffman.Node node2 = node;
            int i4 = 0;
            for (long j = 0; j < jE; j++) {
                byte b2 = w02Var.readByte();
                byte[] bArr2 = Util.a;
                i3 = (i3 << 8) | (b2 & 255);
                i4 += 8;
                while (i4 >= 8) {
                    Huffman.Node[] nodeArr = node2.a;
                    v03.h(nodeArr);
                    node2 = nodeArr[(i3 >>> (i4 - 8)) & 255];
                    v03.h(node2);
                    if (node2.a == null) {
                        foVar.W0(node2.b);
                        i4 -= node2.c;
                        node2 = node;
                    } else {
                        i4 -= 8;
                    }
                }
            }
            while (i4 > 0) {
                Huffman.Node[] nodeArr2 = node2.a;
                v03.h(nodeArr2);
                Huffman.Node node3 = nodeArr2[(i3 << (8 - i4)) & 255];
                v03.h(node3);
                if (node3.a != null || (i = node3.c) > i4) {
                    break;
                }
                foVar.W0(node3.b);
                i4 -= i;
                node2 = node;
            }
            return foVar.v(foVar.e);
        }

        public final int e(int i, int i2) {
            int i3 = i & i2;
            if (i3 < i2) {
                return i3;
            }
            int i4 = 0;
            while (true) {
                byte b = this.d.readByte();
                byte[] bArr = Util.a;
                int i5 = b & 255;
                if ((b & 128) == 0) {
                    return i2 + (i5 << i4);
                }
                i2 += (b & 127) << i4;
                i4 += 7;
            }
        }
    }

    public static final class Writer {
        public final fo b;
        public boolean d;
        public int h;
        public int i;
        public final boolean a = true;
        public int c = Integer.MAX_VALUE;
        public int e = 4096;
        public Header[] f = new Header[8];
        public int g = 7;

        public Writer(fo foVar) {
            this.b = foVar;
        }

        public final void a(int i) {
            int i2;
            if (i > 0) {
                int length = this.f.length - 1;
                int i3 = 0;
                while (true) {
                    i2 = this.g;
                    if (length < i2 || i <= 0) {
                        break;
                    }
                    Header header = this.f[length];
                    v03.h(header);
                    i -= header.c;
                    int i4 = this.i;
                    Header header2 = this.f[length];
                    v03.h(header2);
                    this.i = i4 - header2.c;
                    this.h--;
                    i3++;
                    length--;
                }
                Header[] headerArr = this.f;
                int i5 = i2 + 1;
                System.arraycopy(headerArr, i5, headerArr, i5 + i3, this.h);
                Header[] headerArr2 = this.f;
                int i6 = this.g + 1;
                Arrays.fill(headerArr2, i6, i6 + i3, (Object) null);
                this.g += i3;
            }
        }

        public final void b(Header header) {
            int i = this.e;
            int i2 = header.c;
            if (i2 > i) {
                rb.o0(this.f, null);
                this.g = this.f.length - 1;
                this.h = 0;
                this.i = 0;
                return;
            }
            a((this.i + i2) - i);
            int i3 = this.h + 1;
            Header[] headerArr = this.f;
            if (i3 > headerArr.length) {
                Header[] headerArr2 = new Header[headerArr.length * 2];
                System.arraycopy(headerArr, 0, headerArr2, headerArr.length, headerArr.length);
                this.g = this.f.length - 1;
                this.f = headerArr2;
            }
            int i4 = this.g;
            this.g = i4 - 1;
            this.f[i4] = header;
            this.h++;
            this.i += i2;
        }

        public final void c(ByteString byteString) throws EOFException {
            v03.j(byteString, "data");
            boolean z = this.a;
            fo foVar = this.b;
            if (z) {
                Huffman.a.getClass();
                int iE = byteString.e();
                long j = 0;
                for (int i = 0; i < iE; i++) {
                    byte bK = byteString.k(i);
                    byte[] bArr = Util.a;
                    j += (long) Huffman.c[bK & 255];
                }
                if (((int) ((j + ((long) 7)) >> 3)) < byteString.e()) {
                    fo foVar2 = new fo();
                    Huffman.a.getClass();
                    int iE2 = byteString.e();
                    long j2 = 0;
                    int i2 = 0;
                    for (int i3 = 0; i3 < iE2; i3++) {
                        byte bK2 = byteString.k(i3);
                        byte[] bArr2 = Util.a;
                        int i4 = bK2 & 255;
                        int i5 = Huffman.b[i4];
                        byte b = Huffman.c[i4];
                        j2 = (j2 << b) | ((long) i5);
                        i2 += b;
                        while (i2 >= 8) {
                            i2 -= 8;
                            foVar2.W0((int) (j2 >> i2));
                        }
                    }
                    if (i2 > 0) {
                        foVar2.W0((int) ((255 >>> i2) | (j2 << (8 - i2))));
                    }
                    ByteString byteStringV = foVar2.v(foVar2.e);
                    e(byteStringV.e(), 127, 128);
                    foVar.T0(byteStringV);
                    return;
                }
            }
            e(byteString.e(), 127, 0);
            foVar.T0(byteString);
        }

        /* JADX WARN: Removed duplicated region for block: B:22:0x006e  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void d(java.util.ArrayList r14) throws java.io.EOFException {
            /*
                Method dump skipped, instruction units count: 269
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.Hpack.Writer.d(java.util.ArrayList):void");
        }

        public final void e(int i, int i2, int i3) {
            fo foVar = this.b;
            if (i < i2) {
                foVar.W0(i | i3);
                return;
            }
            foVar.W0(i3 | i2);
            int i4 = i - i2;
            while (i4 >= 128) {
                foVar.W0(128 | (i4 & 127));
                i4 >>>= 7;
            }
            foVar.W0(i4);
        }
    }

    static {
        Header header = new Header(Header.i, "");
        ByteString byteString = Header.f;
        Header header2 = new Header(byteString, Request.GET);
        Header header3 = new Header(byteString, Request.POST);
        ByteString byteString2 = Header.g;
        Header header4 = new Header(byteString2, "/");
        Header header5 = new Header(byteString2, "/index.html");
        ByteString byteString3 = Header.h;
        Header header6 = new Header(byteString3, "http");
        Header header7 = new Header(byteString3, WebgateHelper.DEFAULT_WEBGATE_PROTOCOL);
        ByteString byteString4 = Header.e;
        Header[] headerArr = {header, header2, header3, header4, header5, header6, header7, new Header(byteString4, "200"), new Header(byteString4, "204"), new Header(byteString4, "206"), new Header(byteString4, "304"), new Header(byteString4, "400"), new Header(byteString4, "404"), new Header(byteString4, "500"), new Header("accept-charset", ""), new Header("accept-encoding", "gzip, deflate"), new Header("accept-language", ""), new Header("accept-ranges", ""), new Header("accept", ""), new Header("access-control-allow-origin", ""), new Header("age", ""), new Header("allow", ""), new Header("authorization", ""), new Header("cache-control", ""), new Header("content-disposition", ""), new Header("content-encoding", ""), new Header("content-language", ""), new Header("content-length", ""), new Header("content-location", ""), new Header("content-range", ""), new Header("content-type", ""), new Header("cookie", ""), new Header("date", ""), new Header("etag", ""), new Header("expect", ""), new Header("expires", ""), new Header("from", ""), new Header("host", ""), new Header("if-match", ""), new Header("if-modified-since", ""), new Header("if-none-match", ""), new Header("if-range", ""), new Header("if-unmodified-since", ""), new Header("last-modified", ""), new Header(Search.Type.LINK, ""), new Header("location", ""), new Header("max-forwards", ""), new Header("proxy-authenticate", ""), new Header("proxy-authorization", ""), new Header("range", ""), new Header("referer", ""), new Header("refresh", ""), new Header("retry-after", ""), new Header("server", ""), new Header("set-cookie", ""), new Header("strict-transport-security", ""), new Header("transfer-encoding", ""), new Header("user-agent", ""), new Header("vary", ""), new Header("via", ""), new Header("www-authenticate", "")};
        b = headerArr;
        LinkedHashMap linkedHashMap = new LinkedHashMap(headerArr.length);
        int length = headerArr.length;
        for (int i = 0; i < length; i++) {
            if (!linkedHashMap.containsKey(headerArr[i].a)) {
                linkedHashMap.put(headerArr[i].a, Integer.valueOf(i));
            }
        }
        Map mapUnmodifiableMap = Collections.unmodifiableMap(linkedHashMap);
        v03.i(mapUnmodifiableMap, "unmodifiableMap(result)");
        c = mapUnmodifiableMap;
    }

    private Hpack() {
    }

    public static void a(ByteString byteString) {
        v03.j(byteString, "name");
        int iE = byteString.e();
        for (int i = 0; i < iE; i++) {
            byte bK = byteString.k(i);
            if (65 <= bK && bK < 91) {
                throw new IOException("PROTOCOL_ERROR response malformed: mixed case name: ".concat(byteString.s()));
            }
        }
    }
}
