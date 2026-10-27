package com.amazon.avod.playbackclient.trickplay.download;

import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class BifContractException extends Exception {
    private final Reason mExceptionReason;
    private final long mExceptionValue;

    public enum Reason {
        MAGIC_NUMBER("Magic Number Corruption"),
        MEMORY_ALLOCATION("Memory Allocation"),
        BUFFER_UNDERFLOW("BufferUnderflow");

        private final String mReason;

        Reason(String str) {
            this.mReason = str;
        }

        @Nonnull
        public String getReason() {
            return this.mReason;
        }
    }

    public BifContractException(@Nonnull Reason reason, long j) {
        this.mExceptionReason = (Reason) Preconditions.checkNotNull(reason, "mExceptionValue");
        this.mExceptionValue = j;
    }

    @Override // java.lang.Throwable
    public String toString() {
        return this.mExceptionReason.getReason() + ":" + this.mExceptionValue;
    }
}
