package p6;

import Q8.h;
import S8.AbstractC0727y;
import S8.InterfaceC0725w;
import T.C0732b0;
import h8.AbstractC1386c;
import java.util.ArrayList;
import m7.q;
import n7.AbstractC1998m;
import n7.AbstractC1999n;
import n7.AbstractC2000o;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import s7.i;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: p6.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2110a extends i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f20908m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C0732b0 f20909n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2110a(int i7, C0732b0 c0732b0, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f20908m = i7;
        this.f20909n = c0732b0;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new C2110a(this.f20908m, this.f20909n, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C2110a) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 != 0 && i7 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        AbstractC1386c.u(obj);
        do {
            C0732b0 c0732b0 = this.f20909n;
            int i10 = c0732b0.i();
            int i11 = this.f20908m;
            int i12 = 0;
            if (i10 == i11) {
                if (i11 <= 0) {
                    c0732b0.j(0);
                }
                return q.f19267a;
            }
            String strQ0 = h.Q0(String.valueOf(i11).length(), String.valueOf(c0732b0.i()));
            ArrayList arrayList = new ArrayList(strQ0.length());
            for (int i13 = 0; i13 < strQ0.length(); i13++) {
                arrayList.add(new Integer(Integer.parseInt(String.valueOf(strQ0.charAt(i13)))));
            }
            String strValueOf = String.valueOf(i11);
            ArrayList arrayList2 = new ArrayList(strValueOf.length());
            for (int i14 = 0; i14 < strValueOf.length(); i14++) {
                arrayList2.add(new Integer(Integer.parseInt(String.valueOf(strValueOf.charAt(i14)))));
            }
            ArrayList arrayListF0 = AbstractC1998m.F0(arrayList, arrayList2);
            ArrayList arrayList3 = new ArrayList(AbstractC2000o.J(arrayListF0, 10));
            boolean z6 = false;
            for (Object obj2 : arrayListF0) {
                int i15 = i12 + 1;
                if (i12 < 0) {
                    AbstractC1999n.I();
                    throw null;
                }
                m7.i iVar = (m7.i) obj2;
                int iIntValue = ((Number) iVar.l).intValue();
                int iIntValue2 = ((Number) iVar.f19259m).intValue();
                if (i12 == arrayList.size() - 1) {
                    iIntValue = z6 ? (iIntValue + 1) % 10 : iIntValue2;
                } else if (iIntValue != iIntValue2) {
                    iIntValue = iIntValue < iIntValue2 ? iIntValue + 1 : (iIntValue + 1) % 10;
                    z6 = true;
                }
                arrayList3.add(new Integer(iIntValue));
                i12 = i15;
            }
            c0732b0.j(Integer.parseInt(AbstractC1998m.f0(arrayList3, "", null, null, null, 62)));
            this.l = 1;
        } while (AbstractC0727y.i(66L, this) != enumC2303a);
        return enumC2303a;
    }
}
