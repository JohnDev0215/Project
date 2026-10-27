package Q5;

import S8.InterfaceC0725w;
import h8.AbstractC1386c;
import java.util.List;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: Q5.j2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0584j2 extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f6911m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ P5.p f6912n;
    public final /* synthetic */ T.X o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0584j2(int i7, P5.p pVar, T.X x8, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f6911m = i7;
        this.f6912n = pVar;
        this.o = x8;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new C0584j2(this.f6911m, this.f6912n, this.o, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C0584j2) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            int i10 = this.f6911m;
            if (i10 >= 0) {
                List list = (List) this.o.getValue();
                if (i10 < (list != null ? list.size() : 0)) {
                    this.l = 1;
                    if (this.f6912n.u(i10, false, this) == enumC2303a) {
                        return enumC2303a;
                    }
                }
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
