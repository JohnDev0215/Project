package S5;

import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class e implements InterfaceC2772a {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ String f7999m;

    public /* synthetic */ e(String str, int i7) {
        this.l = i7;
        this.f7999m = str;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        m7.q qVar = m7.q.f19267a;
        String str = this.f7999m;
        switch (this.l) {
            case 0:
                io.sentry.hints.i iVar = k6.o.f18284a;
                io.sentry.hints.i.i(str, false);
                break;
            case 1:
                io.sentry.hints.i iVar2 = k6.o.f18284a;
                io.sentry.hints.i.i(str, false);
                break;
            case 2:
                io.sentry.hints.i iVar3 = k6.o.f18284a;
                io.sentry.hints.i.i(str, true);
                break;
            case 3:
                io.sentry.hints.i iVar4 = k6.o.f18284a;
                io.sentry.hints.i.i(str, true);
                break;
            default:
                io.sentry.hints.i iVar5 = k6.o.f18284a;
                io.sentry.hints.i.i(str, true);
                break;
        }
        return qVar;
    }
}
