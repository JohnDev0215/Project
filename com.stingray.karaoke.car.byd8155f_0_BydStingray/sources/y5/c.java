package Y5;

import A7.m;
import androidx.media3.exoplayer.ExoPlayer;
import e2.O;
import e2.U;
import n2.C1923x;
import timber.log.Timber;

/* JADX INFO: loaded from: classes.dex */
public final class c implements U {
    public final /* synthetic */ d l;

    public c(d dVar) {
        this.l = dVar;
    }

    @Override // e2.U
    public final void f(O o) {
        m.f(o, "error");
        d dVar = this.l;
        String str = "Background Player: onPlayerError on background URI " + dVar.f10789g.getValue() + " " + o;
        m.f(str, "error");
        B9.a aVar = Timber.f22577a;
        aVar.e("PlayerBackgroundManager");
        aVar.b(str, new Object[0]);
        ((C1923x) ((ExoPlayer) dVar.f10787e.getValue())).c();
    }
}
