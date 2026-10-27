package com.amazon.avod.playback.smoothstream.diagnostics;

import com.amazon.avod.media.TimeSpan;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class ManifestRefreshDataPoint extends DataPoint {
    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DataPoint
    public /* bridge */ /* synthetic */ int compareTo(DataPoint dataPoint) {
        return super.compareTo(dataPoint);
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DataPoint
    public /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DataPoint
    public /* bridge */ /* synthetic */ TimeSpan getTimeStamp() {
        return super.getTimeStamp();
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DataPoint
    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    ManifestRefreshDataPoint(@Nonnull TimeSpan timeSpan) {
        super(timeSpan);
    }
}
