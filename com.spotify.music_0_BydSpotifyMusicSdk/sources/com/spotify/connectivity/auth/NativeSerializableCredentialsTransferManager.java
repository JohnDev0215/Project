package com.spotify.connectivity.auth;

/* JADX INFO: loaded from: classes.dex */
public final class NativeSerializableCredentialsTransferManager implements SerializableCredentialsTransferManager {
    @Override // com.spotify.connectivity.auth.SerializableCredentialsTransferManager
    public native SerializableCredentials transferCredentialsForDeviceId(SerializableCredentials serializableCredentials, String str);
}
