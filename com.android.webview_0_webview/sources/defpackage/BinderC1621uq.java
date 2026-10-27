package defpackage;

import android.os.Bundle;
import java.util.concurrent.TimeUnit;

/* JADX INFO: renamed from: uq, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class BinderC1621uq extends AbstractBinderC0465Zq {
    public BinderC1621uq(C1731wq c1731wq, AbstractC1456rq abstractC1456rq) {
    }

    @Override // defpackage.InterfaceC0514ar
    public void j(Bundle bundle) {
        if (bundle.containsKey("job_interval")) {
            It.b("Variations.WebViewDownloadJobInterval", (int) TimeUnit.MILLISECONDS.toMinutes(bundle.getLong("job_interval")), 1, (int) TimeUnit.DAYS.toMinutes(30L), 50);
        }
        if (bundle.containsKey("job_queue_time")) {
            It.b("Variations.WebViewDownloadJobQueueTime", (int) TimeUnit.MILLISECONDS.toMinutes(bundle.getLong("job_queue_time")), 1, (int) TimeUnit.DAYS.toMinutes(30L), 50);
        }
        if (bundle.containsKey("seed_fetch_result")) {
            Lt.a.a("Variations.WebViewDownloadJobFetchResult", bundle.getInt("seed_fetch_result"));
        }
        if (bundle.containsKey("seed_fetch_time")) {
            It.c("Variations.WebViewDownloadJobFetchTime2", bundle.getLong("seed_fetch_time"), 100L, TimeUnit.MINUTES.toMillis(20L), 50);
        }
    }
}
