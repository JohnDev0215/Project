package T7;

import Q7.AbstractC0669w;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import n7.AbstractC1985B;
import n7.AbstractC1998m;
import n7.AbstractC2000o;
import o8.C2058c;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class v extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f8910m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ w f8911n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ v(w wVar, int i7) {
        super(0);
        this.f8910m = i7;
        this.f8911n = wVar;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.f8910m) {
            case 0:
                w wVar = this.f8911n;
                z zVar = wVar.o;
                zVar.b1();
                return Boolean.valueOf(AbstractC0669w.h((C0793l) zVar.f8928w.getValue(), wVar.f8913p));
            case 1:
                w wVar2 = this.f8911n;
                z zVar2 = wVar2.o;
                zVar2.b1();
                return AbstractC0669w.i((C0793l) zVar2.f8928w.getValue(), wVar2.f8913p);
            default:
                w wVar3 = this.f8911n;
                E8.i iVar = wVar3.f8915r;
                H7.w[] wVarArr = w.f8912t;
                if (((Boolean) C7.a.N(iVar, wVarArr[1])).booleanValue()) {
                    return y8.m.f24213b;
                }
                List list = (List) C7.a.N(wVar3.f8914q, wVarArr[0]);
                ArrayList arrayList = new ArrayList(AbstractC2000o.J(list, 10));
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    arrayList.add(((Q7.D) it.next()).z0());
                }
                z zVar3 = wVar3.o;
                C2058c c2058c = wVar3.f8913p;
                return AbstractC1985B.d("package view scope for " + c2058c + " in " + zVar3.getName(), AbstractC1998m.o0(arrayList, new L(zVar3, c2058c)));
        }
    }
}
