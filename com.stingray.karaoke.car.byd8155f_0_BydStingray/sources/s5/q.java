package S5;

import S8.AbstractC0727y;
import S8.InterfaceC0725w;
import T.X;
import com.stingray.karaoke.scoring.utils.ScoringSoundFxType;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class q extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2782k f8036m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ X f8037n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public q(InterfaceC2782k interfaceC2782k, X x8, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f8036m = interfaceC2782k;
        this.f8037n = x8;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new q(this.f8036m, this.f8037n, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((q) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            this.l = 1;
            if (AbstractC0727y.i(4900L, this) == enumC2303a) {
                return enumC2303a;
            }
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
        }
        this.f8037n.setValue((Integer) this.f8036m.b(ScoringSoundFxType.RS_DRONE));
        return m7.q.f19267a;
    }
}
