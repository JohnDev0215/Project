package N5;

import android.media.AudioFocusRequest;
import com.stingray.karaoke.car.player.PlayerActionType;
import com.stingray.karaoke.car.player.PlayerStatus;
import com.stingray.shared.logs.segment.player.ContentCompletedReason;
import h8.AbstractC1386c;
import java.util.Iterator;
import java.util.Set;
import k3.C1672v;
import n7.AbstractC1996k;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: N5.u, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0464u extends s7.i implements InterfaceC2782k {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Z f4774m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0464u(Z z6, InterfaceC2189c interfaceC2189c) {
        super(1, interfaceC2189c);
        this.f4774m = z6;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        return ((C0464u) create((InterfaceC2189c) obj)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(InterfaceC2189c interfaceC2189c) {
        return new C0464u(this.f4774m, interfaceC2189c);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.l;
        if (i7 == 0) {
            AbstractC1386c.u(obj);
            Z z6 = this.f4774m;
            C1672v c1672v = z6.f4667p;
            if (c1672v == null) {
                A7.m.j("mediaController");
                throw null;
            }
            c1672v.b();
            Iterator it = z6.l.f3035a.values().iterator();
            while (it.hasNext()) {
                Integer num = ((I6.a) it.next()).f3029a;
            }
            z6.f4624C.setValue(Boolean.FALSE);
            AudioFocusRequest audioFocusRequest = z6.f4671t;
            if (audioFocusRequest == null) {
                A7.m.j("audioFocusRequest");
                throw null;
            }
            z6.f4628G.abandonAudioFocusRequest(audioFocusRequest);
            z6.m(ContentCompletedReason.COMPLETED, z6.o);
            z6.o();
            PlayerActionType playerActionType = PlayerActionType.Stop;
            Set setC0 = AbstractC1996k.c0(new PlayerStatus[]{PlayerStatus.STOPPED, PlayerStatus.PAUSED});
            this.l = 1;
            obj = Z.H(this.f4774m, playerActionType, setC0, 0L, this, 12);
            if (obj == enumC2303a) {
                return enumC2303a;
            }
        } else {
            if (i7 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            AbstractC1386c.u(obj);
        }
        return obj;
    }
}
