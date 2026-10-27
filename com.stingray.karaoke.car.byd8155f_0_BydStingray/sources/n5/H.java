package N5;

import S8.InterfaceC0725w;
import com.stingray.shared.logs.segment.player.PlaybackInterruptedReason;
import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class H extends s7.i implements InterfaceC2785n {
    public final /* synthetic */ Z l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ PlaybackInterruptedReason f4605m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ String f4606n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public H(Z z6, PlaybackInterruptedReason playbackInterruptedReason, String str, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = z6;
        this.f4605m = playbackInterruptedReason;
        this.f4606n = str;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new H(this.l, this.f4605m, this.f4606n, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        H h10 = (H) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2);
        m7.q qVar = m7.q.f19267a;
        h10.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        Z z6 = this.l;
        if (z6.f4636O.getValue() != null) {
            D6.b bVarB = z6.b(false);
            D6.a aVarA = z6.a(null);
            D6.e eVar = z6.f4654e;
            eVar.getClass();
            PlaybackInterruptedReason playbackInterruptedReason = this.f4605m;
            A7.m.f(playbackInterruptedReason, "reason");
            B5.z zVarB = D6.e.b(eVar, bVarB, aVarA, null, 4);
            zVarB.k(playbackInterruptedReason.toString(), "method");
            zVarB.k(this.f4606n, "method_details");
            eVar.c("Karaoke Playback Interrupted", zVarB, eVar.f1212e);
        }
        return m7.q.f19267a;
    }
}
