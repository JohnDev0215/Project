package defpackage;

import J.N;
import java.util.List;
import org.chromium.chrome.browser.usage_stats.UsageStatsBridge;

/* JADX INFO: compiled from: SuspensionTracker.java */
/* JADX INFO: loaded from: classes2.dex */
public final class zw2 {
    public final UsageStatsBridge a;
    public final hp1 b;
    public final s62<List<String>> c = new s62<>();
    public s62<Void> d;

    public zw2(UsageStatsBridge usageStatsBridge, hp1 hp1Var) {
        this.a = usageStatsBridge;
        this.b = hp1Var;
        int i = 8;
        N.MggFWmhE(usageStatsBridge.b, usageStatsBridge, new t02(new se3(i, this), i));
        this.d = s62.c(null);
    }
}
