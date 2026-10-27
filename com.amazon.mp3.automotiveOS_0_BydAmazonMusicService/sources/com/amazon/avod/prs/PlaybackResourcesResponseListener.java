package com.amazon.avod.prs;

import com.amazon.avod.media.downloadservice.DownloadStatistics;
import com.amazon.bolthttp.BoltException;
import com.amazon.bolthttp.Request;
import com.amazon.bolthttp.Response;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface PlaybackResourcesResponseListener<T> {
    void onHTTPFailure(@Nonnull Request<T> request, @Nonnull BoltException boltException, @Nonnull DownloadStatistics downloadStatistics);

    void onHTTPSuccess(@Nonnull Request<T> request, @Nonnull Response<T> response, @Nonnull DownloadStatistics downloadStatistics);
}
