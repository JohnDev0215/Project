package e2;

import com.alibaba.fastjson.asm.TypeCollector;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: compiled from: ClassReader.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final byte[] f4517a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int[] f4518b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String[] f4519c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f4520d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f4521e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f4522f;

    public b(InputStream inputStream, boolean z7) throws IOException {
        int i8;
        this.f4522f = z7;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int i9 = inputStream.read(bArr);
            i8 = 0;
            if (i9 == -1) {
                break;
            } else if (i9 > 0) {
                byteArrayOutputStream.write(bArr, 0, i9);
            }
        }
        inputStream.close();
        this.f4517a = byteArrayOutputStream.toByteArray();
        int[] iArr = new int[g(8)];
        this.f4518b = iArr;
        int length = iArr.length;
        this.f4519c = new String[length];
        int i10 = 10;
        int i11 = 1;
        while (i11 < length) {
            int i12 = i10 + 1;
            this.f4518b[i11] = i12;
            byte b8 = this.f4517a[i10];
            int iG = 5;
            if (b8 == 1) {
                iG = g(i12) + 3;
                if (iG > i8) {
                    i8 = iG;
                }
            } else if (b8 == 15) {
                iG = 4;
            } else if (b8 != 18 && b8 != 3 && b8 != 4) {
                if (b8 != 5 && b8 != 6) {
                    switch (b8) {
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
                    i11++;
                }
            }
            i10 += iG;
            i11++;
        }
        this.f4520d = i8;
        this.f4521e = i10;
    }

    public void a(TypeCollector typeCollector) {
        int i8;
        char[] cArr = new char[this.f4520d];
        if (this.f4522f) {
            int iB = b();
            for (int iG = g(iB); iG > 0; iG--) {
                if ("RuntimeVisibleAnnotations".equals(f(iB + 2, cArr))) {
                    i8 = iB + 8;
                    break;
                }
                iB += c(iB + 4) + 6;
            }
            i8 = 0;
        } else {
            i8 = 0;
        }
        int i9 = this.f4521e;
        int i10 = this.f4518b[g(i9 + 4)];
        int iG2 = g(i9 + 6);
        int i11 = i9 + 8;
        for (int i12 = 0; i12 < iG2; i12++) {
            i11 += 2;
        }
        int iC = i11 + 2;
        int iC2 = iC;
        for (int iG3 = g(i11); iG3 > 0; iG3--) {
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
        if (i8 != 0) {
            int i13 = i8 + 2;
            for (int iG8 = g(i8); iG8 > 0; iG8--) {
                typeCollector.d(f(i13, cArr));
            }
        }
        for (int iG9 = g(i11); iG9 > 0; iG9--) {
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
        int i8 = this.f4521e;
        int iG = i8 + 8 + (g(i8 + 6) * 2);
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

    public final int c(int i8) {
        byte[] bArr = this.f4517a;
        return (bArr[i8 + 3] & 255) | ((bArr[i8] & 255) << 24) | ((bArr[i8 + 1] & 255) << 16) | ((bArr[i8 + 2] & 255) << 8);
    }

    public final int d(TypeCollector typeCollector, char[] cArr, int i8) {
        int iG = g(i8);
        String strF = f(i8 + 2, cArr);
        String strF2 = f(i8 + 4, cArr);
        int i9 = i8 + 8;
        int i10 = 0;
        int i11 = 0;
        for (int iG2 = g(i8 + 6); iG2 > 0; iG2--) {
            String strF3 = f(i9, cArr);
            int iC = c(i9 + 2);
            int i12 = i9 + 6;
            if (strF3.equals("Code")) {
                i11 = i12;
            }
            i9 = i12 + iC;
        }
        g gVarE = typeCollector.e(iG, strF, strF2);
        if (gVarE != null && i11 != 0) {
            int iC2 = i11 + 8 + c(i11 + 4);
            int i13 = iC2 + 2;
            for (int iG3 = g(iC2); iG3 > 0; iG3--) {
                i13 += 8;
            }
            int iC3 = i13 + 2;
            int i14 = 0;
            for (int iG4 = g(i13); iG4 > 0; iG4--) {
                String strF4 = f(iC3, cArr);
                if (strF4.equals("LocalVariableTable")) {
                    i10 = iC3 + 6;
                } else if (strF4.equals("LocalVariableTypeTable")) {
                    i14 = iC3 + 6;
                }
                iC3 += c(iC3 + 2) + 6;
            }
            if (i10 != 0) {
                if (i14 != 0) {
                    int iG5 = g(i14) * 3;
                    int i15 = i14 + 2;
                    int[] iArr = new int[iG5];
                    while (iG5 > 0) {
                        int i16 = iG5 - 1;
                        iArr[i16] = i15 + 6;
                        int i17 = i16 - 1;
                        iArr[i17] = g(i15 + 8);
                        iG5 = i17 - 1;
                        iArr[iG5] = g(i15);
                        i15 += 10;
                    }
                }
                int i18 = i10 + 2;
                for (int iG6 = g(i10); iG6 > 0; iG6--) {
                    gVarE.b(f(i18 + 4, cArr), g(i18 + 8));
                    i18 += 10;
                }
            }
        }
        return i9;
    }

    public final String e(int i8, int i9, char[] cArr) {
        int i10;
        int i11 = i9 + i8;
        byte[] bArr = this.f4517a;
        int i12 = 0;
        char c8 = 0;
        char c9 = 0;
        while (i8 < i11) {
            int i13 = i8 + 1;
            byte b8 = bArr[i8];
            if (c8 == 0) {
                int i14 = b8 & 255;
                if (i14 < 128) {
                    cArr[i12] = (char) i14;
                    i12++;
                } else if (i14 >= 224 || i14 <= 191) {
                    c9 = (char) (i14 & 15);
                    c8 = 2;
                } else {
                    i10 = i14 & 31;
                    c9 = (char) i10;
                    c8 = 1;
                }
            } else if (c8 == 1) {
                cArr[i12] = (char) ((b8 & 63) | (c9 << 6));
                i12++;
                c8 = 0;
            } else if (c8 == 2) {
                i10 = (b8 & 63) | (c9 << 6);
                c9 = (char) i10;
                c8 = 1;
            }
            i8 = i13;
        }
        return new String(cArr, 0, i12);
    }

    public final String f(int i8, char[] cArr) {
        int iG = g(i8);
        String[] strArr = this.f4519c;
        String str = strArr[iG];
        if (str != null) {
            return str;
        }
        int i9 = this.f4518b[iG];
        String strE = e(i9 + 2, g(i9), cArr);
        strArr[iG] = strE;
        return strE;
    }

    public final int g(int i8) {
        byte[] bArr = this.f4517a;
        return (bArr[i8 + 1] & 255) | ((bArr[i8] & 255) << 8);
    }
}
