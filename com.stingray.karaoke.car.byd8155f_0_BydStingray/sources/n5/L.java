package N5;

import S8.InterfaceC0725w;
import com.stingray.shared.logs.segment.ActionOwner;
import com.stingray.shared.logs.segment.player.PlaybackSetting;
import h8.AbstractC1386c;
import h8.AbstractC1387d;
import java.util.Locale;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class L extends s7.i implements InterfaceC2785n {
    public final /* synthetic */ Z l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ PlaybackSetting f4611m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ ActionOwner f4612n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public L(Z z6, PlaybackSetting playbackSetting, ActionOwner actionOwner, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = z6;
        this.f4611m = playbackSetting;
        this.f4612n = actionOwner;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new L(this.l, this.f4611m, this.f4612n, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        L l = (L) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2);
        m7.q qVar = m7.q.f19267a;
        l.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        Z z6 = this.l;
        D6.b bVarB = z6.b(false);
        D6.a aVarA = z6.a(null);
        D6.e eVar = z6.f4654e;
        eVar.getClass();
        PlaybackSetting playbackSetting = this.f4611m;
        A7.m.f(playbackSetting, "change");
        ActionOwner actionOwner = this.f4612n;
        A7.m.f(actionOwner, "actionOwner");
        B5.z zVarA = D6.e.a(bVarB, aVarA, actionOwner);
        String lowerCase = playbackSetting.name().toLowerCase(Locale.ROOT);
        A7.m.e(lowerCase, "toLowerCase(...)");
        zVarA.k(AbstractC1387d.j(lowerCase), "changes");
        zVarA.k(null, "video_background_url");
        eVar.c("Karaoke Playback Setting", zVarA, true);
        return m7.q.f19267a;
    }
}
