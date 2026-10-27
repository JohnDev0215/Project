package com.amplifyframework.api.aws.sigv4;

import com.amplifyframework.api.ApiException;

/* JADX INFO: loaded from: classes5.dex */
public interface CognitoUserPoolsAuthProvider {
    String getLatestAuthToken() throws ApiException;

    String getUsername();
}
