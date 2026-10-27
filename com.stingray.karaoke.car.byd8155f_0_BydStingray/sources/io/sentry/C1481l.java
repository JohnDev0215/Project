package io.sentry;

import java.io.File;
import java.io.FilenameFilter;

/* JADX INFO: renamed from: io.sentry.l, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C1481l implements FilenameFilter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f16606a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f16607b;

    public /* synthetic */ C1481l(Object obj, int i7) {
        this.f16606a = i7;
        this.f16607b = obj;
    }

    @Override // java.io.FilenameFilter
    public final boolean accept(File file, String str) {
        switch (this.f16606a) {
            case 0:
                return ((AbstractC1487n) this.f16607b).a(str);
            default:
                io.sentry.android.replay.i iVar = (io.sentry.android.replay.i) this.f16607b;
                A7.m.e(str, "name");
                if (Q8.o.s0(str, ".jpg", false)) {
                    File file2 = new File(file, str);
                    String name = file2.getName();
                    A7.m.e(name, "getName(...)");
                    Long lB0 = Q8.o.B0(Q8.h.d1(name, name));
                    if (lB0 != null) {
                        iVar.f16409s.add(new io.sentry.android.replay.j(file2, lB0.longValue(), null));
                    }
                }
                return false;
        }
    }
}
