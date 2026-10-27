package F0;

import S8.InterfaceC0725w;
import androidx.compose.ui.platform.AndroidComposeView;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class r1 extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ u1 f2255m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r1(u1 u1Var, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f2255m = u1Var;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new r1(this.f2255m, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((r1) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        m7.q qVar = m7.q.f19267a;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            AndroidComposeView androidComposeView = this.f2255m.l;
            this.l = 1;
            Object objL = androidComposeView.f11681x.l(this);
            if (objL != enumC2303a) {
                objL = qVar;
            }
            if (objL == enumC2303a) {
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
