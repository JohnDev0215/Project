package N0;

import java.util.ArrayList;
import n7.AbstractC1999n;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class p extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f4397m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ q f4398n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ p(q qVar, int i7) {
        super(0);
        this.f4397m = i7;
        this.f4398n = qVar;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        Object obj;
        Object obj2;
        switch (this.f4397m) {
            case 0:
                ArrayList arrayList = (ArrayList) this.f4398n.l;
                if (arrayList.isEmpty()) {
                    obj = null;
                } else {
                    Object obj3 = arrayList.get(0);
                    float fB = ((s) obj3).f4407a.f9296t.b();
                    int iC = AbstractC1999n.C(arrayList);
                    int i7 = 1;
                    if (1 <= iC) {
                        while (true) {
                            Object obj4 = arrayList.get(i7);
                            float fB2 = ((s) obj4).f4407a.f9296t.b();
                            if (Float.compare(fB, fB2) < 0) {
                                obj3 = obj4;
                                fB = fB2;
                            }
                            if (i7 != iC) {
                                i7++;
                            }
                        }
                    }
                    obj = obj3;
                }
                s sVar = (s) obj;
                return Float.valueOf(sVar != null ? sVar.f4407a.f9296t.b() : 0.0f);
            default:
                ArrayList arrayList2 = (ArrayList) this.f4398n.l;
                if (arrayList2.isEmpty()) {
                    obj2 = null;
                } else {
                    Object obj5 = arrayList2.get(0);
                    float fB3 = ((s) obj5).f4407a.b();
                    int iC2 = AbstractC1999n.C(arrayList2);
                    int i10 = 1;
                    if (1 <= iC2) {
                        while (true) {
                            Object obj6 = arrayList2.get(i10);
                            float fB4 = ((s) obj6).f4407a.b();
                            if (Float.compare(fB3, fB4) < 0) {
                                obj5 = obj6;
                                fB3 = fB4;
                            }
                            if (i10 != iC2) {
                                i10++;
                            }
                        }
                    }
                    obj2 = obj5;
                }
                s sVar2 = (s) obj2;
                return Float.valueOf(sVar2 != null ? sVar2.f4407a.b() : 0.0f);
        }
    }
}
