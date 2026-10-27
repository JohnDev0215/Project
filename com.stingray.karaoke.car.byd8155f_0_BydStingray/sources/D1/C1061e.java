package d1;

import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import m7.q;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: d1.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1061e extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ boolean f13286m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ i f13287n;
    public final /* synthetic */ long o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1061e(boolean z6, i iVar, long j3, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f13286m = z6;
        this.f13287n = iVar;
        this.o = j3;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new C1061e(this.f13286m, this.f13287n, this.o, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C1061e) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            boolean z6 = this.f13286m;
            i iVar = this.f13287n;
            if (z6) {
                x0.d dVar = iVar.l;
                this.l = 2;
                if (dVar.a(this.o, 0L, this) == enumC2303a) {
                    return enumC2303a;
                }
            } else {
                x0.d dVar2 = iVar.l;
                this.l = 1;
                if (dVar2.a(0L, this.o, this) == enumC2303a) {
                    return enumC2303a;
                }
            }
        } else {
            if (i7 != 1 && i7 != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
        }
        return q.f19267a;
    }
}
