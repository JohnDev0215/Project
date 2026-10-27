package com.amazon.podcast.cast;

import com.amazon.music.casting.CastingCategory;
import com.amazon.music.casting.session.CastingSessionManager;
import com.amazon.music.metrics.mts.event.types.TerminationReason;
import com.amazon.podcast.Podcast;

/* JADX INFO: loaded from: classes5.dex */
public final class Casting {
    public static boolean isCasting() {
        CastingSessionManager castingSessionManager;
        if (Podcast.isFireOS() || (castingSessionManager = CastingSessionManager.getInstance()) == null || castingSessionManager.getCastingDevice() == null) {
            return false;
        }
        if ((castingSessionManager.getCastingDevice() == null) || castingSessionManager.getCastingDevice().getCategory().equals(CastingCategory.CHROMECAST)) {
            return castingSessionManager.isInitialized() && castingSessionManager.isConnected();
        }
        castingSessionManager.disconnect(TerminationReason.CASTING);
        return false;
    }
}
