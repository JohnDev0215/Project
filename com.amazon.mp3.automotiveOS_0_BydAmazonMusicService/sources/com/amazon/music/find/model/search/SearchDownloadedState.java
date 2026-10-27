package com.amazon.music.find.model.search;

import kotlin.Metadata;

/* JADX INFO: compiled from: SearchEnums.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\f\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000e¨\u0006\u000f"}, d2 = {"Lcom/amazon/music/find/model/search/SearchDownloadedState;", "", "value", "", "(Ljava/lang/String;II)V", "getValue", "()I", "DOWNLOADED", "QUEUED_FOR_DOWNLOAD", "ERROR_DOWNLOADING", "DOWNLOADING_PAUSED", "DOWNLOADING", "NOT_DOWNLOADED", "PRE_WIDEVINE_REQUIRES_REDOWNLOADING", "DOWNLOAD_CORRUPTED", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum SearchDownloadedState {
    DOWNLOADED(0),
    QUEUED_FOR_DOWNLOAD(1),
    ERROR_DOWNLOADING(2),
    DOWNLOADING_PAUSED(3),
    DOWNLOADING(4),
    NOT_DOWNLOADED(5),
    PRE_WIDEVINE_REQUIRES_REDOWNLOADING(6),
    DOWNLOAD_CORRUPTED(7);

    private final int value;

    SearchDownloadedState(int i) {
        this.value = i;
    }

    public final int getValue() {
        return this.value;
    }
}
