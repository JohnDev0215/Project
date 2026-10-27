package org.apache.shiro.crypto.hash;

import java.util.Objects;
import org.apache.shiro.util.ByteSource;

/* JADX INFO: loaded from: classes.dex */
public class SimpleHashRequest implements HashRequest {
    private final String algorithmName;
    private final int iterations;
    private final ByteSource salt;
    private final ByteSource source;

    public SimpleHashRequest(String str, ByteSource byteSource, ByteSource byteSource2, int i) {
        Objects.requireNonNull(byteSource, "source argument cannot be null");
        this.source = byteSource;
        this.salt = byteSource2;
        this.algorithmName = str;
        this.iterations = Math.max(0, i);
    }

    @Override // org.apache.shiro.crypto.hash.HashRequest
    public ByteSource getSource() {
        return this.source;
    }

    @Override // org.apache.shiro.crypto.hash.HashRequest
    public ByteSource getSalt() {
        return this.salt;
    }

    @Override // org.apache.shiro.crypto.hash.HashRequest
    public int getIterations() {
        return this.iterations;
    }

    @Override // org.apache.shiro.crypto.hash.HashRequest
    public String getAlgorithmName() {
        return this.algorithmName;
    }
}
