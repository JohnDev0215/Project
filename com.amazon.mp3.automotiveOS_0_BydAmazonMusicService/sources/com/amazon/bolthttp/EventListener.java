package com.amazon.bolthttp;

import com.amazon.avod.media.downloadservice.DownloadStatistics;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public abstract class EventListener {
    public static final EventListener NO_OP_LISTENER = new EventListener() { // from class: com.amazon.bolthttp.EventListener.1
    };

    public void onExecutionCompleteEvent(@Nonnull ExecutionCompleteEvent executionCompleteEvent, @Nonnull Request<?> request) {
    }

    public void onExecutionStart(@Nonnull Request<?> request) {
    }

    public void onNetworkConnectionEvent(@Nonnull NetworkConnectionEvent networkConnectionEvent, @Nonnull Request<?> request) {
    }

    public void onNetworkEvent(@Nonnull NetworkEvent networkEvent, @Nonnull Request<?> request) {
    }

    public void onResponseHandlerEvent(@Nonnull ResponseHandlerEvent responseHandlerEvent, @Nonnull Request<?> request) {
    }

    public static final class NetworkEvent {
        private final int mAttemptCount;
        private final long mAttemptDuration;
        private final Exception mException;
        private final State mState;

        public enum State {
            SUCCESS,
            FAILURE
        }

        public NetworkEvent(@Nonnull State state, int i, long j, @Nullable Exception exc) {
            this.mState = (State) Preconditions.checkNotNull(state, "state");
            this.mAttemptCount = i;
            this.mAttemptDuration = j;
            this.mException = exc;
        }

        @Nonnull
        public State getState() {
            return this.mState;
        }

        public int getAttemptCount() {
            return this.mAttemptCount;
        }

        public long getAttemptDurationMillis() {
            return this.mAttemptDuration;
        }

        @Nullable
        public Exception getException() {
            return this.mException;
        }
    }

    public static final class NetworkConnectionEvent {
        private final long mAttemptDurationMillis;
        private final Long mByteCount;
        private final State mState;

        public enum State {
            DNS_LOOKUP_ONLY,
            CONNECTION_SETUP_ONLY,
            REQUEST_SEND_ONLY,
            RESPONSE_FIRST_BYTE,
            RESPONSE_LAST_BYTE
        }

        public NetworkConnectionEvent(@Nonnull State state, @Nonnegative long j, @Nullable Long l) {
            if (j < 0) {
                throw new IllegalArgumentException("attemptDuration < 0");
            }
            this.mState = (State) Preconditions.checkNotNull(state, "state");
            this.mAttemptDurationMillis = j;
            this.mByteCount = l;
        }

        @Nullable
        public Long getByteCount() {
            return this.mByteCount;
        }

        @Nonnull
        public State getState() {
            return this.mState;
        }

        @Nonnegative
        public long getAttemptDurationMillis() {
            return this.mAttemptDurationMillis;
        }
    }

    public static final class ResponseHandlerEvent {
        private final int mAttemptCount;
        private final long mAttemptDuration;
        private final Exception mException;
        private final State mState;

        public enum State {
            SUCCESS,
            FAILURE
        }

        public ResponseHandlerEvent(@Nonnull State state, int i, long j, Exception exc) {
            this.mState = (State) Preconditions.checkNotNull(state, "state");
            this.mAttemptCount = i;
            this.mAttemptDuration = j;
            this.mException = exc;
        }

        @Nonnull
        public State getState() {
            return this.mState;
        }

        public int getAttemptCount() {
            return this.mAttemptCount;
        }

        public long getAttemptDurationMillis() {
            return this.mAttemptDuration;
        }

        @Nullable
        public Exception getException() {
            return this.mException;
        }
    }

    public static final class ExecutionCompleteEvent {

        @Nonnull
        private final DownloadStatistics mDownloadStatistics;
        private final BoltException mException;
        private final long mExecutionDuration;
        private final State mState;

        public enum State {
            SUCCESS,
            FAILURE
        }

        public ExecutionCompleteEvent(@Nonnull State state, long j, @Nullable BoltException boltException, @Nonnull DownloadStatistics downloadStatistics) {
            this.mState = (State) Preconditions.checkNotNull(state, "state");
            this.mExecutionDuration = j;
            this.mException = boltException;
            this.mDownloadStatistics = (DownloadStatistics) Preconditions.checkNotNull(downloadStatistics, "downloadStatistics");
        }

        @Nonnull
        public State getState() {
            return this.mState;
        }

        public long getExecutionDurationMillis() {
            return this.mExecutionDuration;
        }

        @Nonnull
        public DownloadStatistics getDownloadStatistics() {
            return this.mDownloadStatistics;
        }
    }
}
