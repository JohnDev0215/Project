package p;

import com.spotify.connectivity.productstate.RxProductState;
import com.spotify.player.legacyplayer.LoggingParameters;
import com.spotify.player.legacyplayer.PlayOptions$AudioStream;
import com.spotify.player.legacyplayer.PlayOptions$Operation;
import com.spotify.player.legacyplayer.PlayOptions$Trigger;
import com.spotify.player.model.AudioStream;
import com.spotify.player.model.Context;
import com.spotify.player.model.ContextPage;
import com.spotify.player.model.ContextTrack;
import com.spotify.player.model.PlayOrigin;
import com.spotify.player.model.Restrictions;
import com.spotify.player.model.Suppressions;
import com.spotify.player.model.command.options.PlayerOptionOverrides;
import com.spotify.player.model.command.options.PrefetchLevel;
import com.spotify.player.model.command.options.PreparePlayOptions;
import com.spotify.player.model.command.options.SkipToTrack;
import com.squareup.moshi.f;
import java.lang.reflect.Type;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class w20 implements f.e {
    @Override // com.squareup.moshi.f.e
    public final com.squareup.moshi.f a(Type type, Set set, com.squareup.moshi.o oVar) {
        v03.j(type, RxProductState.Keys.KEY_TYPE);
        v03.j(set, "annotations");
        v03.j(oVar, "moshi");
        Class<?> clsG = zo2.g(type);
        com.squareup.moshi.f j20Var = Context.class.isAssignableFrom(clsG) ? new j20(oVar) : wt1.class.isAssignableFrom(clsG) ? new m20(oVar, 3) : Restrictions.class.isAssignableFrom(clsG) ? new t20(oVar) : iu1.class.isAssignableFrom(clsG) ? new m20(oVar, 8) : ContextPage.class.isAssignableFrom(clsG) ? new k20(oVar) : ContextTrack.class.isAssignableFrom(clsG) ? new l20(oVar) : xt1.class.isAssignableFrom(clsG) ? new m20(oVar, 4) : PreparePlayOptions.class.isAssignableFrom(clsG) ? new s20(oVar) : pt1.class.isAssignableFrom(clsG) ? new m20(oVar, 1) : SkipToTrack.class.isAssignableFrom(clsG) ? new u20(oVar) : rt1.class.isAssignableFrom(clsG) ? new m20(oVar, 2) : v03.b(PlayOptions$Trigger.class, clsG) ? new i20(3) : v03.b(AudioStream.class, clsG) ? new i20(1) : v03.b(PlayOptions$AudioStream.class, clsG) ? new i20(0) : v03.b(PlayOptions$Operation.class, clsG) ? new i20(2) : PlayerOptionOverrides.class.isAssignableFrom(clsG) ? new q20(oVar) : b.class.isAssignableFrom(clsG) ? new m20(oVar, 6) : Suppressions.class.isAssignableFrom(clsG) ? new m20(oVar, 11) : nu1.class.isAssignableFrom(clsG) ? new m20(oVar, 9) : PlayOrigin.class.isAssignableFrom(clsG) ? new p20(oVar) : LoggingParameters.class.isAssignableFrom(clsG) ? new m20(oVar, 0) : a.class.isAssignableFrom(clsG) ? new m20(oVar, 5) : hu1.class.isAssignableFrom(clsG) ? new m20(oVar, 7) : PrefetchLevel.class.isAssignableFrom(clsG) ? new m20(oVar, 10) : null;
        if (j20Var != null) {
            return j20Var.nullSafe();
        }
        return null;
    }
}
