package com.amazon.CoralAndroidClient.Connector;

import com.amazon.CoralAndroidClient.ClientBase.ClientRequest;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import com.amazon.CoralAndroidClient.Exception.NativeException;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes2.dex */
public final class ClientRequestJsonEncoder implements ClientRequestEncoder {
    private final Charset mCharset;

    public ClientRequestJsonEncoder(String str) throws NativeException {
        if (Helper.isStringNullOrEmpty(str)) {
            throw new NativeException("charset name is null or empty.");
        }
        try {
            this.mCharset = Charset.forName(str);
        } catch (IllegalArgumentException e) {
            throw new NativeException(e);
        }
    }

    @Override // com.amazon.CoralAndroidClient.Connector.ClientRequestEncoder
    public byte[] encode(ClientRequest clientRequest) throws NativeException {
        if (clientRequest == null) {
            throw new NativeException("ClientRequest is null");
        }
        String json = clientRequest.toJson();
        if (json == null) {
            throw new NativeException("content body is null");
        }
        return json.getBytes(this.mCharset);
    }
}
