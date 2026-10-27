package org.chromium.chrome.browser.feedback;

import defpackage.i9;
import defpackage.yl1;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URL;
import org.chromium.chrome.browser.feedback.ConnectivityChecker;
import org.chromium.chrome.browser.feedback.d;

/* JADX INFO: compiled from: ConnectivityChecker.java */
/* JADX INFO: loaded from: classes.dex */
public final class b extends i9<Integer> {
    public final /* synthetic */ URL h;
    public final /* synthetic */ int i = 5000;
    public final /* synthetic */ ConnectivityChecker.a j;

    public b(URL url, d.c cVar) {
        this.h = url;
        this.j = cVar;
    }

    @Override // defpackage.i9
    public final Integer b() {
        int i = this.i;
        try {
            URL url = this.h;
            yl1 yl1Var = ConnectivityChecker.a;
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoInput(false);
            httpURLConnection.setDoOutput(false);
            httpURLConnection.setConnectTimeout(i);
            httpURLConnection.setReadTimeout(i);
            httpURLConnection.connect();
            return httpURLConnection.getResponseCode() == 204 ? 1 : 2;
        } catch (ProtocolException unused) {
            return 4;
        } catch (SocketTimeoutException unused2) {
            return 3;
        } catch (IOException unused3) {
            return 2;
        }
    }

    @Override // defpackage.i9
    public final void l(Integer num) {
        this.j.a(num.intValue());
    }
}
