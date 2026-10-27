package com.amazon.mp3.find.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.actionbar.view.BauhausActionBarView;
import com.amazon.mp3.actionbar.view.BauhausFilterActionBarView;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.activity.settings.LastViewedScreenUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.bottomnavbar.BottomNavigationBarContainer;
import com.amazon.mp3.brush.BrushViews;
import com.amazon.mp3.catalog.fragment.BrowseViewsFactory;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.featuregate.PCAEvaluator;
import com.amazon.mp3.find.intents.SearchEntityTrackingIntentService;
import com.amazon.mp3.find.util.AlexaUtil;
import com.amazon.mp3.find.view.FindFilterDialogFragment;
import com.amazon.mp3.fragment.contextmenu.ContextMenuItemClickEvent;
import com.amazon.mp3.navigation.DeeplinkAware;
import com.amazon.mp3.providers.DefaultContentEligibilityProvider;
import com.amazon.mp3.providers.DefaultLikesProvider;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.music.find.model.ContentMetadataPlaybackState;
import com.amazon.music.find.providers.SearchBrushRefinementClickListenerProvider;
import com.amazon.music.find.providers.SpellCorrectionListenerClickProvider;
import com.amazon.music.find.utils.KeyboardUtils;
import com.amazon.music.find.view.FindSearchBarView;
import com.amazon.music.find.viewmodels.FindPageGridViewModelContext;
import com.amazon.music.find.viewmodels.SearchExperienceMode;
import com.amazon.music.find.viewmodels.SearchViewModel;
import com.amazon.music.find.viewmodels.SearchViewModelAware;
import com.amazon.music.find.viewmodels.SearchViewModelFactory;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.platform.AtomicMap;
import com.amazon.music.platform.find.common.EntityType;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.locator.ProvidersKt;
import com.amazon.music.platform.providers.InterfaceProvider;
import com.amazon.music.platform.providers.ProfileProvider;
import com.amazon.music.search.SpellCorrections;
import com.amazon.music.views.library.binders.RefinementPillBinder;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.NavigationMetadata;
import com.amazon.music.views.library.models.FilterItemModel;
import com.amazon.music.views.library.models.LinkNavigatorModel;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.SectionGridViewModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.models.enums.TargetType;
import com.amazon.music.views.library.providers.BrushUriClickListenerProvider;
import com.amazon.music.views.library.providers.EndlessScrollDataProvider;
import com.amazon.music.views.library.providers.RowButtonOnClickProvider;
import com.amazon.music.views.library.providers.SeeMoreProvider;
import com.amazon.music.views.library.recyclerview.BaseViewModelAdapterProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.music.voice.launcher.LaunchTargetError;
import com.amazon.music.voice.launcher.LauncherTokens;
import com.amazon.podcast.featuregating.PodcastFeatureGating;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import dagger.android.support.AndroidSupportInjection;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendFunction;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.StateFlow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: SearchBrushFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000Þ\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\t\n\u0002\b\n*\b\u0088\u0001\u008b\u0001\u009b\u0001\u009e\u0001\u0018\u0000 ¼\u00012\b\u0012\u0004\u0012\u00020\u00020\u00012\b\u0012\u0004\u0012\u00020\u00020\u00032\u00020\u0004:\u0002¼\u0001B\t¢\u0006\u0006\bº\u0001\u0010»\u0001J\b\u0010\u0006\u001a\u00020\u0005H\u0002J\b\u0010\b\u001a\u00020\u0007H\u0002J\b\u0010\t\u001a\u00020\u0007H\u0002J\b\u0010\n\u001a\u00020\u0007H\u0002J\b\u0010\u000b\u001a\u00020\u0007H\u0002J\b\u0010\f\u001a\u00020\u0007H\u0002J\u0010\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\rH\u0002J\u0010\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u0010H\u0002J\u0018\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0010H\u0002J\u001a\u0010\u0016\u001a\u00020\u00072\b\u0010\u0015\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0011\u001a\u00020\u0010H\u0002J\u0010\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u0005H\u0002J\b\u0010\u0019\u001a\u00020\u0007H\u0002J\b\u0010\u001a\u001a\u00020\u0007H\u0002J\b\u0010\u001b\u001a\u00020\u0007H\u0002J\b\u0010\u001c\u001a\u00020\u0007H\u0002J\b\u0010\u001d\u001a\u00020\u0007H\u0002J\b\u0010\u001e\u001a\u00020\u0007H\u0002J\b\u0010\u001f\u001a\u00020\u0007H\u0002J\b\u0010 \u001a\u00020\u0007H\u0002J\b\u0010!\u001a\u00020\u0007H\u0002J\b\u0010\"\u001a\u00020\u0007H\u0002J\u0012\u0010$\u001a\u00020\u00072\b\b\u0002\u0010#\u001a\u00020\u0005H\u0002J\b\u0010%\u001a\u00020\u0007H\u0002J\b\u0010&\u001a\u00020\u0007H\u0002J\u0010\u0010(\u001a\u00020\u00072\u0006\u0010'\u001a\u00020\u0005H\u0002J\u0016\u0010,\u001a\u00020\u00072\f\u0010+\u001a\b\u0012\u0004\u0012\u00020*0)H\u0002J\u0014\u00100\u001a\u0004\u0018\u00010/2\b\u0010.\u001a\u0004\u0018\u00010-H\u0002J\u0014\u00102\u001a\u0004\u0018\u0001012\b\u0010.\u001a\u0004\u0018\u00010-H\u0002J\u0012\u00103\u001a\u00020\u00072\b\u0010.\u001a\u0004\u0018\u00010-H\u0002J\u0012\u00104\u001a\u00020\u00072\b\u0010.\u001a\u0004\u0018\u00010-H\u0002J\u001a\u00107\u001a\u00020\u00072\b\u00105\u001a\u0004\u0018\u00010/2\b\u00106\u001a\u0004\u0018\u00010/J;\u0010A\u001a\u00020\u00072\u0006\u00108\u001a\u0002012\u0006\u0010:\u001a\u0002092\b\u0010<\u001a\u0004\u0018\u00010;2\u0006\u0010>\u001a\u00020=2\n\b\u0002\u0010@\u001a\u0004\u0018\u00010?¢\u0006\u0004\bA\u0010BJ3\u0010E\u001a\u00020\u00072\u0006\u00108\u001a\u0002012\b\u0010D\u001a\u0004\u0018\u00010C2\u0006\u0010>\u001a\u00020=2\n\b\u0002\u0010@\u001a\u0004\u0018\u00010?¢\u0006\u0004\bE\u0010FJ&\u0010M\u001a\u0004\u0018\u00010L2\u0006\u0010H\u001a\u00020G2\b\u0010J\u001a\u0004\u0018\u00010I2\b\u0010K\u001a\u0004\u0018\u00010-H\u0016J\u0010\u0010P\u001a\u00020\u00072\u0006\u0010O\u001a\u00020NH\u0016J\u0012\u0010Q\u001a\u00020\u00072\b\u0010K\u001a\u0004\u0018\u00010-H\u0016J\b\u0010R\u001a\u00020\u0007H\u0016J\b\u0010S\u001a\u00020\u0007H\u0016J\b\u0010T\u001a\u00020\u0007H\u0016J\b\u0010U\u001a\u00020\u0007H\u0016J\b\u0010V\u001a\u00020\u0007H\u0016J\u0006\u0010W\u001a\u00020\u0007J\u0012\u0010Z\u001a\u00020\u00072\b\u0010Y\u001a\u0004\u0018\u00010XH\u0016J\u0012\u0010^\u001a\u0004\u0018\u00010]2\u0006\u0010\\\u001a\u00020[H\u0014J\u0012\u0010a\u001a\u0004\u0018\u00010`2\u0006\u0010_\u001a\u00020[H\u0014J\u0012\u0010b\u001a\u00020\u00022\b\u0010K\u001a\u0004\u0018\u00010-H\u0014J\b\u0010c\u001a\u00020/H\u0016J\u0010\u0010g\u001a\u00020f2\u0006\u0010e\u001a\u00020dH\u0014J$\u0010j\u001a\u00020\r2\u0006\u0010\\\u001a\u00020[2\b\u0010h\u001a\u0004\u0018\u00010/2\b\u0010i\u001a\u0004\u0018\u00010/H\u0014J\u0010\u0010m\u001a\u00020\u00072\u0006\u0010l\u001a\u00020kH\u0014J\b\u0010n\u001a\u00020\u0005H\u0014J\b\u0010o\u001a\u00020\u0005H\u0014J\b\u0010p\u001a\u00020\u0007H\u0014J\u0012\u0010s\u001a\u00020\u00072\b\u0010r\u001a\u0004\u0018\u00010qH\u0014J4\u0010{\u001a\u00020\u00072\u0006\u0010u\u001a\u00020t2\u0006\u0010w\u001a\u00020v2\u001a\u0010z\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010y\u0012\u0004\u0012\u00020v\u0012\u0004\u0012\u00020\u00070xH\u0016R\u0014\u0010}\u001a\u00020|8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b}\u0010~R\u0019\u0010\u0080\u0001\u001a\u0004\u0018\u00010\u007f8\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0080\u0001\u0010\u0081\u0001R\"\u0010\u0083\u0001\u001a\r\u0012\u0006\u0012\u0004\u0018\u00010/\u0018\u00010\u0082\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0083\u0001\u0010\u0084\u0001R\u0018\u0010\u0086\u0001\u001a\u00030\u0085\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0086\u0001\u0010\u0087\u0001R\u0018\u0010\u0089\u0001\u001a\u00030\u0088\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0089\u0001\u0010\u008a\u0001R\u0018\u0010\u008c\u0001\u001a\u00030\u008b\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u008c\u0001\u0010\u008d\u0001R\u0018\u0010\u008f\u0001\u001a\u00030\u008e\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u008f\u0001\u0010\u0090\u0001R\u0018\u0010\u0092\u0001\u001a\u00030\u0091\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0092\u0001\u0010\u0093\u0001R\u0018\u0010\u0094\u0001\u001a\u00030\u0085\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0094\u0001\u0010\u0087\u0001R\u0018\u0010\u0095\u0001\u001a\u00030\u0085\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0095\u0001\u0010\u0087\u0001R\u0018\u0010\u0096\u0001\u001a\u00030\u0085\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0096\u0001\u0010\u0087\u0001R\u0018\u0010\u0097\u0001\u001a\u00030\u0085\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0097\u0001\u0010\u0087\u0001R\u001c\u0010\u0099\u0001\u001a\u0005\u0018\u00010\u0098\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0099\u0001\u0010\u009a\u0001R\u0018\u0010\u009c\u0001\u001a\u00030\u009b\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u009c\u0001\u0010\u009d\u0001R\u0018\u0010\u009f\u0001\u001a\u00030\u009e\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u009f\u0001\u0010 \u0001R\u0018\u0010¢\u0001\u001a\u00030¡\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b¢\u0001\u0010£\u0001R\u0018\u0010¥\u0001\u001a\u00030¤\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b¥\u0001\u0010¦\u0001R*\u0010¨\u0001\u001a\u00030§\u00018\u0006@\u0006X\u0087.¢\u0006\u0018\n\u0006\b¨\u0001\u0010©\u0001\u001a\u0006\bª\u0001\u0010«\u0001\"\u0006\b¬\u0001\u0010\u00ad\u0001R\u0014\u0010°\u0001\u001a\u0002018F¢\u0006\b\u001a\u0006\b®\u0001\u0010¯\u0001R\u0014\u0010±\u0001\u001a\u00020\u00058F¢\u0006\b\u001a\u0006\b±\u0001\u0010²\u0001R\u001a\u0010¶\u0001\u001a\u0005\u0018\u00010³\u00018TX\u0094\u0004¢\u0006\b\u001a\u0006\b´\u0001\u0010µ\u0001R\u0017\u0010¹\u0001\u001a\u00020\u00028VX\u0096\u0004¢\u0006\b\u001a\u0006\b·\u0001\u0010¸\u0001¨\u0006½\u0001"}, d2 = {"Lcom/amazon/mp3/find/view/SearchBrushFragment;", "Lcom/amazon/mp3/find/view/BaseBrushFragment;", "Lcom/amazon/music/find/viewmodels/SearchViewModel;", "Lcom/amazon/music/find/viewmodels/SearchViewModelAware;", "Lcom/amazon/mp3/navigation/DeeplinkAware;", "", "isOffline", "", "setUpVoiceActionHandler", "setUpTouchListener", "clearTouchListener", "requestSearchViewFocus", "clearSearchBarViewFocus", "Lcom/amazon/mp3/actionbar/view/BauhausActionBarView;", "actionBarView", "updateQueryHint", "Lcom/amazon/music/find/viewmodels/SearchViewModel$ActionBarState;", "actionBarState", "updateActionBar", "configureActionBar", "Lcom/amazon/music/find/view/FindSearchBarView;", "findActionBarView", "configureFindSearchBar", "focus", "updateSearchViewFocus", "handleProfileSwitchNotification", "handleLoadingSpinner", "setupFindSearchBar", "handleSearchViewQueryChange", "clearFindSearchBarListeners", "handleActionBarState", "handleSearchQueryTextUpdated", "handleExternalNavigationRequested", "handleSearchViewFocusUpdated", "handleContentMetadataPlaybackStateUpdated", "matchLeftRightMargins", "styleActionBar", "showFindFilterDialog", "resetSwipeRefreshLayoutMargin", "hasFocus", "restoreSearchViewClearButton", "", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "models", "setPodcastNewBadgeIfNecessary", "Landroid/os/Bundle;", "bundle", "", "getPageUriFromExtras", "Lcom/amazon/music/find/viewmodels/SearchExperienceMode;", "getExperienceModeFromExtras", "markPageAsInitialized", "markPageUriAsInitialized", "target", "viewId", "trackGenericUiClickEvent", "mode", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "actionType", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "contentMetadata", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;", "interactionType", "", "entityPos", "trackContentMetadataUiClickEvent", "(Lcom/amazon/music/find/viewmodels/SearchExperienceMode;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;Lcom/amazon/music/views/library/metadata/ContentMetadata;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;Ljava/lang/Integer;)V", "Lcom/amazon/music/views/library/metadata/NavigationMetadata;", "navigationMetadata", "trackNavigationMetadataUiClickEvent", "(Lcom/amazon/music/find/viewmodels/SearchExperienceMode;Lcom/amazon/music/views/library/metadata/NavigationMetadata;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;Ljava/lang/Integer;)V", "Landroid/view/LayoutInflater;", "inflater", "Landroid/view/ViewGroup;", "container", "savedInstanceState", "Landroid/view/View;", "onCreateView", "Landroid/content/Context;", "context", "onAttach", "onActivityCreated", "onStart", "onResume", "onStop", "onDestroy", "onDestroyView", "clearVoiceContentMetadataHandlerFactory", "Landroid/view/View$OnTouchListener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "setupTouchListener", "Landroidx/fragment/app/FragmentActivity;", "fragmentActivity", "Lcom/amazon/mp3/find/view/BrushContextMenuProvider;", "createContextMenuProvider", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Lcom/amazon/music/views/library/providers/RowButtonOnClickProvider;", "createRowButtonOnClickProvider", "createViewModel", "getBrushScrollIdentifier", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "pageGridViewModel", "Lcom/amazon/mp3/brush/BrushViews;", "createBrushViews", "title", "subTitle", "createActionBarView", "Lcom/amazon/mp3/actionbar/view/BauhausFilterActionBarView;", "actionBar", "setupActionBarPills", "shouldSwipeBeEnabled", "shouldErrorLayoutBeVisible", "setBottomBarTab", "Lcom/amazon/music/find/viewmodels/FindPageGridViewModelContext;", "pageGridViewModelContext", "afterRenderingCompleted", "Landroid/net/Uri;", "uri", "Lcom/amazon/music/voice/launcher/LauncherTokens;", "launcherTokens", "Lkotlin/Function2;", "Lcom/amazon/music/voice/launcher/LaunchTargetError;", "onComplete", "handle", "Lkotlinx/coroutines/CoroutineScope;", "defaultScope", "Lkotlinx/coroutines/CoroutineScope;", "Lcom/amazon/music/platform/providers/ProfileProvider;", "profileProvider", "Lcom/amazon/music/platform/providers/ProfileProvider;", "Lkotlinx/coroutines/flow/StateFlow;", "profileSwitchNotification", "Lkotlinx/coroutines/flow/StateFlow;", "Landroid/view/View$OnClickListener;", "mBackButtonListener", "Landroid/view/View$OnClickListener;", "com/amazon/mp3/find/view/SearchBrushFragment$mFindUriClickProvider$1", "mFindUriClickProvider", "Lcom/amazon/mp3/find/view/SearchBrushFragment$mFindUriClickProvider$1;", "com/amazon/mp3/find/view/SearchBrushFragment$mFindSeeMoreProvider$1", "mFindSeeMoreProvider", "Lcom/amazon/mp3/find/view/SearchBrushFragment$mFindSeeMoreProvider$1;", "Landroid/view/View$OnKeyListener;", "mSearchViewOnKeyListener", "Landroid/view/View$OnKeyListener;", "Landroid/view/View$OnFocusChangeListener;", "mSearchViewFocusChangeListener", "Landroid/view/View$OnFocusChangeListener;", "mSearchViewOnClickListener", "mSearchClearImageViewClickListener", "mFilterButtonClickListener", "mAlexaButtonClickListener", "Lcom/amazon/mp3/find/view/SearchMetadataClickListener;", "mMetadataClickListener", "Lcom/amazon/mp3/find/view/SearchMetadataClickListener;", "com/amazon/mp3/find/view/SearchBrushFragment$mEndlessScrollDataProvider$1", "mEndlessScrollDataProvider", "Lcom/amazon/mp3/find/view/SearchBrushFragment$mEndlessScrollDataProvider$1;", "com/amazon/mp3/find/view/SearchBrushFragment$mSpellCorrectionClickProvider$1", "mSpellCorrectionClickProvider", "Lcom/amazon/mp3/find/view/SearchBrushFragment$mSpellCorrectionClickProvider$1;", "Lcom/amazon/mp3/providers/DefaultContentEligibilityProvider;", "mContentEligibilityProvider", "Lcom/amazon/mp3/providers/DefaultContentEligibilityProvider;", "Lcom/amazon/mp3/find/intents/SearchEntityTrackingIntentService;", "mEntityTrackingIntentServiceProvider", "Lcom/amazon/mp3/find/intents/SearchEntityTrackingIntentService;", "Lcom/amazon/music/find/viewmodels/SearchViewModelFactory;", "searchViewModelFactory", "Lcom/amazon/music/find/viewmodels/SearchViewModelFactory;", "getSearchViewModelFactory", "()Lcom/amazon/music/find/viewmodels/SearchViewModelFactory;", "setSearchViewModelFactory", "(Lcom/amazon/music/find/viewmodels/SearchViewModelFactory;)V", "getCurrentMode", "()Lcom/amazon/music/find/viewmodels/SearchExperienceMode;", "currentMode", "isTrackSearch", "()Z", "", "getInvalidateRecycleViewDelayedDuration", "()Ljava/lang/Long;", "invalidateRecycleViewDelayedDuration", "getSearchViewModel", "()Lcom/amazon/music/find/viewmodels/SearchViewModel;", "searchViewModel", "<init>", "()V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class SearchBrushFragment extends BaseBrushFragment<SearchViewModel> implements SearchViewModelAware<SearchViewModel>, DeeplinkAware {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Logger logger = LoggerFactory.getLogger(SearchBrushFragment.class.getName());
    private final CoroutineScope defaultScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault());
    private final View.OnClickListener mAlexaButtonClickListener;
    private final View.OnClickListener mBackButtonListener;
    private final DefaultContentEligibilityProvider mContentEligibilityProvider;
    private final SearchBrushFragment$mEndlessScrollDataProvider$1 mEndlessScrollDataProvider;
    private final SearchEntityTrackingIntentService mEntityTrackingIntentServiceProvider;
    private final View.OnClickListener mFilterButtonClickListener;
    private final SearchBrushFragment$mFindSeeMoreProvider$1 mFindSeeMoreProvider;
    private final SearchBrushFragment$mFindUriClickProvider$1 mFindUriClickProvider;
    private SearchMetadataClickListener mMetadataClickListener;
    private final View.OnClickListener mSearchClearImageViewClickListener;
    private final View.OnFocusChangeListener mSearchViewFocusChangeListener;
    private final View.OnClickListener mSearchViewOnClickListener;
    private final View.OnKeyListener mSearchViewOnKeyListener;
    private final SearchBrushFragment$mSpellCorrectionClickProvider$1 mSpellCorrectionClickProvider;
    private final ProfileProvider profileProvider;
    private final StateFlow<String> profileSwitchNotification;
    public SearchViewModelFactory searchViewModelFactory;

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

    /* JADX WARN: Type inference failed for: r0v16, types: [com.amazon.mp3.find.view.SearchBrushFragment$mEndlessScrollDataProvider$1] */
    /* JADX WARN: Type inference failed for: r0v17, types: [com.amazon.mp3.find.view.SearchBrushFragment$mSpellCorrectionClickProvider$1] */
    /* JADX WARN: Type inference failed for: r0v8, types: [com.amazon.mp3.find.view.SearchBrushFragment$mFindUriClickProvider$1] */
    /* JADX WARN: Type inference failed for: r0v9, types: [com.amazon.mp3.find.view.SearchBrushFragment$mFindSeeMoreProvider$1] */
    public SearchBrushFragment() {
        AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
        String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(ProfileProvider.class));
        Intrinsics.checkNotNull(originalClassName);
        InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
        ProfileProvider profileProvider = (ProfileProvider) (interfaceProvider instanceof ProfileProvider ? interfaceProvider : null);
        this.profileProvider = profileProvider;
        this.profileSwitchNotification = profileProvider != null ? (StateFlow) profileProvider.getProfileSwitchNotificationPublisher() : null;
        this.mBackButtonListener = new View.OnClickListener() { // from class: com.amazon.mp3.find.view.SearchBrushFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SearchBrushFragment.m1052mBackButtonListener$lambda0(this.f$0, view);
            }
        };
        this.mFindUriClickProvider = new BrushUriClickListenerProvider() { // from class: com.amazon.mp3.find.view.SearchBrushFragment$mFindUriClickProvider$1

            /* JADX INFO: compiled from: SearchBrushFragment.kt */
            @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
            public /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[TargetType.values().length];
                    iArr[TargetType.DEEPLINK.ordinal()] = 1;
                    iArr[TargetType.URI.ordinal()] = 2;
                    iArr[TargetType.SEARCH_QUERY.ordinal()] = 3;
                    iArr[TargetType.CLEAR_VIEW.ordinal()] = 4;
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
                    return;
                }
                if (i == 1) {
                    try {
                        this.this$0.handleDeepLink(target);
                    } catch (Exception unused2) {
                        SearchBrushFragment.logger.error(Intrinsics.stringPlus("Unable to handle onClick DEEPLINK target: ", target));
                    }
                    this.this$0.trackGenericUiClickEvent(target, viewId);
                } else if (i == 2) {
                    this.this$0.handleBrushUri(target, null, null);
                    this.this$0.trackGenericUiClickEvent(target, viewId);
                } else {
                    if (i != 3) {
                        if (i == 4 && viewId != null) {
                            this.this$0.getViewModel().clearSearchHistory();
                            return;
                        }
                        return;
                    }
                    if (viewId == null || target == null) {
                        return;
                    }
                    this.this$0.getViewModel().executeSearchFromTerm(target, viewId);
                }
            }
        };
        this.mFindSeeMoreProvider = new SeeMoreProvider() { // from class: com.amazon.mp3.find.view.SearchBrushFragment$mFindSeeMoreProvider$1
            @Override // com.amazon.music.views.library.providers.SeeMoreProvider
            public void onSeeMoreClick(String uri, String viewId, String nextPageToken) {
                if (uri == null) {
                    return;
                }
                SearchBrushFragment searchBrushFragment = this.this$0;
                SearchViewModel.executeSeeMore$default(searchBrushFragment.getViewModel(), EntityType.valueOf(uri), viewId, null, null, 12, null);
            }
        };
        this.mSearchViewOnKeyListener = new View.OnKeyListener() { // from class: com.amazon.mp3.find.view.SearchBrushFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnKeyListener
            public final boolean onKey(View view, int i, KeyEvent keyEvent) {
                return SearchBrushFragment.m1057mSearchViewOnKeyListener$lambda1(this.f$0, view, i, keyEvent);
            }
        };
        this.mSearchViewFocusChangeListener = new View.OnFocusChangeListener() { // from class: com.amazon.mp3.find.view.SearchBrushFragment$$ExternalSyntheticLambda4
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                SearchBrushFragment.m1055mSearchViewFocusChangeListener$lambda3(this.f$0, view, z);
            }
        };
        this.mSearchViewOnClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.find.view.SearchBrushFragment$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SearchBrushFragment.m1056mSearchViewOnClickListener$lambda4(this.f$0, view);
            }
        };
        this.mSearchClearImageViewClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.find.view.SearchBrushFragment$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SearchBrushFragment.m1054mSearchClearImageViewClickListener$lambda5(this.f$0, view);
            }
        };
        this.mFilterButtonClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.find.view.SearchBrushFragment$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SearchBrushFragment.m1053mFilterButtonClickListener$lambda7(this.f$0, view);
            }
        };
        this.mAlexaButtonClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.find.view.SearchBrushFragment$$ExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SearchBrushFragment.m1051mAlexaButtonClickListener$lambda8(view);
            }
        };
        this.mEndlessScrollDataProvider = new EndlessScrollDataProvider() { // from class: com.amazon.mp3.find.view.SearchBrushFragment$mEndlessScrollDataProvider$1
            @Override // com.amazon.music.views.library.providers.EndlessScrollDataProvider
            public void loadMore(int page, int totalItemsCount) {
                if (this.this$0.getCurrentMode() == SearchExperienceMode.SEE_ALL) {
                    this.this$0.getViewModel().fetchSeeMoreNextPage(page);
                }
            }
        };
        this.mSpellCorrectionClickProvider = new SpellCorrectionListenerClickProvider() { // from class: com.amazon.mp3.find.view.SearchBrushFragment$mSpellCorrectionClickProvider$1
            @Override // com.amazon.music.find.providers.SpellCorrectionListenerClickProvider
            public void onClick(String linkText, SpellCorrections spellCorrections) {
                Intrinsics.checkNotNullParameter(linkText, "linkText");
                Intrinsics.checkNotNullParameter(spellCorrections, "spellCorrections");
                this.this$0.getViewModel().executeSearchFromSpellCorrection(linkText, spellCorrections);
            }
        };
        this.mContentEligibilityProvider = new DefaultContentEligibilityProvider();
        this.mEntityTrackingIntentServiceProvider = new SearchEntityTrackingIntentService();
    }

    /* JADX INFO: compiled from: SearchBrushFragment.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0007J\u001e\u0010\n\u001a\u00020\u000b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\rH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/amazon/mp3/find/view/SearchBrushFragment$Companion;", "", "()V", "SEARCH_EXPERIENCE_MODE_EXTRA", "", "SEARCH_PAGE_URI_EXTRA", "TAG", "logger", "Lorg/slf4j/Logger;", "kotlin.jvm.PlatformType", "newInstance", "Lcom/amazon/mp3/find/view/SearchBrushFragment;", "bundle", "Landroid/os/Bundle;", "mode", "Lcom/amazon/music/find/viewmodels/SearchExperienceMode;", "extras", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final SearchBrushFragment newInstance(Bundle bundle) {
            SearchBrushFragment searchBrushFragment = new SearchBrushFragment();
            searchBrushFragment.setArguments(bundle);
            return searchBrushFragment;
        }

        @JvmStatic
        public final SearchBrushFragment newInstance(SearchExperienceMode mode, Bundle extras) {
            Bundle bundle = extras != null ? new Bundle(extras) : new Bundle();
            if (mode != null) {
                bundle.putString("SEARCH_EXPERIENCE_MODE_EXTRA", mode.toString());
            }
            return newInstance(bundle);
        }
    }

    public final SearchExperienceMode getCurrentMode() {
        return SearchViewModel.getExperienceModeOrDefault$default(getViewModel(), null, 1, null);
    }

    public final boolean isTrackSearch() {
        return getViewModel().getIsTrackSearch();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: mBackButtonListener$lambda-0, reason: not valid java name */
    public static final void m1052mBackButtonListener$lambda0(SearchBrushFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getViewModel().goBack();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: mSearchViewOnKeyListener$lambda-1, reason: not valid java name */
    public static final boolean m1057mSearchViewOnKeyListener$lambda1(SearchBrushFragment this$0, View view, int i, KeyEvent keyEvent) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (keyEvent != null && keyEvent.getKeyCode() == 66) {
            if (keyEvent != null && keyEvent.getAction() == 0) {
                this$0.getViewModel().executeSearchFromQuery();
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: mSearchViewFocusChangeListener$lambda-3, reason: not valid java name */
    public static final void m1055mSearchViewFocusChangeListener$lambda3(SearchBrushFragment this$0, View v, boolean z) {
        Context context;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.restoreSearchViewClearButton(z);
        logger.debug(Intrinsics.stringPlus("SPL::mSearchViewFocusChangeListener. hasFocus=", Boolean.valueOf(z)));
        this$0.getViewModel().changeSearchFocus(z);
        if (z || (context = this$0.getContext()) == null) {
            return;
        }
        KeyboardUtils keyboardUtils = KeyboardUtils.INSTANCE;
        Intrinsics.checkNotNullExpressionValue(v, "v");
        KeyboardUtils.hideKeyboard$default(keyboardUtils, context, v, 0, 4, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: mSearchViewOnClickListener$lambda-4, reason: not valid java name */
    public static final void m1056mSearchViewOnClickListener$lambda4(SearchBrushFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getViewModel().initiateSearch();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: mSearchClearImageViewClickListener$lambda-5, reason: not valid java name */
    public static final void m1054mSearchClearImageViewClickListener$lambda5(SearchBrushFragment this$0, View view) {
        FindSearchBarView findSearchBarView;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BauhausActionBarView mActionBarView = this$0.getMActionBarView();
        if (mActionBarView != null && (findSearchBarView = mActionBarView.getFindSearchBarView()) != null) {
            FindSearchBarView.clearSearchQuery$default(findSearchBarView, false, 1, null);
        }
        this$0.getViewModel().clearSearchQuery();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: mFilterButtonClickListener$lambda-7, reason: not valid java name */
    public static final void m1053mFilterButtonClickListener$lambda7(SearchBrushFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.getViewModel().showIvyRefinementPills()) {
            BauhausActionBarView mActionBarView = this$0.getMActionBarView();
            if ((mActionBarView instanceof BauhausFilterActionBarView ? (BauhausFilterActionBarView) mActionBarView : null) == null) {
                return;
            }
            this$0.getViewModel().toggleExposedRefinements();
            return;
        }
        this$0.showFindFilterDialog();
        this$0.getViewModel().openFilterDialog();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: mAlexaButtonClickListener$lambda-8, reason: not valid java name */
    public static final void m1051mAlexaButtonClickListener$lambda8(View view) {
        AlexaUtil.INSTANCE.triggerAlexa(PageType.BROWSE_SEARCH);
    }

    @Override // com.amazon.mp3.find.view.BaseBrushFragment
    protected Long getInvalidateRecycleViewDelayedDuration() {
        return getViewModel().getInvalidateRecycleViewDelayedDuration();
    }

    public final SearchViewModelFactory getSearchViewModelFactory() {
        SearchViewModelFactory searchViewModelFactory = this.searchViewModelFactory;
        if (searchViewModelFactory != null) {
            return searchViewModelFactory;
        }
        Intrinsics.throwUninitializedPropertyAccessException("searchViewModelFactory");
        return null;
    }

    private final boolean isOffline() {
        return getViewModel().isOffline();
    }

    private final void setUpVoiceActionHandler() {
        PageType uiPageType = getViewModel().getUiPageType();
        if (uiPageType == null) {
            uiPageType = PageType.UNKNOWN;
        }
        getViewModel().setVoiceContentMetadataHandlerFactory(new SearchMetadataHandlerFactory(CollectionsKt__CollectionsJVMKt.listOf(new SearchMetadataPlayHandler(this, uiPageType))));
    }

    private final void setUpTouchListener() {
        if (getMRecyclerView() == null || getRoot() == null) {
            return;
        }
        getMRecyclerView().setOnTouchListener(null);
        getRoot().setOnTouchListener(null);
        View.OnTouchListener onTouchListener = new View.OnTouchListener() { // from class: com.amazon.mp3.find.view.SearchBrushFragment$$ExternalSyntheticLambda12
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return SearchBrushFragment.m1058setUpTouchListener$lambda9(this.f$0, view, motionEvent);
            }
        };
        getMRecyclerView().setOnTouchListener(onTouchListener);
        getRoot().setOnTouchListener(onTouchListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setUpTouchListener$lambda-9, reason: not valid java name */
    public static final boolean m1058setUpTouchListener$lambda9(SearchBrushFragment this$0, View view, MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        logger.debug("SPL::touchListener");
        this$0.clearSearchBarViewFocus();
        return false;
    }

    private final void clearTouchListener() {
        logger.debug("SPL::clearTouchListener");
        getMRecyclerView().setOnTouchListener(null);
        getRoot().setOnTouchListener(null);
    }

    private final void requestSearchViewFocus() {
        FindSearchBarView findSearchBarView;
        Logger logger2 = logger;
        logger2.debug("SPL::requestSearchViewFocus");
        BauhausActionBarView mActionBarView = getMActionBarView();
        if (mActionBarView == null || (findSearchBarView = mActionBarView.getFindSearchBarView()) == null) {
            return;
        }
        logger2.debug("SPL::requestSearchViewFocus->requesting focus");
        findSearchBarView.performSearchButtonClick();
    }

    private final void clearSearchBarViewFocus() {
        FindSearchBarView findSearchBarView;
        logger.debug("SPL::clearSearchBarViewFocus");
        BauhausActionBarView mActionBarView = getMActionBarView();
        if (mActionBarView == null || (findSearchBarView = mActionBarView.getFindSearchBarView()) == null) {
            return;
        }
        findSearchBarView.clearSearchViewFocus();
    }

    private final void updateQueryHint(BauhausActionBarView actionBarView) {
        Context context = getContext();
        if (context == null) {
            return;
        }
        int i = isOffline() ? R.string.dmusic_prime_search_library_offline_query_hint : PodcastFeatureGating.PODCAST.isEnabled() && !ChildUserUtil.INSTANCE.isChildUser(context) ? R.string.dmusic_prime_search_music_and_podcasts_query_hint : R.string.dmusic_prime_search_prime_query_hint;
        FindSearchBarView findSearchBarView = actionBarView.getFindSearchBarView();
        if (findSearchBarView == null) {
            return;
        }
        String string = context.getString(i);
        Intrinsics.checkNotNullExpressionValue(string, "it.getString(hintId)");
        findSearchBarView.setQueryHint(string);
    }

    private final void updateActionBar(SearchViewModel.ActionBarState actionBarState) {
        BauhausActionBarView mActionBarView = getMActionBarView();
        if (mActionBarView == null) {
            return;
        }
        logger.debug(Intrinsics.stringPlus("SPL::updateActionBar. actionBarState=", actionBarState));
        configureActionBar(mActionBarView, actionBarState);
        configureFindSearchBar(mActionBarView.getFindSearchBarView(), actionBarState);
    }

    private final void configureActionBar(BauhausActionBarView actionBarView, SearchViewModel.ActionBarState actionBarState) {
        if (actionBarState.getShowActionBar()) {
            actionBarView.showActionBar();
        } else {
            actionBarView.hideActionBar();
        }
        if (actionBarState.getUpdateQueryHint()) {
            updateQueryHint(actionBarView);
        }
        if (getViewModel().showIvyRefinementPills() && getViewModel().hasFilterItemModels()) {
            BauhausFilterActionBarView bauhausFilterActionBarView = actionBarView instanceof BauhausFilterActionBarView ? (BauhausFilterActionBarView) actionBarView : null;
            if (bauhausFilterActionBarView == null) {
                return;
            }
            if (actionBarState.getShowExposedRefinements()) {
                bauhausFilterActionBarView.setFilters(getViewModel().getFilterItemModels());
            }
            BauhausFilterActionBarView.showExposedRefinements$default(bauhausFilterActionBarView, actionBarState.getShowExposedRefinements(), null, 2, null);
        }
    }

    private final void configureFindSearchBar(FindSearchBarView findActionBarView, SearchViewModel.ActionBarState actionBarState) {
        if (getMStyleSheet() == null || findActionBarView == null) {
            return;
        }
        Logger logger2 = logger;
        logger2.debug("SPL::update actionBarView start");
        findActionBarView.showBackButton(actionBarState.getShowBackButton());
        findActionBarView.showAlexaButton(AlexaUtil.INSTANCE.shouldShowAlexaButton() && actionBarState.getShowAlexaButton());
        String findSearchBarTitle = actionBarState.getFindSearchBarTitle();
        if (findSearchBarTitle != null) {
            styleActionBar(true);
            findActionBarView.showTitle(findSearchBarTitle);
        } else {
            styleActionBar$default(this, false, 1, null);
            findActionBarView.showSearchBox();
            findActionBarView.showClearButton(actionBarState.getShowClearButton());
        }
        if (getViewModel().hasFilterItemModels()) {
            findActionBarView.showFilterButton(actionBarState.getShowFilterButton(), actionBarState.getActiveFilter(), actionBarState.getShowExposedRefinements());
        }
        logger2.debug("SPL::update actionBarView finish");
    }

    private final void updateSearchViewFocus(boolean focus) {
        if (focus) {
            requestSearchViewFocus();
        } else {
            clearSearchBarViewFocus();
        }
    }

    private final void handleProfileSwitchNotification() {
        Context context = getContext();
        if (context != null ? PCAEvaluator.isEnabled$default(new PCAEvaluator(context), false, 1, null) : false) {
            Context context2 = getContext();
            BuildersKt__Builders_commonKt.launch$default(this.defaultScope, null, null, new C03601(context2 == null ? null : new SearchHistorySyncExperience(context2), null), 3, null);
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.find.view.SearchBrushFragment$handleProfileSwitchNotification$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SearchBrushFragment.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.find.view.SearchBrushFragment$handleProfileSwitchNotification$1", f = "SearchBrushFragment.kt", i = {}, l = {384}, m = "invokeSuspend", n = {}, s = {})
    static final class C03601 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ SearchHistorySyncExperience $searchHistorySyncExperience;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03601(SearchHistorySyncExperience searchHistorySyncExperience, Continuation<? super C03601> continuation) {
            super(2, continuation);
            this.$searchHistorySyncExperience = searchHistorySyncExperience;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return SearchBrushFragment.this.new C03601(this.$searchHistorySyncExperience, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03601) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: com.amazon.mp3.find.view.SearchBrushFragment$handleProfileSwitchNotification$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: SearchBrushFragment.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "", "emit", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;"}, k = 3, mv = {1, 6, 0}, xi = 48)
        static final class C00771<T> implements FlowCollector, SuspendFunction {
            final /* synthetic */ SearchHistorySyncExperience $searchHistorySyncExperience;
            final /* synthetic */ SearchBrushFragment this$0;

            C00771(SearchHistorySyncExperience searchHistorySyncExperience, SearchBrushFragment searchBrushFragment) {
                this.$searchHistorySyncExperience = searchHistorySyncExperience;
                this.this$0 = searchBrushFragment;
            }

            @Override // kotlinx.coroutines.flow.FlowCollector
            public /* bridge */ /* synthetic */ Object emit(Object obj, Continuation continuation) {
                return emit((String) obj, (Continuation<? super Unit>) continuation);
            }

            public final Object emit(String str, Continuation<? super Unit> continuation) {
                Unit unit;
                SearchHistorySyncExperience searchHistorySyncExperience = this.$searchHistorySyncExperience;
                if (searchHistorySyncExperience == null) {
                    unit = null;
                } else {
                    SearchBrushFragment searchBrushFragment = this.this$0;
                    if (searchHistorySyncExperience.canPerformSync()) {
                        searchBrushFragment.getViewModel().syncDefaultProfileHistory();
                        searchHistorySyncExperience.disableSyncExperience();
                    }
                    unit = Unit.INSTANCE;
                }
                return unit == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? unit : Unit.INSTANCE;
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                StateFlow stateFlow = SearchBrushFragment.this.profileSwitchNotification;
                if (stateFlow != null) {
                    C00771 c00771 = new C00771(this.$searchHistorySyncExperience, SearchBrushFragment.this);
                    this.label = 1;
                    if (stateFlow.collect(c00771, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    return Unit.INSTANCE;
                }
            } else if (i == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            throw new KotlinNothingValueException();
        }
    }

    private final void handleLoadingSpinner() {
        getViewModel().getShouldShowLoadingSpinner().observe(getViewLifecycleOwner(), new Observer() { // from class: com.amazon.mp3.find.view.SearchBrushFragment$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                SearchBrushFragment.m1048handleLoadingSpinner$lambda19(this.f$0, (Boolean) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: handleLoadingSpinner$lambda-19, reason: not valid java name */
    public static final void m1048handleLoadingSpinner$lambda19(SearchBrushFragment this$0, Boolean it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        View mLoadingView = this$0.getMLoadingView();
        Intrinsics.checkNotNullExpressionValue(it, "it");
        mLoadingView.setVisibility(it.booleanValue() ? 0 : 8);
        this$0.getMRecyclerView().setVisibility(it.booleanValue() ? 8 : 0);
    }

    private final void setupFindSearchBar() {
        FindSearchBarView findSearchBarView;
        BauhausActionBarView mActionBarView = getMActionBarView();
        if (mActionBarView == null || (findSearchBarView = mActionBarView.getFindSearchBarView()) == null) {
            return;
        }
        findSearchBarView.setSearchViewOnKeyListener(this.mSearchViewOnKeyListener);
        findSearchBarView.setSearchViewFocusChangeListener(this.mSearchViewFocusChangeListener);
        findSearchBarView.setSearchViewOnClickListener(this.mSearchViewOnClickListener);
        findSearchBarView.setBackButtonListener(this.mBackButtonListener);
        findSearchBarView.setClearImageViewListener(this.mSearchClearImageViewClickListener);
        findSearchBarView.setFilterButtonListener(this.mFilterButtonClickListener);
        findSearchBarView.setAlexaButtonListener(this.mAlexaButtonClickListener);
    }

    private final void handleSearchViewQueryChange() {
        FindSearchBarView findSearchBarView;
        BauhausActionBarView mActionBarView = getMActionBarView();
        if (mActionBarView == null || (findSearchBarView = mActionBarView.getFindSearchBarView()) == null) {
            return;
        }
        addDisposable(getViewModel().subscribeToSearchViewQueryTextChange(findSearchBarView.getSearchViewQueryTextObservable()));
    }

    private final void clearFindSearchBarListeners() {
        FindSearchBarView findSearchBarView;
        BauhausActionBarView mActionBarView = getMActionBarView();
        if (mActionBarView == null || (findSearchBarView = mActionBarView.getFindSearchBarView()) == null) {
            return;
        }
        findSearchBarView.clearAllListeners();
    }

    private final void handleActionBarState() {
        addDisposable(getViewModel().m2124getActionBarState().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.mp3.find.view.SearchBrushFragment$$ExternalSyntheticLambda10
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                SearchBrushFragment.m1045handleActionBarState$lambda22(this.f$0, (SearchViewModel.ActionBarState) obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: handleActionBarState$lambda-22, reason: not valid java name */
    public static final void m1045handleActionBarState$lambda22(SearchBrushFragment this$0, SearchViewModel.ActionBarState it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        logger.debug("SPL::handleActionBarState.");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        this$0.updateActionBar(it);
    }

    private final void handleSearchQueryTextUpdated() {
        addDisposable(getViewModel().getSearchQueryTextUpdated().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.mp3.find.view.SearchBrushFragment$$ExternalSyntheticLambda13
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                SearchBrushFragment.m1049handleSearchQueryTextUpdated$lambda23(this.f$0, (String) obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: handleSearchQueryTextUpdated$lambda-23, reason: not valid java name */
    public static final void m1049handleSearchQueryTextUpdated$lambda23(SearchBrushFragment this$0, String it) {
        FindSearchBarView findSearchBarView;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        logger.debug(Intrinsics.stringPlus("SPL::handleSearchQueryTextUpdated. query: ", it));
        BauhausActionBarView mActionBarView = this$0.getMActionBarView();
        if (mActionBarView == null || (findSearchBarView = mActionBarView.getFindSearchBarView()) == null) {
            return;
        }
        Intrinsics.checkNotNullExpressionValue(it, "it");
        findSearchBarView.setSearchQuery(it, false);
    }

    private final void handleExternalNavigationRequested() {
        addDisposable(getViewModel().getExternalNavigationRequested().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.mp3.find.view.SearchBrushFragment$$ExternalSyntheticLambda9
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                SearchBrushFragment.m1047handleExternalNavigationRequested$lambda24(this.f$0, (Unit) obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: handleExternalNavigationRequested$lambda-24, reason: not valid java name */
    public static final void m1047handleExternalNavigationRequested$lambda24(SearchBrushFragment this$0, Unit unit) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        logger.debug("SPL::handleExternalNavigationRequested");
        FragmentActivity activity = this$0.getActivity();
        SearchTabFragmentHost.selectFindTab(activity instanceof MusicHomeActivity ? (MusicHomeActivity) activity : null, null, false, true);
    }

    private final void handleSearchViewFocusUpdated() {
        addDisposable(getViewModel().getSearchViewFocusUpdated().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.mp3.find.view.SearchBrushFragment$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                SearchBrushFragment.m1050handleSearchViewFocusUpdated$lambda25(this.f$0, (Boolean) obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: handleSearchViewFocusUpdated$lambda-25, reason: not valid java name */
    public static final void m1050handleSearchViewFocusUpdated$lambda25(SearchBrushFragment this$0, Boolean it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        logger.debug(Intrinsics.stringPlus("SPL::handleSearchViewFocusUpdated. visible: ", it));
        Intrinsics.checkNotNullExpressionValue(it, "it");
        this$0.updateSearchViewFocus(it.booleanValue());
    }

    private final void handleContentMetadataPlaybackStateUpdated() {
        addDisposable(getViewModel().getContentMetadataPlaybackStateUpdated().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.mp3.find.view.SearchBrushFragment$$ExternalSyntheticLambda11
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                SearchBrushFragment.m1046handleContentMetadataPlaybackStateUpdated$lambda26(this.f$0, (ContentMetadataPlaybackState) obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: handleContentMetadataPlaybackStateUpdated$lambda-26, reason: not valid java name */
    public static final void m1046handleContentMetadataPlaybackStateUpdated$lambda26(SearchBrushFragment this$0, ContentMetadataPlaybackState contentMetadataPlaybackState) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        logger.debug(Intrinsics.stringPlus("SPL::handleContentMetadataPlaybackStateUpdated. state: ", contentMetadataPlaybackState));
        SearchMetadataClickListener searchMetadataClickListener = this$0.mMetadataClickListener;
        if (searchMetadataClickListener == null) {
            return;
        }
        searchMetadataClickListener.playContentMetadata(contentMetadataPlaybackState.getContentMetadataList(), contentMetadataPlaybackState.getPositionToStart());
    }

    static /* synthetic */ void styleActionBar$default(SearchBrushFragment searchBrushFragment, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        searchBrushFragment.styleActionBar(z);
    }

    private final void styleActionBar(boolean matchLeftRightMargins) {
        BauhausActionBarView mActionBarView;
        StyleSheet mStyleSheet = getMStyleSheet();
        if (mStyleSheet == null || (mActionBarView = getMActionBarView()) == null) {
            return;
        }
        Logger logger2 = logger;
        logger2.debug("SPL::styleActionBar->update actionBarView");
        FindSearchBarView findSearchBarView = mActionBarView.getFindSearchBarView();
        if (findSearchBarView != null) {
            logger2.debug("SPL::styleActionBar->update searchBarView");
            findSearchBarView.styleFindSearchBarView(mStyleSheet);
        }
        int spacerInPixels = mStyleSheet.getSpacerInPixels(SpacerKey.MEDIUM);
        if (matchLeftRightMargins) {
            spacerInPixels = 0;
        }
        mActionBarView.setSearchBarMargins(0, 0, spacerInPixels, 0);
        mActionBarView.addTopMarginToToolBar();
    }

    private final void showFindFilterDialog() {
        logger.debug("SPL::showFindFilterDialog");
        FindFilterDialogFragment.Companion companion = FindFilterDialogFragment.INSTANCE;
        FindFilterDialogFragment findFilterDialogFragmentNewInstance = companion.newInstance();
        Context context = getContext();
        if (context != null) {
            findFilterDialogFragmentNewInstance.show(((FragmentActivity) context).getSupportFragmentManager(), companion.getFRAGMENT_TAG());
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type androidx.fragment.app.FragmentActivity");
    }

    private final void resetSwipeRefreshLayoutMargin() {
        LayoutParamUtils.INSTANCE.setLayoutMargins(getMSwipeRefreshLayout(), -1, -1, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : 0, (240 & 32) != 0 ? null : null, (240 & 64) != 0 ? null : 0, (240 & 128) != 0 ? null : null);
    }

    private final void restoreSearchViewClearButton(boolean hasFocus) {
        BauhausActionBarView mActionBarView;
        FindSearchBarView findSearchBarView;
        if (!hasFocus || getMStyleSheet() == null || (mActionBarView = getMActionBarView()) == null || (findSearchBarView = mActionBarView.getFindSearchBarView()) == null) {
            return;
        }
        logger.debug(Intrinsics.stringPlus("SPL::restoreSearchViewClearButton ", Boolean.valueOf(hasFocus)));
        findSearchBarView.showClearButton(true);
    }

    public final void trackGenericUiClickEvent(String target, String viewId) {
        getViewModel().trackGenericUiClickEvent(target, viewId);
    }

    public final void trackContentMetadataUiClickEvent(SearchExperienceMode mode, ActionType actionType, ContentMetadata contentMetadata, InteractionType interactionType, Integer entityPos) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(actionType, "actionType");
        Intrinsics.checkNotNullParameter(interactionType, "interactionType");
        getViewModel().trackContentMetadataUiClickEvent(mode, actionType, contentMetadata, interactionType, entityPos);
    }

    public static /* synthetic */ void trackNavigationMetadataUiClickEvent$default(SearchBrushFragment searchBrushFragment, SearchExperienceMode searchExperienceMode, NavigationMetadata navigationMetadata, InteractionType interactionType, Integer num, int i, Object obj) {
        if ((i & 8) != 0) {
            num = null;
        }
        searchBrushFragment.trackNavigationMetadataUiClickEvent(searchExperienceMode, navigationMetadata, interactionType, num);
    }

    public final void trackNavigationMetadataUiClickEvent(SearchExperienceMode mode, NavigationMetadata navigationMetadata, InteractionType interactionType, Integer entityPos) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(interactionType, "interactionType");
        getViewModel().trackNavigationMetadataUiClickEvent(mode, navigationMetadata, interactionType, entityPos);
    }

    private final String getPageUriFromExtras(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        return bundle.getString("SEARCH_PAGE_URI_EXTRA");
    }

    private final SearchExperienceMode getExperienceModeFromExtras(Bundle bundle) {
        String string = bundle == null ? null : bundle.getString("SEARCH_EXPERIENCE_MODE_EXTRA");
        if (string == null) {
            return null;
        }
        try {
            return SearchExperienceMode.valueOf(string);
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    private final void markPageAsInitialized(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        bundle.remove("SEARCH_EXPERIENCE_MODE_EXTRA");
    }

    private final void markPageUriAsInitialized(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        bundle.remove("SEARCH_PAGE_URI_EXTRA");
    }

    @Override // com.amazon.mp3.find.view.BaseBrushFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        logger.debug("SPL::onCreateView");
        View viewOnCreateView = super.onCreateView(inflater, container, savedInstanceState);
        if (viewOnCreateView == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup");
        }
        setRoot((ViewGroup) viewOnCreateView);
        getMSwipeRefreshLayout().setEnabled(false);
        getMEmptyLayout().setVisibility(8);
        return getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        logger.debug("SPL::onAttach");
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override // com.amazon.mp3.find.view.BaseBrushFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        logger.debug("SPL::onActivityCreated");
        super.onActivityCreated(savedInstanceState);
        setUpVoiceActionHandler();
        setUpTouchListener();
        handleLoadingSpinner();
        handleActionBarState();
        setupFindSearchBar();
        handleProfileSwitchNotification();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        logger.debug("SPL::onStart");
        super.onStart();
        handleSearchQueryTextUpdated();
        handleExternalNavigationRequested();
        handleSearchViewQueryChange();
        handleSearchViewFocusUpdated();
        handleContentMetadataPlaybackStateUpdated();
    }

    @Override // com.amazon.mp3.find.view.BaseBrushFragment, androidx.fragment.app.Fragment
    public void onResume() {
        logger.debug("SPL::onResume");
        setBottomBarTab();
        if (!getSearchViewModel().actionBarStateHasObservers()) {
            handleActionBarState();
        }
        SearchViewModel.ActionBarState actionBarState = getSearchViewModel().getActionBarState();
        if (actionBarState != null && actionBarState.getFindSearchBarTitle() != null) {
            styleActionBar(true);
        } else {
            styleActionBar$default(this, false, 1, null);
        }
        int i = requireActivity().getResources().getConfiguration().orientation;
        Integer mScreenOrientation = getSearchViewModel().getMScreenOrientation();
        if (mScreenOrientation == null || mScreenOrientation.intValue() != i) {
            getSearchViewModel().setNewScreenOrientation(i);
        }
        super.onResume();
    }

    @Override // com.amazon.mp3.find.view.BaseBrushFragment, androidx.fragment.app.Fragment
    public void onStop() {
        logger.debug("SPL::onStop");
        super.onStop();
    }

    @Override // com.amazon.mp3.find.view.BaseBrushFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        logger.debug("SPL::onDestroy");
        CoroutineScopeKt.cancel$default(this.defaultScope, null, 1, null);
        super.onDestroy();
    }

    @Override // com.amazon.mp3.find.view.BaseBrushFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        logger.debug("SPL::onDestroyView");
        clearTouchListener();
        clearSearchBarViewFocus();
        clearVoiceContentMetadataHandlerFactory();
        clearFindSearchBarListeners();
        super.onDestroyView();
    }

    public final void clearVoiceContentMetadataHandlerFactory() {
        getViewModel().setVoiceContentMetadataHandlerFactory(null);
    }

    @Override // com.amazon.mp3.find.view.BaseBrushFragment
    protected BrushContextMenuProvider createContextMenuProvider(FragmentActivity fragmentActivity) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "fragmentActivity");
        return new SearchContextMenuProvider(fragmentActivity, this, getMViewModelWrapper().getMLibraryStateProvider(), getMViewModelWrapper().getMPageStateManager(), new Function2<ContentMetadata, ContextMenuItemClickEvent, Boolean>() { // from class: com.amazon.mp3.find.view.SearchBrushFragment.createContextMenuProvider.1
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
            public final Boolean mo4invoke(ContentMetadata contentMetadata, ContextMenuItemClickEvent contextMenuItemClickEvent) {
                ActionType actionType;
                if (contextMenuItemClickEvent != null && (actionType = contextMenuItemClickEvent.getActionType()) != null) {
                    SearchBrushFragment searchBrushFragment = SearchBrushFragment.this;
                    SearchViewModel.trackContentMetadataUiClickEvent$default(searchBrushFragment.getViewModel(), searchBrushFragment.getCurrentMode(), actionType, contentMetadata, InteractionType.TAP, null, 16, null);
                }
                return Boolean.TRUE;
            }
        }, new Function0<PageType>() { // from class: com.amazon.mp3.find.view.SearchBrushFragment.createContextMenuProvider.2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final PageType invoke() {
                return SearchBrushFragment.this.getViewModel().getUiPageType();
            }
        });
    }

    @Override // com.amazon.mp3.find.view.BaseBrushFragment
    protected RowButtonOnClickProvider createRowButtonOnClickProvider(FragmentActivity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (AmazonApplication.getCapabilities().isLikesEverywhereEnabled()) {
            return new DefaultLikesProvider(activity, getMViewModelWrapper().getMLibraryStateProvider(), null, true, getViewModel().getUiPageType());
        }
        return new SearchAddToLibraryProvider(activity, getMViewModelWrapper().getMLibraryStateProvider(), new Function1<ContentMetadata, Unit>() { // from class: com.amazon.mp3.find.view.SearchBrushFragment.createRowButtonOnClickProvider.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ContentMetadata contentMetadata) {
                invoke2(contentMetadata);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ContentMetadata contentMetadata) {
                SearchViewModel.trackContentMetadataUiClickEvent$default(SearchBrushFragment.this.getViewModel(), SearchBrushFragment.this.getCurrentMode(), ActionType.ADD_TRACK_TO_LIBRARY, contentMetadata, InteractionType.TAP, null, 16, null);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.mp3.find.view.BaseBrushFragment
    public SearchViewModel createViewModel(Bundle savedInstanceState) {
        logger.debug("SPL::createViewModel");
        FragmentActivity fragmentActivityRequireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "requireActivity()");
        SearchViewModel searchViewModel = (SearchViewModel) new ViewModelProvider(fragmentActivityRequireActivity, getSearchViewModelFactory()).get(SearchViewModel.class);
        if (getPageUriFromExtras(savedInstanceState) == null) {
            searchViewModel.setPageUri(getPageUriFromExtras(getArguments()));
            markPageUriAsInitialized(getArguments());
        }
        if (getExperienceModeFromExtras(savedInstanceState) == null) {
            searchViewModel.setInitialExperienceMode(getExperienceModeFromExtras(getArguments()));
            markPageAsInitialized(getArguments());
        }
        return searchViewModel;
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

    @Override // com.amazon.mp3.find.view.BaseBrushFragment
    public String getBrushScrollIdentifier() {
        String name = SearchBrushFragment.class.getName();
        Intrinsics.checkNotNullExpressionValue(name, "SearchBrushFragment::class.java.name");
        return name;
    }

    @Override // com.amazon.mp3.find.view.BaseBrushFragment
    protected BrushViews createBrushViews(PageGridViewModel pageGridViewModel) {
        Intrinsics.checkNotNullParameter(pageGridViewModel, "pageGridViewModel");
        PageType uiPageType = getViewModel().getUiPageType();
        if (uiPageType == null) {
            uiPageType = PageType.UNKNOWN;
        }
        PageType pageType = uiPageType;
        this.mMetadataClickListener = new SearchMetadataClickListener(this, pageType);
        BrushViews brushViews = BrowseViewsFactory.createBrowseViewsV2(this, getMStyleSheet(), getMRecyclerView(), getMContextMenuProvider(), this.mFindSeeMoreProvider, this.mMetadataClickListener, this.mFindUriClickProvider, this.mSpellCorrectionClickProvider, null, this.mEndlessScrollDataProvider, getMViewModelWrapper().getMLibraryStateProvider(), getMContentViewManager(), null, this.mContentEligibilityProvider, getMRowButtonOnClickProvider(), null, getMViewModelWrapper().getItemOrdinalProvider(), pageType, this.mEntityTrackingIntentServiceProvider, null);
        setPodcastNewBadgeIfNecessary(pageGridViewModel.getModels());
        if (getCurrentMode() != SearchExperienceMode.SEE_ALL) {
            resetSwipeRefreshLayoutMargin();
        }
        Intrinsics.checkNotNullExpressionValue(brushViews, "brushViews");
        return brushViews;
    }

    @Override // com.amazon.mp3.find.view.BaseBrushFragment
    protected BauhausActionBarView createActionBarView(FragmentActivity fragmentActivity, String title, String subTitle) {
        BauhausActionBarView bauhausActionBarView;
        Intrinsics.checkNotNullParameter(fragmentActivity, "fragmentActivity");
        FindSearchBarView findSearchBarView = new FindSearchBarView(fragmentActivity);
        if (((SearchViewModel) getViewModel()).showIvyRefinementPills()) {
            BauhausFilterActionBarView bauhausFilterActionBarView = new BauhausFilterActionBarView(getActivity(), null, false, false, findSearchBarView);
            if (((SearchViewModel) getViewModel()).hasFilterItemModels()) {
                setupActionBarPills(bauhausFilterActionBarView);
            }
            bauhausFilterActionBarView.showFilterButton(false);
            bauhausFilterActionBarView.showContextMenuOverflowButton(false);
            bauhausActionBarView = bauhausFilterActionBarView;
        } else {
            FragmentActivity activity = getActivity();
            Boolean bool = Boolean.FALSE;
            bauhausActionBarView = new BauhausActionBarView(activity, null, bool, bool, findSearchBarView);
        }
        bauhausActionBarView.hideFeedButton();
        bauhausActionBarView.setLayoutTransition(null);
        SearchViewModel.ActionBarState actionBarState = getSearchViewModel().getActionBarState();
        if (actionBarState != null) {
            configureActionBar(bauhausActionBarView, actionBarState);
            configureFindSearchBar(findSearchBarView, actionBarState);
        }
        return bauhausActionBarView;
    }

    @Override // com.amazon.mp3.find.view.BaseBrushFragment
    protected void setupActionBarPills(BauhausFilterActionBarView actionBar) {
        Intrinsics.checkNotNullParameter(actionBar, "actionBar");
        boolean zIsEnabled = Feature.use_optimized_observable_browse_views_factory.isEnabled();
        List<FilterItemModel> filterItemModels = getViewModel().getFilterItemModels();
        actionBar.setFilters(filterItemModels);
        StyleSheet mStyleSheet = getMStyleSheet();
        if (mStyleSheet == null) {
            return;
        }
        RefinementPillBinder refinementPillBinder = new RefinementPillBinder(mStyleSheet, new SearchBrushRefinementClickListenerProvider(getViewModel()));
        FragmentActivity fragmentActivityRequireActivity = requireActivity();
        FragmentActivity fragmentActivityRequireActivity2 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity2, "requireActivity()");
        actionBar.setAdapterProvider(new BaseViewModelAdapterProvider(fragmentActivityRequireActivity, null, LifecycleOwnerKt.getLifecycleScope(fragmentActivityRequireActivity2), zIsEnabled), refinementPillBinder, filterItemModels);
    }

    protected void setBottomBarTab() {
        if (BottomNavigationBarContainer.getInstance().isEnabled()) {
            BottomNavigationBarContainer.getInstance().setBottomBarTab(LastViewedScreenUtil.LastViewedScreen.SEARCH);
        }
    }

    @Override // com.amazon.mp3.find.view.BaseBrushFragment
    protected void afterRenderingCompleted(FindPageGridViewModelContext pageGridViewModelContext) {
        Boolean searchViewFocus;
        if (pageGridViewModelContext == null || !pageGridViewModelContext.getExecutionContext().getRequestSearchViewFocus() || (searchViewFocus = pageGridViewModelContext.getExecutionContext().getSearchViewFocus()) == null) {
            return;
        }
        updateSearchViewFocus(searchViewFocus.booleanValue());
    }

    @Override // com.amazon.music.find.viewmodels.SearchViewModelAware
    public SearchViewModel getSearchViewModel() {
        return getViewModel();
    }

    @Override // com.amazon.mp3.navigation.DeeplinkAware
    public void handle(Uri uri, LauncherTokens launcherTokens, Function2<? super LaunchTargetError, ? super LauncherTokens, Unit> onComplete) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        Intrinsics.checkNotNullParameter(launcherTokens, "launcherTokens");
        Intrinsics.checkNotNullParameter(onComplete, "onComplete");
        getViewModel().handleDeeplink(uri, launcherTokens, onComplete);
    }
}
