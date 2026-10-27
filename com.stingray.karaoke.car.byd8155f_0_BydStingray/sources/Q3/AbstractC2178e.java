package q3;

import android.content.pm.PackageInfo;
import h6.m;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;

/* JADX INFO: renamed from: q3.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC2178e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final m6.b f21237a = new m6.b();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final byte[] f21238b = {112, 114, 111, 0};
    public static final byte[] c = {112, 114, 109, 0};
    public static final byte[] d = {48, 49, 53, 0};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final byte[] f21239e = {48, 49, 48, 0};

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final byte[] f21240f = {48, 48, 57, 0};

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final byte[] f21241g = {48, 48, 53, 0};

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final byte[] f21242h = {48, 48, 49, 0};

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final byte[] f21243i = {48, 48, 49, 0};

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final byte[] f21244j = {48, 48, 50, 0};

    public static byte[] a(byte[] bArr) {
        Deflater deflater = new Deflater(1);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(byteArrayOutputStream, deflater);
            try {
                deflaterOutputStream.write(bArr);
                deflaterOutputStream.close();
                deflater.end();
                return byteArrayOutputStream.toByteArray();
            } finally {
            }
        } catch (Throwable th) {
            deflater.end();
            throw th;
        }
    }

    public static byte[] b(C2175b[] c2175bArr, byte[] bArr) throws IOException {
        int i7 = 0;
        int length = 0;
        for (C2175b c2175b : c2175bArr) {
            length += ((((c2175b.f21234g * 2) + 7) & (-8)) / 8) + (c2175b.f21232e * 2) + d(c2175b.f21230a, c2175b.f21231b, bArr).getBytes(StandardCharsets.UTF_8).length + 16 + c2175b.f21233f;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(length);
        if (Arrays.equals(bArr, f21240f)) {
            int length2 = c2175bArr.length;
            while (i7 < length2) {
                C2175b c2175b2 = c2175bArr[i7];
                q(byteArrayOutputStream, c2175b2, d(c2175b2.f21230a, c2175b2.f21231b, bArr));
                p(byteArrayOutputStream, c2175b2);
                i7++;
            }
        } else {
            for (C2175b c2175b3 : c2175bArr) {
                q(byteArrayOutputStream, c2175b3, d(c2175b3.f21230a, c2175b3.f21231b, bArr));
            }
            int length3 = c2175bArr.length;
            while (i7 < length3) {
                p(byteArrayOutputStream, c2175bArr[i7]);
                i7++;
            }
        }
        if (byteArrayOutputStream.size() == length) {
            return byteArrayOutputStream.toByteArray();
        }
        throw new IllegalStateException("The bytes saved do not match expectation. actual=" + byteArrayOutputStream.size() + " expected=" + length);
    }

    public static boolean c(File file) {
        if (!file.isDirectory()) {
            file.delete();
            return true;
        }
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles == null) {
            return false;
        }
        boolean z6 = true;
        for (File file2 : fileArrListFiles) {
            z6 = c(file2) && z6;
        }
        return z6;
    }

    public static String d(String str, String str2, byte[] bArr) {
        byte[] bArr2 = f21242h;
        boolean zEquals = Arrays.equals(bArr, bArr2);
        byte[] bArr3 = f21241g;
        String str3 = (zEquals || Arrays.equals(bArr, bArr3)) ? ":" : "!";
        if (str.length() <= 0) {
            return "!".equals(str3) ? str2.replace(":", "!") : ":".equals(str3) ? str2.replace("!", ":") : str2;
        }
        if (str2.equals("classes.dex")) {
            return str;
        }
        if (str2.contains("!") || str2.contains(":")) {
            return "!".equals(str3) ? str2.replace(":", "!") : ":".equals(str3) ? str2.replace("!", ":") : str2;
        }
        if (str2.endsWith(".apk")) {
            return str2;
        }
        return android.hardware.bydauto.ac.tsimpl.a.l(M5.b.i(str), (Arrays.equals(bArr, bArr2) || Arrays.equals(bArr, bArr3)) ? ":" : "!", str2);
    }

    public static void e(PackageInfo packageInfo, File file) {
        File file2 = new File(file, "profileinstaller_profileWrittenFor_lastUpdateTime.dat");
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(m.m(new FileOutputStream(file2), file2));
            try {
                dataOutputStream.writeLong(packageInfo.lastUpdateTime);
                dataOutputStream.close();
            } finally {
            }
        } catch (IOException unused) {
        }
    }

    public static byte[] f(InputStream inputStream, int i7) throws IOException {
        byte[] bArr = new byte[i7];
        int i10 = 0;
        while (i10 < i7) {
            int i11 = inputStream.read(bArr, i10, i7 - i10);
            if (i11 < 0) {
                throw new IllegalStateException(android.hardware.bydauto.ac.tsimpl.a.e(i7, "Not enough bytes to read: "));
            }
            i10 += i11;
        }
        return bArr;
    }

    public static int[] g(ByteArrayInputStream byteArrayInputStream, int i7) {
        int[] iArr = new int[i7];
        int iM = 0;
        for (int i10 = 0; i10 < i7; i10++) {
            iM += (int) m(byteArrayInputStream, 2);
            iArr[i10] = iM;
        }
        return iArr;
    }

    public static byte[] h(FileInputStream fileInputStream, int i7, int i10) {
        Inflater inflater = new Inflater();
        try {
            byte[] bArr = new byte[i10];
            byte[] bArr2 = new byte[2048];
            int i11 = 0;
            int iInflate = 0;
            while (!inflater.finished() && !inflater.needsDictionary() && i11 < i7) {
                int i12 = fileInputStream.read(bArr2);
                if (i12 < 0) {
                    throw new IllegalStateException("Invalid zip data. Stream ended after $totalBytesRead bytes. Expected " + i7 + " bytes");
                }
                inflater.setInput(bArr2, 0, i12);
                try {
                    iInflate += inflater.inflate(bArr, iInflate, i10 - iInflate);
                    i11 += i12;
                } catch (DataFormatException e10) {
                    throw new IllegalStateException(e10.getMessage());
                }
            }
            if (i11 == i7) {
                if (inflater.finished()) {
                    return bArr;
                }
                throw new IllegalStateException("Inflater did not finish");
            }
            throw new IllegalStateException("Didn't read enough bytes during decompression. expected=" + i7 + " actual=" + i11);
        } finally {
            inflater.end();
        }
    }

    public static C2175b[] i(FileInputStream fileInputStream, byte[] bArr, byte[] bArr2, C2175b[] c2175bArr) throws IOException {
        byte[] bArr3 = f21243i;
        if (!Arrays.equals(bArr, bArr3)) {
            if (!Arrays.equals(bArr, f21244j)) {
                throw new IllegalStateException("Unsupported meta version");
            }
            int iM = (int) m(fileInputStream, 2);
            byte[] bArrH = h(fileInputStream, (int) m(fileInputStream, 4), (int) m(fileInputStream, 4));
            if (fileInputStream.read() > 0) {
                throw new IllegalStateException("Content found after the end of file");
            }
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArrH);
            try {
                C2175b[] c2175bArrK = k(byteArrayInputStream, bArr2, iM, c2175bArr);
                byteArrayInputStream.close();
                return c2175bArrK;
            } catch (Throwable th) {
                try {
                    byteArrayInputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
        if (Arrays.equals(d, bArr2)) {
            throw new IllegalStateException("Requires new Baseline Profile Metadata. Please rebuild the APK with Android Gradle Plugin 7.2 Canary 7 or higher");
        }
        if (!Arrays.equals(bArr, bArr3)) {
            throw new IllegalStateException("Unsupported meta version");
        }
        int iM2 = (int) m(fileInputStream, 1);
        byte[] bArrH2 = h(fileInputStream, (int) m(fileInputStream, 4), (int) m(fileInputStream, 4));
        if (fileInputStream.read() > 0) {
            throw new IllegalStateException("Content found after the end of file");
        }
        ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(bArrH2);
        try {
            C2175b[] c2175bArrJ = j(byteArrayInputStream2, iM2, c2175bArr);
            byteArrayInputStream2.close();
            return c2175bArrJ;
        } catch (Throwable th3) {
            try {
                byteArrayInputStream2.close();
            } catch (Throwable th4) {
                th3.addSuppressed(th4);
            }
            throw th3;
        }
    }

    public static C2175b[] j(ByteArrayInputStream byteArrayInputStream, int i7, C2175b[] c2175bArr) {
        if (byteArrayInputStream.available() == 0) {
            return new C2175b[0];
        }
        if (i7 != c2175bArr.length) {
            throw new IllegalStateException("Mismatched number of dex files found in metadata");
        }
        String[] strArr = new String[i7];
        int[] iArr = new int[i7];
        for (int i10 = 0; i10 < i7; i10++) {
            int iM = (int) m(byteArrayInputStream, 2);
            iArr[i10] = (int) m(byteArrayInputStream, 2);
            strArr[i10] = new String(f(byteArrayInputStream, iM), StandardCharsets.UTF_8);
        }
        for (int i11 = 0; i11 < i7; i11++) {
            C2175b c2175b = c2175bArr[i11];
            if (!c2175b.f21231b.equals(strArr[i11])) {
                throw new IllegalStateException("Order of dexfiles in metadata did not match baseline");
            }
            int i12 = iArr[i11];
            c2175b.f21232e = i12;
            c2175b.f21235h = g(byteArrayInputStream, i12);
        }
        return c2175bArr;
    }

    public static C2175b[] k(ByteArrayInputStream byteArrayInputStream, byte[] bArr, int i7, C2175b[] c2175bArr) throws IOException {
        if (byteArrayInputStream.available() == 0) {
            return new C2175b[0];
        }
        if (i7 != c2175bArr.length) {
            throw new IllegalStateException("Mismatched number of dex files found in metadata");
        }
        for (int i10 = 0; i10 < i7; i10++) {
            m(byteArrayInputStream, 2);
            String str = new String(f(byteArrayInputStream, (int) m(byteArrayInputStream, 2)), StandardCharsets.UTF_8);
            long jM = m(byteArrayInputStream, 4);
            int iM = (int) m(byteArrayInputStream, 2);
            C2175b c2175b = null;
            if (c2175bArr.length > 0) {
                int iIndexOf = str.indexOf("!");
                if (iIndexOf < 0) {
                    iIndexOf = str.indexOf(":");
                }
                String strSubstring = iIndexOf > 0 ? str.substring(iIndexOf + 1) : str;
                int i11 = 0;
                while (true) {
                    if (i11 >= c2175bArr.length) {
                        break;
                    }
                    if (c2175bArr[i11].f21231b.equals(strSubstring)) {
                        c2175b = c2175bArr[i11];
                        break;
                    }
                    i11++;
                }
            }
            if (c2175b == null) {
                throw new IllegalStateException("Missing profile key: ".concat(str));
            }
            c2175b.d = jM;
            int[] iArrG = g(byteArrayInputStream, iM);
            if (Arrays.equals(bArr, f21242h)) {
                c2175b.f21232e = iM;
                c2175b.f21235h = iArrG;
            }
        }
        return c2175bArr;
    }

    public static C2175b[] l(FileInputStream fileInputStream, byte[] bArr, String str) throws IOException {
        if (!Arrays.equals(bArr, f21239e)) {
            throw new IllegalStateException("Unsupported version");
        }
        int iM = (int) m(fileInputStream, 1);
        byte[] bArrH = h(fileInputStream, (int) m(fileInputStream, 4), (int) m(fileInputStream, 4));
        if (fileInputStream.read() > 0) {
            throw new IllegalStateException("Content found after the end of file");
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArrH);
        try {
            C2175b[] c2175bArrN = n(byteArrayInputStream, str, iM);
            byteArrayInputStream.close();
            return c2175bArrN;
        } catch (Throwable th) {
            try {
                byteArrayInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static long m(InputStream inputStream, int i7) throws IOException {
        byte[] bArrF = f(inputStream, i7);
        long j3 = 0;
        for (int i10 = 0; i10 < i7; i10++) {
            j3 += ((long) (bArrF[i10] & 255)) << (i10 * 8);
        }
        return j3;
    }

    public static C2175b[] n(ByteArrayInputStream byteArrayInputStream, String str, int i7) throws IOException {
        TreeMap treeMap;
        if (byteArrayInputStream.available() == 0) {
            return new C2175b[0];
        }
        C2175b[] c2175bArr = new C2175b[i7];
        for (int i10 = 0; i10 < i7; i10++) {
            int iM = (int) m(byteArrayInputStream, 2);
            int iM2 = (int) m(byteArrayInputStream, 2);
            c2175bArr[i10] = new C2175b(str, new String(f(byteArrayInputStream, iM), StandardCharsets.UTF_8), m(byteArrayInputStream, 4), iM2, (int) m(byteArrayInputStream, 4), (int) m(byteArrayInputStream, 4), new int[iM2], new TreeMap());
        }
        for (int i11 = 0; i11 < i7; i11++) {
            C2175b c2175b = c2175bArr[i11];
            int iAvailable = byteArrayInputStream.available() - c2175b.f21233f;
            int iM3 = 0;
            while (true) {
                int iAvailable2 = byteArrayInputStream.available();
                treeMap = c2175b.f21236i;
                if (iAvailable2 <= iAvailable) {
                    break;
                }
                iM3 += (int) m(byteArrayInputStream, 2);
                treeMap.put(Integer.valueOf(iM3), 1);
                for (int iM4 = (int) m(byteArrayInputStream, 2); iM4 > 0; iM4--) {
                    m(byteArrayInputStream, 2);
                    int iM5 = (int) m(byteArrayInputStream, 1);
                    if (iM5 != 6 && iM5 != 7) {
                        while (iM5 > 0) {
                            m(byteArrayInputStream, 1);
                            for (int iM6 = (int) m(byteArrayInputStream, 1); iM6 > 0; iM6--) {
                                m(byteArrayInputStream, 2);
                            }
                            iM5--;
                        }
                    }
                }
            }
            if (byteArrayInputStream.available() != iAvailable) {
                throw new IllegalStateException("Read too much data during profile line parse");
            }
            c2175b.f21235h = g(byteArrayInputStream, c2175b.f21232e);
            int i12 = c2175b.f21234g;
            BitSet bitSetValueOf = BitSet.valueOf(f(byteArrayInputStream, (((i12 * 2) + 7) & (-8)) / 8));
            for (int i13 = 0; i13 < i12; i13++) {
                int i14 = bitSetValueOf.get(i13) ? 2 : 0;
                if (bitSetValueOf.get(i13 + i12)) {
                    i14 |= 4;
                }
                if (i14 != 0) {
                    Integer num = (Integer) treeMap.get(Integer.valueOf(i13));
                    if (num == null) {
                        num = 0;
                    }
                    treeMap.put(Integer.valueOf(i13), Integer.valueOf(i14 | num.intValue()));
                }
            }
        }
        return c2175bArr;
    }

    /* JADX WARN: Finally extract failed */
    public static boolean o(ByteArrayOutputStream byteArrayOutputStream, byte[] bArr, C2175b[] c2175bArr) throws IOException {
        long j3;
        ArrayList arrayList;
        int length;
        byte[] bArr2 = d;
        int i7 = 0;
        if (!Arrays.equals(bArr, bArr2)) {
            byte[] bArr3 = f21239e;
            if (Arrays.equals(bArr, bArr3)) {
                byte[] bArrB = b(c2175bArr, bArr3);
                u(byteArrayOutputStream, c2175bArr.length, 1);
                u(byteArrayOutputStream, bArrB.length, 4);
                byte[] bArrA = a(bArrB);
                u(byteArrayOutputStream, bArrA.length, 4);
                byteArrayOutputStream.write(bArrA);
                return true;
            }
            byte[] bArr4 = f21241g;
            if (Arrays.equals(bArr, bArr4)) {
                u(byteArrayOutputStream, c2175bArr.length, 1);
                for (C2175b c2175b : c2175bArr) {
                    int size = c2175b.f21236i.size() * 4;
                    String strD = d(c2175b.f21230a, c2175b.f21231b, bArr4);
                    Charset charset = StandardCharsets.UTF_8;
                    v(byteArrayOutputStream, strD.getBytes(charset).length);
                    v(byteArrayOutputStream, c2175b.f21235h.length);
                    u(byteArrayOutputStream, size, 4);
                    u(byteArrayOutputStream, c2175b.c, 4);
                    byteArrayOutputStream.write(strD.getBytes(charset));
                    Iterator it = c2175b.f21236i.keySet().iterator();
                    while (it.hasNext()) {
                        v(byteArrayOutputStream, ((Integer) it.next()).intValue());
                        v(byteArrayOutputStream, 0);
                    }
                    for (int i10 : c2175b.f21235h) {
                        v(byteArrayOutputStream, i10);
                    }
                }
                return true;
            }
            byte[] bArr5 = f21240f;
            if (Arrays.equals(bArr, bArr5)) {
                byte[] bArrB2 = b(c2175bArr, bArr5);
                u(byteArrayOutputStream, c2175bArr.length, 1);
                u(byteArrayOutputStream, bArrB2.length, 4);
                byte[] bArrA2 = a(bArrB2);
                u(byteArrayOutputStream, bArrA2.length, 4);
                byteArrayOutputStream.write(bArrA2);
                return true;
            }
            byte[] bArr6 = f21242h;
            if (!Arrays.equals(bArr, bArr6)) {
                return false;
            }
            v(byteArrayOutputStream, c2175bArr.length);
            for (C2175b c2175b2 : c2175bArr) {
                String strD2 = d(c2175b2.f21230a, c2175b2.f21231b, bArr6);
                Charset charset2 = StandardCharsets.UTF_8;
                v(byteArrayOutputStream, strD2.getBytes(charset2).length);
                TreeMap treeMap = c2175b2.f21236i;
                v(byteArrayOutputStream, treeMap.size());
                v(byteArrayOutputStream, c2175b2.f21235h.length);
                u(byteArrayOutputStream, c2175b2.c, 4);
                byteArrayOutputStream.write(strD2.getBytes(charset2));
                Iterator it2 = treeMap.keySet().iterator();
                while (it2.hasNext()) {
                    v(byteArrayOutputStream, ((Integer) it2.next()).intValue());
                }
                for (int i11 : c2175b2.f21235h) {
                    v(byteArrayOutputStream, i11);
                }
            }
            return true;
        }
        ArrayList arrayList2 = new ArrayList(3);
        ArrayList arrayList3 = new ArrayList(3);
        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
        try {
            v(byteArrayOutputStream2, c2175bArr.length);
            int i12 = 2;
            int i13 = 2;
            for (C2175b c2175b3 : c2175bArr) {
                u(byteArrayOutputStream2, c2175b3.c, 4);
                u(byteArrayOutputStream2, c2175b3.d, 4);
                u(byteArrayOutputStream2, c2175b3.f21234g, 4);
                String strD3 = d(c2175b3.f21230a, c2175b3.f21231b, bArr2);
                Charset charset3 = StandardCharsets.UTF_8;
                int length2 = strD3.getBytes(charset3).length;
                v(byteArrayOutputStream2, length2);
                i13 = i13 + 14 + length2;
                byteArrayOutputStream2.write(strD3.getBytes(charset3));
            }
            byte[] byteArray = byteArrayOutputStream2.toByteArray();
            if (i13 != byteArray.length) {
                throw new IllegalStateException("Expected size " + i13 + ", does not match actual size " + byteArray.length);
            }
            C2184k c2184k = new C2184k(1, false, byteArray);
            byteArrayOutputStream2.close();
            arrayList2.add(c2184k);
            ByteArrayOutputStream byteArrayOutputStream3 = new ByteArrayOutputStream();
            int i14 = 0;
            int i15 = 0;
            while (i14 < c2175bArr.length) {
                try {
                    C2175b c2175b4 = c2175bArr[i14];
                    v(byteArrayOutputStream3, i14);
                    v(byteArrayOutputStream3, c2175b4.f21232e);
                    i15 = i15 + 4 + (c2175b4.f21232e * 2);
                    int[] iArr = c2175b4.f21235h;
                    int length3 = iArr.length;
                    int i16 = i7;
                    while (i7 < length3) {
                        int i17 = iArr[i7];
                        v(byteArrayOutputStream3, i17 - i16);
                        i7++;
                        i16 = i17;
                    }
                    i14++;
                    i7 = 0;
                } catch (Throwable th) {
                }
            }
            byte[] byteArray2 = byteArrayOutputStream3.toByteArray();
            if (i15 != byteArray2.length) {
                throw new IllegalStateException("Expected size " + i15 + ", does not match actual size " + byteArray2.length);
            }
            C2184k c2184k2 = new C2184k(3, true, byteArray2);
            byteArrayOutputStream3.close();
            arrayList2.add(c2184k2);
            byteArrayOutputStream3 = new ByteArrayOutputStream();
            int i18 = 0;
            int i19 = 0;
            while (i18 < c2175bArr.length) {
                try {
                    C2175b c2175b5 = c2175bArr[i18];
                    Iterator it3 = c2175b5.f21236i.entrySet().iterator();
                    int iIntValue = 0;
                    while (it3.hasNext()) {
                        iIntValue |= ((Integer) ((Map.Entry) it3.next()).getValue()).intValue();
                    }
                    ByteArrayOutputStream byteArrayOutputStream4 = new ByteArrayOutputStream();
                    try {
                        r(byteArrayOutputStream4, iIntValue, c2175b5);
                        byte[] byteArray3 = byteArrayOutputStream4.toByteArray();
                        byteArrayOutputStream4.close();
                        byteArrayOutputStream4 = new ByteArrayOutputStream();
                        try {
                            s(byteArrayOutputStream4, c2175b5);
                            byte[] byteArray4 = byteArrayOutputStream4.toByteArray();
                            byteArrayOutputStream4.close();
                            v(byteArrayOutputStream3, i18);
                            int length4 = byteArray3.length + i12 + byteArray4.length;
                            int i20 = i19 + 6;
                            ArrayList arrayList4 = arrayList3;
                            u(byteArrayOutputStream3, length4, 4);
                            v(byteArrayOutputStream3, iIntValue);
                            byteArrayOutputStream3.write(byteArray3);
                            byteArrayOutputStream3.write(byteArray4);
                            i19 = i20 + length4;
                            i18++;
                            arrayList3 = arrayList4;
                            i12 = 2;
                        } finally {
                        }
                    } finally {
                    }
                } finally {
                    try {
                        byteArrayOutputStream3.close();
                        throw th;
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
            }
            ArrayList arrayList5 = arrayList3;
            byte[] byteArray5 = byteArrayOutputStream3.toByteArray();
            if (i19 != byteArray5.length) {
                throw new IllegalStateException("Expected size " + i19 + ", does not match actual size " + byteArray5.length);
            }
            C2184k c2184k3 = new C2184k(4, true, byteArray5);
            byteArrayOutputStream3.close();
            arrayList2.add(c2184k3);
            long j10 = 4;
            long size2 = j10 + j10 + 4 + ((long) (arrayList2.size() * 16));
            u(byteArrayOutputStream, arrayList2.size(), 4);
            int i21 = 0;
            while (i21 < arrayList2.size()) {
                C2184k c2184k4 = (C2184k) arrayList2.get(i21);
                int i22 = c2184k4.f21251a;
                if (i22 == 1) {
                    j3 = 0;
                } else if (i22 == 2) {
                    j3 = 1;
                } else if (i22 == 3) {
                    j3 = 2;
                } else if (i22 == 4) {
                    j3 = 3;
                } else {
                    if (i22 != 5) {
                        throw null;
                    }
                    j3 = 4;
                }
                u(byteArrayOutputStream, j3, 4);
                u(byteArrayOutputStream, size2, 4);
                byte[] bArr7 = c2184k4.f21252b;
                if (c2184k4.c) {
                    long length5 = bArr7.length;
                    byte[] bArrA3 = a(bArr7);
                    arrayList = arrayList5;
                    arrayList.add(bArrA3);
                    u(byteArrayOutputStream, bArrA3.length, 4);
                    u(byteArrayOutputStream, length5, 4);
                    length = bArrA3.length;
                } else {
                    arrayList = arrayList5;
                    arrayList.add(bArr7);
                    u(byteArrayOutputStream, bArr7.length, 4);
                    u(byteArrayOutputStream, 0L, 4);
                    length = bArr7.length;
                }
                size2 += (long) length;
                i21++;
                arrayList5 = arrayList;
            }
            ArrayList arrayList6 = arrayList5;
            for (int i23 = 0; i23 < arrayList6.size(); i23++) {
                byteArrayOutputStream.write((byte[]) arrayList6.get(i23));
            }
            return true;
        } catch (Throwable th3) {
            try {
                byteArrayOutputStream2.close();
                throw th3;
            } catch (Throwable th4) {
                th3.addSuppressed(th4);
                throw th3;
            }
        }
    }

    public static void p(ByteArrayOutputStream byteArrayOutputStream, C2175b c2175b) throws IOException {
        s(byteArrayOutputStream, c2175b);
        int[] iArr = c2175b.f21235h;
        int length = iArr.length;
        int i7 = 0;
        int i10 = 0;
        while (i7 < length) {
            int i11 = iArr[i7];
            v(byteArrayOutputStream, i11 - i10);
            i7++;
            i10 = i11;
        }
        int i12 = c2175b.f21234g;
        byte[] bArr = new byte[(((i12 * 2) + 7) & (-8)) / 8];
        for (Map.Entry entry : c2175b.f21236i.entrySet()) {
            int iIntValue = ((Integer) entry.getKey()).intValue();
            int iIntValue2 = ((Integer) entry.getValue()).intValue();
            if ((iIntValue2 & 2) != 0) {
                int i13 = iIntValue / 8;
                bArr[i13] = (byte) (bArr[i13] | (1 << (iIntValue % 8)));
            }
            if ((iIntValue2 & 4) != 0) {
                int i14 = iIntValue + i12;
                int i15 = i14 / 8;
                bArr[i15] = (byte) ((1 << (i14 % 8)) | bArr[i15]);
            }
        }
        byteArrayOutputStream.write(bArr);
    }

    public static void q(ByteArrayOutputStream byteArrayOutputStream, C2175b c2175b, String str) throws IOException {
        Charset charset = StandardCharsets.UTF_8;
        v(byteArrayOutputStream, str.getBytes(charset).length);
        v(byteArrayOutputStream, c2175b.f21232e);
        u(byteArrayOutputStream, c2175b.f21233f, 4);
        u(byteArrayOutputStream, c2175b.c, 4);
        u(byteArrayOutputStream, c2175b.f21234g, 4);
        byteArrayOutputStream.write(str.getBytes(charset));
    }

    public static void r(ByteArrayOutputStream byteArrayOutputStream, int i7, C2175b c2175b) throws IOException {
        int iBitCount = Integer.bitCount(i7 & (-2));
        int i10 = c2175b.f21234g;
        byte[] bArr = new byte[(((iBitCount * i10) + 7) & (-8)) / 8];
        for (Map.Entry entry : c2175b.f21236i.entrySet()) {
            int iIntValue = ((Integer) entry.getKey()).intValue();
            int iIntValue2 = ((Integer) entry.getValue()).intValue();
            int i11 = 0;
            for (int i12 = 1; i12 <= 4; i12 <<= 1) {
                if (i12 != 1 && (i12 & i7) != 0) {
                    if ((i12 & iIntValue2) == i12) {
                        int i13 = (i11 * i10) + iIntValue;
                        int i14 = i13 / 8;
                        bArr[i14] = (byte) ((1 << (i13 % 8)) | bArr[i14]);
                    }
                    i11++;
                }
            }
        }
        byteArrayOutputStream.write(bArr);
    }

    public static void s(ByteArrayOutputStream byteArrayOutputStream, C2175b c2175b) throws IOException {
        int i7 = 0;
        for (Map.Entry entry : c2175b.f21236i.entrySet()) {
            int iIntValue = ((Integer) entry.getKey()).intValue();
            if ((((Integer) entry.getValue()).intValue() & 1) != 0) {
                v(byteArrayOutputStream, iIntValue - i7);
                v(byteArrayOutputStream, 0);
                i7 = iIntValue;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:126:0x01c8  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x01d1  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x021b  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x0225  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0229  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x02e1  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x02f6 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:255:0x02fa  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:306:0x01da A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:308:0x010f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:92:0x016c  */
    /* JADX WARN: Type inference failed for: r11v3 */
    /* JADX WARN: Type inference failed for: r11v4, types: [boolean] */
    /* JADX WARN: Type inference failed for: r11v5 */
    /* JADX WARN: Type inference failed for: r8v0, types: [q3.d] */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v11 */
    /* JADX WARN: Type inference failed for: r8v14 */
    /* JADX WARN: Type inference failed for: r8v15 */
    /* JADX WARN: Type inference failed for: r8v16 */
    /* JADX WARN: Type inference failed for: r8v17 */
    /* JADX WARN: Type inference failed for: r8v18 */
    /* JADX WARN: Type inference failed for: r8v19 */
    /* JADX WARN: Type inference failed for: r8v2 */
    /* JADX WARN: Type inference failed for: r8v20 */
    /* JADX WARN: Type inference failed for: r8v21 */
    /* JADX WARN: Type inference failed for: r8v3 */
    /* JADX WARN: Type inference failed for: r8v4 */
    /* JADX WARN: Type inference failed for: r8v5 */
    /* JADX WARN: Type inference failed for: r8v6 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void t(android.content.Context r18, java.util.concurrent.Executor r19, q3.InterfaceC2177d r20, boolean r21) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 778
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: q3.AbstractC2178e.t(android.content.Context, java.util.concurrent.Executor, q3.d, boolean):void");
    }

    public static void u(ByteArrayOutputStream byteArrayOutputStream, long j3, int i7) throws IOException {
        byte[] bArr = new byte[i7];
        for (int i10 = 0; i10 < i7; i10++) {
            bArr[i10] = (byte) ((j3 >> (i10 * 8)) & 255);
        }
        byteArrayOutputStream.write(bArr);
    }

    public static void v(ByteArrayOutputStream byteArrayOutputStream, int i7) throws IOException {
        u(byteArrayOutputStream, i7, 2);
    }
}
