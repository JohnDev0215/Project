package defpackage;

import J.N;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.usage_stats.UsageStatsBridge;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class ei0 implements Callback {
    public final /* synthetic */ gi0 e;
    public final /* synthetic */ long f;
    public final /* synthetic */ long g;
    public final /* synthetic */ s62 h;

    public /* synthetic */ ei0(gi0 gi0Var, long j, long j2, s62 s62Var) {
        this.e = gi0Var;
        this.f = j;
        this.g = j2;
        this.h = s62Var;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Object obj) {
        final long j = this.f;
        final long j2 = this.g;
        final s62 s62Var = this.h;
        final List list = (List) obj;
        gi0 gi0Var = this.e;
        gi0Var.getClass();
        Callback callback = new Callback() { // from class: fi0
            @Override // org.chromium.base.Callback
            public final void onResult(Object obj2) {
                List list2;
                boolean zBooleanValue = ((Boolean) obj2).booleanValue();
                s62 s62Var2 = s62Var;
                if (!zBooleanValue) {
                    s62Var2.e(null);
                    return;
                }
                int size = 0;
                int size2 = 0;
                while (true) {
                    list2 = list;
                    if (size2 >= list2.size()) {
                        size2 = list2.size();
                        break;
                    }
                    if (j <= ((xs3) list2.get(size2)).a) {
                        break;
                    } else {
                        size2++;
                    }
                }
                while (true) {
                    if (size >= list2.size()) {
                        size = list2.size();
                        break;
                    }
                    if (j2 <= ((xs3) list2.get(size)).a) {
                        break;
                    } else {
                        size++;
                    }
                }
                list2.subList(size2, size).clear();
                s62Var2.b(null);
            }
        };
        UsageStatsBridge usageStatsBridge = gi0Var.a;
        usageStatsBridge.getClass();
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        N.Mr1dopkU(usageStatsBridge.b, usageStatsBridge, timeUnit.toSeconds(j), timeUnit.toSeconds(j2), callback);
    }
}
