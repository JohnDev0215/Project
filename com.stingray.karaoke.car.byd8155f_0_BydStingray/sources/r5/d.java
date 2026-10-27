package R5;

import A7.m;
import C0.r;
import T.X;
import a1.j;
import h6.n;
import m7.q;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class d implements InterfaceC2782k {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ X f7518m;

    public /* synthetic */ d(X x8, int i7) {
        this.l = i7;
        this.f7518m = x8;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.l) {
            case 0:
                String str = (String) obj;
                m.f(str, "it");
                this.f7518m.setValue(str);
                break;
            case 1:
                r rVar = (r) obj;
                m.f(rVar, "coordinates");
                this.f7518m.setValue(new j(rVar.L()));
                break;
            case 2:
                r rVar2 = (r) obj;
                m.f(rVar2, "coordinates");
                this.f7518m.setValue(new j(rVar2.L()));
                break;
            case 3:
                Boolean bool = (Boolean) obj;
                boolean zBooleanValue = bool.booleanValue();
                this.f7518m.setValue(bool);
                n.l = zBooleanValue;
                break;
            default:
                Boolean bool2 = (Boolean) obj;
                boolean zBooleanValue2 = bool2.booleanValue();
                this.f7518m.setValue(bool2);
                n.f15593k = zBooleanValue2;
                break;
        }
        return q.f19267a;
    }
}
