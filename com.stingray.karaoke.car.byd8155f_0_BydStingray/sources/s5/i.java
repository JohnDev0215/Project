package S5;

import T.C0735d;
import T.C0761q;
import z7.InterfaceC2772a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class i implements InterfaceC2785n {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ String f8010m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2772a f8011n;
    public final /* synthetic */ int o;

    public /* synthetic */ i(String str, InterfaceC2772a interfaceC2772a, int i7, int i10) {
        this.l = i10;
        this.f8010m = str;
        this.f8011n = interfaceC2772a;
        this.o = i7;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        C0761q c0761q = (C0761q) obj;
        Integer num = (Integer) obj2;
        switch (this.l) {
            case 0:
                num.intValue();
                io.sentry.config.a.u(this.f8010m, this.f8011n, c0761q, C0735d.Z(this.o | 1));
                break;
            case 1:
                num.getClass();
                l9.l.e(this.f8010m, this.f8011n, c0761q, C0735d.Z(this.o | 1));
                break;
            default:
                num.intValue();
                C7.a.o(this.f8010m, this.f8011n, c0761q, C0735d.Z(this.o | 1));
                break;
        }
        return m7.q.f19267a;
    }
}
