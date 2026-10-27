package com.amazon.mp3.auto.carmode.fragment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ProgressBar;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.auto.AutoFlexEventUtil;
import com.amazon.mp3.auto.AutoUiEventUtil;
import com.amazon.mp3.auto.CarModeAlexaFabViewController;
import com.amazon.mp3.auto.carmode.CarModeAction;
import com.amazon.mp3.auto.carmode.CarModeAlexaActionsHandler;
import com.amazon.mp3.auto.carmode.CarModeIngressAutoPlayTypes;
import com.amazon.mp3.auto.carmode.CarModeNavigation;
import com.amazon.mp3.auto.carmode.CarModePresetsUtility;
import com.amazon.mp3.auto.carmode.CarModeUtility;
import com.amazon.mp3.auto.carmode.fragment.CarModeBrushFragment;
import com.amazon.mp3.auto.carmode.fragment.viewmodel.CarModeBrushViewModel;
import com.amazon.mp3.auto.carmode.view.CarModeEmbeddedDialog;
import com.amazon.mp3.auto.carmode.view.CarModeMiniPlayerView;
import com.amazon.mp3.auto.carmode.view.CarModeMiniPlayerViewController;
import com.amazon.mp3.auto.carmode.view.CarModePlayerViewController;
import com.amazon.mp3.auto.playback.CarModeMetadataClickListener;
import com.amazon.mp3.auto.storage.PresetIdentifier;
import com.amazon.mp3.auto.storage.PresetIdentifierHelper;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.brush.BrushViews;
import com.amazon.mp3.catalog.fragment.BrowseViewsFactory;
import com.amazon.mp3.navigation.ContextAwareDeeplinkManager;
import com.amazon.mp3.navigation.LauncherLinkStatusHandler;
import com.amazon.mp3.playback.PlayerNavigationListener;
import com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.playback.state.LibraryEqualizerStateManager;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.PlaybackHandlerUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.mp3.util.extensions.ContextKt;
import com.amazon.music.browse.brush.BrushLatencyReportingUtil;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.OnPlayStateChangedListener;
import com.amazon.music.metrics.mts.event.types.ExperienceMode;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.LatencyTrackingLeg;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.skyfire.ui.skyfire.Contexts;
import com.amazon.music.subscription.UserSubscription;
import com.amazon.music.uicontentview.ContentViewManager;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.SimpleHorizontalTileModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.models.enums.TargetType;
import com.amazon.music.views.library.providers.BrushUriClickListenerProvider;
import com.amazon.music.views.library.recyclerview.OnItemClickListener;
import com.amazon.music.views.library.recyclerview.adapters.UniversalAdapter;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.podcast.external.car.PodcastMediaMetadata;
import com.amazon.podcast.media.playback.Media;
import com.amazon.podcast.media.playback.PodcastMediaItem;
import com.amazon.ui.foundations.views.BaseButton;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/* JADX INFO: compiled from: CarModeBrushFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000¶\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0006\u0018\u0000 \u008e\u00012\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u0006:\u0002\u008e\u0001B\t¢\u0006\u0006\b\u008c\u0001\u0010\u008d\u0001J\b\u0010\b\u001a\u00020\u0007H\u0002J\u0010\u0010\u000b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\tH\u0002J\u0012\u0010\u000e\u001a\u00020\u00072\b\b\u0002\u0010\r\u001a\u00020\fH\u0002J\u0010\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u000fH\u0002J\b\u0010\u0012\u001a\u00020\u0007H\u0002J\u0010\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u0013H\u0002J\u001a\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\f2\b\b\u0002\u0010\u0017\u001a\u00020\fH\u0002J\u0010\u0010\u001b\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u0019H\u0002J\b\u0010\u001c\u001a\u00020\fH\u0002J\u0010\u0010\u001f\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u001dH\u0002J\u001a\u0010#\u001a\u00020\u00072\u0006\u0010!\u001a\u00020 2\b\b\u0002\u0010\"\u001a\u00020\fH\u0002J\u0012\u0010%\u001a\u00020\u00072\b\u0010$\u001a\u0004\u0018\u00010\u001dH\u0016J\u0012\u0010&\u001a\u00020\u00072\b\u0010$\u001a\u0004\u0018\u00010\u001dH\u0016J&\u0010,\u001a\u0004\u0018\u00010+2\u0006\u0010(\u001a\u00020'2\b\u0010*\u001a\u0004\u0018\u00010)2\b\u0010$\u001a\u0004\u0018\u00010\u001dH\u0016J\u001a\u0010.\u001a\u00020\u00072\u0006\u0010-\u001a\u00020+2\b\u0010$\u001a\u0004\u0018\u00010\u001dH\u0016J\u0006\u0010/\u001a\u00020\u0007J\u0006\u00100\u001a\u00020\u0007J\u0018\u00102\u001a\u00020\u00072\u0006\u0010-\u001a\u00020+2\u0006\u00101\u001a\u00020 H\u0016J\b\u00103\u001a\u00020\u0007H\u0016J\b\u00104\u001a\u00020\u0007H\u0016J\b\u00105\u001a\u00020\u0007H\u0016JD\u0010>\u001a\u00020\u00072:\u0010=\u001a6\u0012\u0013\u0012\u001107¢\u0006\f\b8\u0012\b\b9\u0012\u0004\b\b(:\u0012\u0015\u0012\u0013\u0018\u00010;¢\u0006\f\b8\u0012\b\b9\u0012\u0004\b\b(<\u0012\u0006\u0012\u0004\u0018\u00010\u000706H\u0016JD\u0010?\u001a\u00020\u00072:\u0010=\u001a6\u0012\u0013\u0012\u001107¢\u0006\f\b8\u0012\b\b9\u0012\u0004\b\b(:\u0012\u0015\u0012\u0013\u0018\u00010;¢\u0006\f\b8\u0012\b\b9\u0012\u0004\b\b(<\u0012\u0006\u0012\u0004\u0018\u00010\u000706H\u0016JL\u0010@\u001a\u00020\u00072\u0006\u0010!\u001a\u00020 2:\u0010=\u001a6\u0012\u0013\u0012\u001107¢\u0006\f\b8\u0012\b\b9\u0012\u0004\b\b(:\u0012\u0015\u0012\u0013\u0018\u00010;¢\u0006\f\b8\u0012\b\b9\u0012\u0004\b\b(<\u0012\u0006\u0012\u0004\u0018\u00010\u000706H\u0016JD\u0010A\u001a\u00020\u00072:\u0010=\u001a6\u0012\u0013\u0012\u001107¢\u0006\f\b8\u0012\b\b9\u0012\u0004\b\b(:\u0012\u0015\u0012\u0013\u0018\u00010;¢\u0006\f\b8\u0012\b\b9\u0012\u0004\b\b(<\u0012\u0006\u0012\u0004\u0018\u00010\u000706H\u0016J\u0012\u0010D\u001a\u00020\u00072\b\u0010C\u001a\u0004\u0018\u00010BH\u0016J\b\u0010E\u001a\u00020\u0007H\u0016R\u0016\u0010F\u001a\u00020\f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bF\u0010GR\u0016\u0010H\u001a\u00020\f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bH\u0010GR\u001c\u0010J\u001a\n I*\u0004\u0018\u00010;0;8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bJ\u0010KR\u0018\u0010M\u001a\u0004\u0018\u00010L8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bM\u0010NR\u0016\u0010P\u001a\u00020O8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\bP\u0010QR\u0018\u0010S\u001a\u0004\u0018\u00010R8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bS\u0010TR\u0018\u0010V\u001a\u0004\u0018\u00010U8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bV\u0010WR\u0014\u0010Y\u001a\u00020X8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bY\u0010ZR\u0018\u0010\\\u001a\u0004\u0018\u00010[8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\\\u0010]R\u0016\u0010_\u001a\u00020^8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b_\u0010`R\u0016\u0010b\u001a\u00020a8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\bb\u0010cR\u0018\u0010e\u001a\u0004\u0018\u00010d8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\be\u0010fR\u0018\u0010g\u001a\u0004\u0018\u00010R8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bg\u0010TR\u0016\u0010i\u001a\u00020h8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\bi\u0010jR\u0018\u0010l\u001a\u0004\u0018\u00010k8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bl\u0010mR\u001c\u0010p\u001a\b\u0012\u0004\u0012\u00020o0n8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bp\u0010qR\u0018\u0010s\u001a\u0004\u0018\u00010r8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bs\u0010tR\u0018\u0010v\u001a\u0004\u0018\u00010u8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bv\u0010wR\u0018\u0010y\u001a\u0004\u0018\u00010x8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\by\u0010zR\u0018\u0010|\u001a\u0004\u0018\u00010{8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b|\u0010}R6\u0010\u0081\u0001\u001a\u001f\u0012\u0004\u0012\u00020;\u0012\u0004\u0012\u00020\u007f0~j\u000f\u0012\u0004\u0012\u00020;\u0012\u0004\u0012\u00020\u007f`\u0080\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0081\u0001\u0010\u0082\u0001R\u001c\u0010\u0084\u0001\u001a\u0005\u0018\u00010\u0083\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0084\u0001\u0010\u0085\u0001R\u0018\u0010\u0086\u0001\u001a\u00020\f8\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b\u0086\u0001\u0010GR\u0018\u0010\u0087\u0001\u001a\u00020\f8\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b\u0087\u0001\u0010GR+\u0010\u008a\u0001\u001a\u0014\u0012\u0007\u0012\u0005\u0018\u00010\u0089\u0001\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0088\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u008a\u0001\u0010\u008b\u0001¨\u0006\u008f\u0001"}, d2 = {"Lcom/amazon/mp3/auto/carmode/fragment/CarModeBrushFragment;", "Lcom/amazon/mp3/auto/carmode/fragment/CarModeBaseFragment;", "Lcom/amazon/music/views/library/recyclerview/OnItemClickListener;", "Lcom/amazon/mp3/auto/carmode/CarModeAlexaActionsHandler;", "Lcom/amazon/mp3/navigation/LauncherLinkStatusHandler;", "Lcom/amazon/podcast/media/playback/Media$PodcastMediaMetadataCallback;", "Lcom/amazon/music/media/playback/OnPlayStateChangedListener;", "", "addOrdinalsToModel", "Lcom/amazon/music/views/library/models/SimpleHorizontalTileModel;", "deletion", "updateDatasetOnDelete", "", "fromPlaybackCallback", "selectPlayingPresetIfExists", "Landroid/content/Context;", "context", "showErrorDialog", "createAndAssignDeleteSwipeHandler", "Lcom/amazon/music/metrics/mts/event/types/pagelatencyinfo/LatencyTrackingLeg;", "leg", "reportPageLoadLatency", "match", "fromCallback", "reportPresetMatchMetrics", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "stylizeViews", "hasPresets", "Landroid/os/Bundle;", "bundle", "storeDeeplinkArguments", "", "presetNumber", "performClick", "selectPreset", "savedInstanceState", "onCreate", "onActivityCreated", "Landroid/view/LayoutInflater;", "inflater", "Landroid/view/ViewGroup;", "container", "Landroid/view/View;", "onCreateView", "view", "onViewCreated", "setupBrushViews", "createBrushRootView", "position", "onItemClick", "onPause", "onDestroyView", "onDestroy", "Lkotlin/Function2;", "Lcom/amazon/mp3/navigation/ContextAwareDeeplinkManager$Companion$Status;", "Lkotlin/ParameterName;", "name", NotificationCompat.CATEGORY_STATUS, "", "message", "onComplete", "handlePresetsPlaybackNext", "handlePresetsPlaybackPrevious", "handlePresetPlayback", "handleExitCarMode", "Lcom/amazon/podcast/external/car/PodcastMediaMetadata;", "metadata", "onPodcastMediaMetadataChange", "onPlayStateChanged", "isBrushViewSetup", "Z", "isViewStylized", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "Lcom/amazon/mp3/auto/carmode/view/CarModeMiniPlayerViewController;", "miniPlayerViewController", "Lcom/amazon/mp3/auto/carmode/view/CarModeMiniPlayerViewController;", "Lcom/amazon/mp3/auto/carmode/view/CarModeMiniPlayerView;", "carModeMiniPlayerView", "Lcom/amazon/mp3/auto/carmode/view/CarModeMiniPlayerView;", "Lcom/amazon/ui/foundations/views/BaseButton;", "carModeCloseButton", "Lcom/amazon/ui/foundations/views/BaseButton;", "Lcom/amazon/mp3/auto/playback/CarModeMetadataClickListener;", "metadataClickHandler", "Lcom/amazon/mp3/auto/playback/CarModeMetadataClickListener;", "Lcom/amazon/mp3/playback/playbackcontrol/ActionValidatedPlaybackController;", "playbackController", "Lcom/amazon/mp3/playback/playbackcontrol/ActionValidatedPlaybackController;", "Lcom/amazon/music/media/playback/MediaItem;", "lastMediaItem", "Lcom/amazon/music/media/playback/MediaItem;", "Landroid/widget/ProgressBar;", "loadingSpinner", "Landroid/widget/ProgressBar;", "Lcom/amazon/mp3/auto/carmode/view/CarModeEmbeddedDialog;", "onErrorDialog", "Lcom/amazon/mp3/auto/carmode/view/CarModeEmbeddedDialog;", "Lcom/amazon/mp3/auto/CarModeAlexaFabViewController;", "alexaFabViewController", "Lcom/amazon/mp3/auto/CarModeAlexaFabViewController;", "alexaFloatingActionButton", "Landroidx/appcompat/widget/AppCompatImageView;", "backgroundImageView", "Landroidx/appcompat/widget/AppCompatImageView;", "Landroidx/recyclerview/widget/ItemTouchHelper;", "itemTouchHelper", "Landroidx/recyclerview/widget/ItemTouchHelper;", "", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "adapterDataset", "Ljava/util/List;", "Lcom/amazon/music/uicontentview/ContentViewManager;", "contentViewManager", "Lcom/amazon/music/uicontentview/ContentViewManager;", "Lcom/amazon/mp3/auto/carmode/fragment/viewmodel/CarModeBrushViewModel;", "viewModel", "Lcom/amazon/mp3/auto/carmode/fragment/viewmodel/CarModeBrushViewModel;", "Landroidx/recyclerview/widget/RecyclerView;", "presetRecyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "Lcom/amazon/mp3/brush/BrushViews;", "presetBrushView", "Lcom/amazon/mp3/brush/BrushViews;", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "deeplinkArguments", "Ljava/util/HashMap;", "Lrx/Subscription;", "subscription", "Lrx/Subscription;", "isAutoPlayHandledOnIngress", "isPodcastHarleyEnabled", "Lkotlin/Function1;", "", "pageErrorCallback", "Lkotlin/jvm/functions/Function1;", "<init>", "()V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class CarModeBrushFragment extends CarModeBaseFragment implements OnItemClickListener, CarModeAlexaActionsHandler, LauncherLinkStatusHandler, Media.PodcastMediaMetadataCallback, OnPlayStateChangedListener {
    private static final Logger LOG = LoggerFactory.getLogger(CarModeBrushFragment.class.getName());
    private CarModeAlexaFabViewController alexaFabViewController;
    private BaseButton alexaFloatingActionButton;
    private AppCompatImageView backgroundImageView;
    private BaseButton carModeCloseButton;
    private CarModeMiniPlayerView carModeMiniPlayerView;
    private ContentViewManager contentViewManager;
    private boolean isAutoPlayHandledOnIngress;
    private boolean isBrushViewSetup;
    private boolean isPodcastHarleyEnabled;
    private boolean isViewStylized;
    private ItemTouchHelper itemTouchHelper;
    private MediaItem lastMediaItem;
    private ProgressBar loadingSpinner;
    private CarModeMetadataClickListener metadataClickHandler;
    private CarModeMiniPlayerViewController miniPlayerViewController;
    private CarModeEmbeddedDialog onErrorDialog;
    private BrushViews presetBrushView;
    private RecyclerView presetRecyclerView;
    private Subscription subscription;
    private CarModeBrushViewModel viewModel;
    private final String TAG = CarModeBrushFragment.class.getSimpleName();
    private final ActionValidatedPlaybackController playbackController = PlaybackControllerProvider.INSTANCE.getController(ControlSource.APP_UI);
    private List<BaseViewModel> adapterDataset = new ArrayList();
    private HashMap<String, Object> deeplinkArguments = new HashMap<>();
    private Function1<? super Throwable, Unit> pageErrorCallback = new CarModeBrushFragment$pageErrorCallback$1(this);

    @Override // com.amazon.mp3.navigation.LauncherLinkStatusHandler
    public void broadcastSuccessfullyHandledLauncherLink(Context context, String str, Uri uri) {
        LauncherLinkStatusHandler.DefaultImpls.broadcastSuccessfullyHandledLauncherLink(this, context, str, uri);
    }

    private final void addOrdinalsToModel() {
        int i = 0;
        for (Object obj : this.adapterDataset) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
            }
            BaseViewModel baseViewModel = (BaseViewModel) obj;
            SimpleHorizontalTileModel simpleHorizontalTileModel = baseViewModel instanceof SimpleHorizontalTileModel ? (SimpleHorizontalTileModel) baseViewModel : null;
            if (simpleHorizontalTileModel != null) {
                Log.debug(this.TAG, Intrinsics.stringPlus("Ordinalised title => ", i2 + ") " + ((Object) simpleHorizontalTileModel.getTitle())));
                simpleHorizontalTileModel.setOrdinal(Integer.valueOf(i2));
            }
            i = i2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateDatasetOnDelete(SimpleHorizontalTileModel deletion) {
        PresetIdentifier presetIdentifierAsPresetIdentifier;
        this.adapterDataset.remove(deletion);
        CarModeBrushViewModel carModeBrushViewModel = this.viewModel;
        if (carModeBrushViewModel != null) {
            carModeBrushViewModel.updatePresetList(this.adapterDataset);
        }
        Context context = getContext();
        if (context == null || (presetIdentifierAsPresetIdentifier = PresetIdentifierHelper.INSTANCE.asPresetIdentifier(deletion)) == null) {
            return;
        }
        PresetIdentifierHelper.delete$default(context, presetIdentifierAsPresetIdentifier, false, 4, null);
    }

    static /* synthetic */ void selectPlayingPresetIfExists$default(CarModeBrushFragment carModeBrushFragment, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        carModeBrushFragment.selectPlayingPresetIfExists(z);
    }

    private final void selectPlayingPresetIfExists(boolean fromPlaybackCallback) {
        MutableLiveData<PageGridViewModel> gridPageModel;
        PageGridViewModel value;
        CarModeBrushViewModel carModeBrushViewModel = this.viewModel;
        List<BaseViewModel> models = null;
        if (carModeBrushViewModel != null && (gridPageModel = carModeBrushViewModel.getGridPageModel()) != null && (value = gridPageModel.getValue()) != null) {
            models = value.getModels();
        }
        if (models == null || models.isEmpty()) {
            Log.warning(this.TAG, "empty view model");
            return;
        }
        int i = 0;
        for (Object obj : this.adapterDataset) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
            }
            BaseViewModel baseViewModel = (BaseViewModel) obj;
            if ((baseViewModel instanceof SimpleHorizontalTileModel) && LibraryEqualizerStateManager.INSTANCE.getPlaybackState(((SimpleHorizontalTileModel) baseViewModel).getMetadata(), this.playbackController) != 0) {
                if (i == CarModeUtility.INSTANCE.getCurrentPlayingPresetNumber()) {
                    Log.debug(this.TAG, "The matching Preset at index " + i + " is being played");
                    return;
                }
                selectPreset(i, false);
                reportPresetMatchMetrics(true, fromPlaybackCallback);
                Log.debug(this.TAG, Intrinsics.stringPlus("Find a match with playing content at index ", Integer.valueOf(i)));
                return;
            }
            i = i2;
        }
        Log.debug(this.TAG, "Playing content is not a part of Presets");
        reportPresetMatchMetrics(false, fromPlaybackCallback);
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BrushLatencyReportingUtil.INSTANCE.trackLatency("carmode", LatencyTrackingLeg.INITIALIZING);
        this.isPodcastHarleyEnabled = AmazonApplication.getCapabilities().isPodcastHarleyEnabled();
        this.subscription = StyleSheetProvider.getStyleSheetObservable().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModeBrushFragment$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                CarModeBrushFragment.m263onCreate$lambda5(this.f$0, (StyleSheet) obj);
            }
        }, new Action1() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModeBrushFragment$$ExternalSyntheticLambda2
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                CarModeBrushFragment.m264onCreate$lambda6(this.f$0, (Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreate$lambda-5, reason: not valid java name */
    public static final void m263onCreate$lambda5(CarModeBrushFragment this$0, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setStyleSheet(styleSheet);
        this$0.setupBrushViews();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreate$lambda-6, reason: not valid java name */
    public static final void m264onCreate$lambda6(CarModeBrushFragment this$0, Throwable th) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.error(this$0.TAG, Intrinsics.stringPlus("Failed to fetch stylesheet with error ", th));
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        CarModeMiniPlayerView carModeMiniPlayerView;
        MutableLiveData<PageGridViewModel> gridPageModel;
        MutableLiveData<PageGridViewModel> gridPageModel2;
        super.onActivityCreated(savedInstanceState);
        PageType pageType = PageType.CAR_MODE_PRESET_LIST;
        AutoUiEventUtil.sendUiPageViewEvent(pageType, ExperienceMode.CAR_MODE);
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        CarModeMiniPlayerView carModeMiniPlayerView2 = this.carModeMiniPlayerView;
        if (carModeMiniPlayerView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("carModeMiniPlayerView");
            carModeMiniPlayerView = null;
        } else {
            carModeMiniPlayerView = carModeMiniPlayerView2;
        }
        AppCompatImageView appCompatImageView = this.backgroundImageView;
        if (appCompatImageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("backgroundImageView");
            appCompatImageView = null;
        }
        CarModeMiniPlayerViewController carModeMiniPlayerViewController = new CarModeMiniPlayerViewController(activity, carModeMiniPlayerView, this, pageType, getBitmapChangeListener(appCompatImageView), new PlayerNavigationListener() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModeBrushFragment$onActivityCreated$1$1
            @Override // com.amazon.mp3.playback.PlayerNavigationListener
            public void addTrackToLibrary() {
            }

            @Override // com.amazon.mp3.playback.PlayerNavigationListener
            public void onCreateContextMenu(ContextMenu menu) {
            }

            @Override // com.amazon.mp3.playback.PlayerNavigationListener
            public void showCastingView() {
            }

            @Override // com.amazon.mp3.playback.PlayerNavigationListener
            public void showPlayQueue() {
            }

            @Override // com.amazon.mp3.playback.PlayerNavigationListener
            public void showPodcastEpisodeList() {
            }

            @Override // com.amazon.mp3.playback.PlayerNavigationListener
            public void showNowPlaying(InteractionType type) {
                CarModeNavigation navigationListener = this.this$0.getNavigationListener();
                if (navigationListener == null) {
                    return;
                }
                CarModeNavigation.DefaultImpls.navigateTo$default(navigationListener, CarModeAction.SHOW_NOW_PLAYING_PAGE, null, 2, null);
            }
        });
        carModeMiniPlayerViewController.refreshPlayer();
        this.miniPlayerViewController = carModeMiniPlayerViewController;
        CarModeBrushViewModel carModeBrushViewModel = (CarModeBrushViewModel) new ViewModelProvider(activity).get(CarModeBrushViewModel.class);
        this.viewModel = carModeBrushViewModel;
        if (carModeBrushViewModel != null && (gridPageModel2 = carModeBrushViewModel.getGridPageModel()) != null) {
            gridPageModel2.observe(getViewLifecycleOwner(), new Observer() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModeBrushFragment$$ExternalSyntheticLambda3
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    CarModeBrushFragment.m262onActivityCreated$lambda9$lambda8(this.f$0, (PageGridViewModel) obj);
                }
            });
        }
        CarModeBrushViewModel carModeBrushViewModel2 = this.viewModel;
        if (((carModeBrushViewModel2 == null || (gridPageModel = carModeBrushViewModel2.getGridPageModel()) == null) ? null : gridPageModel.getValue()) == null) {
            CarModeBrushViewModel carModeBrushViewModel3 = this.viewModel;
            if (carModeBrushViewModel3 != null) {
                CarModeBrushViewModel.fetchCarModePage$default(carModeBrushViewModel3, null, this.pageErrorCallback, 1, null);
            }
            CarModeBrushViewModel carModeBrushViewModel4 = this.viewModel;
            if (carModeBrushViewModel4 == null) {
                return;
            }
            carModeBrushViewModel4.initEqualizer();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onActivityCreated$lambda-9$lambda-8, reason: not valid java name */
    public static final void m262onActivityCreated$lambda9$lambda8(CarModeBrushFragment this$0, PageGridViewModel pageGridViewModel) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BrushLatencyReportingUtil.INSTANCE.trackLatency("carmode", LatencyTrackingLeg.RENDERING);
        this$0.setupBrushViews();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showErrorDialog(Context context) {
        CarModeEmbeddedDialog carModeEmbeddedDialog = this.onErrorDialog;
        CarModeEmbeddedDialog carModeEmbeddedDialog2 = null;
        if (carModeEmbeddedDialog == null) {
            Intrinsics.throwUninitializedPropertyAccessException("onErrorDialog");
            carModeEmbeddedDialog = null;
        }
        String string = context.getString(R.string.dmusic_button_refresh);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.dmusic_button_refresh)");
        carModeEmbeddedDialog.setPositiveButton(string, new View.OnClickListener() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModeBrushFragment$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CarModeBrushFragment.m266showErrorDialog$lambda10(this.f$0, view);
            }
        });
        ProgressBar progressBar = this.loadingSpinner;
        if (progressBar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("loadingSpinner");
            progressBar = null;
        }
        progressBar.setVisibility(8);
        CarModeEmbeddedDialog carModeEmbeddedDialog3 = this.onErrorDialog;
        if (carModeEmbeddedDialog3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("onErrorDialog");
        } else {
            carModeEmbeddedDialog2 = carModeEmbeddedDialog3;
        }
        carModeEmbeddedDialog2.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showErrorDialog$lambda-10, reason: not valid java name */
    public static final void m266showErrorDialog$lambda10(CarModeBrushFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ProgressBar progressBar = this$0.loadingSpinner;
        if (progressBar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("loadingSpinner");
            progressBar = null;
        }
        progressBar.setVisibility(0);
        CarModeEmbeddedDialog carModeEmbeddedDialog = this$0.onErrorDialog;
        if (carModeEmbeddedDialog == null) {
            Intrinsics.throwUninitializedPropertyAccessException("onErrorDialog");
            carModeEmbeddedDialog = null;
        }
        carModeEmbeddedDialog.hide();
        CarModeBrushViewModel carModeBrushViewModel = this$0.viewModel;
        if (carModeBrushViewModel == null) {
            return;
        }
        CarModeBrushViewModel.fetchCarModePage$default(carModeBrushViewModel, null, this$0.pageErrorCallback, 1, null);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        OnBackPressedDispatcher onBackPressedDispatcher;
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        super.onCreateView(inflater, container, savedInstanceState);
        FragmentActivity activity = getActivity();
        if (activity != null && (onBackPressedDispatcher = activity.getOnBackPressedDispatcher()) != null) {
            onBackPressedDispatcher.addCallback(getViewLifecycleOwner(), new OnBackPressedCallback() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModeBrushFragment.onCreateView.1
                {
                    super(true);
                }

                @Override // androidx.activity.OnBackPressedCallback
                public void handleOnBackPressed() {
                    CarModeNavigation navigationListener;
                    if (CarModeBrushFragment.this.getContext() == null || (navigationListener = CarModeBrushFragment.this.getNavigationListener()) == null) {
                        return;
                    }
                    CarModeNavigation.DefaultImpls.navigateTo$default(navigationListener, CarModeAction.SHOW_EXIT_PAGE, null, 2, null);
                }
            });
        }
        View viewInflate = inflater.inflate(R.layout.car_mode_home, container, false);
        Intrinsics.checkNotNullExpressionValue(viewInflate, "inflater.inflate(R.layou…e_home, container, false)");
        setRoot(viewInflate);
        View viewFindViewById = getRoot().findViewById(R.id.loading_spinner);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "root.findViewById(R.id.loading_spinner)");
        ProgressBar progressBar = (ProgressBar) viewFindViewById;
        this.loadingSpinner = progressBar;
        CarModeEmbeddedDialog carModeEmbeddedDialog = null;
        if (progressBar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("loadingSpinner");
            progressBar = null;
        }
        progressBar.setVisibility(0);
        this.presetRecyclerView = (RecyclerView) getRoot().findViewById(R.id.car_mode_list);
        View viewFindViewById2 = getRoot().findViewById(R.id.car_mode_mini_player);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "root.findViewById(R.id.car_mode_mini_player)");
        this.carModeMiniPlayerView = (CarModeMiniPlayerView) viewFindViewById2;
        this.carModeCloseButton = (BaseButton) getRoot().findViewById(R.id.car_mode_close_button);
        View viewFindViewById3 = getRoot().findViewById(R.id.car_mode_blur_background);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "root.findViewById(R.id.car_mode_blur_background)");
        this.backgroundImageView = (AppCompatImageView) viewFindViewById3;
        this.alexaFloatingActionButton = (BaseButton) getRoot().findViewById(R.id.car_mode_alexa_button);
        View viewFindViewById4 = getRoot().findViewById(R.id.car_mode_on_error_dialog);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "root.findViewById(R.id.car_mode_on_error_dialog)");
        CarModeEmbeddedDialog carModeEmbeddedDialog2 = (CarModeEmbeddedDialog) viewFindViewById4;
        this.onErrorDialog = carModeEmbeddedDialog2;
        if (carModeEmbeddedDialog2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("onErrorDialog");
        } else {
            carModeEmbeddedDialog = carModeEmbeddedDialog2;
        }
        carModeEmbeddedDialog.hide();
        this.isBrushViewSetup = false;
        this.isViewStylized = false;
        return getRoot();
    }

    private final void createAndAssignDeleteSwipeHandler() {
        final Context contextRequireContext = requireContext();
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallback(contextRequireContext) { // from class: com.amazon.mp3.auto.carmode.fragment.CarModeBrushFragment$createAndAssignDeleteSwipeHandler$swipeHandler$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(contextRequireContext);
                Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
            }

            @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
                RecyclerView recyclerView = this.this$0.presetRecyclerView;
                RecyclerView.Adapter adapter = recyclerView == null ? null : recyclerView.getAdapter();
                UniversalAdapter universalAdapter = adapter instanceof UniversalAdapter ? (UniversalAdapter) adapter : null;
                if (universalAdapter == null) {
                    return;
                }
                CarModeBrushFragment carModeBrushFragment = this.this$0;
                Object obj = universalAdapter.getItems().get(viewHolder.getAdapterPosition());
                SimpleHorizontalTileModel simpleHorizontalTileModel = obj instanceof SimpleHorizontalTileModel ? (SimpleHorizontalTileModel) obj : null;
                if (simpleHorizontalTileModel == null) {
                    return;
                }
                carModeBrushFragment.updateDatasetOnDelete(simpleHorizontalTileModel);
                BrushViews brushViews = carModeBrushFragment.presetBrushView;
                if (brushViews != null) {
                    brushViews.removeItemsFromAdapter(CollectionsKt__CollectionsJVMKt.listOf(obj));
                }
                AutoUiEventUtil.sendUiClickEvent$default(ActionType.SELECT_CAR_MODE_DELETE_PRESET, PageType.CAR_MODE_PRESET_LIST, ExperienceMode.CAR_MODE, null, 8, null);
            }
        });
        this.itemTouchHelper = itemTouchHelper;
        itemTouchHelper.attachToRecyclerView(this.presetRecyclerView);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        StyleSheet styleSheet = getStyleSheet();
        if (styleSheet != null) {
            stylizeViews(styleSheet);
        }
        BaseButton baseButton = this.carModeCloseButton;
        if (baseButton != null) {
            baseButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModeBrushFragment$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    CarModeBrushFragment.m265onViewCreated$lambda12(this.f$0, view2);
                }
            });
        }
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
        this.alexaFabViewController = new CarModeAlexaFabViewController(contextRequireContext, this.alexaFloatingActionButton, PageType.CAR_MODE_PRESET_LIST);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onViewCreated$lambda-12, reason: not valid java name */
    public static final void m265onViewCreated$lambda12(CarModeBrushFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        CarModeNavigation navigationListener = this$0.getNavigationListener();
        if (navigationListener == null) {
            return;
        }
        CarModeNavigation.DefaultImpls.navigateTo$default(navigationListener, CarModeAction.SHOW_EXIT_PAGE, null, 2, null);
    }

    private final void reportPageLoadLatency(LatencyTrackingLeg leg) {
        CarModeBrushViewModel carModeBrushViewModel = this.viewModel;
        boolean z = false;
        if (carModeBrushViewModel != null && carModeBrushViewModel.getInitPageLoad()) {
            z = true;
        }
        if (z) {
            BrushLatencyReportingUtil.INSTANCE.trackLatency("carmode", leg);
        }
    }

    private final void reportPresetMatchMetrics(boolean match, boolean fromCallback) {
        String string = this.playbackController.getPlayback().getPlaybackConfig().getPlaybackInstanceId().toString();
        Intrinsics.checkNotNullExpressionValue(string, "playbackController.playb…backInstanceId.toString()");
        if (fromCallback) {
            AutoFlexEventUtil.emitCarModePresetMatchFlexEvent$default(AutoFlexEventUtil.INSTANCE, match, string, null, 4, null);
            return;
        }
        CarModeBrushViewModel carModeBrushViewModel = this.viewModel;
        boolean z = false;
        if (carModeBrushViewModel != null && carModeBrushViewModel.getInitPageLoad()) {
            z = true;
        }
        if (z) {
            AutoFlexEventUtil.INSTANCE.emitCarModePresetMatchFlexEvent(match, string, AutoFlexEventUtil.CarModeQueueMatchContext.Ingress);
        }
    }

    public final void setupBrushViews() {
        StyleSheet styleSheet = getStyleSheet();
        if (styleSheet == null) {
            return;
        }
        stylizeViews(styleSheet);
        if (this.isBrushViewSetup || !hasPresets()) {
            return;
        }
        this.isBrushViewSetup = true;
        this.contentViewManager = new ContentViewManager();
        PageType pageType = PageType.CAR_MODE_PRESET_LIST;
        CarModeMetadataClickListener carModeMetadataClickListener = new CarModeMetadataClickListener(this, pageType, PlaybackPageType.CAR_MODE_PRESET_LIST);
        this.metadataClickHandler = carModeMetadataClickListener;
        this.presetBrushView = BrowseViewsFactory.createBrowseViewsV2(this, styleSheet, pageType, carModeMetadataClickListener, new BrushUriClickListenerProvider() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModeBrushFragment$setupBrushViews$1$1
            @Override // com.amazon.music.views.library.providers.BrushUriClickListenerProvider
            public void onClick(String target, String viewId, TargetType targetType) {
            }
        }, null, this.contentViewManager, this);
        createBrushRootView();
    }

    private final void stylizeViews(StyleSheet styleSheet) {
        Drawable drawable;
        BaseButton.StyleBuilder styleBuilderWithIcon;
        if (this.isViewStylized) {
            return;
        }
        this.isViewStylized = true;
        BaseButton.StyleBuilder iconBuilder = styleSheet.getIconBuilder(IconSizeKey.LARGE, IconStyleKey.GLASS);
        BaseButton baseButton = this.carModeCloseButton;
        if (baseButton != null) {
            baseButton.setBackgroundResource(R.drawable.ic_car_mode_close_button);
        }
        BaseButton baseButton2 = this.carModeCloseButton;
        if (baseButton2 != null && iconBuilder != null) {
            iconBuilder.applyStyle(baseButton2);
        }
        BaseButton baseButton3 = this.alexaFloatingActionButton;
        if (baseButton3 != null && (drawable = ContextCompat.getDrawable(requireContext(), R.drawable.ic_alexa_bauhaus)) != null && iconBuilder != null && (styleBuilderWithIcon = iconBuilder.withIcon(drawable)) != null) {
            styleBuilderWithIcon.applyStyle(baseButton3);
        }
        CarModeMiniPlayerView carModeMiniPlayerView = this.carModeMiniPlayerView;
        CarModeEmbeddedDialog carModeEmbeddedDialog = null;
        if (carModeMiniPlayerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("carModeMiniPlayerView");
            carModeMiniPlayerView = null;
        }
        carModeMiniPlayerView.setStyleSheet(styleSheet);
        Context context = getContext();
        if (context == null) {
            return;
        }
        CarModeEmbeddedDialog carModeEmbeddedDialog2 = this.onErrorDialog;
        if (carModeEmbeddedDialog2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("onErrorDialog");
            carModeEmbeddedDialog2 = null;
        }
        String string = context.getString(R.string.dmusic_car_mode_time_out_error_title);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.stri…ode_time_out_error_title)");
        carModeEmbeddedDialog2.setTitle(string);
        CarModeEmbeddedDialog carModeEmbeddedDialog3 = this.onErrorDialog;
        if (carModeEmbeddedDialog3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("onErrorDialog");
            carModeEmbeddedDialog3 = null;
        }
        String string2 = context.getString(R.string.dmusic_car_mode_time_out_error_message);
        Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.stri…e_time_out_error_message)");
        carModeEmbeddedDialog3.setMessage(string2);
        CarModeEmbeddedDialog carModeEmbeddedDialog4 = this.onErrorDialog;
        if (carModeEmbeddedDialog4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("onErrorDialog");
        } else {
            carModeEmbeddedDialog = carModeEmbeddedDialog4;
        }
        carModeEmbeddedDialog.initStyling(styleSheet);
    }

    private final boolean hasPresets() {
        MutableLiveData<PageGridViewModel> gridPageModel;
        PageGridViewModel value;
        CarModeBrushViewModel carModeBrushViewModel = this.viewModel;
        List<BaseViewModel> models = null;
        if (carModeBrushViewModel != null && (gridPageModel = carModeBrushViewModel.getGridPageModel()) != null && (value = gridPageModel.getValue()) != null) {
            models = value.getModels();
        }
        return true ^ (models == null || models.isEmpty());
    }

    public final void createBrushRootView() {
        BrushViews brushViews;
        MutableLiveData<PageGridViewModel> gridPageModel;
        PageGridViewModel value;
        List<BaseViewModel> models;
        if (hasPresets()) {
            CarModeBrushViewModel carModeBrushViewModel = this.viewModel;
            if (carModeBrushViewModel != null && (gridPageModel = carModeBrushViewModel.getGridPageModel()) != null && (value = gridPageModel.getValue()) != null && (models = value.getModels()) != null) {
                this.adapterDataset = CollectionsKt___CollectionsKt.toMutableList((Collection) models);
            }
            Bundle arguments = getArguments();
            if (arguments != null) {
                storeDeeplinkArguments(arguments);
            }
            selectPlayingPresetIfExists$default(this, false, 1, null);
            addOrdinalsToModel();
            CarModeBrushViewModel carModeBrushViewModel2 = this.viewModel;
            if (carModeBrushViewModel2 != null) {
                carModeBrushViewModel2.updatePresetList(this.adapterDataset);
            }
            RecyclerView recyclerView = this.presetRecyclerView;
            if (recyclerView != null && (brushViews = this.presetBrushView) != null) {
                UserSubscription userSubscription = UserSubscriptionUtil.getUserSubscription();
                Intrinsics.checkNotNullExpressionValue(userSubscription, "getUserSubscription()");
                BrushViews.createRootView$default(brushViews, userSubscription, this.adapterDataset, 2, recyclerView, (ViewGroup) getRoot(), null, 32, null);
            }
            if (getContext() != null) {
                RecyclerView recyclerView2 = this.presetRecyclerView;
                if (recyclerView2 != null) {
                    recyclerView2.setItemAnimator(null);
                }
                RecyclerView recyclerView3 = this.presetRecyclerView;
                if (recyclerView3 != null) {
                    recyclerView3.setLayoutManager(new LinearLayoutManager(getContext()));
                }
            }
            CarModeMiniPlayerView carModeMiniPlayerView = this.carModeMiniPlayerView;
            if (carModeMiniPlayerView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("carModeMiniPlayerView");
                carModeMiniPlayerView = null;
            }
            carModeMiniPlayerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModeBrushFragment.createBrushRootView.5
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    CarModeMiniPlayerView carModeMiniPlayerView2 = CarModeBrushFragment.this.carModeMiniPlayerView;
                    CarModeMiniPlayerView carModeMiniPlayerView3 = null;
                    if (carModeMiniPlayerView2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("carModeMiniPlayerView");
                        carModeMiniPlayerView2 = null;
                    }
                    int measuredHeight = carModeMiniPlayerView2.getMeasuredHeight();
                    if (measuredHeight > 0) {
                        CarModeMiniPlayerView carModeMiniPlayerView4 = CarModeBrushFragment.this.carModeMiniPlayerView;
                        if (carModeMiniPlayerView4 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("carModeMiniPlayerView");
                        } else {
                            carModeMiniPlayerView3 = carModeMiniPlayerView4;
                        }
                        carModeMiniPlayerView3.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        RecyclerView recyclerView4 = CarModeBrushFragment.this.presetRecyclerView;
                        if (recyclerView4 == null) {
                            return;
                        }
                        recyclerView4.setPadding(recyclerView4.getPaddingLeft(), recyclerView4.getPaddingTop(), recyclerView4.getPaddingRight(), measuredHeight);
                    }
                }
            });
            createAndAssignDeleteSwipeHandler();
            if (LibraryEqualizerStateManager.INSTANCE.isPodcastContentPlaying()) {
                onPodcastMediaMetadataChange(CarModePresetsUtility.INSTANCE.getPodcastMediaMetadata());
            } else {
                onPlayStateChanged();
            }
            ProgressBar progressBar = this.loadingSpinner;
            if (progressBar == null) {
                Intrinsics.throwUninitializedPropertyAccessException("loadingSpinner");
                progressBar = null;
            }
            progressBar.setVisibility(8);
            reportPageLoadLatency(LatencyTrackingLeg.LOAD_COMPLETE);
            this.playbackController.addOnPlayStateChangedListener(this);
            CarModePresetsUtility.INSTANCE.addPodcastMediaMetadataCallback(this);
            Log.info(this.TAG, Intrinsics.stringPlus("Auto Play status:: ", Boolean.valueOf(this.isAutoPlayHandledOnIngress)));
            if (this.isAutoPlayHandledOnIngress) {
                return;
            }
            this.isAutoPlayHandledOnIngress = true;
            CarModeBrushViewModel carModeBrushViewModel3 = this.viewModel;
            if (carModeBrushViewModel3 == null) {
                return;
            }
            if (!carModeBrushViewModel3.isPlayQueueActive()) {
                if (AmazonApplication.getCapabilities().isCarModeAutoPlayFirstPresetDisabled()) {
                    PlaybackHandlerUtil.INSTANCE.emitAutoPlayFlexEvent(CarModeIngressAutoPlayTypes.EMPTY_PLAY_QUEUE_AUTOPLAY_DISABLED);
                    return;
                } else {
                    selectPreset$default(this, 0, false, 2, null);
                    PlaybackHandlerUtil.INSTANCE.emitAutoPlayFlexEvent(CarModeIngressAutoPlayTypes.EMPTY_PLAY_QUEUE_PLAY_FIRST_PRESET);
                    return;
                }
            }
            carModeBrushViewModel3.autoPlayIfMusicOrPodcastPaused(this.playbackController);
        }
    }

    @Override // com.amazon.music.views.library.recyclerview.OnItemClickListener
    public void onItemClick(View view, int position) {
        Intrinsics.checkNotNullParameter(view, "view");
        RecyclerView recyclerView = this.presetRecyclerView;
        boolean z = false;
        if (recyclerView != null && recyclerView.getScrollState() == 1) {
            z = true;
        }
        if (z) {
            return;
        }
        CarModeUtility.INSTANCE.setCurrentlyPlayingPresetNumber(position);
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        ContentViewManager contentViewManager = this.contentViewManager;
        if (contentViewManager != null) {
            contentViewManager.sendUiContentViewEvent(ExperienceMode.CAR_MODE.name());
        }
        super.onPause();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        CarModeMiniPlayerView carModeMiniPlayerView = this.carModeMiniPlayerView;
        if (carModeMiniPlayerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("carModeMiniPlayerView");
            carModeMiniPlayerView = null;
        }
        carModeMiniPlayerView.setVisibility(8);
        CarModeMiniPlayerViewController carModeMiniPlayerViewController = this.miniPlayerViewController;
        if (carModeMiniPlayerViewController != null) {
            carModeMiniPlayerViewController.clear();
        }
        this.miniPlayerViewController = null;
        AppCompatImageView appCompatImageView = this.backgroundImageView;
        if (appCompatImageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("backgroundImageView");
            appCompatImageView = null;
        }
        appCompatImageView.setVisibility(8);
        CarModeMetadataClickListener carModeMetadataClickListener = this.metadataClickHandler;
        if (carModeMetadataClickListener != null) {
            carModeMetadataClickListener.cleanup();
        }
        CarModeEmbeddedDialog carModeEmbeddedDialog = this.onErrorDialog;
        if (carModeEmbeddedDialog == null) {
            Intrinsics.throwUninitializedPropertyAccessException("onErrorDialog");
            carModeEmbeddedDialog = null;
        }
        carModeEmbeddedDialog.hide();
        CarModeAlexaFabViewController carModeAlexaFabViewController = this.alexaFabViewController;
        if (carModeAlexaFabViewController != null) {
            carModeAlexaFabViewController.setAlexaFabButtonVisibility(false);
        }
        CarModeUtility.INSTANCE.setCurrentlyPlayingPresetNumber(-1);
        this.playbackController.removeOnPlayStateChangedListener(this);
        CarModePresetsUtility.INSTANCE.removePodcastMediaMetadataCallback(this);
        CarModeBrushViewModel carModeBrushViewModel = this.viewModel;
        if (carModeBrushViewModel != null) {
            carModeBrushViewModel.setInitPageLoad(false);
        }
        this.lastMediaItem = null;
        super.onDestroyView();
    }

    @Override // com.amazon.mp3.auto.carmode.fragment.CarModeBaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        Subscription subscription = this.subscription;
        if (subscription != null) {
            subscription.unsubscribe();
        }
        this.pageErrorCallback = null;
        CarModeBrushViewModel carModeBrushViewModel = this.viewModel;
        if (carModeBrushViewModel != null) {
            carModeBrushViewModel.clear();
        }
        super.onDestroy();
    }

    private final void storeDeeplinkArguments(Bundle bundle) {
        Object obj = bundle.get("action");
        if (obj != null) {
            this.deeplinkArguments.put("action", obj);
        }
        Object obj2 = bundle.get("position");
        if (obj2 != null) {
            this.deeplinkArguments.put("position", obj2);
        }
        Object obj3 = bundle.get(Contexts.OpenSeeMore.uri);
        if (obj3 == null) {
            return;
        }
        this.deeplinkArguments.put(Contexts.OpenSeeMore.uri, obj3);
    }

    @Override // com.amazon.mp3.auto.carmode.CarModeAlexaActionsHandler
    public void handlePresetsPlaybackNext(Function2<? super ContextAwareDeeplinkManager.Companion.Status, ? super String, Unit> onComplete) {
        Intrinsics.checkNotNullParameter(onComplete, "onComplete");
        int currentPlayingPresetNumber = CarModeUtility.INSTANCE.getCurrentPlayingPresetNumber();
        Log.info(this.TAG, Intrinsics.stringPlus("Current preset number:::", Integer.valueOf(currentPlayingPresetNumber)));
        if (this.adapterDataset.size() <= 1 && currentPlayingPresetNumber == this.adapterDataset.size() - 1) {
            onComplete.mo4invoke(ContextAwareDeeplinkManager.Companion.Status.FAILURE, "Preset @next not available");
        } else {
            onComplete.mo4invoke(ContextAwareDeeplinkManager.Companion.Status.SUCCESS, null);
            selectPreset$default(this, currentPlayingPresetNumber + 1, false, 2, null);
        }
    }

    @Override // com.amazon.mp3.auto.carmode.CarModeAlexaActionsHandler
    public void handlePresetsPlaybackPrevious(Function2<? super ContextAwareDeeplinkManager.Companion.Status, ? super String, Unit> onComplete) {
        Intrinsics.checkNotNullParameter(onComplete, "onComplete");
        int currentPlayingPresetNumber = CarModeUtility.INSTANCE.getCurrentPlayingPresetNumber();
        Log.info(this.TAG, Intrinsics.stringPlus("Current preset number:::", Integer.valueOf(currentPlayingPresetNumber)));
        if (this.adapterDataset.size() <= 1 && currentPlayingPresetNumber <= 0) {
            onComplete.mo4invoke(ContextAwareDeeplinkManager.Companion.Status.FAILURE, "Preset @previous not available");
        } else {
            onComplete.mo4invoke(ContextAwareDeeplinkManager.Companion.Status.SUCCESS, null);
            selectPreset$default(this, currentPlayingPresetNumber - 1, false, 2, null);
        }
    }

    @Override // com.amazon.mp3.auto.carmode.CarModeAlexaActionsHandler
    public void handlePresetPlayback(int presetNumber, Function2<? super ContextAwareDeeplinkManager.Companion.Status, ? super String, Unit> onComplete) {
        Intrinsics.checkNotNullParameter(onComplete, "onComplete");
        Logger logger = LOG;
        logger.debug(Intrinsics.stringPlus("Handling deeplink for PLAY preset by Number - ", Integer.valueOf(presetNumber)));
        if (presetNumber > this.adapterDataset.size()) {
            logger.error(Intrinsics.stringPlus("Cannot play reset number ", Integer.valueOf(presetNumber)));
            onComplete.mo4invoke(ContextAwareDeeplinkManager.Companion.Status.FAILURE, "Preset @" + presetNumber + " not available");
            return;
        }
        onComplete.mo4invoke(ContextAwareDeeplinkManager.Companion.Status.SUCCESS, null);
        selectPreset$default(this, presetNumber - 1, false, 2, null);
    }

    static /* synthetic */ void selectPreset$default(CarModeBrushFragment carModeBrushFragment, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = true;
        }
        carModeBrushFragment.selectPreset(i, z);
    }

    private final void selectPreset(int presetNumber, boolean performClick) {
        LOG.debug(Intrinsics.stringPlus("will play Preset at position ", Integer.valueOf(presetNumber)));
        CarModeUtility.INSTANCE.setCurrentlyPlayingPresetNumber(presetNumber);
        Context context = getContext();
        if (context == null) {
            return;
        }
        ContextKt.runOnUiThread$default(context, 0L, new C02981(presetNumber, performClick), 1, null);
    }

    /* JADX INFO: renamed from: com.amazon.mp3.auto.carmode.fragment.CarModeBrushFragment$selectPreset$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CarModeBrushFragment.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Landroid/content/Context;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
    static final class C02981 extends Lambda implements Function1<Context, Unit> {
        final /* synthetic */ boolean $performClick;
        final /* synthetic */ int $presetNumber;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02981(int i, boolean z) {
            super(1);
            this.$presetNumber = i;
            this.$performClick = z;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Context context) {
            invoke2(context);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(Context it) {
            RecyclerView recyclerView;
            RecyclerView.LayoutManager layoutManager;
            Intrinsics.checkNotNullParameter(it, "it");
            RecyclerView recyclerView2 = CarModeBrushFragment.this.presetRecyclerView;
            if (recyclerView2 != null && (layoutManager = recyclerView2.getLayoutManager()) != null) {
                layoutManager.scrollToPosition(this.$presetNumber);
            }
            if (!this.$performClick || (recyclerView = CarModeBrushFragment.this.presetRecyclerView) == null) {
                return;
            }
            final CarModeBrushFragment carModeBrushFragment = CarModeBrushFragment.this;
            final int i = this.$presetNumber;
            recyclerView.post(new Runnable() { // from class: com.amazon.mp3.auto.carmode.fragment.CarModeBrushFragment$selectPreset$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    CarModeBrushFragment.C02981.m268invoke$lambda0(carModeBrushFragment, i);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: invoke$lambda-0, reason: not valid java name */
        public static final void m268invoke$lambda0(CarModeBrushFragment this$0, int i) {
            RecyclerView.ViewHolder viewHolderFindViewHolderForAdapterPosition;
            View view;
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            RecyclerView recyclerView = this$0.presetRecyclerView;
            if (recyclerView == null || (viewHolderFindViewHolderForAdapterPosition = recyclerView.findViewHolderForAdapterPosition(i)) == null || (view = viewHolderFindViewHolderForAdapterPosition.itemView) == null) {
                return;
            }
            view.performClick();
        }
    }

    @Override // com.amazon.mp3.auto.carmode.CarModeAlexaActionsHandler
    public void handleExitCarMode(Function2<? super ContextAwareDeeplinkManager.Companion.Status, ? super String, Unit> onComplete) {
        Intrinsics.checkNotNullParameter(onComplete, "onComplete");
        onComplete.mo4invoke(ContextAwareDeeplinkManager.Companion.Status.SUCCESS, null);
        if (getContext() == null) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putBoolean("carModeUserExit", true);
        CarModeNavigation navigationListener = getNavigationListener();
        if (navigationListener == null) {
            return;
        }
        navigationListener.navigateTo(CarModeAction.EXIT_CAR_MODE, bundle);
    }

    @Override // com.amazon.podcast.media.playback.Media.PodcastMediaMetadataCallback
    public void onPodcastMediaMetadataChange(PodcastMediaMetadata metadata) {
        if (metadata != null) {
            if (!this.isPodcastHarleyEnabled) {
                CarModeMiniPlayerViewController carModeMiniPlayerViewController = this.miniPlayerViewController;
                if (carModeMiniPlayerViewController != null) {
                    CarModePlayerViewController.refreshPlayerView$default(carModeMiniPlayerViewController, null, true, 1, null);
                }
                CarModeMiniPlayerViewController carModeMiniPlayerViewController2 = this.miniPlayerViewController;
                if (carModeMiniPlayerViewController2 != null) {
                    carModeMiniPlayerViewController2.updatePlayerView();
                }
            }
            selectPlayingPresetIfExists(true);
        }
    }

    @Override // com.amazon.music.media.playback.OnPlayStateChangedListener
    public void onPlayStateChanged() {
        MediaItem currentMediaItem = this.playbackController.getCurrentMediaItem();
        if (currentMediaItem == null || (currentMediaItem instanceof PodcastMediaItem) || Intrinsics.areEqual(currentMediaItem, this.lastMediaItem)) {
            return;
        }
        this.lastMediaItem = currentMediaItem;
        if (!this.isPodcastHarleyEnabled) {
            CarModeMiniPlayerViewController carModeMiniPlayerViewController = this.miniPlayerViewController;
            if (carModeMiniPlayerViewController != null) {
                CarModePlayerViewController.refreshPlayerView$default(carModeMiniPlayerViewController, currentMediaItem, false, 2, null);
            }
            CarModeMiniPlayerViewController carModeMiniPlayerViewController2 = this.miniPlayerViewController;
            if (carModeMiniPlayerViewController2 != null) {
                carModeMiniPlayerViewController2.refreshPlayer();
            }
        }
        if (currentMediaItem.isVoiceInitiated()) {
            selectPlayingPresetIfExists(true);
        }
    }
}
