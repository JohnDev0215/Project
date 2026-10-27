package com.amazon.mp3.playback;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.AudioEffectActivity;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.audioquality.ContextMenuDownloadHelper;
import com.amazon.mp3.audioquality.nerdstats.AudioStatsFragmentActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.baseviews.model.configuration.PlayerModelConfiguration;
import com.amazon.mp3.capability.Capabilities;
import com.amazon.mp3.card.prime.StationUtils;
import com.amazon.mp3.casting.CastingUtil;
import com.amazon.mp3.cloudqueue.sequencer.AdMediaItem;
import com.amazon.mp3.cloudqueue.sequencer.CloudQueueLibraryTrackMediaItem;
import com.amazon.mp3.dialog.EqualizerDialogBuilder;
import com.amazon.mp3.download.MusicDownloader;
import com.amazon.mp3.download.helper.RedownloadStateResolver;
import com.amazon.mp3.environment.url.EndpointsProvider;
import com.amazon.mp3.library.activity.AddToPlaylistPopupActivity;
import com.amazon.mp3.library.activity.LibraryActivityFactory;
import com.amazon.mp3.library.cache.likes.LikesStateCacheManager;
import com.amazon.mp3.library.fragment.AddAndDownloadTrackTask;
import com.amazon.mp3.library.fragment.OnItemFinishedAddingListener;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.nowplaying.MediaItemHelper;
import com.amazon.mp3.library.provider.source.nowplaying.NowPlayingQueueUtil;
import com.amazon.mp3.likes.LikeStateListener;
import com.amazon.mp3.likes.LikesServiceProxy;
import com.amazon.mp3.likes.MediaItemLikesProviderImpl;
import com.amazon.mp3.moderation.ModerationEntity;
import com.amazon.mp3.moderation.ModerationHandler;
import com.amazon.mp3.playback.PlaylistTooltipExperience;
import com.amazon.mp3.playback.activity.NowPlayingUtil;
import com.amazon.mp3.playback.harley.LibraryAccessProviderImpl;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.playback.service.AudioEffectController;
import com.amazon.mp3.playback.songcredits.SongCreditsActivity;
import com.amazon.mp3.playlist.EntityPlaylistUtil;
import com.amazon.mp3.playlist.PlaylistSeedEntityType;
import com.amazon.mp3.prime.browse.SimilarRecommendationsFragment;
import com.amazon.mp3.prime.browse.pager.PaginatedDataType;
import com.amazon.mp3.prime.station.StationsFactory;
import com.amazon.mp3.prime.upsell.nightwing.UpsellUtil;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellReason;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellSourceEntity;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.service.metrics.mts.SelectionSourceHelper;
import com.amazon.mp3.util.BauhausToastUtils;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.DefaultStringType;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.MetricsUtil;
import com.amazon.mp3.util.PlatformUtil;
import com.amazon.mp3.util.StringUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.mp3.util.extensions.ActivityKt;
import com.amazon.music.ContentAccessType;
import com.amazon.music.downloads.DownloadState;
import com.amazon.music.downloads.Group;
import com.amazon.music.downloads.Item;
import com.amazon.music.downloads.notification.DownloadListener;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaItemId;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.PlaybackController;
import com.amazon.music.media.playback.PlaybackProvider;
import com.amazon.music.media.playback.SequencerPlaybackProvider;
import com.amazon.music.media.playback.access.MediaAccessInfo;
import com.amazon.music.media.playback.ratings.LikeState;
import com.amazon.music.media.playback.sequencer.impl.StationSequencer;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.LinkType;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.StoreLinkType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.share.StationShareProvider;
import com.amazon.music.station.StationException;
import com.amazon.music.station.StationItem;
import com.amazon.music.station.StationNotFoundException;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.HashSet;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes3.dex */
public class PlayerContextMenu {
    private static final String TAG = "PlayerContextMenu";
    private FragmentActivity mActivity;
    private DownloadListener mConsumerDownloadListener;
    private LikeStateListener mLikeStateListener;
    private PlaylistTooltipExperience mPlaylistTooltipExperience;
    private Subscription tooltipSubscription;
    private boolean isShowingPlaylistTooltipExperience = false;
    private boolean isDownloadUpsell = false;
    private DownloadState mCurrentTrackDownloadState = DownloadState.UNKNOWN;
    private final Handler mHandler = AmazonApplication.getDefaultHandler();
    private final PlaybackController mPlaybackController = PlaybackControllerProvider.getController(ControlSource.APP_UI);
    private DownloadListener mDownloadListener = new DownloadListener(new HashSet()) { // from class: com.amazon.mp3.playback.PlayerContextMenu.1
        @Override // com.amazon.music.downloads.notification.DownloadListener
        public void onProgressUpdate(final String s, final Group group, final float percentageDownloaded) {
        }

        @Override // com.amazon.music.downloads.notification.DownloadListener
        public void onProgressUpdate(final String s, final Item item, final float percentageDownloaded) {
        }

        @Override // com.amazon.music.downloads.notification.DownloadListener
        public void onStatusUpdate(final DownloadState downloadState, final String s, final Group group) {
        }

        @Override // com.amazon.music.downloads.notification.DownloadListener
        public void onStatusUpdate(final DownloadState downloadState, final String s, final Item item) {
            DownloadState downloadState2;
            MediaItem currentMediaItem = PlayerContextMenu.this.mPlaybackController.getCurrentMediaItem();
            if (item == null || MediaItemHelper.getAsin(currentMediaItem) == null || !MediaItemHelper.getAsin(currentMediaItem).equals(item.getAsin()) || downloadState != (downloadState2 = DownloadState.DOWNLOADED)) {
                return;
            }
            PlayerContextMenu.this.mCurrentTrackDownloadState = downloadState2;
        }
    };
    private boolean mFreeTierUserPlaylistEditingEnabled = AmazonApplication.getCapabilities().isFreeTierUserPlaylistEditingEnabled();
    private final PlayerModelConfiguration playerModelConfiguration = new PlayerModelConfiguration();

    public PlayerContextMenu(FragmentActivity activity) {
        this.mActivity = activity;
        this.mPlaylistTooltipExperience = new PlaylistTooltipExperience.PlaylistTooltipExperienceBuilder().setNumViews(PlaylistTooltipExperience.PlaylistTooltipExperienceBuilder.INSTANCE.getMAX_NUM_NOW_PLAYING_PAGE_VIEWS()).createPlaylistTooltipExperience(activity.getApplicationContext());
    }

    public void setLikeStateListener(final LikeStateListener likeStateListener) {
        this.mLikeStateListener = likeStateListener;
    }

    public void onCreateOptionMenu(final Menu menu, final MenuInflater inflater) {
        if (MediaItemHelper.isInterludeMediaItem(this.mPlaybackController.getCurrentMediaItem())) {
            return;
        }
        inflater.inflate(R.menu.menu_now_playing, menu);
    }

    public void onUpdateOptionMenu(final Menu menu) {
        String currentQualityBadgeTitle;
        if (!AmazonApplication.getCapabilities().shouldCloudBeSupported()) {
            menu.removeItem(R.id.MenuMoreBy);
            menu.removeItem(R.id.MenuGotoAlbum);
        }
        if (!AmazonApplication.getCapabilities().isStoreSupported() || ChildUserUtil.INSTANCE.isChildUser(this.mActivity)) {
            menu.removeItem(R.id.MenuMoreBy);
        }
        if (!NowPlayingUtil.isVideoPlaying() && !MediaProvider.Playlists.isPlaylist(MediaItemHelper.getCurrentUri())) {
            menu.removeItem(R.id.MenuGotoPlaylist);
        }
        Capabilities capabilities = AmazonApplication.getCapabilities();
        if (NowPlayingUtil.isStationPlaying() || NowPlayingUtil.isAlexaPlaying() || !CastingUtil.isPlayQueueSupported() || NowPlayingUtil.isCloudqueuePlaying() || UserSubscriptionUtil.isNightwingOnly()) {
            menu.removeItem(R.id.ViewPlayQueue);
        }
        if (!NowPlayingUtil.isStationPlaying() || NowPlayingUtil.isSFAStationPlaying() || !capabilities.isSharingSupported()) {
            menu.removeItem(R.id.MenuShareStation);
        }
        if (capabilities.shouldHideArtistLink()) {
            menu.removeItem(R.id.MenuGoToArtist);
        }
        if (!capabilities.shouldShowRecommendations()) {
            menu.removeItem(R.id.MenuSimilarRecommend);
        }
        if (!capabilities.isSpatialAudioEnabled(this.mActivity.getApplicationContext()) || NowPlayingUtil.isVideoPlaying()) {
            menu.removeItem(R.id.MenuAudioQuality);
        }
        if (!capabilities.isTrackModerationSupported() || PlatformUtil.isFireOS()) {
            menu.removeItem(R.id.MenuReport);
        }
        if (!AudioEffectController.isAudioEffectsPageSupported()) {
            menu.removeItem(R.id.MenuGoToEqualizer);
        } else {
            MenuItem menuItemFindItem = menu.findItem(R.id.MenuGoToEqualizer);
            if (menuItemFindItem != null && menuItemFindItem.getTitle() != null) {
                SpannableString spannableString = new SpannableString(menuItemFindItem.getTitle());
                spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this.mActivity, CastingUtil.isCasting() ? R.color.setting_disabled_preference_text_color : R.color.white)), 0, spannableString.length(), 0);
                menuItemFindItem.setTitle(spannableString);
            }
        }
        MediaItem currentMediaItem = this.mPlaybackController.getCurrentMediaItem();
        if (currentMediaItem == null) {
            menu.removeItem(R.id.MenuReport);
            return;
        }
        if (capabilities.isYAImmerseEnabled()) {
            String asin = MediaItemHelper.getAsin(currentMediaItem);
            Boolean boolValueOf = Boolean.valueOf(asin != null && MusicDownloader.getInstance(AmazonApplication.getApplication().getApplicationContext()).isTrackAvailableOffline(asin, true));
            if (!new RedownloadStateResolver(this.mActivity).shouldRedownloadTrack(MediaItemHelper.getAsin(currentMediaItem), true, currentMediaItem.getContentEncodings()) || !AmazonApplication.getCapabilities().isSpatialAudioEnabled(this.mActivity) || !boolValueOf.booleanValue()) {
                menu.removeItem(R.id.MenuDownloadSong);
            }
            menu.removeItem(R.id.MenuStationFromAnything);
            menu.removeItem(R.id.MenuDislikeItem);
            menu.removeItem(R.id.ViewPlayQueue);
            menu.removeItem(R.id.MenuShareSong);
            menu.removeItem(R.id.MenuSimilarRecommend);
        } else {
            menu.removeItem(R.id.MenuAudioQuality);
        }
        if ((currentMediaItem instanceof CloudQueueLibraryTrackMediaItem) && currentMediaItem.getType() == MediaItem.Type.OWNED_CONTENT && currentMediaItem.getMediaItemId(MediaItemId.Type.ASIN) == null) {
            menu.removeItem(R.id.MenuNowPlayingContextAddToPlaylist);
            menu.removeItem(R.id.MenuGotoPlaylist);
        }
        if (!MediaItemHelper.canShareSong(currentMediaItem) || !capabilities.isSharingSupported()) {
            menu.removeItem(R.id.MenuShareSong);
            if (!MediaItemHelper.isFromCirrus(currentMediaItem)) {
                menu.removeItem(R.id.MenuSimilarRecommend);
            }
        }
        updateShowDownloadMenuOption(menu, currentMediaItem);
        if (!this.playerModelConfiguration.shouldShowContextMenuViewPlayQueueButton()) {
            menu.removeItem(R.id.ViewPlayQueue);
        }
        if (NowPlayingUtil.isVideoPlaying()) {
            menu.removeItem(R.id.MenuAddSong);
            menu.removeItem(R.id.MenuDownloadSong);
            menu.removeItem(R.id.MenuAddToPlaylist);
            menu.removeItem(R.id.MenuSongCredits);
            menu.removeItem(R.id.MenuMoreBy);
            menu.removeItem(R.id.ViewPlayQueue);
            menu.removeItem(R.id.MenuSimilarRecommend);
            menu.removeItem(R.id.MenuGotoAlbum);
            menu.removeItem(R.id.MenuShareStation);
            menu.removeItem(R.id.MenuGoToEqualizer);
            menu.removeItem(R.id.MenuReport);
            MenuItem menuItemFindItem2 = menu.findItem(R.id.MenuGotoPlaylist);
            if (menuItemFindItem2 != null) {
                menuItemFindItem2.setVisible(false);
            }
        }
        MediaItemId.Type type = MediaItemId.Type.ASIN;
        if (currentMediaItem.getMediaItemId(type) == null) {
            menu.removeItem(R.id.MenuSongCredits);
            menu.removeItem(R.id.MenuAddToPlaylist);
            menu.removeItem(R.id.MenuGoToEntityPlaylist);
        }
        if (currentMediaItem.getMediaItemId(type) == null || !StationUtils.isCustomerAllowedToPlaySFAStation()) {
            menu.removeItem(R.id.MenuStationFromAnything);
        }
        if (AmazonApplication.getCapabilities().isEntityPlaylistSupported()) {
            menu.removeItem(R.id.MenuStationFromAnything);
        } else {
            menu.removeItem(R.id.MenuGoToEntityPlaylist);
        }
        for (int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.getItem(i);
            resetMenuItemForPlaylistTooltipExperience(item);
            switch (item.getItemId()) {
                case R.id.MenuAddSong /* 2131427531 */:
                    if (shouldShowAddSongMenuOption(this.mActivity, currentMediaItem)) {
                        item.setEnabled(true);
                        item.setTitle(this.mActivity.getApplication().getString(R.string.dmusic_context_add_to_library));
                    } else {
                        item.setEnabled(false);
                        item.setTitle(this.mActivity.getApplication().getString(R.string.dmusic_prime_menu_in_library));
                    }
                    StringUtil.tryResetMenuTitleColorForNightwing(item, this.mActivity);
                    continue;
                    styleMenuItemForPlaylistTooltipExperience(item);
                    break;
                case R.id.MenuAddToPlaylist /* 2131427532 */:
                    if (!this.mFreeTierUserPlaylistEditingEnabled) {
                        StringUtil.tryResetMenuTitleColorForNightwing(item, this.mActivity);
                    }
                    break;
                case R.id.MenuAudioQuality /* 2131427571 */:
                    if (this.mActivity.getApplicationContext() != null && (currentQualityBadgeTitle = NowPlayingUtil.getCurrentQualityBadgeTitle(this.mActivity.getResources())) != null) {
                        item.setTitle(((Object) item.getTitle()) + ": " + currentQualityBadgeTitle);
                    }
                    break;
                case R.id.MenuDownloadSong /* 2131427577 */:
                    boolean z = MediaItemHelper.getSource(currentMediaItem).equals("cirrus-local") && MediaItemHelper.getAsin(currentMediaItem) == null;
                    String asin2 = MediaItemHelper.getAsin(currentMediaItem);
                    Boolean boolValueOf2 = Boolean.valueOf(asin2 != null && MusicDownloader.getInstance(AmazonApplication.getApplication().getApplicationContext()).isTrackAvailableOffline(asin2, true));
                    item.setEnabled(true);
                    item.setTitle(R.string.dmusic_context_download);
                    String downloadQuality = new ContextMenuDownloadHelper().getDownloadQuality(currentMediaItem, this.mActivity);
                    if (this.mCurrentTrackDownloadState == DownloadState.DOWNLOADED || z || boolValueOf2.booleanValue()) {
                        if (new RedownloadStateResolver(this.mActivity).shouldRedownloadTrack(MediaItemHelper.getAsin(currentMediaItem), true, currentMediaItem.getContentEncodings()) && AmazonApplication.getCapabilities().isSpatialAudioEnabled(this.mActivity)) {
                            item.setEnabled(true);
                            item.setTitle(this.mActivity.getResources().getString(R.string.dmusic_context_redownload_with_quality, downloadQuality));
                        } else {
                            item.setEnabled(false);
                            item.setTitle(R.string.dmusic_download_state_downloaded);
                        }
                    } else if (AmazonApplication.getCapabilities().isSpatialAudioEnabled(this.mActivity)) {
                        new ContextMenuDownloadHelper().getDownloadQuality(currentMediaItem, this.mActivity);
                        item.setTitle(this.mActivity.getResources().getString(R.string.dmusic_context_download_with_quality, downloadQuality));
                    }
                    if (isFreeTierNonPurchasedItem(currentMediaItem)) {
                        StringUtil.dimTextColor(item, this.mActivity);
                    }
                    updateDimDownloadMenuOption(item, currentMediaItem);
                    break;
                case R.id.MenuGoToArtist /* 2131427586 */:
                    if (capabilities.isYAImmerseEnabled()) {
                        item.setTitle(R.string.menu_view_artist);
                    }
                    break;
                case R.id.MenuGotoAlbum /* 2131427589 */:
                    if (capabilities.isYAImmerseEnabled()) {
                        item.setTitle(R.string.menu_view_album);
                    }
                    break;
                case R.id.MenuGotoPlaylist /* 2131427590 */:
                    if (capabilities.isYAImmerseEnabled()) {
                        item.setTitle(R.string.menu_view_playlist);
                    }
                    break;
                case R.id.MenuMoreBy /* 2131427597 */:
                    String artistName = currentMediaItem.getArtistName();
                    DefaultStringType defaultStringType = DefaultStringType.ARTIST;
                    if (defaultStringType.isPlatformOrCirrusDefault(artistName)) {
                        artistName = defaultStringType.getDefault();
                    }
                    item.setTitle(String.format(this.mActivity.getApplication().getString(R.string.dmusic_context_shop_for), artistName));
                    break;
            }
            styleMenuItemForPlaylistTooltipExperience(item);
        }
        if (currentMediaItem instanceof AdMediaItem) {
            menu.removeItem(R.id.MenuAddToPlaylist);
            menu.removeItem(R.id.MenuAddSong);
            menu.removeItem(R.id.MenuStationFromAnything);
            menu.removeItem(R.id.MenuGoToEntityPlaylist);
        }
        if (!LikesServiceProxy.INSTANCE.shouldShowLikesFeature(currentMediaItem) || this.mLikeStateListener == null || !ConnectivityUtil.hasAnyInternetConnection()) {
            menu.removeItem(R.id.MenuDislikeItem);
        } else {
            MenuItem menuItemFindItem3 = menu.findItem(R.id.MenuDislikeItem);
            int i2 = currentMediaItem.getLikeState() == LikeState.DISLIKE ? R.string.dmusic_disliked_menu_option : R.string.dmusic_dislike_menu_option;
            if (menuItemFindItem3 != null) {
                menuItemFindItem3.setTitle(this.mActivity.getApplication().getString(i2));
            }
        }
        if (AmazonApplication.getCapabilities().isLikesEverywhereEnabled()) {
            menu.removeItem(R.id.MenuAddSong);
        }
        updatePlaylistTooltipExperience();
    }

    public Action1<MenuItem> getClickAction() {
        return new Action1<MenuItem>() { // from class: com.amazon.mp3.playback.PlayerContextMenu.2
            @Override // rx.functions.Action1
            public void call(MenuItem menuItem) {
                PlayerContextMenu.this.onOptionsItemSelected(menuItem);
            }
        };
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public boolean onOptionsItemSelected(final MenuItem item) {
        int currentIndex;
        UiClickEvent uiClickEventBuild;
        sendMetricsEventOnOptionsItemSelected(item);
        MediaItem currentMediaItem = this.mPlaybackController.getCurrentMediaItem();
        if (currentMediaItem != null) {
            currentMediaItem.setLikesProvider(new MediaItemLikesProviderImpl(this.mActivity.getApplicationContext()));
        }
        String asin = MediaItemHelper.getAsin(currentMediaItem);
        Uri currentUri = MediaItemHelper.getCurrentUri();
        boolean zIsStation = MediaProvider.Station.isStation(currentUri);
        switch (item.getItemId()) {
            case R.id.MenuAddSong /* 2131427531 */:
                if (UserSubscriptionUtil.isNightwingOnly()) {
                    displayBlockingUpsell(UpsellReason.ADD_TO_LIBRARY, asin);
                    return true;
                }
                addPrimeTrack();
                return true;
            case R.id.MenuAddToPlaylist /* 2131427532 */:
                Intent startIntentForCurrentItem = AddToPlaylistPopupActivity.getStartIntentForCurrentItem(this.mActivity, PageType.NOW_PLAYING);
                if (startIntentForCurrentItem == null) {
                    Log.error(TAG, "Unable to add current item to playlist");
                } else {
                    this.mActivity.startActivity(startIntentForCurrentItem);
                }
                return true;
            case R.id.MenuAudioQuality /* 2131427571 */:
                this.mActivity.startActivity(new Intent(AmazonApplication.getContext(), (Class<?>) AudioStatsFragmentActivity.class));
                MetricsHolder.getManager().handleEvent(UiClickEvent.builder(ActionType.SELECT_STREAMING_INFORMATION_OVERFLOW).withInteractionType(InteractionType.TAP).withPageType(PageType.NOW_PLAYING_OVERFLOW).withEventTime(System.currentTimeMillis()).build());
                return true;
            case R.id.MenuDislikeItem /* 2131427574 */:
                if (currentMediaItem != null && currentMediaItem.getCanLike() && this.mLikeStateListener != null) {
                    LikeState likeState = currentMediaItem.getLikeState();
                    LikeState likeState2 = LikeState.DISLIKE;
                    LikeState likeState3 = likeState == likeState2 ? LikeState.NEUTRAL : likeState2;
                    boolean z = likeState3 == likeState2;
                    int i = z ? R.string.dmusic_dislike_action_toast : R.string.dmusic_un_dislike_action_toast;
                    int i2 = z ? R.string.dmusic_disliked_menu_option : R.string.dmusic_dislike_menu_option;
                    ActionType actionType = ActionType.LAUNCH_APP;
                    int i3 = z ? 2 : 0;
                    item.setTitle(this.mActivity.getApplication().getString(i2));
                    if (this.mPlaybackController.canRateMediaItems()) {
                        try {
                            currentIndex = this.mPlaybackController.getCurrentIndex();
                        } catch (Exception unused) {
                            Log.debug(TAG, "Sequencer does not support indices.");
                            currentIndex = 0;
                        }
                        currentMediaItem.setRating(i3, currentIndex);
                    } else {
                        currentMediaItem.setLikeState(likeState3);
                    }
                    Boolean boolValueOf = Boolean.valueOf(likeState3 == LikeState.LIKE);
                    if (asin != null) {
                        LikesStateCacheManager.INSTANCE.getInstance(AmazonApplication.getContext()).add(asin, boolValueOf.booleanValue());
                        LikesServiceProxy.INSTANCE.updateMyLikesDB(this.mActivity, currentMediaItem, likeState3);
                    }
                    if (z) {
                        BauhausToastUtils.showTextToast(this.mActivity, i, 0);
                        this.mLikeStateListener.onDislike(currentMediaItem.getMediaItemId().getId());
                        PlaybackController playbackController = this.mPlaybackController;
                        if (playbackController != null && playbackController.canSkipNext()) {
                            this.mPlaybackController.skipNext();
                        }
                    }
                    break;
                }
                return true;
            case R.id.MenuDownloadSong /* 2131427577 */:
                if (isFreeTierNonPurchasedItem(currentMediaItem) || this.isDownloadUpsell) {
                    displayBlockingUpsell(UpsellReason.DOWNLOAD_CONTENT, asin);
                    return true;
                }
                addPrimeTrack();
                if (MediaItemHelper.canDownloadMediaItem(currentMediaItem)) {
                    downloadTrack(currentMediaItem);
                }
                return true;
            case R.id.MenuGoToArtist /* 2131427586 */:
                if (currentMediaItem != null) {
                    this.mActivity.startActivity(LibraryActivityFactory.getArtistDetailsIntent(this.mActivity, currentMediaItem.getArtistName(), MediaItemHelper.getArtistAsin(currentMediaItem)));
                }
                return true;
            case R.id.MenuGoToEntityPlaylist /* 2131427587 */:
                if (currentMediaItem != null) {
                    MediaItemId.Type type = MediaItemId.Type.ASIN;
                    if (currentMediaItem.getMediaItemId(type) != null) {
                        this.mActivity.startActivity(EntityPlaylistUtil.buildEntityPlaylistIntent(this.mActivity.getApplicationContext(), currentMediaItem.getMediaItemId(type).getId(), PlaylistSeedEntityType.TRACK));
                        this.mActivity.overridePendingTransition(R.anim.zoom_in, R.anim.fragment_fade_out);
                    }
                }
                return true;
            case R.id.MenuGoToEqualizer /* 2131427588 */:
                if (CastingUtil.isCasting()) {
                    new EqualizerDialogBuilder(this.mActivity).getUnavailableWhileCastingDialog().show();
                } else if (AudioEffectController.isAudioEffectsPageSupported()) {
                    this.mActivity.startActivity(new Intent(this.mActivity, (Class<?>) AudioEffectActivity.class));
                }
                if (AmazonApplication.getCapabilities().isYAImmerseEnabled()) {
                    uiClickEventBuild = UiClickEvent.builder(ActionType.GO_NOW_PLAYING_EQUALIZER).withInteractionType(InteractionType.TAP).withPageType(PageType.NOW_PLAYING_OVERFLOW).withEventTime(System.currentTimeMillis()).build();
                } else {
                    uiClickEventBuild = UiClickEvent.builder(ActionType.GO_NOW_PLAYING_EQUALIZER).withInteractionType(InteractionType.TAP).withEventTime(System.currentTimeMillis()).build();
                }
                MetricsHolder.getManager().handleEvent(uiClickEventBuild);
                return true;
            case R.id.MenuGotoAlbum /* 2131427589 */:
                Intent albumDetailIntent = NowPlayingUtil.getAlbumDetailIntent(this.mActivity);
                if (albumDetailIntent != null) {
                    if (MediaItemHelper.isPureCatalog(currentMediaItem) || zIsStation) {
                        if (AmazonApplication.getCapabilities().isBauhausADPEnabled()) {
                            albumDetailIntent.putExtra("EXTRA_ALBUM_ASIN", MediaItemHelper.getAlbumAsin(currentMediaItem));
                        } else {
                            albumDetailIntent.putExtra("com.amazon.mp3.library.activity.EXTRA_ALBUM_ASIN", MediaItemHelper.getAlbumAsin(currentMediaItem));
                            albumDetailIntent.putExtra("com.amazon.mp3.EXTRA_ALWAYS_SHOW_NON_LIB_CONTENT", true);
                        }
                    }
                    this.mActivity.startActivity(albumDetailIntent);
                    this.mActivity.overridePendingTransition(R.anim.zoom_in, R.anim.fragment_fade_out);
                }
                return true;
            case R.id.MenuGotoPlaylist /* 2131427590 */:
                if (MediaProvider.Playlists.isPlaylist(currentUri)) {
                    this.mActivity.startActivity(LibraryActivityFactory.getIntentForContentUri(this.mActivity, currentUri));
                    this.mActivity.overridePendingTransition(R.anim.zoom_in, R.anim.fragment_fade_out);
                } else {
                    Log.error(TAG, "Unable to navigate to a playlist for " + currentUri);
                }
                return true;
            case R.id.MenuMoreBy /* 2131427597 */:
                this.mActivity.startActivity(NowPlayingUtil.getArtistExploreIntent(this.mActivity));
                return true;
            case R.id.MenuReport /* 2131427636 */:
                new ModerationHandler(this.mActivity, MediaItemHelper.getAbstractItem(currentMediaItem, true, null), ModerationEntity.NOW_PLAYING_TRACK).startModeration();
                return true;
            case R.id.MenuShareSong /* 2131427638 */:
                return MediaItemHelper.shareMediaItem(this.mActivity, currentMediaItem);
            case R.id.MenuShareStation /* 2131427639 */:
                final String key = MediaProvider.Station.getKey(currentUri);
                final String musicDomain = EndpointsProvider.get().getMusicDomain();
                Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.playback.PlayerContextMenu$$ExternalSyntheticLambda6
                    @Override // rx.functions.Action1
                    public final void call(Object obj) {
                        this.f$0.lambda$onOptionsItemSelected$0(key, (Subscriber) obj);
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.mp3.playback.PlayerContextMenu$$ExternalSyntheticLambda7
                    @Override // rx.functions.Action1
                    public final void call(Object obj) {
                        this.f$0.lambda$onOptionsItemSelected$1(musicDomain, (StationItem) obj);
                    }
                }, new Action1() { // from class: com.amazon.mp3.playback.PlayerContextMenu$$ExternalSyntheticLambda8
                    @Override // rx.functions.Action1
                    public final void call(Object obj) {
                        PlayerContextMenu.lambda$onOptionsItemSelected$2((Throwable) obj);
                    }
                });
                return true;
            case R.id.MenuSimilarRecommend /* 2131427640 */:
                if (currentMediaItem != null) {
                    String albumAsin = MediaItemHelper.getAlbumAsin(currentMediaItem);
                    Intent startIntent = MusicHomeActivity.getStartIntent(this.mActivity);
                    startIntent.putExtra("com.amazon.mp3.fragment.extra", SimilarRecommendationsFragment.class.getSimpleName());
                    startIntent.putExtra("SIMILAR_RECS_FRAGMENT_ASIN_KEY", albumAsin);
                    startIntent.putExtra("SIMILAR_RECS_FRAGMENT_DATA_TYPE_KEY", PaginatedDataType.TRACK);
                    this.mActivity.startActivity(startIntent);
                }
                return true;
            case R.id.MenuSongCredits /* 2131427641 */:
                if (currentMediaItem != null) {
                    MediaItemId.Type type2 = MediaItemId.Type.ASIN;
                    if (currentMediaItem.getMediaItemId(type2) != null) {
                        Intent intent = new Intent(this.mActivity, (Class<?>) SongCreditsActivity.class);
                        intent.putExtra("com.amazon.mp3.EXTRA_SONG_CREDITS_ASIN", currentMediaItem.getMediaItemId(type2).getId());
                        this.mActivity.startActivity(intent);
                        this.mActivity.overridePendingTransition(R.anim.zoom_in, R.anim.fragment_fade_out);
                    }
                }
                return true;
            case R.id.MenuStationFromAnything /* 2131427643 */:
                if (currentMediaItem != null) {
                    MediaItemId.Type type3 = MediaItemId.Type.ASIN;
                    if (currentMediaItem.getMediaItemId(type3) != null) {
                        String id = currentMediaItem.getMediaItemId(type3).getId();
                        StationUtils.startSFA(this.mActivity, id, "TRACK_SEED", PlaybackPageType.PLAYLISTS_LIST, ControlSource.APP_UI);
                        StationUtils.sendStartSFAUiClickMetrics(id, EntityIdType.SFA_TRACK_SEED);
                    }
                }
                return true;
            case R.id.ViewPlayQueue /* 2131427920 */:
                Intent nowPlayingQueueIntent = NowPlayingQueueUtil.getNowPlayingQueueIntent(this.mActivity);
                if (nowPlayingQueueIntent != null) {
                    this.mActivity.startActivity(nowPlayingQueueIntent);
                    return true;
                }
                return true;
            default:
                return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onOptionsItemSelected$0(String str, Subscriber subscriber) {
        StationItem stationItemByKey = null;
        try {
            PlaybackProvider playbackProvider = Playback.getInstance().getPlaybackProvider();
            if ((playbackProvider instanceof SequencerPlaybackProvider) && (((SequencerPlaybackProvider) playbackProvider).getSequencer() instanceof StationSequencer)) {
                stationItemByKey = ((StationSequencer) ((SequencerPlaybackProvider) playbackProvider).getSequencer()).getStationItem();
            }
            if (stationItemByKey == null) {
                stationItemByKey = StationsFactory.getInstance(this.mActivity).getStationManager().getStationItemByKey(str);
            }
            subscriber.onNext(stationItemByKey);
            subscriber.onCompleted();
        } catch (StationException | StationNotFoundException e) {
            subscriber.onError(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onOptionsItemSelected$1(String str, StationItem stationItem) {
        new StationShareProvider(this.mActivity, str, stationItem.getKey(), stationItem.getTitle(), stationItem.getImageUrl()).startShare();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$onOptionsItemSelected$2(Throwable th) {
        Log.error(TAG, "Failed to get station item to share", th);
    }

    private void canPresentTooltip(final Action1<Boolean> nextAction, final Action1<Throwable> errorAction) {
        this.tooltipSubscription = Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.playback.PlayerContextMenu$$ExternalSyntheticLambda11
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                this.f$0.lambda$canPresentTooltip$3((Subscriber) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.computation()).subscribe(nextAction, errorAction);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$canPresentTooltip$3(Subscriber subscriber) {
        subscriber.onNext(Boolean.valueOf(this.mPlaylistTooltipExperience.canPresentTooltip()));
        subscriber.onCompleted();
    }

    private void styleMenuItemForPlaylistTooltipExperience(final MenuItem item) {
        canPresentTooltip(new Action1() { // from class: com.amazon.mp3.playback.PlayerContextMenu$$ExternalSyntheticLambda4
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                this.f$0.lambda$styleMenuItemForPlaylistTooltipExperience$4(item, (Boolean) obj);
            }
        }, new Action1() { // from class: com.amazon.mp3.playback.PlayerContextMenu$$ExternalSyntheticLambda5
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                PlayerContextMenu.lambda$styleMenuItemForPlaylistTooltipExperience$5((Throwable) obj);
            }
        });
        this.tooltipSubscription.unsubscribe();
        this.tooltipSubscription = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$styleMenuItemForPlaylistTooltipExperience$4(MenuItem menuItem, Boolean bool) {
        if (bool.booleanValue()) {
            if (menuItem.getItemId() == R.id.MenuAddToPlaylist) {
                StringUtil.boldTextColor(menuItem);
            } else {
                StringUtil.dimTextColor(menuItem, this.mActivity);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$styleMenuItemForPlaylistTooltipExperience$5(Throwable th) {
        Log.error(TAG, "Error: " + th.getMessage());
    }

    private void resetMenuItemForPlaylistTooltipExperience(MenuItem item) {
        if (this.isShowingPlaylistTooltipExperience) {
            StringUtil.setDefaultTextStyle(item, this.mActivity);
        }
    }

    private void updatePlaylistTooltipExperience() {
        canPresentTooltip(new Action1() { // from class: com.amazon.mp3.playback.PlayerContextMenu$$ExternalSyntheticLambda9
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                this.f$0.lambda$updatePlaylistTooltipExperience$6((Boolean) obj);
            }
        }, new Action1() { // from class: com.amazon.mp3.playback.PlayerContextMenu$$ExternalSyntheticLambda10
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                PlayerContextMenu.lambda$updatePlaylistTooltipExperience$7((Throwable) obj);
            }
        });
        this.tooltipSubscription.unsubscribe();
        this.tooltipSubscription = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$updatePlaylistTooltipExperience$6(Boolean bool) {
        if (bool.booleanValue()) {
            this.isShowingPlaylistTooltipExperience = true;
        } else if (this.isShowingPlaylistTooltipExperience) {
            this.isShowingPlaylistTooltipExperience = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updatePlaylistTooltipExperience$7(Throwable th) {
        Log.error(TAG, "Error: " + th.getMessage());
    }

    private void sendMetricsEventOnOptionsItemSelected(final MenuItem item) {
        MediaItem currentMediaItem = this.mPlaybackController.getCurrentMediaItem();
        String asin = MediaItemHelper.getAsin(currentMediaItem);
        String albumAsin = MediaItemHelper.getAlbumAsin(currentMediaItem);
        String artistAsin = MediaItemHelper.getArtistAsin(currentMediaItem);
        if (currentMediaItem == null) {
        }
        switch (item.getItemId()) {
            case R.id.MenuAddSong /* 2131427531 */:
                sendMetricEvent(ActionType.ADD_TO_LIBRARY_OVERFLOW, InteractionType.TAP, asin, EntityIdType.ASIN);
                break;
            case R.id.MenuAddToPlaylist /* 2131427532 */:
                sendMetricEvent(ActionType.ADD_TO_PLAYLIST_ATTEMPT_OVERFLOW, InteractionType.TAP, asin, EntityIdType.ASIN);
                break;
            case R.id.MenuDislikeItem /* 2131427574 */:
                LikeState likeState = currentMediaItem.getLikeState();
                LikeState likeState2 = LikeState.DISLIKE;
                sendMetricEvent((likeState == likeState2 ? LikeState.NEUTRAL : likeState2) == likeState2 ? ActionType.THUMBS_DOWN_OVERFLOW : ActionType.THUMBS_DOWN_UNDO_OVERFLOW, InteractionType.TAP, asin, EntityIdType.ASIN, this.mPlaybackController.canRateMediaItems() ? null : "onDemand");
                break;
            case R.id.MenuDownloadSong /* 2131427577 */:
                ActionType actionType = ActionType.DOWNLOAD_OVERFLOW;
                if (currentMediaItem.getMediaAccessInfo() != null && currentMediaItem.getMediaAccessInfo().isAvailableOffline()) {
                    actionType = ActionType.REDOWNLOAD_OVERFLOW;
                }
                sendMetricEvent(actionType, InteractionType.TAP, asin, EntityIdType.ASIN);
                break;
            case R.id.MenuGoToArtist /* 2131427586 */:
                sendMetricEvent(ActionType.VIEW_ARTIST_OVERFLOW, InteractionType.TAP, artistAsin, EntityIdType.ASIN);
                break;
            case R.id.MenuGoToEntityPlaylist /* 2131427587 */:
                if (asin != null) {
                    sendMetricEvent(ActionType.GO_ENTITY_PLAYLIST, InteractionType.TAP, asin, EntityIdType.ASIN);
                }
                break;
            case R.id.MenuGotoAlbum /* 2131427589 */:
                sendMetricEvent(ActionType.VIEW_ALBUM_OVERFLOW, InteractionType.TAP, albumAsin, EntityIdType.ASIN);
                break;
            case R.id.MenuGotoPlaylist /* 2131427590 */:
                sendMetricEvent(ActionType.GO_PLAYLISTS, InteractionType.TAP, asin, EntityIdType.ASIN);
                break;
            case R.id.MenuMoreBy /* 2131427597 */:
                sendMetricEvent(ActionType.VIEW_ARTIST_OVERFLOW, InteractionType.TAP, artistAsin, EntityIdType.ASIN);
                MetricsLogger.clickedOnStoreLink(StoreLinkType.ARTIST);
                break;
            case R.id.MenuReport /* 2131427636 */:
                sendMetricEvent(ActionType.REPORT_OVERFLOW, InteractionType.TAP, asin, EntityIdType.ASIN);
                break;
            case R.id.MenuSimilarRecommend /* 2131427640 */:
                sendMetricEvent(ActionType.CUSTOMERS_ALSO_LISTENED_TO_OVERFLOW, InteractionType.TAP, asin, EntityIdType.ASIN);
                break;
            case R.id.MenuSongCredits /* 2131427641 */:
                if (asin != null) {
                    sendMetricEvent(ActionType.GO_SONG_CREDITS, InteractionType.TAP, asin, EntityIdType.ASIN);
                }
                break;
            case R.id.ViewPlayQueue /* 2131427920 */:
                sendMetricEvent(ActionType.GO_PLAY_QUEUE, InteractionType.TAP, asin, EntityIdType.PLAYLIST_ID);
                break;
        }
    }

    public void addPrimeTrack() {
        MediaItem currentMediaItem = this.mPlaybackController.getCurrentMediaItem();
        LibraryAccessProviderImpl.getInstance(this.mActivity).addToLibrary(currentMediaItem);
        if (currentMediaItem != null) {
            String asin = MediaItemHelper.getAsin(currentMediaItem);
            Uri currentUri = MediaItemHelper.getCurrentUri();
            MetricsLogger.clickedAddPrimeContent(LinkType.TRACK, asin, SelectionSourceHelper.getSelectionSourceType(currentUri), SelectionSourceHelper.getSelectionSourceId(currentUri, currentMediaItem, currentMediaItem.getSourceMediaCollectionInfo() != null ? currentMediaItem.getSourceMediaCollectionInfo().getName() : ""));
        }
    }

    public void downloadTrack(final MediaItem item) {
        String asin = MediaItemHelper.getAsin(item);
        new AddAndDownloadTrackTask((Context) this.mActivity, asin, true, true, item.getMediaAccessInfo().isAvailableOffline(), (OnItemFinishedAddingListener) null).execute(new Void[0]);
        this.mDownloadListener.addRequestId(asin);
        DownloadListener downloadListener = this.mConsumerDownloadListener;
        if (downloadListener != null) {
            downloadListener.addRequestId(asin);
        }
    }

    public static void sendMetricEvent(final ActionType actionType, final InteractionType interactionType, final String entityId, final EntityIdType entityIdType) {
        sendMetricEvent(actionType, interactionType, entityId, entityIdType, null);
    }

    public static void sendMetricEvent(final ActionType actionType, final InteractionType interactionType, final String entityId, final EntityIdType entityIdType, final String pageSubType) {
        MetricsUtil.emitLatencyStartLegMetricForAllowedActionTypes(actionType);
        PageType pageType = AmazonApplication.getCapabilities().isYAImmerseEnabled() ? PageType.NOW_PLAYING_OVERFLOW : PageType.NOW_PLAYING;
        if (actionType != null) {
            MetricsHolder.getManager().handleEvent(UiClickEvent.builder(actionType).withInteractionType(interactionType).withEntityId(entityId).withEntityIdType(entityIdType).withEventTime(System.currentTimeMillis()).withPageType(pageType).withPageSubType(pageSubType).build());
        }
    }

    public static void sendMetricEvent(final PageType pageType, final ActionType actionType, final InteractionType interactionType, final EntityType entityType) {
        if (pageType == null || actionType == null) {
            return;
        }
        MetricsHolder.getManager().handleEvent(UiClickEvent.builder(actionType).withInteractionType(interactionType).withEntityType(entityType).withEventTime(System.currentTimeMillis()).withPageType(pageType).build());
    }

    public static boolean shouldShowAddSongMenuOption(final Activity activity, final MediaItem item) {
        if (item == null) {
            return false;
        }
        return !LibraryAccessProviderImpl.getInstance(activity).isInLibrary(item) && (item.getMediaAccessInfo().getContentAccessTypes().contains(ContentAccessType.HAWKFIRE) || item.getMediaAccessInfo().getContentAccessTypes().contains(ContentAccessType.PRIME));
    }

    private void displayBlockingUpsell(final UpsellReason upsellReason, final String asin) {
        UpsellUtil.showBlockingUpsell(this.mActivity, asin, upsellReason, UpsellSourceEntity.PLAYLIST);
    }

    private boolean isFreeTierNonPurchasedItem(final MediaItem mediaItem) {
        if (mediaItem == null) {
            return false;
        }
        MediaAccessInfo mediaAccessInfo = mediaItem.getMediaAccessInfo();
        return UserSubscriptionUtil.isNightwingOnly() && !(mediaAccessInfo != null && mediaAccessInfo.isOwned());
    }

    private boolean isDownloadUpsell(final AbstractItem abstractItem) {
        return abstractItem != null && this.playerModelConfiguration.shouldShowContextMenuDownloadUpsellButton(abstractItem);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: updateShowDownloadMenuOptionSubscribe, reason: merged with bridge method [inline-methods] */
    public void lambda$updateShowDownloadMenuOption$9(Menu menu, AbstractItem abstractItem) {
        if (this.playerModelConfiguration.shouldShowContextMenuDownloadButton(abstractItem)) {
            return;
        }
        menu.removeItem(R.id.MenuDownloadSong);
    }

    private void updateShowDownloadMenuOption(final Menu menu, MediaItem mediaItem) {
        MediaItemHelper.getAbstractItemObservable(mediaItem).subscribe(new Consumer() { // from class: com.amazon.mp3.playback.PlayerContextMenu$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) throws Throwable {
                this.f$0.lambda$updateShowDownloadMenuOption$9(menu, (AbstractItem) obj);
            }
        }, new Consumer() { // from class: com.amazon.mp3.playback.PlayerContextMenu$$ExternalSyntheticLambda1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) throws Throwable {
                this.f$0.lambda$updateShowDownloadMenuOption$10(menu, (Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$updateShowDownloadMenuOption$10(Menu menu, Throwable th) throws Throwable {
        th.printStackTrace();
        Log.error(TAG, "Failed converting MediaItem to AbstractItem. Removing download menu option", th);
        lambda$updateShowDownloadMenuOption$9(menu, null);
    }

    private void updateDimDownloadMenuOption(final MenuItem menuItem, MediaItem mediaItem) {
        MediaItemHelper.getAbstractItemObservable(mediaItem).subscribe(new Consumer() { // from class: com.amazon.mp3.playback.PlayerContextMenu$$ExternalSyntheticLambda2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) throws Throwable {
                this.f$0.lambda$updateDimDownloadMenuOption$11(menuItem, (AbstractItem) obj);
            }
        }, new Consumer() { // from class: com.amazon.mp3.playback.PlayerContextMenu$$ExternalSyntheticLambda3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) throws Throwable {
                PlayerContextMenu.lambda$updateDimDownloadMenuOption$12((Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$updateDimDownloadMenuOption$11(MenuItem menuItem, AbstractItem abstractItem) throws Throwable {
        if (isDownloadUpsell(abstractItem)) {
            this.isDownloadUpsell = true;
            if (ActivityKt.isAvailable(this.mActivity)) {
                StringUtil.dimTextColor(menuItem, this.mActivity);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateDimDownloadMenuOption$12(Throwable th) throws Throwable {
        th.printStackTrace();
        Log.error(TAG, "Failed converting MediaItem to AbstractItem", th);
    }
}
