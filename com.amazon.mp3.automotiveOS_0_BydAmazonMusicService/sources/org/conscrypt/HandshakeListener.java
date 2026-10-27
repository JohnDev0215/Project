package org.conscrypt;

import javax.net.ssl.SSLException;

/* JADX INFO: loaded from: classes7.dex */
public abstract class HandshakeListener {
    public abstract void onHandshakeFinished() throws SSLException;
}
