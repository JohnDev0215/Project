package org.apache.commons.compress.archivers.sevenz;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.compress.utils.ByteUtils;

/* JADX INFO: loaded from: classes4.dex */
abstract class CoderBase {
    private final Class<?>[] acceptableOptions;

    abstract InputStream decode(String str, InputStream inputStream, long j, Coder coder, byte[] bArr, int i) throws IOException;

    Object getOptionsFromCoder(Coder coder, InputStream inputStream) throws IOException {
        return null;
    }

    protected CoderBase(Class<?>... clsArr) {
        this.acceptableOptions = clsArr;
    }

    boolean canAcceptOptions(Object obj) {
        for (Class<?> cls : this.acceptableOptions) {
            if (cls.isInstance(obj)) {
                return true;
            }
        }
        return false;
    }

    byte[] getOptionsAsProperties(Object obj) throws IOException {
        return ByteUtils.EMPTY_BYTE_ARRAY;
    }

    OutputStream encode(OutputStream outputStream, Object obj) throws IOException {
        throw new UnsupportedOperationException("Method doesn't support writing");
    }

    protected static int numberOptionOrDefault(Object obj, int i) {
        return obj instanceof Number ? ((Number) obj).intValue() : i;
    }
}
