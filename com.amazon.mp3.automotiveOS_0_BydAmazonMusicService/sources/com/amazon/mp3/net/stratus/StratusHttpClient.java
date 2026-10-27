package com.amazon.mp3.net.stratus;

import com.amazon.mp3.net.RequestIdInjectingHttpClient;
import com.amazon.mp3.net.stratus.StratusExceptions;

/* JADX INFO: loaded from: classes3.dex */
public class StratusHttpClient extends RequestIdInjectingHttpClient {
    @Override // com.amazon.mp3.net.JsonHttpClient, com.amazon.mp3.net.AbstractHttpClient
    protected boolean validateHttpStatusCode(int status) {
        return status == 200 || (status >= 400 && status <= 500);
    }

    @Override // com.amazon.mp3.net.AbstractHttpClient
    protected void validateResponse() throws StratusExceptions.StratusResourceNotFoundException, StratusExceptions.StratusServiceException {
        StratusExceptions.validateCoralResponse(this);
    }
}
