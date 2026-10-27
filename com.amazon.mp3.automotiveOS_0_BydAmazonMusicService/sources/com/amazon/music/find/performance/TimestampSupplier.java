package com.amazon.music.find.performance;

import kotlin.Metadata;

/* JADX INFO: compiled from: TimestampSupplier.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007H&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\n"}, d2 = {"Lcom/amazon/music/find/performance/TimestampSupplier;", "", "units", "", "getUnits", "()Ljava/lang/String;", "now", "", "toMilliseconds", "interval", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface TimestampSupplier {
    String getUnits();

    long now();

    long toMilliseconds(long interval);
}
