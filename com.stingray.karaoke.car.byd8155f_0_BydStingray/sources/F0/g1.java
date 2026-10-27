package F0;

import S8.InterfaceC0725w;
import T.C0758o0;
import T.C0767t0;
import V8.AbstractC0828q;
import android.view.View;
import com.stingray.drivemodedetector.R;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class g1 extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C0767t0 f2205m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ View f2206n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g1(C0767t0 c0767t0, View view, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f2205m = c0767t0;
        this.f2206n = view;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new g1(this.f2205m, this.f2206n, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((g1) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        m7.q qVar = m7.q.f19267a;
        C0767t0 c0767t0 = this.f2205m;
        View view = this.f2206n;
        try {
            if (i7 == 0) {
                AbstractC1386c.u(obj);
                this.l = 1;
                Object objK = AbstractC0828q.k(c0767t0.f8364r, new C0758o0(2, null), this);
                if (objK != enumC2303a) {
                    objK = qVar;
                }
                if (objK == enumC2303a) {
                    return enumC2303a;
                }
            } else {
                if (i7 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                AbstractC1386c.u(obj);
            }
            if (q1.b(view) == c0767t0) {
                view.setTag(R.id.androidx_compose_ui_view_composition_context, null);
            }
            return qVar;
        } finally {
            if (q1.b(view) == c0767t0) {
                view.setTag(R.id.androidx_compose_ui_view_composition_context, null);
            }
        }
    }
}
