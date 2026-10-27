package p;

import com.spotify.cosmos.cosmos.Request;
import com.spotify.cosmos.rxrouter.RxRouter;
import com.spotify.esperanto.esperantocosmos.EsperantoRxRouter;
import io.reactivex.rxjava3.core.Observable;

/* JADX INFO: loaded from: classes.dex */
public final class g20 implements EsperantoRxRouter {
    public final /* synthetic */ int a;
    public final /* synthetic */ RxRouter b;

    public /* synthetic */ g20(RxRouter rxRouter, int i) {
        this.a = i;
        this.b = rxRouter;
    }

    @Override // com.spotify.esperanto.esperantocosmos.EsperantoRxRouter
    public final Observable resolve(Request request) {
        int i = this.a;
        RxRouter rxRouter = this.b;
        switch (i) {
            case 0:
                v03.j(request, "request");
                break;
            default:
                v03.j(request, "request");
                break;
        }
        return rxRouter.resolve(request);
    }
}
