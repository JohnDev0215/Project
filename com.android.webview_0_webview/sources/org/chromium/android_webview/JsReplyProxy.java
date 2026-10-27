package org.chromium.android_webview;

import defpackage.AbstractC1675vp;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class JsReplyProxy extends AbstractC1675vp {
    public long b;

    public JsReplyProxy(long j) {
        this.b = j;
    }

    public static JsReplyProxy create(long j) {
        return new JsReplyProxy(j);
    }

    private void onDestroy() {
        this.b = 0L;
    }
}
