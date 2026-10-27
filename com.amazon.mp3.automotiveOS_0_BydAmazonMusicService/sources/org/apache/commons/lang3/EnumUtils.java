package org.apache.commons.lang3;

/* JADX INFO: loaded from: classes7.dex */
public class EnumUtils {
    public static <E extends Enum<E>> E getEnum(Class<E> cls, String str) {
        return (E) getEnum(cls, str, null);
    }

    public static <E extends Enum<E>> E getEnum(Class<E> cls, String str, E e) {
        if (str == null) {
            return e;
        }
        try {
            return (E) Enum.valueOf(cls, str);
        } catch (IllegalArgumentException unused) {
            return e;
        }
    }

    public static <E extends Enum<E>> boolean isValidEnum(Class<E> cls, String str) {
        return getEnum(cls, str) != null;
    }
}
