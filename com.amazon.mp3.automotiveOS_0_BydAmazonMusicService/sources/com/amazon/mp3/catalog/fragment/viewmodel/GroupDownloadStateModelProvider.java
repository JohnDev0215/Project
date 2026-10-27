package com.amazon.mp3.catalog.fragment.viewmodel;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import androidx.core.app.NotificationCompat;
import com.amazon.digitalmusicplayback.DownloadPriority;
import com.amazon.digitalmusicplayback.DownloadReason;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.brush.converters.GroupDownloadStateModelConverter;
import com.amazon.mp3.brush.converters.TrackDownloadStateModelConverter;
import com.amazon.mp3.download.MusicDownloader;
import com.amazon.mp3.download.generator.CannotGenerateGroupException;
import com.amazon.mp3.download.generator.GroupGenerator;
import com.amazon.mp3.download.generator.PrimePlaylistGroupGenerator;
import com.amazon.mp3.environment.url.CirrusURLFactory;
import com.amazon.mp3.external.api.uri.QueryAlbumByAsin;
import com.amazon.mp3.library.item.DeluxeContentUtil;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.playback.state.ContentPlaybackUtils;
import com.amazon.mp3.playback.state.PageStateManager;
import com.amazon.mp3.prime.cta.PrimeCollectionTask;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.mp3.util.extensions.DownloadKt;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.downloads.DownloadState;
import com.amazon.music.downloads.Group;
import com.amazon.music.downloads.Item;
import com.amazon.music.downloads.PriorityInfo;
import com.amazon.music.downloads.RequestItem;
import com.amazon.music.downloads.RequestProvider;
import com.amazon.music.downloads.notification.DownloadListener;
import com.amazon.music.externalstorage.DeviceStorageConfiguration;
import com.amazon.music.externalstorage.StorageLocationFileManager;
import com.amazon.music.media.playback.util.Clock;
import com.amazon.music.skyfire.models.TetheredMessageKey;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import com.amazon.music.views.library.models.DownloadStateModel;
import com.amazon.music.views.library.models.HorizontalTileModel;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.PageHeaderModel;
import com.amazon.music.views.library.models.TableRowItemModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/* JADX INFO: compiled from: GroupDownloadStateModelProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u009c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\f\b\u0016\u0018\u0000 U2\u00020\u0001:\u0003VUWB\u000f\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\bS\u0010TJ\b\u0010\u0003\u001a\u00020\u0002H\u0002J]\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0010\u0010\n\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\t\u0018\u00010\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\t2\u0010\u0010\f\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\t\u0018\u00010\b2\b\u0010\r\u001a\u0004\u0018\u00010\tH\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010\u0011\u001a\u0004\u0018\u00010\tH\u0002J\u0014\u0010\u0014\u001a\u0004\u0018\u00010\u00062\b\u0010\u0013\u001a\u0004\u0018\u00010\u0006H\u0002J\u0012\u0010\u0017\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0015\u001a\u00020\tH\u0002J\u0016\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00180\u001a2\u0006\u0010\u0019\u001a\u00020\u0018H\u0002J\u0014\u0010\u001e\u001a\u0004\u0018\u00010\u001d2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0006H\u0002J\u0014\u0010\u001f\u001a\u0004\u0018\u00010\u001d2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0006H\u0002J\n\u0010!\u001a\u0004\u0018\u00010 H\u0002J\u0006\u0010\"\u001a\u00020\u0016J \u0010%\u001a\b\u0012\u0004\u0012\u00020\u00160$2\b\u0010\u0011\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010#\u001a\u00020\u0002J\"\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00160$2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010#\u001a\u00020\u0002H\u0016J\"\u0010'\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010\u001c\u001a\u0004\u0018\u00010\u00062\u0006\u0010#\u001a\u00020\u0002H\u0014J\u0010\u0010*\u001a\u00020)2\b\u0010(\u001a\u0004\u0018\u00010\tJ\u0010\u0010+\u001a\u00020)2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0006J\u0018\u0010.\u001a\u00020)2\b\u0010-\u001a\u0004\u0018\u00010,2\u0006\u0010(\u001a\u00020\tJ\u0010\u0010/\u001a\u00020)2\b\u0010-\u001a\u0004\u0018\u00010,J\u001a\u00100\u001a\u00020)2\b\u0010-\u001a\u0004\u0018\u00010,2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0006J\u0006\u00101\u001a\u00020)J\u0016\u00105\u001a\u00020)2\u000e\u00104\u001a\n\u0012\u0004\u0012\u000203\u0018\u000102J&\u00109\u001a\u00020)2\b\u0010\u0019\u001a\u0004\u0018\u0001062\b\u00107\u001a\u0004\u0018\u00010\t2\b\u00108\u001a\u0004\u0018\u00010\u001dH\u0016J&\u00109\u001a\u00020)2\b\u0010\u0019\u001a\u0004\u0018\u0001062\b\u00107\u001a\u0004\u0018\u00010\t2\b\u0010;\u001a\u0004\u0018\u00010:H\u0016J$\u0010>\u001a\u00020)2\b\u00107\u001a\u0004\u0018\u00010\t2\b\u00108\u001a\u0004\u0018\u00010\u001d2\u0006\u0010=\u001a\u00020<H\u0016J$\u0010>\u001a\u00020)2\b\u00107\u001a\u0004\u0018\u00010\t2\b\u0010;\u001a\u0004\u0018\u00010:2\u0006\u0010=\u001a\u00020<H\u0016J\u001a\u0010?\u001a\u00020\u00022\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0019\u001a\u00020\u0018R\u001a\u0010\u0005\u001a\u00020\u00048\u0004X\u0084\u0004¢\u0006\f\n\u0004\b\u0005\u0010@\u001a\u0004\bA\u0010BR\"\u0010C\u001a\u00020\u00188\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\bC\u0010D\u001a\u0004\bE\u0010F\"\u0004\bG\u0010HR\u0016\u0010J\u001a\u00020I8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bJ\u0010KR \u0010M\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00180L8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bM\u0010NR \u0010O\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020I0L8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bO\u0010NR\u0018\u0010-\u001a\u0004\u0018\u00010,8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b-\u0010PR\u0018\u0010Q\u001a\u0004\u0018\u00010\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bQ\u0010R¨\u0006X"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/viewmodel/GroupDownloadStateModelProvider;", "Lcom/amazon/music/downloads/notification/DownloadListener;", "", "isFreeTierUserPlaylistEditingEnabled", "Landroid/content/Context;", "context", "Landroid/net/Uri;", "contentUri", "", "", "projection", "selection", "selectionArgs", "sortOrder", "Landroid/database/Cursor;", "createCursor", "(Landroid/content/Context;Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;", "groupAsin", "getAlbumUri", "originalUri", "appendTrackIfNeeded", "asin", "Lcom/amazon/music/views/library/models/DownloadStateModel;", "getCachedTrackDownloadState", "Lcom/amazon/music/views/library/models/DownloadStateModel$DownloadState;", "downloadState", "Lrx/Observable;", "checkIfRedownloadNeeded", "uri", "Lcom/amazon/music/downloads/Group;", "getGroupForNonPrimePlaylist", "getGroupForPrimePlaylist", "Lcom/amazon/music/externalstorage/StorageLocationFileManager;", "getStorageLocationFileManager", "lastDownloadState", "computeGroupDownloadStateWithNonLibTracks", "Lio/reactivex/Single;", "getInitialAlbumDownloadState", "getInitialGroupDownloadState", "queryIsTrackGroupDownloaded", "albumAsin", "", "initForAlbum", "init", "Lcom/amazon/mp3/catalog/fragment/viewmodel/GroupDownloadStateModelProvider$Listener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "registerDownloadStateModelListenerForAlbum", "registerDownloadStateModelListenerForTrack", "registerDownloadStateModelListener", "unregisterDownloadStateModelListener", "", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "trackList", "fetchCachedTracksDownloadState", "Lcom/amazon/music/downloads/DownloadState;", "id", "group", "onStatusUpdate", "Lcom/amazon/music/downloads/Item;", "item", "", NotificationCompat.CATEGORY_PROGRESS, "onProgressUpdate", "isRedownloadRequired", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "groupDownloadState", "Lcom/amazon/music/views/library/models/DownloadStateModel$DownloadState;", "getGroupDownloadState", "()Lcom/amazon/music/views/library/models/DownloadStateModel$DownloadState;", "setGroupDownloadState", "(Lcom/amazon/music/views/library/models/DownloadStateModel$DownloadState;)V", "", "groupDownloadProgress", "I", "", "trackDownloadStateMap", "Ljava/util/Map;", "trackDownloadProgressMap", "Lcom/amazon/mp3/catalog/fragment/viewmodel/GroupDownloadStateModelProvider$Listener;", "groupUri", "Landroid/net/Uri;", "<init>", "(Landroid/content/Context;)V", "Companion", "BauhausDefaultListener", "Listener", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public class GroupDownloadStateModelProvider extends DownloadListener {
    private final Context context;
    private int groupDownloadProgress;
    private DownloadStateModel.DownloadState groupDownloadState;
    private Uri groupUri;
    private Listener listener;
    private final Map<String, Integer> trackDownloadProgressMap;
    private final Map<String, DownloadStateModel.DownloadState> trackDownloadStateMap;
    private static final String TAG = CirrusURLFactory.class.getSimpleName();
    private static final Uri NULL_URI = Uri.parse("null_uri");

    /* JADX INFO: compiled from: GroupDownloadStateModelProvider.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J$\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\tH&J\u001a\u0010\u0002\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\tH&J\u0012\u0010\u000b\u001a\u00020\u00032\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&¨\u0006\f"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/viewmodel/GroupDownloadStateModelProvider$Listener;", "", "onDownloadStateUpdated", "", "group", "Lcom/amazon/music/downloads/Group;", TetheredMessageKey.requestId, "", "downloadStateModel", "Lcom/amazon/music/views/library/models/DownloadStateModel;", ParserUtil.TRACK_ASIN_QUERY_PARAM_NAME, "registerId", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface Listener {
        void onDownloadStateUpdated(Group group, String requestId, DownloadStateModel downloadStateModel);

        void onDownloadStateUpdated(String trackAsin, DownloadStateModel downloadStateModel);

        void registerId(String requestId);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GroupDownloadStateModelProvider(Context context) {
        super(new LinkedHashSet());
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.groupDownloadState = DownloadStateModel.DownloadState.NO_STATE;
        this.trackDownloadStateMap = new LinkedHashMap();
        this.trackDownloadProgressMap = new LinkedHashMap();
    }

    protected final void setGroupDownloadState(DownloadStateModel.DownloadState downloadState) {
        Intrinsics.checkNotNullParameter(downloadState, "<set-?>");
        this.groupDownloadState = downloadState;
    }

    public final DownloadStateModel lastDownloadState() {
        return new DownloadStateModel(this.groupDownloadState, this.groupDownloadProgress);
    }

    public final Single<DownloadStateModel> getInitialAlbumDownloadState(final String groupAsin, final boolean computeGroupDownloadStateWithNonLibTracks) {
        Single singleCreate = Single.create(new SingleOnSubscribe() { // from class: com.amazon.mp3.catalog.fragment.viewmodel.GroupDownloadStateModelProvider$$ExternalSyntheticLambda6
            @Override // io.reactivex.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) {
                GroupDownloadStateModelProvider.m656getInitialAlbumDownloadState$lambda0(this.f$0, groupAsin, singleEmitter);
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleCreate, "create<Uri> {\n          …)\n            }\n        }");
        Single<DownloadStateModel> singleFlatMap = singleCreate.flatMap(new Function() { // from class: com.amazon.mp3.catalog.fragment.viewmodel.GroupDownloadStateModelProvider$$ExternalSyntheticLambda7
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return GroupDownloadStateModelProvider.m657getInitialAlbumDownloadState$lambda1(this.f$0, computeGroupDownloadStateWithNonLibTracks, (Uri) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleFlatMap, "albumUriObservable.flatM…)\n            }\n        }");
        return singleFlatMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getInitialAlbumDownloadState$lambda-0, reason: not valid java name */
    public static final void m656getInitialAlbumDownloadState$lambda0(GroupDownloadStateModelProvider this$0, String str, SingleEmitter it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        Uri albumUri = this$0.getAlbumUri(this$0.context, str);
        if (albumUri != null) {
            it.onSuccess(albumUri);
        } else {
            it.onSuccess(NULL_URI);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getInitialAlbumDownloadState$lambda-1, reason: not valid java name */
    public static final SingleSource m657getInitialAlbumDownloadState$lambda1(GroupDownloadStateModelProvider this$0, boolean z, Uri it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        if (Intrinsics.areEqual(it, NULL_URI)) {
            return this$0.getInitialGroupDownloadState(null, z);
        }
        return this$0.getInitialGroupDownloadState(it, z);
    }

    public Single<DownloadStateModel> getInitialGroupDownloadState(Uri originalUri, final boolean computeGroupDownloadStateWithNonLibTracks) {
        final Uri uriAppendTrackIfNeeded = appendTrackIfNeeded(originalUri);
        final MusicDownloader musicDownloader = MusicDownloader.getInstance(this.context);
        final RequestProvider requestProvider = musicDownloader.getRequestProvider();
        Intrinsics.checkNotNullExpressionValue(requestProvider, "downloader.requestProvider");
        Single<DownloadStateModel> singleCreate = Single.create(new SingleOnSubscribe() { // from class: com.amazon.mp3.catalog.fragment.viewmodel.GroupDownloadStateModelProvider$$ExternalSyntheticLambda5
            @Override // io.reactivex.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) {
                GroupDownloadStateModelProvider.m658getInitialGroupDownloadState$lambda6(requestProvider, uriAppendTrackIfNeeded, musicDownloader, this, computeGroupDownloadStateWithNonLibTracks, singleEmitter);
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleCreate, "create {\n            pro…}\n            }\n        }");
        return singleCreate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getInitialGroupDownloadState$lambda-6, reason: not valid java name */
    public static final void m658getInitialGroupDownloadState$lambda6(RequestProvider provider, final Uri uri, final MusicDownloader musicDownloader, final GroupDownloadStateModelProvider this$0, final boolean z, final SingleEmitter it) {
        Intrinsics.checkNotNullParameter(provider, "$provider");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        provider.getRequests(new RequestProvider.RequestProviderCallback() { // from class: com.amazon.mp3.catalog.fragment.viewmodel.GroupDownloadStateModelProvider$$ExternalSyntheticLambda8
            @Override // com.amazon.music.downloads.RequestProvider.RequestProviderCallback
            public final void requestsSubmitted(Set set) {
                GroupDownloadStateModelProvider.m659getInitialGroupDownloadState$lambda6$lambda5(uri, musicDownloader, this$0, z, it, set);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getInitialGroupDownloadState$lambda-6$lambda-5, reason: not valid java name */
    public static final void m659getInitialGroupDownloadState$lambda6$lambda5(Uri uri, MusicDownloader musicDownloader, GroupDownloadStateModelProvider this$0, boolean z, SingleEmitter it, Set set) {
        boolean z2;
        int percentageDownloaded;
        DownloadState downloadState;
        RequestItem requestItem;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "$it");
        DownloadState downloadState2 = DownloadState.UNKNOWN;
        Iterator it2 = set.iterator();
        do {
            z2 = false;
            if (!it2.hasNext()) {
                break;
            }
            requestItem = (RequestItem) it2.next();
            if (Intrinsics.areEqual(requestItem.getRequestId(), String.valueOf(uri))) {
                break;
            }
        } while (!Intrinsics.areEqual(Intrinsics.stringPlus(requestItem.getRequestId(), "/tracks"), String.valueOf(uri)));
        Group group = musicDownloader.getGroup(requestItem.getRequestId());
        if (group == null) {
            percentageDownloaded = 0;
        } else {
            downloadState2 = group.getDownloadState();
            Intrinsics.checkNotNullExpressionValue(downloadState2, "it.downloadState");
            percentageDownloaded = (int) group.getPercentageDownloaded();
            z2 = true;
        }
        if (!z2) {
            if (this$0.queryIsTrackGroupDownloaded(this$0.context, uri, z)) {
                downloadState = DownloadState.DOWNLOADED;
            } else {
                downloadState = DownloadState.CANCELED;
            }
            downloadState2 = downloadState;
        }
        DownloadStateModel.DownloadState downloadState3 = new GroupDownloadStateModelConverter(true).convert(Integer.valueOf(DownloadKt.toAppDownloadState(downloadState2))).getDownloadState();
        try {
            if (this$0.isRedownloadRequired(uri, downloadState3)) {
                downloadState3 = DownloadStateModel.DownloadState.REDOWNLOAD;
            }
        } catch (Exception e) {
            Log.error(TAG, Intrinsics.stringPlus("Failed to get the redownload status: ", e.getMessage()));
        }
        this$0.setGroupDownloadState(downloadState3);
        this$0.groupDownloadProgress = percentageDownloaded;
        it.onSuccess(new DownloadStateModel(downloadState3, percentageDownloaded));
    }

    protected boolean queryIsTrackGroupDownloaded(Context context, Uri uri, boolean computeGroupDownloadStateWithNonLibTracks) {
        Intrinsics.checkNotNullParameter(context, "context");
        String strStringPlus = Intrinsics.stringPlus(DeluxeContentUtil.getNonDeluxeSelection(), " AND prime_status<?");
        String[] nonDeluxeSelectionArgs = DeluxeContentUtil.getNonDeluxeSelectionArgs();
        Intrinsics.checkNotNullExpressionValue(nonDeluxeSelectionArgs, "getNonDeluxeSelectionArgs()");
        LinkedList linkedList = new LinkedList(CollectionsKt__CollectionsKt.listOf(Arrays.copyOf(nonDeluxeSelectionArgs, nonDeluxeSelectionArgs.length)));
        linkedList.add("400");
        if (isFreeTierUserPlaylistEditingEnabled()) {
            strStringPlus = Intrinsics.stringPlus(strStringPlus, " AND ownership_status<?");
            linkedList.add("200");
        } else if (!computeGroupDownloadStateWithNonLibTracks) {
            strStringPlus = Intrinsics.stringPlus(strStringPlus, " AND ownership_status<?");
            linkedList.add("300");
        }
        String str = strStringPlus;
        String[] strArr = {"download_state"};
        Object[] array = linkedList.toArray(new String[0]);
        if (array == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        }
        Cursor cursorCreateCursor = createCursor(context, uri, strArr, str, (String[]) array, null);
        int i = Integer.MIN_VALUE;
        while (cursorCreateCursor != null) {
            try {
                if (!cursorCreateCursor.moveToNext()) {
                    break;
                }
                int i2 = cursorCreateCursor.getInt(cursorCreateCursor.getColumnIndex("download_state"));
                if (i < i2) {
                    i = i2;
                }
            } finally {
                cursorCreateCursor.close();
            }
        }
        boolean z = i == 0;
        if (cursorCreateCursor != null) {
        }
        return z;
    }

    private final boolean isFreeTierUserPlaylistEditingEnabled() {
        return UserSubscriptionUtil.isNightwingOnly() && AmazonApplication.getCapabilities().isFreeTierUserPlaylistEditingEnabled();
    }

    private final Cursor createCursor(Context context, Uri contentUri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        ContentResolver contentResolver;
        if (contentUri == null || context == null || (contentResolver = context.getContentResolver()) == null) {
            return null;
        }
        return contentResolver.query(contentUri, projection, selection, selectionArgs, sortOrder);
    }

    private final Uri getAlbumUri(Context context, String groupAsin) {
        PrimeCollectionTask primeCollectionTask = new PrimeCollectionTask(context, groupAsin, false, PrimeCollectionTask.Task.NONE, new QueryAlbumByAsin(context), Clock.now(), null);
        primeCollectionTask.fetchCollection();
        return primeCollectionTask.getUri();
    }

    public final void initForAlbum(final String albumAsin) {
        Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.catalog.fragment.viewmodel.GroupDownloadStateModelProvider$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                GroupDownloadStateModelProvider.m660initForAlbum$lambda7(this.f$0, albumAsin, (Subscriber) obj);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.mp3.catalog.fragment.viewmodel.GroupDownloadStateModelProvider$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                GroupDownloadStateModelProvider.m661initForAlbum$lambda8(this.f$0, albumAsin, (Uri) obj);
            }
        }, new Action1() { // from class: com.amazon.mp3.catalog.fragment.viewmodel.GroupDownloadStateModelProvider$$ExternalSyntheticLambda2
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                GroupDownloadStateModelProvider.m662initForAlbum$lambda9(albumAsin, (Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initForAlbum$lambda-7, reason: not valid java name */
    public static final void m660initForAlbum$lambda7(GroupDownloadStateModelProvider this$0, String str, Subscriber subscriber) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        subscriber.onNext(this$0.getAlbumUri(this$0.context, str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initForAlbum$lambda-8, reason: not valid java name */
    public static final void m661initForAlbum$lambda8(GroupDownloadStateModelProvider this$0, String str, Uri uri) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.init(uri);
        Log.error(TAG, Intrinsics.stringPlus("Get a null uri based on the asin provided: ", str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initForAlbum$lambda-9, reason: not valid java name */
    public static final void m662initForAlbum$lambda9(String str, Throwable th) {
        Log.error(TAG, Intrinsics.stringPlus("Cannot get an uri based on the asin provided: ", str));
    }

    public final void init(Uri originalUri) {
        Uri uriAppendTrackIfNeeded = appendTrackIfNeeded(originalUri);
        if (uriAppendTrackIfNeeded != null) {
            String string = uriAppendTrackIfNeeded.toString();
            Intrinsics.checkNotNullExpressionValue(string, "uri.toString()");
            this.groupUri = uriAppendTrackIfNeeded;
            addRequestId(string);
            Listener listener = this.listener;
            if (listener == null) {
                return;
            }
            listener.registerId(string);
        }
    }

    private final Uri appendTrackIfNeeded(Uri originalUri) {
        Uri.Builder builderBuildUpon;
        Uri.Builder builderAppendEncodedPath;
        if (MediaProvider.Tracks.isTrackCollection(originalUri, new String[0])) {
            return originalUri;
        }
        if (originalUri == null || (builderBuildUpon = originalUri.buildUpon()) == null || (builderAppendEncodedPath = builderBuildUpon.appendEncodedPath("tracks")) == null) {
            return null;
        }
        return builderAppendEncodedPath.build();
    }

    public final void registerDownloadStateModelListenerForAlbum(Listener listener, String albumAsin) {
        Unit unit;
        Intrinsics.checkNotNullParameter(albumAsin, "albumAsin");
        MusicDownloader.getInstance(this.context).registerDownloadListener(this);
        Uri uri = this.groupUri;
        if (uri == null) {
            unit = null;
        } else {
            addRequestId(uri.toString());
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            initForAlbum(albumAsin);
        }
        this.listener = listener;
    }

    public final void registerDownloadStateModelListenerForTrack(Listener listener) {
        MusicDownloader.getInstance(this.context).registerDownloadListener(this);
        this.listener = listener;
    }

    public final void registerDownloadStateModelListener(Listener listener, Uri uri) {
        Unit unit;
        MusicDownloader.getInstance(this.context).registerDownloadListener(this);
        Uri uri2 = this.groupUri;
        if (uri2 == null) {
            unit = null;
        } else {
            addRequestId(uri2.toString());
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            init(uri);
        }
        this.listener = listener;
        if (listener == null) {
            return;
        }
        Uri uri3 = this.groupUri;
        listener.registerId(uri3 != null ? uri3.toString() : null);
    }

    public final void unregisterDownloadStateModelListener() {
        MusicDownloader.getInstance(this.context).unregisterDownloadListener(this);
        Uri uri = this.groupUri;
        if (uri != null) {
            removeRequestId(uri.toString());
        }
        this.listener = null;
    }

    private final DownloadStateModel getCachedTrackDownloadState(String asin) {
        DownloadStateModel.DownloadState downloadState = this.trackDownloadStateMap.get(asin);
        Integer num = this.trackDownloadProgressMap.get(asin);
        int iIntValue = num == null ? 0 : num.intValue();
        if (downloadState == null) {
            return null;
        }
        return new DownloadStateModel(downloadState, iIntValue);
    }

    @Override // com.amazon.music.downloads.notification.DownloadListener
    public void onStatusUpdate(DownloadState downloadState, final String id, final Group group) {
        final DownloadStateModel downloadStateModelConvert = new GroupDownloadStateModelConverter(false).convert(downloadState == null ? null : Integer.valueOf(DownloadKt.toAppDownloadState(downloadState)));
        checkIfRedownloadNeeded(downloadStateModelConvert.getDownloadState()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.mp3.catalog.fragment.viewmodel.GroupDownloadStateModelProvider$$ExternalSyntheticLambda3
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                GroupDownloadStateModelProvider.m663onStatusUpdate$lambda16(this.f$0, group, id, (DownloadStateModel.DownloadState) obj);
            }
        }, new Action1() { // from class: com.amazon.mp3.catalog.fragment.viewmodel.GroupDownloadStateModelProvider$$ExternalSyntheticLambda4
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                GroupDownloadStateModelProvider.m664onStatusUpdate$lambda17(this.f$0, group, id, downloadStateModelConvert, (Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onStatusUpdate$lambda-16, reason: not valid java name */
    public static final void m663onStatusUpdate$lambda16(GroupDownloadStateModelProvider this$0, Group group, String str, DownloadStateModel.DownloadState newDownloadState) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Listener listener = this$0.listener;
        if (listener != null) {
            Intrinsics.checkNotNullExpressionValue(newDownloadState, "newDownloadState");
            listener.onDownloadStateUpdated(group, str, new DownloadStateModel(newDownloadState, this$0.groupDownloadProgress));
        }
        Intrinsics.checkNotNullExpressionValue(newDownloadState, "newDownloadState");
        this$0.groupDownloadState = newDownloadState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onStatusUpdate$lambda-17, reason: not valid java name */
    public static final void m664onStatusUpdate$lambda17(GroupDownloadStateModelProvider this$0, Group group, String str, DownloadStateModel initialDownloadStateModel, Throwable th) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(initialDownloadStateModel, "$initialDownloadStateModel");
        Log.error(TAG, Intrinsics.stringPlus("Failed to get the redownload status: ", th.getMessage()));
        Listener listener = this$0.listener;
        if (listener != null) {
            listener.onDownloadStateUpdated(group, str, new DownloadStateModel(initialDownloadStateModel.getDownloadState(), this$0.groupDownloadProgress));
        }
        this$0.groupDownloadState = initialDownloadStateModel.getDownloadState();
    }

    @Override // com.amazon.music.downloads.notification.DownloadListener
    public void onStatusUpdate(DownloadState downloadState, String id, Item item) {
        if (item == null || downloadState == null) {
            return;
        }
        DownloadStateModel.DownloadState downloadState2 = new TrackDownloadStateModelConverter().convert(Integer.valueOf(DownloadKt.toAppDownloadState(downloadState))).getDownloadState();
        Integer num = this.trackDownloadProgressMap.get(item.getAsin());
        DownloadStateModel downloadStateModel = new DownloadStateModel(downloadState2, num == null ? 0 : num.intValue());
        Listener listener = this.listener;
        if (listener != null) {
            listener.onDownloadStateUpdated(item.getAsin(), downloadStateModel);
        }
        Map<String, DownloadStateModel.DownloadState> map = this.trackDownloadStateMap;
        String asin = item.getAsin();
        Intrinsics.checkNotNullExpressionValue(asin, "item.asin");
        map.put(asin, downloadState2);
    }

    @Override // com.amazon.music.downloads.notification.DownloadListener
    public void onProgressUpdate(String id, Group group, float progress) {
        if (group != null && Intrinsics.areEqual(group.getUri(), this.groupUri)) {
            GroupDownloadStateModelConverter groupDownloadStateModelConverter = new GroupDownloadStateModelConverter(false);
            DownloadState downloadState = group.getDownloadState();
            Intrinsics.checkNotNullExpressionValue(downloadState, "it.downloadState");
            DownloadStateModel downloadStateModelConvert = groupDownloadStateModelConverter.convert(Integer.valueOf(DownloadKt.toAppDownloadState(downloadState)));
            Listener listener = this.listener;
            if (listener != null) {
                listener.onDownloadStateUpdated(group, id, new DownloadStateModel(downloadStateModelConvert.getDownloadState(), (int) progress));
            }
            this.groupDownloadProgress = (int) progress;
        }
    }

    @Override // com.amazon.music.downloads.notification.DownloadListener
    public void onProgressUpdate(String id, Item item, float progress) {
        if (item != null) {
            DownloadStateModel.DownloadState downloadState = this.trackDownloadStateMap.get(item.getAsin());
            if (downloadState == null) {
                downloadState = DownloadStateModel.DownloadState.NO_STATE;
            }
            int i = (int) progress;
            DownloadStateModel downloadStateModel = new DownloadStateModel(downloadState, i);
            Listener listener = this.listener;
            if (listener != null) {
                listener.onDownloadStateUpdated(item.getAsin(), downloadStateModel);
            }
            Map<String, Integer> map = this.trackDownloadProgressMap;
            String asin = item.getAsin();
            Intrinsics.checkNotNullExpressionValue(asin, "item.asin");
            map.put(asin, Integer.valueOf(i));
        }
    }

    private final Observable<DownloadStateModel.DownloadState> checkIfRedownloadNeeded(final DownloadStateModel.DownloadState downloadState) {
        Observable<DownloadStateModel.DownloadState> observableCreate = Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.catalog.fragment.viewmodel.GroupDownloadStateModelProvider$$ExternalSyntheticLambda9
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                GroupDownloadStateModelProvider.m655checkIfRedownloadNeeded$lambda19(this.f$0, downloadState, (Subscriber) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableCreate, "create {\n            val…t.onCompleted()\n        }");
        return observableCreate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: checkIfRedownloadNeeded$lambda-19, reason: not valid java name */
    public static final void m655checkIfRedownloadNeeded$lambda19(GroupDownloadStateModelProvider this$0, DownloadStateModel.DownloadState downloadState, Subscriber subscriber) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(downloadState, "$downloadState");
        if (isRedownloadRequired$default(this$0, null, downloadState, 1, null)) {
            downloadState = DownloadStateModel.DownloadState.REDOWNLOAD;
        }
        subscriber.onNext(downloadState);
        subscriber.onCompleted();
    }

    public static /* synthetic */ boolean isRedownloadRequired$default(GroupDownloadStateModelProvider groupDownloadStateModelProvider, Uri uri, DownloadStateModel.DownloadState downloadState, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: isRedownloadRequired");
        }
        if ((i & 1) != 0) {
            uri = groupDownloadStateModelProvider.groupUri;
        }
        return groupDownloadStateModelProvider.isRedownloadRequired(uri, downloadState);
    }

    public final boolean isRedownloadRequired(Uri uri, DownloadStateModel.DownloadState downloadState) {
        Intrinsics.checkNotNullParameter(downloadState, "downloadState");
        if (downloadState != DownloadStateModel.DownloadState.DOWNLOADED || !UserSubscriptionUtil.getUserSubscription().isKatana() || uri == null) {
            return false;
        }
        try {
            Group groupForPrimePlaylist = MediaProvider.PrimePlaylists.isPrimePlaylist(uri) ? getGroupForPrimePlaylist(uri) : getGroupForNonPrimePlaylist(uri);
            if (groupForPrimePlaylist != null) {
                return groupForPrimePlaylist.size() > 0;
            }
            return false;
        } catch (CannotGenerateGroupException unused) {
            return false;
        }
    }

    private final Group getGroupForNonPrimePlaylist(Uri uri) {
        if (uri == null) {
            return null;
        }
        return new GroupGenerator(this.context, uri, null, null, null, getStorageLocationFileManager()).generate(false, new PriorityInfo(DownloadPriority.USERINITIATED.name(), DownloadReason.USERINITIATED.name()), null);
    }

    private final Group getGroupForPrimePlaylist(Uri uri) {
        if (uri == null) {
            return null;
        }
        return new PrimePlaylistGroupGenerator(uri, this.context, null, null, null, getStorageLocationFileManager()).generate(new PriorityInfo(DownloadPriority.USERINITIATED.name(), DownloadReason.USERINITIATED.name()), -1);
    }

    private final StorageLocationFileManager getStorageLocationFileManager() {
        return StorageLocationFileManager.create(this.context, new DeviceStorageConfiguration(this.context, Environment.DIRECTORY_MUSIC), Environment.DIRECTORY_MUSIC);
    }

    /* JADX INFO: compiled from: GroupDownloadStateModelProvider.kt */
    @Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0002J\n\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0002J\n\u0010\r\u001a\u0004\u0018\u00010\u000eH&J\b\u0010\u000f\u001a\u00020\u0010H&J$\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u001a\u0010\u0011\u001a\u00020\u00122\b\u0010\u0018\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u0012\u0010\u0019\u001a\u00020\u00122\b\u0010\u0015\u001a\u0004\u0018\u00010\u0006H\u0016J\"\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\"\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0016\u001a\u00020\u0017H\u0002R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/viewmodel/GroupDownloadStateModelProvider$BauhausDefaultListener;", "Lcom/amazon/mp3/catalog/fragment/viewmodel/GroupDownloadStateModelProvider$Listener;", "shouldShowTrackDownloadStatus", "", "(Z)V", "registeredRequestId", "", "convertToTrackAppDownloadStates", "", "downloadState", "Lcom/amazon/music/views/library/models/DownloadStateModel$DownloadState;", "getPageHeaderModel", "Lcom/amazon/music/views/library/models/PageHeaderModel;", "getPageModel", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "getPageStateManager", "Lcom/amazon/mp3/playback/state/PageStateManager;", "onDownloadStateUpdated", "", "group", "Lcom/amazon/music/downloads/Group;", TetheredMessageKey.requestId, "downloadStateModel", "Lcom/amazon/music/views/library/models/DownloadStateModel;", ParserUtil.TRACK_ASIN_QUERY_PARAM_NAME, "registerId", "updateDownloadState", "model", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static abstract class BauhausDefaultListener implements Listener {
        private String registeredRequestId;
        private final boolean shouldShowTrackDownloadStatus;

        /* JADX INFO: compiled from: GroupDownloadStateModelProvider.kt */
        @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[DownloadStateModel.DownloadState.values().length];
                iArr[DownloadStateModel.DownloadState.QUEUING.ordinal()] = 1;
                iArr[DownloadStateModel.DownloadState.DOWNLOADING.ordinal()] = 2;
                iArr[DownloadStateModel.DownloadState.DOWNLOADED.ordinal()] = 3;
                iArr[DownloadStateModel.DownloadState.ERROR_DOWNLOADING.ordinal()] = 4;
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public BauhausDefaultListener() {
            this(false, 1, null);
        }

        /* JADX INFO: renamed from: getPageModel */
        public abstract PageGridViewModel get$pageGridViewModel();

        public abstract PageStateManager getPageStateManager();

        public BauhausDefaultListener(boolean z) {
            this.shouldShowTrackDownloadStatus = z;
        }

        public /* synthetic */ BauhausDefaultListener(boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? true : z);
        }

        @Override // com.amazon.mp3.catalog.fragment.viewmodel.GroupDownloadStateModelProvider.Listener
        public void registerId(String requestId) {
            this.registeredRequestId = requestId;
        }

        @Override // com.amazon.mp3.catalog.fragment.viewmodel.GroupDownloadStateModelProvider.Listener
        public void onDownloadStateUpdated(Group group, String requestId, DownloadStateModel downloadStateModel) {
            ContentMetadata metadata;
            Uri uri;
            Intrinsics.checkNotNullParameter(downloadStateModel, "downloadStateModel");
            PageHeaderModel pageHeaderModel = getPageHeaderModel();
            if (this.registeredRequestId == null) {
                String string = null;
                if (pageHeaderModel != null && (metadata = pageHeaderModel.getMetadata()) != null && (uri = metadata.getUri()) != null) {
                    string = uri.toString();
                }
                this.registeredRequestId = string;
            }
            if (Intrinsics.areEqual(requestId, this.registeredRequestId) || Intrinsics.areEqual(Intrinsics.stringPlus(this.registeredRequestId, "/tracks"), requestId)) {
                PageHeaderModel pageHeaderModel2 = getPageHeaderModel();
                if (pageHeaderModel2 != null) {
                    pageHeaderModel2.updateDownloadState(downloadStateModel);
                }
                PageStateManager.updateModelState$default(getPageStateManager(), group, downloadStateModel, new GroupDownloadStateModelProvider$BauhausDefaultListener$onDownloadStateUpdated$1(this), null, 8, null);
            }
        }

        @Override // com.amazon.mp3.catalog.fragment.viewmodel.GroupDownloadStateModelProvider.Listener
        public void onDownloadStateUpdated(String trackAsin, DownloadStateModel downloadStateModel) {
            Intrinsics.checkNotNullParameter(downloadStateModel, "downloadStateModel");
            PageStateManager.updateModelState$default(getPageStateManager(), trackAsin, downloadStateModel, new GroupDownloadStateModelProvider$BauhausDefaultListener$onDownloadStateUpdated$2(this), null, 8, null);
        }

        private final PageHeaderModel getPageHeaderModel() {
            List<BaseViewModel> models;
            PageGridViewModel pageGridViewModel = get$pageGridViewModel();
            if (pageGridViewModel == null || (models = pageGridViewModel.getModels()) == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            for (Object obj : models) {
                if (obj instanceof PageHeaderModel) {
                    arrayList.add(obj);
                }
            }
            if (!arrayList.isEmpty()) {
                return (PageHeaderModel) arrayList.get(0);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean updateDownloadState(BaseViewModel model, String trackAsin, DownloadStateModel downloadStateModel) {
            HorizontalTileModel horizontalTileModel = model instanceof HorizontalTileModel ? (HorizontalTileModel) model : null;
            if (horizontalTileModel != null && trackAsin != null) {
                ContentMetadata metadata = horizontalTileModel.getMetadata();
                TrackMetadata trackMetadata = metadata instanceof TrackMetadata ? (TrackMetadata) metadata : null;
                if (trackMetadata != null && Intrinsics.areEqual(trackAsin, trackMetadata.getAsin())) {
                    trackMetadata.setDownloadState(Integer.valueOf(convertToTrackAppDownloadStates(downloadStateModel.getDownloadState())));
                    if (this.shouldShowTrackDownloadStatus) {
                        horizontalTileModel.updateDownloadState(downloadStateModel);
                    } else {
                        horizontalTileModel.setDownloadStateModel(null);
                    }
                }
            }
            TableRowItemModel tableRowItemModel = model instanceof TableRowItemModel ? (TableRowItemModel) model : null;
            if (tableRowItemModel == null || trackAsin == null) {
                return false;
            }
            ContentMetadata metadata2 = tableRowItemModel.getMetadata();
            TrackMetadata trackMetadata2 = metadata2 instanceof TrackMetadata ? (TrackMetadata) metadata2 : null;
            if (trackMetadata2 == null || !Intrinsics.areEqual(trackAsin, trackMetadata2.getAsin())) {
                return false;
            }
            trackMetadata2.setDownloadState(Integer.valueOf(convertToTrackAppDownloadStates(downloadStateModel.getDownloadState())));
            if (this.shouldShowTrackDownloadStatus) {
                tableRowItemModel.updateDownloadState(downloadStateModel);
                return true;
            }
            tableRowItemModel.setDownloadStateModel(null);
            return true;
        }

        private final int convertToTrackAppDownloadStates(DownloadStateModel.DownloadState downloadState) {
            int i = downloadState == null ? -1 : WhenMappings.$EnumSwitchMapping$0[downloadState.ordinal()];
            if (i == 1) {
                return 1;
            }
            if (i == 2) {
                return 4;
            }
            if (i != 3) {
                return i != 4 ? 5 : 2;
            }
            return 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean updateDownloadState(BaseViewModel model, Group group, DownloadStateModel downloadStateModel) {
            Uri uri;
            TableRowItemModel tableRowItemModel = model instanceof TableRowItemModel ? (TableRowItemModel) model : null;
            if (tableRowItemModel == null || group == null || (uri = group.getUri()) == null || !Intrinsics.areEqual(uri, ContentPlaybackUtils.INSTANCE.appendTracksToUriForDownloads(tableRowItemModel.getMetadata().getUri()))) {
                return false;
            }
            tableRowItemModel.updateDownloadState(downloadStateModel);
            return true;
        }
    }

    public final void fetchCachedTracksDownloadState(List<? extends BaseViewModel> trackList) {
        DownloadStateModel cachedTrackDownloadState;
        Listener listener;
        if (trackList == null) {
            return;
        }
        ArrayList<String> arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(trackList, 10));
        for (BaseViewModel baseViewModel : trackList) {
            String asin = null;
            TableRowItemModel tableRowItemModel = baseViewModel instanceof TableRowItemModel ? (TableRowItemModel) baseViewModel : null;
            ContentMetadata metadata = tableRowItemModel == null ? null : tableRowItemModel.getMetadata();
            TrackMetadata trackMetadata = metadata instanceof TrackMetadata ? (TrackMetadata) metadata : null;
            if (trackMetadata != null) {
                asin = trackMetadata.getAsin();
            }
            arrayList.add(asin);
        }
        for (String str : arrayList) {
            if (str != null && (cachedTrackDownloadState = getCachedTrackDownloadState(str)) != null && (listener = this.listener) != null) {
                listener.onDownloadStateUpdated(str, cachedTrackDownloadState);
            }
        }
    }
}
