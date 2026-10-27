package com.amazon.avod.content.smoothstream.downloading;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.media.downloadservice.DownloadStatistics;
import com.amazon.avod.media.downloadservice.exceptions.InvalidCDNResponseException;
import com.amazon.avod.media.downloadservice.exceptions.NetworkIOException;
import com.amazon.avod.media.downloadservice.exceptions.NetworkResponseException;
import com.amazon.avod.media.downloadservice.exceptions.NoDataConnectionException;
import com.amazon.avod.media.framework.retry.RetryStopException;
import com.amazon.avod.util.CastUtils;
import com.amazon.avod.util.URLUtils;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class SmoothStreamingDownloadExceptionUtils {
    private static final Map<Class<? extends Exception>, ContentException.ContentError> RECOGNIZED_EXCEPTIONS;

    static {
        HashMap mapNewHashMap = Maps.newHashMap();
        RECOGNIZED_EXCEPTIONS = mapNewHashMap;
        mapNewHashMap.put(NoDataConnectionException.class, ContentException.ContentError.DATA_CONNECTION_UNAVAILABLE_ERROR);
        ContentException.ContentError contentError = ContentException.ContentError.NETWORK_ERROR;
        mapNewHashMap.put(NetworkIOException.class, contentError);
        mapNewHashMap.put(NetworkResponseException.class, ContentException.ContentError.CDN_ERROR);
        mapNewHashMap.put(InvalidCDNResponseException.class, ContentException.ContentError.INVALID_CONTENT_LENGTH);
        mapNewHashMap.put(TimeoutException.class, contentError);
    }

    public ContentException translateExceptionForManifest(@Nonnull Exception exc, @Nullable String str, @Nullable DownloadStatistics downloadStatistics) {
        return translateException(exc, getPrefixedMessage(exc, str, "Manifest: "), downloadStatistics);
    }

    public ContentException translateExceptionForFragment(@Nonnull Exception exc, @Nullable String str, @Nullable DownloadStatistics downloadStatistics) {
        return translateException(exc, getPrefixedMessage(exc, str, "Fragment: "), downloadStatistics);
    }

    private ContentException translateException(@Nonnull Exception exc, @Nonnull String str, @Nullable DownloadStatistics downloadStatistics) {
        Preconditions.checkNotNull(exc, "thrownException parameter cannot be null.");
        Preconditions.checkNotNull(str, "prefixedMessage parameter cannot be null.");
        ContentException contentException = (ContentException) CastUtils.castTo(exc, ContentException.class);
        URL urlNullWhenMalformed = URLUtils.nullWhenMalformed(downloadStatistics != null ? downloadStatistics.getURL() : null);
        if (contentException != null) {
            return contentException;
        }
        if (((RetryStopException) CastUtils.castTo(exc, RetryStopException.class)) != null) {
            return new ContentException(ContentException.ContentError.RETRY_STOP, str, null, urlNullWhenMalformed);
        }
        if (exc instanceof NetworkResponseException) {
            return new ContentException(ContentException.ContentError.CDN_ERROR, str, exc.getCause(), null, ((NetworkResponseException) exc).getResponseSource(), ((NetworkResponseException) exc).getResponseCode(), downloadStatistics);
        }
        if (exc instanceof TimeoutException) {
            return new ContentException(ContentException.ContentError.CDN_ERROR, str, exc.getCause(), null, null, -2, downloadStatistics);
        }
        for (Map.Entry<Class<? extends Exception>, ContentException.ContentError> entry : RECOGNIZED_EXCEPTIONS.entrySet()) {
            if (exc.getClass().isAssignableFrom(entry.getKey())) {
                return new ContentException(entry.getValue(), str, exc.getCause(), urlNullWhenMalformed);
            }
        }
        return new ContentException(ContentException.ContentError.UNKNOWN_ERROR, str, exc, urlNullWhenMalformed);
    }

    private String getPrefixedMessage(@Nonnull Exception exc, @Nullable String str, @Nonnull String str2) {
        Preconditions.checkNotNull(exc, "thrownException parameter cannot be null.");
        Preconditions.checkNotNull(str2, "messagePrefix parameter cannot be null.");
        StringBuilder sb = new StringBuilder();
        sb.append(str2);
        if (str == null) {
            sb.append((String) MoreObjects.firstNonNull(exc.getMessage(), ""));
        } else {
            sb.append(str);
        }
        return sb.toString();
    }
}
