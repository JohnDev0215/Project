package com.amazon.music.skyfire.ui.fragment;

import CoreInterface.v1_0.Method;
import CoreInterface.v1_0.Template;
import ExternalActionInterface.v1_0.PlayableContent;
import ExternalActionInterface.v1_0.PreFetchMethod;
import ExternalActionInterface.v1_0.ShowUpsellMethod;
import ExternalActionInterface.v1_0.ToggleButtonMethod;
import ExternalActionInterface.v1_0.Track;
import ExternalActionInterface.v1_0.TriggerExternalActionMethod;
import ExternalActionInterface.v1_0.TriggerExternalContextMenuMethod;
import ExternalActionInterface.v1_0.TriggerExternalGenericPlaybackMethod;
import ExternalActionInterface.v1_0.TriggerExternalPlaybackMethod;
import ExternalActionInterface.v1_0.Video;
import ExternalUriInterface.v1_0.NavigateToExternalUriMethod;
import InteractionInterface.v1_0.InvokeHttpSkillMethod;
import TemplateContainerInterface.v1_0.BindStandaloneContainerMethod;
import TemplateContainerInterface.v1_0.CreateAndBindStandaloneContainerMethod;
import TemplateContainerInterface.v1_0.CreateStandaloneContainerMethod;
import Touch.SelectionTemplateInterface.v1_0.ChangeSelectionMethod;
import Touch.SelectionTemplateInterface.v1_0.ConfirmSelectionMethod;
import Touch.SelectionTemplateInterface.v1_0.OpenSelectionMethod;
import Touch.SelectionTemplateInterface.v1_0.SelectionTemplate;
import Touch.WidgetsInterface.v1_0.ButtonElement;
import Touch.WidgetsInterface.v1_0.WidgetElement;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.amazon.music.skyfire.SkyFireApplication;
import com.amazon.music.skyfire.core.performance.SkillLatencyTracker;
import com.amazon.music.skyfire.ui.Constants;
import com.amazon.music.skyfire.ui.ktx.AnyKt;
import com.amazon.music.skyfire.ui.ktx.SkyFireKt;
import com.amazon.music.skyfire.ui.providers.ContextMenuProvider;
import com.amazon.music.skyfire.ui.providers.ElementBindingProvider;
import com.amazon.music.skyfire.ui.providers.ExternalActionProvider;
import com.amazon.music.skyfire.ui.providers.NavigationProvider;
import com.amazon.music.skyfire.ui.providers.PlaybackProvider;
import com.amazon.music.skyfire.ui.providers.SkyFireConfigurationProvider;
import com.amazon.music.skyfire.ui.providers.SkyFireProvider;
import com.amazon.music.skyfire.ui.providers.StyleSheetProvider;
import com.amazon.music.skyfire.ui.providers.UpsellProvider;
import com.amazon.music.skyfire.ui.skyfire.DispatcherContext;
import com.amazon.music.skyfire.ui.skyfire.Mapper;
import com.amazon.music.skyfire.ui.skyfire.Methods;
import com.amazon.music.skyfire.ui.skyfire.SkillMethodBuilder;
import com.amazon.music.skyfire.ui.skyfire.TemplateMethodListener;
import com.amazon.music.skyfire.ui.viewmodel.TemplateViewModel;
import com.amazon.music.skyfire.utils.SkillPathUtils;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.ui.foundations.views.BaseButton;
import dagger.android.support.AndroidSupportInjection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: TemplateFragment.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u0000 ¢\u0001*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u00032\b\u0012\u0004\u0012\u0002H\u00010\u00042\u00020\u0005:\u0002¢\u0001B\u0005¢\u0006\u0002\u0010\u0006J\b\u0010h\u001a\u00020iH\u0002J\u0010\u0010j\u001a\u00020i2\u0006\u0010W\u001a\u00020kH\u0002J\b\u0010l\u001a\u00020iH\u0014J\u001c\u0010m\u001a\u0004\u0018\u00010\u001a2\u0012\b\u0002\u0010n\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u001a\u0018\u00010oJ\u0018\u0010p\u001a\u0004\u0018\u00010\u001a2\f\u0010q\u001a\b\u0012\u0004\u0012\u00020s0rH\u0002J\n\u0010t\u001a\u0004\u0018\u00010\u001aH\u0002J\u000e\u0010u\u001a\u00020i2\u0006\u0010v\u001a\u00020sJ\u0015\u0010w\u001a\u00020i2\u0006\u0010W\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010xJ\u0010\u0010y\u001a\u00020V2\u0006\u0010z\u001a\u00020{H\u0016J\u0012\u0010|\u001a\u00020i2\b\u0010}\u001a\u0004\u0018\u00010~H\u0016J(\u0010\u007f\u001a\u00020i2\b\u0010\u0080\u0001\u001a\u00030\u0081\u00012\b\u0010\u0082\u0001\u001a\u00030\u0083\u00012\n\u0010\u0084\u0001\u001a\u0005\u0018\u00010\u0085\u0001H\u0016J\u0016\u0010\u0086\u0001\u001a\u00020i2\u0006\u0010W\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010xJ\t\u0010\u0087\u0001\u001a\u00020iH\u0016J\u0011\u0010\u0088\u0001\u001a\u00020i2\u0006\u0010v\u001a\u00020sH\u0016J\t\u0010\u0089\u0001\u001a\u00020iH\u0016J\t\u0010\u008a\u0001\u001a\u00020iH\u0016J\t\u0010\u008b\u0001\u001a\u00020iH\u0016J\t\u0010\u008c\u0001\u001a\u00020iH\u0016J\t\u0010\u008d\u0001\u001a\u00020iH&J\t\u0010\u008e\u0001\u001a\u00020iH\u0004J'\u0010\u008f\u0001\u001a\u00020i2\u0007\u0010\u0090\u0001\u001a\u00020\u001a2\u0013\u0010\u0091\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001a0.H\u0016J\u0012\u0010\u0092\u0001\u001a\u00020i2\u0007\u0010v\u001a\u00030\u0093\u0001H\u0016J#\u0010\u0094\u0001\u001a\u00020i2\u000e\u0010\u0095\u0001\u001a\t\u0012\u0005\u0012\u00030\u0096\u00010r2\b\u0010\u0097\u0001\u001a\u00030\u0098\u0001H\u0016J\u001d\u0010\u0099\u0001\u001a\u00020i2\b\u0010\u009a\u0001\u001a\u00030\u0083\u00012\b\u0010}\u001a\u0004\u0018\u00010~H\u0016J\u0016\u0010\u009b\u0001\u001a\u00020i2\u0006\u0010W\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010xJ\u0012\u0010\u009c\u0001\u001a\u00020i2\u0007\u0010v\u001a\u00030\u009d\u0001H\u0016J\u0007\u0010\u009e\u0001\u001a\u00020iJ\t\u0010\u009f\u0001\u001a\u00020iH\u0004J\u0016\u0010 \u0001\u001a\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000c0¡\u0001H\u0002R\u001e\u0010\u0007\u001a\u00020\b8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001e\u0010\r\u001a\u00020\u000e8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001e\u0010\u0013\u001a\u00020\u00148\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0019\u001a\u00020\u001a¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0014\u0010\u001d\u001a\u00020\u001eX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u001e\u0010!\u001a\u00020\"8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001e\u0010'\u001a\u00020(8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u001c\u0010-\u001a\u0010\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001a\u0018\u00010.X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010/\u001a\u0004\u0018\u0001008BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b1\u00102R\u001e\u00103\u001a\u0002048\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b5\u00106\"\u0004\b7\u00108R\u0011\u00109\u001a\u00020:8F¢\u0006\u0006\u001a\u0004\b;\u0010<R\u001e\u0010=\u001a\u00020>8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b?\u0010@\"\u0004\bA\u0010BR\u001e\u0010C\u001a\u00020D8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\bE\u0010F\"\u0004\bG\u0010HR\u0016\u0010I\u001a\u0004\u0018\u00010J8DX\u0084\u0004¢\u0006\u0006\u001a\u0004\bK\u0010LR\u0014\u0010M\u001a\b\u0012\u0004\u0012\u00020J0NX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010O\u001a\u00020P8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\bQ\u0010R\"\u0004\bS\u0010TR\u000e\u0010U\u001a\u00020VX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010W\u001a\b\u0012\u0004\u0012\u00028\u00000X8DX\u0084\u0004¢\u0006\u0006\u001a\u0004\bY\u0010ZR\u0014\u0010[\u001a\b\u0012\u0004\u0012\u00028\u00000NX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\\\u001a\u00020]8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b^\u0010_\"\u0004\b`\u0010aR!\u0010b\u001a\b\u0012\u0004\u0012\u00028\u00000c8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bf\u0010g\u001a\u0004\bd\u0010e¨\u0006£\u0001"}, d2 = {"Lcom/amazon/music/skyfire/ui/fragment/TemplateFragment;", "T", "LCoreInterface/v1_0/Template;", "Landroidx/fragment/app/Fragment;", "Lcom/amazon/music/skyfire/ui/skyfire/TemplateMethodListener;", "Lcom/amazon/music/skyfire/ui/skyfire/DispatcherContext;", "()V", "contextMenuProvider", "Lcom/amazon/music/skyfire/ui/providers/ContextMenuProvider;", "getContextMenuProvider", "()Lcom/amazon/music/skyfire/ui/providers/ContextMenuProvider;", "setContextMenuProvider", "(Lcom/amazon/music/skyfire/ui/providers/ContextMenuProvider;)V", "elementBindingProvider", "Lcom/amazon/music/skyfire/ui/providers/ElementBindingProvider;", "getElementBindingProvider", "()Lcom/amazon/music/skyfire/ui/providers/ElementBindingProvider;", "setElementBindingProvider", "(Lcom/amazon/music/skyfire/ui/providers/ElementBindingProvider;)V", "externalActionProvider", "Lcom/amazon/music/skyfire/ui/providers/ExternalActionProvider;", "getExternalActionProvider", "()Lcom/amazon/music/skyfire/ui/providers/ExternalActionProvider;", "setExternalActionProvider", "(Lcom/amazon/music/skyfire/ui/providers/ExternalActionProvider;)V", "listenerId", "", "getListenerId", "()Ljava/lang/String;", "logger", "Lorg/slf4j/Logger;", "getLogger", "()Lorg/slf4j/Logger;", "navigationProvider", "Lcom/amazon/music/skyfire/ui/providers/NavigationProvider;", "getNavigationProvider", "()Lcom/amazon/music/skyfire/ui/providers/NavigationProvider;", "setNavigationProvider", "(Lcom/amazon/music/skyfire/ui/providers/NavigationProvider;)V", "playbackProvider", "Lcom/amazon/music/skyfire/ui/providers/PlaybackProvider;", "getPlaybackProvider", "()Lcom/amazon/music/skyfire/ui/providers/PlaybackProvider;", "setPlaybackProvider", "(Lcom/amazon/music/skyfire/ui/providers/PlaybackProvider;)V", "selectedArgs", "", "selectionFragment", "Lcom/amazon/music/skyfire/ui/fragment/SelectionFragment;", "getSelectionFragment", "()Lcom/amazon/music/skyfire/ui/fragment/SelectionFragment;", "skillLatencyTracker", "Lcom/amazon/music/skyfire/core/performance/SkillLatencyTracker;", "getSkillLatencyTracker", "()Lcom/amazon/music/skyfire/core/performance/SkillLatencyTracker;", "setSkillLatencyTracker", "(Lcom/amazon/music/skyfire/core/performance/SkillLatencyTracker;)V", "skyFireApplication", "Lcom/amazon/music/skyfire/SkyFireApplication;", "getSkyFireApplication", "()Lcom/amazon/music/skyfire/SkyFireApplication;", "skyFireConfigurationProvider", "Lcom/amazon/music/skyfire/ui/providers/SkyFireConfigurationProvider;", "getSkyFireConfigurationProvider", "()Lcom/amazon/music/skyfire/ui/providers/SkyFireConfigurationProvider;", "setSkyFireConfigurationProvider", "(Lcom/amazon/music/skyfire/ui/providers/SkyFireConfigurationProvider;)V", "skyFireProvider", "Lcom/amazon/music/skyfire/ui/providers/SkyFireProvider;", "getSkyFireProvider", "()Lcom/amazon/music/skyfire/ui/providers/SkyFireProvider;", "setSkyFireProvider", "(Lcom/amazon/music/skyfire/ui/providers/SkyFireProvider;)V", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "getStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheetObserver", "Landroidx/lifecycle/Observer;", "styleSheetProvider", "Lcom/amazon/music/skyfire/ui/providers/StyleSheetProvider;", "getStyleSheetProvider", "()Lcom/amazon/music/skyfire/ui/providers/StyleSheetProvider;", "setStyleSheetProvider", "(Lcom/amazon/music/skyfire/ui/providers/StyleSheetProvider;)V", "suppressTemplateChange", "", "template", "Landroidx/lifecycle/LiveData;", "getTemplate", "()Landroidx/lifecycle/LiveData;", "templateObserver", "upsellProvider", "Lcom/amazon/music/skyfire/ui/providers/UpsellProvider;", "getUpsellProvider", "()Lcom/amazon/music/skyfire/ui/providers/UpsellProvider;", "setUpsellProvider", "(Lcom/amazon/music/skyfire/ui/providers/UpsellProvider;)V", "viewModel", "Lcom/amazon/music/skyfire/ui/viewmodel/TemplateViewModel;", "getViewModel", "()Lcom/amazon/music/skyfire/ui/viewmodel/TemplateViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "dispatchOnViewed", "", "displaySelector", "LTouch/SelectionTemplateInterface/v1_0/SelectionTemplate;", "fetchInitialMethods", "getSkillPath", "defaultValueProvider", "Lkotlin/Function0;", "getSkillPathFromMethods", "methods", "", "LCoreInterface/v1_0/Method;", "getSkyfireHttpBaseUrl", "handleMethod", "method", "onBindTemplate", "(LCoreInterface/v1_0/Template;)V", "onContextItemSelected", "item", "Landroid/view/MenuItem;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateContextMenu", "menu", "Landroid/view/ContextMenu;", "v", "Landroid/view/View;", "menuInfo", "Landroid/view/ContextMenu$ContextMenuInfo;", "onCreateTemplate", "onDestroyView", "onHandleMethod", "onPrepareTemplate", "onStart", "onStop", "onStyleSheetChanged", "onTemplateChanged", "onTemplateChangedWrapper", "onTriggerExternalAction", "action", "args", "onTriggerExternalContextMenu", "LExternalActionInterface/v1_0/TriggerExternalContextMenuMethod;", "onTriggerExternalGenericPlayback", "content", "LExternalActionInterface/v1_0/PlayableContent;", "startingIndex", "", "onViewCreated", "view", "overrideViewModelTemplate", "playTracks", "LExternalActionInterface/v1_0/TriggerExternalPlaybackMethod;", "refresh", "resetTemplate", "templateViewModel", "Lkotlin/Lazy;", "Companion", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class TemplateFragment<T extends Template> extends Fragment implements TemplateMethodListener<T>, DispatcherContext {
    public static final String ARG_INITIAL_METHODS = "com.amazon.music.skyfire.ui.InitialMethods";
    public static final String ARG_INITIAL_METHOD_BEHAVIOR = "com.amazon.music.skyfire.ui.MethodBehavior";
    public static final String ARG_NAVIGATION_BEHAVIOR = "com.amazon.music.skyfire.ui.NavigationBehavior";
    public static final String ARG_SCREEN_ID = "com.amazon.music.skyfire.ui.ScreenId";
    public static final String ARG_VIEW_MODEL_PERSISTENCE_BEHAVIOR = "com.amazon.music.skyfire.ui.ViewModelPersistenceBehavior";
    private static final String CURRENT_SKILL_PATH = "com.amazon.music.skyfire.ui.CurrentSkillPath";
    public static final int INITIAL_METHOD_BEHAVIOR_ALWAYS = 1;
    public static final int INITIAL_METHOD_BEHAVIOR_DEFAULT = 0;
    public static final int INITIAL_METHOD_BEHAVIOR_NEVER = 2;
    public static final int NAVIGATION_AVOID_REDUNDANT = 1;
    public static final int NAVIGATION_DEFAULT = 0;
    public static final String TAG_SELECTION_FRAGMENT = "com.amazon.music.skyfire.ui.fragment.TemplateFragment.SelectionFragment";
    public static final int VIEW_MODEL_PERSISTENCE_BEHAVIOR_ACTIVITY = 1;
    public static final int VIEW_MODEL_PERSISTENCE_BEHAVIOR_DEFAULT = 0;
    public ContextMenuProvider contextMenuProvider;
    public ElementBindingProvider elementBindingProvider;
    public ExternalActionProvider externalActionProvider;
    private final Logger logger;
    public NavigationProvider navigationProvider;
    public PlaybackProvider playbackProvider;
    private Map<String, String> selectedArgs;
    public SkillLatencyTracker skillLatencyTracker;
    public SkyFireConfigurationProvider skyFireConfigurationProvider;
    public SkyFireProvider skyFireProvider;
    public StyleSheetProvider styleSheetProvider;
    private boolean suppressTemplateChange;
    public UpsellProvider upsellProvider;
    private final Observer<StyleSheet> styleSheetObserver = new Observer() { // from class: com.amazon.music.skyfire.ui.fragment.TemplateFragment$$ExternalSyntheticLambda0
        @Override // androidx.lifecycle.Observer
        public final void onChanged(Object obj) {
            TemplateFragment.m2200styleSheetObserver$lambda0(this.f$0, (StyleSheet) obj);
        }
    };
    private final Observer<T> templateObserver = new Observer() { // from class: com.amazon.music.skyfire.ui.fragment.TemplateFragment$$ExternalSyntheticLambda1
        @Override // androidx.lifecycle.Observer
        public final void onChanged(Object obj) {
            TemplateFragment.m2201templateObserver$lambda1(this.f$0, (Template) obj);
        }
    };

    /* JADX INFO: renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel = templateViewModel();
    private final String listenerId = SkyFireKt.generateUniqueId(this);

    public void onStyleSheetChanged() {
    }

    public abstract void onTemplateChanged();

    public TemplateFragment() {
        Logger logger = LoggerFactory.getLogger(getClass().getSimpleName());
        Intrinsics.checkNotNullExpressionValue(logger, "getLogger(javaClass.simpleName)");
        this.logger = logger;
    }

    public final StyleSheetProvider getStyleSheetProvider() {
        StyleSheetProvider styleSheetProvider = this.styleSheetProvider;
        if (styleSheetProvider != null) {
            return styleSheetProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("styleSheetProvider");
        return null;
    }

    public final void setStyleSheetProvider(StyleSheetProvider styleSheetProvider) {
        Intrinsics.checkNotNullParameter(styleSheetProvider, "<set-?>");
        this.styleSheetProvider = styleSheetProvider;
    }

    public final ElementBindingProvider getElementBindingProvider() {
        ElementBindingProvider elementBindingProvider = this.elementBindingProvider;
        if (elementBindingProvider != null) {
            return elementBindingProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("elementBindingProvider");
        return null;
    }

    public final void setElementBindingProvider(ElementBindingProvider elementBindingProvider) {
        Intrinsics.checkNotNullParameter(elementBindingProvider, "<set-?>");
        this.elementBindingProvider = elementBindingProvider;
    }

    public final ExternalActionProvider getExternalActionProvider() {
        ExternalActionProvider externalActionProvider = this.externalActionProvider;
        if (externalActionProvider != null) {
            return externalActionProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("externalActionProvider");
        return null;
    }

    public final void setExternalActionProvider(ExternalActionProvider externalActionProvider) {
        Intrinsics.checkNotNullParameter(externalActionProvider, "<set-?>");
        this.externalActionProvider = externalActionProvider;
    }

    public final SkyFireProvider getSkyFireProvider() {
        SkyFireProvider skyFireProvider = this.skyFireProvider;
        if (skyFireProvider != null) {
            return skyFireProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("skyFireProvider");
        return null;
    }

    public final void setSkyFireProvider(SkyFireProvider skyFireProvider) {
        Intrinsics.checkNotNullParameter(skyFireProvider, "<set-?>");
        this.skyFireProvider = skyFireProvider;
    }

    public final SkyFireConfigurationProvider getSkyFireConfigurationProvider() {
        SkyFireConfigurationProvider skyFireConfigurationProvider = this.skyFireConfigurationProvider;
        if (skyFireConfigurationProvider != null) {
            return skyFireConfigurationProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("skyFireConfigurationProvider");
        return null;
    }

    public final void setSkyFireConfigurationProvider(SkyFireConfigurationProvider skyFireConfigurationProvider) {
        Intrinsics.checkNotNullParameter(skyFireConfigurationProvider, "<set-?>");
        this.skyFireConfigurationProvider = skyFireConfigurationProvider;
    }

    public final ContextMenuProvider getContextMenuProvider() {
        ContextMenuProvider contextMenuProvider = this.contextMenuProvider;
        if (contextMenuProvider != null) {
            return contextMenuProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("contextMenuProvider");
        return null;
    }

    public final void setContextMenuProvider(ContextMenuProvider contextMenuProvider) {
        Intrinsics.checkNotNullParameter(contextMenuProvider, "<set-?>");
        this.contextMenuProvider = contextMenuProvider;
    }

    public final PlaybackProvider getPlaybackProvider() {
        PlaybackProvider playbackProvider = this.playbackProvider;
        if (playbackProvider != null) {
            return playbackProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("playbackProvider");
        return null;
    }

    public final void setPlaybackProvider(PlaybackProvider playbackProvider) {
        Intrinsics.checkNotNullParameter(playbackProvider, "<set-?>");
        this.playbackProvider = playbackProvider;
    }

    public final UpsellProvider getUpsellProvider() {
        UpsellProvider upsellProvider = this.upsellProvider;
        if (upsellProvider != null) {
            return upsellProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("upsellProvider");
        return null;
    }

    public final void setUpsellProvider(UpsellProvider upsellProvider) {
        Intrinsics.checkNotNullParameter(upsellProvider, "<set-?>");
        this.upsellProvider = upsellProvider;
    }

    public final NavigationProvider getNavigationProvider() {
        NavigationProvider navigationProvider = this.navigationProvider;
        if (navigationProvider != null) {
            return navigationProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("navigationProvider");
        return null;
    }

    public final void setNavigationProvider(NavigationProvider navigationProvider) {
        Intrinsics.checkNotNullParameter(navigationProvider, "<set-?>");
        this.navigationProvider = navigationProvider;
    }

    public final SkillLatencyTracker getSkillLatencyTracker() {
        SkillLatencyTracker skillLatencyTracker = this.skillLatencyTracker;
        if (skillLatencyTracker != null) {
            return skillLatencyTracker;
        }
        Intrinsics.throwUninitializedPropertyAccessException("skillLatencyTracker");
        return null;
    }

    public final void setSkillLatencyTracker(SkillLatencyTracker skillLatencyTracker) {
        Intrinsics.checkNotNullParameter(skillLatencyTracker, "<set-?>");
        this.skillLatencyTracker = skillLatencyTracker;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: styleSheetObserver$lambda-0, reason: not valid java name */
    public static final void m2200styleSheetObserver$lambda0(TemplateFragment this$0, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onStyleSheetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: templateObserver$lambda-1, reason: not valid java name */
    public static final void m2201templateObserver$lambda1(TemplateFragment this$0, Template template) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.suppressTemplateChange || this$0.getElementBindingProvider().getDispatcherContext() == null) {
            return;
        }
        this$0.onTemplateChangedWrapper();
    }

    private final TemplateViewModel<T> getViewModel() {
        return (TemplateViewModel) this.viewModel.getValue();
    }

    @Override // com.amazon.music.skyfire.ui.skyfire.DispatcherContext
    public final SkyFireApplication getSkyFireApplication() {
        return getSkyFireProvider().getSkyFireApplication();
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidSupportInjection.inject(this);
        SelectionFragment selectionFragment = getSelectionFragment();
        if (selectionFragment == null) {
            return;
        }
        selectionFragment.setDispatcherContext(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        registerForContextMenu(view);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        getElementBindingProvider().setContext(requireContext());
        getSkyFireProvider().getRouter().plusAssign(this);
        getElementBindingProvider().setDispatcherContext(this);
        getStyleSheetProvider().getStyleSheet().observe(this, this.styleSheetObserver);
        getTemplate().observe(this, this.templateObserver);
        onPrepareTemplate();
        dispatchOnViewed();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        getSkyFireProvider().getRouter().minusAssign(this);
        getElementBindingProvider().setContext(null);
        getElementBindingProvider().setDispatcherContext(null);
        getTemplate().removeObservers(this);
        super.onStop();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        View view = getView();
        if (view != null) {
            unregisterForContextMenu(view);
        }
        super.onDestroyView();
    }

    public void onPrepareTemplate() {
        Bundle arguments = getArguments();
        boolean z = false;
        int i = arguments == null ? 0 : arguments.getInt(ARG_INITIAL_METHOD_BEHAVIOR, 0);
        if (i == 1 || (i != 2 && getTemplate().getValue() == null)) {
            z = true;
        }
        if (z) {
            fetchInitialMethods();
        }
    }

    @Override // com.amazon.music.skyfire.ui.skyfire.MethodListener, com.amazon.music.skyfire.ui.skyfire.DispatcherContext
    public final String getListenerId() {
        return this.listenerId;
    }

    @Override // com.amazon.music.skyfire.ui.skyfire.MethodListener
    public final void handleMethod(Method method) throws IllegalAccessException, NoSuchFieldException {
        Intrinsics.checkNotNullParameter(method, "method");
        if (method instanceof CreateStandaloneContainerMethod) {
            Template template = ((CreateStandaloneContainerMethod) method).template();
            if (template == null) {
                throw new NullPointerException("null cannot be cast to non-null type T of com.amazon.music.skyfire.ui.fragment.TemplateFragment");
            }
            onCreateTemplate(template);
            return;
        }
        if (method instanceof CreateAndBindStandaloneContainerMethod) {
            Template template2 = ((CreateAndBindStandaloneContainerMethod) method).template();
            if (template2 == null) {
                throw new NullPointerException("null cannot be cast to non-null type T of com.amazon.music.skyfire.ui.fragment.TemplateFragment");
            }
            onBindTemplate(template2);
            return;
        }
        if (method instanceof BindStandaloneContainerMethod) {
            Template template3 = ((BindStandaloneContainerMethod) method).template();
            if (template3 == null) {
                throw new NullPointerException("null cannot be cast to non-null type T of com.amazon.music.skyfire.ui.fragment.TemplateFragment");
            }
            onBindTemplate(template3);
            return;
        }
        onHandleMethod(method);
    }

    @Override // com.amazon.music.skyfire.ui.skyfire.TemplateMethodListener
    public void onCreateTemplate(T template) {
        Intrinsics.checkNotNullParameter(template, "template");
        getViewModel().getTemplate().setValue(template);
        getSkyFireApplication().dispatchMethods(this.listenerId, template.onCreated());
    }

    public void overrideViewModelTemplate(T template) {
        Intrinsics.checkNotNullParameter(template, "template");
        this.suppressTemplateChange = true;
        getViewModel().getTemplate().setValue(template);
        this.suppressTemplateChange = false;
    }

    @Override // com.amazon.music.skyfire.ui.skyfire.TemplateMethodListener
    public void onBindTemplate(T template) {
        Intrinsics.checkNotNullParameter(template, "template");
        getViewModel().getTemplate().setValue(template);
        getSkyFireApplication().dispatchMethods(this.listenerId, template.onBound());
        dispatchOnViewed();
    }

    public void onHandleMethod(final Method method) throws IllegalAccessException, NoSuchFieldException {
        Intrinsics.checkNotNullParameter(method, "method");
        if (method instanceof NavigateToExternalUriMethod) {
            try {
                String strUri = ((NavigateToExternalUriMethod) method).uri();
                Intrinsics.checkNotNullExpressionValue(strUri, "method.uri()");
                Intent intent = new Intent("android.intent.action.VIEW", AnyKt.toUri(strUri));
                intent.setFlags(268435456);
                startActivity(intent);
                return;
            } catch (Exception e) {
                this.logger.error(Intrinsics.stringPlus("Unable to open URL ", ((NavigateToExternalUriMethod) method).uri()), (Throwable) e);
                return;
            }
        }
        if (method instanceof ShowUpsellMethod) {
            UpsellProvider upsellProvider = getUpsellProvider();
            Context context = getContext();
            ShowUpsellMethod showUpsellMethod = (ShowUpsellMethod) method;
            PlayableContent playableContent = showUpsellMethod.playableContent();
            Intrinsics.checkNotNullExpressionValue(playableContent, "method.playableContent()");
            String strName = showUpsellMethod.name();
            Intrinsics.checkNotNullExpressionValue(strName, "method.name()");
            upsellProvider.showUpsell(context, playableContent, strName);
            return;
        }
        Unit unit = null;
        if (method instanceof ToggleButtonMethod) {
            ToggleButtonMethod toggleButtonMethod = (ToggleButtonMethod) method;
            String strWidgetId = toggleButtonMethod.widgetId();
            if (strWidgetId == null) {
                return;
            }
            T value = getTemplate().getValue();
            WidgetElement widgetElementFindElementById = value == null ? null : SkyFireKt.findElementById(value, strWidgetId);
            if (widgetElementFindElementById == null) {
                return;
            }
            if (widgetElementFindElementById instanceof ButtonElement) {
                SkyFireKt.setValue(widgetElementFindElementById, "isActive", Boolean.valueOf(Intrinsics.areEqual(toggleButtonMethod.isActive(), Boolean.TRUE)));
            }
            View view = getView();
            View viewFindViewWithTag = view == null ? null : view.findViewWithTag(toggleButtonMethod.widgetId());
            BaseButton baseButton = viewFindViewWithTag instanceof BaseButton ? (BaseButton) viewFindViewWithTag : null;
            if (baseButton != null) {
                baseButton.setActive(Intrinsics.areEqual(toggleButtonMethod.isActive(), Boolean.TRUE));
            }
            Intent intent2 = new Intent();
            Constants constants = Constants.INSTANCE;
            intent2.setAction(constants.getTOGGLE_ARTIST_INTENT());
            intent2.putExtra(constants.getWIDGET_ID_KEY(), strWidgetId);
            intent2.putExtra(constants.getIS_ACTIVE_KEY(), toggleButtonMethod.isActive());
            Context context2 = getContext();
            if (context2 == null) {
                return;
            }
            LocalBroadcastManager.getInstance(context2).sendBroadcast(intent2);
            return;
        }
        if (method instanceof TriggerExternalPlaybackMethod) {
            playTracks((TriggerExternalPlaybackMethod) method);
            return;
        }
        if (method instanceof TriggerExternalContextMenuMethod) {
            onTriggerExternalContextMenu((TriggerExternalContextMenuMethod) method);
            return;
        }
        if (method instanceof TriggerExternalGenericPlaybackMethod) {
            TriggerExternalGenericPlaybackMethod triggerExternalGenericPlaybackMethod = (TriggerExternalGenericPlaybackMethod) method;
            List<PlayableContent> listContent = triggerExternalGenericPlaybackMethod.content();
            if (listContent == null) {
                return;
            }
            onTriggerExternalGenericPlayback(listContent, triggerExternalGenericPlaybackMethod.startingIndex());
            return;
        }
        if (method instanceof TriggerExternalActionMethod) {
            TriggerExternalActionMethod triggerExternalActionMethod = (TriggerExternalActionMethod) method;
            String strName2 = triggerExternalActionMethod.name();
            Intrinsics.checkNotNullExpressionValue(strName2, "method.name()");
            Map<String, String> mapContext = triggerExternalActionMethod.context();
            if (mapContext == null) {
                mapContext = MapsKt__MapsKt.emptyMap();
            }
            onTriggerExternalAction(strName2, mapContext);
            return;
        }
        if (method instanceof OpenSelectionMethod) {
            List<Method> listMethods = ((OpenSelectionMethod) method).methods();
            Intrinsics.checkNotNullExpressionValue(listMethods, "method.methods()");
            ArrayList arrayList = new ArrayList();
            for (Method method2 : listMethods) {
                CreateAndBindStandaloneContainerMethod createAndBindStandaloneContainerMethod = method2 instanceof CreateAndBindStandaloneContainerMethod ? (CreateAndBindStandaloneContainerMethod) method2 : null;
                if (createAndBindStandaloneContainerMethod != null) {
                    arrayList.add(createAndBindStandaloneContainerMethod);
                }
            }
            ArrayList arrayList2 = new ArrayList();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                Template template = ((CreateAndBindStandaloneContainerMethod) it.next()).template();
                if (!(template instanceof SelectionTemplate)) {
                    template = null;
                }
                SelectionTemplate selectionTemplate = (SelectionTemplate) template;
                if (selectionTemplate != null) {
                    arrayList2.add(selectionTemplate);
                }
            }
            SelectionTemplate selectionTemplate2 = (SelectionTemplate) CollectionsKt___CollectionsKt.firstOrNull((List) arrayList2);
            if (selectionTemplate2 == null) {
                return;
            }
            displaySelector(selectionTemplate2);
            return;
        }
        if (method instanceof ChangeSelectionMethod) {
            Map<String, String> mapParameters = ((ChangeSelectionMethod) method).parameters();
            Intrinsics.checkNotNullExpressionValue(mapParameters, "method.parameters()");
            this.selectedArgs = MapsKt__MapsKt.toMap(mapParameters);
            return;
        }
        if (method instanceof ConfirmSelectionMethod) {
            Method methodBuildSkillMethod = SkyFireKt.buildSkillMethod(getSkyFireConfigurationProvider(), new Function1<SkillMethodBuilder, Unit>() { // from class: com.amazon.music.skyfire.ui.fragment.TemplateFragment$onHandleMethod$invokeMethod$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SkillMethodBuilder skillMethodBuilder) {
                    invoke2(skillMethodBuilder);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SkillMethodBuilder buildSkillMethod) {
                    Intrinsics.checkNotNullParameter(buildSkillMethod, "$this$buildSkillMethod");
                    buildSkillMethod.setSkillPath(((ConfirmSelectionMethod) method).skillPath());
                    Map<String, String> queryParams = buildSkillMethod.getQueryParams();
                    Map mapEmptyMap = ((TemplateFragment) this).selectedArgs;
                    if (mapEmptyMap == null) {
                        mapEmptyMap = MapsKt__MapsKt.emptyMap();
                    }
                    Map<String, String> mapParameters2 = ((ConfirmSelectionMethod) method).parameters();
                    Intrinsics.checkNotNullExpressionValue(mapParameters2, "method.parameters()");
                    queryParams.putAll(MapsKt__MapsKt.toMap(MapsKt__MapsKt.plus(mapEmptyMap, mapParameters2)));
                }
            });
            Bundle arguments = getArguments();
            if (arguments != null) {
                Mapper mapper = Mapper.INSTANCE;
                arguments.putString(ARG_INITIAL_METHODS, mapper.getInstance().writerFor(mapper.getInstance().getTypeFactory().constructCollectionType(List.class, Method.class)).writeValueAsString(CollectionsKt__CollectionsJVMKt.listOf(methodBuildSkillMethod)));
            }
            resetTemplate();
            fetchInitialMethods();
            return;
        }
        if (!(method instanceof PreFetchMethod)) {
            this.logger.warn("Don't know how to handle method '" + method + '\'');
            return;
        }
        List<PlayableContent> listItems = ((PreFetchMethod) method).items();
        if (listItems == null) {
            return;
        }
        String skyfireHttpBaseUrl = getSkyfireHttpBaseUrl();
        if (skyfireHttpBaseUrl != null) {
            getPlaybackProvider().prefetchVideoStoriesForPlayback(listItems, skyfireHttpBaseUrl);
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            getLogger().warn("Empty Base Url found from skill methods");
        }
    }

    private final void displaySelector(SelectionTemplate template) {
        SelectionFragment selectionFragment = new SelectionFragment();
        selectionFragment.setDispatcherContext(this);
        Bundle bundle = new Bundle();
        bundle.putString(SelectionFragment.ARG_TEMPLATE, Mapper.INSTANCE.objectToString(template));
        selectionFragment.setArguments(bundle);
        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager == null) {
            return;
        }
        selectionFragment.show(fragmentManager, TAG_SELECTION_FRAGMENT);
    }

    private final void dispatchOnViewed() {
        T value = getTemplate().getValue();
        if (value == null) {
            return;
        }
        getSkyFireApplication().dispatchMethods(this.listenerId, value.onViewed());
    }

    private final String getSkyfireHttpBaseUrl() {
        String string;
        List listEmptyList;
        Bundle arguments = getArguments();
        if (arguments == null || (string = arguments.getString(ARG_INITIAL_METHODS)) == null) {
            listEmptyList = null;
        } else {
            Mapper mapper = Mapper.INSTANCE;
            listEmptyList = (List) mapper.getInstance().readValue(string, mapper.getInstance().getTypeFactory().constructCollectionType(List.class, Method.class));
        }
        if (listEmptyList == null) {
            listEmptyList = CollectionsKt__CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : listEmptyList) {
            if (obj instanceof InvokeHttpSkillMethod) {
                arrayList.add(obj);
            }
        }
        InvokeHttpSkillMethod invokeHttpSkillMethod = (InvokeHttpSkillMethod) CollectionsKt___CollectionsKt.firstOrNull((List) arrayList);
        if (invokeHttpSkillMethod == null) {
            return null;
        }
        return new URL(invokeHttpSkillMethod.url()).getHost();
    }

    public void onTriggerExternalAction(String action, Map<String, String> args) {
        Intrinsics.checkNotNullParameter(action, "action");
        Intrinsics.checkNotNullParameter(args, "args");
        Context context = getContext();
        if (context == null) {
            return;
        }
        ExternalActionProvider externalActionProvider = getExternalActionProvider();
        View view = getView();
        if (externalActionProvider.handleAction(context, action, args, view == null ? null : view.findViewWithTag(getSkyFireApplication().getTag()))) {
            return;
        }
        if (Intrinsics.areEqual(action, Methods.refreshSelf)) {
            fetchInitialMethods();
            return;
        }
        this.logger.warn("Don't know how to handle external action '" + action + '\'');
    }

    public void onTriggerExternalGenericPlayback(List<? extends PlayableContent> content, int startingIndex) {
        Intrinsics.checkNotNullParameter(content, "content");
        if (content.get(0) instanceof Video) {
            PlaybackProvider playbackProvider = getPlaybackProvider();
            Context context = getContext();
            if (context == null) {
                throw new NullPointerException("null cannot be cast to non-null type androidx.fragment.app.FragmentActivity");
            }
            playbackProvider.playVideoStories((FragmentActivity) context, content, startingIndex);
            return;
        }
        if (content.size() > 1) {
            PlaybackProvider playbackProvider2 = getPlaybackProvider();
            Context context2 = getContext();
            if (context2 == null) {
                throw new NullPointerException("null cannot be cast to non-null type androidx.fragment.app.FragmentActivity");
            }
            playbackProvider2.playTracks((FragmentActivity) context2, content);
            return;
        }
        PlayableContent playableContent = (PlayableContent) CollectionsKt___CollectionsKt.firstOrNull((List) content);
        if (playableContent == null) {
            return;
        }
        getPlaybackProvider().play(playableContent, getContext());
    }

    public void playTracks(TriggerExternalPlaybackMethod method) {
        Intrinsics.checkNotNullParameter(method, "method");
        List<Track> listTracks = method.tracks();
        if (listTracks == null) {
            return;
        }
        PlaybackProvider playbackProvider = getPlaybackProvider();
        Context context = getContext();
        if (context == null) {
            throw new NullPointerException("null cannot be cast to non-null type androidx.fragment.app.FragmentActivity");
        }
        playbackProvider.playTracks((FragmentActivity) context, listTracks);
    }

    public void onTriggerExternalContextMenu(TriggerExternalContextMenuMethod method) {
        Intrinsics.checkNotNullParameter(method, "method");
        View view = getView();
        if (view == null) {
            return;
        }
        view.setTag(method);
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        activity.openContextMenu(view);
    }

    @Override // androidx.fragment.app.Fragment, android.view.View.OnCreateContextMenuListener
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        Intrinsics.checkNotNullParameter(v, "v");
        super.onCreateContextMenu(menu, v, menuInfo);
        Context context = getContext();
        if (context == null) {
            return;
        }
        Object tag = v.getTag();
        TriggerExternalContextMenuMethod triggerExternalContextMenuMethod = tag instanceof TriggerExternalContextMenuMethod ? (TriggerExternalContextMenuMethod) tag : null;
        if (triggerExternalContextMenuMethod == null) {
            return;
        }
        getContextMenuProvider().openMenu(context, menu, triggerExternalContextMenuMethod);
    }

    @Override // androidx.fragment.app.Fragment
    public boolean onContextItemSelected(MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        super.onContextItemSelected(item);
        return getContextMenuProvider().onMenuItemSelected(item);
    }

    public final void refresh() {
        fetchInitialMethods();
    }

    protected final void onTemplateChangedWrapper() {
        getSkillLatencyTracker().rendering(getSkillPath$default(this, null, 1, null));
        onTemplateChanged();
    }

    protected final Logger getLogger() {
        return this.logger;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final LiveData<T> getTemplate() {
        return getViewModel().getTemplate();
    }

    protected final StyleSheet getStyleSheet() {
        return getStyleSheetProvider().getStyleSheet().getValue();
    }

    protected final void resetTemplate() {
        getViewModel().getTemplate().setValue(null);
    }

    protected void fetchInitialMethods() {
        String string;
        Bundle arguments = getArguments();
        final List<? extends Method> listEmptyList = null;
        if (arguments != null && (string = arguments.getString(ARG_INITIAL_METHODS)) != null) {
            Mapper mapper = Mapper.INSTANCE;
            listEmptyList = (List) mapper.getInstance().readValue(string, mapper.getInstance().getTypeFactory().constructCollectionType(List.class, Method.class));
        }
        if (listEmptyList == null) {
            listEmptyList = CollectionsKt__CollectionsKt.emptyList();
        }
        getSkillLatencyTracker().request(getSkillPath(new Function0<String>(this) { // from class: com.amazon.music.skyfire.ui.fragment.TemplateFragment$fetchInitialMethods$skillPath$1
            final /* synthetic */ TemplateFragment<T> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(0);
                this.this$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                return this.this$0.getSkillPathFromMethods(listEmptyList);
            }
        }));
        getSkyFireApplication().dispatchMethods(this.listenerId, listEmptyList);
    }

    private final Lazy<TemplateViewModel<T>> templateViewModel() {
        return LazyKt__LazyJVMKt.lazy(new Function0<TemplateViewModel<T>>(this) { // from class: com.amazon.music.skyfire.ui.fragment.TemplateFragment.templateViewModel.1
            final /* synthetic */ TemplateFragment<T> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
                this.this$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public final TemplateViewModel<T> invoke() {
                Bundle arguments;
                Bundle arguments2 = this.this$0.getArguments();
                String string = arguments2 == null ? null : arguments2.getString(TemplateFragment.ARG_SCREEN_ID);
                if (string == null && ((arguments = this.this$0.getArguments()) == null || (string = arguments.getString(TemplateFragment.ARG_INITIAL_METHODS)) == null)) {
                    string = "";
                }
                Bundle arguments3 = this.this$0.getArguments();
                if ((arguments3 != null ? arguments3.getInt(TemplateFragment.ARG_VIEW_MODEL_PERSISTENCE_BEHAVIOR, 0) : 0) == 1) {
                    return (TemplateViewModel) ViewModelProviders.of(this.this$0.requireActivity()).get(string, TemplateViewModel.class);
                }
                return (TemplateViewModel) ViewModelProviders.of(this.this$0).get(string, TemplateViewModel.class);
            }
        });
    }

    private final SelectionFragment getSelectionFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        Fragment fragmentFindFragmentByTag = fragmentManager == null ? null : fragmentManager.findFragmentByTag(TAG_SELECTION_FRAGMENT);
        if (fragmentFindFragmentByTag instanceof SelectionFragment) {
            return (SelectionFragment) fragmentFindFragmentByTag;
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ String getSkillPath$default(TemplateFragment templateFragment, Function0 function0, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getSkillPath");
        }
        if ((i & 1) != 0) {
            function0 = null;
        }
        return templateFragment.getSkillPath(function0);
    }

    public final String getSkillPath(Function0<String> defaultValueProvider) {
        Bundle arguments = getArguments();
        String string = arguments == null ? null : arguments.getString(CURRENT_SKILL_PATH);
        if (defaultValueProvider == null) {
            return string;
        }
        String strInvoke = defaultValueProvider.invoke();
        if (strInvoke == null) {
            Bundle arguments2 = getArguments();
            if (arguments2 != null) {
                arguments2.remove(CURRENT_SKILL_PATH);
            }
        } else {
            Bundle arguments3 = getArguments();
            if (arguments3 != null) {
                arguments3.putString(CURRENT_SKILL_PATH, strInvoke);
            }
        }
        return strInvoke;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getSkillPathFromMethods(List<? extends Method> methods) {
        ArrayList arrayList = new ArrayList();
        for (Object obj : methods) {
            if (obj instanceof InvokeHttpSkillMethod) {
                arrayList.add(obj);
            }
        }
        InvokeHttpSkillMethod invokeHttpSkillMethod = (InvokeHttpSkillMethod) CollectionsKt___CollectionsKt.firstOrNull((List) arrayList);
        if (invokeHttpSkillMethod == null) {
            return null;
        }
        return SkillPathUtils.getSkillPath(invokeHttpSkillMethod.url());
    }
}
