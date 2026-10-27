package W8;

import S8.InterfaceC0725w;
import V8.AbstractC0828q;
import V8.InterfaceC0818g;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: W8.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0845e extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f9912m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ InterfaceC0818g f9913n;
    public final /* synthetic */ g o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0845e(InterfaceC0818g interfaceC0818g, g gVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f9913n = interfaceC0818g;
        this.o = gVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        C0845e c0845e = new C0845e(this.f9913n, this.o, interfaceC2189c);
        c0845e.f9912m = obj;
        return c0845e;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C0845e) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        m7.q qVar = m7.q.f19267a;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            U8.z zVarG = this.o.g((InterfaceC0725w) this.f9912m);
            this.l = 1;
            Object objI = AbstractC0828q.i(this.f9913n, zVarG, true, this);
            if (objI != enumC2303a) {
                objI = qVar;
            }
            if (objI == enumC2303a) {
                return enumC2303a;
            }
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
        }
        return qVar;
    }
}
