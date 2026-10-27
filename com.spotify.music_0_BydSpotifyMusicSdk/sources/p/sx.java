package p;

import com.spotify.cosmos.cosmos.Request;
import com.spotify.cosmos.rxrouter.RxRouter;
import com.spotify.esperanto.esperantocosmos.EsperantoRxRouter;
import io.reactivex.rxjava3.core.Observable;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class sx implements EsperantoRxRouter {
    public final /* synthetic */ int a;
    public final /* synthetic */ RxRouter b;

    public /* synthetic */ sx(RxRouter rxRouter, int i) {
        this.a = i;
        this.b = rxRouter;
    }

    @Override // com.spotify.esperanto.esperantocosmos.EsperantoRxRouter
    public final Observable resolve(Request request) {
        int i = this.a;
        return this.b.resolve(request);
    }
}
