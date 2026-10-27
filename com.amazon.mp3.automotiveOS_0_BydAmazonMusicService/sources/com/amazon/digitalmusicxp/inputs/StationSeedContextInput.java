package com.amazon.digitalmusicxp.inputs;

import com.amazon.digitalmusicxp.ExpectedFunctionsKt;
import com.amazon.digitalmusicxp.enums.StationSeedTypeEnum;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StationSeedContextInput.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000e\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0012¢\u0006\u0004\b\u0017\u0010\u0018J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u0019\u0010\u0013\u001a\u0004\u0018\u00010\u00128\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u0019"}, d2 = {"Lcom/amazon/digitalmusicxp/inputs/StationSeedContextInput;", "", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/amazon/digitalmusicxp/enums/StationSeedTypeEnum;", "seedType", "Lcom/amazon/digitalmusicxp/enums/StationSeedTypeEnum;", "getSeedType", "()Lcom/amazon/digitalmusicxp/enums/StationSeedTypeEnum;", "seedId", "Ljava/lang/String;", "getSeedId", "()Ljava/lang/String;", "Lcom/amazon/digitalmusicxp/inputs/MetricsContextInput;", "metricsContext", "Lcom/amazon/digitalmusicxp/inputs/MetricsContextInput;", "getMetricsContext", "()Lcom/amazon/digitalmusicxp/inputs/MetricsContextInput;", "<init>", "(Lcom/amazon/digitalmusicxp/enums/StationSeedTypeEnum;Ljava/lang/String;Lcom/amazon/digitalmusicxp/inputs/MetricsContextInput;)V", "DigitalMusicCloudQueueXP_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class StationSeedContextInput {
    private final MetricsContextInput metricsContext;
    private final String seedId;
    private final StationSeedTypeEnum seedType;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof StationSeedContextInput)) {
            return false;
        }
        StationSeedContextInput stationSeedContextInput = (StationSeedContextInput) other;
        return this.seedType == stationSeedContextInput.seedType && Intrinsics.areEqual(this.seedId, stationSeedContextInput.seedId) && Intrinsics.areEqual(this.metricsContext, stationSeedContextInput.metricsContext);
    }

    public int hashCode() {
        int iHashCode = ((this.seedType.hashCode() * 31) + this.seedId.hashCode()) * 31;
        MetricsContextInput metricsContextInput = this.metricsContext;
        return iHashCode + (metricsContextInput == null ? 0 : metricsContextInput.hashCode());
    }

    public String toString() {
        return "StationSeedContextInput(seedType=" + this.seedType + ", seedId=" + this.seedId + ", metricsContext=" + this.metricsContext + ')';
    }

    public StationSeedContextInput(StationSeedTypeEnum seedType, String seedId, MetricsContextInput metricsContextInput) {
        Intrinsics.checkNotNullParameter(seedType, "seedType");
        Intrinsics.checkNotNullParameter(seedId, "seedId");
        this.seedType = seedType;
        this.seedId = seedId;
        this.metricsContext = metricsContextInput;
        ExpectedFunctionsKt.freeze(this);
    }

    public /* synthetic */ StationSeedContextInput(StationSeedTypeEnum stationSeedTypeEnum, String str, MetricsContextInput metricsContextInput, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(stationSeedTypeEnum, str, (i & 4) != 0 ? null : metricsContextInput);
    }

    public final StationSeedTypeEnum getSeedType() {
        return this.seedType;
    }

    public final String getSeedId() {
        return this.seedId;
    }
}
