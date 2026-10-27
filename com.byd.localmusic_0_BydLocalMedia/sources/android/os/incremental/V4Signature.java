package android.os.incremental;

import android.os.ParcelFileDescriptor;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: classes2.dex */
public class V4Signature {
    public static final String EXT = ".idsig";
    public static final int HASHING_ALGORITHM_SHA256 = 1;
    public static final byte LOG2_BLOCK_SIZE_4096_BYTES = 12;
    public static final int SUPPORTED_VERSION = 2;
    public final byte[] hashingInfo;
    public final byte[] signingInfo;
    public final int version;

    public static class HashingInfo {
        public final int hashAlgorithm;
        public final byte log2BlockSize;
        public final byte[] rawRootHash;
        public final byte[] salt;

        HashingInfo(int i, byte b, byte[] bArr, byte[] bArr2) {
            this.hashAlgorithm = i;
            this.log2BlockSize = b;
            this.salt = bArr;
            this.rawRootHash = bArr2;
        }

        public static HashingInfo fromByteArray(byte[] bArr) throws IOException {
            ByteBuffer byteBufferOrder = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
            return new HashingInfo(byteBufferOrder.getInt(), byteBufferOrder.get(), V4Signature.readBytes(byteBufferOrder), V4Signature.readBytes(byteBufferOrder));
        }
    }

    public static class SigningInfo {
        public final byte[] additionalData;
        public final byte[] apkDigest;
        public final byte[] certificate;
        public final byte[] publicKey;
        public final byte[] signature;
        public final int signatureAlgorithmId;

        SigningInfo(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, int i, byte[] bArr5) {
            this.apkDigest = bArr;
            this.certificate = bArr2;
            this.additionalData = bArr3;
            this.publicKey = bArr4;
            this.signatureAlgorithmId = i;
            this.signature = bArr5;
        }

        public static SigningInfo fromByteArray(byte[] bArr) throws IOException {
            ByteBuffer byteBufferOrder = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
            return new SigningInfo(V4Signature.readBytes(byteBufferOrder), V4Signature.readBytes(byteBufferOrder), V4Signature.readBytes(byteBufferOrder), V4Signature.readBytes(byteBufferOrder), byteBufferOrder.getInt(), V4Signature.readBytes(byteBufferOrder));
        }
    }

    public static V4Signature readFrom(ParcelFileDescriptor parcelFileDescriptor) throws IOException {
        ParcelFileDescriptor.AutoCloseInputStream autoCloseInputStream = new ParcelFileDescriptor.AutoCloseInputStream(parcelFileDescriptor.dup());
        try {
            V4Signature from = readFrom(autoCloseInputStream);
            autoCloseInputStream.close();
            return from;
        } catch (Throwable th) {
            try {
                autoCloseInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static V4Signature readFrom(byte[] bArr) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        try {
            V4Signature from = readFrom(byteArrayInputStream);
            byteArrayInputStream.close();
            return from;
        } catch (Throwable th) {
            try {
                byteArrayInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public byte[] toByteArray() {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                writeTo(byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                return byteArray;
            } finally {
            }
        } catch (IOException unused) {
            return null;
        }
    }

    public static byte[] getSigningData(long j, HashingInfo hashingInfo, SigningInfo signingInfo) {
        int iBytesSize = bytesSize(hashingInfo.salt) + 17 + bytesSize(hashingInfo.rawRootHash) + bytesSize(signingInfo.apkDigest) + bytesSize(signingInfo.certificate) + bytesSize(signingInfo.additionalData);
        ByteBuffer byteBufferOrder = ByteBuffer.allocate(iBytesSize).order(ByteOrder.LITTLE_ENDIAN);
        byteBufferOrder.putInt(iBytesSize);
        byteBufferOrder.putLong(j);
        byteBufferOrder.putInt(hashingInfo.hashAlgorithm);
        byteBufferOrder.put(hashingInfo.log2BlockSize);
        writeBytes(byteBufferOrder, hashingInfo.salt);
        writeBytes(byteBufferOrder, hashingInfo.rawRootHash);
        writeBytes(byteBufferOrder, signingInfo.apkDigest);
        writeBytes(byteBufferOrder, signingInfo.certificate);
        writeBytes(byteBufferOrder, signingInfo.additionalData);
        return byteBufferOrder.array();
    }

    public boolean isVersionSupported() {
        return this.version == 2;
    }

    private V4Signature(int i, byte[] bArr, byte[] bArr2) {
        this.version = i;
        this.hashingInfo = bArr;
        this.signingInfo = bArr2;
    }

    private static V4Signature readFrom(InputStream inputStream) throws IOException {
        return new V4Signature(readIntLE(inputStream), readBytes(inputStream), readBytes(inputStream));
    }

    private void writeTo(OutputStream outputStream) throws IOException {
        writeIntLE(outputStream, this.version);
        writeBytes(outputStream, this.hashingInfo);
        writeBytes(outputStream, this.signingInfo);
    }

    private static int bytesSize(byte[] bArr) {
        return (bArr == null ? 0 : bArr.length) + 4;
    }

    private static void readFully(InputStream inputStream, byte[] bArr) throws IOException {
        int length = bArr.length;
        int i = 0;
        while (i < length) {
            int i2 = inputStream.read(bArr, i, length - i);
            if (i2 < 0) {
                throw new EOFException();
            }
            i += i2;
        }
    }

    private static int readIntLE(InputStream inputStream) throws IOException {
        byte[] bArr = new byte[4];
        readFully(inputStream, bArr);
        return ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN).getInt();
    }

    private static void writeIntLE(OutputStream outputStream, int i) throws IOException {
        outputStream.write(ByteBuffer.wrap(new byte[4]).order(ByteOrder.LITTLE_ENDIAN).putInt(i).array());
    }

    private static byte[] readBytes(InputStream inputStream) throws IOException {
        try {
            byte[] bArr = new byte[readIntLE(inputStream)];
            readFully(inputStream, bArr);
            return bArr;
        } catch (EOFException unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte[] readBytes(ByteBuffer byteBuffer) throws IOException {
        if (byteBuffer.remaining() < 4) {
            throw new EOFException();
        }
        int i = byteBuffer.getInt();
        if (byteBuffer.remaining() < i) {
            throw new EOFException();
        }
        byte[] bArr = new byte[i];
        byteBuffer.get(bArr);
        return bArr;
    }

    private static void writeBytes(OutputStream outputStream, byte[] bArr) throws IOException {
        if (bArr == null) {
            writeIntLE(outputStream, 0);
        } else {
            writeIntLE(outputStream, bArr.length);
            outputStream.write(bArr);
        }
    }

    private static void writeBytes(ByteBuffer byteBuffer, byte[] bArr) {
        if (bArr == null) {
            byteBuffer.putInt(0);
        } else {
            byteBuffer.putInt(bArr.length);
            byteBuffer.put(bArr);
        }
    }
}
