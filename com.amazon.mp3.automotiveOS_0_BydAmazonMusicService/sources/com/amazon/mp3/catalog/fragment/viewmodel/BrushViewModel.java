package com.amazon.mp3.catalog.fragment.viewmodel;

import android.app.Application;
import android.content.Context;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.auto.carmode.CarModeUserPresetHandlerImpl;
import com.amazon.mp3.brush.CommentaryStateUseCase;
import com.amazon.mp3.brush.FollowStateUseCase;
import com.amazon.mp3.brush.LibraryStateUseCase;
import com.amazon.mp3.brush.PodcastStatesUseCase;
import com.amazon.mp3.brush.converters.TrackDownloadStateModelConverter;
import com.amazon.mp3.catalog.fragment.adapter.BaseViewsDiffUtilFactory;
import com.amazon.mp3.catalog.fragment.adapter.FilteredPageCallback;
import com.amazon.mp3.catalog.fragment.usecase.PageModelUseCase;
import com.amazon.mp3.catalog.fragment.viewmodel.BaseViewsRefinementManager;
import com.amazon.mp3.catalog.fragment.viewmodel.BrushViewModel;
import com.amazon.mp3.catalog.fragment.viewmodel.GroupDownloadStateModelProvider;
import com.amazon.mp3.configuration.PreferenceUpdateProvider;
import com.amazon.mp3.fragment.viewmodel.LibrarySyncStateUpdater;
import com.amazon.mp3.fragment.viewmodel.OnFilterChangedListener;
import com.amazon.mp3.library.provider.ContentModelLibraryStateManager;
import com.amazon.mp3.library.provider.LibraryStateData;
import com.amazon.mp3.library.provider.LibraryStateProvider;
import com.amazon.mp3.library.provider.source.cirrus.dbutils.LocalRepositoryEvents;
import com.amazon.mp3.library.provider.source.cirrus.dbutils.RepositoryEvent;
import com.amazon.mp3.library.provider.source.cirrus.dbutils.RepositoryEventType;
import com.amazon.mp3.likes.LikesServiceProxy;
import com.amazon.mp3.platform.providers.LibrarySyncStateProvider;
import com.amazon.mp3.playback.state.LibraryEqualizerStateManager;
import com.amazon.mp3.playback.state.PageStateManager;
import com.amazon.mp3.prime.ContentOwnershipStatus;
import com.amazon.mp3.util.Log;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.account.AccountStateChangeListener;
import com.amazon.music.account.AccountStateField;
import com.amazon.music.browse.brush.BrushLatencyReportingUtil;
import com.amazon.music.downloads.Group;
import com.amazon.music.find.model.search.SearchItem;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.PlaybackController;
import com.amazon.music.media.playback.ratings.LikeState;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.LatencyTrackingLeg;
import com.amazon.music.platform.AtomicMap;
import com.amazon.music.platform.data.NetworkInfo;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.locator.ProvidersKt;
import com.amazon.music.platform.providers.FeatureGateProviderImpl;
import com.amazon.music.platform.providers.InterfaceProvider;
import com.amazon.music.platform.providers.NetworkInfoProvider;
import com.amazon.music.views.library.metadata.TrackMetadata;
import com.amazon.music.views.library.models.DescriptiveRowItemModel;
import com.amazon.music.views.library.models.DownloadStateModel;
import com.amazon.music.views.library.models.ErrorModel;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.models.base.Eligible;
import com.amazon.podcast.ActionBarInfo;
import com.amazon.podcast.AdvertisingPreferencesProviderImpl;
import com.amazon.podcast.DownloadSettings;
import com.amazon.podcast.FragmentInfo;
import com.amazon.podcast.NetworkSettings;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.PodcastDynamicMessaging;
import com.amazon.podcast.PodcastEpisode;
import com.amazon.podcast.PodcastSharingProvider;
import com.amazon.podcast.PodcastShow;
import com.amazon.podcast.UserInfo;
import com.amazon.podcast.bootstrap.Bootstrap;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

/* JADX INFO: compiled from: BrushViewModel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u009e\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\b*\u0002§\u0001\b\u0016\u0018\u0000 í\u00012\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u00022\u00020\u00042\u00020\u0005:\u0002í\u0001B^\u0012\u0006\u0010\u001a\u001a\u00020\u0019\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\b\u0010\u001b\u001a\u0004\u0018\u00010\u000e\u0012\u0006\u0010\u001c\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\u000b\b\u0002\u0010\u0086\u0001\u001a\u0004\u0018\u00010\u000e\u0012\u001a\u0010\u001f\u001a\u0016\u0012\u0004\u0012\u00020\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u001e\u0018\u00010\u001d¢\u0006\u0006\bë\u0001\u0010ì\u0001J\b\u0010\u0007\u001a\u00020\u0006H\u0002J\u0010\u0010\n\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\bH\u0002J\u0012\u0010\r\u001a\u00020\u00062\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0002J\u0010\u0010\u0010\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u000eH\u0002J\u0010\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0011H\u0002J\b\u0010\u0014\u001a\u00020\u0006H\u0002J\b\u0010\u0015\u001a\u00020\u0006H\u0002J\b\u0010\u0016\u001a\u00020\u0006H\u0002J\b\u0010\u0017\u001a\u00020\u0006H\u0002J\b\u0010\u0018\u001a\u00020\u0006H\u0002JF\u0010!\u001a\u00020 2\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u000f\u001a\u00020\u000e2\b\u0010\u001b\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u001c\u001a\u00020\b2\u001a\u0010\u001f\u001a\u0016\u0012\u0004\u0012\u00020\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u001e\u0018\u00010\u001dH\u0002J\b\u0010#\u001a\u00020\"H\u0016J\b\u0010%\u001a\u0004\u0018\u00010$J\b\u0010'\u001a\u0004\u0018\u00010&J\b\u0010(\u001a\u00020\bH\u0016J\u000e\u0010*\u001a\b\u0012\u0004\u0012\u00020\u000b0)H\u0016J\u000e\u0010,\u001a\b\u0012\u0004\u0012\u00020+0)H\u0016J\u0006\u0010.\u001a\u00020-J\n\u00100\u001a\u0004\u0018\u00010/H\u0016J\b\u00101\u001a\u00020\u0006H\u0016J\b\u00102\u001a\u00020\u0006H\u0016J\u0010\u00105\u001a\u00020\u00062\u0006\u00104\u001a\u000203H\u0016J\u000e\u00106\u001a\b\u0012\u0004\u0012\u00020\b0)H\u0016J\u0010\u00108\u001a\u00020\u00062\u0006\u00107\u001a\u00020\u0011H\u0016J\"\u0010=\u001a\u00020\u00062\u0006\u00107\u001a\u00020\u00112\u0006\u0010:\u001a\u0002092\b\u0010<\u001a\u0004\u0018\u00010;H\u0016J\u0018\u0010>\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u00107\u001a\u00020\u0011H\u0016J\u0010\u0010@\u001a\u00020\u00062\u0006\u0010?\u001a\u00020\bH\u0004J\u000e\u0010A\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0011J\b\u0010B\u001a\u00020\u0006H\u0014J\u0016\u0010E\u001a\u00020\u00062\u0006\u0010D\u001a\u00020C2\u0006\u0010\u0012\u001a\u00020\u0011J\f\u0010G\u001a\b\u0012\u0004\u0012\u00020\b0FJ\u0006\u0010H\u001a\u00020\u0006J\u000e\u0010J\u001a\u00020\u00062\u0006\u0010I\u001a\u00020\u000eJ,\u0010M\u001a\u00020\u00062\u0006\u0010L\u001a\u00020K2\u001c\b\u0002\u0010\u001f\u001a\u0016\u0012\u0004\u0012\u00020\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u001e\u0018\u00010\u001dJ\b\u0010N\u001a\u00020\u0006H\u0016J\b\u0010O\u001a\u00020\u0006H\u0016J\u000e\u0010Q\u001a\b\u0012\u0004\u0012\u00020P0)H\u0016J\u000e\u0010S\u001a\b\u0012\u0004\u0012\u00020R0)H\u0016J\u000e\u0010V\u001a\b\u0012\u0004\u0012\u00020U0TH\u0016J \u0010Z\u001a\u00020\u00062\u0006\u0010W\u001a\u00020\u00032\u0006\u0010X\u001a\u00020\u00112\u0006\u0010Y\u001a\u00020\u0011H\u0016J\u0006\u0010[\u001a\u00020\u0006J\u0006\u0010\\\u001a\u00020\u0006J\b\u0010]\u001a\u00020\u0006H\u0016J\u000e\u0010`\u001a\u00020\u00062\u0006\u0010_\u001a\u00020^J\b\u0010a\u001a\u00020\u0006H\u0014J\u0006\u0010b\u001a\u00020\u0011J\u001a\u0010g\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020f0e0T2\u0006\u0010d\u001a\u00020cJ\u001a\u0010i\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020h0e0T2\u0006\u0010d\u001a\u00020cJ\u001a\u0010j\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020f0e0T2\u0006\u0010d\u001a\u00020cJ\u000e\u0010l\u001a\u00020k2\u0006\u0010d\u001a\u00020cJ\u001e\u0010o\u001a\u00020\u00062\u000e\u0010m\u001a\n\u0012\u0004\u0012\u00020f\u0018\u00010\u001e2\u0006\u0010n\u001a\u00020\u000bJ\u001e\u0010q\u001a\u00020\u00062\u000e\u0010p\u001a\n\u0012\u0004\u0012\u00020h\u0018\u00010\u001e2\u0006\u0010n\u001a\u00020\u000bJ\u001e\u0010r\u001a\u00020\u00062\u000e\u0010m\u001a\n\u0012\u0004\u0012\u00020f\u0018\u00010\u001e2\u0006\u0010n\u001a\u00020\u000bJ\u000e\u0010t\u001a\u00020\u00062\u0006\u0010s\u001a\u00020\u000eJ\u0006\u0010u\u001a\u00020\u0006J\u0006\u0010v\u001a\u00020\u0006J\u0016\u0010y\u001a\u00020\u00062\u000e\u0010x\u001a\n\u0012\u0004\u0012\u00020w\u0018\u00010\u001eJ\u0014\u0010{\u001a\u00020\u00062\f\u0010L\u001a\b\u0012\u0004\u0012\u00020\u00060zJ\u0017\u0010\u0080\u0001\u001a\u00020\u00062\u0006\u0010}\u001a\u00020|2\u0006\u0010\u007f\u001a\u00020~R'\u0010\u000f\u001a\u00020\u000e8\u0006@\u0006X\u0086\u000e¢\u0006\u0017\n\u0005\b\u000f\u0010\u0081\u0001\u001a\u0006\b\u0082\u0001\u0010\u0083\u0001\"\u0006\b\u0084\u0001\u0010\u0085\u0001R\u001e\u0010\u0086\u0001\u001a\u0004\u0018\u00010\u000e8\u0006¢\u0006\u0010\n\u0006\b\u0086\u0001\u0010\u0081\u0001\u001a\u0006\b\u0087\u0001\u0010\u0083\u0001R.\u0010\u001f\u001a\u0016\u0012\u0004\u0012\u00020\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u001e\u0018\u00010\u001d8\u0006¢\u0006\u000f\n\u0005\b\u001f\u0010\u0088\u0001\u001a\u0006\b\u0089\u0001\u0010\u008a\u0001R\u0018\u0010\u008c\u0001\u001a\u00030\u008b\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u008c\u0001\u0010\u008d\u0001R\u0018\u0010\u008f\u0001\u001a\u00030\u008e\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u008f\u0001\u0010\u0090\u0001R%\u0010\u0091\u0001\u001a\b\u0012\u0004\u0012\u00020\u000b0)8\u0004X\u0084\u0004¢\u0006\u0010\n\u0006\b\u0091\u0001\u0010\u0092\u0001\u001a\u0006\b\u0093\u0001\u0010\u0094\u0001R\u001d\u0010\u0095\u0001\u001a\b\u0012\u0004\u0012\u00020+0)8\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0095\u0001\u0010\u0092\u0001RD\u0010\u0099\u0001\u001a/\u0012\u000f\u0012\r \u0098\u0001*\u0005\u0018\u00010\u0097\u00010\u0097\u0001 \u0098\u0001*\u0016\u0012\u000f\u0012\r \u0098\u0001*\u0005\u0018\u00010\u0097\u00010\u0097\u0001\u0018\u00010\u0096\u00010\u0096\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0099\u0001\u0010\u009a\u0001R\u0017\u0010\u009b\u0001\u001a\u00020 8\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u009b\u0001\u0010\u009c\u0001R\u0017\u0010\u009d\u0001\u001a\u00020\"8\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u009d\u0001\u0010\u009e\u0001R\u0017\u0010\u009f\u0001\u001a\u00020-8\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u009f\u0001\u0010 \u0001R\u0018\u0010¢\u0001\u001a\u00030¡\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b¢\u0001\u0010£\u0001R\u0018\u0010¥\u0001\u001a\u00030¤\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b¥\u0001\u0010¦\u0001R\u0018\u0010¨\u0001\u001a\u00030§\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b¨\u0001\u0010©\u0001R\u0018\u0010«\u0001\u001a\u00030ª\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b«\u0001\u0010¬\u0001R\u001c\u0010®\u0001\u001a\u0005\u0018\u00010\u00ad\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b®\u0001\u0010¯\u0001R!\u0010°\u0001\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010z8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b°\u0001\u0010±\u0001R\u001b\u0010²\u0001\u001a\u0004\u0018\u00010&8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b²\u0001\u0010³\u0001R\u001b\u0010´\u0001\u001a\u0004\u0018\u00010$8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b´\u0001\u0010µ\u0001R\u001c\u0010·\u0001\u001a\u0005\u0018\u00010¶\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b·\u0001\u0010¸\u0001R\u001c\u0010º\u0001\u001a\u0005\u0018\u00010¹\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bº\u0001\u0010»\u0001R\u001c\u0010¼\u0001\u001a\u0005\u0018\u00010¹\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b¼\u0001\u0010»\u0001R\u001c\u0010½\u0001\u001a\u0005\u0018\u00010¹\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b½\u0001\u0010»\u0001R\u001c\u0010¾\u0001\u001a\u0005\u0018\u00010¹\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b¾\u0001\u0010»\u0001R\u001c\u0010¿\u0001\u001a\u0005\u0018\u00010¹\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b¿\u0001\u0010»\u0001R\u001c\u0010À\u0001\u001a\u0005\u0018\u00010¹\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÀ\u0001\u0010»\u0001R\u0018\u0010Â\u0001\u001a\u00030Á\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\bÂ\u0001\u0010Ã\u0001R\u001d\u0010Ä\u0001\u001a\b\u0012\u0004\u0012\u00020P0)8\u0002X\u0082\u0004¢\u0006\b\n\u0006\bÄ\u0001\u0010\u0092\u0001R\u001d\u0010Å\u0001\u001a\b\u0012\u0004\u0012\u00020R0)8\u0002X\u0082\u0004¢\u0006\b\n\u0006\bÅ\u0001\u0010\u0092\u0001R\u001c\u0010Æ\u0001\u001a\u0005\u0018\u00010¹\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÆ\u0001\u0010»\u0001R\u001c\u0010Ç\u0001\u001a\u0005\u0018\u00010¹\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÇ\u0001\u0010»\u0001R&\u0010É\u0001\u001a\t\u0012\u0005\u0012\u00030È\u00010\u001e8\u0010X\u0090\u0004¢\u0006\u0010\n\u0006\bÉ\u0001\u0010Ê\u0001\u001a\u0006\bË\u0001\u0010Ì\u0001R&\u0010Ñ\u0001\u001a\b\u0012\u0004\u0012\u00020U0T8BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\bÍ\u0001\u0010Î\u0001\u001a\u0006\bÏ\u0001\u0010Ð\u0001R\u001b\u0010Ò\u0001\u001a\u0004\u0018\u00010/8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÒ\u0001\u0010Ó\u0001R\u001d\u0010Ô\u0001\u001a\b\u0012\u0004\u0012\u0002030)8\u0002X\u0082\u0004¢\u0006\b\n\u0006\bÔ\u0001\u0010\u0092\u0001R\u001d\u0010Õ\u0001\u001a\b\u0012\u0004\u0012\u00020\b0)8\u0002X\u0082\u0004¢\u0006\b\n\u0006\bÕ\u0001\u0010\u0092\u0001R\u001a\u0010×\u0001\u001a\u0005\u0018\u00010Ö\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b×\u0001\u0010Ø\u0001R\u0018\u0010?\u001a\u00030Ù\u00018\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b?\u0010Ú\u0001R\u0017\u0010\u001c\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b\u001c\u0010Û\u0001R\u0018\u0010Ý\u0001\u001a\u00030Ü\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\bÝ\u0001\u0010Þ\u0001R\u0018\u0010à\u0001\u001a\u00030ß\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\bà\u0001\u0010á\u0001R\u001f\u0010â\u0001\u001a\u00020\b8\u0014X\u0094D¢\u0006\u0010\n\u0006\bâ\u0001\u0010Û\u0001\u001a\u0006\bã\u0001\u0010ä\u0001R'\u0010ç\u0001\u001a\n\u0012\u0005\u0012\u00030æ\u00010å\u00018\u0014X\u0094\u0004¢\u0006\u0010\n\u0006\bç\u0001\u0010è\u0001\u001a\u0006\bé\u0001\u0010ê\u0001¨\u0006î\u0001"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/viewmodel/BrushViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "Lcom/amazon/mp3/library/provider/LibraryStateProvider$Listener;", "Lcom/amazon/music/find/model/search/SearchItem;", "Lcom/amazon/mp3/catalog/fragment/viewmodel/PageViewModelProvider;", "Lcom/amazon/mp3/catalog/fragment/viewmodel/BaseViewsRefinementManager$Provider;", "", "initPageStateManager", "", "supportRefinements", "initRefinementsManager", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "pageGridViewModel", "addLikesToModels", "", "uri", "isCachedPageLoadEnabled", "", "pageIndex", "tryFetchNextPage", "tryFetchPageOnCacheExpired", "initLibraryStateObservation", "initCommentaryStateUseCase", "initFollowStateUseCase", "initPodcastStatesUseCase", "Landroid/app/Application;", "application", "nextPageToken", "isArtistUpsell", "", "", "refinementLists", "Lcom/amazon/mp3/catalog/fragment/usecase/PageModelUseCase;", "getPageModelUseCase", "Lcom/amazon/mp3/playback/state/PageStateManager;", "getPageStateManager", "Lcom/amazon/mp3/brush/CommentaryStateUseCase;", "getCommentaryStateUseCase", "Lcom/amazon/mp3/brush/FollowStateUseCase;", "getFollowStateUseCase", "hasPageData", "Landroidx/lifecycle/MutableLiveData;", "getPageModelLiveData", "Lcom/amazon/music/views/library/models/ErrorModel;", "getErrorLiveData", "Lcom/amazon/mp3/library/provider/LibraryStateProvider;", "getLibraryStateProvider", "Lcom/amazon/mp3/catalog/fragment/viewmodel/BaseViewsRefinementManager;", "getRefinementManager", "startObservingNetworkEvents", "stopObservingNetworkEvents", "Lcom/amazon/music/platform/data/NetworkInfo;", "value", "onEvent", "getHasConnectionLiveData", "pageSize", "fetchInitPage", "Lcom/amazon/mp3/catalog/fragment/adapter/BaseViewsDiffUtilFactory;", "diffUtilFactory", "Lcom/amazon/mp3/catalog/fragment/viewmodel/FetchType;", "fetchType", "fetchUpdates", "fetchMorePage", "isFetching", "setIsFetching", "fetchData", "tryFetchInitPage", "", "throwable", "onError", "Lrx/Observable;", "isPageCacheExpired", "fetchNewData", "browseId", "fetchFilteredPageData", "Lcom/amazon/mp3/catalog/fragment/adapter/FilteredPageCallback;", "callback", "fetchFilteredPageDataBrushV3", "startObservingLibrarySyncEvents", "stopObservingLibrarySyncEvents", "Lcom/amazon/mp3/platform/providers/LibrarySyncStateProvider$LibrarySyncState;", "getLibrarySyncStateLiveData", "Lcom/amazon/mp3/fragment/viewmodel/LibrarySyncStateUpdater$LibrarySyncEvent;", "getLibrarySyncEventLiveData", "Landroidx/lifecycle/LiveData;", "Lcom/amazon/mp3/library/provider/source/cirrus/dbutils/RepositoryEvent;", "getLocalRepositoryUpdateEventLiveData", "item", "ownershipState", "downloadState", "onStateChanged", "onResume", "refreshModelStateProviders", "refresh", "Lcom/amazon/mp3/fragment/viewmodel/OnFilterChangedListener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "setDownloadChangedListener", "onCleared", "getPodcastPlaybackState", "Landroidx/fragment/app/FragmentActivity;", "fragmentActivity", "", "Lcom/amazon/podcast/PodcastEpisode;", "getPodcastJumpBackInEpisodes", "Lcom/amazon/podcast/PodcastShow;", "getPodcastFollowedShows", "getPodcastSavedEpisodes", "Lcom/amazon/podcast/bootstrap/Bootstrap;", "getPodcastBootstrap", "podcastEpisodes", "pageModel", "updatePodcastJumpBackInEpisodes", "podcastShows", "updatePodcastFollowedShows", "updatePodcastSavedEpisodes", "groupAsin", "registerDownloadStateListener", "registerDownloadStateListenerForTracks", "unregisterDownloadStateListener", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "trackList", "updateTracksDownloadState", "Lkotlin/Function0;", "setOnLibraryStateChanged", "Lcom/amazon/music/views/library/models/base/Eligible;", "model", "Lcom/amazon/music/views/library/models/DownloadStateModel;", "downloadStateModel", "updateModelEligibility", "Ljava/lang/String;", "getUri", "()Ljava/lang/String;", "setUri", "(Ljava/lang/String;)V", "activeFilterModelBrowseId", "getActiveFilterModelBrowseId", "Ljava/util/Map;", "getRefinementLists", "()Ljava/util/Map;", "Lkotlinx/coroutines/CompletableJob;", "viewModelJob", "Lkotlinx/coroutines/CompletableJob;", "Lkotlinx/coroutines/CoroutineScope;", "defaultScope", "Lkotlinx/coroutines/CoroutineScope;", "gridPageModel", "Landroidx/lifecycle/MutableLiveData;", "getGridPageModel", "()Landroidx/lifecycle/MutableLiveData;", "errorModel", "Lrx/subjects/PublishSubject;", "Ljava/lang/Void;", "kotlin.jvm.PlatformType", "mOnClearedSubject", "Lrx/subjects/PublishSubject;", "pageModelUsecase", "Lcom/amazon/mp3/catalog/fragment/usecase/PageModelUseCase;", "pageStateManager", "Lcom/amazon/mp3/playback/state/PageStateManager;", "libraryStateProvider", "Lcom/amazon/mp3/library/provider/LibraryStateProvider;", "Lcom/amazon/mp3/playback/state/LibraryEqualizerStateManager;", "equalizerStateManager", "Lcom/amazon/mp3/playback/state/LibraryEqualizerStateManager;", "Lcom/amazon/mp3/catalog/fragment/viewmodel/GroupDownloadStateModelProvider;", "downloadStateModelProvider", "Lcom/amazon/mp3/catalog/fragment/viewmodel/GroupDownloadStateModelProvider;", "com/amazon/mp3/catalog/fragment/viewmodel/BrushViewModel$downloadStateModelProviderListener$1", "downloadStateModelProviderListener", "Lcom/amazon/mp3/catalog/fragment/viewmodel/BrushViewModel$downloadStateModelProviderListener$1;", "Lcom/amazon/mp3/library/provider/ContentModelLibraryStateManager;", "libraryState", "Lcom/amazon/mp3/library/provider/ContentModelLibraryStateManager;", "Lcom/amazon/mp3/brush/LibraryStateUseCase;", "libraryStateUseCase", "Lcom/amazon/mp3/brush/LibraryStateUseCase;", "onLibraryStateChanged", "Lkotlin/jvm/functions/Function0;", "followStateUseCase", "Lcom/amazon/mp3/brush/FollowStateUseCase;", "commentaryStateUseCase", "Lcom/amazon/mp3/brush/CommentaryStateUseCase;", "Lcom/amazon/mp3/brush/PodcastStatesUseCase;", "podcastStatesUseCase", "Lcom/amazon/mp3/brush/PodcastStatesUseCase;", "Lrx/Subscription;", "subscription", "Lrx/Subscription;", "nextPageSubscription", "refreshPageSubscription", "newDataPageSubscription", "playStateSubscription", "libraryStateSubscription", "Lcom/amazon/mp3/fragment/viewmodel/LibrarySyncStateUpdater;", "libraryStateUpdater", "Lcom/amazon/mp3/fragment/viewmodel/LibrarySyncStateUpdater;", "librarySyncStateLiveData", "librarySyncEventLiveData", "librarySyncStateSubscription", "librarySyncEventSubscription", "Lcom/amazon/mp3/library/provider/source/cirrus/dbutils/RepositoryEventType;", "repositoryUpdatesEventTypeAllowList", "Ljava/util/List;", "getRepositoryUpdatesEventTypeAllowList$DigitalMusicAndroid3P_automotiveProdRelease", "()Ljava/util/List;", "localRepositoryUpdateLiveData$delegate", "Lkotlin/Lazy;", "getLocalRepositoryUpdateLiveData", "()Landroidx/lifecycle/LiveData;", "localRepositoryUpdateLiveData", "refinementsManager", "Lcom/amazon/mp3/catalog/fragment/viewmodel/BaseViewsRefinementManager;", "networkLiveData", "connectedLiveData", "Lcom/amazon/music/platform/providers/NetworkInfoProvider;", "networkInfoProvider", "Lcom/amazon/music/platform/providers/NetworkInfoProvider;", "Ljava/util/concurrent/atomic/AtomicBoolean;", "Ljava/util/concurrent/atomic/AtomicBoolean;", "Z", "Lcom/amazon/music/account/AccountStateChangeListener;", "mAccountStateChangeListener", "Lcom/amazon/music/account/AccountStateChangeListener;", "Lcom/amazon/mp3/configuration/PreferenceUpdateProvider$PreferenceUpdateReceiver;", "mPreferenceUpdateReceiver", "Lcom/amazon/mp3/configuration/PreferenceUpdateProvider$PreferenceUpdateReceiver;", "shouldPerformInitialFetch", "getShouldPerformInitialFetch", "()Z", "", "Lcom/amazon/music/metrics/mts/event/types/pagelatencyinfo/LatencyTrackingLeg;", "brushLatencyLegs", "Ljava/util/Set;", "getBrushLatencyLegs", "()Ljava/util/Set;", "<init>", "(Landroid/app/Application;Ljava/lang/String;Ljava/lang/String;ZZLjava/lang/String;Ljava/util/Map;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public class BrushViewModel extends AndroidViewModel implements LibraryStateProvider.Listener<SearchItem>, PageViewModelProvider, BaseViewsRefinementManager.Provider {
    private static final String TAG = BrushViewModel.class.getSimpleName();
    private final String activeFilterModelBrowseId;
    private final Set<LatencyTrackingLeg> brushLatencyLegs;
    private CommentaryStateUseCase commentaryStateUseCase;
    private final MutableLiveData<Boolean> connectedLiveData;
    private final CoroutineScope defaultScope;
    private final GroupDownloadStateModelProvider downloadStateModelProvider;
    private final BrushViewModel$downloadStateModelProviderListener$1 downloadStateModelProviderListener;
    private final LibraryEqualizerStateManager equalizerStateManager;
    private final MutableLiveData<ErrorModel> errorModel;
    private FollowStateUseCase followStateUseCase;
    private final MutableLiveData<PageGridViewModel> gridPageModel;
    private boolean isArtistUpsell;
    private AtomicBoolean isFetching;
    private final ContentModelLibraryStateManager libraryState;
    private final LibraryStateProvider libraryStateProvider;
    private Subscription libraryStateSubscription;
    private final LibrarySyncStateUpdater libraryStateUpdater;
    private LibraryStateUseCase libraryStateUseCase;
    private final MutableLiveData<LibrarySyncStateUpdater.LibrarySyncEvent> librarySyncEventLiveData;
    private Subscription librarySyncEventSubscription;
    private final MutableLiveData<LibrarySyncStateProvider.LibrarySyncState> librarySyncStateLiveData;
    private Subscription librarySyncStateSubscription;

    /* JADX INFO: renamed from: localRepositoryUpdateLiveData$delegate, reason: from kotlin metadata */
    private final Lazy localRepositoryUpdateLiveData;
    private final AccountStateChangeListener mAccountStateChangeListener;
    private final PublishSubject<Void> mOnClearedSubject;
    private final PreferenceUpdateProvider.PreferenceUpdateReceiver mPreferenceUpdateReceiver;
    private final NetworkInfoProvider networkInfoProvider;
    private final MutableLiveData<NetworkInfo> networkLiveData;
    private Subscription newDataPageSubscription;
    private Subscription nextPageSubscription;
    private Function0<Unit> onLibraryStateChanged;
    private final PageModelUseCase pageModelUsecase;
    private final PageStateManager pageStateManager;
    private Subscription playStateSubscription;
    private PodcastStatesUseCase podcastStatesUseCase;
    private final Map<String, List<String>> refinementLists;
    private BaseViewsRefinementManager refinementsManager;
    private Subscription refreshPageSubscription;
    private final List<RepositoryEventType> repositoryUpdatesEventTypeAllowList;
    private final boolean shouldPerformInitialFetch;
    private Subscription subscription;
    private String uri;
    private final CompletableJob viewModelJob;

    /* JADX INFO: compiled from: BrushViewModel.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LibrarySyncStateUpdater.LibrarySyncEvent.values().length];
            iArr[LibrarySyncStateUpdater.LibrarySyncEvent.SYNC_COMPLETED.ordinal()] = 1;
            iArr[LibrarySyncStateUpdater.LibrarySyncEvent.CLEAR_CACHE_COMPLETED.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v6, types: [com.amazon.mp3.catalog.fragment.viewmodel.BrushViewModel$downloadStateModelProviderListener$1] */
    public BrushViewModel(Application application, String uri, String str, boolean z, boolean z2, String str2, Map<String, ? extends List<String>> map) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        Intrinsics.checkNotNullParameter(uri, "uri");
        this.uri = uri;
        this.activeFilterModelBrowseId = str2;
        this.refinementLists = map;
        CompletableJob completableJobSupervisorJob$default = SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null);
        this.viewModelJob = completableJobSupervisorJob$default;
        this.defaultScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault().plus(completableJobSupervisorJob$default));
        this.gridPageModel = new MutableLiveData<>();
        this.errorModel = new MutableLiveData<>();
        this.mOnClearedSubject = PublishSubject.create();
        this.downloadStateModelProviderListener = new GroupDownloadStateModelProvider.BauhausDefaultListener() { // from class: com.amazon.mp3.catalog.fragment.viewmodel.BrushViewModel$downloadStateModelProviderListener$1
            {
                super(false, 1, null);
            }

            @Override // com.amazon.mp3.catalog.fragment.viewmodel.GroupDownloadStateModelProvider.BauhausDefaultListener
            /* JADX INFO: renamed from: getPageModel */
            public PageGridViewModel get$pageGridViewModel() {
                return this.this$0.getGridPageModel().getValue();
            }

            @Override // com.amazon.mp3.catalog.fragment.viewmodel.GroupDownloadStateModelProvider.BauhausDefaultListener
            public PageStateManager getPageStateManager() {
                return this.this$0.getPageStateManager();
            }

            @Override // com.amazon.mp3.catalog.fragment.viewmodel.GroupDownloadStateModelProvider.BauhausDefaultListener, com.amazon.mp3.catalog.fragment.viewmodel.GroupDownloadStateModelProvider.Listener
            public void onDownloadStateUpdated(Group group, String requestId, DownloadStateModel downloadStateModel) {
                Intrinsics.checkNotNullParameter(downloadStateModel, "downloadStateModel");
                super.onDownloadStateUpdated(group, requestId, downloadStateModel);
                DownloadStateModel.DownloadState downloadState = downloadStateModel.getDownloadState();
                if (downloadState == DownloadStateModel.DownloadState.DOWNLOADING || downloadState == DownloadStateModel.DownloadState.QUEUING) {
                    return;
                }
                this.this$0.libraryState.requestLibraryState();
            }

            @Override // com.amazon.mp3.catalog.fragment.viewmodel.GroupDownloadStateModelProvider.BauhausDefaultListener, com.amazon.mp3.catalog.fragment.viewmodel.GroupDownloadStateModelProvider.Listener
            public void onDownloadStateUpdated(String trackAsin, DownloadStateModel downloadStateModel) {
                Intrinsics.checkNotNullParameter(downloadStateModel, "downloadStateModel");
                super.onDownloadStateUpdated(trackAsin, downloadStateModel);
                DownloadStateModel.DownloadState downloadState = downloadStateModel.getDownloadState();
                if (downloadState == DownloadStateModel.DownloadState.DOWNLOADING || downloadState == DownloadStateModel.DownloadState.QUEUING || trackAsin == null) {
                    return;
                }
                ContentModelLibraryStateManager.updateLibraryState$default(this.this$0.libraryState, trackAsin, new LibraryStateData(null, null, Integer.valueOf(new TrackDownloadStateModelConverter().convert(downloadStateModel)), 3, null), (Function0) null, 4, (Object) null);
            }
        };
        Application application2 = getApplication();
        Intrinsics.checkNotNullExpressionValue(application2, "getApplication()");
        this.libraryStateUpdater = new LibrarySyncStateUpdater(application2);
        this.librarySyncStateLiveData = new MutableLiveData<>();
        this.librarySyncEventLiveData = new MutableLiveData<>();
        this.repositoryUpdatesEventTypeAllowList = CollectionsKt__CollectionsKt.emptyList();
        this.localRepositoryUpdateLiveData = LazyKt__LazyJVMKt.lazy(new Function0<LiveData<RepositoryEvent>>() { // from class: com.amazon.mp3.catalog.fragment.viewmodel.BrushViewModel$localRepositoryUpdateLiveData$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final LiveData<RepositoryEvent> invoke() {
                return LocalRepositoryEvents.INSTANCE.getFilteredRepositoryUpdateLiveData(this.this$0.getRepositoryUpdatesEventTypeAllowList$DigitalMusicAndroid3P_automotiveProdRelease());
            }
        });
        this.networkLiveData = new MutableLiveData<>();
        this.connectedLiveData = new MutableLiveData<>();
        AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
        String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(NetworkInfoProvider.class));
        Intrinsics.checkNotNull(originalClassName);
        InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
        this.networkInfoProvider = (NetworkInfoProvider) (interfaceProvider instanceof NetworkInfoProvider ? interfaceProvider : null);
        this.isFetching = new AtomicBoolean(false);
        AccountStateChangeListener accountStateChangeListener = new AccountStateChangeListener() { // from class: com.amazon.mp3.catalog.fragment.viewmodel.BrushViewModel$$ExternalSyntheticLambda7
            @Override // com.amazon.music.account.AccountStateChangeListener
            public final void onAccountStateChange(Set set) {
                BrushViewModel.m626mAccountStateChangeListener$lambda0(this.f$0, set);
            }
        };
        this.mAccountStateChangeListener = accountStateChangeListener;
        PreferenceUpdateProvider.PreferenceUpdateReceiver preferenceUpdateReceiver = new PreferenceUpdateProvider.PreferenceUpdateReceiver() { // from class: com.amazon.mp3.catalog.fragment.viewmodel.BrushViewModel$$ExternalSyntheticLambda8
            @Override // com.amazon.mp3.configuration.PreferenceUpdateProvider.PreferenceUpdateReceiver
            public final void onReceive() {
                BrushViewModel.m627mPreferenceUpdateReceiver$lambda1(this.f$0);
            }
        };
        this.mPreferenceUpdateReceiver = preferenceUpdateReceiver;
        this.shouldPerformInitialFetch = true;
        this.brushLatencyLegs = ArraysKt___ArraysKt.toSet(LatencyTrackingLeg.values());
        this.isArtistUpsell = z;
        this.pageModelUsecase = getPageModelUseCase(application, this.uri, str, z, map);
        LibraryStateProvider libraryStateProvider = new LibraryStateProvider(application);
        this.libraryStateProvider = libraryStateProvider;
        this.downloadStateModelProvider = new GroupDownloadStateModelProvider(application);
        PageStateManager pageStateManager = new PageStateManager();
        this.pageStateManager = pageStateManager;
        this.libraryState = new ContentModelLibraryStateManager(pageStateManager, libraryStateProvider);
        initPageStateManager();
        initLibraryStateObservation();
        initCommentaryStateUseCase();
        initFollowStateUseCase();
        initPodcastStatesUseCase();
        initRefinementsManager(z2);
        if (str2 != null) {
            fetchFilteredPageData(str2);
        } else if (getShouldPerformInitialFetch()) {
            fetchData(0);
        }
        AccountManagerSingleton.get().registerListener(accountStateChangeListener);
        PreferenceUpdateProvider.registerReceiver(preferenceUpdateReceiver);
        Context applicationContext = application.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "application.applicationContext");
        PlaybackController playbackController = Playback.getInstance().getPlaybackController(ControlSource.APP_UI);
        Intrinsics.checkNotNullExpressionValue(playbackController, "getInstance().getPlaybac…ler(ControlSource.APP_UI)");
        this.equalizerStateManager = new LibraryEqualizerStateManager(applicationContext, pageStateManager, playbackController);
        startObservingNetworkEvents();
        startObservingLibrarySyncEvents();
    }

    public final Map<String, List<String>> getRefinementLists() {
        return this.refinementLists;
    }

    protected final MutableLiveData<PageGridViewModel> getGridPageModel() {
        return this.gridPageModel;
    }

    public List<RepositoryEventType> getRepositoryUpdatesEventTypeAllowList$DigitalMusicAndroid3P_automotiveProdRelease() {
        return this.repositoryUpdatesEventTypeAllowList;
    }

    private final LiveData<RepositoryEvent> getLocalRepositoryUpdateLiveData() {
        return (LiveData) this.localRepositoryUpdateLiveData.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: mAccountStateChangeListener$lambda-0, reason: not valid java name */
    public static final void m626mAccountStateChangeListener$lambda0(BrushViewModel this$0, Set set) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (set.contains(AccountStateField.USER_BENEFITS) || set.contains(AccountStateField.USER_TERMS_OF_USE_MARKETPLACE) || set.contains(AccountStateField.USER_HAS_ACCEPTED_TERMS_OF_USE) || set.contains(AccountStateField.EXPLICIT_FILTER_ENABLED_ON_DEVICE)) {
            this$0.fetchData(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: mPreferenceUpdateReceiver$lambda-1, reason: not valid java name */
    public static final void m627mPreferenceUpdateReceiver$lambda1(BrushViewModel this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.fetchData(0);
    }

    protected boolean getShouldPerformInitialFetch() {
        return this.shouldPerformInitialFetch;
    }

    protected Set<LatencyTrackingLeg> getBrushLatencyLegs() {
        return this.brushLatencyLegs;
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.PageViewModelProvider
    public PageStateManager getPageStateManager() {
        return this.pageStateManager;
    }

    public final CommentaryStateUseCase getCommentaryStateUseCase() {
        return this.commentaryStateUseCase;
    }

    public final FollowStateUseCase getFollowStateUseCase() {
        return this.followStateUseCase;
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.PageViewModelProvider
    public boolean hasPageData() {
        return this.gridPageModel.getValue() != null;
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.PageViewModelProvider
    public MutableLiveData<PageGridViewModel> getPageModelLiveData() {
        return this.gridPageModel;
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.PageViewModelProvider
    public MutableLiveData<ErrorModel> getErrorLiveData() {
        return this.errorModel;
    }

    public final LibraryStateProvider getLibraryStateProvider() {
        return this.libraryStateProvider;
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.BaseViewsRefinementManager.Provider
    public BaseViewsRefinementManager getRefinementManager() {
        return this.refinementsManager;
    }

    public void startObservingNetworkEvents() {
        NetworkInfoProvider networkInfoProvider = this.networkInfoProvider;
        if (networkInfoProvider == null) {
            return;
        }
        networkInfoProvider.addNetworkChangeListener(this);
    }

    public void stopObservingNetworkEvents() {
        NetworkInfoProvider networkInfoProvider = this.networkInfoProvider;
        if (networkInfoProvider == null) {
            return;
        }
        networkInfoProvider.removeNetworkChangeListener(this);
    }

    @Override // com.amazon.music.platform.data.Listener
    public void onEvent(NetworkInfo value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (!Intrinsics.areEqual(value, this.networkLiveData.getValue())) {
            this.networkLiveData.postValue(value);
        }
        if (Intrinsics.areEqual(Boolean.valueOf(value.getIsConnected()), this.connectedLiveData.getValue())) {
            return;
        }
        this.connectedLiveData.postValue(Boolean.valueOf(value.getIsConnected()));
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.NetworkStateProvider
    public MutableLiveData<Boolean> getHasConnectionLiveData() {
        return this.connectedLiveData;
    }

    public synchronized void fetchInitPage(int pageSize) {
        fetchData(0);
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.PageViewModelProvider
    public synchronized void fetchUpdates(int pageSize, BaseViewsDiffUtilFactory diffUtilFactory, FetchType fetchType) {
        Intrinsics.checkNotNullParameter(diffUtilFactory, "diffUtilFactory");
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.PageViewModelProvider
    public synchronized void fetchMorePage(int pageIndex, int pageSize) {
        tryFetchNextPage(pageIndex);
    }

    private final void initPageStateManager() {
        this.pageStateManager.observePageData(this.gridPageModel);
        PageStateManager pageStateManager = this.pageStateManager;
        PublishSubject<Void> mOnClearedSubject = this.mOnClearedSubject;
        Intrinsics.checkNotNullExpressionValue(mOnClearedSubject, "mOnClearedSubject");
        pageStateManager.observeOnCleared(mOnClearedSubject);
    }

    private final void initRefinementsManager(boolean supportRefinements) {
        if (supportRefinements) {
            BaseViewsRefinementManager baseViewsRefinementManager = new BaseViewsRefinementManager(new ContainerRefinementDataProvider());
            this.refinementsManager = baseViewsRefinementManager;
            baseViewsRefinementManager.observePageData(this.gridPageModel);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final synchronized void setIsFetching(boolean isFetching) {
        this.isFetching.compareAndSet(!isFetching, isFetching);
    }

    public final synchronized void fetchData(int pageIndex) {
        if (pageIndex == 0) {
            tryFetchInitPage();
        } else {
            tryFetchNextPage(pageIndex);
        }
    }

    private final void addLikesToModels(PageGridViewModel pageGridViewModel) {
        List<BaseViewModel> models;
        ArrayList arrayList;
        ArrayList arrayList2;
        if (!AmazonApplication.getCapabilities().isLikesEverywhereEnabled()) {
            return;
        }
        if (pageGridViewModel == null || (models = pageGridViewModel.getModels()) == null) {
            arrayList = null;
        } else {
            arrayList = new ArrayList();
            for (Object obj : models) {
                BaseViewModel baseViewModel = (BaseViewModel) obj;
                boolean z = false;
                if (baseViewModel instanceof DescriptiveRowItemModel) {
                    DescriptiveRowItemModel descriptiveRowItemModel = (DescriptiveRowItemModel) baseViewModel;
                    if (descriptiveRowItemModel.getMetadata() instanceof TrackMetadata) {
                        String asin = ((TrackMetadata) descriptiveRowItemModel.getMetadata()).getAsin();
                        if (!(asin == null || StringsKt__StringsJVMKt.isBlank(asin))) {
                            z = true;
                        }
                    }
                }
                if (z) {
                    arrayList.add(obj);
                }
            }
        }
        if (arrayList == null) {
            arrayList2 = null;
        } else {
            arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10));
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                arrayList2.add(((TrackMetadata) ((DescriptiveRowItemModel) ((BaseViewModel) it.next())).getMetadata()).getAsin());
            }
        }
        List likesStateForTrackAsins$default = LikesServiceProxy.Companion.getLikesStateForTrackAsins$default(LikesServiceProxy.INSTANCE, null, arrayList2, 1, null);
        IntRange indices = arrayList != null ? CollectionsKt__CollectionsKt.getIndices(arrayList) : null;
        Intrinsics.checkNotNull(indices);
        int first = indices.getFirst();
        int last = indices.getLast();
        if (first > last) {
            return;
        }
        while (true) {
            int i = first + 1;
            if (likesStateForTrackAsins$default.get(first) == LikeState.LIKE) {
                ((DescriptiveRowItemModel) arrayList.get(first)).setLiked(true);
            }
            if (first == last) {
                return;
            } else {
                first = i;
            }
        }
    }

    private final boolean isCachedPageLoadEnabled(String uri) {
        return StringsKt__StringsKt.contains$default((CharSequence) uri, (CharSequence) "home", false, 2, (Object) null);
    }

    protected void tryFetchInitPage() {
        final boolean zIsCachedPageLoadEnabled = isCachedPageLoadEnabled(this.uri);
        setIsFetching(true);
        Subscription subscription = this.subscription;
        if (subscription != null) {
            subscription.unsubscribe();
        }
        this.subscription = this.pageModelUsecase.getInitPageData(zIsCachedPageLoadEnabled).subscribe(new Action1() { // from class: com.amazon.mp3.catalog.fragment.viewmodel.BrushViewModel$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                BrushViewModel.m630tryFetchInitPage$lambda4(this.f$0, zIsCachedPageLoadEnabled, (PageGridViewModel) obj);
            }
        }, new Action1() { // from class: com.amazon.mp3.catalog.fragment.viewmodel.BrushViewModel$$ExternalSyntheticLambda2
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                BrushViewModel.m631tryFetchInitPage$lambda5(this.f$0, (Throwable) obj);
            }
        }, new Action0() { // from class: com.amazon.mp3.catalog.fragment.viewmodel.BrushViewModel$$ExternalSyntheticLambda3
            @Override // rx.functions.Action0
            public final void call() {
                BrushViewModel.m632tryFetchInitPage$lambda6(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: tryFetchInitPage$lambda-4, reason: not valid java name */
    public static final void m630tryFetchInitPage$lambda4(BrushViewModel this$0, boolean z, PageGridViewModel pageGridViewModel) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Set<LatencyTrackingLeg> brushLatencyLegs = this$0.getBrushLatencyLegs();
        LatencyTrackingLeg latencyTrackingLeg = LatencyTrackingLeg.RENDERING;
        if (brushLatencyLegs.contains(latencyTrackingLeg)) {
            BrushLatencyReportingUtil.INSTANCE.trackLatency(this$0.uri, latencyTrackingLeg);
        }
        this$0.addLikesToModels(pageGridViewModel);
        BaseViewsRefinementManager baseViewsRefinementManager = this$0.refinementsManager;
        if (baseViewsRefinementManager != null) {
            baseViewsRefinementManager.updateUnfilteredData(pageGridViewModel);
        }
        this$0.gridPageModel.postValue(pageGridViewModel);
        this$0.refreshModelStateProviders();
        if (z) {
            this$0.tryFetchPageOnCacheExpired();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: tryFetchInitPage$lambda-5, reason: not valid java name */
    public static final void m631tryFetchInitPage$lambda5(BrushViewModel this$0, Throwable error) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.gridPageModel.postValue(null);
        this$0.setIsFetching(false);
        Intrinsics.checkNotNullExpressionValue(error, "error");
        this$0.onError(error, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: tryFetchInitPage$lambda-6, reason: not valid java name */
    public static final void m632tryFetchInitPage$lambda6(BrushViewModel this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setIsFetching(false);
    }

    private final void tryFetchNextPage(final int pageIndex) {
        PageGridViewModel value = this.gridPageModel.getValue();
        String nextPageToken = value == null ? null : value.getNextPageToken();
        PageGridViewModel value2 = this.gridPageModel.getValue();
        Integer numValueOf = value2 != null ? Integer.valueOf(value2.getPageIndex()) : null;
        if (nextPageToken == null || this.isFetching.get() || numValueOf == null || pageIndex <= numValueOf.intValue()) {
            return;
        }
        setIsFetching(true);
        PageGridViewModel value3 = this.gridPageModel.getValue();
        if (value3 == null) {
            return;
        }
        Subscription subscription = this.nextPageSubscription;
        if (subscription != null) {
            subscription.unsubscribe();
        }
        this.nextPageSubscription = this.pageModelUsecase.getMorePageData(pageIndex, nextPageToken, this.isArtistUpsell, value3, getRefinementLists()).subscribe(new Action1() { // from class: com.amazon.mp3.catalog.fragment.viewmodel.BrushViewModel$$ExternalSyntheticLambda4
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                BrushViewModel.m633tryFetchNextPage$lambda10$lambda7(this.f$0, (PageGridViewModel) obj);
            }
        }, new Action1() { // from class: com.amazon.mp3.catalog.fragment.viewmodel.BrushViewModel$$ExternalSyntheticLambda5
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                BrushViewModel.m634tryFetchNextPage$lambda10$lambda8(this.f$0, pageIndex, (Throwable) obj);
            }
        }, new Action0() { // from class: com.amazon.mp3.catalog.fragment.viewmodel.BrushViewModel$$ExternalSyntheticLambda6
            @Override // rx.functions.Action0
            public final void call() {
                BrushViewModel.m635tryFetchNextPage$lambda10$lambda9(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: tryFetchNextPage$lambda-10$lambda-7, reason: not valid java name */
    public static final void m633tryFetchNextPage$lambda10$lambda7(BrushViewModel this$0, PageGridViewModel pageGridViewModel) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.addLikesToModels(pageGridViewModel);
        this$0.gridPageModel.postValue(pageGridViewModel);
        this$0.refreshModelStateProviders();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: tryFetchNextPage$lambda-10$lambda-8, reason: not valid java name */
    public static final void m634tryFetchNextPage$lambda10$lambda8(BrushViewModel this$0, int i, Throwable error) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setIsFetching(false);
        Intrinsics.checkNotNullExpressionValue(error, "error");
        this$0.onError(error, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: tryFetchNextPage$lambda-10$lambda-9, reason: not valid java name */
    public static final void m635tryFetchNextPage$lambda10$lambda9(BrushViewModel this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setIsFetching(false);
    }

    public final void onError(Throwable throwable, int pageIndex) {
        Intrinsics.checkNotNullParameter(throwable, "throwable");
        Log.error(TAG, "Error fetching data for " + this.uri + ": pageIndex:" + pageIndex + ' ' + throwable);
        this.errorModel.postValue(new ErrorModel(throwable, pageIndex));
    }

    public final Observable<Boolean> isPageCacheExpired() {
        return this.pageModelUsecase.isPageDataExpired();
    }

    private final void tryFetchPageOnCacheExpired() {
        this.pageModelUsecase.isPageDataExpired().subscribe(new Action1() { // from class: com.amazon.mp3.catalog.fragment.viewmodel.BrushViewModel$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                BrushViewModel.m636tryFetchPageOnCacheExpired$lambda15(this.f$0, (Boolean) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: tryFetchPageOnCacheExpired$lambda-15, reason: not valid java name */
    public static final void m636tryFetchPageOnCacheExpired$lambda15(final BrushViewModel this$0, Boolean it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        if (it.booleanValue()) {
            this$0.setIsFetching(true);
            Subscription subscription = this$0.refreshPageSubscription;
            if (subscription != null) {
                subscription.unsubscribe();
            }
            this$0.refreshPageSubscription = PageModelUseCase.getInitPageData$default(this$0.pageModelUsecase, false, 1, null).subscribe(new Action1() { // from class: com.amazon.mp3.catalog.fragment.viewmodel.BrushViewModel$$ExternalSyntheticLambda12
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    BrushViewModel.m637tryFetchPageOnCacheExpired$lambda15$lambda12(this.f$0, (PageGridViewModel) obj);
                }
            }, new Action1() { // from class: com.amazon.mp3.catalog.fragment.viewmodel.BrushViewModel$$ExternalSyntheticLambda13
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    BrushViewModel.m638tryFetchPageOnCacheExpired$lambda15$lambda13(this.f$0, (Throwable) obj);
                }
            }, new Action0() { // from class: com.amazon.mp3.catalog.fragment.viewmodel.BrushViewModel$$ExternalSyntheticLambda14
                @Override // rx.functions.Action0
                public final void call() {
                    BrushViewModel.m639tryFetchPageOnCacheExpired$lambda15$lambda14(this.f$0);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: tryFetchPageOnCacheExpired$lambda-15$lambda-12, reason: not valid java name */
    public static final void m637tryFetchPageOnCacheExpired$lambda15$lambda12(BrushViewModel this$0, PageGridViewModel pageGridViewModel) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (pageGridViewModel == null) {
            return;
        }
        this$0.getGridPageModel().postValue(pageGridViewModel);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: tryFetchPageOnCacheExpired$lambda-15$lambda-13, reason: not valid java name */
    public static final void m638tryFetchPageOnCacheExpired$lambda15$lambda13(BrushViewModel this$0, Throwable error) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setIsFetching(false);
        Log.error(TAG, Intrinsics.stringPlus("Error fetching refreshed page on cache expired for: ", error));
        Intrinsics.checkNotNullExpressionValue(error, "error");
        this$0.onError(error, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: tryFetchPageOnCacheExpired$lambda-15$lambda-14, reason: not valid java name */
    public static final void m639tryFetchPageOnCacheExpired$lambda15$lambda14(BrushViewModel this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setIsFetching(false);
    }

    /* JADX INFO: renamed from: com.amazon.mp3.catalog.fragment.viewmodel.BrushViewModel$fetchNewData$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: BrushViewModel.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.catalog.fragment.viewmodel.BrushViewModel$fetchNewData$1", f = "BrushViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03281 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C03281(Continuation<? super C03281> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return BrushViewModel.this.new C03281(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03281) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            BrushViewModel.this.pageModelUsecase.invalidateCachedPageData().subscribe();
            BrushViewModel.this.setIsFetching(true);
            Subscription subscription = BrushViewModel.this.newDataPageSubscription;
            if (subscription != null) {
                subscription.unsubscribe();
            }
            BrushViewModel brushViewModel = BrushViewModel.this;
            Observable initPageData$default = PageModelUseCase.getInitPageData$default(brushViewModel.pageModelUsecase, false, 1, null);
            final BrushViewModel brushViewModel2 = BrushViewModel.this;
            Action1 action1 = new Action1() { // from class: com.amazon.mp3.catalog.fragment.viewmodel.BrushViewModel$fetchNewData$1$$ExternalSyntheticLambda0
                @Override // rx.functions.Action1
                public final void call(Object obj2) {
                    BrushViewModel.C03281.m649invokeSuspend$lambda1(brushViewModel2, (PageGridViewModel) obj2);
                }
            };
            final BrushViewModel brushViewModel3 = BrushViewModel.this;
            Action1<Throwable> action12 = new Action1() { // from class: com.amazon.mp3.catalog.fragment.viewmodel.BrushViewModel$fetchNewData$1$$ExternalSyntheticLambda1
                @Override // rx.functions.Action1
                public final void call(Object obj2) {
                    BrushViewModel.C03281.m650invokeSuspend$lambda2(brushViewModel3, (Throwable) obj2);
                }
            };
            final BrushViewModel brushViewModel4 = BrushViewModel.this;
            brushViewModel.newDataPageSubscription = initPageData$default.subscribe(action1, action12, new Action0() { // from class: com.amazon.mp3.catalog.fragment.viewmodel.BrushViewModel$fetchNewData$1$$ExternalSyntheticLambda2
                @Override // rx.functions.Action0
                public final void call() {
                    brushViewModel4.setIsFetching(false);
                }
            });
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: invokeSuspend$lambda-1, reason: not valid java name */
        public static final void m649invokeSuspend$lambda1(BrushViewModel brushViewModel, PageGridViewModel pageGridViewModel) {
            if (pageGridViewModel == null) {
                return;
            }
            brushViewModel.getGridPageModel().postValue(pageGridViewModel);
            brushViewModel.refreshModelStateProviders();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: invokeSuspend$lambda-2, reason: not valid java name */
        public static final void m650invokeSuspend$lambda2(BrushViewModel brushViewModel, Throwable error) {
            brushViewModel.setIsFetching(false);
            Log.error(BrushViewModel.TAG, Intrinsics.stringPlus("Error fetching new page data: ", error));
            Intrinsics.checkNotNullExpressionValue(error, "error");
            brushViewModel.onError(error, 0);
        }
    }

    public final synchronized void fetchNewData() {
        BuildersKt__Builders_commonKt.launch$default(this.defaultScope, null, null, new C03281(null), 3, null);
    }

    /* JADX INFO: renamed from: com.amazon.mp3.catalog.fragment.viewmodel.BrushViewModel$fetchFilteredPageData$1, reason: invalid class name */
    /* JADX INFO: compiled from: BrushViewModel.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.catalog.fragment.viewmodel.BrushViewModel$fetchFilteredPageData$1", f = "BrushViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $browseId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(String str, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$browseId = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return BrushViewModel.this.new AnonymousClass1(this.$browseId, continuation);
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
            BrushViewModel.this.setIsFetching(true);
            Subscription subscription = BrushViewModel.this.newDataPageSubscription;
            if (subscription != null) {
                subscription.unsubscribe();
            }
            BrushViewModel brushViewModel = BrushViewModel.this;
            Observable<PageGridViewModel> filteredPageData = brushViewModel.pageModelUsecase.getFilteredPageData(this.$browseId);
            final BrushViewModel brushViewModel2 = BrushViewModel.this;
            Action1<? super PageGridViewModel> action1 = new Action1() { // from class: com.amazon.mp3.catalog.fragment.viewmodel.BrushViewModel$fetchFilteredPageData$1$$ExternalSyntheticLambda0
                @Override // rx.functions.Action1
                public final void call(Object obj2) {
                    BrushViewModel.AnonymousClass1.m642invokeSuspend$lambda1(brushViewModel2, (PageGridViewModel) obj2);
                }
            };
            final BrushViewModel brushViewModel3 = BrushViewModel.this;
            Action1<Throwable> action12 = new Action1() { // from class: com.amazon.mp3.catalog.fragment.viewmodel.BrushViewModel$fetchFilteredPageData$1$$ExternalSyntheticLambda1
                @Override // rx.functions.Action1
                public final void call(Object obj2) {
                    BrushViewModel.AnonymousClass1.m643invokeSuspend$lambda2(brushViewModel3, (Throwable) obj2);
                }
            };
            final BrushViewModel brushViewModel4 = BrushViewModel.this;
            brushViewModel.newDataPageSubscription = filteredPageData.subscribe(action1, action12, new Action0() { // from class: com.amazon.mp3.catalog.fragment.viewmodel.BrushViewModel$fetchFilteredPageData$1$$ExternalSyntheticLambda2
                @Override // rx.functions.Action0
                public final void call() {
                    brushViewModel4.setIsFetching(false);
                }
            });
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: invokeSuspend$lambda-1, reason: not valid java name */
        public static final void m642invokeSuspend$lambda1(BrushViewModel brushViewModel, PageGridViewModel pageGridViewModel) {
            if (pageGridViewModel == null) {
                return;
            }
            brushViewModel.getGridPageModel().postValue(pageGridViewModel);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: invokeSuspend$lambda-2, reason: not valid java name */
        public static final void m643invokeSuspend$lambda2(BrushViewModel brushViewModel, Throwable error) {
            brushViewModel.setIsFetching(false);
            Log.error(BrushViewModel.TAG, Intrinsics.stringPlus("Error fetching new page data: ", error));
            Intrinsics.checkNotNullExpressionValue(error, "error");
            brushViewModel.onError(error, 0);
        }
    }

    public final void fetchFilteredPageData(String browseId) {
        Intrinsics.checkNotNullParameter(browseId, "browseId");
        BuildersKt__Builders_commonKt.launch$default(this.defaultScope, null, null, new AnonymousClass1(browseId, null), 3, null);
    }

    /* JADX INFO: renamed from: com.amazon.mp3.catalog.fragment.viewmodel.BrushViewModel$fetchFilteredPageDataBrushV3$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: BrushViewModel.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.catalog.fragment.viewmodel.BrushViewModel$fetchFilteredPageDataBrushV3$1", f = "BrushViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03271 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ FilteredPageCallback $callback;
        final /* synthetic */ Map<String, List<String>> $refinementLists;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C03271(FilteredPageCallback filteredPageCallback, Map<String, ? extends List<String>> map, Continuation<? super C03271> continuation) {
            super(2, continuation);
            this.$callback = filteredPageCallback;
            this.$refinementLists = map;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return BrushViewModel.this.new C03271(this.$callback, this.$refinementLists, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03271) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            BrushViewModel.this.setIsFetching(true);
            Subscription subscription = BrushViewModel.this.newDataPageSubscription;
            if (subscription != null) {
                subscription.unsubscribe();
            }
            this.$callback.onFilterRequestApplied();
            BrushViewModel brushViewModel = BrushViewModel.this;
            Observable<PageGridViewModel> filteredPageDataBrushV3 = brushViewModel.pageModelUsecase.getFilteredPageDataBrushV3(this.$refinementLists);
            final BrushViewModel brushViewModel2 = BrushViewModel.this;
            Action1<? super PageGridViewModel> action1 = new Action1() { // from class: com.amazon.mp3.catalog.fragment.viewmodel.BrushViewModel$fetchFilteredPageDataBrushV3$1$$ExternalSyntheticLambda0
                @Override // rx.functions.Action1
                public final void call(Object obj2) {
                    BrushViewModel.C03271.m645invokeSuspend$lambda1(brushViewModel2, (PageGridViewModel) obj2);
                }
            };
            final FilteredPageCallback filteredPageCallback = this.$callback;
            final BrushViewModel brushViewModel3 = BrushViewModel.this;
            Action1<Throwable> action12 = new Action1() { // from class: com.amazon.mp3.catalog.fragment.viewmodel.BrushViewModel$fetchFilteredPageDataBrushV3$1$$ExternalSyntheticLambda1
                @Override // rx.functions.Action1
                public final void call(Object obj2) {
                    BrushViewModel.C03271.m646invokeSuspend$lambda2(filteredPageCallback, brushViewModel3, (Throwable) obj2);
                }
            };
            final FilteredPageCallback filteredPageCallback2 = this.$callback;
            final BrushViewModel brushViewModel4 = BrushViewModel.this;
            brushViewModel.newDataPageSubscription = filteredPageDataBrushV3.subscribe(action1, action12, new Action0() { // from class: com.amazon.mp3.catalog.fragment.viewmodel.BrushViewModel$fetchFilteredPageDataBrushV3$1$$ExternalSyntheticLambda2
                @Override // rx.functions.Action0
                public final void call() {
                    BrushViewModel.C03271.m647invokeSuspend$lambda3(filteredPageCallback2, brushViewModel4);
                }
            });
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: invokeSuspend$lambda-1, reason: not valid java name */
        public static final void m645invokeSuspend$lambda1(BrushViewModel brushViewModel, PageGridViewModel pageGridViewModel) {
            if (pageGridViewModel == null) {
                return;
            }
            brushViewModel.getGridPageModel().postValue(pageGridViewModel);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: invokeSuspend$lambda-2, reason: not valid java name */
        public static final void m646invokeSuspend$lambda2(FilteredPageCallback filteredPageCallback, BrushViewModel brushViewModel, Throwable error) {
            filteredPageCallback.onFilterResponse(false);
            brushViewModel.setIsFetching(false);
            Log.error(BrushViewModel.TAG, Intrinsics.stringPlus("Error fetching new page data: ", error));
            Intrinsics.checkNotNullExpressionValue(error, "error");
            brushViewModel.onError(error, 0);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: invokeSuspend$lambda-3, reason: not valid java name */
        public static final void m647invokeSuspend$lambda3(FilteredPageCallback filteredPageCallback, BrushViewModel brushViewModel) {
            filteredPageCallback.onFilterResponse(true);
            brushViewModel.setIsFetching(false);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void fetchFilteredPageDataBrushV3$default(BrushViewModel brushViewModel, FilteredPageCallback filteredPageCallback, Map map, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: fetchFilteredPageDataBrushV3");
        }
        if ((i & 2) != 0) {
            map = null;
        }
        brushViewModel.fetchFilteredPageDataBrushV3(filteredPageCallback, map);
    }

    public final void fetchFilteredPageDataBrushV3(FilteredPageCallback callback, Map<String, ? extends List<String>> refinementLists) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        BuildersKt__Builders_commonKt.launch$default(this.defaultScope, null, null, new C03271(callback, refinementLists, null), 3, null);
    }

    private final void initLibraryStateObservation() {
        this.libraryStateProvider.setListener(this);
        LibraryStateUseCase libraryStateUseCase = new LibraryStateUseCase(this.pageStateManager);
        this.libraryStateUseCase = libraryStateUseCase;
        PublishSubject<Void> mOnClearedSubject = this.mOnClearedSubject;
        Intrinsics.checkNotNullExpressionValue(mOnClearedSubject, "mOnClearedSubject");
        libraryStateUseCase.observeOnCleared(mOnClearedSubject);
        LibraryStateUseCase libraryStateUseCase2 = this.libraryStateUseCase;
        Observable<Void> observableObserveLibraryStateInitialization = libraryStateUseCase2 == null ? null : libraryStateUseCase2.observeLibraryStateInitialization();
        Intrinsics.checkNotNull(observableObserveLibraryStateInitialization);
        this.libraryStateSubscription = observableObserveLibraryStateInitialization.observeOn(Schedulers.io()).subscribeOn(Schedulers.io()).subscribe(new Action1() { // from class: com.amazon.mp3.catalog.fragment.viewmodel.BrushViewModel$$ExternalSyntheticLambda9
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                BrushViewModel.m625initLibraryStateObservation$lambda16(this.f$0, (Void) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initLibraryStateObservation$lambda-16, reason: not valid java name */
    public static final void m625initLibraryStateObservation$lambda16(BrushViewModel this$0, Void r1) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.libraryState.requestLibraryState();
    }

    public void startObservingLibrarySyncEvents() {
        this.libraryStateUpdater.startObservingLibraryStateEvents();
        this.librarySyncStateSubscription = this.libraryStateUpdater.getLibraryStateObserver().subscribe(new Action1() { // from class: com.amazon.mp3.catalog.fragment.viewmodel.BrushViewModel$$ExternalSyntheticLambda10
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                BrushViewModel.m628startObservingLibrarySyncEvents$lambda17(this.f$0, (LibrarySyncStateProvider.LibrarySyncState) obj);
            }
        });
        this.librarySyncEventSubscription = this.libraryStateUpdater.getLibraryEventObserver().subscribe(new Action1() { // from class: com.amazon.mp3.catalog.fragment.viewmodel.BrushViewModel$$ExternalSyntheticLambda11
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                BrushViewModel.m629startObservingLibrarySyncEvents$lambda18(this.f$0, (LibrarySyncStateUpdater.LibrarySyncEvent) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: startObservingLibrarySyncEvents$lambda-17, reason: not valid java name */
    public static final void m628startObservingLibrarySyncEvents$lambda17(BrushViewModel this$0, LibrarySyncStateProvider.LibrarySyncState librarySyncState) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.librarySyncStateLiveData.postValue(librarySyncState);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: startObservingLibrarySyncEvents$lambda-18, reason: not valid java name */
    public static final void m629startObservingLibrarySyncEvents$lambda18(BrushViewModel this$0, LibrarySyncStateUpdater.LibrarySyncEvent librarySyncEvent) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int i = librarySyncEvent == null ? -1 : WhenMappings.$EnumSwitchMapping$0[librarySyncEvent.ordinal()];
        if (i == 1 || i == 2) {
            this$0.librarySyncEventLiveData.postValue(librarySyncEvent);
            this$0.libraryState.requestLibraryState();
        }
    }

    public void stopObservingLibrarySyncEvents() {
        this.libraryStateUpdater.stopObservingLibraryStateEvents();
        Subscription subscription = this.librarySyncStateSubscription;
        if (subscription != null) {
            subscription.unsubscribe();
        }
        this.librarySyncStateSubscription = null;
        Subscription subscription2 = this.librarySyncEventSubscription;
        if (subscription2 != null) {
            subscription2.unsubscribe();
        }
        this.librarySyncEventSubscription = null;
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.LibraryStateObserver
    public MutableLiveData<LibrarySyncStateProvider.LibrarySyncState> getLibrarySyncStateLiveData() {
        return this.librarySyncStateLiveData;
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.LibraryStateObserver
    public MutableLiveData<LibrarySyncStateUpdater.LibrarySyncEvent> getLibrarySyncEventLiveData() {
        return this.librarySyncEventLiveData;
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.LibraryStateObserver
    public LiveData<RepositoryEvent> getLocalRepositoryUpdateEventLiveData() {
        return getLocalRepositoryUpdateLiveData();
    }

    private final void initCommentaryStateUseCase() {
        this.commentaryStateUseCase = new CommentaryStateUseCase(this.pageStateManager);
    }

    private final void initFollowStateUseCase() {
        this.followStateUseCase = new FollowStateUseCase(this.pageStateManager);
    }

    private final void initPodcastStatesUseCase() {
        this.podcastStatesUseCase = new PodcastStatesUseCase(this.pageStateManager);
    }

    @Override // com.amazon.mp3.library.provider.LibraryStateProvider.Listener
    public void onStateChanged(SearchItem item, int ownershipState, int downloadState) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (AmazonApplication.getCapabilities().isLikesEverywhereEnabled()) {
            ContentModelLibraryStateManager.updateLikeState$default(this.libraryState, item, ownershipState != 0, null, 4, null);
        } else {
            ContentOwnershipStatus contentOwnershipStatusFromValue = ContentOwnershipStatus.fromValue(ownershipState);
            ContentModelLibraryStateManager.updateLibraryState$default(this.libraryState, item, new LibraryStateData(Boolean.valueOf(contentOwnershipStatusFromValue.isOwned()), Boolean.valueOf(contentOwnershipStatusFromValue.isInLibrary()), Integer.valueOf(downloadState)), (Function0) null, 4, (Object) null);
        }
    }

    public final void onResume() {
        if (this.gridPageModel.getValue() == null) {
            return;
        }
        tryFetchPageOnCacheExpired();
        refreshModelStateProviders();
    }

    public final void refreshModelStateProviders() {
        this.libraryState.requestLibraryState();
        this.equalizerStateManager.onRefresh();
    }

    @Override // com.amazon.mp3.catalog.fragment.viewmodel.PageViewModelProvider
    public void refresh() {
        refreshModelStateProviders();
    }

    public final void setDownloadChangedListener(OnFilterChangedListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        BaseViewsRefinementManager baseViewsRefinementManager = this.refinementsManager;
        if (baseViewsRefinementManager == null) {
            return;
        }
        baseViewsRefinementManager.setChangedListener(listener);
    }

    @Override // androidx.lifecycle.ViewModel
    protected void onCleared() {
        super.onCleared();
        this.mOnClearedSubject.onNext(null);
        Job.DefaultImpls.cancel$default((Job) this.viewModelJob, (CancellationException) null, 1, (Object) null);
        AccountManagerSingleton.get().deregisterListener(this.mAccountStateChangeListener);
        PreferenceUpdateProvider.deregisterReceiver(this.mPreferenceUpdateReceiver);
        this.libraryStateProvider.setListener(null);
        Subscription subscription = this.subscription;
        if (subscription != null) {
            subscription.unsubscribe();
        }
        this.subscription = null;
        Subscription subscription2 = this.nextPageSubscription;
        if (subscription2 != null) {
            subscription2.unsubscribe();
        }
        this.nextPageSubscription = null;
        Subscription subscription3 = this.refreshPageSubscription;
        if (subscription3 != null) {
            subscription3.unsubscribe();
        }
        this.refreshPageSubscription = null;
        Subscription subscription4 = this.newDataPageSubscription;
        if (subscription4 != null) {
            subscription4.unsubscribe();
        }
        this.newDataPageSubscription = null;
        Subscription subscription5 = this.playStateSubscription;
        if (subscription5 != null) {
            subscription5.unsubscribe();
        }
        this.playStateSubscription = null;
        Subscription subscription6 = this.libraryStateSubscription;
        if (subscription6 != null) {
            subscription6.unsubscribe();
        }
        this.libraryStateSubscription = null;
        this.equalizerStateManager.onDestroy();
        unregisterDownloadStateListener();
        stopObservingNetworkEvents();
        stopObservingLibrarySyncEvents();
    }

    private final PageModelUseCase getPageModelUseCase(Application application, String uri, String nextPageToken, boolean isArtistUpsell, Map<String, ? extends List<String>> refinementLists) {
        PageModelUseCase.Builder builder = new PageModelUseCase.Builder(application, uri);
        if (StringsKt__StringsKt.contains$default((CharSequence) uri, (CharSequence) "home", false, 2, (Object) null)) {
            return builder.withBootStrap().withRecentlyPlayed().withNextPageToken(nextPageToken).build();
        }
        if (StringsKt__StringsKt.contains$default((CharSequence) uri, (CharSequence) "find", false, 2, (Object) null)) {
            return builder.withBootStrap().withRecentSearches().withNextPageToken(nextPageToken).build();
        }
        return builder.withBootStrap().withNextPageToken(nextPageToken).withArtistUpsell(isArtistUpsell).withRefinementsLists(refinementLists).withBrushLatencyEvents(getBrushLatencyLegs()).build();
    }

    public final int getPodcastPlaybackState() {
        return Podcast.getPlayback().getPlaybackState();
    }

    public final LiveData<List<PodcastEpisode>> getPodcastJumpBackInEpisodes(FragmentActivity fragmentActivity) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "fragmentActivity");
        LiveData<List<PodcastEpisode>> podcastJumpBackInEpisodes = Podcast.create(getPodcastBootstrap(fragmentActivity)).getJumpBackIns().getPodcastJumpBackInEpisodes();
        Intrinsics.checkNotNullExpressionValue(podcastJumpBackInEpisodes, "create(bootstrap).jumpBa…podcastJumpBackInEpisodes");
        return podcastJumpBackInEpisodes;
    }

    public final LiveData<List<PodcastShow>> getPodcastFollowedShows(FragmentActivity fragmentActivity) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "fragmentActivity");
        LiveData<List<PodcastShow>> podcastFollowedShows = Podcast.create(getPodcastBootstrap(fragmentActivity)).getFollows().getPodcastFollowedShows();
        Intrinsics.checkNotNullExpressionValue(podcastFollowedShows, "create(bootstrap).follows.podcastFollowedShows");
        return podcastFollowedShows;
    }

    public final LiveData<List<PodcastEpisode>> getPodcastSavedEpisodes(FragmentActivity fragmentActivity) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "fragmentActivity");
        LiveData<List<PodcastEpisode>> podcastSavedEpisodes = Podcast.create(getPodcastBootstrap(fragmentActivity)).getSaves().getPodcastSavedEpisodes();
        Intrinsics.checkNotNullExpressionValue(podcastSavedEpisodes, "create(bootstrap).saves.podcastSavedEpisodes");
        return podcastSavedEpisodes;
    }

    public final Bootstrap getPodcastBootstrap(FragmentActivity fragmentActivity) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "fragmentActivity");
        return new Bootstrap(null, fragmentActivity.getApplicationContext(), new UserInfo(), ActionBarInfo.INSTANCE, FragmentInfo.INSTANCE, new NetworkSettings(), new DownloadSettings(fragmentActivity), new PodcastSharingProvider(), new FeatureGateProviderImpl(), false, new PodcastDynamicMessaging(fragmentActivity), new CarModeUserPresetHandlerImpl(), new AdvertisingPreferencesProviderImpl());
    }

    public final void updatePodcastJumpBackInEpisodes(List<PodcastEpisode> podcastEpisodes, PageGridViewModel pageModel) {
        Intrinsics.checkNotNullParameter(pageModel, "pageModel");
        this.pageModelUsecase.replacePodcastJumpBackInEpisodes(podcastEpisodes, pageModel);
        PodcastStatesUseCase podcastStatesUseCase = this.podcastStatesUseCase;
        if (podcastStatesUseCase == null) {
            return;
        }
        podcastStatesUseCase.podcastJumpBackInStateUpdate();
    }

    public final void updatePodcastFollowedShows(List<PodcastShow> podcastShows, PageGridViewModel pageModel) {
        Intrinsics.checkNotNullParameter(pageModel, "pageModel");
        this.pageModelUsecase.replacePodcastFollowedShows(podcastShows, pageModel);
        PodcastStatesUseCase podcastStatesUseCase = this.podcastStatesUseCase;
        if (podcastStatesUseCase == null) {
            return;
        }
        podcastStatesUseCase.podcastFollowStateUpdate();
    }

    public final void updatePodcastSavedEpisodes(List<PodcastEpisode> podcastEpisodes, PageGridViewModel pageModel) {
        Intrinsics.checkNotNullParameter(pageModel, "pageModel");
        this.pageModelUsecase.replacePodcastSavedEpisodes(podcastEpisodes, pageModel);
        PodcastStatesUseCase podcastStatesUseCase = this.podcastStatesUseCase;
        if (podcastStatesUseCase == null) {
            return;
        }
        podcastStatesUseCase.podcastSavedStateUpdate();
    }

    public final void registerDownloadStateListener(String groupAsin) {
        Intrinsics.checkNotNullParameter(groupAsin, "groupAsin");
        this.downloadStateModelProvider.registerDownloadStateModelListenerForAlbum(this.downloadStateModelProviderListener, groupAsin);
    }

    public final void registerDownloadStateListenerForTracks() {
        this.downloadStateModelProvider.registerDownloadStateModelListenerForTrack(this.downloadStateModelProviderListener);
    }

    public final void unregisterDownloadStateListener() {
        this.downloadStateModelProvider.unregisterDownloadStateModelListener();
    }

    public final void updateTracksDownloadState(List<? extends BaseViewModel> trackList) {
        this.downloadStateModelProvider.fetchCachedTracksDownloadState(trackList);
    }

    public final void setOnLibraryStateChanged(Function0<Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.onLibraryStateChanged = callback;
    }

    public final void updateModelEligibility(Eligible model, DownloadStateModel downloadStateModel) {
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(downloadStateModel, "downloadStateModel");
        this.libraryState.updateEligibilityModel(model, new LibraryStateData(null, null, Integer.valueOf(new TrackDownloadStateModelConverter().convert(downloadStateModel)), 3, null));
    }
}
