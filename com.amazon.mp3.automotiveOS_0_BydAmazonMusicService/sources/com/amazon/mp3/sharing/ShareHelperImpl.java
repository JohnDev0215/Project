package com.amazon.mp3.sharing;

import android.content.Context;
import com.amazon.mp3.library.service.sync.PlaylistSyncHelper;
import com.amazon.mp3.net.AbstractHttpClient;
import com.amazon.mp3.net.service.ServiceException;
import com.amazon.music.media.playback.util.ThreadUtils;
import com.amazon.music.share.ShareHelper;
import java.util.HashSet;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ShareHelperImpl.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"Lcom/amazon/mp3/sharing/ShareHelperImpl;", "Lcom/amazon/music/share/ShareHelper;", "()V", "syncUserPlaylistData", "", "context", "Landroid/content/Context;", "playlistId", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ShareHelperImpl implements ShareHelper {
    @Override // com.amazon.music.share.ShareHelper
    public void syncUserPlaylistData(final Context context, final String playlistId) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(playlistId, "playlistId");
        final HashSet hashSet = new HashSet();
        ThreadUtils.postInBackground(new Runnable() { // from class: com.amazon.mp3.sharing.ShareHelperImpl$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() throws ServiceException, AbstractHttpClient.HttpClientException {
                ShareHelperImpl.m1626syncUserPlaylistData$lambda0(hashSet, playlistId, context);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: syncUserPlaylistData$lambda-0, reason: not valid java name */
    public static final void m1626syncUserPlaylistData$lambda0(HashSet playlistIdSet, String playlistId, Context context) throws ServiceException, AbstractHttpClient.HttpClientException {
        Intrinsics.checkNotNullParameter(playlistIdSet, "$playlistIdSet");
        Intrinsics.checkNotNullParameter(playlistId, "$playlistId");
        Intrinsics.checkNotNullParameter(context, "$context");
        playlistIdSet.add(playlistId);
        PlaylistSyncHelper.syncUpdatedPlaylist(context, playlistIdSet, null, null, true);
    }
}
