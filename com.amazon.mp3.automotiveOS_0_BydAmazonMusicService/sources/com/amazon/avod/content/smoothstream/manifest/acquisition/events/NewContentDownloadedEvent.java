package com.amazon.avod.content.smoothstream.manifest.acquisition.events;

import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.PlayableContent;
import com.amazon.avod.content.event.ContentEventBase;
import com.amazon.avod.content.urlvending.ContentUrl;
import com.amazon.avod.media.downloadservice.DownloadStatistics;
import com.amazon.avod.media.playback.ContentType;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class NewContentDownloadedEvent extends ContentEventBase {
    private final ContentType mContentType;
    private final ContentUrl mContentUrl;
    private final DownloadStatistics mDownloadStatistics;

    public NewContentDownloadedEvent(@Nonnull PlayableContent playableContent, @Nonnull ContentSessionType contentSessionType, @Nonnull ContentUrl contentUrl, @Nonnull DownloadStatistics downloadStatistics, @Nonnull ContentType contentType) {
        super(playableContent, contentSessionType);
        this.mContentUrl = (ContentUrl) Preconditions.checkNotNull(contentUrl);
        this.mDownloadStatistics = (DownloadStatistics) Preconditions.checkNotNull(downloadStatistics);
        this.mContentType = (ContentType) Preconditions.checkNotNull(contentType, "contentType");
    }

    public ContentUrl getContentUrl() {
        return this.mContentUrl;
    }

    public DownloadStatistics getDownloadStatistics() {
        return this.mDownloadStatistics;
    }

    @Nonnull
    public ContentType getContentType() {
        return this.mContentType;
    }
}
