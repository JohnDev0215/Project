package p;

import com.spotify.automotive.playback.ErrorState;
import com.spotify.automotive.ubi.LoggerPlaybackError;
import java.util.Map;
import kotlin.Pair;
import okhttp3.internal.Util;

/* JADX INFO: loaded from: classes.dex */
public abstract class fi1 {
    public static final Map a = Util.z(kotlin.collections.c.i0(new Pair(ErrorState.NETWORK_ERROR, LoggerPlaybackError.NETWORK_ERROR), new Pair(ErrorState.PREMIUM_IS_REQUIRED, LoggerPlaybackError.PREMIUM_IS_REQUIRED), new Pair(ErrorState.PLAYBACK_PERMANENT_ERROR, LoggerPlaybackError.PLAYBACK_PERMANENT_ERROR), new Pair(ErrorState.PLAYBACK_TEMPORARY_ERROR, LoggerPlaybackError.PLAYBACK_TEMPORARY_ERROR), new Pair(ErrorState.VOICE_FAILURE, LoggerPlaybackError.VOICE_FAILURE)));
}
