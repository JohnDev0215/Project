package com.spotify.connectivity.auth;

import java.util.Arrays;
import kotlin.jvm.internal.DefaultConstructorMarker;
import p.hf2;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class SerializableCredentials {
    private final byte[] authBlob;
    private final String deviceId;
    private final String username;

    public SerializableCredentials() {
        this(null, null, null, 7, null);
    }

    private final String component3() {
        return this.deviceId;
    }

    public static /* synthetic */ SerializableCredentials copy$default(SerializableCredentials serializableCredentials, String str, byte[] bArr, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = serializableCredentials.username;
        }
        if ((i & 2) != 0) {
            bArr = serializableCredentials.authBlob;
        }
        if ((i & 4) != 0) {
            str2 = serializableCredentials.deviceId;
        }
        return serializableCredentials.copy(str, bArr, str2);
    }

    public final String component1() {
        return this.username;
    }

    public final byte[] component2() {
        return this.authBlob;
    }

    public final SerializableCredentials copy(String str, byte[] bArr, String str2) {
        return new SerializableCredentials(str, bArr, str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SerializableCredentials)) {
            return false;
        }
        SerializableCredentials serializableCredentials = (SerializableCredentials) obj;
        return v03.b(this.username, serializableCredentials.username) && v03.b(this.authBlob, serializableCredentials.authBlob) && v03.b(this.deviceId, serializableCredentials.deviceId);
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
        int iHashCode2 = (iHashCode + (bArr == null ? 0 : Arrays.hashCode(bArr))) * 31;
        String str2 = this.deviceId;
        return iHashCode2 + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("SerializableCredentials(username=");
        sb.append(this.username);
        sb.append(", authBlob=");
        sb.append(Arrays.toString(this.authBlob));
        sb.append(", deviceId=");
        return hf2.d(sb, this.deviceId, ')');
    }

    public SerializableCredentials(String str, byte[] bArr, String str2) {
        this.username = str;
        this.authBlob = bArr;
        this.deviceId = str2;
    }

    public /* synthetic */ SerializableCredentials(String str, byte[] bArr, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : bArr, (i & 4) != 0 ? null : str2);
    }
}
