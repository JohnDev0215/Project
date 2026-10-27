package p;

import android.net.Uri;
import com.spotify.base.java.logging.Logger;
import com.spotify.voiceassistants.playermodels.CommandPlayResponse;
import com.spotify.voiceassistants.playermodels.SpeakeasyDualResponse;
import com.spotify.voiceassistants.playermodels.VoiceAssistantsPerformance;

/* JADX INFO: loaded from: classes.dex */
public final class ue2 implements io.reactivex.rxjava3.functions.o {
    public final /* synthetic */ Uri c;
    public final /* synthetic */ wk2 e;
    public final /* synthetic */ xe2 k;
    public final /* synthetic */ SpeakeasyDualResponse l;
    public final /* synthetic */ ax1 m;

    public ue2(Uri uri, wk2 wk2Var, xe2 xe2Var, SpeakeasyDualResponse speakeasyDualResponse, ax1 ax1Var) {
        this.c = uri;
        this.e = wk2Var;
        this.k = xe2Var;
        this.l = speakeasyDualResponse;
        this.m = ax1Var;
    }

    @Override // io.reactivex.rxjava3.functions.o
    public final Object apply(Object obj) {
        CommandPlayResponse commandPlayResponse = (CommandPlayResponse) obj;
        wk2 wk2Var = this.e;
        if (wk2Var != null) {
            ((c7) wk2Var).c(VoiceAssistantsPerformance.MEASURE_NETWORK_SPEAKEASY);
        }
        String error = commandPlayResponse.getError();
        Uri uri = this.c;
        xe2 xe2Var = this.k;
        if (error != null) {
            Logger.b("Received error from command/play: %s", commandPlayResponse.getError());
            xe2Var.g.remove(uri);
            mk mkVar = (mk) xe2Var.b;
            mkVar.getClass();
            return new io.reactivex.rxjava3.internal.operators.completable.e(1, new q(3, mkVar));
        }
        SpeakeasyDualResponse.FullSuccessResponse fullSuccessResponseComplete = ((SpeakeasyDualResponse.CanPrepareResponse) this.l).complete(commandPlayResponse);
        cx1 cx1Var = (cx1) xe2Var.g.get(uri);
        if (cx1Var != null) {
            cx1Var.c = fullSuccessResponseComplete;
        }
        return ((mk) xe2Var.b).b(fullSuccessResponseComplete, this.m);
    }
}
