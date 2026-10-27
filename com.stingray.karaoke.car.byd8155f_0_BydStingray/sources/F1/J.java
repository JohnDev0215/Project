package F1;

import A7.C0054b;
import android.view.View;
import android.view.ViewGroup;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class J extends s7.h implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f2306m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ Object f2307n;
    public final /* synthetic */ View o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public J(View view, InterfaceC2189c interfaceC2189c) {
        super(interfaceC2189c);
        this.o = view;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        J j3 = new J(this.o, interfaceC2189c);
        j3.f2307n = obj;
        return j3;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((J) create((P8.l) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object obj2;
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.f2306m;
        View view = this.o;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            P8.l lVar = (P8.l) this.f2307n;
            this.f2307n = lVar;
            this.f2306m = 1;
            lVar.c(view, this);
            return enumC2303a;
        }
        m7.q qVar = m7.q.f19267a;
        if (i7 == 1) {
            P8.l lVar2 = (P8.l) this.f2307n;
            AbstractC1386c.u(obj);
            if (view instanceof ViewGroup) {
                this.f2307n = null;
                this.f2306m = 2;
                lVar2.getClass();
                C0259s c0259s = new C0259s(new C0054b((ViewGroup) view, 1));
                if (c0259s.f2346m.hasNext()) {
                    lVar2.f5476n = c0259s;
                    lVar2.l = 2;
                    lVar2.o = this;
                    obj2 = enumC2303a;
                } else {
                    obj2 = qVar;
                }
                if (obj2 != enumC2303a) {
                    obj2 = qVar;
                }
                if (obj2 == enumC2303a) {
                    return enumC2303a;
                }
            }
        } else {
            if (i7 != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
        }
        return qVar;
    }
}
