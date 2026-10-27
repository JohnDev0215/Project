package b.a.a.i;

import androidx.exifinterface.media.ExifInterface;
import com.alibaba.fastjson.asm.TypeCollector;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: compiled from: ClassReader.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final byte[] f186a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int[] f187b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String[] f188c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f189d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f190e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f191f;

    public b(InputStream inputStream, boolean z) throws IOException {
        int i;
        this.f191f = z;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int i2 = inputStream.read(bArr);
            i = 0;
            if (i2 == -1) {
                break;
            } else if (i2 > 0) {
                byteArrayOutputStream.write(bArr, 0, i2);
            }
        }
        inputStream.close();
        this.f186a = byteArrayOutputStream.toByteArray();
        int[] iArr = new int[g(8)];
        this.f187b = iArr;
        int length = iArr.length;
        this.f188c = new String[length];
        int i3 = 10;
        int i4 = 1;
        while (i4 < length) {
            int i5 = i3 + 1;
            this.f187b[i4] = i5;
            byte b2 = this.f186a[i3];
            int iG = 5;
            if (b2 == 1) {
                iG = g(i5) + 3;
                if (iG > i) {
                    i = iG;
                }
            } else if (b2 == 15) {
                iG = 4;
            } else if (b2 != 18 && b2 != 3 && b2 != 4) {
                if (b2 != 5 && b2 != 6) {
                    switch (b2) {
                        case 9:
                        case 10:
                        case 11:
                        case 12:
                            break;
                        default:
                            iG = 3;
                            break;
                    }
                } else {
                    iG = 9;
                    i4++;
                }
            }
            i3 += iG;
            i4++;
        }
        this.f189d = i;
        this.f190e = i3;
    }

    public void a(TypeCollector typeCollector) {
        int i;
        char[] cArr = new char[this.f189d];
        if (this.f191f) {
            int iB = b();
            for (int iG = g(iB); iG > 0; iG--) {
                if ("RuntimeVisibleAnnotations".equals(f(iB + 2, cArr))) {
                    i = iB + 8;
                    break;
                }
                iB += c(iB + 4) + 6;
            }
            i = 0;
        } else {
            i = 0;
        }
        int i2 = this.f190e;
        int i3 = this.f187b[g(i2 + 4)];
        int iG2 = g(i2 + 6);
        int i4 = i2 + 8;
        for (int i5 = 0; i5 < iG2; i5++) {
            i4 += 2;
        }
        int iC = i4 + 2;
        int iC2 = iC;
        for (int iG3 = g(i4); iG3 > 0; iG3--) {
            iC2 += 8;
            for (int iG4 = g(iC2 + 6); iG4 > 0; iG4--) {
                iC2 += c(iC2 + 2) + 6;
            }
        }
        int iC3 = iC2 + 2;
        for (int iG5 = g(iC2); iG5 > 0; iG5--) {
            iC3 += 8;
            for (int iG6 = g(iC3 + 6); iG6 > 0; iG6--) {
                iC3 += c(iC3 + 2) + 6;
            }
        }
        int iC4 = iC3 + 2;
        for (int iG7 = g(iC3); iG7 > 0; iG7--) {
            iC4 += c(iC4 + 2) + 6;
        }
        if (i != 0) {
            int i6 = i + 2;
            for (int iG8 = g(i); iG8 > 0; iG8--) {
                typeCollector.d(f(i6, cArr));
            }
        }
        for (int iG9 = g(i4); iG9 > 0; iG9--) {
            iC += 8;
            for (int iG10 = g(iC + 6); iG10 > 0; iG10--) {
                iC += c(iC + 2) + 6;
            }
        }
        int iD = iC + 2;
        for (int iG11 = g(iC); iG11 > 0; iG11--) {
            iD = d(typeCollector, cArr, iD);
        }
    }

    public final int b() {
        int i = this.f190e;
        int iG = i + 8 + (g(i + 6) * 2);
        for (int iG2 = g(iG); iG2 > 0; iG2--) {
            for (int iG3 = g(iG + 8); iG3 > 0; iG3--) {
                iG += c(iG + 12) + 6;
            }
            iG += 8;
        }
        int iC = iG + 2;
        for (int iG4 = g(iC); iG4 > 0; iG4--) {
            for (int iG5 = g(iC + 8); iG5 > 0; iG5--) {
                iC += c(iC + 12) + 6;
            }
            iC += 8;
        }
        return iC + 2;
    }

    public final int c(int i) {
        byte[] bArr = this.f186a;
        return (bArr[i + 3] & ExifInterface.MARKER) | ((bArr[i] & ExifInterface.MARKER) << 24) | ((bArr[i + 1] & ExifInterface.MARKER) << 16) | ((bArr[i + 2] & ExifInterface.MARKER) << 8);
    }

    public final int d(TypeCollector typeCollector, char[] cArr, int i) {
        int iG = g(i);
        String strF = f(i + 2, cArr);
        String strF2 = f(i + 4, cArr);
        int i2 = i + 8;
        int i3 = 0;
        int i4 = 0;
        for (int iG2 = g(i + 6); iG2 > 0; iG2--) {
            String strF3 = f(i2, cArr);
            int iC = c(i2 + 2);
            int i5 = i2 + 6;
            if (strF3.equals("Code")) {
                i4 = i5;
            }
            i2 = i5 + iC;
        }
        g gVarE = typeCollector.e(iG, strF, strF2);
        if (gVarE != null && i4 != 0) {
            int iC2 = i4 + 8 + c(i4 + 4);
            int i6 = iC2 + 2;
            for (int iG3 = g(iC2); iG3 > 0; iG3--) {
                i6 += 8;
            }
            int iC3 = i6 + 2;
            int i7 = 0;
            for (int iG4 = g(i6); iG4 > 0; iG4--) {
                String strF4 = f(iC3, cArr);
                if (strF4.equals("LocalVariableTable")) {
                    i3 = iC3 + 6;
                } else if (strF4.equals("LocalVariableTypeTable")) {
                    i7 = iC3 + 6;
                }
                iC3 += c(iC3 + 2) + 6;
            }
            if (i3 != 0) {
                if (i7 != 0) {
                    int iG5 = g(i7) * 3;
                    int i8 = i7 + 2;
                    int[] iArr = new int[iG5];
                    while (iG5 > 0) {
                        int i9 = iG5 - 1;
                        iArr[i9] = i8 + 6;
                        int i10 = i9 - 1;
                        iArr[i10] = g(i8 + 8);
                        iG5 = i10 - 1;
                        iArr[iG5] = g(i8);
                        i8 += 10;
                    }
                }
                int i11 = i3 + 2;
                for (int iG6 = g(i3); iG6 > 0; iG6--) {
                    gVarE.b(f(i11 + 4, cArr), g(i11 + 8));
                    i11 += 10;
                }
            }
        }
        return i2;
    }

    public final String e(int i, int i2, char[] cArr) {
        int i3;
        int i4 = i2 + i;
        byte[] bArr = this.f186a;
        int i5 = 0;
        char c2 = 0;
        char c3 = 0;
        while (i < i4) {
            int i6 = i + 1;
            byte b2 = bArr[i];
            if (c2 == 0) {
                int i7 = b2 & ExifInterface.MARKER;
                if (i7 < 128) {
                    cArr[i5] = (char) i7;
                    i5++;
                } else if (i7 >= 224 || i7 <= 191) {
                    c3 = (char) (i7 & 15);
                    c2 = 2;
                } else {
                    i3 = i7 & 31;
                    c3 = (char) i3;
                    c2 = 1;
                }
            } else if (c2 == 1) {
                cArr[i5] = (char) ((b2 & 63) | (c3 << 6));
                i5++;
                c2 = 0;
            } else if (c2 == 2) {
                i3 = (b2 & 63) | (c3 << 6);
                c3 = (char) i3;
                c2 = 1;
            }
            i = i6;
        }
        return new String(cArr, 0, i5);
    }

    public final String f(int i, char[] cArr) {
        int iG = g(i);
        String[] strArr = this.f188c;
        String str = strArr[iG];
        if (str != null) {
            return str;
        }
        int i2 = this.f187b[iG];
        String strE = e(i2 + 2, g(i2), cArr);
        strArr[iG] = strE;
        return strE;
    }

    public final int g(int i) {
        byte[] bArr = this.f186a;
        return (bArr[i + 1] & ExifInterface.MARKER) | ((bArr[i] & ExifInterface.MARKER) << 8);
    }
}
