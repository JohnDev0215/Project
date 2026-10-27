package com.amazon.identity.auth.device;

import java.io.Closeable;
import java.io.InputStream;
import java.net.HttpURLConnection;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class k4 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f404a = "com.amazon.identity.auth.device.k4";

    public static void a(HttpURLConnection httpURLConnection, String str) throws Throwable {
        InputStream errorStream;
        String str2;
        try {
            errorStream = httpURLConnection.getErrorStream();
            if (errorStream == null) {
                str2 = "No Error Stream Found";
            } else {
                try {
                    str2 = new String(sa.a(errorStream));
                } catch (Throwable th) {
                    th = th;
                    sa.a((Closeable) errorStream);
                    throw th;
                }
            }
            ga.a(f404a, "Received Error from %s: %s", str, str2);
            sa.a((Closeable) errorStream);
        } catch (Throwable th2) {
            th = th2;
            errorStream = null;
        }
    }
}
