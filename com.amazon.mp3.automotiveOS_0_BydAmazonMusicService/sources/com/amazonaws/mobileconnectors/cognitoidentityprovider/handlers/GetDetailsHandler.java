package com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers;

import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserDetails;

/* JADX INFO: loaded from: classes5.dex */
public interface GetDetailsHandler {
    void onFailure(Exception exc);

    void onSuccess(CognitoUserDetails cognitoUserDetails);
}
