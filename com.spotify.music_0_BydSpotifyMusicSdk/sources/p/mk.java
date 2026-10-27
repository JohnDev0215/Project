package p;

import android.net.Uri;
import com.spotify.automotive.ubi.Referrer;
import com.spotify.base.java.logging.Logger;
import com.spotify.player.model.Context;
import com.spotify.voiceassistants.playermodels.CommandPlayResponse;
import com.spotify.voiceassistants.playermodels.SpeakeasyDualResponse;
import com.spotify.voiceassistants.playermodels.VoiceAssistantsPerformance;
import io.reactivex.rxjava3.core.Completable;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class mk implements ov2 {
    public final st1 a;
    public final jf0 b;

    public mk(st1 st1Var, jf0 jf0Var) {
        this.a = st1Var;
        this.b = jf0Var;
    }

    public final Completable a(Object obj, Object obj2) {
        final CommandPlayResponse commandPlayResponse;
        final Context context;
        SpeakeasyDualResponse speakeasyDualResponse = (SpeakeasyDualResponse) obj2;
        if (speakeasyDualResponse instanceof SpeakeasyDualResponse.FullSuccessResponse) {
            commandPlayResponse = ((SpeakeasyDualResponse.FullSuccessResponse) speakeasyDualResponse).getCommandPlayResponse();
            context = commandPlayResponse.getContext();
        } else {
            commandPlayResponse = null;
            context = null;
        }
        if (context != null) {
            return new io.reactivex.rxjava3.internal.operators.completable.e(1, new io.reactivex.rxjava3.functions.a() { // from class: p.lk
                @Override // io.reactivex.rxjava3.functions.a
                public final void run() {
                    mk mkVar = this.c;
                    mkVar.getClass();
                    mkVar.a.b(context.h(), commandPlayResponse.getPlayOptions(), Referrer.VOICE.a());
                }
            });
        }
        lc.n("No context to play after a voice command.");
        return io.reactivex.rxjava3.internal.operators.completable.d.c;
    }

    public final Completable b(Object obj, ax1 ax1Var) {
        sk2 sk2Var;
        Map map = ax1Var.c;
        Uri uri = ax1Var.a;
        cx1 cx1Var = (cx1) map.get(uri);
        if (cx1Var == null) {
            Logger.g("onPreparePlaySuccess called without a corresponding pending search response for uri = %s.", uri);
            return new io.reactivex.rxjava3.internal.operators.completable.e(1, new q(5, ax1Var));
        }
        wk2 wk2Var = ax1Var.f;
        if (wk2Var != null && (sk2Var = ax1Var.e) != null) {
            c7 c7Var = (c7) wk2Var;
            c7Var.c(VoiceAssistantsPerformance.MEASURE_PREPARE_URI);
            ((b7) sk2Var).b(c7Var.b());
            ax1Var.f = null;
        }
        cx1Var.a = true;
        Completable completableA = io.reactivex.rxjava3.internal.operators.completable.d.c;
        if (cx1Var.b) {
            Object obj2 = cx1Var.c;
            if (obj2 != null) {
                completableA = ((mk) ax1Var.d).a(ax1Var.b, obj2);
            } else {
                Logger.b("onPreparePlaySuccess got a null search response.", new Object[0]);
            }
            map.remove(uri);
        }
        return completableA;
    }
}
