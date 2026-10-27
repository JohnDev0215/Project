package h6;

import S8.InterfaceC0725w;
import V8.f0;
import com.stingray.karaoke.car.ui.viewmodel.base.LoadingInterface$LoadingState;
import h8.AbstractC1386c;
import java.net.URL;
import java.util.Arrays;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class k extends s7.i implements InterfaceC2785n {
    public final /* synthetic */ l l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k(l lVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = lVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new k(this.l, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        k kVar = (k) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2);
        m7.q qVar = m7.q.f19267a;
        kVar.invokeSuspend(qVar);
        return qVar;
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Object, java.util.Map] */
    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        l lVar = this.l;
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        try {
            String str = (String) Q8.h.V0((CharSequence) ((f0) lVar.l.d.l).getValue(), new String[]{"-"}, 0, 6).get(0);
            ?? r02 = lVar.f15583n;
            if (!r02.containsKey(str)) {
                str = "en";
            }
            String str2 = (String) r02.get(str);
            lVar.l(LoadingInterface$LoadingState.Loading);
            URL url = new URL(String.format(lVar.f15582m, Arrays.copyOf(new Object[]{str2}, 1)));
            lVar.o.setValue(new String(f.p(url), Q8.a.f7211a));
            lVar.l(LoadingInterface$LoadingState.Done);
        } catch (Exception unused) {
            lVar.l(LoadingInterface$LoadingState.Error);
        }
        return m7.q.f19267a;
    }
}
