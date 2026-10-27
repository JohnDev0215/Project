package org.greenrobot.essentials;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.ByteOrder;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.CharCompanionObject;
import sun.misc.Unsafe;

/* JADX INFO: loaded from: classes5.dex */
public abstract class PrimitiveArrayUtils {
    private static final PrimitiveArrayUtils instanceSafe = new SafeImpl();
    private static volatile PrimitiveArrayUtils instanceUnsafe;

    public abstract int getIntBE(byte[] bArr, int i);

    public abstract int getIntLE(byte[] bArr, int i);

    public abstract int getIntLE(char[] cArr, int i);

    public abstract long getLongBE(byte[] bArr, int i);

    public abstract long getLongLE(byte[] bArr, int i);

    public static PrimitiveArrayUtils getInstance() {
        PrimitiveArrayUtils primitiveArrayUtils = instanceUnsafe;
        return primitiveArrayUtils == null ? instanceSafe : primitiveArrayUtils;
    }

    public static boolean initUnsafeInstance() {
        if (instanceUnsafe != null || UnsafeImpl.UNSAFE == null) {
            return false;
        }
        synchronized (PrimitiveArrayUtils.class) {
            if (instanceUnsafe != null) {
                return true;
            }
            try {
                instanceUnsafe = new UnsafeImpl();
                return true;
            } catch (Throwable unused) {
                return false;
            }
        }
    }

    public static PrimitiveArrayUtils getInstanceSafe() {
        return instanceSafe;
    }

    private static class UnsafeImpl extends PrimitiveArrayUtils {
        private static final boolean BIG_ENDIAN = ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN);
        private static final long BYTE_ARRAY_OFFSET;
        private static final long CHAR_ARRAY_OFFSET;
        private static final boolean UNALIGNED;
        private static final Unsafe UNSAFE;

        private UnsafeImpl() {
        }

        static {
            boolean zInitUnaligned = initUnaligned();
            UNALIGNED = zInitUnaligned;
            if (zInitUnaligned) {
                Unsafe unsafeInitUnsafe = initUnsafe();
                UNSAFE = unsafeInitUnsafe;
                if (unsafeInitUnsafe != null) {
                    BYTE_ARRAY_OFFSET = unsafeInitUnsafe.arrayBaseOffset(byte[].class);
                    CHAR_ARRAY_OFFSET = UNSAFE.arrayBaseOffset(char[].class);
                    return;
                } else {
                    BYTE_ARRAY_OFFSET = 0L;
                    CHAR_ARRAY_OFFSET = 0L;
                    return;
                }
            }
            UNSAFE = null;
            BYTE_ARRAY_OFFSET = 0L;
            CHAR_ARRAY_OFFSET = 0L;
        }

        private static boolean initUnaligned() {
            String property = System.getProperty("java.vendor");
            if (property != null && property.contains("Android")) {
                return guessUnalignedFromOsArch();
            }
            try {
                Method declaredMethod = Class.forName("java.nio.Bits", false, ClassLoader.getSystemClassLoader()).getDeclaredMethod("unaligned", new Class[0]);
                declaredMethod.setAccessible(true);
                return Boolean.TRUE.equals(declaredMethod.invoke(null, new Object[0]));
            } catch (Throwable unused) {
                return guessUnalignedFromOsArch();
            }
        }

        private static boolean guessUnalignedFromOsArch() {
            String property = System.getProperty("os.arch");
            return property != null && property.matches("^(i[3-6]86|x86(_64)?|x64|amd64)$");
        }

        private static Unsafe initUnsafe() {
            Field declaredField;
            try {
                try {
                    declaredField = Unsafe.class.getDeclaredField("theUnsafe");
                } catch (NoSuchElementException unused) {
                    declaredField = Unsafe.class.getDeclaredField("THE_ONE");
                }
                declaredField.setAccessible(true);
                Unsafe unsafe = (Unsafe) declaredField.get(null);
                int i = unsafe.getInt(new byte[]{-54, -2, -70, -66}, unsafe.arrayBaseOffset(byte[].class));
                if (i == -889275714) {
                    if (BIG_ENDIAN) {
                        return unsafe;
                    }
                    System.err.println("Big endian confusion");
                } else if (i == -1095041334) {
                    if (!BIG_ENDIAN) {
                        return unsafe;
                    }
                    System.err.println("Little endian confusion");
                }
            } catch (Throwable unused2) {
            }
            return null;
        }

        @Override // org.greenrobot.essentials.PrimitiveArrayUtils
        public int getIntLE(byte[] bArr, int i) {
            int i2 = UNSAFE.getInt(bArr, BYTE_ARRAY_OFFSET + ((long) i));
            return BIG_ENDIAN ? Integer.reverseBytes(i2) : i2;
        }

        @Override // org.greenrobot.essentials.PrimitiveArrayUtils
        public int getIntLE(char[] cArr, int i) {
            int i2 = UNSAFE.getInt(cArr, CHAR_ARRAY_OFFSET + ((long) (i << 2)));
            return BIG_ENDIAN ? Integer.reverseBytes(i2) : i2;
        }

        @Override // org.greenrobot.essentials.PrimitiveArrayUtils
        public int getIntBE(byte[] bArr, int i) {
            int i2 = UNSAFE.getInt(bArr, BYTE_ARRAY_OFFSET + ((long) i));
            return BIG_ENDIAN ? i2 : Integer.reverseBytes(i2);
        }

        @Override // org.greenrobot.essentials.PrimitiveArrayUtils
        public long getLongLE(byte[] bArr, int i) {
            long j = UNSAFE.getLong(bArr, BYTE_ARRAY_OFFSET + ((long) i));
            return BIG_ENDIAN ? Long.reverseBytes(j) : j;
        }

        @Override // org.greenrobot.essentials.PrimitiveArrayUtils
        public long getLongBE(byte[] bArr, int i) {
            long j = UNSAFE.getLong(bArr, BYTE_ARRAY_OFFSET + ((long) i));
            return BIG_ENDIAN ? j : Long.reverseBytes(j);
        }
    }

    private static class SafeImpl extends PrimitiveArrayUtils {
        private SafeImpl() {
        }

        @Override // org.greenrobot.essentials.PrimitiveArrayUtils
        public int getIntLE(byte[] bArr, int i) {
            return (bArr[i + 3] << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
        }

        @Override // org.greenrobot.essentials.PrimitiveArrayUtils
        public int getIntBE(byte[] bArr, int i) {
            return (bArr[i] << 24) | (bArr[i + 3] & 255) | ((bArr[i + 2] & 255) << 8) | ((bArr[i + 1] & 255) << 16);
        }

        @Override // org.greenrobot.essentials.PrimitiveArrayUtils
        public long getLongLE(byte[] bArr, int i) {
            return (((long) bArr[i + 7]) << 56) | ((long) ((bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16))) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
        }

        @Override // org.greenrobot.essentials.PrimitiveArrayUtils
        public long getLongBE(byte[] bArr, int i) {
            return (((long) bArr[i]) << 56) | ((long) ((bArr[i + 7] & 255) | ((bArr[i + 6] & 255) << 8) | ((bArr[i + 5] & 255) << 16))) | ((((long) bArr[i + 4]) & 255) << 24) | ((((long) bArr[i + 3]) & 255) << 32) | ((((long) bArr[i + 2]) & 255) << 40) | ((((long) bArr[i + 1]) & 255) << 48);
        }

        @Override // org.greenrobot.essentials.PrimitiveArrayUtils
        public int getIntLE(char[] cArr, int i) {
            return ((cArr[i + 1] & CharCompanionObject.MAX_VALUE) << 16) | (cArr[i] & CharCompanionObject.MAX_VALUE);
        }
    }
}
