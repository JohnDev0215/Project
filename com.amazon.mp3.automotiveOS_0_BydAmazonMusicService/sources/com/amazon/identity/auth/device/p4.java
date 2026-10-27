package com.amazon.identity.auth.device;

import com.amazon.identity.auth.device.api.AuthenticatedURLConnection;
import com.amazon.identity.auth.device.api.AuthenticationMethod;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class p4 {
    public HttpURLConnection a(URL url, AuthenticationMethod authenticationMethod) throws IOException {
        return (HttpURLConnection) ib.a(AuthenticatedURLConnection.openConnection(url, authenticationMethod));
    }
}
