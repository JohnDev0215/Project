package defpackage;

import J.N;
import java.util.List;
import org.chromium.chrome.browser.usage_stats.UsageStatsBridge;

/* JADX INFO: compiled from: EventTracker.java */
/* JADX INFO: loaded from: classes2.dex */
public final class gi0 {
    public final UsageStatsBridge a;
    public final s62<List<xs3>> b;

    public gi0(UsageStatsBridge usageStatsBridge) {
        this.a = usageStatsBridge;
        s62<List<xs3>> s62Var = new s62<>();
        this.b = s62Var;
        s62Var.a(new xo(9));
        N.M6Rdk6FF(usageStatsBridge.b, usageStatsBridge, new dr(28, this));
    }
}
