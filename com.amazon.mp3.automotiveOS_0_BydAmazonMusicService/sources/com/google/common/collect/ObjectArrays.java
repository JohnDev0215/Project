package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes6.dex */
public final class ObjectArrays {
    private ObjectArrays() {
    }

    public static <T> T[] newArray(Class<T> cls, int i) {
        return (T[]) ((Object[]) Array.newInstance((Class<?>) cls, i));
    }

    public static <T> T[] newArray(T[] tArr, int i) {
        return (T[]) Platform.newArray(tArr, i);
    }

    static <T> T[] toArrayImpl(Collection<?> collection, T[] tArr) {
        int size = collection.size();
        if (tArr.length < size) {
            tArr = (T[]) newArray(tArr, size);
        }
        fillArray(collection, tArr);
        if (tArr.length > size) {
            tArr[size] = null;
        }
        return tArr;
    }

    static <T> T[] toArrayImpl(Object[] objArr, int i, int i2, T[] tArr) {
        Preconditions.checkPositionIndexes(i, i + i2, objArr.length);
        if (tArr.length < i2) {
            tArr = (T[]) newArray(tArr, i2);
        } else if (tArr.length > i2) {
            tArr[i2] = null;
        }
        System.arraycopy(objArr, i, tArr, 0, i2);
        return tArr;
    }

    static Object[] toArrayImpl(Collection<?> collection) {
        return fillArray(collection, new Object[collection.size()]);
    }

    private static Object[] fillArray(Iterable<?> iterable, Object[] objArr) {
        Iterator<?> it = iterable.iterator();
        int i = 0;
        while (it.hasNext()) {
            objArr[i] = it.next();
            i++;
        }
        return objArr;
    }

    static Object[] checkElementsNotNull(Object... objArr) {
        checkElementsNotNull(objArr, objArr.length);
        return objArr;
    }

    static Object[] checkElementsNotNull(Object[] objArr, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            checkElementNotNull(objArr[i2], i2);
        }
        return objArr;
    }

    static Object checkElementNotNull(@CheckForNull Object obj, int i) {
        if (obj != null) {
            return obj;
        }
        StringBuilder sb = new StringBuilder(20);
        sb.append("at index ");
        sb.append(i);
        throw new NullPointerException(sb.toString());
    }
}
