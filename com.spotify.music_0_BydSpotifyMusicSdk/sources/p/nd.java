package p;

import android.accounts.AccountManager;
import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import com.google.common.base.Optional;
import com.spotify.automotive.auth.model.CoreNativeResponseException;
import com.spotify.base.java.logging.Logger;
import com.spotify.cosmos.cosmonaut.Cosmonaut;
import com.spotify.cosmos.cosmos.Request;
import com.spotify.cosmos.cosmos.Response;
import com.spotify.cosmos.rxrouter.RxRouter;
import com.spotify.esperanto.esperantocosmos.CosmosTransport;
import com.spotify.music.R;
import com.spotify.player.model.PlayOrigin;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.o;
import java.util.Map;
import kotlin.collections.c;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class nd implements ul0 {
    public final /* synthetic */ int a;
    public final xy1 b;

    public /* synthetic */ nd(xy1 xy1Var, int i) {
        this.a = i;
        this.b = xy1Var;
    }

    @Override // p.xy1
    public final Object get() {
        bi1 bi1Var;
        switch (this.a) {
            case 0:
                wi0 wi0Var = (wi0) this.b.get();
                v03.j(wi0Var, "eventPublisher");
                return new id(wi0Var);
            case 1:
                AccountManager accountManager = AccountManager.get((Application) this.b.get());
                vr2.b(accountManager);
                return accountManager;
            case 2:
                return new gf2((AccountManager) this.b.get());
            case 3:
                return new fd((st1) this.b.get());
            case 4:
                return new dd((com.spotify.audiofocus.coreinternal.a) this.b.get());
            case 5:
                dd ddVar = (dd) this.b.get();
                v03.j(ddVar, "audioFocusControllerHolder");
                com.spotify.audiofocus.coreinternal.a aVar = ddVar.a;
                vr2.b(aVar);
                return aVar;
            case 6:
                return new n((Context) this.b.get());
            case 7:
                return new as2((rd1) this.b.get());
            case 8:
                pl2 pl2Var = (pl2) this.b.get();
                b7 b7Var = tk2.a;
                b7Var.c = pl2Var;
                b7Var.b.execute(new rv(19, b7Var));
                return b7Var;
            case 9:
                return new ru((wz0) this.b.get(), new com.spotify.performancesdk.androidtimekeeper.a());
            case 10:
                return new vx1((x00) this.b.get());
            case 11:
                return new yf2((sk2) this.b.get());
            case 12:
                return new ix((Context) this.b.get());
            case 13:
                return new m(this.b);
            case 14:
                Application application = (Application) this.b.get();
                synchronized (ci1.class) {
                    try {
                        if (ci1.c == null) {
                            ci1.c = new bi1(application);
                        }
                        bi1Var = ci1.c;
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                vr2.b(bi1Var);
                return bi1Var;
            case 15:
                return he1.n((Application) this.b.get());
            case 16:
                return Optional.e((s0) this.b.get());
            case 17:
                String string = ((Context) this.b.get()).getApplicationContext().getString(R.string.media_provider_authority);
                vr2.b(string);
                return string;
            case 18:
                return new n((Application) this.b.get());
            case 19:
                return r.b((Application) this.b.get());
            case 20:
                ut utVar = (ut) this.b.get();
                PlayOrigin.Builder builderA = PlayOrigin.a("aam");
                StringBuilder sb = new StringBuilder("4.4.1.0_");
                ((n) utVar).getClass();
                sb.append(kk.a);
                PlayOrigin playOriginA = builderA.f(sb.toString()).a();
                vr2.b(playOriginA);
                return playOriginA;
            case 21:
                return new e10(ua0.a(this.b));
            case 22:
                Application application2 = (Application) this.b.get();
                v03.j(application2, "context");
                return new r60(application2);
            case 23:
                RxRouter rxRouter = (RxRouter) this.b.get();
                v03.j(rxRouter, "rxRouter");
                return new zw1(new CosmosTransport(new g20(rxRouter, 0)));
            case 24:
                final zy2 zy2Var = (zy2) this.b.get();
                v03.j(zy2Var, "wrappedRxRouter");
                CoreNativeResponseException.c.getClass();
                return new RxRouter() { // from class: com.spotify.automotive.auth.model.CoreNativeResponseException$Companion$errorTransformingRouter$1
                    @Override // com.spotify.cosmos.rxrouter.RxRouter
                    public final Observable resolve(final Request request) {
                        v03.j(request, "request");
                        Observable<R> observableFlatMap = zy2Var.resolve(request).flatMap(new o() { // from class: com.spotify.automotive.auth.model.CoreNativeResponseException$Companion$errorTransformingRouter$1$resolve$1
                            @Override // io.reactivex.rxjava3.functions.o
                            public final Object apply(Object obj) {
                                Response response = (Response) obj;
                                v03.j(response, "response");
                                CoreNativeResponseException.c.getClass();
                                int status = response.getStatus();
                                Request request2 = request;
                                Map<String, String> headers = request2.getHeaders();
                                if (headers == null) {
                                    headers = c.h0();
                                }
                                Map<String, String> map = headers;
                                String str = map.get("x-bypass-error");
                                if (str == null) {
                                    str = "false";
                                }
                                if (Boolean.parseBoolean(str) || (status >= 200 && status <= 299)) {
                                    response.getUri();
                                    response.getStatus();
                                    Observable observableJust = Observable.just(response);
                                    v03.i(observableJust, "just(it)");
                                    return observableJust;
                                }
                                String uri = response.getUri();
                                String str2 = uri == null ? "" : uri;
                                Map<String, String> headers2 = response.getHeaders();
                                if (headers2 == null) {
                                    headers2 = c.h0();
                                }
                                Map<String, String> map2 = headers2;
                                String str3 = map2.get("forbidden-reasons");
                                Logger.b("Request to \"%s\" failed status=%d, reason=\"%s\" message=\"%s\" error=\"%s\" response=\"%s\" headers=\"%s\", request=\"%s\"", str2, Integer.valueOf(status), str3, map2.get("message"), map2.get("error"), response.getBodyString(), map2, request2);
                                String bodyString = response.getBodyString();
                                String action = request2.getAction();
                                Observable observableError = Observable.error(new CoreNativeResponseException(status, str2, bodyString, map2, action == null ? "" : action, map, request2.getBody(), str3));
                                v03.i(observableError, "error(\n                 …      )\n                )");
                                return observableError;
                            }
                        });
                        v03.i(observableFlatMap, "request: Request): Obser…wrap(request, response) }");
                        return observableFlatMap;
                    }
                };
            case 25:
                Cosmonaut cosmonaut = (Cosmonaut) this.b.get();
                v03.j(cosmonaut, "cosmonaut");
                vb2 vb2Var = (vb2) cosmonaut.createCosmosService(vb2.class);
                vr2.b(vb2Var);
                return vb2Var;
            case 26:
                RxRouter rxRouter2 = (RxRouter) this.b.get();
                v03.j(rxRouter2, "rxRouter");
                return new dg2(new CosmosTransport(new g20(rxRouter2, 1)));
            case 27:
                Cosmonaut cosmonaut2 = (Cosmonaut) this.b.get();
                v03.j(cosmonaut2, "cosmonaut");
                gw2 gw2Var = (gw2) cosmonaut2.createCosmosService(gw2.class);
                vr2.b(gw2Var);
                return gw2Var;
            case 28:
                Application application3 = (Application) this.b.get();
                v03.j(application3, "application");
                Resources resources = application3.getResources();
                v03.i(resources, "application.resources");
                return resources;
            default:
                return new k((Context) this.b.get());
        }
    }
}
