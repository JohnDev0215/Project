package defpackage;

import org.chromium.components.metrics.AndroidMetricsLogUploader;

/* JADX INFO: renamed from: uQ, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1603uQ {
    public static AbstractC1603uQ a;
    public static final Object b = new Object();

    public AbstractC1603uQ() {
        AndroidMetricsLogUploader.a = new C1548tQ(this);
    }

    public static AbstractC1603uQ a() {
        AbstractC1603uQ abstractC1603uQ;
        synchronized (b) {
            if (a == null) {
                a = new C1658vQ();
            }
            abstractC1603uQ = a;
        }
        return abstractC1603uQ;
    }
}
