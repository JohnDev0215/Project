package d8;

import Q7.AbstractC0669w;
import Q7.Q;
import c8.C1036a;
import java.util.ArrayList;
import n7.AbstractC2000o;
import v8.AbstractC2564d;
import z7.InterfaceC2772a;

/* JADX INFO: renamed from: d8.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1120g extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f13577m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C1122i f13578n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C1120g(C1122i c1122i, int i7) {
        super(0);
        this.f13577m = i7;
        this.f13578n = c1122i;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.f13577m) {
            case 0:
                return AbstractC0669w.c(this.f13578n);
            case 1:
                C1122i c1122i = this.f13578n;
                ArrayList<W7.B> arrayListT = c1122i.f13586s.t();
                ArrayList arrayList = new ArrayList(AbstractC2000o.J(arrayListT, 10));
                for (W7.B b10 : arrayListT) {
                    Q qB = ((c8.e) c1122i.f13588u.f4400n).b(b10);
                    if (qB == null) {
                        throw new AssertionError("Parameter " + b10 + " surely belongs to class " + c1122i.f13586s + ", so it must be resolved");
                    }
                    arrayList.add(qB);
                }
                return arrayList;
            default:
                C1122i c1122i2 = this.f13578n;
                if (AbstractC2564d.f(c1122i2) == null) {
                    return null;
                }
                ((C1036a) c1122i2.f13585r.f4399m).f12429w.getClass();
                return null;
        }
    }
}
