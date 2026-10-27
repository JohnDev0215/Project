package defpackage;

import J.N;
import java.util.Map;
import org.chromium.chrome.browser.usage_stats.UsageStatsBridge;

/* JADX INFO: compiled from: TokenTracker.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ob3 {
    public final s62<Map<String, String>> a;
    public final UsageStatsBridge b;

    public ob3(UsageStatsBridge usageStatsBridge) {
        this.b = usageStatsBridge;
        s62<Map<String, String>> s62Var = new s62<>();
        this.a = s62Var;
        N.MiNnjkrf(usageStatsBridge.b, usageStatsBridge, new x10(17, this));
        s62Var.a(new fm3(10));
    }
}
