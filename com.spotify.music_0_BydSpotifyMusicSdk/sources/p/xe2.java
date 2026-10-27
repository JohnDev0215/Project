package p;

import android.net.Uri;
import com.spotify.voiceassistants.playermodels.SearchEndpointRequest;
import com.spotify.voiceassistants.playermodels.SpeakeasyDualResponse;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleTransformer;
import java.util.HashMap;
import java.util.Locale;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class xe2 implements pv2 {
    public final re2 a;
    public final ov2 b;
    public final Scheduler c;
    public final SingleTransformer d;
    public final SingleTransformer e;
    public final Observable f;
    public final HashMap g;

    public xe2(re2 re2Var, mk mkVar, Scheduler scheduler, od2 od2Var, od2 od2Var2, Observable observable) {
        v03.j(re2Var, "webgateService");
        v03.j(scheduler, "mainThread");
        this.a = re2Var;
        this.b = mkVar;
        this.c = scheduler;
        this.d = od2Var;
        this.e = od2Var2;
        this.f = observable;
        this.g = new HashMap(3);
    }

    public final io.reactivex.rxjava3.internal.operators.completable.b a(Uri uri, SearchEndpointRequest searchEndpointRequest) {
        Single singleFirstOrError;
        Locale locale = null;
        zn znVar = null;
        HashMap map = this.g;
        if (!map.containsKey(uri)) {
            map.put(uri, new cx1());
        }
        String string = uri.toString();
        v03.i(string, "this.toString()");
        if (ng2.g0(string, "spotify:nl:", false)) {
            String string2 = uri.toString();
            v03.i(string2, "uri.toString()");
            singleFirstOrError = Single.just(new SpeakeasyDualResponse.CanPrepareResponse.ActionNlResponse(string2));
            v03.i(singleFirstOrError, "{\n            measuremen…)\n            )\n        }");
        } else {
            singleFirstOrError = this.f.take(1L).flatMapSingle(new co(this, 9, searchEndpointRequest)).map(we2.c).firstOrError();
            v03.i(singleFirstOrError, "private fun search(uri: …    .firstOrError()\n    }");
        }
        Completable completableFlatMapCompletable = singleFirstOrError.doOnSubscribe(new du(8, null)).flatMapCompletable(new xn(this, uri, searchEndpointRequest, locale, znVar, 1));
        completableFlatMapCompletable.getClass();
        Scheduler scheduler = this.c;
        Objects.requireNonNull(scheduler, "scheduler is null");
        return new io.reactivex.rxjava3.internal.operators.completable.b(new io.reactivex.rxjava3.internal.operators.completable.j(completableFlatMapCompletable, scheduler, 0), new ve2(this, uri, 1), 2);
    }
}
