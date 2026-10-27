package io.sentry.instrumentation.file;

import e2.F;
import e2.U;
import h2.l;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class f implements a, l {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f16566m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f16567n;

    public /* synthetic */ f(int i7, int i10, Object obj) {
        this.l = i10;
        this.f16567n = obj;
        this.f16566m = i7;
    }

    @Override // h2.l
    public void b(Object obj) {
        U u4 = (U) obj;
        switch (this.l) {
            case 1:
                u4.N(((n2.U) this.f16567n).f19586a, this.f16566m);
                break;
            default:
                u4.L(this.f16566m, (F) this.f16567n);
                break;
        }
    }

    @Override // io.sentry.instrumentation.file.a
    public Object call() throws IOException {
        ((g) this.f16567n).l.write(this.f16566m);
        return 1;
    }
}
