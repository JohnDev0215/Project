package defpackage;

import android.app.job.JobParameters;
import android.content.Context;
import org.chromium.android_webview.services.AwVariationsSeedFetcher;

/* JADX INFO: renamed from: is, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0961is extends Tu {
    public JobParameters h;
    public final /* synthetic */ AwVariationsSeedFetcher i;

    public C0961is(AwVariationsSeedFetcher awVariationsSeedFetcher, JobParameters jobParameters) {
        this.i = awVariationsSeedFetcher;
        this.h = jobParameters;
    }

    @Override // defpackage.Su
    public Object c() {
        C1629uy c1629uy;
        long jA = AwVariationsSeedFetcher.a();
        try {
            AbstractC0626cr.h();
            AbstractC0626cr.b("Downloading new seed");
            synchronized (C1629uy.a) {
                if (C1629uy.b == null) {
                    C1629uy.b = new C1629uy();
                }
                c1629uy = C1629uy.b;
            }
            boolean z = true;
            C1519sy c1519syA = c1629uy.a(1, null, String.valueOf(83), null);
            p(c1519syA.a, jA, System.currentTimeMillis());
            if (!j()) {
                if (c1519syA.b != null) {
                    C1239ns c1239ns = C1239ns.e;
                    c1239ns.b.post(new RunnableC1129ls(c1239ns, c1519syA.b, new Runnable(this) { // from class: hs
                        public final C0961is s;

                        {
                            this.s = this;
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            C0961is c0961is = this.s;
                            c0961is.i.jobFinished(c0961is.h, false);
                        }
                    }));
                    z = false;
                }
                if (z) {
                }
                return null;
            }
            return null;
        } finally {
            this.i.jobFinished(this.h, false);
        }
    }

    public final void p(int i, long j, long j2) {
        Context context = Is.a;
        C0570br c0570brA = C0570br.a(context);
        c0570brA.a.putInt("seed_fetch_result", i);
        c0570brA.a.putLong("seed_fetch_time", j2 - j);
        if (c0570brA.a.containsKey("last_enqueue_time")) {
            c0570brA.a.putLong("job_queue_time", j - c0570brA.a.getLong("last_enqueue_time"));
        }
        if (c0570brA.a.containsKey("last_job_start_time")) {
            c0570brA.a.putLong("job_interval", j - c0570brA.a.getLong("last_job_start_time"));
        }
        c0570brA.a.remove("last_enqueue_time");
        c0570brA.a.putLong("last_job_start_time", j);
        if (c0570brA.b(context)) {
            return;
        }
        Ts.a("AwVariationsSeedFet-", "Failed to write variations SharedPreferences to disk", new Object[0]);
    }
}
