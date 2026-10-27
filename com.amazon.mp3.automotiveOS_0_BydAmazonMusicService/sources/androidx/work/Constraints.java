package androidx.work;

import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.room.ColumnInfo;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public final class Constraints {
    public static final Constraints NONE = new Builder().build();

    @ColumnInfo(name = "content_uri_triggers")
    private ContentUriTriggers mContentUriTriggers;

    @ColumnInfo(name = "required_network_type")
    private NetworkType mRequiredNetworkType;

    @ColumnInfo(name = "requires_battery_not_low")
    private boolean mRequiresBatteryNotLow;

    @ColumnInfo(name = "requires_charging")
    private boolean mRequiresCharging;

    @ColumnInfo(name = "requires_device_idle")
    private boolean mRequiresDeviceIdle;

    @ColumnInfo(name = "requires_storage_not_low")
    private boolean mRequiresStorageNotLow;

    @ColumnInfo(name = "trigger_content_update_delay")
    private long mTriggerContentUpdateDelay;

    @ColumnInfo(name = "trigger_max_content_delay")
    private long mTriggerMaxContentDelay;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public Constraints() {
        this.mRequiredNetworkType = NetworkType.NOT_REQUIRED;
        this.mTriggerContentUpdateDelay = -1L;
        this.mTriggerMaxContentDelay = -1L;
        this.mContentUriTriggers = new ContentUriTriggers();
    }

    Constraints(Builder builder) {
        this.mRequiredNetworkType = NetworkType.NOT_REQUIRED;
        this.mTriggerContentUpdateDelay = -1L;
        this.mTriggerMaxContentDelay = -1L;
        this.mContentUriTriggers = new ContentUriTriggers();
        this.mRequiresCharging = builder.mRequiresCharging;
        this.mRequiresDeviceIdle = builder.mRequiresDeviceIdle;
        this.mRequiredNetworkType = builder.mRequiredNetworkType;
        this.mRequiresBatteryNotLow = builder.mRequiresBatteryNotLow;
        this.mRequiresStorageNotLow = builder.mRequiresStorageNotLow;
        this.mContentUriTriggers = builder.mContentUriTriggers;
        this.mTriggerContentUpdateDelay = builder.mTriggerContentUpdateDelay;
        this.mTriggerMaxContentDelay = builder.mTriggerContentMaxDelay;
    }

    public Constraints(@NonNull Constraints other) {
        this.mRequiredNetworkType = NetworkType.NOT_REQUIRED;
        this.mTriggerContentUpdateDelay = -1L;
        this.mTriggerMaxContentDelay = -1L;
        this.mContentUriTriggers = new ContentUriTriggers();
        this.mRequiresCharging = other.mRequiresCharging;
        this.mRequiresDeviceIdle = other.mRequiresDeviceIdle;
        this.mRequiredNetworkType = other.mRequiredNetworkType;
        this.mRequiresBatteryNotLow = other.mRequiresBatteryNotLow;
        this.mRequiresStorageNotLow = other.mRequiresStorageNotLow;
        this.mContentUriTriggers = other.mContentUriTriggers;
    }

    @NonNull
    public NetworkType getRequiredNetworkType() {
        return this.mRequiredNetworkType;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setRequiredNetworkType(@NonNull NetworkType requiredNetworkType) {
        this.mRequiredNetworkType = requiredNetworkType;
    }

    public boolean requiresCharging() {
        return this.mRequiresCharging;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setRequiresCharging(boolean requiresCharging) {
        this.mRequiresCharging = requiresCharging;
    }

    @RequiresApi(23)
    public boolean requiresDeviceIdle() {
        return this.mRequiresDeviceIdle;
    }

    @RequiresApi(23)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setRequiresDeviceIdle(boolean requiresDeviceIdle) {
        this.mRequiresDeviceIdle = requiresDeviceIdle;
    }

    public boolean requiresBatteryNotLow() {
        return this.mRequiresBatteryNotLow;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setRequiresBatteryNotLow(boolean requiresBatteryNotLow) {
        this.mRequiresBatteryNotLow = requiresBatteryNotLow;
    }

    public boolean requiresStorageNotLow() {
        return this.mRequiresStorageNotLow;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setRequiresStorageNotLow(boolean requiresStorageNotLow) {
        this.mRequiresStorageNotLow = requiresStorageNotLow;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public long getTriggerContentUpdateDelay() {
        return this.mTriggerContentUpdateDelay;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setTriggerContentUpdateDelay(long triggerContentUpdateDelay) {
        this.mTriggerContentUpdateDelay = triggerContentUpdateDelay;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public long getTriggerMaxContentDelay() {
        return this.mTriggerMaxContentDelay;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setTriggerMaxContentDelay(long triggerMaxContentDelay) {
        this.mTriggerMaxContentDelay = triggerMaxContentDelay;
    }

    @RequiresApi(24)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setContentUriTriggers(@Nullable ContentUriTriggers mContentUriTriggers) {
        this.mContentUriTriggers = mContentUriTriggers;
    }

    @NonNull
    @RequiresApi(24)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public ContentUriTriggers getContentUriTriggers() {
        return this.mContentUriTriggers;
    }

    @RequiresApi(24)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean hasContentUriTriggers() {
        return this.mContentUriTriggers.size() > 0;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Constraints.class != o.getClass()) {
            return false;
        }
        Constraints constraints = (Constraints) o;
        if (this.mRequiresCharging == constraints.mRequiresCharging && this.mRequiresDeviceIdle == constraints.mRequiresDeviceIdle && this.mRequiresBatteryNotLow == constraints.mRequiresBatteryNotLow && this.mRequiresStorageNotLow == constraints.mRequiresStorageNotLow && this.mTriggerContentUpdateDelay == constraints.mTriggerContentUpdateDelay && this.mTriggerMaxContentDelay == constraints.mTriggerMaxContentDelay && this.mRequiredNetworkType == constraints.mRequiredNetworkType) {
            return this.mContentUriTriggers.equals(constraints.mContentUriTriggers);
        }
        return false;
    }

    public int hashCode() {
        int iHashCode = ((((((((this.mRequiredNetworkType.hashCode() * 31) + (this.mRequiresCharging ? 1 : 0)) * 31) + (this.mRequiresDeviceIdle ? 1 : 0)) * 31) + (this.mRequiresBatteryNotLow ? 1 : 0)) * 31) + (this.mRequiresStorageNotLow ? 1 : 0)) * 31;
        long j = this.mTriggerContentUpdateDelay;
        int i = (iHashCode + ((int) (j ^ (j >>> 32)))) * 31;
        long j2 = this.mTriggerMaxContentDelay;
        return ((i + ((int) (j2 ^ (j2 >>> 32)))) * 31) + this.mContentUriTriggers.hashCode();
    }

    public static final class Builder {
        ContentUriTriggers mContentUriTriggers;
        NetworkType mRequiredNetworkType;
        boolean mRequiresBatteryNotLow;
        boolean mRequiresCharging;
        boolean mRequiresDeviceIdle;
        boolean mRequiresStorageNotLow;
        long mTriggerContentMaxDelay;
        long mTriggerContentUpdateDelay;

        public Builder() {
            this.mRequiresCharging = false;
            this.mRequiresDeviceIdle = false;
            this.mRequiredNetworkType = NetworkType.NOT_REQUIRED;
            this.mRequiresBatteryNotLow = false;
            this.mRequiresStorageNotLow = false;
            this.mTriggerContentUpdateDelay = -1L;
            this.mTriggerContentMaxDelay = -1L;
            this.mContentUriTriggers = new ContentUriTriggers();
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Builder(@NonNull Constraints constraints) {
            this.mRequiresCharging = false;
            this.mRequiresDeviceIdle = false;
            this.mRequiredNetworkType = NetworkType.NOT_REQUIRED;
            this.mRequiresBatteryNotLow = false;
            this.mRequiresStorageNotLow = false;
            this.mTriggerContentUpdateDelay = -1L;
            this.mTriggerContentMaxDelay = -1L;
            this.mContentUriTriggers = new ContentUriTriggers();
            this.mRequiresCharging = constraints.requiresCharging();
            this.mRequiresDeviceIdle = constraints.requiresDeviceIdle();
            this.mRequiredNetworkType = constraints.getRequiredNetworkType();
            this.mRequiresBatteryNotLow = constraints.requiresBatteryNotLow();
            this.mRequiresStorageNotLow = constraints.requiresStorageNotLow();
            this.mTriggerContentUpdateDelay = constraints.getTriggerContentUpdateDelay();
            this.mTriggerContentMaxDelay = constraints.getTriggerMaxContentDelay();
            this.mContentUriTriggers = constraints.getContentUriTriggers();
        }

        @NonNull
        public Builder setRequiresCharging(boolean requiresCharging) {
            this.mRequiresCharging = requiresCharging;
            return this;
        }

        @NonNull
        @RequiresApi(23)
        public Builder setRequiresDeviceIdle(boolean requiresDeviceIdle) {
            this.mRequiresDeviceIdle = requiresDeviceIdle;
            return this;
        }

        @NonNull
        public Builder setRequiredNetworkType(@NonNull NetworkType networkType) {
            this.mRequiredNetworkType = networkType;
            return this;
        }

        @NonNull
        public Builder setRequiresBatteryNotLow(boolean requiresBatteryNotLow) {
            this.mRequiresBatteryNotLow = requiresBatteryNotLow;
            return this;
        }

        @NonNull
        public Builder setRequiresStorageNotLow(boolean requiresStorageNotLow) {
            this.mRequiresStorageNotLow = requiresStorageNotLow;
            return this;
        }

        @NonNull
        @RequiresApi(24)
        public Builder addContentUriTrigger(@NonNull Uri uri, boolean triggerForDescendants) {
            this.mContentUriTriggers.add(uri, triggerForDescendants);
            return this;
        }

        @NonNull
        @RequiresApi(24)
        public Builder setTriggerContentUpdateDelay(long duration, @NonNull TimeUnit timeUnit) {
            this.mTriggerContentUpdateDelay = timeUnit.toMillis(duration);
            return this;
        }

        @NonNull
        @RequiresApi(26)
        public Builder setTriggerContentUpdateDelay(Duration duration) {
            this.mTriggerContentUpdateDelay = duration.toMillis();
            return this;
        }

        @NonNull
        @RequiresApi(24)
        public Builder setTriggerContentMaxDelay(long duration, @NonNull TimeUnit timeUnit) {
            this.mTriggerContentMaxDelay = timeUnit.toMillis(duration);
            return this;
        }

        @NonNull
        @RequiresApi(26)
        public Builder setTriggerContentMaxDelay(Duration duration) {
            this.mTriggerContentMaxDelay = duration.toMillis();
            return this;
        }

        @NonNull
        public Constraints build() {
            return new Constraints(this);
        }
    }
}
