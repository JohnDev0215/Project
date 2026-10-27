package androidx.profileinstaller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import p.l13;
import p.n90;
import p.u4;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public abstract class a {
    public static final byte[] a = {112, 114, 111, 0};
    public static final byte[] b = {112, 114, 109, 0};

    public static byte[] a(n90[] n90VarArr, byte[] bArr) throws IOException {
        int i = 0;
        int length = 0;
        for (n90 n90Var : n90VarArr) {
            length += ((((n90Var.g * 2) + 7) & (-8)) / 8) + (n90Var.e * 2) + b(n90Var.a, n90Var.b, bArr).getBytes(StandardCharsets.UTF_8).length + 16 + n90Var.f;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(length);
        if (Arrays.equals(bArr, v03.h)) {
            int length2 = n90VarArr.length;
            while (i < length2) {
                n90 n90Var2 = n90VarArr[i];
                l(byteArrayOutputStream, n90Var2, b(n90Var2.a, n90Var2.b, bArr));
                n(byteArrayOutputStream, n90Var2);
                k(byteArrayOutputStream, n90Var2);
                m(byteArrayOutputStream, n90Var2);
                i++;
            }
        } else {
            for (n90 n90Var3 : n90VarArr) {
                l(byteArrayOutputStream, n90Var3, b(n90Var3.a, n90Var3.b, bArr));
            }
            int length3 = n90VarArr.length;
            while (i < length3) {
                n90 n90Var4 = n90VarArr[i];
                n(byteArrayOutputStream, n90Var4);
                k(byteArrayOutputStream, n90Var4);
                m(byteArrayOutputStream, n90Var4);
                i++;
            }
        }
        if (byteArrayOutputStream.size() == length) {
            return byteArrayOutputStream.toByteArray();
        }
        throw new IllegalStateException("The bytes saved do not match expectation. actual=" + byteArrayOutputStream.size() + " expected=" + length);
    }

    public static String b(String str, String str2, byte[] bArr) {
        byte[] bArr2 = v03.j;
        boolean zEquals = Arrays.equals(bArr, bArr2);
        byte[] bArr3 = v03.i;
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
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        return u4.m(sb, (Arrays.equals(bArr, bArr2) || Arrays.equals(bArr, bArr3)) ? ":" : "!", str2);
    }

    public static int c(int i, int i2, int i3) {
        if (i == 1) {
            throw new IllegalStateException("HOT methods are not stored in the bitmap");
        }
        if (i == 2) {
            return i2;
        }
        if (i == 4) {
            return i2 + i3;
        }
        throw new IllegalStateException(u4.e("Unexpected flag: ", i));
    }

    public static int[] d(int i, ByteArrayInputStream byteArrayInputStream) {
        int[] iArr = new int[i];
        int iK = 0;
        for (int i2 = 0; i2 < i; i2++) {
            iK += (int) l13.k(2, byteArrayInputStream);
            iArr[i2] = iK;
        }
        return iArr;
    }

    public static n90[] e(FileInputStream fileInputStream, byte[] bArr, byte[] bArr2, n90[] n90VarArr) throws IOException {
        byte[] bArr3 = v03.k;
        if (!Arrays.equals(bArr, bArr3)) {
            if (!Arrays.equals(bArr, v03.l)) {
                throw new IllegalStateException("Unsupported meta version");
            }
            int iK = (int) l13.k(2, fileInputStream);
            byte[] bArrJ = l13.j(fileInputStream, (int) l13.k(4, fileInputStream), (int) l13.k(4, fileInputStream));
            if (fileInputStream.read() > 0) {
                throw new IllegalStateException("Content found after the end of file");
            }
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArrJ);
            try {
                n90[] n90VarArrG = g(byteArrayInputStream, bArr2, iK, n90VarArr);
                byteArrayInputStream.close();
                return n90VarArrG;
            } catch (Throwable th) {
                try {
                    byteArrayInputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
        if (Arrays.equals(v03.f, bArr2)) {
            throw new IllegalStateException("Requires new Baseline Profile Metadata. Please rebuild the APK with Android Gradle Plugin 7.2 Canary 7 or higher");
        }
        if (!Arrays.equals(bArr, bArr3)) {
            throw new IllegalStateException("Unsupported meta version");
        }
        int iK2 = (int) l13.k(1, fileInputStream);
        byte[] bArrJ2 = l13.j(fileInputStream, (int) l13.k(4, fileInputStream), (int) l13.k(4, fileInputStream));
        if (fileInputStream.read() > 0) {
            throw new IllegalStateException("Content found after the end of file");
        }
        ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(bArrJ2);
        try {
            n90[] n90VarArrF = f(byteArrayInputStream2, iK2, n90VarArr);
            byteArrayInputStream2.close();
            return n90VarArrF;
        } catch (Throwable th3) {
            try {
                byteArrayInputStream2.close();
            } catch (Throwable th4) {
                th3.addSuppressed(th4);
            }
            throw th3;
        }
    }

    public static n90[] f(ByteArrayInputStream byteArrayInputStream, int i, n90[] n90VarArr) {
        if (byteArrayInputStream.available() == 0) {
            return new n90[0];
        }
        if (i != n90VarArr.length) {
            throw new IllegalStateException("Mismatched number of dex files found in metadata");
        }
        String[] strArr = new String[i];
        int[] iArr = new int[i];
        for (int i2 = 0; i2 < i; i2++) {
            int iK = (int) l13.k(2, byteArrayInputStream);
            iArr[i2] = (int) l13.k(2, byteArrayInputStream);
            strArr[i2] = new String(l13.i(iK, byteArrayInputStream), StandardCharsets.UTF_8);
        }
        for (int i3 = 0; i3 < i; i3++) {
            n90 n90Var = n90VarArr[i3];
            if (!n90Var.b.equals(strArr[i3])) {
                throw new IllegalStateException("Order of dexfiles in metadata did not match baseline");
            }
            int i4 = iArr[i3];
            n90Var.e = i4;
            n90Var.h = d(i4, byteArrayInputStream);
        }
        return n90VarArr;
    }

    public static n90[] g(ByteArrayInputStream byteArrayInputStream, byte[] bArr, int i, n90[] n90VarArr) throws IOException {
        if (byteArrayInputStream.available() == 0) {
            return new n90[0];
        }
        if (i != n90VarArr.length) {
            throw new IllegalStateException("Mismatched number of dex files found in metadata");
        }
        for (int i2 = 0; i2 < i; i2++) {
            l13.k(2, byteArrayInputStream);
            String str = new String(l13.i((int) l13.k(2, byteArrayInputStream), byteArrayInputStream), StandardCharsets.UTF_8);
            long jK = l13.k(4, byteArrayInputStream);
            int iK = (int) l13.k(2, byteArrayInputStream);
            n90 n90Var = null;
            if (n90VarArr.length > 0) {
                int iIndexOf = str.indexOf("!");
                if (iIndexOf < 0) {
                    iIndexOf = str.indexOf(":");
                }
                String strSubstring = iIndexOf > 0 ? str.substring(iIndexOf + 1) : str;
                int i3 = 0;
                while (true) {
                    if (i3 >= n90VarArr.length) {
                        break;
                    }
                    if (n90VarArr[i3].b.equals(strSubstring)) {
                        n90Var = n90VarArr[i3];
                        break;
                    }
                    i3++;
                }
            }
            if (n90Var == null) {
                throw new IllegalStateException("Missing profile key: ".concat(str));
            }
            n90Var.d = jK;
            int[] iArrD = d(iK, byteArrayInputStream);
            if (Arrays.equals(bArr, v03.j)) {
                n90Var.e = iK;
                n90Var.h = iArrD;
            }
        }
        return n90VarArr;
    }

    public static n90[] h(FileInputStream fileInputStream, byte[] bArr, String str) throws IOException {
        if (!Arrays.equals(bArr, v03.g)) {
            throw new IllegalStateException("Unsupported version");
        }
        int iK = (int) l13.k(1, fileInputStream);
        byte[] bArrJ = l13.j(fileInputStream, (int) l13.k(4, fileInputStream), (int) l13.k(4, fileInputStream));
        if (fileInputStream.read() > 0) {
            throw new IllegalStateException("Content found after the end of file");
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArrJ);
        try {
            n90[] n90VarArrI = i(byteArrayInputStream, str, iK);
            byteArrayInputStream.close();
            return n90VarArrI;
        } catch (Throwable th) {
            try {
                byteArrayInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static n90[] i(ByteArrayInputStream byteArrayInputStream, String str, int i) throws IOException {
        TreeMap treeMap;
        if (byteArrayInputStream.available() == 0) {
            return new n90[0];
        }
        n90[] n90VarArr = new n90[i];
        for (int i2 = 0; i2 < i; i2++) {
            int iK = (int) l13.k(2, byteArrayInputStream);
            int iK2 = (int) l13.k(2, byteArrayInputStream);
            n90VarArr[i2] = new n90(str, new String(l13.i(iK, byteArrayInputStream), StandardCharsets.UTF_8), l13.k(4, byteArrayInputStream), iK2, (int) l13.k(4, byteArrayInputStream), (int) l13.k(4, byteArrayInputStream), new int[iK2], new TreeMap());
        }
        for (int i3 = 0; i3 < i; i3++) {
            n90 n90Var = n90VarArr[i3];
            int iAvailable = byteArrayInputStream.available() - n90Var.f;
            int iK3 = 0;
            while (true) {
                int iAvailable2 = byteArrayInputStream.available();
                treeMap = n90Var.i;
                if (iAvailable2 <= iAvailable) {
                    break;
                }
                iK3 += (int) l13.k(2, byteArrayInputStream);
                treeMap.put(Integer.valueOf(iK3), 1);
                for (int iK4 = (int) l13.k(2, byteArrayInputStream); iK4 > 0; iK4--) {
                    l13.k(2, byteArrayInputStream);
                    int iK5 = (int) l13.k(1, byteArrayInputStream);
                    if (iK5 != 6 && iK5 != 7) {
                        while (iK5 > 0) {
                            l13.k(1, byteArrayInputStream);
                            for (int iK6 = (int) l13.k(1, byteArrayInputStream); iK6 > 0; iK6--) {
                                l13.k(2, byteArrayInputStream);
                            }
                            iK5--;
                        }
                    }
                }
            }
            if (byteArrayInputStream.available() != iAvailable) {
                throw new IllegalStateException("Read too much data during profile line parse");
            }
            n90Var.h = d(n90Var.e, byteArrayInputStream);
            int i4 = n90Var.g;
            BitSet bitSetValueOf = BitSet.valueOf(l13.i((((i4 * 2) + 7) & (-8)) / 8, byteArrayInputStream));
            for (int i5 = 0; i5 < i4; i5++) {
                int i6 = bitSetValueOf.get(c(2, i5, i4)) ? 2 : 0;
                if (bitSetValueOf.get(c(4, i5, i4))) {
                    i6 |= 4;
                }
                if (i6 != 0) {
                    Integer num = (Integer) treeMap.get(Integer.valueOf(i5));
                    if (num == null) {
                        num = 0;
                    }
                    treeMap.put(Integer.valueOf(i5), Integer.valueOf(i6 | num.intValue()));
                }
            }
        }
        return n90VarArr;
    }

    /* JADX WARN: Finally extract failed */
    public static boolean j(ByteArrayOutputStream byteArrayOutputStream, byte[] bArr, n90[] n90VarArr) throws IOException {
        ArrayList arrayList;
        int length;
        byte[] bArr2 = v03.f;
        int i = 0;
        if (!Arrays.equals(bArr, bArr2)) {
            byte[] bArr3 = v03.g;
            if (Arrays.equals(bArr, bArr3)) {
                byte[] bArrA = a(n90VarArr, bArr3);
                l13.s(byteArrayOutputStream, n90VarArr.length, 1);
                l13.s(byteArrayOutputStream, bArrA.length, 4);
                byte[] bArrB = l13.b(bArrA);
                l13.s(byteArrayOutputStream, bArrB.length, 4);
                byteArrayOutputStream.write(bArrB);
                return true;
            }
            byte[] bArr4 = v03.i;
            if (Arrays.equals(bArr, bArr4)) {
                l13.s(byteArrayOutputStream, n90VarArr.length, 1);
                for (n90 n90Var : n90VarArr) {
                    int size = n90Var.i.size() * 4;
                    String strB = b(n90Var.a, n90Var.b, bArr4);
                    Charset charset = StandardCharsets.UTF_8;
                    l13.t(byteArrayOutputStream, strB.getBytes(charset).length);
                    l13.t(byteArrayOutputStream, n90Var.h.length);
                    l13.s(byteArrayOutputStream, size, 4);
                    l13.s(byteArrayOutputStream, n90Var.c, 4);
                    byteArrayOutputStream.write(strB.getBytes(charset));
                    Iterator it = n90Var.i.keySet().iterator();
                    while (it.hasNext()) {
                        l13.t(byteArrayOutputStream, ((Integer) it.next()).intValue());
                        l13.t(byteArrayOutputStream, 0);
                    }
                    for (int i2 : n90Var.h) {
                        l13.t(byteArrayOutputStream, i2);
                    }
                }
                return true;
            }
            byte[] bArr5 = v03.h;
            if (Arrays.equals(bArr, bArr5)) {
                byte[] bArrA2 = a(n90VarArr, bArr5);
                l13.s(byteArrayOutputStream, n90VarArr.length, 1);
                l13.s(byteArrayOutputStream, bArrA2.length, 4);
                byte[] bArrB2 = l13.b(bArrA2);
                l13.s(byteArrayOutputStream, bArrB2.length, 4);
                byteArrayOutputStream.write(bArrB2);
                return true;
            }
            byte[] bArr6 = v03.j;
            if (!Arrays.equals(bArr, bArr6)) {
                return false;
            }
            l13.t(byteArrayOutputStream, n90VarArr.length);
            for (n90 n90Var2 : n90VarArr) {
                String strB2 = b(n90Var2.a, n90Var2.b, bArr6);
                Charset charset2 = StandardCharsets.UTF_8;
                l13.t(byteArrayOutputStream, strB2.getBytes(charset2).length);
                TreeMap treeMap = n90Var2.i;
                l13.t(byteArrayOutputStream, treeMap.size());
                l13.t(byteArrayOutputStream, n90Var2.h.length);
                l13.s(byteArrayOutputStream, n90Var2.c, 4);
                byteArrayOutputStream.write(strB2.getBytes(charset2));
                Iterator it2 = treeMap.keySet().iterator();
                while (it2.hasNext()) {
                    l13.t(byteArrayOutputStream, ((Integer) it2.next()).intValue());
                }
                for (int i3 : n90Var2.h) {
                    l13.t(byteArrayOutputStream, i3);
                }
            }
            return true;
        }
        ArrayList arrayList2 = new ArrayList(3);
        ArrayList arrayList3 = new ArrayList(3);
        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
        try {
            l13.t(byteArrayOutputStream2, n90VarArr.length);
            int i4 = 2;
            int i5 = 2;
            for (n90 n90Var3 : n90VarArr) {
                l13.s(byteArrayOutputStream2, n90Var3.c, 4);
                l13.s(byteArrayOutputStream2, n90Var3.d, 4);
                l13.s(byteArrayOutputStream2, n90Var3.g, 4);
                String strB3 = b(n90Var3.a, n90Var3.b, bArr2);
                Charset charset3 = StandardCharsets.UTF_8;
                int length2 = strB3.getBytes(charset3).length;
                l13.t(byteArrayOutputStream2, length2);
                i5 = i5 + 14 + length2;
                byteArrayOutputStream2.write(strB3.getBytes(charset3));
            }
            byte[] byteArray = byteArrayOutputStream2.toByteArray();
            if (i5 != byteArray.length) {
                throw new IllegalStateException("Expected size " + i5 + ", does not match actual size " + byteArray.length);
            }
            b bVar = new b(FileSectionType.c, byteArray, false);
            byteArrayOutputStream2.close();
            arrayList2.add(bVar);
            ByteArrayOutputStream byteArrayOutputStream3 = new ByteArrayOutputStream();
            int i6 = 0;
            for (int i7 = 0; i7 < n90VarArr.length; i7++) {
                try {
                    n90 n90Var4 = n90VarArr[i7];
                    l13.t(byteArrayOutputStream3, i7);
                    l13.t(byteArrayOutputStream3, n90Var4.e);
                    i6 = i6 + 4 + (n90Var4.e * 2);
                    k(byteArrayOutputStream3, n90Var4);
                } catch (Throwable th) {
                }
            }
            byte[] byteArray2 = byteArrayOutputStream3.toByteArray();
            if (i6 != byteArray2.length) {
                throw new IllegalStateException("Expected size " + i6 + ", does not match actual size " + byteArray2.length);
            }
            b bVar2 = new b(FileSectionType.e, byteArray2, true);
            byteArrayOutputStream3.close();
            arrayList2.add(bVar2);
            byteArrayOutputStream3 = new ByteArrayOutputStream();
            int i8 = 0;
            int i9 = 0;
            while (i8 < n90VarArr.length) {
                try {
                    n90 n90Var5 = n90VarArr[i8];
                    Iterator it3 = n90Var5.i.entrySet().iterator();
                    int iIntValue = i;
                    while (it3.hasNext()) {
                        iIntValue |= ((Integer) ((Map.Entry) it3.next()).getValue()).intValue();
                    }
                    ByteArrayOutputStream byteArrayOutputStream4 = new ByteArrayOutputStream();
                    try {
                        m(byteArrayOutputStream4, n90Var5);
                        byte[] byteArray3 = byteArrayOutputStream4.toByteArray();
                        byteArrayOutputStream4.close();
                        byteArrayOutputStream4 = new ByteArrayOutputStream();
                        try {
                            n(byteArrayOutputStream4, n90Var5);
                            byte[] byteArray4 = byteArrayOutputStream4.toByteArray();
                            byteArrayOutputStream4.close();
                            l13.t(byteArrayOutputStream3, i8);
                            int length3 = byteArray3.length + i4 + byteArray4.length;
                            int i10 = i9 + 6;
                            ArrayList arrayList4 = arrayList3;
                            l13.s(byteArrayOutputStream3, length3, 4);
                            l13.t(byteArrayOutputStream3, iIntValue);
                            byteArrayOutputStream3.write(byteArray3);
                            byteArrayOutputStream3.write(byteArray4);
                            i9 = i10 + length3;
                            i8++;
                            arrayList3 = arrayList4;
                            i = 0;
                            i4 = 2;
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
            if (i9 != byteArray5.length) {
                throw new IllegalStateException("Expected size " + i9 + ", does not match actual size " + byteArray5.length);
            }
            b bVar3 = new b(FileSectionType.k, byteArray5, true);
            byteArrayOutputStream3.close();
            arrayList2.add(bVar3);
            long j = 4;
            long size2 = j + j + 4 + ((long) (arrayList2.size() * 16));
            l13.s(byteArrayOutputStream, arrayList2.size(), 4);
            int i11 = 0;
            while (i11 < arrayList2.size()) {
                b bVar4 = (b) arrayList2.get(i11);
                l13.s(byteArrayOutputStream, bVar4.a.a(), 4);
                l13.s(byteArrayOutputStream, size2, 4);
                boolean z = bVar4.c;
                byte[] bArr7 = bVar4.b;
                if (z) {
                    long length4 = bArr7.length;
                    byte[] bArrB3 = l13.b(bArr7);
                    arrayList = arrayList5;
                    arrayList.add(bArrB3);
                    l13.s(byteArrayOutputStream, bArrB3.length, 4);
                    l13.s(byteArrayOutputStream, length4, 4);
                    length = bArrB3.length;
                } else {
                    arrayList = arrayList5;
                    arrayList.add(bArr7);
                    l13.s(byteArrayOutputStream, bArr7.length, 4);
                    l13.s(byteArrayOutputStream, 0L, 4);
                    length = bArr7.length;
                }
                size2 += (long) length;
                i11++;
                arrayList5 = arrayList;
            }
            ArrayList arrayList6 = arrayList5;
            for (int i12 = 0; i12 < arrayList6.size(); i12++) {
                byteArrayOutputStream.write((byte[]) arrayList6.get(i12));
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

    public static void k(ByteArrayOutputStream byteArrayOutputStream, n90 n90Var) throws IOException {
        int iIntValue = 0;
        for (int i : n90Var.h) {
            Integer numValueOf = Integer.valueOf(i);
            l13.t(byteArrayOutputStream, numValueOf.intValue() - iIntValue);
            iIntValue = numValueOf.intValue();
        }
    }

    public static void l(ByteArrayOutputStream byteArrayOutputStream, n90 n90Var, String str) throws IOException {
        Charset charset = StandardCharsets.UTF_8;
        l13.t(byteArrayOutputStream, str.getBytes(charset).length);
        l13.t(byteArrayOutputStream, n90Var.e);
        l13.s(byteArrayOutputStream, n90Var.f, 4);
        l13.s(byteArrayOutputStream, n90Var.c, 4);
        l13.s(byteArrayOutputStream, n90Var.g, 4);
        byteArrayOutputStream.write(str.getBytes(charset));
    }

    public static void m(ByteArrayOutputStream byteArrayOutputStream, n90 n90Var) throws IOException {
        byte[] bArr = new byte[(((n90Var.g * 2) + 7) & (-8)) / 8];
        for (Map.Entry entry : n90Var.i.entrySet()) {
            int iIntValue = ((Integer) entry.getKey()).intValue();
            int iIntValue2 = ((Integer) entry.getValue()).intValue();
            int i = iIntValue2 & 2;
            int i2 = n90Var.g;
            if (i != 0) {
                int iC = c(2, iIntValue, i2);
                int i3 = iC / 8;
                bArr[i3] = (byte) ((1 << (iC % 8)) | bArr[i3]);
            }
            if ((iIntValue2 & 4) != 0) {
                int iC2 = c(4, iIntValue, i2);
                int i4 = iC2 / 8;
                bArr[i4] = (byte) ((1 << (iC2 % 8)) | bArr[i4]);
            }
        }
        byteArrayOutputStream.write(bArr);
    }

    public static void n(ByteArrayOutputStream byteArrayOutputStream, n90 n90Var) throws IOException {
        int i = 0;
        for (Map.Entry entry : n90Var.i.entrySet()) {
            int iIntValue = ((Integer) entry.getKey()).intValue();
            if ((((Integer) entry.getValue()).intValue() & 1) != 0) {
                l13.t(byteArrayOutputStream, iIntValue - i);
                l13.t(byteArrayOutputStream, 0);
                i = iIntValue;
            }
        }
    }
}
