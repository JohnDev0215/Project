package com.amazon.digitalmusicxp.inputs;

import com.amazon.digitalmusicxp.ExpectedFunctionsKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TrackListSeedContextInput.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0014\u0010\u0015J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u001d\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0010\u001a\u00020\u000f8\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0016"}, d2 = {"Lcom/amazon/digitalmusicxp/inputs/TrackListSeedContextInput;", "", "", "toString", "", "hashCode", "other", "", "equals", "", "Lcom/amazon/digitalmusicxp/inputs/TrackReferenceInput;", "tracks", "Ljava/util/List;", "getTracks", "()Ljava/util/List;", "Lcom/amazon/digitalmusicxp/inputs/MetricsContextInput;", "metricsContext", "Lcom/amazon/digitalmusicxp/inputs/MetricsContextInput;", "getMetricsContext", "()Lcom/amazon/digitalmusicxp/inputs/MetricsContextInput;", "<init>", "(Ljava/util/List;Lcom/amazon/digitalmusicxp/inputs/MetricsContextInput;)V", "DigitalMusicCloudQueueXP_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class TrackListSeedContextInput {
    private final MetricsContextInput metricsContext;
    private final List<TrackReferenceInput> tracks;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TrackListSeedContextInput)) {
            return false;
        }
        TrackListSeedContextInput trackListSeedContextInput = (TrackListSeedContextInput) other;
        return Intrinsics.areEqual(this.tracks, trackListSeedContextInput.tracks) && Intrinsics.areEqual(this.metricsContext, trackListSeedContextInput.metricsContext);
    }

    public int hashCode() {
        return (this.tracks.hashCode() * 31) + this.metricsContext.hashCode();
    }

    public String toString() {
        return "TrackListSeedContextInput(tracks=" + this.tracks + ", metricsContext=" + this.metricsContext + ')';
    }

    public TrackListSeedContextInput(List<TrackReferenceInput> tracks, MetricsContextInput metricsContext) {
        Intrinsics.checkNotNullParameter(tracks, "tracks");
        Intrinsics.checkNotNullParameter(metricsContext, "metricsContext");
        this.tracks = tracks;
        this.metricsContext = metricsContext;
        ExpectedFunctionsKt.freeze(this);
    }

    public final List<TrackReferenceInput> getTracks() {
        return this.tracks;
    }
}
