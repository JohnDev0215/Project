package com.amazon.music.downloads;

import android.content.Context;
import com.amazon.music.downloads.RequestItem;
import com.amazon.music.downloads.RequestProvider;
import com.amazon.music.downloads.notification.DownloadListener;
import com.amazon.music.downloads.notification.NotificationProvider;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class MusicDownloadManager implements DownloadManager {
    private static final String TAG = "MusicDownloadManager";
    private final Context mContext;
    private final DatabaseManager mDatabaseManager;
    private DMMDownloadCoordinator mDownloadCoordinator;
    private ItemProvider mItemProvider;
    private ModifiableGroupProvider mModifiableGroupProvider;
    private ModifiableItemProvider mModifiableItemProvider;
    private NotificationProvider mNotificationProvider;
    private QueueWorker mQueueWorker;
    private Logger mLog = LoggerFactory.getLogger(TAG);
    private final Map<String, ModifiableGroup> mIdGroupMap = Collections.synchronizedMap(new HashMap());
    private final Map<String, ModifiableItem> mIdItemMap = Collections.synchronizedMap(new HashMap());
    private final Set<RequestItem> mRequestSet = Collections.synchronizedSet(new HashSet());
    private ThreadPoolExecutor mExecutor = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue());
    private GroupProvider mGroupProvider = new GroupProvider() { // from class: com.amazon.music.downloads.MusicDownloadManager.8
        @Override // com.amazon.music.downloads.GroupProvider
        public Group getGroup(String str) {
            return (Group) MusicDownloadManager.this.mIdGroupMap.get(str);
        }
    };

    public MusicDownloadManager(Context context, DMMDownloadCoordinator dMMDownloadCoordinator) {
        ItemProvider itemProvider = new ItemProvider() { // from class: com.amazon.music.downloads.MusicDownloadManager.9
            @Override // com.amazon.music.downloads.ItemProvider
            public Item getItem(String str) {
                return (Item) MusicDownloadManager.this.mIdItemMap.get(str);
            }
        };
        this.mItemProvider = itemProvider;
        this.mNotificationProvider = new NotificationProvider(this.mGroupProvider, itemProvider);
        this.mModifiableGroupProvider = new ModifiableGroupProvider() { // from class: com.amazon.music.downloads.MusicDownloadManager.10
            @Override // com.amazon.music.downloads.ModifiableGroupProvider
            public ModifiableGroup getGroup(String str) {
                return (ModifiableGroup) MusicDownloadManager.this.mIdGroupMap.get(str);
            }
        };
        this.mModifiableItemProvider = new ModifiableItemProvider() { // from class: com.amazon.music.downloads.MusicDownloadManager.11
            @Override // com.amazon.music.downloads.ModifiableItemProvider
            public ModifiableItem getItem(String str) {
                return (ModifiableItem) MusicDownloadManager.this.mIdItemMap.get(str);
            }
        };
        if (context == null) {
            throw new NullPointerException("Null parameters");
        }
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        this.mDownloadCoordinator = dMMDownloadCoordinator;
        this.mDatabaseManager = DatabaseManager.getInstance(applicationContext);
        registerInternalListener();
        this.mExecutor.execute(new Runnable() { // from class: com.amazon.music.downloads.MusicDownloadManager.1
            @Override // java.lang.Runnable
            public void run() {
                MusicDownloadManager.this.restoreFromDB();
            }
        });
    }

    public boolean isDownloading() {
        QueueWorker queueWorker = this.mQueueWorker;
        return queueWorker != null && queueWorker.isDownloading();
    }

    public void resetDownloadCoordinatorObject(DMMDownloadCoordinator dMMDownloadCoordinator) {
        this.mDownloadCoordinator = dMMDownloadCoordinator;
        DownloadProcessor.resetDownloader();
        DownloadQueueWorker downloadQueueWorker = new DownloadQueueWorker(this.mContext, this.mDownloadCoordinator, this.mModifiableGroupProvider, this.mModifiableItemProvider, this.mNotificationProvider, new ItemDownloadableItemConverter());
        this.mQueueWorker = downloadQueueWorker;
        downloadQueueWorker.start();
        this.mExecutor.execute(new Runnable() { // from class: com.amazon.music.downloads.MusicDownloadManager.2
            @Override // java.lang.Runnable
            public void run() {
                MusicDownloadManager.this.restoreFromDB();
            }
        });
        this.mLog.debug("Queue worker started");
    }

    @Override // com.amazon.music.downloads.DownloadManager
    public void registerDownloadListener(DownloadListener downloadListener) {
        if (downloadListener == null) {
            throw new NullPointerException("Cannot pass a null download listener");
        }
        this.mNotificationProvider.registerDownloadListener(downloadListener);
    }

    @Override // com.amazon.music.downloads.DownloadManager
    public void unregisterDownloadListener(DownloadListener downloadListener) {
        if (downloadListener == null) {
            throw new NullPointerException("Cannot unregister a null listener");
        }
        this.mNotificationProvider.unregisterDownloadListener(downloadListener);
    }

    @Override // com.amazon.music.downloads.DownloadManager
    public RequestProvider getRequestProvider() {
        return new DownloadRequestProvider();
    }

    public Item getItem(String str) {
        if (str == null) {
            throw new NullPointerException("Request id cannot be null");
        }
        return this.mIdItemMap.get(str);
    }

    public Group getGroup(String str) {
        if (str == null) {
            throw new NullPointerException("Request id cannot be null");
        }
        return this.mIdGroupMap.get(str);
    }

    public void download(final String str, final Item item) {
        if (str == null || item == null) {
            throw new NullPointerException("Null parameters");
        }
        this.mExecutor.execute(new Runnable() { // from class: com.amazon.music.downloads.MusicDownloadManager.3
            @Override // java.lang.Runnable
            public void run() {
                if (MusicDownloadManager.this.mIdItemMap.containsKey(str)) {
                    return;
                }
                ModifiableItem modifiableItem = new ModifiableItem(item);
                MusicDownloadManager.this.mIdItemMap.put(str, modifiableItem);
                MusicDownloadManager.this.recreateQueueWorkerIfNeeded();
                RequestItem requestItemGenerateRequestItem = MusicDownloadManager.this.mDatabaseManager.generateRequestItem(str, modifiableItem);
                MusicDownloadManager.this.mDatabaseManager.addDownloadItem(str, modifiableItem);
                modifiableItem.setDownloadState(DownloadState.QUEUED);
                MusicDownloadManager.this.mNotificationProvider.onQueued(requestItemGenerateRequestItem);
                MusicDownloadManager.this.mQueueWorker.add(requestItemGenerateRequestItem);
                MusicDownloadManager.this.mRequestSet.add(requestItemGenerateRequestItem);
            }
        });
    }

    public void download(final String str, final Group group) {
        if (str == null || group == null) {
            throw new NullPointerException("Null parameters");
        }
        this.mExecutor.execute(new Runnable() { // from class: com.amazon.music.downloads.MusicDownloadManager.4
            @Override // java.lang.Runnable
            public void run() {
                if (MusicDownloadManager.this.mIdGroupMap.containsKey(str)) {
                    ModifiableGroup modifiableGroup = (ModifiableGroup) MusicDownloadManager.this.mIdGroupMap.get(str);
                    if (modifiableGroup.getDownloadState() != DownloadState.ERROR) {
                        return;
                    } else {
                        MusicDownloadManager.this.onGroupDownloadStateUpdate(DownloadState.CANCELED, str, modifiableGroup);
                    }
                }
                ModifiableGroup modifiableGroup2 = new ModifiableGroup(group);
                modifiableGroup2.setDownloadState(DownloadState.QUEUED);
                Map<String, ModifiableItem> itemMap = modifiableGroup2.getItemMap();
                if (itemMap == null || itemMap.isEmpty()) {
                    return;
                }
                MusicDownloadManager.this.mIdGroupMap.put(str, modifiableGroup2);
                for (Map.Entry<String, ModifiableItem> entry : itemMap.entrySet()) {
                    ModifiableItem value = entry.getValue();
                    value.setDownloadState(DownloadState.QUEUED);
                    value.setGroupRequestId(str);
                    MusicDownloadManager.this.mIdItemMap.put(entry.getKey(), value);
                    MusicDownloadManager.this.mNotificationProvider.onQueued(new RequestItem(entry.getKey(), RequestItem.RequestType.ITEM, value.isBackground()));
                }
                MusicDownloadManager.this.recreateQueueWorkerIfNeeded();
                RequestItem requestItemGenerateRequestItem = MusicDownloadManager.this.mDatabaseManager.generateRequestItem(str, modifiableGroup2);
                MusicDownloadManager.this.mDatabaseManager.addDownloadGroup(str, modifiableGroup2);
                MusicDownloadManager.this.mNotificationProvider.onQueued(requestItemGenerateRequestItem);
                MusicDownloadManager.this.mQueueWorker.add(requestItemGenerateRequestItem);
                MusicDownloadManager.this.mRequestSet.add(requestItemGenerateRequestItem);
            }
        });
    }

    @Override // com.amazon.music.downloads.DownloadManager
    public void pause(final String str) {
        if (str == null) {
            throw new NullPointerException("Request id cannot be null");
        }
        this.mExecutor.execute(new Runnable() { // from class: com.amazon.music.downloads.MusicDownloadManager.5
            @Override // java.lang.Runnable
            public void run() {
                MusicDownloadManager.this.groupPausingAction(str);
                MusicDownloadManager.this.itemPausingAction(str);
                MusicDownloadManager.this.performClientAction(str, DownloadState.PAUSED_USER);
            }
        });
    }

    @Override // com.amazon.music.downloads.DownloadManager
    public void resume(final String str) {
        if (str == null) {
            throw new NullPointerException("Request id cannot be null");
        }
        this.mExecutor.execute(new Runnable() { // from class: com.amazon.music.downloads.MusicDownloadManager.6
            @Override // java.lang.Runnable
            public void run() {
                if (MusicDownloadManager.this.mGroupProvider.getGroup(str) != null) {
                    Group group = MusicDownloadManager.this.mGroupProvider.getGroup(str);
                    if (group.getDownloadState() == DownloadState.QUEUED || group.getDownloadState() == DownloadState.DOWNLOADING) {
                        return;
                    }
                }
                RequestItem requestItemPerformClientAction = MusicDownloadManager.this.performClientAction(str, DownloadState.QUEUED);
                MusicDownloadManager.this.groupQueueAction(str);
                MusicDownloadManager.this.itemQueueAction(str);
                if (requestItemPerformClientAction != null) {
                    MusicDownloadManager.this.mNotificationProvider.onQueued(requestItemPerformClientAction);
                }
            }
        });
    }

    @Override // com.amazon.music.downloads.DownloadManager
    public void cancel(final String str) {
        if (str == null) {
            throw new NullPointerException("Request id cannot be null");
        }
        this.mExecutor.execute(new Runnable() { // from class: com.amazon.music.downloads.MusicDownloadManager.7
            @Override // java.lang.Runnable
            public void run() {
                MusicDownloadManager.this.groupCancelingAction(str);
                MusicDownloadManager.this.itemCancelingAction(str);
                MusicDownloadManager.this.performClientAction(str, DownloadState.CANCELED);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public RequestItem performClientAction(String str, DownloadState downloadState) {
        ModifiableItem modifiableItem = this.mIdItemMap.get(str);
        if (modifiableItem == null) {
            ModifiableGroup modifiableGroup = this.mIdGroupMap.get(str);
            if (modifiableGroup == null || !groupAction(str, downloadState)) {
                return null;
            }
            RequestItem requestItem = new RequestItem(str, RequestItem.RequestType.GROUP, modifiableGroup.isBackground());
            updateQueueWorker(requestItem, downloadState);
            return requestItem;
        }
        if (!itemAction(str, downloadState)) {
            return null;
        }
        RequestItem requestItem2 = new RequestItem(str, RequestItem.RequestType.ITEM, modifiableItem.isBackground());
        updateQueueWorker(requestItem2, downloadState);
        return requestItem2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void recreateQueueWorkerIfNeeded() {
        QueueWorker queueWorker = this.mQueueWorker;
        if (queueWorker == null || queueWorker.hasTerminated()) {
            DownloadQueueWorker downloadQueueWorker = new DownloadQueueWorker(this.mContext, this.mDownloadCoordinator, this.mModifiableGroupProvider, this.mModifiableItemProvider, this.mNotificationProvider, new ItemDownloadableItemConverter());
            this.mQueueWorker = downloadQueueWorker;
            downloadQueueWorker.start();
            this.mLog.debug("Queue worker started");
        }
    }

    public void destroyQueueWorkerIfPossible() {
        QueueWorker queueWorker = this.mQueueWorker;
        if (queueWorker == null || !queueWorker.isIdle()) {
            return;
        }
        this.mQueueWorker.exit();
        this.mLog.debug("Queue worker called to exit");
    }

    private void updateQueueWorker(RequestItem requestItem, DownloadState downloadState) {
        recreateQueueWorkerIfNeeded();
        int i = AnonymousClass14.$SwitchMap$com$amazon$music$downloads$DownloadState[downloadState.ordinal()];
        if (i == 1) {
            this.mQueueWorker.pause(requestItem);
        } else if (i == 2) {
            this.mQueueWorker.cancel(requestItem);
        } else {
            if (i != 3) {
                return;
            }
            this.mQueueWorker.add(requestItem);
        }
    }

    private boolean groupAction(String str, DownloadState downloadState) {
        ModifiableGroup modifiableGroup = this.mIdGroupMap.get(str);
        DownloadState downloadState2 = modifiableGroup.getDownloadState();
        if (downloadState2 == downloadState || downloadState2 == DownloadState.DOWNLOADED) {
            return false;
        }
        Iterator<String> it = modifiableGroup.getItemMap().keySet().iterator();
        while (it.hasNext()) {
            itemAction(it.next(), downloadState);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void groupPausingAction(String str) {
        ModifiableGroup modifiableGroup = this.mIdGroupMap.get(str);
        if (modifiableGroup == null || modifiableGroup.getDownloadState() == DownloadState.DOWNLOADED) {
            return;
        }
        modifiableGroup.setDownloadState(DownloadState.PAUSING);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void itemPausingAction(String str) {
        ModifiableItem modifiableItem = this.mIdItemMap.get(str);
        if (modifiableItem == null || modifiableItem.getDownloadState() == DownloadState.DOWNLOADED) {
            return;
        }
        modifiableItem.setDownloadState(DownloadState.PAUSING);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void groupCancelingAction(String str) {
        ModifiableGroup modifiableGroup = this.mIdGroupMap.get(str);
        if (modifiableGroup == null) {
            return;
        }
        modifiableGroup.setDownloadState(DownloadState.CANCELING);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void itemCancelingAction(String str) {
        ModifiableItem modifiableItem = this.mIdItemMap.get(str);
        if (modifiableItem != null) {
            modifiableItem.setDownloadState(DownloadState.CANCELING);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void groupQueueAction(String str) {
        ModifiableGroup modifiableGroup = this.mIdGroupMap.get(str);
        if (modifiableGroup == null || modifiableGroup.getDownloadState() == DownloadState.DOWNLOADED) {
            return;
        }
        modifiableGroup.setDownloadState(DownloadState.QUEUED);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void itemQueueAction(String str) {
        ModifiableItem modifiableItem = this.mIdItemMap.get(str);
        if (modifiableItem == null || modifiableItem.getDownloadState() == DownloadState.DOWNLOADED) {
            return;
        }
        modifiableItem.setDownloadState(DownloadState.QUEUED);
    }

    private boolean itemAction(String str, DownloadState downloadState) {
        DownloadState downloadState2;
        ModifiableItem modifiableItem = this.mIdItemMap.get(str);
        return (modifiableItem == null || (downloadState2 = modifiableItem.getDownloadState()) == downloadState || downloadState2 == DownloadState.DOWNLOADED) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void restoreFromDB() {
        ModifiableItem modifiableItemLoadDownloadItem;
        for (RequestItem requestItem : this.mDatabaseManager.loadRequestItems()) {
            String requestId = requestItem.getRequestId();
            int i = AnonymousClass14.$SwitchMap$com$amazon$music$downloads$RequestItem$RequestType[requestItem.getRequestType().ordinal()];
            if (i == 1) {
                ModifiableGroup modifiableGroupLoadDownloadGroup = this.mDatabaseManager.loadDownloadGroup(requestItem.getRequestId());
                if (modifiableGroupLoadDownloadGroup != null) {
                    modifyDownloadStateToQueued(modifiableGroupLoadDownloadGroup);
                    for (Map.Entry<String, ModifiableItem> entry : modifiableGroupLoadDownloadGroup.getItemMap().entrySet()) {
                        modifyDownloadStateToQueued(entry.getValue());
                        this.mIdItemMap.put(entry.getKey(), entry.getValue());
                    }
                    this.mIdGroupMap.put(requestId, modifiableGroupLoadDownloadGroup);
                    this.mRequestSet.add(requestItem);
                    if (modifiableGroupLoadDownloadGroup.getDownloadState() == DownloadState.QUEUED) {
                        recreateQueueWorkerIfNeeded();
                        this.mQueueWorker.add(requestItem);
                    }
                }
            } else if (i == 2 && (modifiableItemLoadDownloadItem = this.mDatabaseManager.loadDownloadItem(requestId)) != null) {
                modifyDownloadStateToQueued(modifiableItemLoadDownloadItem);
                this.mIdItemMap.put(requestId, modifiableItemLoadDownloadItem);
                this.mRequestSet.add(requestItem);
                if (modifiableItemLoadDownloadItem.getDownloadState() == DownloadState.QUEUED) {
                    recreateQueueWorkerIfNeeded();
                    this.mQueueWorker.add(requestItem);
                }
            }
        }
    }

    /* JADX INFO: renamed from: com.amazon.music.downloads.MusicDownloadManager$14, reason: invalid class name */
    static /* synthetic */ class AnonymousClass14 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$downloads$DownloadState;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$downloads$RequestItem$RequestType;

        static {
            int[] iArr = new int[RequestItem.RequestType.values().length];
            $SwitchMap$com$amazon$music$downloads$RequestItem$RequestType = iArr;
            try {
                iArr[RequestItem.RequestType.GROUP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$downloads$RequestItem$RequestType[RequestItem.RequestType.ITEM.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[DownloadState.values().length];
            $SwitchMap$com$amazon$music$downloads$DownloadState = iArr2;
            try {
                iArr2[DownloadState.PAUSED_USER.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$music$downloads$DownloadState[DownloadState.CANCELED.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$music$downloads$DownloadState[DownloadState.QUEUED.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$music$downloads$DownloadState[DownloadState.DOWNLOADING.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$music$downloads$DownloadState[DownloadState.PAUSED_POLICY.ordinal()] = 5;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$music$downloads$DownloadState[DownloadState.UNKNOWN.ordinal()] = 6;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    private void modifyDownloadStateToQueued(ModifiableGroup modifiableGroup) {
        int i = AnonymousClass14.$SwitchMap$com$amazon$music$downloads$DownloadState[modifiableGroup.getDownloadState().ordinal()];
        if (i == 4 || i == 5 || i == 6) {
            modifiableGroup.setDownloadState(DownloadState.QUEUED);
        }
    }

    private void modifyDownloadStateToQueued(ModifiableItem modifiableItem) {
        int i = AnonymousClass14.$SwitchMap$com$amazon$music$downloads$DownloadState[modifiableItem.getDownloadState().ordinal()];
        if (i == 4 || i == 5 || i == 6) {
            modifiableItem.setDownloadState(DownloadState.QUEUED);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isTerminalState(DownloadState downloadState) {
        int i = AnonymousClass14.$SwitchMap$com$amazon$music$downloads$DownloadState[downloadState.ordinal()];
        return (i == 3 || i == 4) ? false : true;
    }

    private class DownloadRequestProvider implements RequestProvider {
        private DownloadRequestProvider() {
        }

        @Override // com.amazon.music.downloads.RequestProvider
        public void getRequests(final RequestProvider.RequestProviderCallback requestProviderCallback) {
            if (requestProviderCallback != null) {
                MusicDownloadManager.this.mExecutor.execute(new Runnable() { // from class: com.amazon.music.downloads.MusicDownloadManager.DownloadRequestProvider.1
                    @Override // java.lang.Runnable
                    public void run() {
                        requestProviderCallback.requestsSubmitted(new CopyOnWriteArraySet(MusicDownloadManager.this.mRequestSet));
                    }
                });
                return;
            }
            throw new NullPointerException("Null parameter");
        }
    }

    private void registerInternalListener() {
        this.mNotificationProvider.registerDownloadListener(new DownloadListener(true) { // from class: com.amazon.music.downloads.MusicDownloadManager.12
            @Override // com.amazon.music.downloads.notification.DownloadListener
            public void onProgressUpdate(String str, Group group, float f) {
            }

            @Override // com.amazon.music.downloads.notification.DownloadListener
            public void onProgressUpdate(String str, Item item, float f) {
            }

            @Override // com.amazon.music.downloads.notification.DownloadListener
            public void onStatusUpdate(DownloadState downloadState, String str, Group group) {
                MusicDownloadManager.this.onGroupDownloadStateUpdate(downloadState, str, group);
            }

            @Override // com.amazon.music.downloads.notification.DownloadListener
            public void onStatusUpdate(DownloadState downloadState, String str, Item item) {
                MusicDownloadManager.this.mLog.debug("download listener status update, " + str + " " + downloadState.toString());
                if (MusicDownloadManager.this.removeItem(downloadState, str, item)) {
                    MusicDownloadManager.this.mDatabaseManager.removeDownloadItems(Collections.singleton(str));
                    MusicDownloadManager.this.mDatabaseManager.removeRequestItem(str);
                } else {
                    MusicDownloadManager.this.mDatabaseManager.updateItemState(str, downloadState);
                    if (downloadState == DownloadState.ERROR) {
                        ErrorReason errorReason = item.getErrorReason();
                        MusicDownloadManager.this.mDatabaseManager.updateItemErrorReason(Collections.singleton(str), errorReason);
                        MusicDownloadManager.this.mLog.warn("Download failed because {}, {}", errorReason, item);
                    }
                }
                if (MusicDownloadManager.this.isTerminalState(downloadState)) {
                    MusicDownloadManager.this.mExecutor.execute(new Runnable() { // from class: com.amazon.music.downloads.MusicDownloadManager.12.1
                        @Override // java.lang.Runnable
                        public void run() {
                            MusicDownloadManager.this.destroyQueueWorkerIfPossible();
                        }
                    });
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onGroupDownloadStateUpdate(DownloadState downloadState, String str, Group group) {
        this.mLog.debug("download listener status update, " + str + " " + downloadState.toString());
        if (downloadState == DownloadState.CANCELED || downloadState == DownloadState.DOWNLOADED) {
            this.mIdGroupMap.remove(str);
            this.mRequestSet.remove(new RequestItem(str, RequestItem.RequestType.GROUP, group.isBackground()));
            Map<String, ModifiableItem> itemMap = new ModifiableGroup(group).getItemMap();
            if (itemMap == null) {
                return;
            }
            this.mDatabaseManager.removeDownloadItems(itemMap.keySet());
            this.mDatabaseManager.removeDownloadGroups(Collections.singleton(str));
            this.mDatabaseManager.removeRequestItem(str);
            for (Map.Entry<String, ModifiableItem> entry : itemMap.entrySet()) {
                String key = entry.getKey();
                ModifiableItem modifiableItem = this.mIdItemMap.get(key);
                if (modifiableItem != null && str.equals(modifiableItem.getGroupRequestId())) {
                    removeItem(downloadState, key, entry.getValue());
                }
            }
        } else {
            this.mDatabaseManager.updateGroupState(str, downloadState);
            if (downloadState == DownloadState.ERROR) {
                ErrorReason errorReason = group.getErrorReason();
                this.mDatabaseManager.updateGroupErrorReason(Collections.singleton(str), errorReason);
                this.mLog.warn("Download failed because {}, {}", errorReason, group);
            }
        }
        if (isTerminalState(downloadState)) {
            this.mExecutor.execute(new Runnable() { // from class: com.amazon.music.downloads.MusicDownloadManager.13
                @Override // java.lang.Runnable
                public void run() {
                    MusicDownloadManager.this.destroyQueueWorkerIfPossible();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean removeItem(DownloadState downloadState, String str, Item item) {
        if (downloadState != DownloadState.CANCELED && downloadState != DownloadState.DOWNLOADED) {
            return false;
        }
        this.mLog.debug("removing item from queue ID: " + str + " download state: " + downloadState.toString());
        this.mIdItemMap.remove(str);
        this.mRequestSet.remove(new RequestItem(str, RequestItem.RequestType.ITEM, item.isBackground()));
        return true;
    }
}
