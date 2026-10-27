package com.amazon.mp3.prime.upsell.nightwing;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.activity.navigation.FragmentController;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.card.prime.StationUtils;
import com.amazon.mp3.cloudqueue.model.PlayableEntityIdentifier;
import com.amazon.mp3.cloudqueue.model.PlayableEntityIdentifierType;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.inappmessaging.DynamicMessagingManager;
import com.amazon.mp3.library.fragment.PrimePlaylistDetailFragment;
import com.amazon.mp3.library.item.Album;
import com.amazon.mp3.library.item.Artist;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.item.Playlist;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.playlist.CatalogPlaylist;
import com.amazon.mp3.prime.browse.metadata.PrimeAlbum;
import com.amazon.mp3.prime.browse.metadata.PrimeArtist;
import com.amazon.mp3.prime.browse.metadata.PrimePlaylist;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellInformation;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellReason;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellSourceEntity;
import com.amazon.mp3.recentlyplayed.RecentTrack;
import com.amazon.mp3.recentlyplayed.RecentlyPlayedItem;
import com.amazon.mp3.search.model.Station;
import com.amazon.mp3.util.ContentAccessUtil;
import com.amazon.mp3.util.Log;
import com.amazon.music.find.model.search.SearchItem;
import com.amazon.music.inappmessaging.external.model.DynamicMessageContextualEventBuilder;
import com.amazon.music.inappmessaging.external.model.DynamicMessageEvent;
import com.amazon.music.inappmessaging.external.model.DynamicMessageMetricsAttributes;
import com.amazon.music.inappmessaging.external.model.Trigger;
import com.amazon.music.media.playback.MediaCollectionType;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.providers.FeatureGateProvider;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/* JADX INFO: loaded from: classes4.dex */
public class UpsellManager {
    private static UpsellManager INSTANCE = null;
    private static final String TAG = "UpsellManager";
    private CompositeSubscription compositeSubscription = new CompositeSubscription();
    private boolean isShowingBlockingDialog;

    private UpsellManager() {
    }

    public static UpsellManager getInstance() {
        if (INSTANCE == null) {
            synchronized (UpsellManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new UpsellManager();
                }
            }
        }
        return INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0142  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public androidx.fragment.app.DialogFragment getUpsellDialog(android.content.Context r17, java.lang.Object r18, java.lang.String r19, com.amazon.mp3.util.ContentAccessUtil.ContentAccessOperation r20) {
        /*
            Method dump skipped, instruction units count: 384
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.prime.upsell.nightwing.UpsellManager.getUpsellDialog(android.content.Context, java.lang.Object, java.lang.String, com.amazon.mp3.util.ContentAccessUtil$ContentAccessOperation):androidx.fragment.app.DialogFragment");
    }

    public String getItemUrl(final Context context, final Object item) {
        if (item instanceof PrimeTrack) {
            PrimeTrack primeTrack = (PrimeTrack) item;
            return getTrackContentDeeplink(context, primeTrack.getAsin(), primeTrack.getAlbumAsin());
        }
        if (item instanceof MusicTrack) {
            MusicTrack musicTrack = (MusicTrack) item;
            return getTrackContentDeeplink(context, musicTrack.getAsin(), musicTrack.getAlbumAsin());
        }
        if (item instanceof PrimeAlbum) {
            return "/albums/" + ((PrimeAlbum) item).getAsin() + "?";
        }
        if (item instanceof PrimePlaylist) {
            return "/playlists/" + ((PrimePlaylist) item).getAsin() + "?";
        }
        if (item instanceof PrimeArtist) {
            return "/artists/" + ((PrimeArtist) item).getAsin() + "?";
        }
        if (!(item instanceof Station)) {
            return "";
        }
        return "/stations/" + ((Station) item).getKey() + "?";
    }

    private String getTrackContentDeeplink(final Context context, final String trackAsin, final String albumAsin) {
        List<Fragment> listEmptyList;
        if (context instanceof MusicHomeActivity) {
            listEmptyList = ((MusicHomeActivity) context).getSupportFragmentManager().getFragments();
        } else {
            listEmptyList = Collections.emptyList();
        }
        Fragment fragment = listEmptyList.size() > 0 ? listEmptyList.get(listEmptyList.size() - 1) : null;
        String playlistAsin = fragment instanceof PrimePlaylistDetailFragment ? MediaProvider.PrimePlaylists.getPlaylistAsin(((PrimePlaylistDetailFragment) fragment).getContentUri()) : "";
        if (StringUtils.isEmpty(playlistAsin)) {
            return "/albums/" + albumAsin + "?trackAsin=" + trackAsin;
        }
        return "/playlists/" + playlistAsin + "?trackAsin=" + trackAsin;
    }

    public DialogFragment getOneClickUpsellDialogForPrime(final Context context, final UpsellInformation upsellInformation, final DynamicMessageMetricsAttributes metricsAttributes) {
        UpsellUtil.emitEligibilityCheckRequestedMetric(upsellInformation);
        showDynamicMessageOneClick(context, buildDynamicMessageEvent(upsellInformation), metricsAttributes);
        return null;
    }

    public String getSeedName(final Object item) {
        if (item == null) {
            return null;
        }
        if (item instanceof SearchItem) {
            return ((SearchItem) item).getTitle();
        }
        if (item instanceof RecentlyPlayedItem) {
            return ((RecentlyPlayedItem) item).getTitle();
        }
        if (item instanceof RecentTrack) {
            return ((RecentTrack) item).getTitle();
        }
        if (item instanceof MusicTrack) {
            return ((MusicTrack) item).getTitle();
        }
        if (item instanceof Album) {
            return ((Album) item).getTitle();
        }
        if (item instanceof Playlist) {
            return ((Playlist) item).getName();
        }
        if (item instanceof CatalogPlaylist) {
            return ((CatalogPlaylist) item).getTitle();
        }
        return null;
    }

    private List<String> getTags(final Object item) {
        if (item == null || !(item instanceof PrimeTrack)) {
            return null;
        }
        return ((PrimeTrack) item).getTags();
    }

    public String getArtistName(final Object item) {
        if (item == null) {
            return null;
        }
        if (item instanceof PrimeAlbum) {
            return ((PrimeAlbum) item).getArtist();
        }
        if (item instanceof PrimeTrack) {
            return ((PrimeTrack) item).getArtist();
        }
        if (item instanceof MusicTrack) {
            return ((MusicTrack) item).getArtistName();
        }
        if (item instanceof Artist) {
            return ((Artist) item).getName();
        }
        return null;
    }

    private String getAlbumAsin(final Object item) {
        if (item == null) {
            return null;
        }
        if (item instanceof PrimeAlbum) {
            return ((PrimeAlbum) item).getAsin();
        }
        if (item instanceof PrimeTrack) {
            return ((PrimeTrack) item).getAlbumAsin();
        }
        return null;
    }

    private String getTrackAsinQueryParameter(final Object item) {
        if (item == null) {
            return null;
        }
        if (item instanceof PrimeAlbum) {
            return "";
        }
        if (!(item instanceof PrimeTrack)) {
            return null;
        }
        return "trackAsin=" + ((PrimeTrack) item).getAsin();
    }

    public void raiseNonBlockingUpsell(Context context, UpsellInformation upsellInformation, MediaCollectionType collectionType) {
        PlaybackPageType playbackPageType = PlaybackPageType.SFA;
        if (upsellInformation.getUpsellSourceEntity() == UpsellSourceEntity.ALBUM_PAGE) {
            playbackPageType = PlaybackPageType.ALBUM_DETAIL;
        }
        if (upsellInformation.getUpsellSourceEntity() == UpsellSourceEntity.PLAYLIST_DETAIL) {
            playbackPageType = PlaybackPageType.PLAYLIST_DETAIL;
        }
        if (isEligibleForUpsellBeforePlayback(upsellInformation)) {
            showUpsellBeforePlayback(context, playbackPageType, upsellInformation, Trigger.TRACK_SHUFFLE_PLAYBACK_UPSELL);
        } else {
            StationUtils.startSFA(context, upsellInformation.getPlayableEntityIdentifierList(), playbackPageType, upsellInformation);
        }
    }

    public void showUpsellBeforePlayback(final Context context, final PlaybackPageType playbackPageType, final UpsellInformation upsellInformation, final Trigger trigger) {
        DynamicMessageMetricsAttributes dynamicMessageMetricsAttributes = new DynamicMessageMetricsAttributes(playbackPageType.getMetricsValue(), upsellInformation.getUpsellReason().toString());
        UpsellUtil.emitEligibilityCheckRequestedMetric(upsellInformation);
        showDynamicMessageOneClick(context, DynamicMessageContextualEventBuilder.builder(trigger).withItemUrl(upsellInformation.getItemUrl() + "&do=playfull").build(), dynamicMessageMetricsAttributes);
    }

    private List<PlayableEntityIdentifier> getPlayableEntityIdentifierList(Object item, final UpsellSourceEntity upsellSourceEntity) {
        if (item instanceof PrimePlaylist) {
            return Collections.singletonList(new PlayableEntityIdentifier(((PrimePlaylist) item).getAsin(), PlayableEntityIdentifierType.ASIN));
        }
        if (item instanceof PrimeAlbum) {
            return Collections.singletonList(new PlayableEntityIdentifier(((PrimeAlbum) item).getAsin(), PlayableEntityIdentifierType.ASIN));
        }
        if (item instanceof PrimeTrack) {
            return Collections.singletonList(new PlayableEntityIdentifier(((PrimeTrack) item).getAsin(), PlayableEntityIdentifierType.ASIN));
        }
        if (item instanceof PrimeArtist) {
            return Collections.singletonList(new PlayableEntityIdentifier(((PrimeArtist) item).getAsin(), PlayableEntityIdentifierType.ASIN));
        }
        if (item instanceof RecentlyPlayedItem) {
            RecentlyPlayedItem recentlyPlayedItem = (RecentlyPlayedItem) item;
            if (upsellSourceEntity != UpsellSourceEntity.STATION) {
                return Collections.singletonList(new PlayableEntityIdentifier(recentlyPlayedItem.getCollectionAsin(), PlayableEntityIdentifierType.ASIN));
            }
            if (MediaProvider.Station.isStationFromAnything(recentlyPlayedItem.getContentUri())) {
                return Collections.singletonList(new PlayableEntityIdentifier(MediaProvider.Station.getStationFromAnythingSeedId(recentlyPlayedItem.getContentUri()), PlayableEntityIdentifierType.STATION_SEED_ID));
            }
            return Collections.singletonList(new PlayableEntityIdentifier(recentlyPlayedItem.getCollectionAsin(), PlayableEntityIdentifierType.STATION_KEY));
        }
        if (item instanceof RecentTrack) {
            return Collections.singletonList(new PlayableEntityIdentifier(((RecentTrack) item).getAsin(), PlayableEntityIdentifierType.ASIN));
        }
        if (item instanceof MusicTrack) {
            return Collections.singletonList(new PlayableEntityIdentifier(((MusicTrack) item).getAsin(), PlayableEntityIdentifierType.ASIN));
        }
        if (item instanceof Station) {
            Station station = (Station) item;
            String sFASeed = station.getSFASeed();
            if (sFASeed != null) {
                return Collections.singletonList(new PlayableEntityIdentifier(sFASeed, PlayableEntityIdentifierType.STATION_SEED_ID));
            }
            return Collections.singletonList(new PlayableEntityIdentifier(station.getKey(), PlayableEntityIdentifierType.STATION_KEY));
        }
        if (item instanceof SearchItem) {
            return Collections.singletonList(new PlayableEntityIdentifier(((SearchItem) item).getAsin(), PlayableEntityIdentifierType.ASIN));
        }
        return null;
    }

    @Nullable
    public synchronized DialogFragment getBlockingUpsellFragment(final FragmentActivity fragmentActivity, final UpsellInformation upsellInformation, final DynamicMessageMetricsAttributes metricsAttributes) {
        UpsellUtil.emitEligibilityCheckRequestedMetric(upsellInformation);
        showDynamicMessageOneClick(fragmentActivity, buildDynamicMessageEvent(upsellInformation), metricsAttributes);
        return null;
    }

    private DynamicMessageEvent buildDynamicMessageEvent(final UpsellInformation upsellInformation) {
        String albumAsin = upsellInformation.getAlbumAsin();
        String trackAsinQueryParameter = upsellInformation.getTrackAsinQueryParameter();
        if (upsellInformation.getUpsellReason() == UpsellReason.STREAM_UPSELL && albumAsin != null && trackAsinQueryParameter != null) {
            return DynamicMessageContextualEventBuilder.builder(Trigger.STREAM_UPSELL).withAlbumAsin(albumAsin).withItemName(upsellInformation.getSeedName()).withItemUrl(upsellInformation.getItemUrl()).withTrackAsinQueryParameter(trackAsinQueryParameter).build();
        }
        return DynamicMessageContextualEventBuilder.builder(Trigger.INELIGIBLE_CONTENT_REQUEST).withArtistName(upsellInformation.getArtistName()).withItemUrl(upsellInformation.getItemUrl()).withItemName(upsellInformation.getSeedName()).build();
    }

    private static UpsellSourceEntity getUpsellSourceEntity(Context context, Object item, String itemName) {
        if ((item instanceof MusicTrack) || itemName.equals(context.getString(R.string.dmusic_library_item_name_track))) {
            return UpsellSourceEntity.TRACK;
        }
        if ((item instanceof Album) || itemName.equals(context.getString(R.string.dmusic_library_item_name_album))) {
            return UpsellSourceEntity.ALBUM;
        }
        if ((item instanceof Artist) || itemName.equals(context.getString(R.string.dmusic_library_item_name_artist))) {
            return UpsellSourceEntity.ARTIST;
        }
        if ((item instanceof Playlist) || itemName.equals(context.getString(R.string.dmusic_library_item_name_playlist))) {
            return UpsellSourceEntity.PLAYLIST;
        }
        if (itemName.equals(context.getString(R.string.dmusic_library_item_name_station))) {
            return UpsellSourceEntity.STATION;
        }
        if (itemName.equals(context.getString(R.string.dmusic_recently_played_alexa))) {
            return UpsellSourceEntity.ALEXA_HISTORY;
        }
        if (itemName.equals(context.getString(R.string.dmusic_library_item_name_genre))) {
            return UpsellSourceEntity.GENRE;
        }
        return null;
    }

    private static UpsellReason getUpsellReasonFromContentAccessOperation(Object item, ContentAccessUtil.ContentAccessOperation contentAccessOperation) {
        if (contentAccessOperation != null) {
            int i = AnonymousClass1.$SwitchMap$com$amazon$mp3$util$ContentAccessUtil$ContentAccessOperation[contentAccessOperation.ordinal()];
            if (i == 1) {
                return UpsellReason.ADD_TO_LIBRARY;
            }
            if (i == 2) {
                if ((item instanceof PrimeTrack) && ((PrimeTrack) item).isVideo()) {
                    return UpsellReason.VIDEO_PLAY;
                }
                return UpsellReason.PLAY;
            }
            if (i == 3) {
                return UpsellReason.DOWNLOAD_CONTENT;
            }
            if (i != 4) {
                if (i != 5) {
                    Log.debug(TAG, "unknown contentAccessOperation");
                }
            } else if (item instanceof PrimeAlbum) {
                return UpsellReason.BROWSE_ALBUM;
            }
            return UpsellReason.STREAM_UPSELL;
        }
        return UpsellReason.OTHER;
    }

    /* JADX INFO: renamed from: com.amazon.mp3.prime.upsell.nightwing.UpsellManager$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$prime$upsell$nightwing$model$UpsellReason;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$util$ContentAccessUtil$ContentAccessOperation;

        static {
            int[] iArr = new int[UpsellReason.values().length];
            $SwitchMap$com$amazon$mp3$prime$upsell$nightwing$model$UpsellReason = iArr;
            try {
                iArr[UpsellReason.DOWNLOAD_CONTENT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$upsell$nightwing$model$UpsellReason[UpsellReason.SHUFFLE_ALL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$upsell$nightwing$model$UpsellReason[UpsellReason.ADD_TO_LIBRARY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$upsell$nightwing$model$UpsellReason[UpsellReason.AD_SKIP.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$upsell$nightwing$model$UpsellReason[UpsellReason.TRACK_SKIP_LIMIT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$upsell$nightwing$model$UpsellReason[UpsellReason.VIDEO_PLAY.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$upsell$nightwing$model$UpsellReason[UpsellReason.BROWSE_ALBUM.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$upsell$nightwing$model$UpsellReason[UpsellReason.STREAM_UPSELL.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$upsell$nightwing$model$UpsellReason[UpsellReason.OTHER.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$upsell$nightwing$model$UpsellReason[UpsellReason.PLAY.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            int[] iArr2 = new int[ContentAccessUtil.ContentAccessOperation.values().length];
            $SwitchMap$com$amazon$mp3$util$ContentAccessUtil$ContentAccessOperation = iArr2;
            try {
                iArr2[ContentAccessUtil.ContentAccessOperation.INTERACT.ordinal()] = 1;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$mp3$util$ContentAccessUtil$ContentAccessOperation[ContentAccessUtil.ContentAccessOperation.STREAM.ordinal()] = 2;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$amazon$mp3$util$ContentAccessUtil$ContentAccessOperation[ContentAccessUtil.ContentAccessOperation.DOWNLOAD.ordinal()] = 3;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$amazon$mp3$util$ContentAccessUtil$ContentAccessOperation[ContentAccessUtil.ContentAccessOperation.BROWSE.ordinal()] = 4;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$amazon$mp3$util$ContentAccessUtil$ContentAccessOperation[ContentAccessUtil.ContentAccessOperation.STREAM_UPSELL.ordinal()] = 5;
            } catch (NoSuchFieldError unused15) {
            }
        }
    }

    private boolean isBlockingUpsell(UpsellReason upsellReason) {
        if (upsellReason == null) {
            return false;
        }
        switch (AnonymousClass1.$SwitchMap$com$amazon$mp3$prime$upsell$nightwing$model$UpsellReason[upsellReason.ordinal()]) {
        }
        return false;
    }

    public synchronized void setShowingBlockingDialog(boolean showingBlockingDialog) {
        this.isShowingBlockingDialog = showingBlockingDialog;
    }

    public void clearSubscription() {
        CompositeSubscription compositeSubscription = this.compositeSubscription;
        if (compositeSubscription == null || !compositeSubscription.hasSubscriptions()) {
            return;
        }
        this.compositeSubscription.clear();
    }

    public void showNonBlockingDynamicMessageOneClick(final FragmentController fragmentController, final Context context, final Trigger trigger, final DynamicMessageMetricsAttributes metricsAttributes) {
        this.compositeSubscription.add(new DynamicMessagingManager(context).displayNonBlockingDynamicMessage((FragmentActivity) context, new DynamicMessageEvent(trigger), metricsAttributes).subscribe(new Action1() { // from class: com.amazon.mp3.prime.upsell.nightwing.UpsellManager$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                UpsellManager.lambda$showNonBlockingDynamicMessageOneClick$0(fragmentController, (Fragment) obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showNonBlockingDynamicMessageOneClick$0(FragmentController fragmentController, Fragment fragment) {
        if (fragment != null) {
            fragmentController.changeScreenFragment(fragment, false, true);
        }
    }

    public void showDynamicMessageOneClick(final Context context, final Trigger trigger, final DynamicMessageMetricsAttributes metricsAttributes) {
        this.compositeSubscription.add(new DynamicMessagingManager(context).displayDynamicMessage((FragmentActivity) context, new DynamicMessageEvent(trigger), metricsAttributes).subscribe());
    }

    public void showDynamicMessageOneClick(final Context context, final DynamicMessageEvent event, final DynamicMessageMetricsAttributes metricsAttributes) {
        this.compositeSubscription.add(new DynamicMessagingManager(context).displayDynamicMessage((FragmentActivity) context, event, metricsAttributes).subscribe());
    }

    public void showDynamicMessageOneClick(final FragmentActivity fragmentActivity, final DynamicMessageEvent event, final DynamicMessageMetricsAttributes metricsAttributes) {
        this.compositeSubscription.add(new DynamicMessagingManager(fragmentActivity.getApplicationContext()).displayDynamicMessage(fragmentActivity, event, metricsAttributes).subscribe());
    }

    public boolean isEligibleForUpsellBeforePlayback(final UpsellInformation upsellInformation) {
        FeatureGateProvider featureGateProvider;
        if (upsellInformation == null || upsellInformation.getItemUrl() == null || (featureGateProvider = (FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class)) == null || !(featureGateProvider.isFeatureAvailable(Feature.track_shuffle_playback_upsell_nightwing) || featureGateProvider.isFeatureAvailable(Feature.track_shuffle_playback_upsell_sonic_rush))) {
            return false;
        }
        return GeometricBackoffWithCapUpsellPacing.INSTANCE.shouldUpsell();
    }

    public boolean isEligibleForBrowseHomeUpsellBeforePlayback(final UpsellInformation upsellInformation) {
        if (upsellInformation.getItemUrl() == null || upsellInformation.getTags() == null || !upsellInformation.getTags().contains("upsell-tag-browsehome-widget")) {
            return false;
        }
        return Feature.browse_home_playback_upsell.isEnabled();
    }
}
