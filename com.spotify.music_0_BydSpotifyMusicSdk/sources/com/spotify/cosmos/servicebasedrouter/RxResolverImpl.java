package com.spotify.cosmos.servicebasedrouter;

import com.spotify.cosmos.cosmos.Lifetime;
import com.spotify.cosmos.cosmos.Request;
import com.spotify.cosmos.cosmos.Response;
import com.spotify.cosmos.routercallback.ResolverCallbackReceiver;
import com.spotify.cosmos.rxrouter.RxRouter;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.functions.f;
import io.reactivex.rxjava3.functions.g;
import io.reactivex.rxjava3.functions.o;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import p.v03;
import p.vg2;
import p.xy1;
import p.zg2;

/* JADX INFO: loaded from: classes.dex */
public final class RxResolverImpl implements RxRouter {
    public static final Companion Companion = new Companion(null);
    private final Scheduler ioScheduler;
    private final xy1 router;
    private zg2 subscriptionTracker;
    private final xy1 subscriptionTrackerProvider;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Observable<Response> performRequest(final RemoteNativeRouter remoteNativeRouter, final Request request) {
            final boolean zEquals = Request.SUB.equals(request.getAction());
            Observable<Response> observableCreate = Observable.create(new ObservableOnSubscribe() { // from class: com.spotify.cosmos.servicebasedrouter.RxResolverImpl$Companion$performRequest$1
                @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
                public final void subscribe(final ObservableEmitter<Response> observableEmitter) {
                    RemoteNativeRouter remoteNativeRouter2 = remoteNativeRouter;
                    String action = request.getAction();
                    if (action == null) {
                        throw new IllegalStateException("Required value was null.".toString());
                    }
                    String uri = request.getUri();
                    if (uri == null) {
                        throw new IllegalStateException("Required value was null.".toString());
                    }
                    Map<String, String> headers = request.getHeaders();
                    byte[] body = request.getBody();
                    ResolverCallbackReceiver.Companion companion = ResolverCallbackReceiver.Companion;
                    final boolean z = zEquals;
                    final Lifetime lifetimeResolve = remoteNativeRouter2.resolve(action, uri, headers, body, companion.forAny(null, new g() { // from class: com.spotify.cosmos.servicebasedrouter.RxResolverImpl$Companion$performRequest$1$lifetime$1
                        @Override // io.reactivex.rxjava3.functions.g
                        public final void accept(Response response) {
                            v03.j(response, "response");
                            if (observableEmitter.isDisposed()) {
                                return;
                            }
                            observableEmitter.onNext(response);
                            if (z) {
                                return;
                            }
                            observableEmitter.onComplete();
                        }
                    }, new g() { // from class: com.spotify.cosmos.servicebasedrouter.RxResolverImpl$Companion$performRequest$1$lifetime$2
                        @Override // io.reactivex.rxjava3.functions.g
                        public final void accept(Throwable th) {
                            if (observableEmitter.isDisposed()) {
                                return;
                            }
                            observableEmitter.onError(th);
                        }
                    }));
                    observableEmitter.setCancellable(new f() { // from class: com.spotify.cosmos.servicebasedrouter.RxResolverImpl$Companion$performRequest$1.1
                        @Override // io.reactivex.rxjava3.functions.f
                        public final void cancel() {
                            lifetimeResolve.release();
                        }
                    });
                }
            });
            v03.i(observableCreate, "router: RemoteNativeRout…          }\n            )");
            return observableCreate;
        }
    }

    public RxResolverImpl(xy1 xy1Var, Scheduler scheduler, xy1 xy1Var2) {
        v03.j(xy1Var, "router");
        v03.j(scheduler, "ioScheduler");
        v03.j(xy1Var2, "subscriptionTrackerProvider");
        this.router = xy1Var;
        this.ioScheduler = scheduler;
        this.subscriptionTrackerProvider = xy1Var2;
    }

    private final zg2 initSubscriptionTrackerIfNull() {
        if (this.subscriptionTracker == null) {
            this.subscriptionTracker = (zg2) this.subscriptionTrackerProvider.get();
        }
        zg2 zg2Var = this.subscriptionTracker;
        if (zg2Var != null) {
            return zg2Var;
        }
        throw new IllegalStateException("Required value was null.".toString());
    }

    private final Observable<Response> requestWithoutConnectedUpstream(final Request request) {
        Observable<Response> observableSwitchMap = ((Observable) this.router.get()).firstOrError().observeOn(this.ioScheduler).toObservable().switchMap(new o() { // from class: com.spotify.cosmos.servicebasedrouter.RxResolverImpl.requestWithoutConnectedUpstream.1
            @Override // io.reactivex.rxjava3.functions.o
            public final ObservableSource<? extends Response> apply(RemoteNativeRouter remoteNativeRouter) {
                Companion companion = RxResolverImpl.Companion;
                v03.i(remoteNativeRouter, "remoteNativeRouter");
                return companion.performRequest(remoteNativeRouter, request);
            }
        });
        v03.i(observableSwitchMap, "request: Request): Obser…eNativeRouter, request) }");
        return observableSwitchMap;
    }

    @Override // com.spotify.cosmos.rxrouter.RxRouter
    public Observable<Response> resolve(Request request) {
        v03.j(request, "request");
        zg2 zg2VarInitSubscriptionTrackerIfNull = initSubscriptionTrackerIfNull();
        String str = String.format("RxResolverImpl: %s", Arrays.copyOf(new Object[]{request}, 1));
        v03.i(str, "format(format, *args)");
        return zg2VarInitSubscriptionTrackerIfNull.b(str, requestWithoutConnectedUpstream(request));
    }

    public final List<vg2> unsubscribeAndReturnLeaks() {
        return initSubscriptionTrackerIfNull().c();
    }
}
