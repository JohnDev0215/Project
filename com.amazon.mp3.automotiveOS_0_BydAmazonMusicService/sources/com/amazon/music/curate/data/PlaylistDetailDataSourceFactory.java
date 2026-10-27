package com.amazon.music.curate.data;

import android.content.Context;
import android.view.View;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import com.amazon.mp3.environment.Environment;
import com.amazon.music.curate.R;
import com.amazon.music.curate.data.database.PlaylistDB;
import com.amazon.music.curate.data.database.RecsCache;
import com.amazon.music.curate.model.Playlist;
import com.amazon.music.curate.model.Track;
import com.amazon.music.curate.model.VisualModel;
import com.amazon.music.curate.provider.Configuration;
import com.amazon.music.curate.provider.FeatureFlagProvider;
import com.amazon.music.curate.provider.OverflowMenuProvider;
import com.amazon.music.curate.provider.SubscriptionProvider;
import com.amazon.music.platform.providers.DeviceInfoProvider;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Scheduler;
import rx.schedulers.Schedulers;

/* JADX INFO: compiled from: PlaylistDetailDataSourceFactory.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000¸\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 C2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001CB¤\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t\u0012\u000e\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\f\u0012!\u0010\r\u001a\u001d\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\u00110\u000e\u0012!\u0010\u0012\u001a\u001d\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u00110\u000e\u0012Q\u0010\u0015\u001aM\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0018\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u001a0\u0019¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u001b\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u001c\u0012\u0004\u0012\u00020\u00110\u0016\u0012<\u0010\u001d\u001a8\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0018\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u001a0\u0019¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020\u00110\u001e\u0012!\u0010\u001f\u001a\u001d\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\u00110\u000e\u0012!\u0010 \u001a\u001d\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\u00110\u000e\u0012'\u0010!\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u001a0\u0019¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020\u00110\u000e\u0012%\b\u0002\u0010\"\u001a\u001f\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u000e¢\u0006\u0002\u0010#J\u0014\u0010>\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030(H\u0016J\u0010\u0010?\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\u0017H\u0002J\u0018\u0010@\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030(0AJ\f\u0010B\u001a\b\u0012\u0004\u0012\u00020.0AR\u000e\u0010$\u001a\u00020%X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010&\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030(0'X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020*X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020,X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010-\u001a\u0004\u0018\u00010.X\u0082\u000e¢\u0006\u0004\n\u0002\u0010/R)\u0010\u001f\u001a\u001d\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\u00110\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R+\u0010\"\u001a\u001f\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\fX\u0082\u0004¢\u0006\u0002\n\u0000R)\u0010\u0012\u001a\u001d\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u00110\u000eX\u0082\u0004¢\u0006\u0002\n\u0000RY\u0010\u0015\u001aM\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0018\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u001a0\u0019¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u001b\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u001c\u0012\u0004\u0012\u00020\u00110\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R)\u0010\r\u001a\u001d\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\u00110\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R)\u0010 \u001a\u001d\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\u00110\u000eX\u0082\u0004¢\u0006\u0002\n\u0000RD\u0010\u001d\u001a8\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0018\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u001a0\u0019¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020\u00110\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R/\u0010!\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u001a0\u0019¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020\u00110\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u000201X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u000203X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u00104\u001a\u0004\u0018\u000105X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u00106\u001a\b\u0012\u0004\u0012\u00020.0'X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u00107\u001a\n 9*\u0004\u0018\u00010808X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010:\u001a\u00020;X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010<\u001a\u00020=X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006D"}, d2 = {"Lcom/amazon/music/curate/data/PlaylistDetailDataSourceFactory;", "Landroidx/paging/DataSource$Factory;", "", "Lcom/amazon/music/curate/model/VisualModel;", "configuration", "Lcom/amazon/music/curate/provider/Configuration;", "context", "Landroid/content/Context;", "playlistId", "", "fragmentSessionId", "onGetFragmentSessionId", "Lkotlin/Function0;", "onSeeMoreClickAction", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "", "onMoreClickAction", "Landroid/view/View;", "view", "onPlayButtonClick", "Lkotlin/Function3;", "Lcom/amazon/music/curate/model/Playlist;", Environment.PLAYLIST_PATH, "", "Lcom/amazon/music/curate/model/Track;", "tracks", "index", "onShuffleButtonClick", "Lkotlin/Function2;", "onDownloadButtonClick", "onShareButtonClick", "onStationButtonClick", "onEditButtonClick", "(Lcom/amazon/music/curate/provider/Configuration;Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "cache", "Lcom/amazon/music/curate/data/database/PlaylistDB;", "dataSourceLiveData", "Landroidx/lifecycle/MutableLiveData;", "Landroidx/paging/DataSource;", "deviceInfoProvider", "Lcom/amazon/music/platform/providers/DeviceInfoProvider;", "featureFlagProvider", "Lcom/amazon/music/curate/provider/FeatureFlagProvider;", "isEmptyPlaylist", "", "Ljava/lang/Boolean;", "overflowMenuProvider", "Lcom/amazon/music/curate/provider/OverflowMenuProvider;", "recentsService", "Lcom/amazon/music/curate/data/RecentsService;", "recsCache", "Lcom/amazon/music/curate/data/database/RecsCache;", "recsLoadedLiveData", "scheduler", "Lrx/Scheduler;", "kotlin.jvm.PlatformType", NotificationCompat.CATEGORY_SERVICE, "Lcom/amazon/music/curate/data/PlaylistService;", "subscriptionProvider", "Lcom/amazon/music/curate/provider/SubscriptionProvider;", "create", "getCountAndDurationText", "getDataSourceLiveData", "Landroidx/lifecycle/LiveData;", "getRecsLoadedLiveData", "Companion", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlaylistDetailDataSourceFactory extends DataSource.Factory<Integer, VisualModel> {
    private static final int END_OF_LIST_TOKEN = -1;
    public static final int NUM_RECS_TO_SHOW = 15;
    private final PlaylistDB cache;
    private final Configuration configuration;
    private final Context context;
    private final MutableLiveData<DataSource<Integer, VisualModel>> dataSourceLiveData;
    private final DeviceInfoProvider deviceInfoProvider;
    private final FeatureFlagProvider featureFlagProvider;
    private String fragmentSessionId;
    private Boolean isEmptyPlaylist;
    private final Function1<Playlist, Unit> onDownloadButtonClick;
    private final Function1<Playlist, Unit> onEditButtonClick;
    private final Function0<String> onGetFragmentSessionId;
    private final Function1<View, Unit> onMoreClickAction;
    private final Function3<Playlist, List<Track>, Integer, Unit> onPlayButtonClick;
    private final Function1<String, Unit> onSeeMoreClickAction;
    private final Function1<Playlist, Unit> onShareButtonClick;
    private final Function2<Playlist, List<Track>, Unit> onShuffleButtonClick;
    private final Function1<List<Track>, Unit> onStationButtonClick;
    private final OverflowMenuProvider overflowMenuProvider;
    private final String playlistId;
    private final RecentsService recentsService;
    private final RecsCache recsCache;
    private final MutableLiveData<Boolean> recsLoadedLiveData;
    private final Scheduler scheduler;
    private final PlaylistService service;
    private final SubscriptionProvider subscriptionProvider;
    private static final Logger LOG = LoggerFactory.getLogger(PlaylistDetailDataSourceFactory.class.getSimpleName());

    public /* synthetic */ PlaylistDetailDataSourceFactory(Configuration configuration, Context context, String str, String str2, Function0 function0, Function1 function1, Function1 function12, Function3 function3, Function2 function2, Function1 function13, Function1 function14, Function1 function15, Function1 function16, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(configuration, context, str, str2, function0, function1, function12, function3, function2, function13, function14, function15, (i & 4096) != 0 ? null : function16);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public PlaylistDetailDataSourceFactory(Configuration configuration, Context context, String playlistId, String str, Function0<String> onGetFragmentSessionId, Function1<? super String, Unit> onSeeMoreClickAction, Function1<? super View, Unit> onMoreClickAction, Function3<? super Playlist, ? super List<Track>, ? super Integer, Unit> onPlayButtonClick, Function2<? super Playlist, ? super List<Track>, Unit> onShuffleButtonClick, Function1<? super Playlist, Unit> onDownloadButtonClick, Function1<? super Playlist, Unit> onShareButtonClick, Function1<? super List<Track>, Unit> onStationButtonClick, Function1<? super Playlist, Unit> function1) {
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(playlistId, "playlistId");
        Intrinsics.checkNotNullParameter(onGetFragmentSessionId, "onGetFragmentSessionId");
        Intrinsics.checkNotNullParameter(onSeeMoreClickAction, "onSeeMoreClickAction");
        Intrinsics.checkNotNullParameter(onMoreClickAction, "onMoreClickAction");
        Intrinsics.checkNotNullParameter(onPlayButtonClick, "onPlayButtonClick");
        Intrinsics.checkNotNullParameter(onShuffleButtonClick, "onShuffleButtonClick");
        Intrinsics.checkNotNullParameter(onDownloadButtonClick, "onDownloadButtonClick");
        Intrinsics.checkNotNullParameter(onShareButtonClick, "onShareButtonClick");
        Intrinsics.checkNotNullParameter(onStationButtonClick, "onStationButtonClick");
        this.configuration = configuration;
        this.context = context;
        this.playlistId = playlistId;
        this.fragmentSessionId = str;
        this.onGetFragmentSessionId = onGetFragmentSessionId;
        this.onSeeMoreClickAction = onSeeMoreClickAction;
        this.onMoreClickAction = onMoreClickAction;
        this.onPlayButtonClick = onPlayButtonClick;
        this.onShuffleButtonClick = onShuffleButtonClick;
        this.onDownloadButtonClick = onDownloadButtonClick;
        this.onShareButtonClick = onShareButtonClick;
        this.onStationButtonClick = onStationButtonClick;
        this.onEditButtonClick = function1;
        this.scheduler = Schedulers.from(Executors.newSingleThreadExecutor());
        this.dataSourceLiveData = new MutableLiveData<>();
        this.recsLoadedLiveData = new MutableLiveData<>();
        PlaylistDB cache = configuration.getCache();
        Intrinsics.checkNotNull(cache);
        this.cache = cache;
        this.recsCache = configuration.getRecsCache();
        PlaylistService service = configuration.getService();
        Intrinsics.checkNotNull(service);
        this.service = service;
        FeatureFlagProvider featureFlagProvider = configuration.getFeatureFlagProvider();
        Intrinsics.checkNotNull(featureFlagProvider);
        this.featureFlagProvider = featureFlagProvider;
        DeviceInfoProvider deviceInfoProvider = configuration.getDeviceInfoProvider();
        Intrinsics.checkNotNull(deviceInfoProvider);
        this.deviceInfoProvider = deviceInfoProvider;
        SubscriptionProvider subscriptionProvider = configuration.getSubscriptionProvider();
        Intrinsics.checkNotNull(subscriptionProvider);
        this.subscriptionProvider = subscriptionProvider;
        RecentsService recentsService = configuration.getRecentsService();
        Intrinsics.checkNotNull(recentsService);
        this.recentsService = recentsService;
        OverflowMenuProvider overflowMenuProvider = configuration.getOverflowMenuProvider();
        Intrinsics.checkNotNull(overflowMenuProvider);
        this.overflowMenuProvider = overflowMenuProvider;
    }

    public final LiveData<DataSource<Integer, VisualModel>> getDataSourceLiveData() {
        return this.dataSourceLiveData;
    }

    public final LiveData<Boolean> getRecsLoadedLiveData() {
        return this.recsLoadedLiveData;
    }

    @Override // androidx.paging.DataSource.Factory
    public DataSource<Integer, VisualModel> create() {
        PlaylistDetailDataSourceFactory$create$dataSource$1 playlistDetailDataSourceFactory$create$dataSource$1 = new PlaylistDetailDataSourceFactory$create$dataSource$1(this);
        this.dataSourceLiveData.postValue(playlistDetailDataSourceFactory$create$dataSource$1);
        return playlistDetailDataSourceFactory$create$dataSource$1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getCountAndDurationText(Playlist playlist) {
        String quantityString = this.context.getResources().getQuantityString(R.plurals.playlist_song_count, playlist.getNumSongs(), Integer.valueOf(playlist.getNumSongs()));
        Intrinsics.checkNotNullExpressionValue(quantityString, "context.resources.getQua…       playlist.numSongs)");
        TimeUnit timeUnit = TimeUnit.SECONDS;
        int hours = (int) timeUnit.toHours(playlist.getDurationSeconds() == null ? 0L : r2.intValue());
        String quantityString2 = this.context.getResources().getQuantityString(R.plurals.playlist_duration_hours, hours, Integer.valueOf(hours));
        Intrinsics.checkNotNullExpressionValue(quantityString2, "context.resources.getQua…           durationHours)");
        int minutes = (int) (timeUnit.toMinutes(playlist.getDurationSeconds() != null ? r12.intValue() : 0L) - TimeUnit.HOURS.toMinutes(hours));
        String quantityString3 = this.context.getResources().getQuantityString(R.plurals.playlist_duration_minutes, minutes, Integer.valueOf(minutes));
        Intrinsics.checkNotNullExpressionValue(quantityString3, "context.resources.getQua…         durationMinutes)");
        return quantityString + " ∙ " + quantityString2 + ' ' + quantityString3;
    }
}
