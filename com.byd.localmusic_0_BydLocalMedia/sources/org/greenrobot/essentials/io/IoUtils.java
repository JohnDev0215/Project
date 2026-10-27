package org.greenrobot.essentials.io;

import android.security.keystore.KeyProperties;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.Checksum;
import org.greenrobot.essentials.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public class IoUtils {
    private static final int BUFFER_SIZE = 8192;

    public static byte[] readAllBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        copyAllBytes(inputStream, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] readAllBytesAndClose(InputStream inputStream) throws IOException {
        try {
            return readAllBytes(inputStream);
        } finally {
            safeClose(inputStream);
        }
    }

    public static String readAllChars(Reader reader) throws IOException {
        char[] cArr = new char[4096];
        StringBuilder sb = new StringBuilder();
        while (true) {
            int i = reader.read(cArr);
            if (i != -1) {
                sb.append(cArr, 0, i);
            } else {
                return sb.toString();
            }
        }
    }

    public static String readAllCharsAndClose(Reader reader) throws IOException {
        try {
            return readAllChars(reader);
        } finally {
            safeClose(reader);
        }
    }

    public static void writeAllCharsAndClose(Writer writer, CharSequence charSequence) throws IOException {
        try {
            writer.append(charSequence);
        } finally {
            safeClose(writer);
        }
    }

    public static void updateChecksum(InputStream inputStream, Checksum checksum) throws IOException {
        byte[] bArr = new byte[8192];
        while (true) {
            int i = inputStream.read(bArr);
            if (i == -1) {
                return;
            } else {
                checksum.update(bArr, 0, i);
            }
        }
    }

    public static String getMd5(InputStream inputStream) throws IOException {
        return StringUtils.hex(getDigest(inputStream, KeyProperties.DIGEST_MD5));
    }

    public static String getSha1(InputStream inputStream) throws IOException {
        return StringUtils.hex(getDigest(inputStream, KeyProperties.DIGEST_SHA1));
    }

    public static String getSha256(InputStream inputStream) throws IOException {
        return StringUtils.hex(getDigest(inputStream, "SHA-256"));
    }

    public static byte[] getDigest(InputStream inputStream, String str) throws IOException {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(str);
            byte[] bArr = new byte[8192];
            while (true) {
                int i = inputStream.read(bArr);
                if (i != -1) {
                    messageDigest.update(bArr, 0, i);
                } else {
                    return messageDigest.digest();
                }
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static int copyAllBytes(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[8192];
        int i = 0;
        while (true) {
            int i2 = inputStream.read(bArr);
            if (i2 == -1) {
                return i;
            }
            outputStream.write(bArr, 0, i2);
            i += i2;
        }
    }

    public static void safeClose(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }
}
