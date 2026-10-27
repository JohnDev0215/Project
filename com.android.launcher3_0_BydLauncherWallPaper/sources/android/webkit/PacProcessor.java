package android.webkit;

import android.annotation.SystemApi;

/* JADX INFO: loaded from: classes3.dex */
@SystemApi
public interface PacProcessor {
    String findProxyForUrl(String str);

    boolean setProxyScript(String str);

    static PacProcessor getInstance() {
        return WebViewFactory.getProvider().getPacProcessor();
    }
}
