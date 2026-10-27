package com.bumptech.glide.load.resource.bitmap;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.core.view.InputDeviceCompat;
import com.bumptech.glide.load.ImageHeaderParser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.Objects;
import spi.d;
import spi.e;
import spi.m0;

/* JADX INFO: loaded from: classes.dex */
public final class DefaultImageHeaderParser implements ImageHeaderParser {
    public static final byte[] a = "Exif\u0000\u0000".getBytes(Charset.forName("UTF-8"));
    public static final int[] b = {0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 8, 4, 8};

    public interface Reader {

        public static final class EndOfFileException extends IOException {
            private static final long serialVersionUID = 1;

            public EndOfFileException() {
                super("Unexpectedly reached end of a file");
            }
        }

        int a() throws IOException;

        int b(byte[] bArr, int i) throws IOException;

        short c() throws IOException;

        long skip(long j) throws IOException;
    }

    public static final class a implements Reader {
        public final ByteBuffer a;

        public a(ByteBuffer byteBuffer) {
            this.a = byteBuffer;
            byteBuffer.order(ByteOrder.BIG_ENDIAN);
        }

        @Override // com.bumptech.glide.load.resource.bitmap.DefaultImageHeaderParser.Reader
        public final int a() throws Reader.EndOfFileException {
            return (c() << 8) | c();
        }

        @Override // com.bumptech.glide.load.resource.bitmap.DefaultImageHeaderParser.Reader
        public final int b(byte[] bArr, int i) {
            int iMin = Math.min(i, this.a.remaining());
            if (iMin == 0) {
                return -1;
            }
            this.a.get(bArr, 0, iMin);
            return iMin;
        }

        @Override // com.bumptech.glide.load.resource.bitmap.DefaultImageHeaderParser.Reader
        public final short c() throws Reader.EndOfFileException {
            if (this.a.remaining() >= 1) {
                return (short) (this.a.get() & 255);
            }
            throw new Reader.EndOfFileException();
        }

        @Override // com.bumptech.glide.load.resource.bitmap.DefaultImageHeaderParser.Reader
        public final long skip(long j) {
            int iMin = (int) Math.min(this.a.remaining(), j);
            ByteBuffer byteBuffer = this.a;
            byteBuffer.position(byteBuffer.position() + iMin);
            return iMin;
        }
    }

    public static final class b {
        public final ByteBuffer a;

        public b(byte[] bArr, int i) {
            this.a = (ByteBuffer) ByteBuffer.wrap(bArr).order(ByteOrder.BIG_ENDIAN).limit(i);
        }

        public final short a(int i) {
            if (this.a.remaining() - i >= 2) {
                return this.a.getShort(i);
            }
            return (short) -1;
        }

        public final int b(int i) {
            if (this.a.remaining() - i >= 4) {
                return this.a.getInt(i);
            }
            return -1;
        }
    }

    public static final class c implements Reader {
        public final InputStream a;

        public c(InputStream inputStream) {
            this.a = inputStream;
        }

        @Override // com.bumptech.glide.load.resource.bitmap.DefaultImageHeaderParser.Reader
        public final int a() throws IOException {
            return (c() << 8) | c();
        }

        @Override // com.bumptech.glide.load.resource.bitmap.DefaultImageHeaderParser.Reader
        public final int b(byte[] bArr, int i) throws IOException {
            int i2 = 0;
            int i3 = 0;
            while (i2 < i && (i3 = this.a.read(bArr, i2, i - i2)) != -1) {
                i2 += i3;
            }
            if (i2 == 0 && i3 == -1) {
                throw new Reader.EndOfFileException();
            }
            return i2;
        }

        @Override // com.bumptech.glide.load.resource.bitmap.DefaultImageHeaderParser.Reader
        public final short c() throws IOException {
            int i = this.a.read();
            if (i != -1) {
                return (short) i;
            }
            throw new Reader.EndOfFileException();
        }

        @Override // com.bumptech.glide.load.resource.bitmap.DefaultImageHeaderParser.Reader
        public final long skip(long j) throws IOException {
            if (j < 0) {
                return 0L;
            }
            long j2 = j;
            while (j2 > 0) {
                long jSkip = this.a.skip(j2);
                if (jSkip <= 0) {
                    if (this.a.read() == -1) {
                        break;
                    }
                    jSkip = 1;
                }
                j2 -= jSkip;
            }
            return j - j2;
        }
    }

    @Override // com.bumptech.glide.load.ImageHeaderParser
    @NonNull
    public final ImageHeaderParser.ImageType a(@NonNull ByteBuffer byteBuffer) throws IOException {
        Objects.requireNonNull(byteBuffer, "Argument must not be null");
        return f(new a(byteBuffer));
    }

    @Override // com.bumptech.glide.load.ImageHeaderParser
    public final int b(@NonNull InputStream inputStream, @NonNull m0 m0Var) throws IOException {
        Objects.requireNonNull(inputStream, "Argument must not be null");
        c cVar = new c(inputStream);
        Objects.requireNonNull(m0Var, "Argument must not be null");
        return e(cVar, m0Var);
    }

    @Override // com.bumptech.glide.load.ImageHeaderParser
    public final int c(@NonNull ByteBuffer byteBuffer, @NonNull m0 m0Var) throws IOException {
        Objects.requireNonNull(byteBuffer, "Argument must not be null");
        a aVar = new a(byteBuffer);
        Objects.requireNonNull(m0Var, "Argument must not be null");
        return e(aVar, m0Var);
    }

    @Override // com.bumptech.glide.load.ImageHeaderParser
    @NonNull
    public final ImageHeaderParser.ImageType d(@NonNull InputStream inputStream) throws IOException {
        Objects.requireNonNull(inputStream, "Argument must not be null");
        return f(new c(inputStream));
    }

    public final int e(Reader reader, m0 m0Var) throws IOException {
        try {
            int iA = reader.a();
            if (!((iA & 65496) == 65496 || iA == 19789 || iA == 18761)) {
                if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                    Log.d("DfltImageHeaderParser", "Parser doesn't handle magic number: " + iA);
                }
                return -1;
            }
            int iG = g(reader);
            if (iG == -1) {
                if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                    Log.d("DfltImageHeaderParser", "Failed to parse exif segment length, or exif segment not found");
                }
                return -1;
            }
            byte[] bArr = (byte[]) m0Var.d(iG, byte[].class);
            try {
                return h(reader, bArr, iG);
            } finally {
                m0Var.c(bArr);
            }
        } catch (Reader.EndOfFileException unused) {
            return -1;
        }
    }

    @NonNull
    public final ImageHeaderParser.ImageType f(Reader reader) throws IOException {
        try {
            int iA = reader.a();
            if (iA == 65496) {
                return ImageHeaderParser.ImageType.JPEG;
            }
            int iC = (iA << 8) | reader.c();
            if (iC == 4671814) {
                return ImageHeaderParser.ImageType.GIF;
            }
            int iC2 = (iC << 8) | reader.c();
            if (iC2 == -1991225785) {
                reader.skip(21L);
                try {
                    return reader.c() >= 3 ? ImageHeaderParser.ImageType.PNG_A : ImageHeaderParser.ImageType.PNG;
                } catch (Reader.EndOfFileException unused) {
                    return ImageHeaderParser.ImageType.PNG;
                }
            }
            if (iC2 == 1380533830) {
                reader.skip(4L);
                if (((reader.a() << 16) | reader.a()) != 1464156752) {
                    return ImageHeaderParser.ImageType.UNKNOWN;
                }
                int iA2 = (reader.a() << 16) | reader.a();
                if ((iA2 & InputDeviceCompat.SOURCE_ANY) != 1448097792) {
                    return ImageHeaderParser.ImageType.UNKNOWN;
                }
                int i = iA2 & 255;
                if (i == 88) {
                    reader.skip(4L);
                    short sC = reader.c();
                    return (sC & 2) != 0 ? ImageHeaderParser.ImageType.ANIMATED_WEBP : (sC & 16) != 0 ? ImageHeaderParser.ImageType.WEBP_A : ImageHeaderParser.ImageType.WEBP;
                }
                if (i != 76) {
                    return ImageHeaderParser.ImageType.WEBP;
                }
                reader.skip(4L);
                return (reader.c() & 8) != 0 ? ImageHeaderParser.ImageType.WEBP_A : ImageHeaderParser.ImageType.WEBP;
            }
            boolean z = false;
            if (((reader.a() << 16) | reader.a()) == 1718909296) {
                int iA3 = (reader.a() << 16) | reader.a();
                if (iA3 == 1635150182 || iA3 == 1635150195) {
                    z = true;
                    break;
                }
                reader.skip(4L);
                int i2 = iC2 - 16;
                if (i2 % 4 == 0) {
                    int i3 = 0;
                    while (i3 < 5 && i2 > 0) {
                        int iA4 = (reader.a() << 16) | reader.a();
                        if (iA4 != 1635150182 && iA4 != 1635150195) {
                            i3++;
                            i2 -= 4;
                        }
                        z = true;
                    }
                }
            }
            return z ? ImageHeaderParser.ImageType.AVIF : ImageHeaderParser.ImageType.UNKNOWN;
        } catch (Reader.EndOfFileException unused2) {
            return ImageHeaderParser.ImageType.UNKNOWN;
        }
    }

    public final int g(Reader reader) throws IOException {
        short sC;
        int iA;
        long j;
        long jSkip;
        do {
            short sC2 = reader.c();
            if (sC2 != 255) {
                if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                    d.w("Unknown segmentId=", sC2, "DfltImageHeaderParser");
                }
                return -1;
            }
            sC = reader.c();
            if (sC == 218) {
                return -1;
            }
            if (sC == 217) {
                if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                    Log.d("DfltImageHeaderParser", "Found MARKER_EOI in exif segment");
                }
                return -1;
            }
            iA = reader.a() - 2;
            if (sC == 225) {
                return iA;
            }
            j = iA;
            jSkip = reader.skip(j);
        } while (jSkip == j);
        if (Log.isLoggable("DfltImageHeaderParser", 3)) {
            StringBuilder sbL = e.l("Unable to skip enough data, type: ", sC, ", wanted to skip: ", iA, ", but actually skipped: ");
            sbL.append(jSkip);
            Log.d("DfltImageHeaderParser", sbL.toString());
        }
        return -1;
    }

    public final int h(Reader reader, byte[] bArr, int i) throws IOException {
        ByteOrder byteOrder;
        StringBuilder sb;
        String str;
        String string;
        int iB = reader.b(bArr, i);
        if (iB != i) {
            if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                Log.d("DfltImageHeaderParser", "Unable to read exif segment data, length: " + i + ", actually read: " + iB);
            }
            return -1;
        }
        boolean z = bArr != null && i > a.length;
        if (z) {
            int i2 = 0;
            while (true) {
                byte[] bArr2 = a;
                if (i2 >= bArr2.length) {
                    break;
                }
                if (bArr[i2] != bArr2[i2]) {
                    z = false;
                    break;
                }
                i2++;
            }
        }
        if (!z) {
            if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                Log.d("DfltImageHeaderParser", "Missing jpeg exif preamble");
            }
            return -1;
        }
        b bVar = new b(bArr, i);
        short sA = bVar.a(6);
        if (sA != 18761) {
            if (sA != 19789 && Log.isLoggable("DfltImageHeaderParser", 3)) {
                d.w("Unknown endianness = ", sA, "DfltImageHeaderParser");
            }
            byteOrder = ByteOrder.BIG_ENDIAN;
        } else {
            byteOrder = ByteOrder.LITTLE_ENDIAN;
        }
        bVar.a.order(byteOrder);
        int iB2 = bVar.b(10) + 6;
        short sA2 = bVar.a(iB2);
        for (int i3 = 0; i3 < sA2; i3++) {
            int i4 = (i3 * 12) + iB2 + 2;
            short sA3 = bVar.a(i4);
            if (sA3 == 274) {
                short sA4 = bVar.a(i4 + 2);
                if (sA4 >= 1 && sA4 <= 12) {
                    int iB3 = bVar.b(i4 + 4);
                    if (iB3 >= 0) {
                        if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                            StringBuilder sbL = e.l("Got tagIndex=", i3, " tagType=", sA3, " formatCode=");
                            sbL.append((int) sA4);
                            sbL.append(" componentCount=");
                            sbL.append(iB3);
                            Log.d("DfltImageHeaderParser", sbL.toString());
                        }
                        int i5 = iB3 + b[sA4];
                        if (i5 <= 4) {
                            int i6 = i4 + 8;
                            if (i6 >= 0 && i6 <= bVar.a.remaining()) {
                                if (i5 >= 0 && i5 + i6 <= bVar.a.remaining()) {
                                    return bVar.a(i6);
                                }
                                if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                                    string = e.c("Illegal number of bytes for TI tag data tagType=", sA3);
                                }
                            } else if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                                string = d.c("Illegal tagValueOffset=", i6, " tagType=", sA3);
                            }
                        } else if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                            sb = new StringBuilder();
                            str = "Got byte count > 4, not orientation, continuing, formatCode=";
                            sb.append(str);
                            sb.append((int) sA4);
                            string = sb.toString();
                        }
                    } else if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                        string = "Negative tiff component count";
                    }
                } else if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                    sb = new StringBuilder();
                    str = "Got invalid format code = ";
                    sb.append(str);
                    sb.append((int) sA4);
                    string = sb.toString();
                }
                Log.d("DfltImageHeaderParser", string);
            }
        }
        return -1;
    }
}
