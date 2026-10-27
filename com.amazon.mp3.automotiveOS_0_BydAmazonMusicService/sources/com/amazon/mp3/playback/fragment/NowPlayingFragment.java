package com.amazon.mp3.playback.fragment;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.MotionEventCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.amazon.digitalmusicxp.models.Queue;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.SubscriptionTier;
import com.amazon.mp3.activity.WideDialogActivity;
import com.amazon.mp3.activity.WideDialogActivityPurpose;
import com.amazon.mp3.activity.settings.SettingsActivity;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.animation.ImageViewAnimations;
import com.amazon.mp3.animation.StationLoadingAnimation;
import com.amazon.mp3.audioquality.nerdstats.AudioStatsFragmentActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.capability.FeatureFlagsProvider;
import com.amazon.mp3.casting.CastingButtonHelper;
import com.amazon.mp3.casting.CastingImageButton;
import com.amazon.mp3.casting.CastingUtil;
import com.amazon.mp3.cloudqueue.FreeTierPlaybackUtil;
import com.amazon.mp3.cloudqueue.metrics.CloudQueueUIMetricsManager;
import com.amazon.mp3.cloudqueue.sequencer.AdMediaItem;
import com.amazon.mp3.cloudqueue.sequencer.CloudQueueLibraryTrackMediaItem;
import com.amazon.mp3.cloudqueue.sequencer.CloudQueueSequencer;
import com.amazon.mp3.cloudqueue.sequencer.FreeTierSequencer;
import com.amazon.mp3.cloudqueue.sequencer.StormTrackMediaItem;
import com.amazon.mp3.dagger.DaggerSubcomponents;
import com.amazon.mp3.dynamicbackground.DynamicBackgroundEffectUtils;
import com.amazon.mp3.dynamicbackground.DynamicBackgroundImageCondition;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.fragment.BaseFragment;
import com.amazon.mp3.fragment.FragmentAudioStats;
import com.amazon.mp3.fragment.contextmenu.shoveler.ContextMenuAddToPlaylistBaseFragment;
import com.amazon.mp3.fragment.contextmenu.shoveler.ShovelerExtensionFunctionsKt;
import com.amazon.mp3.hawkfire.upsell.SubscriptionManagerSingleton;
import com.amazon.mp3.hawkfire.upsell.UnlimitedUpsellActivity;
import com.amazon.mp3.inappmessaging.DynamicMessagingManager;
import com.amazon.mp3.library.activity.LibraryActivityFactory;
import com.amazon.mp3.library.cache.image.CacheManager;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.library.cache.likes.LikesStateCacheManager;
import com.amazon.mp3.library.dialog.NetworkErrorDialog;
import com.amazon.mp3.library.fragment.DeleteContentHandler;
import com.amazon.mp3.library.fragment.LibraryBaseFragment;
import com.amazon.mp3.library.fragment.LyricsFragment;
import com.amazon.mp3.library.item.TrackType;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase;
import com.amazon.mp3.library.provider.source.nowplaying.AlexaMediaItem;
import com.amazon.mp3.library.provider.source.nowplaying.LibraryMediaItem;
import com.amazon.mp3.library.provider.source.nowplaying.MediaItemHelper;
import com.amazon.mp3.library.provider.source.nowplaying.NowPlayingManager;
import com.amazon.mp3.library.service.sync.TrackSyncUtil;
import com.amazon.mp3.likes.LikeStateListener;
import com.amazon.mp3.likes.LikesServiceProxy;
import com.amazon.mp3.likes.MediaItemLikesProviderImpl;
import com.amazon.mp3.lyrics.item.Lyrics;
import com.amazon.mp3.navigation.DeeplinkIntentKey;
import com.amazon.mp3.playback.FrugalLyricsListener;
import com.amazon.mp3.playback.KatanaNPSBadgeProvider;
import com.amazon.mp3.playback.OnLyricsAvailableListener;
import com.amazon.mp3.playback.OnNoLyricsDisplayedListener;
import com.amazon.mp3.playback.PlayStateMutationReason;
import com.amazon.mp3.playback.PlaybackErrorReceiver;
import com.amazon.mp3.playback.PlayerContextMenu;
import com.amazon.mp3.playback.PlaylistTooltipExperience;
import com.amazon.mp3.playback.SkipLimitTooltipPresenter;
import com.amazon.mp3.playback.activity.NowPlayingFragmentActivity;
import com.amazon.mp3.playback.activity.NowPlayingUtil;
import com.amazon.mp3.playback.casting.CastingMediaItem;
import com.amazon.mp3.playback.fragment.PersistentPlayerFragment;
import com.amazon.mp3.playback.harley.LibraryAccessProviderImpl;
import com.amazon.mp3.playback.harley.PlayQueueSequencer;
import com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController;
import com.amazon.mp3.playback.playbackcontrol.OnLyricsLineUpdateListener;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.playback.usecase.AudioVideoToggleUseCase;
import com.amazon.mp3.playback.video.VideoUtil;
import com.amazon.mp3.playback.view.NowPlayingContextMenu;
import com.amazon.mp3.playback.view.NowPlayingPersistentPlayer;
import com.amazon.mp3.playback.view.PersistentPlayer;
import com.amazon.mp3.playback.view.PlayerViewConfig;
import com.amazon.mp3.playback.view.lyrics.FrugalLyricsChecker;
import com.amazon.mp3.playback.view.lyrics.LyricsTextViewContainer;
import com.amazon.mp3.prime.CTAPrimeCache;
import com.amazon.mp3.prime.browse.BrowseFactory;
import com.amazon.mp3.prime.cta.GetTrackPrimeStatusJob;
import com.amazon.mp3.prime.requester.HybridQueueType;
import com.amazon.mp3.prime.upsell.nightwing.UpsellUtil;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellInformation;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellMessagingDialog;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellReason;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellSourceEntity;
import com.amazon.mp3.service.job.Job;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.store.dialog.NoConnectionDialog;
import com.amazon.mp3.store.util.ImageCache;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.mp3.upsellweb.OfferPageWebTargetBuilderFactory;
import com.amazon.mp3.util.ArtworkSizeUtil;
import com.amazon.mp3.util.AutoPlaySFAUtil;
import com.amazon.mp3.util.BauhausDialogBuilder;
import com.amazon.mp3.util.BauhausToastUtils;
import com.amazon.mp3.util.BitmapUtil;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.DefaultStringType;
import com.amazon.mp3.util.ImageUrlModifier;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.MediaPlaybackUtil;
import com.amazon.mp3.util.PlatformUtil;
import com.amazon.mp3.util.RunnableDebouncer;
import com.amazon.mp3.util.ScreenUtil;
import com.amazon.mp3.util.StringUtil;
import com.amazon.mp3.util.UniqueCodeUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.mp3.util.extensions.ActivityKt;
import com.amazon.mp3.util.extensions.CollectionKt;
import com.amazon.mp3.util.extensions.FragmentKt;
import com.amazon.mp3.util.extensions.MediaItemExtensions;
import com.amazon.mp3.util.extensions.ViewExtensions;
import com.amazon.mp3.view.AlbumArtAndSeekBarLayout;
import com.amazon.mp3.view.BauhausRibbon;
import com.amazon.mp3.view.BauhausSeekBarWidget;
import com.amazon.mp3.view.MessagingDialog;
import com.amazon.mp3.view.TrackRatingListener;
import com.amazon.mp3.view.player.PlayerMetadataView;
import com.amazon.mp3.view.stage.StageViewPresenter;
import com.amazon.mp3.view.stage.overlay.OverlayType;
import com.amazon.mp3.view.stage.util.StageLyricsUtil;
import com.amazon.mp3.web.webtarget.WebTarget;
import com.amazon.mp3.weblab.Weblab;
import com.amazon.music.ContentAccessType;
import com.amazon.music.explore.fragment.StageSwipeablePagesFragment;
import com.amazon.music.featureflag.FeatureFlagUnavailableException;
import com.amazon.music.freetier.featuregating.FMPMFeatureGating;
import com.amazon.music.inappmessaging.external.model.DynamicMessageEvent;
import com.amazon.music.inappmessaging.external.model.DynamicMessageMetricsAttributes;
import com.amazon.music.inappmessaging.external.model.Trigger;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaCollectionId;
import com.amazon.music.media.playback.MediaCollectionInfo;
import com.amazon.music.media.playback.MediaCollectionType;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaItemId;
import com.amazon.music.media.playback.OnMetadataChangedListener;
import com.amazon.music.media.playback.OnPlayStateChangedListener;
import com.amazon.music.media.playback.OnPlaybackEventListener;
import com.amazon.music.media.playback.PlayStatus;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.PlaybackProvider;
import com.amazon.music.media.playback.SequencerPlaybackProvider;
import com.amazon.music.media.playback.ratings.LikeState;
import com.amazon.music.media.playback.sequencer.impl.StationSequencer;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.latency.LatencyEventDescriptor;
import com.amazon.music.metrics.mts.event.definition.latency.LatencyTracker;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiPageViewEvent;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.LatencyTrackingLeg;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.PageLoadLatencyCode;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContainerType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContentName;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.platform.data.WeblabTreatment;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.providers.ExperimentFeatureGateProvider;
import com.amazon.music.platform.providers.InterfaceProvider;
import com.amazon.music.platform.providers.WeblabProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.AlphaKey;
import com.amazon.music.views.library.styles.keys.BlurStyleKey;
import com.amazon.music.views.library.styles.keys.ColorKey;
import com.amazon.music.views.library.styles.keys.CornerSizeKey;
import com.amazon.music.views.library.styles.keys.GradientStyleKey;
import com.amazon.music.views.library.styles.keys.LandscapeType;
import com.amazon.music.views.library.styles.keys.ScreenSizeKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.podcast.Podcast;
import com.amazon.ui.foundations.styles.GridSize;
import com.amazon.ui.foundations.utils.BitmapEffectUtils;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import com.amazon.ui.foundations.utils.SpacerParser;
import com.amazon.ui.foundations.views.BaseImage;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.squareup.picasso.Target;
import java.lang.ref.WeakReference;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;
import rx.subscriptions.CompositeSubscription;

/* JADX INFO: loaded from: classes3.dex */
public class NowPlayingFragment extends LibraryBaseFragment implements DeleteContentHandler, View.OnClickListener, OnLyricsLineUpdateListener, LyricsTextViewContainer.LyricsWindowInteractionListener, FrugalLyricsListener {
    private Subscription albumArtLoadSubscription;
    private Target albumArtLoadTarget;
    private PublishSubject<BitmapDrawable> artLoadSubject;
    private Subscription backgroundArtLoad;
    private Subscription badgeSubscription;
    private String badgeText;
    private Subscription blurBackgroundSubscription;
    private CompositeSubscription compositeSubscription;
    private boolean doesPortraitArtRequirePartialBlur;
    private final boolean isFreeTrialEligible;
    private Boolean isLyricsInAutomatedPreview;
    private boolean isNonBlockingDialogueSFA;
    private Subscription localTrackIdSubscription;
    private final View.OnClickListener mAdUpsellOnClickListener;
    private ImageButton mAdsCloseButton;
    private RelativeLayout mAdsDismissableArea;
    private RelativeLayout mAdsUpsellArea;
    private LinearLayout mAdsUpsellBanner;
    private BaseImage mAlbumArt;
    private AlbumArtAndSeekBarLayout mAlbumArtAndSeekBarLayout;
    private Bitmap mAlbumArtBitmapBlurred;
    private Drawable mAlbumArtDrawable;
    private long mAlbumArtJobId;
    private final View.OnTouchListener mAlbumArtOnTouchListener;
    private CacheManager mArtCache;
    private ImageView mArtistImageBackground;
    private View mArtworkSectionLayout;
    protected AudioVideoToggleUseCase mAudioVideoToggleInteractor;
    final View.OnClickListener mAudioVideoToggleListener;
    private ViewGroup mBauhausNowPlayingDialogView;
    private BauhausRibbon mBauhausRibbon;
    private int mBeatCount;
    private boolean mBlockingPlaybackOnRotation;
    private String mCachedAlbumAsin;
    private String mCachedArtistName;
    private String mCachedMediaSourceName;
    private String mCachedTrackTitle;
    private CastingImageButton mCastingButton;
    private CastingButtonHelper mCastingButtonHelper;
    private AlbumArtJob mCurrentAlbumArtJob;
    private int mDefaultUpsellTimeout;
    private final BroadcastReceiver mFinishBroadcastReceiver;
    private Boolean mFragmentIsInitializing;
    public Menu mFragmentMenu;
    private long mGetTrackPrimeStatusJobId;
    private final Handler mHandler = AmazonApplication.getDefaultHandler();
    private Boolean mHasRotated;
    private int mInitialViewContainerHeight;
    private final BroadcastReceiver mIoTSkipCountMessageReceiver;
    private boolean mIsLoaded;
    private boolean mIsToggleVideoOn;
    private int mItemCount;
    private ImageView mLandscapeBackgroundArt;
    private FrameLayout mLandscapeBackgroundArtFrame;
    private final View.OnClickListener mLikeButtonOnClickListener;
    private final BroadcastReceiver mLikesTrackReceiver;
    private Lyrics mLyrics;
    private LyricsTextViewContainer mLyricsViewContainer;
    private MediaItem mMediaItem;
    private MessagingDialog mMessagingDialog;
    final OnMetadataChangedListener mMetadataListener;
    private PlayerMetadataView mMetadataView;
    private NPSOverlayMetricsReporter mNPSOverlayMetricsReporter;
    private NoConnectionDialog mNoConnectionDialog;
    private final View.OnClickListener mOnArtistNameClickListener;
    private final View.OnCreateContextMenuListener mOnCreateContextMenuListener;
    private final View.OnClickListener mOnQualityButtonClickListener;
    private ImageButton mOverflowMenuButton;
    private NowPlayingPersistentPlayer mPersistentPlayer;
    private View mPersistentPlayerContainer;
    final OnPlayStateChangedListener mPlayStateListener;
    private final ActionValidatedPlaybackController mPlaybackController;
    private BroadcastReceiver mPlaybackErrorReceiver;
    private final OnPlaybackEventListener mPlaybackEventListener;
    public PlayerContextMenu mPlayerContextMenu;
    private PersistentPlayerFragment mPlayerFragment;
    private PersistentPlayerFragment.PlayerListener mPlayerListener;
    private PlaylistTooltipExperience mPlaylistTooltipExperience;
    private PopupMenu mPopupMenu;
    private RunnableDebouncer mRunnableDebouncer;
    private BauhausSeekBarWidget mSeekBar;
    private boolean mShowBlockingUpsellOnRotation;
    private boolean mShowLoading;
    private boolean mShowNpsContextualUpsell;
    private FrameLayout mStageBackgroundFrame;
    private StageViewPresenter mStageViewPresenter;
    private StationLoadingAnimation.Listener mStationAnimationListener;
    private StationLoadingAnimation mStationLoadingAnimation;
    private StyleSheet mStyleSheet;
    private String mSubtitleString;
    private BaseImage mSwapArtwork;
    private String mTertiaryString;
    private String mTitleString;
    private CoordinatorLayout mToastContainer;
    private Subscription mToggleSuccessfulSubscription;
    private TrackRatingListener mTrackRatingListener;
    UpsellInformation mUpsellInformation;
    private final Runnable mUpsellTimeOutProgressRunnable;
    private int mUpsellTimeout;
    private String mUrl;
    private ImageCache mUrlAlbumArtImageCache;
    private FrameLayout mViewContainer;
    private View mViewRoot;
    private final View.OnTouchListener mViewRootOnTouchListener;
    private boolean mWasLastTrackThumbedDown;
    Observer<Drawable> onAlbumArtLoadedObserver;
    private Subscription onMenuClickSubscription;
    private SkipLimitTooltipPresenter skipLimitTooltipPresenter;
    private Subscription skipsRemainingSubscription;
    final CompositeSubscription subscription;
    private final BehaviorSubject<MediaItem> tooltipSubject;
    private Subscription tooltipSubscription;
    private Boolean upsellDismissed;
    private BaseFragment videoPlayerFragment;
    private WebTarget webTarget;
    private static final String TAG = NowPlayingFragment.class.getSimpleName();
    private static boolean canShowSkipLimitToolTip = false;
    private static final IntentFilter PLAYBACK_ERROR_FILTER = new IntentFilter("com.amazon.mp3.playbackerror");

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showLyrics$29() {
    }

    private void showContentUnavailableDialogIfNeeded() {
    }

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment
    public boolean getHasOptionsMenu() {
        return true;
    }

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment
    public String getSourceId() {
        return "nowplaying";
    }

    @Override // android.view.View.OnClickListener
    public void onClick(final View view) {
    }

    public NowPlayingFragment() {
        this.isFreeTrialEligible = SubscriptionManagerSingleton.isInitialized() && SubscriptionManagerSingleton.getInstance().hasFreeTrial();
        this.subscription = new CompositeSubscription();
        this.mAlbumArtJobId = -1L;
        this.mUpsellTimeout = 0;
        this.mGetTrackPrimeStatusJobId = -1L;
        this.mDefaultUpsellTimeout = 10000;
        Boolean bool = Boolean.FALSE;
        this.mHasRotated = bool;
        Boolean bool2 = Boolean.TRUE;
        this.mFragmentIsInitializing = bool2;
        this.mPlaybackController = PlaybackControllerProvider.getController(ControlSource.APP_UI);
        this.mCastingButtonHelper = new CastingButtonHelper();
        this.doesPortraitArtRequirePartialBlur = false;
        this.mInitialViewContainerHeight = -1;
        this.artLoadSubject = PublishSubject.create();
        this.tooltipSubject = BehaviorSubject.create();
        this.skipLimitTooltipPresenter = new SkipLimitTooltipPresenter();
        this.compositeSubscription = new CompositeSubscription();
        this.isLyricsInAutomatedPreview = bool2;
        this.upsellDismissed = bool;
        this.albumArtLoadTarget = new Target() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment.1
            @Override // com.squareup.picasso.Target
            public void onPrepareLoad(Drawable drawable) {
            }

            @Override // com.squareup.picasso.Target
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom loadedFrom) {
                Context context = NowPlayingFragment.this.getContext();
                if (context == null || !NowPlayingFragment.this.isAdded()) {
                    return;
                }
                NowPlayingFragment.this.mAlbumArtDrawable = new BitmapDrawable(context.getResources(), bitmap);
                NowPlayingFragment.this.artLoadSubject.onNext((BitmapDrawable) NowPlayingFragment.this.mAlbumArtDrawable);
                if (NowPlayingFragment.this.mMediaItem instanceof AdMediaItem) {
                    CloudQueueUIMetricsManager.get().sendNowPlayingAdUIContentViewEvent((AdMediaItem) NowPlayingFragment.this.mMediaItem);
                }
            }

            @Override // com.squareup.picasso.Target
            public void onBitmapFailed(Exception e, Drawable drawable) {
                Log.error(NowPlayingFragment.TAG, " Error loading album art bitmap", e);
            }
        };
        this.onAlbumArtLoadedObserver = new Observer<Drawable>() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment.2
            @Override // rx.Observer
            public void onCompleted() {
            }

            @Override // rx.Observer
            public void onError(Throwable throwable) {
                Log.error(NowPlayingFragment.TAG, "An error has occoured while loading albumArtwork", throwable);
            }

            @Override // rx.Observer
            public void onNext(Drawable drawable) {
                NowPlayingFragment.this.onAlbumArtLoadFinished(drawable);
                if (!NowPlayingFragment.this.isVideoTrack() || NowPlayingFragment.this.mLandscapeBackgroundArt == null) {
                    return;
                }
                NowPlayingFragment.this.mLandscapeBackgroundArt.setVisibility(8);
            }
        };
        this.mMetadataListener = new OnMetadataChangedListener() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment.3
            @Override // com.amazon.music.media.playback.OnMetadataChangedListener
            public void onMediaCollectionInfoChanged(final MediaCollectionInfo info) {
            }

            @Override // com.amazon.music.media.playback.OnMetadataChangedListener
            public void onMetadataChanged(final MediaItem item) {
                NowPlayingFragment.this.updateAlbumArt(item);
                NowPlayingFragment.this.updateMetaData(item);
                NowPlayingFragment.this.mSeekBar.updateSeekBar();
                NowPlayingFragment.this.updateProgressBar();
            }
        };
        this.mAudioVideoToggleListener = new View.OnClickListener() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(final View view) {
                MediaItem currentMediaItem = NowPlayingFragment.this.mPlaybackController.getCurrentMediaItem();
                MediaItemId mediaItemId = currentMediaItem == null ? null : currentMediaItem.getMediaItemId(MediaItemId.Type.ASIN);
                if (mediaItemId == null) {
                    return;
                }
                HybridQueueType hybridQueueType = (HybridQueueType) view.getTag();
                NowPlayingFragment.this.mAudioVideoToggleInteractor.sendToggleMetrics(mediaItemId.getId(), hybridQueueType, null);
                NowPlayingFragment nowPlayingFragment = NowPlayingFragment.this;
                nowPlayingFragment.mAudioVideoToggleInteractor.doToggle(nowPlayingFragment.requireContext(), hybridQueueType);
            }
        };
        this.mPlaybackEventListener = new OnPlaybackEventListener() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment.5
            @Override // com.amazon.music.media.playback.OnPlaybackEventListener
            public void onPlaybackEvent(int event, ControlSource controlSource) {
                if (event == 28 && NowPlayingFragment.this.shouldShowSfaDialog()) {
                    NowPlayingFragment.this.setupAndShowSfaDialog();
                }
                if (event == 20 || event == 21 || event == 22 || event == 23) {
                    NowPlayingFragment.this.updateLikeButton();
                }
                if (event == 1 && NowPlayingFragment.this.isStage()) {
                    StageViewPresenter unused = NowPlayingFragment.this.mStageViewPresenter;
                }
                if (event == 2 && NowPlayingFragment.this.isStage()) {
                    StageViewPresenter unused2 = NowPlayingFragment.this.mStageViewPresenter;
                }
            }
        };
        this.mIsToggleVideoOn = true;
        this.videoPlayerFragment = null;
        this.mIoTSkipCountMessageReceiver = new BroadcastReceiver() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment.6
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                Log.info(NowPlayingFragment.TAG, "Update player button config after retrieving remaining skip count");
                if (NowPlayingFragment.this.mPersistentPlayer != null) {
                    NowPlayingFragment.this.mPersistentPlayer.setControlsEnabled(true);
                }
            }
        };
        this.mLikesTrackReceiver = new BroadcastReceiver() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment.7
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                if (intent != null) {
                    String stringExtra = intent.getStringExtra("LIKE_TRACK_ASIN");
                    String stringExtra2 = intent.getStringExtra("TRACK_LIKE_STATE");
                    for (MediaItem mediaItem : NowPlayingFragment.this.mPlaybackController.getCurrentQueue()) {
                        String id = mediaItem.getMediaItemId() == null ? null : mediaItem.getMediaItemId().getId();
                        if (stringExtra != null && stringExtra.equals(id)) {
                            LikeState likeState = LikeState.LIKE;
                            LikeState likeState2 = likeState.toString().equals(stringExtra2) ? likeState : LikeState.NEUTRAL;
                            Boolean boolValueOf = Boolean.valueOf(likeState2 == likeState);
                            mediaItem.setLikeState(likeState2);
                            LikesStateCacheManager.INSTANCE.getInstance(NowPlayingFragment.this.getContext()).add(stringExtra, boolValueOf.booleanValue());
                            LikesServiceProxy.INSTANCE.updateMyLikesDB(AmazonApplication.getApplication().getApplicationContext(), mediaItem, likeState2);
                            MediaItem currentMediaItem = NowPlayingFragment.this.mPlaybackController.getCurrentMediaItem();
                            if (currentMediaItem != null && currentMediaItem.getMediaItemId() != null && currentMediaItem.getMediaItemId().getId().equals(stringExtra)) {
                                NowPlayingFragment.this.updateLikeButton();
                            }
                            Log.info(NowPlayingFragment.TAG, "Update the track's like state for asin: %s, like state = %s, ", stringExtra, stringExtra2);
                        }
                    }
                }
            }
        };
        this.mPlaybackErrorReceiver = new PlaybackErrorReceiver(new PlaybackErrorReceiver.PlaybackErrorReceiverListener() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment.8
            @Override // com.amazon.mp3.playback.PlaybackErrorReceiver.PlaybackErrorReceiverListener
            public void startNoNetworkDialog() {
                NowPlayingFragment.this.showNoNetwork();
            }

            @Override // com.amazon.mp3.playback.PlaybackErrorReceiver.PlaybackErrorReceiverListener
            public void startActivityForIntent(final Intent intent) {
                NowPlayingFragment.this.startActivity(intent);
            }
        });
        this.mStationAnimationListener = new StationLoadingAnimation.Listener() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment.9
            @Override // com.amazon.mp3.animation.StationLoadingAnimation.Listener
            public void onAnimationEnd() {
                NowPlayingFragment.access$1704(NowPlayingFragment.this);
                if (!NowPlayingFragment.this.mIsLoaded || NowPlayingFragment.this.mBeatCount < 2) {
                    NowPlayingFragment.this.mStationLoadingAnimation.startHeartBeat(NowPlayingFragment.this.getActivity());
                } else {
                    NowPlayingFragment.this.finishLoading();
                }
            }
        };
        this.mPlayStateListener = new OnPlayStateChangedListener() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment.15
            @Override // com.amazon.music.media.playback.OnPlayStateChangedListener
            public void onPlayStateChanged() {
                MediaItem currentMediaItem = NowPlayingFragment.this.mPlaybackController.getCurrentMediaItem();
                int numMediaItems = 0;
                if (currentMediaItem != null) {
                    if (NowPlayingFragment.canShowSkipLimitToolTip && !(currentMediaItem instanceof AdMediaItem) && !MediaItemHelper.isInterludeMediaItem(currentMediaItem) && !MediaItemExtensions.isVideo(currentMediaItem)) {
                        NowPlayingFragment.this.skipLimitTooltipPresenter.showSkipTooltip(NowPlayingFragment.this.mViewRoot, NowPlayingFragment.this.mStyleSheet, NowPlayingFragment.this.inImmersiveMode(), NowPlayingFragment.this.mHasRotated.booleanValue(), NowPlayingFragment.this.mFragmentIsInitializing.booleanValue());
                        boolean unused = NowPlayingFragment.canShowSkipLimitToolTip = false;
                    }
                    NowPlayingFragment.this.mIsLoaded = true;
                    if (!NowPlayingFragment.this.mStationLoadingAnimation.canShowStationLoadingAnimation(NowPlayingFragment.this.getContext())) {
                        NowPlayingFragment nowPlayingFragment = NowPlayingFragment.this;
                        nowPlayingFragment.updateSeekBarVisibility(nowPlayingFragment.mPlaybackController.getCurrentMediaItem());
                    }
                    if (!currentMediaItem.equals(NowPlayingFragment.this.mMediaItem)) {
                        if (NowPlayingFragment.this.isStage() && NowPlayingFragment.this.mStageViewPresenter != null && NowPlayingFragment.this.mStageViewPresenter.getStageViewModel() != null) {
                            NowPlayingFragment.this.mStageViewPresenter.getStageViewModel().onMediaItemChanged();
                        }
                        NowPlayingFragment.this.mLyrics = null;
                        NowPlayingFragment.this.tooltipSubject.onNext(currentMediaItem);
                        NowPlayingFragment nowPlayingFragment2 = NowPlayingFragment.this;
                        nowPlayingFragment2.updatePlayer(currentMediaItem, nowPlayingFragment2.mMediaItem);
                        NowPlayingFragment.this.updateAlbumArt(currentMediaItem);
                        NowPlayingFragment.this.updateMetaData(currentMediaItem);
                        NowPlayingFragment nowPlayingFragment3 = NowPlayingFragment.this;
                        nowPlayingFragment3.updateOptionsMenuVisibility(nowPlayingFragment3.mMediaItem);
                        NowPlayingFragment.this.clearUserAdPref();
                        NowPlayingFragment nowPlayingFragment4 = NowPlayingFragment.this;
                        nowPlayingFragment4.updateAdVisibility(nowPlayingFragment4.mMediaItem);
                        NowPlayingFragment.this.setupAdsPrefsNotification(currentMediaItem);
                        NowPlayingFragment.this.updateAudioQualityBadge();
                        if (NowPlayingFragment.this.mCastingButtonHelper != null) {
                            NowPlayingFragment.this.mCastingButtonHelper.refreshVisibility();
                        }
                        NowPlayingFragment nowPlayingFragment5 = NowPlayingFragment.this;
                        nowPlayingFragment5.mAudioVideoToggleInteractor.updateToggleState(nowPlayingFragment5.getContext());
                        if (!NowPlayingFragment.this.isStation()) {
                            NowPlayingFragment nowPlayingFragment6 = NowPlayingFragment.this;
                            nowPlayingFragment6.mAudioVideoToggleInteractor.loadTrackAssociationsBatch(nowPlayingFragment6.getContext());
                        }
                        NowPlayingFragment.this.setupViewsForPipIfNeeded();
                        if (!NowPlayingFragment.this.isStage() && NowPlayingFragment.this.getXrayManager() != null) {
                            NowPlayingFragment.this.getXrayManager().checkIfXrayAvailable();
                        }
                    }
                    if (NowPlayingFragment.this.isStage() && NowPlayingFragment.this.mStageViewPresenter != null && NowPlayingFragment.this.mStageViewPresenter.getStageViewModel() != null) {
                        NowPlayingFragment.this.mStageViewPresenter.getStageViewModel().onPlayStatusChanged();
                    }
                    if (!NowPlayingFragment.this.isStage() && NowPlayingFragment.this.getXrayManager() != null && !NowPlayingFragment.this.getXrayManager().wasXrayCheckedForTrack()) {
                        NowPlayingFragment.this.getXrayManager().checkIfXrayAvailable();
                    }
                    NowPlayingFragment.this.updateAudioQualityBadge();
                    if (NowPlayingFragment.this.shouldUpdateLikeButtonState(currentMediaItem)) {
                        NowPlayingFragment.this.updateLikeButton();
                    }
                }
                if (NowPlayingFragment.this.mShowLoading && NowPlayingFragment.this.isStage() && NowPlayingFragment.this.mPlaybackController.getPlayStatus() == PlayStatus.RENDERING) {
                    NowPlayingFragment.this.mShowLoading = false;
                    NowPlayingFragment.this.updateMetadataText();
                }
                NowPlayingFragment.this.mSeekBar.updateSeekBar();
                NowPlayingFragment.this.updateProgressBar();
                NowPlayingFragment.this.updateNowPlayingPlayer(false);
                if (NowPlayingFragment.this.mLyricsViewContainer != null) {
                    PlayStatus playStatus = NowPlayingFragment.this.mPlaybackController.getPlayStatus();
                    if (NowPlayingFragment.this.mLyricsViewContainer.isFrugalLyricsViewVisible() && !NowPlayingFragment.this.shouldShowFrugalLyrics()) {
                        NowPlayingFragment.this.mNPSOverlayMetricsReporter.startTimer(StageLyricsUtil.INSTANCE.getLyricsPreviewViewType(NowPlayingFragment.this.isLyricsInAutomatedPreview.booleanValue()));
                    }
                    NowPlayingFragment.this.mLyricsViewContainer.setPlayStatus(playStatus);
                    NowPlayingFragment.this.mLyricsViewContainer.setPlaybackTime(NowPlayingFragment.this.mPlaybackController.getPositionMillis());
                }
                SequencerPlaybackProvider sequencerPlaybackProvider = (SequencerPlaybackProvider) Playback.getInstance().getPlaybackProvider();
                if (!CastingUtil.isCastingToAlexa() && (sequencerPlaybackProvider.getSequencer() instanceof PlayQueueSequencer)) {
                    numMediaItems = ((PlayQueueSequencer) sequencerPlaybackProvider.getSequencer()).getNumMediaItems();
                }
                if (NowPlayingFragment.this.mItemCount != numMediaItems) {
                    NowPlayingFragment.this.mItemCount = numMediaItems;
                    NowPlayingFragment nowPlayingFragment7 = NowPlayingFragment.this;
                    nowPlayingFragment7.mAudioVideoToggleInteractor.updateToggleState(nowPlayingFragment7.requireContext());
                }
                NowPlayingFragment nowPlayingFragment8 = NowPlayingFragment.this;
                nowPlayingFragment8.handleVideoScreenOnFlags(nowPlayingFragment8.mPlaybackController.getCurrentMediaItem(), NowPlayingFragment.this.mPlaybackController.getPlayStatus());
                if (NowPlayingFragment.this.shouldShowSfaDialog()) {
                    NowPlayingFragment.this.setupAndShowSfaDialog();
                }
            }
        };
        this.mPlayerListener = new PersistentPlayerFragment.PlayerListener() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment.16
            @Override // com.amazon.mp3.playback.fragment.PersistentPlayerFragment.PlayerListener
            public void onPlayerCreated() {
                if (NowPlayingFragment.this.mPlayerFragment != null) {
                    NowPlayingFragment nowPlayingFragment = NowPlayingFragment.this;
                    nowPlayingFragment.mPersistentPlayer = (NowPlayingPersistentPlayer) nowPlayingFragment.mPlayerFragment.getPersistentPlayer();
                    NowPlayingFragment.this.mPersistentPlayer.addThumbsListener(NowPlayingFragment.this.mTrackRatingListener);
                    NowPlayingFragment.this.mPersistentPlayer.setControlsEnabled(!NowPlayingFragment.this.mShowLoading);
                    if (!NowPlayingFragment.this.isStage() && NowPlayingFragment.this.getXrayManager() != null) {
                        NowPlayingFragment.this.getXrayManager().setXrayButtonTouchListener(NowPlayingFragment.this.mPersistentPlayer);
                    }
                    NowPlayingFragment.this.setSkipsRemainingTooltipSubscription();
                }
            }
        };
        this.mTrackRatingListener = new TrackRatingListener() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment.19
            @Override // com.amazon.mp3.view.TrackRatingListener
            public void onThumbsUp() {
                BauhausToastUtils.showTextToast(NowPlayingFragment.this.requireActivity(), R.string.dmusic_prime_stations_thumbs_up_toast, 0);
            }

            @Override // com.amazon.mp3.view.TrackRatingListener
            public void onThumbsDown() {
                BauhausToastUtils.showTextToast(NowPlayingFragment.this.requireActivity(), R.string.dmusic_prime_stations_thumbs_down_toast, 0);
                NowPlayingFragment.this.mWasLastTrackThumbedDown = true;
            }
        };
        this.mAdUpsellOnClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$new$21(view);
            }
        };
        this.mFinishBroadcastReceiver = new BroadcastReceiver() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment.25
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                FragmentActivity activity = NowPlayingFragment.this.getActivity();
                if (activity != null) {
                    String action = intent.getAction();
                    if ("com.amazon.mp3.library.activity.nowplaying.clear_and_finish".equals(action)) {
                        if (NowPlayingManager.getInstance().getCurrentTrack() == null || !"cirrus".equals(NowPlayingManager.getInstance().getCurrentTrack().getSource())) {
                            return;
                        }
                        NowPlayingUtil.clearAndFinishNowPlaying(activity, PlayStateMutationReason.CLEAR_CACHE);
                        return;
                    }
                    if ("com.amazon.mp3.library.activity.nowplaying.finish".equals(action)) {
                        activity.finish();
                    }
                }
            }
        };
        this.mOnCreateContextMenuListener = new View.OnCreateContextMenuListener() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment.26
            @Override // android.view.View.OnCreateContextMenuListener
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo info) {
                if (NowPlayingFragment.this.shouldDisplayContextMenu()) {
                    NowPlayingContextMenu.onCreateContextMenu(menu, NowPlayingFragment.this.getActivity());
                }
            }
        };
        this.mOnQualityButtonClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$new$22(view);
            }
        };
        this.mOnArtistNameClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$new$23(view);
            }
        };
        this.mLikeButtonOnClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$new$24(view);
            }
        };
        this.mAlbumArtOnTouchListener = new NowPlayingOnTouchListener(true, true);
        this.mViewRootOnTouchListener = new NowPlayingOnTouchListener(false, true);
        this.mUpsellTimeOutProgressRunnable = new Runnable() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment.30
            @Override // java.lang.Runnable
            public void run() {
                NowPlayingFragment.this.mUpsellTimeout += 100;
                if (NowPlayingFragment.this.mBauhausNowPlayingDialogView == null || NowPlayingFragment.this.mBauhausNowPlayingDialogView.getVisibility() != 0 || NowPlayingFragment.this.mUpsellTimeout <= NowPlayingFragment.this.mDefaultUpsellTimeout) {
                    NowPlayingFragment.this.mRunnableDebouncer.postDelayed(this, 100L);
                    return;
                }
                if (NowPlayingFragment.this.mShowNpsContextualUpsell) {
                    MetricsLogger.emitUiClickMetricForUpsell(ActionType.NPS_CONTEXTUAL_UPSELL_DIALOG_AUTO_DISMISSED, ContentName.NPS_CONTEXTUAL_UPSELL_DIALOG.getMetricsValue());
                }
                NowPlayingFragment.this.displayDialogView(false);
                NowPlayingFragment.this.upsellDismissed = Boolean.TRUE;
            }
        };
    }

    static /* synthetic */ int access$1704(NowPlayingFragment nowPlayingFragment) {
        int i = nowPlayingFragment.mBeatCount + 1;
        nowPlayingFragment.mBeatCount = i;
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePlayer(final MediaItem currentMediaItem, final MediaItem prevMediaItem) {
        StageViewPresenter stageViewPresenter;
        if (MediaItemExtensions.isVideo(currentMediaItem) && (MediaItemExtensions.isAudio(prevMediaItem) || prevMediaItem == null)) {
            if (ScreenUtil.isLandscape() || (ScreenUtil.isPortrait() && shouldReinflateWhenUpdatingPlayer(currentMediaItem))) {
                this.mViewContainer.removeAllViews();
                resetContainerHeightAndMargins();
                this.mViewRoot = getLayoutInflater().inflate(getContentViewId(), (ViewGroup) this.mViewContainer, true);
            }
            showVideoView();
            if (isStage()) {
                setBackgroundFrameColor(0);
                FrameLayout frameLayout = this.mStageBackgroundFrame;
                if (frameLayout != null) {
                    frameLayout.setVisibility(8);
                }
                BaseImage baseImage = this.mAlbumArt;
                if (baseImage != null) {
                    baseImage.setVisibility(8);
                }
                StageViewPresenter stageViewPresenter2 = this.mStageViewPresenter;
                if (stageViewPresenter2 != null) {
                    stageViewPresenter2.showVideoView(this.mStyleSheet);
                }
                if (ScreenUtil.isLandscape()) {
                    resizeVideoHeightForStage(-1);
                }
            }
        } else if (MediaItemExtensions.isAudio(currentMediaItem) && (MediaItemExtensions.isVideo(prevMediaItem) || prevMediaItem == null)) {
            showAudioView();
        }
        updateNowPlayingPlayer(true);
        if (MediaItemExtensions.isAudio(currentMediaItem)) {
            updateAlbumArt(currentMediaItem);
            if (!isStage() || (stageViewPresenter = this.mStageViewPresenter) == null || !stageViewPresenter.isInFullScreenOverlayMode()) {
                updateSeekBarVisibility(currentMediaItem);
            }
            this.mSeekBar.updateSeekBar();
        }
    }

    public boolean isStage() {
        return AmazonApplication.getCapabilities().isYAImmerseEnabled();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateSeekBarVisibility(MediaItem currentMediaItem) {
        BauhausSeekBarWidget bauhausSeekBarWidget = this.mSeekBar;
        if (bauhausSeekBarWidget == null) {
            Log.error(TAG, "Failed to update seek bar visibility since mSeekBar is null");
        } else {
            bauhausSeekBarWidget.setVisibility(((currentMediaItem instanceof CastingMediaItem) && ((CastingMediaItem) currentMediaItem).isInterlude()) ? 4 : 0);
        }
    }

    private boolean shouldReinflateWhenUpdatingPlayer(final MediaItem currentMediaItem) {
        return isStage() ? MediaItemExtensions.isVideo(currentMediaItem) && this.mViewContainer.findViewById(R.id.StageAlbumArtContainer) == null : MediaItemExtensions.isVideo(currentMediaItem) && this.mViewContainer.findViewById(R.id.AlbumArtAndSeekBarContainer) == null;
    }

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mNPSOverlayMetricsReporter = NPSOverlayMetricsReporter.INSTANCE.getNPSOverlayMetricsReporter();
        com.amazon.podcast.media.playback.Playback playback = Podcast.getPlayback();
        if (playback != null) {
            playback.clear();
        }
        LatencyTracker.INSTANCE.track(new LatencyEventDescriptor(LatencyTrackingLeg.PROCESSING, PageLoadLatencyCode.NOW_PLAYING));
        this.mPlayerContextMenu = new PlayerContextMenu(getActivity());
        this.mViewContainer = new FrameLayout(getActivity());
        this.mRunnableDebouncer = new RunnableDebouncer(this.mHandler);
        this.webTarget = new OfferPageWebTargetBuilderFactory(getActivity()).newBuilder().applyTier(SubscriptionTier.UNLIMITED_INDIVIDUAL).build();
        this.mPlaybackController.addOnPlaybackEventListener(this.mPlaybackEventListener);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LatencyTracker.INSTANCE.track(new LatencyEventDescriptor(LatencyTrackingLeg.RENDERING, PageLoadLatencyCode.NOW_PLAYING));
        Intent intent = getActivity().getIntent();
        this.mMediaItem = null;
        this.mAlbumArtDrawable = null;
        this.mViewContainer.removeAllViews();
        this.mViewRoot = View.inflate(getActivity(), getContentViewId(), this.mViewContainer);
        this.mPlaylistTooltipExperience = new PlaylistTooltipExperience.PlaylistTooltipExperienceBuilder().setRootView(this.mViewRoot).setNumViews(PlaylistTooltipExperience.PlaylistTooltipExperienceBuilder.INSTANCE.getMAX_NUM_NOW_PLAYING_PAGE_VIEWS()).createPlaylistTooltipExperience(getContext());
        this.skipLimitTooltipPresenter.setSkipLimitTooltipExperiences(this.mViewRoot, getContext());
        this.mShowLoading = savedInstanceState == null && intent.getBooleanExtra("com.amazon.music.station.EXTRA_SHOW_STATION_LOADING", false);
        if (intent.getExtras() != null) {
            this.mUpsellInformation = (UpsellInformation) intent.getExtras().getParcelable("com.amazon.music.playback.EXTRA_SHOW_UNLIMITED_UPSELL");
            String string = intent.getExtras().getString("PULL_TRANSFER_TOAST", "");
            if (!TextUtils.isEmpty(string)) {
                BauhausToastUtils.showTextToast(getActivity(), getResources().getString(R.string.cloudqueue_pull_confirm_toast, string), 1);
            }
        }
        if (savedInstanceState != null) {
            restoreStateInformation(savedInstanceState);
            if (savedInstanceState.getBoolean("BLOCK_PLAYBACK_ON_ROTATION", false)) {
                this.mPlaybackController.pause();
                this.mBlockingPlaybackOnRotation = true;
            }
        }
        if (!this.mShowLoading) {
            setHasOptionsMenu(getHasOptionsMenu());
        }
        intent.removeExtra("com.amazon.music.station.EXTRA_SHOW_STATION_LOADING");
        sendUiPageViewMetric();
        View view = this.mViewRoot;
        if (view != null) {
            view.post(new Runnable() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment.10
                @Override // java.lang.Runnable
                public void run() {
                    if (ActivityKt.isAvailable(NowPlayingFragment.this.getActivity())) {
                        LatencyTracker.INSTANCE.track(new LatencyEventDescriptor(LatencyTrackingLeg.LOAD_COMPLETE, PageLoadLatencyCode.NOW_PLAYING));
                    }
                }
            });
        }
        return this.mViewRoot;
    }

    @Override // com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.mLandscapeBackgroundArtFrame = (FrameLayout) getActivity().findViewById(R.id.landscape_background_artwork_frame);
        if (ScreenUtil.isPortrait()) {
            this.mStationLoadingAnimation = new StationLoadingAnimation(this.mViewRoot);
        } else {
            this.mStationLoadingAnimation = new StationLoadingAnimation(this.mLandscapeBackgroundArtFrame);
        }
        this.mStationLoadingAnimation.addListener(this.mStationAnimationListener);
        if (this.mShowLoading) {
            startLoadingAnimation();
        }
        if (isStage()) {
            this.mStageBackgroundFrame = (FrameLayout) getActivity().findViewById(R.id.stage_background_frame);
            this.mArtistImageBackground = (ImageView) getActivity().findViewById(R.id.artist_image_background);
        }
    }

    private void showAudioView() {
        Log.debug(TAG, "showAudioView()");
        if (this.mAlbumArtAndSeekBarLayout != null && !isStage()) {
            this.mAlbumArtAndSeekBarLayout.setVisibility(0);
        }
        FragmentActivity activity = getActivity();
        if (ActivityKt.isAvailable(activity)) {
            Configuration configuration = activity.getResources().getConfiguration();
            BaseFragment baseFragment = this.videoPlayerFragment;
            if (baseFragment != null && baseFragment.isAdded()) {
                FragmentManager childFragmentManager = getChildFragmentManager();
                FragmentTransaction fragmentTransactionBeginTransaction = childFragmentManager.beginTransaction();
                fragmentTransactionBeginTransaction.remove(this.videoPlayerFragment);
                fragmentTransactionBeginTransaction.commitAllowingStateLoss();
                childFragmentManager.executePendingTransactions();
                if (isStage() || configuration.orientation == 2) {
                    onConfigurationChangedAudio(configuration);
                } else {
                    this.mCachedAlbumAsin = null;
                    this.mCachedArtistName = null;
                    this.mCachedTrackTitle = null;
                }
                if (this.mPersistentPlayerContainer != null) {
                    updateNowPlayingPlayer(true);
                }
            } else {
                updateSystemUIVisibility(configuration);
            }
        }
        this.videoPlayerFragment = null;
        if (VideoUtil.INSTANCE.isInPictureInPictureMode(getActivity())) {
            setToolbarLayoutVisibility(false);
        } else {
            setToolbarLayoutVisibility(true);
            addToggleToToolbarLayout();
            this.mAudioVideoToggleInteractor.updateToggleState(getContext());
        }
        if (this.mMediaItem == null) {
            this.mMediaItem = this.mPlaybackController.getCurrentMediaItem();
        }
        updateMetaData(this.mMediaItem);
        updateAlbumArt(this.mMediaItem);
        updateCastingButton();
        initStyleSheet();
        if (!isStage() || this.mStageViewPresenter == null) {
            return;
        }
        FrameLayout frameLayout = this.mStageBackgroundFrame;
        if (frameLayout != null) {
            frameLayout.setVisibility(0);
        }
        if (this.mAlbumArt != null) {
            if (ScreenUtil.isPortrait()) {
                ViewGroup.LayoutParams layoutParams = this.mStageViewPresenter.getStageAlbumArtContainer().getLayoutParams();
                layoutParams.height = -2;
                this.mStageViewPresenter.getStageAlbumArtContainer().setLayoutParams(layoutParams);
            }
            this.mAlbumArt.setVisibility(0);
        }
        this.mStageViewPresenter.showControls();
        this.mStageViewPresenter.showAudioView(this.mStyleSheet);
    }

    private void showVideoView() {
        int i;
        String str;
        Log.debug(TAG, "showVideoView()");
        ImageButton imageButton = this.mOverflowMenuButton;
        if (imageButton != null) {
            imageButton.setVisibility(8);
        }
        if (ActivityKt.isAvailable(getActivity()) && getActivity().getResources().getConfiguration().orientation == 2) {
            updateAlbumArtContainerHeight();
        }
        if (!isStage()) {
            this.mAlbumArtAndSeekBarLayout = (AlbumArtAndSeekBarLayout) this.mViewContainer.findViewById(R.id.AlbumArtAndSeekBar);
        }
        if (ActivityKt.isAvailable(getActivity())) {
            this.mLandscapeBackgroundArt = (ImageView) getActivity().findViewById(R.id.artwork_background);
        }
        AlbumArtAndSeekBarLayout albumArtAndSeekBarLayout = this.mAlbumArtAndSeekBarLayout;
        if (albumArtAndSeekBarLayout != null) {
            albumArtAndSeekBarLayout.setVisibility(8);
        }
        ImageView imageView = this.mLandscapeBackgroundArt;
        if (imageView != null) {
            imageView.setVisibility(8);
        }
        if (isStage()) {
            i = R.id.StageAlbumArtContainer;
            if (ScreenUtil.isPortrait()) {
                ViewGroup.LayoutParams layoutParams = this.mViewRoot.findViewById(R.id.StageAlbumArtContainer).getLayoutParams();
                if (BrowseFactory.isTabletDevice()) {
                    getActivity().getWindowManager().getDefaultDisplay().getMetrics(new DisplayMetrics());
                    layoutParams.height = (int) ((r4.widthPixels * 1) / getResources().getFraction(R.fraction.aspect_ratio_16_9, 1, 1));
                } else {
                    layoutParams.height = getResources().getDimensionPixelSize(R.dimen.stage_artwork_image_height);
                }
                this.mViewRoot.findViewById(R.id.StageAlbumArtContainer).setLayoutParams(layoutParams);
            }
        } else {
            i = R.id.AlbumArtAndSeekBarContainer;
        }
        if (isStage()) {
            str = "STAGE_VIDEO_PLAYER_FRAGMENT_TAG";
            StageVideoPlayerFragment stageVideoPlayerFragment = (StageVideoPlayerFragment) getChildFragmentManager().findFragmentByTag("STAGE_VIDEO_PLAYER_FRAGMENT_TAG");
            this.videoPlayerFragment = stageVideoPlayerFragment;
            if (stageVideoPlayerFragment == null) {
                this.videoPlayerFragment = StageVideoPlayerFragment.INSTANCE.newInstance(getArguments());
            }
        } else {
            str = "VIDEO_PLAYER_FRAGMENT_TAG";
            if (this.videoPlayerFragment == null && this.mViewRoot.findViewById(i) != null) {
                VideoPlayerFragment videoPlayerFragment = (VideoPlayerFragment) getChildFragmentManager().findFragmentByTag("VIDEO_PLAYER_FRAGMENT_TAG");
                this.videoPlayerFragment = videoPlayerFragment;
                if (videoPlayerFragment == null) {
                    this.videoPlayerFragment = VideoPlayerFragment.INSTANCE.newInstance(getArguments());
                }
            }
        }
        if (this.videoPlayerFragment == null) {
            return;
        }
        FragmentManager childFragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransactionBeginTransaction = childFragmentManager.beginTransaction();
        fragmentTransactionBeginTransaction.replace(i, this.videoPlayerFragment, str);
        fragmentTransactionBeginTransaction.commitAllowingStateLoss();
        childFragmentManager.executePendingTransactions();
        BauhausSeekBarWidget bauhausSeekBarWidget = this.mSeekBar;
        if (bauhausSeekBarWidget != null) {
            bauhausSeekBarWidget.bringToFront();
        }
        if (this.mMetadataView != null) {
            if (isStage() && ScreenUtil.isLandscape()) {
                this.mMetadataView.setVisibility(8);
            } else {
                PlayerMetadataView playerMetadataView = (PlayerMetadataView) this.mViewRoot.findViewById(R.id.player_metadata);
                this.mMetadataView = playerMetadataView;
                if (playerMetadataView != null) {
                    playerMetadataView.setVisibility(0);
                }
            }
        }
        if (this.mAlbumArt != null && ScreenUtil.isLandscape()) {
            this.mAlbumArt.setVisibility(8);
        }
        setToolbarLayoutVisibility(!VideoUtil.INSTANCE.isInPictureInPictureMode(getActivity()) && ScreenUtil.isPortrait());
        if (ScreenUtil.isPortrait()) {
            addToggleToToolbarLayout();
            updateCastingButton();
        }
        if (ActivityKt.isAvailable(getActivity())) {
            getActivity().invalidateOptionsMenu();
        }
        this.mAudioVideoToggleInteractor.updateToggleState(getContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showNoNetwork() {
        if (this.mNoConnectionDialog == null) {
            this.mNoConnectionDialog = NoConnectionDialog.newInstance(false);
        }
        if (this.mNoConnectionDialog.isShowing()) {
            return;
        }
        this.mNoConnectionDialog.show(getFragmentManager(), "NoConnectionDialog");
    }

    private void setBackgroundFrameColor(int color) {
        FrameLayout frameLayout = this.mLandscapeBackgroundArtFrame;
        if (frameLayout != null) {
            frameLayout.setBackgroundColor(color);
        }
    }

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle savedState) {
        super.onSaveInstanceState(savedState);
        if (this.mShowLoading && this.mUpsellInformation != null) {
            savedState.putBoolean("SHOW_BLOCKING_UPSELL_ON_ROTATION", true);
            savedState.putInt("REMAINING_UPSELL_TIME_OUT", this.mDefaultUpsellTimeout - this.mUpsellTimeout);
        } else {
            ViewGroup viewGroup = this.mBauhausNowPlayingDialogView;
            if (viewGroup != null && !this.isNonBlockingDialogueSFA) {
                boolean z = viewGroup.getVisibility() == 0;
                this.mShowBlockingUpsellOnRotation = z;
                savedState.putBoolean("SHOW_BLOCKING_UPSELL_ON_ROTATION", z);
                savedState.putInt("REMAINING_UPSELL_TIME_OUT", this.mDefaultUpsellTimeout - this.mUpsellTimeout);
            }
        }
        savedState.putInt("CURRENT_ORIENTATION", getContext().getResources().getConfiguration().orientation);
        if (this.mBlockingPlaybackOnRotation) {
            savedState.putBoolean("BLOCK_PLAYBACK_ON_ROTATION", true);
        }
    }

    private void restoreStateInformation(Bundle savedState) {
        if (savedState.getBoolean("SHOW_BLOCKING_UPSELL_ON_ROTATION", false) && !this.isNonBlockingDialogueSFA) {
            this.mShowBlockingUpsellOnRotation = true;
            this.mDefaultUpsellTimeout = savedState.getInt("REMAINING_UPSELL_TIME_OUT", 0);
        }
        if (savedState.getInt("CURRENT_ORIENTATION", 0) != getContext().getResources().getConfiguration().orientation) {
            this.mHasRotated = Boolean.TRUE;
        }
    }

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        ImageButton imageButton = this.mOverflowMenuButton;
        if (imageButton != null) {
            imageButton.setVisibility(8);
        }
    }

    @Override // com.amazon.mp3.fragment.BaseFragment
    public boolean onBackPressed() {
        StageViewPresenter stageViewPresenter;
        if (isStage() && (stageViewPresenter = this.mStageViewPresenter) != null) {
            OverlayType overlayType = OverlayType.ALBUM_ART;
            int size = stageViewPresenter.getFullscreenOverlayBackstack().size();
            if (size != 0) {
                overlayType = this.mStageViewPresenter.getFullscreenOverlayBackstack().peek().getOverlayViewType().getOverlayType();
            }
            if (size == 0 || (overlayType != OverlayType.LYRICS_SELECTION && overlayType != OverlayType.LYRICS_CARDS_SELECTION)) {
                PlayerContextMenu.sendMetricEvent(PageType.NOW_PLAYING_STAGE, ActionType.NOW_PLAYING_STAGE_COLLAPSE, InteractionType.TAP, EntityType.TRACK);
            }
            if (size > 0) {
                if (overlayType != OverlayType.LYRICS_SELECTION && overlayType != OverlayType.LYRICS_CARDS_SELECTION) {
                    sendUiPageViewMetric();
                }
                return this.mStageViewPresenter.popOverlayFragmentFromBackstack();
            }
            FragmentManager supportFragmentManager = getActivity().getSupportFragmentManager();
            if (supportFragmentManager != null) {
                supportFragmentManager.popBackStack((String) null, 1);
            }
        }
        return super.onBackPressed();
    }

    @Override // com.amazon.mp3.fragment.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        ActionBar supportActionBar;
        ImageView albumArtImageView = getAlbumArtImageView();
        if (albumArtImageView != null) {
            Picasso.get().cancelRequest(albumArtImageView);
        }
        removeParentViews();
        super.onDestroyView();
        NowPlayingPersistentPlayer nowPlayingPersistentPlayer = this.mPersistentPlayer;
        if (nowPlayingPersistentPlayer != null) {
            nowPlayingPersistentPlayer.removeThumbsListener(this.mTrackRatingListener);
        }
        PersistentPlayerFragment persistentPlayerFragment = this.mPlayerFragment;
        if (persistentPlayerFragment != null) {
            persistentPlayerFragment.removePlayerListener(this.mPlayerListener);
        }
        Subscription subscription = this.skipsRemainingSubscription;
        if (subscription != null) {
            subscription.unsubscribe();
        }
        this.mStationLoadingAnimation.removeListener(this.mStationAnimationListener);
        this.mSeekBar.pauseSeekbarProgressUpdate();
        if ((getActivity() instanceof AppCompatActivity) && (supportActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar()) != null) {
            supportActionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.background_dark_blue)));
        }
        PlaylistTooltipExperience playlistTooltipExperience = this.mPlaylistTooltipExperience;
        if (playlistTooltipExperience != null) {
            playlistTooltipExperience.onDestroy();
        }
        this.skipLimitTooltipPresenter.destroyExperiences();
        LyricsTextViewContainer lyricsTextViewContainer = this.mLyricsViewContainer;
        if (lyricsTextViewContainer != null) {
            lyricsTextViewContainer.onDestroy();
        }
        if (this.videoPlayerFragment != null) {
            getChildFragmentManager().beginTransaction().remove(this.videoPlayerFragment).commitAllowingStateLoss();
            this.videoPlayerFragment = null;
        }
        StageViewPresenter stageViewPresenter = this.mStageViewPresenter;
        if (stageViewPresenter != null) {
            stageViewPresenter.onDestroy();
        }
        this.compositeSubscription.unsubscribe();
    }

    @Override // com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            return;
        }
        showActionBar();
    }

    private void removeParentViews() {
        ViewGroup viewGroup;
        if (getView() == null || !(getView().getParent() instanceof ViewGroup) || (viewGroup = (ViewGroup) getView().getParent()) == null) {
            return;
        }
        viewGroup.removeAllViews();
    }

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        unregisterReceivers();
        this.mAudioVideoToggleInteractor.removeAudioVideoToggleListener();
        AlbumArtJob albumArtJob = this.mCurrentAlbumArtJob;
        if (albumArtJob != null) {
            albumArtJob.cancel();
        }
        if (isStage()) {
            this.mPlaybackController.removeOnPlayStateChangedListener(this.mPlayStateListener);
            this.mPlaybackController.removeOnMetadataChangedListener(this.mMetadataListener);
        }
        this.mRunnableDebouncer.remove(this.mUpsellTimeOutProgressRunnable);
        CompositeSubscription compositeSubscription = this.subscription;
        if (compositeSubscription != null && !compositeSubscription.isUnsubscribed()) {
            this.subscription.unsubscribe();
        }
        removeHeaderView();
        this.mPlaybackController.removeOnPlaybackEventListener(this.mPlaybackEventListener);
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(this.mIoTSkipCountMessageReceiver);
        LyricsTextViewContainer lyricsTextViewContainer = this.mLyricsViewContainer;
        if (lyricsTextViewContainer != null) {
            lyricsTextViewContainer.setFrugalLyricsListener(null);
        }
        Subscription subscription = this.mToggleSuccessfulSubscription;
        if (subscription != null) {
            subscription.unsubscribe();
        }
        Subscription subscription2 = this.blurBackgroundSubscription;
        if (subscription2 != null && !subscription2.isUnsubscribed()) {
            this.blurBackgroundSubscription.unsubscribe();
        }
        this.mCastingButtonHelper.unregisterListeners();
        super.onDestroy();
    }

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onPause() {
        PopupMenu popupMenu = this.mPopupMenu;
        if (popupMenu != null) {
            popupMenu.dismiss();
        }
        super.onPause();
        LyricsTextViewContainer lyricsTextViewContainer = this.mLyricsViewContainer;
        if (lyricsTextViewContainer != null && lyricsTextViewContainer.getMHasLyricsLoaded() && !(this.mMediaItem instanceof AdMediaItem)) {
            if (this.isLyricsInAutomatedPreview.booleanValue()) {
                this.mNPSOverlayMetricsReporter.stopTimer(NPSViews.LYRICS_AUTOMATED_PREVIEW, false);
            } else {
                this.mNPSOverlayMetricsReporter.stopTimer(NPSViews.LYRICS_PREVIEW, false);
            }
        } else {
            this.mNPSOverlayMetricsReporter.stopTimer(NPSViews.ALBUM_ART, false);
        }
        Subscription subscription = this.tooltipSubscription;
        if (subscription != null) {
            subscription.unsubscribe();
        }
        Subscription subscription2 = this.skipsRemainingSubscription;
        if (subscription2 != null) {
            subscription2.unsubscribe();
        }
        if (!isStage()) {
            this.mPlaybackController.removeOnPlayStateChangedListener(this.mPlayStateListener);
            this.mPlaybackController.removeOnMetadataChangedListener(this.mMetadataListener);
            this.mNPSOverlayMetricsReporter.sendDwellTimeMetrics(false);
        }
        this.mAudioVideoToggleInteractor.hideToggle();
        removeToggleFromParent();
        Subscription subscription3 = this.backgroundArtLoad;
        if (subscription3 != null) {
            subscription3.unsubscribe();
        }
        Subscription subscription4 = this.albumArtLoadSubscription;
        if (subscription4 != null) {
            subscription4.unsubscribe();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreateOptionsMenu(final Menu menu, final MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        this.mPlayerContextMenu.onCreateOptionMenu(menu, inflater);
        this.mFragmentMenu = menu;
    }

    @Override // androidx.fragment.app.Fragment
    public void onPrepareOptionsMenu(final Menu menu) {
        StageViewPresenter stageViewPresenter;
        if (getActivity() == null) {
            Log.warning(TAG, "Can't show now playing menu because fragment is detached");
            return;
        }
        super.onPrepareOptionsMenu(menu);
        final MediaItem currentMediaItem = this.mPlaybackController.getCurrentMediaItem();
        if (AmazonApplication.getCapabilities().isNPSLikesSupported()) {
            this.mPlayerContextMenu.setLikeStateListener(new LikeStateListener() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment.11
                @Override // com.amazon.mp3.likes.LikeStateListener
                public void onDislike(final String asin) {
                    MediaItem mediaItem;
                    if (NowPlayingFragment.this.mMetadataView == null || (mediaItem = currentMediaItem) == null || mediaItem.getMediaItemId() == null || asin != currentMediaItem.getMediaItemId().getId()) {
                        return;
                    }
                    NowPlayingFragment.this.mMetadataView.setLikeButtonState(false);
                    if (NowPlayingFragment.this.mPlayerFragment != null) {
                        NowPlayingFragment.this.mPlayerFragment.setLikeButtonState(Boolean.FALSE);
                    }
                }
            });
            updateLikeButton();
        }
        this.mPlayerContextMenu.onUpdateOptionMenu(menu);
        if (shouldDisplayOverflowMenuWithShoveler() && this.mOverflowMenuButton != null) {
            for (int i = 0; i < menu.size(); i++) {
                menu.getItem(i).setVisible(false);
            }
            if (isStage() && (stageViewPresenter = this.mStageViewPresenter) != null) {
                stageViewPresenter.showOverflowButton(true);
            } else {
                this.mOverflowMenuButton.setVisibility(0);
            }
            this.onMenuClickSubscription = Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment$$ExternalSyntheticLambda0
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    this.f$0.lambda$onPrepareOptionsMenu$1((Subscriber) obj);
                }
            }).throttleFirst(1000L, TimeUnit.MILLISECONDS).subscribeOn(AndroidSchedulers.mainThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<View>() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment.12
                @Override // rx.Observer
                public void onCompleted() {
                }

                @Override // rx.Observer
                public void onError(Throwable throwable) {
                }

                @Override // rx.Observer
                public void onNext(View view) {
                    NowPlayingFragment.this.mPlayerContextMenu.onUpdateOptionMenu(menu);
                    NowPlayingFragment.this.prepareOverflowMenu(menu);
                }
            });
            this.mOverflowMenuButton.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment.13
                @Override // android.view.View.OnAttachStateChangeListener
                public void onViewAttachedToWindow(View view) {
                }

                @Override // android.view.View.OnAttachStateChangeListener
                public void onViewDetachedFromWindow(View view) {
                    if (NowPlayingFragment.this.onMenuClickSubscription != null && !NowPlayingFragment.this.onMenuClickSubscription.isUnsubscribed()) {
                        NowPlayingFragment.this.onMenuClickSubscription.unsubscribe();
                    }
                    if (NowPlayingFragment.this.blurBackgroundSubscription != null && !NowPlayingFragment.this.blurBackgroundSubscription.isUnsubscribed()) {
                        NowPlayingFragment.this.blurBackgroundSubscription.unsubscribe();
                    }
                    if (NowPlayingFragment.this.localTrackIdSubscription == null || NowPlayingFragment.this.localTrackIdSubscription.isUnsubscribed()) {
                        return;
                    }
                    NowPlayingFragment.this.localTrackIdSubscription.unsubscribe();
                }
            });
        }
        if (currentMediaItem != null) {
            updateOptionsMenuVisibility(currentMediaItem);
            updateAdVisibility(currentMediaItem);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onPrepareOptionsMenu$1(final Subscriber subscriber) {
        setOverflowButtonClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment$$ExternalSyntheticLambda29
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                NowPlayingFragment.lambda$onPrepareOptionsMenu$0(subscriber, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$onPrepareOptionsMenu$0(Subscriber subscriber, View view) {
        if (subscriber == null || !subscriber.isUnsubscribed()) {
            subscriber.onNext(view);
        }
    }

    public void onStageOverflowMenuButtonClicked() {
        PlayerContextMenu playerContextMenu;
        Menu menu = this.mFragmentMenu;
        if (menu == null || (playerContextMenu = this.mPlayerContextMenu) == null) {
            return;
        }
        playerContextMenu.onUpdateOptionMenu(menu);
        prepareOverflowMenu(this.mFragmentMenu);
    }

    private void setOverflowButtonClickListener(View.OnClickListener overflowButtonClickListener) {
        if (isStage()) {
            StageViewPresenter stageViewPresenter = this.mStageViewPresenter;
            if (stageViewPresenter != null) {
                stageViewPresenter.setOverflowButtonOnClickListener(overflowButtonClickListener);
                return;
            }
            return;
        }
        ImageButton imageButton = this.mOverflowMenuButton;
        if (imageButton != null) {
            imageButton.setOnClickListener(overflowButtonClickListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bitmap getBackgroundScreenshot() {
        if (getActivity() == null || getActivity().getWindow() == null || getActivity().getWindow().getDecorView() == null || getActivity().getWindow().getDecorView().findViewById(android.R.id.content) == null) {
            return null;
        }
        return ShovelerExtensionFunctionsKt.getScreenShot(getActivity().getWindow().getDecorView().findViewById(android.R.id.content));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Observable<Bitmap> scaleBitmap(final Bitmap bitmap, final int width, final int height) {
        return Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment$$ExternalSyntheticLambda33
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                NowPlayingFragment.lambda$scaleBitmap$2(bitmap, width, height, (Subscriber) obj);
            }
        }).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$scaleBitmap$2(Bitmap bitmap, int i, int i2, Subscriber subscriber) {
        subscriber.onNext(BitmapEffectUtils.INSTANCE.getScaledBitmap(bitmap, i, i2));
        subscriber.onCompleted();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public NowPlayingXrayManager getXrayManager() {
        return ((NowPlayingFragmentActivity) getActivity()).getXrayManager();
    }

    private void sendUiPageViewMetric() {
        String string;
        Bundle arguments = getArguments();
        String string2 = null;
        if (arguments != null) {
            string2 = arguments.getString(DeeplinkIntentKey.KEY_REF_MARKER.toString());
            string = arguments.getString(DeeplinkIntentKey.KEY_ASSOCIATE_TAG.toString());
        } else {
            string = null;
        }
        String nowPlayingMediaItemAsin = MediaPlaybackUtil.getNowPlayingMediaItemAsin();
        if (isVideoTrack() && this.videoPlayerFragment != null && nowPlayingMediaItemAsin != null) {
            if (isStage()) {
                ((StageVideoPlayerFragment) this.videoPlayerFragment).sendUiPageViewMetrics(this.mIsToggleVideoOn, nowPlayingMediaItemAsin);
                return;
            } else {
                ((VideoPlayerFragment) this.videoPlayerFragment).sendUiPageViewMetrics(this.mIsToggleVideoOn, nowPlayingMediaItemAsin);
                return;
            }
        }
        MetricsHolder.getManager().handleEvent(UiPageViewEvent.builder((isStage() ? PageType.NOW_PLAYING_STAGE : PageType.NOW_PLAYING).getMetricsValue()).withDetailPageItemId(getDetailPageItemId()).withDetailPageItemIdType(getDetailPageItemIdType()).withExternalReference(string2).withAssociateTag(string).withViewType(ScreenUtil.getScreenViewType(getContext())).build());
    }

    @Override // androidx.fragment.app.Fragment
    public boolean onOptionsItemSelected(final MenuItem item) {
        super.onOptionsItemSelected(item);
        return this.mPlayerContextMenu.onOptionsItemSelected(item);
    }

    @Override // androidx.fragment.app.Fragment
    public void onPictureInPictureModeChanged(final boolean isInPictureInPictureMode) {
        PersistentPlayerFragment persistentPlayerFragment;
        super.onPictureInPictureModeChanged(isInPictureInPictureMode);
        setupViewsForPipIfNeeded(isInPictureInPictureMode);
        if (isInPictureInPictureMode) {
            this.mPlaybackController.addOnMetadataChangedListener(this.mMetadataListener);
            this.mPlaybackController.addOnPlayStateChangedListener(this.mPlayStateListener);
            if (!ScreenUtil.isLandscape() || (persistentPlayerFragment = this.mPlayerFragment) == null || persistentPlayerFragment.getView() == null) {
                return;
            }
            this.mPlayerFragment.getView().setVisibility(8);
            return;
        }
        FragmentKt.doOnResume(this, true, new Function0<Unit>() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment.14
            @Override // kotlin.jvm.functions.Function0
            public Unit invoke() {
                if (NowPlayingFragment.this.getContext() == null || NowPlayingFragment.this.getContext().getResources() == null) {
                    return null;
                }
                NowPlayingFragment nowPlayingFragment = NowPlayingFragment.this;
                nowPlayingFragment.onConfigurationChanged(nowPlayingFragment.getContext().getResources().getConfiguration());
                return null;
            }
        });
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(final Configuration newConfig) {
        ImageView imageView;
        super.onConfigurationChanged(newConfig);
        StageViewPresenter stageViewPresenter = this.mStageViewPresenter;
        if (stageViewPresenter != null) {
            stageViewPresenter.onConfigurationChanged(newConfig);
        }
        if (VideoUtil.INSTANCE.isInPictureInPictureMode(getActivity())) {
            resetContainerHeightAndMargins();
            updateAlbumArtContainerHeight();
            return;
        }
        if (isVideoTrack()) {
            onConfigurationChangedVideo(newConfig);
        } else {
            onConfigurationChangedAudio(newConfig);
        }
        setupToggleButton(newConfig.orientation);
        sendUiPageViewMetric();
        if (shouldShowSfaDialog()) {
            setupAndShowSfaDialog();
        }
        if (isVideoTrack() && (imageView = this.mLandscapeBackgroundArt) != null) {
            imageView.setVisibility(8);
        }
        if (!isStage() && getXrayManager() != null) {
            getXrayManager().checkIfXrayAvailable();
        }
        PlaylistTooltipExperience playlistTooltipExperience = this.mPlaylistTooltipExperience;
        if (playlistTooltipExperience != null) {
            playlistTooltipExperience.onConfigurationChanged(getToolbar(), inImmersiveMode());
        }
        this.skipLimitTooltipPresenter.dismissTooltips();
        if (isStage()) {
            this.mSeekBar.setVisibility(0);
            if (!ScreenUtil.isPortrait() || getActivity() == null || getActivity().findViewById(R.id.AudioBackgroundOverlay) == null) {
                return;
            }
            getActivity().findViewById(R.id.AudioBackgroundOverlay).setVisibility(4);
        }
    }

    private void onConfigurationChangedVideo(final Configuration newConfig) {
        showVideoView();
        if (newConfig.orientation == 1) {
            init();
        } else {
            resetContainerHeightAndMargins();
            updateAlbumArtContainerHeight();
        }
        updateStageOrientation(newConfig.orientation);
    }

    private void updateStageOrientation(int orientation) {
        if (!isStage() || this.mStageViewPresenter == null) {
            return;
        }
        if (orientation == 2) {
            resizeVideoHeightForStage(-1);
            this.mStageViewPresenter.showHideControls(false);
        } else if (orientation == 1) {
            resizeVideoHeightForStage(getResources().getDimensionPixelSize(R.dimen.stage_artwork_image_width));
            this.mStageViewPresenter.showHideControls(true);
        }
    }

    private void resizeVideoHeightForStage(int videoHeight) {
        FrameLayout frameLayout = (FrameLayout) this.mViewRoot.findViewById(R.id.StageAlbumArtContainer);
        if (frameLayout != null) {
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) frameLayout.getLayoutParams();
            ((ViewGroup.MarginLayoutParams) layoutParams).height = videoHeight;
            frameLayout.setLayoutParams(layoutParams);
        }
    }

    private void onConfigurationChangedAudio(final Configuration newConfig) {
        this.mUrlAlbumArtImageCache = null;
        this.mViewContainer.removeAllViews();
        this.mViewRoot = getLayoutInflater().inflate(getContentViewId(), (ViewGroup) this.mViewContainer, true);
        this.mAlbumArtDrawable = null;
        this.mInitialViewContainerHeight = -1;
        resetContainerHeightAndMargins();
        init();
        updateAlbumArt(this.mMediaItem);
        setupViewsForPipIfNeeded();
        updateSystemUIVisibility(newConfig);
    }

    private void updateSystemUIVisibility(final Configuration newConfig) {
        if (newConfig.orientation == 2) {
            setImmersiveMode(true);
        } else if (ScreenUtil.isPortrait()) {
            setImmersiveMode(false);
        }
    }

    private void setImmersiveMode(boolean enabled) {
        ActivityKt.setImmersiveMode(getActivity(), enabled);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean inImmersiveMode() {
        return ActivityKt.inImmersiveMode(getActivity());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setupViewsForPipIfNeeded() {
        setupViewsForPipIfNeeded(VideoUtil.INSTANCE.isInPictureInPictureMode(getActivity()));
    }

    private void setupViewsForPipIfNeeded(final boolean isInPipMode) {
        StageViewPresenter stageViewPresenter;
        if (isInPipMode) {
            setToolbarLayoutVisibility(false);
            BauhausSeekBarWidget bauhausSeekBarWidget = this.mSeekBar;
            if (bauhausSeekBarWidget != null) {
                bauhausSeekBarWidget.setVisibility(8);
            }
            LyricsTextViewContainer lyricsTextViewContainer = this.mLyricsViewContainer;
            if (lyricsTextViewContainer != null) {
                lyricsTextViewContainer.setVisibility(8);
            }
            PlayerMetadataView playerMetadataView = this.mMetadataView;
            if (playerMetadataView != null) {
                playerMetadataView.setVisibility(8);
            }
            NowPlayingPersistentPlayer nowPlayingPersistentPlayer = this.mPersistentPlayer;
            if (nowPlayingPersistentPlayer != null) {
                nowPlayingPersistentPlayer.hide();
            }
            if (!isVideoTrack()) {
                this.mLandscapeBackgroundArt.setVisibility(0);
            }
            if (!isStage() || (stageViewPresenter = this.mStageViewPresenter) == null) {
                return;
            }
            stageViewPresenter.getStageContainerLayout().setVisibility(8);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public boolean onContextItemSelected(final MenuItem item) {
        return NowPlayingContextMenu.onContextItemSelected(item, getActivity(), this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        if (!isStage()) {
            this.mViewRoot.setOnTouchListener(this.mViewRootOnTouchListener);
            this.mAlbumArt.setOnTouchListener(this.mAlbumArtOnTouchListener);
        }
        updateAll(this.mPlaybackController.getCurrentMediaItem());
        if (isStage()) {
            return;
        }
        this.mPlaybackController.addOnLyricsLineUpdateListener(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        this.mViewRoot.setOnTouchListener(null);
        this.mAlbumArt.setOnTouchListener(null);
        this.mSeekBar.pauseSeekbarProgressUpdate();
        if (!isStage()) {
            this.mPlaybackController.removeOnLyricsLineUpdateListener(this);
        }
        PlaylistTooltipExperience playlistTooltipExperience = this.mPlaylistTooltipExperience;
        if (playlistTooltipExperience != null) {
            playlistTooltipExperience.dismissPlaylistTooltip(false);
        }
        this.skipLimitTooltipPresenter.dismissTooltips();
        Subscription subscription = this.badgeSubscription;
        if (subscription != null) {
            subscription.unsubscribe();
        }
    }

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment
    public void onActivated() {
        super.onActivated();
        registerReceivers();
        this.mArtCache.unpause();
    }

    public void dismissTooltipOnOverflowOpened() {
        PlaylistTooltipExperience playlistTooltipExperience = this.mPlaylistTooltipExperience;
        if (playlistTooltipExperience != null) {
            playlistTooltipExperience.dismissPlaylistTooltip(true);
        }
    }

    protected int getContentViewId() {
        return isStage() ? (ScreenUtil.isPortrait() || isVideoTrack()) ? R.layout.stage_now_playing : R.layout.stage_now_playing_land : (isVideoTrack() || ScreenUtil.isPortrait()) ? R.layout.library_now_playing : R.layout.library_now_playing_land;
    }

    protected LyricsTextViewContainer initLyricsViewContainer(LyricsTextViewContainer lyricsViewContainer) {
        if (AmazonApplication.getCapabilities().isLyricsSupported()) {
            return lyricsViewContainer;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean shouldUpdateLikeButtonState(@NonNull final MediaItem mediaItem) {
        boolean zIsLikeButtonActive;
        StageViewPresenter stageViewPresenter;
        boolean z = mediaItem != null && mediaItem.getLikeState() == LikeState.LIKE;
        if (isStage() && (stageViewPresenter = this.mStageViewPresenter) != null) {
            zIsLikeButtonActive = stageViewPresenter.isLikeButtonActive();
        } else {
            zIsLikeButtonActive = this.mMetadataView.isLikeButtonActive();
        }
        return zIsLikeButtonActive != z;
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(@NonNull final Context context) {
        DaggerSubcomponents.INSTANCE.getNowPlayingComponent().inject(this);
        super.onAttach(context);
    }

    @Override // com.amazon.mp3.library.fragment.LibraryBaseFragment, com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onResume() {
        StageViewPresenter stageViewPresenter;
        super.onResume();
        if (isStage() && (stageViewPresenter = this.mStageViewPresenter) != null && stageViewPresenter.getTouchEventHandler() != null) {
            this.mStageViewPresenter.getTouchEventHandler().setOnClickListener(this.mStageViewPresenter.getContext(), new StagePresenterOnClickListener(new WeakReference(getStageViewPresenter())));
            this.mStageViewPresenter.setupTouchHandlers();
        }
        boolean z = true;
        if (!isStage()) {
            this.mNPSOverlayMetricsReporter.sendDwellTimeMetrics(true);
        }
        Subscription subscription = this.albumArtLoadSubscription;
        if (subscription != null) {
            subscription.unsubscribe();
        }
        this.albumArtLoadSubscription = this.artLoadSubject.debounce(100L, TimeUnit.MILLISECONDS).observeOn(Schedulers.computation()).map(new Func1() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment$$ExternalSyntheticLambda18
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return this.f$0.lambda$onResume$3((BitmapDrawable) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(this.onAlbumArtLoadedObserver);
        if (!isVideoTrack()) {
            updateAlbumArt(this.mMediaItem);
        }
        if (!isStage() && getXrayManager() != null) {
            getXrayManager().setWasXrayCheckedForTrack(false);
        }
        final Toolbar toolbar = getToolbar();
        if (toolbar != null) {
            MediaItem mediaItem = this.mMediaItem;
            if (mediaItem == null) {
                this.tooltipSubscription = this.tooltipSubject.subscribe(new Action1() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment$$ExternalSyntheticLambda19
                    @Override // rx.functions.Action1
                    public final void call(Object obj) {
                        this.f$0.lambda$onResume$4(toolbar, (MediaItem) obj);
                    }
                });
            } else if (shouldShowTooltip(mediaItem)) {
                showTooltipAsync(toolbar);
            }
            this.mCastingButton = (CastingImageButton) toolbar.findViewById(R.id.CastingButton);
            this.mOverflowMenuButton = (ImageButton) toolbar.findViewById(R.id.MoreOptions);
            if (isStage()) {
                this.mCastingButton.setVisibility(8);
                this.mOverflowMenuButton.setVisibility(8);
                if (getActivity() instanceof NowPlayingFragmentActivity) {
                    ((NowPlayingFragmentActivity) getActivity()).showShareButton(Boolean.FALSE);
                }
            } else {
                this.mCastingButtonHelper.setCastingButton(new WeakReference<>(this.mCastingButton));
            }
        }
        if (VideoUtil.INSTANCE.isInPictureInPictureMode(getActivity()) || (!ScreenUtil.isPortrait() && isVideoTrack())) {
            z = false;
        }
        setToolbarLayoutVisibility(z);
        MediaItem mediaItem2 = this.mMediaItem;
        if (mediaItem2 instanceof AdMediaItem) {
            updateAdVisibility(mediaItem2);
        }
        startPlaybackIfRequested();
        Subscription subscription2 = this.albumArtLoadSubscription;
        if (subscription2 == null || subscription2.isUnsubscribed()) {
            setUpArtLoadSubscription();
        }
        this.mPlaybackController.addOnPlayStateChangedListener(this.mPlayStateListener);
        this.mPlaybackController.addOnMetadataChangedListener(this.mMetadataListener);
        this.mPlayStateListener.onPlayStateChanged();
        this.mFragmentIsInitializing = Boolean.FALSE;
        if (shouldShowSfaDialog()) {
            setupAndShowSfaDialog();
        }
        setSkipsRemainingTooltipSubscription();
        setupToggleButton(getResources().getConfiguration().orientation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Drawable lambda$onResume$3(BitmapDrawable bitmapDrawable) {
        AlbumArtAndSeekBarLayout albumArtAndSeekBarLayout;
        return (!this.doesPortraitArtRequirePartialBlur || (albumArtAndSeekBarLayout = this.mAlbumArtAndSeekBarLayout) == null) ? bitmapDrawable : albumArtAndSeekBarLayout.createDrawableOverlayWithBlurBackground(bitmapDrawable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onResume$4(View view, MediaItem mediaItem) {
        if (mediaItem == null || !shouldShowTooltip(mediaItem)) {
            return;
        }
        showTooltipAsync(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void prepareOverflowMenu(final Menu menu) {
        final MediaItem currentMediaItem;
        if ((isStage() || !isVideoTrack()) && (currentMediaItem = this.mPlaybackController.getCurrentMediaItem()) != null) {
            final String source = MediaProvider.getSource(MediaItemHelper.getCurrentUri());
            final MediaItemId mediaItemId = currentMediaItem.getMediaItemId(MediaItemId.Type.ASIN);
            MediaItem.Type type = currentMediaItem.getType();
            boolean z = "cirrus-local".equals(source) && mediaItemId == null && type == MediaItem.Type.OWNED_CONTENT;
            boolean z2 = type == MediaItem.Type.PRIME_CONTENT || type == MediaItem.Type.UNLIMITED_CONTENT || type == MediaItem.Type.NIGHTWING_CONTENT;
            final boolean z3 = z2;
            final boolean z4 = z;
            this.localTrackIdSubscription = Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment$$ExternalSyntheticLambda30
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    this.f$0.lambda$prepareOverflowMenu$5(source, currentMediaItem, mediaItemId, z3, z4, (Subscriber) obj);
                }
            }).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(new AnonymousClass17(z2, source, z, menu));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$prepareOverflowMenu$5(String str, MediaItem mediaItem, MediaItemId mediaItemId, boolean z, boolean z2, Subscriber subscriber) {
        String localTrackId;
        if (!"cirrus-local".equals(str)) {
            MediaItemId mediaItemId2 = mediaItem.getMediaItemId(MediaItemId.Type.LUID);
            MediaItemId mediaItemId3 = mediaItem.getMediaItemId(MediaItemId.Type.OWNED);
            if (mediaItemId2 == null) {
                mediaItemId2 = mediaItemId3 != null ? mediaItemId3 : mediaItemId;
            }
            localTrackId = z ? mediaItemId.getId() : mediaItemId2.getId();
        } else {
            localTrackId = getLocalTrackId(mediaItem, z, z2);
        }
        subscriber.onNext(localTrackId);
        subscriber.onCompleted();
    }

    /* JADX INFO: renamed from: com.amazon.mp3.playback.fragment.NowPlayingFragment$17, reason: invalid class name */
    class AnonymousClass17 implements Observer<String> {
        final /* synthetic */ boolean val$isCatalogTrack;
        final /* synthetic */ boolean val$isSideloaded;
        final /* synthetic */ Menu val$menu;
        final /* synthetic */ String val$source;

        @Override // rx.Observer
        public void onCompleted() {
        }

        AnonymousClass17(final boolean val$isCatalogTrack, final String val$source, final boolean val$isSideloaded, final Menu val$menu) {
            this.val$isCatalogTrack = val$isCatalogTrack;
            this.val$source = val$source;
            this.val$isSideloaded = val$isSideloaded;
            this.val$menu = val$menu;
        }

        @Override // rx.Observer
        public void onError(final Throwable throwable) {
            Log.error(NowPlayingFragment.TAG, "Failed to get luid for the current play track", throwable);
        }

        @Override // rx.Observer
        public void onNext(final String trackId) {
            Uri contentUri;
            if (trackId != null) {
                if (this.val$isCatalogTrack && !"cirrus-local".equals(this.val$source)) {
                    contentUri = MediaProvider.Tracks.getPrimeAdditionalTrackContentUri("cirrus", "cta", trackId);
                } else {
                    contentUri = MediaProvider.Tracks.getContentUri(this.val$source, trackId);
                }
                Uri uri = contentUri;
                Log.debug(NowPlayingFragment.TAG, "Currently play track: %s, source = %s, trackUri = %s", trackId, this.val$source, uri.toString());
                final Bundle argumentsBundle = ContextMenuAddToPlaylistBaseFragment.INSTANCE.getArgumentsBundle(uri, false, null, PageType.NOW_PLAYING, this.val$isSideloaded);
                final Bitmap backgroundScreenshot = NowPlayingFragment.this.getBackgroundScreenshot();
                NowPlayingFragment nowPlayingFragment = NowPlayingFragment.this;
                nowPlayingFragment.blurBackgroundSubscription = DynamicBackgroundEffectUtils.INSTANCE.getTransformBlurredImageSubscription(backgroundScreenshot, nowPlayingFragment.getContext(), NowPlayingFragment.this.mStyleSheet, new DynamicBackgroundImageCondition(BlurStyleKey.BLUR_2, ColorKey.COLOR_PRIMARY, AlphaKey.GLASS_3, GradientStyleKey.GRADIENT_1)).flatMap(new Func1() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment$17$$ExternalSyntheticLambda0
                    @Override // rx.functions.Func1
                    public final Object call(Object obj) {
                        return this.f$0.lambda$onNext$0(backgroundScreenshot, (Bitmap) obj);
                    }
                }).subscribe(new Observer<Bitmap>() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment.17.1
                    @Override // rx.Observer
                    public void onCompleted() {
                        if (ActivityKt.isAvailable(NowPlayingFragment.this.getActivity())) {
                            AnonymousClass17 anonymousClass17 = AnonymousClass17.this;
                            NowPlayingFragment nowPlayingFragment2 = NowPlayingFragment.this;
                            ShovelerExtensionFunctionsKt.showOverflowWhenResumed(nowPlayingFragment2, argumentsBundle, anonymousClass17.val$menu, nowPlayingFragment2.mPlayerContextMenu, nowPlayingFragment2.mStyleSheet);
                        }
                    }

                    @Override // rx.Observer
                    public void onError(Throwable throwable) {
                        Log.error(NowPlayingFragment.TAG, "Failed to get the background", throwable);
                        if (ActivityKt.isAvailable(NowPlayingFragment.this.getActivity())) {
                            AnonymousClass17 anonymousClass17 = AnonymousClass17.this;
                            NowPlayingFragment nowPlayingFragment2 = NowPlayingFragment.this;
                            ShovelerExtensionFunctionsKt.showOverflowWhenResumed(nowPlayingFragment2, argumentsBundle, anonymousClass17.val$menu, nowPlayingFragment2.mPlayerContextMenu, nowPlayingFragment2.mStyleSheet);
                        }
                    }

                    @Override // rx.Observer
                    public void onNext(Bitmap bitmap) {
                        boolean zShouldDisplayOverflowMenu = ShovelerExtensionFunctionsKt.shouldDisplayOverflowMenu(NowPlayingFragment.this.requireContext(), NowPlayingFragment.this.mStyleSheet);
                        if (bitmap == null || !zShouldDisplayOverflowMenu) {
                            return;
                        }
                        argumentsBundle.putParcelable("EXTRA_BACKGROUND", bitmap);
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ Observable lambda$onNext$0(Bitmap bitmap, Bitmap bitmap2) {
            return NowPlayingFragment.this.scaleBitmap(bitmap2, bitmap.getWidth(), bitmap.getHeight());
        }
    }

    private String getLocalTrackId(final MediaItem currentMediaItem, final boolean isCatalogTrack, final boolean isSideloaded) {
        MediaItemId mediaItemId = currentMediaItem.getMediaItemId(MediaItemId.Type.ASIN);
        if (isSideloaded) {
            MediaItemId mediaItemId2 = currentMediaItem.getMediaItemId(MediaItemId.Type.OWNED);
            if (mediaItemId2 != null) {
                return mediaItemId2.getId();
            }
            return null;
        }
        if (mediaItemId == null) {
            return null;
        }
        SQLiteDatabase writableDatabase = CirrusDatabase.getWritableDatabase(getContext());
        if (isCatalogTrack) {
            String id = mediaItemId.getId();
            String existingLocalTrackLuidForAsin = TrackSyncUtil.getExistingLocalTrackLuidForAsin(writableDatabase, "source=? AND asin=?", new String[]{String.valueOf(1), id});
            return existingLocalTrackLuidForAsin != null ? existingLocalTrackLuidForAsin : id;
        }
        return TrackSyncUtil.getExistingLocalTrackLuidForAsin(writableDatabase, mediaItemId.getId());
    }

    private boolean shouldShowTooltip(MediaItem mMediaItem) {
        return (MediaItemExtensions.isVideo(mMediaItem) || MediaItemHelper.isInterludeMediaItem(mMediaItem) || (mMediaItem instanceof AdMediaItem)) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showTooltip(View toolbar, @NonNull StyleSheet styleSheet) {
        if (this.mPlaylistTooltipExperience == null || isStage()) {
            return;
        }
        this.mPlaylistTooltipExperience.showPlaylistTooltip(toolbar, this.mHasRotated.booleanValue(), this.mFragmentIsInitializing.booleanValue(), styleSheet, inImmersiveMode());
    }

    private void showTooltipAsync(final View toolbar) {
        StyleSheet styleSheet = this.mStyleSheet;
        if (styleSheet != null) {
            showTooltip(toolbar, styleSheet);
        } else {
            StyleSheetProvider.getStyleSheet().observe(getViewLifecycleOwner(), new androidx.lifecycle.Observer<StyleSheet>() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment.18
                @Override // androidx.lifecycle.Observer
                public void onChanged(StyleSheet styleSheet2) {
                    NowPlayingFragment.this.showTooltip(toolbar, styleSheet2);
                    StyleSheetProvider.getStyleSheet().removeObserver(this);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSkipsRemainingTooltipSubscription() {
        StageViewPresenter stageViewPresenter;
        Subscription subscription = this.skipsRemainingSubscription;
        if (subscription == null || subscription.isUnsubscribed()) {
            Observable<Boolean> onNextClickedSubject = null;
            if (isStage() && (stageViewPresenter = this.mStageViewPresenter) != null) {
                onNextClickedSubject = stageViewPresenter.getOnNextClickedSubject();
            } else {
                NowPlayingPersistentPlayer nowPlayingPersistentPlayer = this.mPersistentPlayer;
                if (nowPlayingPersistentPlayer != null) {
                    onNextClickedSubject = nowPlayingPersistentPlayer.getOnNextClickedSubject();
                }
            }
            if (onNextClickedSubject != null) {
                this.skipsRemainingSubscription = onNextClickedSubject.subscribe(new Action1() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment$$ExternalSyntheticLambda31
                    @Override // rx.functions.Action1
                    public final void call(Object obj) {
                        NowPlayingFragment.lambda$setSkipsRemainingTooltipSubscription$6((Boolean) obj);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$setSkipsRemainingTooltipSubscription$6(Boolean bool) {
        if (bool.booleanValue()) {
            canShowSkipLimitToolTip = true;
        }
    }

    private Toolbar getToolbar() {
        View toolbarLayout = getToolbarLayout();
        if (toolbarLayout != null) {
            return (Toolbar) toolbarLayout.findViewById(R.id.toolbar);
        }
        return null;
    }

    private View getToolbarLayout() {
        FragmentActivity activity = getActivity();
        if (ActivityKt.isAvailable(activity)) {
            return activity.findViewById(R.id.toolbar_layout);
        }
        return null;
    }

    private void setToolbarLayoutVisibility(boolean shouldShow) {
        boolean z = shouldShow && !isLyricsOrXrayFullScreenOpen();
        View toolbarLayout = getToolbarLayout();
        if (toolbarLayout != null) {
            toolbarLayout.setVisibility(z ? 0 : 8);
        }
    }

    private boolean isLyricsOrXrayFullScreenOpen() {
        FragmentManager supportFragmentManager = getActivity().getSupportFragmentManager();
        if (supportFragmentManager == null) {
            return false;
        }
        Fragment fragmentFindFragmentById = supportFragmentManager.findFragmentById(R.id.OverlayContainer);
        return (fragmentFindFragmentById instanceof LyricsFragment) || (fragmentFindFragmentById instanceof StageSwipeablePagesFragment);
    }

    @SuppressLint({"NewApi"})
    private void setupToggleButton(int orientation) {
        if (!AmazonApplication.getCapabilities().isVideosEnabled() || VideoUtil.INSTANCE.isInPictureInPictureMode(getActivity())) {
            removeToggleFromParent();
            return;
        }
        if (orientation == 2 && this.videoPlayerFragment != null) {
            if (isStage()) {
                ((StageVideoPlayerFragment) this.videoPlayerFragment).addLandscapeAudioVideoToggle();
            } else {
                ((VideoPlayerFragment) this.videoPlayerFragment).addToggle();
            }
        } else {
            setToolbarLayoutVisibility(true);
            addToggleToToolbarLayout();
            this.mAudioVideoToggleInteractor.updateToggleState(getContext());
        }
        this.mAudioVideoToggleInteractor.setAudioVideoToggleListener(this.mAudioVideoToggleListener);
        this.mAudioVideoToggleInteractor.updateToggleState(getContext());
        Subscription subscription = this.mToggleSuccessfulSubscription;
        if (subscription != null) {
            subscription.unsubscribe();
        }
        Subscription subscriptionSubscribe = this.mAudioVideoToggleInteractor.getToggleSuccessfulObservable().subscribe(new Action1() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment$$ExternalSyntheticLambda9
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                this.f$0.lambda$setupToggleButton$7((HybridQueueType) obj);
            }
        }, new Action1() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment$$ExternalSyntheticLambda10
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                NowPlayingFragment.lambda$setupToggleButton$8((Throwable) obj);
            }
        });
        this.mToggleSuccessfulSubscription = subscriptionSubscribe;
        this.compositeSubscription.add(subscriptionSubscribe);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupToggleButton$7(HybridQueueType hybridQueueType) {
        if (ActivityKt.isAvailable(getActivity())) {
            getActivity().invalidateOptionsMenu();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$setupToggleButton$8(Throwable th) {
        Log.error(TAG, "Error while toggling audio/video %s", CollectionKt.toString(th.getStackTrace(), "\n"));
    }

    private void addToggleToToolbarLayout() {
        View toolbarLayout = getToolbarLayout();
        final ViewGroup viewGroup = toolbarLayout != null ? (ViewGroup) toolbarLayout.findViewById(R.id.audioVideoToggleView) : null;
        if (viewGroup != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment$$ExternalSyntheticLambda7
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$addToggleToToolbarLayout$9(viewGroup);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$addToggleToToolbarLayout$9(ViewGroup viewGroup) {
        View toggleView = this.mAudioVideoToggleInteractor.getToggleView();
        ViewExtensions.removeFromParent(toggleView);
        viewGroup.addView(toggleView);
    }

    private void updateCastingButton() {
        CastingButtonHelper castingButtonHelper = this.mCastingButtonHelper;
        if (castingButtonHelper != null) {
            castingButtonHelper.refreshVisibility();
        }
    }

    private void removeToggleFromParent() {
        ViewExtensions.removeFromParent(this.mAudioVideoToggleInteractor.getToggleView());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setupAdsPrefsNotification(MediaItem currentMediaItem) {
        Context context = getContext();
        if (context != null && isAdded() && canShowBehavioralAdsForMedia(currentMediaItem)) {
            new DynamicMessagingManager(context).displayDynamicMessage((FragmentActivity) context, new DynamicMessageEvent(Trigger.FIRST_STREAM), new DynamicMessageMetricsAttributes(PageType.NOW_PLAYING.getMetricsValue(), null)).subscribe();
        }
    }

    private boolean canShowBehavioralAdsForMedia(MediaItem item) {
        if (!UserSubscriptionUtil.getUserSubscription().isNightwingOnly()) {
            return false;
        }
        try {
            return FeatureFlagsProvider.getFeatureFlagProvider().isBehavioralAdsNotificationSupported() && !item.getMediaAccessInfo().getContentAccessTypes().contains(ContentAccessType.OWNED);
        } catch (FeatureFlagUnavailableException e) {
            Log.error(TAG, "Feature flag provider unavailable ", e);
            return FeatureFlagsProvider.getNoOpProvider().isBehavioralAdsNotificationSupported();
        }
    }

    private void initStyleSheet() {
        StyleSheetProvider.getStyleSheet().observe(getViewLifecycleOwner(), new androidx.lifecycle.Observer() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment$$ExternalSyntheticLambda5
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.lambda$initStyleSheet$10((StyleSheet) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initStyleSheet$10(StyleSheet styleSheet) {
        this.mStyleSheet = styleSheet;
    }

    private void initViewStyling() {
        StyleSheet styleSheet = this.mStyleSheet;
        if (styleSheet == null) {
            StyleSheetProvider.getStyleSheet().observe(getViewLifecycleOwner(), new androidx.lifecycle.Observer() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment$$ExternalSyntheticLambda17
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    this.f$0.lambda$initViewStyling$11((StyleSheet) obj);
                }
            });
            return;
        }
        this.mMetadataView.initViewStyling(styleSheet);
        if (this.mShowLoading) {
            showLoadingMetadata();
        } else {
            BaseImage baseImage = this.mAlbumArt;
            StyleSheet styleSheet2 = this.mStyleSheet;
            CornerSizeKey cornerSizeKey = CornerSizeKey.MEDIUM;
            baseImage.applyCornerRadius(styleSheet2.getCornerSize(cornerSizeKey).intValue());
            this.mSwapArtwork.applyCornerRadius(this.mStyleSheet.getCornerSize(cornerSizeKey).intValue());
        }
        updateGridLayout();
        LyricsTextViewContainer lyricsTextViewContainer = this.mLyricsViewContainer;
        if (lyricsTextViewContainer != null) {
            lyricsTextViewContainer.initStyleSheet(this.mStyleSheet);
        }
        this.mMessagingDialog.initViewStyling(this.mStyleSheet);
        if (isStage()) {
            setupStageViews();
        } else {
            this.mPersistentPlayerContainer.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initViewStyling$11(StyleSheet styleSheet) {
        this.mStyleSheet = styleSheet;
        initViewStyling();
    }

    private void setupStageViews() {
        StageViewPresenter stageViewPresenter = this.mStageViewPresenter;
        if (stageViewPresenter != null) {
            stageViewPresenter.onDestroy();
        }
        StageViewPresenter stageViewPresenter2 = new StageViewPresenter(new WeakReference(this.mViewRoot), new WeakReference(getActivity()), getLifecycle());
        this.mStageViewPresenter = stageViewPresenter2;
        stageViewPresenter2.onCreate(this.mStyleSheet);
        this.mStageViewPresenter.setLikeButtonOnClickListener(this.mLikeButtonOnClickListener);
        this.mStageViewPresenter.setAudioQualityBadgeClickListener(this.mOnQualityButtonClickListener);
        if (isVideoTrack()) {
            return;
        }
        FrameLayout frameLayout = this.mStageBackgroundFrame;
        if (frameLayout != null) {
            frameLayout.setVisibility(0);
        }
        this.mLandscapeBackgroundArtFrame.setVisibility(8);
        if (ScreenUtil.isLandscape()) {
            PlayerMetadataView playerMetadataView = this.mMetadataView;
            if (playerMetadataView != null) {
                playerMetadataView.setVisibility(8);
            }
            PlayerMetadataView playerMetadata = this.mStageViewPresenter.getPlayerMetadata();
            if (playerMetadata != null) {
                this.mMetadataView = playerMetadata;
                playerMetadata.setVisibility(0);
                if (this.mMediaItem == null) {
                    this.mMediaItem = this.mPlaybackController.getCurrentMediaItem();
                }
                this.mMetadataView.switchToStageLandscape();
                updateMetaData(this.mMediaItem);
            }
            ScreenSizeKey screenSizeKey = this.mStyleSheet.getScreenSizeKey(com.amazon.ui.foundations.utils.ScreenUtil.INSTANCE.getScreenWidthPixels((WindowManager) AmazonApplication.getContext().getSystemService("window")));
            if (screenSizeKey == ScreenSizeKey.XSMALL || screenSizeKey == ScreenSizeKey.SMALL || screenSizeKey == ScreenSizeKey.MEDIUM) {
                return;
            }
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) this.mSeekBar.getLayoutParams();
            ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin = this.mStyleSheet.getSpacerInPixels(SpacerKey.HUGE).intValue();
            this.mSeekBar.setLayoutParams(layoutParams);
            return;
        }
        this.mStageViewPresenter.hideLandscapePlayerMetadata();
    }

    private void updateGridLayout() {
        int marginSpace;
        AlbumArtAndSeekBarLayout albumArtAndSeekBarLayout;
        if (!this.mShowLoading) {
            updateMetadataText();
        }
        GridSize gridSize = this.mStyleSheet.getGridSize(getContext());
        if (gridSize != null) {
            int iDpToPixels = SpacerParser.INSTANCE.dpToPixels(getContext().getResources(), 500);
            int screenWidthPixels = com.amazon.ui.foundations.utils.ScreenUtil.INSTANCE.getScreenWidthPixels((WindowManager) AmazonApplication.getContext().getSystemService("window"));
            if (ScreenUtil.isPortrait() && screenWidthPixels > iDpToPixels) {
                marginSpace = (screenWidthPixels - iDpToPixels) / 2;
            } else {
                marginSpace = gridSize.getMarginSpace() + gridSize.getColumnSpace();
            }
            int iIntValue = ScreenUtil.isLandscape() ? this.mStyleSheet.getSpacerInPixels(SpacerKey.MINI).intValue() : 0;
            View view = this.mPersistentPlayerContainer;
            if (view != null) {
                LayoutParamUtils.INSTANCE.setLayoutMargins(view, 0, ScreenUtil.isPortrait() ? 0 : -2, Boolean.FALSE, Integer.valueOf(marginSpace), 0, Integer.valueOf(marginSpace), Integer.valueOf(iIntValue));
            }
            if (!ScreenUtil.isPortrait() || (albumArtAndSeekBarLayout = this.mAlbumArtAndSeekBarLayout) == null) {
                return;
            }
            LayoutParamUtils.INSTANCE.setLayoutMargins(albumArtAndSeekBarLayout, -1, -1, Boolean.FALSE, Integer.valueOf(marginSpace), 0, Integer.valueOf(marginSpace), 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateAudioQualityBadge() {
        if (UserSubscriptionUtil.getUserSubscription().isKatana()) {
            final AtomicInteger atomicInteger = new AtomicInteger();
            Subscription subscription = this.badgeSubscription;
            if (subscription != null) {
                subscription.unsubscribe();
            }
            this.badgeSubscription = KatanaNPSBadgeProvider.getAudioQualityBadge(getContext()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).retryWhen(new Func1() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment$$ExternalSyntheticLambda11
                @Override // rx.functions.Func1
                public final Object call(Object obj) {
                    return this.f$0.lambda$updateAudioQualityBadge$13(atomicInteger, (Observable) obj);
                }
            }).subscribe(new Action1() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment$$ExternalSyntheticLambda12
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    this.f$0.lambda$updateAudioQualityBadge$14((KatanaNPSBadgeProvider.AudioQualityBadge) obj);
                }
            }, new Action1() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment$$ExternalSyntheticLambda13
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    NowPlayingFragment.lambda$updateAudioQualityBadge$15((Throwable) obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Observable lambda$updateAudioQualityBadge$13(final AtomicInteger atomicInteger, Observable observable) {
        return observable.flatMap(new Func1() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment$$ExternalSyntheticLambda32
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return this.f$0.lambda$updateAudioQualityBadge$12(atomicInteger, (Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Observable lambda$updateAudioQualityBadge$12(AtomicInteger atomicInteger, Throwable th) {
        this.mMetadataView.setBadge(null, null);
        int iIncrementAndGet = atomicInteger.incrementAndGet();
        if (iIncrementAndGet <= 3) {
            Log.warning(TAG, "updateAudioQualityBadge retry#" + iIncrementAndGet, th);
            return Observable.timer(500L, TimeUnit.MILLISECONDS);
        }
        return Observable.error(th);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$updateAudioQualityBadge$14(KatanaNPSBadgeProvider.AudioQualityBadge audioQualityBadge) {
        Drawable drawable = null;
        if (audioQualityBadge != null && isAdded()) {
            this.badgeText = NowPlayingUtil.getCurrentQualityBadgeTitle(getResources());
            String string = getResources().getString(audioQualityBadge.getContentDescription());
            if (isStage()) {
                if (this.mStageViewPresenter != null) {
                    if (ScreenUtil.isPortrait()) {
                        this.mStageViewPresenter.updateAudioQualityBadge(this.badgeText, string);
                        return;
                    } else {
                        this.mStageViewPresenter.hideAudioQualityBadge();
                        return;
                    }
                }
                return;
            }
            if (audioQualityBadge.ordinal() == KatanaNPSBadgeProvider.AudioQualityBadge.DOLBY_ATMOS.ordinal() || audioQualityBadge.ordinal() == KatanaNPSBadgeProvider.AudioQualityBadge.DOLBY_ATMOS_ALERT.ordinal()) {
                Drawable drawable2 = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_dd_logo, null);
                drawable2.setTint(ResourcesCompat.getColor(getResources(), R.color.bauhaus_hd, null));
                drawable = drawable2;
            }
            this.mMetadataView.setBadge(this.badgeText, drawable);
            this.mMetadataView.setContentDescription(string);
            KatanaNPSBadgeProvider.displayShowcaseIfNeeded(getActivity(), this.mMetadataView.getBadgeShowcaseTarget());
            return;
        }
        this.badgeText = null;
        if (isStage()) {
            StageViewPresenter stageViewPresenter = this.mStageViewPresenter;
            if (stageViewPresenter != null) {
                stageViewPresenter.hideAudioQualityBadge();
                return;
            }
            return;
        }
        this.mMetadataView.setBadge(null, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$updateAudioQualityBadge$15(Throwable th) {
        Log.error(TAG, "Error setting Audio Quality Badge", th);
    }

    private String getDetailPageItemId() {
        if (isStation()) {
            return getStationKey();
        }
        return null;
    }

    private String getDetailPageItemIdType() {
        if (isStation()) {
            return EntityIdType.STATION_KEY.getMetricsValue();
        }
        return null;
    }

    private void setNonBlockingUpsellInformation() {
        this.mMessagingDialog.setMessage(getString(R.string.dmusic_hawkfire_account_nightwing_upsell_nowplaying_message));
        this.mMessagingDialog.setTitle(getString(R.string.dmusic_hawkfire_account_nightwing_nonblocking_upsell_dialog_header));
        if (AmazonApplication.getCapabilities().isFreeTierContextualUpsellsEnabled() || Feature.sonic_rush.isEnabled()) {
            setContextualNonBlockingUpsellInformation();
        }
        if (FMPMFeatureGating.PROJECT_PHOENIX_DISABLE_UPSELL_CTA.isDisabled() && !UserSubscriptionUtil.getUserSubscription().isHomeOnly()) {
            if (this.mShowNpsContextualUpsell) {
                this.mMessagingDialog.setButton(getString(R.string.dmusic_hawkfire_account_nightwing_sonicrush_nonblocking_nowplaying_shuffle_upsell_dialog_btn));
            } else {
                this.mMessagingDialog.setButton(getString(R.string.dmusic_hawkfire_account_nightwing_upsell_nowplaying_btn));
            }
            this.mMessagingDialog.setButtonOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment$$ExternalSyntheticLambda24
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$setNonBlockingUpsellInformation$16(view);
                }
            });
        } else {
            this.mMessagingDialog.setButton("");
        }
        this.mMessagingDialog.setCloseButtonOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment$$ExternalSyntheticLambda25
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$setNonBlockingUpsellInformation$17(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setNonBlockingUpsellInformation$16(View view) {
        Context context = getContext();
        DynamicMessagingManager dynamicMessagingManager = new DynamicMessagingManager(context);
        if (AmazonApplication.getCapabilities().shouldUseDynamicMessagingForAdsSettingsOnDemandListeningUpsell()) {
            dynamicMessagingManager.displayDynamicMessage((FragmentActivity) context, new DynamicMessageEvent(Trigger.ON_DEMAND_LISTENING_UPSELL), new DynamicMessageMetricsAttributes(ContentName.BAUHAUS_HAWKFIRE_NONBLOCKING_UPSELL.getMetricsValue(), null)).subscribe();
        } else {
            getActivity().startActivity(UnlimitedUpsellActivity.getStartIntent(getActivity(), this.webTarget));
        }
        if (this.mShowNpsContextualUpsell) {
            MetricsLogger.emitUiClickMetricForUpsell(ActionType.NPS_CONTEXTUAL_UPSELL_DIALOG_CTA, ContentName.NPS_CONTEXTUAL_UPSELL_DIALOG.getMetricsValue());
        } else if (this.isFreeTrialEligible) {
            MetricsLogger.emitUiClickMetricForUpsell(ActionType.START_TRIAL_BAUHAUS_NONBLOCKING_UPSELL_DIALOG, ContentName.BAUHAUS_UNLIMITED_UPSELL_TO_TRIAL.getMetricsValue());
        } else {
            MetricsLogger.emitUiClickMetricForUpsell(ActionType.START_PAID_BAUHAUS_NONBLOCKING_UPSELL_DIALOG, ContentName.BAUHAUS_UNLIMITED_UPSELL_TO_PAID.getMetricsValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setNonBlockingUpsellInformation$17(View view) {
        this.upsellDismissed = Boolean.TRUE;
        displayDialogView(false);
        if (this.mShowNpsContextualUpsell) {
            MetricsLogger.emitUiClickMetricForUpsell(ActionType.NPS_CONTEXTUAL_UPSELL_DIALOG_DISMISSED, ContentName.NPS_CONTEXTUAL_UPSELL_DIALOG.getMetricsValue());
        } else {
            MetricsLogger.emitUiClickMetricForUpsell(ActionType.NO_THANKS_BAUHAUS_NONBLOCKING_UPSELL_DIALOG, ContentName.BAUHAUS_UNLIMITED_UPSELL.getMetricsValue());
        }
    }

    private void setContextualNonBlockingUpsellInformation() {
        UpsellInformation upsellInformation = this.mUpsellInformation;
        if (upsellInformation != null) {
            UpsellMessagingDialog contextualUpsellMessagingDialog = getContextualUpsellMessagingDialog(upsellInformation.getUpsellSourceEntity());
            this.mMessagingDialog.setTitle(contextualUpsellMessagingDialog.getUpsellInfoHeader());
            this.mMessagingDialog.setMessage(contextualUpsellMessagingDialog.getUpsellInfoText());
            if (this.mShowNpsContextualUpsell) {
                this.mMessagingDialog.setHeaderMaxLines(2, 10, 28, 1);
                this.mMessagingDialog.setMessageMaxLines(2, 10, 20, 1);
            }
        }
    }

    private UpsellMessagingDialog getContextualUpsellMessagingDialog(final UpsellSourceEntity upsellSourceEntity) {
        String npsUpsellItemTitle = getNpsUpsellItemTitle(upsellSourceEntity);
        if (npsUpsellItemTitle != null && Feature.now_playing_screen_contextual_upsell.isEnabled()) {
            this.mShowNpsContextualUpsell = true;
            this.mDefaultUpsellTimeout = 20000;
            String str = String.format(getString(R.string.dmusic_hawkfire_account_nightwing_sonicrush_nonblocking_nowplaying_shuffle_upsell_dialog_header), npsUpsellItemTitle);
            String string = getString(R.string.dmusic_hawkfire_account_nightwing_sonicrush_nonblocking_nowplaying_shuffle_upsell_dialog_message);
            MetricsLogger.sendUpsellContentViewEvent(ContainerType.NON_BLOCKING_UPSELL_DIALOG, ContentName.NPS_CONTEXTUAL_UPSELL_DIALOG);
            return new UpsellMessagingDialog(str, string);
        }
        String string2 = getString(R.string.dmusic_hawkfire_account_nightwing_nonblocking_upsell_dialog_header);
        String string3 = getString(R.string.dmusic_hawkfire_account_nightwing_upsell_nowplaying_message);
        int i = AnonymousClass33.$SwitchMap$com$amazon$mp3$prime$upsell$nightwing$model$UpsellSourceEntity[upsellSourceEntity.ordinal()];
        if (i == 1) {
            string2 = getString(R.string.dmusic_hawkfire_account_nightwing_nonblocking_upsell_dialog_header_track);
            string3 = getString(R.string.dmusic_hawkfire_account_nightwing_upsell_nowplaying_message_track);
        } else if (i == 2) {
            string2 = getString(R.string.dmusic_hawkfire_account_nightwing_nonblocking_upsell_dialog_header_album);
            string3 = getString(R.string.dmusic_hawkfire_account_nightwing_upsell_nowplaying_message_album);
        } else if (i == 3) {
            string2 = getString(R.string.dmusic_hawkfire_account_nightwing_nonblocking_upsell_dialog_header_playlist);
            string3 = getString(R.string.dmusic_hawkfire_account_nightwing_upsell_nowplaying_message_playlist);
        } else if (i == 4) {
            string2 = getString(R.string.dmusic_hawkfire_account_nightwing_nonblocking_upsell_dialog_header_artist);
            string3 = getString(R.string.dmusic_hawkfire_account_nightwing_upsell_nowplaying_message_artist);
        }
        return new UpsellMessagingDialog(string2, string3);
    }

    /* JADX INFO: renamed from: com.amazon.mp3.playback.fragment.NowPlayingFragment$33, reason: invalid class name */
    static /* synthetic */ class AnonymousClass33 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$prime$upsell$nightwing$model$UpsellSourceEntity;

        static {
            int[] iArr = new int[UpsellSourceEntity.values().length];
            $SwitchMap$com$amazon$mp3$prime$upsell$nightwing$model$UpsellSourceEntity = iArr;
            try {
                iArr[UpsellSourceEntity.TRACK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$upsell$nightwing$model$UpsellSourceEntity[UpsellSourceEntity.ALBUM.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$upsell$nightwing$model$UpsellSourceEntity[UpsellSourceEntity.PLAYLIST.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$upsell$nightwing$model$UpsellSourceEntity[UpsellSourceEntity.ARTIST.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private String getNpsUpsellItemTitle(final UpsellSourceEntity upsellSourceEntity) {
        String strRemoveExplicitLabelFromAlbumOrTrackTitle;
        UpsellInformation upsellInformation = this.mUpsellInformation;
        if (upsellInformation == null) {
            return null;
        }
        if (upsellSourceEntity == UpsellSourceEntity.ARTIST) {
            strRemoveExplicitLabelFromAlbumOrTrackTitle = upsellInformation.getArtistName();
        } else if (upsellSourceEntity == UpsellSourceEntity.TRACK || upsellSourceEntity == UpsellSourceEntity.ALBUM) {
            strRemoveExplicitLabelFromAlbumOrTrackTitle = StringUtil.removeExplicitLabelFromAlbumOrTrackTitle(upsellInformation.getSeedName());
        } else {
            strRemoveExplicitLabelFromAlbumOrTrackTitle = upsellSourceEntity == UpsellSourceEntity.PLAYLIST ? upsellInformation.getSeedName() : "";
        }
        if (StringUtil.isNullOrWhiteSpaces(strRemoveExplicitLabelFromAlbumOrTrackTitle)) {
            return null;
        }
        if (strRemoveExplicitLabelFromAlbumOrTrackTitle.length() + getString(R.string.dmusic_hawkfire_account_nightwing_sonicrush_nonblocking_nowplaying_shuffle_upsell_dialog_header).length() <= 80) {
            return strRemoveExplicitLabelFromAlbumOrTrackTitle;
        }
        return strRemoveExplicitLabelFromAlbumOrTrackTitle.substring(0, (80 - r0.length()) - 3) + "...";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean shouldShowSfaDialog() {
        return AutoPlaySFAUtil.shouldShowSfaDialog() && SettingsUtil.isSfaSettingEnabled(getContext()) && !AutoPlaySFAUtil.didCustomerDismissDialog(getContext()) && !AutoPlaySFAUtil.shouldNotSeeSFADialogWhileSFAPlaying();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setupAndShowSfaDialog() {
        this.mMessagingDialog.setMessage(getString(R.string.dmusic_nowplaying_sfa_dialog_title));
        this.mMessagingDialog.setTitle(getString(R.string.dmusic_nowplaying_sfa_dialog_header));
        this.mMessagingDialog.setButton(getString(R.string.dmusic_nowplaying_sfa_dialog_button));
        this.mMessagingDialog.setButtonOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment$$ExternalSyntheticLambda22
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$setupAndShowSfaDialog$18(view);
            }
        });
        this.mMessagingDialog.setCloseButtonOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment$$ExternalSyntheticLambda23
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$setupAndShowSfaDialog$19(view);
            }
        });
        displayDialogView(true);
        this.isNonBlockingDialogueSFA = true;
        MetricsLogger.emitUIContentViewMetric(ContentName.SFA_DIALOG_FRAGMENT, ContainerType.DIALOG);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupAndShowSfaDialog$18(View view) {
        displayDialogView(false);
        AutoPlaySFAUtil.onSfaDialogShown();
        startActivity(SettingsActivity.getStartAndScrollToIntent(getApplication(), R.string.setting_key_category_playback));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupAndShowSfaDialog$19(View view) {
        displayDialogView(false);
        AutoPlaySFAUtil.onSfaDialogShown();
        AutoPlaySFAUtil.customerDismissedDialogAndShouldNotSeeItAgain(getContext());
        this.isNonBlockingDialogueSFA = false;
        MetricsLogger.emitUiClickMetricForCancelSfa(ActionType.CANCEL_BAUHAUS_NONBLOCKING_SFA_DIALOG);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void displayDialogView(boolean showDialog) {
        this.mBauhausNowPlayingDialogView.setVisibility(showDialog ? 0 : 8);
        LyricsTextViewContainer lyricsTextViewContainer = this.mLyricsViewContainer;
        if (lyricsTextViewContainer != null) {
            lyricsTextViewContainer.setIsBlocked(showDialog);
            this.mLyricsViewContainer.show(!showDialog);
        }
        MediaItem currentMediaItem = this.mPlaybackController.getCurrentMediaItem();
        if (currentMediaItem != null) {
            showLyrics(currentMediaItem);
        }
    }

    private void setAdUpsellInformation() {
        LinearLayout linearLayout = (LinearLayout) this.mViewRoot.findViewById(R.id.ads_upsell_banner);
        this.mAdsUpsellBanner = linearLayout;
        if (linearLayout == null) {
            Log.error(TAG, "mAdsUpsellBanner is null, cannot proceed to show set Ad Upsell");
            return;
        }
        this.mAdsUpsellArea = (RelativeLayout) linearLayout.findViewById(R.id.feature_banner_upsell_area);
        this.mAdsDismissableArea = (RelativeLayout) this.mAdsUpsellBanner.findViewById(R.id.feature_banner_dimissable_area);
        this.mAdsCloseButton = (ImageButton) this.mAdsUpsellBanner.findViewById(R.id.AdCloseButton);
        this.mAdsUpsellArea.setOnClickListener(this.mAdUpsellOnClickListener);
        this.mAdsDismissableArea.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment.20
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                NowPlayingFragment.this.mAdsUpsellBanner.setVisibility(8);
                NowPlayingFragment.this.saveAdCloseUserPref();
                MetricsLogger.emitUiClickMetricForUpsell(ActionType.NO_THANKS_ADS_UPSELL_DIALOG, ContentName.BAUHAUS_UNLIMITED_UPSELL.getMetricsValue());
            }
        });
        this.mAdsCloseButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment.21
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                NowPlayingFragment.this.mAdsUpsellBanner.setVisibility(8);
                NowPlayingFragment.this.saveAdCloseUserPref();
                MetricsLogger.emitUiClickMetricForUpsell(ActionType.NO_THANKS_ADS_UPSELL_DIALOG, ContentName.BAUHAUS_UNLIMITED_UPSELL.getMetricsValue());
            }
        });
    }

    private void setUpAdsUpsellBauhausRibbon() {
        BauhausRibbon bauhausRibbon = (BauhausRibbon) this.mViewRoot.findViewById(R.id.upsell_bauhaus_ribbon);
        this.mBauhausRibbon = bauhausRibbon;
        StyleSheet styleSheet = this.mStyleSheet;
        if (styleSheet != null && bauhausRibbon != null) {
            bauhausRibbon.initViewStyling(styleSheet);
            this.mBauhausRibbon.setRibbonText(R.string.dmusic_hawkfire_account_nightwing_upsell_ads_upsell_message);
            this.mBauhausRibbon.setRibbonClickListener(this.mAdUpsellOnClickListener);
            this.mBauhausRibbon.setNavigationButtonClickListener(this.mAdUpsellOnClickListener);
            this.mBauhausRibbon.setDismissButtonClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment$$ExternalSyntheticLambda6
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$setUpAdsUpsellBauhausRibbon$20(view);
                }
            });
            return;
        }
        Log.error(TAG, "Cannot setup Ads Upsell Bauhaus Ribbon since mStyle is null");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setUpAdsUpsellBauhausRibbon$20(View view) {
        this.mBauhausRibbon.setVisibility(8);
        saveAdCloseUserPref();
        MetricsLogger.emitUiClickMetricForUpsell(ActionType.NO_THANKS_ADS_UPSELL_DIALOG, ContentName.BAUHAUS_UNLIMITED_UPSELL.getMetricsValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$21(View view) {
        Context context = getContext();
        DynamicMessagingManager dynamicMessagingManager = new DynamicMessagingManager(context);
        if (AmazonApplication.getCapabilities().shouldUseDynamicMessagingForAdsSettingsOnDemandListeningUpsell()) {
            dynamicMessagingManager.displayDynamicMessage((FragmentActivity) context, new DynamicMessageEvent(Trigger.ADS_UPSELL), new DynamicMessageMetricsAttributes(ContentName.BAUHAUS_HAWKFIRE_ADS_UPSELL.getMetricsValue(), null)).subscribe();
        } else {
            getActivity().startActivity(UnlimitedUpsellActivity.getStartIntent(getActivity(), this.webTarget));
        }
        if (this.isFreeTrialEligible) {
            MetricsLogger.emitUiClickMetricForUpsell(ActionType.START_TRIAL_BAUHAUS_ADS_UPSELL_DIALOG, ContentName.BAUHAUS_UNLIMITED_UPSELL_TO_TRIAL.getMetricsValue());
        } else {
            MetricsLogger.emitUiClickMetricForUpsell(ActionType.START_PAID_BAUHAUS_ADS_UPSELL_DIALOG, ContentName.BAUHAUS_UNLIMITED_UPSELL_TO_PAID.getMetricsValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearUserAdPref() {
        SharedPreferences.Editor editorEdit = getActivity().getSharedPreferences(getSharedPrefName(), 0).edit();
        editorEdit.remove("HIDE_AD_UPSELL");
        editorEdit.apply();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveAdCloseUserPref() {
        SharedPreferences.Editor editorEdit = getActivity().getSharedPreferences(getSharedPrefName(), 0).edit();
        editorEdit.putBoolean("HIDE_AD_UPSELL", true);
        editorEdit.apply();
    }

    private String getSharedPrefName() {
        return "com.amazon.mp3_" + getClass().getSimpleName();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isVideoTrack() {
        return NowPlayingUtil.isVideoPlaying();
    }

    private void startLoadingAnimation() {
        this.mBeatCount = 0;
        StationLoadingAnimation stationLoadingAnimation = this.mStationLoadingAnimation;
        if (stationLoadingAnimation != null) {
            stationLoadingAnimation.reset();
            this.mStationLoadingAnimation.startHeartBeat(getContext());
        }
    }

    private void registerReceivers() {
        IntentFilter intentFilter = new IntentFilter("com.amazon.mp3.library.activity.nowplaying.finish");
        intentFilter.addAction("com.amazon.mp3.library.activity.nowplaying.clear_and_finish");
        registerReceiver(this.mFinishBroadcastReceiver, intentFilter);
        if (getContext() != null) {
            LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(getContext());
            localBroadcastManager.registerReceiver(this.mIoTSkipCountMessageReceiver, new IntentFilter("IOT_SKIP_LIMIT_BROADCAST"));
            localBroadcastManager.registerReceiver(this.mLikesTrackReceiver, new IntentFilter("LIKE_TRACK_BROADCAST"));
        }
        if (isStage()) {
            registerReceiver(this.mPlaybackErrorReceiver, PLAYBACK_ERROR_FILTER);
        }
    }

    private void unregisterReceivers() {
        unregisterReceiver(this.mFinishBroadcastReceiver);
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(this.mLikesTrackReceiver);
        if (isStage()) {
            try {
                unregisterReceiver(this.mPlaybackErrorReceiver);
            } catch (Exception e) {
                Log.error(TAG, "Error while unregistering NowPlaying finished receiver", e);
            }
        }
    }

    private void updateAll(final MediaItem item) {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        updatePlayer(item, this.mMediaItem);
        String str = TAG;
        Log.debug(str, "updateAll: %s time=%d", "updatePlayer", Long.valueOf(SystemClock.elapsedRealtime() - jElapsedRealtime));
        long jElapsedRealtime2 = SystemClock.elapsedRealtime();
        updateAlbumArt(item);
        Log.debug(str, "updateAll: %s time=%d", "updateAlbumArt", Long.valueOf(SystemClock.elapsedRealtime() - jElapsedRealtime2));
        long jElapsedRealtime3 = SystemClock.elapsedRealtime();
        updateMetaData(item);
        updateAudioQualityBadge();
        Log.debug(str, "updateAll: %s time=%d", "updateMetaData", Long.valueOf(SystemClock.elapsedRealtime() - jElapsedRealtime3));
        long jElapsedRealtime4 = SystemClock.elapsedRealtime();
        updateSeekBar();
        Log.debug(str, "updateAll: %s time=%d", "updateSeekBar", Long.valueOf(SystemClock.elapsedRealtime() - jElapsedRealtime4));
        long jElapsedRealtime5 = SystemClock.elapsedRealtime();
        updateProgressBar();
        Log.debug(str, "updateAll: %s time=%d", "updateProgressBar", Long.valueOf(SystemClock.elapsedRealtime() - jElapsedRealtime5));
        updateNowPlayingPlayer(false);
        if (item != null) {
            showContentUnavailableDialogIfNeeded();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateNowPlayingPlayer(final boolean forceUpdate) {
        PersistentPlayer.PlayerType playerType;
        PersistentPlayerFragment persistentPlayerFragment;
        boolean z = false;
        if (AutoPlaySFAUtil.shouldNotSeeSFADialogWhileSFAPlaying()) {
            this.mBauhausNowPlayingDialogView.setVisibility(8);
            this.isNonBlockingDialogueSFA = false;
        }
        boolean zCanRateMediaItems = this.mPlaybackController.canRateMediaItems();
        PlaybackProvider playbackProvider = Playback.getInstance().getPlaybackProvider();
        if ((playbackProvider instanceof SequencerPlaybackProvider) && (((SequencerPlaybackProvider) playbackProvider).getSequencer() instanceof FreeTierSequencer)) {
            z = true;
        }
        if (z) {
            playerType = PersistentPlayer.PlayerType.CLOUDQUEUE;
        } else if (zCanRateMediaItems) {
            playerType = PersistentPlayer.PlayerType.PRIME_STATIONS;
        } else if (NowPlayingUtil.isAlexaPlaying()) {
            playerType = PersistentPlayer.PlayerType.ALEXA;
        } else if (isVideoTrack()) {
            playerType = PersistentPlayer.PlayerType.VIDEO;
        } else {
            playerType = PersistentPlayer.PlayerType.NOW_PLAYING;
        }
        if (forceUpdate || (persistentPlayerFragment = this.mPlayerFragment) == null || !playerType.equals(persistentPlayerFragment.getPersistentPlayerType())) {
            if ((getActivity() == null || !VideoUtil.INSTANCE.isInPictureInPictureMode(getActivity())) && !isStage()) {
                PersistentPlayerFragment persistentPlayerFragment2 = new PersistentPlayerFragment();
                this.mPlayerFragment = persistentPlayerFragment2;
                persistentPlayerFragment2.setPersistentPlayerType(playerType);
                this.mPlayerFragment.setLikeButtonOnClickListener(this.mLikeButtonOnClickListener);
                if (getXrayManager() != null) {
                    getXrayManager().setPersistentPlayerFragment(this.mPlayerFragment);
                }
                if (zCanRateMediaItems) {
                    this.mPlayerFragment.addPlayerListener(this.mPlayerListener);
                }
                View view = this.mPersistentPlayerContainer;
                if (view instanceof ViewGroup) {
                    ((ViewGroup) view).removeAllViews();
                }
                FragmentManager childFragmentManager = getChildFragmentManager();
                FragmentTransaction fragmentTransactionBeginTransaction = childFragmentManager.beginTransaction();
                fragmentTransactionBeginTransaction.replace(R.id.NowPlayingPersistentPlayerContainer, this.mPlayerFragment, PersistentPlayerFragment.getFragmentTag());
                fragmentTransactionBeginTransaction.commitAllowingStateLoss();
                childFragmentManager.executePendingTransactions();
            }
        }
    }

    private class AlbumArtJob extends Job {
        private final MediaItem mMediaItem;
        private boolean mShouldCancelJob;

        public AlbumArtJob(final MediaItem item, final int artWidth) {
            this.mMediaItem = item;
            FragmentActivity activity = NowPlayingFragment.this.getActivity();
            if (NowPlayingFragment.this.mUrlAlbumArtImageCache == null && activity != null) {
                NowPlayingFragment.this.mUrlAlbumArtImageCache = new ImageCache(activity, artWidth, artWidth, Bitmap.Config.RGB_565);
            }
            NowPlayingFragment.this.updateAdVisibility(item);
            ImageView albumArtImageView = NowPlayingFragment.this.getAlbumArtImageView();
            if (albumArtImageView != null && (item instanceof AdMediaItem) && ScreenUtil.isPortrait()) {
                albumArtImageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            }
        }

        @Override // com.amazon.mp3.service.job.Job
        public void cancel() {
            this.mShouldCancelJob = true;
        }

        @Override // com.amazon.mp3.service.job.Job
        public int run() throws Exception {
            if (this.mShouldCancelJob || this.mMediaItem == null) {
                return 3;
            }
            MediaItem currentMediaItem = NowPlayingFragment.this.mPlaybackController.getCurrentMediaItem();
            if (currentMediaItem instanceof AlexaMediaItem) {
                return loadArtworkForAlbumASIN();
            }
            if ((currentMediaItem instanceof AdMediaItem) || MediaItemHelper.isInterludeMediaItem(currentMediaItem)) {
                return loadAdMediaItemArt(currentMediaItem);
            }
            if (!(currentMediaItem instanceof StormTrackMediaItem) && !(currentMediaItem instanceof CloudQueueLibraryTrackMediaItem)) {
                if (NowPlayingFragment.this.mPlaybackController.canRateCurrentItem()) {
                    return loadArtworkForPrimeBrowseMediaItem();
                }
                if (MediaItemHelper.getAlbumId(this.mMediaItem) != 3 && MediaItemHelper.getAlbumId(this.mMediaItem) != LibraryMediaItem.NO_ALBUM_ID) {
                    return loadArtworkForLibraryMediaItem();
                }
                if (currentMediaItem instanceof LibraryMediaItem) {
                    if (TrackType.CATALOG_PLAYLIST_TRACK.equals(((LibraryMediaItem) currentMediaItem).getTrackType())) {
                        return loadArtworkForPrimePlaylistMediaItem();
                    }
                    return loadArtworkForPrimeBrowseMediaItem();
                }
                return loadArtworkForPrimeBrowseMediaItem();
            }
            return loadArtworkForCloudQueueItem(currentMediaItem);
        }

        private int loadAdMediaItemArt(final MediaItem mediaItem) {
            NowPlayingFragment.this.mHandler.post(new Runnable() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment.AlbumArtJob.1
                @Override // java.lang.Runnable
                public void run() {
                    int albumArtWidth = NowPlayingFragment.this.getAlbumArtWidth();
                    int albumArtHeight = NowPlayingFragment.this.getAlbumArtHeight();
                    if (albumArtWidth > 0 || albumArtHeight > 0) {
                        Picasso.get().load(mediaItem.getImageUri(albumArtWidth, albumArtWidth, MediaItem.ImageType.ALBUM)).resize(albumArtWidth, albumArtHeight).centerCrop().into(NowPlayingFragment.this.albumArtLoadTarget);
                    } else {
                        Log.debug(NowPlayingFragment.TAG, "Invalid Artwork dimension; width=%d height=%d", Integer.valueOf(albumArtWidth), Integer.valueOf(albumArtHeight));
                    }
                }
            });
            return 1;
        }

        private int loadArtworkForAlbumASIN() {
            MediaCollectionId id;
            MediaItem currentMediaItem = NowPlayingFragment.this.mPlaybackController.getCurrentMediaItem();
            if (currentMediaItem == null || MediaItemExtensions.isVideo(currentMediaItem)) {
                return 3;
            }
            MediaCollectionType mediaCollectionType = MediaCollectionType.ALBUM;
            MediaCollectionId.Type type = MediaCollectionId.Type.ASIN;
            MediaCollectionInfo mediaCollectionInfo = currentMediaItem.getMediaCollectionInfo(mediaCollectionType, type);
            if (mediaCollectionInfo == null || (id = mediaCollectionInfo.getId(type)) == null) {
                return 3;
            }
            String id2 = id.getId();
            if (TextUtils.isEmpty(id2)) {
                return 3;
            }
            String artworkUrlForAsin = new CTAPrimeCache(NowPlayingFragment.this.getActivity()).getArtworkUrlForAsin(id2);
            NowPlayingFragment nowPlayingFragment = NowPlayingFragment.this;
            nowPlayingFragment.mAlbumArtDrawable = nowPlayingFragment.mUrlAlbumArtImageCache.cacheOnCurrentThread(artworkUrlForAsin);
            if (NowPlayingFragment.this.mAlbumArtDrawable == null) {
                return 3;
            }
            NowPlayingFragment.this.artLoadSubject.onNext((BitmapDrawable) NowPlayingFragment.this.mAlbumArtDrawable);
            return 1;
        }

        private int loadArtworkForLibraryMediaItem() {
            MediaCollectionId id;
            MediaItem currentMediaItem = NowPlayingFragment.this.mPlaybackController.getCurrentMediaItem();
            long albumId = MediaItemHelper.getAlbumId(currentMediaItem);
            if (albumId != 3 && albumId != -1) {
                if (currentMediaItem != null) {
                    if (TextUtils.isEmpty(MediaItem.getMediaItemIdString(currentMediaItem, MediaItemId.Type.LUID, null))) {
                        CTAPrimeCache cTAPrimeCache = new CTAPrimeCache(NowPlayingFragment.this.getActivity());
                        MediaCollectionType mediaCollectionType = MediaCollectionType.ALBUM;
                        MediaCollectionId.Type type = MediaCollectionId.Type.ASIN;
                        MediaCollectionInfo mediaCollectionInfo = currentMediaItem.getMediaCollectionInfo(mediaCollectionType, type);
                        if (mediaCollectionInfo != null && (id = mediaCollectionInfo.getId(type)) != null) {
                            String id2 = id.getId();
                            if (!TextUtils.isEmpty(id2)) {
                                String artworkUrlForAsin = cTAPrimeCache.getArtworkUrlForAsin(id2);
                                NowPlayingFragment nowPlayingFragment = NowPlayingFragment.this;
                                nowPlayingFragment.mAlbumArtDrawable = nowPlayingFragment.mUrlAlbumArtImageCache.cacheOnCurrentThread(artworkUrlForAsin);
                            }
                        }
                    } else {
                        loadArtworkFromCache(Long.toString(albumId));
                    }
                }
                if (NowPlayingFragment.this.mAlbumArt != null) {
                    NowPlayingFragment.this.artLoadSubject.onNext((BitmapDrawable) NowPlayingFragment.this.mAlbumArtDrawable);
                    return 1;
                }
            }
            return 3;
        }

        private int loadArtworkForCloudQueueItem(final MediaItem mediaItem) {
            if (ConnectivityUtil.hasAnyInternetConnection(NowPlayingFragment.this.getActivity())) {
                int albumArtWidth = NowPlayingFragment.this.getAlbumArtWidth();
                Uri uriCreateImageUri = mediaItem.createImageUri(albumArtWidth, albumArtWidth, new MediaItem.ImageType[0]);
                if (TextUtils.isEmpty(uriCreateImageUri != null ? uriCreateImageUri.toString() : "")) {
                    String albumAsin = MediaItemHelper.getAlbumAsin(mediaItem);
                    if (!TextUtils.isEmpty(albumAsin)) {
                        String artworkUrlForAsin = new CTAPrimeCache(NowPlayingFragment.this.getActivity()).getArtworkUrlForAsin(albumAsin);
                        if (!TextUtils.isEmpty(artworkUrlForAsin)) {
                            NowPlayingFragment nowPlayingFragment = NowPlayingFragment.this;
                            nowPlayingFragment.mAlbumArtDrawable = nowPlayingFragment.mUrlAlbumArtImageCache.cacheOnCurrentThread(artworkUrlForAsin);
                            NowPlayingFragment.this.artLoadSubject.onNext((BitmapDrawable) NowPlayingFragment.this.mAlbumArtDrawable);
                            return 1;
                        }
                        return loadArtworkForPrimePlaylistMediaItem();
                    }
                }
                CloudQueueSequencer cloudQueueSequencer = CloudQueueSequencer.INSTANCE.getCloudQueueSequencer();
                if (MediaProvider.PrimePlaylists.isPrimePlaylist(cloudQueueSequencer != null ? cloudQueueSequencer.getContentUri() : null)) {
                    return loadArtworkForPrimePlaylistMediaItem();
                }
                return loadArtworkForPrimeBrowseMediaItem();
            }
            return loadArtworkForLibraryMediaItem();
        }

        private void loadArtworkFromCache(String albumId) {
            int albumArtWidth = NowPlayingFragment.this.getAlbumArtWidth();
            if (albumArtWidth > 0) {
                NowPlayingFragment nowPlayingFragment = NowPlayingFragment.this;
                nowPlayingFragment.mAlbumArtDrawable = nowPlayingFragment.mArtCache.getOnCurrentThread(ImageLoaderFactory.ItemType.ALBUM, albumArtWidth, albumId, true);
                NowPlayingFragment.this.artLoadSubject.onNext((BitmapDrawable) NowPlayingFragment.this.mAlbumArtDrawable);
            }
        }

        private int loadArtworkForPrimePlaylistMediaItem() {
            MediaItem currentMediaItem = NowPlayingFragment.this.mPlaybackController.getCurrentMediaItem();
            if (currentMediaItem != null && !MediaItemExtensions.isVideo(currentMediaItem)) {
                MediaItemId.Type type = MediaItemId.Type.ASIN;
                if (currentMediaItem.getMediaItemId(type) != null) {
                    String source = MediaItemHelper.getSource(currentMediaItem);
                    String id = currentMediaItem.getMediaItemId(type).getId();
                    int albumArtWidth = NowPlayingFragment.this.getAlbumArtWidth();
                    if (id != null && albumArtWidth > 0) {
                        NowPlayingFragment nowPlayingFragment = NowPlayingFragment.this;
                        nowPlayingFragment.mAlbumArtDrawable = nowPlayingFragment.mArtCache.getOnCurrentThread(ImageLoaderFactory.ItemType.PRIME_PLAYLIST_TRACK, source, albumArtWidth, Long.toString(id.hashCode()), true);
                        NowPlayingFragment.this.artLoadSubject.onNext((BitmapDrawable) NowPlayingFragment.this.mAlbumArtDrawable);
                        return 1;
                    }
                }
            }
            return 3;
        }

        private int loadArtworkForPrimeBrowseMediaItem() {
            final int albumArtWidth = NowPlayingFragment.this.getAlbumArtWidth();
            MediaItem mediaItem = this.mMediaItem;
            if (mediaItem == null || MediaItemExtensions.isVideo(mediaItem)) {
                return 3;
            }
            Uri uriCreateImageUri = this.mMediaItem.createImageUri(albumArtWidth, albumArtWidth, new MediaItem.ImageType[0]);
            final String string = uriCreateImageUri != null ? uriCreateImageUri.toString() : "";
            Log.verbose(NowPlayingFragment.TAG, "Loading artwork from URL");
            Log.debugBuildOnly(NowPlayingFragment.TAG, "%s", string);
            if (NowPlayingFragment.this.mShowBlockingUpsellOnRotation) {
                NowPlayingFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment.AlbumArtJob.2
                    @Override // java.lang.Runnable
                    public void run() {
                        NowPlayingFragment.this.displayBlockingUpsell();
                    }
                });
            }
            if (albumArtWidth > 0) {
                NowPlayingFragment.this.mHandler.post(new Runnable() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment.AlbumArtJob.3
                    @Override // java.lang.Runnable
                    public void run() {
                        int albumArtWidth2 = NowPlayingFragment.this.getAlbumArtWidth();
                        if (albumArtWidth2 <= 0) {
                            return;
                        }
                        NowPlayingFragment.this.mAlbumArtDrawable = null;
                        if (string.isEmpty()) {
                            NowPlayingFragment.this.mUrl = null;
                        } else if (AlbumArtJob.this.mMediaItem.getMediaItemId(MediaItemId.Type.OWNED) == null) {
                            NowPlayingFragment.this.mUrl = ImageUrlModifier.with(string).scaleToLongest(albumArtWidth).toUrl();
                        } else {
                            NowPlayingFragment.this.mUrl = ImageUrlModifier.with(string).toUrl();
                        }
                        if (!NowPlayingFragment.this.mPlaybackController.canRateMediaItems() || CastingUtil.isCastingToAlexa()) {
                            Picasso.get().load(NowPlayingFragment.this.mUrl).noFade().resize(albumArtWidth2, albumArtWidth2).placeholder(R.drawable.placeholder_large).into(NowPlayingFragment.this.albumArtLoadTarget);
                            return;
                        }
                        if (NowPlayingFragment.this.mWasLastTrackThumbedDown) {
                            Picasso.get().load(NowPlayingFragment.this.mUrl).noFade().resize(albumArtWidth2, albumArtWidth2).placeholder(R.drawable.station_placeholder_detail).into(NowPlayingFragment.this.albumArtLoadTarget);
                            NowPlayingFragment.this.mWasLastTrackThumbedDown = false;
                        } else {
                            if (NowPlayingFragment.this.mShowLoading) {
                                NowPlayingFragment.this.mSwapArtwork.getImage();
                            } else {
                                NowPlayingFragment.this.getAlbumArtImageView();
                            }
                            Picasso.get().load(NowPlayingFragment.this.mUrl).noFade().resize(albumArtWidth2, albumArtWidth2).placeholder(R.drawable.station_placeholder_detail).into(NowPlayingFragment.this.albumArtLoadTarget);
                        }
                    }
                });
            }
            return 1;
        }
    }

    @Override // com.amazon.mp3.service.job.JobSessionFragment
    protected void onJobFinished(final long jobId, final Job job, final int resultCode, final Bundle resultBundle) {
        if (jobId == this.mAlbumArtJobId && resultCode == 1) {
            this.mAlbumArtJobId = -1L;
            this.mCurrentAlbumArtJob = null;
        } else if (jobId == this.mGetTrackPrimeStatusJobId) {
            this.mGetTrackPrimeStatusJobId = -1L;
        }
        super.onJobFinished(jobId, job, resultCode, resultBundle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onAlbumArtLoadFinished(Drawable drawable) {
        StageViewPresenter stageViewPresenter;
        ImageView albumArtImageView = getAlbumArtImageView();
        if (albumArtImageView != null) {
            if (ScreenUtil.isLandscape()) {
                if (isStage()) {
                    int screenWidthPixels = (int) (((double) com.amazon.ui.foundations.utils.ScreenUtil.INSTANCE.getScreenWidthPixels((WindowManager) getContext().getSystemService("window"))) * 0.23d);
                    drawable = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(BitmapUtil.convertDrawableToBitmap(drawable), screenWidthPixels, screenWidthPixels, true));
                }
                albumArtImageView.setImageDrawable(drawable);
                albumArtImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            } else {
                albumArtImageView.setImageDrawable(drawable);
            }
        }
        if ((this.mUpsellInformation != null || this.mShowBlockingUpsellOnRotation) && !this.upsellDismissed.booleanValue()) {
            getActivity().runOnUiThread(new Runnable() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment.22
                @Override // java.lang.Runnable
                public void run() {
                    NowPlayingFragment.this.displayBlockingUpsell();
                }
            });
        }
        if (this.mLandscapeBackgroundArt != null && this.mStyleSheet != null && getActivity() != null && this.mStyleSheet.getLandscapeType(getActivity()) == LandscapeType.TABLET_LANDSCAPE) {
            loadHeroImageArtForTabletLandscape();
        }
        if (!isStage() || (stageViewPresenter = this.mStageViewPresenter) == null) {
            return;
        }
        this.mAlbumArtBitmapBlurred = this.mStageViewPresenter.getBlurBitmap(this.mStageViewPresenter.getBlurBitmap(stageViewPresenter.getBitmapFromDrawable(drawable).copy(Bitmap.Config.ARGB_8888, true), 24.0f), 24.0f);
        this.mStageViewPresenter.getBlurAlbumArtSubject().onNext(this.mAlbumArtBitmapBlurred);
    }

    private boolean shouldDisplayOverflowMenuWithShoveler() {
        InterfaceProvider interfaceProvider;
        WeblabTreatment weblabTreatmentEvaluateFeatureAndGetTreatment;
        if ((!isStage() && isVideoTrack()) || (interfaceProvider = Providers.INSTANCE.get(ExperimentFeatureGateProvider.class)) == null) {
            return false;
        }
        ExperimentFeatureGateProvider experimentFeatureGateProvider = (ExperimentFeatureGateProvider) interfaceProvider;
        Feature feature = Feature.shoveler_add_to_playlist;
        if (PlatformUtil.isFireOS()) {
            weblabTreatmentEvaluateFeatureAndGetTreatment = experimentFeatureGateProvider.evaluateFeatureAndGetTreatment(feature, Weblab.DM_CURATE_NOW_PLAYING_IMPROVEMENTS_FIRE_TABLET_AB.toString(), Weblab.DM_CURATE_NOW_PLAYING_IMPROVEMENTS_FIRE_TABLET_AA.toString());
        } else {
            weblabTreatmentEvaluateFeatureAndGetTreatment = experimentFeatureGateProvider.evaluateFeatureAndGetTreatment(feature, Weblab.DM_CURATE_NOW_PLAYING_IMPROVEMENTS_AB.toString(), Weblab.DM_CURATE_NOW_PLAYING_IMPROVEMENTS_AA.toString());
        }
        return WeblabTreatment.T1 == weblabTreatmentEvaluateFeatureAndGetTreatment;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean shouldDisplayContextMenu() {
        MediaItem mediaItem = this.mMediaItem;
        return (mediaItem == null || (mediaItem instanceof AdMediaItem) || MediaItemHelper.isInterludeMediaItem(mediaItem)) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateOptionsMenuVisibility(@NonNull final MediaItem item) {
        if (getActivity() instanceof NowPlayingFragmentActivity) {
            ((NowPlayingFragmentActivity) getActivity()).showShareButton(Boolean.valueOf((isStage() || MediaItemHelper.isInterludeMediaItem(item)) ? false : true));
        }
        if ((item instanceof AdMediaItem) || MediaItemHelper.isInterludeMediaItem(item)) {
            setHasOptionsMenu(false);
        } else {
            setHasOptionsMenu(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateAdVisibility(@NonNull final MediaItem item) {
        if (FMPMFeatureGating.PROJECT_PHOENIX_DISABLE_UPSELL_CTA.isDisabled()) {
            if (isStage()) {
                setUpAdsUpsellBauhausRibbon();
            } else {
                setAdUpsellInformation();
            }
            boolean z = getActivity().getSharedPreferences(getSharedPrefName(), 0).getBoolean("HIDE_AD_UPSELL", false);
            View view = isStage() ? this.mBauhausRibbon : this.mAdsUpsellBanner;
            if (!(item instanceof AdMediaItem) || view == null) {
                if (view != null) {
                    view.setVisibility(8);
                    return;
                }
                return;
            }
            PlaylistTooltipExperience playlistTooltipExperience = this.mPlaylistTooltipExperience;
            if (playlistTooltipExperience != null) {
                playlistTooltipExperience.dismissPlaylistTooltip(false);
            }
            if (z) {
                view.setVisibility(8);
            } else {
                MetricsLogger.sendUpsellContentViewEvent(ContainerType.AD_UPSELL_DIALOG, ContentName.BAUHAUS_HAWKFIRE_ADS_UPSELL);
                view.setVisibility(0);
            }
            if (this.mBauhausNowPlayingDialogView != null) {
                displayDialogView(false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateAlbumArt(final MediaItem item) {
        if (item == null) {
            return;
        }
        if (VideoUtil.INSTANCE.isInPictureInPictureMode(getActivity())) {
            this.mLandscapeBackgroundArt.setScaleType(ImageView.ScaleType.CENTER);
            this.mLandscapeBackgroundArt.setImageResource(R.drawable.ic_other_song);
            return;
        }
        if (skipAlbumArtUpdate(item) && !isStage()) {
            Log.verbose(TAG, "Skipping album art update (Track: %s)", item.getName());
            return;
        }
        AlbumArtJob albumArtJob = this.mCurrentAlbumArtJob;
        if (albumArtJob != null) {
            albumArtJob.cancel();
        }
        if (this.mAlbumArt != null) {
            if (this.mLandscapeBackgroundArt != null) {
                if (ScreenUtil.isLandscape()) {
                    this.mLandscapeBackgroundArt.setImageDrawable(this.mAlbumArtDrawable);
                } else {
                    this.mLandscapeBackgroundArt.setImageDrawable(null);
                }
            }
            Picasso.get().cancelRequest(this.mAlbumArt.getImage());
            if (!isVideoTrack()) {
                this.mAlbumArt.post(new Runnable() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment.23
                    @Override // java.lang.Runnable
                    public void run() {
                        int albumArtWidth = NowPlayingFragment.this.getAlbumArtWidth();
                        if (albumArtWidth > 0) {
                            NowPlayingFragment nowPlayingFragment = NowPlayingFragment.this;
                            nowPlayingFragment.mCurrentAlbumArtJob = nowPlayingFragment.new AlbumArtJob(item, albumArtWidth);
                            NowPlayingFragment nowPlayingFragment2 = NowPlayingFragment.this;
                            nowPlayingFragment2.mAlbumArtJobId = nowPlayingFragment2.addJob(nowPlayingFragment2.mCurrentAlbumArtJob);
                        }
                    }
                });
            }
        } else if (this.mLandscapeBackgroundArt != null) {
            Picasso.get().cancelRequest(this.mLandscapeBackgroundArt);
            if (!isVideoTrack()) {
                this.mLandscapeBackgroundArt.post(new Runnable() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment.24
                    @Override // java.lang.Runnable
                    public void run() {
                        int albumArtWidth = NowPlayingFragment.this.getAlbumArtWidth();
                        if (albumArtWidth > 0) {
                            NowPlayingFragment nowPlayingFragment = NowPlayingFragment.this;
                            nowPlayingFragment.mCurrentAlbumArtJob = nowPlayingFragment.new AlbumArtJob(item, albumArtWidth);
                            NowPlayingFragment nowPlayingFragment2 = NowPlayingFragment.this;
                            nowPlayingFragment2.mAlbumArtJobId = nowPlayingFragment2.addJob(nowPlayingFragment2.mCurrentAlbumArtJob);
                        }
                    }
                });
            }
        }
        MediaCollectionInfo mediaCollectionInfo = item.getMediaCollectionInfo(MediaCollectionType.ALBUM, MediaCollectionId.Type.ASIN);
        this.mCachedAlbumAsin = MediaItemHelper.isMCIDNonnull(mediaCollectionInfo) ? mediaCollectionInfo.getId().getId() : "";
    }

    private boolean skipAlbumArtUpdate(MediaItem item) {
        String str;
        if (this.mAlbumArtDrawable == null) {
            return false;
        }
        MediaCollectionInfo mediaCollectionInfo = item.getMediaCollectionInfo(MediaCollectionType.ALBUM, MediaCollectionId.Type.ASIN);
        return MediaItemHelper.isMCIDNonnull(mediaCollectionInfo) && (str = this.mCachedAlbumAsin) != null && str.equals(mediaCollectionInfo.getId().getId());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getAlbumArtWidth() {
        View view;
        FragmentActivity activity = getActivity();
        int width = 0;
        if (!ActivityKt.isAvailable(activity)) {
            Log.error(TAG, "Activity is null or unavailable. Can't display albumArt.");
            return 0;
        }
        if (ScreenUtil.isPortrait()) {
            width = ArtworkSizeUtil.getFullWidthArtworkSize(activity);
        } else {
            StyleSheet styleSheet = this.mStyleSheet;
            if (styleSheet != null && styleSheet.getLandscapeType(activity) == LandscapeType.TABLET_LANDSCAPE && (view = this.mArtworkSectionLayout) != null) {
                width = view.getWidth();
            } else {
                FrameLayout frameLayout = this.mLandscapeBackgroundArtFrame;
                if (frameLayout != null) {
                    width = frameLayout.getWidth();
                }
            }
        }
        return width == 0 ? ScreenUtil.getDeviceWidth() : width;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getAlbumArtHeight() {
        View view;
        FragmentActivity activity = getActivity();
        int height = 0;
        if (!ActivityKt.isAvailable(activity)) {
            Log.error(TAG, "Activity is null. Can't display albumArt.");
            return 0;
        }
        if (ScreenUtil.isPortrait()) {
            height = ArtworkSizeUtil.getFullWidthArtworkSize(activity);
        } else {
            StyleSheet styleSheet = this.mStyleSheet;
            if (styleSheet != null && styleSheet.getLandscapeType(activity) == LandscapeType.TABLET_LANDSCAPE && (view = this.mArtworkSectionLayout) != null) {
                height = view.getHeight();
            } else {
                FrameLayout frameLayout = this.mLandscapeBackgroundArtFrame;
                if (frameLayout != null) {
                    height = frameLayout.getHeight();
                }
            }
        }
        return height == 0 ? ScreenUtil.getDeviceHeight() : height;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateMetaData(final MediaItem item) {
        String albumName;
        String albumName2;
        if (item == null) {
            return;
        }
        this.mMediaItem = item;
        if (this.mLyricsViewContainer != null && !this.mShowLoading) {
            showLyrics(item);
        }
        this.mCachedTrackTitle = item.getName();
        this.mCachedArtistName = item.getArtistName();
        if (MediaItemHelper.isInterludeMediaItem(item) && TextUtils.isEmpty(this.mCachedArtistName)) {
            if (item.getCollections().get(0) == null || item.getCollections().get(0).getName().isEmpty()) {
                albumName2 = item.getAlbumName();
            } else {
                albumName2 = item.getCollections().get(0).getSubtitle();
            }
            this.mCachedArtistName = albumName2;
        }
        if (item.getCollections().get(0) == null || item.getCollections().get(0).getName().isEmpty()) {
            albumName = item.getAlbumName();
        } else {
            albumName = item.getCollections().get(0).getName();
        }
        this.mCachedMediaSourceName = albumName;
        if (!this.mShowLoading && !VideoUtil.INSTANCE.isInPictureInPictureMode(getActivity())) {
            updateMetadataText();
        }
        StringUtil.getCachedFormattedTime(this.mPlaybackController.getDurationMillis());
        if (!this.mPlaybackController.canRateMediaItems() && item.getMediaAccessInfo().getContentAccessTypes().contains(ContentAccessType.PRIME)) {
            MediaItemId.Type type = MediaItemId.Type.ASIN;
            if (item.getMediaItemId(type) != null && item.getMediaItemId(type).getId() != null) {
                this.mGetTrackPrimeStatusJobId = addJob(new GetTrackPrimeStatusJob(CirrusDatabase.getWritableDatabase(getActivity()), item.getMediaItemId(type).getId()));
            }
        }
        updateLikeButton();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finishLoading() {
        StageViewPresenter stageViewPresenter;
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        setHasOptionsMenu(getHasOptionsMenu());
        PersistentPlayerFragment persistentPlayerFragment = this.mPlayerFragment;
        if (persistentPlayerFragment != null && persistentPlayerFragment.getPersistentPlayer() != null) {
            this.mPlayerFragment.getPersistentPlayer().setControlsEnabled(true);
        }
        this.mMetadataView.displayLoadingView(false);
        updateMetadataText();
        updateAudioQualityBadge();
        this.mStationLoadingAnimation.finish(activity);
        if (CastingUtil.isCastingToAlexa()) {
            ImageViewAnimations.fade(this.mAlbumArt.getImage(), 1.0f);
        } else {
            Log.info(TAG, "initiating crossfade for mAlbumArt with placeholder mSwapArtwork");
        }
        if (!isStage() || (stageViewPresenter = this.mStageViewPresenter) == null || !stageViewPresenter.isInFullScreenOverlayMode()) {
            updateSeekBarVisibility(this.mPlaybackController.getCurrentMediaItem());
        }
        UpsellInformation upsellInformation = this.mUpsellInformation;
        if (upsellInformation != null) {
            UpsellUtil.emitEligibilityCheckRequestedMetric(upsellInformation);
            displayBlockingUpsell();
            MetricsLogger.sendUpsellContentViewEvent(ContainerType.NON_BLOCKING_UPSELL_DIALOG, ContentName.BAUHAUS_HAWKFIRE_NONBLOCKING_UPSELL);
        }
        MediaItem currentMediaItem = this.mPlaybackController.getCurrentMediaItem();
        if (currentMediaItem != null) {
            showLyrics(currentMediaItem);
        }
        this.mShowLoading = false;
        NowPlayingPersistentPlayer nowPlayingPersistentPlayer = this.mPersistentPlayer;
        if (nowPlayingPersistentPlayer != null) {
            nowPlayingPersistentPlayer.setControlsEnabled(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void displayBlockingUpsell() {
        if (this.mBauhausNowPlayingDialogView != null) {
            displayDialogView(true);
            this.mRunnableDebouncer.post(this.mUpsellTimeOutProgressRunnable);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateMetadataText() {
        String str;
        String str2;
        String str3 = this.mCachedTrackTitle;
        if (str3 == null) {
            return;
        }
        this.mTitleString = str3;
        DefaultStringType defaultStringType = DefaultStringType.ARTIST;
        if (defaultStringType.isPlatformOrCirrusDefault(this.mCachedArtistName)) {
            str = defaultStringType.getDefault();
        } else {
            str = this.mCachedArtistName;
        }
        this.mSubtitleString = str;
        DefaultStringType defaultStringType2 = DefaultStringType.ALBUM;
        if (defaultStringType2.isPlatformOrCirrusDefault(this.mCachedMediaSourceName)) {
            str2 = defaultStringType2.getDefault();
        } else {
            str2 = this.mCachedMediaSourceName;
        }
        this.mTertiaryString = str2;
        if (ChildUserUtil.INSTANCE.isChildUser(getContext())) {
            this.mTertiaryString = StringUtil.removeExplicitLabelFromAlbumOrTrackTitle(this.mTertiaryString);
        }
        updatemTertiaryString(this.mPlaybackController.getCurrentMediaItem());
        Queue currentPlayingQueueObject = CloudQueueSequencer.INSTANCE.getCurrentPlayingQueueObject();
        String title = currentPlayingQueueObject != null ? currentPlayingQueueObject.getTitle() : null;
        PlayerMetadataView playerMetadataView = this.mMetadataView;
        String str4 = this.mTitleString;
        String str5 = this.mSubtitleString;
        if (title == null) {
            title = this.mTertiaryString;
        }
        playerMetadataView.setText(str4, str5, title);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isStation() {
        SequencerPlaybackProvider sequencerPlaybackProvider = (SequencerPlaybackProvider) Playback.getInstance().getPlaybackProvider();
        if (sequencerPlaybackProvider.getSequencer() instanceof StationSequencer) {
            return true;
        }
        if ((sequencerPlaybackProvider.getSequencer() instanceof FreeTierSequencer) && ((FreeTierSequencer) sequencerPlaybackProvider.getSequencer()).isStationPlayback()) {
            return true;
        }
        return (sequencerPlaybackProvider.getSequencer() instanceof CloudQueueSequencer) && ((CloudQueueSequencer) sequencerPlaybackProvider.getSequencer()).getIsOnlineInteraction();
    }

    private String getStationKey() {
        SequencerPlaybackProvider sequencerPlaybackProvider = (SequencerPlaybackProvider) Playback.getInstance().getPlaybackProvider();
        if (!(sequencerPlaybackProvider.getSequencer() instanceof StationSequencer) || ((StationSequencer) sequencerPlaybackProvider.getSequencer()).getStationItem() == null) {
            return null;
        }
        return ((StationSequencer) sequencerPlaybackProvider.getSequencer()).getStationItem().getKey();
    }

    private String getBasedOnString() {
        String str;
        DefaultStringType defaultStringType = DefaultStringType.ALBUM;
        if (defaultStringType.isPlatformOrCirrusDefault(this.mCachedMediaSourceName)) {
            str = defaultStringType.getDefault();
        } else {
            str = this.mCachedMediaSourceName;
        }
        String string = getString(R.string.dmusic_now_playing_based_on);
        return str.contains(StringUtil.cleanFormatString(string)) ? str : String.format(string, str);
    }

    private void updatemTertiaryString(MediaItem mediaItem) {
        String str;
        DefaultStringType defaultStringType = DefaultStringType.ALBUM;
        if (defaultStringType.isPlatformOrCirrusDefault(this.mCachedMediaSourceName)) {
            str = defaultStringType.getDefault();
        } else {
            str = this.mCachedMediaSourceName;
        }
        this.mTertiaryString = str;
        if (mediaItem == null || PlayerViewConfig.PlayerViewContentType.fromMediaItem(mediaItem) != PlayerViewConfig.PlayerViewContentType.AD) {
            if ((NowPlayingUtil.isSFAStationPlaying() || NowPlayingUtil.isAMFStationPlaying()) && !NowPlayingUtil.isUserPlaylistPlaying() && !NowPlayingUtil.isGoldenPlaylistPlaying()) {
                this.mTertiaryString = getBasedOnString();
            } else if (NowPlayingUtil.isPrimeOrAMUStationPlaying()) {
                this.mTertiaryString = getBasedOnString();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateProgressBar() {
        BauhausSeekBarWidget bauhausSeekBarWidget = this.mSeekBar;
        if (bauhausSeekBarWidget == null || this.mRunnableDebouncer == null) {
            return;
        }
        bauhausSeekBarWidget.updateProgressBar();
    }

    private void updateSeekBar() {
        BauhausSeekBarWidget bauhausSeekBarWidget = this.mSeekBar;
        if (bauhausSeekBarWidget == null) {
            return;
        }
        bauhausSeekBarWidget.updateSeekBar();
    }

    private void startPlaybackIfRequested() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            Intent intent = activity.getIntent();
            boolean booleanExtra = intent.getBooleanExtra("com.amazon.mp3.library.EXTRA_START_PLAYBACK", false);
            intent.removeExtra("com.amazon.mp3.library.EXTRA_START_PLAYBACK");
            if (booleanExtra) {
                if (this.mPlaybackController.getCurrentMediaItem() == null) {
                    Log.error(TAG, "NowPlayingFragment received start playback intent on null track");
                } else {
                    if (PlayStatus.RENDERING.equals(this.mPlaybackController.getPlayStatus())) {
                        return;
                    }
                    this.mPlaybackController.play();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$22(View view) {
        if (this.badgeText == null || CastingUtil.isCastingToAlexa()) {
            return;
        }
        if (AmazonApplication.getCapabilities().isSpatialAudioEnabled(getContext())) {
            startActivity(new Intent(getContext(), (Class<?>) AudioStatsFragmentActivity.class));
        } else if (BauhausDialogBuilder.INSTANCE.isBauhausDialogEnabled()) {
            new FragmentAudioStats().show(getChildFragmentManager(), FragmentAudioStats.INSTANCE.getTAG());
        } else {
            WideDialogActivity.showActivity(getContext(), WideDialogActivityPurpose.SHOW_AUDIO_STATS_PAGE, R.style.Theme_AmazonMP3_Dialog);
        }
        MetricsHolder.getManager().handleEvent(UiClickEvent.builder(ActionType.SELECT_STREAMING_INFORMATION).withInteractionType(InteractionType.TAP).withPageType(isStage() ? PageType.NOW_PLAYING_STAGE : PageType.NOW_PLAYING).withEventTime(System.currentTimeMillis()).build());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$23(View view) {
        MediaItem mediaItem;
        FragmentActivity activity = getActivity();
        if (NowPlayingUtil.isSideLoadedTrackPlaying() || (mediaItem = this.mMediaItem) == null || (mediaItem instanceof AdMediaItem) || MediaItemHelper.isInterludeMediaItem(mediaItem)) {
            return;
        }
        if (!ConnectivityUtil.hasAnyInternetConnection()) {
            if (NowPlayingUtil.isDownloaded(this.mPlaybackController.getCurrentMediaItem())) {
                return;
            }
            showDialog(UniqueCodeUtil.nextUniqueCode(), NetworkErrorDialog.create(activity, this));
        } else {
            activity.startActivity(LibraryActivityFactory.getArtistDetailsIntent(activity, this.mMediaItem.getArtistName(), MediaItemHelper.getArtistAsin(this.mMediaItem)));
            MetricsHolder.getManager().handleEvent(UiClickEvent.builder(ActionType.SELECT_ARTIST_NPS).withInteractionType(InteractionType.TAP).withPageType(PageType.NOW_PLAYING).withEventTime(System.currentTimeMillis()).build());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$24(View view) {
        FragmentActivity activity = getActivity();
        MediaItem currentMediaItem = this.mPlaybackController.getCurrentMediaItem();
        if (currentMediaItem == null) {
            Log.error(TAG, "current media item is null, can't update like state");
            return;
        }
        currentMediaItem.setLikesProvider(new MediaItemLikesProviderImpl(getContext()));
        if (!ConnectivityUtil.hasAnyInternetConnection()) {
            if (NowPlayingUtil.isDownloaded(this.mPlaybackController.getCurrentMediaItem())) {
                return;
            }
            showDialog(UniqueCodeUtil.nextUniqueCode(), NetworkErrorDialog.create(activity, this));
            return;
        }
        boolean z = LikesServiceProxy.INSTANCE.toggleLikeStatus(currentMediaItem, this.mPlaybackController, requireContext());
        if (isStage()) {
            StageViewPresenter stageViewPresenter = this.mStageViewPresenter;
            if (stageViewPresenter != null) {
                stageViewPresenter.setLikeButtonState(z);
            }
        } else {
            this.mMetadataView.setLikeButtonState(z);
            PersistentPlayerFragment persistentPlayerFragment = this.mPlayerFragment;
            if (persistentPlayerFragment != null) {
                persistentPlayerFragment.setLikeButtonState(Boolean.valueOf(z));
            }
        }
        BauhausToastUtils.showTextToast(requireActivity(), z ? R.string.dmusic_like_action_toast : R.string.dmusic_un_like_action_toast, 0);
        MetricsHolder.getManager().handleEvent(UiClickEvent.builder(z ? ActionType.THUMBS_UP : ActionType.THUMBS_UP_UNDO).withEntityId(currentMediaItem.getMediaItemId().getId()).withInteractionType(InteractionType.TAP).withPageType(isStage() ? PageType.NOW_PLAYING_STAGE : PageType.NOW_PLAYING).withEventTime(System.currentTimeMillis()).withPageSubType(this.mPlaybackController.canRateMediaItems() ? null : "onDemand").build());
    }

    private class NowPlayingOnTouchListener implements View.OnTouchListener {
        private boolean allowSwipeToSkipLandscape;
        private boolean allowSwipeToSkipPortrait;
        private float endX;
        private float endY;
        private float startX;
        private float startY;

        public NowPlayingOnTouchListener(boolean allowSwipeToSkipPortrait, boolean allowSwipeToSkipLandscape) {
            this.allowSwipeToSkipPortrait = allowSwipeToSkipPortrait;
            this.allowSwipeToSkipLandscape = allowSwipeToSkipLandscape;
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(final View v, final MotionEvent event) {
            int actionMasked = MotionEventCompat.getActionMasked(event);
            if (actionMasked == 0) {
                this.startX = event.getX();
                this.startY = event.getY();
                return false;
            }
            if (actionMasked == 1) {
                float x = event.getX();
                this.endX = x;
                float f = x - this.startX;
                float y = event.getY();
                this.endY = y;
                float f2 = y - this.startY;
                if (Math.abs(f) > 120.0f && ((f2 <= 120.0f || Math.abs(f) >= f2) && isSwipeToSkipAllowed())) {
                    if (f > 0.0f) {
                        if (!UserSubscriptionUtil.isNightwingOnly() || NowPlayingFragment.this.mPlaybackController.getController().canSkipPrevious()) {
                            NowPlayingFragment.this.mNPSOverlayMetricsReporter.setPositionOnNextPreviousButtonClick(NowPlayingFragment.this.mPlaybackController.getPositionMillis());
                            NowPlayingFragment.this.mPlaybackController.skipPrevious();
                        } else {
                            UpsellUtil.raiseBlockingUpsell(NowPlayingFragment.this.getContext(), UpsellUtil.getAsinFromMediaItem(NowPlayingFragment.this.mPlaybackController.getCurrentMediaItem()), NowPlayingFragment.this.mPlaybackController.getCurrentMediaItem() instanceof AdMediaItem ? UpsellReason.AD_SKIP : UpsellReason.TRACK_PREVIOUS);
                        }
                    } else if (!UserSubscriptionUtil.isNightwingOnly() || NowPlayingFragment.this.mPlaybackController.getController().canSkipNext()) {
                        NowPlayingFragment.this.mNPSOverlayMetricsReporter.setPositionOnNextPreviousButtonClick(NowPlayingFragment.this.mPlaybackController.getPositionMillis());
                        NowPlayingFragment.this.mPlaybackController.skipNextForResult();
                    } else {
                        UpsellUtil.raiseBlockingUpsell(NowPlayingFragment.this.getContext(), UpsellUtil.getAsinFromMediaItem(NowPlayingFragment.this.mPlaybackController.getCurrentMediaItem()), NowPlayingFragment.this.mPlaybackController.getCurrentMediaItem() instanceof AdMediaItem ? UpsellReason.AD_SKIP : UpsellReason.TRACK_NEXT);
                    }
                    return false;
                }
                FragmentActivity activity = NowPlayingFragment.this.getActivity();
                if (f2 > 120.0f && activity != null) {
                    activity.onBackPressed();
                    return true;
                }
            }
            return false;
        }

        private boolean isSwipeToSkipAllowed() {
            return (this.allowSwipeToSkipPortrait && ScreenUtil.isPortrait()) || (this.allowSwipeToSkipLandscape && ScreenUtil.isLandscape());
        }
    }

    private void init() {
        PlayerMetadataView playerMetadataView;
        PlayerMetadataView playerMetadataView2;
        this.mViewRoot.setOnClickListener(this);
        this.mAlbumArtAndSeekBarLayout = (AlbumArtAndSeekBarLayout) this.mViewContainer.findViewById(R.id.AlbumArtAndSeekBar);
        if (ScreenUtil.isPortrait()) {
            this.mViewContainer.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment.27
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    Context context = NowPlayingFragment.this.getContext();
                    if (context != null) {
                        NowPlayingFragment.this.mViewContainer.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        int iDpToPixels = SpacerParser.INSTANCE.dpToPixels(context.getResources(), 840);
                        if (NowPlayingFragment.this.mInitialViewContainerHeight == -1) {
                            NowPlayingFragment nowPlayingFragment = NowPlayingFragment.this;
                            nowPlayingFragment.mInitialViewContainerHeight = nowPlayingFragment.mViewContainer.getHeight();
                        }
                        if (NowPlayingFragment.this.isStage() || NowPlayingFragment.this.mInitialViewContainerHeight <= iDpToPixels) {
                            return;
                        }
                        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, iDpToPixels);
                        int i = (NowPlayingFragment.this.mInitialViewContainerHeight - iDpToPixels) / 2;
                        layoutParams.topMargin = i;
                        layoutParams.bottomMargin = i;
                        NowPlayingFragment.this.mViewContainer.setLayoutParams(layoutParams);
                    }
                }
            });
            updateAlbumArtContainerHeight();
        } else {
            resetContainerHeightAndMargins();
        }
        setUpArtLoadSubscription();
        PlayerMetadataView playerMetadataView3 = (PlayerMetadataView) this.mViewRoot.findViewById(R.id.player_metadata);
        this.mMetadataView = playerMetadataView3;
        if (playerMetadataView3 != null && isStage() && isVideoTrack() && ScreenUtil.isLandscape()) {
            this.mMetadataView.setVisibility(8);
        }
        if (UserSubscriptionUtil.getUserSubscription().isKatana() && !isStage() && (playerMetadataView2 = this.mMetadataView) != null) {
            playerMetadataView2.showBadging(true);
            this.mMetadataView.setBadgeOnClickListener(this.mOnQualityButtonClickListener);
        }
        WeblabProvider weblabProvider = (WeblabProvider) Providers.INSTANCE.get(WeblabProvider.class);
        weblabProvider.getTreatment(Weblab.DM_ASTERIX_ARTIST_NAME_DETAIL_LINK_AA.toString(), true);
        if (weblabProvider.getTreatment(Weblab.DM_ASTERIX_ARTIST_NAME_DETAIL_LINK_AB.toString(), true) == WeblabTreatment.T1 && (playerMetadataView = this.mMetadataView) != null) {
            playerMetadataView.setArtistNameTextOnClickLIstener(this.mOnArtistNameClickListener);
        }
        if (!isStage()) {
            this.mLyricsViewContainer = initLyricsViewContainer((LyricsTextViewContainer) this.mViewRoot.findViewById(R.id.LyricsViewContainer));
        }
        this.mSwapArtwork = (BaseImage) this.mViewRoot.findViewById(R.id.swap_art);
        if (isStage()) {
            this.mSeekBar = (BauhausSeekBarWidget) this.mViewRoot.findViewById(R.id.stage_seekbar);
        } else {
            this.mSeekBar = (BauhausSeekBarWidget) this.mViewRoot.findViewById(R.id.PlayerCoverViewSeekBar);
        }
        this.mAlbumArt = (BaseImage) this.mViewRoot.findViewById(R.id.PlayerCoverViewAlbumArt);
        this.mLandscapeBackgroundArt = (ImageView) getActivity().findViewById(R.id.artwork_background);
        this.mToastContainer = (CoordinatorLayout) this.mViewRoot.findViewById(R.id.toast_container);
        this.mArtworkSectionLayout = this.mViewRoot.findViewById(R.id.artwork_section_layout);
        this.mBauhausNowPlayingDialogView = (LinearLayout) this.mViewRoot.findViewById(R.id.bauhausNowPlayingNonBlockingView);
        this.mMessagingDialog = (MessagingDialog) this.mViewRoot.findViewById(R.id.messagingDialog);
        View viewFindViewById = this.mViewContainer.findViewById(R.id.NowPlayingPersistentPlayerContainer);
        this.mPersistentPlayerContainer = viewFindViewById;
        if (viewFindViewById != null && !isStage() && getXrayManager() != null) {
            getXrayManager().setPersistentPlayerTouchListener(this.mPersistentPlayerContainer);
        }
        initViewStyling();
        this.mMetadataView.setLikeButtonOnClickListener(this.mLikeButtonOnClickListener);
        if (!isStage()) {
            this.mAlbumArt.setOnCreateContextMenuListener(this.mOnCreateContextMenuListener);
        }
        getActivity();
        this.mAlbumArt.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment.28
            @Override // android.view.View.OnClickListener
            public void onClick(final View v) {
                MediaItem currentMediaItem = NowPlayingFragment.this.mPlaybackController.getCurrentMediaItem();
                if (currentMediaItem instanceof AdMediaItem) {
                    AdMediaItem adMediaItem = (AdMediaItem) currentMediaItem;
                    Uri adClickUri = adMediaItem.getAdClickUri();
                    if (FreeTierPlaybackUtil.canHandleAdClick(adClickUri)) {
                        CloudQueueUIMetricsManager.get().sendAdClickingEvent(adMediaItem);
                        NowPlayingFragment.this.startActivity(new Intent("android.intent.action.VIEW", adClickUri));
                        return;
                    }
                    return;
                }
                if (!NowPlayingFragment.this.isStage() || NowPlayingFragment.this.mStageViewPresenter == null) {
                    return;
                }
                NowPlayingFragment.this.mStageViewPresenter.handleTouchEvent();
            }
        });
        this.mArtCache = CacheManager.getInstance();
        boolean z = this.mPlaybackController.canRateMediaItems() || this.mShowLoading;
        initStyleSheet();
        if (z) {
            if (this.mShowLoading) {
                LyricsTextViewContainer lyricsTextViewContainer = this.mLyricsViewContainer;
                if (lyricsTextViewContainer != null) {
                    lyricsTextViewContainer.setVisibility(4);
                }
                this.mSeekBar.setVisibility(4);
            }
            showLoadingMetadata();
        }
        MediaItem mediaItem = this.mMediaItem;
        if (mediaItem != null) {
            updateAll(mediaItem);
        }
        if (this.mUpsellInformation != null || this.mShowBlockingUpsellOnRotation) {
            setNonBlockingUpsellInformation();
        }
        showActionBar();
        LyricsTextViewContainer lyricsTextViewContainer2 = this.mLyricsViewContainer;
        if (lyricsTextViewContainer2 != null) {
            lyricsTextViewContainer2.setFrugalLyricsEnabled(true);
            this.mLyricsViewContainer.setLyricsWindowInteractionListener(this);
            this.mLyricsViewContainer.setFrugalLyricsListener(this);
        }
        this.isNonBlockingDialogueSFA = false;
    }

    private void resetContainerHeightAndMargins() {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.mViewContainer.getLayoutParams();
        layoutParams.topMargin = 0;
        layoutParams.bottomMargin = 0;
        layoutParams.height = -1;
        this.mViewContainer.setLayoutParams(layoutParams);
    }

    private void updateAlbumArtContainerHeight() {
        this.mViewRoot.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment.29
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                if (!NowPlayingFragment.this.isAdded() || NowPlayingFragment.this.mViewRoot == null) {
                    return;
                }
                NowPlayingFragment.this.mViewRoot.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                NowPlayingFragment.this.updateAlbumArtAndSeekBarContainerHeight();
            }
        });
    }

    private void showLoadingMetadata() {
        MediaCollectionInfo mediaCollectionInfo = this.mPlaybackController.getMediaCollectionInfo();
        if (mediaCollectionInfo != null) {
            if (mediaCollectionInfo.getType() == MediaCollectionType.GOLDEN_PLAYLIST) {
                this.mMetadataView.setPrimaryText(getResources().getString(R.string.dmusic_loading_playlist));
            } else {
                this.mMetadataView.setText(getResources().getString(R.string.dmusic_loading_station, ""), mediaCollectionInfo.getName(), getResources().getString(R.string.dmusic_library_item_name_station));
            }
        } else {
            this.mMetadataView.setPrimaryText(getResources().getString(R.string.dmusic_loading_station, ""));
        }
        MediaItem mediaItem = this.mMediaItem;
        if (mediaItem != null) {
            updateAll(mediaItem);
        }
        if (this.mUpsellInformation != null || this.mShowBlockingUpsellOnRotation) {
            setNonBlockingUpsellInformation();
        }
        this.compositeSubscription.add(this.mAudioVideoToggleInteractor.getToggleSuccessfulObservable().subscribe(new Action1() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment$$ExternalSyntheticLambda20
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                this.f$0.lambda$showLoadingMetadata$25((HybridQueueType) obj);
            }
        }, new Action1() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment$$ExternalSyntheticLambda21
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                NowPlayingFragment.lambda$showLoadingMetadata$26((Throwable) obj);
            }
        }));
        showActionBar();
        LyricsTextViewContainer lyricsTextViewContainer = this.mLyricsViewContainer;
        if (lyricsTextViewContainer != null) {
            lyricsTextViewContainer.setFrugalLyricsListener(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showLoadingMetadata$25(HybridQueueType hybridQueueType) {
        if (ActivityKt.isAvailable(getActivity())) {
            getActivity().invalidateOptionsMenu();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showLoadingMetadata$26(Throwable th) {
        Log.error(TAG, "Error while toggling audio/video %s", CollectionKt.toString(th.getStackTrace(), "\n"));
    }

    private void showActionBar() {
        if (getActivity() instanceof NowPlayingFragmentActivity) {
            NowPlayingFragmentActivity nowPlayingFragmentActivity = (NowPlayingFragmentActivity) getActivity();
            ActionBar supportActionBar = nowPlayingFragmentActivity.getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.transparent)));
            }
            nowPlayingFragmentActivity.showActionBar();
        }
    }

    private void setUpArtLoadSubscription() {
        this.albumArtLoadSubscription = this.artLoadSubject.debounce(100L, TimeUnit.MILLISECONDS).observeOn(Schedulers.computation()).map(new Func1() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment$$ExternalSyntheticLambda8
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return this.f$0.lambda$setUpArtLoadSubscription$27((BitmapDrawable) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(this.onAlbumArtLoadedObserver);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Drawable lambda$setUpArtLoadSubscription$27(BitmapDrawable bitmapDrawable) {
        AlbumArtAndSeekBarLayout albumArtAndSeekBarLayout;
        return (!this.doesPortraitArtRequirePartialBlur || (albumArtAndSeekBarLayout = this.mAlbumArtAndSeekBarLayout) == null) ? bitmapDrawable : albumArtAndSeekBarLayout.createDrawableOverlayWithBlurBackground(bitmapDrawable);
    }

    private void showLyrics(final MediaItem item) {
        ViewGroup viewGroup;
        if (VideoUtil.INSTANCE.isInPictureInPictureMode(getActivity()) || this.mLyricsViewContainer == null) {
            return;
        }
        if (item == null || (item instanceof AdMediaItem) || AmazonApplication.getCapabilities().shouldDisableLyrics(getApplication()) || ((viewGroup = this.mBauhausNowPlayingDialogView) != null && viewGroup.getVisibility() == 0)) {
            this.mLyricsViewContainer.show(false);
            this.mNPSOverlayMetricsReporter.startTimer(NPSViews.ALBUM_ART);
        } else {
            this.mLyricsViewContainer.show(false);
            Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment$$ExternalSyntheticLambda14
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    this.f$0.lambda$showLyrics$28(item, (Subscriber) obj);
                }
            }).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).toCompletable().subscribe(new Action0() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment$$ExternalSyntheticLambda15
                @Override // rx.functions.Action0
                public final void call() {
                    NowPlayingFragment.lambda$showLyrics$29();
                }
            }, new Action1() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment$$ExternalSyntheticLambda16
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    NowPlayingFragment.lambda$showLyrics$30((Throwable) obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showLyrics$28(MediaItem mediaItem, Subscriber subscriber) {
        LyricsTextViewContainer lyricsTextViewContainer = this.mLyricsViewContainer;
        if (lyricsTextViewContainer != null) {
            lyricsTextViewContainer.setOnLyricsAvailableListener(new OnLyricsAvailableListener() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment.31
                @Override // com.amazon.mp3.playback.OnLyricsAvailableListener
                public void onLyricsAvailable(@NonNull final Lyrics lyrics) {
                    if (NowPlayingFragment.this.mLyricsViewContainer.getHasUserInteracted()) {
                        NowPlayingFragment.this.isLyricsInAutomatedPreview = Boolean.FALSE;
                    }
                    NowPlayingFragment.this.mLyrics = lyrics;
                    NPSOverlayMetricsReporter nPSOverlayMetricsReporter = NowPlayingFragment.this.mNPSOverlayMetricsReporter;
                    StageLyricsUtil.Companion companion = StageLyricsUtil.INSTANCE;
                    nPSOverlayMetricsReporter.setFirstLyricsLineStartTime(companion.getTimeToTransitionFromAlbumArtToLyrics(lyrics));
                    if (NowPlayingFragment.this.shouldShowFrugalLyrics()) {
                        return;
                    }
                    NowPlayingFragment.this.mNPSOverlayMetricsReporter.startTimer(companion.getLyricsPreviewViewType(NowPlayingFragment.this.isLyricsInAutomatedPreview.booleanValue()));
                }
            });
            this.mLyricsViewContainer.setOnNoLyricsDisplayedListener(new OnNoLyricsDisplayedListener() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment.32
                @Override // com.amazon.mp3.playback.OnNoLyricsDisplayedListener
                public void onNoLyricsDisplayed() {
                    NowPlayingFragment.this.mNPSOverlayMetricsReporter.startTimer(NPSViews.ALBUM_ART);
                }
            });
            this.mLyricsViewContainer.showLyricsForMediaItem(mediaItem);
            this.mLyricsViewContainer.setPlayStatus(this.mPlaybackController.getPlayStatus());
        }
        subscriber.onNext(null);
        subscriber.onCompleted();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$showLyrics$30(Throwable th) {
        Log.error(TAG, "Error setting up lyricsViewContainer", th);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean shouldShowFrugalLyrics() {
        if (this.mLyrics == null) {
            return false;
        }
        int linePosition = this.mLyricsViewContainer.getLinePosition(this.mPlaybackController.getPositionMillis());
        FrugalLyricsChecker frugalLyricsChecker = FrugalLyricsChecker.INSTANCE;
        int thresholdIndex = frugalLyricsChecker.getThresholdIndex();
        if (thresholdIndex == -2) {
            thresholdIndex = frugalLyricsChecker.calculateThresholdIndex(this.mLyrics);
        }
        return thresholdIndex != -1 && linePosition > thresholdIndex && this.isLyricsInAutomatedPreview.booleanValue();
    }

    @Override // com.amazon.mp3.library.fragment.DeleteContentHandler
    public void deleteContent() {
        LibraryAccessProviderImpl.getInstance(getActivity()).removeFromLibrary(this.mPlaybackController.getCurrentMediaItem());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ImageView getAlbumArtImageView() {
        BaseImage baseImage;
        BaseImage baseImage2;
        FragmentActivity activity = getActivity();
        if (!ActivityKt.isAvailable(activity)) {
            Log.error(TAG, "Activity is null. Can't get album art imageview.");
            return null;
        }
        if (isStage() && (baseImage2 = this.mAlbumArt) != null) {
            return baseImage2.getImage();
        }
        if (ScreenUtil.isPortrait() && (baseImage = this.mAlbumArt) != null) {
            return baseImage.getImage();
        }
        if (!ScreenUtil.isLandscape() || this.mStyleSheet == null) {
            return null;
        }
        this.mLandscapeBackgroundArt.setVisibility(0);
        if (this.mStyleSheet.getLandscapeType(activity) == LandscapeType.TABLET_LANDSCAPE && this.mAlbumArt != null) {
            View view = this.mArtworkSectionLayout;
            if (view != null) {
                view.setVisibility(0);
            }
            return this.mAlbumArt.getImage();
        }
        return this.mLandscapeBackgroundArt;
    }

    private void loadHeroImageArtForTabletLandscape() {
        Subscription subscription = this.backgroundArtLoad;
        if (subscription != null) {
            subscription.unsubscribe();
        }
        this.backgroundArtLoad = Observable.fromCallable(new Callable() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment$$ExternalSyntheticLambda26
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.lambda$loadHeroImageArtForTabletLandscape$31();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment$$ExternalSyntheticLambda27
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                this.f$0.lambda$loadHeroImageArtForTabletLandscape$32((RequestCreator) obj);
            }
        }, new Action1() { // from class: com.amazon.mp3.playback.fragment.NowPlayingFragment$$ExternalSyntheticLambda28
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                NowPlayingFragment.lambda$loadHeroImageArtForTabletLandscape$33((Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ RequestCreator lambda$loadHeroImageArtForTabletLandscape$31() throws Exception {
        Uri uriCreateImageUri;
        MediaItem currentMediaItem = this.mPlaybackController.getCurrentMediaItem();
        if (currentMediaItem == null || (uriCreateImageUri = currentMediaItem.createImageUri(getAlbumArtWidth(), getAlbumArtHeight(), MediaItem.ImageType.ARTIST, MediaItem.ImageType.ALBUM)) == null) {
            return null;
        }
        return Picasso.get().load(uriCreateImageUri).resize(getAlbumArtWidth(), getAlbumArtHeight()).transform(new BitmapUtil.BlurImageTransformation(getContext(), this.mStyleSheet.getBlurStyle(BlurStyleKey.BLUR_1).floatValue()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$loadHeroImageArtForTabletLandscape$32(RequestCreator requestCreator) {
        ImageView imageView;
        if (requestCreator == null || (imageView = this.mLandscapeBackgroundArt) == null) {
            return;
        }
        requestCreator.into(imageView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$loadHeroImageArtForTabletLandscape$33(Throwable th) {
        Log.error(TAG, "Error trying to load tablet landscape background art", th);
    }

    private boolean shouldResizeAlbumArtAndSeekBarContainer() {
        return !isStage() && getMetadataAndControlContainerHeightDP() < 244;
    }

    private void resizeArtworkForSmallScreen() {
        if (this.mAlbumArtAndSeekBarLayout == null) {
            return;
        }
        this.mAlbumArtAndSeekBarLayout.getLayoutParams().height = Math.round(this.mAlbumArtAndSeekBarLayout.getWidth() * getArtworkScalingFactor());
        this.mAlbumArtAndSeekBarLayout.setPortraitArtworkScalingFactor(getArtworkScalingFactor());
        LyricsTextViewContainer lyricsTextViewContainer = this.mLyricsViewContainer;
        if (lyricsTextViewContainer != null) {
            lyricsTextViewContainer.invalidate();
        }
        this.mAlbumArtAndSeekBarLayout.requestLayout();
    }

    private float getArtworkScalingFactor() {
        SpacerParser spacerParser = SpacerParser.INSTANCE;
        return ((spacerParser.pixelsToDp(getResources(), this.mAlbumArtAndSeekBarLayout.getHeight()) - (244 - getMetadataAndControlContainerHeightDP())) - (spacerParser.pixelsToDp(getResources(), this.mSeekBar.getHeight()) / 2)) / spacerParser.pixelsToDp(getResources(), this.mAlbumArtAndSeekBarLayout.getWidth());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateAlbumArtAndSeekBarContainerHeight() {
        FrameLayout frameLayout;
        int albumArtAndSeekBarContainerDefaultHeight = 0;
        if (!isVideoTrack()) {
            if (shouldResizeAlbumArtAndSeekBarContainer()) {
                resizeArtworkForSmallScreen();
                this.doesPortraitArtRequirePartialBlur = true;
                albumArtAndSeekBarContainerDefaultHeight = getResizedAlbumArtAndSeekBarContainerHeightForSmallScreen();
            } else {
                FrameLayout frameLayout2 = this.mViewContainer;
                if (frameLayout2 != null && (frameLayout = (FrameLayout) frameLayout2.findViewById(R.id.AlbumArtAndSeekBarContainer)) != null) {
                    albumArtAndSeekBarContainerDefaultHeight = frameLayout.getHeight();
                }
            }
        } else {
            albumArtAndSeekBarContainerDefaultHeight = getAlbumArtAndSeekBarContainerDefaultHeight();
            FrameLayout frameLayout3 = this.mViewContainer;
            if (frameLayout3 != null) {
                int height = frameLayout3.getHeight();
                if (ScreenUtil.isLandscape() || VideoUtil.INSTANCE.isInPictureInPictureMode(getActivity())) {
                    albumArtAndSeekBarContainerDefaultHeight = -1;
                } else if (height - albumArtAndSeekBarContainerDefaultHeight < SpacerParser.INSTANCE.dpToPixels(getResources(), 244)) {
                    albumArtAndSeekBarContainerDefaultHeight = getResizedAlbumArtAndSeekBarContainerHeightForSmallScreen();
                }
            }
        }
        if (isStage()) {
            return;
        }
        setAlbumArtAndSeekBarContainerHeight(albumArtAndSeekBarContainerDefaultHeight);
    }

    private int getResizedAlbumArtAndSeekBarContainerHeightForSmallScreen() {
        if (this.mViewContainer == null) {
            return 0;
        }
        return SpacerParser.INSTANCE.dpToPixels(getResources(), r0.pixelsToDp(getResources(), this.mViewContainer.getHeight()) - 244);
    }

    private void setAlbumArtAndSeekBarContainerHeight(int height) {
        FrameLayout frameLayout = (FrameLayout) this.mViewRoot.findViewById(R.id.AlbumArtAndSeekBarContainer);
        if (frameLayout != null) {
            ((ViewGroup.MarginLayoutParams) ((ConstraintLayout.LayoutParams) frameLayout.getLayoutParams())).height = height;
            frameLayout.requestLayout();
        }
    }

    private int getAlbumArtAndSeekBarContainerDefaultHeight() {
        AlbumArtAndSeekBarLayout albumArtAndSeekBarLayout = this.mAlbumArtAndSeekBarLayout;
        if (albumArtAndSeekBarLayout != null) {
            return albumArtAndSeekBarLayout.getDefaultHeight();
        }
        return 0;
    }

    private int getMetadataAndControlContainerHeightDP() {
        return SpacerParser.INSTANCE.pixelsToDp(getResources(), this.mPersistentPlayerContainer.getHeight() + this.mMetadataView.getHeight());
    }

    public static NowPlayingFragment newInstance(final Bundle extras) {
        NowPlayingFragment nowPlayingFragment = new NowPlayingFragment();
        nowPlayingFragment.setArguments(extras);
        return nowPlayingFragment;
    }

    @Override // com.amazon.mp3.playback.playbackcontrol.OnLyricsLineUpdateListener
    public void onLyricsLineUpdated(long positionMillis) {
        LyricsTextViewContainer lyricsTextViewContainer = this.mLyricsViewContainer;
        if (lyricsTextViewContainer != null) {
            lyricsTextViewContainer.setPlaybackTime(positionMillis);
        }
    }

    @Override // com.amazon.mp3.playback.view.lyrics.LyricsTextViewContainer.LyricsWindowInteractionListener
    public void onLyricsWindowClicked() {
        goToLyricsFragment();
        sendAccessFullScreenLyricsUiClickEvent(InteractionType.TAP);
    }

    @Override // com.amazon.mp3.playback.view.lyrics.LyricsTextViewContainer.LyricsWindowInteractionListener
    public void onLyricsWindowSwipedUp() {
        if (ScreenUtil.isPortrait()) {
            goToLyricsFragment();
            sendAccessFullScreenLyricsUiClickEvent(InteractionType.SWIPE);
        }
    }

    private void sendAccessFullScreenLyricsUiClickEvent(InteractionType interactionType) {
        ActionValidatedPlaybackController actionValidatedPlaybackController = this.mPlaybackController;
        MetricsHolder.getManager().handleEvent(UiClickEvent.builder(ActionType.EXPAND_LYRICS_FULL).withInteractionType(interactionType).withPageType(PageType.NOW_PLAYING).withEntityIdType(EntityIdType.ASIN).withEntityId(actionValidatedPlaybackController != null ? MediaItem.getMediaItemIdString(actionValidatedPlaybackController.getCurrentMediaItem(), MediaItemId.Type.ASIN, null) : null).withEntityType(EntityType.TRACK).build());
    }

    @Override // com.amazon.mp3.playback.FrugalLyricsListener
    public void onFrugalLyricsDisplayed() {
        this.mPlaybackController.removeOnLyricsLineUpdateListener(this);
    }

    @Override // com.amazon.mp3.playback.FrugalLyricsListener
    public void onFrugalLyricsHidden() {
        this.mPlaybackController.addOnLyricsLineUpdateListener(this);
    }

    private void goToLyricsFragment() {
        if (getActivity() instanceof NowPlayingFragmentActivity) {
            ((NowPlayingFragmentActivity) getActivity()).goToLyricsFragment();
            LyricsTextViewContainer lyricsTextViewContainer = this.mLyricsViewContainer;
            if (lyricsTextViewContainer != null) {
                lyricsTextViewContainer.setHasUserInteracted(true);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleVideoScreenOnFlags(MediaItem mediaItem, PlayStatus playStatus) {
        if (getActivity() == null) {
            return;
        }
        if (MediaItemExtensions.isVideo(mediaItem)) {
            if (playStatus.willPlay()) {
                getActivity().getWindow().addFlags(128);
                return;
            } else {
                getActivity().getWindow().clearFlags(128);
                return;
            }
        }
        getActivity().getWindow().clearFlags(128);
    }

    public void updateLikeButton() {
        PlayerMetadataView playerMetadataView = this.mMetadataView;
        if (playerMetadataView == null) {
            return;
        }
        boolean z = false;
        if (this.mPlaybackController == null) {
            playerMetadataView.showLikeButton(false);
            return;
        }
        if (isStage()) {
            this.mMetadataView.showLikeButton(false);
            return;
        }
        MediaItem currentMediaItem = this.mPlaybackController.getCurrentMediaItem();
        if (!LikesServiceProxy.INSTANCE.shouldShowLikesFeature(currentMediaItem) || ScreenUtil.isLandscape()) {
            this.mMetadataView.showLikeButton(false);
            return;
        }
        if (currentMediaItem != null && currentMediaItem.getLikeState() == LikeState.LIKE) {
            z = true;
        }
        boolean zHasAnyInternetConnection = ConnectivityUtil.hasAnyInternetConnection();
        this.mMetadataView.setLikeButtonState(z);
        this.mMetadataView.showLikeButton(true);
        this.mMetadataView.setLikeButtonEnabledState(zHasAnyInternetConnection);
    }

    public StageViewPresenter getStageViewPresenter() {
        return this.mStageViewPresenter;
    }

    private static class StagePresenterOnClickListener implements View.OnClickListener {
        private WeakReference<StageViewPresenter> mStageViewPresenter;

        StagePresenterOnClickListener(WeakReference<StageViewPresenter> mStageViewPresenter) {
            this.mStageViewPresenter = mStageViewPresenter;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(final View view) {
            if (this.mStageViewPresenter.get() != null) {
                this.mStageViewPresenter.get().handleTouchEvent();
            }
        }
    }
}
