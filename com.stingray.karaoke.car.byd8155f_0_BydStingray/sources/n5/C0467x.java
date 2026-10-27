package N5;

import V8.AbstractC0828q;
import com.stingray.karaoke.car.player.PlayerService;
import com.stingray.karaoke.car.player.PlayerStatus;
import com.stingray.kca.model.AudioResponse;
import com.stingray.shared.logs.segment.ActionOwner;
import com.stingray.shared.logs.segment.player.PlaybackSetting;
import h8.AbstractC1386c;
import java.util.List;
import k3.C1672v;
import n2.C1923x;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: N5.x, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0467x extends s7.i implements InterfaceC2782k {
    public PlayerStatus l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f4777m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Z f4778n;
    public final /* synthetic */ ActionOwner o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0467x(Z z6, ActionOwner actionOwner, InterfaceC2189c interfaceC2189c) {
        super(1, interfaceC2189c);
        this.f4778n = z6;
        this.o = actionOwner;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        return ((C0467x) create((InterfaceC2189c) obj)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(InterfaceC2189c interfaceC2189c) {
        return new C0467x(this.f4778n, this.o, interfaceC2189c);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        PlayerStatus playerStatus;
        AudioResponse audioResponse;
        List list;
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.f4777m;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            Z z6 = this.f4778n;
            PlayerStatus playerStatus2 = (PlayerStatus) z6.f4634M.getValue();
            V8.f0 f0Var = z6.f4657f0;
            boolean zBooleanValue = ((Boolean) f0Var.getValue()).booleanValue();
            f0 f0Var2 = (f0) z6.f4636O.getValue();
            q0 q0Var = z6.c;
            if (f0Var2 != null && (audioResponse = f0Var2.f4692b) != null && (list = audioResponse.f13077b) != null) {
                Boolean boolValueOf = Boolean.valueOf(!zBooleanValue);
                f0Var.getClass();
                f0Var.m(null, boolValueOf);
                if (Z.k(list) && Z.j(list)) {
                    long jI = z6.i() + ((long) (zBooleanValue ? z6.f4623A : -z6.f4623A));
                    boolean z10 = ((V8.f0) z6.f4635N.l).getValue() == PlayerStatus.STOPPED;
                    List list2 = (List) ((V8.f0) q0Var.f4752v.l).getValue();
                    C1923x c1923x = PlayerService.f13008u;
                    C1672v c1672v = z6.f4667p;
                    if (c1672v == null) {
                        A7.m.j("mediaController");
                        throw null;
                    }
                    f0 f0Var3 = (f0) list2.get(c1672v.b0());
                    A7.m.f(f0Var3, "item");
                    C1923x c1923x2 = PlayerService.f13008u;
                    if (c1923x2 == null) {
                        A7.m.j("player");
                        throw null;
                    }
                    C8.d.q(c1923x2.b0(), C8.d.n(f0Var3, 0L));
                    if (z10) {
                        z6.f4676y = -1L;
                        C1672v c1672v2 = z6.f4667p;
                        if (c1672v2 == null) {
                            A7.m.j("mediaController");
                            throw null;
                        }
                        c1672v2.b();
                    } else {
                        z6.f4676y = jI;
                        A7.m.f("changing vocals at: " + jI, "text");
                    }
                    z6.p(PlaybackSetting.LEAD_VOCALS, this.o);
                }
            }
            if (z6.l()) {
                z6.A(z6.f4676y, ((Number) ((V8.f0) q0Var.f4750t.l).getValue()).intValue());
            }
            C0466w c0466w = new C0466w(playerStatus2, null);
            this.l = playerStatus2;
            this.f4777m = 1;
            obj = AbstractC0828q.k(z6.f4634M, c0466w, this);
            if (obj == enumC2303a) {
                return enumC2303a;
            }
            playerStatus = playerStatus2;
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            playerStatus = this.l;
            AbstractC1386c.u(obj);
        }
        PlayerStatus playerStatus3 = (PlayerStatus) obj;
        return (!(playerStatus3.getIsActive() && playerStatus.getIsActive()) && (playerStatus3.getIsActive() || playerStatus.getIsActive())) ? AbstractC0465v.f4775a[playerStatus3.ordinal()] == 1 ? EnumC0445a.f4678m : EnumC0445a.f4679n : EnumC0445a.l;
    }
}
