package com.amazon.mp3.detailpages.playlist.actions;

import android.content.Context;
import android.net.Uri;
import androidx.fragment.app.FragmentActivity;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.card.prime.StationUtils;
import com.amazon.mp3.cloudqueue.FreeTierPlaybackUtil;
import com.amazon.mp3.cloudqueue.model.PlayableEntityIdentifier;
import com.amazon.mp3.cloudqueue.model.PlayableEntityIdentifierType;
import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.playlist.CatalogPlaylist;
import com.amazon.mp3.playlist.CatalogPlaylistTrack;
import com.amazon.mp3.prime.upsell.nightwing.UpsellUtil;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellReason;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellSourceEntity;
import com.amazon.mp3.util.StringUtil;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaCollectionType;
import com.amazon.music.media.playback.util.Clock;
import com.amazon.music.media.playback.util.ThreadUtils;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.musicplayqueueservice.client.common.casting.Track;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: PlaylistSFAAction.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u001a\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0012\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0006\u0010\u0013\u001a\u00020\rJ\u0014\u0010\u0014\u001a\u00020\r2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0016J\u0018\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/amazon/mp3/detailpages/playlist/actions/PlaylistSFAAction;", "", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "playlistUri", "Landroid/net/Uri;", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "(Landroidx/fragment/app/FragmentActivity;Landroid/net/Uri;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;)V", "playlistTrackInfo", "Lcom/amazon/mp3/library/util/PlaylistUtil$PlaylistTrackInfo;", "kotlin.jvm.PlatformType", "startLibraryPlaylistSFA", "", "playlistAsin", "", "startFullscreen", "", "startPrimePlaylistSFA", "startPrimePlaylistSFAForFreeTier", "startSharedUserPlaylistSFA", "trackAsinList", "", "startSharedUserPlaylistSFAForFreeTier", "context", "Landroid/content/Context;", Environment.PLAYLIST_PATH, "Lcom/amazon/mp3/playlist/CatalogPlaylist;", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlaylistSFAAction {
    private static final String TAG = Reflection.getOrCreateKotlinClass(PlaylistSFAAction.class).getSimpleName();
    private final FragmentActivity activity;
    private final PageType pageType;
    private final PlaylistUtil.PlaylistTrackInfo playlistTrackInfo;
    private final Uri playlistUri;

    public PlaylistSFAAction(FragmentActivity activity, Uri playlistUri, PageType pageType) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(playlistUri, "playlistUri");
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        this.activity = activity;
        this.playlistUri = playlistUri;
        this.pageType = pageType;
        this.playlistTrackInfo = PlaylistUtil.createPlaylistInfo(activity, playlistUri, CirrusMediaSource.match(playlistUri));
    }

    public final void startPrimePlaylistSFA(String playlistAsin) {
        Intrinsics.checkNotNullParameter(playlistAsin, "playlistAsin");
        StationUtils.startSFA(this.activity, playlistAsin, "PLAYLIST_SEED", PlaybackPageType.PLAYLIST_DETAIL, ControlSource.APP_UI);
        StationUtils.sendStartSFAUiClickMetrics(playlistAsin, EntityIdType.SFA_PLAYLIST_SEED);
    }

    public final void startSharedUserPlaylistSFA(List<String> trackAsinList) {
        Intrinsics.checkNotNullParameter(trackAsinList, "trackAsinList");
        List<Track> trackListFromSharedPlaylistWithLimitation = StationUtils.getTrackListFromSharedPlaylistWithLimitation(trackAsinList);
        StationUtils.startSFA(this.activity, trackListFromSharedPlaylistWithLimitation, "TRACK_LIST_SEED", PlaybackPageType.PLAYLIST_DETAIL);
        StationUtils.sendStartSFAUiClickMetrics(StringUtil.join(",", StationUtils.convertTracksToAsins(trackListFromSharedPlaylistWithLimitation)), EntityIdType.SFA_TRACK_LIST_SEED);
    }

    public static /* synthetic */ void startLibraryPlaylistSFA$default(PlaylistSFAAction playlistSFAAction, String str, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        playlistSFAAction.startLibraryPlaylistSFA(str, z);
    }

    public final void startLibraryPlaylistSFA(String playlistAsin, boolean startFullscreen) {
        Uri uri = this.playlistUri;
        if (MediaProvider.UdoPlaylists.isUdoPlaylist(uri)) {
            ThreadUtils.postInBackground(new Runnable() { // from class: com.amazon.mp3.detailpages.playlist.actions.PlaylistSFAAction$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    PlaylistSFAAction.m885startLibraryPlaylistSFA$lambda0(this.f$0);
                }
            });
        } else {
            if (!MediaProvider.PrimePlaylists.isPrimePlaylist(uri) || playlistAsin == null) {
                return;
            }
            StationUtils.startSFA(this.activity, playlistAsin, "PLAYLIST_SEED", PlaybackPageType.PLAYLIST_DETAIL, ControlSource.APP_UI);
            StationUtils.sendStartSFAUiClickMetrics(playlistAsin, EntityIdType.SFA_PLAYLIST_SEED, this.pageType);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: startLibraryPlaylistSFA$lambda-0, reason: not valid java name */
    public static final void m885startLibraryPlaylistSFA$lambda0(PlaylistSFAAction this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        List<String> asinListFromTrackLuidSet = StationUtils.getAsinListFromTrackLuidSet(this$0.activity, this$0.playlistTrackInfo.mCachePlaylist.keySet());
        StationUtils.startSFA(this$0.activity, StationUtils.convertAsinsToTracks(asinListFromTrackLuidSet), "TRACK_LIST_SEED", PlaybackPageType.PLAYLIST_DETAIL);
        StationUtils.sendStartSFAUiClickMetrics(StringUtil.join(",", asinListFromTrackLuidSet), EntityIdType.SFA_TRACK_LIST_SEED, this$0.pageType);
    }

    public final void startPrimePlaylistSFAForFreeTier() {
        CatalogPlaylist catalogPlaylist = AmazonApplication.getLibraryItemFactory().getCatalogPlaylist(this.playlistUri);
        if (catalogPlaylist == null) {
            return;
        }
        FragmentActivity fragmentActivity = this.activity;
        if (catalogPlaylist.isGoldenPlaylist()) {
            FreeTierPlaybackUtil.playCloudStation(fragmentActivity, new PlayableEntityIdentifier(catalogPlaylist.getAsin(), PlayableEntityIdentifierType.ASIN), true, true, true, PlaybackPageType.PLAYLIST_DETAIL, MediaCollectionType.GOLDEN_PLAYLIST);
        } else if (catalogPlaylist.isSharedUserPlaylist()) {
            startSharedUserPlaylistSFAForFreeTier(fragmentActivity, catalogPlaylist);
        } else {
            StationUtils.startSFA(this.activity, catalogPlaylist.getAsin(), "PLAYLIST_SEED", PlaybackPageType.PLAYLIST_DETAIL, ControlSource.APP_UI);
        }
    }

    private final void startSharedUserPlaylistSFAForFreeTier(Context context, CatalogPlaylist playlist) {
        ArrayList arrayList = new ArrayList(100);
        Iterator<CatalogPlaylistTrack> it = playlist.getTracks().iterator();
        while (it.hasNext()) {
            arrayList.add(new PlayableEntityIdentifier(it.next().getAsin(), PlayableEntityIdentifierType.ASIN));
            if (arrayList.size() >= 100) {
                break;
            }
        }
        FreeTierPlaybackUtil.playCloudStation(context, (List<PlayableEntityIdentifier>) arrayList, true, false, true, PlaybackPageType.SFA, Clock.now(), UpsellUtil.getUpsellInformation(arrayList, UpsellReason.PLAY, UpsellSourceEntity.PLAYLIST));
    }
}
