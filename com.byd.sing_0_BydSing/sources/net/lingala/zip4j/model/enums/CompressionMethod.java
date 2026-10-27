package net.lingala.zip4j.model.enums;

import net.lingala.zip4j.exception.ZipException;

/* JADX INFO: loaded from: classes.dex */
public enum CompressionMethod {
    STORE(0),
    DEFLATE(8),
    AES_INTERNAL_ONLY(99);

    private int code;

    CompressionMethod(int i) {
        this.code = i;
    }

    public static CompressionMethod getCompressionMethodFromCode(int i) throws ZipException {
        CompressionMethod[] compressionMethodArrValues = values();
        for (int i2 = 0; i2 < 3; i2++) {
            CompressionMethod compressionMethod = compressionMethodArrValues[i2];
            if (compressionMethod.getCode() == i) {
                return compressionMethod;
            }
        }
        throw new ZipException("Unknown compression method", ZipException.Type.UNKNOWN_COMPRESSION_METHOD);
    }

    public int getCode() {
        return this.code;
    }
}
