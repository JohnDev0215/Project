package org.apache.http.conn.ssl;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.SSLContext;
import org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory;

/* JADX INFO: loaded from: classes7.dex */
@Deprecated
public class SSLContexts {
    public static SSLContext createDefault() throws SSLInitializationException {
        try {
            SSLContext sSLContext = SSLContext.getInstance(SSLSocketFactoryFactory.DEFAULT_PROTOCOL);
            sSLContext.init(null, null, null);
            return sSLContext;
        } catch (KeyManagementException e) {
            throw new SSLInitializationException(e.getMessage(), e);
        } catch (NoSuchAlgorithmException e2) {
            throw new SSLInitializationException(e2.getMessage(), e2);
        }
    }
}
