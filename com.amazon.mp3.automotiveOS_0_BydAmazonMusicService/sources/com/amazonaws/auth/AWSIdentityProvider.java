package com.amazonaws.auth;

/* JADX INFO: loaded from: classes5.dex */
public interface AWSIdentityProvider {
    String getToken();

    String refresh();
}
