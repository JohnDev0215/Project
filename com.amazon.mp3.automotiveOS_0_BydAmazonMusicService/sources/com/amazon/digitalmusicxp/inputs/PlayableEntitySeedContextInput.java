package com.amazon.digitalmusicxp.inputs;

import com.amazon.digitalmusicxp.ExpectedFunctionsKt;
import com.amazon.digitalmusicxp.enums.PlayableEntitySeedIdTypeEnum;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlayableEntitySeedContextInput.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0012¢\u0006\u0004\b\u0017\u0010\u0018J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u0019\u0010\u0013\u001a\u0004\u0018\u00010\u00128\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u0019"}, d2 = {"Lcom/amazon/digitalmusicxp/inputs/PlayableEntitySeedContextInput;", "", "", "toString", "", "hashCode", "other", "", "equals", "id", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "Lcom/amazon/digitalmusicxp/enums/PlayableEntitySeedIdTypeEnum;", "idType", "Lcom/amazon/digitalmusicxp/enums/PlayableEntitySeedIdTypeEnum;", "getIdType", "()Lcom/amazon/digitalmusicxp/enums/PlayableEntitySeedIdTypeEnum;", "Lcom/amazon/digitalmusicxp/inputs/MetricsContextInput;", "metricsContext", "Lcom/amazon/digitalmusicxp/inputs/MetricsContextInput;", "getMetricsContext", "()Lcom/amazon/digitalmusicxp/inputs/MetricsContextInput;", "<init>", "(Ljava/lang/String;Lcom/amazon/digitalmusicxp/enums/PlayableEntitySeedIdTypeEnum;Lcom/amazon/digitalmusicxp/inputs/MetricsContextInput;)V", "DigitalMusicCloudQueueXP_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class PlayableEntitySeedContextInput {
    private final String id;
    private final PlayableEntitySeedIdTypeEnum idType;
    private final MetricsContextInput metricsContext;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PlayableEntitySeedContextInput)) {
            return false;
        }
        PlayableEntitySeedContextInput playableEntitySeedContextInput = (PlayableEntitySeedContextInput) other;
        return Intrinsics.areEqual(this.id, playableEntitySeedContextInput.id) && this.idType == playableEntitySeedContextInput.idType && Intrinsics.areEqual(this.metricsContext, playableEntitySeedContextInput.metricsContext);
    }

    public int hashCode() {
        int iHashCode = ((this.id.hashCode() * 31) + this.idType.hashCode()) * 31;
        MetricsContextInput metricsContextInput = this.metricsContext;
        return iHashCode + (metricsContextInput == null ? 0 : metricsContextInput.hashCode());
    }

    public String toString() {
        return "PlayableEntitySeedContextInput(id=" + this.id + ", idType=" + this.idType + ", metricsContext=" + this.metricsContext + ')';
    }

    public PlayableEntitySeedContextInput(String id, PlayableEntitySeedIdTypeEnum idType, MetricsContextInput metricsContextInput) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(idType, "idType");
        this.id = id;
        this.idType = idType;
        this.metricsContext = metricsContextInput;
        ExpectedFunctionsKt.freeze(this);
    }

    public /* synthetic */ PlayableEntitySeedContextInput(String str, PlayableEntitySeedIdTypeEnum playableEntitySeedIdTypeEnum, MetricsContextInput metricsContextInput, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, playableEntitySeedIdTypeEnum, (i & 4) != 0 ? null : metricsContextInput);
    }

    public final String getId() {
        return this.id;
    }

    public final PlayableEntitySeedIdTypeEnum getIdType() {
        return this.idType;
    }
}
