package com.amazon.avod.smoothstream.dash;

import com.amazon.avod.metrics.pmet.util.ReportableString;
import com.google.common.base.Preconditions;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public enum FragmentURLResolutionPmetMetrics {
    FRAGMENT_URL_RESOLUTION_ERROR,
    FRAGMENT_URL_HIERARCHICAL_PARSING_ERROR;

    private static final Set<String> ERROR_NAMES = new HashSet();

    static {
        for (FragmentURLResolutionPmetMetrics fragmentURLResolutionPmetMetrics : values()) {
            ERROR_NAMES.add(fragmentURLResolutionPmetMetrics.name());
        }
    }

    public static ReportableString toReportableString(@Nonnull FragmentURLResolutionPmetMetrics fragmentURLResolutionPmetMetrics) {
        Preconditions.checkNotNull(fragmentURLResolutionPmetMetrics, "metric");
        return new ReportableString(fragmentURLResolutionPmetMetrics.name(), ERROR_NAMES, "UNKNOWN_ERROR");
    }
}
