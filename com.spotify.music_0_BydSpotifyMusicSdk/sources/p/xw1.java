package p;

import com.google.common.collect.ImmutableList;
import com.spotify.player.model.ContextTrack;
import com.spotify.player.model.PlayerQueue;

/* JADX INFO: loaded from: classes.dex */
public final class xw1 implements zc1 {
    public final st1 b;
    public final io.reactivex.rxjava3.disposables.a c = new io.reactivex.rxjava3.disposables.a();
    public final m60 d;

    public xw1(st1 st1Var, m60 m60Var) {
        this.b = st1Var;
        this.d = m60Var;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ pr1 s(PlayerQueue playerQueue) {
        ImmutableList immutableListC = playerQueue.c();
        String str = immutableListC.size() > 0 ? (String) ((ContextTrack) immutableListC.get(0)).b().get("image_url") : null;
        ImmutableList immutableListB = playerQueue.b();
        return pr1.a(str, immutableListB.size() > 0 ? (String) ((ContextTrack) immutableListB.get(0)).b().get("image_url") : null);
    }

    @Override // p.zc1
    public final void a() {
        this.c.d();
    }
}
