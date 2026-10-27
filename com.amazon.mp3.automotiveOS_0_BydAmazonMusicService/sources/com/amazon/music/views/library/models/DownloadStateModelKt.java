package com.amazon.music.views.library.models;

import kotlin.Metadata;

/* JADX INFO: compiled from: DownloadStateModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\b\n\u0002\b\u0002\u001a\u0011\u0010\u0000\u001a\u00020\u0001*\u0004\u0018\u00010\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"isDownloaded", "", "", "(Ljava/lang/Integer;)Z", "DMMViewLibrary_release"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class DownloadStateModelKt {
    public static final boolean isDownloaded(Integer num) {
        return num != null && num.intValue() == 0;
    }
}
