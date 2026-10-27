package com.amazon.music.views.library.utils;

import kotlin.Metadata;

/* JADX INFO: compiled from: MediaUtil.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/music/views/library/utils/MediaUtil;", "", "()V", "isPodcastEntity", "", "contentType", "", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MediaUtil {
    public static final MediaUtil INSTANCE = new MediaUtil();

    public final boolean isPodcastEntity(int contentType) {
        return contentType == 11 || contentType == 12;
    }

    private MediaUtil() {
    }
}
