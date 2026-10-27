package com.amazon.avod.content.urlvending;

import android.net.Uri;
import com.amazon.avod.content.ContentException;
import com.amazon.avod.media.downloadservice.DownloadStatistics;
import com.amazon.avod.media.framework.retry.RetriableCall;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface ContentUrlSelector {
    @Nonnull
    ContentUrl getCurrentContentUrl();

    void manuallyTriggerFailover(@Nonnull FailoverType failoverType, @Nullable FailoverMode failoverMode, long j);

    RetriableCall.RetryCode notifyDownloadFailure(@Nonnull ContentException contentException, @Nonnull ContentUrl contentUrl, int i, long j, boolean z, @Nullable String str, @Nullable DownloadStatistics downloadStatistics, @Nonnull DownloadType downloadType);

    boolean notifyDownloadRedirect(@Nonnull ContentUrl contentUrl, @Nonnull Uri uri);

    void notifyDownloadSuccess(@Nullable DownloadStatistics downloadStatistics);

    void notifyQoeDecrease(@Nonnull ContentUrl contentUrl, long j);

    void setContentUrlSwitchingPolicy(@Nonnull ContentUrlSwitchingPolicy contentUrlSwitchingPolicy);
}
