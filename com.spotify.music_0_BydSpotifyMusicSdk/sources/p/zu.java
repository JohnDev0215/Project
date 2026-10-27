package p;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Debug;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import io.reactivex.rxjava3.core.Single;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlinx.coroutines.internal.DiagnosticCoroutineContextException;
import okio.SegmentedByteString;

/* JADX INFO: loaded from: classes.dex */
public abstract class zu {
    public static final char[] a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    public static long b = -1;
    public static final char[] c = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    public static final int[] d = new int[0];
    public static final Object[] e = new Object[0];
    public static final ke2 f = new ke2(9);
    public static final ke2 g = new ke2(14);
    public static final vk0 h = new vk0(5);

    public static int A(int i, byte[] bArr, int i2, int i3, v51 v51Var, j13 j13Var) {
        vd1 vd1Var = (vd1) v51Var;
        vd1Var.c(z(bArr, i2));
        int i4 = i2 + 8;
        while (i4 < i3) {
            int iW = W(bArr, i4, j13Var);
            if (i != j13Var.c) {
                break;
            }
            vd1Var.c(z(bArr, iW));
            i4 = iW + 8;
        }
        return i4;
    }

    public static float B(byte[] bArr, int i) {
        return Float.intBitsToFloat(x(bArr, i));
    }

    public static int C(int i, byte[] bArr, int i2, int i3, v51 v51Var, j13 j13Var) {
        cv0 cv0Var = (cv0) v51Var;
        cv0Var.c(Float.intBitsToFloat(x(bArr, i2)));
        int i4 = i2 + 4;
        while (i4 < i3) {
            int iW = W(bArr, i4, j13Var);
            if (i != j13Var.c) {
                break;
            }
            cv0Var.c(Float.intBitsToFloat(x(bArr, iW)));
            i4 = iW + 4;
        }
        return i4;
    }

    public static final int C0(SegmentedByteString segmentedByteString, int i) {
        int i2;
        v03.j(segmentedByteString, "<this>");
        int i3 = i + 1;
        int length = segmentedByteString.l.length;
        int[] iArr = segmentedByteString.m;
        v03.j(iArr, "<this>");
        int i4 = length - 1;
        int i5 = 0;
        while (true) {
            if (i5 <= i4) {
                i2 = (i5 + i4) >>> 1;
                int i6 = iArr[i2];
                if (i6 >= i3) {
                    if (i6 <= i3) {
                        break;
                    }
                    i4 = i2 - 1;
                } else {
                    i5 = i2 + 1;
                }
            } else {
                i2 = (-i5) - 1;
                break;
            }
        }
        return i2 >= 0 ? i2 : ~i2;
    }

    public static int D(k72 k72Var, int i, byte[] bArr, int i2, int i3, v51 v51Var, j13 j13Var) {
        int i4 = (i & (-8)) | 4;
        Object objI = k72Var.i();
        int iR0 = r0(objI, k72Var, bArr, i2, i3, i4, j13Var);
        k72Var.c(objI);
        j13Var.k = objI;
        v51Var.add(objI);
        while (iR0 < i3) {
            int iW = W(bArr, iR0, j13Var);
            if (i != j13Var.c) {
                break;
            }
            Object objI2 = k72Var.i();
            int iR02 = r0(objI2, k72Var, bArr, iW, i3, i4, j13Var);
            k72Var.c(objI2);
            j13Var.k = objI2;
            v51Var.add(objI2);
            iR0 = iR02;
        }
        return iR0;
    }

    public static String D0(String str) {
        byte[] bytes = str.getBytes();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update(bytes);
            return m0(messageDigest.digest());
        } catch (NoSuchAlgorithmException e2) {
            Log.e("FirebaseCrashlytics", "Could not create hashing algorithm: SHA-1, returning empty string.", e2);
            return "";
        }
    }

    public static int E(k72 k72Var, int i, byte[] bArr, int i2, int i3, v51 v51Var, j13 j13Var) throws InvalidProtocolBufferException {
        Object objI = k72Var.i();
        int iS0 = s0(objI, k72Var, bArr, i2, i3, j13Var);
        k72Var.c(objI);
        j13Var.k = objI;
        v51Var.add(objI);
        while (iS0 < i3) {
            int iW = W(bArr, iS0, j13Var);
            if (i != j13Var.c) {
                break;
            }
            Object objI2 = k72Var.i();
            int iS02 = s0(objI2, k72Var, bArr, iW, i3, j13Var);
            k72Var.c(objI2);
            j13Var.k = objI2;
            v51Var.add(objI2);
            iS0 = iS02;
        }
        return iS0;
    }

    public static int E0(int i, byte[] bArr, int i2, int i3, j13 j13Var) throws InvalidProtocolBufferException {
        if ((i >>> 3) == 0) {
            throw InvalidProtocolBufferException.b();
        }
        int i4 = i & 7;
        if (i4 == 0) {
            return Y(bArr, i2, j13Var);
        }
        if (i4 == 1) {
            return i2 + 8;
        }
        if (i4 == 2) {
            return W(bArr, i2, j13Var) + j13Var.c;
        }
        if (i4 != 3) {
            if (i4 == 5) {
                return i2 + 4;
            }
            throw InvalidProtocolBufferException.b();
        }
        int i5 = (i & (-8)) | 4;
        int i6 = 0;
        while (i2 < i3) {
            i2 = W(bArr, i2, j13Var);
            i6 = j13Var.c;
            if (i6 == i5) {
                break;
            }
            i2 = E0(i6, bArr, i2, i3, j13Var);
        }
        if (i2 > i3 || i6 != i5) {
            throw InvalidProtocolBufferException.g();
        }
        return i2;
    }

    public static int F(byte[] bArr, int i, v51 v51Var, j13 j13Var) throws InvalidProtocolBufferException {
        km kmVar = (km) v51Var;
        int iW = W(bArr, i, j13Var);
        int i2 = j13Var.c + iW;
        while (iW < i2) {
            iW = Y(bArr, iW, j13Var);
            kmVar.c(j13Var.e != 0);
        }
        if (iW == i2) {
            return iW;
        }
        throw InvalidProtocolBufferException.j();
    }

    public static void F0(fz0 fz0Var, a1 a1Var, a1 a1Var2) {
        try {
            fa0.a(vr2.k(vr2.c(a1Var, a1Var2, fz0Var)), lq2.a, null);
        } catch (Throwable th) {
            a1Var2.k(kotlin.a.b(th));
            throw th;
        }
    }

    public static int G(byte[] bArr, int i, v51 v51Var, j13 j13Var) throws InvalidProtocolBufferException {
        ta0 ta0Var = (ta0) v51Var;
        int iW = W(bArr, i, j13Var);
        int i2 = j13Var.c + iW;
        while (iW < i2) {
            ta0Var.c(Double.longBitsToDouble(z(bArr, iW)));
            iW += 8;
        }
        if (iW == i2) {
            return iW;
        }
        throw InvalidProtocolBufferException.j();
    }

    public static String G0(FileInputStream fileInputStream) {
        Scanner scannerUseDelimiter = new Scanner(fileInputStream).useDelimiter("\\A");
        return scannerUseDelimiter.hasNext() ? scannerUseDelimiter.next() : "";
    }

    public static int H(byte[] bArr, int i, v51 v51Var, j13 j13Var) throws InvalidProtocolBufferException {
        e51 e51Var = (e51) v51Var;
        int iW = W(bArr, i, j13Var);
        int i2 = j13Var.c + iW;
        while (iW < i2) {
            e51Var.c(x(bArr, iW));
            iW += 4;
        }
        if (iW == i2) {
            return iW;
        }
        throw InvalidProtocolBufferException.j();
    }

    public static int I(byte[] bArr, int i, v51 v51Var, j13 j13Var) throws InvalidProtocolBufferException {
        vd1 vd1Var = (vd1) v51Var;
        int iW = W(bArr, i, j13Var);
        int i2 = j13Var.c + iW;
        while (iW < i2) {
            vd1Var.c(z(bArr, iW));
            iW += 8;
        }
        if (iW == i2) {
            return iW;
        }
        throw InvalidProtocolBufferException.j();
    }

    public static int J(byte[] bArr, int i, v51 v51Var, j13 j13Var) throws InvalidProtocolBufferException {
        cv0 cv0Var = (cv0) v51Var;
        int iW = W(bArr, i, j13Var);
        int i2 = j13Var.c + iW;
        while (iW < i2) {
            cv0Var.c(Float.intBitsToFloat(x(bArr, iW)));
            iW += 4;
        }
        if (iW == i2) {
            return iW;
        }
        throw InvalidProtocolBufferException.j();
    }

    public static int K(byte[] bArr, int i, v51 v51Var, j13 j13Var) throws InvalidProtocolBufferException {
        e51 e51Var = (e51) v51Var;
        int iW = W(bArr, i, j13Var);
        int i2 = j13Var.c + iW;
        while (iW < i2) {
            iW = W(bArr, iW, j13Var);
            e51Var.c(au.b(j13Var.c));
        }
        if (iW == i2) {
            return iW;
        }
        throw InvalidProtocolBufferException.j();
    }

    public static int L(byte[] bArr, int i, v51 v51Var, j13 j13Var) throws InvalidProtocolBufferException {
        vd1 vd1Var = (vd1) v51Var;
        int iW = W(bArr, i, j13Var);
        int i2 = j13Var.c + iW;
        while (iW < i2) {
            iW = Y(bArr, iW, j13Var);
            vd1Var.c(au.c(j13Var.e));
        }
        if (iW == i2) {
            return iW;
        }
        throw InvalidProtocolBufferException.j();
    }

    public static int M(byte[] bArr, int i, v51 v51Var, j13 j13Var) throws InvalidProtocolBufferException {
        e51 e51Var = (e51) v51Var;
        int iW = W(bArr, i, j13Var);
        int i2 = j13Var.c + iW;
        while (iW < i2) {
            iW = W(bArr, iW, j13Var);
            e51Var.c(j13Var.c);
        }
        if (iW == i2) {
            return iW;
        }
        throw InvalidProtocolBufferException.j();
    }

    public static int N(byte[] bArr, int i, v51 v51Var, j13 j13Var) throws InvalidProtocolBufferException {
        vd1 vd1Var = (vd1) v51Var;
        int iW = W(bArr, i, j13Var);
        int i2 = j13Var.c + iW;
        while (iW < i2) {
            iW = Y(bArr, iW, j13Var);
            vd1Var.c(j13Var.e);
        }
        if (iW == i2) {
            return iW;
        }
        throw InvalidProtocolBufferException.j();
    }

    public static int O(int i, byte[] bArr, int i2, int i3, v51 v51Var, j13 j13Var) {
        e51 e51Var = (e51) v51Var;
        int iW = W(bArr, i2, j13Var);
        e51Var.c(au.b(j13Var.c));
        while (iW < i3) {
            int iW2 = W(bArr, iW, j13Var);
            if (i != j13Var.c) {
                break;
            }
            iW = W(bArr, iW2, j13Var);
            e51Var.c(au.b(j13Var.c));
        }
        return iW;
    }

    public static int P(int i, byte[] bArr, int i2, int i3, v51 v51Var, j13 j13Var) {
        vd1 vd1Var = (vd1) v51Var;
        int iY = Y(bArr, i2, j13Var);
        vd1Var.c(au.c(j13Var.e));
        while (iY < i3) {
            int iW = W(bArr, iY, j13Var);
            if (i != j13Var.c) {
                break;
            }
            iY = Y(bArr, iW, j13Var);
            vd1Var.c(au.c(j13Var.e));
        }
        return iY;
    }

    public static int Q(byte[] bArr, int i, j13 j13Var) throws InvalidProtocolBufferException {
        int iW = W(bArr, i, j13Var);
        int i2 = j13Var.c;
        if (i2 < 0) {
            throw InvalidProtocolBufferException.f();
        }
        if (i2 == 0) {
            j13Var.k = "";
            return iW;
        }
        j13Var.k = new String(bArr, iW, i2, w51.a);
        return iW + i2;
    }

    public static int R(int i, byte[] bArr, int i2, int i3, v51 v51Var, j13 j13Var) throws InvalidProtocolBufferException {
        int iW = W(bArr, i2, j13Var);
        int i4 = j13Var.c;
        if (i4 < 0) {
            throw InvalidProtocolBufferException.f();
        }
        if (i4 == 0) {
            v51Var.add("");
        } else {
            v51Var.add(new String(bArr, iW, i4, w51.a));
            iW += i4;
        }
        while (iW < i3) {
            int iW2 = W(bArr, iW, j13Var);
            if (i != j13Var.c) {
                break;
            }
            iW = W(bArr, iW2, j13Var);
            int i5 = j13Var.c;
            if (i5 < 0) {
                throw InvalidProtocolBufferException.f();
            }
            if (i5 == 0) {
                v51Var.add("");
            } else {
                v51Var.add(new String(bArr, iW, i5, w51.a));
                iW += i5;
            }
        }
        return iW;
    }

    public static int S(int i, byte[] bArr, int i2, int i3, v51 v51Var, j13 j13Var) throws InvalidProtocolBufferException {
        int iW = W(bArr, i2, j13Var);
        int i4 = j13Var.c;
        if (i4 < 0) {
            throw InvalidProtocolBufferException.f();
        }
        if (i4 == 0) {
            v51Var.add("");
        } else {
            int i5 = iW + i4;
            if (com.google.protobuf.r.a.x(iW, i5, bArr) != 0) {
                throw InvalidProtocolBufferException.c();
            }
            v51Var.add(new String(bArr, iW, i4, w51.a));
            iW = i5;
        }
        while (iW < i3) {
            int iW2 = W(bArr, iW, j13Var);
            if (i != j13Var.c) {
                break;
            }
            iW = W(bArr, iW2, j13Var);
            int i6 = j13Var.c;
            if (i6 < 0) {
                throw InvalidProtocolBufferException.f();
            }
            if (i6 == 0) {
                v51Var.add("");
            } else {
                int i7 = iW + i6;
                if (com.google.protobuf.r.a.x(iW, i7, bArr) != 0) {
                    throw InvalidProtocolBufferException.c();
                }
                v51Var.add(new String(bArr, iW, i6, w51.a));
                iW = i7;
            }
        }
        return iW;
    }

    public static int T(byte[] bArr, int i, j13 j13Var) throws InvalidProtocolBufferException {
        int iW = W(bArr, i, j13Var);
        int i2 = j13Var.c;
        if (i2 < 0) {
            throw InvalidProtocolBufferException.f();
        }
        if (i2 == 0) {
            j13Var.k = "";
            return iW;
        }
        j13Var.k = com.google.protobuf.r.a.u(bArr, iW, i2);
        return iW + i2;
    }

    public static int U(int i, byte[] bArr, int i2, int i3, com.google.protobuf.o oVar, j13 j13Var) throws InvalidProtocolBufferException {
        if ((i >>> 3) == 0) {
            throw InvalidProtocolBufferException.b();
        }
        int i4 = i & 7;
        if (i4 == 0) {
            int iY = Y(bArr, i2, j13Var);
            oVar.f(i, Long.valueOf(j13Var.e));
            return iY;
        }
        if (i4 == 1) {
            oVar.f(i, Long.valueOf(z(bArr, i2)));
            return i2 + 8;
        }
        if (i4 == 2) {
            int iW = W(bArr, i2, j13Var);
            int i5 = j13Var.c;
            if (i5 < 0) {
                throw InvalidProtocolBufferException.f();
            }
            if (i5 > bArr.length - iW) {
                throw InvalidProtocolBufferException.j();
            }
            if (i5 == 0) {
                oVar.f(i, ByteString.c);
            } else {
                oVar.f(i, ByteString.c(bArr, iW, i5));
            }
            return iW + i5;
        }
        if (i4 != 3) {
            if (i4 != 5) {
                throw InvalidProtocolBufferException.b();
            }
            oVar.f(i, Integer.valueOf(x(bArr, i2)));
            return i2 + 4;
        }
        com.google.protobuf.o oVar2 = new com.google.protobuf.o();
        int i6 = (i & (-8)) | 4;
        int i7 = 0;
        while (true) {
            if (i2 >= i3) {
                break;
            }
            int iW2 = W(bArr, i2, j13Var);
            int i8 = j13Var.c;
            if (i8 == i6) {
                i7 = i8;
                i2 = iW2;
                break;
            }
            i7 = i8;
            i2 = U(i8, bArr, iW2, i3, oVar2, j13Var);
        }
        if (i2 > i3 || i7 != i6) {
            throw InvalidProtocolBufferException.g();
        }
        oVar.f(i, oVar2);
        return i2;
    }

    public static int V(int i, byte[] bArr, int i2, j13 j13Var) {
        int i3 = i & 127;
        int i4 = i2 + 1;
        byte b2 = bArr[i2];
        if (b2 >= 0) {
            j13Var.c = i3 | (b2 << 7);
            return i4;
        }
        int i5 = i3 | ((b2 & 127) << 7);
        int i6 = i2 + 2;
        byte b3 = bArr[i4];
        if (b3 >= 0) {
            j13Var.c = i5 | (b3 << 14);
            return i6;
        }
        int i7 = i5 | ((b3 & 127) << 14);
        int i8 = i2 + 3;
        byte b4 = bArr[i6];
        if (b4 >= 0) {
            j13Var.c = i7 | (b4 << 21);
            return i8;
        }
        int i9 = i7 | ((b4 & 127) << 21);
        int i10 = i2 + 4;
        byte b5 = bArr[i8];
        if (b5 >= 0) {
            j13Var.c = i9 | (b5 << 28);
            return i10;
        }
        int i11 = i9 | ((b5 & 127) << 28);
        while (true) {
            int i12 = i10 + 1;
            if (bArr[i10] >= 0) {
                j13Var.c = i11;
                return i12;
            }
            i10 = i12;
        }
    }

    public static int W(byte[] bArr, int i, j13 j13Var) {
        int i2 = i + 1;
        byte b2 = bArr[i];
        if (b2 < 0) {
            return V(b2, bArr, i2, j13Var);
        }
        j13Var.c = b2;
        return i2;
    }

    public static int X(int i, byte[] bArr, int i2, int i3, v51 v51Var, j13 j13Var) {
        e51 e51Var = (e51) v51Var;
        int iW = W(bArr, i2, j13Var);
        e51Var.c(j13Var.c);
        while (iW < i3) {
            int iW2 = W(bArr, iW, j13Var);
            if (i != j13Var.c) {
                break;
            }
            iW = W(bArr, iW2, j13Var);
            e51Var.c(j13Var.c);
        }
        return iW;
    }

    public static int Y(byte[] bArr, int i, j13 j13Var) {
        int i2 = i + 1;
        long j = bArr[i];
        if (j >= 0) {
            j13Var.e = j;
            return i2;
        }
        int i3 = i + 2;
        byte b2 = bArr[i2];
        long j2 = (j & 127) | (((long) (b2 & 127)) << 7);
        int i4 = 7;
        while (b2 < 0) {
            int i5 = i3 + 1;
            byte b3 = bArr[i3];
            i4 += 7;
            j2 |= ((long) (b3 & 127)) << i4;
            b2 = b3;
            i3 = i5;
        }
        j13Var.e = j2;
        return i3;
    }

    public static int Z(int i, byte[] bArr, int i2, int i3, v51 v51Var, j13 j13Var) {
        vd1 vd1Var = (vd1) v51Var;
        int iY = Y(bArr, i2, j13Var);
        vd1Var.c(j13Var.e);
        while (iY < i3) {
            int iW = W(bArr, iY, j13Var);
            if (i != j13Var.c) {
                break;
            }
            iY = Y(bArr, iW, j13Var);
            vd1Var.c(j13Var.e);
        }
        return iY;
    }

    public static r61 a() {
        return new r61(null);
    }

    public static final void a0(y10 y10Var) {
        p61 p61Var = (p61) y10Var.E0(qo.o);
        if (p61Var != null && !p61Var.b()) {
            throw ((kotlinx.coroutines.g) p61Var).B();
        }
    }

    public static void b(byte b2, byte b3, byte b4, byte b5, char[] cArr, int i) {
        if (!o0(b3)) {
            if ((((b3 + 112) + (b2 << 28)) >> 30) == 0 && !o0(b4) && !o0(b5)) {
                int i2 = ((b2 & 7) << 18) | ((b3 & 63) << 12) | ((b4 & 63) << 6) | (b5 & 63);
                cArr[i] = (char) ((i2 >>> 10) + 55232);
                cArr[i + 1] = (char) ((i2 & 1023) + 56320);
                return;
            }
        }
        throw InvalidProtocolBufferException.c();
    }

    public static String b0(ByteString byteString) {
        hk0 hk0Var = new hk0(24, byteString);
        StringBuilder sb = new StringBuilder(byteString.size());
        for (int i = 0; i < ((ByteString) hk0Var.e).size(); i++) {
            byte bA = ((ByteString) hk0Var.e).a(i);
            if (bA == 34) {
                sb.append("\\\"");
            } else if (bA == 39) {
                sb.append("\\'");
            } else if (bA != 92) {
                switch (bA) {
                    case 7:
                        sb.append("\\a");
                        break;
                    case 8:
                        sb.append("\\b");
                        break;
                    case 9:
                        sb.append("\\t");
                        break;
                    case 10:
                        sb.append("\\n");
                        break;
                    case 11:
                        sb.append("\\v");
                        break;
                    case 12:
                        sb.append("\\f");
                        break;
                    case 13:
                        sb.append("\\r");
                        break;
                    default:
                        if (bA < 32 || bA > 126) {
                            sb.append('\\');
                            sb.append((char) (((bA >>> 6) & 3) + 48));
                            sb.append((char) (((bA >>> 3) & 7) + 48));
                            sb.append((char) ((bA & 7) + 48));
                        } else {
                            sb.append((char) bA);
                        }
                        break;
                }
            } else {
                sb.append("\\\\");
            }
        }
        return sb.toString();
    }

    public static boolean c(byte b2) {
        return b2 >= 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0035, code lost:
    
        r3 = r4[1];
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String c0(java.io.File r7) throws java.lang.Throwable {
        /*
            java.lang.String r0 = "MemTotal"
            java.lang.String r1 = "Failed to close system file reader."
            boolean r2 = r7.exists()
            r3 = 0
            if (r2 == 0) goto L60
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L43
            java.io.FileReader r4 = new java.io.FileReader     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L43
            r4.<init>(r7)     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L43
            r5 = 1024(0x400, float:1.435E-42)
            r2.<init>(r4, r5)     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L43
        L17:
            java.lang.String r4 = r2.readLine()     // Catch: java.lang.Throwable -> L38 java.lang.Exception -> L3b
            if (r4 == 0) goto L3d
            java.lang.String r5 = "\\s*:\\s*"
            java.util.regex.Pattern r5 = java.util.regex.Pattern.compile(r5)     // Catch: java.lang.Throwable -> L38 java.lang.Exception -> L3b
            r6 = 2
            java.lang.String[] r4 = r5.split(r4, r6)     // Catch: java.lang.Throwable -> L38 java.lang.Exception -> L3b
            int r5 = r4.length     // Catch: java.lang.Throwable -> L38 java.lang.Exception -> L3b
            r6 = 1
            if (r5 <= r6) goto L17
            r5 = 0
            r5 = r4[r5]     // Catch: java.lang.Throwable -> L38 java.lang.Exception -> L3b
            boolean r5 = r5.equals(r0)     // Catch: java.lang.Throwable -> L38 java.lang.Exception -> L3b
            if (r5 == 0) goto L17
            r3 = r4[r6]     // Catch: java.lang.Throwable -> L38 java.lang.Exception -> L3b
            goto L3d
        L38:
            r7 = move-exception
            r3 = r2
            goto L5c
        L3b:
            r0 = move-exception
            goto L45
        L3d:
            q(r2, r1)
            goto L60
        L41:
            r7 = move-exception
            goto L5c
        L43:
            r0 = move-exception
            r2 = r3
        L45:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L38
            r4.<init>()     // Catch: java.lang.Throwable -> L38
            java.lang.String r5 = "Error parsing "
            r4.append(r5)     // Catch: java.lang.Throwable -> L38
            r4.append(r7)     // Catch: java.lang.Throwable -> L38
            java.lang.String r7 = r4.toString()     // Catch: java.lang.Throwable -> L38
            java.lang.String r4 = "FirebaseCrashlytics"
            android.util.Log.e(r4, r7, r0)     // Catch: java.lang.Throwable -> L38
            goto L3d
        L5c:
            q(r3, r1)
            throw r7
        L60:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: p.zu.c0(java.io.File):java.lang.String");
    }

    public static void d(byte b2, byte b3, char[] cArr, int i) {
        if (b2 < -62 || o0(b3)) {
            throw InvalidProtocolBufferException.c();
        }
        cArr[i] = (char) (((b2 & 31) << 6) | (b3 & 63));
    }

    public static final us d0(Annotation annotation) {
        v03.j(annotation, "<this>");
        Class<? extends Annotation> clsAnnotationType = annotation.annotationType();
        v03.i(clsAnnotationType, "this as java.lang.annota…otation).annotationType()");
        return r22.a(clsAnnotationType);
    }

    public static void e(byte b2, byte b3, byte b4, char[] cArr, int i) {
        if (o0(b3) || ((b2 == -32 && b3 < -96) || ((b2 == -19 && b3 >= -96) || o0(b4)))) {
            throw InvalidProtocolBufferException.c();
        }
        cArr[i] = (char) (((b2 & 15) << 12) | ((b3 & 63) << 6) | (b4 & 63));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v6 */
    public static int e0() {
        boolean zN0 = n0();
        ?? r0 = zN0;
        if (p0()) {
            r0 = (zN0 ? 1 : 0) | 2;
        }
        return (Debug.isDebuggerConnected() || Debug.waitingForDebugger()) ? r0 | 4 : r0;
    }

    public static final ExecutorService f(boolean z) {
        ExecutorService executorServiceNewFixedThreadPool = Executors.newFixedThreadPool(Math.max(2, Math.min(Runtime.getRuntime().availableProcessors() - 1, 4)), new fx(z));
        v03.i(executorServiceNewFixedThreadPool, "newFixedThreadPool(\n    …)),\n        factory\n    )");
        return executorServiceNewFixedThreadPool;
    }

    public static final Class f0(us usVar) {
        Class clsA = usVar.a();
        v03.g(clsA, "null cannot be cast to non-null type java.lang.Class<T of kotlin.jvm.JvmClassMappingKt.<get-java>>");
        return clsA;
    }

    public static final int g(char c2) {
        if ('0' <= c2 && c2 < ':') {
            return c2 - '0';
        }
        if ('a' <= c2 && c2 < 'g') {
            return c2 - 'W';
        }
        if ('A' <= c2 && c2 < 'G') {
            return c2 - '7';
        }
        throw new IllegalArgumentException("Unexpected hex digit: " + c2);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static final Class g0(e81 e81Var) {
        v03.j(e81Var, "<this>");
        Class clsA = ((ts) e81Var).a();
        if (!clsA.isPrimitive()) {
            return clsA;
        }
        String name = clsA.getName();
        switch (name.hashCode()) {
            case -1325958191:
                if (!name.equals("double")) {
                }
                break;
            case 104431:
                if (!name.equals("int")) {
                }
                break;
            case 3039496:
                if (!name.equals("byte")) {
                }
                break;
            case 3052374:
                if (!name.equals("char")) {
                }
                break;
            case 3327612:
                if (!name.equals("long")) {
                }
                break;
            case 3625364:
                if (!name.equals("void")) {
                }
                break;
            case 64711720:
                if (!name.equals("boolean")) {
                }
                break;
            case 97526364:
                if (!name.equals("float")) {
                }
                break;
            case 109413500:
                if (!name.equals("short")) {
                }
                break;
        }
        return clsA;
    }

    public static void h(Class cls) {
        int modifiers = cls.getModifiers();
        if (Modifier.isInterface(modifiers)) {
            throw new UnsupportedOperationException("Interface can't be instantiated! Interface name: ".concat(cls.getName()));
        }
        if (Modifier.isAbstract(modifiers)) {
            throw new UnsupportedOperationException("Abstract class can't be instantiated! Class name: ".concat(cls.getName()));
        }
    }

    public static final p61 h0(y10 y10Var) {
        p61 p61Var = (p61) y10Var.E0(qo.o);
        if (p61Var != null) {
            return p61Var;
        }
        throw new IllegalStateException(("Current context doesn't contain Job in it: " + y10Var).toString());
    }

    public static final int i(int i, int i2, int[] iArr) {
        v03.j(iArr, "array");
        int i3 = i - 1;
        int i4 = 0;
        while (i4 <= i3) {
            int i5 = (i4 + i3) >>> 1;
            int i6 = iArr[i5];
            if (i6 < i2) {
                i4 = i5 + 1;
            } else {
                if (i6 <= i2) {
                    return i5;
                }
                i3 = i5 - 1;
            }
        }
        return ~i4;
    }

    public static int i0(Context context, String str, String str2) {
        String packageName;
        Resources resources = context.getResources();
        int i = context.getApplicationContext().getApplicationInfo().icon;
        if (i > 0) {
            try {
                packageName = context.getResources().getResourcePackageName(i);
                if ("android".equals(packageName)) {
                    packageName = context.getPackageName();
                }
            } catch (Resources.NotFoundException unused) {
                packageName = context.getPackageName();
            }
        } else {
            packageName = context.getPackageName();
        }
        return resources.getIdentifier(str, str2, packageName);
    }

    public static final int j(long[] jArr, int i, long j) {
        v03.j(jArr, "array");
        int i2 = i - 1;
        int i3 = 0;
        while (i3 <= i2) {
            int i4 = (i3 + i2) >>> 1;
            long j2 = jArr[i4];
            if (j2 < j) {
                i3 = i4 + 1;
            } else {
                if (j2 <= j) {
                    return i4;
                }
                i2 = i4 - 1;
            }
        }
        return ~i3;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v6, types: [int] */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r2v10, types: [long] */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v5, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v6, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r6v0, types: [java.lang.StringBuilder] */
    public static synchronized long j0() {
        long j;
        ?? r0 = "Unexpected meminfo format while computing RAM: ";
        synchronized (zu.class) {
            try {
                if (b == -1) {
                    String strC0 = c0(new File("/proc/meminfo"));
                    long j2 = 0;
                    if (!TextUtils.isEmpty(strC0)) {
                        ?? upperCase = strC0.toUpperCase(Locale.US);
                        try {
                            if (upperCase.endsWith("KB")) {
                                r0 = 1024;
                                upperCase = Long.parseLong(upperCase.split("KB")[0].trim());
                            } else if (upperCase.endsWith("MB")) {
                                r0 = 1048576;
                                upperCase = Long.parseLong(upperCase.split("MB")[0].trim());
                            } else if (upperCase.endsWith("GB")) {
                                r0 = 1073741824;
                                upperCase = Long.parseLong(upperCase.split("GB")[0].trim());
                            }
                            j2 = ((long) r0) * upperCase;
                        } catch (NumberFormatException e2) {
                            Log.e("FirebaseCrashlytics", ((String) r0) + upperCase, e2);
                        }
                    }
                    b = j2;
                }
                j = b;
            } catch (Throwable th) {
                throw th;
            }
        }
        return j;
    }

    public static final void k0(y10 y10Var, Throwable th) {
        Throwable runtimeException;
        Iterator it = b20.a.iterator();
        while (it.hasNext()) {
            try {
                ((a20) it.next()).q(y10Var, th);
            } catch (Throwable th2) {
                if (th == th2) {
                    runtimeException = th;
                } else {
                    runtimeException = new RuntimeException("Exception while trying to handle coroutine exception", th2);
                    kotlin.a.a(runtimeException, th);
                }
                Thread threadCurrentThread = Thread.currentThread();
                threadCurrentThread.getUncaughtExceptionHandler().uncaughtException(threadCurrentThread, runtimeException);
            }
        }
        try {
            kotlin.a.a(th, new DiagnosticCoroutineContextException(y10Var));
        } catch (Throwable unused) {
        }
        Thread threadCurrentThread2 = Thread.currentThread();
        threadCurrentThread2.getUncaughtExceptionHandler().uncaughtException(threadCurrentThread2, th);
    }

    public static String m0(byte[] bArr) {
        char[] cArr = new char[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            byte b2 = bArr[i];
            int i2 = i * 2;
            char[] cArr2 = a;
            cArr[i2] = cArr2[(b2 & 255) >>> 4];
            cArr[i2 + 1] = cArr2[b2 & 15];
        }
        return new String(cArr);
    }

    public static boolean n0() {
        if (!Build.PRODUCT.contains("sdk")) {
            String str = Build.HARDWARE;
            if (!str.contains("goldfish") && !str.contains("ranchu")) {
                return false;
            }
        }
        return true;
    }

    public static boolean o0(byte b2) {
        return b2 > -65;
    }

    public static final void p(int i) {
        if (i < 1) {
            throw new IllegalArgumentException(u4.e("Expected positive parallelism level, but got ", i).toString());
        }
    }

    public static boolean p0() {
        boolean zN0 = n0();
        String str = Build.TAGS;
        if ((zN0 || str == null || !str.contains("test-keys")) && !new File("/system/app/Superuser.apk").exists()) {
            return !zN0 && new File("/system/xbin/su").exists();
        }
        return true;
    }

    public static void q(Closeable closeable, String str) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e2) {
                Log.e("FirebaseCrashlytics", str, e2);
            }
        }
    }

    public static void r(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException e2) {
                throw e2;
            } catch (Exception unused) {
            }
        }
    }

    public static int r0(Object obj, k72 k72Var, byte[] bArr, int i, int i2, int i3, j13 j13Var) {
        int iL = ((com.google.protobuf.k) k72Var).L(obj, bArr, i, i2, i3, j13Var);
        j13Var.k = obj;
        return iL;
    }

    public static int s(int i, byte[] bArr, int i2, int i3, v51 v51Var, j13 j13Var) {
        km kmVar = (km) v51Var;
        int iY = Y(bArr, i2, j13Var);
        kmVar.c(j13Var.e != 0);
        while (iY < i3) {
            int iW = W(bArr, iY, j13Var);
            if (i != j13Var.c) {
                break;
            }
            iY = Y(bArr, iW, j13Var);
            kmVar.c(j13Var.e != 0);
        }
        return iY;
    }

    public static int s0(Object obj, k72 k72Var, byte[] bArr, int i, int i2, j13 j13Var) throws InvalidProtocolBufferException {
        int iV = i + 1;
        int i3 = bArr[i];
        if (i3 < 0) {
            iV = V(i3, bArr, iV, j13Var);
            i3 = j13Var.c;
        }
        int i4 = iV;
        if (i3 < 0 || i3 > i2 - i4) {
            throw InvalidProtocolBufferException.j();
        }
        int i5 = i3 + i4;
        k72Var.b(obj, bArr, i4, i5, j13Var);
        j13Var.k = obj;
        return i5;
    }

    public static int t(byte[] bArr, int i, j13 j13Var) throws InvalidProtocolBufferException {
        int iW = W(bArr, i, j13Var);
        int i2 = j13Var.c;
        if (i2 < 0) {
            throw InvalidProtocolBufferException.f();
        }
        if (i2 > bArr.length - iW) {
            throw InvalidProtocolBufferException.j();
        }
        if (i2 == 0) {
            j13Var.k = ByteString.c;
            return iW;
        }
        j13Var.k = ByteString.c(bArr, iW, i2);
        return iW + i2;
    }

    public static int u(int i, byte[] bArr, int i2, int i3, v51 v51Var, j13 j13Var) throws InvalidProtocolBufferException {
        int iW = W(bArr, i2, j13Var);
        int i4 = j13Var.c;
        if (i4 < 0) {
            throw InvalidProtocolBufferException.f();
        }
        if (i4 > bArr.length - iW) {
            throw InvalidProtocolBufferException.j();
        }
        if (i4 == 0) {
            v51Var.add(ByteString.c);
        } else {
            v51Var.add(ByteString.c(bArr, iW, i4));
            iW += i4;
        }
        while (iW < i3) {
            int iW2 = W(bArr, iW, j13Var);
            if (i != j13Var.c) {
                break;
            }
            iW = W(bArr, iW2, j13Var);
            int i5 = j13Var.c;
            if (i5 < 0) {
                throw InvalidProtocolBufferException.f();
            }
            if (i5 > bArr.length - iW) {
                throw InvalidProtocolBufferException.j();
            }
            if (i5 == 0) {
                v51Var.add(ByteString.c);
            } else {
                v51Var.add(ByteString.c(bArr, iW, i5));
                iW += i5;
            }
        }
        return iW;
    }

    public static double v(byte[] bArr, int i) {
        return Double.longBitsToDouble(z(bArr, i));
    }

    public static int w(int i, byte[] bArr, int i2, int i3, v51 v51Var, j13 j13Var) {
        ta0 ta0Var = (ta0) v51Var;
        ta0Var.c(Double.longBitsToDouble(z(bArr, i2)));
        int i4 = i2 + 8;
        while (i4 < i3) {
            int iW = W(bArr, i4, j13Var);
            if (i != j13Var.c) {
                break;
            }
            ta0Var.c(Double.longBitsToDouble(z(bArr, iW)));
            i4 = iW + 8;
        }
        return i4;
    }

    public static int x(byte[] bArr, int i) {
        return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    public static int y(int i, byte[] bArr, int i2, int i3, v51 v51Var, j13 j13Var) {
        e51 e51Var = (e51) v51Var;
        e51Var.c(x(bArr, i2));
        int i4 = i2 + 4;
        while (i4 < i3) {
            int iW = W(bArr, i4, j13Var);
            if (i != j13Var.c) {
                break;
            }
            e51Var.c(x(bArr, iW));
            i4 = iW + 4;
        }
        return i4;
    }

    public static long z(byte[] bArr, int i) {
        return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
    }

    public abstract void A0(i1 i1Var, i1 i1Var2);

    public abstract void B0(i1 i1Var, Thread thread);

    public void k(int i) {
        new Handler(Looper.getMainLooper()).post(new p42(i, 0, this));
    }

    public void l(Typeface typeface) {
        new Handler(Looper.getMainLooper()).post(new ua(this, 1, typeface));
    }

    public abstract o11 l0();

    public abstract boolean m(j1 j1Var, e1 e1Var, e1 e1Var2);

    public abstract boolean n(j1 j1Var, Object obj, Object obj2);

    public abstract boolean o(j1 j1Var, i1 i1Var, i1 i1Var2);

    public abstract Single q0(kh0 kh0Var, kh0 kh0Var2, kh0 kh0Var3, kh0 kh0Var4, kh0 kh0Var5, kh0 kh0Var6);

    public abstract Object t0(Class cls);

    public abstract void u0(int i);

    public abstract void v0(Typeface typeface);

    public abstract void w0(Typeface typeface, boolean z);

    public zu x0(byte[] bArr) {
        return y0(bArr, bArr.length);
    }

    public abstract zu y0(byte[] bArr, int i);

    public abstract zu z0(char c2);
}
