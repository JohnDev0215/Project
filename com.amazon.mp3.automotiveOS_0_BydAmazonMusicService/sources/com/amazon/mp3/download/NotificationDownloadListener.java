package com.amazon.mp3.download;

import android.widget.RemoteViews;
import com.amazon.mp3.download.service.NotificationView;
import com.amazon.mp3.util.Log;
import com.amazon.music.downloads.DownloadState;
import com.amazon.music.downloads.ErrorReason;
import com.amazon.music.downloads.Group;
import com.amazon.music.downloads.Item;
import com.amazon.music.downloads.RequestItem;
import com.amazon.music.downloads.RequestProvider;
import com.amazon.music.downloads.notification.DownloadListener;
import com.amazon.music.downloads.notification.NotificationInfo;
import com.amazon.music.skyfire.models.TetheredMessageKey;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/* JADX INFO: compiled from: NotificationDownloadListener.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001f\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\u0018\u0000 92\u00020\u0001:\u00019B%\b\u0016\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\b\u0010\u001b\u001a\u00020\u001cH\u0002J(\u0010\u001d\u001a\u00020\u001c2\u0012\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020!0 0\u001f2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020#0\u001fJ\u001a\u0010$\u001a\u0014\u0012\u0004\u0012\u00020!\u0012\n\u0012\b\u0012\u0004\u0012\u00020!0 0%H\u0002J\b\u0010&\u001a\u00020\u001cH\u0002J\u0018\u0010\"\u001a\u00020\u001c2\u0006\u0010'\u001a\u00020\u00042\u0006\u0010(\u001a\u00020)H\u0002J \u0010*\u001a\u00020\u001c2\u0006\u0010'\u001a\u00020\u00042\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.H\u0016J \u0010*\u001a\u00020\u001c2\u0006\u0010'\u001a\u00020\u00042\u0006\u0010/\u001a\u0002002\u0006\u0010-\u001a\u00020.H\u0016J(\u0010*\u001a\u00020\u001c2\u0006\u0010'\u001a\u00020\u00042\u0006\u00101\u001a\u0002022\u0006\u0010-\u001a\u00020.2\u0006\u00103\u001a\u000204H\u0002J \u00105\u001a\u00020\u001c2\u0006\u00106\u001a\u00020!2\u0006\u0010'\u001a\u00020\u00042\u0006\u0010+\u001a\u00020,H\u0016J \u00105\u001a\u00020\u001c2\u0006\u00106\u001a\u00020!2\u0006\u0010'\u001a\u00020\u00042\u0006\u0010+\u001a\u000200H\u0016J \u00105\u001a\u00020\u001c2\u0006\u00106\u001a\u00020!2\u0006\u0010'\u001a\u00020\u00042\u0006\u00101\u001a\u000202H\u0002J\b\u00107\u001a\u00020\u001cH\u0002J\u0006\u00108\u001a\u00020\u001cR\u0016\u0010\n\u001a\n \u000b*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u00020\u0006X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0012X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006:"}, d2 = {"Lcom/amazon/mp3/download/NotificationDownloadListener;", "Lcom/amazon/music/downloads/notification/DownloadListener;", TetheredMessageKey.requestId, "", "", "downloader", "Lcom/amazon/mp3/download/MusicDownloader;", "view", "Lcom/amazon/mp3/download/service/NotificationView;", "(Ljava/util/Collection;Lcom/amazon/mp3/download/MusicDownloader;Lcom/amazon/mp3/download/service/NotificationView;)V", "TAG", "kotlin.jvm.PlatformType", "mDownloader", "getMDownloader", "()Lcom/amazon/mp3/download/MusicDownloader;", "setMDownloader", "(Lcom/amazon/mp3/download/MusicDownloader;)V", "mNotificationExecutor", "Ljava/util/concurrent/ExecutorService;", "getMNotificationExecutor", "()Ljava/util/concurrent/ExecutorService;", "setMNotificationExecutor", "(Ljava/util/concurrent/ExecutorService;)V", "mNotificationView", "mRequestCallback", "Lcom/amazon/music/downloads/RequestProvider$RequestProviderCallback;", "mRequestIds", "dismissNotificationIfAllRequestsServed", "", "fetchDownloadingOrQueuedRequests", "onSuccess", "Lrx/functions/Action1;", "", "Lcom/amazon/music/downloads/DownloadState;", "onError", "", "getRemainingDownloadState", "Lrx/Observable$Transformer;", "onCompleted", "id", "errorReason", "Lcom/amazon/music/downloads/ErrorReason;", "onProgressUpdate", "group", "Lcom/amazon/music/downloads/Group;", "percentageDownloaded", "", "item", "Lcom/amazon/music/downloads/Item;", "notificationInfo", "Lcom/amazon/music/downloads/notification/NotificationInfo;", "isPaused", "", "onStatusUpdate", "state", "refreshDownloadList", "shutDown", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class NotificationDownloadListener extends DownloadListener {
    private final String TAG;
    public MusicDownloader mDownloader;
    public ExecutorService mNotificationExecutor;
    private final NotificationView mNotificationView;
    private final RequestProvider.RequestProviderCallback mRequestCallback;
    private final Collection<String> mRequestIds;

    /* JADX INFO: compiled from: NotificationDownloadListener.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[DownloadState.values().length];
            iArr[DownloadState.QUEUED.ordinal()] = 1;
            iArr[DownloadState.PAUSED_USER.ordinal()] = 2;
            iArr[DownloadState.PAUSED_POLICY.ordinal()] = 3;
            iArr[DownloadState.PAUSED_PRIORITY.ordinal()] = 4;
            iArr[DownloadState.CANCELED.ordinal()] = 5;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private final Observable.Transformer<DownloadState, List<DownloadState>> getRemainingDownloadState() {
        return new Observable.Transformer() { // from class: com.amazon.mp3.download.NotificationDownloadListener$$ExternalSyntheticLambda10
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return NotificationDownloadListener.m947getRemainingDownloadState$lambda13((Observable) obj);
            }
        };
    }

    public final MusicDownloader getMDownloader() {
        MusicDownloader musicDownloader = this.mDownloader;
        if (musicDownloader != null) {
            return musicDownloader;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mDownloader");
        return null;
    }

    public final void setMDownloader(MusicDownloader musicDownloader) {
        Intrinsics.checkNotNullParameter(musicDownloader, "<set-?>");
        this.mDownloader = musicDownloader;
    }

    public final ExecutorService getMNotificationExecutor() {
        ExecutorService executorService = this.mNotificationExecutor;
        if (executorService != null) {
            return executorService;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mNotificationExecutor");
        return null;
    }

    public final void setMNotificationExecutor(ExecutorService executorService) {
        Intrinsics.checkNotNullParameter(executorService, "<set-?>");
        this.mNotificationExecutor = executorService;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NotificationDownloadListener(Collection<String> requestId, MusicDownloader downloader, NotificationView view) {
        super(requestId);
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(downloader, "downloader");
        Intrinsics.checkNotNullParameter(view, "view");
        this.TAG = NotificationDownloadListener.class.getSimpleName();
        this.mRequestCallback = new RequestProvider.RequestProviderCallback() { // from class: com.amazon.mp3.download.NotificationDownloadListener$$ExternalSyntheticLambda1
            @Override // com.amazon.music.downloads.RequestProvider.RequestProviderCallback
            public final void requestsSubmitted(Set set) {
                NotificationDownloadListener.m949mRequestCallback$lambda17(this.f$0, set);
            }
        };
        setMDownloader(downloader);
        this.mRequestIds = requestId;
        this.mNotificationView = view;
        setMNotificationExecutor(new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), new ThreadPoolExecutor.DiscardPolicy()));
    }

    public final void shutDown() {
        ExecutorService mNotificationExecutor = getMNotificationExecutor();
        if (mNotificationExecutor.isShutdown()) {
            return;
        }
        mNotificationExecutor.shutdown();
    }

    @Override // com.amazon.music.downloads.notification.DownloadListener
    public void onStatusUpdate(final DownloadState state, final String id, final Group group) {
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(group, "group");
        ExecutorService mNotificationExecutor = getMNotificationExecutor();
        if (mNotificationExecutor.isShutdown() || mNotificationExecutor.isTerminated()) {
            return;
        }
        mNotificationExecutor.execute(new Runnable() { // from class: com.amazon.mp3.download.NotificationDownloadListener$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                NotificationDownloadListener.m955onStatusUpdate$lambda2$lambda1(state, this, id, group);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onStatusUpdate$lambda-2$lambda-1, reason: not valid java name */
    public static final void m955onStatusUpdate$lambda2$lambda1(DownloadState state, NotificationDownloadListener this$0, String id, Group group) {
        Intrinsics.checkNotNullParameter(state, "$state");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(id, "$id");
        Intrinsics.checkNotNullParameter(group, "$group");
        if (state == DownloadState.ERROR) {
            ErrorReason errorReason = group.getErrorReason();
            Intrinsics.checkNotNullExpressionValue(errorReason, "group.errorReason");
            this$0.onError(id, errorReason);
        } else {
            NotificationInfo notificationInfo = group.getNotificationInfo();
            Intrinsics.checkNotNullExpressionValue(notificationInfo, "group.notificationInfo");
            this$0.onStatusUpdate(state, id, notificationInfo);
        }
    }

    @Override // com.amazon.music.downloads.notification.DownloadListener
    public void onStatusUpdate(final DownloadState state, final String id, final Item group) {
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(group, "group");
        ExecutorService mNotificationExecutor = getMNotificationExecutor();
        if (mNotificationExecutor.isShutdown() || mNotificationExecutor.isTerminated()) {
            return;
        }
        mNotificationExecutor.execute(new Runnable() { // from class: com.amazon.mp3.download.NotificationDownloadListener$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                NotificationDownloadListener.m956onStatusUpdate$lambda4$lambda3(state, this, id, group);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onStatusUpdate$lambda-4$lambda-3, reason: not valid java name */
    public static final void m956onStatusUpdate$lambda4$lambda3(DownloadState state, NotificationDownloadListener this$0, String id, Item group) {
        Intrinsics.checkNotNullParameter(state, "$state");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(id, "$id");
        Intrinsics.checkNotNullParameter(group, "$group");
        if (state == DownloadState.ERROR) {
            ErrorReason errorReason = group.getErrorReason();
            Intrinsics.checkNotNullExpressionValue(errorReason, "group.errorReason");
            this$0.onError(id, errorReason);
        } else {
            NotificationInfo notificationInfo = group.getNotificationInfo();
            Intrinsics.checkNotNullExpressionValue(notificationInfo, "group.notificationInfo");
            this$0.onStatusUpdate(state, id, notificationInfo);
        }
    }

    @Override // com.amazon.music.downloads.notification.DownloadListener
    public void onProgressUpdate(final String id, final Group group, final float percentageDownloaded) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(group, "group");
        ExecutorService mNotificationExecutor = getMNotificationExecutor();
        if (mNotificationExecutor.isShutdown() || mNotificationExecutor.isTerminated()) {
            return;
        }
        mNotificationExecutor.execute(new Runnable() { // from class: com.amazon.mp3.download.NotificationDownloadListener$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                NotificationDownloadListener.m953onProgressUpdate$lambda6$lambda5(this.f$0, id, group, percentageDownloaded);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onProgressUpdate$lambda-6$lambda-5, reason: not valid java name */
    public static final void m953onProgressUpdate$lambda6$lambda5(NotificationDownloadListener this$0, String id, Group group, float f) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(id, "$id");
        Intrinsics.checkNotNullParameter(group, "$group");
        NotificationInfo notificationInfo = group.getNotificationInfo();
        Intrinsics.checkNotNullExpressionValue(notificationInfo, "group.notificationInfo");
        this$0.onProgressUpdate(id, notificationInfo, f, false);
    }

    @Override // com.amazon.music.downloads.notification.DownloadListener
    public void onProgressUpdate(final String id, final Item item, final float percentageDownloaded) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(item, "item");
        ExecutorService mNotificationExecutor = getMNotificationExecutor();
        if (mNotificationExecutor.isShutdown() || mNotificationExecutor.isTerminated()) {
            return;
        }
        mNotificationExecutor.execute(new Runnable() { // from class: com.amazon.mp3.download.NotificationDownloadListener$$ExternalSyntheticLambda11
            @Override // java.lang.Runnable
            public final void run() {
                NotificationDownloadListener.m954onProgressUpdate$lambda8$lambda7(this.f$0, id, item, percentageDownloaded);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onProgressUpdate$lambda-8$lambda-7, reason: not valid java name */
    public static final void m954onProgressUpdate$lambda8$lambda7(NotificationDownloadListener this$0, String id, Item item, float f) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(id, "$id");
        Intrinsics.checkNotNullParameter(item, "$item");
        NotificationInfo notificationInfo = item.getNotificationInfo();
        Intrinsics.checkNotNullExpressionValue(notificationInfo, "item.notificationInfo");
        this$0.onProgressUpdate(id, notificationInfo, f, false);
    }

    private final void onStatusUpdate(DownloadState state, String id, NotificationInfo notificationInfo) {
        if (state == DownloadState.DOWNLOADED) {
            this.mRequestIds.remove(id);
            dismissNotificationIfAllRequestsServed();
            refreshDownloadList();
            return;
        }
        if (this.mNotificationView.isNotificationEnabled()) {
            int i = WhenMappings.$EnumSwitchMapping$0[state.ordinal()];
            if (i == 2 || i == 3) {
                if (Intrinsics.areEqual(id, this.mNotificationView.getCurrentNotification().getRequestId())) {
                    onProgressUpdate(id, notificationInfo, this.mNotificationView.getCurrentNotification().getLastPercentageDownloaded(), true);
                }
            } else if (i == 4) {
                Log.info(this.TAG, "Background request id: %s paused due to foreground request", id);
            } else {
                if (i != 5) {
                    return;
                }
                this.mRequestIds.remove(id);
                dismissNotificationIfAllRequestsServed();
            }
        }
    }

    private final void refreshDownloadList() {
        RequestProvider requestProvider = getMDownloader().getRequestProvider();
        Intrinsics.checkNotNullExpressionValue(requestProvider, "mDownloader.requestProvider");
        requestProvider.getRequests(this.mRequestCallback);
    }

    private final void onError(String id, ErrorReason errorReason) {
        RemoteViews remoteViewsCreateErrorNotificationView;
        if (this.mNotificationView.isNotificationEnabled() && this.mRequestIds.contains(id) && (remoteViewsCreateErrorNotificationView = this.mNotificationView.createErrorNotificationView(id, errorReason)) != null) {
            NotificationView notificationView = this.mNotificationView;
            notificationView.showNotification(remoteViewsCreateErrorNotificationView, notificationView.getErrorResourceId(), true);
        }
    }

    private final void onCompleted() {
        this.mNotificationView.removeNotificationRunnable();
        RemoteViews remoteViewsCreateCompleteNotificationView = this.mNotificationView.createCompleteNotificationView();
        NotificationView notificationView = this.mNotificationView;
        notificationView.showCompletedNotification(remoteViewsCreateCompleteNotificationView, notificationView.getDownloadCompleteResourceId());
    }

    private final void onProgressUpdate(String id, NotificationInfo notificationInfo, float percentageDownloaded, boolean isPaused) {
        if (this.mRequestIds.contains(id) && this.mNotificationView.isNotificationEnabled()) {
            this.mNotificationView.removeNotificationRunnable();
            RemoteViews remoteViewsCreateProgressNotificationView = this.mNotificationView.createProgressNotificationView(id, notificationInfo, percentageDownloaded, isPaused);
            if (remoteViewsCreateProgressNotificationView == null) {
                return;
            }
            int downloadingResourceId = this.mNotificationView.getDownloadingResourceId();
            if (((int) percentageDownloaded) == 100) {
                downloadingResourceId = this.mNotificationView.getDownloadCompleteResourceId();
                dismissNotificationIfAllRequestsServed();
            }
            this.mNotificationView.showNotification(remoteViewsCreateProgressNotificationView, downloadingResourceId, isPaused);
        }
    }

    private final void dismissNotificationIfAllRequestsServed() {
        fetchDownloadingOrQueuedRequests(new Action1() { // from class: com.amazon.mp3.download.NotificationDownloadListener$$ExternalSyntheticLambda4
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                NotificationDownloadListener.m945dismissNotificationIfAllRequestsServed$lambda9(this.f$0, (List) obj);
            }
        }, new Action1() { // from class: com.amazon.mp3.download.NotificationDownloadListener$$ExternalSyntheticLambda5
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                NotificationDownloadListener.m944dismissNotificationIfAllRequestsServed$lambda10(this.f$0, (Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: dismissNotificationIfAllRequestsServed$lambda-9, reason: not valid java name */
    public static final void m945dismissNotificationIfAllRequestsServed$lambda9(NotificationDownloadListener this$0, List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (list.isEmpty()) {
            this$0.mNotificationView.clearNotification();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: dismissNotificationIfAllRequestsServed$lambda-10, reason: not valid java name */
    public static final void m944dismissNotificationIfAllRequestsServed$lambda10(NotificationDownloadListener this$0, Throwable th) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.error(this$0.TAG, th.toString());
    }

    public final void fetchDownloadingOrQueuedRequests(Action1<List<DownloadState>> onSuccess, Action1<Throwable> onError) {
        Intrinsics.checkNotNullParameter(onSuccess, "onSuccess");
        Intrinsics.checkNotNullParameter(onError, "onError");
        Observable.from(this.mRequestIds).map(new Func1() { // from class: com.amazon.mp3.download.NotificationDownloadListener$$ExternalSyntheticLambda3
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return NotificationDownloadListener.m946fetchDownloadingOrQueuedRequests$lambda11(this.f$0, (String) obj);
            }
        }).compose(getRemainingDownloadState()).toSingle().observeOn(Schedulers.from(getMNotificationExecutor())).subscribe(onSuccess, onError);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchDownloadingOrQueuedRequests$lambda-11, reason: not valid java name */
    public static final DownloadState m946fetchDownloadingOrQueuedRequests$lambda11(NotificationDownloadListener this$0, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Item item = this$0.getMDownloader().getItem(str);
        Group group = this$0.getMDownloader().getGroup(str);
        if (item != null) {
            return item.getDownloadState();
        }
        if (group != null) {
            return group.getDownloadState();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getRemainingDownloadState$lambda-13, reason: not valid java name */
    public static final Observable m947getRemainingDownloadState$lambda13(Observable observable) {
        return observable.filter(new Func1() { // from class: com.amazon.mp3.download.NotificationDownloadListener$$ExternalSyntheticLambda12
            @Override // rx.functions.Func1
            public final Object call(Object obj) {
                return NotificationDownloadListener.m948getRemainingDownloadState$lambda13$lambda12((DownloadState) obj);
            }
        }).toList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getRemainingDownloadState$lambda-13$lambda-12, reason: not valid java name */
    public static final Boolean m948getRemainingDownloadState$lambda13$lambda12(DownloadState downloadState) {
        return Boolean.valueOf(downloadState != null && (downloadState == DownloadState.DOWNLOADING || downloadState == DownloadState.QUEUED));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: mRequestCallback$lambda-17, reason: not valid java name */
    public static final void m949mRequestCallback$lambda17(final NotificationDownloadListener this$0, Set set) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (set != null) {
            Observable.from(set).map(new Func1() { // from class: com.amazon.mp3.download.NotificationDownloadListener$$ExternalSyntheticLambda7
                @Override // rx.functions.Func1
                public final Object call(Object obj) {
                    return NotificationDownloadListener.m950mRequestCallback$lambda17$lambda14(this.f$0, (RequestItem) obj);
                }
            }).compose(this$0.getRemainingDownloadState()).toSingle().observeOn(Schedulers.from(this$0.getMNotificationExecutor())).subscribe(new Action1() { // from class: com.amazon.mp3.download.NotificationDownloadListener$$ExternalSyntheticLambda8
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    NotificationDownloadListener.m951mRequestCallback$lambda17$lambda15(this.f$0, (List) obj);
                }
            }, new Action1() { // from class: com.amazon.mp3.download.NotificationDownloadListener$$ExternalSyntheticLambda9
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    NotificationDownloadListener.m952mRequestCallback$lambda17$lambda16(this.f$0, (Throwable) obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: mRequestCallback$lambda-17$lambda-14, reason: not valid java name */
    public static final DownloadState m950mRequestCallback$lambda17$lambda14(NotificationDownloadListener this$0, RequestItem requestItem) {
        Group group;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (requestItem.getRequestType() == RequestItem.RequestType.ITEM) {
            Item item = this$0.getMDownloader().getItem(requestItem.getRequestId());
            if (item == null) {
                return null;
            }
            return item.getDownloadState();
        }
        if (requestItem.getRequestType() != RequestItem.RequestType.GROUP || (group = this$0.getMDownloader().getGroup(requestItem.getRequestId())) == null) {
            return null;
        }
        return group.getDownloadState();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: mRequestCallback$lambda-17$lambda-15, reason: not valid java name */
    public static final void m951mRequestCallback$lambda17$lambda15(NotificationDownloadListener this$0, List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (list.isEmpty()) {
            this$0.onCompleted();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: mRequestCallback$lambda-17$lambda-16, reason: not valid java name */
    public static final void m952mRequestCallback$lambda17$lambda16(NotificationDownloadListener this$0, Throwable th) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.error(this$0.TAG, th.toString());
    }
}
