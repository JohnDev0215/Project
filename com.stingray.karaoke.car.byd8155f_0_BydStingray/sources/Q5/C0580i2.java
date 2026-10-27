package Q5;

import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import java.util.List;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: Q5.i2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0580i2 extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ P5.p f6883m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f6884n;
    public final /* synthetic */ T.X o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0580i2(int i7, P5.p pVar, T.X x8, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f6883m = pVar;
        this.f6884n = i7;
        this.o = x8;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        T.X x8 = this.o;
        return new C0580i2(this.f6884n, this.f6883m, x8, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C0580i2) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        int i7;
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i10 = this.l;
        if (i10 == 0) {
            AbstractC1386c.u(obj);
            P5.p pVar = this.f6883m;
            if (pVar.f() == null && (i7 = this.f6884n) >= 0) {
                List list = (List) this.o.getValue();
                if (i7 < (list != null ? list.size() : 0)) {
                    this.l = 1;
                    if (pVar.u(i7, true, this) == enumC2303a) {
                        return enumC2303a;
                    }
                }
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
        }
        return m7.q.f19267a;
    }
}
