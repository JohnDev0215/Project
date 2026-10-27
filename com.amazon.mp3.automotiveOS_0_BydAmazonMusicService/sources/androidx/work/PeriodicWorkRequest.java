package androidx.work;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.work.WorkRequest;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public final class PeriodicWorkRequest extends WorkRequest {

    @SuppressLint({"MinMaxConstant"})
    public static final long MIN_PERIODIC_FLEX_MILLIS = 300000;

    @SuppressLint({"MinMaxConstant"})
    public static final long MIN_PERIODIC_INTERVAL_MILLIS = 900000;

    PeriodicWorkRequest(Builder builder) {
        super(builder.mId, builder.mWorkSpec, builder.mTags);
    }

    public static final class Builder extends WorkRequest.Builder<Builder, PeriodicWorkRequest> {
        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // androidx.work.WorkRequest.Builder
        @NonNull
        public Builder getThis() {
            return this;
        }

        public Builder(@NonNull Class<? extends ListenableWorker> workerClass, long repeatInterval, @NonNull TimeUnit repeatIntervalTimeUnit) {
            super(workerClass);
            this.mWorkSpec.setPeriodic(repeatIntervalTimeUnit.toMillis(repeatInterval));
        }

        @RequiresApi(26)
        public Builder(@NonNull Class<? extends ListenableWorker> workerClass, @NonNull Duration repeatInterval) {
            super(workerClass);
            this.mWorkSpec.setPeriodic(repeatInterval.toMillis());
        }

        public Builder(@NonNull Class<? extends ListenableWorker> workerClass, long repeatInterval, @NonNull TimeUnit repeatIntervalTimeUnit, long flexInterval, @NonNull TimeUnit flexIntervalTimeUnit) {
            super(workerClass);
            this.mWorkSpec.setPeriodic(repeatIntervalTimeUnit.toMillis(repeatInterval), flexIntervalTimeUnit.toMillis(flexInterval));
        }

        @RequiresApi(26)
        public Builder(@NonNull Class<? extends ListenableWorker> workerClass, @NonNull Duration repeatInterval, @NonNull Duration flexInterval) {
            super(workerClass);
            this.mWorkSpec.setPeriodic(repeatInterval.toMillis(), flexInterval.toMillis());
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // androidx.work.WorkRequest.Builder
        @NonNull
        public PeriodicWorkRequest buildInternal() {
            if (this.mBackoffCriteriaSet && this.mWorkSpec.constraints.requiresDeviceIdle()) {
                throw new IllegalArgumentException("Cannot set backoff criteria on an idle mode job");
            }
            if (this.mWorkSpec.expedited) {
                throw new IllegalArgumentException("PeriodicWorkRequests cannot be expedited");
            }
            return new PeriodicWorkRequest(this);
        }
    }
}
