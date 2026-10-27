package Q5;

import com.stingray.karaoke.car.player.PlayerStatus;
import com.stingray.karaoke.car.player.SongQueuePosition;
import com.stingray.kca.model.KaraokeSongResponseV2;
import com.stingray.shared.logs.segment.ActionOwner;
import d6.C1104a;
import d6.C1105b;
import d6.C1106c;
import d6.C1107d;
import java.util.List;
import z7.InterfaceC2787p;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class X1 extends A7.j implements InterfaceC2787p {
    @Override // z7.InterfaceC2787p
    public final Object o(Object obj, Object obj2, Object obj3, Object obj4) {
        KaraokeSongResponseV2 karaokeSongResponseV2 = (KaraokeSongResponseV2) obj;
        SongQueuePosition songQueuePosition = (SongQueuePosition) obj2;
        boolean zBooleanValue = ((Boolean) obj3).booleanValue();
        F6.b bVar = (F6.b) obj4;
        A7.m.f(karaokeSongResponseV2, "p0");
        A7.m.f(songQueuePosition, "p1");
        d6.j jVar = (d6.j) this.f455m;
        jVar.getClass();
        if (karaokeSongResponseV2.f13136a == null) {
            return C1106c.f13468a;
        }
        N5.q0 q0Var = jVar.f13483v;
        if (!zBooleanValue && ((List) ((V8.f0) q0Var.f4752v.l).getValue()).size() == 100 && ((Number) ((V8.f0) q0Var.f4750t.l).getValue()).intValue() == 0 && songQueuePosition != SongQueuePosition.SING_NOW) {
            return C1105b.f13467a;
        }
        if (!((Boolean) jVar.f13485x.c.getValue()).booleanValue() && songQueuePosition == SongQueuePosition.SCORE_NOW) {
            return C1107d.f13469a;
        }
        boolean z6 = !q0Var.g() && !q0Var.f4740h.isEmpty() && ((V8.f0) jVar.f13484w.f4635N.l).getValue() == PlayerStatus.STOPPED && (songQueuePosition == SongQueuePosition.SING_LAST || songQueuePosition == SongQueuePosition.SING_NEXT);
        jVar.f13483v.d(new N5.f0(karaokeSongResponseV2), songQueuePosition, false, z6, bVar);
        if (songQueuePosition == SongQueuePosition.SCORE_NOW) {
            jVar.f13484w.x(true, true, ActionOwner.HOST, true, true);
        } else if (songQueuePosition == SongQueuePosition.SING_NOW) {
            jVar.f13484w.x(false, true, ActionOwner.HOST, true, A7.m.a(karaokeSongResponseV2.f13146n, Boolean.TRUE));
        }
        return new C1104a(z6);
    }
}
