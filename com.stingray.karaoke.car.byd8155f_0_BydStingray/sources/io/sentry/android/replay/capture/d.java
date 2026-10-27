package io.sentry.android.replay.capture;

import io.sentry.protocol.t;
import java.util.Date;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class d implements Runnable {
    public final /* synthetic */ int l = 0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ long f16361m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Date f16362n;
    public final /* synthetic */ t o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f16363p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ int f16364q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ int f16365r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ A7.o f16366s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ c f16367t;

    /* JADX WARN: Multi-variable type inference failed */
    public /* synthetic */ d(f fVar, long j3, Date date, t tVar, int i7, int i10, int i11, InterfaceC2782k interfaceC2782k) {
        this.f16367t = fVar;
        this.f16361m = j3;
        this.f16362n = date;
        this.o = tVar;
        this.f16363p = i7;
        this.f16364q = i10;
        this.f16365r = i11;
        this.f16366s = (A7.o) interfaceC2782k;
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [A7.o, z7.k] */
    /* JADX WARN: Type inference failed for: r0v8, types: [A7.o, z7.k] */
    @Override // java.lang.Runnable
    public final void run() {
        switch (this.l) {
            case 0:
                f fVar = (f) this.f16367t;
                A7.m.f(fVar, "this$0");
                Date date = this.f16362n;
                t tVar = this.o;
                A7.m.f(tVar, "$replayId");
                this.f16366s.b(c.h(fVar, this.f16361m, date, tVar, this.f16363p, this.f16364q, this.f16365r));
                break;
            default:
                o oVar = (o) this.f16367t;
                A7.m.f(oVar, "this$0");
                Date date2 = this.f16362n;
                t tVar2 = this.o;
                A7.m.f(tVar2, "$replayId");
                this.f16366s.b(c.h(oVar, this.f16361m, date2, tVar2, this.f16363p, this.f16364q, this.f16365r));
                break;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public /* synthetic */ d(o oVar, long j3, Date date, t tVar, int i7, int i10, int i11, InterfaceC2782k interfaceC2782k) {
        this.f16367t = oVar;
        this.f16361m = j3;
        this.f16362n = date;
        this.o = tVar;
        this.f16363p = i7;
        this.f16364q = i10;
        this.f16365r = i11;
        this.f16366s = (A7.o) interfaceC2782k;
    }
}
