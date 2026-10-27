package com.amazon.mp3.net.dmls;

import com.amazon.mp3.net.JsonHttpClient;
import com.amazon.mp3.net.dmls.DMLSExceptions;

/* JADX INFO: loaded from: classes3.dex */
public class DMLSHttpClient extends JsonHttpClient {
    @Override // com.amazon.mp3.net.JsonHttpClient, com.amazon.mp3.net.AbstractHttpClient
    protected boolean validateHttpStatusCode(int status) {
        return status == 200 || (status >= 400 && status <= 599);
    }

    @Override // com.amazon.mp3.net.AbstractHttpClient
    protected void validateResponse() throws DMLSExceptions.DMLSException {
        DMLSExceptions.validateCoralResponse(this);
    }
}
