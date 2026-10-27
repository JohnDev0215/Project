package com.amazon.avod.pmet;

import com.amazon.avod.metrics.pmet.MetricParameter;
import com.amazon.avod.metrics.pmet.util.ReportableString;
import com.amazon.avod.metrics.pmet.util.Separator;
import com.google.common.base.Preconditions;
import java.util.Locale;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class TitleIdPivot implements MetricParameter {
    private final String mTitleId;
    private final Set<String> mTitleIdPivotsWhitelist;

    public TitleIdPivot(@Nullable String str, @Nonnull Set<String> set) {
        this.mTitleId = str;
        this.mTitleIdPivotsWhitelist = (Set) Preconditions.checkNotNull(set, "titleIdPivotsWhitelist");
    }

    @Override // com.amazon.avod.metrics.pmet.MetricParameter
    @Nonnull
    public String toReportableString() {
        if (this.mTitleId != null) {
            return String.format(Locale.US, "%s%s", Separator.COLON.toReportableString(), new ReportableString(this.mTitleId, this.mTitleIdPivotsWhitelist, "Other").toReportableString());
        }
        return PlaybackPmetMetricReporter.UNAVAILABLE_REPORTABLE_STRING.toReportableString();
    }
}
