package com.amazon.bolthttp;

import com.amazon.avod.media.downloadservice.DownloadStatistics;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class Response<T> {

    @Nonnull
    private final DownloadStatistics mDownloadStatistics;
    private final BoltException mException;
    private final Request<T> mRequest;
    private final T mValue;

    public static <T> Response<T> create(@Nonnull Request<T> request, @Nullable T t, @Nonnull DownloadStatistics downloadStatistics) {
        return new Response<>(request, t, null, downloadStatistics);
    }

    public static <T> Response<T> create(@Nonnull Request<T> request, @Nonnull BoltException boltException, @Nonnull DownloadStatistics downloadStatistics) {
        return new Response<>(request, null, (BoltException) Preconditions.checkNotNull(boltException, "exception"), downloadStatistics);
    }

    private Response(@Nonnull Request<T> request, @Nullable T t, @Nullable BoltException boltException, @Nonnull DownloadStatistics downloadStatistics) {
        this.mRequest = (Request) Preconditions.checkNotNull(request, "request");
        this.mValue = t;
        this.mException = boltException;
        this.mDownloadStatistics = (DownloadStatistics) Preconditions.checkNotNull(downloadStatistics, "downloadStatistics");
    }

    @Nonnull
    public Request<T> getRequest() {
        return this.mRequest;
    }

    @Nullable
    public T getValue() {
        return this.mValue;
    }

    @Nonnull
    public DownloadStatistics getDownloadStatistics() {
        return this.mDownloadStatistics;
    }

    @Nullable
    public BoltException getException() {
        return this.mException;
    }

    public boolean hasException() {
        return this.mException != null;
    }

    public String toString() {
        return String.format("Response[value=%s, exception=%s]", this.mValue, this.mException);
    }
}
