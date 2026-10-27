package m1;

import g1.q;
import java.util.Iterator;
import l1.C1731b;

/* JADX INFO: renamed from: m1.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1793a extends l1.g {

    /* JADX INFO: renamed from: n0, reason: collision with root package name */
    public final /* synthetic */ int f18891n0;

    /* JADX INFO: renamed from: o0, reason: collision with root package name */
    public float f18892o0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C1793a(q qVar, int i7, int i10) {
        super(qVar, i7);
        this.f18891n0 = i10;
    }

    @Override // l1.g, l1.C1731b, l1.h
    public final void a() {
        switch (this.f18891n0) {
            case 0:
                Iterator it = this.f18433m0.iterator();
                while (it.hasNext()) {
                    C1731b c1731bB = this.f18431k0.b(it.next());
                    c1731bB.h();
                    Object obj = this.f18371N;
                    if (obj != null) {
                        c1731bB.p(obj);
                    } else {
                        Object obj2 = this.f18372O;
                        if (obj2 != null) {
                            c1731bB.f18401j0 = 6;
                            c1731bB.f18372O = obj2;
                        } else {
                            c1731bB.p(0);
                        }
                    }
                    Object obj3 = this.f18373P;
                    if (obj3 != null) {
                        c1731bB.f18401j0 = 7;
                        c1731bB.f18373P = obj3;
                    } else {
                        Object obj4 = this.f18374Q;
                        if (obj4 != null) {
                            c1731bB.j(obj4);
                        } else {
                            c1731bB.j(0);
                        }
                    }
                    float f10 = this.f18892o0;
                    if (f10 != 0.5f) {
                        c1731bB.f18396h = f10;
                    }
                }
                break;
            default:
                Iterator it2 = this.f18433m0.iterator();
                while (it2.hasNext()) {
                    C1731b c1731bB2 = this.f18431k0.b(it2.next());
                    c1731bB2.i();
                    Object obj5 = this.f18375R;
                    if (obj5 != null) {
                        c1731bB2.q(obj5);
                    } else {
                        Object obj6 = this.f18376S;
                        if (obj6 != null) {
                            c1731bB2.f18401j0 = 10;
                            c1731bB2.f18376S = obj6;
                        } else {
                            c1731bB2.q(0);
                        }
                    }
                    Object obj7 = this.f18378U;
                    if (obj7 != null) {
                        c1731bB2.f18401j0 = 12;
                        c1731bB2.f18378U = obj7;
                    } else {
                        Object obj8 = this.f18379V;
                        if (obj8 != null) {
                            c1731bB2.f(obj8);
                        } else {
                            c1731bB2.f(0);
                        }
                    }
                    float f11 = this.f18892o0;
                    if (f11 != 0.5f) {
                        c1731bB2.f18398i = f11;
                    }
                }
                break;
        }
    }
}
