package org.conscrypt;

import java.io.IOException;
import java.net.Socket;

/* JADX INFO: loaded from: classes7.dex */
public class PreKitKatPlatformOpenSSLSocketAdapterFactory extends BaseOpenSSLSocketAdapterFactory {
    public PreKitKatPlatformOpenSSLSocketAdapterFactory(OpenSSLSocketFactoryImpl openSSLSocketFactoryImpl) {
        super(openSSLSocketFactoryImpl);
    }

    @Override // org.conscrypt.BaseOpenSSLSocketAdapterFactory
    protected Socket wrap(OpenSSLSocketImpl openSSLSocketImpl) throws IOException {
        return new PreKitKatPlatformOpenSSLSocketImplAdapter(openSSLSocketImpl);
    }
}
