package com.amazon.avod.playback.smoothstream.diagnostics;

import com.amazon.avod.media.TimeSpan;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
class CdnSwitchedDataPoint extends DataPoint {
    final String mFailoverRuleId;
    final String mNewCdn;
    final String mOldCdn;

    CdnSwitchedDataPoint(TimeSpan timeSpan, String str, String str2, @Nullable String str3) {
        super(timeSpan);
        this.mOldCdn = str;
        this.mNewCdn = str2;
        this.mFailoverRuleId = str3;
    }

    public String getOldCdn() {
        return this.mOldCdn;
    }

    public String getNewCdn() {
        return this.mNewCdn;
    }

    @Nullable
    public String getFailoverRuleId() {
        return this.mFailoverRuleId;
    }
}
