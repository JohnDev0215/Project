package com.spotify.connectivity.auth;

import com.spotify.connectivity.auth.CredentialsStorage;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class NativeCredentialsStorage implements CredentialsStorage {
    private long nThis;

    private NativeCredentialsStorage() {
    }

    @Override // com.spotify.connectivity.auth.CredentialsStorage
    public native void deleteStoredCredentials();

    public final native void destroy();

    @Override // com.spotify.connectivity.auth.CredentialsStorage
    public native void forgetRememberMe(String str);

    @Override // com.spotify.connectivity.auth.CredentialsStorage
    public native boolean getRememberMeMode();

    @Override // com.spotify.connectivity.auth.CredentialsStorage
    public native List<String> getSavedUsernames();

    @Override // com.spotify.connectivity.auth.CredentialsStorage
    public native CredentialsStorage.StoredCredentialsAndUsername getStoredCredentials();

    @Override // com.spotify.connectivity.auth.CredentialsStorage
    public native SerializableCredentials getStoredCredentialsForUser(String str);

    @Override // com.spotify.connectivity.auth.CredentialsStorage
    public native void saveLoginDetails(SerializableCredentials serializableCredentials, String str, boolean z);

    @Override // com.spotify.connectivity.auth.CredentialsStorage
    public native void setRememberMeMode(String str, boolean z);
}
