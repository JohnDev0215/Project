package com.amazon.digitalmusicxp.types;

import com.amazon.digitalmusicxp.ExpectedFunctionsKt;
import com.amazon.digitalmusicxp.enums.StationSeedTypeEnum;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StationSeed.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000e\u001a\u00020\u0002¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/amazon/digitalmusicxp/types/StationSeed;", "", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/amazon/digitalmusicxp/enums/StationSeedTypeEnum;", "seedType", "Lcom/amazon/digitalmusicxp/enums/StationSeedTypeEnum;", "getSeedType", "()Lcom/amazon/digitalmusicxp/enums/StationSeedTypeEnum;", "seedId", "Ljava/lang/String;", "getSeedId", "()Ljava/lang/String;", "<init>", "(Lcom/amazon/digitalmusicxp/enums/StationSeedTypeEnum;Ljava/lang/String;)V", "DigitalMusicCloudQueueXP_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class StationSeed {
    private final String seedId;
    private final StationSeedTypeEnum seedType;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof StationSeed)) {
            return false;
        }
        StationSeed stationSeed = (StationSeed) other;
        return this.seedType == stationSeed.seedType && Intrinsics.areEqual(this.seedId, stationSeed.seedId);
    }

    public int hashCode() {
        return (this.seedType.hashCode() * 31) + this.seedId.hashCode();
    }

    public String toString() {
        return "StationSeed(seedType=" + this.seedType + ", seedId=" + this.seedId + ')';
    }

    public StationSeed(StationSeedTypeEnum seedType, String seedId) {
        Intrinsics.checkNotNullParameter(seedType, "seedType");
        Intrinsics.checkNotNullParameter(seedId, "seedId");
        this.seedType = seedType;
        this.seedId = seedId;
        ExpectedFunctionsKt.freeze(this);
    }

    public final StationSeedTypeEnum getSeedType() {
        return this.seedType;
    }

    public final String getSeedId() {
        return this.seedId;
    }
}
