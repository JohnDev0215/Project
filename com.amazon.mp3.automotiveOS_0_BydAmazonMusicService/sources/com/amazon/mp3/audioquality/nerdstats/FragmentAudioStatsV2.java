package com.amazon.mp3.audioquality.nerdstats;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import com.amazon.mp3.activity.WideDialogActivity;
import com.amazon.mp3.activity.WideDialogActivityPurpose;
import com.amazon.mp3.audioquality.nerdstats.models.NerdStats;
import com.amazon.mp3.audioquality.nerdstats.models.NerdStatsDataModels$SignalFlowState;
import com.amazon.mp3.audioquality.nerdstats.models.NerdStatsDataModels$SignalFlowType;
import com.amazon.mp3.audioquality.nerdstats.models.NerdStatsReducedQualityAlertType;
import com.amazon.mp3.audioquality.nerdstats.models.NerdStatsSignalFlowStats;
import com.amazon.mp3.audioquality.nerdstats.models.NerdStatsViewModel;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.environment.url.EndpointsProvider;
import com.amazon.mp3.fragment.BaseFragment;
import com.amazon.mp3.hawkfire.upsell.UnsupportedMarketplaceException;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.util.Log;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.google.firebase.perf.util.Constants;
import java.io.Serializable;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: FragmentAudioStatsV2.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u0000 92\u00020\u0001:\u00019B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\b\u0010\u0018\u001a\u00020\u0007H\u0002J\u0018\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\b\u0010\u001e\u001a\u00020\u0007H\u0002J&\u0010\u001f\u001a\u0004\u0018\u00010 2\u0006\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010$2\b\u0010%\u001a\u0004\u0018\u00010&H\u0016J\b\u0010'\u001a\u00020\u0007H\u0016J\b\u0010(\u001a\u00020\u0007H\u0016J\b\u0010)\u001a\u00020\u0007H\u0016J\u0018\u0010*\u001a\u00020\u00072\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.H\u0002J\u000e\u0010/\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u00100\u001a\u00020\u00072\u0006\u00101\u001a\u00020\u001bJ\b\u00102\u001a\u00020\u0007H\u0002J\u0010\u00103\u001a\u0002042\u0006\u00105\u001a\u00020\u001bH\u0002J\u0010\u00106\u001a\u00020\u00072\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\u0010\u00107\u001a\u00020\u00072\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\u0010\u00108\u001a\u00020\u00072\u0006\u0010\u001c\u001a\u00020\u001dH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\f\u001a\u00020\r8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0082.¢\u0006\u0002\n\u0000¨\u0006:"}, d2 = {"Lcom/amazon/mp3/audioquality/nerdstats/FragmentAudioStatsV2;", "Lcom/amazon/mp3/fragment/BaseFragment;", "()V", "animatedDivider", "Landroid/widget/ProgressBar;", "onResumeCallback", "Lkotlin/Function0;", "", "signalFlowType", "Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsDataModels$SignalFlowType;", "viewBinding", "Lcom/amazon/mp3/audioquality/nerdstats/FragmentAudioStatsV2Binding;", "viewModel", "Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsViewModel;", "getViewModel", "()Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "viewModelObserver", "Landroidx/lifecycle/Observer;", "Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsDataModels$NerdStats;", "animateDivider", "state", "Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsDataModels$SignalFlowState;", "bindLearnMoreOnClick", "displayAlert", "shouldDisplayBetweenTrackAndDeviceNode", "", "dataModel", "Lcom/amazon/mp3/audioquality/nerdstats/models/NerdStatsDataModels$NerdStatsSignalFlowStats;", "enqueueViewModelRefresh", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onPause", "onResume", "sendUiClickMetric", "action", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", ContextMappingConstants.CONTENT_INFO, "", "setBorderStyle", "setOpacity", Constants.ENABLE_DISABLE, "setupDividerAnimation", "subtextViewState", "", "isVisible", "updateSignalFlow", "updateSignalFlowAlerts", "updateSignalFlowNodes", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FragmentAudioStatsV2 extends BaseFragment {
    private ProgressBar animatedDivider;
    private Function0<Unit> onResumeCallback;
    private NerdStatsDataModels$SignalFlowType signalFlowType;
    private FragmentAudioStatsV2Binding viewBinding;

    /* JADX INFO: renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(NerdStatsViewModel.class), new Function0<ViewModelStore>() { // from class: com.amazon.mp3.audioquality.nerdstats.FragmentAudioStatsV2$special$$inlined$activityViewModels$default$1
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ViewModelStore invoke() {
            FragmentActivity fragmentActivityRequireActivity = this.requireActivity();
            Intrinsics.checkExpressionValueIsNotNull(fragmentActivityRequireActivity, "requireActivity()");
            ViewModelStore viewModelStore = fragmentActivityRequireActivity.getViewModelStore();
            Intrinsics.checkExpressionValueIsNotNull(viewModelStore, "requireActivity().viewModelStore");
            return viewModelStore;
        }
    }, new Function0<ViewModelProvider.Factory>() { // from class: com.amazon.mp3.audioquality.nerdstats.FragmentAudioStatsV2$special$$inlined$activityViewModels$default$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ViewModelProvider.Factory invoke() {
            FragmentActivity fragmentActivityRequireActivity = this.requireActivity();
            Intrinsics.checkExpressionValueIsNotNull(fragmentActivityRequireActivity, "requireActivity()");
            ViewModelProvider.Factory defaultViewModelProviderFactory = fragmentActivityRequireActivity.getDefaultViewModelProviderFactory();
            Intrinsics.checkExpressionValueIsNotNull(defaultViewModelProviderFactory, "requireActivity().defaultViewModelProviderFactory");
            return defaultViewModelProviderFactory;
        }
    });
    private Observer<NerdStats> viewModelObserver;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = FragmentAudioStatsV2.class.getSimpleName();
    private static final String SIGNAL_FLOW_TYPE_KEY = "signalFlowType";
    private static final float DISABLED_OPACITY = 0.25f;
    private static final float ENABLED_OPACITY = 1.0f;

    /* JADX INFO: compiled from: FragmentAudioStatsV2.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[NerdStatsDataModels$SignalFlowType.values().length];
            iArr[NerdStatsDataModels$SignalFlowType.Spatial.ordinal()] = 1;
            iArr[NerdStatsDataModels$SignalFlowType.Stereo.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[NerdStatsDataModels$SignalFlowState.values().length];
            iArr2[NerdStatsDataModels$SignalFlowState.Active.ordinal()] = 1;
            iArr2[NerdStatsDataModels$SignalFlowState.Inactive.ordinal()] = 2;
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    private final int subtextViewState(boolean isVisible) {
        return isVisible ? 0 : 8;
    }

    private final NerdStatsViewModel getViewModel() {
        return (NerdStatsViewModel) this.viewModel.getValue();
    }

    /* JADX INFO: compiled from: FragmentAudioStatsV2.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u0007X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0016\u0010\n\u001a\n \u000b*\u0004\u0018\u00010\u00070\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/amazon/mp3/audioquality/nerdstats/FragmentAudioStatsV2$Companion;", "", "()V", "DISABLED_OPACITY", "", "ENABLED_OPACITY", "SIGNAL_FLOW_TYPE_KEY", "", "getSIGNAL_FLOW_TYPE_KEY", "()Ljava/lang/String;", "TAG", "kotlin.jvm.PlatformType", "newInstance", "Lcom/amazon/mp3/audioquality/nerdstats/FragmentAudioStatsV2;", "extras", "Landroid/os/Bundle;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getSIGNAL_FLOW_TYPE_KEY() {
            return FragmentAudioStatsV2.SIGNAL_FLOW_TYPE_KEY;
        }

        public final FragmentAudioStatsV2 newInstance(Bundle extras) {
            FragmentAudioStatsV2 fragmentAudioStatsV2 = new FragmentAudioStatsV2();
            fragmentAudioStatsV2.setArguments(extras);
            return fragmentAudioStatsV2;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        Bundle arguments = getArguments();
        if (arguments != null) {
            Serializable serializable = arguments.getSerializable(SIGNAL_FLOW_TYPE_KEY);
            if (serializable == null) {
                throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.audioquality.nerdstats.models.NerdStatsDataModels.SignalFlowType");
            }
            this.signalFlowType = (NerdStatsDataModels$SignalFlowType) serializable;
        }
        View rootView = inflater.inflate(R.layout.audio_stats_page_v2, container, false);
        View viewFindViewById = rootView.findViewById(R.id.nerd_stats_signal_flow_divider);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "rootView.findViewById(R.…tats_signal_flow_divider)");
        this.animatedDivider = (ProgressBar) viewFindViewById;
        Intrinsics.checkNotNullExpressionValue(rootView, "rootView");
        this.viewBinding = new FragmentAudioStatsV2Binding(rootView);
        bindLearnMoreOnClick();
        setupDividerAnimation();
        this.viewModelObserver = new Observer() { // from class: com.amazon.mp3.audioquality.nerdstats.FragmentAudioStatsV2$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FragmentAudioStatsV2.m244onCreateView$lambda2(this.f$0, (NerdStats) obj);
            }
        };
        LiveData<NerdStats> liveData = getViewModel().getLiveData();
        Observer<NerdStats> observer = this.viewModelObserver;
        if (observer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelObserver");
            observer = null;
        }
        liveData.observe(this, observer);
        return rootView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreateView$lambda-2, reason: not valid java name */
    public static final void m244onCreateView$lambda2(FragmentAudioStatsV2 this$0, NerdStats nerdStats) {
        NerdStatsSignalFlowStats spatialStats;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        NerdStatsDataModels$SignalFlowType nerdStatsDataModels$SignalFlowType = this$0.signalFlowType;
        if (nerdStatsDataModels$SignalFlowType == null) {
            Intrinsics.throwUninitializedPropertyAccessException("signalFlowType");
            nerdStatsDataModels$SignalFlowType = null;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[nerdStatsDataModels$SignalFlowType.ordinal()];
        boolean z = true;
        if (i == 1) {
            spatialStats = nerdStats.getSpatialStats();
        } else {
            if (i != 2) {
                throw new NoWhenBranchMatchedException();
            }
            spatialStats = nerdStats.getStereoStats();
        }
        if (spatialStats == null) {
            return;
        }
        this$0.updateSignalFlow(spatialStats);
        if (spatialStats.getSignalFlowState() != NerdStatsDataModels$SignalFlowState.Active && !nerdStats.getEnableSpatialToggle()) {
            z = false;
        }
        this$0.setOpacity(z);
        this$0.setBorderStyle(spatialStats.getSignalFlowState());
        this$0.animateDivider(spatialStats.getSignalFlowState());
    }

    @Override // com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        ProgressBar progressBar = this.animatedDivider;
        if (progressBar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("animatedDivider");
            progressBar = null;
        }
        if (progressBar.isIndeterminate()) {
            animateDivider(NerdStatsDataModels$SignalFlowState.Active);
        }
        Function0<Unit> function0 = this.onResumeCallback;
        if (function0 != null) {
            function0.invoke();
        }
        this.onResumeCallback = null;
    }

    @Override // com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        ProgressBar progressBar = this.animatedDivider;
        if (progressBar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("animatedDivider");
            progressBar = null;
        }
        Drawable indeterminateDrawable = progressBar.getIndeterminateDrawable();
        if (indeterminateDrawable == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.audioquality.nerdstats.NerdStatsAnimatedGradient");
        }
        ((NerdStatsAnimatedGradient) indeterminateDrawable).stop();
    }

    @Override // com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        LiveData<NerdStats> liveData = getViewModel().getLiveData();
        Observer<NerdStats> observer = this.viewModelObserver;
        if (observer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelObserver");
            observer = null;
        }
        liveData.removeObserver(observer);
        FragmentAudioStatsV2Binding fragmentAudioStatsV2Binding = this.viewBinding;
        if (fragmentAudioStatsV2Binding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewBinding");
            fragmentAudioStatsV2Binding = null;
        }
        fragmentAudioStatsV2Binding.getHeaderQuality().setOnClickListener(null);
        ProgressBar progressBar = this.animatedDivider;
        if (progressBar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("animatedDivider");
            progressBar = null;
        }
        progressBar.setIndeterminateDrawable(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void enqueueViewModelRefresh() {
        new Handler(Looper.getMainLooper()).postAtFrontOfQueue(new Runnable() { // from class: com.amazon.mp3.audioquality.nerdstats.FragmentAudioStatsV2$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                FragmentAudioStatsV2.m243enqueueViewModelRefresh$lambda3(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: enqueueViewModelRefresh$lambda-3, reason: not valid java name */
    public static final void m243enqueueViewModelRefresh$lambda3(FragmentAudioStatsV2 this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getViewModel().refresh(NerdStatsViewModel.RefreshReason.PlayStateChange);
    }

    private final void setupDividerAnimation() {
        float fApplyDimension = TypedValue.applyDimension(1, 200.0f, getResources().getDisplayMetrics());
        ProgressBar progressBar = this.animatedDivider;
        ProgressBar progressBar2 = null;
        if (progressBar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("animatedDivider");
            progressBar = null;
        }
        Context context = getContext();
        progressBar.setIndeterminateDrawable(context == null ? null : new NerdStatsAnimatedGradient(fApplyDimension, context));
        ProgressBar progressBar3 = this.animatedDivider;
        if (progressBar3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("animatedDivider");
        } else {
            progressBar2 = progressBar3;
        }
        Drawable indeterminateDrawable = progressBar2.getIndeterminateDrawable();
        if (indeterminateDrawable == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.audioquality.nerdstats.NerdStatsAnimatedGradient");
        }
        ((NerdStatsAnimatedGradient) indeterminateDrawable).stop();
    }

    private final void animateDivider(NerdStatsDataModels$SignalFlowState state) {
        ProgressBar progressBar = this.animatedDivider;
        ProgressBar progressBar2 = null;
        if (progressBar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("animatedDivider");
            progressBar = null;
        }
        Drawable indeterminateDrawable = progressBar.getIndeterminateDrawable();
        if (indeterminateDrawable == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.audioquality.nerdstats.NerdStatsAnimatedGradient");
        }
        NerdStatsAnimatedGradient nerdStatsAnimatedGradient = (NerdStatsAnimatedGradient) indeterminateDrawable;
        NerdStatsDataModels$SignalFlowState nerdStatsDataModels$SignalFlowState = NerdStatsDataModels$SignalFlowState.Active;
        if (state != nerdStatsDataModels$SignalFlowState) {
            nerdStatsAnimatedGradient.stop();
        } else {
            nerdStatsAnimatedGradient.start();
        }
        ProgressBar progressBar3 = this.animatedDivider;
        if (progressBar3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("animatedDivider");
        } else {
            progressBar2 = progressBar3;
        }
        progressBar2.setIndeterminate(state == nerdStatsDataModels$SignalFlowState);
    }

    public final void setBorderStyle(NerdStatsDataModels$SignalFlowState state) {
        Drawable drawable;
        Intrinsics.checkNotNullParameter(state, "state");
        FragmentAudioStatsV2Binding fragmentAudioStatsV2Binding = this.viewBinding;
        if (fragmentAudioStatsV2Binding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewBinding");
            fragmentAudioStatsV2Binding = null;
        }
        ConstraintLayout signalFlowLayoutBorder = fragmentAudioStatsV2Binding.getSignalFlowLayoutBorder();
        if (signalFlowLayoutBorder == null) {
            return;
        }
        int i = WhenMappings.$EnumSwitchMapping$1[state.ordinal()];
        if (i == 1) {
            drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.nerd_stats_signal_flow_background_focused, null);
        } else {
            if (i != 2) {
                throw new NoWhenBranchMatchedException();
            }
            drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.nerd_stats_signal_flow_background, null);
        }
        signalFlowLayoutBorder.setBackground(drawable);
    }

    public final void setOpacity(boolean isEnabled) {
        float f = isEnabled ? ENABLED_OPACITY : DISABLED_OPACITY;
        FragmentAudioStatsV2Binding fragmentAudioStatsV2Binding = this.viewBinding;
        if (fragmentAudioStatsV2Binding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewBinding");
            fragmentAudioStatsV2Binding = null;
        }
        ConstraintLayout signalFlowLayoutBorder = fragmentAudioStatsV2Binding.getSignalFlowLayoutBorder();
        if (signalFlowLayoutBorder == null) {
            return;
        }
        signalFlowLayoutBorder.setAlpha(f);
    }

    private final void bindLearnMoreOnClick() {
        FragmentAudioStatsV2Binding fragmentAudioStatsV2Binding = this.viewBinding;
        if (fragmentAudioStatsV2Binding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewBinding");
            fragmentAudioStatsV2Binding = null;
        }
        fragmentAudioStatsV2Binding.getHeaderQuality().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.audioquality.nerdstats.FragmentAudioStatsV2$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentAudioStatsV2.m242bindLearnMoreOnClick$lambda7(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bindLearnMoreOnClick$lambda-7, reason: not valid java name */
    public static final void m242bindLearnMoreOnClick$lambda7(FragmentAudioStatsV2 this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        try {
            String string = EndpointsProvider.get().getKatanaLearnMoreEndpoint().toString();
            if (string == null) {
                return;
            }
            Uri uri = Uri.parse(string);
            Intrinsics.checkNotNullExpressionValue(uri, "parse(url)");
            this$0.startActivity(new Intent("android.intent.action.VIEW", uri));
        } catch (UnsupportedMarketplaceException e) {
            Log.error(TAG, "Localized endpoint not available for this locale", e);
        }
    }

    private final void updateSignalFlow(NerdStatsSignalFlowStats dataModel) {
        Context context = getContext();
        FragmentAudioStatsV2Binding fragmentAudioStatsV2Binding = null;
        if (context != null) {
            FragmentAudioStatsV2Binding fragmentAudioStatsV2Binding2 = this.viewBinding;
            if (fragmentAudioStatsV2Binding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewBinding");
                fragmentAudioStatsV2Binding2 = null;
            }
            ImageView headerImage = fragmentAudioStatsV2Binding2.getHeaderImage();
            if (headerImage != null) {
                headerImage.setImageDrawable(ContextCompat.getDrawable(context, dataModel.getTrackBadging().getImageId()));
            }
        }
        FragmentAudioStatsV2Binding fragmentAudioStatsV2Binding3 = this.viewBinding;
        if (fragmentAudioStatsV2Binding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewBinding");
            fragmentAudioStatsV2Binding3 = null;
        }
        ImageView headerImage2 = fragmentAudioStatsV2Binding3.getHeaderImage();
        if (headerImage2 != null) {
            headerImage2.setVisibility(0);
        }
        FragmentAudioStatsV2Binding fragmentAudioStatsV2Binding4 = this.viewBinding;
        if (fragmentAudioStatsV2Binding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewBinding");
            fragmentAudioStatsV2Binding4 = null;
        }
        fragmentAudioStatsV2Binding4.getHeaderQuality().setText(dataModel.getTrackBadging().getTitle());
        FragmentAudioStatsV2Binding fragmentAudioStatsV2Binding5 = this.viewBinding;
        if (fragmentAudioStatsV2Binding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewBinding");
        } else {
            fragmentAudioStatsV2Binding = fragmentAudioStatsV2Binding5;
        }
        fragmentAudioStatsV2Binding.getHeaderQualityMessage().setText(dataModel.getTrackBadging().getDescription());
        updateSignalFlowNodes(dataModel);
        updateSignalFlowAlerts(dataModel);
    }

    private final void updateSignalFlowNodes(NerdStatsSignalFlowStats dataModel) {
        Context context = getContext();
        FragmentAudioStatsV2Binding fragmentAudioStatsV2Binding = null;
        if (context != null) {
            FragmentAudioStatsV2Binding fragmentAudioStatsV2Binding2 = this.viewBinding;
            if (fragmentAudioStatsV2Binding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewBinding");
                fragmentAudioStatsV2Binding2 = null;
            }
            fragmentAudioStatsV2Binding2.getTrackNodeQuality().setText(context.getString(R.string.dmusic_nerd_stats_track_quality, dataModel.getTrackQuality().getTitle()));
            FragmentAudioStatsV2Binding fragmentAudioStatsV2Binding3 = this.viewBinding;
            if (fragmentAudioStatsV2Binding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewBinding");
                fragmentAudioStatsV2Binding3 = null;
            }
            fragmentAudioStatsV2Binding3.getTrackNodeSubtext().setText(dataModel.getTrackQuality().getSubtitle());
            FragmentAudioStatsV2Binding fragmentAudioStatsV2Binding4 = this.viewBinding;
            if (fragmentAudioStatsV2Binding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewBinding");
                fragmentAudioStatsV2Binding4 = null;
            }
            fragmentAudioStatsV2Binding4.getDeviceNodeName().setText(context.getString(R.string.dmusic_nerd_stats_device, dataModel.getDeviceQuality().getTitle()));
            FragmentAudioStatsV2Binding fragmentAudioStatsV2Binding5 = this.viewBinding;
            if (fragmentAudioStatsV2Binding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewBinding");
                fragmentAudioStatsV2Binding5 = null;
            }
            fragmentAudioStatsV2Binding5.getDeviceNodeSubtext().setText(dataModel.getDeviceQuality().getSubtitle());
            Drawable drawable = ContextCompat.getDrawable(context, dataModel.getOutputQuality().getImageId());
            if (drawable != null) {
                drawable.setTint(ResourcesCompat.getColor(context.getResources(), R.color.white, null));
            }
            FragmentAudioStatsV2Binding fragmentAudioStatsV2Binding6 = this.viewBinding;
            if (fragmentAudioStatsV2Binding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewBinding");
                fragmentAudioStatsV2Binding6 = null;
            }
            fragmentAudioStatsV2Binding6.getOutputNodeImage().setImageDrawable(ContextCompat.getDrawable(context, dataModel.getOutputQuality().getImageId()));
            FragmentAudioStatsV2Binding fragmentAudioStatsV2Binding7 = this.viewBinding;
            if (fragmentAudioStatsV2Binding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewBinding");
                fragmentAudioStatsV2Binding7 = null;
            }
            fragmentAudioStatsV2Binding7.getOutputNodeName().setText(context.getString(R.string.dmusic_nerd_stats_output, dataModel.getOutputQuality().getTitle()));
            FragmentAudioStatsV2Binding fragmentAudioStatsV2Binding8 = this.viewBinding;
            if (fragmentAudioStatsV2Binding8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewBinding");
                fragmentAudioStatsV2Binding8 = null;
            }
            fragmentAudioStatsV2Binding8.getOutputNodeSubtext().setText(dataModel.getOutputQuality().getSubtitle());
        }
        FragmentAudioStatsV2Binding fragmentAudioStatsV2Binding9 = this.viewBinding;
        if (fragmentAudioStatsV2Binding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewBinding");
            fragmentAudioStatsV2Binding9 = null;
        }
        fragmentAudioStatsV2Binding9.getTrackNodeSubtext().setVisibility(subtextViewState(dataModel.getTrackQuality().getSubtitle().length() > 0));
        FragmentAudioStatsV2Binding fragmentAudioStatsV2Binding10 = this.viewBinding;
        if (fragmentAudioStatsV2Binding10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewBinding");
            fragmentAudioStatsV2Binding10 = null;
        }
        fragmentAudioStatsV2Binding10.getDeviceNodeSubtext().setVisibility(subtextViewState(dataModel.getDeviceQuality().getSubtitle().length() > 0));
        FragmentAudioStatsV2Binding fragmentAudioStatsV2Binding11 = this.viewBinding;
        if (fragmentAudioStatsV2Binding11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewBinding");
        } else {
            fragmentAudioStatsV2Binding = fragmentAudioStatsV2Binding11;
        }
        fragmentAudioStatsV2Binding.getOutputNodeSubtext().setVisibility(subtextViewState(dataModel.getOutputQuality().getSubtitle().length() > 0));
    }

    private final void updateSignalFlowAlerts(NerdStatsSignalFlowStats dataModel) {
        NerdStatsReducedQualityAlertType reducedQualityAlert = dataModel.getReducedQualityAlert();
        FragmentAudioStatsV2Binding fragmentAudioStatsV2Binding = null;
        if (reducedQualityAlert instanceof NerdStatsReducedQualityAlertType.StreamingSetting) {
            displayAlert(true, dataModel);
            final Context context = getContext();
            if (context == null) {
                return;
            }
            FragmentAudioStatsV2Binding fragmentAudioStatsV2Binding2 = this.viewBinding;
            if (fragmentAudioStatsV2Binding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewBinding");
            } else {
                fragmentAudioStatsV2Binding = fragmentAudioStatsV2Binding2;
            }
            fragmentAudioStatsV2Binding.getTrackToDeviceAlert().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.audioquality.nerdstats.FragmentAudioStatsV2$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FragmentAudioStatsV2.m245updateSignalFlowAlerts$lambda12$lambda11(context, this, view);
                }
            });
            return;
        }
        if (reducedQualityAlert instanceof NerdStatsReducedQualityAlertType.DownloadSetting) {
            displayAlert(true, dataModel);
            final Context context2 = getContext();
            if (context2 == null) {
                return;
            }
            FragmentAudioStatsV2Binding fragmentAudioStatsV2Binding3 = this.viewBinding;
            if (fragmentAudioStatsV2Binding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewBinding");
            } else {
                fragmentAudioStatsV2Binding = fragmentAudioStatsV2Binding3;
            }
            fragmentAudioStatsV2Binding.getTrackToDeviceAlert().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.audioquality.nerdstats.FragmentAudioStatsV2$$ExternalSyntheticLambda3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FragmentAudioStatsV2.m246updateSignalFlowAlerts$lambda14$lambda13(context2, this, view);
                }
            });
            return;
        }
        FragmentAudioStatsV2Binding fragmentAudioStatsV2Binding4 = this.viewBinding;
        if (fragmentAudioStatsV2Binding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewBinding");
            fragmentAudioStatsV2Binding4 = null;
        }
        fragmentAudioStatsV2Binding4.getTrackToDeviceAlert().setVisibility(8);
        FragmentAudioStatsV2Binding fragmentAudioStatsV2Binding5 = this.viewBinding;
        if (fragmentAudioStatsV2Binding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewBinding");
        } else {
            fragmentAudioStatsV2Binding = fragmentAudioStatsV2Binding5;
        }
        fragmentAudioStatsV2Binding.getDeviceToOutputAlert().setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: updateSignalFlowAlerts$lambda-12$lambda-11, reason: not valid java name */
    public static final void m245updateSignalFlowAlerts$lambda12$lambda11(Context ctx, FragmentAudioStatsV2 this$0, View view) {
        Intrinsics.checkNotNullParameter(ctx, "$ctx");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        WideDialogActivity.Companion.showActivity$default(WideDialogActivity.INSTANCE, ctx, WideDialogActivityPurpose.SHOW_KATANA_AUDIO_QUALITY_PREFERENCES, R.style.Theme_AmazonMP3_Dialog, null, 8, null);
        this$0.sendUiClickMetric(ActionType.GO_AUDIO_QUALITY_SETTINGS, "goAudioQualitySettings");
        this$0.onResumeCallback = new FragmentAudioStatsV2$updateSignalFlowAlerts$1$1$1(this$0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: updateSignalFlowAlerts$lambda-14$lambda-13, reason: not valid java name */
    public static final void m246updateSignalFlowAlerts$lambda14$lambda13(Context ctx, FragmentAudioStatsV2 this$0, View view) {
        Intrinsics.checkNotNullParameter(ctx, "$ctx");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        WideDialogActivity.Companion.showActivity$default(WideDialogActivity.INSTANCE, ctx, WideDialogActivityPurpose.SHOW_KATANA_DOWNLOAD_QUALITY_PREFERENCES, R.style.Theme_AmazonMP3_Dialog, null, 8, null);
        this$0.sendUiClickMetric(ActionType.GO_DOWNLOAD_SETTINGS, "goDownloadSettings");
        this$0.onResumeCallback = new FragmentAudioStatsV2$updateSignalFlowAlerts$2$1$1(this$0);
    }

    private final void displayAlert(boolean shouldDisplayBetweenTrackAndDeviceNode, NerdStatsSignalFlowStats dataModel) {
        Unit unit;
        String message = dataModel.getReducedQualityAlert().getMessage();
        FragmentAudioStatsV2Binding fragmentAudioStatsV2Binding = null;
        if (message == null) {
            unit = null;
        } else {
            if (shouldDisplayBetweenTrackAndDeviceNode) {
                FragmentAudioStatsV2Binding fragmentAudioStatsV2Binding2 = this.viewBinding;
                if (fragmentAudioStatsV2Binding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewBinding");
                    fragmentAudioStatsV2Binding2 = null;
                }
                fragmentAudioStatsV2Binding2.getTrackToDeviceAlert().setText(message);
                FragmentAudioStatsV2Binding fragmentAudioStatsV2Binding3 = this.viewBinding;
                if (fragmentAudioStatsV2Binding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewBinding");
                    fragmentAudioStatsV2Binding3 = null;
                }
                fragmentAudioStatsV2Binding3.getTrackToDeviceAlert().setVisibility(0);
                FragmentAudioStatsV2Binding fragmentAudioStatsV2Binding4 = this.viewBinding;
                if (fragmentAudioStatsV2Binding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewBinding");
                    fragmentAudioStatsV2Binding4 = null;
                }
                fragmentAudioStatsV2Binding4.getDeviceToOutputAlert().setVisibility(8);
            } else {
                FragmentAudioStatsV2Binding fragmentAudioStatsV2Binding5 = this.viewBinding;
                if (fragmentAudioStatsV2Binding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewBinding");
                    fragmentAudioStatsV2Binding5 = null;
                }
                fragmentAudioStatsV2Binding5.getDeviceToOutputAlert().setText(message);
                FragmentAudioStatsV2Binding fragmentAudioStatsV2Binding6 = this.viewBinding;
                if (fragmentAudioStatsV2Binding6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewBinding");
                    fragmentAudioStatsV2Binding6 = null;
                }
                fragmentAudioStatsV2Binding6.getDeviceToOutputAlert().setVisibility(0);
                FragmentAudioStatsV2Binding fragmentAudioStatsV2Binding7 = this.viewBinding;
                if (fragmentAudioStatsV2Binding7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewBinding");
                    fragmentAudioStatsV2Binding7 = null;
                }
                fragmentAudioStatsV2Binding7.getTrackToDeviceAlert().setVisibility(8);
            }
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            FragmentAudioStatsV2Binding fragmentAudioStatsV2Binding8 = this.viewBinding;
            if (fragmentAudioStatsV2Binding8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewBinding");
                fragmentAudioStatsV2Binding8 = null;
            }
            fragmentAudioStatsV2Binding8.getTrackToDeviceAlert().setVisibility(8);
            FragmentAudioStatsV2Binding fragmentAudioStatsV2Binding9 = this.viewBinding;
            if (fragmentAudioStatsV2Binding9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewBinding");
            } else {
                fragmentAudioStatsV2Binding = fragmentAudioStatsV2Binding9;
            }
            fragmentAudioStatsV2Binding.getDeviceToOutputAlert().setVisibility(8);
        }
    }

    private final void sendUiClickMetric(ActionType action, String contentInfo) {
        MetricsLogger.sendEvent(UiClickEvent.builder(action).withInteractionType(InteractionType.TAP).withContentInfo(MetricsLogger.getContentInfo(contentInfo)).withEventTime(System.currentTimeMillis()).build());
    }
}
