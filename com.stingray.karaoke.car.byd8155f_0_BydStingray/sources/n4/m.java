package n4;

import android.util.Log;
import com.bumptech.glide.load.ImageHeaderParser$ImageType;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import l3.C1764u;

/* JADX INFO: loaded from: classes.dex */
public final class m implements e4.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final byte[] f19945a = "Exif\u0000\u0000".getBytes(Charset.forName("UTF-8"));

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[] f19946b = {0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 8, 4, 8};

    public static int e(l lVar, F.l lVar2) {
        try {
            int iP = lVar.p();
            if (!((iP & 65496) == 65496 || iP == 19789 || iP == 18761)) {
                if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                    Log.d("DfltImageHeaderParser", "Parser doesn't handle magic number: " + iP);
                }
                return -1;
            }
            int iG = g(lVar);
            if (iG == -1) {
                if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                    Log.d("DfltImageHeaderParser", "Failed to parse exif segment length, or exif segment not found");
                }
                return -1;
            }
            byte[] bArr = (byte[]) lVar2.d(iG, byte[].class);
            try {
                return h(lVar, bArr, iG);
            } finally {
                lVar2.i(bArr);
            }
        } catch (k unused) {
            return -1;
        }
    }

    public static ImageHeaderParser$ImageType f(l lVar) {
        try {
            int iP = lVar.p();
            if (iP == 65496) {
                return ImageHeaderParser$ImageType.JPEG;
            }
            int iJ = (iP << 8) | lVar.j();
            if (iJ == 4671814) {
                return ImageHeaderParser$ImageType.GIF;
            }
            int iJ2 = (iJ << 8) | lVar.j();
            if (iJ2 == -1991225785) {
                lVar.h(21L);
                try {
                    return lVar.j() >= 3 ? ImageHeaderParser$ImageType.PNG_A : ImageHeaderParser$ImageType.PNG;
                } catch (k unused) {
                    return ImageHeaderParser$ImageType.PNG;
                }
            }
            if (iJ2 == 1380533830) {
                lVar.h(4L);
                if (((lVar.p() << 16) | lVar.p()) != 1464156752) {
                    return ImageHeaderParser$ImageType.UNKNOWN;
                }
                int iP2 = (lVar.p() << 16) | lVar.p();
                if ((iP2 & (-256)) != 1448097792) {
                    return ImageHeaderParser$ImageType.UNKNOWN;
                }
                int i7 = iP2 & 255;
                if (i7 == 88) {
                    lVar.h(4L);
                    short sJ = lVar.j();
                    return (sJ & 2) != 0 ? ImageHeaderParser$ImageType.ANIMATED_WEBP : (sJ & 16) != 0 ? ImageHeaderParser$ImageType.WEBP_A : ImageHeaderParser$ImageType.WEBP;
                }
                if (i7 != 76) {
                    return ImageHeaderParser$ImageType.WEBP;
                }
                lVar.h(4L);
                return (lVar.j() & 8) != 0 ? ImageHeaderParser$ImageType.WEBP_A : ImageHeaderParser$ImageType.WEBP;
            }
            if (((lVar.p() << 16) | lVar.p()) != 1718909296) {
                return ImageHeaderParser$ImageType.UNKNOWN;
            }
            int iP3 = (lVar.p() << 16) | lVar.p();
            if (iP3 == 1635150195) {
                return ImageHeaderParser$ImageType.ANIMATED_AVIF;
            }
            int i10 = 0;
            boolean z6 = iP3 == 1635150182;
            lVar.h(4L);
            int i11 = iJ2 - 16;
            if (i11 % 4 == 0) {
                while (i10 < 5 && i11 > 0) {
                    int iP4 = (lVar.p() << 16) | lVar.p();
                    if (iP4 == 1635150195) {
                        return ImageHeaderParser$ImageType.ANIMATED_AVIF;
                    }
                    if (iP4 == 1635150182) {
                        z6 = true;
                    }
                    i10++;
                    i11 -= 4;
                }
            }
            return z6 ? ImageHeaderParser$ImageType.AVIF : ImageHeaderParser$ImageType.UNKNOWN;
        } catch (k unused2) {
            return ImageHeaderParser$ImageType.UNKNOWN;
        }
    }

    public static int g(l lVar) {
        short sJ;
        int iP;
        long j3;
        long jH;
        do {
            short sJ2 = lVar.j();
            if (sJ2 != 255) {
                if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                    android.hardware.bydauto.ac.tsimpl.a.o("Unknown segmentId=", sJ2, "DfltImageHeaderParser");
                }
                return -1;
            }
            sJ = lVar.j();
            if (sJ == 218) {
                return -1;
            }
            if (sJ == 217) {
                if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                    Log.d("DfltImageHeaderParser", "Found MARKER_EOI in exif segment");
                }
                return -1;
            }
            iP = lVar.p() - 2;
            if (sJ == 225) {
                return iP;
            }
            j3 = iP;
            jH = lVar.h(j3);
        } while (jH == j3);
        if (Log.isLoggable("DfltImageHeaderParser", 3)) {
            StringBuilder sbL = M5.b.l("Unable to skip enough data, type: ", ", wanted to skip: ", sJ, iP, ", but actually skipped: ");
            sbL.append(jH);
            Log.d("DfltImageHeaderParser", sbL.toString());
        }
        return -1;
    }

    public static int h(l lVar, byte[] bArr, int i7) {
        ByteOrder byteOrder;
        String str;
        int iW = lVar.w(i7, bArr);
        if (iW != i7) {
            if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                Log.d("DfltImageHeaderParser", "Unable to read exif segment data, length: " + i7 + ", actually read: " + iW);
            }
            return -1;
        }
        short s6 = 1;
        int i10 = 0;
        byte[] bArr2 = f19945a;
        boolean z6 = bArr != null && i7 > bArr2.length;
        if (z6) {
            int i11 = 0;
            while (true) {
                if (i11 >= bArr2.length) {
                    break;
                }
                if (bArr[i11] != bArr2[i11]) {
                    z6 = false;
                    break;
                }
                i11++;
            }
        }
        if (!z6) {
            if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                Log.d("DfltImageHeaderParser", "Missing jpeg exif preamble");
            }
            return -1;
        }
        j jVar = new j(i7, bArr);
        short sC = jVar.c(6);
        if (sC != 18761) {
            if (sC != 19789 && Log.isLoggable("DfltImageHeaderParser", 3)) {
                android.hardware.bydauto.ac.tsimpl.a.o("Unknown endianness = ", sC, "DfltImageHeaderParser");
            }
            byteOrder = ByteOrder.BIG_ENDIAN;
        } else {
            byteOrder = ByteOrder.LITTLE_ENDIAN;
        }
        ByteBuffer byteBuffer = jVar.f19944m;
        byteBuffer.order(byteOrder);
        int i12 = byteBuffer.remaining() - 10 >= 4 ? byteBuffer.getInt(10) : -1;
        short sC2 = jVar.c(i12 + 6);
        while (i10 < sC2) {
            int i13 = (i10 * 12) + i12 + 8;
            short sC3 = jVar.c(i13);
            if (sC3 == 274) {
                short sC4 = jVar.c(i13 + 2);
                if (sC4 >= s6 && sC4 <= 12) {
                    int i14 = i13 + 4;
                    int i15 = byteBuffer.remaining() - i14 >= 4 ? byteBuffer.getInt(i14) : -1;
                    if (i15 >= 0) {
                        if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                            StringBuilder sbL = M5.b.l("Got tagIndex=", " tagType=", i10, sC3, " formatCode=");
                            sbL.append((int) sC4);
                            sbL.append(" componentCount=");
                            sbL.append(i15);
                            Log.d("DfltImageHeaderParser", sbL.toString());
                        }
                        int i16 = i15 + f19946b[sC4];
                        if (i16 <= 4) {
                            int i17 = i13 + 8;
                            if (i17 < 0 || i17 > byteBuffer.remaining()) {
                                if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                                    Log.d("DfltImageHeaderParser", "Illegal tagValueOffset=" + i17 + " tagType=" + ((int) sC3));
                                }
                            } else {
                                if (i16 >= 0 && i16 + i17 <= byteBuffer.remaining()) {
                                    return jVar.c(i17);
                                }
                                if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                                    android.hardware.bydauto.ac.tsimpl.a.o("Illegal number of bytes for TI tag data tagType=", sC3, "DfltImageHeaderParser");
                                }
                            }
                        } else if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                            str = "Got byte count > 4, not orientation, continuing, formatCode=";
                            android.hardware.bydauto.ac.tsimpl.a.o(str, sC4, "DfltImageHeaderParser");
                        }
                    } else if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                        Log.d("DfltImageHeaderParser", "Negative tiff component count");
                    }
                } else if (Log.isLoggable("DfltImageHeaderParser", 3)) {
                    str = "Got invalid format code = ";
                    android.hardware.bydauto.ac.tsimpl.a.o(str, sC4, "DfltImageHeaderParser");
                }
            }
            i10++;
            s6 = 1;
        }
        return -1;
    }

    @Override // e4.d
    public final ImageHeaderParser$ImageType a(ByteBuffer byteBuffer) {
        A4.h.c(byteBuffer, "Argument must not be null");
        return f(new j(0, byteBuffer));
    }

    @Override // e4.d
    public final int b(ByteBuffer byteBuffer, F.l lVar) {
        j jVar = new j(0, byteBuffer);
        A4.h.c(lVar, "Argument must not be null");
        return e(jVar, lVar);
    }

    @Override // e4.d
    public final int c(InputStream inputStream, F.l lVar) {
        C1764u c1764u = new C1764u(inputStream, 8);
        A4.h.c(lVar, "Argument must not be null");
        return e(c1764u, lVar);
    }

    @Override // e4.d
    public final ImageHeaderParser$ImageType d(InputStream inputStream) {
        return f(new C1764u(inputStream, 8));
    }
}
