package com.amazon.music.downloads;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.Collection;
import kotlin.Metadata;

/* JADX INFO: compiled from: Downloader.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H&J\u0010\u0010\b\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0002H&J\u0016\u0010\b\u001a\u00020\u00062\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00020\tH&J\u0010\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0002H&J\u0016\u0010\u000b\u001a\u00020\u00062\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00020\tH&J\u0010\u0010\f\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0002H&J\u0011\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\u0002H¦\u0002J\u0010\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u000fH&J\u0010\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u000fH&¨\u0006\u0013"}, d2 = {"Lcom/amazon/music/downloads/Downloader;", "", "", "id", "Lcom/amazon/music/downloads/DownloadableItem;", "downloadableItem", "", "download", "cancel", "", "ids", "pause", "resume", "", "contains", "Lcom/amazon/music/downloads/DownloadEventListener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "registerDownloadEventListener", "unregisterDownloadEventListener", "DMMDownloadManager_release"}, k = 1, mv = {1, 6, 0})
public interface Downloader {
    void cancel(String id);

    void cancel(Collection<String> ids);

    boolean contains(String id);

    void download(String id, DownloadableItem downloadableItem);

    void pause(String id);

    void pause(Collection<String> ids);

    void registerDownloadEventListener(DownloadEventListener listener);

    void resume(String id);

    void unregisterDownloadEventListener(DownloadEventListener listener);
}
