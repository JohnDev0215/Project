package p;

import com.spotify.cosmos.cosmos.Request;
import com.spotify.cosmos.rxrouter.RxRouter;
import com.spotify.esperanto.esperantocosmos.EsperantoRxRouter;
import io.reactivex.rxjava3.core.Observable;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class wz implements EsperantoRxRouter, pz0 {
    public final /* synthetic */ int a;
    public final /* synthetic */ RxRouter b;

    public /* synthetic */ wz(RxRouter rxRouter, int i) {
        this.a = i;
        this.b = rxRouter;
    }

    @Override // p.pz0
    public final FunctionReferenceImpl a() {
        switch (this.a) {
        }
        return new FunctionReferenceImpl(1, this.b, RxRouter.class, "resolve", "resolve(Lcom/spotify/cosmos/cosmos/Request;)Lio/reactivex/rxjava3/core/Observable;", 0);
    }

    public final boolean equals(Object obj) {
        switch (this.a) {
            case 0:
                if ((obj instanceof EsperantoRxRouter) && (obj instanceof pz0)) {
                    break;
                }
                break;
            case 1:
                if ((obj instanceof EsperantoRxRouter) && (obj instanceof pz0)) {
                    break;
                }
                break;
            case 2:
                if ((obj instanceof EsperantoRxRouter) && (obj instanceof pz0)) {
                    break;
                }
                break;
            case 3:
                if ((obj instanceof EsperantoRxRouter) && (obj instanceof pz0)) {
                    break;
                }
                break;
            case 4:
                if ((obj instanceof EsperantoRxRouter) && (obj instanceof pz0)) {
                    break;
                }
                break;
            default:
                if ((obj instanceof EsperantoRxRouter) && (obj instanceof pz0)) {
                    break;
                }
                break;
        }
        return v03.b(a(), ((pz0) obj).a());
    }

    public final int hashCode() {
        switch (this.a) {
        }
        return a().hashCode();
    }

    @Override // com.spotify.esperanto.esperantocosmos.EsperantoRxRouter
    public final Observable resolve(Request request) {
        int i = this.a;
        RxRouter rxRouter = this.b;
        switch (i) {
            case 0:
                v03.j(request, "p0");
                break;
            case 1:
                v03.j(request, "p0");
                break;
            case 2:
                v03.j(request, "p0");
                break;
            case 3:
                v03.j(request, "p0");
                break;
            case 4:
                v03.j(request, "p0");
                break;
            default:
                v03.j(request, "p0");
                break;
        }
        return rxRouter.resolve(request);
    }
}
