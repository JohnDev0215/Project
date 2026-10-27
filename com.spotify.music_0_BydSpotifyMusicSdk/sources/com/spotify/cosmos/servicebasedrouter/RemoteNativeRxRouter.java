package com.spotify.cosmos.servicebasedrouter;

import android.os.Handler;
import com.spotify.cosmos.cosmos.Lifetime;
import com.spotify.cosmos.cosmos.Request;
import com.spotify.cosmos.cosmos.Response;
import com.spotify.cosmos.routercallback.ResolverCallbackReceiver;
import com.spotify.cosmos.rxrouter.RxRouter;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.functions.f;
import io.reactivex.rxjava3.functions.g;
import kotlin.jvm.internal.DefaultConstructorMarker;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public class RemoteNativeRxRouter implements RxRouter {
    public static final Companion Companion = new Companion(null);
    private final Handler handler;
    private final RemoteNativeRouter router;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean isSubscription(Request request) {
            return Request.SUB.equals(request.getAction());
        }
    }

    public RemoteNativeRxRouter(RemoteNativeRouter remoteNativeRouter, Handler handler) {
        this.router = remoteNativeRouter;
        this.handler = handler;
    }

    @Override // com.spotify.cosmos.rxrouter.RxRouter
    public Observable<Response> resolve(final Request request) {
        v03.j(request, "request");
        Observable<Response> observableCreate = Observable.create(new ObservableOnSubscribe() { // from class: com.spotify.cosmos.servicebasedrouter.RemoteNativeRxRouter.resolve.1
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            public final void subscribe(final ObservableEmitter<Response> observableEmitter) {
                ResolverCallbackReceiver.Companion companion = ResolverCallbackReceiver.Companion;
                Handler handler = RemoteNativeRxRouter.this.handler;
                final Request request2 = request;
                ResolverCallbackReceiver<T> resolverCallbackReceiverForAny = companion.forAny(handler, new g() { // from class: com.spotify.cosmos.servicebasedrouter.RemoteNativeRxRouter$resolve$1$callbackReceiver$1
                    @Override // io.reactivex.rxjava3.functions.g
                    public final void accept(Response response) {
                        v03.j(response, "response");
                        if (observableEmitter.isDisposed()) {
                            return;
                        }
                        observableEmitter.onNext(response);
                        if (RemoteNativeRxRouter.Companion.isSubscription(request2)) {
                            return;
                        }
                        observableEmitter.onComplete();
                    }
                }, new g() { // from class: com.spotify.cosmos.servicebasedrouter.RemoteNativeRxRouter$resolve$1$callbackReceiver$2
                    @Override // io.reactivex.rxjava3.functions.g
                    public final void accept(Throwable th) {
                        if (observableEmitter.isDisposed()) {
                            return;
                        }
                        ObservableEmitter<Response> observableEmitter2 = observableEmitter;
                        if (th == null) {
                            throw new IllegalStateException("Required value was null.".toString());
                        }
                        observableEmitter2.onError(th);
                    }
                });
                if (RemoteNativeRxRouter.this.router == null) {
                    throw new IllegalStateException("Required value was null.".toString());
                }
                RemoteNativeRouter remoteNativeRouter = RemoteNativeRxRouter.this.router;
                String action = request.getAction();
                if (action == null) {
                    throw new IllegalStateException("Required value was null.".toString());
                }
                String uri = request.getUri();
                if (uri == null) {
                    throw new IllegalStateException("Required value was null.".toString());
                }
                final Lifetime lifetimeResolve = remoteNativeRouter.resolve(action, uri, request.getHeaders(), request.getBody(), resolverCallbackReceiverForAny);
                observableEmitter.setCancellable(new f() { // from class: com.spotify.cosmos.servicebasedrouter.RemoteNativeRxRouter.resolve.1.1
                    @Override // io.reactivex.rxjava3.functions.f
                    public final void cancel() {
                        lifetimeResolve.release();
                    }
                });
            }
        });
        v03.i(observableCreate, "override fun resolve(req…)\n            }\n        )");
        return observableCreate;
    }

    public RemoteNativeRxRouter(RemoteNativeRouter remoteNativeRouter) {
        this(remoteNativeRouter, null);
    }
}
