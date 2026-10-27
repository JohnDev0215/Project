package b6;

import K5.D;
import K5.F;
import K5.G;
import S8.AbstractC0727y;
import S8.E;
import S8.InterfaceC0725w;
import V8.f0;
import com.stingray.karaoke.car.ui.viewmodel.base.LoadingInterface$LoadingState;
import com.stingray.kca.model.KaraokeItemSortTypeInfo;
import h8.AbstractC1386c;
import java.util.List;
import m7.q;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class n extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ o f12263m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ String f12264n;
    public final /* synthetic */ KaraokeItemSortTypeInfo o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public n(o oVar, String str, KaraokeItemSortTypeInfo karaokeItemSortTypeInfo, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f12263m = oVar;
        this.f12264n = str;
        this.o = karaokeItemSortTypeInfo;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new n(this.f12263m, this.f12264n, this.o, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((n) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object objF;
        KaraokeItemSortTypeInfo karaokeItemSortTypeInfo = this.o;
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        o oVar = this.f12263m;
        try {
            if (i7 == 0) {
                AbstractC1386c.u(obj);
                oVar.f12272v = false;
                G g7 = oVar.f12271u;
                String str = this.f12264n;
                g7.getClass();
                A7.m.f(str, "id");
                g7.f3619a.put(str, karaokeItemSortTypeInfo);
                oVar.f12266C.l(karaokeItemSortTypeInfo);
                F f10 = oVar.f12270t;
                KaraokeItemSortTypeInfo karaokeItemSortTypeInfo2 = (KaraokeItemSortTypeInfo) ((f0) oVar.f12267D.l).getValue();
                String str2 = karaokeItemSortTypeInfo2 != null ? karaokeItemSortTypeInfo2.c : null;
                A7.m.c(str2);
                String str3 = this.f12264n;
                int iQ = oVar.q();
                this.l = 1;
                f10.getClass();
                Z8.e eVar = E.f8084a;
                objF = AbstractC0727y.F(Z8.d.f11010n, new D(f10, str3, true, 0, iQ, false, str2, null), this);
                if (objF == enumC2303a) {
                    return enumC2303a;
                }
            } else {
                if (i7 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                AbstractC1386c.u(obj);
                objF = obj;
            }
            oVar.f12273w.l((List) objF);
            oVar.k(0, 0);
            oVar.t(LoadingInterface$LoadingState.Done);
        } catch (Throwable unused) {
            oVar.t(LoadingInterface$LoadingState.Error);
        }
        return q.f19267a;
    }
}
