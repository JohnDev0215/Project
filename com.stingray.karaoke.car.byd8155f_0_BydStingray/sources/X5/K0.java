package X5;

import com.stingray.kca.model.PitchInfoResponse;
import com.stingray.kca.model.SignedFile;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class K0 extends s7.i implements InterfaceC2785n {
    public /* synthetic */ Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ V0 f10181m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public K0(V0 v02, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f10181m = v02;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        K0 k02 = new K0(this.f10181m, interfaceC2189c);
        k02.l = obj;
        return k02;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        K0 k02 = (K0) create((PitchInfoResponse) obj, (InterfaceC2189c) obj2);
        m7.q qVar = m7.q.f19267a;
        k02.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        SignedFile signedFile;
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        PitchInfoResponse pitchInfoResponse = (PitchInfoResponse) this.l;
        V0.a(this.f10181m, (pitchInfoResponse == null || (signedFile = pitchInfoResponse.f13188a) == null) ? null : signedFile.f13206a);
        return m7.q.f19267a;
    }
}
