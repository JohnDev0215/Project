package com.amazon.music.downloads;

import com.amazon.music.downloads.DMMDownloadCoordinator;
import com.amazon.music.skyfire.models.TetheredMessageKey;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: DMMDownloader.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000]\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0006*\u0001%\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0015\u001a\u00020\u0014¢\u0006\u0004\b(\u0010)J\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0016J\u0010\u0010\b\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\u0016\u0010\b\u001a\u00020\u00062\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00020\tH\u0016J\u0010\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\u0016\u0010\u000b\u001a\u00020\u00062\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00020\tH\u0016J\u0010\u0010\r\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\u0011\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0003\u001a\u00020\u0002H\u0096\u0002J\u0010\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u0010H\u0016J\u0010\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u0010H\u0016R\u0017\u0010\u0015\u001a\u00020\u00148\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u001c\u0010\u001b\u001a\n \u001a*\u0004\u0018\u00010\u00190\u00198\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001b\u0010\u001cRV\u0010\u001f\u001aB\u0012\f\u0012\n \u001a*\u0004\u0018\u00010\u00020\u0002\u0012\f\u0012\n \u001a*\u0004\u0018\u00010\u00020\u0002 \u001a* \u0012\f\u0012\n \u001a*\u0004\u0018\u00010\u00020\u0002\u0012\f\u0012\n \u001a*\u0004\u0018\u00010\u00020\u0002\u0018\u00010\u001e0\u001d8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001f\u0010 RT\u0010!\u001aB\u0012\f\u0012\n \u001a*\u0004\u0018\u00010\u00020\u0002\u0012\f\u0012\n \u001a*\u0004\u0018\u00010\u00040\u0004 \u001a* \u0012\f\u0012\n \u001a*\u0004\u0018\u00010\u00020\u0002\u0012\f\u0012\n \u001a*\u0004\u0018\u00010\u00040\u0004\u0018\u00010\u001e0\u001d8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b!\u0010 R\u0014\u0010#\u001a\u00020\"8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b#\u0010$R\u0014\u0010&\u001a\u00020%8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b&\u0010'¨\u0006*"}, d2 = {"Lcom/amazon/music/downloads/DMMDownloader;", "Lcom/amazon/music/downloads/Downloader;", "", TetheredMessageKey.requestId, "Lcom/amazon/music/downloads/DownloadableItem;", "downloadableItem", "", "download", "cancel", "", "requestIds", "pause", "ids", "resume", "", "contains", "Lcom/amazon/music/downloads/DownloadEventListener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "registerDownloadEventListener", "unregisterDownloadEventListener", "Lcom/amazon/music/downloads/DMMDownloadCoordinator;", "downloadCoordinator", "Lcom/amazon/music/downloads/DMMDownloadCoordinator;", "getDownloadCoordinator", "()Lcom/amazon/music/downloads/DMMDownloadCoordinator;", "Lorg/slf4j/Logger;", "kotlin.jvm.PlatformType", "mLog", "Lorg/slf4j/Logger;", "", "", "mDownloadParamToRequestIDMap", "Ljava/util/Map;", "mRequestIDtoDownloadableItemMap", "Lcom/amazon/music/downloads/EventThread;", "mEventThread", "Lcom/amazon/music/downloads/EventThread;", "com/amazon/music/downloads/DMMDownloader$mDownloadCoordinatorListener$1", "mDownloadCoordinatorListener", "Lcom/amazon/music/downloads/DMMDownloader$mDownloadCoordinatorListener$1;", "<init>", "(Lcom/amazon/music/downloads/DMMDownloadCoordinator;)V", "DMMDownloadManager_release"}, k = 1, mv = {1, 6, 0})
public final class DMMDownloader implements Downloader {
    private final DMMDownloadCoordinator downloadCoordinator;
    private final DMMDownloader$mDownloadCoordinatorListener$1 mDownloadCoordinatorListener;
    private Map<String, String> mDownloadParamToRequestIDMap;
    private final EventThread mEventThread;
    private final Logger mLog;
    private final Map<String, DownloadableItem> mRequestIDtoDownloadableItemMap;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v9, types: [com.amazon.music.downloads.DMMDownloadCoordinator$DMMDownloadListener, com.amazon.music.downloads.DMMDownloader$mDownloadCoordinatorListener$1] */
    public DMMDownloader(DMMDownloadCoordinator downloadCoordinator) {
        Intrinsics.checkNotNullParameter(downloadCoordinator, "downloadCoordinator");
        this.downloadCoordinator = downloadCoordinator;
        this.mLog = LoggerFactory.getLogger(DMMDownloader.class.getSimpleName());
        this.mDownloadParamToRequestIDMap = Collections.synchronizedMap(new HashMap());
        this.mRequestIDtoDownloadableItemMap = Collections.synchronizedMap(new HashMap());
        this.mEventThread = new EventThread();
        ?? r0 = new DMMDownloadCoordinator.DMMDownloadListener() { // from class: com.amazon.music.downloads.DMMDownloader$mDownloadCoordinatorListener$1

            /* JADX INFO: compiled from: DMMDownloader.kt */
            @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
            public /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[ErrorReason.values().length];
                    iArr[ErrorReason.NO_ERROR.ordinal()] = 1;
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            @Override // com.amazon.music.downloads.DMMDownloadCoordinator.DMMDownloadListener
            public void downloadComplete(String ID, ErrorReason error, String fileName) {
                Intrinsics.checkNotNullParameter(ID, "ID");
                Intrinsics.checkNotNullParameter(error, "error");
                Intrinsics.checkNotNullParameter(fileName, "fileName");
                String str = (String) this.this$0.mDownloadParamToRequestIDMap.get(ID);
                if (str == null) {
                    return;
                }
                DMMDownloader dMMDownloader = this.this$0;
                DownloadableItem downloadableItem = (DownloadableItem) dMMDownloader.mRequestIDtoDownloadableItemMap.get(str);
                if (downloadableItem != null) {
                    downloadableItem.setProcessing(false);
                }
                dMMDownloader.mRequestIDtoDownloadableItemMap.remove(str);
                dMMDownloader.mDownloadParamToRequestIDMap.remove(ID);
                if (WhenMappings.$EnumSwitchMapping$0[error.ordinal()] == 1) {
                    dMMDownloader.mLog.debug("Download complete for ID " + ID + ", requestID " + str + ", error " + error + ", location " + fileName);
                    dMMDownloader.mEventThread.onDownloaded(str, fileName);
                    return;
                }
                dMMDownloader.mLog.debug("Download failed for ID " + ID + ", requestID " + str + ", error " + error + ", location " + fileName);
                dMMDownloader.mEventThread.onError(str, error);
            }

            @Override // com.amazon.music.downloads.DMMDownloadCoordinator.DMMDownloadListener
            public void progressUpdate(String ID, float percent) {
                Intrinsics.checkNotNullParameter(ID, "ID");
                String str = (String) this.this$0.mDownloadParamToRequestIDMap.get(ID);
                if (str == null) {
                    return;
                }
                this.this$0.mEventThread.onProgress(str, percent);
            }

            @Override // com.amazon.music.downloads.DMMDownloadCoordinator.DMMDownloadListener
            public void downloadPaused(String ID, ErrorReason error) {
                Intrinsics.checkNotNullParameter(ID, "ID");
                Intrinsics.checkNotNullParameter(error, "error");
                String str = (String) this.this$0.mDownloadParamToRequestIDMap.get(ID);
                if (str == null) {
                    return;
                }
                DMMDownloader dMMDownloader = this.this$0;
                dMMDownloader.mLog.debug("Download paused for ID " + ID + ", requestID " + str + ", error " + error);
                dMMDownloader.mEventThread.onPaused(str);
            }

            @Override // com.amazon.music.downloads.DMMDownloadCoordinator.DMMDownloadListener
            public void downloadResumed(String ID, ErrorReason error) {
                Intrinsics.checkNotNullParameter(ID, "ID");
                Intrinsics.checkNotNullParameter(error, "error");
                String str = (String) this.this$0.mDownloadParamToRequestIDMap.get(ID);
                if (str == null) {
                    return;
                }
                DMMDownloader dMMDownloader = this.this$0;
                dMMDownloader.mLog.debug("Download resumed for ID " + ID + ", requestID " + str + ", error " + error);
                dMMDownloader.mEventThread.onResumed(str);
            }
        };
        this.mDownloadCoordinatorListener = r0;
        downloadCoordinator.registerListener(r0);
    }

    public final DMMDownloadCoordinator getDownloadCoordinator() {
        return this.downloadCoordinator;
    }

    @Override // com.amazon.music.downloads.Downloader
    public void download(String requestId, DownloadableItem downloadableItem) {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(downloadableItem, "downloadableItem");
        this.mLog.debug("Downloading " + downloadableItem.getDownloadItemInfo() + " with ID " + requestId);
        Map<String, DownloadableItem> mRequestIDtoDownloadableItemMap = this.mRequestIDtoDownloadableItemMap;
        Intrinsics.checkNotNullExpressionValue(mRequestIDtoDownloadableItemMap, "mRequestIDtoDownloadableItemMap");
        mRequestIDtoDownloadableItemMap.put(requestId, downloadableItem);
        Map<String, String> mDownloadParamToRequestIDMap = this.mDownloadParamToRequestIDMap;
        Intrinsics.checkNotNullExpressionValue(mDownloadParamToRequestIDMap, "mDownloadParamToRequestIDMap");
        mDownloadParamToRequestIDMap.put(downloadableItem.getDownloadItemInfo().getId(), requestId);
        this.mEventThread.onAdded(requestId);
        downloadableItem.setProcessing(true);
        downloadableItem.setQueued(false);
        this.downloadCoordinator.download(downloadableItem.getDownloadItemInfo());
    }

    @Override // com.amazon.music.downloads.Downloader
    public void cancel(String requestId) {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        this.mLog.debug("Canceling download with request ID " + requestId + '.');
        DownloadableItem downloadableItem = this.mRequestIDtoDownloadableItemMap.get(requestId);
        if (downloadableItem != null) {
            downloadableItem.setCanceled(true);
            downloadableItem.setProcessing(false);
            getDownloadCoordinator().cancelDownload(downloadableItem.getDownloadItemInfo());
            this.mRequestIDtoDownloadableItemMap.remove(requestId);
            this.mDownloadParamToRequestIDMap.remove(downloadableItem.getDownloadItemInfo().getId());
        }
        this.mEventThread.onCanceled(requestId);
    }

    @Override // com.amazon.music.downloads.Downloader
    public void cancel(Collection<String> requestIds) {
        Intrinsics.checkNotNullParameter(requestIds, "requestIds");
        Iterator<String> it = requestIds.iterator();
        while (it.hasNext()) {
            cancel(it.next());
        }
    }

    @Override // com.amazon.music.downloads.Downloader
    public void pause(String requestId) {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        this.mLog.debug("Pausing download with ID " + requestId + '.');
        DownloadableItem downloadableItem = this.mRequestIDtoDownloadableItemMap.get(requestId);
        if (downloadableItem == null) {
            return;
        }
        downloadableItem.setPaused(true);
        getDownloadCoordinator().pause(downloadableItem.getDownloadItemInfo());
    }

    @Override // com.amazon.music.downloads.Downloader
    public void pause(Collection<String> ids) {
        Intrinsics.checkNotNullParameter(ids, "ids");
        Iterator<String> it = ids.iterator();
        while (it.hasNext()) {
            pause(it.next());
        }
    }

    @Override // com.amazon.music.downloads.Downloader
    public void resume(String requestId) {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        this.mLog.debug("Resuming download with ID " + requestId + '.');
        DownloadableItem downloadableItem = this.mRequestIDtoDownloadableItemMap.get(requestId);
        if (downloadableItem == null) {
            return;
        }
        downloadableItem.setPaused(false);
        if (!downloadableItem.getIsQueued() && !downloadableItem.getIsProcessing()) {
            download(requestId, downloadableItem);
        } else {
            getDownloadCoordinator().resume(downloadableItem.getDownloadItemInfo());
        }
    }

    @Override // com.amazon.music.downloads.Downloader
    public boolean contains(String requestId) {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        return this.mRequestIDtoDownloadableItemMap.containsKey(requestId);
    }

    @Override // com.amazon.music.downloads.Downloader
    public void registerDownloadEventListener(DownloadEventListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.mEventThread.registerDownloadEventListener(listener);
    }

    @Override // com.amazon.music.downloads.Downloader
    public void unregisterDownloadEventListener(DownloadEventListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.mEventThread.unregisterDownloadEventListener(listener);
    }
}
