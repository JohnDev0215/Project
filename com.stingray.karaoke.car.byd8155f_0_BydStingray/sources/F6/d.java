package f6;

import S8.InterfaceC0725w;
import com.stingray.karaoke.car.ui.viewmodel.base.LoadingInterface$LoadingState;
import com.stingray.shared.logs.segment.songQueue.SourceType;
import h8.AbstractC1386c;
import m7.q;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import timber.log.Timber;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class d extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ g f14605m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(g gVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f14605m = gVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new d(this.f14605m, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((d) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        g gVar = this.f14605m;
        try {
            if (i7 == 0) {
                AbstractC1386c.u(obj);
                gVar.l(LoadingInterface$LoadingState.Loading);
                this.l = 1;
                if (g.o(gVar, this) == enumC2303a) {
                    return enumC2303a;
                }
            } else {
                if (i7 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                AbstractC1386c.u(obj);
            }
            gVar.l(LoadingInterface$LoadingState.Done);
            L5.i iVar = gVar.f14615w;
            String str = iVar.f4035g;
            if (str != null) {
                iVar.f(str, new F6.b(SourceType.DEEP_LINK, str));
                iVar.f4035g = null;
            }
        } catch (Exception e10) {
            String str2 = "Failed to load home data " + e10;
            A7.m.f(str2, "error");
            B9.a aVar = Timber.f22577a;
            aVar.e("");
            aVar.b(str2, new Object[0]);
            gVar.l(LoadingInterface$LoadingState.Error);
        }
        return q.f19267a;
    }
}
