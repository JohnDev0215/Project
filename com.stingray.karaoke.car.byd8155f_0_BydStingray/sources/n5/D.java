package N5;

import com.stingray.karaoke.car.player.LeadVocalState;
import com.stingray.kca.model.AudioResponse;
import h8.AbstractC1386c;
import java.util.List;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2786o;

/* JADX INFO: loaded from: classes.dex */
public final class D extends s7.i implements InterfaceC2786o {
    public /* synthetic */ AudioResponse l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ boolean f4597m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Z f4598n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public D(Z z6, InterfaceC2189c interfaceC2189c) {
        super(3, interfaceC2189c);
        this.f4598n = z6;
    }

    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, Object obj3) {
        boolean zBooleanValue = ((Boolean) obj2).booleanValue();
        D d = new D(this.f4598n, (InterfaceC2189c) obj3);
        d.l = (AudioResponse) obj;
        d.f4597m = zBooleanValue;
        return d.invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        AudioResponse audioResponse = this.l;
        boolean z6 = this.f4597m;
        List list = audioResponse != null ? audioResponse.f13077b : null;
        this.f4598n.getClass();
        boolean zJ = Z.j(list);
        boolean zK = Z.k(list);
        if (z6 && zJ) {
            return LeadVocalState.LeadVocalOn;
        }
        if (!z6 && zK) {
            return LeadVocalState.LeadVocalOff;
        }
        if (!zK && zJ) {
            return LeadVocalState.DisabledLeadVocalOn;
        }
        return LeadVocalState.DisabledLeadVocalOff;
    }
}
