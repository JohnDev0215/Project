package Q5;

import T.C0732b0;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class O2 implements InterfaceC2772a {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f6471m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2782k f6472n;
    public final /* synthetic */ C0732b0 o;

    public /* synthetic */ O2(int i7, InterfaceC2782k interfaceC2782k, C0732b0 c0732b0, int i10) {
        this.l = i10;
        this.f6471m = i7;
        this.f6472n = interfaceC2782k;
        this.o = c0732b0;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.l) {
            case 0:
                C0732b0 c0732b0 = this.o;
                int i7 = c0732b0.i();
                int i10 = this.f6471m;
                if (i7 != i10) {
                    c0732b0.j(i10);
                    this.f6472n.b(Integer.valueOf(c0732b0.i()));
                }
                break;
            default:
                C0732b0 c0732b02 = this.o;
                c0732b02.j(this.f6471m);
                this.f6472n.b(Integer.valueOf(c0732b02.i()));
                break;
        }
        return m7.q.f19267a;
    }
}
