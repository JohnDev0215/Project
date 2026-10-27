package org.apache.shiro.util;

import java.io.File;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public interface ByteSource {
    byte[] getBytes();

    boolean isEmpty();

    String toBase64();

    String toHex();

    public static final class Util {
        public static ByteSource bytes(byte[] bArr) {
            return new SimpleByteSource(bArr);
        }

        public static ByteSource bytes(char[] cArr) {
            return new SimpleByteSource(cArr);
        }

        public static ByteSource bytes(String str) {
            return new SimpleByteSource(str);
        }

        public static ByteSource bytes(ByteSource byteSource) {
            return new SimpleByteSource(byteSource);
        }

        public static ByteSource bytes(File file) {
            return new SimpleByteSource(file);
        }

        public static ByteSource bytes(InputStream inputStream) {
            return new SimpleByteSource(inputStream);
        }

        public static boolean isCompatible(Object obj) {
            return SimpleByteSource.isCompatible(obj);
        }

        public static ByteSource bytes(Object obj) throws IllegalArgumentException {
            if (obj == null) {
                return null;
            }
            if (!isCompatible(obj)) {
                throw new IllegalArgumentException("Unable to heuristically acquire bytes for object of type [" + obj.getClass().getName() + "].  If this type is indeed a byte-backed data type, you might want to write your own ByteSource implementation to extract its bytes explicitly.");
            }
            if (obj instanceof byte[]) {
                return bytes((byte[]) obj);
            }
            if (obj instanceof ByteSource) {
                return (ByteSource) obj;
            }
            if (obj instanceof char[]) {
                return bytes((char[]) obj);
            }
            if (obj instanceof String) {
                return bytes((String) obj);
            }
            if (obj instanceof File) {
                return bytes((File) obj);
            }
            if (obj instanceof InputStream) {
                return bytes((InputStream) obj);
            }
            throw new IllegalStateException("Encountered unexpected byte source.  This is a bug - please notify the Shiro developer list asap (the isCompatible implementation does not reflect this method's implementation).");
        }
    }
}
