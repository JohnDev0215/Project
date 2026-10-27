package X5;

import S8.AbstractC0727y;
import S8.InterfaceC0725w;
import com.stingray.kca.model.AudioResponse;
import com.stingray.kca.model.KmlResourceV2;
import com.stingray.kca.model.PitchInfoResponse;
import com.stingray.kca.model.SignedFile;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class U0 extends s7.i implements InterfaceC2785n {
    public final /* synthetic */ V0 l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public U0(V0 v02, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = v02;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new U0(this.l, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        U0 u02 = (U0) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2);
        m7.q qVar = m7.q.f19267a;
        u02.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        SignedFile signedFile;
        KmlResourceV2 kmlResourceV2;
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        V0 v02 = this.l;
        AudioResponse audioResponse = (AudioResponse) ((V8.f0) v02.f10206a.f4643V.l).getValue();
        String str = null;
        String str2 = (audioResponse == null || (kmlResourceV2 = audioResponse.f13076a) == null) ? null : kmlResourceV2.f13155a;
        if (h6.n.f15589g) {
            AbstractC0727y.w(v02.d, null, null, new S0(v02, str2, null), 3);
        } else {
            v02.b(str2);
        }
        PitchInfoResponse pitchInfoResponse = (PitchInfoResponse) ((V8.f0) v02.f10206a.f4645X.l).getValue();
        if (pitchInfoResponse != null && (signedFile = pitchInfoResponse.f13188a) != null) {
            str = signedFile.f13206a;
        }
        V0.a(v02, str);
        return m7.q.f19267a;
    }
}
