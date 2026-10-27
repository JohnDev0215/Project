package com.amazon.mp3.detailpages.album;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.KeyEventDispatcher;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.actionbar.ActionBarProvider;
import com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager;
import com.amazon.mp3.activity.BaseActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.baseviews.model.configuration.AlbumModelConfiguration;
import com.amazon.mp3.brush.CommentaryStateUseCase;
import com.amazon.mp3.card.prime.StationUtils;
import com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment;
import com.amazon.mp3.catalog.fragment.LibraryBaseViewsFragment;
import com.amazon.mp3.catalog.fragment.adapter.BaseViewsBinderConfiguration;
import com.amazon.mp3.catalog.fragment.adapter.BaseViewsBinderProvider;
import com.amazon.mp3.catalog.fragment.adapter.BaseViewsDiffUtil;
import com.amazon.mp3.catalog.fragment.adapter.BaseViewsDiffUtilFactory;
import com.amazon.mp3.catalog.fragment.datasource.LikesAndFollowsDBOperations;
import com.amazon.mp3.catalog.fragment.datasource.dao.LibraryAlbumDetailDao;
import com.amazon.mp3.catalog.fragment.datasource.repository.AlbumDetailDataRepository;
import com.amazon.mp3.catalog.fragment.datasource.repository.PageDataRepository;
import com.amazon.mp3.catalog.fragment.provider.LibraryContentEnabilityProvider;
import com.amazon.mp3.catalog.fragment.viewmodel.AlbumBrushViewModel;
import com.amazon.mp3.catalog.fragment.viewmodel.BaseViewsRefinementManager;
import com.amazon.mp3.catalog.fragment.viewmodel.BrushViewModel;
import com.amazon.mp3.catalog.fragment.viewmodel.GroupDownloadStateModelProvider;
import com.amazon.mp3.catalog.fragment.viewmodel.PageViewModelFactoryProvider;
import com.amazon.mp3.catalog.fragment.viewmodel.PageViewModelProvider;
import com.amazon.mp3.detailpages.actions.DefaultTrackInformationFetcher;
import com.amazon.mp3.detailpages.actions.PlayCollectionAction;
import com.amazon.mp3.detailpages.actions.TrackListContainerBaseViewModelFetcher;
import com.amazon.mp3.detailpages.album.AlbumDetailFragment;
import com.amazon.mp3.detailpages.album.actions.AddTracksAction;
import com.amazon.mp3.detailpages.album.actions.AlbumDownloadAction;
import com.amazon.mp3.detailpages.album.click.CollectionTrackClickListener;
import com.amazon.mp3.detailpages.album.click.CollectionTrackClickListenerFactory;
import com.amazon.mp3.detailpages.album.data.PageViewModelProviderFactory;
import com.amazon.mp3.detailpages.album.util.AlbumDetailPageHeaderUtil;
import com.amazon.mp3.detailpages.album.util.NightwingAlbumEnabilityProvider;
import com.amazon.mp3.dynamicbackground.DynamicBackgroundEffectUtils;
import com.amazon.mp3.dynamicbackground.DynamicBackgroundImageCondition;
import com.amazon.mp3.environment.url.EndpointsProvider;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.fragment.contextmenu.AlbumContextMenuProvider;
import com.amazon.mp3.fragment.contextmenu.ContextMenuPageType;
import com.amazon.mp3.fragment.contextmenu.PrimeAlbumContextMenuProvider;
import com.amazon.mp3.fragment.contextmenu.RemoveLibraryContentProvider;
import com.amazon.mp3.fragment.headerbar.OverflowMenuReceiver;
import com.amazon.mp3.fragment.viewmodel.OnFilterChangedListener;
import com.amazon.mp3.haptics.HapticsUtil;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.item.factory.CirrusSourceLibraryItemFactory;
import com.amazon.mp3.library.item.factory.LibraryItemFactory;
import com.amazon.mp3.library.provider.LibraryStateProvider;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.StateProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.navigation.DeeplinkIntentKey;
import com.amazon.mp3.navigation.deeplink.BaseViewsDeeplinkActionHandler;
import com.amazon.mp3.playback.state.ContentPlaybackUtils;
import com.amazon.mp3.playback.state.PageStateManager;
import com.amazon.mp3.prime.CTAPrimeCache;
import com.amazon.mp3.prime.browse.metadata.PrimeAlbum;
import com.amazon.mp3.prime.upsell.nightwing.UpsellUtil;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellReason;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellSourceEntity;
import com.amazon.mp3.providers.CatalogEnabilityProvider;
import com.amazon.mp3.providers.DefaultAddToLibraryProvider;
import com.amazon.mp3.providers.DefaultCachedArtworkProvider;
import com.amazon.mp3.providers.DefaultContextMenuProvider;
import com.amazon.mp3.providers.DefaultLikesProvider;
import com.amazon.mp3.providers.LibraryContextMenuProvider;
import com.amazon.mp3.providers.MetadataContextMenuHandler;
import com.amazon.mp3.providers.MissingSDCardHandler;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.mp3.util.BauhausToastUtils;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.DbUtil;
import com.amazon.mp3.util.IdGenerator;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.mp3.view.refinements.DetailPageRefinementManager;
import com.amazon.mp3.view.refinements.RefinementFilterType;
import com.amazon.music.freetier.featuregating.FMPMFeatureGating;
import com.amazon.music.imageloader.ImageLoader;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.PageLoadLatencyCode;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.platform.playback.augmentation.CommentaryAugmentation;
import com.amazon.music.platform.playback.augmentation.PlaybackAugmentation;
import com.amazon.music.platform.providers.EntityEligibilitiesProvider;
import com.amazon.music.share.AlbumShareProvider;
import com.amazon.music.uicontentview.ContentViewManager;
import com.amazon.music.views.library.binders.ActionIconOnClickListener;
import com.amazon.music.views.library.binders.DownloadIconOnClickListener;
import com.amazon.music.views.library.binders.RefinementPillBinder;
import com.amazon.music.views.library.binders.UniversalBinder;
import com.amazon.music.views.library.click.ClickListenerFactory;
import com.amazon.music.views.library.metadata.AlbumMetadata;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import com.amazon.music.views.library.models.CollectionDetailHeaderModel;
import com.amazon.music.views.library.models.DescriptiveRowItemModel;
import com.amazon.music.views.library.models.DownloadStateModel;
import com.amazon.music.views.library.models.ExposedRefinementListModel;
import com.amazon.music.views.library.models.FeaturedToggleViewModel;
import com.amazon.music.views.library.models.FilterCollectionModel;
import com.amazon.music.views.library.models.FilterItemModel;
import com.amazon.music.views.library.models.FilterModel;
import com.amazon.music.views.library.models.MultiStateButtonModel;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.PageHeaderActionIconModel;
import com.amazon.music.views.library.models.PageHeaderModel;
import com.amazon.music.views.library.models.RefinementPillModel;
import com.amazon.music.views.library.models.SectionGridViewModel;
import com.amazon.music.views.library.models.base.BaseViewContentModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.models.base.LibraryAddableModel;
import com.amazon.music.views.library.models.enums.TargetType;
import com.amazon.music.views.library.providers.BrushUriClickListenerProvider;
import com.amazon.music.views.library.providers.ContentEnabilityProvider;
import com.amazon.music.views.library.providers.FeaturedToggleListenerProvider;
import com.amazon.music.views.library.providers.MultiStateButtonClickListenerProvider;
import com.amazon.music.views.library.providers.RefinementCallback;
import com.amazon.music.views.library.providers.RowButtonOnClickProvider;
import com.amazon.music.views.library.providers.SeeMoreProvider;
import com.amazon.music.views.library.recyclerview.BaseViewModelAdapterProvider;
import com.amazon.music.views.library.recyclerview.HideableBaseViewModelAdapterProvider;
import com.amazon.music.views.library.recyclerview.adapters.UniversalAdapter;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.AlphaKey;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.music.views.library.views.FeaturedToggleView;
import com.amazon.music.views.library.views.presentation.ActionBarOverlayable;
import com.amazon.ui.foundations.views.BaseButton;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsJvmKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.SetsKt__SetsJVMKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.MainCoroutineDispatcher;
import org.objectweb.asm.commons.GeneratorAdapter;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/* JADX INFO: compiled from: AlbumDetailFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000ò\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u0000 \u0095\u00022\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u00062\u00020\u00072\u00020\b:\u0002\u0095\u0002B\t¢\u0006\u0006\b\u0093\u0002\u0010\u0094\u0002J\u0012\u0010\f\u001a\u00020\u000b2\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0016J&\u0010\u0012\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u000e\u001a\u00020\r2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0016J\u001a\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u00112\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0016J\n\u0010\u0016\u001a\u0004\u0018\u00010\u0015H\u0016J\b\u0010\u0017\u001a\u0004\u0018\u00010\u0015J\u0012\u0010\u0018\u001a\u00020\u000b2\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0016J\b\u0010\u0019\u001a\u00020\u000bH\u0016J\u001a\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\r2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000fH\u0014J\b\u0010\u001c\u001a\u00020\u001bH\u0014J\b\u0010\u001d\u001a\u00020\u000bH\u0014J\b\u0010\u001f\u001a\u00020\u001eH\u0014J\u0010\u0010\"\u001a\u00020\u000b2\u0006\u0010!\u001a\u00020 H\u0016J\b\u0010#\u001a\u00020\u000bH\u0014J\u0010\u0010$\u001a\u00020\u000b2\u0006\u0010!\u001a\u00020 H\u0014J\n\u0010&\u001a\u0004\u0018\u00010%H\u0016J\b\u0010(\u001a\u00020'H\u0014J\u0016\u0010+\u001a\u0010\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030*0)H\u0016J\b\u0010,\u001a\u00020\u001eH\u0014J\b\u0010-\u001a\u00020\u001eH\u0016J\b\u0010/\u001a\u00020.H\u0016J\b\u00101\u001a\u000200H\u0016J\u0011\u00102\u001a\u0004\u0018\u00010\u001bH\u0014¢\u0006\u0004\b2\u00103J\b\u00104\u001a\u00020\u000bH\u0016J\b\u00105\u001a\u00020\u000bH\u0016J\b\u00106\u001a\u00020\u000bH\u0016J\b\u00107\u001a\u00020\u000bH\u0016J\b\u00108\u001a\u00020\u001eH\u0014J\b\u00109\u001a\u00020\u000bH\u0016J\u0012\u0010<\u001a\u00020\u000b2\b\u0010;\u001a\u0004\u0018\u00010:H\u0016J\u0012\u0010?\u001a\u00020\u000b2\b\u0010>\u001a\u0004\u0018\u00010=H\u0016J\u0012\u0010A\u001a\u0004\u0018\u00010@2\u0006\u0010!\u001a\u00020 H\u0014J\"\u0010F\u001a\u00020\u000b2\b\u0010C\u001a\u0004\u0018\u00010B2\u0006\u0010D\u001a\u00020\u001e2\u0006\u0010E\u001a\u00020\u001eH\u0014J\u0010\u0010I\u001a\u00020\u000b2\u0006\u0010H\u001a\u00020GH\u0016J\u001a\u0010L\u001a\u00020\u000b2\u0006\u0010K\u001a\u00020J2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0011H\u0016J\b\u0010M\u001a\u00020\u000bH\u0014J\b\u0010N\u001a\u00020\u000bH\u0016J\u0010\u0010N\u001a\u00020\u000b2\u0006\u0010K\u001a\u00020OH\u0016J\u000e\u0010P\u001a\b\u0012\u0004\u0012\u00020G0)H\u0016J\u0014\u0010R\u001a\u0004\u0018\u00010Q2\n\b\u0002\u0010C\u001a\u0004\u0018\u00010BJ\u0012\u0010U\u001a\u00020\u000b2\b\u0010T\u001a\u0004\u0018\u00010SH\u0016J\u0010\u0010W\u001a\u00020\u001e2\u0006\u0010V\u001a\u00020\u001bH\u0016J\n\u0010Y\u001a\u0004\u0018\u00010XH\u0016J\u0011\u0010Z\u001a\u0004\u0018\u00010\u001bH\u0016¢\u0006\u0004\bZ\u00103J\n\u0010\\\u001a\u0004\u0018\u00010[H\u0016J\n\u0010^\u001a\u0004\u0018\u00010]H\u0016J\u001a\u0010a\u001a\u00020\u001e2\b\u0010`\u001a\u0004\u0018\u00010_2\u0006\u0010V\u001a\u00020\u001bH\u0016J\b\u0010b\u001a\u00020\u001eH\u0016J\b\u0010c\u001a\u00020\u001eH\u0016J\b\u0010d\u001a\u00020\u001eH\u0016J\u000f\u0010e\u001a\u0004\u0018\u00010\u001b¢\u0006\u0004\be\u00103J\u0014\u0010h\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020g0)0fH\u0016J\u0018\u0010k\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010g0f2\u0006\u0010j\u001a\u00020iH\u0016J\b\u0010l\u001a\u00020XH\u0016J\n\u0010m\u001a\u0004\u0018\u00010iH\u0016J\n\u0010n\u001a\u0004\u0018\u00010XH\u0016J\b\u0010o\u001a\u00020XH\u0016J\u0018\u0010q\u001a\u0004\u0018\u00010X2\f\u0010p\u001a\b\u0012\u0004\u0012\u00020g0)H\u0016J%\u0010s\u001a\n\u0012\u0004\u0012\u00020X\u0018\u00010r2\f\u0010p\u001a\b\u0012\u0004\u0012\u00020g0)H\u0016¢\u0006\u0004\bs\u0010tJ\n\u0010v\u001a\u0004\u0018\u00010uH\u0014J\u000e\u0010x\u001a\b\u0012\u0004\u0012\u00020w0)H\u0016J\b\u0010y\u001a\u00020\u000bH\u0016J\n\u0010{\u001a\u0004\u0018\u00010zH\u0016J\u0018\u0010~\u001a\u00020\u001e2\u0006\u0010|\u001a\u00020X2\u0006\u0010}\u001a\u00020\u001eH\u0016J\b\u0010\u007f\u001a\u00020\u001eH\u0016J\t\u0010\u0080\u0001\u001a\u00020\u000bH\u0016J\t\u0010\u0081\u0001\u001a\u00020\u001eH\u0014J\f\u0010\u0083\u0001\u001a\u0005\u0018\u00010\u0082\u0001H\u0014J\u0012\u0010\u0085\u0001\u001a\u000b\u0012\u0005\u0012\u00030\u0084\u0001\u0018\u00010)H\u0014J\u0012\u0010\u0087\u0001\u001a\u00020\u000b2\u0007\u0010\u0086\u0001\u001a\u00020iH\u0014J\u0012\u0010\u0088\u0001\u001a\u00020\u000b2\u0007\u0010\u0086\u0001\u001a\u00020iH\u0014J\u0013\u0010\u008b\u0001\u001a\u00020\u000b2\b\u0010\u008a\u0001\u001a\u00030\u0089\u0001H\u0002J\t\u0010\u008c\u0001\u001a\u00020\u000bH\u0002J)\u0010\u0090\u0001\u001a\u00020\u000b2\n\u0010\u008e\u0001\u001a\u0005\u0018\u00010\u008d\u00012\t\u0010\u008f\u0001\u001a\u0004\u0018\u00010XH\u0002¢\u0006\u0006\b\u0090\u0001\u0010\u0091\u0001J\u0012\u0010\u0093\u0001\u001a\u00030\u0092\u00012\u0006\u0010j\u001a\u00020iH\u0002J\t\u0010\u0094\u0001\u001a\u00020\u000bH\u0002J\t\u0010\u0095\u0001\u001a\u00020\u000bH\u0002J\t\u0010\u0096\u0001\u001a\u00020\u000bH\u0002J\t\u0010\u0097\u0001\u001a\u00020\u000bH\u0002J\f\u0010\u0099\u0001\u001a\u0005\u0018\u00010\u0098\u0001H\u0002J\t\u0010\u009a\u0001\u001a\u00020\u000bH\u0002J\t\u0010\u009b\u0001\u001a\u00020\u001bH\u0002J\u0018\u0010\u009f\u0001\u001a\u0011\u0012\u0005\u0012\u00030\u009d\u0001\u0012\u0005\u0012\u00030\u009e\u00010\u009c\u0001H\u0002J\u001f\u0010¤\u0001\u001a\u00020\u000b2\b\u0010¡\u0001\u001a\u00030 \u00012\n\b\u0002\u0010£\u0001\u001a\u00030¢\u0001H\u0002J%\u0010¨\u0001\u001a\u00020\u000b2\u0007\u0010¥\u0001\u001a\u00020\u001e2\u0007\u0010¦\u0001\u001a\u00020\u001b2\b\u0010§\u0001\u001a\u00030 \u0001H\u0002J)\u0010ª\u0001\u001a\u00020\u000b2\u0007\u0010©\u0001\u001a\u00020\u001e2\u0007\u0010¡\u0001\u001a\u00020[2\f\b\u0002\u0010£\u0001\u001a\u0005\u0018\u00010¢\u0001H\u0002J\u001e\u0010«\u0001\u001a\u00020\u000b2\u0007\u0010¡\u0001\u001a\u00020[2\n\u0010£\u0001\u001a\u0005\u0018\u00010¢\u0001H\u0002J\t\u0010¬\u0001\u001a\u00020\u000bH\u0002J\t\u0010\u00ad\u0001\u001a\u00020\u000bH\u0002J\t\u0010®\u0001\u001a\u00020\u000bH\u0002J\u0013\u0010¯\u0001\u001a\u00020\u000b2\b\u0010C\u001a\u0004\u0018\u00010BH\u0002J\u001d\u0010²\u0001\u001a\u00020\u000b2\b\u0010C\u001a\u0004\u0018\u00010B2\b\u0010±\u0001\u001a\u00030°\u0001H\u0002J\t\u0010³\u0001\u001a\u00020\u000bH\u0002J\u0013\u0010´\u0001\u001a\u00020\u000b2\b\b\u0002\u0010D\u001a\u00020\u001eH\u0002J\u001b\u0010¶\u0001\u001a\u00020\u000b2\u0010\u0010µ\u0001\u001a\u000b\u0012\u0005\u0012\u00030\u0084\u0001\u0018\u00010)H\u0002J\t\u0010·\u0001\u001a\u00020\u000bH\u0002J\t\u0010¸\u0001\u001a\u00020\u000bH\u0002J\t\u0010¹\u0001\u001a\u00020\u000bH\u0002J\u0013\u0010º\u0001\u001a\u0004\u0018\u00010\u001bH\u0002¢\u0006\u0005\bº\u0001\u00103J\n\u0010¼\u0001\u001a\u00030»\u0001H\u0002J\n\u0010¾\u0001\u001a\u00030½\u0001H\u0002J$\u0010Á\u0001\u001a\u00030\u008d\u00012\u000b\b\u0002\u0010¿\u0001\u001a\u0004\u0018\u00010X2\u000b\b\u0002\u0010À\u0001\u001a\u0004\u0018\u00010XH\u0002J\u000b\u0010Â\u0001\u001a\u0004\u0018\u00010XH\u0002J\u000b\u0010Ã\u0001\u001a\u0004\u0018\u00010XH\u0002J\t\u0010Ä\u0001\u001a\u00020\u001eH\u0002J\u000b\u0010Å\u0001\u001a\u0004\u0018\u00010XH\u0002J\f\u0010Æ\u0001\u001a\u0005\u0018\u00010 \u0001H\u0002J\u0012\u0010È\u0001\u001a\u000b\u0012\u0005\u0012\u00030Ç\u0001\u0018\u00010)H\u0002J\t\u0010É\u0001\u001a\u00020\u001eH\u0002J\t\u0010Ê\u0001\u001a\u00020\u001eH\u0002J\t\u0010Ë\u0001\u001a\u00020\u001eH\u0002J\t\u0010Ì\u0001\u001a\u00020\u001eH\u0002J\t\u0010Í\u0001\u001a\u00020\u001eH\u0002J\u0017\u0010x\u001a\b\u0012\u0004\u0012\u00020w0)2\u0007\u0010Î\u0001\u001a\u00020\u001eH\u0002J*\u0010Ô\u0001\u001a\u00020\u000b2\b\u0010Ð\u0001\u001a\u00030Ï\u00012\t\b\u0002\u0010Ñ\u0001\u001a\u00020\u001b2\n\b\u0002\u0010Ó\u0001\u001a\u00030Ò\u0001H\u0002J\t\u0010Õ\u0001\u001a\u00020\u000bH\u0002J\t\u0010Ö\u0001\u001a\u00020\u000bH\u0002J\t\u0010×\u0001\u001a\u00020\u000bH\u0002J\t\u0010Ø\u0001\u001a\u00020\u000bH\u0002J\u0012\u0010Ø\u0001\u001a\u00020\u000b2\u0007\u0010Ù\u0001\u001a\u00020\u001eH\u0002J\u0013\u0010Ü\u0001\u001a\f\u0012\u0005\u0012\u00030Û\u0001\u0018\u00010Ú\u0001H\u0002R\u001b\u0010Ý\u0001\u001a\u0004\u0018\u00010\u00158\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÝ\u0001\u0010Þ\u0001R\u001c\u0010à\u0001\u001a\u0005\u0018\u00010ß\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bà\u0001\u0010á\u0001R!\u0010ç\u0001\u001a\u00030â\u00018BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\bã\u0001\u0010ä\u0001\u001a\u0006\bå\u0001\u0010æ\u0001R\u001a\u0010é\u0001\u001a\u00030è\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bé\u0001\u0010ê\u0001R\u001b\u0010ë\u0001\u001a\u0004\u0018\u00010\u001e8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bë\u0001\u0010ì\u0001R\u001b\u0010¿\u0001\u001a\u0004\u0018\u00010X8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b¿\u0001\u0010í\u0001R\u001c\u0010ï\u0001\u001a\u0005\u0018\u00010î\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bï\u0001\u0010ð\u0001R\u0018\u0010ò\u0001\u001a\u00030ñ\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\bò\u0001\u0010ó\u0001R!\u0010ø\u0001\u001a\u00030ô\u00018BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\bõ\u0001\u0010ä\u0001\u001a\u0006\bö\u0001\u0010÷\u0001R\u001c\u0010ú\u0001\u001a\u0005\u0018\u00010ù\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bú\u0001\u0010û\u0001R\u001b\u0010ü\u0001\u001a\u0004\u0018\u00010S8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bü\u0001\u0010ý\u0001R\u001c\u0010ÿ\u0001\u001a\u0005\u0018\u00010þ\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÿ\u0001\u0010\u0080\u0002R\u001c\u0010\u0082\u0002\u001a\u0005\u0018\u00010\u0081\u00028\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0082\u0002\u0010\u0083\u0002R\u001c\u0010\u0085\u0002\u001a\u0005\u0018\u00010\u0084\u00028\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0085\u0002\u0010\u0086\u0002R\u001c\u0010\u0087\u0002\u001a\u0005\u0018\u00010\u0098\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0087\u0002\u0010\u0088\u0002R\u001c\u0010\u008a\u0002\u001a\u0005\u0018\u00010\u0089\u00028\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u008a\u0002\u0010\u008b\u0002R\u0019\u0010\u008c\u0002\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u008c\u0002\u0010\u008d\u0002R\u001b\u0010\u008e\u0002\u001a\u0004\u0018\u00010X8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u008e\u0002\u0010í\u0001R*\u0010\u0090\u0002\u001a\u00020\u001e2\u0007\u0010\u008f\u0002\u001a\u00020\u001e8\u0002@BX\u0082\u000e¢\u0006\u0010\n\u0006\b\u0090\u0002\u0010\u008d\u0002\"\u0006\b\u0091\u0002\u0010\u0092\u0002¨\u0006\u0096\u0002"}, d2 = {"Lcom/amazon/mp3/detailpages/album/AlbumDetailFragment;", "Lcom/amazon/mp3/catalog/fragment/LibraryBaseViewsFragment;", "Lcom/amazon/mp3/detailpages/album/click/CollectionTrackClickListener$CollectionInformationFetcher;", "Lcom/amazon/mp3/detailpages/album/click/CollectionTrackClickListener$PlaybackAugmentationProvider;", "Lcom/amazon/mp3/actionbar/BaseViewsFilterActionBarManager$InfoProvider;", "Lcom/amazon/music/views/library/providers/RefinementCallback;", "Lcom/amazon/mp3/fragment/headerbar/OverflowMenuReceiver;", "Lcom/amazon/music/imageloader/ImageLoader$ImageLoaderCallback;", "Lcom/amazon/mp3/fragment/viewmodel/OnFilterChangedListener;", "Landroid/os/Bundle;", "savedInstanceState", "", "onCreate", "Landroid/view/LayoutInflater;", "inflater", "Landroid/view/ViewGroup;", "container", "Landroid/view/View;", "onCreateView", "view", "onViewCreated", "Lcom/amazon/mp3/providers/MetadataContextMenuHandler;", "getContextMenuProvider", "getPrimeContextMenuProvider", "onActivityCreated", "onDestroy", "getInflatedRootView", "", "getLoadingViewId", "setupViews", "", "isDisplayingOnlyLibraryContent", "Landroidx/fragment/app/FragmentActivity;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "initPageModelProvider", "registerLiveDataObservers", "refreshPageGridViewModel", "Lcom/amazon/mp3/catalog/fragment/datasource/repository/PageDataRepository;", "getPageDataRepository", "Lcom/amazon/music/views/library/recyclerview/BaseViewModelAdapterProvider;", "getAdapterProvider", "", "Lcom/amazon/music/views/library/binders/UniversalBinder;", "getBinderList", "shouldRemoveGoToAlbum", "shouldHideRefinementsOnLoad", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "getPageType", "Lcom/amazon/mp3/catalog/fragment/adapter/BaseViewsDiffUtilFactory;", "getDiffUtilFactory", "getEmptyLayoutID", "()Ljava/lang/Integer;", "onStart", "onResume", "onPause", "onStop", "requireNetworkConnection", "goOfflineClickAction", "Landroid/graphics/Bitmap;", "originalBitmap", "onBitmapLoaded", "Landroid/graphics/drawable/Drawable;", "p0", "onPrepareLoad", "Lcom/amazon/music/views/library/providers/RowButtonOnClickProvider;", "getRowButtonOnClickProvider", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "pageGridViewModel", "isInitialPage", "isMorePage", "updatePageModel", "Lcom/amazon/music/views/library/models/FilterModel;", "filterModel", "onChildFilterClick", "Lcom/amazon/music/views/library/models/RefinementPillModel;", "model", "onClick", "onContentStateUpdated", "onFiltersReset", "Lcom/amazon/music/views/library/models/FilterCollectionModel;", "getRefinementFilterModels", "Lcom/amazon/music/views/library/models/PageHeaderModel;", "getPageHeaderModel", "Lcom/amazon/mp3/actionbar/ActionBarProvider;", "provider", "setActionBarProvider", "actionBarHeight", "shouldDisplayPageComponentsInTopAppBar", "", "getArtworkUrl", "getFilterButtonClickDestination", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "getContentMetadata", "Lcom/amazon/music/views/library/models/ExposedRefinementListModel;", "getRefinementListModel", "Landroidx/recyclerview/widget/RecyclerView;", "recyclerView", "shouldDisplayFilterButton", "shouldDisplayFilterAndContextButtons", "shouldDisplayTitle", "doesHaveRefinement", "findPositionOfFirstTrack", "Lrx/Observable;", "Lcom/amazon/mp3/library/item/MusicTrack;", "getAllTracks", "Landroid/net/Uri;", "uri", "getTrack", "getSortOrder", "getCollectionUri", "getCollectionId", "getCollectionName", "trackList", "getSelection", "", "getSelectionArgs", "(Ljava/util/List;)[Ljava/lang/String;", "Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;", "getContentEnabilityProvider", "Lcom/amazon/music/platform/playback/augmentation/PlaybackAugmentation;", "getPlaybackAugmentations", "emitUiPageViewMetric", "Lcom/amazon/mp3/providers/MissingSDCardHandler;", "getMissingSDCardProvider", "filterType", "selected", "shouldOverrideSelectionChange", "shouldOverrideResetFilters", "onNoNetworkDialogCanceled", "shouldSendInitializingLatencyEvent", "Lcom/amazon/music/metrics/mts/event/types/pagelatencyinfo/PageLoadLatencyCode;", "getPageLoadLatencyCode", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "getTrackList", "trackUri", "onTrackAdded", "onTrackRemoved", "Lcom/amazon/ui/foundations/views/BaseButton;", "button", "initEmptyViewResetFiltersButton", "setupActionBarComponents", "", "changedAlbumId", "changedAlbumAsin", "handleAlbumStateChanged", "(Ljava/lang/Long;Ljava/lang/String;)V", "Lcom/amazon/mp3/catalog/fragment/datasource/dao/LibraryAlbumDetailDao;", "getAlbumDetailDao", "switchToOffline", "switchToOnlineLibrary", "swapLibraryAndCatalogSource", "displayViewAccordingToAlbumOwnership", "Lcom/amazon/mp3/detailpages/album/LibraryAlbumDetailViewModel;", "getLibraryViewModel", "bindBackgroundImage", "randomTrackIndex", "", "Lcom/amazon/music/views/library/models/PageHeaderActionIconModel$ActionType;", "Lcom/amazon/music/views/library/binders/ActionIconOnClickListener;", "getPageHeaderOnClickListenerMap", "Lcom/amazon/music/views/library/metadata/AlbumMetadata;", "metadata", "Lcom/amazon/music/views/library/models/DownloadStateModel$DownloadState;", "downloadState", "likeAndDownloadAlbum", "shuffleOn", "trackPosition", "contentMetadata", "initiatePlayback", "isDownloadNeeded", "addTracksAndDownload", "startAlbumDownloadAction", "initializeRefinementOverflowManager", "initializeBaseViewsRefinementManager", "initializeDetailPageRefinementManager", "fetchAlbumDownloadState", "Lcom/amazon/music/views/library/models/DownloadStateModel;", "downloadStateModel", "updateAlbumDownloadState", "displayFilteredData", "exitPageIfRequired", "filteredTrackModels", "shouldShowEmptyView", "initializeActionBarManager", "setContextMenuProviderForActionBar", "setFilterButton", "findPositionOfRefinementList", "Lcom/amazon/music/views/library/providers/BrushUriClickListenerProvider;", "getUpsellButtonListenerProvider", "Lcom/amazon/music/views/library/providers/FeaturedToggleListenerProvider;", "getFeaturedToggleListenerProvider", "albumAsin", "albumName", "generateAlbumDBId", "getAlbumAsin", "getCollectionArtistName", "isAlbumDownloaded", "getAlbumArtUrl", "retrieveAlbumMetadata", "Lcom/amazon/music/views/library/metadata/TrackMetadata;", "extractTrackMetadata", "navigatedToOfflineLibrary", "navigatedToOnlineLibrary", "navigatedToCatalog", "isBadgingInLibrarySupported", "isOpenedFromBrowseHome", "isTrackSelected", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "actionType", "entityPosition", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;", "interactionType", "emitUiClickMetric", "handleDeeplink", "resumeOfflineFromNoNetworkDialog", "refreshPageModelState", "updateHeaderActions", "isAddable", "", "Lcom/amazon/mp3/view/refinements/RefinementFilterType;", "getDefaultFilters", "primeContextMenuHandler", "Lcom/amazon/mp3/providers/MetadataContextMenuHandler;", "Lcom/amazon/mp3/fragment/contextmenu/PrimeAlbumContextMenuProvider$PrimeAlbumContextMenuProviderListener;", "primeAlbumContextMenuProviderListener", "Lcom/amazon/mp3/fragment/contextmenu/PrimeAlbumContextMenuProvider$PrimeAlbumContextMenuProviderListener;", "Lcom/amazon/mp3/detailpages/actions/DefaultTrackInformationFetcher;", "trackInformationFetcher$delegate", "Lkotlin/Lazy;", "getTrackInformationFetcher", "()Lcom/amazon/mp3/detailpages/actions/DefaultTrackInformationFetcher;", "trackInformationFetcher", "Lcom/amazon/music/uicontentview/ContentViewManager;", "contentViewManager", "Lcom/amazon/music/uicontentview/ContentViewManager;", "refinementCapability", "Ljava/lang/Boolean;", "Ljava/lang/String;", "Landroid/widget/ImageView;", "backgroundImageView", "Landroid/widget/ImageView;", "Lcom/amazon/mp3/view/refinements/DetailPageRefinementManager;", "detailPageRefinementManager", "Lcom/amazon/mp3/view/refinements/DetailPageRefinementManager;", "Lcom/amazon/mp3/baseviews/model/configuration/AlbumModelConfiguration;", "configuration$delegate", "getConfiguration", "()Lcom/amazon/mp3/baseviews/model/configuration/AlbumModelConfiguration;", "configuration", "Lcom/amazon/mp3/catalog/fragment/viewmodel/AlbumBrushViewModel;", "albumDetailViewModel", "Lcom/amazon/mp3/catalog/fragment/viewmodel/AlbumBrushViewModel;", "actionBarProvider", "Lcom/amazon/mp3/actionbar/ActionBarProvider;", "Lcom/amazon/mp3/actionbar/BaseViewsFilterActionBarManager;", "actionBarManager", "Lcom/amazon/mp3/actionbar/BaseViewsFilterActionBarManager;", "Lcom/amazon/mp3/catalog/fragment/viewmodel/BaseViewsRefinementManager;", "refinementsManager", "Lcom/amazon/mp3/catalog/fragment/viewmodel/BaseViewsRefinementManager;", "Lcom/amazon/mp3/catalog/fragment/viewmodel/GroupDownloadStateModelProvider;", "groupDownloadStateModelProvider", "Lcom/amazon/mp3/catalog/fragment/viewmodel/GroupDownloadStateModelProvider;", "offlineAlbumDetailViewModel", "Lcom/amazon/mp3/detailpages/album/LibraryAlbumDetailViewModel;", "Lcom/amazon/mp3/fragment/contextmenu/AlbumContextMenuProvider$AlbumContextMenuProviderListener;", "albumContextMenuProviderListener", "Lcom/amazon/mp3/fragment/contextmenu/AlbumContextMenuProvider$AlbumContextMenuProviderListener;", "displayedNoNetworkDialogFromOffline", "Z", "defaultFilterString", "value", "isArtistCommentaryEnabled", "setArtistCommentaryEnabled", "(Z)V", "<init>", "()V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class AlbumDetailFragment extends LibraryBaseViewsFragment implements CollectionTrackClickListener.CollectionInformationFetcher, CollectionTrackClickListener.PlaybackAugmentationProvider, BaseViewsFilterActionBarManager.InfoProvider, RefinementCallback, OverflowMenuReceiver, ImageLoader.ImageLoaderCallback, OnFilterChangedListener {
    private static final String TAG = AlbumDetailFragment.class.getSimpleName();
    private BaseViewsFilterActionBarManager actionBarManager;
    private ActionBarProvider actionBarProvider;
    private String albumAsin;
    private AlbumContextMenuProvider.AlbumContextMenuProviderListener albumContextMenuProviderListener;
    private AlbumBrushViewModel albumDetailViewModel;
    private ImageView backgroundImageView;
    private String defaultFilterString;
    private boolean displayedNoNetworkDialogFromOffline;
    private GroupDownloadStateModelProvider groupDownloadStateModelProvider;
    private boolean isArtistCommentaryEnabled;
    private LibraryAlbumDetailViewModel offlineAlbumDetailViewModel;
    private PrimeAlbumContextMenuProvider.PrimeAlbumContextMenuProviderListener primeAlbumContextMenuProviderListener;
    private MetadataContextMenuHandler primeContextMenuHandler;
    private Boolean refinementCapability;
    private BaseViewsRefinementManager refinementsManager;

    /* JADX INFO: renamed from: trackInformationFetcher$delegate, reason: from kotlin metadata */
    private final Lazy trackInformationFetcher = LazyKt__LazyJVMKt.lazy(new Function0<DefaultTrackInformationFetcher>() { // from class: com.amazon.mp3.detailpages.album.AlbumDetailFragment$trackInformationFetcher$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final DefaultTrackInformationFetcher invoke() {
            return new DefaultTrackInformationFetcher(new TrackListContainerBaseViewModelFetcher());
        }
    });
    private ContentViewManager contentViewManager = new ContentViewManager();
    private final DetailPageRefinementManager detailPageRefinementManager = new DetailPageRefinementManager(new Function0<Unit>() { // from class: com.amazon.mp3.detailpages.album.AlbumDetailFragment$detailPageRefinementManager$1
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2() {
            this.this$0.displayFilteredData();
        }
    });

    /* JADX INFO: renamed from: configuration$delegate, reason: from kotlin metadata */
    private final Lazy configuration = LazyKt__LazyJVMKt.lazy(new Function0<AlbumModelConfiguration>() { // from class: com.amazon.mp3.detailpages.album.AlbumDetailFragment$configuration$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final AlbumModelConfiguration invoke() {
            return new AlbumModelConfiguration(null, null, 3, null);
        }
    });

    @Override // com.amazon.mp3.catalog.fragment.LibraryBaseViewsFragment
    protected Integer getEmptyLayoutID() {
        return null;
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected int getLoadingViewId() {
        return R.id.detail_loading_spinner;
    }

    @Override // com.amazon.music.views.library.providers.RefinementCallback
    public void onFiltersReset(FilterCollectionModel model) {
        Intrinsics.checkNotNullParameter(model, "model");
    }

    @Override // com.amazon.music.imageloader.ImageLoader.ImageLoaderCallback
    public void onPrepareLoad(Drawable p0) {
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    /* JADX INFO: renamed from: shouldDisplayTitle */
    public boolean getDisplayTitle() {
        return false;
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    public boolean shouldHideRefinementsOnLoad() {
        return true;
    }

    @Override // com.amazon.mp3.catalog.fragment.LibraryBaseViewsFragment
    protected boolean shouldRemoveGoToAlbum() {
        return true;
    }

    @Override // com.amazon.mp3.fragment.viewmodel.OnFilterChangedListener
    public void downloadSelectionChanged(boolean z) {
        OnFilterChangedListener.DefaultImpls.downloadSelectionChanged(this, z);
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    /* JADX INFO: renamed from: getTitle */
    public String getTitleText() {
        return BaseViewsFilterActionBarManager.InfoProvider.DefaultImpls.getTitle(this);
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    public boolean shouldDisplayContextMenuOverflowButton() {
        return BaseViewsFilterActionBarManager.InfoProvider.DefaultImpls.shouldDisplayContextMenuOverflowButton(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final DefaultTrackInformationFetcher getTrackInformationFetcher() {
        return (DefaultTrackInformationFetcher) this.trackInformationFetcher.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final AlbumModelConfiguration getConfiguration() {
        return (AlbumModelConfiguration) this.configuration.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setArtistCommentaryEnabled(boolean z) {
        CommentaryStateUseCase commentaryStateUseCase;
        if (this.isArtistCommentaryEnabled == z) {
            return;
        }
        this.isArtistCommentaryEnabled = z;
        AlbumBrushViewModel albumBrushViewModel = this.albumDetailViewModel;
        if (albumBrushViewModel != null && (commentaryStateUseCase = albumBrushViewModel.getCommentaryStateUseCase()) != null) {
            commentaryStateUseCase.updateCommentaryToggleState(this.isArtistCommentaryEnabled);
        }
        updateHeaderActions();
    }

    @Override // com.amazon.mp3.catalog.fragment.LibraryBaseViewsFragment, com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        Bundle arguments;
        Bundle arguments2 = getArguments();
        this.albumAsin = arguments2 == null ? null : arguments2.getString("EXTRA_ALBUM_ASIN");
        Bundle arguments3 = getArguments();
        setContentUri(arguments3 == null ? null : (Uri) arguments3.getParcelable("com.amazon.mp3.library.EXTRA_CONTENT_URI"));
        Bundle arguments4 = getArguments();
        this.defaultFilterString = arguments4 == null ? null : arguments4.getString("SELECTED_FILTER_KEY");
        setSourceIdString(getSourceId());
        if (savedInstanceState == null) {
            Bundle arguments5 = getArguments();
            boolean z = false;
            if (arguments5 != null && !arguments5.containsKey("EXTRA_OFFLINE_LIBRARY_NAVIGATION")) {
                z = true;
            }
            if (z && (arguments = getArguments()) != null) {
                arguments.putBoolean("EXTRA_OFFLINE_LIBRARY_NAVIGATION", isSourceLocal());
            }
        }
        if (isDisplayingOnlyLibraryContent()) {
            fetchAlbumDownloadState(null);
        }
        super.onCreate(savedInstanceState);
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View viewOnCreateView = super.onCreateView(inflater, container, savedInstanceState);
        ViewGroup root = getRoot();
        this.backgroundImageView = root == null ? null : (ImageView) root.findViewById(R.id.background_image_view);
        return viewOnCreateView;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        ViewGroup root = getRoot();
        BaseButton baseButton = root == null ? null : (BaseButton) root.findViewById(R.id.resetFilterButton);
        if (baseButton != null) {
            initEmptyViewResetFiltersButton(baseButton);
        }
        this.albumContextMenuProviderListener = new AlbumContextMenuProvider.AlbumContextMenuProviderListener() { // from class: com.amazon.mp3.detailpages.album.AlbumDetailFragment.onViewCreated.2
            @Override // com.amazon.mp3.fragment.contextmenu.AlbumContextMenuProvider.AlbumContextMenuProviderListener
            public void removeDownloadAlbumMenuItem(ContextMenu menu) {
                if (menu != null) {
                    menu.removeItem(R.id.MenuAlbumContextDownloading);
                }
                if (menu != null) {
                    menu.removeItem(R.id.MenuAlbumContextDownloaded);
                }
                if (menu == null) {
                    return;
                }
                menu.removeItem(R.id.MenuAlbumContextDownload);
            }

            @Override // com.amazon.mp3.fragment.contextmenu.AlbumContextMenuProvider.AlbumContextMenuProviderListener
            public void removeDeleteAlbumMenuItem(ContextMenu menu) {
                if (menu != null) {
                    menu.removeItem(R.id.MenuAlbumContextDelete);
                }
                if (menu == null) {
                    return;
                }
                menu.removeItem(R.id.MenuAlbumContextDeleteFromCirrus);
            }

            @Override // com.amazon.mp3.fragment.contextmenu.AlbumContextMenuProvider.AlbumContextMenuProviderListener
            public boolean shouldDisplayAddToPlaylist() {
                return AlbumDetailFragment.this.getConfiguration().shouldShowContextMenuAddToPlaylistButton();
            }

            @Override // com.amazon.mp3.fragment.contextmenu.AlbumContextMenuProvider.AlbumContextMenuProviderListener
            public ContextMenuPageType getContextMenuPageType() {
                return ContextMenuPageType.DETAIL;
            }
        };
        this.primeAlbumContextMenuProviderListener = new PrimeAlbumContextMenuProvider.PrimeAlbumContextMenuProviderListener() { // from class: com.amazon.mp3.detailpages.album.AlbumDetailFragment.onViewCreated.3
            @Override // com.amazon.mp3.fragment.contextmenu.PrimeAlbumContextMenuProvider.PrimeAlbumContextMenuProviderListener
            public void addAlbumForPlaylist(PrimeAlbum album) {
            }

            @Override // com.amazon.mp3.fragment.contextmenu.PrimeAlbumContextMenuProvider.PrimeAlbumContextMenuProviderListener
            public void startAlbumPlayback(PrimeAlbum album) {
            }

            @Override // com.amazon.mp3.fragment.contextmenu.PrimeAlbumContextMenuProvider.PrimeAlbumContextMenuProviderListener
            public void updateAlbumStatus(PrimeAlbum album) {
            }

            @Override // com.amazon.mp3.fragment.contextmenu.PrimeAlbumContextMenuProvider.PrimeAlbumContextMenuProviderListener
            public void updateAlbumStatusForPlaylist(PrimeAlbum album) {
            }

            @Override // com.amazon.mp3.fragment.contextmenu.PrimeAlbumContextMenuProvider.PrimeAlbumContextMenuProviderListener
            public StateProvider<PrimeAlbum> getAlbumStateProvider() {
                throw new NotImplementedError(Intrinsics.stringPlus("An operation is not implemented: ", "Not yet implemented"));
            }

            @Override // com.amazon.mp3.fragment.contextmenu.PrimeAlbumContextMenuProvider.PrimeAlbumContextMenuProviderListener
            public ContextMenuPageType getContextMenuPageType() {
                return ContextMenuPageType.DETAIL;
            }

            @Override // com.amazon.mp3.fragment.contextmenu.PrimeAlbumContextMenuProvider.PrimeAlbumContextMenuProviderListener
            public void removeDownloadAlbumMenuItem(ContextMenu menu) {
                Intrinsics.checkNotNullParameter(menu, "menu");
                menu.removeItem(R.id.MenuAlbumContextDownloading);
                menu.removeItem(R.id.MenuAlbumContextDownloaded);
                menu.removeItem(R.id.MenuAlbumContextDownload);
            }
        };
    }

    private final void initEmptyViewResetFiltersButton(final BaseButton button) {
        getSubscriptions().add(StyleSheetProvider.getStyleSheetObservable().subscribe(new Action1() { // from class: com.amazon.mp3.detailpages.album.AlbumDetailFragment$$ExternalSyntheticLambda4
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                AlbumDetailFragment.m811initEmptyViewResetFiltersButton$lambda1(this.f$0, button, (StyleSheet) obj);
            }
        }));
        button.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.detailpages.album.AlbumDetailFragment$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AlbumDetailFragment.m812initEmptyViewResetFiltersButton$lambda2(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initEmptyViewResetFiltersButton$lambda-1, reason: not valid java name */
    public static final void m811initEmptyViewResetFiltersButton$lambda1(AlbumDetailFragment this$0, BaseButton button, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(button, "$button");
        BaseButton.StyleBuilder buttonBuilder = styleSheet.getButtonBuilder(ButtonSizeKey.MEDIUM, ButtonStyleKey.SOLID);
        if (buttonBuilder == null) {
            return;
        }
        String string = this$0.getResources().getString(R.string.dmusic_reset_all_filters);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.st…dmusic_reset_all_filters)");
        BaseButton.StyleBuilder styleBuilderWithText = buttonBuilder.withText(string);
        if (styleBuilderWithText == null) {
            return;
        }
        styleBuilderWithText.applyStyle(button);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initEmptyViewResetFiltersButton$lambda-2, reason: not valid java name */
    public static final void m812initEmptyViewResetFiltersButton$lambda2(AlbumDetailFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DetailPageRefinementManager detailPageRefinementManager = this$0.detailPageRefinementManager;
        if (detailPageRefinementManager == null) {
            return;
        }
        detailPageRefinementManager.onFiltersReset();
    }

    @Override // com.amazon.mp3.catalog.fragment.LibraryBaseViewsFragment, com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public MetadataContextMenuHandler getContextMenuProvider() {
        if (getContextMenuHandler() == null) {
            FragmentActivity activity = getActivity();
            BaseActivity baseActivity = activity instanceof BaseActivity ? (BaseActivity) activity : null;
            if (baseActivity != null) {
                setContextMenuHandler(new LibraryContextMenuProvider(baseActivity, this, getPageType(), getTrackContextMenuListener(), null, this.albumContextMenuProviderListener, null, LifecycleOwnerKt.getLifecycleScope(this), 80, null));
                MetadataContextMenuHandler contextMenuHandler = getContextMenuHandler();
                if (contextMenuHandler != null && (contextMenuHandler instanceof LifecycleObserver)) {
                    getLifecycle().addObserver((LifecycleObserver) contextMenuHandler);
                }
            }
        }
        return getContextMenuHandler();
    }

    public final MetadataContextMenuHandler getPrimeContextMenuProvider() {
        LibraryStateProvider libraryStateProvider;
        PageStateManager pageStateManager;
        if (this.primeContextMenuHandler == null) {
            FragmentActivity activity = getActivity();
            BaseActivity baseActivity = activity instanceof BaseActivity ? (BaseActivity) activity : null;
            if (baseActivity != null && (libraryStateProvider = getLibraryStateProvider()) != null) {
                PageViewModelProvider pageModelProvider = getPageModelProvider();
                BrushViewModel brushViewModel = pageModelProvider instanceof BrushViewModel ? (BrushViewModel) pageModelProvider : null;
                if (brushViewModel != null && (pageStateManager = brushViewModel.getPageStateManager()) != null) {
                    this.primeContextMenuHandler = new DefaultContextMenuProvider(baseActivity, this, libraryStateProvider, getPageType(), pageStateManager);
                    Lifecycle lifecycle = getLifecycle();
                    MetadataContextMenuHandler metadataContextMenuHandler = this.primeContextMenuHandler;
                    if (metadataContextMenuHandler == null) {
                        throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.providers.DefaultContextMenuProvider");
                    }
                    lifecycle.addObserver((DefaultContextMenuProvider) metadataContextMenuHandler);
                }
            }
        }
        return this.primeContextMenuHandler;
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        if (this.actionBarManager == null) {
            setupActionBarComponents();
        }
        super.onActivityCreated(savedInstanceState);
    }

    @Override // com.amazon.mp3.catalog.fragment.LibraryBaseViewsFragment, com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        BaseViewsFilterActionBarManager baseViewsFilterActionBarManager = this.actionBarManager;
        if (baseViewsFilterActionBarManager != null) {
            getLifecycle().removeObserver(baseViewsFilterActionBarManager);
        }
        getLifecycle().removeObserver(this.detailPageRefinementManager);
        MetadataContextMenuHandler contextMenuHandler = getContextMenuHandler();
        if (contextMenuHandler != null && (contextMenuHandler instanceof LifecycleObserver)) {
            getLifecycle().removeObserver((LifecycleObserver) contextMenuHandler);
        }
        MetadataContextMenuHandler metadataContextMenuHandler = this.primeContextMenuHandler;
        if (metadataContextMenuHandler != null && (metadataContextMenuHandler instanceof LifecycleObserver)) {
            getLifecycle().removeObserver((LifecycleObserver) metadataContextMenuHandler);
        }
    }

    private final void setupActionBarComponents() {
        initializeActionBarManager();
        initializeRefinementOverflowManager();
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected ViewGroup getInflatedRootView(LayoutInflater inflater, ViewGroup container) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View viewInflate = inflater.inflate(R.layout.fragment_detail, container, false);
        if (viewInflate != null) {
            return (ConstraintLayout) viewInflate;
        }
        throw new NullPointerException("null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout");
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected void setupViews() {
        ExposedRefinementListModel exposedRefinementListModelRetrieveRefinementListModel;
        super.setupViews();
        setupActionBarComponents();
        BaseViewsBinderConfiguration viewsConfiguration = getViewsConfiguration();
        if (viewsConfiguration != null) {
            RefinementPillBinder refinementPillBinder = new RefinementPillBinder(viewsConfiguration.getStyleSheet(), this.detailPageRefinementManager);
            BaseViewModelAdapterProvider adapterProvider = viewsConfiguration.getAdapterProvider();
            BaseViewsRefinementManager baseViewsRefinementManager = this.refinementsManager;
            List<FilterModel> filterModels = (baseViewsRefinementManager == null || (exposedRefinementListModelRetrieveRefinementListModel = baseViewsRefinementManager.retrieveRefinementListModel()) == null) ? null : exposedRefinementListModelRetrieveRefinementListModel.getFilterModels();
            if (filterModels == null) {
                filterModels = new ArrayList<>();
            }
            BaseViewsFilterActionBarManager baseViewsFilterActionBarManager = this.actionBarManager;
            if (baseViewsFilterActionBarManager != null) {
                baseViewsFilterActionBarManager.setAdapterProviderForActionBarView(adapterProvider, refinementPillBinder, filterModels);
            }
        }
        BaseViewsFilterActionBarManager baseViewsFilterActionBarManager2 = this.actionBarManager;
        int actionBarHeight = baseViewsFilterActionBarManager2 == null ? 0 : baseViewsFilterActionBarManager2.getActionBarHeight();
        KeyEventDispatcher.Component activity = getActivity();
        ActionBarOverlayable actionBarOverlayable = activity instanceof ActionBarOverlayable ? (ActionBarOverlayable) activity : null;
        if (actionBarOverlayable == null) {
            return;
        }
        actionBarOverlayable.setContentOffset(getRecyclerView(), actionBarHeight);
    }

    @Override // com.amazon.mp3.catalog.fragment.LibraryBaseViewsFragment
    protected boolean isDisplayingOnlyLibraryContent() {
        return this.albumAsin == null || navigatedToOfflineLibrary() || navigatedToOnlineLibrary();
    }

    @Override // com.amazon.mp3.catalog.fragment.LibraryBaseViewsFragment, com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public void initPageModelProvider(FragmentActivity activity) {
        PageViewModelProvider pageModelProvider;
        MutableLiveData<PageGridViewModel> pageModelLiveData;
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (getView() != null && (pageModelProvider = getPageModelProvider()) != null && (pageModelLiveData = pageModelProvider.getPageModelLiveData()) != null) {
            pageModelLiveData.removeObservers(getViewLifecycleOwner());
        }
        PageViewModelProvider pageModelProvider2 = getPageModelProvider();
        BrushViewModel brushViewModel = pageModelProvider2 instanceof BrushViewModel ? (BrushViewModel) pageModelProvider2 : null;
        if (brushViewModel != null) {
            brushViewModel.unregisterDownloadStateListener();
        }
        if (isDisplayingOnlyLibraryContent()) {
            if (getPageModelProvider() == null || !(getPageModelProvider() instanceof LibraryAlbumDetailViewModel)) {
                setPageModelProvider(getLibraryViewModel());
                if (getPageModelProvider() instanceof LibraryAlbumDetailViewModel) {
                    PageViewModelProvider pageModelProvider3 = getPageModelProvider();
                    if (pageModelProvider3 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.detailpages.album.LibraryAlbumDetailViewModel");
                    }
                    LibraryAlbumDetailViewModel libraryAlbumDetailViewModel = (LibraryAlbumDetailViewModel) pageModelProvider3;
                    this.offlineAlbumDetailViewModel = libraryAlbumDetailViewModel;
                    libraryAlbumDetailViewModel.setDownloadChangedListener(this);
                }
            }
            String str = this.albumAsin;
            if (str == null) {
                return;
            }
            PageViewModelProvider pageModelProvider4 = getPageModelProvider();
            BrushViewModel brushViewModel2 = pageModelProvider4 instanceof BrushViewModel ? (BrushViewModel) pageModelProvider4 : null;
            if (brushViewModel2 == null) {
                return;
            }
            brushViewModel2.registerDownloadStateListener(str);
            return;
        }
        if (getPageModelProvider() == null || !(getPageModelProvider() instanceof BrushViewModel)) {
            String strStringPlus = Intrinsics.stringPlus("album/", this.albumAsin);
            Bundle arguments = getArguments();
            String string = arguments == null ? null : arguments.getString("BRUSH_NEXT_PAGE_TOKEN");
            PageViewModelProviderFactory.Companion companion = PageViewModelProviderFactory.INSTANCE;
            Application application = activity.getApplication();
            Intrinsics.checkNotNullExpressionValue(application, "activity.application");
            setPageModelProvider(companion.getCatalogPageViewModelProvider(this, application, strStringPlus, string, this.albumAsin));
            if (getPageModelProvider() instanceof AlbumBrushViewModel) {
                PageViewModelProvider pageModelProvider5 = getPageModelProvider();
                if (pageModelProvider5 == null) {
                    throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.catalog.fragment.viewmodel.AlbumBrushViewModel");
                }
                this.albumDetailViewModel = (AlbumBrushViewModel) pageModelProvider5;
            }
            PageViewModelProvider pageModelProvider6 = getPageModelProvider();
            if (pageModelProvider6 == null) {
                throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.catalog.fragment.viewmodel.BrushViewModel");
            }
            ((BrushViewModel) pageModelProvider6).setDownloadChangedListener(this);
            PageViewModelProvider pageModelProvider7 = getPageModelProvider();
            if (pageModelProvider7 == null) {
                throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.catalog.fragment.viewmodel.BrushViewModel");
            }
            ((BrushViewModel) pageModelProvider7).setOnLibraryStateChanged(new Function0<Unit>() { // from class: com.amazon.mp3.detailpages.album.AlbumDetailFragment.initPageModelProvider.2
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    AlbumDetailFragment.this.onContentStateUpdated();
                }
            });
            PageViewModelProvider pageModelProvider8 = getPageModelProvider();
            if (pageModelProvider8 == null) {
                throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.catalog.fragment.viewmodel.BrushViewModel");
            }
            setLibraryStateProvider(((BrushViewModel) pageModelProvider8).getLibraryStateProvider());
            RowButtonOnClickProvider rowButtonOnClickProvider = getRowButtonOnClickProvider();
            DefaultAddToLibraryProvider defaultAddToLibraryProvider = rowButtonOnClickProvider instanceof DefaultAddToLibraryProvider ? (DefaultAddToLibraryProvider) rowButtonOnClickProvider : null;
            if (defaultAddToLibraryProvider == null) {
                return;
            }
            defaultAddToLibraryProvider.setLibraryStateProvider(getLibraryStateProvider());
        }
    }

    private final void handleAlbumStateChanged(Long changedAlbumId, String changedAlbumAsin) {
        if (changedAlbumId != null) {
            Uri contentUri = MediaProvider.Albums.getContentUri("cirrus", changedAlbumId.longValue());
            Log.info(TAG, "The catalog album's album ID has changed, updating the album URIs from " + getContentUri() + " to " + contentUri);
            setContentUri(contentUri);
        }
        if (changedAlbumAsin == null) {
            return;
        }
        Log.info(TAG, "The catalog album's ASIN has changed from " + ((Object) this.albumAsin) + " to " + changedAlbumAsin);
        this.albumAsin = changedAlbumAsin;
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        initPageModelProvider(activity);
    }

    @Override // com.amazon.mp3.catalog.fragment.LibraryBaseViewsFragment, com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected void registerLiveDataObservers() {
        MutableLiveData<AlbumBrushViewModel.AlbumDataFetchInfo> initialAlbumDataFetchStatus;
        MutableLiveData<AlbumBrushViewModel.AlbumDataFetchInfo> initialAlbumDataFetchStatus2;
        super.registerLiveDataObservers();
        AlbumBrushViewModel albumBrushViewModel = this.albumDetailViewModel;
        if (albumBrushViewModel != null && (initialAlbumDataFetchStatus2 = albumBrushViewModel.getInitialAlbumDataFetchStatus()) != null) {
            initialAlbumDataFetchStatus2.removeObservers(this);
        }
        AlbumBrushViewModel albumBrushViewModel2 = this.albumDetailViewModel;
        if (albumBrushViewModel2 == null || (initialAlbumDataFetchStatus = albumBrushViewModel2.getInitialAlbumDataFetchStatus()) == null) {
            return;
        }
        initialAlbumDataFetchStatus.observe(getViewLifecycleOwner(), new Observer() { // from class: com.amazon.mp3.detailpages.album.AlbumDetailFragment$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                AlbumDetailFragment.m818registerLiveDataObservers$lambda17(this.f$0, (AlbumBrushViewModel.AlbumDataFetchInfo) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: registerLiveDataObservers$lambda-17, reason: not valid java name */
    public static final void m818registerLiveDataObservers$lambda17(AlbumDetailFragment this$0, AlbumBrushViewModel.AlbumDataFetchInfo albumDataFetchInfo) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (albumDataFetchInfo.getCompleted()) {
            this$0.handleAlbumStateChanged(albumDataFetchInfo.getAlbumId(), albumDataFetchInfo.getAlbumAsin());
            AlbumBrushViewModel albumBrushViewModel = this$0.albumDetailViewModel;
            if (albumBrushViewModel != null) {
                albumBrushViewModel.fetchData(0);
            }
            this$0.fetchAlbumDownloadState(null);
            String str = this$0.albumAsin;
            if (str == null) {
                return;
            }
            PageViewModelProvider pageModelProvider = this$0.getPageModelProvider();
            BrushViewModel brushViewModel = pageModelProvider instanceof BrushViewModel ? (BrushViewModel) pageModelProvider : null;
            if (brushViewModel == null) {
                return;
            }
            brushViewModel.registerDownloadStateListener(str);
        }
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected void refreshPageGridViewModel(FragmentActivity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        initPageModelProvider(activity);
        initializeBaseViewsRefinementManager();
        initializeDetailPageRefinementManager();
        initPageDataObserver();
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public PageDataRepository getPageDataRepository() {
        Uri contentUri = getContentUri();
        if (contentUri == null) {
            return null;
        }
        return new AlbumDetailDataRepository(getAlbumDetailDao(contentUri));
    }

    private final LibraryAlbumDetailDao getAlbumDetailDao(Uri uri) {
        return new LibraryAlbumDetailDao(getContext(), uri, getPageLoadLatencyCode(), Boolean.valueOf(isBadgingInLibrarySupported()), getDefaultFilters());
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected BaseViewModelAdapterProvider getAdapterProvider() {
        if (FMPMFeatureGating.SHOULD_HIDE_TRACKLIST.isEnabled() || FMPMFeatureGating.SHOULD_REMOVE_TRACKLIST.isEnabled()) {
            return new HideableBaseViewModelAdapterProvider(this, null, LifecycleOwnerKt.getLifecycleScope(this), useOptimizedAdapterObservable(), 2, null);
        }
        return super.getAdapterProvider();
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public List<UniversalBinder<?, ?>> getBinderList() {
        BaseViewsBinderConfiguration baseViewsBinderConfigurationCopy;
        BaseViewsBinderConfiguration viewsConfiguration = getViewsConfiguration();
        if (viewsConfiguration != null) {
            Function3<String, String, Integer, Unit> function3 = new Function3<String, String, Integer, Unit>() { // from class: com.amazon.mp3.detailpages.album.AlbumDetailFragment$getBinderList$1$onClickCallback$1
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(String str, String str2, Integer num) {
                    invoke(str, str2, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(String str, String str2, int i) {
                    if (str != null) {
                        AlbumDetailFragment albumDetailFragment = this.this$0;
                        AbstractBaseViewsFragment.sendUiClickMetric$default(albumDetailFragment, ActionType.PLAY_SONG, null, albumDetailFragment.getPageType(), null, str, EntityIdType.ASIN, null, Integer.valueOf(i), null, null, null, null, 3914, null);
                    } else {
                        AlbumDetailFragment albumDetailFragment2 = this.this$0;
                        AbstractBaseViewsFragment.sendUiClickMetric$default(albumDetailFragment2, ActionType.PLAY_SONG, null, albumDetailFragment2.getPageType(), null, str2, EntityIdType.CD_OBJECT_ID, null, Integer.valueOf(i), null, null, null, null, 3914, null);
                    }
                }
            };
            FragmentActivity fragmentActivityRequireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "requireActivity()");
            CollectionTrackClickListenerFactory collectionTrackClickListenerFactory = new CollectionTrackClickListenerFactory(fragmentActivityRequireActivity, this, PlaybackPageType.ALBUM_DETAIL, function3, this);
            BaseViewsBinderProvider.BinderListBuilder binderListBuilder = new BaseViewsBinderProvider.BinderListBuilder(viewsConfiguration);
            ContentViewManager contentViewManager = this.contentViewManager;
            boolean zIsDownloadTrackButtonEnabled = AmazonApplication.getCapabilities().isDownloadTrackButtonEnabled();
            Boolean bool = Boolean.TRUE;
            BaseViewsBinderProvider.BinderListBuilder binderListBuilderWithSections = binderListBuilder.withSections(new BaseViewsBinderProvider.BinderListBuilder.SectionBuilder(contentViewManager, null, bool, null, null, bool, Boolean.valueOf(zIsDownloadTrackButtonEnabled), null, collectionTrackClickListenerFactory, GeneratorAdapter.NE, null));
            ContentViewManager contentViewManager2 = this.contentViewManager;
            SeeMoreProvider seeMoreProvider = null;
            Boolean bool2 = null;
            Boolean bool3 = null;
            Boolean bool4 = null;
            Boolean bool5 = null;
            ClickListenerFactory clickListenerFactory = null;
            BaseViewsBinderConfiguration viewsConfiguration2 = getViewsConfiguration();
            if (viewsConfiguration2 == null) {
                baseViewsBinderConfigurationCopy = null;
            } else {
                baseViewsBinderConfigurationCopy = viewsConfiguration2.copy((7871 & 1) != 0 ? viewsConfiguration2.context : null, (7871 & 2) != 0 ? viewsConfiguration2.lifecycleOwner : null, (7871 & 4) != 0 ? viewsConfiguration2.styleSheet : null, (7871 & 8) != 0 ? viewsConfiguration2.imageLoader : null, (7871 & 16) != 0 ? viewsConfiguration2.adapterProvider : null, (7871 & 32) != 0 ? viewsConfiguration2.artworkProvider : null, (7871 & 64) != 0 ? viewsConfiguration2.contentEnabilityProvider : new CatalogEnabilityProvider(), (7871 & 128) != 0 ? viewsConfiguration2.contentEligibilityProvider : null, (7871 & 256) != 0 ? viewsConfiguration2.contextMenuProvider : getPrimeContextMenuProvider(), (7871 & 512) != 0 ? viewsConfiguration2.rowButtonOnClickProvider : null, (7871 & 1024) != 0 ? viewsConfiguration2.metadataOnClickListener : null, (7871 & 2048) != 0 ? viewsConfiguration2.missingSDCardProvider : null, (7871 & 4096) != 0 ? viewsConfiguration2.trackDownloadButtonProvider : null);
            }
            return BaseViewsBinderProvider.BinderListBuilder.withCollectionHeader$default(binderListBuilderWithSections.withShovelers(new BaseViewsBinderProvider.BinderListBuilder.ShovelerBuilder(contentViewManager2, seeMoreProvider, bool2, bool, bool3, bool4, bool5, clickListenerFactory, baseViewsBinderConfigurationCopy, 246, null)), getPageHeaderOnClickListenerMap(), null, 2, null).withRefinements(this.detailPageRefinementManager).withButtonNavigator(getUpsellButtonListenerProvider()).withFeaturedToggle(getFeaturedToggleListenerProvider(), this.contentViewManager).withShuffledWithText().withMultiStateButton(new MultiStateButtonClickListenerProvider() { // from class: com.amazon.mp3.detailpages.album.AlbumDetailFragment$getBinderList$1$1
                @Override // com.amazon.music.views.library.providers.MultiStateButtonClickListenerProvider
                public void onClick(MultiStateButtonModel model, View view) {
                    Intrinsics.checkNotNullParameter(model, "model");
                    AbstractBaseViewsFragment.sendUiClickMetric$default(this.this$0, model.getShowPrimary() ? ActionType.SELECT_PREVIEW_TRACKLIST : ActionType.SELECT_HIDE_TRACKLIST, null, this.this$0.getPageType(), null, null, null, null, null, null, null, null, null, 4090, null);
                    AlbumBrushViewModel albumBrushViewModel = this.this$0.albumDetailViewModel;
                    if (albumBrushViewModel == null) {
                        return;
                    }
                    albumBrushViewModel.toggleTracklistVisibility();
                }
            }).build();
        }
        Log.error(TAG, "viewsConfiguration is null, returning empty list of binders");
        return CollectionsKt__CollectionsKt.emptyList();
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public PageType getPageType() {
        return PageType.DETAIL_ALBUM;
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public BaseViewsDiffUtilFactory getDiffUtilFactory() {
        return new BaseViewsDiffUtilFactory() { // from class: com.amazon.mp3.detailpages.album.AlbumDetailFragment.getDiffUtilFactory.1
            @Override // com.amazon.mp3.catalog.fragment.adapter.BaseViewsDiffUtilFactory
            public BaseViewsDiffUtil getBaseViewsDiffUtil(List<? extends BaseViewModel> oldList, List<? extends BaseViewModel> newList) {
                Intrinsics.checkNotNullParameter(oldList, "oldList");
                Intrinsics.checkNotNullParameter(newList, "newList");
                return new BaseViewsDiffUtil(oldList, newList);
            }
        };
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment, androidx.fragment.app.Fragment
    public void onResume() {
        String str;
        super.onResume();
        if (!isDisplayingOnlyLibraryContent() || (str = this.albumAsin) == null) {
            return;
        }
        PageViewModelProvider pageModelProvider = getPageModelProvider();
        BrushViewModel brushViewModel = pageModelProvider instanceof BrushViewModel ? (BrushViewModel) pageModelProvider : null;
        if (brushViewModel == null) {
            return;
        }
        brushViewModel.registerDownloadStateListener(str);
    }

    @Override // com.amazon.mp3.catalog.fragment.LibraryBaseViewsFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        syncPendingAddedTracks();
        PageViewModelProvider pageModelProvider = getPageModelProvider();
        BrushViewModel brushViewModel = pageModelProvider instanceof BrushViewModel ? (BrushViewModel) pageModelProvider : null;
        if (brushViewModel != null) {
            brushViewModel.unregisterDownloadStateListener();
        }
        this.contentViewManager.sendUiContentViewEvent();
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        PageViewModelProvider pageModelProvider = getPageModelProvider();
        BrushViewModel brushViewModel = pageModelProvider instanceof BrushViewModel ? (BrushViewModel) pageModelProvider : null;
        if (brushViewModel == null) {
            return;
        }
        brushViewModel.unregisterDownloadStateListener();
    }

    @Override // com.amazon.mp3.catalog.fragment.LibraryBaseViewsFragment, com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected boolean requireNetworkConnection() {
        return !isDisplayingOnlyLibraryContent() || super.requireNetworkConnection();
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment, com.amazon.mp3.dialog.NoNetworkEmptyPageDialog.Listener
    public void goOfflineClickAction() {
        if (this.displayedNoNetworkDialogFromOffline) {
            resumeOfflineFromNoNetworkDialog();
        } else {
            downloadSelectionChanged(true);
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getDefault(), null, new C03401(null), 2, null);
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.detailpages.album.AlbumDetailFragment$goOfflineClickAction$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AlbumDetailFragment.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.detailpages.album.AlbumDetailFragment$goOfflineClickAction$1", f = "AlbumDetailFragment.kt", i = {}, l = {730}, m = "invokeSuspend", n = {}, s = {})
    static final class C03401 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C03401(Continuation<? super C03401> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AlbumDetailFragment.this.new C03401(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03401) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                LibraryItemFactory libraryItemFactory = AmazonApplication.getLibraryItemFactory();
                if (libraryItemFactory != null) {
                    boolean zIsAlbumAvailableOffline = ((CirrusSourceLibraryItemFactory) libraryItemFactory).isAlbumAvailableOffline(AlbumDetailFragment.this.getContentUri());
                    MainCoroutineDispatcher main2 = Dispatchers.getMain();
                    C00751 c00751 = new C00751(zIsAlbumAvailableOffline, AlbumDetailFragment.this, null);
                    this.label = 1;
                    if (BuildersKt.withContext(main2, c00751, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.library.item.factory.CirrusSourceLibraryItemFactory");
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: com.amazon.mp3.detailpages.album.AlbumDetailFragment$goOfflineClickAction$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: AlbumDetailFragment.kt */
        @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
        @DebugMetadata(c = "com.amazon.mp3.detailpages.album.AlbumDetailFragment$goOfflineClickAction$1$1", f = "AlbumDetailFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class C00751 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ boolean $isAlbumAvailableOffline;
            int label;
            final /* synthetic */ AlbumDetailFragment this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00751(boolean z, AlbumDetailFragment albumDetailFragment, Continuation<? super C00751> continuation) {
                super(2, continuation);
                this.$isAlbumAvailableOffline = z;
                this.this$0 = albumDetailFragment;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C00751(this.$isAlbumAvailableOffline, this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
            public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C00751) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                if (this.$isAlbumAvailableOffline) {
                    this.this$0.switchToOffline();
                } else {
                    AlbumDetailFragment.super.goOfflineClickAction();
                }
                return Unit.INSTANCE;
            }
        }
    }

    @Override // com.amazon.music.imageloader.ImageLoader.ImageLoaderCallback
    public void onBitmapLoaded(Bitmap originalBitmap) {
        Context context;
        StyleSheet styleSheet;
        if (originalBitmap == null || (context = getContext()) == null || (styleSheet = getStyleSheet()) == null) {
            return;
        }
        Bitmap sentBitmap = originalBitmap.copy(Bitmap.Config.ARGB_8888, true);
        DynamicBackgroundEffectUtils dynamicBackgroundEffectUtils = DynamicBackgroundEffectUtils.INSTANCE;
        Intrinsics.checkNotNullExpressionValue(sentBitmap, "sentBitmap");
        dynamicBackgroundEffectUtils.getTransformBlurredImageSubscription(sentBitmap, context, styleSheet, new DynamicBackgroundImageCondition(null, null, AlphaKey.GLASS_1, null, 11, null)).subscribe(new Action1() { // from class: com.amazon.mp3.detailpages.album.AlbumDetailFragment$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                AlbumDetailFragment.m816onBitmapLoaded$lambda24$lambda23$lambda21(this.f$0, (Bitmap) obj);
            }
        }, new Action1() { // from class: com.amazon.mp3.detailpages.album.AlbumDetailFragment$$ExternalSyntheticLambda2
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                AlbumDetailFragment.m817onBitmapLoaded$lambda24$lambda23$lambda22(this.f$0, (Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onBitmapLoaded$lambda-24$lambda-23$lambda-21, reason: not valid java name */
    public static final void m816onBitmapLoaded$lambda24$lambda23$lambda21(AlbumDetailFragment this$0, Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ImageView imageView = this$0.backgroundImageView;
        if (imageView == null) {
            return;
        }
        imageView.setImageBitmap(bitmap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onBitmapLoaded$lambda-24$lambda-23$lambda-22, reason: not valid java name */
    public static final void m817onBitmapLoaded$lambda24$lambda23$lambda22(AlbumDetailFragment this$0, Throwable th) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.error(TAG, Intrinsics.stringPlus("Background is not loaded successfully. Error: ", th.getMessage()));
        ImageView imageView = this$0.backgroundImageView;
        if (imageView == null) {
            return;
        }
        imageView.setVisibility(8);
    }

    @Override // com.amazon.mp3.catalog.fragment.LibraryBaseViewsFragment, com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected RowButtonOnClickProvider getRowButtonOnClickProvider(FragmentActivity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (AmazonApplication.getCapabilities().isLikesEverywhereEnabled()) {
            if (getRowButtonOnClickProvider() == null) {
                setRowButtonOnClickProvider(new DefaultLikesProvider(activity, getLibraryStateProviderInstance(), getTrackContextMenuListener(), true, getPageType()));
            }
            return getRowButtonOnClickProvider();
        }
        return super.getRowButtonOnClickProvider(activity);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void switchToOffline() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            arguments.putBoolean("EXTRA_OFFLINE_LIBRARY_NAVIGATION", true);
        }
        swapLibraryAndCatalogSource();
    }

    private final void switchToOnlineLibrary() {
        this.defaultFilterString = RefinementFilterType.LIBRARY.toString();
        swapLibraryAndCatalogSource();
    }

    private final void swapLibraryAndCatalogSource() {
        RecyclerView recyclerView = getRecyclerView();
        if (recyclerView != null) {
            recyclerView.setAdapter(null);
        }
        setPageModel(null);
        initBinderConfiguration();
        FragmentActivity activity = getActivity();
        if (activity != null) {
            refreshPageGridViewModel(activity);
        }
        retrievePageData();
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected void updatePageModel(PageGridViewModel pageGridViewModel, boolean isInitialPage, boolean isMorePage) {
        AlbumBrushViewModel albumBrushViewModel;
        String collectionId;
        exitPageIfRequired(isInitialPage);
        if (navigatedToCatalog() && pageGridViewModel == null && getContentUri() != null) {
            if (getContentUri() == null) {
                return;
            }
            displayViewAccordingToAlbumOwnership();
            return;
        }
        setPageModel(pageGridViewModel);
        EntityEligibilitiesProvider entityEligibilitiesProvider = null;
        byte b = 0;
        byte b2 = 0;
        if (navigatedToCatalog() && (collectionId = getCollectionId()) != null) {
            long jGenerateAlbumDBId$default = generateAlbumDBId$default(this, collectionId, null, 2, null);
            Long lValueOf = !Intrinsics.areEqual(MediaProvider.Albums.getContentUri("cirrus", jGenerateAlbumDBId$default), getContentUri()) ? Long.valueOf(jGenerateAlbumDBId$default) : null;
            if (Intrinsics.areEqual(collectionId, this.albumAsin)) {
                collectionId = null;
            }
            handleAlbumStateChanged(lValueOf, collectionId);
        }
        fetchAlbumDownloadState(pageGridViewModel);
        AlbumMetadata albumMetadataRetrieveAlbumMetadata = retrieveAlbumMetadata();
        if (albumMetadataRetrieveAlbumMetadata != null) {
            if (new AlbumModelConfiguration(entityEligibilitiesProvider, b2 == true ? 1 : 0, 3, b == true ? 1 : 0).shouldShowTracklistPreview(albumMetadataRetrieveAlbumMetadata) && (albumBrushViewModel = this.albumDetailViewModel) != null) {
                AlbumBrushViewModel.setTracklistModelsVisibility$default(albumBrushViewModel, getPageModel(), false, 2, null);
            }
        }
        super.updatePageModel(pageGridViewModel, isInitialPage, isMorePage);
        getTrackInformationFetcher().setPageModel(pageGridViewModel);
        setContextMenuProviderForActionBar();
        setFilterButton();
        handleDeeplink();
        bindBackgroundImage();
    }

    private final void displayViewAccordingToAlbumOwnership() {
        LibraryAlbumDetailViewModel libraryViewModel = getLibraryViewModel();
        if (libraryViewModel != null) {
            libraryViewModel.getOwnedStateData().observe(this, new Observer() { // from class: com.amazon.mp3.detailpages.album.AlbumDetailFragment$$ExternalSyntheticLambda6
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    AlbumDetailFragment.m809displayViewAccordingToAlbumOwnership$lambda28(this.f$0, (Boolean) obj);
                }
            });
            libraryViewModel.fetchIsOwnedState();
        } else {
            displayViewState();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: displayViewAccordingToAlbumOwnership$lambda-28, reason: not valid java name */
    public static final void m809displayViewAccordingToAlbumOwnership$lambda28(AlbumDetailFragment this$0, Boolean bool) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (Intrinsics.areEqual(bool, Boolean.TRUE)) {
            this$0.switchToOnlineLibrary();
        } else if (Intrinsics.areEqual(bool, Boolean.FALSE)) {
            this$0.displayViewState();
        }
    }

    private final LibraryAlbumDetailViewModel getLibraryViewModel() {
        FragmentActivity activity;
        PageDataRepository pageDataRepository = getPageDataRepository();
        if (pageDataRepository == null || (activity = getActivity()) == null) {
            return null;
        }
        PageViewModelFactoryProvider pageViewModelFactoryProvider = PageViewModelFactoryProvider.INSTANCE;
        Application application = activity.getApplication();
        Intrinsics.checkNotNullExpressionValue(application, "it.application");
        PageViewModelProvider viewModel = pageViewModelFactoryProvider.getViewModel(this, new PageViewModelFactoryProvider.LibraryAlbumDetailViewModelFactory(application, pageDataRepository));
        if (viewModel != null) {
            return (LibraryAlbumDetailViewModel) viewModel;
        }
        throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.detailpages.album.LibraryAlbumDetailViewModel");
    }

    private final void bindBackgroundImage() {
        BaseViewsBinderConfiguration viewsConfiguration;
        ImageLoader imageLoader;
        Unit unit;
        ContentMetadata contentMetadata = getContentMetadata();
        if (contentMetadata instanceof AlbumMetadata) {
            String imageUrl = ((AlbumMetadata) contentMetadata).getImageUrl();
            StyleSheet styleSheet = null;
            byte b = 0;
            if (imageUrl == null || (viewsConfiguration = getViewsConfiguration()) == null || (imageLoader = viewsConfiguration.getImageLoader()) == null) {
                unit = null;
            } else {
                imageLoader.loadImage(imageUrl, this);
                unit = Unit.INSTANCE;
            }
            if (unit == null) {
                new DefaultCachedArtworkProvider(getActivity(), styleSheet, 2, b == true ? 1 : 0).loadCachedArtwork(this, this.backgroundImageView, contentMetadata);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0051  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int randomTrackIndex() {
        /*
            r10 = this;
            com.amazon.music.views.library.models.PageGridViewModel r0 = r10.getPageModel()
            java.lang.String r1 = "TRACK_LIST_TAG"
            r2 = 0
            r3 = 1
            r4 = 0
            if (r0 != 0) goto Ld
        Lb:
            r5 = r2
            goto L58
        Ld:
            java.util.List r0 = r0.getModels()
            if (r0 != 0) goto L14
            goto Lb
        L14:
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            java.util.Iterator r0 = r0.iterator()
        L1d:
            boolean r6 = r0.hasNext()
            if (r6 == 0) goto L58
            java.lang.Object r6 = r0.next()
            r7 = r6
            com.amazon.music.views.library.models.base.BaseViewModel r7 = (com.amazon.music.views.library.models.base.BaseViewModel) r7
            java.util.List r8 = r7.getTags()
            if (r8 != 0) goto L32
        L30:
            r8 = r4
            goto L3b
        L32:
            java.lang.String r9 = "TRACK_TAG"
            boolean r8 = r8.contains(r9)
            if (r8 != r3) goto L30
            r8 = r3
        L3b:
            if (r8 != 0) goto L51
            java.util.List r7 = r7.getTags()
            if (r7 != 0) goto L45
        L43:
            r7 = r4
            goto L4c
        L45:
            boolean r7 = r7.contains(r1)
            if (r7 != r3) goto L43
            r7 = r3
        L4c:
            if (r7 == 0) goto L4f
            goto L51
        L4f:
            r7 = r4
            goto L52
        L51:
            r7 = r3
        L52:
            if (r7 == 0) goto L1d
            r5.add(r6)
            goto L1d
        L58:
            if (r5 != 0) goto L5c
        L5a:
            r0 = r4
            goto L63
        L5c:
            boolean r0 = r5.isEmpty()
            if (r0 != 0) goto L5a
            r0 = r3
        L63:
            r6 = -1
            if (r0 == 0) goto Lac
            java.lang.Object r0 = kotlin.collections.CollectionsKt.first(r5)
            com.amazon.music.views.library.models.base.BaseViewModel r0 = (com.amazon.music.views.library.models.base.BaseViewModel) r0
            java.util.List r0 = r0.getTags()
            if (r0 != 0) goto L73
            goto L7a
        L73:
            boolean r0 = r0.contains(r1)
            if (r0 != r3) goto L7a
            r4 = r3
        L7a:
            if (r4 == 0) goto L91
            java.lang.Object r0 = kotlin.collections.CollectionsKt.first(r5)
            boolean r1 = r0 instanceof com.amazon.music.views.library.models.SectionGridViewModel
            if (r1 == 0) goto L87
            com.amazon.music.views.library.models.SectionGridViewModel r0 = (com.amazon.music.views.library.models.SectionGridViewModel) r0
            goto L88
        L87:
            r0 = r2
        L88:
            if (r0 != 0) goto L8c
            r5 = r2
            goto L91
        L8c:
            java.util.List r0 = r0.getModels()
            r5 = r0
        L91:
            if (r5 != 0) goto L94
            goto Lac
        L94:
            kotlin.ranges.IntRange r0 = kotlin.collections.CollectionsKt.getIndices(r5)
            if (r0 != 0) goto L9b
            goto Lac
        L9b:
            boolean r1 = r0.isEmpty()
            r1 = r1 ^ r3
            if (r1 == 0) goto La3
            r2 = r0
        La3:
            if (r2 != 0) goto La6
            goto Lac
        La6:
            kotlin.random.Random$Default r0 = kotlin.random.Random.INSTANCE
            int r6 = kotlin.ranges.RangesKt.random(r2, r0)
        Lac:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.detailpages.album.AlbumDetailFragment.randomTrackIndex():int");
    }

    private final Map<PageHeaderActionIconModel.ActionType, ActionIconOnClickListener> getPageHeaderOnClickListenerMap() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        PageHeaderActionIconModel.ActionType actionType = AmazonApplication.getCapabilities().isLikesEverywhereEnabled() ? PageHeaderActionIconModel.ActionType.FAVORITE : PageHeaderActionIconModel.ActionType.ADD;
        linkedHashMap.put(PageHeaderActionIconModel.ActionType.SHUFFLE, new ActionIconOnClickListener() { // from class: com.amazon.mp3.detailpages.album.AlbumDetailFragment.getPageHeaderOnClickListenerMap.1
            @Override // com.amazon.music.views.library.binders.ActionIconOnClickListener
            public void onClick(Context context, ContentMetadata metadata) {
                int iRandomTrackIndex;
                Intrinsics.checkNotNullParameter(context, "context");
                HapticsUtil.INSTANCE.hapticFeedbackOnPlayback();
                if (!(metadata instanceof AlbumMetadata) || (iRandomTrackIndex = AlbumDetailFragment.this.randomTrackIndex()) == -1) {
                    return;
                }
                AlbumDetailFragment.emitUiClickMetric$default(AlbumDetailFragment.this, ActionType.PLAY_SHUFFLE_ALL, iRandomTrackIndex, null, 4, null);
                AlbumDetailFragment.this.initiatePlayback(true, iRandomTrackIndex, (AlbumMetadata) metadata);
            }
        });
        linkedHashMap.put(PageHeaderActionIconModel.ActionType.SHARE, new ActionIconOnClickListener() { // from class: com.amazon.mp3.detailpages.album.AlbumDetailFragment.getPageHeaderOnClickListenerMap.2
            @Override // com.amazon.music.views.library.binders.ActionIconOnClickListener
            public void onClick(Context context, ContentMetadata metadata) {
                Intrinsics.checkNotNullParameter(context, "context");
                String musicDomain = EndpointsProvider.get().getMusicDomain();
                String albumArtUrl = AlbumDetailFragment.this.getAlbumArtUrl();
                if (albumArtUrl == null) {
                    albumArtUrl = new CTAPrimeCache(AlbumDetailFragment.this.getActivity()).getArtworkUrlForAsin(AlbumDetailFragment.this.getAlbumAsin());
                }
                new AlbumShareProvider(AlbumDetailFragment.this.getActivity(), musicDomain, AlbumDetailFragment.this.getAlbumAsin(), AlbumDetailFragment.this.getCollectionName(), AlbumDetailFragment.this.getCollectionArtistName(), albumArtUrl).startShare();
            }
        });
        linkedHashMap.put(PageHeaderActionIconModel.ActionType.STATION, new ActionIconOnClickListener() { // from class: com.amazon.mp3.detailpages.album.AlbumDetailFragment.getPageHeaderOnClickListenerMap.3
            @Override // com.amazon.music.views.library.binders.ActionIconOnClickListener
            public void onClick(Context context, ContentMetadata metadata) {
                Intrinsics.checkNotNullParameter(context, "context");
                StationUtils.sendStartSFAUiClickMetrics(AlbumDetailFragment.this.getAlbumAsin(), EntityIdType.SFA_ALBUM_SEED);
                StationUtils.startSFA(AlbumDetailFragment.this.getActivity(), AlbumDetailFragment.this.getAlbumAsin(), "ALBUM_SEED", PlaybackPageType.ALBUM_DETAIL, ControlSource.APP_UI);
            }
        });
        linkedHashMap.put(PageHeaderActionIconModel.ActionType.PLAY, new ActionIconOnClickListener() { // from class: com.amazon.mp3.detailpages.album.AlbumDetailFragment.getPageHeaderOnClickListenerMap.4
            @Override // com.amazon.music.views.library.binders.ActionIconOnClickListener
            public void onClick(Context context, ContentMetadata metadata) {
                Intrinsics.checkNotNullParameter(context, "context");
                HapticsUtil.INSTANCE.hapticFeedbackOnPlayback();
                AlbumMetadata albumMetadata = metadata instanceof AlbumMetadata ? (AlbumMetadata) metadata : null;
                if (albumMetadata == null) {
                    return;
                }
                AlbumDetailFragment albumDetailFragment = AlbumDetailFragment.this;
                AlbumDetailFragment.emitUiClickMetric$default(albumDetailFragment, ActionType.PLAY_ALBUM, -1, null, 4, null);
                albumDetailFragment.initiatePlayback(false, 0, albumMetadata);
            }
        });
        linkedHashMap.put(actionType, new ActionIconOnClickListener() { // from class: com.amazon.mp3.detailpages.album.AlbumDetailFragment.getPageHeaderOnClickListenerMap.5
            @Override // com.amazon.music.views.library.binders.ActionIconOnClickListener
            public void onClick(Context context, ContentMetadata metadata) {
                Intrinsics.checkNotNullParameter(context, "context");
                AlbumMetadata albumMetadata = metadata instanceof AlbumMetadata ? (AlbumMetadata) metadata : null;
                if (albumMetadata == null) {
                    return;
                }
                final AlbumDetailFragment albumDetailFragment = AlbumDetailFragment.this;
                Boolean isFollowed = albumMetadata.getIsFollowed();
                if (!(isFollowed == null ? false : isFollowed.booleanValue())) {
                    AlbumDetailFragment.addTracksAndDownload$default(albumDetailFragment, false, albumMetadata, null, 4, null);
                    AlbumDetailFragment.emitUiClickMetric$default(albumDetailFragment, ActionType.ADD_ALBUM_TO_LIBRARY, -1, null, 4, null);
                } else {
                    new RemoveLibraryContentProvider(albumDetailFragment.getActivity(), new RemoveLibraryContentProvider.OnRemoveLibraryContentListener() { // from class: com.amazon.mp3.detailpages.album.AlbumDetailFragment$getPageHeaderOnClickListenerMap$5$onClick$1$removeLibraryContentProvider$1
                        @Override // com.amazon.mp3.fragment.contextmenu.RemoveLibraryContentProvider.OnRemoveLibraryContentListener
                        public void onRemoveContentComplete(Uri uri) {
                            Intrinsics.checkNotNullParameter(uri, "uri");
                        }

                        @Override // com.amazon.mp3.fragment.contextmenu.RemoveLibraryContentProvider.OnRemoveLibraryContentListener
                        public void onRemoveContentConfirm() {
                            FragmentManager supportFragmentManager;
                            List<BaseViewModel> unfilteredFilterableBaseViewModels;
                            BaseViewsRefinementManager baseViewsRefinementManager = albumDetailFragment.refinementsManager;
                            if (baseViewsRefinementManager != null && (unfilteredFilterableBaseViewModels = baseViewsRefinementManager.getUnfilteredFilterableBaseViewModels()) != null) {
                                ArrayList arrayList = new ArrayList();
                                for (Object obj : unfilteredFilterableBaseViewModels) {
                                    if (obj instanceof LibraryAddableModel) {
                                        arrayList.add(obj);
                                    }
                                }
                                ArrayList<LibraryAddableModel> arrayList2 = new ArrayList();
                                for (Object obj2 : arrayList) {
                                    if (!((LibraryAddableModel) obj2).getIsInLibrary()) {
                                        arrayList2.add(obj2);
                                    }
                                }
                                for (LibraryAddableModel libraryAddableModel : arrayList2) {
                                    LibraryAddableModel.updateLibraryState$default(libraryAddableModel, false, null, 2, null);
                                    ContentMetadata metadata2 = libraryAddableModel.getMetadata();
                                    TrackMetadata trackMetadata = metadata2 instanceof TrackMetadata ? (TrackMetadata) metadata2 : null;
                                    if (trackMetadata != null) {
                                        trackMetadata.setInLibrary(Boolean.FALSE);
                                    }
                                }
                            }
                            albumDetailFragment.updateHeaderActions(true);
                            AlbumDetailFragment.emitUiClickMetric$default(albumDetailFragment, ActionType.REMOVE_ALBUM_FROM_CLOUD, -1, null, 4, null);
                            FragmentActivity activity = albumDetailFragment.getActivity();
                            if (activity == null || (supportFragmentManager = activity.getSupportFragmentManager()) == null) {
                                return;
                            }
                            supportFragmentManager.popBackStack();
                        }
                    }).removeAlbumFromCloud((AlbumMetadata) metadata);
                }
            }
        });
        linkedHashMap.put(PageHeaderActionIconModel.ActionType.DOWNLOAD, new DownloadIconOnClickListener() { // from class: com.amazon.mp3.detailpages.album.AlbumDetailFragment.getPageHeaderOnClickListenerMap.6
            @Override // com.amazon.music.views.library.binders.DownloadIconOnClickListener
            public void onClick(Context context, ContentMetadata metadata, DownloadStateModel.DownloadState downloadState) {
                Intrinsics.checkNotNullParameter(context, "context");
                Intrinsics.checkNotNullParameter(downloadState, "downloadState");
                AlbumMetadata albumMetadata = metadata instanceof AlbumMetadata ? (AlbumMetadata) metadata : null;
                if (albumMetadata == null) {
                    return;
                }
                AlbumDetailFragment albumDetailFragment = AlbumDetailFragment.this;
                if (AmazonApplication.getCapabilities().isLikesEverywhereEnabled()) {
                    albumDetailFragment.likeAndDownloadAlbum(albumMetadata, downloadState);
                } else {
                    albumDetailFragment.addTracksAndDownload(true, albumMetadata, downloadState);
                }
            }
        });
        return linkedHashMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void likeAndDownloadAlbum(final AlbumMetadata metadata, final DownloadStateModel.DownloadState downloadState) {
        LikesAndFollowsDBOperations.INSTANCE.updateAlbumLikeStatusInLocalDB(true, metadata).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.mp3.detailpages.album.AlbumDetailFragment$$ExternalSyntheticLambda8
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                AlbumDetailFragment.m814likeAndDownloadAlbum$lambda35(this.f$0, metadata, downloadState, (Boolean) obj);
            }
        }, new Action1() { // from class: com.amazon.mp3.detailpages.album.AlbumDetailFragment$$ExternalSyntheticLambda9
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                AlbumDetailFragment.m815likeAndDownloadAlbum$lambda36((Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: likeAndDownloadAlbum$lambda-35, reason: not valid java name */
    public static final void m814likeAndDownloadAlbum$lambda35(AlbumDetailFragment this$0, AlbumMetadata metadata, DownloadStateModel.DownloadState downloadState, Boolean bool) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(metadata, "$metadata");
        Intrinsics.checkNotNullParameter(downloadState, "$downloadState");
        if (Intrinsics.areEqual(bool, Boolean.TRUE)) {
            this$0.startAlbumDownloadAction(metadata, downloadState);
            this$0.updateHeaderActions(false);
            this$0.refreshPageModelState();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: likeAndDownloadAlbum$lambda-36, reason: not valid java name */
    public static final void m815likeAndDownloadAlbum$lambda36(Throwable th) {
        Log.error(TAG, String.valueOf(th.getMessage()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void initiatePlayback(boolean shuffleOn, int trackPosition, AlbumMetadata contentMetadata) {
        if (!(UserSubscriptionUtil.isNightwingOnly() && !Feature.should_migrate_free_to_cq_for_non_station.isEnabled() && Intrinsics.areEqual(contentMetadata.getIsOwned(), Boolean.FALSE))) {
            BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), null, new C03421(contentMetadata, trackPosition, shuffleOn, null), 2, null);
        } else {
            StationUtils.startSFA(getActivity(), getAlbumAsin(), "ALBUM_SEED", PlaybackPageType.ALBUM_DETAIL, ControlSource.APP_UI);
            StationUtils.sendStartSFAUiClickMetrics(getAlbumAsin(), EntityIdType.SFA_ALBUM_SEED);
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.detailpages.album.AlbumDetailFragment$initiatePlayback$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AlbumDetailFragment.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.detailpages.album.AlbumDetailFragment$initiatePlayback$1", f = "AlbumDetailFragment.kt", i = {0}, l = {1071, 1073}, m = "invokeSuspend", n = {"playAction"}, s = {"L$0"})
    static final class C03421 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ AlbumMetadata $contentMetadata;
        final /* synthetic */ boolean $shuffleOn;
        final /* synthetic */ int $trackPosition;
        Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03421(AlbumMetadata albumMetadata, int i, boolean z, Continuation<? super C03421> continuation) {
            super(2, continuation);
            this.$contentMetadata = albumMetadata;
            this.$trackPosition = i;
            this.$shuffleOn = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AlbumDetailFragment.this.new C03421(this.$contentMetadata, this.$trackPosition, this.$shuffleOn, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03421) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:24:0x0086 A[RETURN] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r11) {
            /*
                r10 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r10.label
                r2 = 2
                r3 = 1
                if (r1 == 0) goto L24
                if (r1 == r3) goto L1b
                if (r1 != r2) goto L13
                kotlin.ResultKt.throwOnFailure(r11)
                goto L87
            L13:
                java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r11.<init>(r0)
                throw r11
            L1b:
                java.lang.Object r1 = r10.L$0
                com.amazon.mp3.detailpages.actions.PlayCollectionAction r1 = (com.amazon.mp3.detailpages.actions.PlayCollectionAction) r1
                kotlin.ResultKt.throwOnFailure(r11)
            L22:
                r6 = r1
                goto L67
            L24:
                kotlin.ResultKt.throwOnFailure(r11)
                kotlin.jvm.internal.Ref$BooleanRef r11 = new kotlin.jvm.internal.Ref$BooleanRef
                r11.<init>()
                r11.element = r3
                com.amazon.mp3.detailpages.album.AlbumDetailFragment r1 = com.amazon.mp3.detailpages.album.AlbumDetailFragment.this
                com.amazon.music.views.library.providers.ContentEnabilityProvider r1 = r1.getContentEnabilityProvider()
                if (r1 != 0) goto L37
                goto L45
            L37:
                com.amazon.music.views.library.metadata.AlbumMetadata r4 = r10.$contentMetadata
                boolean r5 = r1.isContentAccessAvailable(r4)
                if (r5 != 0) goto L45
                r1.handleContentAccessUnavailable(r4)
                r1 = 0
                r11.element = r1
            L45:
                boolean r11 = r11.element
                if (r11 == 0) goto L87
                com.amazon.mp3.detailpages.actions.PlayCollectionAction r1 = new com.amazon.mp3.detailpages.actions.PlayCollectionAction
                com.amazon.mp3.detailpages.album.AlbumDetailFragment r11 = com.amazon.mp3.detailpages.album.AlbumDetailFragment.this
                com.amazon.mp3.detailpages.actions.DefaultTrackInformationFetcher r11 = com.amazon.mp3.detailpages.album.AlbumDetailFragment.access$getTrackInformationFetcher(r11)
                com.amazon.music.metrics.mts.event.types.PlaybackPageType r4 = com.amazon.music.metrics.mts.event.types.PlaybackPageType.ALBUM_DETAIL
                r1.<init>(r11, r4)
                com.amazon.mp3.detailpages.album.AlbumDetailFragment r11 = com.amazon.mp3.detailpages.album.AlbumDetailFragment.this
                com.amazon.mp3.detailpages.actions.DefaultTrackInformationFetcher r11 = com.amazon.mp3.detailpages.album.AlbumDetailFragment.access$getTrackInformationFetcher(r11)
                r10.L$0 = r1
                r10.label = r3
                java.lang.Object r11 = r11.getTracks(r10)
                if (r11 != r0) goto L22
                return r0
            L67:
                r4 = r11
                java.util.List r4 = (java.util.List) r4
                kotlinx.coroutines.MainCoroutineDispatcher r11 = kotlinx.coroutines.Dispatchers.getMain()
                com.amazon.mp3.detailpages.album.AlbumDetailFragment$initiatePlayback$1$2 r1 = new com.amazon.mp3.detailpages.album.AlbumDetailFragment$initiatePlayback$1$2
                com.amazon.mp3.detailpages.album.AlbumDetailFragment r5 = com.amazon.mp3.detailpages.album.AlbumDetailFragment.this
                int r7 = r10.$trackPosition
                boolean r8 = r10.$shuffleOn
                r9 = 0
                r3 = r1
                r3.<init>(r4, r5, r6, r7, r8, r9)
                r3 = 0
                r10.L$0 = r3
                r10.label = r2
                java.lang.Object r11 = kotlinx.coroutines.BuildersKt.withContext(r11, r1, r10)
                if (r11 != r0) goto L87
                return r0
            L87:
                kotlin.Unit r11 = kotlin.Unit.INSTANCE
                return r11
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.detailpages.album.AlbumDetailFragment.C03421.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* JADX INFO: renamed from: com.amazon.mp3.detailpages.album.AlbumDetailFragment$initiatePlayback$1$2, reason: invalid class name */
        /* JADX INFO: compiled from: AlbumDetailFragment.kt */
        @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
        @DebugMetadata(c = "com.amazon.mp3.detailpages.album.AlbumDetailFragment$initiatePlayback$1$2", f = "AlbumDetailFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ List<MusicTrack> $musicTracks;
            final /* synthetic */ PlayCollectionAction $playAction;
            final /* synthetic */ boolean $shuffleOn;
            final /* synthetic */ int $trackPosition;
            int label;
            final /* synthetic */ AlbumDetailFragment this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass2(List<? extends MusicTrack> list, AlbumDetailFragment albumDetailFragment, PlayCollectionAction playCollectionAction, int i, boolean z, Continuation<? super AnonymousClass2> continuation) {
                super(2, continuation);
                this.$musicTracks = list;
                this.this$0 = albumDetailFragment;
                this.$playAction = playCollectionAction;
                this.$trackPosition = i;
                this.$shuffleOn = z;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass2(this.$musicTracks, this.this$0, this.$playAction, this.$trackPosition, this.$shuffleOn, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
            public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                FragmentActivity activity;
                IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                List<MusicTrack> list = this.$musicTracks;
                ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    arrayList.add(((MusicTrack) it.next()).getAsin());
                }
                DbUtil.WhereClause filterByIds$default = PlayCollectionAction.TrackInformationFetcher.DefaultImpls.getFilterByIds$default(this.this$0.getTrackInformationFetcher(), CollectionsKt___CollectionsKt.toMutableList((Collection) arrayList), null, 2, null);
                FragmentActivity activity2 = this.this$0.getActivity();
                if (((activity2 == null || activity2.isFinishing()) ? false : true) && (activity = this.this$0.getActivity()) != null) {
                    PlayCollectionAction playCollectionAction = this.$playAction;
                    AlbumDetailFragment albumDetailFragment = this.this$0;
                    int i = this.$trackPosition;
                    boolean z = this.$shuffleOn;
                    boolean zIsAlbumDownloaded = albumDetailFragment.isAlbumDownloaded();
                    String collectionId = albumDetailFragment.getCollectionId();
                    String collectionName = albumDetailFragment.getCollectionName();
                    Uri collectionUri = albumDetailFragment.getCollectionUri();
                    Observable<DbUtil.WhereClause> observableJust = Observable.just(filterByIds$default);
                    Intrinsics.checkNotNullExpressionValue(observableJust, "just(currentTrackFilter)");
                    playCollectionAction.startNowPlayingActivity(activity, zIsAlbumDownloaded, collectionId, collectionName, collectionUri, i, z, true, observableJust, albumDetailFragment.refinementSortOrder(), false, albumDetailFragment.isRefinementFilterApplied(), albumDetailFragment.getPlaybackAugmentations(false));
                }
                return Unit.INSTANCE;
            }
        }
    }

    static /* synthetic */ void addTracksAndDownload$default(AlbumDetailFragment albumDetailFragment, boolean z, ContentMetadata contentMetadata, DownloadStateModel.DownloadState downloadState, int i, Object obj) {
        if ((i & 4) != 0) {
            downloadState = null;
        }
        albumDetailFragment.addTracksAndDownload(z, contentMetadata, downloadState);
    }

    /* JADX INFO: renamed from: com.amazon.mp3.detailpages.album.AlbumDetailFragment$addTracksAndDownload$1, reason: invalid class name */
    /* JADX INFO: compiled from: AlbumDetailFragment.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.detailpages.album.AlbumDetailFragment$addTracksAndDownload$1", f = "AlbumDetailFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ DownloadStateModel.DownloadState $downloadState;
        final /* synthetic */ boolean $isDownloadNeeded;
        final /* synthetic */ ContentMetadata $metadata;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(ContentMetadata contentMetadata, boolean z, DownloadStateModel.DownloadState downloadState, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$metadata = contentMetadata;
            this.$isDownloadNeeded = z;
            this.$downloadState = downloadState;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AlbumDetailFragment.this.new AnonymousClass1(this.$metadata, this.$isDownloadNeeded, this.$downloadState, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            List<BaseViewModel> unfilteredFilterableBaseViewModels;
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            ContentEnabilityProvider contentEnabilityProvider = AlbumDetailFragment.this.getContentEnabilityProvider();
            if (contentEnabilityProvider != null) {
                final ContentMetadata contentMetadata = this.$metadata;
                final AlbumDetailFragment albumDetailFragment = AlbumDetailFragment.this;
                final boolean z = this.$isDownloadNeeded;
                final DownloadStateModel.DownloadState downloadState = this.$downloadState;
                if (!contentEnabilityProvider.isContentAccessAvailable(contentMetadata)) {
                    contentEnabilityProvider.handleContentAccessUnavailable(contentMetadata);
                } else {
                    ArrayList arrayList = new ArrayList();
                    BaseViewsRefinementManager baseViewsRefinementManager = albumDetailFragment.refinementsManager;
                    if (baseViewsRefinementManager != null && (unfilteredFilterableBaseViewModels = baseViewsRefinementManager.getUnfilteredFilterableBaseViewModels()) != null) {
                    }
                    Context context = AmazonApplication.getContext();
                    Intrinsics.checkNotNullExpressionValue(context, "getContext()");
                    new AddTracksAction(context, arrayList).addTracks().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.mp3.detailpages.album.AlbumDetailFragment$addTracksAndDownload$1$$ExternalSyntheticLambda0
                        @Override // rx.functions.Action1
                        public final void call(Object obj2) {
                            AlbumDetailFragment.AnonymousClass1.m820invokeSuspend$lambda6$lambda4(z, albumDetailFragment, contentMetadata, downloadState, (Integer) obj2);
                        }
                    }, new Action1() { // from class: com.amazon.mp3.detailpages.album.AlbumDetailFragment$addTracksAndDownload$1$$ExternalSyntheticLambda1
                        @Override // rx.functions.Action1
                        public final void call(Object obj2) {
                            AlbumDetailFragment.AnonymousClass1.m821invokeSuspend$lambda6$lambda5((Throwable) obj2);
                        }
                    });
                }
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: invokeSuspend$lambda-6$lambda-4, reason: not valid java name */
        public static final void m820invokeSuspend$lambda6$lambda4(boolean z, AlbumDetailFragment albumDetailFragment, ContentMetadata contentMetadata, DownloadStateModel.DownloadState downloadState, Integer it) {
            String quantityString;
            List<BaseViewModel> unfilteredFilterableBaseViewModels;
            boolean zIsLikesEverywhereEnabled = AmazonApplication.getCapabilities().isLikesEverywhereEnabled();
            if ((it == null || it.intValue() != 0) && !z) {
                if (zIsLikesEverywhereEnabled) {
                    quantityString = albumDetailFragment.getResources().getString(R.string.dmusic_start_following_toast_message);
                } else {
                    Resources resources = albumDetailFragment.getResources();
                    Intrinsics.checkNotNullExpressionValue(it, "it");
                    quantityString = resources.getQuantityString(R.plurals.dmusic_all_prime_songs_added_to_library, it.intValue(), it);
                }
                Intrinsics.checkNotNullExpressionValue(quantityString, "if (isLikesEnabled) reso…                  it, it)");
                BauhausToastUtils.showTextToast(albumDetailFragment.getActivity(), quantityString, 0);
            }
            BaseViewsRefinementManager baseViewsRefinementManager = albumDetailFragment.refinementsManager;
            if (baseViewsRefinementManager != null && (unfilteredFilterableBaseViewModels = baseViewsRefinementManager.getUnfilteredFilterableBaseViewModels()) != null) {
                ArrayList arrayList = new ArrayList();
                for (Object obj : unfilteredFilterableBaseViewModels) {
                    if (obj instanceof LibraryAddableModel) {
                        arrayList.add(obj);
                    }
                }
                ArrayList<LibraryAddableModel> arrayList2 = new ArrayList();
                for (Object obj2 : arrayList) {
                    if (!((LibraryAddableModel) obj2).getIsInLibrary()) {
                        arrayList2.add(obj2);
                    }
                }
                for (LibraryAddableModel libraryAddableModel : arrayList2) {
                    LibraryAddableModel.updateLibraryState$default(libraryAddableModel, true, null, 2, null);
                    ContentMetadata metadata = libraryAddableModel.getMetadata();
                    TrackMetadata trackMetadata = metadata instanceof TrackMetadata ? (TrackMetadata) metadata : null;
                    if (trackMetadata != null) {
                        trackMetadata.setInLibrary(Boolean.TRUE);
                    }
                }
            }
            if (z) {
                albumDetailFragment.startAlbumDownloadAction(contentMetadata, downloadState);
            }
            albumDetailFragment.updateHeaderActions(false);
            albumDetailFragment.refreshPageModelState();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: invokeSuspend$lambda-6$lambda-5, reason: not valid java name */
        public static final void m821invokeSuspend$lambda6$lambda5(Throwable th) {
            Log.error(AlbumDetailFragment.TAG, String.valueOf(th.getMessage()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void addTracksAndDownload(boolean isDownloadNeeded, ContentMetadata metadata, DownloadStateModel.DownloadState downloadState) {
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), null, new AnonymousClass1(metadata, isDownloadNeeded, downloadState, null), 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startAlbumDownloadAction(ContentMetadata metadata, DownloadStateModel.DownloadState downloadState) {
        FragmentActivity activity;
        AlbumMetadata albumMetadata = metadata instanceof AlbumMetadata ? (AlbumMetadata) metadata : null;
        if (albumMetadata == null || (activity = getActivity()) == null || downloadState == null) {
            return;
        }
        new AlbumDownloadAction(activity, getPageType(), metadata.getUri()).downloadAlbum(albumMetadata, downloadState, shouldBlockPlaybackAndDownload(albumMetadata, isSourceLocal(), false));
    }

    private final void initializeRefinementOverflowManager() {
        if (getContext() == null) {
            return;
        }
        getSubscriptions().add(StyleSheetProvider.getStyleSheetObservable().subscribe(new Action1() { // from class: com.amazon.mp3.detailpages.album.AlbumDetailFragment$$ExternalSyntheticLambda3
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                AlbumDetailFragment.m813initializeRefinementOverflowManager$lambda40$lambda39(this.f$0, (StyleSheet) obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initializeRefinementOverflowManager$lambda-40$lambda-39, reason: not valid java name */
    public static final void m813initializeRefinementOverflowManager$lambda40$lambda39(AlbumDetailFragment this$0, StyleSheet it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DetailPageRefinementManager detailPageRefinementManager = this$0.detailPageRefinementManager;
        Intrinsics.checkNotNullExpressionValue(it, "it");
        detailPageRefinementManager.initializeOverflowManager(this$0, it);
    }

    private final void initializeBaseViewsRefinementManager() {
        String str = TAG;
        Log.debug(str, "initializing BaseViewsRefinementManager");
        if (getPageModelProvider() instanceof BaseViewsRefinementManager.Provider) {
            PageViewModelProvider pageModelProvider = getPageModelProvider();
            if (pageModelProvider == null) {
                throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.catalog.fragment.viewmodel.BaseViewsRefinementManager.Provider");
            }
            this.refinementsManager = ((BaseViewsRefinementManager.Provider) pageModelProvider).getRefinementsManager();
            return;
        }
        Log.error(str, "pageModelProvider is not an instance of BaseViewsRefinementManager.Provider. No refinement manager provided. Refinements disabled");
    }

    private final void initializeDetailPageRefinementManager() {
        Log.debug(TAG, "setting DetailPageRefinementManager's dependencies");
        this.detailPageRefinementManager.setActionBarManager(this.actionBarManager);
        this.detailPageRefinementManager.setRefinementsManager(this.refinementsManager);
        this.detailPageRefinementManager.setAdditionalCallbackHandler(this);
        getLifecycle().addObserver(this.detailPageRefinementManager);
    }

    private final void fetchAlbumDownloadState(final PageGridViewModel pageGridViewModel) {
        Single<DownloadStateModel> initialGroupDownloadState;
        Context context;
        if (this.groupDownloadStateModelProvider == null && (context = getContext()) != null) {
            this.groupDownloadStateModelProvider = new GroupDownloadStateModelProvider(context);
        }
        GroupDownloadStateModelProvider groupDownloadStateModelProvider = this.groupDownloadStateModelProvider;
        if (groupDownloadStateModelProvider == null) {
            return;
        }
        updateAlbumDownloadState(pageGridViewModel, groupDownloadStateModelProvider.lastDownloadState());
        boolean z = (isDisplayingOnlyLibraryContent() || TextUtils.isEmpty(this.albumAsin)) ? false : true;
        String str = this.albumAsin;
        if (str != null) {
            initialGroupDownloadState = groupDownloadStateModelProvider.getInitialAlbumDownloadState(str, z);
        } else if (getContentUri() == null) {
            return;
        } else {
            initialGroupDownloadState = groupDownloadStateModelProvider.getInitialGroupDownloadState(getContentUri(), z);
        }
        initialGroupDownloadState.subscribeOn(io.reactivex.schedulers.Schedulers.io()).subscribe(new SingleObserver<DownloadStateModel>() { // from class: com.amazon.mp3.detailpages.album.AlbumDetailFragment$fetchAlbumDownloadState$2$1
            @Override // io.reactivex.SingleObserver
            public void onSubscribe(Disposable d) {
                Intrinsics.checkNotNullParameter(d, "d");
            }

            @Override // io.reactivex.SingleObserver
            public void onSuccess(DownloadStateModel downloadStateModel) {
                Intrinsics.checkNotNullParameter(downloadStateModel, "downloadStateModel");
                this.this$0.updateAlbumDownloadState(pageGridViewModel, downloadStateModel);
            }

            @Override // io.reactivex.SingleObserver
            public void onError(Throwable e) {
                Intrinsics.checkNotNullParameter(e, "e");
                Log.error(AlbumDetailFragment.TAG, Intrinsics.stringPlus("Action failed for fetching initial download state for album, error message: ", e.getMessage()));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateAlbumDownloadState(PageGridViewModel pageGridViewModel, DownloadStateModel downloadStateModel) {
        PageHeaderModel pageHeaderModel = getPageHeaderModel(pageGridViewModel);
        if (pageHeaderModel != null) {
            pageHeaderModel.updateDownloadState(downloadStateModel);
        }
        if ((pageHeaderModel == null ? null : pageHeaderModel.getMetadata()) instanceof AlbumMetadata) {
            ContentMetadata metadata = pageHeaderModel != null ? pageHeaderModel.getMetadata() : null;
            if (metadata == null) {
                throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.metadata.AlbumMetadata");
            }
            ((AlbumMetadata) metadata).setDownloaded(Boolean.valueOf(downloadStateModel.getDownloadState() == DownloadStateModel.DownloadState.DOWNLOADED));
            if (getPageModelProvider() instanceof BrushViewModel) {
                PageViewModelProvider pageModelProvider = getPageModelProvider();
                if (pageModelProvider == null) {
                    throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.catalog.fragment.viewmodel.BrushViewModel");
                }
                ((BrushViewModel) pageModelProvider).updateModelEligibility(pageHeaderModel, downloadStateModel);
            }
        }
    }

    @Override // com.amazon.music.views.library.providers.RefinementCallback
    public void onChildFilterClick(FilterModel filterModel) {
        Intrinsics.checkNotNullParameter(filterModel, "filterModel");
        if (filterModel.isCollection()) {
            FilterCollectionModel collectionModel = filterModel.getCollectionModel();
            if (collectionModel == null) {
                return;
            }
            Log.debug(TAG, "onChildFilterClick " + collectionModel.getTitle() + " has been clicked");
            return;
        }
        FilterItemModel itemModel = filterModel.getItemModel();
        if (itemModel == null) {
            return;
        }
        Log.debug(TAG, "onChildFilterClick " + itemModel.getTitle() + " has been clicked");
    }

    @Override // com.amazon.music.views.library.providers.RefinementClickListenerProvider
    public void onClick(RefinementPillModel model, View view) {
        FilterItemModel itemModel;
        Intrinsics.checkNotNullParameter(model, "model");
        FilterModel content = model.getContent();
        if (content == null || content.isCollection() || (itemModel = content.getItemModel()) == null) {
            return;
        }
        BaseViewsRefinementManager.INSTANCE.sendRefinementUiClickMetric(itemModel, getPageType());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void displayFilteredData() {
        Context context = getContext();
        if (context == null) {
            return;
        }
        BaseViewsRefinementManager baseViewsRefinementManager = this.refinementsManager;
        List<? extends BaseViewModel> listApplyRefinementFilters$default = baseViewsRefinementManager == null ? null : BaseViewsRefinementManager.applyRefinementFilters$default(baseViewsRefinementManager, context, null, null, 6, null);
        PageViewModelProvider pageModelProvider = getPageModelProvider();
        BrushViewModel brushViewModel = pageModelProvider instanceof BrushViewModel ? (BrushViewModel) pageModelProvider : null;
        if (brushViewModel != null) {
            brushViewModel.updateTracksDownloadState(listApplyRefinementFilters$default);
        }
        shouldShowEmptyView(listApplyRefinementFilters$default);
    }

    protected void onContentStateUpdated() {
        fetchAlbumDownloadState(getPageModel());
        displayFilteredData();
    }

    static /* synthetic */ void exitPageIfRequired$default(AlbumDetailFragment albumDetailFragment, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        albumDetailFragment.exitPageIfRequired(z);
    }

    private final void exitPageIfRequired(boolean isInitialPage) {
        FragmentActivity activity;
        FragmentManager supportFragmentManager;
        if (isInitialPage) {
            return;
        }
        BaseViewsRefinementManager baseViewsRefinementManager = this.refinementsManager;
        boolean z = false;
        if (baseViewsRefinementManager != null && !baseViewsRefinementManager.hasFilterableData()) {
            z = true;
        }
        if (!z || (activity = getActivity()) == null || (supportFragmentManager = activity.getSupportFragmentManager()) == null) {
            return;
        }
        supportFragmentManager.popBackStack();
    }

    private final void shouldShowEmptyView(List<? extends BaseViewModel> filteredTrackModels) {
        if (filteredTrackModels != null && filteredTrackModels.size() == 0) {
            showEmptyView();
            BaseViewsFilterActionBarManager baseViewsFilterActionBarManager = this.actionBarManager;
            if (baseViewsFilterActionBarManager == null) {
                return;
            }
            baseViewsFilterActionBarManager.shouldShowExposedRefinementList(true);
            return;
        }
        BaseViewsFilterActionBarManager baseViewsFilterActionBarManager2 = this.actionBarManager;
        if (baseViewsFilterActionBarManager2 != null) {
            BaseViewsFilterActionBarManager.shouldShowExposedRefinementList$default(baseViewsFilterActionBarManager2, false, 1, null);
        }
        BaseViewsFilterActionBarManager baseViewsFilterActionBarManager3 = this.actionBarManager;
        if (baseViewsFilterActionBarManager3 != null) {
            baseViewsFilterActionBarManager3.shouldShowFilterButton();
        }
        showRecyclerView();
    }

    @Override // com.amazon.music.views.library.providers.RefinementCallback
    public void onFiltersReset() {
        Log.debug(TAG, "reset all refinement filters");
        showRecyclerView();
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    public List<FilterModel> getRefinementFilterModels() {
        ExposedRefinementListModel exposedRefinementListModelRetrieveRefinementListModel;
        BaseViewsRefinementManager baseViewsRefinementManager = this.refinementsManager;
        List<FilterModel> filterModels = null;
        if (baseViewsRefinementManager != null && (exposedRefinementListModelRetrieveRefinementListModel = baseViewsRefinementManager.retrieveRefinementListModel()) != null) {
            filterModels = exposedRefinementListModelRetrieveRefinementListModel.getFilterModels();
        }
        return filterModels == null ? new ArrayList() : filterModels;
    }

    public final PageHeaderModel getPageHeaderModel(PageGridViewModel pageGridViewModel) {
        List<BaseViewModel> models;
        if (pageGridViewModel == null || (models = pageGridViewModel.getModels()) == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : models) {
            if (obj instanceof PageHeaderModel) {
                arrayList.add(obj);
            }
        }
        return (PageHeaderModel) CollectionsKt___CollectionsKt.getOrNull(arrayList, 0);
    }

    private final void initializeActionBarManager() {
        BaseViewsFilterActionBarManager baseViewsFilterActionBarManager = new BaseViewsFilterActionBarManager(this, this, getRecyclerView(), this.actionBarProvider);
        this.actionBarManager = baseViewsFilterActionBarManager;
        this.detailPageRefinementManager.setActionBarManager(baseViewsFilterActionBarManager);
        BaseViewsFilterActionBarManager baseViewsFilterActionBarManager2 = this.actionBarManager;
        if (baseViewsFilterActionBarManager2 != null) {
            baseViewsFilterActionBarManager2.shouldShowFilterButton();
        }
        BaseViewsFilterActionBarManager baseViewsFilterActionBarManager3 = this.actionBarManager;
        if (baseViewsFilterActionBarManager3 != null) {
            BaseViewsFilterActionBarManager.shouldShowExposedRefinementList$default(baseViewsFilterActionBarManager3, false, 1, null);
        }
        BaseViewsFilterActionBarManager baseViewsFilterActionBarManager4 = this.actionBarManager;
        if (baseViewsFilterActionBarManager4 != null) {
            baseViewsFilterActionBarManager4.shouldShowPageComponentInTopAppBar();
        }
        BaseViewsFilterActionBarManager baseViewsFilterActionBarManager5 = this.actionBarManager;
        if (baseViewsFilterActionBarManager5 == null) {
            return;
        }
        getLifecycle().addObserver(baseViewsFilterActionBarManager5);
    }

    private final void setContextMenuProviderForActionBar() {
        MetadataContextMenuHandler contextMenuProvider;
        BaseViewsFilterActionBarManager baseViewsFilterActionBarManager;
        ContentMetadata contentMetadata = getContentMetadata();
        if (contentMetadata == null || (contextMenuProvider = getContextMenuProvider()) == null || (baseViewsFilterActionBarManager = this.actionBarManager) == null) {
            return;
        }
        baseViewsFilterActionBarManager.setActionBarContextMenuProvider(contentMetadata, contextMenuProvider);
    }

    private final void setFilterButton() {
        BaseViewsFilterActionBarManager baseViewsFilterActionBarManager = this.actionBarManager;
        if (baseViewsFilterActionBarManager == null) {
            return;
        }
        baseViewsFilterActionBarManager.shouldShowFilterButton();
    }

    @Override // com.amazon.mp3.fragment.headerbar.OverflowMenuReceiver
    public void setActionBarProvider(ActionBarProvider provider) {
        this.actionBarProvider = provider;
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    public boolean shouldDisplayPageComponentsInTopAppBar(int actionBarHeight) {
        Integer numFindPositionOfFirstTrack;
        RecyclerView recyclerView = getRecyclerView();
        RecyclerView.LayoutManager layoutManager = recyclerView == null ? null : recyclerView.getLayoutManager();
        LinearLayoutManager linearLayoutManager = layoutManager instanceof LinearLayoutManager ? (LinearLayoutManager) layoutManager : null;
        if (linearLayoutManager == null) {
            return false;
        }
        if (doesHaveRefinement()) {
            numFindPositionOfFirstTrack = findPositionOfRefinementList();
        } else {
            numFindPositionOfFirstTrack = findPositionOfFirstTrack();
        }
        if (numFindPositionOfFirstTrack == null) {
            return false;
        }
        return BaseViewsFilterActionBarManager.INSTANCE.isTargetViewUnderActionBarView(numFindPositionOfFirstTrack.intValue(), actionBarHeight, linearLayoutManager);
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    public String getArtworkUrl() {
        return getAlbumArtUrl();
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    public Integer getFilterButtonClickDestination() {
        List<Object> items;
        RecyclerView recyclerView = getRecyclerView();
        RecyclerView.Adapter adapter = recyclerView == null ? null : recyclerView.getAdapter();
        UniversalAdapter universalAdapter = adapter instanceof UniversalAdapter ? (UniversalAdapter) adapter : null;
        if (universalAdapter == null || (items = universalAdapter.getItems()) == null) {
            return null;
        }
        int i = 0;
        Iterator<Object> it = items.iterator();
        while (true) {
            if (!it.hasNext()) {
                i = -1;
                break;
            }
            if (it.next() instanceof ExposedRefinementListModel) {
                break;
            }
            i++;
        }
        return Integer.valueOf(i);
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    public ContentMetadata getContentMetadata() {
        return retrieveAlbumMetadata();
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    public ExposedRefinementListModel getRefinementListModel() {
        List<Object> items;
        Integer numFindPositionOfRefinementList = findPositionOfRefinementList();
        if (numFindPositionOfRefinementList == null) {
            return null;
        }
        int iIntValue = numFindPositionOfRefinementList.intValue();
        RecyclerView recyclerView = getRecyclerView();
        RecyclerView.Adapter adapter = recyclerView == null ? null : recyclerView.getAdapter();
        UniversalAdapter universalAdapter = adapter instanceof UniversalAdapter ? (UniversalAdapter) adapter : null;
        Object obj = (universalAdapter == null || (items = universalAdapter.getItems()) == null) ? null : items.get(iIntValue);
        if (obj instanceof ExposedRefinementListModel) {
            return (ExposedRefinementListModel) obj;
        }
        return null;
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    public boolean shouldDisplayFilterButton(RecyclerView recyclerView, int actionBarHeight) {
        AlbumMetadata albumMetadataRetrieveAlbumMetadata = retrieveAlbumMetadata();
        return (albumMetadataRetrieveAlbumMetadata != null ? getConfiguration().shouldShowRefinementsOption(albumMetadataRetrieveAlbumMetadata) : true) && BaseViewsFilterActionBarManager.InfoProvider.DefaultImpls.shouldDisplayFilterButton(this, recyclerView, actionBarHeight);
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    public boolean shouldDisplayFilterAndContextButtons() {
        return Feature.album_filter_context_menu_button.isEnabled();
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    public boolean doesHaveRefinement() {
        Boolean boolValueOf;
        if (this.refinementCapability == null) {
            AlbumMetadata albumMetadataRetrieveAlbumMetadata = retrieveAlbumMetadata();
            if (albumMetadataRetrieveAlbumMetadata != null) {
                boolValueOf = Boolean.valueOf(getConfiguration().shouldShowRefinementsOption(albumMetadataRetrieveAlbumMetadata));
            } else {
                boolValueOf = Boolean.TRUE;
            }
            this.refinementCapability = boolValueOf;
        }
        Boolean bool = this.refinementCapability;
        if (bool == null) {
            return true;
        }
        return bool.booleanValue();
    }

    private final Integer findPositionOfRefinementList() {
        UniversalAdapter universalAdapter;
        List<Object> items;
        Integer numValueOf;
        RecyclerView recyclerView = getRecyclerView();
        if ((recyclerView == null ? null : recyclerView.getAdapter()) instanceof UniversalAdapter) {
            RecyclerView recyclerView2 = getRecyclerView();
            RecyclerView.Adapter adapter = recyclerView2 == null ? null : recyclerView2.getAdapter();
            if (adapter == null) {
                throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.recyclerview.adapters.UniversalAdapter");
            }
            universalAdapter = (UniversalAdapter) adapter;
        } else {
            universalAdapter = null;
        }
        if (universalAdapter == null || (items = universalAdapter.getItems()) == null) {
            numValueOf = null;
        } else {
            int i = 0;
            Iterator<Object> it = items.iterator();
            while (true) {
                if (!it.hasNext()) {
                    i = -1;
                    break;
                }
                if (it.next() instanceof ExposedRefinementListModel) {
                    break;
                }
                i++;
            }
            numValueOf = Integer.valueOf(i);
        }
        if (numValueOf != null && numValueOf.intValue() == -1) {
            return null;
        }
        return numValueOf;
    }

    public final Integer findPositionOfFirstTrack() {
        UniversalAdapter universalAdapter;
        List<Object> items;
        Integer numValueOf;
        RecyclerView recyclerView = getRecyclerView();
        if ((recyclerView == null ? null : recyclerView.getAdapter()) instanceof UniversalAdapter) {
            RecyclerView recyclerView2 = getRecyclerView();
            RecyclerView.Adapter adapter = recyclerView2 == null ? null : recyclerView2.getAdapter();
            if (adapter == null) {
                throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.recyclerview.adapters.UniversalAdapter");
            }
            universalAdapter = (UniversalAdapter) adapter;
        } else {
            universalAdapter = null;
        }
        if (universalAdapter == null || (items = universalAdapter.getItems()) == null) {
            numValueOf = null;
        } else {
            int i = 0;
            Iterator<Object> it = items.iterator();
            while (true) {
                if (!it.hasNext()) {
                    i = -1;
                    break;
                }
                if (it.next() instanceof DescriptiveRowItemModel) {
                    break;
                }
                i++;
            }
            numValueOf = Integer.valueOf(i);
        }
        if (numValueOf != null && numValueOf.intValue() == -1) {
            return null;
        }
        return numValueOf;
    }

    private final BrushUriClickListenerProvider getUpsellButtonListenerProvider() {
        return new BrushUriClickListenerProvider() { // from class: com.amazon.mp3.detailpages.album.AlbumDetailFragment.getUpsellButtonListenerProvider.1
            @Override // com.amazon.music.views.library.providers.BrushUriClickListenerProvider
            public void onClick(String target, String viewId, TargetType targetType) {
                UpsellUtil.showBlockingUpsell(AlbumDetailFragment.this.getActivity(), AlbumDetailFragment.this.getCollectionId(), UpsellReason.PERSISTENT_UPSELL, UpsellSourceEntity.ALBUM);
            }
        };
    }

    private final FeaturedToggleListenerProvider getFeaturedToggleListenerProvider() {
        return new FeaturedToggleListenerProvider() { // from class: com.amazon.mp3.detailpages.album.AlbumDetailFragment.getFeaturedToggleListenerProvider.1
            @Override // com.amazon.music.views.library.providers.FeaturedToggleListenerProvider
            public void onClick(FeaturedToggleViewModel model, FeaturedToggleView view) {
                Intrinsics.checkNotNullParameter(model, "model");
                Intrinsics.checkNotNullParameter(view, "view");
                view.updateToggle();
                if (model.getToggleType() == FeaturedToggleViewModel.ToggleType.ARTIST_COMMENTARY) {
                    AlbumDetailFragment.emitUiClickMetric$default(AlbumDetailFragment.this, view.getIsToggleActive() ? ActionType.TOGGLE_COMMENTARY_ON : ActionType.TOGGLE_COMMENTARY_OFF, 0, InteractionType.TOGGLE, 2, null);
                    AlbumDetailFragment.this.setArtistCommentaryEnabled(view.getIsToggleActive());
                }
            }
        };
    }

    @Override // com.amazon.mp3.detailpages.album.click.CollectionTrackClickListener.CollectionInformationFetcher
    public Observable<List<MusicTrack>> getAllTracks() {
        return getTrackInformationFetcher().getAllTracks();
    }

    @Override // com.amazon.mp3.detailpages.album.click.CollectionTrackClickListener.CollectionInformationFetcher
    public Observable<MusicTrack> getTrack(Uri uri) {
        List<BaseViewModel> models;
        SectionGridViewModel sectionGridViewModel;
        List<BaseViewModel> models2;
        Intrinsics.checkNotNullParameter(uri, "uri");
        PageGridViewModel pageModel = getPageModel();
        final BaseViewContentModel baseViewContentModel = null;
        Object obj = null;
        if (pageModel == null || (models = pageModel.getModels()) == null) {
            sectionGridViewModel = null;
        } else {
            ArrayList arrayList = new ArrayList();
            for (Object obj2 : models) {
                if (obj2 instanceof SectionGridViewModel) {
                    arrayList.add(obj2);
                }
            }
            sectionGridViewModel = (SectionGridViewModel) CollectionsKt___CollectionsKt.firstOrNull((List) arrayList);
        }
        if (sectionGridViewModel != null) {
            models2 = sectionGridViewModel.getModels();
        } else {
            PageGridViewModel pageModel2 = getPageModel();
            models2 = pageModel2 == null ? null : pageModel2.getModels();
        }
        if (models2 != null) {
            ArrayList arrayList2 = new ArrayList();
            for (Object obj3 : models2) {
                if (obj3 instanceof BaseViewContentModel) {
                    arrayList2.add(obj3);
                }
            }
            Iterator it = arrayList2.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Object next = it.next();
                if (Intrinsics.areEqual(((BaseViewContentModel) next).getMetadata().getUri(), uri)) {
                    obj = next;
                    break;
                }
            }
            baseViewContentModel = (BaseViewContentModel) obj;
        }
        Observable<MusicTrack> observableCreate = Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.detailpages.album.AlbumDetailFragment$$ExternalSyntheticLambda7
            @Override // rx.functions.Action1
            public final void call(Object obj4) {
                AlbumDetailFragment.m810getTrack$lambda55(baseViewContentModel, (Subscriber) obj4);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableCreate, "create { subscriber ->\n …r.onCompleted()\n        }");
        return observableCreate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getTrack$lambda-55, reason: not valid java name */
    public static final void m810getTrack$lambda55(BaseViewContentModel baseViewContentModel, Subscriber subscriber) {
        ContentMetadata metadata;
        MusicTrack track = null;
        if (baseViewContentModel != null && (metadata = baseViewContentModel.getMetadata()) != null) {
            track = AmazonApplication.getLibraryItemFactory().getTrack(metadata.getUri());
        }
        subscriber.onNext(track);
        subscriber.onCompleted();
    }

    @Override // com.amazon.mp3.detailpages.album.click.CollectionTrackClickListener.CollectionInformationFetcher
    public String getSortOrder() {
        String albumNameSortOrder = CirrusMediaSource.getAlbumNameSortOrder();
        Intrinsics.checkNotNullExpressionValue(albumNameSortOrder, "getAlbumNameSortOrder()");
        return albumNameSortOrder;
    }

    @Override // com.amazon.mp3.detailpages.album.click.CollectionTrackClickListener.CollectionInformationFetcher
    public Uri getCollectionUri() {
        AlbumMetadata albumMetadataRetrieveAlbumMetadata = retrieveAlbumMetadata();
        Bundle arguments = getArguments();
        Object obj = arguments == null ? null : arguments.get("com.amazon.mp3.library.EXTRA_CONTENT_URI");
        Uri uri = obj instanceof Uri ? (Uri) obj : null;
        Uri uri2 = albumMetadataRetrieveAlbumMetadata == null ? null : albumMetadataRetrieveAlbumMetadata.getUri();
        if (uri2 != null || (uri2 = getContentUri()) != null) {
            uri = uri2;
        }
        if (uri != null) {
            return ContentPlaybackUtils.INSTANCE.appendTracksIfNeeded(uri);
        }
        Log.warning(TAG, "Unable to retrieve Album URI");
        return null;
    }

    public String getCollectionId() {
        return getAlbumAsin();
    }

    @Override // com.amazon.mp3.detailpages.album.click.CollectionTrackClickListener.CollectionInformationFetcher
    public String getCollectionName() {
        AlbumMetadata albumMetadataRetrieveAlbumMetadata = retrieveAlbumMetadata();
        if (albumMetadataRetrieveAlbumMetadata != null) {
            String title = albumMetadataRetrieveAlbumMetadata.getTitle();
            return title == null ? "" : title;
        }
        Log.warning(TAG, "Album name is empty");
        return "";
    }

    static /* synthetic */ long generateAlbumDBId$default(AlbumDetailFragment albumDetailFragment, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = albumDetailFragment.getCollectionId();
        }
        if ((i & 2) != 0) {
            str2 = albumDetailFragment.getCollectionName();
        }
        return albumDetailFragment.generateAlbumDBId(str, str2);
    }

    private final long generateAlbumDBId(String albumAsin, String albumName) {
        return IdGenerator.generateAlbumId(albumAsin, albumName);
    }

    @Override // com.amazon.mp3.detailpages.album.click.CollectionTrackClickListener.CollectionInformationFetcher
    public String getSelection(List<? extends MusicTrack> trackList) {
        Intrinsics.checkNotNullParameter(trackList, "trackList");
        StringBuilder sb = new StringBuilder();
        int size = trackList.size();
        int i = 0;
        while (i < size) {
            int i2 = i + 1;
            sb.append("_id = ?");
            if (i != trackList.size() - 1) {
                sb.append(" OR ");
            }
            i = i2;
        }
        if (sb.length() == 0) {
            return null;
        }
        return sb.toString();
    }

    @Override // com.amazon.mp3.detailpages.album.click.CollectionTrackClickListener.CollectionInformationFetcher
    public String[] getSelectionArgs(List<? extends MusicTrack> trackList) {
        Intrinsics.checkNotNullParameter(trackList, "trackList");
        int size = trackList.size();
        String[] strArr = new String[size];
        for (int i = 0; i < size; i++) {
            strArr[i] = "";
        }
        int size2 = trackList.size();
        for (int i2 = 0; i2 < size2; i2++) {
            String id = trackList.get(i2).getId();
            Intrinsics.checkNotNullExpressionValue(id, "trackList[i].id");
            strArr[i2] = id;
        }
        return strArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getAlbumAsin() {
        AlbumMetadata albumMetadataRetrieveAlbumMetadata = retrieveAlbumMetadata();
        if (albumMetadataRetrieveAlbumMetadata != null) {
            return albumMetadataRetrieveAlbumMetadata.getAsin();
        }
        Log.info(TAG, "Album ASIN is null");
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getCollectionArtistName() {
        AlbumMetadata albumMetadataRetrieveAlbumMetadata = retrieveAlbumMetadata();
        if (albumMetadataRetrieveAlbumMetadata == null) {
            return null;
        }
        return albumMetadataRetrieveAlbumMetadata.getArtistTitle();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isAlbumDownloaded() {
        Uri collectionUri = getCollectionUri();
        if (collectionUri == null) {
            return false;
        }
        return Intrinsics.areEqual("cirrus-local", MediaProvider.getSource(collectionUri));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getAlbumArtUrl() {
        AlbumMetadata albumMetadataRetrieveAlbumMetadata = retrieveAlbumMetadata();
        if (albumMetadataRetrieveAlbumMetadata == null) {
            return null;
        }
        return albumMetadataRetrieveAlbumMetadata.getImageUrl();
    }

    private final AlbumMetadata retrieveAlbumMetadata() {
        List<BaseViewModel> models;
        Object next;
        PageGridViewModel pageModel = getPageModel();
        if (pageModel == null || (models = pageModel.getModels()) == null) {
            return null;
        }
        Iterator<T> it = models.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (((BaseViewModel) next) instanceof CollectionDetailHeaderModel) {
                break;
            }
        }
        BaseViewModel baseViewModel = (BaseViewModel) next;
        if (baseViewModel == null) {
            return null;
        }
        ContentMetadata metadata = ((CollectionDetailHeaderModel) baseViewModel).getMetadata();
        if (metadata instanceof AlbumMetadata) {
            return (AlbumMetadata) metadata;
        }
        return null;
    }

    private final List<TrackMetadata> extractTrackMetadata() {
        List<BaseViewModel> models;
        List listFilterIsInstance;
        PageGridViewModel pageModel = getPageModel();
        if (pageModel == null || (models = pageModel.getModels()) == null || (listFilterIsInstance = CollectionsKt___CollectionsJvmKt.filterIsInstance(models, DescriptiveRowItemModel.class)) == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = listFilterIsInstance.iterator();
        while (it.hasNext()) {
            ContentMetadata metadata = ((DescriptiveRowItemModel) it.next()).getMetadata();
            TrackMetadata trackMetadata = metadata instanceof TrackMetadata ? (TrackMetadata) metadata : null;
            if (trackMetadata != null) {
                arrayList.add(trackMetadata);
            }
        }
        return arrayList;
    }

    private final boolean navigatedToOfflineLibrary() {
        Bundle arguments = getArguments();
        return (arguments == null ? false : Intrinsics.areEqual(arguments.get("EXTRA_OFFLINE_LIBRARY_NAVIGATION"), Boolean.TRUE)) || isSourceLocal();
    }

    private final boolean navigatedToOnlineLibrary() {
        return Intrinsics.areEqual(this.defaultFilterString, RefinementFilterType.LIBRARY.toString());
    }

    private final boolean navigatedToCatalog() {
        return (navigatedToOnlineLibrary() || navigatedToOfflineLibrary()) ? false : true;
    }

    private final boolean isBadgingInLibrarySupported() {
        return !isOpenedFromBrowseHome() && AmazonApplication.getCapabilities().isAlbumBadgingInLibrarySupported();
    }

    private final boolean isOpenedFromBrowseHome() {
        return navigatedToCatalog() && !isSourceLocal();
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected ContentEnabilityProvider getContentEnabilityProvider() {
        if (getEnabilityProvider() == null) {
            if (UserSubscriptionUtil.isNightwingOnly()) {
                setEnabilityProvider(new NightwingAlbumEnabilityProvider());
            } else {
                Context context = getContext();
                if (context == null) {
                    Context context2 = getContext();
                    context = context2 == null ? null : context2.getApplicationContext();
                    if (context == null) {
                        context = AmazonApplication.getApplication().getApplicationContext();
                    }
                }
                Intrinsics.checkNotNullExpressionValue(context, "context ?: this.context?…tion().applicationContext");
                setEnabilityProvider(new LibraryContentEnabilityProvider(context, getActivity()));
            }
        }
        return getEnabilityProvider();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<PlaybackAugmentation> getPlaybackAugmentations(boolean isTrackSelected) {
        return CollectionsKt__CollectionsKt.listOfNotNull(this.isArtistCommentaryEnabled ? new CommentaryAugmentation(isTrackSelected) : null);
    }

    @Override // com.amazon.mp3.detailpages.album.click.CollectionTrackClickListener.PlaybackAugmentationProvider
    public List<PlaybackAugmentation> getPlaybackAugmentations() {
        return getPlaybackAugmentations(true);
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public void emitUiPageViewMetric() {
        String string;
        String string2;
        Bundle arguments = getArguments();
        String string3 = null;
        if (arguments == null) {
            string = null;
            string2 = null;
        } else {
            string = arguments.getString(DeeplinkIntentKey.KEY_REF_MARKER.toString());
            string2 = arguments.getString(DeeplinkIntentKey.KEY_ASSOCIATE_TAG.toString());
            string3 = arguments.getString("EXTRA_ALBUM_ASIN");
        }
        AbstractBaseViewsFragment.sendUiPageViewMetric$default(this, getPageType(), null, string3 == null ? String.valueOf(MediaProvider.Albums.getAlbumId(getCollectionUri())) : string3, string3 != null ? "ASIN" : "CD_OBJECT_ID", string, string2, null, null, 194, null);
    }

    static /* synthetic */ void emitUiClickMetric$default(AlbumDetailFragment albumDetailFragment, ActionType actionType, int i, InteractionType interactionType, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = -1;
        }
        if ((i2 & 4) != 0) {
            interactionType = InteractionType.TAP;
        }
        albumDetailFragment.emitUiClickMetric(actionType, i, interactionType);
    }

    private final void emitUiClickMetric(ActionType actionType, int entityPosition, InteractionType interactionType) {
        String strValueOf = this.albumAsin;
        if (strValueOf == null) {
            strValueOf = String.valueOf(MediaProvider.Albums.getAlbumId(getCollectionUri()));
        }
        AbstractBaseViewsFragment.sendUiClickMetric$default(this, actionType, interactionType, getPageType(), null, strValueOf, this.albumAsin != null ? EntityIdType.ASIN : EntityIdType.CD_OBJECT_ID, null, Integer.valueOf(entityPosition), null, null, null, null, 3912, null);
    }

    private final void handleDeeplink() {
        ContentMetadata contentMetadata = getContentMetadata();
        Bundle arguments = getArguments();
        if (contentMetadata == null || arguments == null || !(contentMetadata instanceof AlbumMetadata)) {
            return;
        }
        BaseViewsDeeplinkActionHandler baseViewsDeeplinkActionHandler = BaseViewsDeeplinkActionHandler.INSTANCE;
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
        baseViewsDeeplinkActionHandler.handleAlbumDeeplinkAction(contextRequireContext, (AlbumMetadata) contentMetadata, arguments);
    }

    @Override // com.amazon.mp3.catalog.fragment.LibraryBaseViewsFragment, com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public MissingSDCardHandler getMissingSDCardProvider() {
        Context context = getContext();
        if (context == null) {
            return null;
        }
        return new MissingSDCardHandler(context);
    }

    @Override // com.amazon.mp3.fragment.viewmodel.OnFilterChangedListener
    public boolean shouldOverrideSelectionChange(String filterType, boolean selected) {
        Intrinsics.checkNotNullParameter(filterType, "filterType");
        if (selected || !Intrinsics.areEqual(filterType, RefinementFilterType.INSTANCE.getDisplayName(RefinementFilterType.DOWNLOADED)) || ConnectivityUtil.hasAnyInternetConnection(getContext()) || !(getPageModelProvider() instanceof LibraryAlbumDetailViewModel)) {
            return false;
        }
        this.displayedNoNetworkDialogFromOffline = true;
        showNetworkErrorDialog();
        return true;
    }

    @Override // com.amazon.mp3.fragment.viewmodel.OnFilterChangedListener
    public boolean shouldOverrideResetFilters() {
        return shouldOverrideSelectionChange(RefinementFilterType.INSTANCE.getDisplayName(RefinementFilterType.DOWNLOADED), false);
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment, com.amazon.mp3.dialog.NoNetworkEmptyPageDialog.Listener
    public void onNoNetworkDialogCanceled() {
        if (this.displayedNoNetworkDialogFromOffline) {
            resumeOfflineFromNoNetworkDialog();
        } else {
            super.onNoNetworkDialogCanceled();
        }
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected boolean shouldSendInitializingLatencyEvent() {
        return Feature.album_detail_telemetry_correction.isEnabled();
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected PageLoadLatencyCode getPageLoadLatencyCode() {
        if (isDisplayingOnlyLibraryContent() || (getPageModelProvider() instanceof LibraryAlbumDetailViewModel)) {
            return PageLoadLatencyCode.LIBRARY_ALBUM_DETAIL;
        }
        return PageLoadLatencyCode.ALBUM_DETAIL;
    }

    private final void resumeOfflineFromNoNetworkDialog() {
        this.displayedNoNetworkDialogFromOffline = false;
        if (shouldShowEmptyView()) {
            showEmptyView();
        } else {
            showRecyclerView();
        }
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected List<BaseViewModel> getTrackList() {
        List<BaseViewModel> models;
        PageGridViewModel pageModel = getPageModel();
        ArrayList arrayList = null;
        if (pageModel != null && (models = pageModel.getModels()) != null) {
            arrayList = new ArrayList();
            for (Object obj : models) {
                List<String> tags = ((BaseViewModel) obj).getTags();
                boolean z = false;
                if (tags != null && tags.contains("TRACK_TAG")) {
                    z = true;
                }
                if (z) {
                    arrayList.add(obj);
                }
            }
        }
        return arrayList;
    }

    @Override // com.amazon.mp3.catalog.fragment.LibraryBaseViewsFragment
    protected void onTrackAdded(Uri trackUri) {
        Intrinsics.checkNotNullParameter(trackUri, "trackUri");
        refreshPageModelState();
        updateHeaderActions();
    }

    @Override // com.amazon.mp3.catalog.fragment.LibraryBaseViewsFragment
    protected void onTrackRemoved(Uri trackUri) {
        Intrinsics.checkNotNullParameter(trackUri, "trackUri");
        exitPageIfRequired$default(this, false, 1, null);
        refreshPageModelState();
        updateHeaderActions(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void refreshPageModelState() {
        if (isDisplayingOnlyLibraryContent()) {
            PageViewModelProvider pageModelProvider = getPageModelProvider();
            if (pageModelProvider == null) {
                return;
            }
            PageViewModelProvider.DefaultImpls.fetchUpdates$default(pageModelProvider, 20, getDiffUtilFactory(), null, 4, null);
            return;
        }
        if (getPageModelProvider() instanceof BrushViewModel) {
            PageViewModelProvider pageModelProvider2 = getPageModelProvider();
            if (pageModelProvider2 == null) {
                throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.catalog.fragment.viewmodel.BrushViewModel");
            }
            ((BrushViewModel) pageModelProvider2).refreshModelStateProviders();
        }
    }

    private final void updateHeaderActions() {
        List<TrackMetadata> listExtractTrackMetadata = extractTrackMetadata();
        boolean z = true;
        if (listExtractTrackMetadata != null) {
            if (listExtractTrackMetadata.isEmpty()) {
                z = false;
            } else {
                Iterator<T> it = listExtractTrackMetadata.iterator();
                while (it.hasNext()) {
                    if (Intrinsics.areEqual(((TrackMetadata) it.next()).getIsInLibrary(), Boolean.FALSE)) {
                        break;
                    }
                }
                z = false;
            }
        }
        updateHeaderActions(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateHeaderActions(boolean isAddable) {
        List<BaseViewModel> models;
        Boolean isOwned;
        PageGridViewModel pageModel = getPageModel();
        if (pageModel == null || (models = pageModel.getModels()) == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : models) {
            if (obj instanceof PageHeaderModel) {
                arrayList.add(obj);
            }
        }
        boolean zBooleanValue = false;
        PageHeaderModel pageHeaderModel = (PageHeaderModel) CollectionsKt___CollectionsKt.getOrNull(arrayList, 0);
        if (pageHeaderModel == null) {
            return;
        }
        AlbumMetadata albumMetadataRetrieveAlbumMetadata = retrieveAlbumMetadata();
        boolean zIsAlbumDownloaded = isAlbumDownloaded();
        if (albumMetadataRetrieveAlbumMetadata != null && (isOwned = albumMetadataRetrieveAlbumMetadata.getIsOwned()) != null) {
            zBooleanValue = isOwned.booleanValue();
        }
        boolean z = zBooleanValue;
        if (AmazonApplication.getCapabilities().isLikesEverywhereEnabled()) {
            pageHeaderModel.updateFollowState(!isAddable);
        }
        if (AmazonApplication.getCapabilities().shouldShowSRPageHeaders(Feature.album_detail_performance_improvements_phase1B.isEnabled(true))) {
            pageHeaderModel.updateActionIconModels(AlbumDetailPageHeaderUtil.INSTANCE.generateVRAlbumDetailHeaderIcons(albumMetadataRetrieveAlbumMetadata, z, isAddable, zIsAlbumDownloaded, this.isArtistCommentaryEnabled));
        } else {
            pageHeaderModel.updateActionIconModels(AlbumDetailPageHeaderUtil.INSTANCE.generateAlbumDetailHeaderIcons(this.albumAsin, z, isAddable, zIsAlbumDownloaded));
        }
    }

    private final Set<RefinementFilterType> getDefaultFilters() {
        RefinementFilterType refinementTypeFromDisplayName;
        String str = this.defaultFilterString;
        if (str == null || (refinementTypeFromDisplayName = RefinementFilterType.INSTANCE.getRefinementTypeFromDisplayName(str)) == null) {
            return null;
        }
        return SetsKt__SetsJVMKt.setOf(refinementTypeFromDisplayName);
    }
}
