package org.apache.commons.lang3;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.BitSet;
import java.util.HashMap;
import org.apache.commons.lang3.mutable.MutableInt;

/* JADX INFO: loaded from: classes7.dex */
public class ArrayUtils {
    public static final boolean[] EMPTY_BOOLEAN_ARRAY = new boolean[0];
    public static final Boolean[] EMPTY_BOOLEAN_OBJECT_ARRAY = new Boolean[0];
    public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    public static final Byte[] EMPTY_BYTE_OBJECT_ARRAY = new Byte[0];
    public static final char[] EMPTY_CHAR_ARRAY = new char[0];
    public static final Character[] EMPTY_CHARACTER_OBJECT_ARRAY = new Character[0];
    public static final Class<?>[] EMPTY_CLASS_ARRAY = new Class[0];
    public static final double[] EMPTY_DOUBLE_ARRAY = new double[0];
    public static final Double[] EMPTY_DOUBLE_OBJECT_ARRAY = new Double[0];
    public static final Field[] EMPTY_FIELD_ARRAY = new Field[0];
    public static final float[] EMPTY_FLOAT_ARRAY = new float[0];
    public static final Float[] EMPTY_FLOAT_OBJECT_ARRAY = new Float[0];
    public static final int[] EMPTY_INT_ARRAY = new int[0];
    public static final Integer[] EMPTY_INTEGER_OBJECT_ARRAY = new Integer[0];
    public static final long[] EMPTY_LONG_ARRAY = new long[0];
    public static final Long[] EMPTY_LONG_OBJECT_ARRAY = new Long[0];
    public static final Method[] EMPTY_METHOD_ARRAY = new Method[0];
    public static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];
    public static final short[] EMPTY_SHORT_ARRAY = new short[0];
    public static final Short[] EMPTY_SHORT_OBJECT_ARRAY = new Short[0];
    public static final String[] EMPTY_STRING_ARRAY = new String[0];
    public static final Throwable[] EMPTY_THROWABLE_ARRAY = new Throwable[0];
    public static final Type[] EMPTY_TYPE_ARRAY = new Type[0];

    public static <T> T[] add(T[] tArr, T t) {
        Class<?> componentType;
        if (tArr != null) {
            componentType = tArr.getClass().getComponentType();
        } else if (t != null) {
            componentType = t.getClass();
        } else {
            throw new IllegalArgumentException("Arguments cannot both be null");
        }
        T[] tArr2 = (T[]) ((Object[]) copyArrayGrow1(tArr, componentType));
        tArr2[tArr2.length - 1] = t;
        return tArr2;
    }

    public static <T> T[] clone(T[] tArr) {
        if (tArr == null) {
            return null;
        }
        return (T[]) ((Object[]) tArr.clone());
    }

    private static Object copyArrayGrow1(Object obj, Class<?> cls) {
        if (obj != null) {
            int length = Array.getLength(obj);
            Object objNewInstance = Array.newInstance(obj.getClass().getComponentType(), length + 1);
            System.arraycopy(obj, 0, objNewInstance, 0, length);
            return objNewInstance;
        }
        return Array.newInstance(cls, 1);
    }

    public static int getLength(Object obj) {
        if (obj == null) {
            return 0;
        }
        return Array.getLength(obj);
    }

    public static boolean isEmpty(char[] cArr) {
        return getLength(cArr) == 0;
    }

    public static boolean isEmpty(Object[] objArr) {
        return getLength(objArr) == 0;
    }

    static Object removeAll(Object obj, BitSet bitSet) {
        if (obj == null) {
            return null;
        }
        int length = getLength(obj);
        Object objNewInstance = Array.newInstance(obj.getClass().getComponentType(), length - bitSet.cardinality());
        int iNextClearBit = 0;
        int i = 0;
        while (true) {
            int iNextSetBit = bitSet.nextSetBit(iNextClearBit);
            if (iNextSetBit == -1) {
                break;
            }
            int i2 = iNextSetBit - iNextClearBit;
            if (i2 > 0) {
                System.arraycopy(obj, iNextClearBit, objNewInstance, i, i2);
                i += i2;
            }
            iNextClearBit = bitSet.nextClearBit(iNextSetBit);
        }
        int i3 = length - iNextClearBit;
        if (i3 > 0) {
            System.arraycopy(obj, iNextClearBit, objNewInstance, i, i3);
        }
        return objNewInstance;
    }

    @SafeVarargs
    public static <T> T[] removeElements(T[] tArr, T... tArr2) {
        if (isEmpty(tArr) || isEmpty(tArr2)) {
            return (T[]) clone(tArr);
        }
        HashMap map = new HashMap(tArr2.length);
        for (T t : tArr2) {
            MutableInt mutableInt = (MutableInt) map.get(t);
            if (mutableInt == null) {
                map.put(t, new MutableInt(1));
            } else {
                mutableInt.increment();
            }
        }
        BitSet bitSet = new BitSet();
        for (int i = 0; i < tArr.length; i++) {
            T t2 = tArr[i];
            MutableInt mutableInt2 = (MutableInt) map.get(t2);
            if (mutableInt2 != null) {
                if (mutableInt2.decrementAndGet() == 0) {
                    map.remove(t2);
                }
                bitSet.set(i);
            }
        }
        return (T[]) ((Object[]) removeAll(tArr, bitSet));
    }
}
