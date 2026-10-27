package org.apache.commons.net.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.net.ssl.SSLSocket;

/* JADX INFO: loaded from: classes3.dex */
public class SSLSocketUtils {
    private SSLSocketUtils() {
    }

    public static boolean enableEndpointNameVerification(SSLSocket socket) {
        Object sslParams;
        try {
            Class<?> cls = Class.forName("javax.net.ssl.SSLParameters");
            Method setEndpointIdentificationAlgorithm = cls.getDeclaredMethod("setEndpointIdentificationAlgorithm", String.class);
            Method getSSLParameters = SSLSocket.class.getDeclaredMethod("getSSLParameters", new Class[0]);
            Method setSSLParameters = SSLSocket.class.getDeclaredMethod("setSSLParameters", cls);
            if (setEndpointIdentificationAlgorithm != null && getSSLParameters != null && setSSLParameters != null && (sslParams = getSSLParameters.invoke(socket, new Object[0])) != null) {
                setEndpointIdentificationAlgorithm.invoke(sslParams, "HTTPS");
                setSSLParameters.invoke(socket, sslParams);
                return true;
            }
        } catch (ClassNotFoundException e) {
        } catch (IllegalAccessException e2) {
        } catch (IllegalArgumentException e3) {
        } catch (NoSuchMethodException e4) {
        } catch (SecurityException e5) {
        } catch (InvocationTargetException e6) {
        }
        return false;
    }
}
