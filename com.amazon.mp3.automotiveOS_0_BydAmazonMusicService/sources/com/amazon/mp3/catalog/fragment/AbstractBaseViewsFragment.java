package com.amazon.mp3.catalog.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.RelativeLayout;
import androidx.core.view.ViewGroupKt;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.card.prime.ContextMenuHandler;
import com.amazon.mp3.card.prime.ContextMenuSupportingFragment;
import com.amazon.mp3.catalog.fragment.adapter.BaseRecyclerViewProvider;
import com.amazon.mp3.catalog.fragment.adapter.BaseViewsBinderConfiguration;
import com.amazon.mp3.catalog.fragment.adapter.BaseViewsDiffUtilFactory;
import com.amazon.mp3.catalog.fragment.adapter.DefaultRecyclerViewProvider;
import com.amazon.mp3.catalog.fragment.datasource.repository.PageDataRepository;
import com.amazon.mp3.catalog.fragment.viewmodel.PageViewModelProvider;
import com.amazon.mp3.cloudqueue.FreeTierPlaybackUtil;
import com.amazon.mp3.dialog.NoNetworkEmptyPageDialog;
import com.amazon.mp3.library.provider.LibraryStateProvider;
import com.amazon.mp3.providers.DefaultAddToLibraryProvider;
import com.amazon.mp3.providers.DefaultCachedArtworkProvider;
import com.amazon.mp3.providers.DefaultContentEligibilityProvider;
import com.amazon.mp3.providers.DefaultTrackDownloadButtonProvider;
import com.amazon.mp3.providers.MetadataContextMenuHandler;
import com.amazon.mp3.providers.MissingSDCardHandler;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.FastScrollerHelper;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.MetricsUtil;
import com.amazon.mp3.util.ScreenUtil;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.account.AccountStateChangeListener;
import com.amazon.music.account.AccountStateField;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.latency.LatencyEventDescriptor;
import com.amazon.music.metrics.mts.event.definition.latency.LatencyTracker;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiPageViewEvent;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.LatencyTrackingLeg;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.PageLoadLatencyCode;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.picassoimageloader.PicassoImageLoader;
import com.amazon.music.views.library.binders.UniversalBinder;
import com.amazon.music.views.library.deeplinks.ContentMetadataOnClickListener;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import com.amazon.music.views.library.models.ErrorModel;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.base.BaseViewContentModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.providers.ContentEnabilityProvider;
import com.amazon.music.views.library.providers.RowButtonOnClickProvider;
import com.amazon.music.views.library.providers.TrackDownloadButtonProvider;
import com.amazon.music.views.library.recyclerview.BaseViewModelAdapterProvider;
import com.amazon.music.views.library.recyclerview.EndlessScrollListener;
import com.amazon.music.views.library.recyclerview.SimpleItemTouchHelperCallback;
import com.amazon.music.views.library.recyclerview.indexer.FastScroller;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.views.BauhausLoadingView;
import com.amazon.music.views.library.views.DefaultPageHeaderView;
import com.google.android.gms.common.internal.ImagesContract;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: compiled from: AbstractBaseViewsFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000è\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\"\n\u0002\u0018\u0002\n\u0002\b\"\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\b&\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\t¢\u0006\u0006\b\u0090\u0002\u0010\u0091\u0002J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J\b\u0010\t\u001a\u00020\bH\u0002J\b\u0010\u000b\u001a\u00020\nH\u0002J\b\u0010\f\u001a\u00020\bH\u0002J\b\u0010\r\u001a\u00020\nH\u0002J\u0012\u0010\u0010\u001a\u00020\n2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000eH\u0016J\b\u0010\u0011\u001a\u00020\bH\u0014J\u0010\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u0012H\u0004J\b\u0010\u0015\u001a\u00020\nH\u0016J\u0012\u0010\u0016\u001a\u00020\n2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000eH\u0016J\b\u0010\u0017\u001a\u00020\nH\u0016J\b\u0010\u0018\u001a\u00020\nH\u0016J\b\u0010\u0019\u001a\u00020\nH\u0004J&\u0010\u001f\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\u001b\u001a\u00020\u001a2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000eH\u0016J\b\u0010 \u001a\u00020\nH\u0014J\u001a\u0010#\u001a\u00020\n2\b\u0010!\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\"\u001a\u00020\u001cH\u0014J\u0014\u0010%\u001a\u0004\u0018\u00010\u001e2\b\u0010$\u001a\u0004\u0018\u00010\u001cH\u0014J\b\u0010'\u001a\u00020&H\u0014J\u0010\u0010(\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u0004H&J\n\u0010*\u001a\u0004\u0018\u00010)H&J\u0016\u0010-\u001a\u0010\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030,0+H&J\n\u0010/\u001a\u0004\u0018\u00010.H&J\b\u00101\u001a\u000200H&J\b\u00103\u001a\u000202H&J\n\u00105\u001a\u0004\u0018\u000104H&J\b\u00106\u001a\u00020\nH&J\u001a\u00107\u001a\u00020\u001c2\u0006\u0010\u001b\u001a\u00020\u001a2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001cH\u0014J\u0012\u00109\u001a\u0004\u0018\u0001082\u0006\u0010\u0005\u001a\u00020\u0004H\u0014J\n\u0010;\u001a\u0004\u0018\u00010:H\u0014J\b\u0010<\u001a\u00020\bH\u0014J\u001a\u0010@\u001a\u0004\u0018\u00010?2\u0006\u0010>\u001a\u00020=2\u0006\u0010\u0013\u001a\u00020\u0012H\u0014J\b\u0010A\u001a\u00020\bH\u0014J\b\u0010C\u001a\u00020BH\u0014J\b\u0010E\u001a\u00020DH\u0014J\n\u0010G\u001a\u0004\u0018\u00010FH$J\n\u0010I\u001a\u0004\u0018\u00010HH\u0014J\u001c\u0010M\u001a\u00020\n2\b\u0010K\u001a\u0004\u0018\u00010J2\b\u0010L\u001a\u0004\u0018\u00010\u001eH\u0016J\"\u0010S\u001a\u00020\n2\u0006\u0010O\u001a\u00020N2\u0006\u0010P\u001a\u00020\u001e2\b\u0010R\u001a\u0004\u0018\u00010QH\u0016J\u0010\u0010V\u001a\u00020\b2\u0006\u0010U\u001a\u00020TH\u0016J\b\u0010W\u001a\u00020\nH\u0014J\b\u0010X\u001a\u00020\nH\u0014J\b\u0010Y\u001a\u00020\bH\u0014J\b\u0010Z\u001a\u00020\nH\u0014J\u0012\u0010Z\u001a\u00020\n2\b\b\u0002\u0010[\u001a\u00020\bH\u0004J\b\u0010\\\u001a\u00020\nH\u0014J\b\u0010]\u001a\u00020\nH\u0016J\b\u0010^\u001a\u00020\nH\u0016J\b\u0010_\u001a\u00020\nH\u0014J\u0010\u0010_\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u0004H\u0014J\b\u0010`\u001a\u00020\bH$J\b\u0010a\u001a\u00020\bH\u0014J\u0010\u0010b\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u0004H\u0014J\b\u0010c\u001a\u00020\nH\u0014J\b\u0010d\u001a\u00020\nH\u0004J\b\u0010e\u001a\u00020\nH\u0004J\"\u0010j\u001a\u00020\n2\b\u0010g\u001a\u0004\u0018\u00010f2\u0006\u0010h\u001a\u00020\b2\u0006\u0010i\u001a\u00020\bH\u0014J\u0010\u0010m\u001a\u00020\n2\u0006\u0010l\u001a\u00020kH\u0014J\b\u0010n\u001a\u00020\nH\u0004J\b\u0010o\u001a\u00020\nH\u0014J\b\u0010p\u001a\u00020\bH\u0004J\u0010\u0010r\u001a\u00020\n2\u0006\u0010q\u001a\u00020&H\u0014J\u0018\u0010t\u001a\u00020\n2\u0006\u0010s\u001a\u00020&2\u0006\u0010q\u001a\u00020&H\u0014J\b\u0010u\u001a\u00020\nH\u0014J\b\u0010v\u001a\u00020\nH\u0014J\b\u0010w\u001a\u00020\nH\u0014J\u0010\u0010z\u001a\u00020\n2\u0006\u0010y\u001a\u00020xH\u0014J\b\u0010{\u001a\u00020\nH\u0014J\b\u0010|\u001a\u00020\nH\u0004J\u0010\u0010~\u001a\u00020\n2\u0006\u0010}\u001a\u00020&H\u0004Jv\u0010\u0088\u0001\u001a\u00020\n2\u0006\u0010\u007f\u001a\u0002002\u000b\b\u0002\u0010\u0080\u0001\u001a\u0004\u0018\u00010D2\u000b\b\u0002\u0010\u0081\u0001\u001a\u0004\u0018\u00010D2\u000b\b\u0002\u0010\u0082\u0001\u001a\u0004\u0018\u00010D2\u000b\b\u0002\u0010\u0083\u0001\u001a\u0004\u0018\u00010D2\u000b\b\u0002\u0010\u0084\u0001\u001a\u0004\u0018\u00010D2\u000b\b\u0002\u0010\u0085\u0001\u001a\u0004\u0018\u00010D2\f\b\u0002\u0010\u0087\u0001\u001a\u0005\u0018\u00010\u0086\u0001H\u0004¢\u0006\u0006\b\u0088\u0001\u0010\u0089\u0001J¾\u0001\u0010\u0098\u0001\u001a\u00020\n2\b\u0010\u008b\u0001\u001a\u00030\u008a\u00012\n\b\u0002\u0010\u008d\u0001\u001a\u00030\u008c\u00012\n\b\u0002\u0010\u007f\u001a\u0004\u0018\u0001002\u000b\b\u0002\u0010\u0080\u0001\u001a\u0004\u0018\u00010D2\u000b\b\u0002\u0010\u008e\u0001\u001a\u0004\u0018\u00010D2\f\b\u0002\u0010\u0090\u0001\u001a\u0005\u0018\u00010\u008f\u00012\f\b\u0002\u0010\u0092\u0001\u001a\u0005\u0018\u00010\u0091\u00012\u000b\b\u0002\u0010\u0093\u0001\u001a\u0004\u0018\u00010&2\u000b\b\u0002\u0010\u0094\u0001\u001a\u0004\u0018\u00010D2\u000b\b\u0002\u0010\u0085\u0001\u001a\u0004\u0018\u00010D2\u000b\b\u0002\u0010\u0095\u0001\u001a\u0004\u0018\u00010D2\u001e\b\u0002\u0010\u0097\u0001\u001a\u0017\u0012\u0011\u0012\u000f\u0012\u0004\u0012\u00020D\u0012\u0004\u0012\u00020D0\u0096\u0001\u0018\u00010+H\u0004¢\u0006\u0006\b\u0098\u0001\u0010\u0099\u0001J%\u0010\u009e\u0001\u001a\u00020\b2\b\u0010\u009b\u0001\u001a\u00030\u009a\u00012\u0007\u0010\u009c\u0001\u001a\u00020\b2\u0007\u0010\u009d\u0001\u001a\u00020\bH\u0014J\u0012\u0010 \u0001\u001a\u000b\u0012\u0005\u0012\u00030\u009f\u0001\u0018\u00010+H\u0014J\t\u0010¡\u0001\u001a\u00020\nH\u0016J\t\u0010¢\u0001\u001a\u00020\nH\u0016J\t\u0010£\u0001\u001a\u00020\nH\u0016J\f\u0010¥\u0001\u001a\u0005\u0018\u00010¤\u0001H\u0014J\t\u0010¦\u0001\u001a\u00020&H\u0016R \u0010¨\u0001\u001a\u000b §\u0001*\u0004\u0018\u00010D0D8\u0002X\u0082\u0004¢\u0006\b\n\u0006\b¨\u0001\u0010©\u0001R)\u0010ª\u0001\u001a\u00020\b8\u0004@\u0004X\u0084\u000e¢\u0006\u0018\n\u0006\bª\u0001\u0010«\u0001\u001a\u0006\b¬\u0001\u0010\u00ad\u0001\"\u0006\b®\u0001\u0010¯\u0001R\u0019\u0010°\u0001\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b°\u0001\u0010«\u0001R)\u0010K\u001a\u0004\u0018\u00010.8\u0004@\u0004X\u0084\u000e¢\u0006\u0017\n\u0005\bK\u0010±\u0001\u001a\u0006\b²\u0001\u0010³\u0001\"\u0006\b´\u0001\u0010µ\u0001R\u001b\u0010¶\u0001\u001a\u0004\u0018\u00010J8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b¶\u0001\u0010·\u0001R*\u0010¸\u0001\u001a\u0004\u0018\u00010f8\u0004@\u0004X\u0084\u000e¢\u0006\u0017\n\u0006\b¸\u0001\u0010¹\u0001\u001a\u0005\b\u0017\u0010º\u0001\"\u0006\b»\u0001\u0010¼\u0001R)\u0010\u0013\u001a\u0004\u0018\u00010\u00128\u0004@\u0004X\u0084\u000e¢\u0006\u0017\n\u0005\b\u0013\u0010½\u0001\u001a\u0006\b¾\u0001\u0010¿\u0001\"\u0006\bÀ\u0001\u0010Á\u0001R*\u0010Â\u0001\u001a\u0004\u0018\u00010?8\u0004@\u0004X\u0084\u000e¢\u0006\u0017\n\u0006\bÂ\u0001\u0010Ã\u0001\u001a\u0005\b@\u0010Ä\u0001\"\u0006\bÅ\u0001\u0010Æ\u0001R,\u0010È\u0001\u001a\u0005\u0018\u00010Ç\u00018\u0004@\u0004X\u0084\u000e¢\u0006\u0018\n\u0006\bÈ\u0001\u0010É\u0001\u001a\u0006\bÊ\u0001\u0010Ë\u0001\"\u0006\bÌ\u0001\u0010Í\u0001R+\u0010Î\u0001\u001a\u0004\u0018\u00010F8\u0004@\u0004X\u0084\u000e¢\u0006\u0018\n\u0006\bÎ\u0001\u0010Ï\u0001\u001a\u0006\bÐ\u0001\u0010Ñ\u0001\"\u0006\bÒ\u0001\u0010Ó\u0001R*\u0010Ô\u0001\u001a\u0004\u0018\u0001088\u0004@\u0004X\u0084\u000e¢\u0006\u0017\n\u0006\bÔ\u0001\u0010Õ\u0001\u001a\u0005\b9\u0010Ö\u0001\"\u0006\b×\u0001\u0010Ø\u0001R+\u0010Ù\u0001\u001a\u0004\u0018\u00010\u00068\u0004@\u0004X\u0084\u000e¢\u0006\u0018\n\u0006\bÙ\u0001\u0010Ú\u0001\u001a\u0006\bÛ\u0001\u0010Ü\u0001\"\u0006\bÝ\u0001\u0010Þ\u0001R+\u0010ß\u0001\u001a\u0004\u0018\u00010:8\u0004@\u0004X\u0084\u000e¢\u0006\u0018\n\u0006\bß\u0001\u0010à\u0001\u001a\u0006\bá\u0001\u0010â\u0001\"\u0006\bã\u0001\u0010ä\u0001R)\u0010$\u001a\u0004\u0018\u00010\u001c8\u0004@\u0004X\u0084\u000e¢\u0006\u0017\n\u0005\b$\u0010å\u0001\u001a\u0006\bæ\u0001\u0010ç\u0001\"\u0006\bè\u0001\u0010é\u0001R\u001c\u0010ë\u0001\u001a\u0005\u0018\u00010ê\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bë\u0001\u0010ì\u0001R+\u0010í\u0001\u001a\u0004\u0018\u00010\u001e8\u0004@\u0004X\u0084\u000e¢\u0006\u0018\n\u0006\bí\u0001\u0010î\u0001\u001a\u0006\bï\u0001\u0010ð\u0001\"\u0006\bñ\u0001\u0010ò\u0001R\u0019\u0010\"\u001a\u0004\u0018\u00010\u001e8\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b\"\u0010î\u0001R,\u0010ô\u0001\u001a\u0005\u0018\u00010ó\u00018\u0004@\u0004X\u0084\u000e¢\u0006\u0018\n\u0006\bô\u0001\u0010õ\u0001\u001a\u0006\bö\u0001\u0010÷\u0001\"\u0006\bø\u0001\u0010ù\u0001R,\u0010û\u0001\u001a\u0005\u0018\u00010ú\u00018\u0004@\u0004X\u0084\u000e¢\u0006\u0018\n\u0006\bû\u0001\u0010ü\u0001\u001a\u0006\bý\u0001\u0010þ\u0001\"\u0006\bÿ\u0001\u0010\u0080\u0002R#\u0010\u0086\u0002\u001a\u0005\u0018\u00010\u0081\u00028DX\u0084\u0084\u0002¢\u0006\u0010\n\u0006\b\u0082\u0002\u0010\u0083\u0002\u001a\u0006\b\u0084\u0002\u0010\u0085\u0002R \u0010\u0088\u0002\u001a\u00030\u0087\u00028\u0014X\u0094\u0004¢\u0006\u0010\n\u0006\b\u0088\u0002\u0010\u0089\u0002\u001a\u0006\b\u008a\u0002\u0010\u008b\u0002R\u001a\u0010\u008f\u0002\u001a\u0005\u0018\u00010\u008c\u00028$X¤\u0004¢\u0006\b\u001a\u0006\b\u008d\u0002\u0010\u008e\u0002¨\u0006\u0092\u0002"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/AbstractBaseViewsFragment;", "Landroidx/fragment/app/Fragment;", "Lcom/amazon/mp3/card/prime/ContextMenuSupportingFragment;", "Lcom/amazon/mp3/dialog/NoNetworkEmptyPageDialog$Listener;", "Landroidx/fragment/app/FragmentActivity;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Lcom/amazon/music/views/library/providers/TrackDownloadButtonProvider;", "getTrackDownloadProvider", "", "shouldShowNoNetworkDialog", "", "dismissNoNetworkDialog", "hasValidModels", "sendLatencyLoadCompleteEvent", "Landroid/os/Bundle;", "savedInstanceState", "onCreate", "shouldSendInitializingLatencyEvent", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "styleViews", "onStart", "onActivityCreated", "getPageModel", "onResume", "initBinderConfiguration", "Landroid/view/LayoutInflater;", "inflater", "Landroid/view/ViewGroup;", "container", "Landroid/view/View;", "onCreateView", "initEmptyView", "emptyView", "emptyLayout", "addEmptyChildView", "root", "getEmptyChildView", "", "getLoadingViewId", "initPageModelProvider", "Lcom/amazon/mp3/catalog/fragment/datasource/repository/PageDataRepository;", "getPageDataRepository", "", "Lcom/amazon/music/views/library/binders/UniversalBinder;", "getBinderList", "Lcom/amazon/mp3/providers/MetadataContextMenuHandler;", "getContextMenuProvider", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "getPageType", "Lcom/amazon/mp3/catalog/fragment/adapter/BaseViewsDiffUtilFactory;", "getDiffUtilFactory", "Lcom/amazon/mp3/providers/MissingSDCardHandler;", "getMissingSDCardProvider", "emitUiPageViewMetric", "getInflatedRootView", "Lcom/amazon/music/views/library/providers/RowButtonOnClickProvider;", "getRowButtonOnClickProvider", "Lcom/amazon/mp3/library/provider/LibraryStateProvider;", "getLibraryStateProviderInstance", "shouldShowFastScroller", "Landroid/content/Context;", "context", "Lcom/amazon/mp3/catalog/fragment/adapter/BaseRecyclerViewProvider;", "getRecyclerViewProvider", "useOptimizedAdapterObservable", "Lcom/amazon/music/views/library/recyclerview/BaseViewModelAdapterProvider;", "getAdapterProvider", "", "getSourceId", "Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;", "getContentEnabilityProvider", "Lcom/amazon/music/views/library/deeplinks/ContentMetadataOnClickListener;", "getMetadataClickListener", "Lcom/amazon/mp3/card/prime/ContextMenuHandler;", "contextMenuHandler", "view", "openContextMenuForView", "Landroid/view/ContextMenu;", "menu", "v", "Landroid/view/ContextMenu$ContextMenuInfo;", "menuInfo", "onCreateContextMenu", "Landroid/view/MenuItem;", "item", "onContextItemSelected", "showEmptyView", "hideEmptyView", "shouldShowEmptyView", "showLoadingView", "hideList", "showRecyclerView", "goOfflineClickAction", "onNoNetworkDialogCanceled", "showNetworkErrorDialog", "requireNetworkConnection", "shouldBlockDataFetch", "refreshPageGridViewModel", "registerLiveDataObservers", "initPageDataObserver", "initNetworkDataObserver", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "pageGridViewModel", "isInitialPage", "isMorePage", "updatePageModel", "Lcom/amazon/music/views/library/models/ErrorModel;", "errorModel", "updateOnError", "displayViewState", "retrievePageData", "hasFetchedData", "pageSize", "getInitialData", "pageIndex", "getMoreData", "onLoadInitPage", "onLoadMorePage", "onLoadUpdatedPage", "Lkotlin/ranges/IntRange;", "indicesChanged", "onLoadPageRangeChanged", "setupViews", "createRootView", "position", "scrollToPosition", "pageType", "pageSubType", "detailPageItemId", "detailPageItemIdType", "externalReference", "associateTag", "blockRef", "", "loadTime", "sendUiPageViewMetric", "(Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;)V", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "actionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;", "interactionType", ContextMappingConstants.ENTITY_ID, "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/EntityIdType;", ContextMappingConstants.ENTITY_ID_TYPE, "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/EntityType;", PageUriUtils.ENTITY_TYPE_QUERY_PARAM, "entityPosition", ImagesContract.URL, "refMarker", "", ContextMappingConstants.CONTENT_INFO, "sendUiClickMetric", "(Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;Ljava/lang/String;Ljava/lang/String;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/EntityIdType;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/EntityType;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "metadata", "isSourceLocal", "isShuffled", "shouldBlockPlaybackAndDownload", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "getTrackList", "onStop", "onDestroyView", "onDestroy", "Lcom/amazon/music/metrics/mts/event/types/pagelatencyinfo/PageLoadLatencyCode;", "getPageLoadLatencyCode", "getPageSize", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "shouldUseDiffUtilForReorders", "Z", "getShouldUseDiffUtilForReorders", "()Z", "setShouldUseDiffUtilForReorders", "(Z)V", "initialPageLoadSent", "Lcom/amazon/mp3/providers/MetadataContextMenuHandler;", "getContextMenuHandler", "()Lcom/amazon/mp3/providers/MetadataContextMenuHandler;", "setContextMenuHandler", "(Lcom/amazon/mp3/providers/MetadataContextMenuHandler;)V", "activeContextMenuHandler", "Lcom/amazon/mp3/card/prime/ContextMenuHandler;", "pageModel", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "()Lcom/amazon/music/views/library/models/PageGridViewModel;", "setPageModel", "(Lcom/amazon/music/views/library/models/PageGridViewModel;)V", "Lcom/amazon/music/views/library/styles/StyleSheet;", "getStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "setStyleSheet", "(Lcom/amazon/music/views/library/styles/StyleSheet;)V", "recyclerViewProvider", "Lcom/amazon/mp3/catalog/fragment/adapter/BaseRecyclerViewProvider;", "()Lcom/amazon/mp3/catalog/fragment/adapter/BaseRecyclerViewProvider;", "setRecyclerViewProvider", "(Lcom/amazon/mp3/catalog/fragment/adapter/BaseRecyclerViewProvider;)V", "Lcom/amazon/mp3/catalog/fragment/adapter/BaseViewsBinderConfiguration;", "viewsConfiguration", "Lcom/amazon/mp3/catalog/fragment/adapter/BaseViewsBinderConfiguration;", "getViewsConfiguration", "()Lcom/amazon/mp3/catalog/fragment/adapter/BaseViewsBinderConfiguration;", "setViewsConfiguration", "(Lcom/amazon/mp3/catalog/fragment/adapter/BaseViewsBinderConfiguration;)V", "enabilityProvider", "Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;", "getEnabilityProvider", "()Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;", "setEnabilityProvider", "(Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;)V", "rowButtonOnClickProvider", "Lcom/amazon/music/views/library/providers/RowButtonOnClickProvider;", "()Lcom/amazon/music/views/library/providers/RowButtonOnClickProvider;", "setRowButtonOnClickProvider", "(Lcom/amazon/music/views/library/providers/RowButtonOnClickProvider;)V", "trackDownloadButtonProvider", "Lcom/amazon/music/views/library/providers/TrackDownloadButtonProvider;", "getTrackDownloadButtonProvider", "()Lcom/amazon/music/views/library/providers/TrackDownloadButtonProvider;", "setTrackDownloadButtonProvider", "(Lcom/amazon/music/views/library/providers/TrackDownloadButtonProvider;)V", "libraryStateProvider", "Lcom/amazon/mp3/library/provider/LibraryStateProvider;", "getLibraryStateProvider", "()Lcom/amazon/mp3/library/provider/LibraryStateProvider;", "setLibraryStateProvider", "(Lcom/amazon/mp3/library/provider/LibraryStateProvider;)V", "Landroid/view/ViewGroup;", "getRoot", "()Landroid/view/ViewGroup;", "setRoot", "(Landroid/view/ViewGroup;)V", "Lcom/amazon/mp3/dialog/NoNetworkEmptyPageDialog;", "noConnectionDialog", "Lcom/amazon/mp3/dialog/NoNetworkEmptyPageDialog;", "loadingView", "Landroid/view/View;", "getLoadingView", "()Landroid/view/View;", "setLoadingView", "(Landroid/view/View;)V", "Landroidx/recyclerview/widget/RecyclerView;", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "getRecyclerView", "()Landroidx/recyclerview/widget/RecyclerView;", "setRecyclerView", "(Landroidx/recyclerview/widget/RecyclerView;)V", "Lcom/amazon/music/views/library/recyclerview/EndlessScrollListener;", "endlessScrollListener", "Lcom/amazon/music/views/library/recyclerview/EndlessScrollListener;", "getEndlessScrollListener", "()Lcom/amazon/music/views/library/recyclerview/EndlessScrollListener;", "setEndlessScrollListener", "(Lcom/amazon/music/views/library/recyclerview/EndlessScrollListener;)V", "Lcom/amazon/music/views/library/recyclerview/indexer/FastScroller;", "fastScroller$delegate", "Lkotlin/Lazy;", "getFastScroller", "()Lcom/amazon/music/views/library/recyclerview/indexer/FastScroller;", "fastScroller", "Lcom/amazon/music/account/AccountStateChangeListener;", "mAccountStateChangeListener", "Lcom/amazon/music/account/AccountStateChangeListener;", "getMAccountStateChangeListener", "()Lcom/amazon/music/account/AccountStateChangeListener;", "Lcom/amazon/mp3/catalog/fragment/viewmodel/PageViewModelProvider;", "getPageModelProvider", "()Lcom/amazon/mp3/catalog/fragment/viewmodel/PageViewModelProvider;", "pageModelProvider", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public abstract class AbstractBaseViewsFragment extends Fragment implements ContextMenuSupportingFragment, NoNetworkEmptyPageDialog.Listener {
    private ContextMenuHandler activeContextMenuHandler;
    private MetadataContextMenuHandler contextMenuHandler;
    private View emptyLayout;
    private ContentEnabilityProvider enabilityProvider;
    private EndlessScrollListener endlessScrollListener;
    private boolean initialPageLoadSent;
    private LibraryStateProvider libraryStateProvider;
    private View loadingView;
    private NoNetworkEmptyPageDialog noConnectionDialog;
    private PageGridViewModel pageModel;
    private RecyclerView recyclerView;
    private BaseRecyclerViewProvider recyclerViewProvider;
    private ViewGroup root;
    private RowButtonOnClickProvider rowButtonOnClickProvider;
    private boolean shouldUseDiffUtilForReorders;
    private StyleSheet styleSheet;
    private TrackDownloadButtonProvider trackDownloadButtonProvider;
    private BaseViewsBinderConfiguration viewsConfiguration;
    private final String TAG = AbstractBaseViewsFragment.class.getSimpleName();

    /* JADX INFO: renamed from: fastScroller$delegate, reason: from kotlin metadata */
    private final Lazy fastScroller = LazyKt__LazyJVMKt.lazy(new Function0<FastScroller>() { // from class: com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment$fastScroller$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final FastScroller invoke() {
            View view;
            if (!this.this$0.shouldShowFastScroller() || (view = this.this$0.getView()) == null) {
                return null;
            }
            return (FastScroller) view.findViewById(R.id.fastScroller);
        }
    });
    private final AccountStateChangeListener mAccountStateChangeListener = new AccountStateChangeListener() { // from class: com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment$$ExternalSyntheticLambda2
        @Override // com.amazon.music.account.AccountStateChangeListener
        public final void onAccountStateChange(Set set) {
            AbstractBaseViewsFragment.m454mAccountStateChangeListener$lambda0(this.f$0, set);
        }
    };

    public abstract void emitUiPageViewMetric();

    public abstract List<UniversalBinder<?, ?>> getBinderList();

    protected abstract ContentEnabilityProvider getContentEnabilityProvider();

    public abstract MetadataContextMenuHandler getContextMenuProvider();

    public abstract BaseViewsDiffUtilFactory getDiffUtilFactory();

    protected View getEmptyChildView(ViewGroup root) {
        return null;
    }

    protected int getLoadingViewId() {
        return R.id.loading_spinner;
    }

    public abstract MissingSDCardHandler getMissingSDCardProvider();

    public abstract PageDataRepository getPageDataRepository();

    protected PageLoadLatencyCode getPageLoadLatencyCode() {
        return null;
    }

    protected abstract PageViewModelProvider getPageModelProvider();

    public int getPageSize() {
        return 20;
    }

    public abstract PageType getPageType();

    protected String getSourceId() {
        return "cirrus";
    }

    protected List<BaseViewModel> getTrackList() {
        return null;
    }

    public abstract void initPageModelProvider(FragmentActivity activity);

    protected abstract boolean requireNetworkConnection();

    protected boolean shouldSendInitializingLatencyEvent() {
        return false;
    }

    protected boolean shouldShowFastScroller() {
        return false;
    }

    protected void updateOnError(ErrorModel errorModel) {
        Intrinsics.checkNotNullParameter(errorModel, "errorModel");
    }

    protected boolean useOptimizedAdapterObservable() {
        return true;
    }

    protected final boolean getShouldUseDiffUtilForReorders() {
        return this.shouldUseDiffUtilForReorders;
    }

    protected final void setShouldUseDiffUtilForReorders(boolean z) {
        this.shouldUseDiffUtilForReorders = z;
    }

    protected final MetadataContextMenuHandler getContextMenuHandler() {
        return this.contextMenuHandler;
    }

    protected final void setContextMenuHandler(MetadataContextMenuHandler metadataContextMenuHandler) {
        this.contextMenuHandler = metadataContextMenuHandler;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final PageGridViewModel getPageModel() {
        return this.pageModel;
    }

    protected final void setPageModel(PageGridViewModel pageGridViewModel) {
        this.pageModel = pageGridViewModel;
    }

    protected final StyleSheet getStyleSheet() {
        return this.styleSheet;
    }

    protected final BaseRecyclerViewProvider getRecyclerViewProvider() {
        return this.recyclerViewProvider;
    }

    protected final void setRecyclerViewProvider(BaseRecyclerViewProvider baseRecyclerViewProvider) {
        this.recyclerViewProvider = baseRecyclerViewProvider;
    }

    protected final BaseViewsBinderConfiguration getViewsConfiguration() {
        return this.viewsConfiguration;
    }

    protected final void setViewsConfiguration(BaseViewsBinderConfiguration baseViewsBinderConfiguration) {
        this.viewsConfiguration = baseViewsBinderConfiguration;
    }

    protected final ContentEnabilityProvider getEnabilityProvider() {
        return this.enabilityProvider;
    }

    protected final void setEnabilityProvider(ContentEnabilityProvider contentEnabilityProvider) {
        this.enabilityProvider = contentEnabilityProvider;
    }

    protected final RowButtonOnClickProvider getRowButtonOnClickProvider() {
        return this.rowButtonOnClickProvider;
    }

    protected final void setRowButtonOnClickProvider(RowButtonOnClickProvider rowButtonOnClickProvider) {
        this.rowButtonOnClickProvider = rowButtonOnClickProvider;
    }

    protected final LibraryStateProvider getLibraryStateProvider() {
        return this.libraryStateProvider;
    }

    protected final void setLibraryStateProvider(LibraryStateProvider libraryStateProvider) {
        this.libraryStateProvider = libraryStateProvider;
    }

    protected final ViewGroup getRoot() {
        return this.root;
    }

    protected final View getLoadingView() {
        return this.loadingView;
    }

    protected final RecyclerView getRecyclerView() {
        return this.recyclerView;
    }

    protected final EndlessScrollListener getEndlessScrollListener() {
        return this.endlessScrollListener;
    }

    protected final void setEndlessScrollListener(EndlessScrollListener endlessScrollListener) {
        this.endlessScrollListener = endlessScrollListener;
    }

    protected final FastScroller getFastScroller() {
        return (FastScroller) this.fastScroller.getValue();
    }

    protected AccountStateChangeListener getMAccountStateChangeListener() {
        return this.mAccountStateChangeListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: mAccountStateChangeListener$lambda-0, reason: not valid java name */
    public static final void m454mAccountStateChangeListener$lambda0(AbstractBaseViewsFragment this$0, Set set) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (set.contains(AccountStateField.USER_BENEFITS) || set.contains(AccountStateField.USER_MUSIC_TERRITORY_OF_RESIDENCE) || set.contains(AccountStateField.EXPLICIT_FILTER_ENABLED_ON_DEVICE)) {
            BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getMain()), null, null, new AbstractBaseViewsFragment$mAccountStateChangeListener$1$1(this$0, null), 3, null);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        PageLoadLatencyCode pageLoadLatencyCode;
        super.onCreate(savedInstanceState);
        if (shouldSendInitializingLatencyEvent() && (pageLoadLatencyCode = getPageLoadLatencyCode()) != null) {
            LatencyTracker.INSTANCE.track(new LatencyEventDescriptor(LatencyTrackingLeg.INITIALIZING, pageLoadLatencyCode));
            Log.debug(this.TAG, "Begin LatencyTrackingLeg.INITIALIZING");
        }
        StyleSheetProvider.getStyleSheet().observe(this, new Observer() { // from class: com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment$$ExternalSyntheticLambda5
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                AbstractBaseViewsFragment.m455onCreate$lambda2(this.f$0, (StyleSheet) obj);
            }
        });
        if (savedInstanceState == null) {
            emitUiPageViewMetric();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreate$lambda-2, reason: not valid java name */
    public static final void m455onCreate$lambda2(AbstractBaseViewsFragment this$0, StyleSheet it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.styleSheet = it;
        Intrinsics.checkNotNullExpressionValue(it, "it");
        this$0.styleViews(it);
        this$0.initBinderConfiguration();
        if (this$0.hasValidModels()) {
            this$0.setupViews();
        }
    }

    protected final void styleViews(StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        View view = this.loadingView;
        BauhausLoadingView bauhausLoadingView = view instanceof BauhausLoadingView ? (BauhausLoadingView) view : null;
        if (bauhausLoadingView == null) {
            return;
        }
        bauhausLoadingView.initStyle(styleSheet);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        AccountManagerSingleton.get().registerListener(getMAccountStateChangeListener());
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        BaseViewsBinderConfiguration viewsConfiguration;
        super.onActivityCreated(savedInstanceState);
        FragmentActivity activity = getActivity();
        if (activity != null && (viewsConfiguration = getViewsConfiguration()) != null) {
            viewsConfiguration.setRowButtonOnClickProvider(getRowButtonOnClickProvider(activity));
        }
        mo456getPageModel();
    }

    /* JADX INFO: renamed from: getPageModel, reason: collision with other method in class */
    public void mo456getPageModel() {
        FragmentActivity activity;
        if (getPageModelProvider() == null && (activity = getActivity()) != null) {
            refreshPageGridViewModel(activity);
        }
        registerLiveDataObservers();
        retrievePageData();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        PageViewModelProvider pageModelProvider = getPageModelProvider();
        if (pageModelProvider == null) {
            return;
        }
        pageModelProvider.refresh();
    }

    protected final void initBinderConfiguration() {
        StyleSheet styleSheet;
        RowButtonOnClickProvider rowButtonOnClickProvider;
        TrackDownloadButtonProvider trackDownloadProvider;
        Context context = getContext();
        if (context == null || (styleSheet = getStyleSheet()) == null) {
            return;
        }
        setContextMenuHandler(getContextMenuProvider());
        PicassoImageLoader picassoImageLoaderBuild = PicassoImageLoader.builder(context, Picasso.get()).withTag(context).build();
        BaseViewModelAdapterProvider adapterProvider = getAdapterProvider();
        ContentEnabilityProvider contentEnabilityProvider = getContentEnabilityProvider();
        DefaultContentEligibilityProvider defaultContentEligibilityProvider = new DefaultContentEligibilityProvider();
        DefaultCachedArtworkProvider defaultCachedArtworkProvider = new DefaultCachedArtworkProvider(getActivity(), styleSheet);
        MetadataContextMenuHandler contextMenuHandler = getContextMenuHandler();
        FragmentActivity activity = getActivity();
        RowButtonOnClickProvider rowButtonOnClickProvider2 = (activity == null || (rowButtonOnClickProvider = getRowButtonOnClickProvider(activity)) == null) ? null : rowButtonOnClickProvider;
        ContentMetadataOnClickListener metadataClickListener = getMetadataClickListener();
        MissingSDCardHandler missingSDCardProvider = getMissingSDCardProvider();
        FragmentActivity activity2 = getActivity();
        TrackDownloadButtonProvider trackDownloadButtonProvider = (activity2 == null || (trackDownloadProvider = getTrackDownloadProvider(activity2)) == null) ? null : trackDownloadProvider;
        Intrinsics.checkNotNullExpressionValue(picassoImageLoaderBuild, "build()");
        setViewsConfiguration(new BaseViewsBinderConfiguration(context, this, styleSheet, picassoImageLoaderBuild, adapterProvider, defaultCachedArtworkProvider, contentEnabilityProvider, defaultContentEligibilityProvider, contextMenuHandler, rowButtonOnClickProvider2, metadataClickListener, missingSDCardProvider, trackDownloadButtonProvider));
        BaseViewsBinderConfiguration viewsConfiguration = getViewsConfiguration();
        if (viewsConfiguration == null) {
            return;
        }
        getLifecycle().addObserver(viewsConfiguration);
    }

    private final TrackDownloadButtonProvider getTrackDownloadProvider(FragmentActivity activity) {
        if (this.trackDownloadButtonProvider == null) {
            this.trackDownloadButtonProvider = new DefaultTrackDownloadButtonProvider(activity, getPageType());
        }
        return this.trackDownloadButtonProvider;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        ViewGroup inflatedRootView = getInflatedRootView(inflater, container);
        this.root = inflatedRootView;
        this.loadingView = inflatedRootView == null ? null : inflatedRootView.findViewById(getLoadingViewId());
        ViewGroup viewGroup = this.root;
        this.recyclerView = viewGroup == null ? null : (RecyclerView) viewGroup.findViewById(R.id.recyclerView);
        ViewGroup viewGroup2 = this.root;
        this.emptyLayout = viewGroup2 != null ? viewGroup2.findViewById(R.id.empty_layout) : null;
        initEmptyView();
        showLoadingView();
        if (hasValidModels() && this.styleSheet != null) {
            showRecyclerView();
            setupViews();
        }
        return this.root;
    }

    protected void initEmptyView() {
        View view = this.emptyLayout;
        ViewGroup viewGroup = view instanceof ViewGroup ? (ViewGroup) view : null;
        if (viewGroup == null) {
            return;
        }
        addEmptyChildView(getEmptyChildView(viewGroup), viewGroup);
    }

    protected void addEmptyChildView(View emptyView, ViewGroup emptyLayout) {
        Intrinsics.checkNotNullParameter(emptyLayout, "emptyLayout");
        if (emptyView == null) {
            return;
        }
        if (emptyView instanceof RelativeLayout) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(13);
            emptyView.setLayoutParams(layoutParams);
        }
        emptyView.setClickable(false);
        emptyView.setOnClickListener(null);
        emptyLayout.addView(emptyView);
    }

    protected ViewGroup getInflatedRootView(LayoutInflater inflater, ViewGroup container) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View viewInflate = inflater.inflate(R.layout.base_views_layout, container, false);
        if (viewInflate != null) {
            return (ViewGroup) viewInflate;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup");
    }

    protected RowButtonOnClickProvider getRowButtonOnClickProvider(FragmentActivity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (this.rowButtonOnClickProvider == null) {
            this.rowButtonOnClickProvider = new DefaultAddToLibraryProvider(activity, getLibraryStateProviderInstance(), null, 4, null);
        }
        return this.rowButtonOnClickProvider;
    }

    protected LibraryStateProvider getLibraryStateProviderInstance() {
        if (this.libraryStateProvider == null) {
            Context context = getContext();
            this.libraryStateProvider = context == null ? null : new LibraryStateProvider(context);
        }
        return this.libraryStateProvider;
    }

    protected BaseRecyclerViewProvider getRecyclerViewProvider(Context context, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        BaseViewsBinderConfiguration baseViewsBinderConfiguration = this.viewsConfiguration;
        if (baseViewsBinderConfiguration == null) {
            return null;
        }
        return new DefaultRecyclerViewProvider(context, styleSheet, baseViewsBinderConfiguration.getAdapterProvider(), new ItemTouchHelper(new SimpleItemTouchHelperCallback()));
    }

    protected BaseViewModelAdapterProvider getAdapterProvider() {
        return new BaseViewModelAdapterProvider(this, null, LifecycleOwnerKt.getLifecycleScope(this), useOptimizedAdapterObservable(), 2, null);
    }

    protected ContentMetadataOnClickListener getMetadataClickListener() {
        return new DefaultMetadataClickListener(this, getPageType(), null, 4, null);
    }

    @Override // com.amazon.mp3.card.prime.ContextMenuSupportingFragment
    public synchronized void openContextMenuForView(ContextMenuHandler contextMenuHandler, View view) {
        if (view != null) {
            this.activeContextMenuHandler = contextMenuHandler;
            registerForContextMenu(view);
            FragmentActivity activity = getActivity();
            if (activity != null) {
                activity.openContextMenu(view);
            }
            unregisterForContextMenu(view);
        }
    }

    @Override // androidx.fragment.app.Fragment, android.view.View.OnCreateContextMenuListener
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        Intrinsics.checkNotNullParameter(v, "v");
        ContextMenuHandler contextMenuHandler = this.activeContextMenuHandler;
        if (contextMenuHandler == null) {
            return;
        }
        contextMenuHandler.onCreateContextMenu(menu, getSourceId());
    }

    @Override // androidx.fragment.app.Fragment
    public boolean onContextItemSelected(MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        ContextMenuHandler contextMenuHandler = this.activeContextMenuHandler;
        if (contextMenuHandler == null) {
            return false;
        }
        return contextMenuHandler.onContextMenuItemSelected(item, getSourceId());
    }

    protected void showEmptyView() {
        if (shouldShowNoNetworkDialog() && getActivity() != null) {
            FragmentActivity fragmentActivityRequireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "requireActivity()");
            showNetworkErrorDialog(fragmentActivityRequireActivity);
            return;
        }
        View view = this.loadingView;
        if (view != null) {
            view.setVisibility(8);
        }
        View view2 = this.emptyLayout;
        if (view2 != null) {
            view2.setVisibility(0);
        }
        RecyclerView recyclerView = this.recyclerView;
        if (recyclerView == null) {
            return;
        }
        recyclerView.setVisibility(8);
    }

    protected void hideEmptyView() {
        View view = this.emptyLayout;
        if (view == null) {
            return;
        }
        view.setVisibility(8);
    }

    protected boolean shouldShowEmptyView() {
        return !hasValidModels();
    }

    protected void showLoadingView() {
        showLoadingView(true);
    }

    protected final void showLoadingView(boolean hideList) {
        RecyclerView recyclerView;
        View view = this.loadingView;
        if (view != null) {
            view.setVisibility(0);
        }
        View view2 = this.emptyLayout;
        if (view2 != null) {
            view2.setVisibility(8);
        }
        if (!hideList || (recyclerView = this.recyclerView) == null) {
            return;
        }
        recyclerView.setVisibility(8);
    }

    protected void showRecyclerView() {
        View view = this.loadingView;
        if (view != null) {
            view.setVisibility(8);
        }
        View view2 = this.emptyLayout;
        if (view2 != null) {
            view2.setVisibility(8);
        }
        RecyclerView recyclerView = this.recyclerView;
        if (recyclerView == null) {
            return;
        }
        recyclerView.setVisibility(0);
    }

    @Override // com.amazon.mp3.dialog.NoNetworkEmptyPageDialog.Listener
    public void goOfflineClickAction() {
        NoNetworkEmptyPageDialog.Companion companion = NoNetworkEmptyPageDialog.INSTANCE;
        Context context = getContext();
        if (context == null) {
            context = AmazonApplication.getApplication().getApplicationContext();
        }
        Intrinsics.checkNotNullExpressionValue(context, "context ?: AmazonApplica…tion().applicationContext");
        companion.defaultGoOfflineAction(context);
    }

    @Override // com.amazon.mp3.dialog.NoNetworkEmptyPageDialog.Listener
    public void onNoNetworkDialogCanceled() {
        FragmentManager supportFragmentManager;
        FragmentActivity activity = getActivity();
        if (activity == null || (supportFragmentManager = activity.getSupportFragmentManager()) == null) {
            return;
        }
        supportFragmentManager.popBackStack();
    }

    protected void showNetworkErrorDialog() {
        Unit unit;
        FragmentActivity activity = getActivity();
        if (activity == null) {
            unit = null;
        } else {
            showNetworkErrorDialog(activity);
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            showEmptyView();
        }
    }

    protected synchronized void showNetworkErrorDialog(FragmentActivity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (this.noConnectionDialog == null) {
            this.noConnectionDialog = new NoNetworkEmptyPageDialog(this);
        }
        NoNetworkEmptyPageDialog noNetworkEmptyPageDialog = this.noConnectionDialog;
        boolean z = false;
        if (noNetworkEmptyPageDialog != null && !noNetworkEmptyPageDialog.isShowing()) {
            z = true;
        }
        if (z) {
            Log.info(this.TAG, "Display no network connection error dialog to customer");
            NoNetworkEmptyPageDialog noNetworkEmptyPageDialog2 = this.noConnectionDialog;
            if (noNetworkEmptyPageDialog2 != null) {
                FragmentManager supportFragmentManager = activity.getSupportFragmentManager();
                Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "activity.supportFragmentManager");
                noNetworkEmptyPageDialog2.show(supportFragmentManager, "NoConnectionDialog");
            }
        }
        View view = this.loadingView;
        if (view != null) {
            view.setVisibility(8);
        }
        View view2 = this.emptyLayout;
        if (view2 != null) {
            view2.setVisibility(8);
        }
        RecyclerView recyclerView = this.recyclerView;
        if (recyclerView != null) {
            recyclerView.setVisibility(8);
        }
    }

    private final boolean shouldShowNoNetworkDialog() {
        return (!requireNetworkConnection() || hasValidModels() || ConnectivityUtil.hasAnyInternetConnection(getContext())) ? false : true;
    }

    protected boolean shouldBlockDataFetch() {
        return requireNetworkConnection() && !ConnectivityUtil.hasAnyInternetConnection(getContext());
    }

    private final void dismissNoNetworkDialog() {
        NoNetworkEmptyPageDialog noNetworkEmptyPageDialog = this.noConnectionDialog;
        if (noNetworkEmptyPageDialog != null && noNetworkEmptyPageDialog.isShowing()) {
            noNetworkEmptyPageDialog.dismiss();
        }
    }

    protected void refreshPageGridViewModel(FragmentActivity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        initPageModelProvider(activity);
    }

    protected void registerLiveDataObservers() {
        initPageDataObserver();
        initNetworkDataObserver();
    }

    protected final void initPageDataObserver() {
        MutableLiveData<ErrorModel> errorLiveData;
        MutableLiveData<ErrorModel> errorLiveData2;
        MutableLiveData<PageGridViewModel> pageModelLiveData;
        MutableLiveData<PageGridViewModel> pageModelLiveData2;
        PageViewModelProvider pageModelProvider = getPageModelProvider();
        if (pageModelProvider != null && (pageModelLiveData2 = pageModelProvider.getPageModelLiveData()) != null) {
            pageModelLiveData2.removeObservers(getViewLifecycleOwner());
        }
        PageViewModelProvider pageModelProvider2 = getPageModelProvider();
        if (pageModelProvider2 != null && (pageModelLiveData = pageModelProvider2.getPageModelLiveData()) != null) {
            pageModelLiveData.observe(getViewLifecycleOwner(), new Observer() { // from class: com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment$$ExternalSyntheticLambda0
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    AbstractBaseViewsFragment.m452initPageDataObserver$lambda19(this.f$0, (PageGridViewModel) obj);
                }
            });
        }
        PageViewModelProvider pageModelProvider3 = getPageModelProvider();
        if (pageModelProvider3 != null && (errorLiveData2 = pageModelProvider3.getErrorLiveData()) != null) {
            errorLiveData2.removeObservers(getViewLifecycleOwner());
        }
        PageViewModelProvider pageModelProvider4 = getPageModelProvider();
        if (pageModelProvider4 == null || (errorLiveData = pageModelProvider4.getErrorLiveData()) == null) {
            return;
        }
        errorLiveData.observe(getViewLifecycleOwner(), new Observer() { // from class: com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                AbstractBaseViewsFragment.m453initPageDataObserver$lambda20(this.f$0, (ErrorModel) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initPageDataObserver$lambda-19, reason: not valid java name */
    public static final void m452initPageDataObserver$lambda19(AbstractBaseViewsFragment this$0, PageGridViewModel pageGridViewModel) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        RecyclerView recyclerView = this$0.recyclerView;
        boolean z = (recyclerView == null ? null : recyclerView.getAdapter()) == null;
        PageGridViewModel pageGridViewModel2 = this$0.pageModel;
        this$0.updatePageModel(pageGridViewModel, z, (pageGridViewModel2 == null ? 0 : pageGridViewModel2.getPageIndex()) < (pageGridViewModel == null ? 0 : pageGridViewModel.getPageIndex()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initPageDataObserver$lambda-20, reason: not valid java name */
    public static final void m453initPageDataObserver$lambda20(AbstractBaseViewsFragment this$0, ErrorModel pageErrorModel) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(pageErrorModel, "pageErrorModel");
        this$0.updateOnError(pageErrorModel);
    }

    protected final void initNetworkDataObserver() {
        PageViewModelProvider pageModelProvider = getPageModelProvider();
        if (pageModelProvider == null) {
            return;
        }
        pageModelProvider.getHasConnectionLiveData().removeObservers(getViewLifecycleOwner());
        pageModelProvider.getHasConnectionLiveData().observe(getViewLifecycleOwner(), new Observer() { // from class: com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment$$ExternalSyntheticLambda4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                AbstractBaseViewsFragment.m451initNetworkDataObserver$lambda22$lambda21(this.f$0, (Boolean) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initNetworkDataObserver$lambda-22$lambda-21, reason: not valid java name */
    public static final void m451initNetworkDataObserver$lambda22$lambda21(AbstractBaseViewsFragment this$0, Boolean hasConnection) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(hasConnection, "hasConnection");
        if (hasConnection.booleanValue()) {
            NoNetworkEmptyPageDialog noNetworkEmptyPageDialog = this$0.noConnectionDialog;
            boolean z = false;
            if (noNetworkEmptyPageDialog != null && noNetworkEmptyPageDialog.isShowing()) {
                z = true;
            }
            if (z) {
                this$0.dismissNoNetworkDialog();
                this$0.retrievePageData();
                return;
            }
        }
        if (this$0.shouldShowNoNetworkDialog()) {
            this$0.showNetworkErrorDialog();
        }
    }

    protected void updatePageModel(PageGridViewModel pageGridViewModel, boolean isInitialPage, boolean isMorePage) {
        this.pageModel = pageGridViewModel;
        if (isInitialPage) {
            onLoadInitPage();
        } else if (isMorePage) {
            onLoadMorePage();
        } else {
            onLoadUpdatedPage();
        }
        displayViewState();
    }

    protected final void displayViewState() {
        if (shouldShowNoNetworkDialog()) {
            showNetworkErrorDialog();
        } else if (shouldShowEmptyView()) {
            showEmptyView();
        } else {
            showRecyclerView();
        }
    }

    private final boolean hasValidModels() {
        List<BaseViewModel> models;
        PageGridViewModel pageGridViewModel = this.pageModel;
        if (pageGridViewModel == null || (models = pageGridViewModel.getModels()) == null) {
            return false;
        }
        return !models.isEmpty();
    }

    protected void retrievePageData() {
        if (hasFetchedData()) {
            return;
        }
        showLoadingView();
        getInitialData(getPageSize());
    }

    protected final boolean hasFetchedData() {
        if (!hasValidModels()) {
            PageViewModelProvider pageModelProvider = getPageModelProvider();
            if (!(pageModelProvider != null && pageModelProvider.hasPageData())) {
                return false;
            }
        }
        return true;
    }

    protected void getInitialData(int pageSize) {
        if (shouldShowNoNetworkDialog()) {
            showNetworkErrorDialog();
            return;
        }
        if (shouldBlockDataFetch()) {
            return;
        }
        PageLoadLatencyCode pageLoadLatencyCode = getPageLoadLatencyCode();
        if (pageLoadLatencyCode != null) {
            LatencyTracker.INSTANCE.track(new LatencyEventDescriptor(LatencyTrackingLeg.REQUEST, pageLoadLatencyCode));
            Log.debug(this.TAG, "Begin LatencyTrackingLeg.REQUEST");
        }
        PageViewModelProvider pageModelProvider = getPageModelProvider();
        if (pageModelProvider == null) {
            return;
        }
        pageModelProvider.fetchInitPage(pageSize);
    }

    protected void getMoreData(int pageIndex, int pageSize) {
        PageViewModelProvider pageModelProvider;
        if (shouldBlockDataFetch() || (pageModelProvider = getPageModelProvider()) == null) {
            return;
        }
        pageModelProvider.fetchMorePage(pageIndex, pageSize);
    }

    protected void onLoadInitPage() {
        if (this.styleSheet != null) {
            setupViews();
        }
    }

    protected void onLoadMorePage() {
        BaseRecyclerViewProvider recyclerViewProvider;
        if (hasValidModels() && this.recyclerViewProvider == null && this.styleSheet != null) {
            setupViews();
        }
        PageGridViewModel pageGridViewModel = this.pageModel;
        if (pageGridViewModel == null || (recyclerViewProvider = getRecyclerViewProvider()) == null) {
            return;
        }
        recyclerViewProvider.addItemsToAdapter(pageGridViewModel.getModels());
    }

    protected void onLoadUpdatedPage() {
        BaseRecyclerViewProvider recyclerViewProvider;
        PageGridViewModel pageGridViewModel = this.pageModel;
        if (pageGridViewModel == null || (recyclerViewProvider = getRecyclerViewProvider()) == null) {
            return;
        }
        recyclerViewProvider.updateAdapter(pageGridViewModel.getModels(), getDiffUtilFactory(), getShouldUseDiffUtilForReorders());
    }

    protected void onLoadPageRangeChanged(IntRange indicesChanged) {
        List<BaseViewModel> models;
        BaseRecyclerViewProvider recyclerViewProvider;
        Intrinsics.checkNotNullParameter(indicesChanged, "indicesChanged");
        PageGridViewModel pageGridViewModel = this.pageModel;
        if (pageGridViewModel == null || (models = pageGridViewModel.getModels()) == null || (recyclerViewProvider = getRecyclerViewProvider()) == null) {
            return;
        }
        recyclerViewProvider.updateAdapterForRange(models, indicesChanged.getFirst(), CollectionsKt___CollectionsKt.count(indicesChanged));
    }

    protected void setupViews() {
        PageLoadLatencyCode pageLoadLatencyCode = getPageLoadLatencyCode();
        if (pageLoadLatencyCode != null) {
            LatencyTracker.INSTANCE.track(new LatencyEventDescriptor(LatencyTrackingLeg.RENDERING, pageLoadLatencyCode));
        }
        Context context = getContext();
        if (context != null && getRecyclerViewProvider() == null) {
            StyleSheet styleSheet = getStyleSheet();
            Intrinsics.checkNotNull(styleSheet);
            setRecyclerViewProvider(getRecyclerViewProvider(context, styleSheet));
        }
        createRootView();
    }

    protected final void createRootView() {
        PageGridViewModel pageGridViewModel = this.pageModel;
        if (pageGridViewModel == null) {
            return;
        }
        List<BaseViewModel> models = pageGridViewModel.getModels();
        final RecyclerView recyclerView = getRecyclerView();
        if (recyclerView == null) {
            return;
        }
        BaseRecyclerViewProvider recyclerViewProvider = getRecyclerViewProvider();
        if (recyclerViewProvider != null) {
            int tileType = pageGridViewModel.getTileType();
            ViewParent parent = recyclerView.getParent();
            if (parent == null) {
                throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup");
            }
            recyclerViewProvider.createRootView(models, tileType, recyclerView, (ViewGroup) parent);
        }
        BaseRecyclerViewProvider recyclerViewProvider2 = getRecyclerViewProvider();
        if (recyclerViewProvider2 != null) {
            recyclerViewProvider2.setAdapter(recyclerView, models, getBinderList());
        }
        final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager != null) {
            setEndlessScrollListener(new EndlessScrollListener(this) { // from class: com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment$createRootView$1$1$1$1
                final /* synthetic */ AbstractBaseViewsFragment this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(this.$layoutManager, 0, 2, null);
                    this.this$0 = this;
                    Intrinsics.checkNotNullExpressionValue(this.$layoutManager, "layoutManager");
                }

                @Override // com.amazon.music.views.library.recyclerview.EndlessScrollListener
                public void onLoadMore(int page, int totalItemsCount) {
                    this.this$0.getMoreData(page, totalItemsCount);
                }
            });
            EndlessScrollListener endlessScrollListener = getEndlessScrollListener();
            if (endlessScrollListener != null) {
                recyclerView.addOnScrollListener(endlessScrollListener);
            }
        }
        FastScrollerHelper.INSTANCE.initFastScroller(getFastScroller(), recyclerView, new Function1<FastScroller, Unit>() { // from class: com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment$createRootView$1$1$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(FastScroller fastScroller) {
                invoke2(fastScroller);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(FastScroller fastScroller) {
                View next;
                Intrinsics.checkNotNullParameter(fastScroller, "fastScroller");
                ViewGroup.LayoutParams layoutParams = fastScroller.getLayoutParams();
                RecyclerView recyclerView2 = recyclerView;
                AbstractBaseViewsFragment abstractBaseViewsFragment = this;
                Iterator<View> it = ViewGroupKt.getChildren(recyclerView2).iterator();
                while (true) {
                    if (!it.hasNext()) {
                        next = null;
                        break;
                    } else {
                        next = it.next();
                        if (next instanceof DefaultPageHeaderView) {
                            break;
                        }
                    }
                }
                DefaultPageHeaderView defaultPageHeaderView = (DefaultPageHeaderView) (next instanceof DefaultPageHeaderView ? next : null);
                int measuredHeight = defaultPageHeaderView == null ? 0 : defaultPageHeaderView.getMeasuredHeight();
                View view = abstractBaseViewsFragment.getView();
                if (view != null) {
                    layoutParams.height = view.getMeasuredHeight() - measuredHeight;
                }
                fastScroller.setLayoutParams(layoutParams);
            }
        });
        recyclerView.post(new Runnable() { // from class: com.amazon.mp3.catalog.fragment.AbstractBaseViewsFragment$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                AbstractBaseViewsFragment.m450createRootView$lambda33$lambda32$lambda31(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: createRootView$lambda-33$lambda-32$lambda-31, reason: not valid java name */
    public static final void m450createRootView$lambda33$lambda32$lambda31(AbstractBaseViewsFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.sendLatencyLoadCompleteEvent();
    }

    protected final void scrollToPosition(int position) {
        RecyclerView recyclerView = this.recyclerView;
        if (recyclerView == null) {
            return;
        }
        recyclerView.scrollToPosition(position);
    }

    public static /* synthetic */ void sendUiPageViewMetric$default(AbstractBaseViewsFragment abstractBaseViewsFragment, PageType pageType, String str, String str2, String str3, String str4, String str5, String str6, Long l, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sendUiPageViewMetric");
        }
        abstractBaseViewsFragment.sendUiPageViewMetric(pageType, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? null : str3, (i & 16) != 0 ? null : str4, (i & 32) != 0 ? null : str5, (i & 64) != 0 ? null : str6, (i & 128) == 0 ? l : null);
    }

    protected final void sendUiPageViewMetric(PageType pageType, String pageSubType, String detailPageItemId, String detailPageItemIdType, String externalReference, String associateTag, String blockRef, Long loadTime) {
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        UiPageViewEvent.Builder builderWithViewType = UiPageViewEvent.builder(pageType.getMetricsValue()).withEventTime(System.currentTimeMillis()).withViewType(ScreenUtil.getScreenViewType(getContext()));
        if (pageSubType != null) {
            builderWithViewType.withPageSubType(pageSubType);
        }
        if (detailPageItemId != null) {
            builderWithViewType.withDetailPageItemId(detailPageItemId);
        }
        if (detailPageItemIdType != null) {
            builderWithViewType.withDetailPageItemIdType(detailPageItemIdType);
        }
        if (externalReference != null) {
            builderWithViewType.withExternalReference(externalReference);
        }
        if (associateTag != null) {
            builderWithViewType.withAssociateTag(associateTag);
        }
        if (blockRef != null) {
            builderWithViewType.withBlockRef(blockRef);
        }
        if (loadTime != null) {
            loadTime.longValue();
            builderWithViewType.withLoadTime(loadTime.longValue());
        }
        MetricsHolder.getManager().handleEvent(builderWithViewType.build());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void sendUiClickMetric$default(AbstractBaseViewsFragment abstractBaseViewsFragment, ActionType actionType, InteractionType interactionType, PageType pageType, String str, String str2, EntityIdType entityIdType, EntityType entityType, Integer num, String str3, String str4, String str5, List list, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sendUiClickMetric");
        }
        abstractBaseViewsFragment.sendUiClickMetric(actionType, (i & 2) != 0 ? InteractionType.TAP : interactionType, (i & 4) != 0 ? null : pageType, (i & 8) != 0 ? null : str, (i & 16) != 0 ? null : str2, (i & 32) != 0 ? null : entityIdType, (i & 64) != 0 ? null : entityType, (i & 128) != 0 ? null : num, (i & 256) != 0 ? null : str3, (i & 512) != 0 ? null : str4, (i & 1024) != 0 ? null : str5, (i & 2048) == 0 ? list : null);
    }

    protected final void sendUiClickMetric(ActionType actionType, InteractionType interactionType, PageType pageType, String pageSubType, String entityId, EntityIdType entityIdType, EntityType entityType, Integer entityPosition, String url, String blockRef, String refMarker, List<? extends Map<String, String>> contentInfo) {
        Intrinsics.checkNotNullParameter(actionType, "actionType");
        Intrinsics.checkNotNullParameter(interactionType, "interactionType");
        MetricsUtil.emitLatencyStartLegMetricForAllowedActionTypes(actionType);
        UiClickEvent.Builder builderWithEventTime = UiClickEvent.builder(actionType).withInteractionType(interactionType).withEventTime(System.currentTimeMillis());
        if (pageType != null) {
            builderWithEventTime.withPageType(pageType);
        }
        if (pageSubType != null) {
            builderWithEventTime.withPageSubType(pageSubType);
        }
        if (entityId != null) {
            builderWithEventTime.withEntityId(entityId);
        }
        if (entityIdType != null) {
            builderWithEventTime.withEntityIdType(entityIdType);
        }
        if (entityType != null) {
            builderWithEventTime.withEntityType(entityType);
        }
        if (entityPosition != null) {
            entityPosition.intValue();
            builderWithEventTime.withEntityPos(entityPosition.intValue());
        }
        if (url != null) {
            builderWithEventTime.withUrl(url);
        }
        if (blockRef != null) {
            builderWithEventTime.withRefMarker(refMarker);
        }
        if (contentInfo != null) {
            builderWithEventTime.withContentInfo(contentInfo);
        }
        MetricsHolder.getManager().handleEvent(builderWithEventTime.build());
    }

    protected boolean shouldBlockPlaybackAndDownload(ContentMetadata metadata, boolean isSourceLocal, boolean isShuffled) {
        ArrayList arrayList;
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        List<BaseViewModel> trackList = getTrackList();
        if (trackList == null) {
            arrayList = null;
        } else {
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : trackList) {
                BaseViewModel baseViewModel = (BaseViewModel) obj;
                if ((baseViewModel instanceof BaseViewContentModel) && (((BaseViewContentModel) baseViewModel).getMetadata() instanceof TrackMetadata)) {
                    arrayList2.add(obj);
                }
            }
            arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList2, 10));
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                arrayList.add((TrackMetadata) ((BaseViewContentModel) ((BaseViewModel) it.next())).getMetadata());
            }
        }
        return FreeTierPlaybackUtil.shouldBlockPlaybackAndDownload(getContentEnabilityProvider(), metadata, arrayList, isSourceLocal);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        AccountManagerSingleton.get().deregisterListener(getMAccountStateChangeListener());
        super.onStop();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        RecyclerView recyclerView;
        super.onDestroyView();
        EndlessScrollListener endlessScrollListener = this.endlessScrollListener;
        if (endlessScrollListener != null && (recyclerView = getRecyclerView()) != null) {
            recyclerView.removeOnScrollListener(endlessScrollListener);
        }
        RecyclerView recyclerView2 = this.recyclerView;
        if (recyclerView2 == null) {
            return;
        }
        recyclerView2.setAdapter(null);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        BaseRecyclerViewProvider baseRecyclerViewProvider = this.recyclerViewProvider;
        if (baseRecyclerViewProvider != null) {
            baseRecyclerViewProvider.cleanupAdapterProvider();
        }
        super.onDestroy();
    }

    private final void sendLatencyLoadCompleteEvent() {
        if (this.initialPageLoadSent) {
            return;
        }
        this.initialPageLoadSent = true;
        PageLoadLatencyCode pageLoadLatencyCode = getPageLoadLatencyCode();
        if (pageLoadLatencyCode == null) {
            return;
        }
        LatencyTracker.INSTANCE.track(new LatencyEventDescriptor(LatencyTrackingLeg.LOAD_COMPLETE, pageLoadLatencyCode));
    }
}
