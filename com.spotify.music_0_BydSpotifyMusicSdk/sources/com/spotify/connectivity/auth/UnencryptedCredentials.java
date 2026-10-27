package com.spotify.connectivity.auth;

import java.util.Arrays;
import kotlin.jvm.internal.DefaultConstructorMarker;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class UnencryptedCredentials {
    private final byte[] authBlob;
    private final String username;

    public UnencryptedCredentials() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public static /* synthetic */ UnencryptedCredentials copy$default(UnencryptedCredentials unencryptedCredentials, String str, byte[] bArr, int i, Object obj) {
        if ((i & 1) != 0) {
            str = unencryptedCredentials.username;
        }
        if ((i & 2) != 0) {
            bArr = unencryptedCredentials.authBlob;
        }
        return unencryptedCredentials.copy(str, bArr);
    }

    public final String component1() {
        return this.username;
    }

    public final byte[] component2() {
        return this.authBlob;
    }

    public final UnencryptedCredentials copy(String str, byte[] bArr) {
        return new UnencryptedCredentials(str, bArr);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof UnencryptedCredentials)) {
            return false;
        }
        UnencryptedCredentials unencryptedCredentials = (UnencryptedCredentials) obj;
        return v03.b(this.username, unencryptedCredentials.username) && v03.b(this.authBlob, unencryptedCredentials.authBlob);
    }

    public final byte[] getAuthBlob() {
        return this.authBlob;
    }

    public final String getUsername() {
        return this.username;
    }

    public int hashCode() {
        String str = this.username;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        byte[] bArr = this.authBlob;
        return iHashCode + (bArr != null ? Arrays.hashCode(bArr) : 0);
    }

    public String toString() {
        return "UnencryptedCredentials(username=" + this.username + ", authBlob=" + Arrays.toString(this.authBlob) + ')';
    }

    public UnencryptedCredentials(String str, byte[] bArr) {
        this.username = str;
        this.authBlob = bArr;
    }

    public /* synthetic */ UnencryptedCredentials(String str, byte[] bArr, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : bArr);
    }
}
