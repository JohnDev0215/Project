package Q5;

import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: Q5.y1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0642y1 extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ E.D f7147m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ b6.g f7148n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0642y1(E.D d, b6.g gVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f7147m = d;
        this.f7148n = gVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new C0642y1(this.f7147m, this.f7148n, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C0642y1) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            b6.g gVar = this.f7148n;
            int i10 = gVar.f12242a;
            int i11 = gVar.f12243b;
            this.l = 1;
            if (this.f7147m.j(i10, i11, this) == enumC2303a) {
                return enumC2303a;
            }
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
        }
        return m7.q.f19267a;
    }
}
