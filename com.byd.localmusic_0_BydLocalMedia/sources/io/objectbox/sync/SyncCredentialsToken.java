package io.objectbox.sync;

import com.bumptech.glide.load.Key;
import io.objectbox.sync.SyncCredentials;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public class SyncCredentialsToken extends SyncCredentials {
    private volatile boolean cleared;

    @Nullable
    private byte[] token;
    private final SyncCredentials.CredentialsType type;

    SyncCredentialsToken(SyncCredentials.CredentialsType credentialsType) {
        this.type = credentialsType;
        this.token = null;
    }

    SyncCredentialsToken(SyncCredentials.CredentialsType credentialsType, byte[] bArr) {
        this(credentialsType);
        if (bArr == null || bArr.length == 0) {
            throw new IllegalArgumentException("Token must not be empty");
        }
        this.token = bArr;
    }

    SyncCredentialsToken(SyncCredentials.CredentialsType credentialsType, String str) {
        this(credentialsType, asUtf8Bytes(str));
    }

    public long getTypeId() {
        return this.type.id;
    }

    @Nullable
    public byte[] getTokenBytes() {
        if (this.cleared) {
            throw new IllegalStateException("Credentials already have been cleared");
        }
        return this.token;
    }

    public void clear() {
        this.cleared = true;
        byte[] bArr = this.token;
        if (bArr != null) {
            Arrays.fill(bArr, (byte) 0);
        }
        this.token = null;
    }

    private static byte[] asUtf8Bytes(String str) {
        try {
            return str.getBytes(Key.STRING_CHARSET_NAME);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
