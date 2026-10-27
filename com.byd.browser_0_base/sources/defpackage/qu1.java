package defpackage;

import android.os.SystemClock;

/* JADX INFO: compiled from: PageInfoDiscoverabilityMetrics.java */
/* JADX INFO: loaded from: classes3.dex */
public final class qu1 {
    public Long a;
    public Long b;
    public Long c;
    public Long d;

    public final void a(int i) {
        oj2.U(i, 7, "WebsiteSettings.Discoverability.Action");
        if (i == 0) {
            this.a = Long.valueOf(SystemClock.elapsedRealtime());
            return;
        }
        if (i == 1) {
            Long lValueOf = Long.valueOf(SystemClock.elapsedRealtime());
            this.b = lValueOf;
            oj2.b0(lValueOf.longValue() - this.a.longValue(), "WebsiteSettings.Discoverability.TimeToOpen");
            this.a = null;
            return;
        }
        if (i == 2) {
            if (this.b != null) {
                oj2.Y(SystemClock.elapsedRealtime() - this.b.longValue(), "WebsiteSettings.Discoverability.TimeToClickHighlight");
            }
            this.b = null;
        } else {
            if (i == 4) {
                this.c = Long.valueOf(SystemClock.elapsedRealtime());
                return;
            }
            if (i == 5) {
                Long lValueOf2 = Long.valueOf(SystemClock.elapsedRealtime());
                this.d = lValueOf2;
                oj2.b0(lValueOf2.longValue() - this.c.longValue(), "WebsiteSettings.Discoverability.TimeToOpenFromStoreIcon");
                this.c = null;
                return;
            }
            if (i != 6) {
                return;
            }
            if (this.d != null) {
                oj2.Y(SystemClock.elapsedRealtime() - this.d.longValue(), "WebsiteSettings.Discoverability.TimeToClickHighlightStoreInfo");
            }
            this.d = null;
        }
    }
}
