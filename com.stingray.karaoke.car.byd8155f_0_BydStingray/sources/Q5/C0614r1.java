package Q5;

import z7.InterfaceC2782k;

/* JADX INFO: renamed from: Q5.r1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C0614r1 implements InterfaceC2782k {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2782k f7044m;

    public /* synthetic */ C0614r1(int i7, InterfaceC2782k interfaceC2782k) {
        this.l = i7;
        this.f7044m = interfaceC2782k;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.l) {
            case 0:
                String str = (String) obj;
                A7.m.f(str, "new");
                if (!Q8.h.D0(str, '\n')) {
                    this.f7044m.b(str);
                }
                break;
            case 1:
                Boolean bool = (Boolean) obj;
                bool.booleanValue();
                this.f7044m.b(bool);
                break;
            default:
                String str2 = (String) obj;
                A7.m.f(str2, "it");
                this.f7044m.b(str2);
                break;
        }
        return m7.q.f19267a;
    }
}
