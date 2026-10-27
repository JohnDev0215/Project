package org.chromium.content.browser;

import J.N;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RenderWidgetHostViewImpl {
    public long a;
    public Throwable b;

    public RenderWidgetHostViewImpl(long j) {
        this.a = j;
    }

    private void clearNativePtr() {
        this.a = 0L;
        this.b = new RuntimeException("clearNativePtr");
    }

    public static RenderWidgetHostViewImpl create(long j) {
        return new RenderWidgetHostViewImpl(j);
    }

    public void a() {
        if (this.a == 0) {
            return;
        }
        N.MQWja$xA(this.a, this);
    }
}
