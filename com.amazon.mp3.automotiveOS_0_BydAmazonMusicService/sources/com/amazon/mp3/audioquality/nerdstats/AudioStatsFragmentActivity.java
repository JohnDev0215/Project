package com.amazon.mp3.audioquality.nerdstats;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.DimenRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelLazy;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import com.amazon.mp3.actionbar.ActionBarProvider;
import com.amazon.mp3.activity.AudioEffectActivity;
import com.amazon.mp3.activity.WideDialogActivity;
import com.amazon.mp3.activity.WideDialogActivityPurpose;
import com.amazon.mp3.activity.settings.SettingsActivity;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.audioquality.nerdstats.FragmentAudioStatsV2;
import com.amazon.mp3.audioquality.nerdstats.models.NerdStats;
import com.amazon.mp3.audioquality.nerdstats.models.NerdStatsDataModels$SignalFlowState;
import com.amazon.mp3.audioquality.nerdstats.models.NerdStatsDataModels$SignalFlowType;
import com.amazon.mp3.audioquality.nerdstats.models.NerdStatsSignalFlowStats;
import com.amazon.mp3.audioquality.nerdstats.models.NerdStatsViewModel;
import com.amazon.mp3.audioquality.nerdstats.models.NerdStatsViewModelFactory;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.casting.CastingFragment;
import com.amazon.mp3.casting.CastingUtil;
import com.amazon.mp3.dialog.EqualizerDialogBuilder;
import com.amazon.mp3.environment.url.EndpointsProvider;
import com.amazon.mp3.hawkfire.upsell.UnsupportedMarketplaceException;
import com.amazon.mp3.library.provider.source.nowplaying.MediaItemHelper;
import com.amazon.mp3.playback.harley.PlaybackInitializationHelper;
import com.amazon.mp3.playback.service.AudioEffectController;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.util.BauhausToastUtils;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.castingviews.CastingUIMetricsLogger;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.OnPlayStateChangedListener;
import com.amazon.music.media.playback.OnPlaybackEventListener;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.PlaybackController;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContentName;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: AudioStatsFragmentActivity.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000Ì\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0011\u0018\u0000 `2\u00020\u0001:\u0006`abcdeB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010+\u001a\u00020\u0007H\u0002J\b\u0010,\u001a\u00020\u0007H\u0002J\b\u0010-\u001a\u00020\u0007H\u0002J\b\u0010.\u001a\u00020\u0007H\u0002J\b\u0010/\u001a\u00020\u0007H\u0016J\u001b\u00100\u001a\b\u0012\u0004\u0012\u00020\u0010012\u0006\u00102\u001a\u00020&H\u0002¢\u0006\u0002\u00103J\n\u00104\u001a\u0004\u0018\u000105H\u0002J\u0014\u00106\u001a\u000607R\u00020\u00002\u0006\u00108\u001a\u000209H\u0002J\u0010\u0010:\u001a\u00020;2\u0006\u00102\u001a\u00020&H\u0002J\u0012\u0010<\u001a\u00020\u00072\b\u0010=\u001a\u0004\u0018\u00010>H\u0014J\u0010\u0010?\u001a\u00020;2\u0006\u0010@\u001a\u00020AH\u0016J\b\u0010B\u001a\u00020\u0007H\u0014J\u0010\u0010C\u001a\u00020;2\u0006\u0010D\u001a\u00020EH\u0016J\b\u0010F\u001a\u00020\u0007H\u0014J\b\u0010G\u001a\u00020HH\u0002J,\u0010I\u001a\u00020\u00072\u0006\u0010J\u001a\u00020K2\u0006\u0010L\u001a\u0002052\b\u0010M\u001a\u0004\u0018\u0001052\b\u0010N\u001a\u0004\u0018\u00010OH\u0002J\u0010\u0010P\u001a\u00020\u00072\u0006\u0010Q\u001a\u000209H\u0002J\"\u0010R\u001a\u00020\u00072\u0006\u0010S\u001a\u00020;2\b\u0010T\u001a\u0004\u0018\u00010U2\u0006\u0010N\u001a\u00020OH\u0002J\u001a\u0010V\u001a\u00020\u00072\u0006\u0010W\u001a\u00020;2\b\u0010T\u001a\u0004\u0018\u00010UH\u0002J\u0010\u0010X\u001a\u00020\u00072\u0006\u00102\u001a\u00020&H\u0002J\u0010\u0010Y\u001a\u00020\u00072\u0006\u00102\u001a\u00020&H\u0002J\u0010\u0010Z\u001a\u00020\u00072\u0006\u00102\u001a\u00020&H\u0002J\u001a\u0010[\u001a\u00020\u00072\u0006\u0010W\u001a\u00020;2\b\u0010T\u001a\u0004\u0018\u00010UH\u0002J\u0018\u0010\\\u001a\u00020\u00072\u0006\u00102\u001a\u00020&2\u0006\u0010]\u001a\u00020HH\u0002J\u001a\u0010^\u001a\u00020\u00072\u0006\u0010W\u001a\u00020;2\b\u0010T\u001a\u0004\u0018\u00010UH\u0002J\u0010\u0010_\u001a\u00020\u00072\u0006\u00102\u001a\u00020&H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n \f*\u0004\u0018\u00010\u000b0\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u001e\u001a\u00020\u001f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\"\u0010#\u001a\u0004\b \u0010!R\u0014\u0010$\u001a\b\u0012\u0004\u0012\u00020&0%X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010'\u001a\u0004\u0018\u00010(X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010)\u001a\u0004\u0018\u00010*X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006f"}, d2 = {"Lcom/amazon/mp3/audioquality/nerdstats/AudioStatsFragmentActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "loudnessMessage", "Landroid/widget/TextView;", "onResumeCallback", "Lkotlin/Function0;", "", "playStateListener", "Lcom/amazon/music/media/playback/OnPlayStateChangedListener;", "playbackController", "Lcom/amazon/music/media/playback/PlaybackController;", "kotlin.jvm.PlatformType", "playbackEventListener", "Lcom/amazon/music/media/playback/OnPlaybackEventListener;", "spatialFragment", "Lcom/amazon/mp3/audioquality/nerdstats/FragmentAudioStatsV2;", "spatialToggleButton", "Landroid/widget/RadioButton;", "spinnerView", "Landroid/widget/ProgressBar;", "stereoFragment", "stereoToggleButton", "streamQualityHeaderInfo", "streamQualityHeaderValue", "tabletSignalFlowLeftContainer", "Landroid/widget/FrameLayout;", "tabletSignalFlowRightContainer", "toggleButtonGroup", "Landroid/widget/RadioGroup;", "viewModel", "Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsViewModel;", "getViewModel", "()Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "viewModelObserver", "Landroidx/lifecycle/Observer;", "Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsDataModels$NerdStats;", "viewPager", "Landroidx/viewpager2/widget/ViewPager2;", "viewPagerCallback", "Landroidx/viewpager2/widget/ViewPager2$OnPageChangeCallback;", "clearDualContainerListeners", "clearViewPagerListeners", "configureViewPager", "enqueueViewModelRefresh", "finish", "getAudioStatsFragments", "", "nerdStats", "(Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsDataModels$NerdStats;)[Lcom/amazon/mp3/audioquality/nerdstats/FragmentAudioStatsV2;", "getCurrentMediaItemAsin", "", "getNerdStatsSpacerDecoration", "Lcom/amazon/mp3/audioquality/nerdstats/AudioStatsFragmentActivity$HorizontalMarginItemDecoration;", "position", "", "isQualityChanging", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "menu", "Landroid/view/Menu;", "onDestroy", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onResume", "prepareViewElements", "Lcom/amazon/mp3/audioquality/nerdstats/AudioStatsFragmentActivity$LayoutType;", "sendUIClickMetric", "actionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "contentName", "asin", "interactionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;", "setViewPagerItem", "desiredItem", "toggleSpatialMode", "expectedSpatialModeState", "spatialToggleData", "Lcom/amazon/mp3/audioquality/nerdstats/AudioStatsFragmentActivity$SpatialToggleData;", "updateDualContainerClickListeners", "enableSpatialToggle", "updateDualContainerFragments", "updateLoudnessNormalizationMessage", "updatePageHeader", "updateToggleButtons", "updateUserInteraction", "preparedLayoutType", "updateViewPagerCallback", "updateViewPagerFragments", "Companion", "HorizontalMarginItemDecoration", "LayoutType", "NerdStatsActionBarProvider", "SpatialToggleData", "ViewPagerAdapter", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AudioStatsFragmentActivity extends AppCompatActivity {
    private static final int LEFT_INDEX = 0;
    private TextView loudnessMessage;
    private Function0<Unit> onResumeCallback;
    private final OnPlayStateChangedListener playStateListener;
    private PlaybackController playbackController;
    private final OnPlaybackEventListener playbackEventListener;
    private final FragmentAudioStatsV2 spatialFragment;
    private RadioButton spatialToggleButton;
    private ProgressBar spinnerView;
    private final FragmentAudioStatsV2 stereoFragment;
    private RadioButton stereoToggleButton;
    private TextView streamQualityHeaderInfo;
    private TextView streamQualityHeaderValue;
    private FrameLayout tabletSignalFlowLeftContainer;
    private FrameLayout tabletSignalFlowRightContainer;
    private RadioGroup toggleButtonGroup;

    /* JADX INFO: renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel = new ViewModelLazy(Reflection.getOrCreateKotlinClass(NerdStatsViewModel.class), new Function0<ViewModelStore>() { // from class: com.amazon.mp3.audioquality.nerdstats.AudioStatsFragmentActivity$special$$inlined$viewModels$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ViewModelStore invoke() {
            ViewModelStore viewModelStore = this.getViewModelStore();
            Intrinsics.checkNotNullExpressionValue(viewModelStore, "viewModelStore");
            return viewModelStore;
        }
    }, new Function0<ViewModelProvider.Factory>() { // from class: com.amazon.mp3.audioquality.nerdstats.AudioStatsFragmentActivity$viewModel$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ViewModelProvider.Factory invoke() {
            return NerdStatsViewModelFactory.INSTANCE.getViewModelFactory(this.this$0);
        }
    });
    private Observer<NerdStats> viewModelObserver;
    private ViewPager2 viewPager;
    private ViewPager2.OnPageChangeCallback viewPagerCallback;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = AudioStatsFragmentActivity.class.getSimpleName();
    private static final float DISABLED_OPACITY = 0.25f;
    private static final float ENABLED_OPACITY = 1.0f;
    private static final int RIGHT_INDEX = 1;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: AudioStatsFragmentActivity.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0082\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/amazon/mp3/audioquality/nerdstats/AudioStatsFragmentActivity$LayoutType;", "", "(Ljava/lang/String;I)V", "ViewPager", "DualContainer", "None", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    enum LayoutType {
        ViewPager,
        DualContainer,
        None
    }

    /* JADX INFO: compiled from: AudioStatsFragmentActivity.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LayoutType.values().length];
            iArr[LayoutType.ViewPager.ordinal()] = 1;
            iArr[LayoutType.DualContainer.ordinal()] = 2;
            iArr[LayoutType.None.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public AudioStatsFragmentActivity() {
        Bundle bundle = new Bundle();
        FragmentAudioStatsV2.Companion companion = FragmentAudioStatsV2.INSTANCE;
        bundle.putSerializable(companion.getSIGNAL_FLOW_TYPE_KEY(), NerdStatsDataModels$SignalFlowType.Spatial);
        this.spatialFragment = companion.newInstance(bundle);
        Bundle bundle2 = new Bundle();
        bundle2.putSerializable(companion.getSIGNAL_FLOW_TYPE_KEY(), NerdStatsDataModels$SignalFlowType.Stereo);
        this.stereoFragment = companion.newInstance(bundle2);
        this.playbackController = Playback.getInstance().getPlaybackController(ControlSource.APP_UI);
        this.playStateListener = new OnPlayStateChangedListener() { // from class: com.amazon.mp3.audioquality.nerdstats.AudioStatsFragmentActivity$$ExternalSyntheticLambda1
            @Override // com.amazon.music.media.playback.OnPlayStateChangedListener
            public final void onPlayStateChanged() {
                AudioStatsFragmentActivity.m234playStateListener$lambda0(this.f$0);
            }
        };
        this.playbackEventListener = new OnPlaybackEventListener() { // from class: com.amazon.mp3.audioquality.nerdstats.AudioStatsFragmentActivity$$ExternalSyntheticLambda2
            @Override // com.amazon.music.media.playback.OnPlaybackEventListener
            public final void onPlaybackEvent(int i, ControlSource controlSource) {
                AudioStatsFragmentActivity.m235playbackEventListener$lambda1(this.f$0, i, controlSource);
            }
        };
    }

    private final NerdStatsViewModel getViewModel() {
        return (NerdStatsViewModel) this.viewModel.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: playStateListener$lambda-0, reason: not valid java name */
    public static final void m234playStateListener$lambda0(AudioStatsFragmentActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getViewModel().refresh(NerdStatsViewModel.RefreshReason.PlayStateChange);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: playbackEventListener$lambda-1, reason: not valid java name */
    public static final void m235playbackEventListener$lambda1(AudioStatsFragmentActivity this$0, int i, ControlSource noName_1) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
        if (i == 19) {
            this$0.getViewModel().refresh(NerdStatsViewModel.RefreshReason.PlayerChange);
        }
    }

    /* JADX INFO: compiled from: AudioStatsFragmentActivity.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/amazon/mp3/audioquality/nerdstats/AudioStatsFragmentActivity$Companion;", "", "()V", "DISABLED_OPACITY", "", "ENABLED_OPACITY", "LEFT_INDEX", "", "RIGHT_INDEX", "TAG", "", "kotlin.jvm.PlatformType", "isSpatialActive", "", "nerdStats", "Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsDataModels$NerdStats;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean isSpatialActive(NerdStats nerdStats) {
            NerdStatsSignalFlowStats spatialStats = nerdStats.getSpatialStats();
            return (spatialStats == null ? null : spatialStats.getSignalFlowState()) == NerdStatsDataModels$SignalFlowState.Active;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: AudioStatsFragmentActivity.kt */
    @Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\b\u0082\b\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000e\u001a\u00020\u0002\u0012\u0006\u0010\u0012\u001a\u00020\u0002\u0012\u0006\u0010\u0014\u001a\u00020\u0002\u0012\u0006\u0010\u0016\u001a\u00020\u0002\u0012\b\u0010\u0018\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u001a\u0010\u001bJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0012\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u000f\u001a\u0004\b\u0013\u0010\u0011R\u0017\u0010\u0014\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u000f\u001a\u0004\b\u0015\u0010\u0011R\u0017\u0010\u0016\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0016\u0010\u000f\u001a\u0004\b\u0017\u0010\u0011R\u0019\u0010\u0018\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u000f\u001a\u0004\b\u0019\u0010\u0011¨\u0006\u001c"}, d2 = {"Lcom/amazon/mp3/audioquality/nerdstats/AudioStatsFragmentActivity$SpatialToggleData;", "", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsDataModels$SignalFlowType;", "activeType", "Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsDataModels$SignalFlowType;", "getActiveType", "()Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsDataModels$SignalFlowType;", "spatialLabel", "Ljava/lang/String;", "getSpatialLabel", "()Ljava/lang/String;", "stereoLabel", "getStereoLabel", "spatialAnnouncement", "getSpatialAnnouncement", "stereoAnnouncement", "getStereoAnnouncement", "asin", "getAsin", "<init>", "(Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsDataModels$SignalFlowType;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
    static final /* data */ class SpatialToggleData {
        private final NerdStatsDataModels$SignalFlowType activeType;
        private final String asin;
        private final String spatialAnnouncement;
        private final String spatialLabel;
        private final String stereoAnnouncement;
        private final String stereoLabel;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SpatialToggleData)) {
                return false;
            }
            SpatialToggleData spatialToggleData = (SpatialToggleData) other;
            return this.activeType == spatialToggleData.activeType && Intrinsics.areEqual(this.spatialLabel, spatialToggleData.spatialLabel) && Intrinsics.areEqual(this.stereoLabel, spatialToggleData.stereoLabel) && Intrinsics.areEqual(this.spatialAnnouncement, spatialToggleData.spatialAnnouncement) && Intrinsics.areEqual(this.stereoAnnouncement, spatialToggleData.stereoAnnouncement) && Intrinsics.areEqual(this.asin, spatialToggleData.asin);
        }

        public int hashCode() {
            int iHashCode = ((((((((this.activeType.hashCode() * 31) + this.spatialLabel.hashCode()) * 31) + this.stereoLabel.hashCode()) * 31) + this.spatialAnnouncement.hashCode()) * 31) + this.stereoAnnouncement.hashCode()) * 31;
            String str = this.asin;
            return iHashCode + (str == null ? 0 : str.hashCode());
        }

        public String toString() {
            return "SpatialToggleData(activeType=" + this.activeType + ", spatialLabel=" + this.spatialLabel + ", stereoLabel=" + this.stereoLabel + ", spatialAnnouncement=" + this.spatialAnnouncement + ", stereoAnnouncement=" + this.stereoAnnouncement + ", asin=" + ((Object) this.asin) + ')';
        }

        public SpatialToggleData(NerdStatsDataModels$SignalFlowType activeType, String spatialLabel, String stereoLabel, String spatialAnnouncement, String stereoAnnouncement, String str) {
            Intrinsics.checkNotNullParameter(activeType, "activeType");
            Intrinsics.checkNotNullParameter(spatialLabel, "spatialLabel");
            Intrinsics.checkNotNullParameter(stereoLabel, "stereoLabel");
            Intrinsics.checkNotNullParameter(spatialAnnouncement, "spatialAnnouncement");
            Intrinsics.checkNotNullParameter(stereoAnnouncement, "stereoAnnouncement");
            this.activeType = activeType;
            this.spatialLabel = spatialLabel;
            this.stereoLabel = stereoLabel;
            this.spatialAnnouncement = spatialAnnouncement;
            this.stereoAnnouncement = stereoAnnouncement;
            this.asin = str;
        }

        public final NerdStatsDataModels$SignalFlowType getActiveType() {
            return this.activeType;
        }

        public final String getSpatialLabel() {
            return this.spatialLabel;
        }

        public final String getStereoLabel() {
            return this.stereoLabel;
        }

        public final String getSpatialAnnouncement() {
            return this.spatialAnnouncement;
        }

        public final String getStereoAnnouncement() {
            return this.stereoAnnouncement;
        }

        public final String getAsin() {
            return this.asin;
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nerd_stats);
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_down);
        View viewFindViewById = findViewById(R.id.nerd_stats_loudness_message);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(R.id.nerd_stats_loudness_message)");
        this.loudnessMessage = (TextView) viewFindViewById;
        View viewFindViewById2 = findViewById(R.id.nerd_stats_progress_bar);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "findViewById(R.id.nerd_stats_progress_bar)");
        this.spinnerView = (ProgressBar) viewFindViewById2;
        View viewFindViewById3 = findViewById(R.id.nerd_stats_steam_quality_header_info);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "findViewById(R.id.nerd_s…team_quality_header_info)");
        this.streamQualityHeaderInfo = (TextView) viewFindViewById3;
        View viewFindViewById4 = findViewById(R.id.nerd_stats_steam_quality_header_value);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "findViewById(R.id.nerd_s…eam_quality_header_value)");
        this.streamQualityHeaderValue = (TextView) viewFindViewById4;
        View viewFindViewById5 = findViewById(R.id.nerd_stats_toolbar_layout);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById5, "findViewById(R.id.nerd_stats_toolbar_layout)");
        View viewFindViewById6 = viewFindViewById5.findViewById(R.id.toolbar);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById6, "toolbarLayout.findViewById(R.id.toolbar)");
        setSupportActionBar((Toolbar) viewFindViewById6);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            new NerdStatsActionBarProvider(supportActionBar);
        }
        View viewFindViewById7 = findViewById(R.id.nerd_close_btn);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById7, "findViewById(R.id.nerd_close_btn)");
        ((ImageButton) viewFindViewById7).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.audioquality.nerdstats.AudioStatsFragmentActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AudioStatsFragmentActivity.m232onCreate$lambda3(this.f$0, view);
            }
        });
        final LayoutType layoutTypePrepareViewElements = prepareViewElements();
        this.viewModelObserver = new Observer() { // from class: com.amazon.mp3.audioquality.nerdstats.AudioStatsFragmentActivity$$ExternalSyntheticLambda4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                AudioStatsFragmentActivity.m233onCreate$lambda6(this.f$0, layoutTypePrepareViewElements, (NerdStats) obj);
            }
        };
        LiveData<NerdStats> liveData = getViewModel().getLiveData();
        Observer<NerdStats> observer = this.viewModelObserver;
        if (observer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelObserver");
            observer = null;
        }
        liveData.observe(this, observer);
        this.playbackController.addOnPlayStateChangedListener(this.playStateListener);
        this.playbackController.addOnPlaybackEventListener(this.playbackEventListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreate$lambda-3, reason: not valid java name */
    public static final void m232onCreate$lambda3(AudioStatsFragmentActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreate$lambda-6, reason: not valid java name */
    public static final void m233onCreate$lambda6(AudioStatsFragmentActivity this$0, LayoutType preparedLayoutType, NerdStats nerdStats) {
        NerdStatsSignalFlowStats stereoStats;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(preparedLayoutType, "$preparedLayoutType");
        if (nerdStats.getIsSpatialToggleComplete()) {
            NerdStatsSignalFlowStats spatialStats = nerdStats.getSpatialStats();
            SpatialToggleData spatialToggleData = null;
            if (spatialStats != null && (stereoStats = nerdStats.getStereoStats()) != null) {
                Companion companion = INSTANCE;
                Intrinsics.checkNotNullExpressionValue(nerdStats, "nerdStats");
                spatialToggleData = new SpatialToggleData(companion.isSpatialActive(nerdStats) ? NerdStatsDataModels$SignalFlowType.Spatial : NerdStatsDataModels$SignalFlowType.Stereo, spatialStats.getToggleLabel(), stereoStats.getToggleLabel(), spatialStats.getToggleAnnouncement(), stereoStats.getToggleAnnouncement(), nerdStats.getMediaItemAsin());
            }
            Intrinsics.checkNotNullExpressionValue(nerdStats, "nerdStats");
            this$0.updatePageHeader(nerdStats);
            int i = WhenMappings.$EnumSwitchMapping$0[preparedLayoutType.ordinal()];
            if (i == 1) {
                this$0.clearViewPagerListeners();
                this$0.updateViewPagerFragments(nerdStats);
                this$0.updateViewPagerCallback(nerdStats.getEnableSpatialToggle(), spatialToggleData);
                this$0.updateToggleButtons(nerdStats.getEnableSpatialToggle(), spatialToggleData);
            } else if (i == 2) {
                this$0.clearDualContainerListeners();
                this$0.updateDualContainerFragments(nerdStats);
                this$0.updateDualContainerClickListeners(nerdStats.getEnableSpatialToggle(), spatialToggleData);
            } else if (i == 3) {
                Log.error(TAG, "No view elements are available for displaying Signal Flow Diagrams");
            }
        }
        Intrinsics.checkNotNullExpressionValue(nerdStats, "nerdStats");
        this$0.updateUserInteraction(nerdStats, preparedLayoutType);
        this$0.updateLoudnessNormalizationMessage(nerdStats);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        Function0<Unit> function0 = this.onResumeCallback;
        if (function0 != null) {
            function0.invoke();
        }
        this.onResumeCallback = null;
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        LiveData<NerdStats> liveData = getViewModel().getLiveData();
        Observer<NerdStats> observer = this.viewModelObserver;
        if (observer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelObserver");
            observer = null;
        }
        liveData.removeObserver(observer);
        this.playbackController.removeOnPlayStateChangedListener(this.playStateListener);
        this.playbackController.removeOnPlaybackEventListener(this.playbackEventListener);
        clearViewPagerListeners();
        clearDualContainerListeners();
    }

    @Override // android.app.Activity
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_down);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void enqueueViewModelRefresh() {
        new Handler(Looper.getMainLooper()).postAtFrontOfQueue(new Runnable() { // from class: com.amazon.mp3.audioquality.nerdstats.AudioStatsFragmentActivity$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                AudioStatsFragmentActivity.m231enqueueViewModelRefresh$lambda7(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: enqueueViewModelRefresh$lambda-7, reason: not valid java name */
    public static final void m231enqueueViewModelRefresh$lambda7(AudioStatsFragmentActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getViewModel().refresh(NerdStatsViewModel.RefreshReason.PlayStateChange);
    }

    private final LayoutType prepareViewElements() {
        ViewPager2 viewPager2 = (ViewPager2) findViewById(R.id.nerd_stats_signal_flow_pager);
        this.viewPager = viewPager2;
        if (viewPager2 != null) {
            configureViewPager();
            setRequestedOrientation(1);
            this.spatialToggleButton = (RadioButton) findViewById(R.id.atmos_toggle);
            this.stereoToggleButton = (RadioButton) findViewById(R.id.hd_toggle);
            this.toggleButtonGroup = (RadioGroup) findViewById(R.id.nerd_stats_toggle_group);
            return LayoutType.ViewPager;
        }
        this.tabletSignalFlowLeftContainer = (FrameLayout) findViewById(R.id.tablet_signal_flow_left_container);
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.tablet_signal_flow_right_container);
        this.tabletSignalFlowRightContainer = frameLayout;
        if (this.tabletSignalFlowLeftContainer != null && frameLayout != null) {
            return LayoutType.DualContainer;
        }
        return LayoutType.None;
    }

    private final void updateLoudnessNormalizationMessage(NerdStats nerdStats) {
        TextView textView = null;
        if (nerdStats.getEnableSpatialToggle() && !SettingsUtil.isLoudnessNormalizationEnabled(this)) {
            TextView textView2 = this.loudnessMessage;
            if (textView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("loudnessMessage");
                textView2 = null;
            }
            textView2.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.audioquality.nerdstats.AudioStatsFragmentActivity$$ExternalSyntheticLambda9
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AudioStatsFragmentActivity.m238updateLoudnessNormalizationMessage$lambda8(this.f$0, view);
                }
            });
            TextView textView3 = this.loudnessMessage;
            if (textView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("loudnessMessage");
            } else {
                textView = textView3;
            }
            textView.setVisibility(0);
            return;
        }
        TextView textView4 = this.loudnessMessage;
        if (textView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("loudnessMessage");
        } else {
            textView = textView4;
        }
        textView.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: updateLoudnessNormalizationMessage$lambda-8, reason: not valid java name */
    public static final void m238updateLoudnessNormalizationMessage$lambda8(AudioStatsFragmentActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SettingsActivity.start(this$0);
    }

    private final boolean isQualityChanging(NerdStats nerdStats) {
        if (nerdStats.getEnableSpatialToggle()) {
            return INSTANCE.isSpatialActive(nerdStats) ^ SettingsUtil.isSpatialModeSettingEnabled(this);
        }
        return false;
    }

    private final void updateUserInteraction(NerdStats nerdStats, LayoutType preparedLayoutType) {
        boolean zIsQualityChanging = isQualityChanging(nerdStats);
        ProgressBar progressBar = this.spinnerView;
        if (progressBar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("spinnerView");
            progressBar = null;
        }
        boolean z = false;
        progressBar.setVisibility(zIsQualityChanging ? 0 : 8);
        if (nerdStats.getEnableSpatialToggle() && !zIsQualityChanging) {
            z = true;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[preparedLayoutType.ordinal()];
        if (i != 1) {
            if (i != 2) {
                return;
            }
            FrameLayout frameLayout = this.tabletSignalFlowRightContainer;
            if (frameLayout != null) {
                frameLayout.setEnabled(z);
            }
            FrameLayout frameLayout2 = this.tabletSignalFlowLeftContainer;
            if (frameLayout2 == null) {
                return;
            }
            frameLayout2.setEnabled(z);
            return;
        }
        RadioGroup radioGroup = this.toggleButtonGroup;
        if (radioGroup != null) {
            radioGroup.setEnabled(z);
        }
        RadioButton radioButton = this.spatialToggleButton;
        if (radioButton != null) {
            radioButton.setEnabled(z);
        }
        RadioButton radioButton2 = this.stereoToggleButton;
        if (radioButton2 != null) {
            radioButton2.setEnabled(z);
        }
        ViewPager2 viewPager2 = this.viewPager;
        if (viewPager2 == null) {
            return;
        }
        viewPager2.setUserInputEnabled(z);
    }

    private final void updatePageHeader(NerdStats nerdStats) {
        TextView textView = this.streamQualityHeaderValue;
        TextView textView2 = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("streamQualityHeaderValue");
            textView = null;
        }
        textView.setText(nerdStats.getSummary().getPlaybackQuality());
        String playbackNote = nerdStats.getSummary().getPlaybackNote();
        TextView textView3 = this.streamQualityHeaderInfo;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("streamQualityHeaderInfo");
            textView3 = null;
        }
        textView3.setText(playbackNote);
        TextView textView4 = this.streamQualityHeaderInfo;
        if (textView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("streamQualityHeaderInfo");
        } else {
            textView2 = textView4;
        }
        textView2.setVisibility(playbackNote.length() == 0 ? 8 : 0);
    }

    private final FragmentAudioStatsV2[] getAudioStatsFragments(NerdStats nerdStats) {
        return (nerdStats.getSpatialStats() == null || nerdStats.getStereoStats() == null) ? nerdStats.getStereoStats() != null ? new FragmentAudioStatsV2[]{this.stereoFragment} : nerdStats.getSpatialStats() != null ? new FragmentAudioStatsV2[]{this.spatialFragment} : new FragmentAudioStatsV2[0] : new FragmentAudioStatsV2[]{this.spatialFragment, this.stereoFragment};
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void updateViewPagerFragments(com.amazon.mp3.audioquality.nerdstats.models.NerdStats r8) {
        /*
            r7 = this;
            androidx.viewpager2.widget.ViewPager2 r0 = r7.viewPager
            if (r0 != 0) goto L5
            goto L3c
        L5:
            com.amazon.mp3.audioquality.nerdstats.FragmentAudioStatsV2[] r1 = r7.getAudioStatsFragments(r8)
            int r2 = r1.length
            r3 = 0
            r4 = 1
            if (r2 <= r4) goto L10
            r2 = r4
            goto L11
        L10:
            r2 = r3
        L11:
            if (r2 == 0) goto L25
            int r5 = r1.length
            androidx.recyclerview.widget.RecyclerView$Adapter r6 = r0.getAdapter()
            if (r6 != 0) goto L1c
        L1a:
            r5 = r3
            goto L23
        L1c:
            int r6 = r6.getItemCount()
            if (r5 != r6) goto L1a
            r5 = r4
        L23:
            if (r5 != 0) goto L2d
        L25:
            com.amazon.mp3.audioquality.nerdstats.AudioStatsFragmentActivity$ViewPagerAdapter r5 = new com.amazon.mp3.audioquality.nerdstats.AudioStatsFragmentActivity$ViewPagerAdapter
            r5.<init>(r7, r1)
            r0.setAdapter(r5)
        L2d:
            com.amazon.mp3.audioquality.nerdstats.AudioStatsFragmentActivity$Companion r0 = com.amazon.mp3.audioquality.nerdstats.AudioStatsFragmentActivity.INSTANCE
            boolean r8 = com.amazon.mp3.audioquality.nerdstats.AudioStatsFragmentActivity.Companion.access$isSpatialActive(r0, r8)
            if (r2 == 0) goto L39
            if (r8 == 0) goto L38
            goto L39
        L38:
            r3 = r4
        L39:
            r7.setViewPagerItem(r3)
        L3c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.audioquality.nerdstats.AudioStatsFragmentActivity.updateViewPagerFragments(com.amazon.mp3.audioquality.nerdstats.models.NerdStatsDataModels$NerdStats):void");
    }

    private final void setViewPagerItem(int desiredItem) {
        ViewPager2 viewPager2 = this.viewPager;
        if (viewPager2 == null) {
            return;
        }
        if (viewPager2.getCurrentItem() != desiredItem) {
            RecyclerView.Adapter adapter = viewPager2.getAdapter();
            if (desiredItem < (adapter == null ? 0 : adapter.getItemCount())) {
                viewPager2.setCurrentItem(desiredItem);
            }
        }
        while (viewPager2.getItemDecorationCount() > 0) {
            viewPager2.removeItemDecorationAt(0);
        }
        viewPager2.addItemDecoration(getNerdStatsSpacerDecoration(viewPager2.getCurrentItem()));
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        boolean zOnCreateOptionsMenu = super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        Intrinsics.checkNotNullExpressionValue(menuInflater, "menuInflater");
        menuInflater.inflate(R.menu.menu_nerd_stats, menu);
        return zOnCreateOptionsMenu;
    }

    private final String getCurrentMediaItemAsin() {
        return MediaItemHelper.getAsin(Playback.getInstance().getPlaybackController(ControlSource.NONE).getCurrentMediaItem());
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem item) {
        String string;
        Intrinsics.checkNotNullParameter(item, "item");
        switch (item.getItemId()) {
            case R.id.NerdStatsMenuCastToDevice /* 2131427705 */:
                Intent startIntent = CastingFragment.getStartIntent(this);
                CastingUIMetricsLogger.recordCastingButtonClickMetric(CastingUIMetricsLogger.ContentName.NOW_PLAYING);
                startActivity(startIntent);
                return true;
            case R.id.NerdStatsMenuDownloadQualitySettings /* 2131427706 */:
                WideDialogActivity.Companion.showActivity$default(WideDialogActivity.INSTANCE, this, WideDialogActivityPurpose.SHOW_KATANA_DOWNLOAD_QUALITY_PREFERENCES, R.style.Theme_AmazonMP3_Dialog, null, 8, null);
                sendUIClickMetric(ActionType.GO_DOWNLOAD_SETTINGS, "goDownloadSettings", getCurrentMediaItemAsin(), null);
                this.onResumeCallback = new AnonymousClass1(this);
                return true;
            case R.id.NerdStatsMenuEqualizer /* 2131427707 */:
                if (CastingUtil.isCasting()) {
                    new EqualizerDialogBuilder(this).getUnavailableWhileCastingDialog().show();
                } else if (AudioEffectController.isAudioEffectsPageSupported()) {
                    startActivity(new Intent(this, (Class<?>) AudioEffectActivity.class));
                }
                MetricsHolder.getManager().handleEvent(UiClickEvent.builder(ActionType.GO_NOW_PLAYING_EQUALIZER).withInteractionType(InteractionType.TAP).withEventTime(System.currentTimeMillis()).build());
                return true;
            case R.id.NerdStatsMenuLearnMore /* 2131427708 */:
                try {
                    if (UserSubscriptionUtil.getUserSubscription().isIvy()) {
                        string = EndpointsProvider.get().getIvyLearnMoreEndpoint().toString();
                    } else {
                        string = EndpointsProvider.get().getKatanaLearnMoreEndpoint().toString();
                    }
                    if (string != null) {
                        Uri uri = Uri.parse(string);
                        Intrinsics.checkNotNullExpressionValue(uri, "parse(url)");
                        startActivity(new Intent("android.intent.action.VIEW", uri));
                    }
                } catch (UnsupportedMarketplaceException e) {
                    Log.error(TAG, "Localized endpoint not available for this local", e);
                }
                return true;
            case R.id.NerdStatsMenuStreamingQualitySettings /* 2131427709 */:
                WideDialogActivity.Companion.showActivity$default(WideDialogActivity.INSTANCE, this, WideDialogActivityPurpose.SHOW_KATANA_AUDIO_QUALITY_PREFERENCES, R.style.Theme_AmazonMP3_Dialog, null, 8, null);
                sendUIClickMetric(ActionType.GO_AUDIO_QUALITY_SETTINGS, "goAudioQualitySettings", getCurrentMediaItemAsin(), null);
                this.onResumeCallback = new AnonymousClass2(this);
                return true;
            default:
                return false;
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.audioquality.nerdstats.AudioStatsFragmentActivity$onOptionsItemSelected$1, reason: invalid class name */
    /* JADX INFO: compiled from: AudioStatsFragmentActivity.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function0<Unit> {
        AnonymousClass1(Object obj) {
            super(0, obj, AudioStatsFragmentActivity.class, "enqueueViewModelRefresh", "enqueueViewModelRefresh()V", 0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2() {
            ((AudioStatsFragmentActivity) this.receiver).enqueueViewModelRefresh();
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.audioquality.nerdstats.AudioStatsFragmentActivity$onOptionsItemSelected$2, reason: invalid class name */
    /* JADX INFO: compiled from: AudioStatsFragmentActivity.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    /* synthetic */ class AnonymousClass2 extends FunctionReferenceImpl implements Function0<Unit> {
        AnonymousClass2(Object obj) {
            super(0, obj, AudioStatsFragmentActivity.class, "enqueueViewModelRefresh", "enqueueViewModelRefresh()V", 0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2() {
            ((AudioStatsFragmentActivity) this.receiver).enqueueViewModelRefresh();
        }
    }

    private final void clearViewPagerListeners() {
        ViewPager2 viewPager2;
        ViewPager2.OnPageChangeCallback onPageChangeCallback = this.viewPagerCallback;
        if (onPageChangeCallback != null && (viewPager2 = this.viewPager) != null) {
            viewPager2.unregisterOnPageChangeCallback(onPageChangeCallback);
        }
        this.viewPagerCallback = null;
        RadioGroup radioGroup = this.toggleButtonGroup;
        if (radioGroup == null) {
            return;
        }
        radioGroup.setOnCheckedChangeListener(null);
    }

    private final void clearDualContainerListeners() {
        FrameLayout frameLayout = this.tabletSignalFlowLeftContainer;
        if (frameLayout != null) {
            frameLayout.setOnClickListener(null);
        }
        FrameLayout frameLayout2 = this.tabletSignalFlowRightContainer;
        if (frameLayout2 == null) {
            return;
        }
        frameLayout2.setOnClickListener(null);
    }

    private final void updateToggleButtons(boolean enableSpatialToggle, final SpatialToggleData spatialToggleData) {
        if (spatialToggleData == null) {
            RadioGroup radioGroup = this.toggleButtonGroup;
            if (radioGroup == null) {
                return;
            }
            radioGroup.setVisibility(8);
            return;
        }
        RadioButton radioButton = this.spatialToggleButton;
        if (radioButton != null) {
            radioButton.setText(spatialToggleData.getSpatialLabel());
        }
        RadioButton radioButton2 = this.stereoToggleButton;
        if (radioButton2 != null) {
            radioButton2.setText(spatialToggleData.getStereoLabel());
        }
        if (spatialToggleData.getActiveType() == NerdStatsDataModels$SignalFlowType.Spatial) {
            RadioButton radioButton3 = this.spatialToggleButton;
            if (radioButton3 != null) {
                radioButton3.setChecked(true);
            }
        } else {
            RadioButton radioButton4 = this.stereoToggleButton;
            if (radioButton4 != null) {
                radioButton4.setChecked(true);
            }
        }
        RadioGroup radioGroup2 = this.toggleButtonGroup;
        if (radioGroup2 != null) {
            radioGroup2.setVisibility(0);
        }
        if (enableSpatialToggle) {
            RadioGroup radioGroup3 = this.toggleButtonGroup;
            if (radioGroup3 != null) {
                radioGroup3.setAlpha(ENABLED_OPACITY);
            }
            RadioGroup radioGroup4 = this.toggleButtonGroup;
            if (radioGroup4 == null) {
                return;
            }
            radioGroup4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.amazon.mp3.audioquality.nerdstats.AudioStatsFragmentActivity$$ExternalSyntheticLambda6
                @Override // android.widget.RadioGroup.OnCheckedChangeListener
                public final void onCheckedChanged(RadioGroup radioGroup5, int i) {
                    AudioStatsFragmentActivity.m239updateToggleButtons$lambda14(this.f$0, spatialToggleData, radioGroup5, i);
                }
            });
            return;
        }
        RadioGroup radioGroup5 = this.toggleButtonGroup;
        if (radioGroup5 == null) {
            return;
        }
        radioGroup5.setAlpha(DISABLED_OPACITY);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: updateToggleButtons$lambda-14, reason: not valid java name */
    public static final void m239updateToggleButtons$lambda14(AudioStatsFragmentActivity this$0, SpatialToggleData spatialToggleData, RadioGroup radioGroup, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (i == R.id.atmos_toggle) {
            ViewPager2 viewPager2 = this$0.viewPager;
            if (viewPager2 != null && viewPager2.getCurrentItem() == 0) {
                return;
            }
            this$0.toggleSpatialMode(true, spatialToggleData, InteractionType.TOGGLE);
            this$0.setViewPagerItem(0);
            return;
        }
        if (i == R.id.hd_toggle) {
            ViewPager2 viewPager22 = this$0.viewPager;
            if (viewPager22 != null && viewPager22.getCurrentItem() == 1) {
                return;
            }
            this$0.toggleSpatialMode(false, spatialToggleData, InteractionType.TOGGLE);
            this$0.setViewPagerItem(1);
            return;
        }
        this$0.setViewPagerItem(0);
    }

    private final void updateDualContainerFragments(NerdStats nerdStats) {
        FragmentAudioStatsV2[] audioStatsFragments = getAudioStatsFragments(nerdStats);
        FragmentAudioStatsV2 fragmentAudioStatsV2 = audioStatsFragments.length > 0 ? audioStatsFragments[0] : null;
        FragmentAudioStatsV2 fragmentAudioStatsV22 = audioStatsFragments.length > 1 ? audioStatsFragments[1] : null;
        FrameLayout frameLayout = this.tabletSignalFlowLeftContainer;
        if (frameLayout == null) {
            return;
        }
        int id = frameLayout.getId();
        FrameLayout frameLayout2 = this.tabletSignalFlowRightContainer;
        if (frameLayout2 == null) {
            return;
        }
        int id2 = frameLayout2.getId();
        Fragment fragmentFindFragmentById = getSupportFragmentManager().findFragmentById(id);
        Fragment fragmentFindFragmentById2 = getSupportFragmentManager().findFragmentById(id2);
        if (Intrinsics.areEqual(fragmentAudioStatsV2, fragmentFindFragmentById) && Intrinsics.areEqual(fragmentAudioStatsV22, fragmentFindFragmentById2)) {
            return;
        }
        if (fragmentFindFragmentById != null || fragmentFindFragmentById2 != null) {
            FragmentTransaction fragmentTransactionBeginTransaction = getSupportFragmentManager().beginTransaction();
            Intrinsics.checkNotNullExpressionValue(fragmentTransactionBeginTransaction, "supportFragmentManager.beginTransaction()");
            if (fragmentFindFragmentById != null) {
                fragmentTransactionBeginTransaction.remove(fragmentFindFragmentById);
            }
            if (fragmentFindFragmentById2 != null) {
                fragmentTransactionBeginTransaction.remove(fragmentFindFragmentById2);
            }
            fragmentTransactionBeginTransaction.commit();
            getSupportFragmentManager().executePendingTransactions();
        }
        FragmentTransaction fragmentTransactionBeginTransaction2 = getSupportFragmentManager().beginTransaction();
        Intrinsics.checkNotNullExpressionValue(fragmentTransactionBeginTransaction2, "supportFragmentManager.beginTransaction()");
        if (fragmentAudioStatsV2 != null) {
            fragmentTransactionBeginTransaction2.replace(id, fragmentAudioStatsV2);
        }
        if (fragmentAudioStatsV22 != null) {
            fragmentTransactionBeginTransaction2.replace(id2, fragmentAudioStatsV22);
        }
        fragmentTransactionBeginTransaction2.commit();
    }

    private final void configureViewPager() {
        ViewPager2 viewPager2 = this.viewPager;
        if (viewPager2 == null) {
            return;
        }
        viewPager2.setOffscreenPageLimit(1);
        final float dimension = getResources().getDimension(R.dimen.nerdstats_next_signalflow_visible) + getResources().getDimension(R.dimen.nerdstats_current_signalflow_horizontal_margin);
        viewPager2.setPageTransformer(new ViewPager2.PageTransformer() { // from class: com.amazon.mp3.audioquality.nerdstats.AudioStatsFragmentActivity$$ExternalSyntheticLambda5
            @Override // androidx.viewpager2.widget.ViewPager2.PageTransformer
            public final void transformPage(View view, float f) {
                AudioStatsFragmentActivity.m230configureViewPager$lambda22$lambda21(dimension, view, f);
            }
        });
        viewPager2.setClipToPadding(false);
        viewPager2.setSaveFromParentEnabled(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: configureViewPager$lambda-22$lambda-21, reason: not valid java name */
    public static final void m230configureViewPager$lambda22$lambda21(float f, View page, float f2) {
        Intrinsics.checkNotNullParameter(page, "page");
        page.setTranslationX((-f) * f2);
    }

    private final void updateViewPagerCallback(boolean enableSpatialToggle, final SpatialToggleData spatialToggleData) {
        final ViewPager2 viewPager2 = this.viewPager;
        if (viewPager2 == null) {
            return;
        }
        viewPager2.setUserInputEnabled(enableSpatialToggle);
        if (enableSpatialToggle) {
            ViewPager2.OnPageChangeCallback onPageChangeCallback = new ViewPager2.OnPageChangeCallback() { // from class: com.amazon.mp3.audioquality.nerdstats.AudioStatsFragmentActivity$updateViewPagerCallback$1$1
                @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
                public void onPageSelected(int position) {
                    if (position == AudioStatsFragmentActivity.LEFT_INDEX) {
                        RadioButton radioButton = this.this$0.spatialToggleButton;
                        if ((radioButton == null || radioButton.isChecked()) ? false : true) {
                            RadioButton radioButton2 = this.this$0.spatialToggleButton;
                            if (radioButton2 != null) {
                                radioButton2.setChecked(true);
                            }
                            viewPager2.removeItemDecorationAt(0);
                            viewPager2.addItemDecoration(this.this$0.getNerdStatsSpacerDecoration(AudioStatsFragmentActivity.LEFT_INDEX));
                            this.this$0.toggleSpatialMode(true, spatialToggleData, InteractionType.SWIPE);
                        }
                    } else if (position == AudioStatsFragmentActivity.RIGHT_INDEX) {
                        RadioButton radioButton3 = this.this$0.stereoToggleButton;
                        if ((radioButton3 == null || radioButton3.isChecked()) ? false : true) {
                            RadioButton radioButton4 = this.this$0.stereoToggleButton;
                            if (radioButton4 != null) {
                                radioButton4.setChecked(true);
                            }
                            viewPager2.removeItemDecorationAt(0);
                            viewPager2.addItemDecoration(this.this$0.getNerdStatsSpacerDecoration(AudioStatsFragmentActivity.RIGHT_INDEX));
                            this.this$0.toggleSpatialMode(false, spatialToggleData, InteractionType.SWIPE);
                        }
                    }
                    super.onPageSelected(position);
                }
            };
            this.viewPagerCallback = onPageChangeCallback;
            viewPager2.registerOnPageChangeCallback(onPageChangeCallback);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void toggleSpatialMode(boolean expectedSpatialModeState, SpatialToggleData spatialToggleData, InteractionType interactionType) {
        ActionType actionType;
        boolean z = !SettingsUtil.isSpatialModeSettingEnabled(this);
        if (z != expectedSpatialModeState) {
            Log.error(TAG, "Ignoring toggleSpatialMode(), setting is already %s", Boolean.valueOf(expectedSpatialModeState));
            return;
        }
        SettingsUtil.setSpatialMode(this, z);
        PlaybackInitializationHelper.updateSpatialMode(this);
        if (spatialToggleData != null) {
            String string = getResources().getString(R.string.dmusic_ivy_spatial_mode_toast_message, z ? spatialToggleData.getSpatialAnnouncement() : spatialToggleData.getStereoAnnouncement());
            Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.st…oast_message, newQuality)");
            BauhausToastUtils.showTextToast(this, string, 0);
        }
        if (!this.playbackController.isStopped()) {
            this.playbackController.play();
        }
        if (z) {
            actionType = ActionType.SELECT_ATMOS_360_ON;
        } else {
            actionType = ActionType.SELECT_ATMOS_360_OFF;
        }
        sendUIClickMetric(actionType, ContentName.STREAMING_INFORMATION_DIALOG.toString(), spatialToggleData == null ? null : spatialToggleData.getAsin(), interactionType);
        Log.debug(TAG, Intrinsics.stringPlus("Spatial Mode is Enabled: ", Boolean.valueOf(z)));
    }

    private final void updateDualContainerClickListeners(boolean enableSpatialToggle, final SpatialToggleData spatialToggleData) {
        if (enableSpatialToggle) {
            FrameLayout frameLayout = this.tabletSignalFlowLeftContainer;
            if (frameLayout != null) {
                frameLayout.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.audioquality.nerdstats.AudioStatsFragmentActivity$$ExternalSyntheticLambda7
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        AudioStatsFragmentActivity.m236updateDualContainerClickListeners$lambda25(this.f$0, spatialToggleData, view);
                    }
                });
            }
            FrameLayout frameLayout2 = this.tabletSignalFlowRightContainer;
            if (frameLayout2 == null) {
                return;
            }
            frameLayout2.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.audioquality.nerdstats.AudioStatsFragmentActivity$$ExternalSyntheticLambda8
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AudioStatsFragmentActivity.m237updateDualContainerClickListeners$lambda26(this.f$0, spatialToggleData, view);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: updateDualContainerClickListeners$lambda-25, reason: not valid java name */
    public static final void m236updateDualContainerClickListeners$lambda25(AudioStatsFragmentActivity this$0, SpatialToggleData spatialToggleData, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.toggleSpatialMode(true, spatialToggleData, InteractionType.TAP);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: updateDualContainerClickListeners$lambda-26, reason: not valid java name */
    public static final void m237updateDualContainerClickListeners$lambda26(AudioStatsFragmentActivity this$0, SpatialToggleData spatialToggleData, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.toggleSpatialMode(false, spatialToggleData, InteractionType.TAP);
    }

    private final void sendUIClickMetric(ActionType actionType, String contentName, String asin, InteractionType interactionType) {
        if (interactionType == null) {
            interactionType = InteractionType.TAP;
        }
        MetricsLogger.sendEvent(UiClickEvent.builder(actionType).withInteractionType(interactionType).withContentInfo(MetricsLogger.getContentInfo(contentName)).withPageType(PageType.NERD_STATS).withEntityId(asin).withEntityIdType(EntityIdType.ASIN).withEntityType(EntityType.TRACK).withEventTime(System.currentTimeMillis()).build());
    }

    /* JADX INFO: compiled from: AudioStatsFragmentActivity.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0017J\b\u0010\f\u001a\u00020\u000bH\u0016R\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\b¨\u0006\r"}, d2 = {"Lcom/amazon/mp3/audioquality/nerdstats/AudioStatsFragmentActivity$ViewPagerAdapter;", "Landroidx/viewpager2/adapter/FragmentStateAdapter;", "fa", "Landroidx/fragment/app/FragmentActivity;", "fragments", "", "Lcom/amazon/mp3/audioquality/nerdstats/FragmentAudioStatsV2;", "(Landroidx/fragment/app/FragmentActivity;[Lcom/amazon/mp3/audioquality/nerdstats/FragmentAudioStatsV2;)V", "[Lcom/amazon/mp3/audioquality/nerdstats/FragmentAudioStatsV2;", "createFragment", "position", "", "getItemCount", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    private static final class ViewPagerAdapter extends FragmentStateAdapter {
        private final FragmentAudioStatsV2[] fragments;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewPagerAdapter(FragmentActivity fa, FragmentAudioStatsV2[] fragments) {
            super(fa);
            Intrinsics.checkNotNullParameter(fa, "fa");
            Intrinsics.checkNotNullParameter(fragments, "fragments");
            this.fragments = fragments;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.fragments.length;
        }

        @Override // androidx.viewpager2.adapter.FragmentStateAdapter
        @NonNull
        public FragmentAudioStatsV2 createFragment(int position) {
            FragmentAudioStatsV2[] fragmentAudioStatsV2Arr = this.fragments;
            if (position < fragmentAudioStatsV2Arr.length) {
                return fragmentAudioStatsV2Arr[position];
            }
            throw new IndexOutOfBoundsException("Unsupported call to createFragment(" + position + ')');
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: AudioStatsFragmentActivity.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J(\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016R\u000e\u0010\b\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/amazon/mp3/audioquality/nerdstats/AudioStatsFragmentActivity$HorizontalMarginItemDecoration;", "Landroidx/recyclerview/widget/RecyclerView$ItemDecoration;", "context", "Landroid/content/Context;", "horizontalMarginInDp", "", "position", "(Lcom/amazon/mp3/audioquality/nerdstats/AudioStatsFragmentActivity;Landroid/content/Context;II)V", "horizontalMarginInPx", "getItemOffsets", "", "outRect", "Landroid/graphics/Rect;", "view", "Landroid/view/View;", "parent", "Landroidx/recyclerview/widget/RecyclerView;", "state", "Landroidx/recyclerview/widget/RecyclerView$State;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    final class HorizontalMarginItemDecoration extends RecyclerView.ItemDecoration {
        private final int horizontalMarginInPx;
        private final int position;
        final /* synthetic */ AudioStatsFragmentActivity this$0;

        public HorizontalMarginItemDecoration(AudioStatsFragmentActivity this$0, @DimenRes Context context, int i, int i2) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(context, "context");
            this.this$0 = this$0;
            this.horizontalMarginInPx = (int) context.getResources().getDimension(i);
            this.position = i2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            Intrinsics.checkNotNullParameter(outRect, "outRect");
            Intrinsics.checkNotNullParameter(view, "view");
            Intrinsics.checkNotNullParameter(parent, "parent");
            Intrinsics.checkNotNullParameter(state, "state");
            if (this.position == AudioStatsFragmentActivity.LEFT_INDEX) {
                outRect.right = this.horizontalMarginInPx + ((int) this.this$0.getResources().getDimension(R.dimen.spacer_28));
                outRect.left = this.horizontalMarginInPx;
            } else {
                outRect.left = this.horizontalMarginInPx + ((int) this.this$0.getResources().getDimension(R.dimen.spacer_28));
                outRect.right = this.horizontalMarginInPx;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final HorizontalMarginItemDecoration getNerdStatsSpacerDecoration(int position) {
        return new HorizontalMarginItemDecoration(this, this, R.dimen.nerdstats_current_signalflow_horizontal_margin, position);
    }

    /* JADX INFO: compiled from: AudioStatsFragmentActivity.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\b\u0010\b\u001a\u00020\u0007H\u0016J\b\u0010\t\u001a\u00020\u0007H\u0016J\b\u0010\n\u001a\u00020\u0007H\u0016J\u0010\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0010H\u0016R\u000e\u0010\u0005\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/amazon/mp3/audioquality/nerdstats/AudioStatsFragmentActivity$NerdStatsActionBarProvider;", "Lcom/amazon/mp3/actionbar/ActionBarProvider;", "supportBar", "Landroidx/appcompat/app/ActionBar;", "(Landroidx/appcompat/app/ActionBar;)V", "actionBar", "removeHeaderView", "", "requestHomeButtonAsBack", "requestHomeButtonAsStoreClose", "restoreHomeButton", "setHeaderView", "headerView", "Landroid/view/View;", "setHomeButtonVisibility", "visible", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    private static final class NerdStatsActionBarProvider implements ActionBarProvider {
        private final ActionBar actionBar;

        public NerdStatsActionBarProvider(ActionBar supportBar) {
            Intrinsics.checkNotNullParameter(supportBar, "supportBar");
            this.actionBar = supportBar;
            supportBar.setDisplayShowTitleEnabled(false);
            supportBar.setDisplayShowHomeEnabled(true);
        }

        @Override // com.amazon.mp3.actionbar.ActionBarProvider
        public void setHeaderView(View headerView) {
            Intrinsics.checkNotNullParameter(headerView, "headerView");
            this.actionBar.setDisplayShowCustomEnabled(true);
            this.actionBar.setCustomView(headerView);
        }

        @Override // com.amazon.mp3.actionbar.ActionBarProvider
        public void removeHeaderView() {
            this.actionBar.setDisplayShowCustomEnabled(false);
        }

        @Override // com.amazon.mp3.actionbar.ActionBarProvider
        public void requestHomeButtonAsBack() {
            this.actionBar.setDisplayHomeAsUpEnabled(true);
        }

        @Override // com.amazon.mp3.actionbar.ActionBarProvider
        public void requestHomeButtonAsStoreClose() {
            this.actionBar.setDisplayHomeAsUpEnabled(true);
        }

        @Override // com.amazon.mp3.actionbar.ActionBarProvider
        public void restoreHomeButton() {
            this.actionBar.setDisplayHomeAsUpEnabled(false);
        }

        @Override // com.amazon.mp3.actionbar.ActionBarProvider
        public void setHomeButtonVisibility(int visible) {
            this.actionBar.setDisplayShowHomeEnabled(visible == 0);
        }
    }
}
