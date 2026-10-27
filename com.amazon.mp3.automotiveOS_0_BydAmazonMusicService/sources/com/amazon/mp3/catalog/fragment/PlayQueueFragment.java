package com.amazon.mp3.catalog.fragment;

import android.app.AlertDialog;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.catalog.fragment.adapter.BaseViewsBinderConfiguration;
import com.amazon.mp3.catalog.fragment.adapter.BaseViewsBinderProvider;
import com.amazon.mp3.catalog.fragment.adapter.BaseViewsDiffUtil;
import com.amazon.mp3.catalog.fragment.adapter.BaseViewsDiffUtilFactory;
import com.amazon.mp3.catalog.fragment.datasource.repository.EditablePageDataRepository;
import com.amazon.mp3.catalog.fragment.datasource.repository.PlayQueueDataRepository;
import com.amazon.mp3.catalog.fragment.provider.LibraryContentEnabilityProvider;
import com.amazon.mp3.catalog.fragment.viewmodel.EditableViewModelProvider;
import com.amazon.mp3.catalog.fragment.viewmodel.PageViewModelFactoryProvider;
import com.amazon.mp3.catalog.fragment.viewmodel.PageViewModelProvider;
import com.amazon.mp3.catalog.fragment.viewmodel.PlayQueueViewModel;
import com.amazon.mp3.cloudqueue.sequencer.CloudQueueSequencer;
import com.amazon.mp3.fragment.contextmenu.AbstractTrackContextMenuListener;
import com.amazon.mp3.playback.PlayStateMutationReason;
import com.amazon.mp3.playback.activity.NowPlayingFragmentActivity;
import com.amazon.mp3.playback.activity.NowPlayingUtil;
import com.amazon.mp3.playback.view.MiniPlayerView;
import com.amazon.mp3.playback.view.MiniPlayerViewController;
import com.amazon.mp3.playback.view.MiniPlayerViewWithProgressBar;
import com.amazon.mp3.playback.view.NowPlayingMiniPlayerViewController;
import com.amazon.mp3.providers.DefaultLikesProvider;
import com.amazon.mp3.providers.LibraryContextMenuProvider;
import com.amazon.mp3.providers.MetadataContextMenuHandler;
import com.amazon.mp3.providers.MissingSDCardHandler;
import com.amazon.mp3.providers.PlayQueueClickListener;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.mp3.util.AccessibilityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.latency.LatencyEventDescriptor;
import com.amazon.music.metrics.mts.event.definition.latency.LatencyTracker;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.LatencyTrackingLeg;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.PageLoadLatencyCode;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.binders.UniversalBinder;
import com.amazon.music.views.library.deeplinks.ContentMetadataOnClickListener;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.providers.ContentEnabilityProvider;
import com.amazon.music.views.library.providers.RowButtonOnClickProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.ui.foundations.styles.ButtonStyle;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.styles.GridSize;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.views.BaseButton;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlayQueueFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000ò\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0002\u0012\u0015\u0018\u0000 j2\u00020\u0001:\u0001jB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010!\u001a\u00020\"H\u0002J\n\u0010#\u001a\u0004\u0018\u00010$H\u0002J\b\u0010%\u001a\u00020\"H\u0016J\u0016\u0010&\u001a\u0010\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030(0'H\u0016J\n\u0010)\u001a\u0004\u0018\u00010*H\u0014J\n\u0010+\u001a\u0004\u0018\u00010,H\u0016J\b\u0010-\u001a\u00020.H\u0016J\u001a\u0010/\u001a\u0002002\u0006\u00101\u001a\u0002022\b\u00103\u001a\u0004\u0018\u000100H\u0014J\n\u00104\u001a\u0004\u0018\u000105H\u0014J\n\u00106\u001a\u0004\u0018\u000107H\u0016J\u0018\u00108\u001a\u00020\"2\u0006\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020:H\u0014J\n\u0010<\u001a\u0004\u0018\u00010=H\u0016J\b\u0010>\u001a\u00020?H\u0016J\u0012\u0010@\u001a\u0004\u0018\u00010A2\u0006\u0010B\u001a\u00020CH\u0014J\b\u0010D\u001a\u00020\u0004H\u0014J\b\u0010E\u001a\u00020\"H\u0002J\u0010\u0010F\u001a\u00020\"2\u0006\u0010G\u001a\u00020HH\u0002J\u0010\u0010I\u001a\u00020\"2\u0006\u0010G\u001a\u00020HH\u0002J\b\u0010J\u001a\u00020\"H\u0002J\u0010\u0010K\u001a\u00020\"2\u0006\u0010B\u001a\u00020CH\u0016J\u0010\u0010L\u001a\u00020\"2\u0006\u0010G\u001a\u00020HH\u0002J\u0010\u0010M\u001a\u00020\"2\u0006\u0010N\u001a\u00020OH\u0016J\u0010\u0010P\u001a\u00020\u000e2\u0006\u0010Q\u001a\u00020RH\u0016J\u0012\u0010S\u001a\u00020\"2\b\u0010T\u001a\u0004\u0018\u00010UH\u0016J&\u0010V\u001a\u0004\u0018\u00010\u001a2\u0006\u00101\u001a\u0002022\b\u00103\u001a\u0004\u0018\u0001002\b\u0010T\u001a\u0004\u0018\u00010UH\u0016J\b\u0010W\u001a\u00020\"H\u0016J\b\u0010X\u001a\u00020\"H\u0016J\b\u0010Y\u001a\u00020\"H\u0014J\b\u0010Z\u001a\u00020\"H\u0016J\u001a\u0010[\u001a\u00020\"2\u0006\u0010\\\u001a\u00020\u001a2\b\u0010T\u001a\u0004\u0018\u00010UH\u0016J\b\u0010]\u001a\u00020\"H\u0002J\b\u0010^\u001a\u00020\"H\u0002J\b\u0010_\u001a\u00020\u000eH\u0014J\u0010\u0010`\u001a\u00020\"2\u0006\u0010a\u001a\u00020bH\u0002J\b\u0010c\u001a\u00020\"H\u0014J\b\u0010d\u001a\u00020\"H\u0014J\b\u0010e\u001a\u00020\"H\u0014J\u0010\u0010f\u001a\u00020\"2\u0006\u0010g\u001a\u00020hH\u0002J\b\u0010i\u001a\u00020\"H\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\u00020\u000e8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u000e8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u000fR\u0010\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0013R\u0010\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0016R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006k"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/PlayQueueFragment;", "Lcom/amazon/mp3/catalog/fragment/EditableBaseViewsFragment;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "clearLayout", "Landroid/widget/FrameLayout;", "clearQueueButton", "Lcom/amazon/ui/foundations/views/BaseButton;", "clearTextView", "Landroid/widget/TextView;", "exitButton", "isLyricsSharingEnabled", "", "()Z", "isStage", "mFinishBroadcastReceiver", "com/amazon/mp3/catalog/fragment/PlayQueueFragment$mFinishBroadcastReceiver$1", "Lcom/amazon/mp3/catalog/fragment/PlayQueueFragment$mFinishBroadcastReceiver$1;", "mVideoTracksReplacedRemoved", "com/amazon/mp3/catalog/fragment/PlayQueueFragment$mVideoTracksReplacedRemoved$1", "Lcom/amazon/mp3/catalog/fragment/PlayQueueFragment$mVideoTracksReplacedRemoved$1;", "miniPlayerWithProgressBar", "Lcom/amazon/mp3/playback/view/MiniPlayerViewWithProgressBar;", "playQueueContainer", "Landroid/view/View;", "playQueueScreen", "Landroidx/constraintlayout/widget/ConstraintLayout;", "playQueueViewModel", "Lcom/amazon/mp3/catalog/fragment/viewmodel/PlayQueueViewModel;", "playerController", "Lcom/amazon/mp3/playback/view/MiniPlayerViewController;", "clearNowPlayingQueue", "", "createClearDialog", "Landroid/app/AlertDialog;", "emitUiPageViewMetric", "getBinderList", "", "Lcom/amazon/music/views/library/binders/UniversalBinder;", "getContentEnabilityProvider", "Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;", "getContextMenuProvider", "Lcom/amazon/mp3/providers/MetadataContextMenuHandler;", "getDiffUtilFactory", "Lcom/amazon/mp3/catalog/fragment/adapter/BaseViewsDiffUtilFactory;", "getInflatedRootView", "Landroid/view/ViewGroup;", "inflater", "Landroid/view/LayoutInflater;", "container", "getMetadataClickListener", "Lcom/amazon/music/views/library/deeplinks/ContentMetadataOnClickListener;", "getMissingSDCardProvider", "Lcom/amazon/mp3/providers/MissingSDCardHandler;", "getMoreData", "pageIndex", "", "pageSize", "getPageDataRepository", "Lcom/amazon/mp3/catalog/fragment/datasource/repository/EditablePageDataRepository;", "getPageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "getRowButtonOnClickProvider", "Lcom/amazon/music/views/library/providers/RowButtonOnClickProvider;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "getSourceId", "hideActionBar", "initClearQueueButton", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "initExitButton", "initMiniPlayer", "initPageModelProvider", "initStyling", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "onContextItemSelected", "menuItem", "Landroid/view/MenuItem;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "onDestroy", "onDestroyView", "onLoadMorePage", "onResume", "onViewCreated", "view", "recordUIPageMetrics", "registerReceivers", "requireNetworkConnection", "sendUIClickMetrics", "actionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "showEmptyView", "showLoadingView", "showRecyclerView", "unregisterReceiver", "receiver", "Landroid/content/BroadcastReceiver;", "unregisterReceivers", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlayQueueFragment extends EditableBaseViewsFragment {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private FrameLayout clearLayout;
    private BaseButton clearQueueButton;
    private TextView clearTextView;
    private BaseButton exitButton;
    private MiniPlayerViewWithProgressBar miniPlayerWithProgressBar;
    private View playQueueContainer;
    private ConstraintLayout playQueueScreen;
    private PlayQueueViewModel playQueueViewModel;
    private MiniPlayerViewController playerController;
    private final String TAG = PlayQueueFragment.class.getSimpleName();
    private final PlayQueueFragment$mVideoTracksReplacedRemoved$1 mVideoTracksReplacedRemoved = new BroadcastReceiver() { // from class: com.amazon.mp3.catalog.fragment.PlayQueueFragment$mVideoTracksReplacedRemoved$1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(intent, "intent");
            EditableViewModelProvider pageModelProvider = this.this$0.getPageModelProvider();
            if (pageModelProvider == null) {
                return;
            }
            pageModelProvider.fetchInitPage(Integer.MAX_VALUE);
        }
    };
    private final PlayQueueFragment$mFinishBroadcastReceiver$1 mFinishBroadcastReceiver = new BroadcastReceiver() { // from class: com.amazon.mp3.catalog.fragment.PlayQueueFragment$mFinishBroadcastReceiver$1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(intent, "intent");
            String action = intent.getAction();
            if (Intrinsics.areEqual("com.amazon.mp3.library.activity.nowplaying.clear_and_finish", action) || Intrinsics.areEqual("com.amazon.mp3.library.activity.nowplaying.finish", action)) {
                this.this$0.clearNowPlayingQueue();
            }
        }
    };

    @JvmStatic
    public static final Intent getStartIntent(Context context) {
        return INSTANCE.getStartIntent(context);
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public void emitUiPageViewMetric() {
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public MissingSDCardHandler getMissingSDCardProvider() {
        return null;
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected String getSourceId() {
        return "nowplaying";
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected boolean requireNetworkConnection() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isStage() {
        return AmazonApplication.getCapabilities().isYAImmerseEnabled();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isLyricsSharingEnabled() {
        return AmazonApplication.getCapabilities().isLyricsSharingEnabled();
    }

    /* JADX INFO: compiled from: PlayQueueFragment.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/PlayQueueFragment$Companion;", "", "()V", "ACTION_REFRESH_NOW_PLAYING_QUEUE_SCREEN", "", "getStartIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final Intent getStartIntent(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intent intent = new Intent();
            intent.setClass(context, NowPlayingFragmentActivity.class);
            intent.putExtra("com.amazon.mp3.fragment.extra", PlayQueueFragment.class.getSimpleName());
            intent.setFlags(536870912);
            return intent;
        }
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LatencyTracker.INSTANCE.track(new LatencyEventDescriptor(LatencyTrackingLeg.PROCESSING, PageLoadLatencyCode.PLAY_QUEUE));
        registerReceivers();
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        RecyclerView.Adapter adapter;
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        super.onConfigurationChanged(newConfig);
        RecyclerView recyclerView = getRecyclerView();
        if (recyclerView == null || (adapter = recyclerView.getAdapter()) == null) {
            return;
        }
        adapter.notifyDataSetChanged();
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        LatencyTracker.INSTANCE.track(new LatencyEventDescriptor(LatencyTrackingLeg.RENDERING, PageLoadLatencyCode.PLAY_QUEUE));
        View viewOnCreateView = super.onCreateView(inflater, container, savedInstanceState);
        FrameLayout frameLayout = viewOnCreateView == null ? null : (FrameLayout) viewOnCreateView.findViewById(R.id.clear_frame);
        this.clearLayout = frameLayout;
        if (frameLayout != null) {
            frameLayout.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.catalog.fragment.PlayQueueFragment$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PlayQueueFragment.m527onCreateView$lambda0(this.f$0, view);
                }
            });
        }
        this.clearTextView = viewOnCreateView == null ? null : (TextView) viewOnCreateView.findViewById(R.id.clear_text);
        this.miniPlayerWithProgressBar = viewOnCreateView == null ? null : (MiniPlayerViewWithProgressBar) viewOnCreateView.findViewById(R.id.mini_player_with_progress_bar);
        this.playQueueContainer = viewOnCreateView == null ? null : viewOnCreateView.findViewById(R.id.play_queue_container);
        this.playQueueScreen = viewOnCreateView == null ? null : (ConstraintLayout) viewOnCreateView.findViewById(R.id.PlayQueueScreen);
        this.exitButton = viewOnCreateView == null ? null : (BaseButton) viewOnCreateView.findViewById(R.id.ExitPlayQueueScreenButton);
        this.clearQueueButton = viewOnCreateView != null ? (BaseButton) viewOnCreateView.findViewById(R.id.ClearQueueButton) : null;
        StyleSheetProvider.getStyleSheet().observe(getViewLifecycleOwner(), new Observer() { // from class: com.amazon.mp3.catalog.fragment.PlayQueueFragment$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                PlayQueueFragment.m528onCreateView$lambda1(this.f$0, (StyleSheet) obj);
            }
        });
        return viewOnCreateView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreateView$lambda-0, reason: not valid java name */
    public static final void m527onCreateView$lambda0(PlayQueueFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AlertDialog alertDialogCreateClearDialog = this$0.createClearDialog();
        if (alertDialogCreateClearDialog != null) {
            alertDialogCreateClearDialog.show();
        }
        this$0.sendUIClickMetrics(ActionType.DELETE_PLAY_QUEUE_ATTEMPT);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreateView$lambda-1, reason: not valid java name */
    public static final void m528onCreateView$lambda1(PlayQueueFragment this$0, StyleSheet it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        this$0.initStyling(it);
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment, androidx.fragment.app.Fragment
    public void onResume() {
        FragmentManager supportFragmentManager;
        super.onResume();
        if (NowPlayingUtil.isAlexaPlaying()) {
            FragmentActivity activity = getActivity();
            if (activity == null || (supportFragmentManager = activity.getSupportFragmentManager()) == null) {
                return;
            }
            supportFragmentManager.popBackStack();
            return;
        }
        recordUIPageMetrics();
        hideActionBar();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initMiniPlayer();
    }

    private final void initExitButton(StyleSheet styleSheet) {
        Drawable drawable;
        BaseButton baseButton;
        BaseButton.StyleBuilder styleBuilderWithIcon;
        BaseButton baseButton2 = this.exitButton;
        if (baseButton2 != null) {
            baseButton2.setVisibility(0);
        }
        BaseButton.StyleBuilder iconBuilder = styleSheet.getIconBuilder(IconSizeKey.SMALL, IconStyleKey.GLASS);
        Context context = getContext();
        if (context == null || (drawable = ContextCompat.getDrawable(context, R.drawable.ic_action_cancel)) == null || (baseButton = this.exitButton) == null) {
            return;
        }
        if (iconBuilder != null && (styleBuilderWithIcon = iconBuilder.withIcon(drawable)) != null) {
            styleBuilderWithIcon.applyStyle(baseButton);
        }
        baseButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.catalog.fragment.PlayQueueFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PlayQueueFragment.m526initExitButton$lambda5$lambda4$lambda3$lambda2(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initExitButton$lambda-5$lambda-4$lambda-3$lambda-2, reason: not valid java name */
    public static final void m526initExitButton$lambda5$lambda4$lambda3$lambda2(PlayQueueFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentActivity activity = this$0.getActivity();
        if (activity == null) {
            return;
        }
        activity.onBackPressed();
    }

    private final void initClearQueueButton(StyleSheet styleSheet) {
        BaseButton baseButton = this.clearQueueButton;
        if (baseButton != null) {
            baseButton.setVisibility(0);
        }
        FrameLayout frameLayout = this.clearLayout;
        if (frameLayout != null) {
            frameLayout.setVisibility(8);
        }
        BaseButton.StyleBuilder buttonBuilder = styleSheet.getButtonBuilder(ButtonSizeKey.SMALL, ButtonStyleKey.GLASS);
        ButtonStyle iconStyle = styleSheet.getIconStyle(IconStyleKey.SECONDARY_GLASS);
        if (buttonBuilder != null) {
            String string = getResources().getString(R.string.dmusic_now_playing_queue_clear_playlist);
            Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.st…ing_queue_clear_playlist)");
            buttonBuilder.withText(string);
            if (iconStyle != null) {
                buttonBuilder.withActiveStyle(iconStyle);
            }
            BaseButton baseButton2 = this.clearQueueButton;
            if (baseButton2 != null) {
                buttonBuilder.applyStyle(baseButton2);
            }
        }
        BaseButton baseButton3 = this.clearQueueButton;
        if (baseButton3 == null) {
            return;
        }
        baseButton3.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.catalog.fragment.PlayQueueFragment$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PlayQueueFragment.m525initClearQueueButton$lambda9(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initClearQueueButton$lambda-9, reason: not valid java name */
    public static final void m525initClearQueueButton$lambda9(PlayQueueFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AlertDialog alertDialogCreateClearDialog = this$0.createClearDialog();
        if (alertDialogCreateClearDialog != null) {
            alertDialogCreateClearDialog.show();
        }
        this$0.sendUIClickMetrics(ActionType.DELETE_PLAY_QUEUE_ATTEMPT);
    }

    private final void initMiniPlayer() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            MiniPlayerViewWithProgressBar miniPlayerViewWithProgressBar = this.miniPlayerWithProgressBar;
            MiniPlayerView miniPlayerView = miniPlayerViewWithProgressBar == null ? null : miniPlayerViewWithProgressBar.getMiniPlayerView();
            if (miniPlayerView != null) {
                miniPlayerView.setMinimizeViewVisibility(0);
            }
            NowPlayingMiniPlayerViewController nowPlayingMiniPlayerViewController = new NowPlayingMiniPlayerViewController(activity, miniPlayerView, this, MiniPlayerViewController.MiniPLayerSource.PLAY_QUEUE);
            this.playerController = nowPlayingMiniPlayerViewController;
            nowPlayingMiniPlayerViewController.refreshPlayer();
        }
        if (isStage() && isLyricsSharingEnabled()) {
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(this.playQueueScreen);
            MiniPlayerViewWithProgressBar miniPlayerViewWithProgressBar2 = this.miniPlayerWithProgressBar;
            if (miniPlayerViewWithProgressBar2 != null) {
                int id = miniPlayerViewWithProgressBar2.getId();
                constraintSet.connect(id, 4, 0, 4);
                constraintSet.clear(id, 3);
            }
            BaseButton baseButton = this.exitButton;
            if (baseButton != null) {
                int id2 = baseButton.getId();
                View view = this.playQueueContainer;
                if (view != null) {
                    int id3 = view.getId();
                    BaseButton baseButton2 = this.clearQueueButton;
                    if (baseButton2 != null) {
                        constraintSet.connect(id3, 4, baseButton2.getId(), 3);
                    }
                    constraintSet.connect(id3, 3, id2, 4);
                }
            }
            constraintSet.applyTo(this.playQueueScreen);
        }
    }

    private final void hideActionBar() {
        FragmentActivity activity = getActivity();
        NowPlayingFragmentActivity nowPlayingFragmentActivity = activity instanceof NowPlayingFragmentActivity ? (NowPlayingFragmentActivity) activity : null;
        if (nowPlayingFragmentActivity == null) {
            return;
        }
        nowPlayingFragmentActivity.hideActionBar();
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        unregisterReceivers();
        super.onDestroy();
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected void onLoadMorePage() {
        super.onLoadUpdatedPage();
    }

    private final void initStyling(StyleSheet styleSheet) {
        FontStyle fontStyle;
        FontStyle fontStyleCopy$default;
        TextView textView = this.clearTextView;
        if (textView != null && (fontStyle = styleSheet.getFontStyle(FontStyleKey.PRIMARY)) != null && (fontStyleCopy$default = FontStyle.copy$default(fontStyle, null, null, 0, Boolean.TRUE, 7, null)) != null) {
            FontUtil.INSTANCE.applyFontStyle(textView, fontStyleCopy$default);
        }
        GridSize gridSize = styleSheet.getGridSize();
        if (gridSize != null) {
            int marginSpace = gridSize.getMarginSpace() + gridSize.getColumnSpace();
            View view = this.playQueueContainer;
            if (view != null) {
                view.setPadding(marginSpace, view.getPaddingTop(), marginSpace, view.getPaddingBottom());
            }
        }
        if (isLyricsSharingEnabled() && isStage()) {
            initExitButton(styleSheet);
            initClearQueueButton(styleSheet);
        }
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected ViewGroup getInflatedRootView(LayoutInflater inflater, ViewGroup container) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View viewInflate = inflater.inflate(R.layout.play_queue_layout, container, false);
        if (viewInflate != null) {
            return (ViewGroup) viewInflate;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup");
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public void initPageModelProvider(FragmentActivity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        EditablePageDataRepository pageDataRepository = getPageDataRepository();
        if (pageDataRepository == null) {
            return;
        }
        PageViewModelFactoryProvider pageViewModelFactoryProvider = PageViewModelFactoryProvider.INSTANCE;
        Application application = activity.getApplication();
        Intrinsics.checkNotNullExpressionValue(application, "activity.application");
        PageViewModelProvider viewModel = pageViewModelFactoryProvider.getViewModel(this, new PageViewModelFactoryProvider.PlayQueueViewModelFactory(application, pageDataRepository, false, 4, null));
        setPageModelProvider(viewModel instanceof EditableViewModelProvider ? (EditableViewModelProvider) viewModel : null);
        EditableViewModelProvider pageModelProvider = getPageModelProvider();
        this.playQueueViewModel = pageModelProvider instanceof PlayQueueViewModel ? (PlayQueueViewModel) pageModelProvider : null;
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public EditablePageDataRepository getPageDataRepository() {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return null;
        }
        return new PlayQueueDataRepository(activity, CloudQueueSequencer.INSTANCE.isCloudQueuePlaying(), false, 4, null);
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected RowButtonOnClickProvider getRowButtonOnClickProvider(FragmentActivity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (AmazonApplication.getCapabilities().isLikesEverywhereEnabled()) {
            if (getRowButtonOnClickProvider() == null) {
                setRowButtonOnClickProvider(new DefaultLikesProvider(activity, getLibraryStateProviderInstance(), null, true, getPageType()));
            }
            return getRowButtonOnClickProvider();
        }
        return super.getRowButtonOnClickProvider(activity);
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public List<UniversalBinder<?, ?>> getBinderList() {
        BaseViewsBinderConfiguration viewsConfiguration = getViewsConfiguration();
        if (viewsConfiguration != null) {
            return CollectionsKt__CollectionsJVMKt.listOf(BaseViewsBinderProvider.INSTANCE.getPlayQueueItemBinder(viewsConfiguration));
        }
        return CollectionsKt__CollectionsKt.emptyList();
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public MetadataContextMenuHandler getContextMenuProvider() {
        final FragmentActivity activity = getActivity();
        if (activity == null) {
            return null;
        }
        if (getContextMenuHandler() == null) {
            setContextMenuHandler(new LibraryContextMenuProvider(activity, this, PageType.UNKNOWN, new AbstractTrackContextMenuListener(this) { // from class: com.amazon.mp3.catalog.fragment.PlayQueueFragment$getContextMenuProvider$1$1
                final /* synthetic */ PlayQueueFragment this$0;

                @Override // com.amazon.mp3.fragment.contextmenu.AbstractTrackContextMenuListener
                protected void removeItemRemoveFromPlayQueue(Menu menu) {
                    Intrinsics.checkNotNullParameter(menu, "menu");
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(this.$it, null);
                    this.this$0 = this;
                    Intrinsics.checkNotNullExpressionValue(this.$it, "it");
                }

                @Override // com.amazon.mp3.fragment.contextmenu.AbstractTrackContextMenuListener
                protected void removeFromPlayQueue(int trackPos, boolean isShuffled) {
                    this.this$0.deleteModel(trackPos);
                    String string = this.this$0.getString(R.string.song_removed_content_description);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.song_…oved_content_description)");
                    if (AccessibilityUtil.isAccessibilityEnabled(this.this$0.getContext())) {
                        AccessibilityUtil.announceForAccessibility(this.this$0.getContext(), string);
                    }
                }
            }, null, null, null, LifecycleOwnerKt.getLifecycleScope(this), 112, null));
        }
        return getContextMenuHandler();
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected ContentMetadataOnClickListener getMetadataClickListener() {
        Context context = getContext();
        if (context == null) {
            return null;
        }
        return new PlayQueueClickListener(context);
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected ContentEnabilityProvider getContentEnabilityProvider() {
        Context context = getContext();
        Context applicationContext = context == null ? null : context.getApplicationContext();
        if (applicationContext == null) {
            applicationContext = AmazonApplication.getApplication().getApplicationContext();
        }
        Intrinsics.checkNotNullExpressionValue(applicationContext, "this.context?.applicatio…tion().applicationContext");
        return new LibraryContentEnabilityProvider(applicationContext, getActivity());
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public PageType getPageType() {
        return PageType.UNKNOWN;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0030  */
    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment, androidx.fragment.app.Fragment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onContextItemSelected(android.view.MenuItem r5) {
        /*
            r4 = this;
            java.lang.String r0 = "menuItem"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            com.amazon.mp3.cloudqueue.sequencer.CloudQueueSequencer$Companion r0 = com.amazon.mp3.cloudqueue.sequencer.CloudQueueSequencer.INSTANCE
            boolean r0 = r0.isCloudQueuePlaying()
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L42
            int r0 = r5.getItemId()
            r3 = 2131427643(0x7f0b013b, float:1.8476908E38)
            if (r0 == r3) goto L30
            java.lang.CharSequence r0 = r5.getTitle()
            if (r0 != 0) goto L20
        L1e:
            r0 = r1
            goto L2e
        L20:
            r3 = 2132020231(0x7f140c07, float:1.967882E38)
            java.lang.String r3 = r4.getString(r3)
            boolean r0 = r0.equals(r3)
            if (r0 != r2) goto L1e
            r0 = r2
        L2e:
            if (r0 == 0) goto L42
        L30:
            com.amazon.mp3.playback.view.MiniPlayerViewController r0 = r4.playerController
            if (r0 == 0) goto L3a
            com.amazon.mp3.playback.view.NowPlayingMiniPlayerViewController r0 = (com.amazon.mp3.playback.view.NowPlayingMiniPlayerViewController) r0
            r0.dismiss()
            goto L42
        L3a:
            java.lang.NullPointerException r5 = new java.lang.NullPointerException
            java.lang.String r0 = "null cannot be cast to non-null type com.amazon.mp3.playback.view.NowPlayingMiniPlayerViewController"
            r5.<init>(r0)
            throw r5
        L42:
            com.amazon.mp3.providers.MetadataContextMenuHandler r0 = r4.getContextMenuHandler()
            if (r0 != 0) goto L4a
        L48:
            r0 = r1
            goto L53
        L4a:
            java.lang.String r3 = "nowplaying"
            boolean r0 = r0.onContextMenuItemSelected(r5, r3)
            if (r0 != r2) goto L48
            r0 = r2
        L53:
            if (r0 != 0) goto L64
            com.amazon.mp3.playback.view.MiniPlayerViewController r0 = r4.playerController
            if (r0 != 0) goto L5b
        L59:
            r5 = r1
            goto L62
        L5b:
            boolean r5 = r0.onContextItemSelected(r5)
            if (r5 != r2) goto L59
            r5 = r2
        L62:
            if (r5 == 0) goto L65
        L64:
            r1 = r2
        L65:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.catalog.fragment.PlayQueueFragment.onContextItemSelected(android.view.MenuItem):boolean");
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected void showEmptyView() {
        super.showEmptyView();
        FrameLayout frameLayout = this.clearLayout;
        if (frameLayout == null) {
            return;
        }
        frameLayout.setVisibility(8);
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected void showLoadingView() {
        super.showLoadingView();
        FrameLayout frameLayout = this.clearLayout;
        if (frameLayout == null) {
            return;
        }
        frameLayout.setVisibility(8);
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected void showRecyclerView() {
        ViewTreeObserver viewTreeObserver;
        Integer upcomingHeaderPosition;
        super.showRecyclerView();
        PlayQueueViewModel playQueueViewModel = this.playQueueViewModel;
        if (playQueueViewModel != null && (upcomingHeaderPosition = playQueueViewModel.getUpcomingHeaderPosition()) != null) {
            scrollToPosition(upcomingHeaderPosition.intValue());
        }
        FrameLayout frameLayout = this.clearLayout;
        if (frameLayout == null || (viewTreeObserver = frameLayout.getViewTreeObserver()) == null) {
            return;
        }
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.amazon.mp3.catalog.fragment.PlayQueueFragment.showRecyclerView.2
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                if (!PlayQueueFragment.this.isLyricsSharingEnabled() || !PlayQueueFragment.this.isStage()) {
                    FrameLayout frameLayout2 = PlayQueueFragment.this.clearLayout;
                    if (frameLayout2 == null) {
                        return;
                    }
                    frameLayout2.setVisibility(0);
                    return;
                }
                BaseButton baseButton = PlayQueueFragment.this.clearQueueButton;
                if (baseButton == null) {
                    return;
                }
                baseButton.setVisibility(0);
            }
        });
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        MiniPlayerViewController miniPlayerViewController = this.playerController;
        if (miniPlayerViewController == null) {
            return;
        }
        miniPlayerViewController.clear();
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public BaseViewsDiffUtilFactory getDiffUtilFactory() {
        return new BaseViewsDiffUtilFactory() { // from class: com.amazon.mp3.catalog.fragment.PlayQueueFragment.getDiffUtilFactory.1
            @Override // com.amazon.mp3.catalog.fragment.adapter.BaseViewsDiffUtilFactory
            public BaseViewsDiffUtil getBaseViewsDiffUtil(List<? extends BaseViewModel> oldList, List<? extends BaseViewModel> newList) {
                Intrinsics.checkNotNullParameter(oldList, "oldList");
                Intrinsics.checkNotNullParameter(newList, "newList");
                return new PlayQueueDiffUtilCallback(oldList, newList);
            }
        };
    }

    private final void recordUIPageMetrics() {
        LatencyTracker.INSTANCE.track(new LatencyEventDescriptor(LatencyTrackingLeg.LOAD_COMPLETE, PageLoadLatencyCode.PLAY_QUEUE));
    }

    private final void registerReceivers() {
        IntentFilter intentFilter = new IntentFilter("com.amazon.mp3.library.activity.nowplaying.finish");
        intentFilter.addAction("com.amazon.mp3.library.activity.nowplaying.clear_and_finish");
        Context context = getContext();
        if (context != null) {
            context.registerReceiver(this.mFinishBroadcastReceiver, intentFilter);
        }
        IntentFilter intentFilter2 = new IntentFilter("com.amazon.mp3.playback.video.actionRefreshNowPlayingQueueScreen");
        Context context2 = getContext();
        if (context2 == null) {
            return;
        }
        context2.registerReceiver(this.mVideoTracksReplacedRemoved, intentFilter2);
    }

    private final void unregisterReceivers() {
        unregisterReceiver(this.mFinishBroadcastReceiver);
        unregisterReceiver(this.mVideoTracksReplacedRemoved);
    }

    private final void unregisterReceiver(BroadcastReceiver receiver) {
        try {
            Context context = getContext();
            if (context == null) {
                return;
            }
            context.unregisterReceiver(receiver);
        } catch (IllegalArgumentException unused) {
            Log.debug(this.TAG, Intrinsics.stringPlus("failed to unregister receiver: ", receiver.getClass()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void clearNowPlayingQueue() {
        clearModels();
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        activity.finish();
    }

    private final AlertDialog createClearDialog() {
        Context context = getContext();
        if (context == null) {
            return null;
        }
        return new AlertDialog.Builder(context).setTitle(R.string.dmusic_dialog_clear_play_queue_title).setMessage(R.string.dmusic_dialog_clear_play_queue_message).setNegativeButton(R.string.dmusic_button_cancel, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.catalog.fragment.PlayQueueFragment$$ExternalSyntheticLambda3
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        }).setPositiveButton(R.string.dmusic_dialog_clear_text, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.catalog.fragment.PlayQueueFragment$$ExternalSyntheticLambda4
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                PlayQueueFragment.m524createClearDialog$lambda28$lambda27(this.f$0, dialogInterface, i);
            }
        }).create();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: createClearDialog$lambda-28$lambda-27, reason: not valid java name */
    public static final void m524createClearDialog$lambda28$lambda27(PlayQueueFragment this$0, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialogInterface.cancel();
        this$0.sendUIClickMetrics(ActionType.DELETE_PLAY_QUEUE);
        NowPlayingUtil.clearAndFinishNowPlaying(this$0.getActivity(), PlayStateMutationReason.CLEARED_QUEUE_FROM_UI);
    }

    private final void sendUIClickMetrics(ActionType actionType) {
        MetricsHolder.getManager().handleEvent(UiClickEvent.builder(actionType).withInteractionType(InteractionType.TAP).withEventTime(System.currentTimeMillis()).build());
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected void getMoreData(int pageIndex, int pageSize) {
        PageGridViewModel pageModel = getPageModel();
        if (pageModel == null) {
            return;
        }
        EditableViewModelProvider pageModelProvider = getPageModelProvider();
        PlayQueueViewModel playQueueViewModel = pageModelProvider instanceof PlayQueueViewModel ? (PlayQueueViewModel) pageModelProvider : null;
        if (playQueueViewModel == null) {
            return;
        }
        playQueueViewModel.fetchMorePage(pageModel, pageIndex, pageSize);
    }
}
