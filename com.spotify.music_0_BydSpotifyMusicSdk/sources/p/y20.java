package p;

import com.spotify.cosmos.rxrouter.RxRouter;
import com.spotify.esperanto.esperantocosmos.CosmosTransport;

/* JADX INFO: loaded from: classes.dex */
public final class y20 implements ul0 {
    public final /* synthetic */ int a;
    public final xy1 b;
    public final Object c;

    public /* synthetic */ y20(Object obj, wy1 wy1Var, int i) {
        this.a = i;
        this.c = obj;
        this.b = wy1Var;
    }

    @Override // p.xy1
    public final Object get() {
        int i = this.a;
        xy1 xy1Var = this.b;
        Object obj = this.c;
        switch (i) {
            case 0:
                e52 e52Var = (e52) xy1Var.get();
                ((u11) obj).getClass();
                v03.j(e52Var, "retrofit");
                Object objB = new d52(e52Var).e().b(gs1.class);
                v03.i(objB, "retrofit.newBuilder().bu…erIdEndpoint::class.java)");
                return (gs1) objB;
            default:
                RxRouter rxRouter = (RxRouter) xy1Var.get();
                ((zb0) obj).getClass();
                v03.j(rxRouter, "rxRouter");
                return new vz(new CosmosTransport(new wz(rxRouter, 0)));
        }
    }
}
