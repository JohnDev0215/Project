package org.apache.shiro.util;

import java.io.File;
import java.io.InputStream;
import java.util.Arrays;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.CodecSupport;
import org.apache.shiro.codec.Hex;

/* JADX INFO: loaded from: classes.dex */
public class SimpleByteSource implements ByteSource {
    private final byte[] bytes;
    private String cachedBase64;
    private String cachedHex;

    public SimpleByteSource(byte[] bArr) {
        this.bytes = bArr;
    }

    public SimpleByteSource(char[] cArr) {
        this.bytes = CodecSupport.toBytes(cArr);
    }

    public SimpleByteSource(String str) {
        this.bytes = CodecSupport.toBytes(str);
    }

    public SimpleByteSource(ByteSource byteSource) {
        this.bytes = byteSource.getBytes();
    }

    public SimpleByteSource(File file) {
        this.bytes = new BytesHelper().getBytes(file);
    }

    public SimpleByteSource(InputStream inputStream) {
        this.bytes = new BytesHelper().getBytes(inputStream);
    }

    public static boolean isCompatible(Object obj) {
        return (obj instanceof byte[]) || (obj instanceof char[]) || (obj instanceof String) || (obj instanceof ByteSource) || (obj instanceof File) || (obj instanceof InputStream);
    }

    @Override // org.apache.shiro.util.ByteSource
    public byte[] getBytes() {
        return this.bytes;
    }

    @Override // org.apache.shiro.util.ByteSource
    public boolean isEmpty() {
        byte[] bArr = this.bytes;
        return bArr == null || bArr.length == 0;
    }

    @Override // org.apache.shiro.util.ByteSource
    public String toHex() {
        if (this.cachedHex == null) {
            this.cachedHex = Hex.encodeToString(getBytes());
        }
        return this.cachedHex;
    }

    @Override // org.apache.shiro.util.ByteSource
    public String toBase64() {
        if (this.cachedBase64 == null) {
            this.cachedBase64 = Base64.encodeToString(getBytes());
        }
        return this.cachedBase64;
    }

    public String toString() {
        return toBase64();
    }

    public int hashCode() {
        byte[] bArr = this.bytes;
        if (bArr == null || bArr.length == 0) {
            return 0;
        }
        return Arrays.hashCode(bArr);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ByteSource) {
            return Arrays.equals(getBytes(), ((ByteSource) obj).getBytes());
        }
        return false;
    }

    private static final class BytesHelper extends CodecSupport {
        private BytesHelper() {
        }

        public byte[] getBytes(File file) {
            return toBytes(file);
        }

        public byte[] getBytes(InputStream inputStream) {
            return toBytes(inputStream);
        }
    }
}
