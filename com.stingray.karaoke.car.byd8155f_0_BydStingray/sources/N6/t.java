package n6;

import S8.AbstractC0727y;
import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import java.util.LinkedList;
import n7.AbstractC2004s;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class t extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ u f20126m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public t(u uVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f20126m = uVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new t(this.f20126m, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        ((t) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
        return EnumC2303a.l;
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
            u uVar = this.f20126m;
            uVar.getClass();
            final long jCurrentTimeMillis = System.currentTimeMillis();
            float fA = uVar.a();
            boolean z6 = fA >= 20.0f && fA <= 100.0f;
            LinkedList linkedList = uVar.f20129e;
            if (z6) {
                linkedList.add(new m7.i(Float.valueOf(fA), Long.valueOf(jCurrentTimeMillis)));
            }
            AbstractC2004s.M(linkedList, new InterfaceC2782k() { // from class: n6.p
                @Override // z7.InterfaceC2782k
                public final Object b(Object obj2) {
                    m7.i iVar = (m7.i) obj2;
                    A7.m.f(iVar, "it");
                    return Boolean.valueOf(jCurrentTimeMillis - ((Number) iVar.f19259m).longValue() > 100);
                }
            });
            this.l = 1;
        } while (AbstractC0727y.i(5L, this) != enumC2303a);
        return enumC2303a;
    }
}
