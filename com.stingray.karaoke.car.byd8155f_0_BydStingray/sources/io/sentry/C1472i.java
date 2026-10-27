package io.sentry;

import h8.AbstractC1387d;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: renamed from: io.sentry.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1472i implements InterfaceC1511t {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Map f16552m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final F1 f16553n;

    public C1472i(F1 f12, int i7) {
        this.l = i7;
        switch (i7) {
            case 1:
                this.f16552m = Collections.synchronizedMap(new WeakHashMap());
                h6.e.q(f12, "options are required");
                this.f16553n = f12;
                break;
            default:
                this.f16552m = Collections.synchronizedMap(new HashMap());
                this.f16553n = f12;
                break;
        }
    }

    @Override // io.sentry.InterfaceC1511t
    public final C1480k1 i(C1480k1 c1480k1, C1523x c1523x) {
        io.sentry.protocol.s sVarB;
        String str;
        Long l;
        switch (this.l) {
            case 0:
                if (e2.class.isInstance(AbstractC1387d.g(c1523x)) && (sVarB = c1480k1.b()) != null && (str = sVarB.l) != null && (l = sVarB.o) != null) {
                    Map map = this.f16552m;
                    Long l10 = (Long) map.get(str);
                    if (l10 == null || l10.equals(l)) {
                        map.put(str, l);
                    } else {
                        this.f16553n.getLogger().k(EnumC1495p1.INFO, "Event %s has been dropped due to multi-threaded deduplication", c1480k1.l);
                        c1523x.c(io.sentry.hints.e.MULTITHREADED_DEDUPLICATION, "sentry:eventDropReason");
                    }
                }
                break;
            default:
                F1 f12 = this.f16553n;
                if (f12.isEnableDeduplication()) {
                    Throwable th = c1480k1.f15985u;
                    if (th instanceof io.sentry.exception.a) {
                        th = ((io.sentry.exception.a) th).f16529m;
                    }
                    if (th != null) {
                        Map map2 = this.f16552m;
                        if (!map2.containsKey(th)) {
                            ArrayList arrayList = new ArrayList();
                            for (Throwable cause = th; cause.getCause() != null; cause = cause.getCause()) {
                                arrayList.add(cause.getCause());
                            }
                            Iterator it = arrayList.iterator();
                            while (it.hasNext()) {
                                if (map2.containsKey(it.next())) {
                                }
                            }
                            map2.put(th, null);
                        }
                        f12.getLogger().k(EnumC1495p1.DEBUG, "Duplicate Exception detected. Event %s will be discarded.", c1480k1.l);
                    }
                } else {
                    f12.getLogger().k(EnumC1495p1.DEBUG, "Event deduplication is disabled.", new Object[0]);
                }
                break;
        }
        return c1480k1;
    }
}
