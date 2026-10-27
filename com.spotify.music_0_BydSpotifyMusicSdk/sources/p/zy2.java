package p;

import android.os.Handler;
import android.os.HandlerThread;
import com.spotify.cosmos.cosmos.Request;
import com.spotify.cosmos.rxrouter.RxRouter;
import com.spotify.cosmos.servicebasedrouter.RemoteNativeRxRouter;
import io.reactivex.rxjava3.core.Observable;

/* JADX INFO: loaded from: classes.dex */
public final class zy2 implements RxRouter {
    public final io.reactivex.rxjava3.subjects.b a = io.reactivex.rxjava3.subjects.b.a();
    public RemoteNativeRxRouter b;
    public Handler c;
    public HandlerThread d;

    @Override // com.spotify.cosmos.rxrouter.RxRouter
    public final Observable resolve(Request request) {
        v03.j(request, "request");
        RemoteNativeRxRouter remoteNativeRxRouter = this.b;
        if (remoteNativeRxRouter != null) {
            return remoteNativeRxRouter.resolve(request);
        }
        Observable observableSwitchMap = this.a.filter(ro.k).map(yy2.c).switchMap(new ux1(10, request));
        v03.i(observableSwitchMap, "request: Request): Obser…olve(request) }\n        }");
        return observableSwitchMap;
    }
}
