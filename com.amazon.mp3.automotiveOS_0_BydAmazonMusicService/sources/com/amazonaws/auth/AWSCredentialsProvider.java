package com.amazonaws.auth;

/* JADX INFO: loaded from: classes5.dex */
public interface AWSCredentialsProvider {
    AWSCredentials getCredentials();

    void refresh();
}
