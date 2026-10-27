package defpackage;

import org.chromium.components.metrics.AndroidMetricsLogUploader;

/* JADX INFO: renamed from: Jq, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0178Jq {
    public static AbstractC0178Jq a;
    public static final Object b = new Object();

    public AbstractC0178Jq() {
        AndroidMetricsLogUploader.a = new C0160Iq(this);
    }

    public static AbstractC0178Jq a() {
        AbstractC0178Jq abstractC0178Jq;
        synchronized (b) {
            if (a == null) {
                a = new C0196Kq();
            }
            abstractC0178Jq = a;
        }
        return abstractC0178Jq;
    }
}
