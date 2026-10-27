package com.spotify.automotive.auth;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import com.spotify.automotive.auth.model.CoreNativeResponseException;
import com.spotify.automotive.auth.model.LoginCredentials;
import com.spotify.automotive.auth.model.LoginOptions;
import com.spotify.automotive.auth.model.LoginRequest;
import com.spotify.automotive.auth.model.LoginResponse;
import com.spotify.base.java.logging.Logger;
import com.spotify.connectivity.connectiontype.ConnectionApis;
import com.spotify.cosmos.cosmonaut.TypedResponse;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.internal.functions.k;
import io.reactivex.rxjava3.internal.operators.completable.d;
import io.reactivex.rxjava3.internal.operators.completable.l;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import p.ad1;
import p.bd1;
import p.c7;
import p.cd1;
import p.dd1;
import p.ed1;
import p.fd1;
import p.gd1;
import p.hd1;
import p.j;
import p.lj;
import p.qm;
import p.rm;
import p.sd1;
import p.sk2;
import p.sm;
import p.td1;
import p.ut;
import p.vb2;

/* JADX INFO: loaded from: classes.dex */
public final class b implements ad1 {
    public final Context a;
    public final ut b;
    public final vb2 c;
    public final td1 d;
    public final qm e;
    public final j f;
    public final sk2 g;
    public final ConnectionApis h;
    public Disposable i = Disposable.e(k.b);
    public Disposable j;
    public Disposable k;

    public b(Context context, ut utVar, vb2 vb2Var, qm qmVar, td1 td1Var, j jVar, sk2 sk2Var, ConnectionApis connectionApis) {
        EmptyDisposable emptyDisposable = EmptyDisposable.c;
        this.j = emptyDisposable;
        this.k = emptyDisposable;
        this.a = context;
        this.b = utVar;
        this.c = vb2Var;
        this.d = td1Var;
        this.e = qmVar;
        this.f = jVar;
        this.g = sk2Var;
        this.h = connectionApis;
    }

    public final l a(LoginState loginState) {
        Logger.e("Logging out because of state: %s", loginState.toString());
        if (!this.j.isDisposed()) {
            this.j.dispose();
        }
        int i = 0;
        Completable completableSwitchMapCompletable = ((sd1) this.d).a.distinctUntilChanged().filter(new lj(0)).take(1L).timeout(5L, TimeUnit.SECONDS, Observable.just(LoginState.e)).switchMapCompletable(new dd1(i, this));
        ed1 ed1Var = new ed1(0);
        completableSwitchMapCompletable.getClass();
        return new l(new io.reactivex.rxjava3.internal.operators.completable.b(completableSwitchMapCompletable, ed1Var, 2), k.d, new fd1(this, i, loginState));
    }

    public final void b(Throwable th) {
        try {
            if ((th instanceof CoreNativeResponseException) && ((CoreNativeResponseException) th).c()) {
                ConnectivityManager connectivityManager = (ConnectivityManager) this.a.getSystemService("connectivity");
                Network activeNetwork = connectivityManager.getActiveNetwork();
                Logger.b("Active network: %s", activeNetwork);
                if (activeNetwork != null) {
                    Logger.b("Active Network Capabilities: %s", connectivityManager.getNetworkCapabilities(activeNetwork));
                    Logger.b("Active Mapped ConnectionType: %s", this.h.getConnectionType());
                }
                HashMap map = new HashMap(5);
                for (Network network : connectivityManager.getAllNetworks()) {
                    map.put(network, connectivityManager.getNetworkCapabilities(network));
                }
                Logger.b("All available networks: %s", map);
            }
        } catch (Exception e) {
            Logger.c(e, "Failed to log network state", new Object[0]);
        }
    }

    public final Observable c(LoginCredentials loginCredentials, boolean z, c7 c7Var) {
        int i = 0;
        if (z) {
            Logger.e("Attempting to log in when already logged in, will log out first.", new Object[0]);
        }
        CompletableSource completableSourceA = z ? a(LoginState.k) : d.c;
        int i2 = 1;
        bd1 bd1Var = new bd1(this, 1);
        completableSourceA.getClass();
        l lVar = new l(completableSourceA, k.d, bd1Var);
        LoginRequest.c.getClass();
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.a = loginCredentials;
        int i3 = 2;
        loginRequest.b = new LoginOptions(true, false, 2, null);
        AtomicInteger atomicInteger = new AtomicInteger();
        c7Var.g("login_with_credentials");
        c7Var.g("cosmos-service-request");
        vb2 vb2Var = this.c;
        Single<TypedResponse<LoginResponse>> singleDoOnSuccess = vb2Var.a(loginRequest).doOnSuccess(new a(i3, c7Var));
        dd1 dd1Var = new dd1(i2, vb2Var);
        sm smVar = (sm) this.e;
        smVar.getClass();
        Observable observable = singleDoOnSuccess.flatMap(new rm(smVar, i, dd1Var)).map(new gd1(this, c7Var, i2)).retryWhen(new cd1(this, atomicInteger, 0)).onErrorResumeNext(new gd1(this, c7Var, i3)).doFinally(new hd1(this, c7Var, 1)).toObservable();
        Observable observableJust = Observable.just(LoginState.c);
        Objects.requireNonNull(observable, "next is null");
        return observableJust.concatWith(new io.reactivex.rxjava3.internal.operators.mixed.a(lVar, i, observable)).doOnDispose(new bd1(this, 2));
    }

    public final void d(LoginState loginState) {
        td1 td1Var = this.d;
        sd1 sd1Var = (sd1) td1Var;
        Logger.e("Login state updated: %s (was: %s)", loginState, sd1Var.c);
        sd1Var.c = loginState;
        sd1Var.a.onNext(loginState);
        if (loginState == LoginState.e && this.j.isDisposed()) {
            Observable observableDoOnNext = this.c.currentSession().map(new ed1(1)).distinctUntilChanged().filter(new lj(1)).map(new ed1(2)).onErrorResumeWith(Observable.just(LoginState.k)).doOnNext(new ed1(0));
            Objects.requireNonNull(td1Var);
            this.j = observableDoOnNext.subscribe(new a(1, td1Var));
        }
    }
}
