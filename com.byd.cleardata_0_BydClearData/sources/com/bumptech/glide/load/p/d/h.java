package com.bumptech.glide.load.p.d;

import android.util.Log;
import com.bumptech.glide.load.ImageHeaderParser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes.dex */
public final class h implements ImageHeaderParser {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final byte[] f2326a = "Exif\u0000\u0000".getBytes(Charset.forName("UTF-8"));

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final int[] f2327b = {0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 8, 4, 8};

    private static final class a implements c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final ByteBuffer f2328a;

        a(ByteBuffer byteBuffer) {
            this.f2328a = byteBuffer;
            byteBuffer.order(ByteOrder.BIG_ENDIAN);
        }

        @Override // com.bumptech.glide.load.p.d.h.c
        public int a() {
            return (d() << 8) | d();
        }

        @Override // com.bumptech.glide.load.p.d.h.c
        public long b(long j) {
            int iMin = (int) Math.min(this.f2328a.remaining(), j);
            ByteBuffer byteBuffer = this.f2328a;
            byteBuffer.position(byteBuffer.position() + iMin);
            return iMin;
        }

        @Override // com.bumptech.glide.load.p.d.h.c
        public int c(byte[] bArr, int i) {
            int iMin = Math.min(i, this.f2328a.remaining());
            if (iMin == 0) {
                return -1;
            }
            this.f2328a.get(bArr, 0, iMin);
            return iMin;
        }

        @Override // com.bumptech.glide.load.p.d.h.c
        public short d() throws c.a {
            if (this.f2328a.remaining() >= 1) {
                return (short) (this.f2328a.get() & 255);
            }
            throw new c.a();
        }
    }

    private static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final ByteBuffer f2329a;

        b(byte[] bArr, int i) {
            this.f2329a = (ByteBuffer) ByteBuffer.wrap(bArr).order(ByteOrder.BIG_ENDIAN).limit(i);
        }

        private boolean c(int i, int i2) {
            return this.f2329a.remaining() - i >= i2;
        }

        short a(int i) {
            if (c(i, 2)) {
                return this.f2329a.getShort(i);
            }
            return (short) -1;
        }

        int b(int i) {
            if (c(i, 4)) {
                return this.f2329a.getInt(i);
            }
            return -1;
        }

        int d() {
            return this.f2329a.remaining();
        }

        void e(ByteOrder byteOrder) {
            this.f2329a.order(byteOrder);
        }
    }

    private interface c {

        public static final class a extends IOException {
            a() {
                super("Unexpectedly reached end of a file");
            }
        }

        int a();

        long b(long j);

        int c(byte[] bArr, int i);

        short d();
    }

    private static final class d implements c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final InputStream f2330a;

        d(InputStream inputStream) {
            this.f2330a = inputStream;
        }

        @Override // com.bumptech.glide.load.p.d.h.c
        public int a() {
            return (d() << 8) | d();
        }

        @Override // com.bumptech.glide.load.p.d.h.c
        public long b(long j) throws IOException {
            if (j < 0) {
                return 0L;
            }
            long j2 = j;
            while (j2 > 0) {
                long jSkip = this.f2330a.skip(j2);
                if (jSkip <= 0) {
                    if (this.f2330a.read() == -1) {
                        break;
                    }
                    jSkip = 1;
                }
                j2 -= jSkip;
            }
            return j - j2;
        }

        @Override // com.bumptech.glide.load.p.d.h.c
        public int c(byte[] bArr, int i) throws c.a {
            int i2 = 0;
            int i3 = 0;
            while (i2 < i && (i3 = this.f2330a.read(bArr, i2, i - i2)) != -1) {
                i2 += i3;
            }
            if (i2 == 0 && i3 == -1) {
                throw new c.a();
            }
            return i2;
        }

        @Override // com.bumptech.glide.load.p.d.h.c
        public short d() throws IOException {
            int i = this.f2330a.read();
            if (i != -1) {
                return (short) i;
            }
            throw new c.a();
        }
    }

    private static int d(int i, int i2) {
        return i + 2 + (i2 * 12);
    }

    private int e(c cVar, com.bumptech.glide.load.n.a0.b bVar) {
        try {
            int iA = cVar.a();
            if (!g(iA)) {
                if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                    Log.d("DfltImageHeaderParser", "Parser doesn't handle magic number: " + iA);
                }
                return -1;
            }
            int i = i(cVar);
            if (i == -1) {
                if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                    Log.d("DfltImageHeaderParser", "Failed to parse exif segment length, or exif segment not found");
                }
                return -1;
            }
            byte[] bArr = (byte[]) bVar.e(i, byte[].class);
            try {
                return k(cVar, bArr, i);
            } finally {
                bVar.d(bArr);
            }
        } catch (c.a unused) {
            return -1;
        }
    }

    private ImageHeaderParser.ImageType f(c cVar) {
        try {
            int iA = cVar.a();
            if (iA == 65496) {
                return ImageHeaderParser.ImageType.JPEG;
            }
            int iD = (iA << 8) | cVar.d();
            if (iD == 4671814) {
                return ImageHeaderParser.ImageType.GIF;
            }
            int iD2 = (iD << 8) | cVar.d();
            if (iD2 == -1991225785) {
                cVar.b(21L);
                try {
                    return cVar.d() >= 3 ? ImageHeaderParser.ImageType.PNG_A : ImageHeaderParser.ImageType.PNG;
                } catch (c.a unused) {
                    return ImageHeaderParser.ImageType.PNG;
                }
            }
            if (iD2 != 1380533830) {
                return ImageHeaderParser.ImageType.UNKNOWN;
            }
            cVar.b(4L);
            if (((cVar.a() << 16) | cVar.a()) != 1464156752) {
                return ImageHeaderParser.ImageType.UNKNOWN;
            }
            int iA2 = (cVar.a() << 16) | cVar.a();
            if ((iA2 & (-256)) != 1448097792) {
                return ImageHeaderParser.ImageType.UNKNOWN;
            }
            int i = iA2 & 255;
            if (i == 88) {
                cVar.b(4L);
                return (cVar.d() & 16) != 0 ? ImageHeaderParser.ImageType.WEBP_A : ImageHeaderParser.ImageType.WEBP;
            }
            if (i != 76) {
                return ImageHeaderParser.ImageType.WEBP;
            }
            cVar.b(4L);
            return (cVar.d() & 8) != 0 ? ImageHeaderParser.ImageType.WEBP_A : ImageHeaderParser.ImageType.WEBP;
        } catch (c.a unused2) {
            return ImageHeaderParser.ImageType.UNKNOWN;
        }
    }

    private static boolean g(int i) {
        return (i & 65496) == 65496 || i == 19789 || i == 18761;
    }

    private boolean h(byte[] bArr, int i) {
        boolean z = bArr != null && i > f2326a.length;
        if (z) {
            int i2 = 0;
            while (true) {
                byte[] bArr2 = f2326a;
                if (i2 >= bArr2.length) {
                    break;
                }
                if (bArr[i2] != bArr2[i2]) {
                    return false;
                }
                i2++;
            }
        }
        return z;
    }

    private int i(c cVar) {
        short sD;
        int iA;
        long j;
        long jB;
        do {
            short sD2 = cVar.d();
            if (sD2 != 255) {
                if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                    Log.d("DfltImageHeaderParser", "Unknown segmentId=" + ((int) sD2));
                }
                return -1;
            }
            sD = cVar.d();
            if (sD == 218) {
                return -1;
            }
            if (sD == 217) {
                if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                    Log.d("DfltImageHeaderParser", "Found MARKER_EOI in exif segment");
                }
                return -1;
            }
            iA = cVar.a() - 2;
            if (sD == 225) {
                return iA;
            }
            j = iA;
            jB = cVar.b(j);
        } while (jB == j);
        if (Log.isLoggable("DfltImageHeaderParser", 3)) {
            Log.d("DfltImageHeaderParser", "Unable to skip enough data, type: " + ((int) sD) + ", wanted to skip: " + iA + ", but actually skipped: " + jB);
        }
        return -1;
    }

    private static int j(b bVar) {
        ByteOrder byteOrder;
        StringBuilder sb;
        String str;
        String string;
        short sA = bVar.a(6);
        if (sA != 18761) {
            if (sA != 19789 && Log.isLoggable("DfltImageHeaderParser", 3)) {
                Log.d("DfltImageHeaderParser", "Unknown endianness = " + ((int) sA));
            }
            byteOrder = ByteOrder.BIG_ENDIAN;
        } else {
            byteOrder = ByteOrder.LITTLE_ENDIAN;
        }
        bVar.e(byteOrder);
        int iB = bVar.b(10) + 6;
        short sA2 = bVar.a(iB);
        for (int i = 0; i < sA2; i++) {
            int iD = d(iB, i);
            short sA3 = bVar.a(iD);
            if (sA3 == 274) {
                short sA4 = bVar.a(iD + 2);
                if (sA4 >= 1 && sA4 <= 12) {
                    int iB2 = bVar.b(iD + 4);
                    if (iB2 >= 0) {
                        if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                            Log.d("DfltImageHeaderParser", "Got tagIndex=" + i + " tagType=" + ((int) sA3) + " formatCode=" + ((int) sA4) + " componentCount=" + iB2);
                        }
                        int i2 = iB2 + f2327b[sA4];
                        if (i2 <= 4) {
                            int i3 = iD + 8;
                            if (i3 >= 0 && i3 <= bVar.d()) {
                                if (i2 >= 0 && i2 + i3 <= bVar.d()) {
                                    return bVar.a(i3);
                                }
                                if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                                    sb = new StringBuilder();
                                    sb.append("Illegal number of bytes for TI tag data tagType=");
                                    sb.append((int) sA3);
                                }
                            } else if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                                string = "Illegal tagValueOffset=" + i3 + " tagType=" + ((int) sA3);
                                Log.d("DfltImageHeaderParser", string);
                            }
                        } else if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                            sb = new StringBuilder();
                            str = "Got byte count > 4, not orientation, continuing, formatCode=";
                            sb.append(str);
                            sb.append((int) sA4);
                        }
                    } else if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                        string = "Negative tiff component count";
                        Log.d("DfltImageHeaderParser", string);
                    }
                } else if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                    sb = new StringBuilder();
                    str = "Got invalid format code = ";
                    sb.append(str);
                    sb.append((int) sA4);
                }
                string = sb.toString();
                Log.d("DfltImageHeaderParser", string);
            }
        }
        return -1;
    }

    private int k(c cVar, byte[] bArr, int i) {
        int iC = cVar.c(bArr, i);
        if (iC == i) {
            if (h(bArr, i)) {
                return j(new b(bArr, i));
            }
            if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                Log.d("DfltImageHeaderParser", "Missing jpeg exif preamble");
            }
            return -1;
        }
        if (Log.isLoggable("DfltImageHeaderParser", 3)) {
            Log.d("DfltImageHeaderParser", "Unable to read exif segment data, length: " + i + ", actually read: " + iC);
        }
        return -1;
    }

    @Override // com.bumptech.glide.load.ImageHeaderParser
    public ImageHeaderParser.ImageType a(ByteBuffer byteBuffer) {
        return f(new a((ByteBuffer) com.bumptech.glide.t.j.d(byteBuffer)));
    }

    @Override // com.bumptech.glide.load.ImageHeaderParser
    public int b(InputStream inputStream, com.bumptech.glide.load.n.a0.b bVar) {
        return e(new d((InputStream) com.bumptech.glide.t.j.d(inputStream)), (com.bumptech.glide.load.n.a0.b) com.bumptech.glide.t.j.d(bVar));
    }

    @Override // com.bumptech.glide.load.ImageHeaderParser
    public ImageHeaderParser.ImageType c(InputStream inputStream) {
        return f(new d((InputStream) com.bumptech.glide.t.j.d(inputStream)));
    }
}
