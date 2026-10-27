package S0;

import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: S0.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0693e extends s7.i implements InterfaceC2782k {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C0696h f7831m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ G f7832n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0693e(C0696h c0696h, G g7, InterfaceC2189c interfaceC2189c) {
        super(1, interfaceC2189c);
        this.f7831m = c0696h;
        this.f7832n = g7;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        return ((C0693e) create((InterfaceC2189c) obj)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(InterfaceC2189c interfaceC2189c) {
        return new C0693e(this.f7831m, this.f7832n, interfaceC2189c);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            this.l = 1;
            obj = this.f7831m.c(this.f7832n, this);
            if (obj == enumC2303a) {
                return enumC2303a;
            }
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
        }
        return obj;
    }
}
