package com.byd.foundation.utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.byd.dilink.os.BootBusinessConstants;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ArrayUtils {
    public static final int INDEX_NOT_FOUND = -1;

    @NonNull
    public static byte[] add(@Nullable byte[] bArr, byte b2) {
        return (byte[]) addOne(bArr, Byte.valueOf(b2), Byte.TYPE);
    }

    @NonNull
    public static byte[] add(@Nullable byte[] bArr, int i, byte b2) {
        return (byte[]) realAdd(bArr, i, Byte.valueOf(b2), Byte.TYPE);
    }

    @Nullable
    public static byte[] add(@Nullable byte[] bArr, int i, @Nullable byte[] bArr2) {
        Object objRealAddArr = realAddArr(bArr, i, bArr2, Byte.TYPE);
        if (objRealAddArr == null) {
            return null;
        }
        return (byte[]) objRealAddArr;
    }

    @NonNull
    public static char[] add(@Nullable char[] cArr, char c2) {
        return (char[]) addOne(cArr, Character.valueOf(c2), Character.TYPE);
    }

    @NonNull
    public static char[] add(@Nullable char[] cArr, int i, char c2) {
        return (char[]) realAdd(cArr, i, Character.valueOf(c2), Character.TYPE);
    }

    public static char[] add(@Nullable char[] cArr, int i, @Nullable char[] cArr2) {
        Object objRealAddArr = realAddArr(cArr, i, cArr2, Character.TYPE);
        if (objRealAddArr == null) {
            return null;
        }
        return (char[]) objRealAddArr;
    }

    @NonNull
    public static double[] add(@Nullable double[] dArr, double d) {
        return (double[]) addOne(dArr, Double.valueOf(d), Double.TYPE);
    }

    @NonNull
    public static double[] add(@Nullable double[] dArr, int i, double d) {
        return (double[]) realAdd(dArr, i, Double.valueOf(d), Double.TYPE);
    }

    @Nullable
    public static double[] add(@Nullable double[] dArr, int i, @Nullable double[] dArr2) {
        Object objRealAddArr = realAddArr(dArr, i, dArr2, Double.TYPE);
        if (objRealAddArr == null) {
            return null;
        }
        return (double[]) objRealAddArr;
    }

    @NonNull
    public static float[] add(@Nullable float[] fArr, float f) {
        return (float[]) addOne(fArr, Float.valueOf(f), Float.TYPE);
    }

    @NonNull
    public static float[] add(@Nullable float[] fArr, int i, float f) {
        return (float[]) realAdd(fArr, i, Float.valueOf(f), Float.TYPE);
    }

    @Nullable
    public static float[] add(@Nullable float[] fArr, int i, @Nullable float[] fArr2) {
        Object objRealAddArr = realAddArr(fArr, i, fArr2, Float.TYPE);
        if (objRealAddArr == null) {
            return null;
        }
        return (float[]) objRealAddArr;
    }

    @NonNull
    public static int[] add(@Nullable int[] iArr, int i) {
        return (int[]) addOne(iArr, Integer.valueOf(i), Integer.TYPE);
    }

    @NonNull
    public static int[] add(@Nullable int[] iArr, int i, int i2) {
        return (int[]) realAdd(iArr, i, Integer.valueOf(i2), Integer.TYPE);
    }

    @Nullable
    public static int[] add(@Nullable int[] iArr, int i, @Nullable int[] iArr2) {
        Object objRealAddArr = realAddArr(iArr, i, iArr2, Integer.TYPE);
        if (objRealAddArr == null) {
            return null;
        }
        return (int[]) objRealAddArr;
    }

    @NonNull
    public static long[] add(@Nullable long[] jArr, int i, long j) {
        return (long[]) realAdd(jArr, i, Long.valueOf(j), Long.TYPE);
    }

    @Nullable
    public static long[] add(@Nullable long[] jArr, int i, @Nullable long[] jArr2) {
        Object objRealAddArr = realAddArr(jArr, i, jArr2, Long.TYPE);
        if (objRealAddArr == null) {
            return null;
        }
        return (long[]) objRealAddArr;
    }

    @NonNull
    public static long[] add(@Nullable long[] jArr, long j) {
        return (long[]) addOne(jArr, Long.valueOf(j), Long.TYPE);
    }

    @NonNull
    public static <T> T[] add(@Nullable T[] tArr, int i, @Nullable T t) {
        Class<?> componentType;
        if (tArr != null) {
            componentType = tArr.getClass().getComponentType();
        } else {
            if (t == null) {
                T[] tArr2 = (T[]) new Object[1];
                tArr2[0] = null;
                return tArr2;
            }
            componentType = t.getClass();
        }
        return (T[]) ((Object[]) realAdd(tArr, i, t, componentType));
    }

    @Nullable
    public static <T> T[] add(@Nullable T[] tArr, int i, @Nullable T[] tArr2) {
        Class<?> cls;
        if (tArr != null) {
            cls = tArr.getClass();
        } else {
            if (tArr2 == null) {
                return null;
            }
            cls = tArr2.getClass();
        }
        return (T[]) ((Object[]) realAddArr(tArr, i, tArr2, cls.getComponentType()));
    }

    @NonNull
    public static <T> T[] add(@Nullable T[] tArr, @Nullable T t) {
        return (T[]) ((Object[]) addOne(tArr, t, tArr != null ? tArr.getClass() : t != null ? t.getClass() : Object.class));
    }

    @NonNull
    public static short[] add(@Nullable short[] sArr, int i, short s) {
        return (short[]) realAdd(sArr, i, Short.valueOf(s), Short.TYPE);
    }

    @Nullable
    public static short[] add(@Nullable short[] sArr, int i, @Nullable short[] sArr2) {
        Object objRealAddArr = realAddArr(sArr, i, sArr2, Short.TYPE);
        if (objRealAddArr == null) {
            return null;
        }
        return (short[]) objRealAddArr;
    }

    @NonNull
    public static short[] add(@Nullable short[] sArr, short s) {
        return (short[]) addOne(sArr, Short.valueOf(s), Short.TYPE);
    }

    @NonNull
    public static boolean[] add(@Nullable boolean[] zArr, int i, boolean z) {
        return (boolean[]) realAdd(zArr, i, Boolean.valueOf(z), Boolean.TYPE);
    }

    @Nullable
    public static boolean[] add(@Nullable boolean[] zArr, int i, @Nullable boolean[] zArr2) {
        Object objRealAddArr = realAddArr(zArr, i, zArr2, Boolean.TYPE);
        if (objRealAddArr == null) {
            return null;
        }
        return (boolean[]) objRealAddArr;
    }

    @NonNull
    public static boolean[] add(@Nullable boolean[] zArr, boolean z) {
        return (boolean[]) addOne(zArr, Boolean.valueOf(z), Boolean.TYPE);
    }

    @NonNull
    private static Object addOne(@Nullable Object obj, @Nullable Object obj2, Class<?> cls) {
        Object objNewInstance;
        int i = 0;
        if (obj != null) {
            int length = getLength(obj);
            objNewInstance = Array.newInstance(obj.getClass().getComponentType(), length + 1);
            System.arraycopy(obj, 0, objNewInstance, 0, length);
            i = length;
        } else {
            objNewInstance = Array.newInstance(cls, 1);
        }
        Array.set(objNewInstance, i, obj2);
        return objNewInstance;
    }

    @NonNull
    public static <T> List<T> asArrayList(@Nullable T... tArr) {
        ArrayList arrayList = new ArrayList();
        if (tArr != null && tArr.length != 0) {
            arrayList.addAll(Arrays.asList(tArr));
        }
        return arrayList;
    }

    @NonNull
    public static <T> List<T> asLinkedList(@Nullable T... tArr) {
        LinkedList linkedList = new LinkedList();
        if (tArr != null && tArr.length != 0) {
            linkedList.addAll(Arrays.asList(tArr));
        }
        return linkedList;
    }

    @NonNull
    public static <T> List<T> asList(@Nullable T... tArr) {
        return (tArr == null || tArr.length == 0) ? Collections.emptyList() : Arrays.asList(tArr);
    }

    @NonNull
    public static <T> List<T> asUnmodifiableList(@Nullable T... tArr) {
        return Collections.unmodifiableList(asList(tArr));
    }

    public static boolean contains(@Nullable byte[] bArr, byte b2) {
        return indexOf(bArr, b2) != -1;
    }

    public static boolean contains(@Nullable char[] cArr, char c2) {
        return indexOf(cArr, c2) != -1;
    }

    public static boolean contains(@Nullable double[] dArr, double d) {
        return indexOf(dArr, d) != -1;
    }

    public static boolean contains(@Nullable double[] dArr, double d, double d2) {
        return indexOf(dArr, d, 0, d2) != -1;
    }

    public static boolean contains(@Nullable float[] fArr, float f) {
        return indexOf(fArr, f) != -1;
    }

    public static boolean contains(@Nullable int[] iArr, int i) {
        return indexOf(iArr, i) != -1;
    }

    public static boolean contains(@Nullable long[] jArr, long j) {
        return indexOf(jArr, j) != -1;
    }

    public static boolean contains(@Nullable Object[] objArr, @Nullable Object obj) {
        return indexOf(objArr, obj) != -1;
    }

    public static boolean contains(@Nullable short[] sArr, short s) {
        return indexOf(sArr, s) != -1;
    }

    public static boolean contains(@Nullable boolean[] zArr, boolean z) {
        return indexOf(zArr, z) != -1;
    }

    @Nullable
    public static byte[] copy(@Nullable byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        return (byte[]) subArray(bArr, 0, bArr.length);
    }

    @Nullable
    public static char[] copy(@Nullable char[] cArr) {
        if (cArr == null) {
            return null;
        }
        return (char[]) subArray(cArr, 0, cArr.length);
    }

    @Nullable
    public static double[] copy(@Nullable double[] dArr) {
        if (dArr == null) {
            return null;
        }
        return (double[]) subArray(dArr, 0, dArr.length);
    }

    @Nullable
    public static float[] copy(@Nullable float[] fArr) {
        if (fArr == null) {
            return null;
        }
        return (float[]) subArray(fArr, 0, fArr.length);
    }

    @Nullable
    public static int[] copy(@Nullable int[] iArr) {
        if (iArr == null) {
            return null;
        }
        return (int[]) subArray(iArr, 0, iArr.length);
    }

    @Nullable
    public static long[] copy(@Nullable long[] jArr) {
        if (jArr == null) {
            return null;
        }
        return (long[]) subArray(jArr, 0, jArr.length);
    }

    public static <T> T[] copy(@Nullable T[] tArr) {
        if (tArr == null) {
            return null;
        }
        return (T[]) ((Object[]) subArray(tArr, 0, tArr.length));
    }

    @Nullable
    public static short[] copy(@Nullable short[] sArr) {
        if (sArr == null) {
            return null;
        }
        return (short[]) subArray(sArr, 0, sArr.length);
    }

    @Nullable
    public static boolean[] copy(@Nullable boolean[] zArr) {
        if (zArr == null) {
            return null;
        }
        return (boolean[]) subArray(zArr, 0, zArr.length);
    }

    public static boolean equals(byte[] bArr, byte[] bArr2) {
        return Arrays.equals(bArr, bArr2);
    }

    public static boolean equals(char[] cArr, char[] cArr2) {
        return Arrays.equals(cArr, cArr2);
    }

    public static boolean equals(double[] dArr, double[] dArr2) {
        return Arrays.equals(dArr, dArr2);
    }

    public static boolean equals(float[] fArr, float[] fArr2) {
        return Arrays.equals(fArr, fArr2);
    }

    public static boolean equals(int[] iArr, int[] iArr2) {
        return Arrays.equals(iArr, iArr2);
    }

    public static boolean equals(@Nullable Object[] objArr, @Nullable Object[] objArr2) {
        return Arrays.deepEquals(objArr, objArr2);
    }

    public static boolean equals(short[] sArr, short[] sArr2) {
        return Arrays.equals(sArr, sArr2);
    }

    public static boolean equals(boolean[] zArr, boolean[] zArr2) {
        return Arrays.equals(zArr, zArr2);
    }

    @Nullable
    public static Object get(@Nullable Object obj, int i) {
        return get(obj, i, null);
    }

    @Nullable
    public static Object get(@Nullable Object obj, int i, @Nullable Object obj2) {
        if (obj == null) {
            return obj2;
        }
        try {
            return Array.get(obj, i);
        } catch (Exception unused) {
            return obj2;
        }
    }

    public static int getLength(@Nullable Object obj) {
        if (obj == null) {
            return 0;
        }
        return Array.getLength(obj);
    }

    public static int indexOf(@Nullable byte[] bArr, byte b2) {
        return indexOf(bArr, b2, 0);
    }

    public static int indexOf(@Nullable byte[] bArr, byte b2, int i) {
        if (bArr == null) {
            return -1;
        }
        if (i < 0) {
            i = 0;
        }
        while (i < bArr.length) {
            if (b2 == bArr[i]) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static int indexOf(@Nullable char[] cArr, char c2) {
        return indexOf(cArr, c2, 0);
    }

    public static int indexOf(@Nullable char[] cArr, char c2, int i) {
        if (cArr == null) {
            return -1;
        }
        if (i < 0) {
            i = 0;
        }
        while (i < cArr.length) {
            if (c2 == cArr[i]) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static int indexOf(@Nullable double[] dArr, double d) {
        return indexOf(dArr, d, 0);
    }

    public static int indexOf(@Nullable double[] dArr, double d, double d2) {
        return indexOf(dArr, d, 0, d2);
    }

    public static int indexOf(@Nullable double[] dArr, double d, int i) {
        if (isEmpty(dArr)) {
            return -1;
        }
        if (i < 0) {
            i = 0;
        }
        while (i < dArr.length) {
            if (d == dArr[i]) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static int indexOf(@Nullable double[] dArr, double d, int i, double d2) {
        if (isEmpty(dArr)) {
            return -1;
        }
        if (i < 0) {
            i = 0;
        }
        double d3 = d - d2;
        double d4 = d + d2;
        while (i < dArr.length) {
            if (dArr[i] >= d3 && dArr[i] <= d4) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static int indexOf(@Nullable float[] fArr, float f) {
        return indexOf(fArr, f, 0);
    }

    public static int indexOf(@Nullable float[] fArr, float f, int i) {
        if (isEmpty(fArr)) {
            return -1;
        }
        if (i < 0) {
            i = 0;
        }
        while (i < fArr.length) {
            if (f == fArr[i]) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static int indexOf(@Nullable int[] iArr, int i) {
        return indexOf(iArr, i, 0);
    }

    public static int indexOf(@Nullable int[] iArr, int i, int i2) {
        if (iArr == null) {
            return -1;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        while (i2 < iArr.length) {
            if (i == iArr[i2]) {
                return i2;
            }
            i2++;
        }
        return -1;
    }

    public static int indexOf(@Nullable long[] jArr, long j) {
        return indexOf(jArr, j, 0);
    }

    public static int indexOf(@Nullable long[] jArr, long j, int i) {
        if (jArr == null) {
            return -1;
        }
        if (i < 0) {
            i = 0;
        }
        while (i < jArr.length) {
            if (j == jArr[i]) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static int indexOf(@Nullable Object[] objArr, @Nullable Object obj) {
        return indexOf(objArr, obj, 0);
    }

    public static int indexOf(@Nullable Object[] objArr, @Nullable Object obj, int i) {
        if (objArr == null) {
            return -1;
        }
        if (i < 0) {
            i = 0;
        }
        if (obj == null) {
            while (i < objArr.length) {
                if (objArr[i] == null) {
                    return i;
                }
                i++;
            }
        } else {
            while (i < objArr.length) {
                if (obj.equals(objArr[i])) {
                    return i;
                }
                i++;
            }
        }
        return -1;
    }

    public static int indexOf(@Nullable short[] sArr, short s) {
        return indexOf(sArr, s, 0);
    }

    public static int indexOf(@Nullable short[] sArr, short s, int i) {
        if (sArr == null) {
            return -1;
        }
        if (i < 0) {
            i = 0;
        }
        while (i < sArr.length) {
            if (s == sArr[i]) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static int indexOf(@Nullable boolean[] zArr, boolean z) {
        return indexOf(zArr, z, 0);
    }

    public static int indexOf(@Nullable boolean[] zArr, boolean z, int i) {
        if (isEmpty(zArr)) {
            return -1;
        }
        if (i < 0) {
            i = 0;
        }
        while (i < zArr.length) {
            if (z == zArr[i]) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static boolean isEmpty(@Nullable Object obj) {
        return getLength(obj) == 0;
    }

    public static boolean isSameLength(@Nullable Object obj, @Nullable Object obj2) {
        return getLength(obj) == getLength(obj2);
    }

    public static <T> T[] newArray(T... tArr) {
        return tArr;
    }

    @NonNull
    public static boolean[] newBooleanArray(boolean... zArr) {
        return zArr;
    }

    @NonNull
    public static byte[] newByteArray(byte... bArr) {
        return bArr;
    }

    @NonNull
    public static char[] newCharArray(char... cArr) {
        return cArr;
    }

    @NonNull
    public static double[] newDoubleArray(double... dArr) {
        return dArr;
    }

    @NonNull
    public static float[] newFloatArray(float... fArr) {
        return fArr;
    }

    @NonNull
    public static int[] newIntArray(int... iArr) {
        return iArr;
    }

    @NonNull
    public static long[] newLongArray(long... jArr) {
        return jArr;
    }

    @NonNull
    public static short[] newShortArray(short... sArr) {
        return sArr;
    }

    @NonNull
    private static Object realAdd(@Nullable Object obj, int i, @Nullable Object obj2, Class<?> cls) {
        if (obj == null) {
            if (i == 0) {
                Object objNewInstance = Array.newInstance(cls, 1);
                Array.set(objNewInstance, 0, obj2);
                return objNewInstance;
            }
            throw new IndexOutOfBoundsException("Index: " + i + ", Length: 0");
        }
        int length = Array.getLength(obj);
        if (i > length || i < 0) {
            throw new IndexOutOfBoundsException("Index: " + i + ", Length: " + length);
        }
        Object objNewInstance2 = Array.newInstance(cls, length + 1);
        System.arraycopy(obj, 0, objNewInstance2, 0, i);
        Array.set(objNewInstance2, i, obj2);
        if (i < length) {
            System.arraycopy(obj, i, objNewInstance2, i + 1, length - i);
        }
        return objNewInstance2;
    }

    @Nullable
    private static Object realAddArr(@Nullable Object obj, int i, @Nullable Object obj2, Class cls) {
        if (obj == null && obj2 == null) {
            return null;
        }
        int length = getLength(obj);
        int length2 = getLength(obj2);
        if (length == 0) {
            if (i == 0) {
                return realCopy(obj2);
            }
            throw new IndexOutOfBoundsException("Index: " + i + ", array1 Length: 0");
        }
        if (length2 == 0) {
            return realCopy(obj);
        }
        if (i > length || i < 0) {
            throw new IndexOutOfBoundsException("Index: " + i + ", array1 Length: " + length);
        }
        Object objNewInstance = Array.newInstance(obj.getClass().getComponentType(), length + length2);
        if (i == length) {
            System.arraycopy(obj, 0, objNewInstance, 0, length);
            System.arraycopy(obj2, 0, objNewInstance, length, length2);
        } else if (i == 0) {
            System.arraycopy(obj2, 0, objNewInstance, 0, length2);
            System.arraycopy(obj, 0, objNewInstance, length2, length);
        } else {
            System.arraycopy(obj, 0, objNewInstance, 0, i);
            System.arraycopy(obj2, 0, objNewInstance, i, length2);
            System.arraycopy(obj, i, objNewInstance, length2 + i, length - i);
        }
        return objNewInstance;
    }

    @Nullable
    private static Object realCopy(@Nullable Object obj) {
        if (obj == null) {
            return null;
        }
        return subArray(obj, 0, getLength(obj));
    }

    @NonNull
    private static Object remove(@NonNull Object obj, int i) {
        int length = getLength(obj);
        if (i < 0 || i >= length) {
            throw new IndexOutOfBoundsException("Index: " + i + ", Length: " + length);
        }
        int i2 = length - 1;
        Object objNewInstance = Array.newInstance(obj.getClass().getComponentType(), i2);
        System.arraycopy(obj, 0, objNewInstance, 0, i);
        if (i < i2) {
            System.arraycopy(obj, i + 1, objNewInstance, i, (length - i) - 1);
        }
        return objNewInstance;
    }

    @Nullable
    public static byte[] remove(@Nullable byte[] bArr, int i) {
        if (bArr == null) {
            return null;
        }
        return (byte[]) remove((Object) bArr, i);
    }

    @Nullable
    public static char[] remove(@Nullable char[] cArr, int i) {
        if (cArr == null) {
            return null;
        }
        return (char[]) remove((Object) cArr, i);
    }

    @Nullable
    public static double[] remove(@Nullable double[] dArr, int i) {
        if (dArr == null) {
            return null;
        }
        return (double[]) remove((Object) dArr, i);
    }

    @Nullable
    public static float[] remove(@Nullable float[] fArr, int i) {
        if (fArr == null) {
            return null;
        }
        return (float[]) remove((Object) fArr, i);
    }

    @Nullable
    public static int[] remove(@Nullable int[] iArr, int i) {
        if (iArr == null) {
            return null;
        }
        return (int[]) remove((Object) iArr, i);
    }

    @Nullable
    public static long[] remove(@Nullable long[] jArr, int i) {
        if (jArr == null) {
            return null;
        }
        return (long[]) remove((Object) jArr, i);
    }

    @Nullable
    public static Object[] remove(@Nullable Object[] objArr, int i) {
        if (objArr == null) {
            return null;
        }
        return (Object[]) remove((Object) objArr, i);
    }

    @Nullable
    public static short[] remove(@Nullable short[] sArr, int i) {
        if (sArr == null) {
            return null;
        }
        return (short[]) remove((Object) sArr, i);
    }

    @Nullable
    public static boolean[] remove(@Nullable boolean[] zArr, int i) {
        if (zArr == null) {
            return null;
        }
        return (boolean[]) remove((Object) zArr, i);
    }

    @Nullable
    public static byte[] removeElement(@Nullable byte[] bArr, byte b2) {
        int iIndexOf = indexOf(bArr, b2);
        return iIndexOf == -1 ? copy(bArr) : remove(bArr, iIndexOf);
    }

    @Nullable
    public static char[] removeElement(@Nullable char[] cArr, char c2) {
        int iIndexOf = indexOf(cArr, c2);
        return iIndexOf == -1 ? copy(cArr) : remove(cArr, iIndexOf);
    }

    @Nullable
    public static double[] removeElement(@Nullable double[] dArr, double d) {
        int iIndexOf = indexOf(dArr, d);
        return iIndexOf == -1 ? copy(dArr) : remove(dArr, iIndexOf);
    }

    @Nullable
    public static float[] removeElement(@Nullable float[] fArr, float f) {
        int iIndexOf = indexOf(fArr, f);
        return iIndexOf == -1 ? copy(fArr) : remove(fArr, iIndexOf);
    }

    @Nullable
    public static int[] removeElement(@Nullable int[] iArr, int i) {
        int iIndexOf = indexOf(iArr, i);
        return iIndexOf == -1 ? copy(iArr) : remove(iArr, iIndexOf);
    }

    @Nullable
    public static long[] removeElement(@Nullable long[] jArr, long j) {
        int iIndexOf = indexOf(jArr, j);
        return iIndexOf == -1 ? copy(jArr) : remove(jArr, iIndexOf);
    }

    @Nullable
    public static Object[] removeElement(@Nullable Object[] objArr, @Nullable Object obj) {
        int iIndexOf = indexOf(objArr, obj);
        return iIndexOf == -1 ? copy(objArr) : remove(objArr, iIndexOf);
    }

    @Nullable
    public static short[] removeElement(@Nullable short[] sArr, short s) {
        int iIndexOf = indexOf(sArr, s);
        return iIndexOf == -1 ? copy(sArr) : remove(sArr, iIndexOf);
    }

    @Nullable
    public static boolean[] removeElement(@Nullable boolean[] zArr, boolean z) {
        int iIndexOf = indexOf(zArr, z);
        return iIndexOf == -1 ? copy(zArr) : remove(zArr, iIndexOf);
    }

    public static void reverse(byte[] bArr) {
        if (bArr == null) {
            return;
        }
        int length = bArr.length - 1;
        for (int i = 0; length > i; i++) {
            byte b2 = bArr[length];
            bArr[length] = bArr[i];
            bArr[i] = b2;
            length--;
        }
    }

    public static void reverse(char[] cArr) {
        if (cArr == null) {
            return;
        }
        int length = cArr.length - 1;
        for (int i = 0; length > i; i++) {
            char c2 = cArr[length];
            cArr[length] = cArr[i];
            cArr[i] = c2;
            length--;
        }
    }

    public static void reverse(double[] dArr) {
        if (dArr == null) {
            return;
        }
        int length = dArr.length - 1;
        for (int i = 0; length > i; i++) {
            double d = dArr[length];
            dArr[length] = dArr[i];
            dArr[i] = d;
            length--;
        }
    }

    public static void reverse(float[] fArr) {
        if (fArr == null) {
            return;
        }
        int length = fArr.length - 1;
        for (int i = 0; length > i; i++) {
            float f = fArr[length];
            fArr[length] = fArr[i];
            fArr[i] = f;
            length--;
        }
    }

    public static void reverse(int[] iArr) {
        if (iArr == null) {
            return;
        }
        int length = iArr.length - 1;
        for (int i = 0; length > i; i++) {
            int i2 = iArr[length];
            iArr[length] = iArr[i];
            iArr[i] = i2;
            length--;
        }
    }

    public static void reverse(long[] jArr) {
        if (jArr == null) {
            return;
        }
        int length = jArr.length - 1;
        for (int i = 0; length > i; i++) {
            long j = jArr[length];
            jArr[length] = jArr[i];
            jArr[i] = j;
            length--;
        }
    }

    public static <T> void reverse(T[] tArr) {
        if (tArr == null) {
            return;
        }
        int length = tArr.length - 1;
        for (int i = 0; length > i; i++) {
            T t = tArr[length];
            tArr[length] = tArr[i];
            tArr[i] = t;
            length--;
        }
    }

    public static void reverse(short[] sArr) {
        if (sArr == null) {
            return;
        }
        int length = sArr.length - 1;
        for (int i = 0; length > i; i++) {
            short s = sArr[length];
            sArr[length] = sArr[i];
            sArr[i] = s;
            length--;
        }
    }

    public static void reverse(boolean[] zArr) {
        if (zArr == null) {
            return;
        }
        int length = zArr.length - 1;
        for (int i = 0; length > i; i++) {
            boolean z = zArr[length];
            zArr[length] = zArr[i];
            zArr[i] = z;
            length--;
        }
    }

    public static void set(@Nullable Object obj, int i, @Nullable Object obj2) {
        if (obj == null) {
            return;
        }
        Array.set(obj, i, obj2);
    }

    public static void sort(@Nullable byte[] bArr) {
        if (bArr == null || bArr.length < 2) {
            return;
        }
        Arrays.sort(bArr);
    }

    public static void sort(@Nullable char[] cArr) {
        if (cArr == null || cArr.length < 2) {
            return;
        }
        Arrays.sort(cArr);
    }

    public static void sort(@Nullable double[] dArr) {
        if (dArr == null || dArr.length < 2) {
            return;
        }
        Arrays.sort(dArr);
    }

    public static void sort(@Nullable float[] fArr) {
        if (fArr == null || fArr.length < 2) {
            return;
        }
        Arrays.sort(fArr);
    }

    public static void sort(@Nullable int[] iArr) {
        if (iArr == null || iArr.length < 2) {
            return;
        }
        Arrays.sort(iArr);
    }

    public static void sort(@Nullable long[] jArr) {
        if (jArr == null || jArr.length < 2) {
            return;
        }
        Arrays.sort(jArr);
    }

    public static <T> void sort(@Nullable T[] tArr, Comparator<? super T> comparator) {
        if (tArr == null || tArr.length < 2) {
            return;
        }
        Arrays.sort(tArr, comparator);
    }

    public static void sort(@Nullable short[] sArr) {
        if (sArr == null || sArr.length < 2) {
            return;
        }
        Arrays.sort(sArr);
    }

    @Nullable
    public static <T> T[] subAnyArray(@Nullable T[] tArr, int i, int i2) {
        return (T[]) ((Object[]) subArray(tArr, i, i2));
    }

    @Nullable
    private static Object subArray(@Nullable Object obj, int i, int i2) {
        if (obj == null) {
            return null;
        }
        if (i < 0) {
            i = 0;
        }
        int length = getLength(obj);
        if (i2 > length) {
            i2 = length;
        }
        int i3 = i2 - i;
        Class<?> componentType = obj.getClass().getComponentType();
        if (i3 <= 0) {
            return Array.newInstance(componentType, 0);
        }
        Object objNewInstance = Array.newInstance(componentType, i3);
        System.arraycopy(obj, i, objNewInstance, 0, i3);
        return objNewInstance;
    }

    @Nullable
    public static boolean[] subBooleanArray(@Nullable boolean[] zArr, int i, int i2) {
        return (boolean[]) subArray(zArr, i, i2);
    }

    @Nullable
    public static byte[] subByteArray(@Nullable byte[] bArr, int i, int i2) {
        return (byte[]) subArray(bArr, i, i2);
    }

    @Nullable
    public static char[] subCharArray(@Nullable char[] cArr, int i, int i2) {
        return (char[]) subArray(cArr, i, i2);
    }

    @Nullable
    public static double[] subDoubleArray(@Nullable double[] dArr, int i, int i2) {
        return (double[]) subArray(dArr, i, i2);
    }

    @Nullable
    public static float[] subFloatArray(@Nullable float[] fArr, int i, int i2) {
        return (float[]) subArray(fArr, i, i2);
    }

    @Nullable
    public static int[] subIntArray(@Nullable int[] iArr, int i, int i2) {
        return (int[]) subArray(iArr, i, i2);
    }

    @Nullable
    public static long[] subLongArray(@Nullable long[] jArr, int i, int i2) {
        return (long[]) subArray(jArr, i, i2);
    }

    @Nullable
    public static short[] subShortArray(@Nullable short[] sArr, int i, int i2) {
        return (short[]) subArray(sArr, i, i2);
    }

    @Nullable
    public static Boolean[] toObject(@Nullable boolean[] zArr) {
        if (zArr == null) {
            return null;
        }
        if (zArr.length == 0) {
            return new Boolean[0];
        }
        Boolean[] boolArr = new Boolean[zArr.length];
        for (int i = 0; i < zArr.length; i++) {
            boolArr[i] = zArr[i] ? Boolean.TRUE : Boolean.FALSE;
        }
        return boolArr;
    }

    @Nullable
    public static Byte[] toObject(@Nullable byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        if (bArr.length == 0) {
            return new Byte[0];
        }
        Byte[] bArr2 = new Byte[bArr.length];
        for (int i = 0; i < bArr.length; i++) {
            bArr2[i] = new Byte(bArr[i]);
        }
        return bArr2;
    }

    @Nullable
    public static Character[] toObject(@Nullable char[] cArr) {
        if (cArr == null) {
            return null;
        }
        if (cArr.length == 0) {
            return new Character[0];
        }
        Character[] chArr = new Character[cArr.length];
        for (int i = 0; i < cArr.length; i++) {
            chArr[i] = new Character(cArr[i]);
        }
        return chArr;
    }

    @Nullable
    public static Double[] toObject(@Nullable double[] dArr) {
        if (dArr == null) {
            return null;
        }
        if (dArr.length == 0) {
            return new Double[0];
        }
        Double[] dArr2 = new Double[dArr.length];
        for (int i = 0; i < dArr.length; i++) {
            dArr2[i] = new Double(dArr[i]);
        }
        return dArr2;
    }

    @Nullable
    public static Float[] toObject(@Nullable float[] fArr) {
        if (fArr == null) {
            return null;
        }
        if (fArr.length == 0) {
            return new Float[0];
        }
        Float[] fArr2 = new Float[fArr.length];
        for (int i = 0; i < fArr.length; i++) {
            fArr2[i] = new Float(fArr[i]);
        }
        return fArr2;
    }

    @Nullable
    public static Integer[] toObject(@Nullable int[] iArr) {
        if (iArr == null) {
            return null;
        }
        if (iArr.length == 0) {
            return new Integer[0];
        }
        Integer[] numArr = new Integer[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            numArr[i] = new Integer(iArr[i]);
        }
        return numArr;
    }

    @Nullable
    public static Long[] toObject(@Nullable long[] jArr) {
        if (jArr == null) {
            return null;
        }
        if (jArr.length == 0) {
            return new Long[0];
        }
        Long[] lArr = new Long[jArr.length];
        for (int i = 0; i < jArr.length; i++) {
            lArr[i] = new Long(jArr[i]);
        }
        return lArr;
    }

    @Nullable
    public static Short[] toObject(@Nullable short[] sArr) {
        if (sArr == null) {
            return null;
        }
        if (sArr.length == 0) {
            return new Short[0];
        }
        Short[] shArr = new Short[sArr.length];
        for (int i = 0; i < sArr.length; i++) {
            shArr[i] = new Short(sArr[i]);
        }
        return shArr;
    }

    @Nullable
    public static byte[] toPrimitive(@Nullable Byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        if (bArr.length == 0) {
            return new byte[0];
        }
        byte[] bArr2 = new byte[bArr.length];
        for (int i = 0; i < bArr.length; i++) {
            bArr2[i] = bArr[i].byteValue();
        }
        return bArr2;
    }

    @Nullable
    public static byte[] toPrimitive(@Nullable Byte[] bArr, byte b2) {
        if (bArr == null) {
            return null;
        }
        if (bArr.length == 0) {
            return new byte[0];
        }
        byte[] bArr2 = new byte[bArr.length];
        for (int i = 0; i < bArr.length; i++) {
            Byte b3 = bArr[i];
            bArr2[i] = b3 == null ? b2 : b3.byteValue();
        }
        return bArr2;
    }

    @Nullable
    public static char[] toPrimitive(@Nullable Character[] chArr) {
        if (chArr == null) {
            return null;
        }
        if (chArr.length == 0) {
            return new char[0];
        }
        char[] cArr = new char[chArr.length];
        for (int i = 0; i < chArr.length; i++) {
            cArr[i] = chArr[i].charValue();
        }
        return cArr;
    }

    @Nullable
    public static char[] toPrimitive(@Nullable Character[] chArr, char c2) {
        if (chArr == null) {
            return null;
        }
        if (chArr.length == 0) {
            return new char[0];
        }
        char[] cArr = new char[chArr.length];
        for (int i = 0; i < chArr.length; i++) {
            Character ch = chArr[i];
            cArr[i] = ch == null ? c2 : ch.charValue();
        }
        return cArr;
    }

    @Nullable
    public static double[] toPrimitive(@Nullable Double[] dArr) {
        if (dArr == null) {
            return null;
        }
        if (dArr.length == 0) {
            return new double[0];
        }
        double[] dArr2 = new double[dArr.length];
        for (int i = 0; i < dArr.length; i++) {
            dArr2[i] = dArr[i].doubleValue();
        }
        return dArr2;
    }

    @Nullable
    public static double[] toPrimitive(@Nullable Double[] dArr, double d) {
        if (dArr == null) {
            return null;
        }
        if (dArr.length == 0) {
            return new double[0];
        }
        double[] dArr2 = new double[dArr.length];
        for (int i = 0; i < dArr.length; i++) {
            Double d2 = dArr[i];
            dArr2[i] = d2 == null ? d : d2.doubleValue();
        }
        return dArr2;
    }

    @Nullable
    public static float[] toPrimitive(@Nullable Float[] fArr) {
        if (fArr == null) {
            return null;
        }
        if (fArr.length == 0) {
            return new float[0];
        }
        float[] fArr2 = new float[fArr.length];
        for (int i = 0; i < fArr.length; i++) {
            fArr2[i] = fArr[i].floatValue();
        }
        return fArr2;
    }

    @Nullable
    public static float[] toPrimitive(@Nullable Float[] fArr, float f) {
        if (fArr == null) {
            return null;
        }
        if (fArr.length == 0) {
            return new float[0];
        }
        float[] fArr2 = new float[fArr.length];
        for (int i = 0; i < fArr.length; i++) {
            Float f2 = fArr[i];
            fArr2[i] = f2 == null ? f : f2.floatValue();
        }
        return fArr2;
    }

    @Nullable
    public static int[] toPrimitive(@Nullable Integer[] numArr) {
        if (numArr == null) {
            return null;
        }
        if (numArr.length == 0) {
            return new int[0];
        }
        int[] iArr = new int[numArr.length];
        for (int i = 0; i < numArr.length; i++) {
            iArr[i] = numArr[i].intValue();
        }
        return iArr;
    }

    @Nullable
    public static int[] toPrimitive(@Nullable Integer[] numArr, int i) {
        if (numArr == null) {
            return null;
        }
        if (numArr.length == 0) {
            return new int[0];
        }
        int[] iArr = new int[numArr.length];
        for (int i2 = 0; i2 < numArr.length; i2++) {
            Integer num = numArr[i2];
            iArr[i2] = num == null ? i : num.intValue();
        }
        return iArr;
    }

    @Nullable
    public static long[] toPrimitive(@Nullable Long[] lArr) {
        if (lArr == null) {
            return null;
        }
        if (lArr.length == 0) {
            return new long[0];
        }
        long[] jArr = new long[lArr.length];
        for (int i = 0; i < lArr.length; i++) {
            jArr[i] = lArr[i].longValue();
        }
        return jArr;
    }

    @Nullable
    public static long[] toPrimitive(@Nullable Long[] lArr, long j) {
        if (lArr == null) {
            return null;
        }
        if (lArr.length == 0) {
            return new long[0];
        }
        long[] jArr = new long[lArr.length];
        for (int i = 0; i < lArr.length; i++) {
            Long l = lArr[i];
            jArr[i] = l == null ? j : l.longValue();
        }
        return jArr;
    }

    @Nullable
    public static short[] toPrimitive(@Nullable Short[] shArr) {
        if (shArr == null) {
            return null;
        }
        if (shArr.length == 0) {
            return new short[0];
        }
        short[] sArr = new short[shArr.length];
        for (int i = 0; i < shArr.length; i++) {
            sArr[i] = shArr[i].shortValue();
        }
        return sArr;
    }

    @Nullable
    public static short[] toPrimitive(@Nullable Short[] shArr, short s) {
        if (shArr == null) {
            return null;
        }
        if (shArr.length == 0) {
            return new short[0];
        }
        short[] sArr = new short[shArr.length];
        for (int i = 0; i < shArr.length; i++) {
            Short sh = shArr[i];
            sArr[i] = sh == null ? s : sh.shortValue();
        }
        return sArr;
    }

    @Nullable
    public static boolean[] toPrimitive(@Nullable Boolean[] boolArr) {
        if (boolArr == null) {
            return null;
        }
        if (boolArr.length == 0) {
            return new boolean[0];
        }
        boolean[] zArr = new boolean[boolArr.length];
        for (int i = 0; i < boolArr.length; i++) {
            zArr[i] = boolArr[i].booleanValue();
        }
        return zArr;
    }

    @Nullable
    public static boolean[] toPrimitive(@Nullable Boolean[] boolArr, boolean z) {
        if (boolArr == null) {
            return null;
        }
        if (boolArr.length == 0) {
            return new boolean[0];
        }
        boolean[] zArr = new boolean[boolArr.length];
        for (int i = 0; i < boolArr.length; i++) {
            Boolean bool = boolArr[i];
            zArr[i] = bool == null ? z : bool.booleanValue();
        }
        return zArr;
    }

    @NonNull
    public static String toString(@Nullable Object obj) {
        if (obj == null) {
            return BootBusinessConstants.SubBusinessState.SUB_BUSINESS_NULL;
        }
        if (obj instanceof Object[]) {
            return Arrays.deepToString((Object[]) obj);
        }
        if (obj instanceof boolean[]) {
            return Arrays.toString((boolean[]) obj);
        }
        if (obj instanceof byte[]) {
            return Arrays.toString((byte[]) obj);
        }
        if (obj instanceof char[]) {
            return Arrays.toString((char[]) obj);
        }
        if (obj instanceof double[]) {
            return Arrays.toString((double[]) obj);
        }
        if (obj instanceof float[]) {
            return Arrays.toString((float[]) obj);
        }
        if (obj instanceof int[]) {
            return Arrays.toString((int[]) obj);
        }
        if (obj instanceof long[]) {
            return Arrays.toString((long[]) obj);
        }
        if (obj instanceof short[]) {
            return Arrays.toString((short[]) obj);
        }
        throw new IllegalArgumentException("Array has incompatible type: " + obj.getClass());
    }
}
