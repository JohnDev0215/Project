package U5;

import S8.AbstractC0727y;
import S8.InterfaceC0725w;
import T.C0732b0;
import T.X;
import com.stingray.drivemodedetector.R;
import com.stingray.karaoke.scoring.utils.ScoringSoundFxType;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class C extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ boolean f9086m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2782k f9087n;
    public final /* synthetic */ int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ X f9088p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ X f9089q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ C0732b0 f9090r;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C(boolean z6, InterfaceC2782k interfaceC2782k, int i7, X x8, X x9, C0732b0 c0732b0, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f9086m = z6;
        this.f9087n = interfaceC2782k;
        this.o = i7;
        this.f9088p = x8;
        this.f9089q = x9;
        this.f9090r = c0732b0;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new C(this.f9086m, this.f9087n, this.o, this.f9088p, this.f9089q, this.f9090r, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        X x8 = this.f9089q;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            if (((Boolean) this.f9088p.getValue()).booleanValue() && this.f9086m) {
                int i10 = this.o;
                this.f9087n.b(i10 != 0 ? i10 != 1 ? i10 != 2 ? i10 != 3 ? i10 != 4 ? null : ScoringSoundFxType.RS_STAR5 : ScoringSoundFxType.RS_STAR4 : ScoringSoundFxType.RS_STAR3 : ScoringSoundFxType.RS_STAR2 : ScoringSoundFxType.RS_STAR1);
                x8.setValue(Boolean.TRUE);
                this.l = 1;
                if (AbstractC0727y.i(100L, this) == enumC2303a) {
                    return enumC2303a;
                }
            }
            return m7.q.f19267a;
        }
        if (i7 != 1) {
            if (i7 != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
            this.f9090r.j(R.drawable.star_10);
            return m7.q.f19267a;
        }
        AbstractC1386c.u(obj);
        x8.setValue(Boolean.FALSE);
        this.l = 2;
        if (AbstractC0727y.i(100L, this) == enumC2303a) {
            return enumC2303a;
        }
        this.f9090r.j(R.drawable.star_10);
        return m7.q.f19267a;
    }
}
