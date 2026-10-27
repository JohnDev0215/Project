package com.spotify.pinpairing.pinpairingimpl;

import com.spotify.automotive.ubi.LoginUbiLogger$ErrorInfo;
import com.spotify.base.java.logging.Logger;
import com.spotify.connectivity.productstate.RxProductState;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.g;
import io.reactivex.rxjava3.schedulers.e;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import p.at1;
import p.it1;
import p.od1;
import p.pd1;
import p.v03;
import p.ys1;
import p.zs1;

/* JADX INFO: loaded from: classes.dex */
public final class c implements zs1 {
    public final com.google.gson.a a;
    public final OkHttpClient b;
    public final ArrayList c;
    public final io.reactivex.rxjava3.disposables.a d;
    public final String e;
    public final String f;
    public final Scheduler g;
    public final Scheduler h;
    public final String i;
    public final String j;

    public c() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Scheduler schedulerA = io.reactivex.rxjava3.android.schedulers.c.a();
        Scheduler scheduler = e.c;
        this.a = new com.google.gson.a();
        this.c = new ArrayList();
        this.d = new io.reactivex.rxjava3.disposables.a();
        this.b = okHttpClient;
        this.e = "1fd85409eb8e441fb783f60caf2fdaab";
        this.f = RxProductState.Keys.KEY_STREAMING;
        this.g = schedulerA;
        this.h = scheduler;
        this.i = "https://accounts.spotify.com/oauth2/device/authorize";
        this.j = "https://accounts.spotify.com/api/token";
    }

    public final void a(Throwable th) {
        this.d.d();
        if (th instanceof IOException) {
        } else {
            new IOException(th);
        }
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            try {
                pd1 pd1Var = (pd1) ((ys1) it.next());
                pd1Var.g.v(pd1Var.h, LoginUbiLogger$ErrorInfo.GENERAL);
                pd1Var.b.post(new od1(pd1Var, 0));
            } catch (Exception e) {
                Logger.c(e, "Exception raised by Listener.onFailure.", new Object[0]);
            }
        }
    }

    public final IOException b(ResponseBody responseBody) throws InvalidObjectException {
        String strA;
        if (responseBody == null) {
            throw new InvalidObjectException("Response is null.");
        }
        String strQ = responseBody.q();
        if (v03.y(strQ)) {
            throw new InvalidObjectException("Response is empty.");
        }
        strA = ((OAuthTokenErrorResponse) this.a.b(OAuthTokenErrorResponse.class, strQ)).a();
        int i = it1.a;
        if (strA == null) {
            strA = "";
        }
        switch (strA) {
            case "authorization_pending":
                return new RetryException();
            case "access_denied":
                return new AccessDeniedException();
            case "slow_down":
                return new SlowDownException();
            case "expired_token":
            case "invalid_request":
                Logger.b("Polling request failed because the user code was not found or expired.", new Object[0]);
                return new UserCodeExpiredException();
            default:
                String str = String.format("Polling for token failed with unknown error \"%s\".", strA);
                Logger.b(str, new Object[0]);
                return new IOException(str);
        }
    }

    public final void c() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.a("client_id", this.e);
        builder.a("scope", this.f);
        FormBody formBody = new FormBody(builder.b, builder.c);
        Request.Builder builder2 = new Request.Builder();
        builder2.h(this.i);
        builder2.f(com.spotify.cosmos.cosmos.Request.POST, formBody);
        int i = 0;
        this.d.c(Single.create(new b(this, builder2.b(), i)).map(new at1(this, i)).subscribeOn(this.h).observeOn(this.g).doOnSuccess(new at1(this, i)).subscribe(new at1(this, 1), new at1(this, 2)));
    }

    public final void d(final StartFlowResponseV2 startFlowResponseV2, final int i) {
        FormBody.Builder builder = new FormBody.Builder();
        builder.a("client_id", this.e);
        builder.a("device_code", startFlowResponseV2.a());
        builder.a("grant_type", "urn:ietf:params:oauth:grant-type:device_code");
        builder.a("scope", this.f);
        FormBody formBody = new FormBody(builder.b, builder.c);
        Request.Builder builder2 = new Request.Builder();
        builder2.h(this.j);
        builder2.f(com.spotify.cosmos.cosmos.Request.POST, formBody);
        int i2 = 1;
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        Scheduler scheduler = this.h;
        this.d.c(Single.create(new b(this, builder2.b(), i2)).map(new at1(this, i2)).delaySubscription(i, timeUnit, scheduler).subscribeOn(scheduler).observeOn(this.g).subscribe(new at1(this, 3), new g() { // from class: com.spotify.pinpairing.pinpairingimpl.a
            @Override // io.reactivex.rxjava3.functions.g
            public final void accept(Object obj) {
                Throwable th = (Throwable) obj;
                c cVar = this.c;
                cVar.getClass();
                boolean z = th instanceof RetryException;
                StartFlowResponseV2 startFlowResponseV22 = startFlowResponseV2;
                int i3 = i;
                if (z) {
                    cVar.d(startFlowResponseV22, i3);
                    return;
                }
                if (th instanceof SlowDownException) {
                    cVar.d(startFlowResponseV22, i3 + 5000);
                } else if (th instanceof UserCodeExpiredException) {
                    cVar.c();
                } else {
                    cVar.a(th);
                }
            }
        }));
    }
}
