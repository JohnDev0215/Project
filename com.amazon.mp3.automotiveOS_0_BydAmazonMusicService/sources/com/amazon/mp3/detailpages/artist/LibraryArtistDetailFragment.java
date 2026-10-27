package com.amazon.mp3.detailpages.artist;

import android.app.Application;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.KeyEventDispatcher;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.actionbar.ActionBarProvider;
import com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.activity.navigation.FragmentController;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment;
import com.amazon.mp3.catalog.fragment.LibraryBaseViewsFragment;
import com.amazon.mp3.catalog.fragment.adapter.BaseViewsBinderConfiguration;
import com.amazon.mp3.catalog.fragment.adapter.BaseViewsBinderProvider;
import com.amazon.mp3.catalog.fragment.adapter.BaseViewsDiffUtil;
import com.amazon.mp3.catalog.fragment.adapter.BaseViewsDiffUtilFactory;
import com.amazon.mp3.catalog.fragment.datasource.dao.LibraryArtistDetailDao;
import com.amazon.mp3.catalog.fragment.datasource.repository.PageDataRepository;
import com.amazon.mp3.catalog.fragment.provider.LibraryContentEnabilityProvider;
import com.amazon.mp3.catalog.fragment.viewmodel.BaseViewsRefinementManager;
import com.amazon.mp3.catalog.fragment.viewmodel.PageViewModelFactoryProvider;
import com.amazon.mp3.catalog.fragment.viewmodel.PageViewModelProvider;
import com.amazon.mp3.cloudqueue.FreeTierPlaybackUtil;
import com.amazon.mp3.detailpages.actions.DefaultTrackInformationFetcher;
import com.amazon.mp3.detailpages.actions.GenericBaseViewModelFetcher;
import com.amazon.mp3.detailpages.actions.PlayCollectionAction;
import com.amazon.mp3.fragment.headerbar.OverflowMenuReceiver;
import com.amazon.mp3.fragment.viewmodel.OnFilterChangedListener;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.item.factory.CirrusSourceLibraryItemFactory;
import com.amazon.mp3.library.item.factory.LibraryItemFactory;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.playback.state.ContentPlaybackUtils;
import com.amazon.mp3.prime.upsell.nightwing.UpsellUtil;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellReason;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellSourceEntity;
import com.amazon.mp3.providers.ArtistClickHandler;
import com.amazon.mp3.providers.CatalogEnabilityProvider;
import com.amazon.mp3.providers.DefaultAddToLibraryProvider;
import com.amazon.mp3.providers.DefaultLikesProvider;
import com.amazon.mp3.providers.MetadataContextMenuHandler;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.DbUtil;
import com.amazon.mp3.util.IdGenerator;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.mp3.view.refinements.DetailPageRefinementManager;
import com.amazon.mp3.view.refinements.RefinementFilterType;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.PageLoadLatencyCode;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.skyfire.ui.skyfire.Contexts;
import com.amazon.music.skyfire.ui.utils.FadingBackdropHelper;
import com.amazon.music.views.library.binders.ActionIconOnClickListener;
import com.amazon.music.views.library.binders.RefinementPillBinder;
import com.amazon.music.views.library.binders.UniversalBinder;
import com.amazon.music.views.library.deeplinks.ContentMetadataOnClickListener;
import com.amazon.music.views.library.metadata.ArtistMetadata;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import com.amazon.music.views.library.models.ArtistDetailHeaderModel;
import com.amazon.music.views.library.models.ExposedRefinementListModel;
import com.amazon.music.views.library.models.FilterCollectionModel;
import com.amazon.music.views.library.models.FilterItemModel;
import com.amazon.music.views.library.models.FilterModel;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.PageHeaderActionIconModel;
import com.amazon.music.views.library.models.RefinementPillModel;
import com.amazon.music.views.library.models.VisualRowItemModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.providers.ContentEnabilityProvider;
import com.amazon.music.views.library.providers.RefinementCallback;
import com.amazon.music.views.library.providers.RowButtonOnClickProvider;
import com.amazon.music.views.library.recyclerview.BaseViewModelAdapterProvider;
import com.amazon.music.views.library.recyclerview.adapters.UniversalAdapter;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.music.views.library.utils.BaseViewContainerModelUtil;
import com.amazon.music.views.library.views.presentation.ActionBarOverlayable;
import com.amazon.ui.foundations.views.BaseButton;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.MainCoroutineDispatcher;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/* JADX INFO: compiled from: LibraryArtistDetailFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000È\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 ¨\u00012\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u0005:\u0002¨\u0001B\u0011\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\b\u0010)\u001a\u00020\u001eH\u0002J\b\u0010*\u001a\u00020+H\u0002J<\u0010,\u001a\u00020+2\u0006\u0010-\u001a\u00020.2\b\u0010/\u001a\u0004\u0018\u00010\u00152\b\u00100\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u00101\u001a\u0004\u0018\u0001022\n\b\u0002\u00103\u001a\u0004\u0018\u000104H\u0002J\b\u00105\u001a\u00020+H\u0002J\b\u00106\u001a\u00020+H\u0016J\u000f\u00107\u001a\u0004\u0018\u000108H\u0002¢\u0006\u0002\u00109J\n\u0010:\u001a\u0004\u0018\u00010\u0011H\u0002J\n\u0010;\u001a\u0004\u0018\u00010\u0011H\u0016J\u0016\u0010<\u001a\u0010\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030>0=H\u0016J\n\u0010?\u001a\u0004\u0018\u00010@H\u0014J\n\u0010A\u001a\u0004\u0018\u00010BH\u0016J\b\u0010C\u001a\u00020DH\u0016J\u000f\u0010E\u001a\u0004\u0018\u000108H\u0014¢\u0006\u0002\u00109J\u000f\u0010F\u001a\u0004\u0018\u000108H\u0016¢\u0006\u0002\u00109J\u001a\u0010G\u001a\u00020\u00132\u0006\u0010H\u001a\u00020I2\b\u0010J\u001a\u0004\u0018\u00010\u0013H\u0014J\n\u0010K\u001a\u0004\u0018\u00010LH\u0014J\n\u0010M\u001a\u0004\u0018\u00010NH\u0016J\u0014\u0010O\u001a\u000e\u0012\u0004\u0012\u00020Q\u0012\u0004\u0012\u00020R0PH\u0002J\n\u0010S\u001a\u0004\u0018\u00010TH\u0014J\b\u0010U\u001a\u00020VH\u0016J\b\u0010W\u001a\u00020XH\u0002J\u000e\u0010Y\u001a\b\u0012\u0004\u0012\u00020Z0=H\u0016J\n\u0010[\u001a\u0004\u0018\u00010\\H\u0016J\u0012\u0010]\u001a\u0004\u0018\u00010^2\u0006\u0010_\u001a\u00020`H\u0014J\n\u0010a\u001a\u0004\u0018\u00010\u0011H\u0016J\u001e\u0010b\u001a\u0002082\u0006\u0010c\u001a\u00020\u001e2\f\u0010d\u001a\b\u0012\u0004\u0012\u00020e0=H\u0002J\b\u0010f\u001a\u00020+H\u0016J\b\u0010g\u001a\u00020+H\u0002J\u0010\u0010h\u001a\u00020+2\u0006\u0010i\u001a\u00020jH\u0002J\u0010\u0010k\u001a\u00020+2\u0006\u0010_\u001a\u00020`H\u0016J\b\u0010l\u001a\u00020+H\u0002J\b\u0010m\u001a\u00020+H\u0002J\b\u0010n\u001a\u00020\u001eH\u0002J\b\u0010o\u001a\u00020\u001eH\u0014J\b\u0010p\u001a\u00020+H\u0002J\u0012\u0010q\u001a\u00020+2\b\u0010r\u001a\u0004\u0018\u00010sH\u0016J\u0010\u0010t\u001a\u00020+2\u0006\u0010u\u001a\u00020ZH\u0016J\u001a\u0010v\u001a\u00020+2\u0006\u0010w\u001a\u00020x2\b\u0010y\u001a\u0004\u0018\u000104H\u0016J\u0018\u0010z\u001a\u00020+2\u0006\u0010{\u001a\u00020|2\u0006\u0010}\u001a\u00020QH\u0002J\u0012\u0010~\u001a\u00020+2\b\u0010r\u001a\u0004\u0018\u00010sH\u0016J&\u0010\u007f\u001a\u0004\u0018\u0001042\u0006\u0010H\u001a\u00020I2\b\u0010J\u001a\u0004\u0018\u00010\u00132\b\u0010r\u001a\u0004\u0018\u00010sH\u0016J\t\u0010\u0080\u0001\u001a\u00020+H\u0016J\t\u0010\u0081\u0001\u001a\u00020+H\u0016J\u0012\u0010\u0081\u0001\u001a\u00020+2\u0007\u0010w\u001a\u00030\u0082\u0001H\u0016J\t\u0010\u0083\u0001\u001a\u00020+H\u0016J\u001b\u0010\u0084\u0001\u001a\u00020+2\u0006\u0010y\u001a\u0002042\b\u0010r\u001a\u0004\u0018\u00010sH\u0016J\t\u0010\u0085\u0001\u001a\u00020\u001eH\u0014J\u000b\u0010\u0086\u0001\u001a\u0004\u0018\u00010|H\u0002J\u0010\u0010\u0087\u0001\u001a\t\u0012\u0005\u0012\u00030\u0088\u00010=H\u0002J\u0014\u0010\u0089\u0001\u001a\u00020+2\t\u0010\u008a\u0001\u001a\u0004\u0018\u00010\fH\u0016J\t\u0010\u008b\u0001\u001a\u00020+H\u0002J\t\u0010\u008c\u0001\u001a\u00020+H\u0014J$\u0010\u008d\u0001\u001a\u00020\u001e2\u0007\u0010\u008e\u0001\u001a\u00020B2\u0007\u0010\u008f\u0001\u001a\u00020\u001e2\u0007\u0010\u0090\u0001\u001a\u00020\u001eH\u0014J\t\u0010\u0091\u0001\u001a\u00020\u001eH\u0016J\u0012\u0010\u0092\u0001\u001a\u00020\u001e2\u0007\u0010\u0093\u0001\u001a\u000208H\u0016J\t\u0010\u0094\u0001\u001a\u00020\u001eH\u0016J\t\u0010\u0095\u0001\u001a\u00020\u001eH\u0016J\u001b\u0010\u0096\u0001\u001a\u00020\u001e2\u0007\u0010\u0097\u0001\u001a\u00020\u00112\u0007\u0010\u0098\u0001\u001a\u00020\u001eH\u0016J\t\u0010\u0099\u0001\u001a\u00020\u001eH\u0014J\u001b\u0010\u0099\u0001\u001a\u00020\u001e2\u0010\u0010\u009a\u0001\u001a\u000b\u0012\u0005\u0012\u00030\u009b\u0001\u0018\u00010=H\u0002J\t\u0010\u009c\u0001\u001a\u00020+H\u0014J\t\u0010\u009d\u0001\u001a\u00020+H\u0014J\u0019\u0010\u009e\u0001\u001a\u00020+2\u0006\u0010{\u001a\u00020|2\u0006\u0010c\u001a\u00020\u001eH\u0002J\t\u0010\u009f\u0001\u001a\u00020+H\u0002J!\u0010 \u0001\u001a\u00020+2\u0007\u0010¡\u0001\u001a\u00020\u00112\r\u0010¢\u0001\u001a\b\u0012\u0004\u0012\u00020Z0=H\u0002J'\u0010£\u0001\u001a\u00020+2\n\u0010¤\u0001\u001a\u0005\u0018\u00010¥\u00012\u0007\u0010¦\u0001\u001a\u00020\u001e2\u0007\u0010§\u0001\u001a\u00020\u001eH\u0014R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000fR\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010!\u001a\u00020\"8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b%\u0010&\u001a\u0004\b#\u0010$R\u0010\u0010'\u001a\u0004\u0018\u00010(X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006©\u0001"}, d2 = {"Lcom/amazon/mp3/detailpages/artist/LibraryArtistDetailFragment;", "Lcom/amazon/mp3/catalog/fragment/LibraryBaseViewsFragment;", "Lcom/amazon/mp3/actionbar/BaseViewsFilterActionBarManager$InfoProvider;", "Lcom/amazon/music/views/library/providers/RefinementCallback;", "Lcom/amazon/mp3/fragment/headerbar/OverflowMenuReceiver;", "Lcom/amazon/mp3/fragment/viewmodel/OnFilterChangedListener;", "catalogFragment", "Landroidx/fragment/app/Fragment;", "(Landroidx/fragment/app/Fragment;)V", "actionBarManager", "Lcom/amazon/mp3/actionbar/BaseViewsFilterActionBarManager;", "actionBarProvider", "Lcom/amazon/mp3/actionbar/ActionBarProvider;", "artistId", "", "Ljava/lang/Long;", "asin", "", "backgroundArtworkOverlayLayout", "Landroid/view/ViewGroup;", "backgroundImageView", "Landroid/widget/ImageView;", "catalogArtistName", "getCatalogFragment", "()Landroidx/fragment/app/Fragment;", "detailPageRefinementManager", "Lcom/amazon/mp3/view/refinements/DetailPageRefinementManager;", "followerCount", "initialFilterSelection", "isTrackDownloadEnabled", "", "refinementsManager", "Lcom/amazon/mp3/catalog/fragment/viewmodel/BaseViewsRefinementManager;", "trackInformationFetcher", "Lcom/amazon/mp3/detailpages/actions/DefaultTrackInformationFetcher;", "getTrackInformationFetcher", "()Lcom/amazon/mp3/detailpages/actions/DefaultTrackInformationFetcher;", "trackInformationFetcher$delegate", "Lkotlin/Lazy;", "viewModel", "Lcom/amazon/mp3/detailpages/artist/LibraryArtistDetailViewModel;", "areAllDownloaded", "bindBackgroundImage", "", "bindBackgroundImageView", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "artworkView", "backgroundImageUrl", "recyclerViewOverlay", "Landroidx/recyclerview/widget/RecyclerView;", "viewOverlay", "Landroid/view/View;", "bindEmptyViewBackgroundImage", "emitUiPageViewMetric", "findPositionOfRefinementList", "", "()Ljava/lang/Integer;", "getArtistHeroImageUrl", "getArtworkUrl", "getBinderList", "", "Lcom/amazon/music/views/library/binders/UniversalBinder;", "getContentEnabilityProvider", "Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;", "getContentMetadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "getDiffUtilFactory", "Lcom/amazon/mp3/catalog/fragment/adapter/BaseViewsDiffUtilFactory;", "getEmptyLayoutID", "getFilterButtonClickDestination", "getInflatedRootView", "inflater", "Landroid/view/LayoutInflater;", "container", "getMetadataClickListener", "Lcom/amazon/music/views/library/deeplinks/ContentMetadataOnClickListener;", "getPageDataRepository", "Lcom/amazon/mp3/catalog/fragment/datasource/repository/PageDataRepository;", "getPageHeaderOnClickListenerMap", "", "Lcom/amazon/music/views/library/models/PageHeaderActionIconModel$ActionType;", "Lcom/amazon/music/views/library/binders/ActionIconOnClickListener;", "getPageLoadLatencyCode", "Lcom/amazon/music/metrics/mts/event/types/pagelatencyinfo/PageLoadLatencyCode;", "getPageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "getPlaybackPageType", "Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;", "getRefinementFilterModels", "Lcom/amazon/music/views/library/models/FilterModel;", "getRefinementListModel", "Lcom/amazon/music/views/library/models/ExposedRefinementListModel;", "getRowButtonOnClickProvider", "Lcom/amazon/music/views/library/providers/RowButtonOnClickProvider;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "getTitle", "getTrackStartPosition", "shuffleOn", "list", "Lcom/amazon/mp3/library/item/MusicTrack;", "goOfflineClickAction", "handleRefinementsUpdated", "initEmptyViewResetFiltersButton", "button", "Lcom/amazon/ui/foundations/views/BaseButton;", "initPageModelProvider", "initializeActionBarManager", "initializeRefinementOverflowManager", "isAllOwnedContent", "isDisplayingOnlyLibraryContent", "navigateToCatalogDP", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onChildFilterClick", "filterModel", "onClick", "model", "Lcom/amazon/music/views/library/models/RefinementPillModel;", "view", "onClickPlayback", "artistMetadata", "Lcom/amazon/music/views/library/metadata/ArtistMetadata;", "actionType", "onCreate", "onCreateView", "onDestroy", "onFiltersReset", "Lcom/amazon/music/views/library/models/FilterCollectionModel;", "onNoNetworkDialogCanceled", "onViewCreated", "requireNetworkConnection", "retrieveArtistMetadata", "retrieveTrackMetadataList", "Lcom/amazon/music/views/library/metadata/TrackMetadata;", "setActionBarProvider", "provider", "setContextMenuProviderForActionBar", "setupViews", "shouldBlockPlaybackAndDownload", "metadata", "isSourceLocal", "isShuffled", "shouldDisplayFilterAndContextButtons", "shouldDisplayPageComponentsInTopAppBar", "actionBarHeight", "shouldDisplayTitle", "shouldOverrideResetFilters", "shouldOverrideSelectionChange", "filterType", "selected", "shouldShowEmptyView", "filteredTrackModels", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "showEmptyView", "showRecyclerView", "startPlayback", "switchToOffline", "updateFilterSelected", "filterId", "refinementFilterModels", "updatePageModel", "pageGridViewModel", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "isInitialPage", "isMorePage", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LibraryArtistDetailFragment extends LibraryBaseViewsFragment implements BaseViewsFilterActionBarManager.InfoProvider, RefinementCallback, OverflowMenuReceiver, OnFilterChangedListener {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = LibraryArtistDetailFragment.class.getSimpleName();
    private BaseViewsFilterActionBarManager actionBarManager;
    private ActionBarProvider actionBarProvider;
    private Long artistId;
    private String asin;
    private ViewGroup backgroundArtworkOverlayLayout;
    private ImageView backgroundImageView;
    private String catalogArtistName;
    private final Fragment catalogFragment;
    private final DetailPageRefinementManager detailPageRefinementManager;
    private String followerCount;
    private String initialFilterSelection;
    private final boolean isTrackDownloadEnabled;
    private BaseViewsRefinementManager refinementsManager;

    /* JADX INFO: renamed from: trackInformationFetcher$delegate, reason: from kotlin metadata */
    private final Lazy trackInformationFetcher;
    private LibraryArtistDetailViewModel viewModel;

    /* JADX INFO: compiled from: LibraryArtistDetailFragment.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PageHeaderActionIconModel.ActionType.values().length];
            iArr[PageHeaderActionIconModel.ActionType.PLAY.ordinal()] = 1;
            iArr[PageHeaderActionIconModel.ActionType.SHUFFLE.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public LibraryArtistDetailFragment() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    @JvmStatic
    @JvmOverloads
    public static final LibraryArtistDetailFragment newInstance(Bundle bundle) {
        return INSTANCE.newInstance(bundle);
    }

    @JvmStatic
    public static final boolean shouldDisplayBauhausLibraryPage(Context context, Uri uri) {
        return INSTANCE.shouldDisplayBauhausLibraryPage(context, uri);
    }

    @JvmStatic
    public static final boolean shouldDisplayBauhausLibraryPage(Context context, String str) {
        return INSTANCE.shouldDisplayBauhausLibraryPage(context, str);
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    public String getArtworkUrl() {
        return null;
    }

    @Override // com.amazon.mp3.catalog.fragment.LibraryBaseViewsFragment
    protected Integer getEmptyLayoutID() {
        return null;
    }

    @Override // com.amazon.mp3.catalog.fragment.LibraryBaseViewsFragment
    protected boolean isDisplayingOnlyLibraryContent() {
        return true;
    }

    @Override // com.amazon.music.views.library.providers.RefinementCallback
    public void onChildFilterClick(FilterModel filterModel) {
        Intrinsics.checkNotNullParameter(filterModel, "filterModel");
    }

    @Override // com.amazon.music.views.library.providers.RefinementCallback
    public void onFiltersReset(FilterCollectionModel model) {
        Intrinsics.checkNotNullParameter(model, "model");
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    public boolean shouldDisplayFilterAndContextButtons() {
        return false;
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    /* JADX INFO: renamed from: shouldDisplayTitle */
    public boolean getDisplayTitle() {
        return true;
    }

    public /* synthetic */ LibraryArtistDetailFragment(Fragment fragment, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : fragment);
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    public boolean doesHaveRefinement() {
        return BaseViewsFilterActionBarManager.InfoProvider.DefaultImpls.doesHaveRefinement(this);
    }

    @Override // com.amazon.mp3.fragment.viewmodel.OnFilterChangedListener
    public void downloadSelectionChanged(boolean z) {
        OnFilterChangedListener.DefaultImpls.downloadSelectionChanged(this, z);
    }

    public final Fragment getCatalogFragment() {
        return this.catalogFragment;
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    public boolean shouldDisplayContextMenuOverflowButton() {
        return BaseViewsFilterActionBarManager.InfoProvider.DefaultImpls.shouldDisplayContextMenuOverflowButton(this);
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    public boolean shouldDisplayFilterButton(RecyclerView recyclerView, int i) {
        return BaseViewsFilterActionBarManager.InfoProvider.DefaultImpls.shouldDisplayFilterButton(this, recyclerView, i);
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    public boolean shouldHideRefinementsOnLoad() {
        return BaseViewsFilterActionBarManager.InfoProvider.DefaultImpls.shouldHideRefinementsOnLoad(this);
    }

    public LibraryArtistDetailFragment(Fragment fragment) {
        this.catalogFragment = fragment;
        this.trackInformationFetcher = LazyKt__LazyJVMKt.lazy(new Function0<DefaultTrackInformationFetcher>() { // from class: com.amazon.mp3.detailpages.artist.LibraryArtistDetailFragment$trackInformationFetcher$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final DefaultTrackInformationFetcher invoke() {
                return new DefaultTrackInformationFetcher(new GenericBaseViewModelFetcher(new Function1<BaseViewModel, Boolean>() { // from class: com.amazon.mp3.detailpages.artist.LibraryArtistDetailFragment$trackInformationFetcher$2$visualRowModelFilter$1
                    @Override // kotlin.jvm.functions.Function1
                    public final Boolean invoke(BaseViewModel model) {
                        Intrinsics.checkNotNullParameter(model, "model");
                        return Boolean.valueOf(model instanceof VisualRowItemModel);
                    }
                }));
            }
        });
        this.detailPageRefinementManager = new DetailPageRefinementManager(new Function0<Unit>() { // from class: com.amazon.mp3.detailpages.artist.LibraryArtistDetailFragment$detailPageRefinementManager$1
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
                this.this$0.handleRefinementsUpdated();
            }
        });
        this.isTrackDownloadEnabled = AmazonApplication.getCapabilities().isTrackDownloadFeatureAvailable();
    }

    /* JADX INFO: compiled from: LibraryArtistDetailFragment.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u001c\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\u00102\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0007J\u001c\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u000eH\u0007J\u001c\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n \f*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/amazon/mp3/detailpages/artist/LibraryArtistDetailFragment$Companion;", "", "()V", "ARTIST_ASIN_KEY", "", "ARTIST_FOLLOWER_COUNT", "ARTIST_ID_KEY", "ARTIST_NAME_KEY", "DEFAULT_ARTIST_PLAYBACK_START_POSITION", "", "SELECTED_FILTER_KEY", "TAG", "kotlin.jvm.PlatformType", "generateArtistUri", "Landroid/net/Uri;", "bundle", "Landroid/os/Bundle;", "newInstance", "Lcom/amazon/mp3/detailpages/artist/LibraryArtistDetailFragment;", "catalogFragment", "Landroidx/fragment/app/Fragment;", "shouldDisplayBauhausLibraryPage", "", "context", "Landroid/content/Context;", "contentUri", PageUriUtils.SOURCE_QUERY_PARAM, "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @JvmOverloads
        public final LibraryArtistDetailFragment newInstance(Bundle bundle) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            return newInstance$default(this, bundle, null, 2, null);
        }

        private Companion() {
        }

        public static /* synthetic */ LibraryArtistDetailFragment newInstance$default(Companion companion, Bundle bundle, Fragment fragment, int i, Object obj) {
            if ((i & 2) != 0) {
                fragment = null;
            }
            return companion.newInstance(bundle, fragment);
        }

        @JvmStatic
        @JvmOverloads
        public final LibraryArtistDetailFragment newInstance(Bundle bundle, Fragment catalogFragment) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            LibraryArtistDetailFragment libraryArtistDetailFragment = new LibraryArtistDetailFragment(catalogFragment);
            if (!bundle.containsKey("com.amazon.mp3.library.EXTRA_CONTENT_URI")) {
                bundle.putParcelable("com.amazon.mp3.library.EXTRA_CONTENT_URI", generateArtistUri(bundle));
            }
            libraryArtistDetailFragment.setArguments(bundle);
            return libraryArtistDetailFragment;
        }

        private final Uri generateArtistUri(Bundle bundle) {
            String string;
            String string2 = bundle.getString("com.amazon.mp3.library.EXTRA_SOURCE_ID", "cirrus");
            long jGenerateArtistId = bundle.getLong(Contexts.LibraryArtistDetail.artistId, -1L);
            if (jGenerateArtistId == -1 && (string = bundle.getString("ARTIST_NAME")) != null) {
                jGenerateArtistId = IdGenerator.generateArtistId(string);
            }
            if (jGenerateArtistId == -1) {
                Log.error(LibraryArtistDetailFragment.TAG, "artistId was not populated successfully, failed to build contentUri");
                return null;
            }
            return MediaProvider.Artists.getContentUri(string2, jGenerateArtistId);
        }

        @JvmStatic
        public final boolean shouldDisplayBauhausLibraryPage(Context context, String source) {
            Intrinsics.checkNotNullParameter(context, "context");
            return AmazonApplication.getCapabilities().isBauhausLibraryArtistDPEnabled() && (!ConnectivityUtil.hasAnyInternetConnection(context) || Intrinsics.areEqual(source, "cirrus-local"));
        }

        @JvmStatic
        public final boolean shouldDisplayBauhausLibraryPage(Context context, Uri contentUri) {
            Intrinsics.checkNotNullParameter(context, "context");
            String source = null;
            if (contentUri != null && !Uri.EMPTY.equals(contentUri)) {
                source = MediaProvider.getSource(contentUri);
            }
            return shouldDisplayBauhausLibraryPage(context, source);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final DefaultTrackInformationFetcher getTrackInformationFetcher() {
        return (DefaultTrackInformationFetcher) this.trackInformationFetcher.getValue();
    }

    @Override // com.amazon.mp3.catalog.fragment.LibraryBaseViewsFragment, com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        this.asin = arguments == null ? null : arguments.getString("ARTIST_ASIN");
        Bundle arguments2 = getArguments();
        this.initialFilterSelection = arguments2 == null ? null : arguments2.getString("SELECTED_FILTER");
        Bundle arguments3 = getArguments();
        this.followerCount = arguments3 == null ? null : arguments3.getString("ARTIST_FOLLOWER_COUNT");
        Bundle arguments4 = getArguments();
        this.catalogArtistName = arguments4 != null ? arguments4.getString("ARTIST_NAME") : null;
        super.onCreate(savedInstanceState);
        this.artistId = Long.valueOf(MediaProvider.Artists.getArtistId(getContentUri()));
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        super.onCreateView(inflater, container, savedInstanceState);
        ViewGroup root = getRoot();
        this.backgroundImageView = root == null ? null : (ImageView) root.findViewById(R.id.artwork);
        ViewGroup root2 = getRoot();
        this.backgroundArtworkOverlayLayout = root2 != null ? (ViewGroup) root2.findViewById(R.id.artworkOverlayLayout) : null;
        return getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        ViewGroup root = getRoot();
        BaseButton baseButton = root == null ? null : (BaseButton) root.findViewById(R.id.resetFilterButton);
        if (baseButton == null) {
            return;
        }
        initEmptyViewResetFiltersButton(baseButton);
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initializeActionBarManager();
        BaseViewsFilterActionBarManager baseViewsFilterActionBarManager = this.actionBarManager;
        if (baseViewsFilterActionBarManager != null) {
            getLifecycle().addObserver(baseViewsFilterActionBarManager);
        }
        initializeRefinementOverflowManager();
        this.detailPageRefinementManager.setActionBarManager(this.actionBarManager);
        this.detailPageRefinementManager.setRefinementsManager(this.refinementsManager);
        this.detailPageRefinementManager.setAdditionalCallbackHandler(this);
    }

    @Override // com.amazon.mp3.catalog.fragment.LibraryBaseViewsFragment, com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        BaseViewsFilterActionBarManager baseViewsFilterActionBarManager = this.actionBarManager;
        if (baseViewsFilterActionBarManager != null) {
            getLifecycle().removeObserver(baseViewsFilterActionBarManager);
        }
        this.detailPageRefinementManager.setAdditionalCallbackHandler(null);
    }

    private final void updateFilterSelected(String filterId, List<? extends FilterModel> refinementFilterModels) {
        RefinementPillModel refinementPillModel;
        Object next;
        FilterItemModel itemModel;
        List<RefinementPillModel> refinementList;
        Object obj;
        FilterItemModel itemModel2;
        Iterator<T> it = refinementFilterModels.iterator();
        while (true) {
            refinementPillModel = null;
            obj = null;
            refinementPillModel = null;
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            FilterItemModel itemModel3 = ((FilterModel) next).getItemModel();
            if (Intrinsics.areEqual(itemModel3 == null ? null : itemModel3.getBrowseId(), filterId)) {
                break;
            }
        }
        FilterModel filterModel = (FilterModel) next;
        if (filterModel == null || (itemModel = filterModel.getItemModel()) == null) {
            return;
        }
        itemModel.setSelected(true);
        ExposedRefinementListModel refinementListModel = getRefinementListModel();
        if (refinementListModel != null && (refinementList = refinementListModel.getRefinementList()) != null) {
            Iterator<T> it2 = refinementList.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                Object next2 = it2.next();
                FilterModel content = ((RefinementPillModel) next2).getContent();
                if (Intrinsics.areEqual((content == null || (itemModel2 = content.getItemModel()) == null) ? null : itemModel2.getBrowseId(), filterId)) {
                    obj = next2;
                    break;
                }
            }
            refinementPillModel = (RefinementPillModel) obj;
        }
        if (refinementPillModel != null) {
            refinementPillModel.updateFilterModel(itemModel);
        }
        BaseViewsRefinementManager baseViewsRefinementManager = this.refinementsManager;
        if (baseViewsRefinementManager != null) {
            baseViewsRefinementManager.updateSelectedFilterSet(itemModel);
        }
        handleRefinementsUpdated();
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected ViewGroup getInflatedRootView(LayoutInflater inflater, ViewGroup container) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View viewInflate = inflater.inflate(R.layout.library_artist_fragment_detail, container, false);
        if (viewInflate != null) {
            return (ConstraintLayout) viewInflate;
        }
        throw new NullPointerException("null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout");
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected void setupViews() {
        super.setupViews();
        BaseViewsBinderConfiguration viewsConfiguration = getViewsConfiguration();
        if (viewsConfiguration != null) {
            RefinementPillBinder refinementPillBinder = new RefinementPillBinder(viewsConfiguration.getStyleSheet(), this.detailPageRefinementManager);
            BaseViewModelAdapterProvider adapterProvider = viewsConfiguration.getAdapterProvider();
            List<FilterModel> refinementFilterModels = getRefinementFilterModels();
            String str = this.initialFilterSelection;
            if (str != null) {
                updateFilterSelected(str, refinementFilterModels);
            }
            BaseViewsFilterActionBarManager baseViewsFilterActionBarManager = this.actionBarManager;
            if (baseViewsFilterActionBarManager != null) {
                baseViewsFilterActionBarManager.setAdapterProviderForActionBarView(adapterProvider, refinementPillBinder, refinementFilterModels);
            }
        }
        BaseViewsFilterActionBarManager baseViewsFilterActionBarManager2 = this.actionBarManager;
        int actionBarHeight = baseViewsFilterActionBarManager2 == null ? 0 : baseViewsFilterActionBarManager2.getActionBarHeight();
        KeyEventDispatcher.Component activity = getActivity();
        ActionBarOverlayable actionBarOverlayable = activity instanceof ActionBarOverlayable ? (ActionBarOverlayable) activity : null;
        if (actionBarOverlayable != null) {
            actionBarOverlayable.setContentOffset(getRecyclerView(), actionBarHeight);
        }
        bindBackgroundImage();
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected void updatePageModel(PageGridViewModel pageGridViewModel, boolean isInitialPage, boolean isMorePage) {
        super.updatePageModel(pageGridViewModel, isInitialPage, isMorePage);
        getTrackInformationFetcher().setPageModel(pageGridViewModel);
        setContextMenuProviderForActionBar();
    }

    @Override // com.amazon.mp3.catalog.fragment.LibraryBaseViewsFragment, com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public void initPageModelProvider(FragmentActivity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        PageDataRepository pageDataRepository = getPageDataRepository();
        if (pageDataRepository != null) {
            PageViewModelFactoryProvider pageViewModelFactoryProvider = PageViewModelFactoryProvider.INSTANCE;
            Application application = activity.getApplication();
            Intrinsics.checkNotNullExpressionValue(application, "activity.application");
            setPageModelProvider(pageViewModelFactoryProvider.getViewModel(this, new PageViewModelFactoryProvider.LibraryArtistDetailViewModelFactory(application, pageDataRepository)));
            LibraryArtistDetailViewModel libraryArtistDetailViewModel = this.viewModel;
            if (libraryArtistDetailViewModel != null) {
                libraryArtistDetailViewModel.unregisterDownloadStateListener();
            }
            PageViewModelProvider pageModelProvider = getPageModelProvider();
            LibraryArtistDetailViewModel libraryArtistDetailViewModel2 = pageModelProvider instanceof LibraryArtistDetailViewModel ? (LibraryArtistDetailViewModel) pageModelProvider : null;
            this.viewModel = libraryArtistDetailViewModel2;
            if (libraryArtistDetailViewModel2 != null) {
                libraryArtistDetailViewModel2.registerDownloadStateListener(getContentUri());
            }
            LibraryArtistDetailViewModel libraryArtistDetailViewModel3 = this.viewModel;
            if (libraryArtistDetailViewModel3 != null) {
                libraryArtistDetailViewModel3.setDownloadChangedListener(this);
            }
        }
        if (getPageModelProvider() instanceof BaseViewsRefinementManager.Provider) {
            PageViewModelProvider pageModelProvider2 = getPageModelProvider();
            if (pageModelProvider2 == null) {
                throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.catalog.fragment.viewmodel.BaseViewsRefinementManager.Provider");
            }
            this.refinementsManager = ((BaseViewsRefinementManager.Provider) pageModelProvider2).getRefinementManager();
            return;
        }
        Log.error(TAG, "pageModelProvider is not an instance of BaseViewsRefinementManager.Provider. No refinement manager provided. Refinements disabled");
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public PageDataRepository getPageDataRepository() {
        Uri contentUri = getContentUri();
        if (contentUri == null) {
            return null;
        }
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
        return new PageDataRepository(new LibraryArtistDetailDao(contextRequireContext, contentUri, getPageLoadLatencyCode(), this.followerCount, this.catalogArtistName));
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public List<UniversalBinder<?, ?>> getBinderList() {
        ContentMetadataOnClickListener metadataClickListener = getMetadataClickListener();
        BaseViewsBinderConfiguration viewsConfiguration = getViewsConfiguration();
        if (viewsConfiguration != null) {
            return BaseViewsBinderProvider.BinderListBuilder.withVisualRowItems$default(new BaseViewsBinderProvider.BinderListBuilder(viewsConfiguration).withHeaderRowItems(), null, Boolean.valueOf(this.isTrackDownloadEnabled), 1, null).withArtistDetailHeader(new ArtistClickHandler(requireActivity(), null, null, metadataClickListener), getPageHeaderOnClickListenerMap(), false, true, isAllOwnedContent()).withRefinements(this.detailPageRefinementManager).build();
        }
        Log.error(TAG, "viewsConfiguration is null, returning empty list of binders");
        return CollectionsKt__CollectionsKt.emptyList();
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected ContentMetadataOnClickListener getMetadataClickListener() {
        return new ContentMetadataOnClickListener() { // from class: com.amazon.mp3.detailpages.artist.LibraryArtistDetailFragment.getMetadataClickListener.1
            @Override // com.amazon.music.views.library.deeplinks.ContentMetadataOnClickListener
            public void onContentListClicked(List<? extends ContentMetadata> contentMetadataList, int positionToStart, String contentListTitle) {
                Intrinsics.checkNotNullParameter(contentMetadataList, "contentMetadataList");
            }

            @Override // com.amazon.music.views.library.deeplinks.ContentMetadataOnClickListener
            public void onContentClicked(ContentMetadata contentMetadata) {
                Intrinsics.checkNotNullParameter(contentMetadata, "contentMetadata");
                TrackMetadata trackMetadata = contentMetadata instanceof TrackMetadata ? (TrackMetadata) contentMetadata : null;
                if (trackMetadata == null) {
                    return;
                }
                LibraryArtistDetailFragment libraryArtistDetailFragment = LibraryArtistDetailFragment.this;
                ContentEnabilityProvider contentEnabilityProvider = libraryArtistDetailFragment.getContentEnabilityProvider();
                boolean z = false;
                if (contentEnabilityProvider != null && !contentEnabilityProvider.isContentAccessAvailable(trackMetadata)) {
                    z = true;
                }
                if (!z) {
                    LibraryArtistDetailViewModel libraryArtistDetailViewModel = libraryArtistDetailFragment.viewModel;
                    if (libraryArtistDetailViewModel != null) {
                        libraryArtistDetailViewModel.handleTrackItemClickPlayback(libraryArtistDetailFragment.getContext(), trackMetadata, libraryArtistDetailFragment.getContentUri(), libraryArtistDetailFragment.getTrackInformationFetcher());
                    }
                } else if (contentEnabilityProvider != null) {
                    contentEnabilityProvider.handleContentAccessUnavailable(trackMetadata);
                }
                AbstractBaseViewsFragment.sendUiClickMetric$default(libraryArtistDetailFragment, ActionType.PLAY_SONG, null, libraryArtistDetailFragment.getPageType(), null, trackMetadata.getAsin(), EntityIdType.ASIN, null, trackMetadata.getTrackPosition(), null, trackMetadata.getViewId(), null, null, 3402, null);
            }
        };
    }

    @Override // com.amazon.mp3.catalog.fragment.LibraryBaseViewsFragment, com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected RowButtonOnClickProvider getRowButtonOnClickProvider(FragmentActivity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (getRowButtonOnClickProvider() == null) {
            if (AmazonApplication.getCapabilities().isLikesEverywhereEnabled()) {
                setRowButtonOnClickProvider(new DefaultLikesProvider(activity, getLibraryStateProviderInstance(), null, true, getPageType()));
            } else {
                setRowButtonOnClickProvider(new DefaultAddToLibraryProvider(activity, getLibraryStateProviderInstance(), getTrackContextMenuListener()));
            }
        }
        return getRowButtonOnClickProvider();
    }

    private final Map<PageHeaderActionIconModel.ActionType, ActionIconOnClickListener> getPageHeaderOnClickListenerMap() {
        final ArtistMetadata artistMetadataRetrieveArtistMetadata = retrieveArtistMetadata();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(PageHeaderActionIconModel.ActionType.PLAY, new ActionIconOnClickListener() { // from class: com.amazon.mp3.detailpages.artist.LibraryArtistDetailFragment.getPageHeaderOnClickListenerMap.1
            @Override // com.amazon.music.views.library.binders.ActionIconOnClickListener
            public void onClick(Context context, ContentMetadata metadata) {
                Intrinsics.checkNotNullParameter(context, "context");
                ArtistMetadata artistMetadata = artistMetadataRetrieveArtistMetadata;
                if (artistMetadata == null) {
                    return;
                }
                this.onClickPlayback(artistMetadata, PageHeaderActionIconModel.ActionType.PLAY);
            }
        });
        linkedHashMap.put(PageHeaderActionIconModel.ActionType.SHUFFLE, new ActionIconOnClickListener() { // from class: com.amazon.mp3.detailpages.artist.LibraryArtistDetailFragment.getPageHeaderOnClickListenerMap.2
            @Override // com.amazon.music.views.library.binders.ActionIconOnClickListener
            public void onClick(Context context, ContentMetadata metadata) {
                Intrinsics.checkNotNullParameter(context, "context");
                ArtistMetadata artistMetadata = artistMetadataRetrieveArtistMetadata;
                if (artistMetadata == null) {
                    return;
                }
                this.onClickPlayback(artistMetadata, PageHeaderActionIconModel.ActionType.SHUFFLE);
            }
        });
        return linkedHashMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onClickPlayback(ArtistMetadata artistMetadata, PageHeaderActionIconModel.ActionType actionType) {
        UpsellReason upsellReason;
        int i = WhenMappings.$EnumSwitchMapping$0[actionType.ordinal()];
        boolean z = false;
        if (i == 1) {
            upsellReason = UpsellReason.PLAY;
        } else if (i != 2) {
            upsellReason = null;
        } else {
            upsellReason = UpsellReason.SHUFFLE_ALL;
            z = true;
        }
        if (shouldBlockPlaybackAndDownload(artistMetadata, true, z)) {
            UpsellUtil.showBlockingUpsell(getActivity(), artistMetadata.getAsin(), upsellReason, UpsellSourceEntity.ARTIST);
        } else {
            startPlayback(artistMetadata, z);
        }
    }

    private final void startPlayback(final ArtistMetadata artistMetadata, final boolean shuffleOn) {
        final PlayCollectionAction playCollectionAction = new PlayCollectionAction(getTrackInformationFetcher(), getPlaybackPageType());
        getTrackInformationFetcher().getAllTracks().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.mp3.detailpages.artist.LibraryArtistDetailFragment$$ExternalSyntheticLambda2
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                LibraryArtistDetailFragment.m838startPlayback$lambda13(this.f$0, artistMetadata, playCollectionAction, shuffleOn, (List) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: startPlayback$lambda-13, reason: not valid java name */
    public static final void m838startPlayback$lambda13(LibraryArtistDetailFragment this$0, ArtistMetadata artistMetadata, PlayCollectionAction playAction, boolean z, List musicTracks) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(artistMetadata, "$artistMetadata");
        Intrinsics.checkNotNullParameter(playAction, "$playAction");
        Intrinsics.checkNotNullExpressionValue(musicTracks, "musicTracks");
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(musicTracks, 10));
        Iterator it = musicTracks.iterator();
        while (it.hasNext()) {
            arrayList.add(((MusicTrack) it.next()).getAsin());
        }
        DbUtil.WhereClause filterByIds$default = PlayCollectionAction.TrackInformationFetcher.DefaultImpls.getFilterByIds$default(this$0.getTrackInformationFetcher(), CollectionsKt___CollectionsKt.toMutableList((Collection) arrayList), null, 2, null);
        String name = artistMetadata.getName();
        if (name == null) {
            return;
        }
        FragmentActivity fragmentActivityRequireActivity = this$0.requireActivity();
        Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "requireActivity()");
        boolean zAreAllDownloaded = this$0.areAllDownloaded();
        String asin = artistMetadata.getAsin();
        Uri uriAppendTracksIfNeeded = ContentPlaybackUtils.INSTANCE.appendTracksIfNeeded(this$0.getContentUri());
        int trackStartPosition = this$0.getTrackStartPosition(z, musicTracks);
        Observable observableJust = Observable.just(filterByIds$default);
        Intrinsics.checkNotNullExpressionValue(observableJust, "just(currentTrackFilter)");
        PlayCollectionAction.startNowPlayingActivity$default(playAction, fragmentActivityRequireActivity, zAreAllDownloaded, asin, name, uriAppendTracksIfNeeded, trackStartPosition, z, true, observableJust, null, false, false, null, 7680, null);
    }

    private final boolean areAllDownloaded() {
        List<TrackMetadata> listRetrieveTrackMetadataList = retrieveTrackMetadataList();
        if (!(listRetrieveTrackMetadataList instanceof Collection) || !listRetrieveTrackMetadataList.isEmpty()) {
            Iterator<T> it = listRetrieveTrackMetadataList.iterator();
            while (it.hasNext()) {
                Integer downloadState = ((TrackMetadata) it.next()).getDownloadState();
                if (downloadState == null || downloadState.intValue() != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private final boolean isAllOwnedContent() {
        List<TrackMetadata> listRetrieveTrackMetadataList = retrieveTrackMetadataList();
        if ((listRetrieveTrackMetadataList instanceof Collection) && listRetrieveTrackMetadataList.isEmpty()) {
            return true;
        }
        Iterator<T> it = listRetrieveTrackMetadataList.iterator();
        while (it.hasNext()) {
            if (!Intrinsics.areEqual(((TrackMetadata) it.next()).getIsOwned(), Boolean.TRUE)) {
                return false;
            }
        }
        return true;
    }

    private final int getTrackStartPosition(boolean shuffleOn, List<? extends MusicTrack> list) {
        if (list.isEmpty()) {
            return -1;
        }
        if (shuffleOn) {
            return Random.INSTANCE.nextInt(list.size());
        }
        return 0;
    }

    private final PlaybackPageType getPlaybackPageType() {
        return PlaybackPageType.ARTIST_DETAIL;
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public PageType getPageType() {
        return PageType.DETAIL_ARTIST;
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public BaseViewsDiffUtilFactory getDiffUtilFactory() {
        return new BaseViewsDiffUtilFactory() { // from class: com.amazon.mp3.detailpages.artist.LibraryArtistDetailFragment.getDiffUtilFactory.1
            @Override // com.amazon.mp3.catalog.fragment.adapter.BaseViewsDiffUtilFactory
            public BaseViewsDiffUtil getBaseViewsDiffUtil(List<? extends BaseViewModel> oldList, List<? extends BaseViewModel> newList) {
                Intrinsics.checkNotNullParameter(oldList, "oldList");
                Intrinsics.checkNotNullParameter(newList, "newList");
                return new BaseViewsDiffUtil(oldList, newList);
            }
        };
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected ContentEnabilityProvider getContentEnabilityProvider() {
        if (UserSubscriptionUtil.isNightwingOnly()) {
            return new CatalogEnabilityProvider();
        }
        Context context = getContext();
        Context applicationContext = context == null ? null : context.getApplicationContext();
        if (applicationContext == null) {
            applicationContext = AmazonApplication.getApplication().getApplicationContext();
        }
        Intrinsics.checkNotNullExpressionValue(applicationContext, "this.context?.applicatio…tion().applicationContext");
        return new LibraryContentEnabilityProvider(applicationContext, getActivity());
    }

    private final void bindBackgroundImage() {
        String artistHeroImageUrl = getArtistHeroImageUrl();
        StyleSheet styleSheet = getStyleSheet();
        if (styleSheet == null) {
            return;
        }
        bindBackgroundImageView$default(this, styleSheet, this.backgroundImageView, artistHeroImageUrl, getRecyclerView(), null, 16, null);
    }

    private final void bindEmptyViewBackgroundImage() {
        String artistHeroImageUrl = getArtistHeroImageUrl();
        StyleSheet styleSheet = getStyleSheet();
        if (styleSheet == null) {
            return;
        }
        bindBackgroundImageView$default(this, styleSheet, this.backgroundImageView, artistHeroImageUrl, null, this.backgroundArtworkOverlayLayout, 8, null);
    }

    private final void initializeRefinementOverflowManager() {
        if (getContext() == null) {
            return;
        }
        MutableLiveData<StyleSheet> styleSheet = StyleSheetProvider.getStyleSheet();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "viewLifecycleOwner");
        styleSheet.observe(viewLifecycleOwner, new Observer() { // from class: com.amazon.mp3.detailpages.artist.LibraryArtistDetailFragment$initializeRefinementOverflowManager$lambda-19$$inlined$observe$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // androidx.lifecycle.Observer
            public final void onChanged(T t) {
                StyleSheet styleSheet2 = (StyleSheet) t;
                DetailPageRefinementManager detailPageRefinementManager = this.this$0.detailPageRefinementManager;
                LibraryArtistDetailFragment libraryArtistDetailFragment = this.this$0;
                Intrinsics.checkNotNullExpressionValue(styleSheet2, "styleSheet");
                detailPageRefinementManager.initializeOverflowManager(libraryArtistDetailFragment, styleSheet2);
            }
        });
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
    public final void handleRefinementsUpdated() {
        BaseViewsRefinementManager baseViewsRefinementManager = this.refinementsManager;
        boolean z = false;
        if (baseViewsRefinementManager != null && baseViewsRefinementManager.areFilterSelectionsEmpty()) {
            z = true;
        }
        if (z) {
            navigateToCatalogDP();
            return;
        }
        Context context = getContext();
        if (context != null) {
            BaseViewsRefinementManager baseViewsRefinementManager2 = this.refinementsManager;
            shouldShowEmptyView(baseViewsRefinementManager2 == null ? null : BaseViewsRefinementManager.applyRefinementFilters$default(baseViewsRefinementManager2, context, null, null, 6, null));
        }
        PageViewModelProvider pageModelProvider = getPageModelProvider();
        if (pageModelProvider == null) {
            return;
        }
        PageViewModelProvider.DefaultImpls.fetchUpdates$default(pageModelProvider, 20, getDiffUtilFactory(), null, 4, null);
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected void showRecyclerView() {
        super.showRecyclerView();
        ViewGroup viewGroup = this.backgroundArtworkOverlayLayout;
        if (viewGroup == null) {
            return;
        }
        viewGroup.setVisibility(8);
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected void showEmptyView() {
        super.showEmptyView();
        ViewGroup viewGroup = this.backgroundArtworkOverlayLayout;
        if (viewGroup != null) {
            viewGroup.setVisibility(0);
        }
        bindEmptyViewBackgroundImage();
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected boolean shouldShowEmptyView() {
        Context context = getContext();
        if (context == null) {
            return false;
        }
        BaseViewsRefinementManager baseViewsRefinementManager = this.refinementsManager;
        return true == shouldShowEmptyView(baseViewsRefinementManager == null ? null : baseViewsRefinementManager.getFilteredDataList(context));
    }

    @Override // com.amazon.mp3.catalog.fragment.LibraryBaseViewsFragment, com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected boolean requireNetworkConnection() {
        String str = this.initialFilterSelection;
        return (str == null || str.length() == 0) && !isSourceLocal();
    }

    private final boolean shouldShowEmptyView(List<? extends BaseViewModel> filteredTrackModels) {
        if (filteredTrackModels != null && filteredTrackModels.size() == 0) {
            showEmptyView();
            BaseViewsFilterActionBarManager baseViewsFilterActionBarManager = this.actionBarManager;
            if (baseViewsFilterActionBarManager == null) {
                return true;
            }
            baseViewsFilterActionBarManager.shouldShowExposedRefinementList(true);
            return true;
        }
        BaseViewsFilterActionBarManager baseViewsFilterActionBarManager2 = this.actionBarManager;
        if (baseViewsFilterActionBarManager2 != null) {
            BaseViewsFilterActionBarManager.shouldShowExposedRefinementList$default(baseViewsFilterActionBarManager2, false, 1, null);
        }
        showRecyclerView();
        return false;
    }

    private final void initEmptyViewResetFiltersButton(final BaseButton button) {
        MutableLiveData<StyleSheet> styleSheet = StyleSheetProvider.getStyleSheet();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "viewLifecycleOwner");
        styleSheet.observe(viewLifecycleOwner, new Observer() { // from class: com.amazon.mp3.detailpages.artist.LibraryArtistDetailFragment$initEmptyViewResetFiltersButton$$inlined$observe$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // androidx.lifecycle.Observer
            public final void onChanged(T t) {
                BaseButton.StyleBuilder buttonBuilder = ((StyleSheet) t).getButtonBuilder(ButtonSizeKey.MEDIUM, ButtonStyleKey.SOLID);
                if (buttonBuilder == null) {
                    return;
                }
                String string = this.this$0.getResources().getString(R.string.dmusic_reset_all_filters);
                Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.st…dmusic_reset_all_filters)");
                BaseButton.StyleBuilder styleBuilderWithText = buttonBuilder.withText(string);
                if (styleBuilderWithText == null) {
                    return;
                }
                styleBuilderWithText.applyStyle(button);
            }
        });
        button.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.detailpages.artist.LibraryArtistDetailFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LibraryArtistDetailFragment.m836initEmptyViewResetFiltersButton$lambda24(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initEmptyViewResetFiltersButton$lambda-24, reason: not valid java name */
    public static final void m836initEmptyViewResetFiltersButton$lambda24(LibraryArtistDetailFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.navigateToCatalogDP();
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void navigateToCatalogDP() {
        /*
            r6 = this;
            android.content.Context r0 = r6.getContext()
            boolean r0 = com.amazon.mp3.util.ConnectivityUtil.hasAnyInternetConnection(r0)
            if (r0 != 0) goto Le
            r6.showNetworkErrorDialog()
            goto L53
        Le:
            java.lang.String r0 = r6.asin
            if (r0 != 0) goto L13
            goto L53
        L13:
            androidx.fragment.app.Fragment r1 = r6.getCatalogFragment()
            r2 = 0
            if (r1 != 0) goto L1c
        L1a:
            r1 = r2
            goto L3f
        L1c:
            android.content.Context r3 = r6.getContext()
            boolean r4 = r3 instanceof com.amazon.mp3.activity.MusicHomeActivity
            if (r4 == 0) goto L27
            com.amazon.mp3.activity.MusicHomeActivity r3 = (com.amazon.mp3.activity.MusicHomeActivity) r3
            goto L28
        L27:
            r3 = r2
        L28:
            if (r3 != 0) goto L2b
            goto L35
        L2b:
            com.amazon.mp3.activity.navigation.FragmentController r4 = r3.getFragmentController()
            if (r4 != 0) goto L32
            goto L35
        L32:
            r4.popBackStack()
        L35:
            if (r3 != 0) goto L38
            goto L1a
        L38:
            r4 = 0
            r5 = 1
            r3.changeScreenFragment(r1, r5, r5, r4)
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
        L3f:
            if (r1 != 0) goto L53
            com.amazon.mp3.detailpages.artist.LibraryArtistDetailFragment$$ExternalSyntheticLambda1 r1 = new com.amazon.mp3.detailpages.artist.LibraryArtistDetailFragment$$ExternalSyntheticLambda1
            r1.<init>()
            com.amazon.mp3.dmsfcore.providers.DMSFNavigationProvider$Companion r3 = com.amazon.mp3.dmsfcore.providers.DMSFNavigationProvider.INSTANCE
            java.lang.Long r4 = r6.artistId
            boolean r0 = r3.openArtistDetailPage(r0, r4, r2, r1)
            if (r0 != 0) goto L53
            r6.showNetworkErrorDialog()
        L53:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.detailpages.artist.LibraryArtistDetailFragment.navigateToCatalogDP():void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: navigateToCatalogDP$lambda-28$lambda-27$lambda-26, reason: not valid java name */
    public static final void m837navigateToCatalogDP$lambda28$lambda27$lambda26(LibraryArtistDetailFragment this_run) {
        FragmentController fragmentController;
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        Context context = this_run.getContext();
        MusicHomeActivity musicHomeActivity = context instanceof MusicHomeActivity ? (MusicHomeActivity) context : null;
        if (musicHomeActivity == null || (fragmentController = musicHomeActivity.getFragmentController()) == null) {
            return;
        }
        fragmentController.popBackStack();
    }

    /* JADX INFO: renamed from: com.amazon.mp3.detailpages.artist.LibraryArtistDetailFragment$goOfflineClickAction$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: LibraryArtistDetailFragment.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.detailpages.artist.LibraryArtistDetailFragment$goOfflineClickAction$1", f = "LibraryArtistDetailFragment.kt", i = {}, l = {766}, m = "invokeSuspend", n = {}, s = {})
    static final class C03471 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C03471(Continuation<? super C03471> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return LibraryArtistDetailFragment.this.new C03471(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03471) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                LibraryItemFactory libraryItemFactory = AmazonApplication.getLibraryItemFactory();
                if (libraryItemFactory != null) {
                    boolean zIsArtistAvailableOffline = ((CirrusSourceLibraryItemFactory) libraryItemFactory).isArtistAvailableOffline(LibraryArtistDetailFragment.this.getContentUri());
                    MainCoroutineDispatcher main2 = Dispatchers.getMain();
                    C00761 c00761 = new C00761(zIsArtistAvailableOffline, LibraryArtistDetailFragment.this, null);
                    this.label = 1;
                    if (BuildersKt.withContext(main2, c00761, this) == coroutine_suspended) {
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

        /* JADX INFO: renamed from: com.amazon.mp3.detailpages.artist.LibraryArtistDetailFragment$goOfflineClickAction$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: LibraryArtistDetailFragment.kt */
        @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
        @DebugMetadata(c = "com.amazon.mp3.detailpages.artist.LibraryArtistDetailFragment$goOfflineClickAction$1$1", f = "LibraryArtistDetailFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class C00761 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ boolean $isAvailableOffline;
            int label;
            final /* synthetic */ LibraryArtistDetailFragment this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00761(boolean z, LibraryArtistDetailFragment libraryArtistDetailFragment, Continuation<? super C00761> continuation) {
                super(2, continuation);
                this.$isAvailableOffline = z;
                this.this$0 = libraryArtistDetailFragment;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C00761(this.$isAvailableOffline, this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
            public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C00761) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                if (this.$isAvailableOffline) {
                    this.this$0.switchToOffline();
                } else {
                    LibraryArtistDetailFragment.super.goOfflineClickAction();
                }
                return Unit.INSTANCE;
            }
        }
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment, com.amazon.mp3.dialog.NoNetworkEmptyPageDialog.Listener
    public void goOfflineClickAction() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getDefault(), null, new C03471(null), 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void switchToOffline() {
        if (hasFetchedData()) {
            BaseViewsRefinementManager baseViewsRefinementManager = this.refinementsManager;
            if (baseViewsRefinementManager != null) {
                baseViewsRefinementManager.resetRefinementFilters();
            }
            updateFilterSelected(RefinementFilterType.DOWNLOADED.name(), getRefinementFilterModels());
            BaseViewsFilterActionBarManager baseViewsFilterActionBarManager = this.actionBarManager;
            if (baseViewsFilterActionBarManager == null) {
                return;
            }
            baseViewsFilterActionBarManager.refreshActionBarFilters();
            return;
        }
        this.initialFilterSelection = RefinementFilterType.DOWNLOADED.name();
        retrievePageData();
    }

    @Override // com.amazon.music.views.library.providers.RefinementCallback
    public void onFiltersReset() {
        Log.debug(TAG, "reset all refinement filters");
        showRecyclerView();
    }

    private final void initializeActionBarManager() {
        BaseViewsFilterActionBarManager baseViewsFilterActionBarManager = new BaseViewsFilterActionBarManager(this, this, getRecyclerView(), this.actionBarProvider);
        this.actionBarManager = baseViewsFilterActionBarManager;
        baseViewsFilterActionBarManager.shouldShowFilterButton();
        BaseViewsFilterActionBarManager baseViewsFilterActionBarManager2 = this.actionBarManager;
        if (baseViewsFilterActionBarManager2 != null) {
            BaseViewsFilterActionBarManager.shouldShowExposedRefinementList$default(baseViewsFilterActionBarManager2, false, 1, null);
        }
        BaseViewsFilterActionBarManager baseViewsFilterActionBarManager3 = this.actionBarManager;
        if (baseViewsFilterActionBarManager3 == null) {
            return;
        }
        baseViewsFilterActionBarManager3.shouldShowPageComponentInTopAppBar();
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

    @Override // com.amazon.mp3.fragment.headerbar.OverflowMenuReceiver
    public void setActionBarProvider(ActionBarProvider provider) {
        this.actionBarProvider = provider;
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
    public boolean shouldDisplayPageComponentsInTopAppBar(int actionBarHeight) {
        Integer numFindPositionOfRefinementList;
        RecyclerView recyclerView = getRecyclerView();
        RecyclerView.LayoutManager layoutManager = recyclerView == null ? null : recyclerView.getLayoutManager();
        LinearLayoutManager linearLayoutManager = layoutManager instanceof LinearLayoutManager ? (LinearLayoutManager) layoutManager : null;
        if (linearLayoutManager == null || (numFindPositionOfRefinementList = findPositionOfRefinementList()) == null) {
            return false;
        }
        return BaseViewsFilterActionBarManager.INSTANCE.isTargetViewUnderActionBarView(numFindPositionOfRefinementList.intValue(), actionBarHeight, linearLayoutManager);
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    /* JADX INFO: renamed from: getTitle */
    public String getTitleText() {
        ArtistMetadata artistMetadataRetrieveArtistMetadata = retrieveArtistMetadata();
        if (artistMetadataRetrieveArtistMetadata == null) {
            return null;
        }
        return artistMetadataRetrieveArtistMetadata.getName();
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
        return retrieveArtistMetadata();
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

    private final ArtistMetadata retrieveArtistMetadata() {
        ArtistDetailHeaderModel artistDetailHeaderModel;
        PageGridViewModel pageModel = getPageModel();
        if (pageModel == null || (artistDetailHeaderModel = (ArtistDetailHeaderModel) BaseViewContainerModelUtil.INSTANCE.findChildByType(pageModel, ArtistDetailHeaderModel.class)) == null) {
            return null;
        }
        return artistDetailHeaderModel.getMetadata();
    }

    private final String getArtistHeroImageUrl() {
        ArtistDetailHeaderModel artistDetailHeaderModel;
        PageGridViewModel pageModel = getPageModel();
        if (pageModel == null || (artistDetailHeaderModel = (ArtistDetailHeaderModel) BaseViewContainerModelUtil.INSTANCE.findChildByType(pageModel, ArtistDetailHeaderModel.class)) == null) {
            return null;
        }
        return artistDetailHeaderModel.getHeroImageUrl();
    }

    private final List<TrackMetadata> retrieveTrackMetadataList() {
        List<BaseViewModel> models;
        PageGridViewModel pageModel = getPageModel();
        ArrayList arrayList = null;
        if (pageModel != null && (models = pageModel.getModels()) != null) {
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : models) {
                BaseViewModel baseViewModel = (BaseViewModel) obj;
                if ((baseViewModel instanceof VisualRowItemModel) && (((VisualRowItemModel) baseViewModel).getMetadata() instanceof TrackMetadata)) {
                    arrayList2.add(obj);
                }
            }
            ArrayList arrayList3 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList2, 10));
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                arrayList3.add((TrackMetadata) ((VisualRowItemModel) ((BaseViewModel) it.next())).getMetadata());
            }
            arrayList = arrayList3;
        }
        return arrayList == null ? CollectionsKt__CollectionsKt.emptyList() : arrayList;
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public void emitUiPageViewMetric() {
        String str;
        String strValueOf = this.asin;
        if (strValueOf != null) {
            str = "ASIN";
        } else {
            strValueOf = String.valueOf(this.artistId);
            str = Contexts.LibraryArtistDetail.artistId;
        }
        AbstractBaseViewsFragment.sendUiPageViewMetric$default(this, getPageType(), null, strValueOf, str, null, null, null, null, 242, null);
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected boolean shouldBlockPlaybackAndDownload(ContentMetadata metadata, boolean isSourceLocal, boolean isShuffled) {
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        return FreeTierPlaybackUtil.shouldBlockPlaybackAndDownload(getContentEnabilityProvider(), metadata, retrieveTrackMetadataList(), isSourceLocal);
    }

    @Override // com.amazon.mp3.fragment.viewmodel.OnFilterChangedListener
    public boolean shouldOverrideSelectionChange(String filterType, boolean selected) {
        Intrinsics.checkNotNullParameter(filterType, "filterType");
        if (getActivity() != null && !ConnectivityUtil.hasAnyInternetConnection(getActivity()) && !selected) {
            BaseViewsRefinementManager baseViewsRefinementManager = this.refinementsManager;
            if (baseViewsRefinementManager != null && baseViewsRefinementManager.selectedRefinementCount() == 1) {
                FragmentActivity activity = getActivity();
                if (activity == null) {
                    return true;
                }
                showNetworkErrorDialog(activity);
                return true;
            }
        }
        return false;
    }

    @Override // com.amazon.mp3.fragment.viewmodel.OnFilterChangedListener
    public boolean shouldOverrideResetFilters() {
        return shouldOverrideSelectionChange(RefinementFilterType.LIBRARY.name(), false);
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment, com.amazon.mp3.dialog.NoNetworkEmptyPageDialog.Listener
    public void onNoNetworkDialogCanceled() {
        if (hasFetchedData()) {
            if (shouldShowEmptyView()) {
                showEmptyView();
                return;
            } else {
                showRecyclerView();
                return;
            }
        }
        String str = this.initialFilterSelection;
        if (str == null || str.length() == 0) {
            super.onNoNetworkDialogCanceled();
        }
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected PageLoadLatencyCode getPageLoadLatencyCode() {
        return PageLoadLatencyCode.LIBRARY_ARTIST_DETAIL;
    }

    static /* synthetic */ void bindBackgroundImageView$default(LibraryArtistDetailFragment libraryArtistDetailFragment, StyleSheet styleSheet, ImageView imageView, String str, RecyclerView recyclerView, View view, int i, Object obj) {
        libraryArtistDetailFragment.bindBackgroundImageView(styleSheet, imageView, str, (i & 8) != 0 ? null : recyclerView, (i & 16) != 0 ? null : view);
    }

    private final void bindBackgroundImageView(StyleSheet styleSheet, ImageView artworkView, String backgroundImageUrl, RecyclerView recyclerViewOverlay, View viewOverlay) {
        if (artworkView == null || backgroundImageUrl == null) {
            return;
        }
        if (recyclerViewOverlay != null) {
            new FadingBackdropHelper(artworkView, recyclerViewOverlay, styleSheet).setBackdropURL(backgroundImageUrl);
        } else {
            if (viewOverlay == null) {
                return;
            }
            new FadingBackdropHelper(artworkView, viewOverlay, styleSheet).setBackdropURL(backgroundImageUrl);
        }
    }
}
