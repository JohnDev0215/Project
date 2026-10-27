package com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations;

/* JADX INFO: loaded from: classes5.dex */
public interface CognitoIdentityProviderContinuation<T> {
    void continueTask();

    T getParameters();
}
