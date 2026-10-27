package com.bugsnag.android.internal;

import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt__MapsKt;

/* JADX INFO: compiled from: InternalMetricsNoop.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0012\u0010\u0013J\u0014\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002H\u0016J\u001c\u0010\b\u001a\u00020\u00072\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002H\u0016J\u001c\u0010\u000b\u001a\u00020\u00072\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\t0\u0002H\u0016J\u0018\u0010\u000e\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\tH\u0016J\u0018\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\tH\u0016¨\u0006\u0014"}, d2 = {"Lcom/bugsnag/android/internal/InternalMetricsNoop;", "Lcom/bugsnag/android/internal/InternalMetrics;", "", "", "", "toJsonableMap", "differences", "", "setConfigDifferences", "", "newCallbackCounts", "setCallbackCounts", "stringsTrimmed", "charsRemoved", "setMetadataTrimMetrics", "breadcrumbsRemoved", "bytesRemoved", "setBreadcrumbTrimMetrics", "<init>", "()V", "bugsnag-android-core_release"}, k = 1, mv = {1, 4, 2})
public final class InternalMetricsNoop implements InternalMetrics {
    @Override // com.bugsnag.android.internal.InternalMetrics
    public void setBreadcrumbTrimMetrics(int breadcrumbsRemoved, int bytesRemoved) {
    }

    @Override // com.bugsnag.android.internal.InternalMetrics
    public void setCallbackCounts(Map<String, Integer> newCallbackCounts) {
    }

    @Override // com.bugsnag.android.internal.InternalMetrics
    public void setConfigDifferences(Map<String, ? extends Object> differences) {
    }

    @Override // com.bugsnag.android.internal.InternalMetrics
    public void setMetadataTrimMetrics(int stringsTrimmed, int charsRemoved) {
    }

    @Override // com.bugsnag.android.internal.InternalMetrics
    public Map<String, Object> toJsonableMap() {
        return MapsKt__MapsKt.emptyMap();
    }
}
