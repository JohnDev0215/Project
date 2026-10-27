package io.objectbox.sync;

/* JADX INFO: loaded from: classes5.dex */
public class SyncCredentials {
    public static SyncCredentials sharedSecret(String str) {
        return new SyncCredentialsToken(CredentialsType.SHARED_SECRET, str);
    }

    public static SyncCredentials sharedSecret(byte[] bArr) {
        return new SyncCredentialsToken(CredentialsType.SHARED_SECRET, bArr);
    }

    public static SyncCredentials google(String str) {
        return new SyncCredentialsToken(CredentialsType.GOOGLE, str);
    }

    public static SyncCredentials none() {
        return new SyncCredentialsToken(CredentialsType.NONE);
    }

    public enum CredentialsType {
        NONE(1),
        SHARED_SECRET(2),
        GOOGLE(3);

        public final long id;

        CredentialsType(long j) {
            this.id = j;
        }
    }

    SyncCredentials() {
    }
}
