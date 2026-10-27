package h6;

import S8.InterfaceC0725w;
import V8.f0;
import com.stingray.karaoke.car.ui.viewmodel.base.LoadingInterface$LoadingState;
import h8.AbstractC1386c;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Arrays;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class o extends s7.i implements InterfaceC2785n {
    public final /* synthetic */ p l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public o(p pVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = pVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new o(this.l, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        o oVar = (o) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2);
        m7.q qVar = m7.q.f19267a;
        oVar.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        p pVar = this.l;
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        try {
            try {
                String str = (String) Q8.h.V0((CharSequence) ((f0) pVar.l.d.l).getValue(), new String[]{"-"}, 0, 6).get(0);
                pVar.l(LoadingInterface$LoadingState.Loading);
                URL url = new URL(String.format(pVar.f15595m, Arrays.copyOf(new Object[]{str}, 1)));
                pVar.o.setValue(new String(f.p(url), Q8.a.f7211a));
                pVar.l(LoadingInterface$LoadingState.Done);
            } catch (FileNotFoundException unused) {
                pVar.l(LoadingInterface$LoadingState.Error);
            }
        } catch (FileNotFoundException unused2) {
            pVar.l(LoadingInterface$LoadingState.Loading);
            URL url2 = new URL(String.format(pVar.f15595m, Arrays.copyOf(new Object[]{pVar.f15596n}, 1)));
            pVar.o.setValue(new String(f.p(url2), Q8.a.f7211a));
            pVar.l(LoadingInterface$LoadingState.Done);
        } catch (Exception unused3) {
            pVar.l(LoadingInterface$LoadingState.Error);
        }
        return m7.q.f19267a;
    }
}
