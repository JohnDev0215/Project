package p;

import android.net.Uri;
import com.spotify.voiceassistants.playermodels.CommandPlayRequest;
import com.spotify.voiceassistants.playermodels.SearchEndpointRequest;
import com.spotify.voiceassistants.playermodels.SpeakeasyDualResponse;
import io.reactivex.rxjava3.core.Completable;
import java.util.HashMap;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class xn implements io.reactivex.rxjava3.functions.o {
    public final /* synthetic */ int c;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Comparable k;
    public final /* synthetic */ Object l;
    public final /* synthetic */ Object m;
    public final /* synthetic */ Object n;

    public /* synthetic */ xn(Object obj, Comparable comparable, Object obj2, Locale locale, zn znVar, int i) {
        this.c = i;
        this.e = obj;
        this.k = comparable;
        this.l = obj2;
        this.m = locale;
        this.n = znVar;
    }

    @Override // io.reactivex.rxjava3.functions.o
    public final Object apply(Object obj) {
        int i = this.c;
        Object obj2 = this.n;
        Object obj3 = this.m;
        Object obj4 = this.l;
        Comparable comparable = this.k;
        Object obj5 = this.e;
        switch (i) {
            case 0:
                rn rnVar = (rn) obj;
                v03.j(rnVar, "resolver");
                if2 if2Var = (if2) obj5;
                String str = (String) comparable;
                return rnVar.c(if2Var, str, (Integer) obj4, (Locale) obj3).doOnError(new tn((zn) obj2, if2Var, str));
            default:
                SpeakeasyDualResponse speakeasyDualResponse = (SpeakeasyDualResponse) obj;
                v03.j(speakeasyDualResponse, "response");
                xe2 xe2Var = (xe2) obj5;
                Uri uri = (Uri) comparable;
                cx1 cx1Var = (cx1) xe2Var.g.get(uri);
                if (cx1Var != null) {
                    cx1Var.c = speakeasyDualResponse;
                }
                SearchEndpointRequest searchEndpointRequest = (SearchEndpointRequest) obj4;
                wk2 wk2Var = (wk2) obj2;
                HashMap map = xe2Var.g;
                ov2 ov2Var = xe2Var.b;
                ax1 ax1Var = new ax1(uri, searchEndpointRequest, map, ov2Var, (sk2) obj3, wk2Var);
                if (!(speakeasyDualResponse instanceof SpeakeasyDualResponse.CanPrepareResponse)) {
                    return ((mk) ov2Var).b(speakeasyDualResponse, ax1Var);
                }
                Completable completableFlatMapCompletable = xe2Var.a.a.b(new CommandPlayRequest(((SpeakeasyDualResponse.CanPrepareResponse) speakeasyDualResponse).getActionNlUri(), searchEndpointRequest.getSourceDevice().getDeviceType(), false, 4, null)).compose(xe2Var.e).flatMapCompletable(new ue2(uri, wk2Var, xe2Var, speakeasyDualResponse, ax1Var));
                ve2 ve2Var = new ve2(xe2Var, uri, 0);
                completableFlatMapCompletable.getClass();
                return new io.reactivex.rxjava3.internal.operators.completable.b(completableFlatMapCompletable, ve2Var, 2);
        }
    }
}
