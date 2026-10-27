package com.android.settingslib.fuelgauge;

import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings;
import java.time.Duration;
import java.time.Instant;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Estimate.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u000b¨\u0006\r"}, d2 = {"Lcom/android/settingslib/fuelgauge/Estimate;", "", "estimateMillis", "", "isBasedOnUsage", "", "averageDischargeTime", "(JZJ)V", "getAverageDischargeTime", "()J", "getEstimateMillis", "()Z", "Companion", "frameworks__base__packages__SettingsLib__android_common__SettingsLib"}, k = 1, mv = {1, 1, 16})
public final class Estimate {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final long averageDischargeTime;
    private final long estimateMillis;
    private final boolean isBasedOnUsage;

    @JvmStatic
    public static final Estimate getCachedEstimateIfAvailable(Context context) {
        return INSTANCE.getCachedEstimateIfAvailable(context);
    }

    @JvmStatic
    public static final Instant getLastCacheUpdateTime(Context context) {
        return INSTANCE.getLastCacheUpdateTime(context);
    }

    @JvmStatic
    public static final void storeCachedEstimate(Context context, Estimate estimate) {
        INSTANCE.storeCachedEstimate(context, estimate);
    }

    public Estimate(long estimateMillis, boolean isBasedOnUsage, long averageDischargeTime) {
        this.estimateMillis = estimateMillis;
        this.isBasedOnUsage = isBasedOnUsage;
        this.averageDischargeTime = averageDischargeTime;
    }

    public final long getEstimateMillis() {
        return this.estimateMillis;
    }

    /* JADX INFO: renamed from: isBasedOnUsage, reason: from getter */
    public final boolean getIsBasedOnUsage() {
        return this.isBasedOnUsage;
    }

    public final long getAverageDischargeTime() {
        return this.averageDischargeTime;
    }

    /* JADX INFO: compiled from: Estimate.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0004H\u0007¨\u0006\f"}, d2 = {"Lcom/android/settingslib/fuelgauge/Estimate$Companion;", "", "()V", "getCachedEstimateIfAvailable", "Lcom/android/settingslib/fuelgauge/Estimate;", "context", "Landroid/content/Context;", "getLastCacheUpdateTime", "Ljava/time/Instant;", "storeCachedEstimate", "", "estimate", "frameworks__base__packages__SettingsLib__android_common__SettingsLib"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        @JvmStatic
        public final Estimate getCachedEstimateIfAvailable(Context context) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            ContentResolver resolver = context.getContentResolver();
            Instant lastUpdateTime = Instant.ofEpochMilli(Settings.Global.getLong(resolver, "battery_estimates_last_update_time", -1L));
            if (Duration.between(lastUpdateTime, Instant.now()).compareTo(Duration.ofMinutes(1L)) > 0) {
                return null;
            }
            long j = -1;
            return new Estimate(Settings.Global.getLong(resolver, "time_remaining_estimate_millis", j), Settings.Global.getInt(resolver, "time_remaining_estimate_based_on_usage", 0) == 1, Settings.Global.getLong(resolver, "average_time_to_discharge", j));
        }

        @JvmStatic
        public final void storeCachedEstimate(Context context, Estimate estimate) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intrinsics.checkParameterIsNotNull(estimate, "estimate");
            ContentResolver contentResolver = context.getContentResolver();
            Settings.Global.putLong(contentResolver, "time_remaining_estimate_millis", estimate.getEstimateMillis());
            Settings.Global.putInt(contentResolver, "time_remaining_estimate_based_on_usage", estimate.getIsBasedOnUsage() ? 1 : 0);
            Settings.Global.putLong(contentResolver, "average_time_to_discharge", estimate.getAverageDischargeTime());
            Settings.Global.putLong(contentResolver, "battery_estimates_last_update_time", System.currentTimeMillis());
        }

        @JvmStatic
        public final Instant getLastCacheUpdateTime(Context context) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Instant instantOfEpochMilli = Instant.ofEpochMilli(Settings.Global.getLong(context.getContentResolver(), "battery_estimates_last_update_time", -1L));
            Intrinsics.checkExpressionValueIsNotNull(instantOfEpochMilli, "Instant.ofEpochMilli(\n  …                     -1))");
            return instantOfEpochMilli;
        }
    }
}
