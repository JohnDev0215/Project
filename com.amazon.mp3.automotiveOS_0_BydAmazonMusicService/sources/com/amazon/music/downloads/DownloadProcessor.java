package com.amazon.music.downloads;

import android.util.Log;
import com.amazon.music.downloads.RequestItem;
import com.amazon.music.downloads.notification.NotificationProvider;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class DownloadProcessor implements ModifiableGroupProvider, ModifiableItemProvider {
    private static final String TAG = "DownloadProcessor";
    private static Downloader sDownloader = null;
    private static boolean shouldResetDownloader = false;
    private final DownloadableItemConverter mDownloadableItemConverter;
    private final ModifiableGroupProvider mGroupProvider;
    private final ModifiableItemProvider mItemProvider;
    private final NotificationProvider mListener;
    private Logger mLog = LoggerFactory.getLogger(TAG);

    public DownloadProcessor(DMMDownloadCoordinator dMMDownloadCoordinator, ModifiableGroupProvider modifiableGroupProvider, ModifiableItemProvider modifiableItemProvider, NotificationProvider notificationProvider, DownloadableItemConverter downloadableItemConverter) {
        createDownloader(dMMDownloadCoordinator);
        this.mGroupProvider = modifiableGroupProvider;
        this.mItemProvider = modifiableItemProvider;
        this.mListener = notificationProvider;
        this.mDownloadableItemConverter = downloadableItemConverter;
    }

    @Override // com.amazon.music.downloads.ModifiableGroupProvider
    public ModifiableGroup getGroup(String str) {
        return this.mGroupProvider.getGroup(str);
    }

    @Override // com.amazon.music.downloads.ModifiableItemProvider
    public ModifiableItem getItem(String str) {
        return this.mItemProvider.getItem(str);
    }

    public void download(RequestItem requestItem) {
        String requestId = requestItem.getRequestId();
        int i = AnonymousClass1.$SwitchMap$com$amazon$music$downloads$RequestItem$RequestType[requestItem.getRequestType().ordinal()];
        if (i == 1) {
            processGroup(requestId);
        } else {
            if (i != 2) {
                return;
            }
            processItem(requestId);
        }
    }

    public ErrorReason hasInstantFailReason(RequestItem requestItem) {
        ModifiableItem item;
        String requestId = requestItem.getRequestId();
        int i = AnonymousClass1.$SwitchMap$com$amazon$music$downloads$RequestItem$RequestType[requestItem.getRequestType().ordinal()];
        if (i == 1) {
            ModifiableGroup group = this.mGroupProvider.getGroup(requestId);
            if (group != null && group.mDownloadState == DownloadState.ERROR && isInstantFailReason(group.mErrorReason)) {
                return group.getErrorReason();
            }
            return null;
        }
        if (i == 2 && (item = this.mItemProvider.getItem(requestId)) != null && item.mDownloadState == DownloadState.ERROR && isInstantFailReason(item.mErrorReason)) {
            return item.getErrorReason();
        }
        return null;
    }

    public void markAsInstantError(RequestItem requestItem, ErrorReason errorReason) {
        ModifiableItem item;
        String requestId = requestItem.getRequestId();
        int i = AnonymousClass1.$SwitchMap$com$amazon$music$downloads$RequestItem$RequestType[requestItem.getRequestType().ordinal()];
        if (i == 1) {
            ModifiableGroup group = this.mGroupProvider.getGroup(requestId);
            if (group == null || group.getDownloadState() == DownloadState.DOWNLOADED) {
                return;
            }
            for (Map.Entry<String, ModifiableItem> entry : group.getItemMap().entrySet()) {
                if (entry.getValue().getDownloadState() != DownloadState.DOWNLOADED) {
                    entry.getValue().setDownloadState(DownloadState.ERROR);
                    entry.getValue().setErrorReason(errorReason);
                    this.mListener.onError(new RequestItem(entry.getKey(), RequestItem.RequestType.ITEM, entry.getValue().isBackground()), errorReason);
                }
            }
            group.setDownloadState(DownloadState.ERROR);
            group.setErrorReason(errorReason);
            this.mListener.onError(requestItem, errorReason);
            return;
        }
        if (i != 2 || (item = this.mItemProvider.getItem(requestId)) == null || item.getDownloadState() == DownloadState.DOWNLOADED) {
            return;
        }
        item.setDownloadState(DownloadState.ERROR);
        item.setErrorReason(errorReason);
        this.mListener.onError(requestItem, errorReason);
    }

    private boolean isInstantFailReason(ErrorReason errorReason) {
        return errorReason == ErrorReason.DISK_OUT_OF_SPACE || errorReason == ErrorReason.NO_DISK_ACCESS_PERMISSION;
    }

    public void pause(RequestItem requestItem) {
        String requestId = requestItem.getRequestId();
        int i = AnonymousClass1.$SwitchMap$com$amazon$music$downloads$RequestItem$RequestType[requestItem.getRequestType().ordinal()];
        if (i != 1) {
            if (i != 2) {
                return;
            }
            sDownloader.pause(requestId);
            return;
        }
        ModifiableGroup group = this.mGroupProvider.getGroup(requestId);
        if (group == null) {
            this.mLog.error("pause() found null group for requestId = %s" + requestId);
            return;
        }
        sDownloader.pause(group.getItemMap().keySet());
    }

    public void cancel(RequestItem requestItem) {
        String requestId = requestItem.getRequestId();
        int i = AnonymousClass1.$SwitchMap$com$amazon$music$downloads$RequestItem$RequestType[requestItem.getRequestType().ordinal()];
        if (i != 1) {
            if (i != 2) {
                return;
            }
            sDownloader.cancel(requestId);
            return;
        }
        ModifiableGroup group = this.mGroupProvider.getGroup(requestId);
        if (group == null) {
            this.mLog.error("cancel() found null group for requestId = " + requestId);
            return;
        }
        sDownloader.cancel(group.getItemMap().keySet());
    }

    private void createDownloader(DMMDownloadCoordinator dMMDownloadCoordinator) {
        synchronized (DownloadProcessor.class) {
            if (sDownloader == null || shouldResetDownloader) {
                sDownloader = new DMMDownloader(dMMDownloadCoordinator);
                shouldResetDownloader = false;
            }
        }
    }

    static synchronized void resetDownloader() {
        shouldResetDownloader = true;
    }

    private void processGroup(String str) {
        ModifiableGroup group = this.mGroupProvider.getGroup(str);
        if (group != null && canDownloadGroup(group.getDownloadState())) {
            group.setDownloadState(DownloadState.DOWNLOADING);
            this.mListener.onDownloadStarted(new RequestItem(str, RequestItem.RequestType.GROUP, group.isBackground()));
            Map<String, ModifiableItem> itemMap = group.getItemMap();
            if (itemMap == null || itemMap.isEmpty()) {
                this.mLog.error("Group download request is empty");
                return;
            }
            Map<String, ModifiableItem> mapUnmodifiableMap = Collections.unmodifiableMap(new LinkedHashMap(group.getItemMap()));
            process(mapUnmodifiableMap, str);
            updateGroupStatus(mapUnmodifiableMap, str);
        }
    }

    private void processItem(String str) {
        ModifiableItem item = this.mItemProvider.getItem(str);
        if (item == null) {
            this.mLog.info("Item download request no longer exist");
        } else {
            process(Collections.singletonMap(str, item), null);
        }
    }

    private boolean shouldSkipCurrentCollectionDownload(Set<String> set, Map<String, ModifiableItem> map, String str) {
        int i;
        if (this.mGroupProvider.getGroup(str) != null && (this.mGroupProvider.getGroup(str).getDownloadState() == DownloadState.CANCELING || this.mGroupProvider.getGroup(str).getDownloadState() == DownloadState.CANCELED)) {
            return true;
        }
        if (str != null && this.mGroupProvider.getGroup(str) == null) {
            return true;
        }
        Iterator<String> it = set.iterator();
        boolean z = false;
        while (it.hasNext()) {
            ModifiableItem modifiableItem = map.get(it.next());
            if (modifiableItem != null && ((i = AnonymousClass1.$SwitchMap$com$amazon$music$downloads$DownloadState[modifiableItem.getDownloadState().ordinal()]) == 1 || i == 2 || i == 3 || i == 4)) {
                z = true;
            }
        }
        return z;
    }

    /* JADX INFO: renamed from: com.amazon.music.downloads.DownloadProcessor$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$downloads$DownloadState;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$downloads$RequestItem$RequestType;

        static {
            int[] iArr = new int[DownloadState.values().length];
            $SwitchMap$com$amazon$music$downloads$DownloadState = iArr;
            try {
                iArr[DownloadState.PAUSED_POLICY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$downloads$DownloadState[DownloadState.PAUSED_USER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$music$downloads$DownloadState[DownloadState.PAUSED_PRIORITY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$music$downloads$DownloadState[DownloadState.ERROR.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$music$downloads$DownloadState[DownloadState.CANCELED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$music$downloads$DownloadState[DownloadState.DOWNLOADED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            int[] iArr2 = new int[RequestItem.RequestType.values().length];
            $SwitchMap$com$amazon$music$downloads$RequestItem$RequestType = iArr2;
            try {
                iArr2[RequestItem.RequestType.GROUP.ordinal()] = 1;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$music$downloads$RequestItem$RequestType[RequestItem.RequestType.ITEM.ordinal()] = 2;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    private void process(Map<String, ModifiableItem> map, String str) {
        Set<String> hashSet = new HashSet<>();
        while (true) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            Map<String, String> map2 = new HashMap<>();
            Map<String, String> map3 = new HashMap<>();
            if (shouldSkipCurrentCollectionDownload(hashSet, map, str)) {
                return;
            }
            hashSet.clear();
            ModifiableGroup group = this.mGroupProvider.getGroup(str);
            if (group != null) {
                group.setDownloadState(DownloadState.DOWNLOADING);
            }
            Iterator<Map.Entry<String, ModifiableItem>> it = map.entrySet().iterator();
            int i = 0;
            while (it.hasNext()) {
                Map.Entry<String, ModifiableItem> next = it.next();
                String key = next.getKey();
                ModifiableItem value = next.getValue();
                if (!noNeedToDownload(value.getDownloadState())) {
                    i++;
                    value.setDownloadState(DownloadState.DOWNLOADING);
                    Iterator<Map.Entry<String, ModifiableItem>> it2 = it;
                    this.mListener.onDownloadStarted(new RequestItem(key, RequestItem.RequestType.ITEM, value.isBackground()));
                    if (sDownloader.contains(key)) {
                        arrayList3.add(key);
                    } else if (value.isOwned()) {
                        map3.put(value.getLuid(), key);
                        arrayList2.add(value.getLuid());
                    } else {
                        map2.put(value.getAsin(), key);
                        arrayList.add(value.getAsin());
                    }
                    hashSet.add(key);
                    if (i == 10) {
                        break;
                    } else {
                        it = it2;
                    }
                }
            }
            filterDownloadableItems(arrayList3, map);
            filterDownloadableItems(map3, map);
            filterDownloadableItems(map2, map);
            int size = arrayList3.size() + map3.size() + map2.size();
            DownloadMonitor downloadMonitorCreateMonitor = createMonitor(createMonitorMap(map3, map2, arrayList3, map), hashSet);
            startDownloading(arrayList3);
            startDownloading(map3, map);
            startDownloading(map2, map);
            if (size > 0) {
                monitor(downloadMonitorCreateMonitor);
                ArrayList arrayList4 = new ArrayList();
                arrayList4.addAll(new ArrayList(arrayList3));
                arrayList4.addAll(new ArrayList(map3.values()));
                arrayList4.addAll(new ArrayList(map2.values()));
                for (int i2 = 0; i2 < arrayList4.size(); i2++) {
                    ModifiableItem modifiableItem = map.get(arrayList4.get(i2));
                    if (modifiableItem != null && str != null && this.mGroupProvider.getGroup(str) != null && modifiableItem.getDownloadState() == DownloadState.ERROR && isInstantFailReason(modifiableItem.getErrorReason())) {
                        markAsInstantError(new RequestItem(str, RequestItem.RequestType.GROUP, this.mGroupProvider.getGroup(str).isBackground()), modifiableItem.getErrorReason());
                        return;
                    }
                }
            } else {
                Log.d(TAG, "Unregistering listener because total count <= 0.");
                sDownloader.unregisterDownloadEventListener(downloadMonitorCreateMonitor);
                return;
            }
        }
    }

    private void filterDownloadableItems(Map<String, String> map, Map<String, ModifiableItem> map2) {
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            if (map2.get(it.next().getValue()).getDownloadState() != DownloadState.DOWNLOADING) {
                it.remove();
            }
        }
    }

    private void filterDownloadableItems(List<String> list, Map<String, ModifiableItem> map) {
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            if (map.get(it.next()).getDownloadState() != DownloadState.DOWNLOADING) {
                it.remove();
            }
        }
    }

    private int startDownloading(Map<String, String> map, Map<String, ModifiableItem> map2) {
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        int i = 0;
        while (it.hasNext()) {
            String str = map.get(it.next().getKey());
            sDownloader.download(str, this.mDownloadableItemConverter.convert(map2.get(str)));
            i++;
        }
        return i;
    }

    private int startDownloading(List<String> list) {
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            sDownloader.resume(it.next());
        }
        return list.size();
    }

    private Map<String, ModifiableItem> createMonitorMap(Map<String, String> map, Map<String, String> map2, List<String> list, Map<String, ModifiableItem> map3) {
        HashMap map4 = new HashMap();
        for (String str : list) {
            map4.put(str, map3.get(str));
        }
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            String value = it.next().getValue();
            map4.put(value, map3.get(value));
        }
        Iterator<Map.Entry<String, String>> it2 = map2.entrySet().iterator();
        while (it2.hasNext()) {
            String value2 = it2.next().getValue();
            map4.put(value2, map3.get(value2));
        }
        return map4;
    }

    private void monitor(DownloadMonitor downloadMonitor) {
        synchronized (downloadMonitor) {
            while (!downloadMonitor.shouldWakeUp()) {
                try {
                    downloadMonitor.wait();
                } catch (InterruptedException unused) {
                }
            }
        }
    }

    private DownloadMonitor createMonitor(Map<String, ModifiableItem> map, Set<String> set) {
        return new DownloadMonitor(map, this.mListener, this, this, sDownloader);
    }

    private void updateGroupStatus(Map<String, ModifiableItem> map, String str) {
        ModifiableGroup group;
        if (map == null || map.isEmpty() || (group = this.mGroupProvider.getGroup(str)) == null) {
            return;
        }
        RequestItem requestItem = new RequestItem(str, RequestItem.RequestType.GROUP, group.isBackground());
        Iterator<Map.Entry<String, ModifiableItem>> it = map.entrySet().iterator();
        int i = 0;
        int i2 = 0;
        while (it.hasNext()) {
            ModifiableItem value = it.next().getValue();
            if (group.getDownloadState() == DownloadState.DOWNLOADING || group.getDownloadState() == DownloadState.PAUSING) {
                requestItem = new RequestItem(str, RequestItem.RequestType.GROUP, group.isBackground());
                DownloadState downloadState = value.getDownloadState();
                switch (AnonymousClass1.$SwitchMap$com$amazon$music$downloads$DownloadState[downloadState.ordinal()]) {
                    case 1:
                    case 2:
                    case 3:
                        this.mLog.info(TAG, "Setting group " + str + " to Paused because at least one item " + value + " is paused.");
                        group.setDownloadState(downloadState);
                        this.mListener.onPaused(requestItem, downloadState);
                        break;
                    case 4:
                        ErrorReason errorReason = value.getErrorReason();
                        group.setDownloadState(DownloadState.ERROR);
                        group.setErrorReason(errorReason);
                        this.mListener.onError(requestItem, errorReason);
                        return;
                    case 5:
                        i2++;
                        break;
                    case 6:
                        i++;
                        break;
                }
            } else {
                if (i > 0 || i + i2 != map.size() || group.getDownloadState() == DownloadState.CANCELED) {
                    return;
                }
                group.setDownloadState(DownloadState.DOWNLOADED);
                this.mListener.onDownloaded(requestItem);
                return;
            }
        }
        if (i > 0) {
        }
    }

    private boolean canDownloadGroup(DownloadState downloadState) {
        return downloadState == DownloadState.QUEUED || downloadState == DownloadState.DOWNLOADING || downloadState == DownloadState.PAUSED_USER || downloadState == DownloadState.PAUSED_POLICY;
    }

    private boolean noNeedToDownload(DownloadState downloadState) {
        return downloadState == DownloadState.DOWNLOADED || downloadState == DownloadState.CANCELED;
    }
}
