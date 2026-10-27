package com.facebook.soloader;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes6.dex */
public final class NativeDeps {
    private static final int LIB_PREFIX_LEN = 3;
    private static final int LIB_SUFFIX_LEN = 3;

    @Nullable
    private static byte[] sEncodedDeps = null;
    private static volatile boolean sInitialized = false;
    private static Map<Integer, List<Integer>> sPrecomputedDeps = null;
    private static List<Integer> sPrecomputedLibs = null;
    private static volatile boolean sUseDepsFileAsync = false;
    private static final int LIB_PREFIX_SUFFIX_LEN = 3 + 3;
    private static final ReentrantReadWriteLock sWaitForDepsFileLock = new ReentrantReadWriteLock();

    public static String[] getDependencies(String str, ElfByteChannel elfByteChannel) throws IOException {
        String[] strArrAwaitGetDepsFromPrecomputedDeps = awaitGetDepsFromPrecomputedDeps(str);
        return strArrAwaitGetDepsFromPrecomputedDeps != null ? strArrAwaitGetDepsFromPrecomputedDeps : MinElf.extract_DT_NEEDED(elfByteChannel);
    }

    @Nullable
    private static String[] awaitGetDepsFromPrecomputedDeps(String str) {
        if (sInitialized) {
            return tryGetDepsFromPrecomputedDeps(str);
        }
        if (!sUseDepsFileAsync) {
            return null;
        }
        ReentrantReadWriteLock reentrantReadWriteLock = sWaitForDepsFileLock;
        reentrantReadWriteLock.readLock().lock();
        try {
            String[] strArrTryGetDepsFromPrecomputedDeps = tryGetDepsFromPrecomputedDeps(str);
            reentrantReadWriteLock.readLock().unlock();
            return strArrTryGetDepsFromPrecomputedDeps;
        } catch (Throwable th) {
            sWaitForDepsFileLock.readLock().unlock();
            throw th;
        }
    }

    private static boolean libIsAtOffset(String str, int i) {
        int i2;
        int i3 = LIB_PREFIX_LEN;
        while (true) {
            int length = str.length();
            i2 = LIB_SUFFIX_LEN;
            if (i3 >= length - i2 || i >= sEncodedDeps.length || (str.codePointAt(i3) & 255) != sEncodedDeps[i]) {
                break;
            }
            i3++;
            i++;
        }
        return i3 == str.length() - i2;
    }

    private static int hashLib(String str) {
        int iCodePointAt = 5381;
        for (int i = LIB_PREFIX_LEN; i < str.length() - LIB_SUFFIX_LEN; i++) {
            iCodePointAt = str.codePointAt(i) + (iCodePointAt << 5) + iCodePointAt;
        }
        return iCodePointAt;
    }

    private static int getOffsetForLib(String str) {
        List<Integer> list = sPrecomputedDeps.get(Integer.valueOf(hashLib(str)));
        if (list == null) {
            return -1;
        }
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            int iIntValue = it.next().intValue();
            if (libIsAtOffset(str, iIntValue)) {
                return iIntValue;
            }
        }
        return -1;
    }

    @Nullable
    private static String getLibString(int i) {
        if (i >= sPrecomputedLibs.size()) {
            return null;
        }
        int iIntValue = sPrecomputedLibs.get(i).intValue();
        int i2 = iIntValue;
        while (true) {
            byte[] bArr = sEncodedDeps;
            if (i2 >= bArr.length || bArr[i2] <= 32) {
                break;
            }
            i2++;
        }
        int i3 = (i2 - iIntValue) + LIB_PREFIX_SUFFIX_LEN;
        char[] cArr = new char[i3];
        cArr[0] = 'l';
        cArr[1] = 'i';
        cArr[2] = 'b';
        for (int i4 = 0; i4 < i3 - LIB_PREFIX_SUFFIX_LEN; i4++) {
            cArr[LIB_PREFIX_LEN + i4] = (char) sEncodedDeps[iIntValue + i4];
        }
        cArr[i3 - 3] = '.';
        cArr[i3 - 2] = 's';
        cArr[i3 - 1] = 'o';
        return new String(cArr);
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x003e, code lost:
    
        if (r2 == false) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0040, code lost:
    
        r6 = getLibString(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0044, code lost:
    
        if (r6 != null) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0046, code lost:
    
        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0047, code lost:
    
        r0.add(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x004e, code lost:
    
        if (r0.size() != 0) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0050, code lost:
    
        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x005d, code lost:
    
        return (java.lang.String[]) r0.toArray(new java.lang.String[r0.size()]);
     */
    @javax.annotation.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String[] getDepsForLibAtOffset(int r6, int r7) {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            int r6 = r6 + r7
            int r7 = com.facebook.soloader.NativeDeps.LIB_PREFIX_SUFFIX_LEN
            int r6 = r6 - r7
            r7 = 0
            r1 = r7
            r2 = r1
        Lc:
            byte[] r3 = com.facebook.soloader.NativeDeps.sEncodedDeps
            int r4 = r3.length
            r5 = 0
            if (r6 >= r4) goto L3e
            r3 = r3[r6]
            r4 = 10
            if (r3 == r4) goto L3e
            r4 = 32
            if (r3 != r4) goto L2b
            if (r2 == 0) goto L3a
            java.lang.String r1 = getLibString(r1)
            if (r1 != 0) goto L25
            return r5
        L25:
            r0.add(r1)
            r1 = r7
            r2 = r1
            goto L3a
        L2b:
            r2 = 48
            if (r3 < r2) goto L3d
            r2 = 57
            if (r3 <= r2) goto L34
            goto L3d
        L34:
            int r1 = r1 * 10
            int r3 = r3 + (-48)
            int r1 = r1 + r3
            r2 = 1
        L3a:
            int r6 = r6 + 1
            goto Lc
        L3d:
            return r5
        L3e:
            if (r2 == 0) goto L4a
            java.lang.String r6 = getLibString(r1)
            if (r6 != 0) goto L47
            return r5
        L47:
            r0.add(r6)
        L4a:
            int r6 = r0.size()
            if (r6 != 0) goto L51
            return r5
        L51:
            int r6 = r0.size()
            java.lang.String[] r6 = new java.lang.String[r6]
            java.lang.Object[] r6 = r0.toArray(r6)
            java.lang.String[] r6 = (java.lang.String[]) r6
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.NativeDeps.getDepsForLibAtOffset(int, int):java.lang.String[]");
    }

    @Nullable
    static String[] tryGetDepsFromPrecomputedDeps(String str) {
        int offsetForLib;
        if (sInitialized && str.length() > LIB_PREFIX_SUFFIX_LEN && (offsetForLib = getOffsetForLib(str)) != -1) {
            return getDepsForLibAtOffset(offsetForLib, str.length());
        }
        return null;
    }
}
