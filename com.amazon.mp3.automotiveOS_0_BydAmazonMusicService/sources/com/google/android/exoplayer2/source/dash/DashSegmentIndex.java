package com.google.android.exoplayer2.source.dash;

import com.google.android.exoplayer2.source.dash.manifest.RangedUri;

/* JADX INFO: loaded from: classes6.dex */
public interface DashSegmentIndex {
    long getDurationUs(int i, long j);

    int getFirstSegmentNum();

    int getSegmentCount(long j);

    int getSegmentNum(long j, long j2);

    RangedUri getSegmentUrl(int i);

    long getTimeUs(int i);

    boolean isExplicit();
}
