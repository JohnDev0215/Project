package org.apache.commons.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes7.dex */
public class IOUtils {
    public static final char DIR_SEPARATOR = File.separatorChar;
    public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];

    @Deprecated
    public static final String LINE_SEPARATOR = System.lineSeparator();
    public static final String LINE_SEPARATOR_UNIX = StandardLineSeparator.LF.getString();
    public static final String LINE_SEPARATOR_WINDOWS = StandardLineSeparator.CRLF.getString();
    private static final ThreadLocal<byte[]> SKIP_BYTE_BUFFER;
    private static final ThreadLocal<char[]> SKIP_CHAR_BUFFER;

    static {
        final Supplier supplier = new Supplier() { // from class: org.apache.commons.io.IOUtils$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return IOUtils.byteArray();
            }
        };
        SKIP_BYTE_BUFFER = new ThreadLocal() { // from class: org.apache.commons.io.IOUtils$$ExternalSyntheticThreadLocal2
            @Override // java.lang.ThreadLocal
            protected /* synthetic */ Object initialValue() {
                return supplier.get();
            }
        };
        final Supplier supplier2 = new Supplier() { // from class: org.apache.commons.io.IOUtils$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return IOUtils.charArray();
            }
        };
        SKIP_CHAR_BUFFER = new ThreadLocal() { // from class: org.apache.commons.io.IOUtils$$ExternalSyntheticThreadLocal2
            @Override // java.lang.ThreadLocal
            protected /* synthetic */ Object initialValue() {
                return supplier2.get();
            }
        };
    }

    public static byte[] byteArray() {
        return byteArray(8192);
    }

    public static byte[] byteArray(int i) {
        return new byte[i];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static char[] charArray() {
        return charArray(8192);
    }

    private static char[] charArray(int i) {
        return new char[i];
    }

    public static int copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        long jCopyLarge = copyLarge(inputStream, outputStream);
        if (jCopyLarge > 2147483647L) {
            return -1;
        }
        return (int) jCopyLarge;
    }

    public static long copy(InputStream inputStream, OutputStream outputStream, int i) throws IOException {
        return copyLarge(inputStream, outputStream, byteArray(i));
    }

    public static long copyLarge(InputStream inputStream, OutputStream outputStream) throws IOException {
        return copy(inputStream, outputStream, 8192);
    }

    public static long copyLarge(InputStream inputStream, OutputStream outputStream, byte[] bArr) throws IOException {
        Objects.requireNonNull(inputStream, "inputStream");
        Objects.requireNonNull(outputStream, "outputStream");
        long j = 0;
        while (true) {
            int i = inputStream.read(bArr);
            if (-1 == i) {
                return j;
            }
            outputStream.write(bArr, 0, i);
            j += (long) i;
        }
    }

    public static int length(Object[] objArr) {
        if (objArr == null) {
            return 0;
        }
        return objArr.length;
    }
}
