package com.amazon.mp3.cloudqueue;

import androidx.annotation.NonNull;
import com.amazon.hermes.UnhandledError;
import com.amazon.mp3.cloudqueue.model.ResponseStatus;
import com.amazon.musicplayqueueservice.client.common.NoCleanLanguageVersionException;
import com.android.volley.NetworkError;
import com.android.volley.TimeoutError;

/* JADX INFO: loaded from: classes3.dex */
public final class CloudQueueNoNextException extends Exception {
    private Reason mReason;

    public enum Reason {
        DEVICE_OFFLINE,
        CONCURRENT_STREAM_LIMIT,
        THROTTLING_LIMIT_REACHED,
        UNKNOWN_FAILURE,
        QUEUE_TERMINATED,
        PROCESSING_ERROR,
        NETWORK_ERROR,
        CLEAN_CONTENT_UNAVAILABLE,
        REACH_END_OF_QUEUE,
        INVALID_STATION_SEED
    }

    CloudQueueNoNextException(final Exception exception) {
        if ((exception instanceof NetworkError) || (exception instanceof TimeoutError)) {
            this.mReason = Reason.NETWORK_ERROR;
            return;
        }
        if (exception instanceof NoCleanLanguageVersionException) {
            this.mReason = Reason.CLEAN_CONTENT_UNAVAILABLE;
        } else if (exception instanceof UnhandledError) {
            this.mReason = Reason.PROCESSING_ERROR;
        } else {
            this.mReason = Reason.PROCESSING_ERROR;
        }
    }

    CloudQueueNoNextException(@NonNull final ResponseStatus responseStatus) {
        this.mReason = Reason.valueOf(responseStatus.name());
    }

    CloudQueueNoNextException(@NonNull final Reason reason) {
        this.mReason = reason;
    }

    public Reason getReason() {
        return this.mReason;
    }
}
