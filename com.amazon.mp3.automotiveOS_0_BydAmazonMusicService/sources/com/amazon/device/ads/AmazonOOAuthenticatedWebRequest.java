package com.amazon.device.ads;

import com.amazon.identity.auth.device.api.AuthenticatedURLConnection;
import com.amazon.identity.auth.device.api.AuthenticationMethod;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/* JADX INFO: loaded from: classes2.dex */
class AmazonOOAuthenticatedWebRequest extends HttpURLConnectionWebRequest {
    private final AuthenticationMethod authMethod;

    public AmazonOOAuthenticatedWebRequest(AuthenticationMethod authenticationMethod) {
        this.authMethod = authenticationMethod;
    }

    @Override // com.amazon.device.ads.HttpURLConnectionWebRequest
    protected HttpURLConnection openConnection(URL url) throws IOException {
        return AuthenticatedURLConnection.openConnection(url, this.authMethod);
    }
}
