package com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers;

import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserCodeDeliveryDetails;

/* JADX INFO: loaded from: classes5.dex */
public interface VerificationHandler {
    void onFailure(Exception exc);

    void onSuccess(CognitoUserCodeDeliveryDetails cognitoUserCodeDeliveryDetails);
}
