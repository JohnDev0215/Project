package p;

import com.spotify.player.model.PlayOrigin;

/* JADX INFO: loaded from: classes.dex */
public final class v1 implements oz0 {
    public final /* synthetic */ z1 c;

    public v1(z1 z1Var) {
        this.c = z1Var;
    }

    @Override // p.oz0
    public final Object apply(Object obj) {
        z1 z1Var = this.c;
        PlayOrigin.Builder builderA = PlayOrigin.a(z1Var.e.f());
        PlayOrigin playOrigin = z1Var.e;
        return builderA.f(playOrigin.g()).i(playOrigin.k()).c(playOrigin.d()).g((String) obj).b(playOrigin.c()).d(playOrigin.e()).a();
    }
}
