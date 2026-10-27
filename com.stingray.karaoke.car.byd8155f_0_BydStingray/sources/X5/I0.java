package X5;

import S8.AbstractC0727y;
import com.stingray.kca.model.AudioResponse;
import com.stingray.kca.model.KmlResourceV2;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class I0 extends s7.i implements InterfaceC2785n {
    public /* synthetic */ Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ V0 f10178m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public I0(V0 v02, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f10178m = v02;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        I0 i02 = new I0(this.f10178m, interfaceC2189c);
        i02.l = obj;
        return i02;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        I0 i02 = (I0) create((AudioResponse) obj, (InterfaceC2189c) obj2);
        m7.q qVar = m7.q.f19267a;
        i02.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        KmlResourceV2 kmlResourceV2;
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        AudioResponse audioResponse = (AudioResponse) this.l;
        String str = (audioResponse == null || (kmlResourceV2 = audioResponse.f13076a) == null) ? null : kmlResourceV2.f13155a;
        boolean z6 = h6.n.f15589g;
        V0 v02 = this.f10178m;
        if (z6) {
            AbstractC0727y.w(v02.d, null, null, new S0(v02, str, null), 3);
        } else {
            v02.b(str);
        }
        if (((V8.f0) v02.f10206a.f4645X.l).getValue() == null && h6.n.f15589g) {
            V0.a(v02, null);
        }
        return m7.q.f19267a;
    }
}
