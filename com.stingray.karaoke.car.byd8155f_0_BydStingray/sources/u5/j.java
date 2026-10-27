package U5;

import S8.AbstractC0727y;
import S8.InterfaceC0725w;
import T.C0730a0;
import T.C0732b0;
import T.Q0;
import T.X;
import com.stingray.karaoke.scoring.utils.ScoringSoundFxType;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class j extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ boolean f9142m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2782k f9143n;
    public final /* synthetic */ ScoringSoundFxType o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f9144p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ int f9145q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ int f9146r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2782k f9147s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ Q0 f9148t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ X f9149u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final /* synthetic */ C0732b0 f9150v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final /* synthetic */ C0730a0 f9151w;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j(boolean z6, InterfaceC2782k interfaceC2782k, ScoringSoundFxType scoringSoundFxType, int i7, int i10, int i11, InterfaceC2782k interfaceC2782k2, Q0 q02, X x8, C0732b0 c0732b0, C0730a0 c0730a0, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f9142m = z6;
        this.f9143n = interfaceC2782k;
        this.o = scoringSoundFxType;
        this.f9144p = i7;
        this.f9145q = i10;
        this.f9146r = i11;
        this.f9147s = interfaceC2782k2;
        this.f9148t = q02;
        this.f9149u = x8;
        this.f9150v = c0732b0;
        this.f9151w = c0730a0;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new j(this.f9142m, this.f9143n, this.o, this.f9144p, this.f9145q, this.f9146r, this.f9147s, this.f9148t, this.f9149u, this.f9150v, this.f9151w, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((j) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        C0730a0 c0730a0 = this.f9151w;
        C0732b0 c0732b0 = this.f9150v;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            Q0 q02 = this.f9148t;
            if (((Number) q02.getValue()).intValue() > 0) {
                X x8 = this.f9149u;
                if (!((Boolean) x8.getValue()).booleanValue() && this.f9142m) {
                    c0732b0.j(((Number) this.f9143n.b(this.o)).intValue());
                    x8.setValue(Boolean.TRUE);
                }
            }
            int iIntValue = ((Number) q02.getValue()).intValue();
            int i10 = this.f9144p;
            if (iIntValue == i10) {
                if (i10 == 0) {
                    long j3 = ((long) this.f9145q) + ((long) this.f9146r);
                    this.l = 1;
                    if (AbstractC0727y.i(j3, this) == enumC2303a) {
                        return enumC2303a;
                    }
                }
            }
            return m7.q.f19267a;
        }
        if (i7 != 1) {
            if (i7 != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
            c0730a0.j(1.0f);
            this.f9147s.b(new Integer(c0732b0.i()));
            return m7.q.f19267a;
        }
        AbstractC1386c.u(obj);
        c0730a0.j(1.3f);
        this.l = 2;
        if (AbstractC0727y.i(25L, this) == enumC2303a) {
            return enumC2303a;
        }
        c0730a0.j(1.0f);
        this.f9147s.b(new Integer(c0732b0.i()));
        return m7.q.f19267a;
    }
}
