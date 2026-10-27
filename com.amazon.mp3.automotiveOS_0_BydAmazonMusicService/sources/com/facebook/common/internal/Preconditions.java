package com.facebook.common.internal;

import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public final class Preconditions {
    private Preconditions() {
    }

    public static void checkArgument(@Nullable Boolean expression) {
        if (expression != null && !expression.booleanValue()) {
            throw new IllegalArgumentException();
        }
    }

    public static void checkArgument(boolean expression, @Nullable Object errorMessage) {
        if (!expression) {
            throw new IllegalArgumentException(String.valueOf(errorMessage));
        }
    }

    public static void checkArgument(boolean expression, @Nullable String errorMessageTemplate, Object... errorMessageArgs) {
        if (!expression) {
            throw new IllegalArgumentException(format(errorMessageTemplate, errorMessageArgs));
        }
    }

    public static void checkState(boolean expression) {
        if (!expression) {
            throw new IllegalStateException();
        }
    }

    public static void checkState(boolean expression, @Nullable Object errorMessage) {
        if (!expression) {
            throw new IllegalStateException(String.valueOf(errorMessage));
        }
    }

    public static <T> T checkNotNull(@Nullable T reference) {
        reference.getClass();
        return reference;
    }

    public static <T> T checkNotNull(@Nullable T reference, @Nullable Object errorMessage) {
        if (reference != null) {
            return reference;
        }
        throw new NullPointerException(String.valueOf(errorMessage));
    }

    public static int checkElementIndex(int index, int size) {
        return checkElementIndex(index, size, "index");
    }

    public static int checkElementIndex(int index, int size, @Nullable String desc) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(badElementIndex(index, size, desc));
        }
        return index;
    }

    private static String badElementIndex(int index, int size, @Nullable String desc) {
        if (index < 0) {
            return format("%s (%s) must not be negative", desc, Integer.valueOf(index));
        }
        if (size >= 0) {
            return format("%s (%s) must be less than size (%s)", desc, Integer.valueOf(index), Integer.valueOf(size));
        }
        throw new IllegalArgumentException("negative size: " + size);
    }

    static String format(@Nullable String template, Object... args) {
        int iIndexOf;
        String strValueOf = String.valueOf(template);
        StringBuilder sb = new StringBuilder(strValueOf.length() + (args.length * 16));
        int i = 0;
        int i2 = 0;
        while (i < args.length && (iIndexOf = strValueOf.indexOf("%s", i2)) != -1) {
            sb.append(strValueOf.substring(i2, iIndexOf));
            sb.append(args[i]);
            i2 = iIndexOf + 2;
            i++;
        }
        sb.append(strValueOf.substring(i2));
        if (i < args.length) {
            sb.append(" [");
            sb.append(args[i]);
            for (int i3 = i + 1; i3 < args.length; i3++) {
                sb.append(", ");
                sb.append(args[i3]);
            }
            sb.append(']');
        }
        return sb.toString();
    }
}
