package com.spotify.cosmos.servicebasedrouter;

import com.spotify.base.java.logging.Logger;
import com.spotify.cosmos.cosmos.Request;
import com.spotify.cosmos.cosmos.Response;
import com.spotify.cosmos.rxrouter.RxRouter;
import com.spotify.cosmos.servicebasedrouter.ScopedRouter;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.f;
import io.reactivex.rxjava3.functions.g;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.operators.observable.m3;
import io.reactivex.rxjava3.observables.a;
import java.util.Set;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Ref$BooleanRef;
import p.lq2;
import p.ou;
import p.pa0;
import p.py0;
import p.ry0;
import p.ux2;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class ScopedRouter implements RxRouter {
    public static final Companion Companion = new Companion(null);
    private static final Set<String> IDEMPOTENT_ACTIONS = ux2.z(Request.GET, Request.SUB);
    private final Observable<RouterState> routerStateObservable;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Set<String> getIDEMPOTENT_ACTIONS() {
            return ScopedRouter.IDEMPOTENT_ACTIONS;
        }
    }

    public interface RouterState {

        public static final class Available implements RouterState {
            private final RxRouter router;

            public Available(RxRouter rxRouter) {
                v03.j(rxRouter, "router");
                this.router = rxRouter;
            }

            public final RxRouter getRouter() {
                return this.router;
            }
        }

        public static final class NotAvailable implements RouterState {
            public static final NotAvailable INSTANCE = new NotAvailable();

            private NotAvailable() {
            }
        }
    }

    public ScopedRouter(ScopeConnector scopeConnector) {
        v03.j(scopeConnector, "scopeConnector");
        a aVarReplay = routerState(scopeConnector).replay(1);
        aVarReplay.getClass();
        this.routerStateObservable = new m3(aVarReplay);
    }

    private final Observable<RouterState> routerState(final ScopeConnector scopeConnector) {
        return Observable.create(new ObservableOnSubscribe() { // from class: com.spotify.cosmos.servicebasedrouter.ScopedRouter.routerState.1
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            public final void subscribe(final ObservableEmitter<RouterState> observableEmitter) {
                observableEmitter.setDisposable(scopeConnector.subscribe(new ry0() { // from class: com.spotify.cosmos.servicebasedrouter.ScopedRouter$routerState$1$disposable$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // p.ry0
                    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                        invoke((RxRouter) obj);
                        return lq2.a;
                    }

                    public final void invoke(RxRouter rxRouter) {
                        v03.j(rxRouter, "router");
                        observableEmitter.onNext(new ScopedRouter.RouterState.Available(rxRouter));
                    }
                }, new py0() { // from class: com.spotify.cosmos.servicebasedrouter.ScopedRouter$routerState$1$disposable$2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // p.py0
                    public /* bridge */ /* synthetic */ Object invoke() {
                        m194invoke();
                        return lq2.a;
                    }

                    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                    public final void m194invoke() {
                        observableEmitter.onNext(ScopedRouter.RouterState.NotAvailable.INSTANCE);
                    }
                }));
            }
        });
    }

    @Override // com.spotify.cosmos.rxrouter.RxRouter
    public Observable<Response> resolve(final Request request) {
        v03.j(request, "request");
        final boolean Z = ou.Z(IDEMPOTENT_ACTIONS, request.getAction());
        Observable<Response> observableCreate = Observable.create(new ObservableOnSubscribe() { // from class: com.spotify.cosmos.servicebasedrouter.ScopedRouter.resolve.1
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            public final void subscribe(final ObservableEmitter<Response> observableEmitter) {
                final pa0 pa0Var = new pa0();
                final Ref$BooleanRef ref$BooleanRef = new Ref$BooleanRef();
                ref$BooleanRef.element = true;
                Observable observable = ScopedRouter.this.routerStateObservable;
                final Request request2 = request;
                final boolean z = Z;
                final Disposable disposableSubscribe = observable.subscribe(new g() { // from class: com.spotify.cosmos.servicebasedrouter.ScopedRouter$resolve$1$routerDisposable$1
                    @Override // io.reactivex.rxjava3.functions.g
                    public final void accept(ScopedRouter.RouterState routerState) {
                        if (!(routerState instanceof ScopedRouter.RouterState.Available)) {
                            if (v03.b(routerState, ScopedRouter.RouterState.NotAvailable.INSTANCE)) {
                                DisposableHelper.d(pa0Var.a.c, null);
                                return;
                            }
                            return;
                        }
                        if (ref$BooleanRef.element) {
                            pa0 pa0Var2 = pa0Var;
                            Observable<Response> observableResolve = ((ScopedRouter.RouterState.Available) routerState).getRouter().resolve(request2);
                            final Request request3 = request2;
                            final ObservableEmitter<Response> observableEmitter2 = observableEmitter;
                            g gVar = new g() { // from class: com.spotify.cosmos.servicebasedrouter.ScopedRouter$resolve$1$routerDisposable$1.1
                                @Override // io.reactivex.rxjava3.functions.g
                                public final void accept(Response response) {
                                    v03.i(response, "response");
                                    if (!ScopedRouterKt.isSuccessful(response)) {
                                        Logger.a("error processing response for " + request3 + ", response: " + response, new Object[0]);
                                    }
                                    observableEmitter2.onNext(response);
                                }
                            };
                            final ObservableEmitter<Response> observableEmitter3 = observableEmitter;
                            v03.i(observableEmitter3, "emitter");
                            g gVar2 = new g() { // from class: com.spotify.cosmos.servicebasedrouter.ScopedRouter$resolve$1$routerDisposable$1.2
                                @Override // io.reactivex.rxjava3.functions.g
                                public final void accept(Throwable th) {
                                    observableEmitter3.onError(th);
                                }
                            };
                            final ObservableEmitter<Response> observableEmitter4 = observableEmitter;
                            v03.i(observableEmitter4, "emitter");
                            DisposableHelper.d(pa0Var2.a.c, observableResolve.subscribe(gVar, gVar2, new io.reactivex.rxjava3.functions.a() { // from class: com.spotify.cosmos.servicebasedrouter.ScopedRouter$resolve$1$routerDisposable$1.3
                                @Override // io.reactivex.rxjava3.functions.a
                                public final void run() {
                                    observableEmitter4.onComplete();
                                }
                            }));
                            ref$BooleanRef.element = z;
                        }
                    }
                });
                observableEmitter.setCancellable(new f() { // from class: com.spotify.cosmos.servicebasedrouter.ScopedRouter.resolve.1.1
                    @Override // io.reactivex.rxjava3.functions.f
                    public final void cancel() {
                        disposableSubscribe.dispose();
                        DisposableHelper.d(pa0Var.a.c, null);
                    }
                });
            }
        });
        v03.i(observableCreate, "override fun resolve(req…        }\n        }\n    }");
        return observableCreate;
    }
}
