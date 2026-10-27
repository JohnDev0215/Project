package S6;

import N0.q;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class d implements h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f8071a;

    public d(K6.k kVar, h hVar) {
        ArrayList arrayList = new ArrayList(3);
        this.f8071a = arrayList;
        arrayList.add(kVar);
        arrayList.add(hVar);
    }

    @Override // S6.h
    public final Object a(q qVar, c cVar) {
        ArrayList arrayList = this.f8071a;
        int size = arrayList.size();
        Object[] objArr = new Object[size];
        for (int i7 = 0; i7 < size; i7++) {
            objArr[i7] = ((h) arrayList.get(i7)).a(qVar, cVar);
        }
        return objArr;
    }
}
