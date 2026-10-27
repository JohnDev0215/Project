package org.apache.commons.lang3;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes7.dex */
public class Validate {
    public static void isTrue(boolean z, String str, long j) {
        if (!z) {
            throw new IllegalArgumentException(String.format(str, Long.valueOf(j)));
        }
    }

    public static void isTrue(boolean z, String str, Object... objArr) {
        if (!z) {
            throw new IllegalArgumentException(String.format(str, objArr));
        }
    }

    public static void isTrue(boolean z) {
        if (!z) {
            throw new IllegalArgumentException("The validated expression is false");
        }
    }

    public static <T> T notNull(T t) {
        return (T) notNull(t, "The validated object is null", new Object[0]);
    }

    public static <T> T notNull(T t, final String str, final Object... objArr) {
        Objects.requireNonNull(t, (Supplier<String>) new Supplier() { // from class: org.apache.commons.lang3.Validate$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return String.format(str, objArr);
            }
        });
        return t;
    }

    public static <T> T[] notEmpty(T[] tArr, final String str, final Object... objArr) {
        Objects.requireNonNull(tArr, (Supplier<String>) new Supplier() { // from class: org.apache.commons.lang3.Validate$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return String.format(str, objArr);
            }
        });
        if (tArr.length != 0) {
            return tArr;
        }
        throw new IllegalArgumentException(String.format(str, objArr));
    }

    public static <T> T[] notEmpty(T[] tArr) {
        return (T[]) notEmpty(tArr, "The validated array is empty", new Object[0]);
    }

    public static <T extends Collection<?>> T notEmpty(T t, final String str, final Object... objArr) {
        Objects.requireNonNull(t, (Supplier<String>) new Supplier() { // from class: org.apache.commons.lang3.Validate$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return String.format(str, objArr);
            }
        });
        if (t.isEmpty()) {
            throw new IllegalArgumentException(String.format(str, objArr));
        }
        return t;
    }

    public static <T extends Collection<?>> T notEmpty(T t) {
        return (T) notEmpty(t, "The validated collection is empty", new Object[0]);
    }

    public static <T extends CharSequence> T notEmpty(T t, final String str, final Object... objArr) {
        Objects.requireNonNull(t, (Supplier<String>) new Supplier() { // from class: org.apache.commons.lang3.Validate$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return String.format(str, objArr);
            }
        });
        if (t.length() != 0) {
            return t;
        }
        throw new IllegalArgumentException(String.format(str, objArr));
    }

    public static <T extends CharSequence> T notEmpty(T t) {
        return (T) notEmpty(t, "The validated character sequence is empty", new Object[0]);
    }

    public static <T extends CharSequence> T notBlank(T t, final String str, final Object... objArr) {
        Objects.requireNonNull(t, (Supplier<String>) new Supplier() { // from class: org.apache.commons.lang3.Validate$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return String.format(str, objArr);
            }
        });
        if (StringUtils.isBlank(t)) {
            throw new IllegalArgumentException(String.format(str, objArr));
        }
        return t;
    }

    public static <T extends CharSequence> T notBlank(T t) {
        return (T) notBlank(t, "The validated character sequence is blank", new Object[0]);
    }

    public static <T> T[] noNullElements(T[] tArr, String str, Object... objArr) {
        notNull(tArr);
        for (int i = 0; i < tArr.length; i++) {
            if (tArr[i] == null) {
                throw new IllegalArgumentException(String.format(str, ArrayUtils.add(objArr, Integer.valueOf(i))));
            }
        }
        return tArr;
    }

    public static <T> T[] noNullElements(T[] tArr) {
        return (T[]) noNullElements(tArr, "The validated array contains null element at index: %d", new Object[0]);
    }

    public static void matchesPattern(CharSequence charSequence, String str) {
        if (!Pattern.matches(str, charSequence)) {
            throw new IllegalArgumentException(String.format("The string %s does not match the pattern %s", charSequence, str));
        }
    }

    public static void inclusiveBetween(long j, long j2, long j3) {
        if (j3 < j || j3 > j2) {
            throw new IllegalArgumentException(String.format("The value %s is not in the specified inclusive range of %s to %s", Long.valueOf(j3), Long.valueOf(j), Long.valueOf(j2)));
        }
    }

    public static void inclusiveBetween(long j, long j2, long j3, String str) {
        if (j3 < j || j3 > j2) {
            throw new IllegalArgumentException(str);
        }
    }

    public static void inclusiveBetween(double d, double d2, double d3) {
        if (d3 < d || d3 > d2) {
            throw new IllegalArgumentException(String.format("The value %s is not in the specified inclusive range of %s to %s", Double.valueOf(d3), Double.valueOf(d), Double.valueOf(d2)));
        }
    }

    public static void exclusiveBetween(long j, long j2, long j3) {
        if (j3 <= j || j3 >= j2) {
            throw new IllegalArgumentException(String.format("The value %s is not in the specified exclusive range of %s to %s", Long.valueOf(j3), Long.valueOf(j), Long.valueOf(j2)));
        }
    }
}
