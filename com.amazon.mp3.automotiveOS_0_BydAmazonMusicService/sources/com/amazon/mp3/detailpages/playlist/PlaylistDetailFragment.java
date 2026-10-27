package com.amazon.mp3.detailpages.playlist;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.KeyEventDispatcher;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.actionbar.ActionBarProvider;
import com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager;
import com.amazon.mp3.activity.BaseActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.baseviews.model.configuration.PlaylistModelConfiguration;
import com.amazon.mp3.capability.FeatureFlagsProvider;
import com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment;
import com.amazon.mp3.catalog.fragment.LibraryBaseViewsFragment;
import com.amazon.mp3.catalog.fragment.PrimePlaylistDetailMetadataClickListener;
import com.amazon.mp3.catalog.fragment.adapter.BaseViewsBinderConfiguration;
import com.amazon.mp3.catalog.fragment.adapter.BaseViewsBinderProvider;
import com.amazon.mp3.catalog.fragment.adapter.BaseViewsDiffUtil;
import com.amazon.mp3.catalog.fragment.adapter.BaseViewsDiffUtilFactory;
import com.amazon.mp3.catalog.fragment.converter.BauhausDetailHeaderTextUtil;
import com.amazon.mp3.catalog.fragment.datasource.dao.PlaylistDetailDao;
import com.amazon.mp3.catalog.fragment.datasource.repository.PlaylistDetailDataRepository;
import com.amazon.mp3.catalog.fragment.provider.PlaylistContentEnabilityProvider;
import com.amazon.mp3.catalog.fragment.viewmodel.BaseViewsRefinementManager;
import com.amazon.mp3.catalog.fragment.viewmodel.PageFetchInfo;
import com.amazon.mp3.catalog.fragment.viewmodel.PageViewModelFactoryProvider;
import com.amazon.mp3.catalog.fragment.viewmodel.PageViewModelProvider;
import com.amazon.mp3.catalog.fragment.viewmodel.PlaylistDetailViewModel;
import com.amazon.mp3.cloudqueue.FreeTierPlaybackUtil;
import com.amazon.mp3.cloudqueue.model.PlayableEntityIdentifier;
import com.amazon.mp3.cloudqueue.model.PlayableEntityIdentifierType;
import com.amazon.mp3.curate.provider.CurateEndpointSettingsProvider;
import com.amazon.mp3.curate.provider.CurateFeatureFlagProvider;
import com.amazon.mp3.curate.provider.CurateFragmentsStackProvider;
import com.amazon.mp3.curate.provider.CurateLibraryNavigationProvider;
import com.amazon.mp3.curate.provider.CurateNetworkProvider;
import com.amazon.mp3.curate.provider.CuratePlaybackProvider;
import com.amazon.mp3.curate.provider.CuratePlaylistProvider;
import com.amazon.mp3.curate.provider.CurateStyleSheetProvider;
import com.amazon.mp3.curate.provider.CurateSubscriptionProvider;
import com.amazon.mp3.detailpages.actions.GenericBaseViewModelFetcher;
import com.amazon.mp3.detailpages.actions.PlaylistTrackInformationFetcher;
import com.amazon.mp3.detailpages.album.click.CollectionTrackClickListener;
import com.amazon.mp3.detailpages.playlist.actions.ButtonNavigatorClickListener;
import com.amazon.mp3.detailpages.playlist.actions.DeleteUserPlaylistAction;
import com.amazon.mp3.detailpages.playlist.actions.PlaylistDownloadAction;
import com.amazon.mp3.detailpages.playlist.actions.PlaylistFollowAction;
import com.amazon.mp3.detailpages.playlist.actions.PlaylistPlaybackAction;
import com.amazon.mp3.detailpages.playlist.actions.PlaylistSFAAction;
import com.amazon.mp3.detailpages.playlist.actions.PlaylistShareAction;
import com.amazon.mp3.detailpages.playlist.actions.PlaylistUnfollowAction;
import com.amazon.mp3.detailpages.playlist.playlistSongSearch.PlaylistSongSearchHandler;
import com.amazon.mp3.dialog.ErrorDialogButton;
import com.amazon.mp3.dialog.OneButtonErrorDialog;
import com.amazon.mp3.dynamicbackground.DynamicBackgroundEffectUtils;
import com.amazon.mp3.dynamicbackground.DynamicBackgroundImageCondition;
import com.amazon.mp3.eligibility.CachedCapabilityActionProviderImpl;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.fragment.contextmenu.AbstractPlaylistContextMenuListener;
import com.amazon.mp3.fragment.contextmenu.AbstractTrackContextMenuListener;
import com.amazon.mp3.fragment.contextmenu.ContextMenuPageType;
import com.amazon.mp3.fragment.headerbar.OverflowMenuReceiver;
import com.amazon.mp3.fragment.navigation.NavigationProvider;
import com.amazon.mp3.fragment.viewmodel.OnFilterChangedListener;
import com.amazon.mp3.haptics.HapticsUtil;
import com.amazon.mp3.library.activity.AddToPlaylistActivity;
import com.amazon.mp3.library.activity.AddToPlaylistTabActivity;
import com.amazon.mp3.library.activity.AllAccessPlaylistLimitReachedActivity;
import com.amazon.mp3.library.activity.SavePlaylistActivity;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.item.Playlist;
import com.amazon.mp3.library.provider.LibraryStateProvider;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.library.provider.source.cirrus.CirrusPlaylistScratch;
import com.amazon.mp3.library.service.sync.PlaylistDatabaseCallback;
import com.amazon.mp3.library.service.sync.PlaylistSyncHelper;
import com.amazon.mp3.library.util.AllAccessUserPlaylistLimitTracker;
import com.amazon.mp3.library.util.AllAccessUserPlaylistSelectionHandler;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.navigation.deeplink.BaseViewsDeeplinkActionHandler;
import com.amazon.mp3.net.AbstractHttpClient;
import com.amazon.mp3.net.playlist.MusicPlaylistExceptions;
import com.amazon.mp3.net.service.ServiceException;
import com.amazon.mp3.ondemandplaylists.api.GetOnDemandPlaylistsService;
import com.amazon.mp3.ondemandplaylists.metrics.GetOnDemandPlaylistsMetricsUtil;
import com.amazon.mp3.platform.providers.Coroutines;
import com.amazon.mp3.playback.state.ContentPlaybackUtils;
import com.amazon.mp3.playlist.CatalogPlaylist;
import com.amazon.mp3.prime.ContentUnavailableReason;
import com.amazon.mp3.prime.browse.PrimeTabFragmentHost;
import com.amazon.mp3.prime.upsell.nightwing.UpsellUtil;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellInformation;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellReason;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellSourceEntity;
import com.amazon.mp3.providers.DefaultAddToLibraryProvider;
import com.amazon.mp3.providers.DefaultCachedArtworkProvider;
import com.amazon.mp3.providers.DefaultLikesProvider;
import com.amazon.mp3.providers.LibraryContextMenuProvider;
import com.amazon.mp3.providers.MetadataContextMenuHandler;
import com.amazon.mp3.providers.MissingSDCardHandler;
import com.amazon.mp3.providers.PlaylistTrackContextMenuProvider;
import com.amazon.mp3.providers.PrimePlaylistContextMenuProvider;
import com.amazon.mp3.providers.UserPlaylistDetailProfileLinkHandler;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.ContentAccessUtil;
import com.amazon.mp3.util.ContentEnabilityDialogUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.mp3.util.extensions.ActivityKt;
import com.amazon.mp3.view.refinements.DetailPageRefinementManager;
import com.amazon.mp3.view.refinements.RefinementFilterType;
import com.amazon.mp3.voice.hints.guicontext.GUIMetadataNotReadyException;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.account.AccountStateChangeListener;
import com.amazon.music.account.AccountStateField;
import com.amazon.music.curate.skyfire.Curate;
import com.amazon.music.curate.skyfire.bootstrap.CurateBootstrapProviders;
import com.amazon.music.explore.ExploreFactory;
import com.amazon.music.explore.events.SingleLiveEvent;
import com.amazon.music.explore.managers.ExploreManagerFactory;
import com.amazon.music.explore.managers.ExploreVideoStoryManager;
import com.amazon.music.explore.views.videoPreview.CoachMarkTutorialView;
import com.amazon.music.find.skyfire.Contexts;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.freetier.featuregating.FMPMFeatureGating;
import com.amazon.music.imageloader.ImageLoader;
import com.amazon.music.media.playback.MediaCollectionType;
import com.amazon.music.media.playback.util.ThreadUtils;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.SelectionSourceType;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.PageLoadLatencyCode;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageSubType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.subscription.BrowseMode;
import com.amazon.music.uicontentview.ContentViewManager;
import com.amazon.music.views.library.binders.ActionIconOnClickListener;
import com.amazon.music.views.library.binders.DownloadIconOnClickListener;
import com.amazon.music.views.library.binders.LargeIconOnClickListener;
import com.amazon.music.views.library.binders.RefinementPillBinder;
import com.amazon.music.views.library.binders.UniversalBinder;
import com.amazon.music.views.library.deeplinks.ContentMetadataOnClickListener;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.PlaylistMetadata;
import com.amazon.music.views.library.models.ButtonType;
import com.amazon.music.views.library.models.DownloadStateModel;
import com.amazon.music.views.library.models.EmptyStateButtonType;
import com.amazon.music.views.library.models.ErrorModel;
import com.amazon.music.views.library.models.ExposedRefinementListModel;
import com.amazon.music.views.library.models.FilterCollectionModel;
import com.amazon.music.views.library.models.FilterItemModel;
import com.amazon.music.views.library.models.FilterModel;
import com.amazon.music.views.library.models.MultiStateButtonModel;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.PageHeaderActionIconModel;
import com.amazon.music.views.library.models.PageHeaderModel;
import com.amazon.music.views.library.models.RefinementPillModel;
import com.amazon.music.views.library.models.StoryPageHeaderModel;
import com.amazon.music.views.library.models.VisualRowItemModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.providers.ButtonActionProvider;
import com.amazon.music.views.library.providers.ContentEnabilityProvider;
import com.amazon.music.views.library.providers.EmptyStateActionProvider;
import com.amazon.music.views.library.providers.FeaturedToggleListenerProvider;
import com.amazon.music.views.library.providers.MultiStateButtonClickListenerProvider;
import com.amazon.music.views.library.providers.RefinementCallback;
import com.amazon.music.views.library.providers.RowButtonOnClickProvider;
import com.amazon.music.views.library.recyclerview.BaseViewModelAdapterProvider;
import com.amazon.music.views.library.recyclerview.HideableBaseViewModelAdapterProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.AlphaKey;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.music.views.library.views.presentation.ActionBarOverlayable;
import com.amazon.ui.foundations.views.BaseButton;
import com.visualon.OSMPUtils.voOSType;
import dagger.android.support.AndroidSupportInjection;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.IntRange;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import rx.Observable;
import rx.functions.Action1;

/* JADX INFO: compiled from: PlaylistDetailFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000²\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0010*\u0002\u009b\u0002\u0018\u0000 Í\u00022\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u00062\u00020\u00072\u00020\b2\u00020\t2\u00020\n:\u0004Í\u0002Î\u0002B\t¢\u0006\u0006\bË\u0002\u0010Ì\u0002J\u0010\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u000bH\u0002J\n\u0010\u0010\u001a\u0004\u0018\u00010\u000fH\u0002J\u0010\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0011H\u0002J\b\u0010\u0015\u001a\u00020\u0014H\u0002J'\u0010\u001a\u001a\u00020\r2\b\u0010\u0017\u001a\u0004\u0018\u00010\u00162\b\u0010\u0019\u001a\u0004\u0018\u00010\u0018H\u0082@ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u001bJ\b\u0010\u001c\u001a\u00020\rH\u0002J\u0018\u0010 \u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\u000f2\u0006\u0010\u001f\u001a\u00020\u001eH\u0002J\b\u0010!\u001a\u00020\rH\u0002J\u0018\u0010%\u001a\u00020\r2\u000e\u0010$\u001a\n\u0012\u0004\u0012\u00020#\u0018\u00010\"H\u0002J\b\u0010&\u001a\u00020\u001eH\u0002J\b\u0010'\u001a\u00020\rH\u0002J\u0018\u0010*\u001a\u00020\r2\u0006\u0010(\u001a\u00020\u00122\u0006\u0010)\u001a\u00020\u001eH\u0002J\b\u0010+\u001a\u00020\rH\u0002J\b\u0010,\u001a\u00020\rH\u0002J\b\u0010-\u001a\u00020\rH\u0002J\n\u0010/\u001a\u0004\u0018\u00010.H\u0002J\b\u00101\u001a\u000200H\u0002J\u0010\u00103\u001a\u00020\r2\u0006\u00102\u001a\u00020\u0018H\u0002J\b\u00104\u001a\u00020\rH\u0002J\u0014\u00108\u001a\u000e\u0012\u0004\u0012\u000206\u0012\u0004\u0012\u00020705H\u0002J\"\u0010:\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\u000f2\u0006\u0010\u001f\u001a\u00020\u001e2\b\b\u0002\u00109\u001a\u00020\u001eH\u0002J5\u0010>\u001a\u00020\r2\b\u0010\u001d\u001a\u0004\u0018\u00010;2\u0006\u0010\u001f\u001a\u00020\u001e2\b\b\u0002\u00109\u001a\u00020\u001e2\b\u0010=\u001a\u0004\u0018\u00010<H\u0002¢\u0006\u0004\b>\u0010?J\u0018\u0010A\u001a\u00020\r2\u0006\u0010@\u001a\u00020.2\u0006\u0010\u001f\u001a\u00020\u001eH\u0002J\b\u0010B\u001a\u00020\rH\u0002J\u0012\u0010D\u001a\u00020\r2\b\u0010C\u001a\u0004\u0018\u00010.H\u0002J\u001a\u0010E\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\u00162\b\u0010\u001d\u001a\u0004\u0018\u00010;H\u0002J\b\u0010F\u001a\u00020\u001eH\u0002J\n\u0010G\u001a\u0004\u0018\u00010\u0018H\u0002J\u0012\u0010I\u001a\u00020\r2\b\b\u0002\u0010H\u001a\u00020\u001eH\u0002J \u0010M\u001a\u00020\r2\u0006\u0010J\u001a\u00020<2\u0006\u0010K\u001a\u00020<2\u0006\u0010L\u001a\u00020<H\u0002J\u0010\u0010O\u001a\u00020\r2\u0006\u0010N\u001a\u00020\u000fH\u0002J\b\u0010P\u001a\u00020\rH\u0002J\n\u0010R\u001a\u0004\u0018\u00010QH\u0002J\u0012\u0010R\u001a\u0004\u0018\u00010Q2\u0006\u0010\u0019\u001a\u00020\u0018H\u0002J\n\u0010S\u001a\u0004\u0018\u00010.H\u0002J\n\u0010U\u001a\u0004\u0018\u00010TH\u0002J\b\u0010V\u001a\u00020\rH\u0002J\u001a\u0010Y\u001a\u00020\r2\b\u0010\u001d\u001a\u0004\u0018\u00010\u000f2\u0006\u0010X\u001a\u00020WH\u0002J\u0010\u0010Z\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\u000fH\u0002J\b\u0010[\u001a\u00020\u001eH\u0002J\b\u0010\\\u001a\u00020\rH\u0002J\b\u0010]\u001a\u00020\rH\u0002J\u001a\u0010b\u001a\u00020`2\u0006\u0010_\u001a\u00020^2\b\u0010a\u001a\u0004\u0018\u00010`H\u0014J\b\u0010c\u001a\u00020<H\u0014J\u0010\u0010d\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\u0016H\u0016J\u0012\u0010g\u001a\u00020\r2\b\u0010f\u001a\u0004\u0018\u00010eH\u0016J&\u0010i\u001a\u0004\u0018\u00010h2\u0006\u0010_\u001a\u00020^2\b\u0010a\u001a\u0004\u0018\u00010`2\b\u0010f\u001a\u0004\u0018\u00010eH\u0016J\u001a\u0010k\u001a\u00020\r2\u0006\u0010j\u001a\u00020h2\b\u0010f\u001a\u0004\u0018\u00010eH\u0016J\b\u0010l\u001a\u00020\rH\u0016J\b\u0010m\u001a\u00020\rH\u0016J\b\u0010n\u001a\u00020\rH\u0016J\b\u0010o\u001a\u00020\rH\u0016J\b\u0010p\u001a\u00020\rH\u0016J\b\u0010q\u001a\u00020\rH\u0016J\u0012\u0010r\u001a\u00020\r2\b\u0010f\u001a\u0004\u0018\u00010eH\u0016J\u0006\u0010s\u001a\u00020\rJ\b\u0010t\u001a\u00020\rH\u0016J\"\u0010y\u001a\u00020\r2\u0006\u0010u\u001a\u00020<2\u0006\u0010v\u001a\u00020<2\b\u0010x\u001a\u0004\u0018\u00010wH\u0016J\b\u0010{\u001a\u00020zH\u0016J\n\u0010}\u001a\u0004\u0018\u00010|H\u0016J\u0010\u0010~\u001a\u00020\r2\u0006\u0010X\u001a\u00020WH\u0016J\t\u0010\u0080\u0001\u001a\u0004\u0018\u00010\u007fJ\u001b\u0010\u0083\u0001\u001a\u00020\r2\u0007\u0010\u0081\u0001\u001a\u00020<2\u0007\u0010\u0082\u0001\u001a\u00020<H\u0016J\t\u0010\u0084\u0001\u001a\u00020\rH\u0016J\n\u0010\u0086\u0001\u001a\u00030\u0085\u0001H\u0014J\u0018\u0010\u0088\u0001\u001a\u0011\u0012\r\u0012\u000b\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0087\u00010\"H\u0016J\n\u0010\u008a\u0001\u001a\u00030\u0089\u0001H\u0016J\u0014\u0010\u008b\u0001\u001a\u0004\u0018\u00010<H\u0014¢\u0006\u0006\b\u008b\u0001\u0010\u008c\u0001J\t\u0010\u008d\u0001\u001a\u00020\rH\u0016J\f\u0010\u008f\u0001\u001a\u0005\u0018\u00010\u008e\u0001H\u0014J\t\u0010\u0090\u0001\u001a\u00020\u001eH\u0014J\u0015\u0010\u0093\u0001\u001a\u00020\r2\n\u0010\u0092\u0001\u001a\u0005\u0018\u00010\u0091\u0001H\u0016J\u0012\u0010\u0095\u0001\u001a\u00020\u001e2\u0007\u0010\u0094\u0001\u001a\u00020<H\u0016J\u000b\u0010\u0096\u0001\u001a\u0004\u0018\u00010.H\u0016J\u0010\u0010\u0098\u0001\u001a\t\u0012\u0005\u0012\u00030\u0097\u00010\"H\u0016J\u0014\u0010\u0099\u0001\u001a\u0004\u0018\u00010<H\u0016¢\u0006\u0006\b\u0099\u0001\u0010\u008c\u0001J\u000b\u0010\u009a\u0001\u001a\u0004\u0018\u00010;H\u0016J\f\u0010\u009c\u0001\u001a\u0005\u0018\u00010\u009b\u0001H\u0016J\u001e\u0010\u009f\u0001\u001a\u00020\u001e2\n\u0010\u009e\u0001\u001a\u0005\u0018\u00010\u009d\u00012\u0007\u0010\u0094\u0001\u001a\u00020<H\u0016J\t\u0010 \u0001\u001a\u00020\u001eH\u0016J\t\u0010¡\u0001\u001a\u00020\u001eH\u0016J\t\u0010¢\u0001\u001a\u00020\u001eH\u0016J\t\u0010£\u0001\u001a\u00020\u001eH\u0016J\t\u0010¤\u0001\u001a\u00020\u001eH\u0014J\u0012\u0010¦\u0001\u001a\u00020\u001e2\u0007\u0010¥\u0001\u001a\u00020\u001eH\u0014J\t\u0010§\u0001\u001a\u00020\u001eH\u0016J\u0013\u0010©\u0001\u001a\u00020\r2\b\u0010¨\u0001\u001a\u00030\u0097\u0001H\u0016J\f\u0010«\u0001\u001a\u0005\u0018\u00010ª\u0001H\u0016J\u001d\u0010®\u0001\u001a\u00020\r2\b\u0010\u00ad\u0001\u001a\u00030¬\u00012\b\u0010j\u001a\u0004\u0018\u00010hH\u0016J\t\u0010¯\u0001\u001a\u00020\rH\u0016J\f\u0010±\u0001\u001a\u0005\u0018\u00010°\u0001H\u0014J\u0013\u0010¯\u0001\u001a\u00020\r2\b\u0010\u00ad\u0001\u001a\u00030²\u0001H\u0016J\t\u0010³\u0001\u001a\u00020\rH\u0014J\u0013\u0010¶\u0001\u001a\u00020\r2\b\u0010µ\u0001\u001a\u00030´\u0001H\u0016J\u0013\u0010¸\u0001\u001a\u00020\r2\b\u0010µ\u0001\u001a\u00030·\u0001H\u0016J'\u0010½\u0001\u001a\u00020\r2\n\u0010º\u0001\u001a\u0005\u0018\u00010¹\u00012\u0007\u0010»\u0001\u001a\u00020\u001e2\u0007\u0010¼\u0001\u001a\u00020\u001eH\u0014J\u0013\u0010À\u0001\u001a\u00020\r2\b\u0010¿\u0001\u001a\u00030¾\u0001H\u0014J\b\u0010%\u001a\u00020\u001eH\u0014J\f\u0010Â\u0001\u001a\u0005\u0018\u00010Á\u0001H\u0016J\u0014\u0010Ä\u0001\u001a\u0005\u0018\u00010Ã\u00012\u0006\u0010X\u001a\u00020WH\u0014J\u0011\u0010Å\u0001\u001a\u00020\r2\u0006\u00102\u001a\u00020\u0018H\u0014J\u0015\u0010È\u0001\u001a\u00020\r2\n\u0010Ç\u0001\u001a\u0005\u0018\u00010Æ\u0001H\u0016J\u0015\u0010Ë\u0001\u001a\u00020\r2\n\u0010Ê\u0001\u001a\u0005\u0018\u00010É\u0001H\u0016J\t\u0010Ì\u0001\u001a\u00020\u001eH\u0014J\t\u0010Í\u0001\u001a\u00020\u001eH\u0014J\u0007\u0010Î\u0001\u001a\u00020\u001eJ\u0007\u0010Ï\u0001\u001a\u00020.J\"\u0010Ñ\u0001\u001a\u00020\u001e2\u0006\u0010\u001d\u001a\u00020;2\u0007\u0010Ð\u0001\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001eH\u0014J\u001b\u0010Ô\u0001\u001a\f\u0012\u0007\u0012\u0005\u0018\u00010Ó\u00010Ò\u00012\u0006\u0010\u0019\u001a\u00020\u0018H\u0016J\u0017\u0010Õ\u0001\u001a\u0010\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030Ó\u00010\"0Ò\u0001H\u0016J\t\u0010Ö\u0001\u001a\u00020.H\u0016J\u000b\u0010×\u0001\u001a\u0004\u0018\u00010\u0018H\u0016J\u000b\u0010Ø\u0001\u001a\u0004\u0018\u00010.H\u0016J\t\u0010Ù\u0001\u001a\u00020.H\u0016J\u001b\u0010Û\u0001\u001a\u0004\u0018\u00010.2\u000e\u0010Ú\u0001\u001a\t\u0012\u0005\u0012\u00030Ó\u00010\"H\u0016J+\u0010Ý\u0001\u001a\u000b\u0012\u0004\u0012\u00020.\u0018\u00010Ü\u00012\u000e\u0010Ú\u0001\u001a\t\u0012\u0005\u0012\u00030Ó\u00010\"H\u0016¢\u0006\u0006\bÝ\u0001\u0010Þ\u0001J\t\u0010ß\u0001\u001a\u00020\u001eH\u0014J\f\u0010á\u0001\u001a\u0005\u0018\u00010à\u0001H\u0014J\u0011\u0010â\u0001\u001a\n\u0012\u0004\u0012\u00020#\u0018\u00010\"H\u0014J\u0013\u0010ã\u0001\u001a\u00020\r2\n\b\u0002\u0010@\u001a\u0004\u0018\u00010.J\t\u0010ä\u0001\u001a\u00020\rH\u0014J\u0013\u0010ç\u0001\u001a\u00020\r2\b\u0010æ\u0001\u001a\u00030å\u0001H\u0014R\u0019\u0010è\u0001\u001a\u00020.8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bè\u0001\u0010é\u0001R\u0019\u0010ê\u0001\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bê\u0001\u0010ë\u0001R!\u0010ñ\u0001\u001a\u00030ì\u00018BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\bí\u0001\u0010î\u0001\u001a\u0006\bï\u0001\u0010ð\u0001R\u001b\u0010ò\u0001\u001a\u0004\u0018\u00010\u001e8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bò\u0001\u0010ó\u0001R\u0019\u0010ô\u0001\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bô\u0001\u0010ë\u0001R\u0019\u0010õ\u0001\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bõ\u0001\u0010ë\u0001R\u0019\u0010ö\u0001\u001a\u00020z8\u0002@\u0002X\u0082.¢\u0006\b\n\u0006\bö\u0001\u0010÷\u0001R\u001c\u0010ù\u0001\u001a\u0005\u0018\u00010ø\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bù\u0001\u0010ú\u0001R\u0019\u0010û\u0001\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bû\u0001\u0010ë\u0001R\u001b\u0010ü\u0001\u001a\u0004\u0018\u00010Q8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bü\u0001\u0010ý\u0001R\u0019\u0010þ\u0001\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bþ\u0001\u0010ë\u0001R0\u0010\u0081\u0002\u001a\u0005\u0018\u00010ÿ\u00012\n\u0010\u0080\u0002\u001a\u0005\u0018\u00010ÿ\u00018\u0002@BX\u0082\u000e¢\u0006\u0010\n\u0006\b\u0081\u0002\u0010\u0082\u0002\"\u0006\b\u0083\u0002\u0010\u0084\u0002R\u001c\u0010\u0085\u0002\u001a\u0005\u0018\u00010\u0091\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0085\u0002\u0010\u0086\u0002R\u001c\u0010\u0088\u0002\u001a\u0005\u0018\u00010\u0087\u00028\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0088\u0002\u0010\u0089\u0002R\u001c\u0010\u008b\u0002\u001a\u0005\u0018\u00010\u008a\u00028\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u008b\u0002\u0010\u008c\u0002R\u0018\u0010\u008e\u0002\u001a\u00030\u008d\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u008e\u0002\u0010\u008f\u0002R!\u0010\u0094\u0002\u001a\u00030\u0090\u00028BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\b\u0091\u0002\u0010î\u0001\u001a\u0006\b\u0092\u0002\u0010\u0093\u0002R\u0019\u0010\u0095\u0002\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0095\u0002\u0010ë\u0001R!\u0010\u009a\u0002\u001a\u00030\u0096\u00028BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\b\u0097\u0002\u0010î\u0001\u001a\u0006\b\u0098\u0002\u0010\u0099\u0002R\u0018\u0010\u009c\u0002\u001a\u00030\u009b\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u009c\u0002\u0010\u009d\u0002R$\u0010\u009f\u0002\u001a\u000f\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\r0\u009e\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u009f\u0002\u0010 \u0002R\u0018\u0010¢\u0002\u001a\u00030¡\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\b¢\u0002\u0010£\u0002R \u0010¥\u0002\u001a\u00030¤\u00028\u0014X\u0094\u0004¢\u0006\u0010\n\u0006\b¥\u0002\u0010¦\u0002\u001a\u0006\b§\u0002\u0010¨\u0002R\u0018\u0010ª\u0002\u001a\u00030©\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\bª\u0002\u0010«\u0002R\u0019\u0010¬\u0002\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b¬\u0002\u0010ë\u0001R\u0019\u0010\u00ad\u0002\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u00ad\u0002\u0010ë\u0001R*\u0010¯\u0002\u001a\u00030®\u00028\u0006@\u0006X\u0087.¢\u0006\u0018\n\u0006\b¯\u0002\u0010°\u0002\u001a\u0006\b±\u0002\u0010²\u0002\"\u0006\b³\u0002\u0010´\u0002R\u001c\u0010¶\u0002\u001a\u0005\u0018\u00010µ\u00028\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b¶\u0002\u0010·\u0002R\u001c\u0010¹\u0002\u001a\u0005\u0018\u00010¸\u00028\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b¹\u0002\u0010º\u0002R\u001c\u0010¼\u0002\u001a\u0005\u0018\u00010»\u00028\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b¼\u0002\u0010½\u0002R\u0017\u0010&\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b&\u0010ë\u0001R\u0019\u0010¾\u0002\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b¾\u0002\u0010ë\u0001R\u001c\u0010À\u0002\u001a\u0005\u0018\u00010¿\u00028\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÀ\u0002\u0010Á\u0002R.\u0010Â\u0002\u001a\u0004\u0018\u00010.2\t\u0010\u0080\u0002\u001a\u0004\u0018\u00010.8\u0002@BX\u0082\u000e¢\u0006\u0010\n\u0006\bÂ\u0002\u0010é\u0001\"\u0006\bÃ\u0002\u0010Ä\u0002R6\u0010Å\u0002\u001a\u0004\u0018\u00010\u00182\t\u0010\u0080\u0002\u001a\u0004\u0018\u00010\u00188\u0014@TX\u0094\u000e¢\u0006\u0018\n\u0006\bÅ\u0002\u0010Æ\u0002\u001a\u0006\bÇ\u0002\u0010È\u0002\"\u0006\bÉ\u0002\u0010Ê\u0002\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006Ï\u0002"}, d2 = {"Lcom/amazon/mp3/detailpages/playlist/PlaylistDetailFragment;", "Lcom/amazon/mp3/catalog/fragment/LibraryBaseViewsFragment;", "Lcom/amazon/mp3/fragment/headerbar/OverflowMenuReceiver;", "Lcom/amazon/mp3/actionbar/BaseViewsFilterActionBarManager$InfoProvider;", "Lcom/amazon/music/views/library/providers/RefinementCallback;", "Lcom/amazon/music/imageloader/ImageLoader$ImageLoaderCallback;", "Lcom/amazon/music/explore/managers/ExploreVideoStoryManager$PageHeaderUpdateCallback;", "Lcom/amazon/mp3/detailpages/album/click/CollectionTrackClickListener$CollectionInformationFetcher;", "Lcom/amazon/mp3/fragment/viewmodel/OnFilterChangedListener;", "Lcom/amazon/music/views/library/providers/EmptyStateActionProvider;", "Lcom/amazon/music/views/library/providers/ButtonActionProvider;", "Lcom/amazon/ui/foundations/views/BaseButton;", "button", "", "initEmptyViewResetFiltersButton", "Lcom/amazon/music/views/library/metadata/PlaylistMetadata;", "getPlaylistMetadata", "", "Lcom/amazon/mp3/view/refinements/RefinementFilterType;", "getDefaultSelectedRefinements", "Lcom/amazon/music/views/library/providers/FeaturedToggleListenerProvider;", "getFeaturedToggleListenerProvider", "Landroid/content/Context;", "context", "Landroid/net/Uri;", "uri", "getPlaylistLuidAsync", "(Landroid/content/Context;Landroid/net/Uri;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "emitUiPageViewMetricHelper", "metadata", "", "isShuffled", "emitUiClickMetric", "displayFilteredData", "", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "filteredTrackModels", "shouldShowEmptyView", "shouldRequestVideoStory", "addSongsToPlaylist", PageUriUtils.FILTER_QUERY_PARAM, "selected", "updateFilterSelected", "bindBackgroundImage", "initActionBarContextMenu", "setFilterButton", "", "selectionSourceId", "Lcom/amazon/music/metrics/mts/event/types/SelectionSourceType;", "selectionSourceType", "trackUri", "removeTrackFromPlaylist", "initializeActionBarManager", "", "Lcom/amazon/music/views/library/models/PageHeaderActionIconModel$ActionType;", "Lcom/amazon/music/views/library/binders/ActionIconOnClickListener;", "getPageHeaderOnClickListenerMap", "isLargeIcon", "onClickPlayback", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "", "trackCount", "startPlayback", "(Lcom/amazon/music/views/library/metadata/ContentMetadata;ZZLjava/lang/Integer;)V", "asin", "beginOnDemandGoldenPlaylistPlayback", "sharePlaylist", "playlistAsin", "startSFA", "followPlaylist", "isPlaylistDownloaded", "getPlaylistUriForQuery", "isPrivacyError", "showPlaylistNotFoundDialog", "dialogTitleStringId", "dialogDescriptionStringId", "confirmButtonStringId", "showErrorDialog", "playlistMetadata", "updateContentUriOnAsinChanged", "registerDownloadStateListener", "Lcom/amazon/mp3/playlist/CatalogPlaylist;", "getCatalogPlaylist", "getEntityId", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/EntityIdType;", "getEntityIdType", "handleDeeplink", "Landroidx/fragment/app/FragmentActivity;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "deleteOfflinePlaylist", "displayExplicitContentFilteredDialog", "shouldBlockPlaybackForVideos", "fetchSuggestionModels", "refreshTracks", "Landroid/view/LayoutInflater;", "inflater", "Landroid/view/ViewGroup;", "container", "getInflatedRootView", "getLoadingViewId", "onAttach", "Landroid/os/Bundle;", "savedInstanceState", "onCreate", "Landroid/view/View;", "onCreateView", "view", "onViewCreated", "onStart", "onPause", "onResume", "onStop", "onDestroy", "onDestroyView", "onActivityCreated", "initiateGetSuggestions", "getPageModel", "requestCode", "resultCode", "Landroid/content/Intent;", "data", "onActivityResult", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "getPageType", "Lcom/amazon/mp3/catalog/fragment/datasource/repository/PlaylistDetailDataRepository;", "getPageDataRepository", "initPageModelProvider", "Lcom/amazon/music/views/library/models/PageHeaderModel;", "getPageHeaderModel", "prevIndex", "curIndex", "updatePagination", "showStoryCoachMarkTutorial", "Lcom/amazon/music/views/library/recyclerview/BaseViewModelAdapterProvider;", "getAdapterProvider", "Lcom/amazon/music/views/library/binders/UniversalBinder;", "getBinderList", "Lcom/amazon/mp3/catalog/fragment/adapter/BaseViewsDiffUtilFactory;", "getDiffUtilFactory", "getEmptyLayoutID", "()Ljava/lang/Integer;", "emitUiPageViewMetric", "Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;", "getContentEnabilityProvider", "isDisplayingOnlyLibraryContent", "Lcom/amazon/mp3/actionbar/ActionBarProvider;", "provider", "setActionBarProvider", "actionBarHeight", "shouldDisplayPageComponentsInTopAppBar", "getArtworkUrl", "Lcom/amazon/music/views/library/models/FilterModel;", "getRefinementFilterModels", "getFilterButtonClickDestination", "getContentMetadata", "Lcom/amazon/music/views/library/models/ExposedRefinementListModel;", "getRefinementListModel", "Landroidx/recyclerview/widget/RecyclerView;", "recyclerView", "shouldDisplayFilterButton", "shouldDisplayFilterAndContextButtons", "shouldDisplayTitle", "shouldDisplayContextMenuOverflowButton", "doesHaveRefinement", "shouldRemoveDownloadForTracks", "isVideoTrack", "shouldRemoveVideoPlayForTracks", "shouldHideRefinementsOnLoad", "filterModel", "onChildFilterClick", "Lcom/amazon/mp3/providers/MissingSDCardHandler;", "getMissingSDCardProvider", "Lcom/amazon/music/views/library/models/RefinementPillModel;", "model", "onClick", "onFiltersReset", "Lcom/amazon/music/views/library/deeplinks/ContentMetadataOnClickListener;", "getMetadataClickListener", "Lcom/amazon/music/views/library/models/FilterCollectionModel;", "setupViews", "Lcom/amazon/music/views/library/models/ButtonType;", "buttonType", "onButtonClicked", "Lcom/amazon/music/views/library/models/EmptyStateButtonType;", "onEmptyStateButtonClicked", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "pageGridViewModel", "isInitialPage", "isMorePage", "updatePageModel", "Lcom/amazon/music/views/library/models/ErrorModel;", "errorModel", "updateOnError", "Lcom/amazon/mp3/providers/MetadataContextMenuHandler;", "getContextMenuProvider", "Lcom/amazon/music/views/library/providers/RowButtonOnClickProvider;", "getRowButtonOnClickProvider", "removeTrackFromUserPlaylist", "Landroid/graphics/Bitmap;", "originalBitmap", "onBitmapLoaded", "Landroid/graphics/drawable/Drawable;", "p0", "onPrepareLoad", "shouldSupportRemoveTrackFromPlaylist", "shouldSupportDeletePlaylist", "isCatalogPlaylist", "getPlaylistName", "isSourceLocal", "shouldBlockPlaybackAndDownload", "Lrx/Observable;", "Lcom/amazon/mp3/library/item/MusicTrack;", "getTrack", "getAllTracks", "getSortOrder", "getCollectionUri", "getCollectionId", "getCollectionName", "trackList", "getSelection", "", "getSelectionArgs", "(Ljava/util/List;)[Ljava/lang/String;", "shouldSendInitializingLatencyEvent", "Lcom/amazon/music/metrics/mts/event/types/pagelatencyinfo/PageLoadLatencyCode;", "getPageLoadLatencyCode", "getTrackList", "removeSuggestionAndUpdate", "onLoadUpdatedPage", "Lkotlin/ranges/IntRange;", "indicesChanged", "onLoadPageRangeChanged", "luid", "Ljava/lang/String;", "isFetchingSuggestions", "Z", "Lcom/amazon/mp3/detailpages/actions/PlaylistTrackInformationFetcher;", "trackInformationFetcher$delegate", "Lkotlin/Lazy;", "getTrackInformationFetcher", "()Lcom/amazon/mp3/detailpages/actions/PlaylistTrackInformationFetcher;", "trackInformationFetcher", "refinementCapability", "Ljava/lang/Boolean;", "isCatalog", "isEntity", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageSubType;", "pageSubType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageSubType;", "addToLibrary", "mCatalogPlaylist", "Lcom/amazon/mp3/playlist/CatalogPlaylist;", "startedFromPrimeBrowse", "Lcom/amazon/mp3/actionbar/BaseViewsFilterActionBarManager;", "value", "actionBarManager", "Lcom/amazon/mp3/actionbar/BaseViewsFilterActionBarManager;", "setActionBarManager", "(Lcom/amazon/mp3/actionbar/BaseViewsFilterActionBarManager;)V", "actionBarProvider", "Lcom/amazon/mp3/actionbar/ActionBarProvider;", "Lcom/amazon/mp3/catalog/fragment/viewmodel/PlaylistDetailViewModel;", "playlistDetailViewModel", "Lcom/amazon/mp3/catalog/fragment/viewmodel/PlaylistDetailViewModel;", "Lcom/amazon/mp3/catalog/fragment/viewmodel/BaseViewsRefinementManager;", "refinementsManager", "Lcom/amazon/mp3/catalog/fragment/viewmodel/BaseViewsRefinementManager;", "Lcom/amazon/mp3/view/refinements/DetailPageRefinementManager;", "detailPageRefinementManager", "Lcom/amazon/mp3/view/refinements/DetailPageRefinementManager;", "Lcom/amazon/mp3/baseviews/model/configuration/PlaylistModelConfiguration;", "configuration$delegate", "getConfiguration", "()Lcom/amazon/mp3/baseviews/model/configuration/PlaylistModelConfiguration;", "configuration", "shouldReobserve", "Lcom/amazon/mp3/catalog/fragment/PrimePlaylistDetailMetadataClickListener;", "primeMetadataOnClickListener$delegate", "getPrimeMetadataOnClickListener", "()Lcom/amazon/mp3/catalog/fragment/PrimePlaylistDetailMetadataClickListener;", "primeMetadataOnClickListener", "com/amazon/mp3/detailpages/playlist/PlaylistDetailFragment$playlistFollowActionCallback$1", "playlistFollowActionCallback", "Lcom/amazon/mp3/detailpages/playlist/PlaylistDetailFragment$playlistFollowActionCallback$1;", "Lkotlin/Function1;", "playlistUnfollowActionCallback", "Lkotlin/jvm/functions/Function1;", "Landroid/content/BroadcastReceiver;", "mUpsellBroadcastReceiver", "Landroid/content/BroadcastReceiver;", "Lcom/amazon/music/account/AccountStateChangeListener;", "mAccountStateChangeListener", "Lcom/amazon/music/account/AccountStateChangeListener;", "getMAccountStateChangeListener", "()Lcom/amazon/music/account/AccountStateChangeListener;", "Lcom/amazon/mp3/curate/provider/CurateFeatureFlagProvider;", "curateFeatureFlagProvider", "Lcom/amazon/mp3/curate/provider/CurateFeatureFlagProvider;", "isOnDemandGoldenPlaylist", "isVideoPlaylist", "Lcom/amazon/music/explore/managers/ExploreManagerFactory;", "exploreManagerFactory", "Lcom/amazon/music/explore/managers/ExploreManagerFactory;", "getExploreManagerFactory", "()Lcom/amazon/music/explore/managers/ExploreManagerFactory;", "setExploreManagerFactory", "(Lcom/amazon/music/explore/managers/ExploreManagerFactory;)V", "Lcom/amazon/music/explore/managers/ExploreVideoStoryManager;", "exploreVideoStoryManager", "Lcom/amazon/music/explore/managers/ExploreVideoStoryManager;", "Landroidx/viewpager/widget/ViewPager;", "viewPager", "Landroidx/viewpager/widget/ViewPager;", "Landroid/widget/ImageView;", "backgroundImageView", "Landroid/widget/ImageView;", "hasVideoStory", "Lcom/amazon/mp3/fragment/contextmenu/AbstractPlaylistContextMenuListener;", "playlistContextMenuListener", "Lcom/amazon/mp3/fragment/contextmenu/AbstractPlaylistContextMenuListener;", "playlistVersion", "setPlaylistVersion", "(Ljava/lang/String;)V", "contentUri", "Landroid/net/Uri;", "getContentUri", "()Landroid/net/Uri;", "setContentUri", "(Landroid/net/Uri;)V", "<init>", "()V", "Companion", "PlaybackDatabaseCallbackImpl", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class PlaylistDetailFragment extends LibraryBaseViewsFragment implements OverflowMenuReceiver, BaseViewsFilterActionBarManager.InfoProvider, RefinementCallback, ImageLoader.ImageLoaderCallback, ExploreVideoStoryManager.PageHeaderUpdateCallback, CollectionTrackClickListener.CollectionInformationFetcher, OnFilterChangedListener, EmptyStateActionProvider, ButtonActionProvider {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = PlaylistDetailFragment.class.getSimpleName();
    private BaseViewsFilterActionBarManager actionBarManager;
    private ActionBarProvider actionBarProvider;
    private boolean addToLibrary;
    private ImageView backgroundImageView;

    /* JADX INFO: renamed from: configuration$delegate, reason: from kotlin metadata */
    private final Lazy configuration;
    private Uri contentUri;
    private final CurateFeatureFlagProvider curateFeatureFlagProvider;
    private final DetailPageRefinementManager detailPageRefinementManager;
    public ExploreManagerFactory exploreManagerFactory;
    private ExploreVideoStoryManager exploreVideoStoryManager;
    private boolean hasVideoStory;
    private boolean isCatalog;
    private boolean isEntity;
    private boolean isFetchingSuggestions;
    private boolean isOnDemandGoldenPlaylist;
    private boolean isVideoPlaylist;
    private final AccountStateChangeListener mAccountStateChangeListener;
    private CatalogPlaylist mCatalogPlaylist;
    private final BroadcastReceiver mUpsellBroadcastReceiver;
    private PageSubType pageSubType;
    private PageType pageType;
    private AbstractPlaylistContextMenuListener playlistContextMenuListener;
    private PlaylistDetailViewModel playlistDetailViewModel;
    private final PlaylistDetailFragment$playlistFollowActionCallback$1 playlistFollowActionCallback;
    private final Function1<Boolean, Unit> playlistUnfollowActionCallback;
    private String playlistVersion;

    /* JADX INFO: renamed from: primeMetadataOnClickListener$delegate, reason: from kotlin metadata */
    private final Lazy primeMetadataOnClickListener;
    private Boolean refinementCapability;
    private BaseViewsRefinementManager refinementsManager;
    private boolean shouldReobserve;
    private boolean shouldRequestVideoStory;
    private boolean startedFromPrimeBrowse;
    private ViewPager viewPager;
    private String luid = "";

    /* JADX INFO: renamed from: trackInformationFetcher$delegate, reason: from kotlin metadata */
    private final Lazy trackInformationFetcher = LazyKt__LazyJVMKt.lazy(new Function0<PlaylistTrackInformationFetcher>() { // from class: com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment$trackInformationFetcher$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final PlaylistTrackInformationFetcher invoke() {
            return new PlaylistTrackInformationFetcher(new GenericBaseViewModelFetcher(new Function1<BaseViewModel, Boolean>() { // from class: com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment$trackInformationFetcher$2$visualRowModelFilter$1
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(BaseViewModel model) {
                    Intrinsics.checkNotNullParameter(model, "model");
                    return Boolean.valueOf(model instanceof VisualRowItemModel);
                }
            }), this.this$0.playlistDetailViewModel);
        }
    });

    /* JADX INFO: renamed from: com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment$getPlaylistLuidAsync$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PlaylistDetailFragment.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment", f = "PlaylistDetailFragment.kt", i = {}, l = {845}, m = "getPlaylistLuidAsync", n = {}, s = {})
    static final class C03511 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C03511(Continuation<? super C03511> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PlaylistDetailFragment.this.getPlaylistLuidAsync(null, null, this);
        }
    }

    private final boolean shouldRequestVideoStory() {
        return false;
    }

    @Override // com.amazon.mp3.catalog.fragment.LibraryBaseViewsFragment
    protected Integer getEmptyLayoutID() {
        return null;
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected int getLoadingViewId() {
        return R.id.detail_loading_spinner;
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

    @Override // com.amazon.music.imageloader.ImageLoader.ImageLoaderCallback
    public void onPrepareLoad(Drawable p0) {
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    public boolean shouldDisplayContextMenuOverflowButton() {
        return false;
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
    protected boolean shouldRemoveVideoPlayForTracks(boolean isVideoTrack) {
        return !isVideoTrack;
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected boolean shouldSendInitializingLatencyEvent() {
        return true;
    }

    /* JADX WARN: Type inference failed for: r0v9, types: [com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment$playlistFollowActionCallback$1] */
    public PlaylistDetailFragment() {
        setShouldUseDiffUtilForReorders(true);
        this.detailPageRefinementManager = new DetailPageRefinementManager(new Function0<Unit>() { // from class: com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment$detailPageRefinementManager$1
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
        this.configuration = LazyKt__LazyJVMKt.lazy(new Function0<PlaylistModelConfiguration>() { // from class: com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment$configuration$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final PlaylistModelConfiguration invoke() {
                return new PlaylistModelConfiguration(null, 1, null);
            }
        });
        this.primeMetadataOnClickListener = LazyKt__LazyJVMKt.lazy(new Function0<PrimePlaylistDetailMetadataClickListener>() { // from class: com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment$primeMetadataOnClickListener$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final PrimePlaylistDetailMetadataClickListener invoke() {
                PlaylistDetailFragment playlistDetailFragment = this.this$0;
                return new PrimePlaylistDetailMetadataClickListener(playlistDetailFragment, playlistDetailFragment.getPageType());
            }
        });
        this.playlistFollowActionCallback = new PlaylistFollowAction.Callback() { // from class: com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment$playlistFollowActionCallback$1
            @Override // com.amazon.mp3.detailpages.playlist.actions.PlaylistFollowAction.Callback
            public void onFollowPlaylistFailed(String message) {
            }

            @Override // com.amazon.mp3.detailpages.playlist.actions.PlaylistFollowAction.Callback
            public void onFollowPlaylistFinished(String luid) {
                PlaylistDetailViewModel playlistDetailViewModel = this.this$0.playlistDetailViewModel;
                if (playlistDetailViewModel != null) {
                    playlistDetailViewModel.updateFollowState(true);
                }
                FragmentActivity activity = this.this$0.getActivity();
                if (activity == null) {
                    return;
                }
                activity.invalidateOptionsMenu();
            }
        };
        this.playlistUnfollowActionCallback = new Function1<Boolean, Unit>() { // from class: com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment$playlistUnfollowActionCallback$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke(bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(boolean z) {
                FragmentManager supportFragmentManager;
                if (z) {
                    if (!this.this$0.isSourceLocal()) {
                        PlaylistDetailViewModel playlistDetailViewModel = this.this$0.playlistDetailViewModel;
                        if (playlistDetailViewModel != null) {
                            playlistDetailViewModel.updateFollowState(false);
                        }
                        FragmentActivity activity = this.this$0.getActivity();
                        if (activity != null) {
                            activity.invalidateOptionsMenu();
                        }
                        PageViewModelProvider pageModelProvider = this.this$0.getPageModelProvider();
                        if (pageModelProvider == null) {
                            return;
                        }
                        PageViewModelProvider.DefaultImpls.fetchUpdates$default(pageModelProvider, 20, this.this$0.getDiffUtilFactory(), null, 4, null);
                        return;
                    }
                    FragmentActivity activity2 = this.this$0.getActivity();
                    if (activity2 == null || (supportFragmentManager = activity2.getSupportFragmentManager()) == null) {
                        return;
                    }
                    supportFragmentManager.popBackStack();
                }
            }
        };
        this.mUpsellBroadcastReceiver = new BroadcastReceiver() { // from class: com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment$mUpsellBroadcastReceiver$1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                Intrinsics.checkNotNullParameter(context, "context");
                Intrinsics.checkNotNullParameter(intent, "intent");
                if (this.this$0.getActivity() == null || !Intrinsics.areEqual(intent.getAction(), "com.amazon.mp3.upsell.skip_limit_upsell")) {
                    return;
                }
                Bundle extras = intent.getExtras();
                UpsellUtil.showBlockingUpsell(this.this$0.getActivity(), extras == null ? null : (UpsellInformation) extras.getParcelable("com.amazon.music.playback.EXTRA_SHOW_UNLIMITED_UPSELL"));
            }
        };
        this.mAccountStateChangeListener = new AccountStateChangeListener() { // from class: com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment$$ExternalSyntheticLambda0
            @Override // com.amazon.music.account.AccountStateChangeListener
            public final void onAccountStateChange(Set set) {
                PlaylistDetailFragment.m845mAccountStateChangeListener$lambda0(this.f$0, set);
            }
        };
        this.curateFeatureFlagProvider = new CurateFeatureFlagProvider();
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

    @Override // com.amazon.mp3.fragment.viewmodel.OnFilterChangedListener
    public boolean shouldOverrideResetFilters() {
        return OnFilterChangedListener.DefaultImpls.shouldOverrideResetFilters(this);
    }

    @Override // com.amazon.mp3.fragment.viewmodel.OnFilterChangedListener
    public boolean shouldOverrideSelectionChange(String str, boolean z) {
        return OnFilterChangedListener.DefaultImpls.shouldOverrideSelectionChange(this, str, z);
    }

    private final PlaylistTrackInformationFetcher getTrackInformationFetcher() {
        return (PlaylistTrackInformationFetcher) this.trackInformationFetcher.getValue();
    }

    /* JADX INFO: compiled from: PlaylistDetailFragment.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J/\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0017¢\u0006\u0002\u0010\u001bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0016\u0010\u0010\u001a\n \u0011*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/amazon/mp3/detailpages/playlist/PlaylistDetailFragment$Companion;", "", "()V", "ADD_TO_LIBRARY", "", "BACKGROUND_ART_LOADING_DURATION", "", "DEFAULT_START_POSITION", "", "EXTRA_IS_VIDEO_PLAYLIST", "IS_CATALOG", "PAGE_SUB_TYPE", Contexts.OpenAlexa.pageType, "PLAYLIST_ASIN", "SHOW_STORY_COACH_MARK_TUTORIAL", "STORY_ASIN", "TAG", "kotlin.jvm.PlatformType", "newInstance", "Lcom/amazon/mp3/detailpages/playlist/PlaylistDetailFragment;", "dependencies", "Lcom/amazon/mp3/detailpages/playlist/PlaylistDetailDependencies;", "addToLibrary", "", "bundle", "Landroid/os/Bundle;", "isOnDemandPlaylist", "(Lcom/amazon/mp3/detailpages/playlist/PlaylistDetailDependencies;ZLandroid/os/Bundle;Ljava/lang/Boolean;)Lcom/amazon/mp3/detailpages/playlist/PlaylistDetailFragment;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public static /* synthetic */ PlaylistDetailFragment newInstance$default(Companion companion, PlaylistDetailDependencies playlistDetailDependencies, boolean z, Bundle bundle, Boolean bool, int i, Object obj) {
            if ((i & 8) != 0) {
                bool = null;
            }
            return companion.newInstance(playlistDetailDependencies, z, bundle, bool);
        }

        public final PlaylistDetailFragment newInstance(PlaylistDetailDependencies dependencies, boolean addToLibrary, Bundle bundle, Boolean isOnDemandPlaylist) {
            Intrinsics.checkNotNullParameter(dependencies, "dependencies");
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            PlaylistDetailFragment playlistDetailFragment = new PlaylistDetailFragment();
            bundle.putSerializable(Contexts.OpenAlexa.pageType, dependencies.pageType());
            bundle.putSerializable("PAGE_SUB_TYPE", dependencies.pageSubType(isOnDemandPlaylist));
            bundle.putBoolean("IS_CATALOG", dependencies.isCatalog());
            bundle.putBoolean("ADD_TO_LIBRARY", addToLibrary);
            playlistDetailFragment.setArguments(bundle);
            return playlistDetailFragment;
        }
    }

    private final void setActionBarManager(BaseViewsFilterActionBarManager baseViewsFilterActionBarManager) {
        if (!Intrinsics.areEqual(this.actionBarManager, baseViewsFilterActionBarManager)) {
            this.actionBarManager = baseViewsFilterActionBarManager;
        }
        if (Intrinsics.areEqual(this.detailPageRefinementManager.getActionBarManager(), baseViewsFilterActionBarManager)) {
            return;
        }
        this.detailPageRefinementManager.setActionBarManager(baseViewsFilterActionBarManager);
    }

    private final PlaylistModelConfiguration getConfiguration() {
        return (PlaylistModelConfiguration) this.configuration.getValue();
    }

    private final PrimePlaylistDetailMetadataClickListener getPrimeMetadataOnClickListener() {
        return (PrimePlaylistDetailMetadataClickListener) this.primeMetadataOnClickListener.getValue();
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected AccountStateChangeListener getMAccountStateChangeListener() {
        return this.mAccountStateChangeListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: mAccountStateChangeListener$lambda-0, reason: not valid java name */
    public static final void m845mAccountStateChangeListener$lambda0(PlaylistDetailFragment this$0, Set set) {
        PageViewModelProvider pageModelProvider;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        super.getMAccountStateChangeListener();
        if (!set.contains(AccountStateField.EXPLICIT_FILTER_ENABLED_ON_DEVICE) || (pageModelProvider = this$0.getPageModelProvider()) == null) {
            return;
        }
        PageViewModelProvider.DefaultImpls.fetchUpdates$default(pageModelProvider, 20, this$0.getDiffUtilFactory(), null, 4, null);
    }

    public final ExploreManagerFactory getExploreManagerFactory() {
        ExploreManagerFactory exploreManagerFactory = this.exploreManagerFactory;
        if (exploreManagerFactory != null) {
            return exploreManagerFactory;
        }
        Intrinsics.throwUninitializedPropertyAccessException("exploreManagerFactory");
        return null;
    }

    private final void setPlaylistVersion(String str) {
        if (Intrinsics.areEqual(this.playlistVersion, str)) {
            return;
        }
        this.playlistVersion = str;
        bindBackgroundImage();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.mp3.catalog.fragment.LibraryBaseViewsFragment
    public Uri getContentUri() {
        return this.contentUri;
    }

    @Override // com.amazon.mp3.catalog.fragment.LibraryBaseViewsFragment
    protected void setContentUri(Uri uri) {
        if (!this.isCatalog) {
            uri = ContentPlaybackUtils.INSTANCE.appendTracksIfNeeded(uri);
        }
        this.contentUri = uri;
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

    @Override // com.amazon.mp3.catalog.fragment.LibraryBaseViewsFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
        final FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        this.playlistContextMenuListener = new AbstractPlaylistContextMenuListener(this) { // from class: com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment$onAttach$1$1
            final /* synthetic */ PlaylistDetailFragment this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.$it);
                this.this$0 = this;
                Intrinsics.checkNotNullExpressionValue(this.$it, "it");
            }

            @Override // com.amazon.mp3.fragment.contextmenu.PlaylistContextMenuProvider.PlaylistContextMenuProviderListener
            public void removeDeletePlaylistMenuItem(ContextMenu menu) {
                if (menu != null) {
                    menu.removeItem(R.id.MenuPlaylistContextDeleteDevice);
                }
                if (!this.this$0.shouldSupportDeletePlaylist() && menu != null) {
                    menu.removeItem(R.id.MenuPlaylistDelete);
                }
                if (this.this$0.isCatalog) {
                    return;
                }
                if (menu != null) {
                    menu.removeItem(R.id.MenuPlaylistDelete);
                }
                if (!this.this$0.isSourceLocal() || menu == null) {
                    return;
                }
                menu.removeItem(R.id.MenuPlaylistContextDelete);
            }

            @Override // com.amazon.mp3.fragment.contextmenu.PlaylistContextMenuProvider.PlaylistContextMenuProviderListener
            public void removeDownloadPlaylistMenuItem(ContextMenu menu) {
                if (menu == null) {
                    return;
                }
                menu.removeItem(R.id.MenuPlaylistContextDownload);
            }

            @Override // com.amazon.mp3.fragment.contextmenu.PlaylistContextMenuProvider.PlaylistContextMenuProviderListener
            public ContextMenuPageType getContextMenuPageType() {
                return ContextMenuPageType.DETAIL;
            }

            @Override // com.amazon.mp3.dialog.RenamePlaylistDialog.OnPlaylistRenamedListener
            public void onRenameCompleted(String title, Playlist playlist) {
                Intrinsics.checkNotNullParameter(title, "title");
                Intrinsics.checkNotNullParameter(playlist, "playlist");
                PageHeaderModel pageHeaderModel = this.this$0.getPageHeaderModel();
                if (pageHeaderModel == null) {
                    return;
                }
                pageHeaderModel.updateTitle(title);
            }

            @Override // com.amazon.mp3.fragment.contextmenu.PlaylistContextMenuProvider.PlaylistContextMenuProviderListener
            public void deleteContent(int position, AbstractItem item) {
                Intrinsics.checkNotNullParameter(item, "item");
                if (item instanceof Playlist) {
                    FragmentActivity it = this.$it;
                    Intrinsics.checkNotNullExpressionValue(it, "it");
                    Playlist playlist = (Playlist) item;
                    Uri contentUri = playlist.getContentUri();
                    Intrinsics.checkNotNullExpressionValue(contentUri, "item.contentUri");
                    String name = playlist.getName();
                    Intrinsics.checkNotNullExpressionValue(name, "item.name");
                    DeleteUserPlaylistAction deleteUserPlaylistAction = new DeleteUserPlaylistAction(it, contentUri, name);
                    final PlaylistDetailFragment playlistDetailFragment = this.this$0;
                    deleteUserPlaylistAction.perform(new Function0<Unit>() { // from class: com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment$onAttach$1$1$deleteContent$1
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
                            FragmentManager supportFragmentManager;
                            FragmentActivity activity2 = playlistDetailFragment.getActivity();
                            if (activity2 == null || (supportFragmentManager = activity2.getSupportFragmentManager()) == null) {
                                return;
                            }
                            supportFragmentManager.popBackStack();
                        }
                    });
                    return;
                }
                if (item instanceof CatalogPlaylist) {
                    FragmentActivity it2 = this.$it;
                    Intrinsics.checkNotNullExpressionValue(it2, "it");
                    CatalogPlaylist catalogPlaylist = (CatalogPlaylist) item;
                    new PlaylistUnfollowAction(it2, catalogPlaylist.getAsin(), catalogPlaylist.getLuid(), CirrusMediaSource.matchCloud(this.this$0.getContentUri())).unfollowPlaylist(this.this$0.playlistUnfollowActionCallback);
                }
            }
        };
    }

    @Override // com.amazon.mp3.catalog.fragment.LibraryBaseViewsFragment, com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        this.isCatalog = arguments == null ? false : arguments.getBoolean("IS_CATALOG");
        Serializable serializable = arguments == null ? null : arguments.getSerializable(Contexts.OpenAlexa.pageType);
        if (serializable == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.metrics.mts.event.types.uiinteraction.PageType");
        }
        this.pageType = (PageType) serializable;
        this.pageSubType = (PageSubType) arguments.getSerializable("PAGE_SUB_TYPE");
        this.addToLibrary = arguments.getBoolean("ADD_TO_LIBRARY");
        this.isEntity = arguments.getBoolean("EXTRA_IS_ENTITY");
        this.isVideoPlaylist = Boolean.valueOf(arguments.getBoolean("com.amazon.mp3.library.EXTRA_IS_VIDEO", false)).booleanValue();
        super.onCreate(savedInstanceState);
        this.startedFromPrimeBrowse = arguments.getBoolean("com.amazon.mp3.EXTRA_ALWAYS_SHOW_NON_LIB_CONTENT", false);
        getSubscriptions().add(StyleSheetProvider.getStyleSheetObservable().subscribe(new Action1() { // from class: com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment$$ExternalSyntheticLambda4
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                PlaylistDetailFragment.m848onCreate$lambda2(this.f$0, (StyleSheet) obj);
            }
        }));
        getLifecycle().addObserver(this.detailPageRefinementManager);
        boolean zShouldRequestVideoStory = shouldRequestVideoStory();
        this.shouldRequestVideoStory = zShouldRequestVideoStory;
        if (zShouldRequestVideoStory) {
            ExploreVideoStoryManager exploreVideoStoryManagerCreateVideoStoryManager = getExploreManagerFactory().createVideoStoryManager(this);
            this.exploreVideoStoryManager = exploreVideoStoryManagerCreateVideoStoryManager;
            if (exploreVideoStoryManagerCreateVideoStoryManager == null) {
                return;
            }
            exploreVideoStoryManagerCreateVideoStoryManager.requestVideoBackgroundMethod(MediaProvider.Playlists.getCatalogPlaylistId(getPlaylistUriForQuery()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreate$lambda-2, reason: not valid java name */
    public static final void m848onCreate$lambda2(PlaylistDetailFragment this$0, StyleSheet it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DetailPageRefinementManager detailPageRefinementManager = this$0.detailPageRefinementManager;
        Intrinsics.checkNotNullExpressionValue(it, "it");
        detailPageRefinementManager.initializeOverflowManager(this$0, it);
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        super.onCreateView(inflater, container, savedInstanceState);
        if (this.shouldRequestVideoStory) {
            ViewGroup root = getRoot();
            ViewPager viewPager = root == null ? null : (ViewPager) root.findViewById(R.id.video_preview_pager);
            if (viewPager == null) {
                throw new NullPointerException("null cannot be cast to non-null type androidx.viewpager.widget.ViewPager");
            }
            this.viewPager = viewPager;
            ExploreVideoStoryManager exploreVideoStoryManager = this.exploreVideoStoryManager;
            if (exploreVideoStoryManager != null) {
                exploreVideoStoryManager.initVideoBackground(this, viewPager, getChildFragmentManager());
            }
        }
        ViewGroup root2 = getRoot();
        this.backgroundImageView = root2 != null ? (ImageView) root2.findViewById(R.id.background_image_view) : null;
        return getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        String string;
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), null, new C03531(null), 2, null);
        ViewGroup root = getRoot();
        BaseButton baseButton = root == null ? null : (BaseButton) root.findViewById(R.id.resetFilterButton);
        if (baseButton != null) {
            initEmptyViewResetFiltersButton(baseButton);
        }
        Bundle arguments = getArguments();
        if (arguments == null || (string = arguments.getString("STORY_ASIN")) == null) {
            return;
        }
        FragmentActivity fragmentActivityRequireActivity = requireActivity();
        Bundle arguments2 = getArguments();
        ExploreFactory.openVideoStoriesPage(fragmentActivityRequireActivity, arguments2 != null ? arguments2.getString("PLAYLIST_ASIN") : null, string);
    }

    /* JADX INFO: renamed from: com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment$onViewCreated$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PlaylistDetailFragment.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment$onViewCreated$1", f = "PlaylistDetailFragment.kt", i = {}, l = {490}, m = "invokeSuspend", n = {}, s = {})
    static final class C03531 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C03531(Continuation<? super C03531> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PlaylistDetailFragment.this.new C03531(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03531) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    PlaylistDetailFragment playlistDetailFragment = PlaylistDetailFragment.this;
                    Context context = playlistDetailFragment.getContext();
                    Uri contentUri = PlaylistDetailFragment.this.getContentUri();
                    this.label = 1;
                    if (playlistDetailFragment.getPlaylistLuidAsync(context, contentUri, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
            } catch (Exception e) {
                Log.error(PlaylistDetailFragment.TAG, "Error fetching the LUID", e);
            }
            return Unit.INSTANCE;
        }
    }

    private final void initEmptyViewResetFiltersButton(final BaseButton button) {
        MutableLiveData<StyleSheet> styleSheet = StyleSheetProvider.getStyleSheet();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "viewLifecycleOwner");
        styleSheet.observe(viewLifecycleOwner, new Observer() { // from class: com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment$initEmptyViewResetFiltersButton$$inlined$observe$1
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
        button.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment$$ExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PlaylistDetailFragment.m843initEmptyViewResetFiltersButton$lambda6(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initEmptyViewResetFiltersButton$lambda-6, reason: not valid java name */
    public static final void m843initEmptyViewResetFiltersButton$lambda6(PlaylistDetailFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DetailPageRefinementManager detailPageRefinementManager = this$0.detailPageRefinementManager;
        if (detailPageRefinementManager == null) {
            return;
        }
        detailPageRefinementManager.onFiltersReset();
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment, androidx.fragment.app.Fragment
    public void onStart() {
        PlaylistDetailViewModel playlistDetailViewModel = this.playlistDetailViewModel;
        if (playlistDetailViewModel != null) {
            playlistDetailViewModel.setDownloadChangedListener(this);
        }
        super.onStart();
        registerDownloadStateListener();
    }

    @Override // com.amazon.mp3.catalog.fragment.LibraryBaseViewsFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        PlaylistDetailViewModel playlistDetailViewModel = this.playlistDetailViewModel;
        if (playlistDetailViewModel != null) {
            playlistDetailViewModel.unregisterDownloadStateListener();
        }
        Context context = getContext();
        if (context == null) {
            return;
        }
        context.unregisterReceiver(this.mUpsellBroadcastReceiver);
    }

    private final PlaylistMetadata getPlaylistMetadata() {
        PageHeaderModel pageHeaderModel = getPageHeaderModel();
        ContentMetadata metadata = pageHeaderModel == null ? null : pageHeaderModel.getMetadata();
        if (metadata instanceof PlaylistMetadata) {
            return (PlaylistMetadata) metadata;
        }
        return null;
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment, androidx.fragment.app.Fragment
    public void onResume() {
        ExploreVideoStoryManager exploreVideoStoryManager;
        super.onResume();
        if (this.shouldReobserve) {
            initLibraryStateObserver();
            this.shouldReobserve = false;
        }
        if (this.shouldRequestVideoStory && (exploreVideoStoryManager = this.exploreVideoStoryManager) != null) {
            exploreVideoStoryManager.startPlayback();
        }
        registerDownloadStateListener();
        Context context = getContext();
        if (context != null) {
            context.registerReceiver(this.mUpsellBroadcastReceiver, new IntentFilter("com.amazon.mp3.upsell.skip_limit_upsell"));
        }
        boolean zIsSmartPlaylist = MediaProvider.isSmartPlaylist(getContentUri());
        if (!AmazonApplication.getCapabilities().isPlaylistSongSearchEnabled() || this.isCatalog || zIsSmartPlaylist) {
            return;
        }
        refreshTracks();
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        PlaylistDetailViewModel playlistDetailViewModel = this.playlistDetailViewModel;
        if (playlistDetailViewModel == null) {
            return;
        }
        playlistDetailViewModel.setDownloadChangedListener(null);
    }

    @Override // com.amazon.mp3.catalog.fragment.LibraryBaseViewsFragment, com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        if (this.shouldRequestVideoStory) {
            ExploreVideoStoryManager exploreVideoStoryManager = this.exploreVideoStoryManager;
            if (exploreVideoStoryManager != null) {
                exploreVideoStoryManager.shutdown();
            }
            FragmentActivity activity = getActivity();
            if (activity != null) {
                activity.setRequestedOrientation(-1);
            }
        }
        getLifecycle().removeObserver(this.detailPageRefinementManager);
        BaseViewsFilterActionBarManager baseViewsFilterActionBarManager = this.actionBarManager;
        if (baseViewsFilterActionBarManager != null) {
            getLifecycle().removeObserver(baseViewsFilterActionBarManager);
        }
        if (getContextMenuHandler() instanceof LibraryContextMenuProvider) {
            Lifecycle lifecycle = getLifecycle();
            MetadataContextMenuHandler contextMenuHandler = getContextMenuHandler();
            if (contextMenuHandler == null) {
                throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.providers.LibraryContextMenuProvider");
            }
            lifecycle.removeObserver((LibraryContextMenuProvider) contextMenuHandler);
        }
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        ViewPropertyAnimator viewPropertyAnimatorAnimate;
        super.onDestroyView();
        ImageView imageView = this.backgroundImageView;
        if (imageView == null || (viewPropertyAnimatorAnimate = imageView.animate()) == null) {
            return;
        }
        viewPropertyAnimatorAnimate.cancel();
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initiateGetSuggestions();
        this.detailPageRefinementManager.setAdditionalCallbackHandler(this);
    }

    public final void initiateGetSuggestions() {
        CuratePlaylistProvider curatePlaylistProvider;
        MutableLiveData<List<BaseViewModel>> suggestions;
        List<String> pathSegments;
        String str;
        if (MediaProvider.isSmartPlaylist(getContentUri())) {
            Uri contentUri = getContentUri();
            Long lValueOf = null;
            if (contentUri != null && (pathSegments = contentUri.getPathSegments()) != null && (str = pathSegments.get(4)) != null) {
                try {
                    lValueOf = Long.valueOf(Long.parseLong(str));
                } catch (NumberFormatException unused) {
                }
            }
            if (lValueOf != null && lValueOf.longValue() == 3) {
                return;
            }
        }
        if (!AmazonApplication.getCapabilities().isPlaylistSongRecommendationWidgetEnabled() || isSourceLocal() || getIsCatalog()) {
            return;
        }
        fetchSuggestionModels();
        Observer<? super List<BaseViewModel>> observer = new Observer() { // from class: com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment$$ExternalSyntheticLambda5
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                PlaylistDetailFragment.m844initiateGetSuggestions$lambda10(this.f$0, (List) obj);
            }
        };
        PlaylistDetailViewModel playlistDetailViewModel = this.playlistDetailViewModel;
        if (playlistDetailViewModel == null || (curatePlaylistProvider = playlistDetailViewModel.getCuratePlaylistProvider()) == null || (suggestions = curatePlaylistProvider.getSuggestions()) == null) {
            return;
        }
        suggestions.observe(getViewLifecycleOwner(), observer);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initiateGetSuggestions$lambda-10, reason: not valid java name */
    public static final void m844initiateGetSuggestions$lambda10(PlaylistDetailFragment this$0, List list) {
        CuratePlaylistProvider curatePlaylistProvider;
        MutableLiveData<List<BaseViewModel>> suggestions;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        PlaylistDetailViewModel playlistDetailViewModel = this$0.playlistDetailViewModel;
        if (playlistDetailViewModel != null && playlistDetailViewModel.getCuratePlaylistProvider() != null) {
            BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this$0), Dispatchers.getDefault(), null, new PlaylistDetailFragment$initiateGetSuggestions$suggestionsObserver$1$1$1(this$0, null), 2, null);
        }
        PlaylistDetailViewModel playlistDetailViewModel2 = this$0.playlistDetailViewModel;
        if (playlistDetailViewModel2 == null || (curatePlaylistProvider = playlistDetailViewModel2.getCuratePlaylistProvider()) == null || (suggestions = curatePlaylistProvider.getSuggestions()) == null) {
            return;
        }
        suggestions.removeObservers(this$0.getViewLifecycleOwner());
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    /* JADX INFO: renamed from: getPageModel */
    public void mo456getPageModel() {
        SingleLiveEvent<Boolean> singleLiveEventOnModelsReady;
        if (this.shouldRequestVideoStory) {
            ExploreVideoStoryManager exploreVideoStoryManager = this.exploreVideoStoryManager;
            if (exploreVideoStoryManager == null || (singleLiveEventOnModelsReady = exploreVideoStoryManager.onModelsReady()) == null) {
                return;
            }
            singleLiveEventOnModelsReady.observe(this, new Observer() { // from class: com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment$$ExternalSyntheticLambda3
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    PlaylistDetailFragment.m842getPageModel$lambda11(this.f$0, (Boolean) obj);
                }
            });
            return;
        }
        super.mo456getPageModel();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getPageModel$lambda-11, reason: not valid java name */
    public static final void m842getPageModel$lambda11(PlaylistDetailFragment this$0, Boolean bool) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ExploreVideoStoryManager exploreVideoStoryManager = this$0.exploreVideoStoryManager;
        boolean z = false;
        if (exploreVideoStoryManager != null && exploreVideoStoryManager.getHasVideoStory()) {
            z = true;
        }
        this$0.hasVideoStory = z;
        super.mo456getPageModel();
        if (this$0.hasVideoStory) {
            FragmentActivity activity = this$0.getActivity();
            if (activity == null) {
                return;
            }
            activity.setRequestedOrientation(1);
            return;
        }
        ExploreVideoStoryManager exploreVideoStoryManager2 = this$0.exploreVideoStoryManager;
        if (exploreVideoStoryManager2 == null) {
            return;
        }
        exploreVideoStoryManager2.shutdown();
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        PageViewModelProvider pageModelProvider;
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == AddToPlaylistTabActivity.REQUEST_CODE || requestCode == AddToPlaylistActivity.REQUEST_CODE || requestCode == SavePlaylistActivity.REQUEST_CODE) && resultCode == -1 && (pageModelProvider = getPageModelProvider()) != null) {
            PageViewModelProvider.DefaultImpls.fetchUpdates$default(pageModelProvider, 20, getDiffUtilFactory(), null, 4, null);
        }
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public PageType getPageType() {
        PageType pageType = this.pageType;
        if (pageType != null) {
            return pageType;
        }
        Intrinsics.throwUninitializedPropertyAccessException("pageType");
        return null;
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public PlaylistDetailDataRepository getPageDataRepository() {
        Uri playlistUriForQuery = getPlaylistUriForQuery();
        Context context = AmazonApplication.getApplication().getApplicationContext();
        Context context2 = null;
        byte b = 0;
        if (playlistUriForQuery == null) {
            return null;
        }
        Intrinsics.checkNotNullExpressionValue(context, "context");
        return new PlaylistDetailDataRepository(context, playlistUriForQuery, this.isCatalog, Boolean.valueOf(this.addToLibrary), getConfiguration(), new PlaylistDetailDao(context, playlistUriForQuery, this.isCatalog, isSourceLocal(), Boolean.valueOf(this.hasVideoStory), getPageLoadLatencyCode(), null, this.isVideoPlaylist, new PlaylistModelConfiguration(new CachedCapabilityActionProviderImpl(context2, 1, b == true ? 1 : 0)), 64, null));
    }

    @Override // com.amazon.mp3.catalog.fragment.LibraryBaseViewsFragment, com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public void initPageModelProvider(FragmentActivity activity) {
        PlaylistDetailViewModel playlistDetailViewModel;
        Intrinsics.checkNotNullParameter(activity, "activity");
        PlaylistDetailDataRepository pageDataRepository = getPageDataRepository();
        if (pageDataRepository != null) {
            PageViewModelFactoryProvider pageViewModelFactoryProvider = PageViewModelFactoryProvider.INSTANCE;
            Application application = activity.getApplication();
            Intrinsics.checkNotNullExpressionValue(application, "activity.application");
            setPageModelProvider(pageViewModelFactoryProvider.getViewModel(this, new PageViewModelFactoryProvider.PlaylistDetailViewModelFactory(application, pageDataRepository, this.isCatalog, this.isVideoPlaylist, getContentUri())));
            registerDownloadStateListener();
            ContentEnabilityProvider contentEnabilityProvider = getContentEnabilityProvider();
            if (contentEnabilityProvider != null && (playlistDetailViewModel = this.playlistDetailViewModel) != null) {
                playlistDetailViewModel.setContentEnabilityProvider(contentEnabilityProvider);
            }
            getTrackInformationFetcher().setPlaylistTrackFetcher(this.playlistDetailViewModel);
        }
        if (getPageModelProvider() instanceof BaseViewsRefinementManager.Provider) {
            PageViewModelProvider pageModelProvider = getPageModelProvider();
            if (pageModelProvider == null) {
                throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.catalog.fragment.viewmodel.BaseViewsRefinementManager.Provider");
            }
            BaseViewsRefinementManager refinementsManager = ((BaseViewsRefinementManager.Provider) pageModelProvider).getRefinementManager();
            this.refinementsManager = refinementsManager;
            this.detailPageRefinementManager.setRefinementsManager(refinementsManager);
            return;
        }
        Log.error(TAG, "pageModelProvider is not an instance of BaseViewsRefinementManager.Provider. No refinement manager provided. Refinements disabled");
    }

    private final Set<RefinementFilterType> getDefaultSelectedRefinements() {
        Bundle arguments = getArguments();
        if (arguments == null) {
            return null;
        }
        return RefinementFilterType.Companion.getEnabledRefinementFilters$default(RefinementFilterType.INSTANCE, arguments, null, 2, null);
    }

    public final PageHeaderModel getPageHeaderModel() {
        PlaylistDetailViewModel playlistDetailViewModel = this.playlistDetailViewModel;
        if (playlistDetailViewModel == null) {
            return null;
        }
        return PlaylistDetailViewModel.getPageHeaderModel$default(playlistDetailViewModel, null, 1, null);
    }

    @Override // com.amazon.music.explore.managers.ExploreVideoStoryManager.PageHeaderUpdateCallback
    public void updatePagination(int prevIndex, int curIndex) {
        PageHeaderModel pageHeaderModel = getPageHeaderModel();
        if (pageHeaderModel == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.models.StoryPageHeaderModel");
        }
        ((StoryPageHeaderModel) pageHeaderModel).updatePagination(prevIndex, curIndex);
    }

    @Override // com.amazon.music.explore.managers.ExploreVideoStoryManager.PageHeaderUpdateCallback
    public void showStoryCoachMarkTutorial() {
        final SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        if (defaultSharedPreferences.getBoolean("show_story_coach_mark_tutorial", true)) {
            StyleSheetProvider.getStyleSheet().observe(this, new Observer() { // from class: com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment$$ExternalSyntheticLambda7
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    PlaylistDetailFragment.m851showStoryCoachMarkTutorial$lambda15(this.f$0, defaultSharedPreferences, (StyleSheet) obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showStoryCoachMarkTutorial$lambda-15, reason: not valid java name */
    public static final void m851showStoryCoachMarkTutorial$lambda15(PlaylistDetailFragment this$0, SharedPreferences sharedPreferences, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Context context = this$0.getContext();
        Intrinsics.checkNotNullExpressionValue(styleSheet, "styleSheet");
        CoachMarkTutorialView coachMarkTutorialView = new CoachMarkTutorialView(context, styleSheet);
        coachMarkTutorialView.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        FragmentActivity activity = this$0.getActivity();
        ViewGroup viewGroup = activity == null ? null : (ViewGroup) activity.findViewById(android.R.id.content);
        if (viewGroup == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup");
        }
        viewGroup.addView(coachMarkTutorialView);
        SharedPreferences.Editor editorEdit = sharedPreferences != null ? sharedPreferences.edit() : null;
        if (editorEdit != null) {
            editorEdit.putBoolean("show_story_coach_mark_tutorial", false);
        }
        if (editorEdit == null) {
            return;
        }
        editorEdit.apply();
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected BaseViewModelAdapterProvider getAdapterProvider() {
        if (FMPMFeatureGating.SHOULD_HIDE_TRACKLIST.isEnabled() || FMPMFeatureGating.SHOULD_REMOVE_TRACKLIST.isEnabled()) {
            return new HideableBaseViewModelAdapterProvider(this, this.playlistDetailViewModel, LifecycleOwnerKt.getLifecycleScope(this), useOptimizedAdapterObservable());
        }
        return super.getAdapterProvider();
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public List<UniversalBinder<?, ?>> getBinderList() {
        BaseViewsBinderConfiguration viewsConfiguration = getViewsConfiguration();
        if (viewsConfiguration != null) {
            Function3<String, String, Integer, Unit> function3 = new Function3<String, String, Integer, Unit>() { // from class: com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment$getBinderList$1$onClickCallback$1
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(String str, String str2, Integer num) {
                    invoke(str, str2, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(String str, String str2, int i) {
                    ActionType actionType = this.this$0.isVideoPlaylist ? ActionType.PLAY_VIDEO : ActionType.PLAY_SONG;
                    if (str != null) {
                        PlaylistDetailFragment playlistDetailFragment = this.this$0;
                        AbstractBaseViewsFragment.sendUiClickMetric$default(playlistDetailFragment, actionType, null, playlistDetailFragment.getPageType(), null, str, EntityIdType.ASIN, null, Integer.valueOf(i), null, null, null, null, 3914, null);
                    } else {
                        PlaylistDetailFragment playlistDetailFragment2 = this.this$0;
                        AbstractBaseViewsFragment.sendUiClickMetric$default(playlistDetailFragment2, actionType, null, playlistDetailFragment2.getPageType(), null, str2, EntityIdType.CD_OBJECT_ID, null, Integer.valueOf(i), null, null, null, null, 3914, null);
                    }
                }
            };
            Observer observer = new Observer() { // from class: com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment$$ExternalSyntheticLambda9
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    PlaylistDetailFragment.m841getBinderList$lambda17$lambda16(this.f$0, (String) obj);
                }
            };
            FragmentActivity fragmentActivityRequireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "requireActivity()");
            PlaylistTrackClickListenerFactory playlistTrackClickListenerFactory = new PlaylistTrackClickListenerFactory(observer, fragmentActivityRequireActivity, this, this, PlaybackPageType.PLAYLIST_DETAIL, function3, this.isCatalog, this.luid, getPlaylistUriForQuery());
            UserPlaylistDetailProfileLinkHandler userPlaylistDetailProfileLinkHandler = new UserPlaylistDetailProfileLinkHandler(getContext());
            ButtonNavigatorClickListener buttonNavigatorClickListener = new ButtonNavigatorClickListener(getActivity(), new PlaylistDetailFragment$getBinderList$1$buttonNavigatorClickListener$1(this), this.luid, new PlaylistDetailFragment$getBinderList$1$buttonNavigatorClickListener$2(this), new PlaylistDetailFragment$getBinderList$1$buttonNavigatorClickListener$3(this));
            BaseViewsBinderProvider.BinderListBuilder binderListBuilderWithCollectionHeader = new BaseViewsBinderProvider.BinderListBuilder(viewsConfiguration).withTextRowItems().withFeaturedToggle(getFeaturedToggleListenerProvider(), new ContentViewManager()).withVisualRowItems(playlistTrackClickListenerFactory, Boolean.valueOf(AmazonApplication.getCapabilities().isDownloadTrackButtonEnabled())).withHeaderRowItems().withCollectionHeader(getPageHeaderOnClickListenerMap(), userPlaylistDetailProfileLinkHandler);
            Map<PageHeaderActionIconModel.ActionType, ActionIconOnClickListener> pageHeaderOnClickListenerMap = getPageHeaderOnClickListenerMap();
            ExploreVideoStoryManager exploreVideoStoryManager = this.exploreVideoStoryManager;
            View.OnTouchListener textContainerOnTouchListener = exploreVideoStoryManager == null ? null : exploreVideoStoryManager.getTextContainerOnTouchListener();
            ExploreVideoStoryManager exploreVideoStoryManager2 = this.exploreVideoStoryManager;
            return binderListBuilderWithCollectionHeader.withStoryHeader(pageHeaderOnClickListenerMap, textContainerOnTouchListener, exploreVideoStoryManager2 != null ? Integer.valueOf(exploreVideoStoryManager2.getPaginationCount()) : null).withRefinements(this.detailPageRefinementManager).withButtonNavigator(buttonNavigatorClickListener).withShuffledWithText().withMultiStateButton(new MultiStateButtonClickListenerProvider() { // from class: com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment$getBinderList$1$1
                @Override // com.amazon.music.views.library.providers.MultiStateButtonClickListenerProvider
                public void onClick(MultiStateButtonModel model, View view) {
                    Intrinsics.checkNotNullParameter(model, "model");
                    AbstractBaseViewsFragment.sendUiClickMetric$default(this.this$0, model.getShowPrimary() ? ActionType.SELECT_PREVIEW_TRACKLIST : ActionType.SELECT_HIDE_TRACKLIST, null, this.this$0.getPageType(), null, null, null, null, null, null, null, null, null, 4090, null);
                    PlaylistDetailViewModel playlistDetailViewModel = this.this$0.playlistDetailViewModel;
                    if (playlistDetailViewModel == null) {
                        return;
                    }
                    playlistDetailViewModel.toggleTracklistVisibility();
                }
            }).withEmptyView(this).withHighlightableMessageView(this).build();
        }
        return CollectionsKt__CollectionsKt.emptyList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getBinderList$lambda-17$lambda-16, reason: not valid java name */
    public static final void m841getBinderList$lambda17$lambda16(PlaylistDetailFragment this$0, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.removeSuggestionAndUpdate(str);
    }

    private final FeaturedToggleListenerProvider getFeaturedToggleListenerProvider() {
        Function0<Unit> function0 = new Function0<Unit>() { // from class: com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment$getFeaturedToggleListenerProvider$confirmSelectionCallback$1
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
                PageViewModelProvider pageModelProvider = this.this$0.getPageModelProvider();
                if (pageModelProvider == null) {
                    return;
                }
                PageViewModelProvider.DefaultImpls.fetchUpdates$default(pageModelProvider, 20, this.this$0.getDiffUtilFactory(), null, 4, null);
            }
        };
        Context context = getContext();
        PageType pageType = this.pageType;
        if (pageType == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pageType");
            pageType = null;
        }
        return new AllAccessUserPlaylistSelectionHandler(context, pageType, getEntityId(), getEntityIdType(), function0).getAAUPToggleListenerProvider();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object getPlaylistLuidAsync(android.content.Context r6, android.net.Uri r7, kotlin.coroutines.Continuation<? super kotlin.Unit> r8) throws java.lang.Throwable {
        /*
            r5 = this;
            boolean r0 = r8 instanceof com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment.C03511
            if (r0 == 0) goto L13
            r0 = r8
            com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment$getPlaylistLuidAsync$1 r0 = (com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment.C03511) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment$getPlaylistLuidAsync$1 r0 = new com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment$getPlaylistLuidAsync$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L35
            if (r2 != r3) goto L2d
            java.lang.Object r6 = r0.L$0
            com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment r6 = (com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment) r6
            kotlin.ResultKt.throwOnFailure(r8)
            goto L57
        L2d:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L35:
            kotlin.ResultKt.throwOnFailure(r8)
            boolean r8 = r5.isCatalog
            if (r8 == 0) goto L41
            java.lang.String r6 = ""
            r7 = r6
            r6 = r5
            goto L5a
        L41:
            kotlinx.coroutines.CoroutineDispatcher r8 = kotlinx.coroutines.Dispatchers.getDefault()
            com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment$getPlaylistLuidAsync$2 r2 = new com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment$getPlaylistLuidAsync$2
            r4 = 0
            r2.<init>(r6, r7, r4)
            r0.L$0 = r5
            r0.label = r3
            java.lang.Object r8 = kotlinx.coroutines.BuildersKt.withContext(r8, r2, r0)
            if (r8 != r1) goto L56
            return r1
        L56:
            r6 = r5
        L57:
            r7 = r8
            java.lang.String r7 = (java.lang.String) r7
        L5a:
            r6.luid = r7
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment.getPlaylistLuidAsync(android.content.Context, android.net.Uri, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment$getPlaylistLuidAsync$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PlaylistDetailFragment.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment$getPlaylistLuidAsync$2", f = "PlaylistDetailFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03522 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String>, Object> {
        final /* synthetic */ Context $context;
        final /* synthetic */ Uri $uri;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03522(Context context, Uri uri, Continuation<? super C03522> continuation) {
            super(2, continuation);
            this.$context = context;
            this.$uri = uri;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C03522(this.$context, this.$uri, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super String> continuation) {
            return ((C03522) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            String playlistLuid = PlaylistUtil.getPlaylistLuid(this.$context, this.$uri);
            return playlistLuid == null ? "" : playlistLuid;
        }
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public BaseViewsDiffUtilFactory getDiffUtilFactory() {
        return new BaseViewsDiffUtilFactory() { // from class: com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment.getDiffUtilFactory.1
            @Override // com.amazon.mp3.catalog.fragment.adapter.BaseViewsDiffUtilFactory
            public BaseViewsDiffUtil getBaseViewsDiffUtil(List<? extends BaseViewModel> oldList, List<? extends BaseViewModel> newList) {
                Intrinsics.checkNotNullParameter(oldList, "oldList");
                Intrinsics.checkNotNullParameter(newList, "newList");
                return new BaseViewsDiffUtil(oldList, newList);
            }
        };
    }

    /* JADX INFO: renamed from: com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment$emitUiPageViewMetric$1, reason: invalid class name */
    /* JADX INFO: compiled from: PlaylistDetailFragment.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment$emitUiPageViewMetric$1", f = "PlaylistDetailFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PlaylistDetailFragment.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            PlaylistDetailFragment.this.emitUiPageViewMetricHelper();
            return Unit.INSTANCE;
        }
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public void emitUiPageViewMetric() {
        BuildersKt__Builders_commonKt.launch$default(Coroutines.INSTANCE.getIoScope(), null, null, new AnonymousClass1(null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0056  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void emitUiPageViewMetricHelper() {
        /*
            r14 = this;
            boolean r0 = r14.isCatalog
            r1 = 0
            if (r0 == 0) goto L14
            com.amazon.mp3.playlist.CatalogPlaylist r0 = r14.getCatalogPlaylist()
            if (r0 != 0) goto Ld
            r0 = r1
            goto L11
        Ld:
            java.lang.String r0 = r0.getAsin()
        L11:
            java.lang.String r2 = "ASIN"
            goto L22
        L14:
            android.content.Context r0 = r14.getContext()
            android.net.Uri r2 = r14.getContentUri()
            java.lang.String r0 = com.amazon.mp3.library.util.PlaylistUtil.getPlaylistLuid(r0, r2)
            java.lang.String r2 = "PLAYLIST_ID"
        L22:
            r6 = r0
            r7 = r2
            com.amazon.music.metrics.mts.event.types.uiinteraction.PageType r4 = r14.getPageType()
            com.amazon.mp3.featuregate.Feature r0 = com.amazon.mp3.featuregate.Feature.uipageview_should_send_pagesubtype
            boolean r0 = r0.isEnabled()
            if (r0 == 0) goto L63
            com.amazon.music.metrics.mts.event.types.uiinteraction.PageType r0 = r14.pageType
            java.lang.String r2 = "pageType"
            if (r0 != 0) goto L3a
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r0 = r1
        L3a:
            com.amazon.music.metrics.mts.event.types.uiinteraction.PageType r3 = com.amazon.music.metrics.mts.event.types.uiinteraction.PageType.DETAIL_PLAYLIST
            if (r0 == r3) goto L56
            com.amazon.music.metrics.mts.event.types.uiinteraction.PageType r0 = r14.pageType
            if (r0 != 0) goto L46
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r0 = r1
        L46:
            com.amazon.music.metrics.mts.event.types.uiinteraction.PageType r3 = com.amazon.music.metrics.mts.event.types.uiinteraction.PageType.DETAIL_PERSONALIZED_PLAYLIST
            if (r0 == r3) goto L56
            com.amazon.music.metrics.mts.event.types.uiinteraction.PageType r0 = r14.pageType
            if (r0 != 0) goto L52
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r0 = r1
        L52:
            com.amazon.music.metrics.mts.event.types.uiinteraction.PageType r2 = com.amazon.music.metrics.mts.event.types.uiinteraction.PageType.DETAIL_USER_PLAYLIST
            if (r0 != r2) goto L63
        L56:
            com.amazon.music.metrics.mts.event.types.uiinteraction.PageSubType r0 = r14.pageSubType
            if (r0 != 0) goto L5b
            goto L5f
        L5b:
            java.lang.String r1 = r0.getMetricsValue()
        L5f:
            java.lang.String r1 = java.lang.String.valueOf(r1)
        L63:
            r5 = r1
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 240(0xf0, float:3.36E-43)
            r13 = 0
            r3 = r14
            com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment.sendUiPageViewMetric$default(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment.emitUiPageViewMetricHelper():void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void emitUiClickMetric(PlaylistMetadata metadata, boolean isShuffled) {
        String entityId = getEntityId();
        EntityIdType entityIdType = metadata.getAsin() != null ? EntityIdType.ASIN : EntityIdType.PLAYLIST_ID;
        ActionType actionType = ActionType.PLAY_SHUFFLE_ALL;
        if (!isShuffled) {
            if (this.isCatalog) {
                actionType = ActionType.PLAY_CURATED_PLAYLIST;
            } else {
                actionType = ActionType.PLAY_PLAYLIST;
            }
        }
        AbstractBaseViewsFragment.sendUiClickMetric$default(this, actionType, null, getPageType(), null, entityId, entityIdType, null, -1, null, null, null, null, 3914, null);
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected ContentEnabilityProvider getContentEnabilityProvider() {
        if (getEnabilityProvider() == null) {
            Context context = getContext();
            Context applicationContext = context == null ? null : context.getApplicationContext();
            if (applicationContext == null) {
                applicationContext = AmazonApplication.getApplication().getApplicationContext();
            }
            Intrinsics.checkNotNullExpressionValue(applicationContext, "this.context?.applicatio…tion().applicationContext");
            setEnabilityProvider(new PlaylistContentEnabilityProvider(applicationContext, getActivity()));
        }
        return getEnabilityProvider();
    }

    @Override // com.amazon.mp3.fragment.headerbar.OverflowMenuReceiver
    public void setActionBarProvider(ActionBarProvider provider) {
        this.actionBarProvider = provider;
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    public boolean shouldDisplayPageComponentsInTopAppBar(int actionBarHeight) {
        RecyclerView recyclerView = getRecyclerView();
        Integer numFindPositionOfFirstTrack = null;
        RecyclerView.LayoutManager layoutManager = recyclerView == null ? null : recyclerView.getLayoutManager();
        LinearLayoutManager linearLayoutManager = layoutManager instanceof LinearLayoutManager ? (LinearLayoutManager) layoutManager : null;
        if (linearLayoutManager == null) {
            return false;
        }
        if (doesHaveRefinement()) {
            PlaylistDetailViewModel playlistDetailViewModel = this.playlistDetailViewModel;
            if (playlistDetailViewModel != null) {
                numFindPositionOfFirstTrack = playlistDetailViewModel.findPositionOfRefinementList();
            }
        } else {
            PlaylistDetailViewModel playlistDetailViewModel2 = this.playlistDetailViewModel;
            if (playlistDetailViewModel2 != null) {
                numFindPositionOfFirstTrack = playlistDetailViewModel2.findPositionOfFirstTrack();
            }
        }
        if (numFindPositionOfFirstTrack == null) {
            return false;
        }
        return BaseViewsFilterActionBarManager.INSTANCE.isTargetViewUnderActionBarView(numFindPositionOfFirstTrack.intValue(), actionBarHeight, linearLayoutManager);
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    public String getArtworkUrl() {
        PlaylistDetailViewModel playlistDetailViewModel = this.playlistDetailViewModel;
        if (playlistDetailViewModel == null) {
            return null;
        }
        return playlistDetailViewModel.getArtworkUrl();
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
    public Integer getFilterButtonClickDestination() {
        PlaylistDetailViewModel playlistDetailViewModel = this.playlistDetailViewModel;
        if (playlistDetailViewModel == null) {
            return null;
        }
        return playlistDetailViewModel.findPositionOfRefinementList();
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    public ContentMetadata getContentMetadata() {
        PlaylistDetailViewModel playlistDetailViewModel = this.playlistDetailViewModel;
        if (playlistDetailViewModel == null) {
            return null;
        }
        return PlaylistDetailViewModel.getPlaylistMetadata$default(playlistDetailViewModel, null, 1, null);
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    public ExposedRefinementListModel getRefinementListModel() {
        Integer numFindPositionOfRefinementList;
        int iIntValue;
        List<BaseViewModel> models;
        PlaylistDetailViewModel playlistDetailViewModel = this.playlistDetailViewModel;
        if (playlistDetailViewModel == null || (numFindPositionOfRefinementList = playlistDetailViewModel.findPositionOfRefinementList()) == null || (iIntValue = numFindPositionOfRefinementList.intValue()) < 0) {
            return null;
        }
        PageGridViewModel pageModel = getPageModel();
        BaseViewModel baseViewModel = (pageModel == null || (models = pageModel.getModels()) == null) ? null : models.get(iIntValue);
        if (baseViewModel instanceof ExposedRefinementListModel) {
            return (ExposedRefinementListModel) baseViewModel;
        }
        return null;
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    public boolean shouldDisplayFilterButton(RecyclerView recyclerView, int actionBarHeight) {
        return doesHaveRefinement() && BaseViewsFilterActionBarManager.InfoProvider.DefaultImpls.shouldDisplayFilterButton(this, recyclerView, actionBarHeight);
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    public boolean shouldDisplayFilterAndContextButtons() {
        if (!Feature.playlist_filter_context_menu_button.isEnabled()) {
            Uri contentUri = getContentUri();
            if (!getConfiguration().shouldShowHeaderFilterContextMenuButton(AmazonApplication.getLibraryItemFactory().getItem(contentUri == null ? null : ContentPlaybackUtils.INSTANCE.removeTracksFromUriIfNeeded(contentUri)))) {
                return false;
            }
        }
        return (this.isVideoPlaylist || PlaylistUtil.isRecentlyAddedPlaylist(getContentUri()) || PlaylistUtil.isRecentlyAddedToDevicePlaylist(getContentUri())) ? false : true;
    }

    @Override // com.amazon.mp3.actionbar.BaseViewsFilterActionBarManager.InfoProvider
    public boolean doesHaveRefinement() {
        Boolean boolValueOf;
        if (this.refinementCapability == null) {
            PlaylistMetadata playlistMetadata = getPlaylistMetadata();
            if (playlistMetadata != null) {
                boolValueOf = Boolean.valueOf(getConfiguration().shouldShowRefinementsOption(playlistMetadata));
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

    @Override // com.amazon.mp3.catalog.fragment.LibraryBaseViewsFragment
    protected boolean shouldRemoveDownloadForTracks() {
        if (this.isCatalog) {
            return true;
        }
        return super.shouldRemoveDownloadForTracks();
    }

    @Override // com.amazon.mp3.catalog.fragment.LibraryBaseViewsFragment, com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public MissingSDCardHandler getMissingSDCardProvider() {
        Context context = getContext();
        if (context == null) {
            return null;
        }
        return new MissingSDCardHandler(context);
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

    @Override // com.amazon.music.views.library.providers.RefinementCallback
    public void onFiltersReset() {
        Log.debug(TAG, "reset all refinement filters");
        showRecyclerView();
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected ContentMetadataOnClickListener getMetadataClickListener() {
        if (this.isCatalog) {
            return getPrimeMetadataOnClickListener();
        }
        return super.getMetadataClickListener();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void displayFilteredData() {
        PlaylistDetailViewModel playlistDetailViewModel = this.playlistDetailViewModel;
        if (playlistDetailViewModel != null) {
            playlistDetailViewModel.willDisplayFilteredData();
        }
        Context context = getContext();
        if (context == null) {
            return;
        }
        PlaylistDetailViewModel playlistDetailViewModel2 = this.playlistDetailViewModel;
        CuratePlaylistProvider curatePlaylistProvider = playlistDetailViewModel2 == null ? null : playlistDetailViewModel2.getCuratePlaylistProvider();
        List<BaseViewModel> cappedSuggestions = (!AmazonApplication.getCapabilities().isPlaylistSongRecommendationWidgetEnabled() || curatePlaylistProvider == null) ? null : curatePlaylistProvider.getCappedSuggestions();
        BaseViewsRefinementManager baseViewsRefinementManager = this.refinementsManager;
        List<? extends BaseViewModel> listApplyRefinementFilters$default = baseViewsRefinementManager != null ? BaseViewsRefinementManager.applyRefinementFilters$default(baseViewsRefinementManager, context, null, cappedSuggestions, 2, null) : null;
        PlaylistDetailViewModel playlistDetailViewModel3 = this.playlistDetailViewModel;
        if (playlistDetailViewModel3 != null) {
            playlistDetailViewModel3.refreshTracksDownloadState(listApplyRefinementFilters$default);
        }
        shouldShowEmptyView(listApplyRefinementFilters$default);
    }

    private final void shouldShowEmptyView(List<? extends BaseViewModel> filteredTrackModels) {
        if ((filteredTrackModels != null && filteredTrackModels.size() == 0) || filteredTrackModels == null) {
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
        showRecyclerView();
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected void setupViews() {
        super.setupViews();
        initializeActionBarManager();
        BaseViewsBinderConfiguration viewsConfiguration = getViewsConfiguration();
        if (viewsConfiguration != null) {
            RefinementPillBinder refinementPillBinder = new RefinementPillBinder(viewsConfiguration.getStyleSheet(), this.detailPageRefinementManager);
            BaseViewModelAdapterProvider adapterProvider = viewsConfiguration.getAdapterProvider();
            List<FilterModel> refinementFilterModels = getRefinementFilterModels();
            BaseViewsFilterActionBarManager baseViewsFilterActionBarManager = this.actionBarManager;
            if (baseViewsFilterActionBarManager != null) {
                baseViewsFilterActionBarManager.setAdapterProviderForActionBarView(adapterProvider, refinementPillBinder, refinementFilterModels);
            }
        }
        Set<RefinementFilterType> defaultSelectedRefinements = getDefaultSelectedRefinements();
        if (defaultSelectedRefinements != null) {
            Iterator<T> it = defaultSelectedRefinements.iterator();
            while (it.hasNext()) {
                updateFilterSelected((RefinementFilterType) it.next(), true);
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

    @Override // com.amazon.music.views.library.providers.ButtonActionProvider
    public void onButtonClicked(ButtonType buttonType) {
        Intrinsics.checkNotNullParameter(buttonType, "buttonType");
        if (buttonType instanceof ButtonType.ADD_SONGS) {
            addSongsToPlaylist();
        }
    }

    private final void addSongsToPlaylist() {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        if (AmazonApplication.getCapabilities().isPlaylistSongSearchEnabled() && ConnectivityUtil.hasAnyInternetConnection(activity.getApplicationContext())) {
            new PlaylistSongSearchHandler(activity, this.luid, getPlaylistName()).startPlaylistSongSearchActivty();
        } else {
            PlaylistUtil.startEditPlaylist(activity, PlaylistUtil.withAppendedScratchArgument(getPlaylistUriForQuery()), true);
        }
    }

    @Override // com.amazon.music.views.library.providers.EmptyStateActionProvider
    public void onEmptyStateButtonClicked(EmptyStateButtonType buttonType) {
        Intrinsics.checkNotNullParameter(buttonType, "buttonType");
        if (buttonType instanceof EmptyStateButtonType.ADDSONGS) {
            addSongsToPlaylist();
        }
    }

    private final void updateFilterSelected(RefinementFilterType filter, boolean selected) {
        FilterModel filterModelRetrieveFilterModelByTitle;
        FilterItemModel itemModel;
        BaseViewsRefinementManager baseViewsRefinementManager = this.refinementsManager;
        if (baseViewsRefinementManager == null || (filterModelRetrieveFilterModelByTitle = baseViewsRefinementManager.retrieveFilterModelByTitle(RefinementFilterType.INSTANCE.getDisplayName(filter))) == null || filterModelRetrieveFilterModelByTitle.isCollection() || (itemModel = filterModelRetrieveFilterModelByTitle.getItemModel()) == null) {
            return;
        }
        itemModel.setSelected(selected);
        BaseViewsRefinementManager baseViewsRefinementManager2 = this.refinementsManager;
        if (baseViewsRefinementManager2 != null) {
            baseViewsRefinementManager2.updateSelectedFilterSet(itemModel);
        }
        displayFilteredData();
    }

    /* JADX INFO: renamed from: com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment$updatePageModel$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PlaylistDetailFragment.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment$updatePageModel$1", f = "PlaylistDetailFragment.kt", i = {}, l = {1159}, m = "invokeSuspend", n = {}, s = {})
    static final class C03561 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ boolean $isMorePage;
        int label;
        final /* synthetic */ PlaylistDetailFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03561(boolean z, PlaylistDetailFragment playlistDetailFragment, Continuation<? super C03561> continuation) {
            super(2, continuation);
            this.$isMorePage = z;
            this.this$0 = playlistDetailFragment;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C03561(this.$isMorePage, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03561) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Integer num;
            String followerCountString;
            PageHeaderModel pageHeaderModel;
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                if (this.$isMorePage) {
                    return Unit.INSTANCE;
                }
                PlaylistDetailViewModel playlistDetailViewModel = this.this$0.playlistDetailViewModel;
                if (playlistDetailViewModel == null) {
                    num = null;
                    followerCountString = BauhausDetailHeaderTextUtil.INSTANCE.getFollowerCountString(num);
                    if (followerCountString != null && (pageHeaderModel = this.this$0.getPageHeaderModel()) != null) {
                        pageHeaderModel.updateText4(followerCountString);
                    }
                    return Unit.INSTANCE;
                }
                this.label = 1;
                obj = playlistDetailViewModel.fetchFollowerCount(this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            num = (Integer) obj;
            followerCountString = BauhausDetailHeaderTextUtil.INSTANCE.getFollowerCountString(num);
            if (followerCountString != null) {
                pageHeaderModel.updateText4(followerCountString);
            }
            return Unit.INSTANCE;
        }
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected void updatePageModel(PageGridViewModel pageGridViewModel, boolean isInitialPage, boolean isMorePage) {
        PlaylistDetailViewModel playlistDetailViewModel;
        if (pageGridViewModel != null && Feature.pdp_follower_count.isEnabled()) {
            LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
            Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "viewLifecycleOwner");
            BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(viewLifecycleOwner), Dispatchers.getIO(), null, new C03561(isMorePage, this, null), 2, null);
        }
        PlaylistDetailViewModel playlistDetailViewModel2 = this.playlistDetailViewModel;
        PlaylistMetadata playlistMetadata = playlistDetailViewModel2 == null ? null : playlistDetailViewModel2.getPlaylistMetadata(pageGridViewModel);
        setPlaylistVersion(playlistMetadata == null ? null : playlistMetadata.getVersion());
        PlaylistDetailViewModel playlistDetailViewModel3 = this.playlistDetailViewModel;
        if (playlistDetailViewModel3 != null) {
            playlistDetailViewModel3.updateRowNumber(pageGridViewModel);
        }
        CatalogPlaylist catalogPlaylist = getCatalogPlaylist();
        boolean z = false;
        if (catalogPlaylist != null && playlistMetadata != null && getConfiguration().shouldShowTracklistPreview(playlistMetadata) && !catalogPlaylist.isSharedUserPlaylist() && (playlistDetailViewModel = this.playlistDetailViewModel) != null) {
            PlaylistDetailViewModel.setTracklistModelsVisibility$default(playlistDetailViewModel, getPageModel(), false, 2, null);
        }
        super.updatePageModel(pageGridViewModel, isInitialPage, isMorePage);
        PlaylistDetailViewModel playlistDetailViewModel4 = this.playlistDetailViewModel;
        if (playlistDetailViewModel4 != null) {
            playlistDetailViewModel4.updateAvailabilityState();
        }
        initActionBarContextMenu();
        setFilterButton();
        getTrackInformationFetcher().setPageModel(pageGridViewModel);
        if (this.isCatalog && getContentUri() != null && !Intrinsics.areEqual(getContentUri(), Uri.EMPTY) && playlistMetadata != null) {
            updateContentUriOnAsinChanged(playlistMetadata);
            CatalogPlaylist catalogPlaylist2 = getCatalogPlaylist();
            if (catalogPlaylist2 != null && catalogPlaylist2.getTrackCount() > 0) {
                getPrimeMetadataOnClickListener().updatePlaylistInfo(catalogPlaylist2);
            } else {
                showPlaylistNotFoundDialog$default(this, false, 1, null);
            }
        }
        if (AmazonApplication.getCapabilities().isOnDemandPlaylistsEnabled()) {
            if (GetOnDemandPlaylistsService.INSTANCE.get().isOnDemandPlaylist(playlistMetadata != null ? playlistMetadata.getAsin() : null) && UserSubscriptionUtil.getUserSubscription().isNightwingOnly()) {
                z = true;
            }
        }
        this.isOnDemandGoldenPlaylist = z;
        handleDeeplink();
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected void updateOnError(ErrorModel errorModel) {
        Intrinsics.checkNotNullParameter(errorModel, "errorModel");
        super.updateOnError(errorModel);
        Throwable throwable = errorModel.getThrowable();
        if (throwable instanceof MusicPlaylistExceptions.AccessDeniedException ? true : throwable instanceof MusicPlaylistExceptions.PlaylistSuppressedException) {
            showPlaylistNotFoundDialog(Feature.playlists_privacy_enforcement.isEnabled());
        } else {
            showPlaylistNotFoundDialog$default(this, false, 1, null);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:4:0x0008  */
    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected boolean shouldShowEmptyView() {
        /*
            r6 = this;
            com.amazon.music.views.library.models.PageGridViewModel r0 = r6.getPageModel()
            r1 = 1
            r2 = 0
            if (r0 != 0) goto La
        L8:
            r0 = r2
            goto L33
        La:
            java.util.List r0 = r0.getModels()
            if (r0 != 0) goto L11
            goto L8
        L11:
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.Iterator r0 = r0.iterator()
        L1a:
            boolean r4 = r0.hasNext()
            if (r4 == 0) goto L2c
            java.lang.Object r4 = r0.next()
            boolean r5 = r4 instanceof com.amazon.music.views.library.models.HighlightableMessageModel
            if (r5 == 0) goto L1a
            r3.add(r4)
            goto L1a
        L2c:
            boolean r0 = r3.isEmpty()
            if (r0 != 0) goto L8
            r0 = r1
        L33:
            if (r0 != 0) goto L9c
            com.amazon.music.views.library.models.PageGridViewModel r0 = r6.getPageModel()
            if (r0 != 0) goto L3d
        L3b:
            r0 = r2
            goto L66
        L3d:
            java.util.List r0 = r0.getModels()
            if (r0 != 0) goto L44
            goto L3b
        L44:
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.Iterator r0 = r0.iterator()
        L4d:
            boolean r4 = r0.hasNext()
            if (r4 == 0) goto L5f
            java.lang.Object r4 = r0.next()
            boolean r5 = r4 instanceof com.amazon.music.views.library.models.EmptyStateModel
            if (r5 == 0) goto L4d
            r3.add(r4)
            goto L4d
        L5f:
            boolean r0 = r3.isEmpty()
            if (r0 != 0) goto L3b
            r0 = r1
        L66:
            if (r0 != 0) goto L9c
            com.amazon.music.views.library.models.PageGridViewModel r0 = r6.getPageModel()
            if (r0 != 0) goto L70
        L6e:
            r0 = r2
            goto L99
        L70:
            java.util.List r0 = r0.getModels()
            if (r0 != 0) goto L77
            goto L6e
        L77:
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.Iterator r0 = r0.iterator()
        L80:
            boolean r4 = r0.hasNext()
            if (r4 == 0) goto L92
            java.lang.Object r4 = r0.next()
            boolean r5 = r4 instanceof com.amazon.music.views.library.models.TableRowItemModel
            if (r5 == 0) goto L80
            r3.add(r4)
            goto L80
        L92:
            boolean r0 = r3.isEmpty()
            if (r0 != 0) goto L6e
            r0 = r1
        L99:
            if (r0 != 0) goto L9c
            goto L9d
        L9c:
            r1 = r2
        L9d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment.shouldShowEmptyView():boolean");
    }

    private final void bindBackgroundImage() {
        PlaylistDetailViewModel playlistDetailViewModel = this.playlistDetailViewModel;
        StyleSheet styleSheet = null;
        byte b = 0;
        PlaylistMetadata playlistMetadata$default = playlistDetailViewModel == null ? null : PlaylistDetailViewModel.getPlaylistMetadata$default(playlistDetailViewModel, null, 1, null);
        if (playlistMetadata$default != null) {
            new DefaultCachedArtworkProvider(getActivity(), styleSheet, 2, b == true ? 1 : 0).loadCachedArtwork(this, this.backgroundImageView, playlistMetadata$default);
        }
    }

    @Override // com.amazon.mp3.catalog.fragment.LibraryBaseViewsFragment, com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    public MetadataContextMenuHandler getContextMenuProvider() {
        if (getContextMenuHandler() == null) {
            FragmentActivity activity = getActivity();
            BaseActivity baseActivity = activity instanceof BaseActivity ? (BaseActivity) activity : null;
            if (baseActivity != null && getContext() != null) {
                if (this.isCatalog) {
                    setContextMenuHandler(new PrimePlaylistContextMenuProvider(baseActivity, this, getPageType(), this.startedFromPrimeBrowse, getTrackContextMenuListener(), this.playlistContextMenuListener, 0, LifecycleOwnerKt.getLifecycleScope(this), 64, null));
                } else {
                    setContextMenuHandler(new PlaylistTrackContextMenuProvider(baseActivity, this, getPageType(), getTrackContextMenuListener(), this.playlistContextMenuListener, LifecycleOwnerKt.getLifecycleScope(this)));
                }
            }
            if (getContextMenuHandler() instanceof LibraryContextMenuProvider) {
                Lifecycle lifecycle = getLifecycle();
                MetadataContextMenuHandler contextMenuHandler = getContextMenuHandler();
                if (contextMenuHandler == null) {
                    throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.providers.LibraryContextMenuProvider");
                }
                lifecycle.addObserver((LibraryContextMenuProvider) contextMenuHandler);
            }
            return getContextMenuHandler();
        }
        return getContextMenuHandler();
    }

    @Override // com.amazon.mp3.catalog.fragment.LibraryBaseViewsFragment, com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected RowButtonOnClickProvider getRowButtonOnClickProvider(FragmentActivity activity) {
        PageType pageType;
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (getRowButtonOnClickProvider() == null) {
            if (AmazonApplication.getCapabilities().isLikesEverywhereEnabled()) {
                boolean zCanLikePlaylistTracks = this.isCatalog ? true : PlaylistUtil.canLikePlaylistTracks(activity, getContentUri());
                LibraryStateProvider libraryStateProviderInstance = getLibraryStateProviderInstance();
                AbstractTrackContextMenuListener trackContextMenuListener = getTrackContextMenuListener();
                PageType pageType2 = this.pageType;
                if (pageType2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("pageType");
                    pageType = null;
                } else {
                    pageType = pageType2;
                }
                setRowButtonOnClickProvider(new DefaultLikesProvider(activity, libraryStateProviderInstance, trackContextMenuListener, zCanLikePlaylistTracks, pageType));
                RowButtonOnClickProvider rowButtonOnClickProvider = getRowButtonOnClickProvider();
                DefaultLikesProvider defaultLikesProvider = rowButtonOnClickProvider instanceof DefaultLikesProvider ? (DefaultLikesProvider) rowButtonOnClickProvider : null;
                if (defaultLikesProvider != null) {
                    defaultLikesProvider.setSelectionSourceId(selectionSourceId());
                    defaultLikesProvider.setSelectionSourceType(selectionSourceType());
                }
            } else {
                setRowButtonOnClickProvider(super.getRowButtonOnClickProvider(activity));
                RowButtonOnClickProvider rowButtonOnClickProvider2 = getRowButtonOnClickProvider();
                DefaultAddToLibraryProvider defaultAddToLibraryProvider = rowButtonOnClickProvider2 instanceof DefaultAddToLibraryProvider ? (DefaultAddToLibraryProvider) rowButtonOnClickProvider2 : null;
                if (defaultAddToLibraryProvider != null) {
                    defaultAddToLibraryProvider.setSelectionSourceId(selectionSourceId());
                    defaultAddToLibraryProvider.setSelectionSourceType(selectionSourceType());
                }
            }
        }
        return getRowButtonOnClickProvider();
    }

    private final void initActionBarContextMenu() {
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

    private final String selectionSourceId() {
        CatalogPlaylist catalogPlaylist;
        if (!this.isCatalog || (catalogPlaylist = getCatalogPlaylist()) == null) {
            return null;
        }
        return catalogPlaylist.getAsin();
    }

    private final SelectionSourceType selectionSourceType() {
        if (this.isCatalog) {
            return new BrowseMode(AccountManagerSingleton.get(), FeatureFlagsProvider.getFeatureFlagProvider()).getBrowseMode() == BrowseMode.Mode.PRIME ? SelectionSourceType.PRIME_PLAYLIST : SelectionSourceType.UNLIMITED_PLAYLIST;
        }
        return SelectionSourceType.AUTO_PLAYLIST;
    }

    @Override // com.amazon.mp3.catalog.fragment.LibraryBaseViewsFragment
    protected void removeTrackFromUserPlaylist(Uri trackUri) {
        Intrinsics.checkNotNullParameter(trackUri, "trackUri");
        Uri contentUri = getContentUri();
        if (contentUri == null || AllAccessUserPlaylistLimitTracker.canRemove(contentUri) == AllAccessUserPlaylistLimitTracker.Response.SUCCESS) {
            BuildersKt__Builders_commonKt.launch$default(Coroutines.INSTANCE.getIoScope(), null, null, new C03552(trackUri, null), 3, null);
        } else {
            startActivity(AllAccessPlaylistLimitReachedActivity.getStartIntent(AllAccessUserPlaylistLimitTracker.Response.AAUP_RESTRICTION_LIMIT.toString()));
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment$removeTrackFromUserPlaylist$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PlaylistDetailFragment.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment$removeTrackFromUserPlaylist$2", f = "PlaylistDetailFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03552 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Uri $trackUri;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03552(Uri uri, Continuation<? super C03552> continuation) {
            super(2, continuation);
            this.$trackUri = uri;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PlaylistDetailFragment.this.new C03552(this.$trackUri, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03552) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            Uri contentUri = PlaylistDetailFragment.this.getContentUri();
            Context context = PlaylistDetailFragment.this.getContext();
            if (context == null) {
                context = AmazonApplication.getApplication().getApplicationContext();
            }
            Uri editPlaylistUri = PlaylistUtil.getEditPlaylistUri(PlaylistUtil.withAppendedScratchArgument(contentUri));
            CirrusPlaylistScratch.create(context, PlaylistUtil.getPlaylistId(editPlaylistUri));
            Iterator<Uri> it = PlaylistUtil.getPlaylistTrackUrisFromTrack(context, editPlaylistUri, this.$trackUri).iterator();
            if (it.hasNext()) {
                PlaylistDetailFragment.this.removeTrackFromPlaylist(it.next());
            }
            PlaylistDetailFragment.this.shouldReobserve = true;
            AllAccessUserPlaylistLimitTracker.incrementRemoveCounter(editPlaylistUri);
            PlaylistDetailFragment.this.startActivityForResult(SavePlaylistActivity.getStartIntent(context, editPlaylistUri), SavePlaylistActivity.REQUEST_CODE);
            return Unit.INSTANCE;
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
        dynamicBackgroundEffectUtils.getTransformBlurredImageSubscription(sentBitmap, context, styleSheet, new DynamicBackgroundImageCondition(null, null, AlphaKey.GLASS_1, null, 11, null)).subscribe(new Action1() { // from class: com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                PlaylistDetailFragment.m846onBitmapLoaded$lambda40$lambda39$lambda37(this.f$0, (Bitmap) obj);
            }
        }, new Action1() { // from class: com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment$$ExternalSyntheticLambda2
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                PlaylistDetailFragment.m847onBitmapLoaded$lambda40$lambda39$lambda38(this.f$0, (Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onBitmapLoaded$lambda-40$lambda-39$lambda-37, reason: not valid java name */
    public static final void m846onBitmapLoaded$lambda40$lambda39$lambda37(PlaylistDetailFragment this$0, Bitmap bitmap) {
        ViewPropertyAnimator viewPropertyAnimatorAnimate;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ImageView imageView = this$0.backgroundImageView;
        if (imageView != null) {
            imageView.setAlpha(0.0f);
        }
        ImageView imageView2 = this$0.backgroundImageView;
        if (imageView2 != null) {
            imageView2.setImageBitmap(bitmap);
        }
        ImageView imageView3 = this$0.backgroundImageView;
        ViewPropertyAnimator viewPropertyAnimatorAlpha = null;
        if (imageView3 != null && (viewPropertyAnimatorAnimate = imageView3.animate()) != null) {
            viewPropertyAnimatorAlpha = viewPropertyAnimatorAnimate.alpha(1.0f);
        }
        if (viewPropertyAnimatorAlpha == null) {
            return;
        }
        viewPropertyAnimatorAlpha.setDuration(1000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onBitmapLoaded$lambda-40$lambda-39$lambda-38, reason: not valid java name */
    public static final void m847onBitmapLoaded$lambda40$lambda39$lambda38(PlaylistDetailFragment this$0, Throwable th) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.error(TAG, Intrinsics.stringPlus("Background is not loaded successfully. Error: ", th.getMessage()));
        ImageView imageView = this$0.backgroundImageView;
        if (imageView == null) {
            return;
        }
        imageView.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r1v2, types: [T, android.net.Uri] */
    /* JADX WARN: Type inference failed for: r8v3, types: [T, android.net.Uri] */
    public final void removeTrackFromPlaylist(Uri trackUri) {
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = trackUri.buildUpon().appendQueryParameter("udo", "true").build();
        if (CirrusMediaSource.match(trackUri)) {
            objectRef.element = PlaylistUtil.withAppendedScratchArgument(trackUri);
        }
        BuildersKt__Builders_commonKt.launch$default(Coroutines.INSTANCE.getIoScope(), null, null, new C03541(objectRef, null), 3, null);
    }

    /* JADX INFO: renamed from: com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment$removeTrackFromPlaylist$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PlaylistDetailFragment.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment$removeTrackFromPlaylist$1", f = "PlaylistDetailFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03541 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.ObjectRef<Uri> $deleteTrackUri;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03541(Ref.ObjectRef<Uri> objectRef, Continuation<? super C03541> continuation) {
            super(2, continuation);
            this.$deleteTrackUri = objectRef;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PlaylistDetailFragment.this.new C03541(this.$deleteTrackUri, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03541) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            ContentResolver contentResolver;
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            Context context = PlaylistDetailFragment.this.getContext();
            if (context != null && (contentResolver = context.getContentResolver()) != null) {
                Boxing.boxInt(contentResolver.delete(this.$deleteTrackUri.element, null, null));
            }
            return Unit.INSTANCE;
        }
    }

    @Override // com.amazon.mp3.catalog.fragment.LibraryBaseViewsFragment
    protected boolean shouldSupportRemoveTrackFromPlaylist() {
        return (this.isCatalog || MediaProvider.isSmartPlaylist(getContentUri())) ? false : true;
    }

    @Override // com.amazon.mp3.catalog.fragment.LibraryBaseViewsFragment
    protected boolean shouldSupportDeletePlaylist() {
        return !this.isCatalog;
    }

    /* JADX INFO: renamed from: isCatalogPlaylist, reason: from getter */
    public final boolean getIsCatalog() {
        return this.isCatalog;
    }

    public final String getPlaylistName() throws GUIMetadataNotReadyException {
        PageHeaderModel pageHeaderModel = getPageHeaderModel();
        String headlineTitle = pageHeaderModel == null ? null : pageHeaderModel.getHeadlineTitle();
        if (headlineTitle != null) {
            return headlineTitle;
        }
        throw new GUIMetadataNotReadyException("Playlist name not ready in Playlist detail page");
    }

    private final void initializeActionBarManager() {
        BaseViewsFilterActionBarManager baseViewsFilterActionBarManager = this.actionBarManager;
        if (baseViewsFilterActionBarManager != null) {
            getLifecycle().removeObserver(baseViewsFilterActionBarManager);
        }
        setActionBarManager(new BaseViewsFilterActionBarManager(this, this, getRecyclerView(), this.actionBarProvider));
        BaseViewsFilterActionBarManager baseViewsFilterActionBarManager2 = this.actionBarManager;
        if (baseViewsFilterActionBarManager2 == null) {
            return;
        }
        getLifecycle().addObserver(baseViewsFilterActionBarManager2);
    }

    private final Map<PageHeaderActionIconModel.ActionType, ActionIconOnClickListener> getPageHeaderOnClickListenerMap() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(PageHeaderActionIconModel.ActionType.PLAY, new ActionIconOnClickListener() { // from class: com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment.getPageHeaderOnClickListenerMap.1
            @Override // com.amazon.music.views.library.binders.ActionIconOnClickListener
            public void onClick(Context context, ContentMetadata metadata) {
                Intrinsics.checkNotNullParameter(context, "context");
                PlaylistMetadata playlistMetadata = metadata instanceof PlaylistMetadata ? (PlaylistMetadata) metadata : null;
                if (playlistMetadata == null) {
                    return;
                }
                PlaylistDetailFragment playlistDetailFragment = PlaylistDetailFragment.this;
                if (playlistDetailFragment.shouldBlockPlaybackForVideos()) {
                    playlistDetailFragment.displayExplicitContentFilteredDialog((PlaylistMetadata) metadata);
                    return;
                }
                HapticsUtil.INSTANCE.hapticFeedbackOnPlayback();
                playlistDetailFragment.emitUiClickMetric((PlaylistMetadata) metadata, false);
                PlaylistDetailFragment.onClickPlayback$default(playlistDetailFragment, playlistMetadata, false, false, 4, null);
            }
        });
        linkedHashMap.put(PageHeaderActionIconModel.ActionType.SHUFFLE, new LargeIconOnClickListener() { // from class: com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment.getPageHeaderOnClickListenerMap.2
            @Override // com.amazon.music.views.library.binders.LargeIconOnClickListener
            public void onClick(Context context, ContentMetadata metadata, boolean isLargeIcon) {
                Intrinsics.checkNotNullParameter(context, "context");
                PlaylistMetadata playlistMetadata = metadata instanceof PlaylistMetadata ? (PlaylistMetadata) metadata : null;
                if (playlistMetadata == null) {
                    return;
                }
                PlaylistDetailFragment playlistDetailFragment = PlaylistDetailFragment.this;
                if (!Feature.song_history_toolbar_disabled.isEnabled() || playlistMetadata.getIsAllOwned() || !MediaProvider.isSmartPlaylist(playlistMetadata.getUri())) {
                    if (playlistDetailFragment.shouldBlockPlaybackForVideos()) {
                        playlistDetailFragment.displayExplicitContentFilteredDialog((PlaylistMetadata) metadata);
                        return;
                    }
                    HapticsUtil.INSTANCE.hapticFeedbackOnPlayback();
                    playlistDetailFragment.emitUiClickMetric((PlaylistMetadata) metadata, true);
                    playlistDetailFragment.onClickPlayback(playlistMetadata, true, isLargeIcon);
                    return;
                }
                UpsellUtil.showBlockingUpsell(playlistDetailFragment.getActivity(), MediaProvider.Playlists.getCatalogPlaylistId(playlistDetailFragment.getPlaylistUriForQuery()), UpsellReason.SHUFFLE_ALL, UpsellSourceEntity.PLAYLIST_DETAIL);
            }
        });
        linkedHashMap.put(PageHeaderActionIconModel.ActionType.EDIT, new ActionIconOnClickListener() { // from class: com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment.getPageHeaderOnClickListenerMap.3
            @Override // com.amazon.music.views.library.binders.ActionIconOnClickListener
            public void onClick(Context context, ContentMetadata metadata) {
                Intrinsics.checkNotNullParameter(context, "context");
                PlaylistUtil.startEditPlaylist(PlaylistDetailFragment.this.getActivity(), PlaylistDetailFragment.this.getContentUri(), false);
            }
        });
        linkedHashMap.put(PageHeaderActionIconModel.ActionType.STATION, new LargeIconOnClickListener() { // from class: com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment.getPageHeaderOnClickListenerMap.4
            @Override // com.amazon.music.views.library.binders.LargeIconOnClickListener
            public void onClick(Context context, ContentMetadata metadata, boolean isLargeIcon) {
                Intrinsics.checkNotNullParameter(context, "context");
                PlaylistMetadata playlistMetadata = metadata instanceof PlaylistMetadata ? (PlaylistMetadata) metadata : null;
                String asin = playlistMetadata != null ? playlistMetadata.getAsin() : null;
                PlaylistDetailFragment playlistDetailFragment = PlaylistDetailFragment.this;
                if (metadata != null) {
                    playlistDetailFragment.emitUiClickMetric((PlaylistMetadata) metadata, false);
                    PlaylistDetailFragment.this.startSFA(asin);
                    return;
                }
                throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.metadata.PlaylistMetadata");
            }
        });
        ActionIconOnClickListener actionIconOnClickListener = new ActionIconOnClickListener() { // from class: com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment$getPageHeaderOnClickListenerMap$followActionIconOnClickListener$1
            @Override // com.amazon.music.views.library.binders.ActionIconOnClickListener
            public void onClick(Context context, ContentMetadata metadata) {
                Intrinsics.checkNotNullParameter(context, "context");
                PlaylistMetadata playlistMetadata = metadata instanceof PlaylistMetadata ? (PlaylistMetadata) metadata : null;
                if (playlistMetadata == null) {
                    return;
                }
                PlaylistDetailFragment playlistDetailFragment = this.this$0;
                if (Intrinsics.areEqual(playlistMetadata.getIsFollowed(), Boolean.TRUE)) {
                    FragmentActivity activity = playlistDetailFragment.getActivity();
                    if (activity == null) {
                        return;
                    }
                    PlaylistMetadata playlistMetadata2 = (PlaylistMetadata) metadata;
                    new PlaylistUnfollowAction(activity, playlistMetadata2.getAsin(), playlistMetadata2.getLuid(), CirrusMediaSource.matchCloud(playlistDetailFragment.getContentUri())).unfollowPlaylist(playlistDetailFragment.playlistUnfollowActionCallback);
                    return;
                }
                if (Intrinsics.areEqual(playlistMetadata.getIsFollowed(), Boolean.FALSE)) {
                    playlistDetailFragment.followPlaylist(context, metadata);
                }
            }
        };
        linkedHashMap.put(PageHeaderActionIconModel.ActionType.FOLLOW, actionIconOnClickListener);
        linkedHashMap.put(PageHeaderActionIconModel.ActionType.FAVORITE, actionIconOnClickListener);
        linkedHashMap.put(PageHeaderActionIconModel.ActionType.SHARE, new ActionIconOnClickListener() { // from class: com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment.getPageHeaderOnClickListenerMap.5
            @Override // com.amazon.music.views.library.binders.ActionIconOnClickListener
            public void onClick(Context context, ContentMetadata metadata) {
                Intrinsics.checkNotNullParameter(context, "context");
                PlaylistDetailFragment.this.sharePlaylist();
            }
        });
        linkedHashMap.put(PageHeaderActionIconModel.ActionType.DOWNLOAD, new DownloadIconOnClickListener() { // from class: com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment.getPageHeaderOnClickListenerMap.6
            @Override // com.amazon.music.views.library.binders.DownloadIconOnClickListener
            public void onClick(Context context, ContentMetadata metadata, DownloadStateModel.DownloadState downloadState) {
                Intrinsics.checkNotNullParameter(context, "context");
                Intrinsics.checkNotNullParameter(downloadState, "downloadState");
                FragmentActivity activity = PlaylistDetailFragment.this.getActivity();
                if (activity == null) {
                    return;
                }
                PlaylistDetailFragment playlistDetailFragment = PlaylistDetailFragment.this;
                if (metadata == null) {
                    return;
                }
                boolean z = !playlistDetailFragment.isCatalog;
                if (downloadState != DownloadStateModel.DownloadState.DOWNLOADED) {
                    Uri uri = metadata.getUri();
                    PageType pageType = playlistDetailFragment.pageType;
                    if (pageType == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("pageType");
                        pageType = null;
                    }
                    new PlaylistDownloadAction(activity, uri, pageType, playlistDetailFragment.isCatalog).handleDownloadBtnClick(metadata instanceof PlaylistMetadata ? (PlaylistMetadata) metadata : null, playlistDetailFragment.shouldBlockPlaybackAndDownload(metadata, playlistDetailFragment.isSourceLocal(), false), downloadState, playlistDetailFragment.playlistFollowActionCallback);
                    return;
                }
                if (MediaProvider.isSmartPlaylist(metadata.getUri()) || !z) {
                    return;
                }
                playlistDetailFragment.deleteOfflinePlaylist(metadata instanceof PlaylistMetadata ? (PlaylistMetadata) metadata : null, activity);
            }
        });
        return linkedHashMap;
    }

    static /* synthetic */ void onClickPlayback$default(PlaylistDetailFragment playlistDetailFragment, PlaylistMetadata playlistMetadata, boolean z, boolean z2, int i, Object obj) {
        if ((i & 4) != 0) {
            z2 = false;
        }
        playlistDetailFragment.onClickPlayback(playlistMetadata, z, z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onClickPlayback(PlaylistMetadata metadata, boolean isShuffled, boolean isLargeIcon) {
        if (this.isOnDemandGoldenPlaylist && !Feature.should_migrate_free_to_cq_for_non_station.isEnabled()) {
            String asin = metadata.getAsin();
            if (asin == null) {
                return;
            }
            beginOnDemandGoldenPlaylistPlayback(asin, isShuffled);
            return;
        }
        PlaylistDetailViewModel playlistDetailViewModel = this.playlistDetailViewModel;
        startPlayback(metadata, isShuffled, isLargeIcon, playlistDetailViewModel == null ? null : playlistDetailViewModel.getFilteredTrackCount(getPageModel()));
    }

    private final void startPlayback(ContentMetadata metadata, boolean isShuffled, boolean isLargeIcon, Integer trackCount) {
        if (metadata == null) {
            return;
        }
        PlaylistDetailViewModel playlistDetailViewModel = this.playlistDetailViewModel;
        if ((playlistDetailViewModel != null && playlistDetailViewModel.isFreeTier()) && isShuffled) {
            boolean z = metadata instanceof PlaylistMetadata;
            PlaylistMetadata playlistMetadata = z ? (PlaylistMetadata) metadata : null;
            if (((playlistMetadata == null || playlistMetadata.getIsAllOwned()) ? false : true) && !Feature.should_migrate_free_to_cq_for_non_station.isEnabled()) {
                PlaylistMetadata playlistMetadata2 = z ? (PlaylistMetadata) metadata : null;
                startSFA(playlistMetadata2 != null ? playlistMetadata2.getAsin() : null);
                return;
            }
        }
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        PlaylistPlaybackAction playlistPlaybackAction = new PlaylistPlaybackAction(activity, this.isCatalog, isPlaylistDownloaded(), shouldBlockPlaybackAndDownload(metadata, isSourceLocal(), isShuffled), getTrackInformationFetcher(), false, 32, null);
        PlaylistDetailViewModel playlistDetailViewModel2 = this.playlistDetailViewModel;
        Integer numFirstAvailableTrackIndex = playlistDetailViewModel2 == null ? null : playlistDetailViewModel2.firstAvailableTrackIndex(getPageModel());
        PlaylistDetailViewModel playlistDetailViewModel3 = this.playlistDetailViewModel;
        playlistPlaybackAction.startPlayback(metadata, isShuffled, (voOSType.VOOSMP_PID_ANALYTICS_AGENT & 4) != 0 ? null : numFirstAvailableTrackIndex, (voOSType.VOOSMP_PID_ANALYTICS_AGENT & 8) != 0 ? null : trackCount, (voOSType.VOOSMP_PID_ANALYTICS_AGENT & 16) != 0 ? false : false, true, (voOSType.VOOSMP_PID_ANALYTICS_AGENT & 64) != 0 ? null : playlistDetailViewModel3 == null ? null : playlistDetailViewModel3.sortOrder(), (voOSType.VOOSMP_PID_ANALYTICS_AGENT & 128) != 0 ? null : null, (voOSType.VOOSMP_PID_ANALYTICS_AGENT & 256) != 0 ? null : null);
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected boolean shouldBlockPlaybackAndDownload(ContentMetadata metadata, boolean isSourceLocal, boolean isShuffled) {
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        PlaylistDetailViewModel playlistDetailViewModel = this.playlistDetailViewModel;
        if (!(playlistDetailViewModel != null && playlistDetailViewModel.isPartiallyOwned()) || isShuffled) {
            return super.shouldBlockPlaybackAndDownload(metadata, isSourceLocal, isShuffled);
        }
        return false;
    }

    private final void beginOnDemandGoldenPlaylistPlayback(String asin, boolean isShuffled) {
        GetOnDemandPlaylistsMetricsUtil.EventType eventType = GetOnDemandPlaylistsMetricsUtil.EventType.EVENT_AUTH;
        PlayableEntityIdentifier playableEntityIdentifier = new PlayableEntityIdentifier(asin, PlayableEntityIdentifierType.ASIN);
        PlaybackPageType playbackPageType = PlaybackPageType.PLAYLIST_DETAIL;
        MediaCollectionType mediaCollectionType = MediaCollectionType.GOLDEN_PLAYLIST;
        if (isShuffled) {
            FreeTierPlaybackUtil.playCloudStation(getContext(), playableEntityIdentifier, true, true, true, playbackPageType, mediaCollectionType);
        } else {
            FreeTierPlaybackUtil.startPlaylistOnDemand(getContext(), playableEntityIdentifier, true, true, true, playbackPageType, mediaCollectionType, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sharePlaylist() {
        Uri playlistUriForQuery;
        FragmentActivity activity = getActivity();
        if (activity == null || (playlistUriForQuery = getPlaylistUriForQuery()) == null) {
            return;
        }
        new PlaylistShareAction(activity, playlistUriForQuery, this.isCatalog).sharePlaylist();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startSFA(String playlistAsin) {
        Uri playlistUriForQuery;
        PlaylistDetailViewModel playlistDetailViewModel;
        List<String> listRetrievePlaylistTrackAsinList$default;
        FragmentActivity activity = getActivity();
        if (activity == null || (playlistUriForQuery = getPlaylistUriForQuery()) == null) {
            return;
        }
        PageType pageType = this.pageType;
        if (pageType == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pageType");
            pageType = null;
        }
        PlaylistSFAAction playlistSFAAction = new PlaylistSFAAction(activity, playlistUriForQuery, pageType);
        if (this.isCatalog) {
            if (MediaProvider.PrimePlaylists.isPrimePlaylist(playlistUriForQuery)) {
                if (UserSubscriptionUtil.isNightwingOnly()) {
                    playlistSFAAction.startPrimePlaylistSFAForFreeTier();
                    return;
                } else if (playlistAsin != null) {
                    playlistSFAAction.startPrimePlaylistSFA(playlistAsin);
                    return;
                } else {
                    Log.warning(TAG, "startSFA skipped, playlistAsin is null");
                    return;
                }
            }
            if (!MediaProvider.SharedUserPlaylists.isSharedUserPlaylist(playlistUriForQuery) || (playlistDetailViewModel = this.playlistDetailViewModel) == null || (listRetrievePlaylistTrackAsinList$default = PlaylistDetailViewModel.retrievePlaylistTrackAsinList$default(playlistDetailViewModel, null, 1, null)) == null) {
                return;
            }
            playlistSFAAction.startSharedUserPlaylistSFA(listRetrievePlaylistTrackAsinList$default);
            return;
        }
        PlaylistSFAAction.startLibraryPlaylistSFA$default(playlistSFAAction, playlistAsin, false, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void followPlaylist(Context context, ContentMetadata metadata) {
        PlaylistMetadata playlistMetadata = metadata instanceof PlaylistMetadata ? (PlaylistMetadata) metadata : null;
        if (playlistMetadata != null) {
            AbstractBaseViewsFragment.sendUiClickMetric$default(this, ActionType.ADD_PLAYLIST_TO_LIBRARY, null, getPageType(), null, playlistMetadata.getAsin(), EntityIdType.ASIN, null, null, null, null, null, null, 4042, null);
        }
        PlaylistFollowAction playlistFollowAction = new PlaylistFollowAction(context, playlistMetadata);
        playlistFollowAction.setListener(this.playlistFollowActionCallback);
        if (playlistFollowAction.canFollowPlaylist()) {
            PlaylistFollowAction.followPlaylist$default(playlistFollowAction, null, 1, null);
        }
    }

    private final boolean isPlaylistDownloaded() {
        if (!isSourceLocal()) {
            PlaylistDetailViewModel playlistDetailViewModel = this.playlistDetailViewModel;
            if (!(playlistDetailViewModel == null ? false : playlistDetailViewModel.isPlaylistDownloaded())) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Uri getPlaylistUriForQuery() {
        if (this.isCatalog) {
            return getContentUri();
        }
        Uri contentUri = getContentUri();
        if (contentUri == null) {
            return null;
        }
        List<String> pathSegments = contentUri.getPathSegments();
        Intrinsics.checkNotNullExpressionValue(pathSegments, "it.pathSegments");
        String str = pathSegments.get(1);
        String str2 = pathSegments.get(3);
        long j = Long.parseLong(pathSegments.get(4));
        if (Intrinsics.areEqual(str2, "smart")) {
            Uri contentUri2 = MediaProvider.SmartPlaylists.getContentUri(str, j);
            Intrinsics.checkNotNullExpressionValue(contentUri2, "{\n                    Sm…ce, id)\n                }");
            return contentUri2;
        }
        if (Intrinsics.areEqual(str2, "udo")) {
            Uri contentUri3 = MediaProvider.UdoPlaylists.getContentUri(str, j);
            Intrinsics.checkNotNullExpressionValue(contentUri3, "{\n                    Ud…ce, id)\n                }");
            return contentUri3;
        }
        throw new IllegalArgumentException("Unknown playlist type!");
    }

    static /* synthetic */ void showPlaylistNotFoundDialog$default(PlaylistDetailFragment playlistDetailFragment, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        playlistDetailFragment.showPlaylistNotFoundDialog(z);
    }

    private final void showPlaylistNotFoundDialog(boolean isPrivacyError) {
        int i;
        int i2;
        int i3;
        if (isPrivacyError) {
            i = R.string.dmusic_playlist_privacy_playlist_unavailable_dialog_title;
            i2 = R.string.dmusic_playlist_privacy_playlist_unavailable_dialog_description;
            i3 = R.string.dmusic_okay_text;
        } else {
            i = R.string.dmusic_dialog_prime_playlist_not_found_title;
            i2 = R.string.dmusic_dialog_prime_playlist_not_found_message;
            i3 = R.string.dmusic_dialog_prime_not_found_button;
        }
        showErrorDialog(i, i2, i3);
    }

    private final void showErrorDialog(int dialogTitleStringId, int dialogDescriptionStringId, int confirmButtonStringId) {
        hideEmptyView();
        new OneButtonErrorDialog(getActivity(), getString(dialogTitleStringId), getString(dialogDescriptionStringId), new ErrorDialogButton(getString(confirmButtonStringId), new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment$$ExternalSyntheticLambda10
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                PlaylistDetailFragment.m850showErrorDialog$lambda50(this.f$0, dialogInterface, i);
            }
        })).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showErrorDialog$lambda-50, reason: not valid java name */
    public static final void m850showErrorDialog$lambda50(PlaylistDetailFragment this$0, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        PrimeTabFragmentHost primeTabFragmentHostNewInstance = PrimeTabFragmentHost.newInstance(new Bundle());
        KeyEventDispatcher.Component activity = this$0.getActivity();
        NavigationProvider navigationProvider = activity instanceof NavigationProvider ? (NavigationProvider) activity : null;
        if (navigationProvider == null) {
            return;
        }
        navigationProvider.changeScreenFragment(primeTabFragmentHostNewInstance, false, true, false);
    }

    private final void updateContentUriOnAsinChanged(PlaylistMetadata playlistMetadata) {
        Uri contentUri;
        CatalogPlaylist catalogPlaylist = getCatalogPlaylist(playlistMetadata.getUri());
        if (catalogPlaylist == null || catalogPlaylist.getAsin() == null) {
            return;
        }
        if (catalogPlaylist.isSharedUserPlaylist() || catalogPlaylist.isEntityPlaylist()) {
            contentUri = MediaProvider.SharedUserPlaylists.getContentUri(catalogPlaylist.getSource(), catalogPlaylist.getAsin());
        } else {
            contentUri = MediaProvider.PrimePlaylists.getContentUri(catalogPlaylist.getSource(), catalogPlaylist.getAsin());
        }
        if (contentUri == null || contentUri.getPath() == null) {
            return;
        }
        String path = contentUri.getPath();
        Uri contentUri2 = getContentUri();
        Intrinsics.checkNotNull(contentUri2);
        if (Intrinsics.areEqual(path, contentUri2.getPath())) {
            return;
        }
        Log.debug(TAG, "Reset PrimePlaylist contentUri from %s to %s", getContentUri(), contentUri);
        setContentUri(contentUri);
        registerDownloadStateListener();
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        refreshPageGridViewModel(activity);
    }

    private final void registerDownloadStateListener() {
        PlaylistDetailViewModel playlistDetailViewModel = this.playlistDetailViewModel;
        if (playlistDetailViewModel != null) {
            playlistDetailViewModel.unregisterDownloadStateListener();
        }
        PageViewModelProvider pageModelProvider = getPageModelProvider();
        PlaylistDetailViewModel playlistDetailViewModel2 = pageModelProvider instanceof PlaylistDetailViewModel ? (PlaylistDetailViewModel) pageModelProvider : null;
        this.playlistDetailViewModel = playlistDetailViewModel2;
        if (playlistDetailViewModel2 == null) {
            return;
        }
        playlistDetailViewModel2.registerDownloadStateListener(getContentUri());
    }

    private final CatalogPlaylist getCatalogPlaylist() {
        if (this.mCatalogPlaylist != null || !getIsCatalog()) {
            return this.mCatalogPlaylist;
        }
        PlaylistDetailViewModel playlistDetailViewModel = this.playlistDetailViewModel;
        AbstractItem playlist = playlistDetailViewModel == null ? null : playlistDetailViewModel.getPlaylist();
        CatalogPlaylist catalogPlaylist = playlist instanceof CatalogPlaylist ? (CatalogPlaylist) playlist : AmazonApplication.getLibraryItemFactory().getCatalogPlaylist(getContentUri());
        this.mCatalogPlaylist = catalogPlaylist;
        return catalogPlaylist;
    }

    private final CatalogPlaylist getCatalogPlaylist(Uri uri) {
        return AmazonApplication.getLibraryItemFactory().getCatalogPlaylist(uri);
    }

    @Override // com.amazon.mp3.detailpages.album.click.CollectionTrackClickListener.CollectionInformationFetcher
    public Observable<MusicTrack> getTrack(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        return getTrackInformationFetcher().getTrack(uri);
    }

    @Override // com.amazon.mp3.detailpages.album.click.CollectionTrackClickListener.CollectionInformationFetcher
    public Observable<List<MusicTrack>> getAllTracks() {
        return getTrackInformationFetcher().getAllTracks();
    }

    @Override // com.amazon.mp3.detailpages.album.click.CollectionTrackClickListener.CollectionInformationFetcher
    public String getSortOrder() {
        if (this.isCatalog) {
            String primePlaylistSortOrder = CirrusMediaSource.getPrimePlaylistSortOrder();
            Intrinsics.checkNotNullExpressionValue(primePlaylistSortOrder, "{\n            CirrusMedi…listSortOrder()\n        }");
            return primePlaylistSortOrder;
        }
        String udoPlaylistSortOrder = CirrusMediaSource.getUdoPlaylistSortOrder();
        Intrinsics.checkNotNullExpressionValue(udoPlaylistSortOrder, "{\n            CirrusMedi…listSortOrder()\n        }");
        return udoPlaylistSortOrder;
    }

    @Override // com.amazon.mp3.detailpages.album.click.CollectionTrackClickListener.CollectionInformationFetcher
    public Uri getCollectionUri() {
        return getContentUri();
    }

    public String getCollectionId() {
        PlaylistMetadata playlistMetadata$default;
        String string;
        PlaylistDetailViewModel playlistDetailViewModel = this.playlistDetailViewModel;
        if (playlistDetailViewModel == null || (playlistMetadata$default = PlaylistDetailViewModel.getPlaylistMetadata$default(playlistDetailViewModel, null, 1, null)) == null) {
            return null;
        }
        if (this.isCatalog) {
            string = playlistMetadata$default.getAsin();
        } else {
            String asin = playlistMetadata$default.getAsin();
            if (asin != null) {
                return asin;
            }
            string = playlistMetadata$default.getUri().toString();
            Intrinsics.checkNotNullExpressionValue(string, "metadata.uri.toString()");
        }
        return string;
    }

    private final String getEntityId() {
        PlaylistMetadata playlistMetadata = getPlaylistMetadata();
        if (playlistMetadata == null) {
            return null;
        }
        if (this.isCatalog) {
            return playlistMetadata.getAsin();
        }
        return playlistMetadata.getLuid();
    }

    private final EntityIdType getEntityIdType() {
        if (getPlaylistMetadata() == null) {
            return null;
        }
        if (this.isCatalog) {
            return EntityIdType.ASIN;
        }
        return EntityIdType.PLAYLIST_ID;
    }

    @Override // com.amazon.mp3.detailpages.album.click.CollectionTrackClickListener.CollectionInformationFetcher
    public String getCollectionName() {
        PlaylistMetadata playlistMetadata$default;
        String title;
        PlaylistDetailViewModel playlistDetailViewModel = this.playlistDetailViewModel;
        return (playlistDetailViewModel == null || (playlistMetadata$default = PlaylistDetailViewModel.getPlaylistMetadata$default(playlistDetailViewModel, null, 1, null)) == null || (title = playlistMetadata$default.getTitle()) == null) ? "" : title;
    }

    @Override // com.amazon.mp3.detailpages.album.click.CollectionTrackClickListener.CollectionInformationFetcher
    public String getSelection(List<? extends MusicTrack> trackList) {
        Intrinsics.checkNotNullParameter(trackList, "trackList");
        if (this.isCatalog || isSourceLocal()) {
            return null;
        }
        return PlaylistUtil.getSelection(getContentUri());
    }

    @Override // com.amazon.mp3.detailpages.album.click.CollectionTrackClickListener.CollectionInformationFetcher
    public String[] getSelectionArgs(List<? extends MusicTrack> trackList) {
        Intrinsics.checkNotNullParameter(trackList, "trackList");
        if (this.isCatalog || isSourceLocal()) {
            return null;
        }
        return PlaylistUtil.getSelectionArgs(getContentUri());
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected PageLoadLatencyCode getPageLoadLatencyCode() {
        return PageLoadLatencyCode.PLAYLIST_DETAIL;
    }

    private final void handleDeeplink() {
        String str;
        int iIndexOf;
        boolean z;
        FragmentActivity activity;
        ContentMetadata contentMetadata = getContentMetadata();
        Bundle arguments = getArguments();
        if (arguments == null || !arguments.containsKey("com.amazon.mp3.library.fragment.EXTRA_DEEPLINK_ACTION")) {
            return;
        }
        String string = arguments.getString("com.amazon.mp3.library.fragment.EXTRA_DEEPLINK_TRACK_ASIN");
        PlaylistDetailViewModel playlistDetailViewModel = this.playlistDetailViewModel;
        List listRetrievePlaylistTrackAsinList$default = playlistDetailViewModel == null ? null : PlaylistDetailViewModel.retrievePlaylistTrackAsinList$default(playlistDetailViewModel, null, 1, null);
        if (string == null || listRetrievePlaylistTrackAsinList$default == null) {
            str = null;
            iIndexOf = 0;
            z = false;
        } else {
            iIndexOf = listRetrievePlaylistTrackAsinList$default.indexOf(string);
            if (iIndexOf == -1) {
                string = null;
                iIndexOf = 0;
            }
            str = string;
            z = true;
        }
        if (contentMetadata == null || !(contentMetadata instanceof PlaylistMetadata) || (activity = getActivity()) == null) {
            return;
        }
        BaseViewsDeeplinkActionHandler.INSTANCE.handlePlaylistDeeplinkAction((PlaylistMetadata) contentMetadata, arguments, new PlaylistPlaybackAction(activity, this.isCatalog, isPlaylistDownloaded(), shouldBlockPlaybackAndDownload(contentMetadata, isSourceLocal(), false), getTrackInformationFetcher(), this.isOnDemandGoldenPlaylist), Integer.valueOf(iIndexOf), str, z, activity);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void deleteOfflinePlaylist(PlaylistMetadata metadata, final FragmentActivity activity) {
        if (metadata == null) {
            return;
        }
        Uri offlineUri = MediaProvider.UdoPlaylists.getContentUri("cirrus-local", PlaylistUtil.getPlaylistId(metadata.getUri()));
        String title = metadata.getTitle();
        if (title == null) {
            title = "";
        }
        Intrinsics.checkNotNullExpressionValue(offlineUri, "offlineUri");
        new DeleteUserPlaylistAction(activity, offlineUri, title).perform(new Function0<Unit>() { // from class: com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment$deleteOfflinePlaylist$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                if (this.this$0.isSourceLocal()) {
                    activity.getSupportFragmentManager().popBackStack();
                }
            }
        });
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected List<BaseViewModel> getTrackList() {
        List<BaseViewModel> models;
        PageGridViewModel pageModel = getPageModel();
        ArrayList arrayList = null;
        if (pageModel != null && (models = pageModel.getModels()) != null) {
            arrayList = new ArrayList();
            for (Object obj : models) {
                if (obj instanceof VisualRowItemModel) {
                    arrayList.add(obj);
                }
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void displayExplicitContentFilteredDialog(PlaylistMetadata metadata) {
        ActivityKt.showDialogFragment(getActivity(), ContentEnabilityDialogUtil.getDialogForDisabledReason(getContext(), ContentUnavailableReason.EXPLICIT_CONTENT_FILTERED, null, null, metadata, ContentAccessUtil.ContentAccessOperation.STREAM));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean shouldBlockPlaybackForVideos() {
        return this.isVideoPlaylist && AmazonApplication.getCapabilities().isExplicitLanguageFilterEnabled();
    }

    public final void removeSuggestionAndUpdate(String asin) {
        PlaylistDetailViewModel playlistDetailViewModel;
        CuratePlaylistProvider curatePlaylistProvider;
        if (!(asin == null || StringsKt__StringsJVMKt.isBlank(asin)) && (playlistDetailViewModel = this.playlistDetailViewModel) != null && (curatePlaylistProvider = playlistDetailViewModel.getCuratePlaylistProvider()) != null) {
            curatePlaylistProvider.clearSuggestionByAsin(asin);
        }
        PageViewModelProvider pageModelProvider = getPageModelProvider();
        if (pageModelProvider == null) {
            return;
        }
        PageViewModelProvider.DefaultImpls.fetchUpdates$default(pageModelProvider, 20, getDiffUtilFactory(), null, 4, null);
    }

    private final void fetchSuggestionModels() {
        Context applicationContext = AmazonApplication.getApplication().getApplicationContext();
        CurateEndpointSettingsProvider curateEndpointSettingsProvider = new CurateEndpointSettingsProvider();
        CurateFragmentsStackProvider curateFragmentsStackProvider = CurateFragmentsStackProvider.INSTANCE;
        CurateLibraryNavigationProvider curateLibraryNavigationProvider = new CurateLibraryNavigationProvider(new WeakReference(getActivity()));
        CurateNetworkProvider curateNetworkProvider = new CurateNetworkProvider();
        CurateStyleSheetProvider curateStyleSheetProvider = new CurateStyleSheetProvider();
        CuratePlaybackProvider curatePlaybackProvider = new CuratePlaybackProvider();
        CurateSubscriptionProvider curateSubscriptionProvider = new CurateSubscriptionProvider();
        PlaylistDetailViewModel playlistDetailViewModel = this.playlistDetailViewModel;
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), null, new C03481(applicationContext, this, new CurateBootstrapProviders(applicationContext, curateEndpointSettingsProvider, curateFragmentsStackProvider, curateLibraryNavigationProvider, curateNetworkProvider, curateStyleSheetProvider, curatePlaybackProvider, curateSubscriptionProvider, playlistDetailViewModel == null ? null : playlistDetailViewModel.getCuratePlaylistProvider(), new CurateFeatureFlagProvider()), null), 2, null);
    }

    /* JADX INFO: renamed from: com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment$fetchSuggestionModels$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PlaylistDetailFragment.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment$fetchSuggestionModels$1", f = "PlaylistDetailFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03481 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Context $context;
        final /* synthetic */ CurateBootstrapProviders $providers;
        int label;
        final /* synthetic */ PlaylistDetailFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03481(Context context, PlaylistDetailFragment playlistDetailFragment, CurateBootstrapProviders curateBootstrapProviders, Continuation<? super C03481> continuation) {
            super(2, continuation);
            this.$context = context;
            this.this$0 = playlistDetailFragment;
            this.$providers = curateBootstrapProviders;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C03481(this.$context, this.this$0, this.$providers, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03481) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            String playlistLuid = PlaylistUtil.getPlaylistLuid(this.$context, this.this$0.getContentUri());
            if (playlistLuid == null) {
                playlistLuid = "";
            }
            if (!this.this$0.isFetchingSuggestions) {
                this.this$0.isFetchingSuggestions = true;
                Curate.create(this.$providers).recommendationsWidget(playlistLuid);
            }
            this.this$0.isFetchingSuggestions = false;
            return Unit.INSTANCE;
        }
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected void onLoadUpdatedPage() {
        PageFetchInfo lastFetchedPageInfo;
        PlaylistDetailViewModel playlistDetailViewModel = this.playlistDetailViewModel;
        IntRange indicesChanged = null;
        if (playlistDetailViewModel != null && (lastFetchedPageInfo = playlistDetailViewModel.getLastFetchedPageInfo()) != null) {
            indicesChanged = lastFetchedPageInfo.getIndicesChanged();
        }
        if (indicesChanged != null) {
            onLoadPageRangeChanged(indicesChanged);
        } else {
            super.onLoadUpdatedPage();
        }
    }

    @Override // com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment
    protected void onLoadPageRangeChanged(IntRange indicesChanged) {
        Intrinsics.checkNotNullParameter(indicesChanged, "indicesChanged");
        super.onLoadPageRangeChanged(indicesChanged);
        PlaylistDetailViewModel playlistDetailViewModel = this.playlistDetailViewModel;
        if (playlistDetailViewModel == null) {
            return;
        }
        playlistDetailViewModel.onLoadPageRangeChanged(indicesChanged);
    }

    private final void refreshTracks() {
        final HashSet hashSet = new HashSet();
        final PlaybackDatabaseCallbackImpl playbackDatabaseCallbackImpl = new PlaybackDatabaseCallbackImpl(this);
        ThreadUtils.postInBackground(new Runnable() { // from class: com.amazon.mp3.detailpages.playlist.PlaylistDetailFragment$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() throws ServiceException, AbstractHttpClient.HttpClientException {
                PlaylistDetailFragment.m849refreshTracks$lambda59(this.f$0, hashSet, playbackDatabaseCallbackImpl);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: refreshTracks$lambda-59, reason: not valid java name */
    public static final void m849refreshTracks$lambda59(PlaylistDetailFragment this$0, HashSet playlistIdSet, PlaybackDatabaseCallbackImpl callback) throws ServiceException, AbstractHttpClient.HttpClientException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(playlistIdSet, "$playlistIdSet");
        Intrinsics.checkNotNullParameter(callback, "$callback");
        playlistIdSet.add(PlaylistUtil.getPlaylistLuid(this$0.getContext(), this$0.getContentUri()));
        PlaylistSyncHelper.syncUpdatedPlaylist(this$0.getActivity(), playlistIdSet, callback, null, true);
    }

    /* JADX INFO: compiled from: PlaylistDetailFragment.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lcom/amazon/mp3/detailpages/playlist/PlaylistDetailFragment$PlaybackDatabaseCallbackImpl;", "Lcom/amazon/mp3/library/service/sync/PlaylistDatabaseCallback;", "(Lcom/amazon/mp3/detailpages/playlist/PlaylistDetailFragment;)V", "onUpdated", "", "numberOfUpdatedTracks", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class PlaybackDatabaseCallbackImpl implements PlaylistDatabaseCallback {
        final /* synthetic */ PlaylistDetailFragment this$0;

        public PlaybackDatabaseCallbackImpl(PlaylistDetailFragment this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
        }

        @Override // com.amazon.mp3.library.service.sync.PlaylistDatabaseCallback
        public void onUpdated(int numberOfUpdatedTracks) {
            PlaylistSyncHelper.autodownloadUnfinishedUnifiedPlaylists(this.this$0.getContext());
            PageViewModelProvider pageModelProvider = this.this$0.getPageModelProvider();
            if (pageModelProvider == null) {
                return;
            }
            PageViewModelProvider.DefaultImpls.fetchUpdates$default(pageModelProvider, 20, this.this$0.getDiffUtilFactory(), null, 4, null);
        }
    }
}
