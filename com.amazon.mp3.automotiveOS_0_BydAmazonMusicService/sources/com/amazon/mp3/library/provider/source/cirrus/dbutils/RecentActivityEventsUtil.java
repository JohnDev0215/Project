package com.amazon.mp3.library.provider.source.cirrus.dbutils;

import android.net.Uri;
import com.amazon.mp3.library.provider.MediaProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RecentActivityRepositoryEvents.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\t"}, d2 = {"Lcom/amazon/mp3/library/provider/source/cirrus/dbutils/RecentActivityEventsUtil;", "", "()V", "getRecentActivityUpdateEventType", "Lcom/amazon/mp3/library/provider/source/cirrus/dbutils/RecentActivityUpdateEventType;", "contentUri", "Landroid/net/Uri;", "sendEvent", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RecentActivityEventsUtil {
    public static final RecentActivityEventsUtil INSTANCE = new RecentActivityEventsUtil();

    private RecentActivityEventsUtil() {
    }

    private final RecentActivityUpdateEventType getRecentActivityUpdateEventType(Uri contentUri) {
        if (MediaProvider.PrimePlaylists.isPrimePlaylist(contentUri) || MediaProvider.SharedUserPlaylists.isSharedUserPlaylist(contentUri)) {
            return RecentActivityUpdateEventType.PRIME_PLAYLIST;
        }
        if (MediaProvider.Playlists.isPlaylist(contentUri)) {
            return RecentActivityUpdateEventType.UDO_PLAYLIST;
        }
        return null;
    }

    public final void sendEvent(Uri contentUri) {
        Intrinsics.checkNotNullParameter(contentUri, "contentUri");
        RecentActivityUpdateEventType recentActivityUpdateEventType = getRecentActivityUpdateEventType(contentUri);
        if (recentActivityUpdateEventType == null) {
            return;
        }
        LocalRepositoryEvents.INSTANCE.sendEvent(new RecentActivityUpdateEvent(recentActivityUpdateEventType));
    }
}
