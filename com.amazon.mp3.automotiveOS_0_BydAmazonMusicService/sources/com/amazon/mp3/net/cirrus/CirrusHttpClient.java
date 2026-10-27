package com.amazon.mp3.net.cirrus;

import com.amazon.mp3.net.CoralHttpClient;
import com.amazon.mp3.net.service.ServiceException;

/* JADX INFO: loaded from: classes3.dex */
public class CirrusHttpClient extends CoralHttpClient {
    @Override // com.amazon.mp3.net.JsonHttpClient, com.amazon.mp3.net.AbstractHttpClient
    protected boolean validateHttpStatusCode(int status) {
        return status == 200 || (status >= 400 && status <= 599);
    }

    @Override // com.amazon.mp3.net.CoralHttpClient
    protected ServiceException getMappedException(String type, String message) {
        return CirrusExceptions.getMappedException(type, message);
    }
}
