package x1;

import android.util.Log;
import com.bumptech.glide.load.ImageHeaderParser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

/* JADX INFO: compiled from: DefaultImageHeaderParser.java */
/* JADX INFO: loaded from: classes.dex */
public final class k implements ImageHeaderParser {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final byte[] f9759a = "Exif\u0000\u0000".getBytes(Charset.forName("UTF-8"));

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final int[] f9760b = {0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 8, 4, 8};

    /* JADX INFO: compiled from: DefaultImageHeaderParser.java */
    private static final class a implements c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final ByteBuffer f9761a;

        a(ByteBuffer byteBuffer) {
            this.f9761a = byteBuffer;
            byteBuffer.order(ByteOrder.BIG_ENDIAN);
        }

        @Override // x1.k.c
        public long a(long j7) {
            int iMin = (int) Math.min(this.f9761a.remaining(), j7);
            ByteBuffer byteBuffer = this.f9761a;
            byteBuffer.position(byteBuffer.position() + iMin);
            return iMin;
        }

        @Override // x1.k.c
        public int b() {
            return ((e() << 8) & 65280) | (e() & 255);
        }

        @Override // x1.k.c
        public int c(byte[] bArr, int i7) {
            int iMin = Math.min(i7, this.f9761a.remaining());
            if (iMin == 0) {
                return -1;
            }
            this.f9761a.get(bArr, 0, iMin);
            return iMin;
        }

        @Override // x1.k.c
        public short d() {
            return (short) (e() & 255);
        }

        @Override // x1.k.c
        public int e() {
            if (this.f9761a.remaining() < 1) {
                return -1;
            }
            return this.f9761a.get();
        }
    }

    /* JADX INFO: compiled from: DefaultImageHeaderParser.java */
    private static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final ByteBuffer f9762a;

        b(byte[] bArr, int i7) {
            this.f9762a = (ByteBuffer) ByteBuffer.wrap(bArr).order(ByteOrder.BIG_ENDIAN).limit(i7);
        }

        private boolean c(int i7, int i8) {
            return this.f9762a.remaining() - i7 >= i8;
        }

        short a(int i7) {
            if (c(i7, 2)) {
                return this.f9762a.getShort(i7);
            }
            return (short) -1;
        }

        int b(int i7) {
            if (c(i7, 4)) {
                return this.f9762a.getInt(i7);
            }
            return -1;
        }

        int d() {
            return this.f9762a.remaining();
        }

        void e(ByteOrder byteOrder) {
            this.f9762a.order(byteOrder);
        }
    }

    /* JADX INFO: compiled from: DefaultImageHeaderParser.java */
    private interface c {
        long a(long j7);

        int b();

        int c(byte[] bArr, int i7);

        short d();

        int e();
    }

    /* JADX INFO: compiled from: DefaultImageHeaderParser.java */
    private static final class d implements c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final InputStream f9763a;

        d(InputStream inputStream) {
            this.f9763a = inputStream;
        }

        @Override // x1.k.c
        public long a(long j7) throws IOException {
            if (j7 < 0) {
                return 0L;
            }
            long j8 = j7;
            while (j8 > 0) {
                long jSkip = this.f9763a.skip(j8);
                if (jSkip <= 0) {
                    if (this.f9763a.read() == -1) {
                        break;
                    }
                    jSkip = 1;
                }
                j8 -= jSkip;
            }
            return j7 - j8;
        }

        @Override // x1.k.c
        public int b() {
            return ((this.f9763a.read() << 8) & 65280) | (this.f9763a.read() & 255);
        }

        @Override // x1.k.c
        public int c(byte[] bArr, int i7) throws IOException {
            int i8 = i7;
            while (i8 > 0) {
                int i9 = this.f9763a.read(bArr, i7 - i8, i8);
                if (i9 == -1) {
                    break;
                }
                i8 -= i9;
            }
            return i7 - i8;
        }

        @Override // x1.k.c
        public short d() {
            return (short) (this.f9763a.read() & 255);
        }

        @Override // x1.k.c
        public int e() {
            return this.f9763a.read();
        }
    }

    private static int d(int i7, int i8) {
        return i7 + 2 + (i8 * 12);
    }

    private int e(c cVar, r1.b bVar) {
        int iB = cVar.b();
        if (!g(iB)) {
            if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                Log.d("DfltImageHeaderParser", "Parser doesn't handle magic number: " + iB);
            }
            return -1;
        }
        int i7 = i(cVar);
        if (i7 == -1) {
            if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                Log.d("DfltImageHeaderParser", "Failed to parse exif segment length, or exif segment not found");
            }
            return -1;
        }
        byte[] bArr = (byte[]) bVar.e(i7, byte[].class);
        try {
            return k(cVar, bArr, i7);
        } finally {
            bVar.d(bArr);
        }
    }

    private ImageHeaderParser.ImageType f(c cVar) {
        int iB = cVar.b();
        if (iB == 65496) {
            return ImageHeaderParser.ImageType.JPEG;
        }
        int iB2 = ((iB << 16) & (-65536)) | (cVar.b() & 65535);
        if (iB2 == -1991225785) {
            cVar.a(21L);
            return cVar.e() >= 3 ? ImageHeaderParser.ImageType.PNG_A : ImageHeaderParser.ImageType.PNG;
        }
        if ((iB2 >> 8) == 4671814) {
            return ImageHeaderParser.ImageType.GIF;
        }
        if (iB2 != 1380533830) {
            return ImageHeaderParser.ImageType.UNKNOWN;
        }
        cVar.a(4L);
        if ((((cVar.b() << 16) & (-65536)) | (cVar.b() & 65535)) != 1464156752) {
            return ImageHeaderParser.ImageType.UNKNOWN;
        }
        int iB3 = ((cVar.b() << 16) & (-65536)) | (cVar.b() & 65535);
        if ((iB3 & (-256)) != 1448097792) {
            return ImageHeaderParser.ImageType.UNKNOWN;
        }
        int i7 = iB3 & 255;
        if (i7 == 88) {
            cVar.a(4L);
            return (cVar.e() & 16) != 0 ? ImageHeaderParser.ImageType.WEBP_A : ImageHeaderParser.ImageType.WEBP;
        }
        if (i7 != 76) {
            return ImageHeaderParser.ImageType.WEBP;
        }
        cVar.a(4L);
        return (cVar.e() & 8) != 0 ? ImageHeaderParser.ImageType.WEBP_A : ImageHeaderParser.ImageType.WEBP;
    }

    private static boolean g(int i7) {
        return (i7 & 65496) == 65496 || i7 == 19789 || i7 == 18761;
    }

    private boolean h(byte[] bArr, int i7) {
        boolean z6 = bArr != null && i7 > f9759a.length;
        if (z6) {
            int i8 = 0;
            while (true) {
                byte[] bArr2 = f9759a;
                if (i8 >= bArr2.length) {
                    break;
                }
                if (bArr[i8] != bArr2[i8]) {
                    return false;
                }
                i8++;
            }
        }
        return z6;
    }

    private int i(c cVar) {
        short sD;
        int iB;
        long j7;
        long jA;
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
            iB = cVar.b() - 2;
            if (sD == 225) {
                return iB;
            }
            j7 = iB;
            jA = cVar.a(j7);
        } while (jA == j7);
        if (Log.isLoggable("DfltImageHeaderParser", 3)) {
            Log.d("DfltImageHeaderParser", "Unable to skip enough data, type: " + ((int) sD) + ", wanted to skip: " + iB + ", but actually skipped: " + jA);
        }
        return -1;
    }

    private static int j(b bVar) {
        ByteOrder byteOrder;
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
        for (int i7 = 0; i7 < sA2; i7++) {
            int iD = d(iB, i7);
            short sA3 = bVar.a(iD);
            if (sA3 == 274) {
                short sA4 = bVar.a(iD + 2);
                if (sA4 >= 1 && sA4 <= 12) {
                    int iB2 = bVar.b(iD + 4);
                    if (iB2 >= 0) {
                        if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                            Log.d("DfltImageHeaderParser", "Got tagIndex=" + i7 + " tagType=" + ((int) sA3) + " formatCode=" + ((int) sA4) + " componentCount=" + iB2);
                        }
                        int i8 = iB2 + f9760b[sA4];
                        if (i8 <= 4) {
                            int i9 = iD + 8;
                            if (i9 >= 0 && i9 <= bVar.d()) {
                                if (i8 >= 0 && i8 + i9 <= bVar.d()) {
                                    return bVar.a(i9);
                                }
                                if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                                    Log.d("DfltImageHeaderParser", "Illegal number of bytes for TI tag data tagType=" + ((int) sA3));
                                }
                            } else if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                                Log.d("DfltImageHeaderParser", "Illegal tagValueOffset=" + i9 + " tagType=" + ((int) sA3));
                            }
                        } else if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                            Log.d("DfltImageHeaderParser", "Got byte count > 4, not orientation, continuing, formatCode=" + ((int) sA4));
                        }
                    } else if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                        Log.d("DfltImageHeaderParser", "Negative tiff component count");
                    }
                } else if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                    Log.d("DfltImageHeaderParser", "Got invalid format code = " + ((int) sA4));
                }
            }
        }
        return -1;
    }

    private int k(c cVar, byte[] bArr, int i7) {
        int iC = cVar.c(bArr, i7);
        if (iC == i7) {
            if (h(bArr, i7)) {
                return j(new b(bArr, i7));
            }
            if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                Log.d("DfltImageHeaderParser", "Missing jpeg exif preamble");
            }
            return -1;
        }
        if (Log.isLoggable("DfltImageHeaderParser", 3)) {
            Log.d("DfltImageHeaderParser", "Unable to read exif segment data, length: " + i7 + ", actually read: " + iC);
        }
        return -1;
    }

    @Override // com.bumptech.glide.load.ImageHeaderParser
    public ImageHeaderParser.ImageType a(ByteBuffer byteBuffer) {
        return f(new a((ByteBuffer) k2.j.d(byteBuffer)));
    }

    @Override // com.bumptech.glide.load.ImageHeaderParser
    public int b(InputStream inputStream, r1.b bVar) {
        return e(new d((InputStream) k2.j.d(inputStream)), (r1.b) k2.j.d(bVar));
    }

    @Override // com.bumptech.glide.load.ImageHeaderParser
    public ImageHeaderParser.ImageType c(InputStream inputStream) {
        return f(new d((InputStream) k2.j.d(inputStream)));
    }
}
