package com.amazon.mp3.detailpages.playlist.actions;

import android.content.Context;
import android.net.Uri;
import androidx.fragment.app.FragmentActivity;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.card.prime.StationUtils;
import com.amazon.mp3.cloudqueue.FreeTierPlaybackUtil;
import com.amazon.mp3.cloudqueue.model.PlayableEntityIdentifier;
import com.amazon.mp3.cloudqueue.model.PlayableEntityIdentifierType;
import com.amazon.mp3.detailpages.actions.PlayCollectionAction;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.playback.state.ContentPlaybackUtils;
import com.amazon.mp3.prime.upsell.nightwing.UpsellUtil;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellReason;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellSourceEntity;
import com.amazon.mp3.util.DbUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.freetier.featuregating.FMPMFeatureGating;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaCollectionType;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.PlaylistMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import com.amazon.music.views.library.models.VisualRowItemModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/* JADX INFO: compiled from: PlaylistPlaybackAction.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u0005¢\u0006\u0002\u0010\u000bJ3\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\r2\b\u0010\u0012\u001a\u0004\u0018\u00010\rH\u0002¢\u0006\u0002\u0010\u0013JN\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00162\u0006\u0010\u0007\u001a\u00020\u00052,\b\u0002\u0010\u0017\u001a&\u0012\u0004\u0012\u00020\u0019\u0012\u0006\u0012\u0004\u0018\u00010\u001a\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\r\u0012\u0006\u0012\u0004\u0018\u00010\u0015\u0018\u00010\u0018H\u0002Jb\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00162\u0006\u0010\u0007\u001a\u00020\u00052\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001a2,\b\u0002\u0010\u0017\u001a&\u0012\u0004\u0012\u00020\u0019\u0012\u0006\u0012\u0004\u0018\u00010\u001a\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\r\u0012\u0006\u0012\u0004\u0018\u00010\u0015\u0018\u00010\u00182\u0006\u0010\u001e\u001a\u00020\u0005H\u0002JX\u0010\u001f\u001a\u00020\u00152\u0006\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00162\b\u0010\u001d\u001a\u0004\u0018\u00010\u001a2,\b\u0002\u0010\u0017\u001a&\u0012\u0004\u0012\u00020\u0019\u0012\u0006\u0012\u0004\u0018\u00010\u001a\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\r\u0012\u0006\u0012\u0004\u0018\u00010\u0015\u0018\u00010\u00182\u0006\u0010\u001e\u001a\u00020\u0005H\u0002J\u0012\u0010 \u001a\u00020\u00052\b\u0010!\u001a\u0004\u0018\u00010\"H\u0002J\u008d\u0001\u0010#\u001a\u00020\u00152\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u000e\u001a\u00020\u00052\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\r2\b\b\u0002\u0010%\u001a\u00020\u00052\u0006\u0010\u001e\u001a\u00020\u00052\n\b\u0002\u0010&\u001a\u0004\u0018\u00010\u001a2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001a2,\b\u0002\u0010\u0017\u001a&\u0012\u0004\u0012\u00020\u0019\u0012\u0006\u0012\u0004\u0018\u00010\u001a\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\r\u0012\u0006\u0012\u0004\u0018\u00010\u0015\u0018\u00010\u0018¢\u0006\u0002\u0010'J\"\u0010(\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010*0)2\u0006\u0010+\u001a\u00020\u001a2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006,"}, d2 = {"Lcom/amazon/mp3/detailpages/playlist/actions/PlaylistPlaybackAction;", "Lcom/amazon/mp3/detailpages/actions/PlayCollectionAction;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "isCatalog", "", "isPlaylistDownloaded", "shouldBlockPlayback", "trackInformationFetcher", "Lcom/amazon/mp3/detailpages/actions/PlayCollectionAction$TrackInformationFetcher;", "isOnDemandGoldenPlaylist", "(Landroidx/fragment/app/FragmentActivity;ZZZLcom/amazon/mp3/detailpages/actions/PlayCollectionAction$TrackInformationFetcher;Z)V", "getPlayStartPosition", "", "isShuffled", "metadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "filteredTrackCount", "trackPosition", "(ZLcom/amazon/music/views/library/metadata/ContentMetadata;Ljava/lang/Integer;Ljava/lang/Integer;)I", "onLibraryPlaylistStartPlaybackCalled", "", "Lcom/amazon/music/views/library/metadata/PlaylistMetadata;", "uiClickEventCallback", "Lkotlin/Function4;", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/EntityIdType;", "onPlaylistStartPlaybackCalled", ParserUtil.TRACK_ASIN_QUERY_PARAM_NAME, "showNowPlaying", "onPrimePlaylistStartPlaybackCalled", "shouldFetchWhereClause", "uri", "Landroid/net/Uri;", "startPlayback", "trackCount", "isCustomerSelectedEntity", "sortOrder", "(Lcom/amazon/music/views/library/metadata/ContentMetadata;ZLjava/lang/Integer;Ljava/lang/Integer;ZZLjava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function4;)V", "trackIdFilterClause", "Lrx/Observable;", "Lcom/amazon/mp3/util/DbUtil$WhereClause;", "field", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlaylistPlaybackAction extends PlayCollectionAction {
    private final FragmentActivity activity;
    private final boolean isCatalog;
    private final boolean isOnDemandGoldenPlaylist;
    private final boolean isPlaylistDownloaded;
    private final boolean shouldBlockPlayback;

    public /* synthetic */ PlaylistPlaybackAction(FragmentActivity fragmentActivity, boolean z, boolean z2, boolean z3, PlayCollectionAction.TrackInformationFetcher trackInformationFetcher, boolean z4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(fragmentActivity, z, z2, z3, trackInformationFetcher, (i & 32) != 0 ? false : z4);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public PlaylistPlaybackAction(FragmentActivity activity, boolean z, boolean z2, boolean z3, PlayCollectionAction.TrackInformationFetcher trackInformationFetcher, boolean z4) {
        PlaybackPageType playbackPageType;
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(trackInformationFetcher, "trackInformationFetcher");
        if (z) {
            playbackPageType = PlaybackPageType.PRIME_PLAYLIST_DETAIL;
        } else {
            playbackPageType = PlaybackPageType.PLAYLIST_DETAIL;
        }
        super(trackInformationFetcher, playbackPageType);
        this.activity = activity;
        this.isCatalog = z;
        this.isPlaylistDownloaded = z2;
        this.shouldBlockPlayback = z3;
        this.isOnDemandGoldenPlaylist = z4;
    }

    public final void startPlayback(ContentMetadata metadata, boolean isShuffled, Integer trackPosition, Integer trackCount, boolean isCustomerSelectedEntity, boolean showNowPlaying, String sortOrder, String trackAsin, Function4<? super ActionType, ? super String, ? super EntityIdType, ? super Integer, Unit> uiClickEventCallback) {
        boolean z;
        Integer num;
        Integer num2;
        PlaylistMetadata playlistMetadata;
        PlaylistMetadata playlistMetadata2;
        String title;
        if (metadata instanceof PlaylistMetadata) {
            z = isShuffled;
            num2 = trackCount;
            playlistMetadata = (PlaylistMetadata) metadata;
            num = trackPosition;
        } else {
            z = isShuffled;
            num = trackPosition;
            num2 = trackCount;
            playlistMetadata = null;
        }
        int playStartPosition = getPlayStartPosition(z, playlistMetadata, num2, num);
        if (this.shouldBlockPlayback) {
            playlistMetadata2 = playlistMetadata;
        } else {
            Observable<DbUtil.WhereClause> observableTrackIdFilterClause = trackIdFilterClause(this.isCatalog ? "PrimePlaylistTracks.asin" : "luid", playlistMetadata == null ? null : playlistMetadata.getUri());
            FragmentActivity fragmentActivity = this.activity;
            boolean z2 = this.isPlaylistDownloaded;
            String asin = playlistMetadata == null ? null : playlistMetadata.getAsin();
            if (playlistMetadata == null || (title = playlistMetadata.getTitle()) == null) {
                title = "";
            }
            playlistMetadata2 = playlistMetadata;
            PlayCollectionAction.startNowPlayingActivity$default(this, fragmentActivity, z2, asin, title, ContentPlaybackUtils.INSTANCE.appendTracksIfNeeded(playlistMetadata != null ? playlistMetadata.getUri() : null), playStartPosition, isShuffled, showNowPlaying, observableTrackIdFilterClause, sortOrder, isCustomerSelectedEntity, false, null, 6144, null);
        }
        if (playlistMetadata2 == null) {
            return;
        }
        onPlaylistStartPlaybackCalled(isShuffled, playlistMetadata2, this.shouldBlockPlayback, trackAsin, uiClickEventCallback, showNowPlaying);
    }

    private final Observable<DbUtil.WhereClause> trackIdFilterClause(final String field, Uri uri) {
        if (!shouldFetchWhereClause(uri)) {
            Observable<DbUtil.WhereClause> observableJust = Observable.just(null);
            Intrinsics.checkNotNullExpressionValue(observableJust, "just(null)");
            return observableJust;
        }
        Observable<DbUtil.WhereClause> observableObserveOn = getTrackInfoFetcher().fetchAllIds(new Function1<BaseViewModel, String>() { // from class: com.amazon.mp3.detailpages.playlist.actions.PlaylistPlaybackAction.trackIdFilterClause.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final String invoke(BaseViewModel currentModel) {
                Intrinsics.checkNotNullParameter(currentModel, "currentModel");
                VisualRowItemModel visualRowItemModel = currentModel instanceof VisualRowItemModel ? (VisualRowItemModel) currentModel : null;
                ContentMetadata metadata = visualRowItemModel == null ? null : visualRowItemModel.getMetadata();
                TrackMetadata trackMetadata = metadata instanceof TrackMetadata ? (TrackMetadata) metadata : null;
                if (PlaylistPlaybackAction.this.isCatalog) {
                    if (trackMetadata == null) {
                        return null;
                    }
                    return trackMetadata.getAsin();
                }
                if (trackMetadata == null) {
                    return null;
                }
                return trackMetadata.getLuid();
            }
        }).map(new Func1() { // from class: com.amazon.mp3.detailpages.playlist.actions.PlaylistPlaybackAction$$ExternalSyntheticLambda0
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return PlaylistPlaybackAction.m879trackIdFilterClause$lambda1(this.f$0, field, (List) obj);
            }
        }).observeOn(Schedulers.io());
        Intrinsics.checkNotNullExpressionValue(observableObserveOn, "private fun trackIdFilte…On(Schedulers.io())\n    }");
        return observableObserveOn;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: trackIdFilterClause$lambda-1, reason: not valid java name */
    public static final DbUtil.WhereClause m879trackIdFilterClause$lambda1(PlaylistPlaybackAction this$0, String field, List currentTrackIds) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(field, "$field");
        Intrinsics.checkNotNullExpressionValue(currentTrackIds, "currentTrackIds");
        if (currentTrackIds.size() < 1) {
            return null;
        }
        return this$0.getTrackInfoFetcher().getFilterByIds(CollectionsKt___CollectionsKt.toMutableList((Collection) currentTrackIds), field);
    }

    private final boolean shouldFetchWhereClause(Uri uri) {
        if (uri != null) {
            return (PlaylistUtil.isRecentlyAddedPlaylist(uri) || PlaylistUtil.isRecentlyAddedToDevicePlaylist(uri)) ? false : true;
        }
        Log.error(PlayCollectionAction.INSTANCE.getTAG(), "Attempting to determine if we should fetch a where clause for playlist playback, but the playlist uri is null.");
        return true;
    }

    private final int getPlayStartPosition(boolean isShuffled, ContentMetadata metadata, Integer filteredTrackCount, Integer trackPosition) {
        if (filteredTrackCount == null) {
            PlaylistMetadata playlistMetadata = metadata instanceof PlaylistMetadata ? (PlaylistMetadata) metadata : null;
            filteredTrackCount = playlistMetadata == null ? null : playlistMetadata.getTrackCount();
        }
        if (filteredTrackCount == null || filteredTrackCount.intValue() == 0) {
            return -1;
        }
        if (isShuffled) {
            return Random.INSTANCE.nextInt(filteredTrackCount.intValue());
        }
        if (trackPosition == null) {
            return 0;
        }
        return trackPosition.intValue();
    }

    private final void onPlaylistStartPlaybackCalled(boolean isShuffled, PlaylistMetadata metadata, boolean shouldBlockPlayback, String trackAsin, Function4<? super ActionType, ? super String, ? super EntityIdType, ? super Integer, Unit> uiClickEventCallback, boolean showNowPlaying) {
        if (this.isCatalog) {
            onPrimePlaylistStartPlaybackCalled(isShuffled, metadata, trackAsin, uiClickEventCallback, showNowPlaying);
        } else {
            onLibraryPlaylistStartPlaybackCalled(isShuffled, metadata, shouldBlockPlayback, uiClickEventCallback);
        }
    }

    private final void onPrimePlaylistStartPlaybackCalled(boolean isShuffled, PlaylistMetadata metadata, String trackAsin, Function4<? super ActionType, ? super String, ? super EntityIdType, ? super Integer, Unit> uiClickEventCallback, boolean showNowPlaying) {
        ActionType actionType;
        if (!UserSubscriptionUtil.isNightwingOnly() || Feature.should_migrate_free_to_cq_for_non_station.isEnabled()) {
            if (!isShuffled) {
                actionType = ActionType.PLAY_CURATED_PLAYLIST;
            } else {
                actionType = ActionType.PLAY_SHUFFLE_ALL;
            }
            if (uiClickEventCallback == null) {
                return;
            }
            uiClickEventCallback.invoke(actionType, metadata.getAsin(), EntityIdType.ASIN, -1);
            return;
        }
        Context applicationContext = this.activity.getApplicationContext();
        if (this.isOnDemandGoldenPlaylist && trackAsin != null && FMPMFeatureGating.FREE_TIER_DEEPLINKING_ENABLED.isEnabled()) {
            FreeTierPlaybackUtil.startPlaylistOnDemand(applicationContext, new PlayableEntityIdentifier(metadata.getAsin(), PlayableEntityIdentifierType.ASIN), true, true, showNowPlaying, PlaybackPageType.GOLDEN_PLAYLIST, MediaCollectionType.GOLDEN_PLAYLIST, trackAsin);
        } else if (Intrinsics.areEqual(metadata.getIsFree(), Boolean.TRUE)) {
            FreeTierPlaybackUtil.playCloudStation(applicationContext, new PlayableEntityIdentifier(metadata.getAsin(), PlayableEntityIdentifierType.ASIN), true, true, true, PlaybackPageType.GOLDEN_PLAYLIST);
        } else {
            StationUtils.startSFA(applicationContext, metadata.getAsin(), "PLAYLIST_SEED", PlaybackPageType.PLAYLIST_DETAIL, ControlSource.APP_UI);
        }
    }

    private final void onLibraryPlaylistStartPlaybackCalled(boolean isShuffled, PlaylistMetadata metadata, boolean shouldBlockPlayback, Function4<? super ActionType, ? super String, ? super EntityIdType, ? super Integer, Unit> uiClickEventCallback) {
        ActionType actionType;
        UpsellReason upsellReason;
        Context applicationContext = this.activity.getApplicationContext();
        if (!isShuffled) {
            actionType = ActionType.PLAY_PLAYLIST;
            upsellReason = UpsellReason.PLAY;
        } else {
            actionType = ActionType.PLAY_SHUFFLE_ALL;
            upsellReason = UpsellReason.SHUFFLE_ALL;
        }
        if (uiClickEventCallback != null) {
            uiClickEventCallback.invoke(actionType, PlaylistUtil.getPlaylistLuid(applicationContext, metadata.getUri()), EntityIdType.PLAYLIST_ID, -1);
        }
        if (shouldBlockPlayback) {
            UpsellUtil.showBlockingUpsell(this.activity, String.valueOf(PlaylistUtil.getPlaylistId(metadata.getUri())), upsellReason, UpsellSourceEntity.PLAYLIST);
        }
    }
}
