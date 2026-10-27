package defpackage;

import defpackage.ms2;
import org.chromium.chrome.browser.metrics.UmaUtils;
import org.chromium.chrome.browser.metrics.a;

/* JADX INFO: compiled from: ActivityTabStartupMetricsTracker.java */
/* JADX INFO: loaded from: classes2.dex */
public final class e3 implements ms2.a {
    public final /* synthetic */ a a;

    public e3(a aVar) {
        this.a = aVar;
    }

    @Override // ms2.a
    public final void a() {
        if (UmaUtils.c() || UmaUtils.b()) {
            return;
        }
        this.a.j = true;
    }

    @Override // ms2.a
    public final void b(long j) {
        oj2.O("Startup.Android.Cold.FirstPaintOccurredPreForeground", false);
        a aVar = this.a;
        if (!aVar.g) {
            aVar.g = true;
            oj2.Y(j, "Startup.Android.Cold.TimeToFirstVisibleContent");
        }
        if (aVar.h) {
            return;
        }
        aVar.h = true;
        oj2.Y(j, "Startup.Android.Cold.TimeToVisibleContent");
    }
}
