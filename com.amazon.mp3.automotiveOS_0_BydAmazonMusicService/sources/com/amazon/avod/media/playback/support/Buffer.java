package com.amazon.avod.media.playback.support;

import com.google.common.base.Preconditions;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class Buffer {
    private final long mBytesAvailable;
    private final Long mBytesCapacity;
    private final long mTimeAvailable;
    private final Long mTimeCapacity;
    private final Type mType;

    public enum Position {
        Front,
        Back
    }

    public enum Type {
        Video,
        Audio
    }

    private Buffer(Builder builder) {
        this.mType = builder.mType;
        this.mTimeAvailable = builder.mTimeAvailable;
        this.mTimeCapacity = builder.mTimeCapacity;
        this.mBytesAvailable = builder.mBytesAvailable;
        this.mBytesCapacity = builder.mBytesCapacity;
    }

    @Nonnull
    public Type getType() {
        return this.mType;
    }

    public long getTimeAvailable() {
        return this.mTimeAvailable;
    }

    public Long getTimeCapacity() {
        return this.mTimeCapacity;
    }

    public long getBytesAvailable() {
        return this.mBytesAvailable;
    }

    public Long getBytesCapacity() {
        return this.mBytesCapacity;
    }

    public static class Builder {
        private long mBytesAvailable;
        private Long mBytesCapacity;
        private long mTimeAvailable;
        private Long mTimeCapacity;
        private Type mType;

        public Builder type(@Nonnull Type type) {
            this.mType = (Type) Preconditions.checkNotNull(type, "buffer type");
            return this;
        }

        public Builder timeAvailable(@Nonnegative long j) {
            Preconditions.checkArgument(j >= 0, "timeAvailable must be non negative");
            this.mTimeAvailable = j;
            return this;
        }

        public Builder timeCapacity(@Nonnegative long j) {
            Preconditions.checkArgument(j >= 0, "timeCapacity must be non negative");
            this.mTimeCapacity = j == 0 ? null : Long.valueOf(j);
            return this;
        }

        public Builder bytesAvailable(@Nonnegative long j) {
            Preconditions.checkArgument(j >= 0, "bytesAvailable must be non negative");
            this.mBytesAvailable = j;
            return this;
        }

        public Builder bytesCapacity(@Nonnegative long j) {
            Preconditions.checkArgument(j >= 0, "bytesCapacity must be non negative");
            this.mBytesCapacity = j == 0 ? null : Long.valueOf(j);
            return this;
        }

        public Buffer build() {
            return new Buffer(this);
        }
    }
}
