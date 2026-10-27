package P5;

import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import y0.C2738p;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class m extends s7.h implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f5374m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ z f5375n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public m(z zVar, InterfaceC2189c interfaceC2189c) {
        super(interfaceC2189c);
        this.f5375n = zVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        m mVar = new m(this.f5375n, interfaceC2189c);
        mVar.f5374m = obj;
        return mVar;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((m) create((y0.z) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    /* JADX WARN: Type inference failed for: r8v5, types: [java.lang.Object, java.util.List] */
    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        ?? r82 = ((y0.z) this.f5374m).f24110p.f24042D.f24065a;
        int size = r82.size();
        for (int i7 = 0; i7 < size; i7++) {
            Object obj2 = r82.get(i7);
            if (C2738p.a(((y0.q) obj2).f24077a, this.f5375n.f5410a)) {
                return obj2;
            }
        }
        return null;
    }
}
