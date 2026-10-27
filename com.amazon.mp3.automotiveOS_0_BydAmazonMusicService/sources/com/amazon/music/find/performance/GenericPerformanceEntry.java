package com.amazon.music.find.performance;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GenericPerformanceEntry.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\n\b&\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\tR\u0014\u0010\b\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000b¨\u0006\u0011"}, d2 = {"Lcom/amazon/music/find/performance/GenericPerformanceEntry;", "Lcom/amazon/music/find/performance/PerformanceEntry;", "name", "", "entryType", "Lcom/amazon/music/find/performance/EntryType;", "startTime", "", "duration", "(Ljava/lang/String;Lcom/amazon/music/find/performance/EntryType;JJ)V", "getDuration", "()J", "getEntryType", "()Lcom/amazon/music/find/performance/EntryType;", "getName", "()Ljava/lang/String;", "getStartTime", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class GenericPerformanceEntry implements PerformanceEntry {
    private final long duration;
    private final EntryType entryType;
    private final String name;
    private final long startTime;

    public GenericPerformanceEntry(String name, EntryType entryType, long j, long j2) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(entryType, "entryType");
        this.name = name;
        this.entryType = entryType;
        this.startTime = j;
        this.duration = j2;
    }

    @Override // com.amazon.music.find.performance.PerformanceEntry
    public String getName() {
        return this.name;
    }

    @Override // com.amazon.music.find.performance.PerformanceEntry
    public EntryType getEntryType() {
        return this.entryType;
    }

    @Override // com.amazon.music.find.performance.PerformanceEntry
    public long getStartTime() {
        return this.startTime;
    }

    @Override // com.amazon.music.find.performance.PerformanceEntry
    public long getDuration() {
        return this.duration;
    }
}
