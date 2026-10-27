package p;

import com.spotify.automotive.pathfinder.type.EpisodePlaybackState;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class yr implements y4 {
    public static final yr c = new yr();
    public static final List e = v03.C("playPositionMilliseconds", "state");

    @Override // p.y4
    public final Object a(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        EpisodePlaybackState episodePlaybackState;
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        Integer num = null;
        EpisodePlaybackState episodePlaybackState2 = null;
        while (true) {
            int iW = u71Var.W(e);
            if (iW == 0) {
                num = (Integer) c5.b.a(u71Var, cVar);
            } else {
                if (iW != 1) {
                    v03.h(num);
                    int iIntValue = num.intValue();
                    v03.h(episodePlaybackState2);
                    return new nr(iIntValue, episodePlaybackState2);
                }
                String strU = u71Var.u();
                v03.h(strU);
                EpisodePlaybackState[] episodePlaybackStateArrValues = EpisodePlaybackState.values();
                int length = episodePlaybackStateArrValues.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        episodePlaybackState = null;
                        break;
                    }
                    episodePlaybackState = episodePlaybackStateArrValues[i];
                    if (v03.b(episodePlaybackState.a(), strU)) {
                        break;
                    }
                    i++;
                }
                episodePlaybackState2 = episodePlaybackState == null ? EpisodePlaybackState.UNKNOWN__ : episodePlaybackState;
            }
        }
    }
}
