package p6;

import S8.AbstractC0727y;
import S8.InterfaceC0725w;
import T.C0732b0;
import T.X;
import com.stingray.karaoke.scoring.utils.ScoringSoundFxType;
import h8.AbstractC1386c;
import m7.q;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import s7.i;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class c extends i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f20923m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2785n f20924n;
    public final /* synthetic */ C0732b0 o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ X f20925p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(int i7, InterfaceC2785n interfaceC2785n, C0732b0 c0732b0, X x8, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f20923m = i7;
        this.f20924n = interfaceC2785n;
        this.o = c0732b0;
        this.f20925p = x8;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new c(this.f20923m, this.f20924n, this.o, this.f20925p, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((c) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        X x8 = this.f20925p;
        int i10 = this.f20923m;
        C0732b0 c0732b0 = this.o;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            if (c0732b0.i() != i10) {
                int i11 = c0732b0.i();
                InterfaceC2785n interfaceC2785n = this.f20924n;
                if (i10 > i11) {
                    x8.setValue(Boolean.TRUE);
                    interfaceC2785n.invoke(i10 != 2 ? i10 != 3 ? i10 != 4 ? null : ScoringSoundFxType.GP_MULTI_L4 : ScoringSoundFxType.GP_MULTI_L3 : ScoringSoundFxType.GP_MULTI_L2, null);
                    this.l = 1;
                    if (AbstractC0727y.i(250L, this) == enumC2303a) {
                        return enumC2303a;
                    }
                } else {
                    interfaceC2785n.invoke(ScoringSoundFxType.GP_MULTI_DOWN, null);
                }
            } else {
                x8.setValue(Boolean.FALSE);
            }
            c0732b0.j(i10);
            return q.f19267a;
        }
        if (i7 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        AbstractC1386c.u(obj);
        x8.setValue(Boolean.FALSE);
        c0732b0.j(i10);
        return q.f19267a;
    }
}
