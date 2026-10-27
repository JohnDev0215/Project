package org.chromium.mojo.bindings;

import org.chromium.mojo.system.AsyncWaiter;
import org.chromium.mojo.system.Handle;

/* JADX INFO: loaded from: classes3.dex */
public class BindingsHelper {
    public static final int ALIGNMENT = 8;
    public static final int ARRAY_NULLABLE = 1;
    public static final int ELEMENT_NULLABLE = 2;
    public static final DataHeader MAP_STRUCT_HEADER = new DataHeader(24, 0);
    public static final int NOTHING_NULLABLE = 0;
    public static final int POINTER_SIZE = 8;
    public static final int SERIALIZED_HANDLE_SIZE = 4;
    public static final int SERIALIZED_INTERFACE_SIZE = 8;
    public static final int UNION_SIZE = 16;
    public static final int UNSPECIFIED_ARRAY_LENGTH = -1;

    public static int align(int i) {
        return ((i + 8) - 1) & (-8);
    }

    public static long align(long j) {
        return ((j + 8) - 1) & (-8);
    }

    public static int hashCode(int i) {
        return i;
    }

    public static int hashCode(long j) {
        return (int) (j ^ (j >>> 32));
    }

    public static int hashCode(boolean z) {
        return z ? 1231 : 1237;
    }

    public static boolean isArrayNullable(int i) {
        return (i & 1) > 0;
    }

    public static boolean isElementNullable(int i) {
        return (i & 2) > 0;
    }

    private static boolean isSurrogate(char c) {
        return c >= 55296 && c < 57344;
    }

    public static int utf8StringSizeInBytes(String str) {
        int i = 0;
        int i2 = 0;
        while (i < str.length()) {
            char cCharAt = str.charAt(i);
            boolean zIsSurrogate = isSurrogate(cCharAt);
            int codePoint = cCharAt;
            if (zIsSurrogate) {
                i++;
                codePoint = Character.toCodePoint(cCharAt, str.charAt(i));
            }
            i2++;
            if (codePoint > 127) {
                i2++;
                if (codePoint > 2047) {
                    i2++;
                    if (codePoint > 65535) {
                        i2++;
                        if (codePoint > 2097151) {
                            i2++;
                            if (codePoint > 67108863) {
                                i2++;
                            }
                        }
                    }
                }
            }
            i++;
        }
        return i2;
    }

    public static boolean equals(Object obj, Object obj2) {
        if (obj == obj2) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        return obj.equals(obj2);
    }

    public static int hashCode(Object obj) {
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public static int hashCode(float f) {
        return Float.floatToIntBits(f);
    }

    public static int hashCode(double d) {
        return hashCode(Double.doubleToLongBits(d));
    }

    static AsyncWaiter getDefaultAsyncWaiterForHandle(Handle handle) {
        if (handle.getCore() != null) {
            return handle.getCore().getDefaultAsyncWaiter();
        }
        return null;
    }
}
