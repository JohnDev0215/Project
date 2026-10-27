package com.amazon.mp3.find.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.auto.CarModeAlexaFabViewController;
import com.amazon.mp3.auto.carmode.CarModeAction;
import com.amazon.mp3.auto.carmode.CarModeNavigation;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.brush.BrushViews;
import com.amazon.mp3.catalog.fragment.BrowseViewsFactory;
import com.amazon.mp3.navigation.DeeplinkAware;
import com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.providers.DefaultContentEligibilityProvider;
import com.amazon.music.find.model.ContentMetadataPlaybackState;
import com.amazon.music.find.viewmodels.CarModeSearchViewModel;
import com.amazon.music.find.viewmodels.CarModeSearchViewModelFactory;
import com.amazon.music.find.viewmodels.SearchViewModelAware;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.config.MediaItemImageManager;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.deeplinks.ContentMetadataOnClickListener;
import com.amazon.music.views.library.models.LinkNavigatorModel;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.SectionGridViewModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.models.enums.TargetType;
import com.amazon.music.views.library.providers.BrushUriClickListenerProvider;
import com.amazon.music.views.library.providers.EndlessScrollDataProvider;
import com.amazon.music.views.library.providers.RowButtonOnClickProvider;
import com.amazon.music.views.library.providers.SeeMoreProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.BlurStyleKey;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.music.voice.launcher.LaunchTargetError;
import com.amazon.music.voice.launcher.LauncherTokens;
import com.amazon.ui.foundations.utils.BitmapEffectUtils;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import com.amazon.ui.foundations.views.BaseButton;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import dagger.android.support.AndroidSupportInjection;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: CarModeSearchBrushFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u008d\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\n*\u0003]`c\u0018\u0000 \u007f2\b\u0012\u0004\u0012\u00020\u00020\u00012\b\u0012\u0004\u0012\u00020\u00020\u00032\u00020\u0004:\u0001\u007fB\u0007¢\u0006\u0004\b}\u0010~J\b\u0010\u0006\u001a\u00020\u0005H\u0002J\b\u0010\u0007\u001a\u00020\u0005H\u0002J\b\u0010\b\u001a\u00020\u0005H\u0002J\b\u0010\t\u001a\u00020\u0005H\u0002J\b\u0010\n\u001a\u00020\u0005H\u0002J\b\u0010\u000b\u001a\u00020\u0005H\u0002J\b\u0010\f\u001a\u00020\u0005H\u0002J\b\u0010\r\u001a\u00020\u0005H\u0002J\u0016\u0010\u0011\u001a\u00020\u00052\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0002J\u0014\u0010\u0015\u001a\u0004\u0018\u00010\u00142\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012H\u0002J\u0012\u0010\u0016\u001a\u00020\u00052\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012H\u0002J\b\u0010\u0017\u001a\u00020\u0005H\u0002J\u0010\u0010\u001a\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u0018H\u0002J\u001a\u0010\u001d\u001a\u00020\u00052\b\u0010\u001b\u001a\u0004\u0018\u00010\u00142\b\u0010\u001c\u001a\u0004\u0018\u00010\u0014J\u001a\u0010\"\u001a\u00020 2\u0006\u0010\u001f\u001a\u00020\u001e2\b\u0010!\u001a\u0004\u0018\u00010 H\u0014J&\u0010%\u001a\u0004\u0018\u00010$2\u0006\u0010\u001f\u001a\u00020\u001e2\b\u0010!\u001a\u0004\u0018\u00010 2\b\u0010#\u001a\u0004\u0018\u00010\u0012H\u0016J\u0010\u0010(\u001a\u00020\u00052\u0006\u0010'\u001a\u00020&H\u0016J\u0012\u0010)\u001a\u00020\u00052\b\u0010#\u001a\u0004\u0018\u00010\u0012H\u0016J\b\u0010*\u001a\u00020\u0005H\u0016J\b\u0010+\u001a\u00020\u0005H\u0016J\b\u0010,\u001a\u00020\u0005H\u0016J\b\u0010-\u001a\u00020\u0005H\u0016J\b\u0010.\u001a\u00020\u0005H\u0016J\u0012\u00101\u001a\u00020\u00052\b\u00100\u001a\u0004\u0018\u00010/H\u0016J\u0012\u00105\u001a\u0004\u0018\u0001042\u0006\u00103\u001a\u000202H\u0014J\u0012\u00108\u001a\u0004\u0018\u0001072\u0006\u00106\u001a\u000202H\u0014J\u0012\u00109\u001a\u00020\u00022\b\u0010#\u001a\u0004\u0018\u00010\u0012H\u0014J\b\u0010:\u001a\u00020\u0014H\u0016J\u0010\u0010>\u001a\u00020=2\u0006\u0010<\u001a\u00020;H\u0014J\b\u0010@\u001a\u00020?H\u0014J\b\u0010A\u001a\u00020?H\u0014J4\u0010I\u001a\u00020\u00052\u0006\u0010C\u001a\u00020B2\u0006\u0010E\u001a\u00020D2\u001a\u0010H\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010G\u0012\u0004\u0012\u00020D\u0012\u0004\u0012\u00020\u00050FH\u0016R\u0014\u0010K\u001a\u00020J8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bK\u0010LR\u0014\u0010N\u001a\u00020M8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bN\u0010OR\u0018\u0010Q\u001a\u0004\u0018\u00010P8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bQ\u0010RR\u0018\u0010T\u001a\u0004\u0018\u00010S8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bT\u0010UR\u0018\u0010V\u001a\u0004\u0018\u00010S8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bV\u0010UR\u0018\u0010X\u001a\u0004\u0018\u00010W8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bX\u0010YR\u0014\u0010[\u001a\u00020Z8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b[\u0010\\R\u0014\u0010^\u001a\u00020]8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b^\u0010_R\u0014\u0010a\u001a\u00020`8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\ba\u0010bR\u0014\u0010d\u001a\u00020c8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bd\u0010eR\u0018\u0010g\u001a\u0004\u0018\u00010f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bg\u0010hR\u0018\u0010j\u001a\u0004\u0018\u00010i8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bj\u0010kR\u0014\u0010m\u001a\u00020l8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bm\u0010nR\"\u0010p\u001a\u00020o8\u0006@\u0006X\u0087.¢\u0006\u0012\n\u0004\bp\u0010q\u001a\u0004\br\u0010s\"\u0004\bt\u0010uR\u0016\u0010y\u001a\u0004\u0018\u00010v8TX\u0094\u0004¢\u0006\u0006\u001a\u0004\bw\u0010xR\u0014\u0010|\u001a\u00020\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bz\u0010{¨\u0006\u0080\u0001"}, d2 = {"Lcom/amazon/mp3/find/view/CarModeSearchBrushFragment;", "Lcom/amazon/mp3/find/view/BaseBrushFragment;", "Lcom/amazon/music/find/viewmodels/CarModeSearchViewModel;", "Lcom/amazon/music/find/viewmodels/SearchViewModelAware;", "Lcom/amazon/mp3/navigation/DeeplinkAware;", "", "setUpVoiceActionHandler", "setupAlexaFabViewController", "clearTouchListener", "handleLoadingSpinner", "styleActionButtons", "handleExternalNavigationRequested", "handleContentMetadataPlaybackStateUpdated", "resetSwipeRefreshLayoutMargin", "", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "models", "setPodcastNewBadgeIfNecessary", "Landroid/os/Bundle;", "bundle", "", "getPageUriFromExtras", "markPageUriAsInitialized", "updateBackgroundImage", "Landroid/graphics/Bitmap;", "bitmap", "setBlurredBackground", "target", "viewId", "trackGenericUiClickEvent", "Landroid/view/LayoutInflater;", "inflater", "Landroid/view/ViewGroup;", "container", "inflateLayout", "savedInstanceState", "Landroid/view/View;", "onCreateView", "Landroid/content/Context;", "context", "onAttach", "onActivityCreated", "onStart", "onResume", "onStop", "onDestroy", "onDestroyView", "Landroid/view/View$OnTouchListener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "setupTouchListener", "Landroidx/fragment/app/FragmentActivity;", "fragmentActivity", "Lcom/amazon/mp3/find/view/BrushContextMenuProvider;", "createContextMenuProvider", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Lcom/amazon/music/views/library/providers/RowButtonOnClickProvider;", "createRowButtonOnClickProvider", "createViewModel", "getBrushScrollIdentifier", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "pageGridViewModel", "Lcom/amazon/mp3/brush/BrushViews;", "createBrushViews", "", "shouldSwipeBeEnabled", "shouldErrorLayoutBeVisible", "Landroid/net/Uri;", "uri", "Lcom/amazon/music/voice/launcher/LauncherTokens;", "launcherTokens", "Lkotlin/Function2;", "Lcom/amazon/music/voice/launcher/LaunchTargetError;", "onComplete", "handle", "Lcom/amazon/mp3/playback/playbackcontrol/ActionValidatedPlaybackController;", "playbackController", "Lcom/amazon/mp3/playback/playbackcontrol/ActionValidatedPlaybackController;", "Lcom/amazon/music/media/playback/config/MediaItemImageManager;", "mediaItemImageManager", "Lcom/amazon/music/media/playback/config/MediaItemImageManager;", "Lcom/amazon/mp3/auto/CarModeAlexaFabViewController;", "mAlexaFabViewController", "Lcom/amazon/mp3/auto/CarModeAlexaFabViewController;", "Lcom/amazon/ui/foundations/views/BaseButton;", "backButton", "Lcom/amazon/ui/foundations/views/BaseButton;", "alexaButton", "Lcom/amazon/mp3/auto/carmode/CarModeNavigation;", "navigationListener", "Lcom/amazon/mp3/auto/carmode/CarModeNavigation;", "Landroid/view/View$OnClickListener;", "mBackButtonListener", "Landroid/view/View$OnClickListener;", "com/amazon/mp3/find/view/CarModeSearchBrushFragment$mEndlessScrollDataProvider$1", "mEndlessScrollDataProvider", "Lcom/amazon/mp3/find/view/CarModeSearchBrushFragment$mEndlessScrollDataProvider$1;", "com/amazon/mp3/find/view/CarModeSearchBrushFragment$mFindUriClickProvider$1", "mFindUriClickProvider", "Lcom/amazon/mp3/find/view/CarModeSearchBrushFragment$mFindUriClickProvider$1;", "com/amazon/mp3/find/view/CarModeSearchBrushFragment$mFindSeeMoreProvider$1", "mFindSeeMoreProvider", "Lcom/amazon/mp3/find/view/CarModeSearchBrushFragment$mFindSeeMoreProvider$1;", "Lcom/amazon/mp3/find/view/CarModeSearchMetadataClickListener;", "mMetadataClickListener", "Lcom/amazon/mp3/find/view/CarModeSearchMetadataClickListener;", "Lcom/amazon/mp3/find/view/CarModeSearchMetadataPlayHandler;", "mSearchMetadataPlayHandler", "Lcom/amazon/mp3/find/view/CarModeSearchMetadataPlayHandler;", "Lcom/amazon/mp3/providers/DefaultContentEligibilityProvider;", "mContentEligibilityProvider", "Lcom/amazon/mp3/providers/DefaultContentEligibilityProvider;", "Lcom/amazon/music/find/viewmodels/CarModeSearchViewModelFactory;", "viewModelFactory", "Lcom/amazon/music/find/viewmodels/CarModeSearchViewModelFactory;", "getViewModelFactory", "()Lcom/amazon/music/find/viewmodels/CarModeSearchViewModelFactory;", "setViewModelFactory", "(Lcom/amazon/music/find/viewmodels/CarModeSearchViewModelFactory;)V", "", "getInvalidateRecycleViewDelayedDuration", "()Ljava/lang/Long;", "invalidateRecycleViewDelayedDuration", "getSearchViewModel", "()Lcom/amazon/music/find/viewmodels/CarModeSearchViewModel;", "searchViewModel", "<init>", "()V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class CarModeSearchBrushFragment extends BaseBrushFragment<CarModeSearchViewModel> implements SearchViewModelAware<CarModeSearchViewModel>, DeeplinkAware {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Logger logger = LoggerFactory.getLogger(CarModeSearchBrushFragment.class.getName());
    private BaseButton alexaButton;
    private BaseButton backButton;
    private CarModeAlexaFabViewController mAlexaFabViewController;
    private final View.OnClickListener mBackButtonListener;
    private final DefaultContentEligibilityProvider mContentEligibilityProvider;
    private final CarModeSearchBrushFragment$mEndlessScrollDataProvider$1 mEndlessScrollDataProvider;
    private final CarModeSearchBrushFragment$mFindSeeMoreProvider$1 mFindSeeMoreProvider;
    private final CarModeSearchBrushFragment$mFindUriClickProvider$1 mFindUriClickProvider;
    private CarModeSearchMetadataClickListener mMetadataClickListener;
    private CarModeSearchMetadataPlayHandler mSearchMetadataPlayHandler;
    private final MediaItemImageManager mediaItemImageManager;
    private CarModeNavigation navigationListener;
    private final ActionValidatedPlaybackController playbackController;
    public CarModeSearchViewModelFactory viewModelFactory;

    @Override // com.amazon.mp3.find.view.BaseBrushFragment
    protected BrushContextMenuProvider createContextMenuProvider(FragmentActivity fragmentActivity) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "fragmentActivity");
        return null;
    }

    @Override // com.amazon.mp3.find.view.BaseBrushFragment
    protected RowButtonOnClickProvider createRowButtonOnClickProvider(FragmentActivity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        return null;
    }

    @Override // com.amazon.mp3.find.view.BaseBrushFragment, com.amazon.mp3.prime.browse.PrimeMusicPagerFragment
    public void setupTouchListener(View.OnTouchListener listener) {
    }

    @Override // com.amazon.mp3.find.view.BaseBrushFragment
    protected boolean shouldErrorLayoutBeVisible() {
        return false;
    }

    @Override // com.amazon.mp3.find.view.BaseBrushFragment
    protected boolean shouldSwipeBeEnabled() {
        return false;
    }

    /* JADX WARN: Type inference failed for: r0v5, types: [com.amazon.mp3.find.view.CarModeSearchBrushFragment$mEndlessScrollDataProvider$1] */
    /* JADX WARN: Type inference failed for: r0v6, types: [com.amazon.mp3.find.view.CarModeSearchBrushFragment$mFindUriClickProvider$1] */
    /* JADX WARN: Type inference failed for: r0v7, types: [com.amazon.mp3.find.view.CarModeSearchBrushFragment$mFindSeeMoreProvider$1] */
    public CarModeSearchBrushFragment() {
        ActionValidatedPlaybackController controller = PlaybackControllerProvider.INSTANCE.getController(ControlSource.APP_UI);
        this.playbackController = controller;
        this.mediaItemImageManager = new MediaItemImageManager(controller.getPlayback().getPlaybackConfig(), R.dimen.car_mode_default_media_item_image_size, R.dimen.car_mode_default_media_item_image_size, new MediaItem.ImageType[0]);
        this.mBackButtonListener = new View.OnClickListener() { // from class: com.amazon.mp3.find.view.CarModeSearchBrushFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CarModeSearchBrushFragment.m1015mBackButtonListener$lambda0(this.f$0, view);
            }
        };
        this.mEndlessScrollDataProvider = new EndlessScrollDataProvider() { // from class: com.amazon.mp3.find.view.CarModeSearchBrushFragment$mEndlessScrollDataProvider$1
            @Override // com.amazon.music.views.library.providers.EndlessScrollDataProvider
            public void loadMore(int page, int totalItemsCount) {
            }
        };
        this.mFindUriClickProvider = new BrushUriClickListenerProvider() { // from class: com.amazon.mp3.find.view.CarModeSearchBrushFragment$mFindUriClickProvider$1

            /* JADX INFO: compiled from: CarModeSearchBrushFragment.kt */
            @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
            public /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[TargetType.values().length];
                    iArr[TargetType.DEEPLINK.ordinal()] = 1;
                    iArr[TargetType.URI.ordinal()] = 2;
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            @Override // com.amazon.music.views.library.providers.BrushUriClickListenerProvider
            public void onClick(String target, String viewId, TargetType targetType) {
                int i = targetType == null ? -1 : WhenMappings.$EnumSwitchMapping$0[targetType.ordinal()];
                if (i == -1) {
                    try {
                        this.this$0.handleDeepLink(target);
                    } catch (Exception unused) {
                        this.this$0.handleBrushUri(target, null, null);
                    }
                    this.this$0.trackGenericUiClickEvent(target, viewId);
                } else if (i == 1) {
                    try {
                        this.this$0.handleDeepLink(target);
                    } catch (Exception unused2) {
                        CarModeSearchBrushFragment.logger.error(Intrinsics.stringPlus("Unable to handle onClick DEEPLINK target: ", target));
                    }
                    this.this$0.trackGenericUiClickEvent(target, viewId);
                } else {
                    if (i != 2) {
                        return;
                    }
                    this.this$0.handleBrushUri(target, null, null);
                    this.this$0.trackGenericUiClickEvent(target, viewId);
                }
            }
        };
        this.mFindSeeMoreProvider = new SeeMoreProvider() { // from class: com.amazon.mp3.find.view.CarModeSearchBrushFragment$mFindSeeMoreProvider$1
            @Override // com.amazon.music.views.library.providers.SeeMoreProvider
            public void onSeeMoreClick(String uri, String viewId, String nextPageToken) {
            }
        };
        this.mContentEligibilityProvider = new DefaultContentEligibilityProvider();
    }

    /* JADX INFO: compiled from: CarModeSearchBrushFragment.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n \b*\u0004\u0018\u00010\u00070\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/amazon/mp3/find/view/CarModeSearchBrushFragment$Companion;", "", "()V", "SEARCH_PAGE_URI_EXTRA", "", "TAG", "logger", "Lorg/slf4j/Logger;", "kotlin.jvm.PlatformType", "newInstance", "Lcom/amazon/mp3/find/view/CarModeSearchBrushFragment;", "bundle", "Landroid/os/Bundle;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final CarModeSearchBrushFragment newInstance(Bundle bundle) {
            CarModeSearchBrushFragment carModeSearchBrushFragment = new CarModeSearchBrushFragment();
            carModeSearchBrushFragment.setArguments(bundle);
            return carModeSearchBrushFragment;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: mBackButtonListener$lambda-0, reason: not valid java name */
    public static final void m1015mBackButtonListener$lambda0(CarModeSearchBrushFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getViewModel().goBack();
    }

    @Override // com.amazon.mp3.find.view.BaseBrushFragment
    protected Long getInvalidateRecycleViewDelayedDuration() {
        return getViewModel().getInvalidateRecycleViewDelayedDuration();
    }

    @Override // com.amazon.music.find.viewmodels.SearchViewModelAware
    public CarModeSearchViewModel getSearchViewModel() {
        return getViewModel();
    }

    public final CarModeSearchViewModelFactory getViewModelFactory() {
        CarModeSearchViewModelFactory carModeSearchViewModelFactory = this.viewModelFactory;
        if (carModeSearchViewModelFactory != null) {
            return carModeSearchViewModelFactory;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModelFactory");
        return null;
    }

    private final void setUpVoiceActionHandler() {
        PageType uiPageType = getViewModel().getUiPageType();
        if (uiPageType == null) {
            uiPageType = PageType.UNKNOWN;
        }
        CarModeSearchMetadataPlayHandler carModeSearchMetadataPlayHandler = new CarModeSearchMetadataPlayHandler(this, uiPageType, getViewModel().getPlaybackPageType());
        getViewModel().setVoiceContentMetadataHandlerFactory(new SearchMetadataHandlerFactory(CollectionsKt__CollectionsJVMKt.listOf(carModeSearchMetadataPlayHandler)));
        this.mSearchMetadataPlayHandler = carModeSearchMetadataPlayHandler;
    }

    private final void setupAlexaFabViewController() {
        if (getActivity() == null) {
            return;
        }
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
        this.mAlexaFabViewController = new CarModeAlexaFabViewController(contextRequireContext, this.alexaButton, getViewModel().getUiPageType());
    }

    private final void clearTouchListener() {
        logger.debug("SPL::clearTouchListener");
        getMRecyclerView().setOnTouchListener(null);
        getRoot().setOnTouchListener(null);
    }

    private final void handleLoadingSpinner() {
        getViewModel().getShouldShowLoadingSpinner().observe(getViewLifecycleOwner(), new Observer() { // from class: com.amazon.mp3.find.view.CarModeSearchBrushFragment$$ExternalSyntheticLambda3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                CarModeSearchBrushFragment.m1014handleLoadingSpinner$lambda3(this.f$0, (Boolean) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: handleLoadingSpinner$lambda-3, reason: not valid java name */
    public static final void m1014handleLoadingSpinner$lambda3(CarModeSearchBrushFragment this$0, Boolean it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        View mLoadingView = this$0.getMLoadingView();
        Intrinsics.checkNotNullExpressionValue(it, "it");
        mLoadingView.setVisibility(it.booleanValue() ? 0 : 8);
        this$0.getMRecyclerView().setVisibility(it.booleanValue() ? 8 : 0);
    }

    private final void styleActionButtons() {
        Drawable drawable;
        BaseButton.StyleBuilder styleBuilderWithIcon;
        Drawable drawable2;
        BaseButton.StyleBuilder styleBuilderWithIcon2;
        StyleSheet mStyleSheet = getMStyleSheet();
        BaseButton.StyleBuilder iconBuilder = mStyleSheet == null ? null : mStyleSheet.getIconBuilder(IconSizeKey.LARGE, IconStyleKey.GLASS);
        BaseButton baseButton = this.backButton;
        if (baseButton != null && (drawable2 = ContextCompat.getDrawable(requireContext(), R.drawable.ic_chevron_caretdown)) != null && iconBuilder != null && (styleBuilderWithIcon2 = iconBuilder.withIcon(drawable2)) != null) {
            styleBuilderWithIcon2.applyStyle(baseButton);
        }
        BaseButton baseButton2 = this.alexaButton;
        if (baseButton2 == null || (drawable = ContextCompat.getDrawable(requireContext(), R.drawable.ic_alexa_bauhaus)) == null || iconBuilder == null || (styleBuilderWithIcon = iconBuilder.withIcon(drawable)) == null) {
            return;
        }
        styleBuilderWithIcon.applyStyle(baseButton2);
    }

    private final void handleExternalNavigationRequested() {
        addDisposable(getViewModel().getExternalNavigationRequested().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.mp3.find.view.CarModeSearchBrushFragment$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                CarModeSearchBrushFragment.m1013handleExternalNavigationRequested$lambda8(this.f$0, (Unit) obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: handleExternalNavigationRequested$lambda-8, reason: not valid java name */
    public static final void m1013handleExternalNavigationRequested$lambda8(CarModeSearchBrushFragment this$0, Unit unit) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        CarModeNavigation carModeNavigation = this$0.navigationListener;
        if (carModeNavigation == null) {
            return;
        }
        CarModeNavigation.DefaultImpls.navigateTo$default(carModeNavigation, CarModeAction.SHOW_HOME_PAGE, null, 2, null);
    }

    private final void handleContentMetadataPlaybackStateUpdated() {
        addDisposable(getViewModel().getContentMetadataPlaybackStateUpdated().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.mp3.find.view.CarModeSearchBrushFragment$$ExternalSyntheticLambda2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                CarModeSearchBrushFragment.m1012handleContentMetadataPlaybackStateUpdated$lambda9(this.f$0, (ContentMetadataPlaybackState) obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: handleContentMetadataPlaybackStateUpdated$lambda-9, reason: not valid java name */
    public static final void m1012handleContentMetadataPlaybackStateUpdated$lambda9(CarModeSearchBrushFragment this$0, ContentMetadataPlaybackState contentMetadataPlaybackState) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        CarModeSearchMetadataClickListener carModeSearchMetadataClickListener = this$0.mMetadataClickListener;
        if (carModeSearchMetadataClickListener == null) {
            return;
        }
        ContentMetadataOnClickListener.DefaultImpls.onContentListClicked$default(carModeSearchMetadataClickListener, contentMetadataPlaybackState.getContentMetadataList(), contentMetadataPlaybackState.getPositionToStart(), null, 4, null);
    }

    private final void resetSwipeRefreshLayoutMargin() {
        LayoutParamUtils.INSTANCE.setLayoutMargins(getMSwipeRefreshLayout(), -1, -1, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : 0, (240 & 32) != 0 ? null : null, (240 & 64) != 0 ? null : 0, (240 & 128) != 0 ? null : null);
    }

    public final void trackGenericUiClickEvent(String target, String viewId) {
        getViewModel().trackGenericUiClickEvent(target, viewId);
    }

    private final String getPageUriFromExtras(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        return bundle.getString("SEARCH_PAGE_URI_EXTRA");
    }

    private final void markPageUriAsInitialized(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        bundle.remove("SEARCH_PAGE_URI_EXTRA");
    }

    @Override // com.amazon.mp3.find.view.BaseBrushFragment
    protected ViewGroup inflateLayout(LayoutInflater inflater, ViewGroup container) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View viewInflate = inflater.inflate(R.layout.search_results_car_mode, container, false);
        if (viewInflate != null) {
            return (ViewGroup) viewInflate;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup");
    }

    @Override // com.amazon.mp3.find.view.BaseBrushFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        OnBackPressedDispatcher onBackPressedDispatcher;
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View viewOnCreateView = super.onCreateView(inflater, container, savedInstanceState);
        if (viewOnCreateView == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup");
        }
        setRoot((ViewGroup) viewOnCreateView);
        FragmentActivity activity = getActivity();
        if (activity != null && (onBackPressedDispatcher = activity.getOnBackPressedDispatcher()) != null) {
            onBackPressedDispatcher.addCallback(getViewLifecycleOwner(), new OnBackPressedCallback() { // from class: com.amazon.mp3.find.view.CarModeSearchBrushFragment.onCreateView.1
                {
                    super(true);
                }

                @Override // androidx.activity.OnBackPressedCallback
                public void handleOnBackPressed() {
                    CarModeSearchBrushFragment.this.getViewModel().goBack();
                }
            });
        }
        this.backButton = (BaseButton) getRoot().findViewById(R.id.car_mode_search_back_button);
        this.alexaButton = (BaseButton) getRoot().findViewById(R.id.car_mode_search_alexa_button);
        styleActionButtons();
        getMSwipeRefreshLayout().setEnabled(false);
        getMEmptyLayout().setVisibility(8);
        return getRoot();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        logger.debug("SPL::onAttach");
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
        if (context instanceof CarModeNavigation) {
            this.navigationListener = (CarModeNavigation) context;
        }
    }

    @Override // com.amazon.mp3.find.view.BaseBrushFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        logger.debug("SPL::onActivityCreated");
        super.onActivityCreated(savedInstanceState);
        BaseButton baseButton = this.backButton;
        if (baseButton != null) {
            baseButton.setOnClickListener(this.mBackButtonListener);
        }
        setupAlexaFabViewController();
        setUpVoiceActionHandler();
        handleLoadingSpinner();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        logger.debug("SPL::onStart");
        super.onStart();
        handleExternalNavigationRequested();
        handleContentMetadataPlaybackStateUpdated();
    }

    @Override // com.amazon.mp3.find.view.BaseBrushFragment, androidx.fragment.app.Fragment
    public void onResume() {
        updateBackgroundImage();
        styleActionButtons();
        CarModeAlexaFabViewController carModeAlexaFabViewController = this.mAlexaFabViewController;
        if (carModeAlexaFabViewController != null) {
            carModeAlexaFabViewController.setAlexaFabButtonVisibility(true);
        }
        super.onResume();
    }

    private final void updateBackgroundImage() {
        if (Intrinsics.areEqual(this.playbackController.getCurrentMediaItem(), this.mediaItemImageManager.getMediaItem())) {
            return;
        }
        this.mediaItemImageManager.setMediaItem(this.playbackController.getCurrentMediaItem());
        Bitmap bitmap = this.mediaItemImageManager.getBitmap();
        if (bitmap == null) {
            return;
        }
        setBlurredBackground(bitmap);
    }

    private final void setBlurredBackground(Bitmap bitmap) {
        Drawable blurBitmapDrawable;
        StyleSheet mStyleSheet = getMStyleSheet();
        if (mStyleSheet == null) {
            return;
        }
        Bitmap bitmapBackground = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        Float blurStyle = mStyleSheet.getBlurStyle(BlurStyleKey.BLUR_2);
        if (blurStyle == null) {
            return;
        }
        float fFloatValue = blurStyle.floatValue();
        Context context = getContext();
        if (context == null) {
            blurBitmapDrawable = null;
        } else {
            BitmapEffectUtils bitmapEffectUtils = BitmapEffectUtils.INSTANCE;
            Intrinsics.checkNotNullExpressionValue(bitmapBackground, "bitmapBackground");
            blurBitmapDrawable = bitmapEffectUtils.getBlurBitmapDrawable(context, bitmapBackground, fFloatValue);
        }
        getMBackgroundArtworkLayout().setImageDrawable(blurBitmapDrawable);
    }

    @Override // com.amazon.mp3.find.view.BaseBrushFragment, androidx.fragment.app.Fragment
    public void onStop() {
        logger.debug("SPL::onStop");
        CarModeAlexaFabViewController carModeAlexaFabViewController = this.mAlexaFabViewController;
        if (carModeAlexaFabViewController != null) {
            carModeAlexaFabViewController.setAlexaFabButtonVisibility(false);
        }
        super.onStop();
    }

    @Override // com.amazon.mp3.find.view.BaseBrushFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        CarModeAlexaFabViewController carModeAlexaFabViewController = this.mAlexaFabViewController;
        if (carModeAlexaFabViewController != null) {
            carModeAlexaFabViewController.destroy();
        }
        this.mAlexaFabViewController = null;
        this.navigationListener = null;
        super.onDestroy();
    }

    @Override // com.amazon.mp3.find.view.BaseBrushFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        clearTouchListener();
        super.onDestroyView();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.mp3.find.view.BaseBrushFragment
    public CarModeSearchViewModel createViewModel(Bundle savedInstanceState) {
        logger.debug("SPL::createViewModel");
        FragmentActivity fragmentActivityRequireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "requireActivity()");
        CarModeSearchViewModel carModeSearchViewModel = (CarModeSearchViewModel) new ViewModelProvider(fragmentActivityRequireActivity, getViewModelFactory()).get(CarModeSearchViewModel.class);
        if (getPageUriFromExtras(savedInstanceState) == null) {
            carModeSearchViewModel.setPageUri(getPageUriFromExtras(getArguments()));
            markPageUriAsInitialized(getArguments());
        }
        return carModeSearchViewModel;
    }

    @Override // com.amazon.mp3.find.view.BaseBrushFragment
    public String getBrushScrollIdentifier() {
        String name = CarModeSearchBrushFragment.class.getName();
        Intrinsics.checkNotNullExpressionValue(name, "CarModeSearchBrushFragment::class.java.name");
        return name;
    }

    @Override // com.amazon.mp3.find.view.BaseBrushFragment
    protected BrushViews createBrushViews(PageGridViewModel pageGridViewModel) {
        Intrinsics.checkNotNullParameter(pageGridViewModel, "pageGridViewModel");
        CarModeSearchMetadataPlayHandler carModeSearchMetadataPlayHandler = this.mSearchMetadataPlayHandler;
        this.mMetadataClickListener = carModeSearchMetadataPlayHandler == null ? null : new CarModeSearchMetadataClickListener(carModeSearchMetadataPlayHandler);
        BrushViews brushViews = BrowseViewsFactory.createBrowseViewsV2(this, getMStyleSheet(), getMRecyclerView(), getMContextMenuProvider(), this.mFindSeeMoreProvider, this.mMetadataClickListener, this.mFindUriClickProvider, null, null, this.mEndlessScrollDataProvider, getMViewModelWrapper().getMLibraryStateProvider(), getMContentViewManager(), null, this.mContentEligibilityProvider, getMRowButtonOnClickProvider(), null, getMViewModelWrapper().getItemOrdinalProvider(), null, null, null);
        setPodcastNewBadgeIfNecessary(pageGridViewModel.getModels());
        resetSwipeRefreshLayoutMargin();
        Intrinsics.checkNotNullExpressionValue(brushViews, "brushViews");
        return brushViews;
    }

    @Override // com.amazon.mp3.navigation.DeeplinkAware
    public void handle(Uri uri, LauncherTokens launcherTokens, Function2<? super LaunchTargetError, ? super LauncherTokens, Unit> onComplete) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        Intrinsics.checkNotNullParameter(launcherTokens, "launcherTokens");
        Intrinsics.checkNotNullParameter(onComplete, "onComplete");
        getViewModel().handleDeeplink(uri, launcherTokens, onComplete);
    }

    private final void setPodcastNewBadgeIfNecessary(List<? extends BaseViewModel> models) {
        ArrayList arrayList = new ArrayList();
        for (Object obj : models) {
            if (obj instanceof SectionGridViewModel) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList2, ((SectionGridViewModel) it.next()).getModels());
        }
        ArrayList arrayList3 = new ArrayList();
        for (Object obj2 : arrayList2) {
            if (obj2 instanceof LinkNavigatorModel) {
                arrayList3.add(obj2);
            }
        }
        ArrayList arrayList4 = new ArrayList();
        for (Object obj3 : arrayList3) {
            if (StringsKt__StringsKt.contains$default((CharSequence) ((LinkNavigatorModel) obj3).getTarget(), (CharSequence) "/podcasts", false, 2, (Object) null)) {
                arrayList4.add(obj3);
            }
        }
        Iterator it2 = arrayList4.iterator();
        while (it2.hasNext()) {
            ((LinkNavigatorModel) it2.next()).setShouldShowNewBadge(true);
        }
    }
}
