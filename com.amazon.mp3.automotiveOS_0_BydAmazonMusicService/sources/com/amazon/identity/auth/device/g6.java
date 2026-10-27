package com.amazon.identity.auth.device;

import java.net.Socket;
import javax.net.ssl.SSLSocket;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class g6 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final boolean f348a = true;

    public static void a(Socket socket) {
        if (!(socket instanceof SSLSocket)) {
            throw new SecurityException(String.format("SSLSocket is required, %s is not supported", socket.getClass().getCanonicalName()));
        }
        SSLSocket sSLSocket = (SSLSocket) socket;
        if (f348a) {
            String[] supportedProtocols = sSLSocket.getSupportedProtocols();
            String[] strArr = new String[supportedProtocols.length];
            int i = 0;
            for (String str : supportedProtocols) {
                if (str.equals("TLSv1") || str.equals("TLSv1.1") || str.equals("TLSv1.2")) {
                    strArr[i] = str;
                    i++;
                }
            }
            if (i == 0) {
                throw new SecurityException("Device does not support TLS");
            }
            String[] strArr2 = new String[i];
            System.arraycopy(strArr, 0, strArr2, 0, i);
            sSLSocket.setEnabledProtocols(strArr2);
        }
    }
}
