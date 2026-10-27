package com.amazon.music.find.performance;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PerformanceMeasure.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007R\u0014\u0010\u0006\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\r"}, d2 = {"Lcom/amazon/music/find/performance/PerformanceMeasure;", "Lcom/amazon/music/find/performance/GenericPerformanceEntry;", "name", "", "startTime", "", "duration", "(Ljava/lang/String;JJ)V", "getDuration", "()J", "getName", "()Ljava/lang/String;", "getStartTime", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PerformanceMeasure extends GenericPerformanceEntry {
    private final long duration;
    private final String name;
    private final long startTime;

    @Override // com.amazon.music.find.performance.GenericPerformanceEntry, com.amazon.music.find.performance.PerformanceEntry
    public long getDuration() {
        return this.duration;
    }

    @Override // com.amazon.music.find.performance.GenericPerformanceEntry, com.amazon.music.find.performance.PerformanceEntry
    public String getName() {
        return this.name;
    }

    @Override // com.amazon.music.find.performance.GenericPerformanceEntry, com.amazon.music.find.performance.PerformanceEntry
    public long getStartTime() {
        return this.startTime;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PerformanceMeasure(String name, long j, long j2) {
        super(name, EntryType.MEASURE, j, j2);
        Intrinsics.checkNotNullParameter(name, "name");
        this.name = name;
        this.startTime = j;
        this.duration = j2;
    }
}
