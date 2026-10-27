package org.chromium.android_webview;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class AwHttpAuthHandler {
    public long a;
    public final boolean b;

    public AwHttpAuthHandler(long j, boolean z) {
        this.a = j;
        this.b = z;
    }

    public static AwHttpAuthHandler create(long j, boolean z) {
        return new AwHttpAuthHandler(j, z);
    }

    public void handlerDestroyed() {
        this.a = 0L;
    }
}
