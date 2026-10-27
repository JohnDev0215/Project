package p;

import com.spotify.player.esperanto.proto.EsContextPlayerState$PlaybackQuality;
import com.spotify.player.model.BitrateLevel;
import kotlin.NoWhenBranchMatchedException;

/* JADX INFO: loaded from: classes.dex */
public abstract class mu1 {
    public static final BitrateLevel a(EsContextPlayerState$PlaybackQuality.BitrateLevel bitrateLevel) {
        int iOrdinal = bitrateLevel.ordinal();
        BitrateLevel bitrateLevel2 = BitrateLevel.c;
        switch (iOrdinal) {
            case 0:
            case 7:
                return bitrateLevel2;
            case 1:
                return BitrateLevel.e;
            case 2:
                return BitrateLevel.k;
            case 3:
                return BitrateLevel.l;
            case 4:
                return BitrateLevel.m;
            case 5:
                return BitrateLevel.n;
            case 6:
                return BitrateLevel.o;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
