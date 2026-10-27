package W5;

import S8.AbstractC0727y;
import S8.InterfaceC0725w;
import com.stingray.karaoke.car.ui.navigation.NavigationType;
import com.stingray.karaoke.car.ui.navigation.StingrayKaraokeTabScreen;
import h8.AbstractC1386c;
import java.util.LinkedHashMap;
import n3.C1930E;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class b extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ e f9812m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ StingrayKaraokeTabScreen f9813n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(e eVar, StingrayKaraokeTabScreen stingrayKaraokeTabScreen, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f9812m = eVar;
        this.f9813n = stingrayKaraokeTabScreen;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new b(this.f9812m, this.f9813n, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((b) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            this.l = 1;
            if (AbstractC0727y.i(20L, this) == enumC2303a) {
                return enumC2303a;
            }
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
        }
        e eVar = this.f9812m;
        LinkedHashMap linkedHashMap = eVar.d;
        NavigationType navigationType = NavigationType.TAB;
        C1930E c1930e = (C1930E) linkedHashMap.get(navigationType);
        if (c1930e != null) {
            c1930e.q();
        }
        eVar.j(this.f9813n);
        C1930E c1930e2 = (C1930E) eVar.d.get(navigationType);
        if (c1930e2 != null) {
            c1930e2.n(eVar.a().name(), new U5.i(4));
        }
        eVar.e();
        return m7.q.f19267a;
    }
}
