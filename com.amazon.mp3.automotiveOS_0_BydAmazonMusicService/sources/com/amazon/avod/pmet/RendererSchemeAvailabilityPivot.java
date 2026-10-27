package com.amazon.avod.pmet;

import com.amazon.avod.media.playback.support.AvailabilityStatus;
import com.amazon.avod.metrics.pmet.MetricParameter;
import dagger.internal.Preconditions;
import java.util.Locale;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class RendererSchemeAvailabilityPivot implements MetricParameter {
    private final AvailabilityStatus mAvailabilityStatus;
    private final String mRendererScheme;

    public RendererSchemeAvailabilityPivot(@Nonnull String str, @Nonnull AvailabilityStatus availabilityStatus) {
        this.mRendererScheme = (String) Preconditions.checkNotNull(str, "rendererScheme");
        this.mAvailabilityStatus = (AvailabilityStatus) Preconditions.checkNotNull(availabilityStatus, "availabilityStatus");
    }

    @Override // com.amazon.avod.metrics.pmet.MetricParameter
    @Nonnull
    public String toReportableString() {
        return String.format(Locale.US, "%s:%s", this.mRendererScheme, this.mAvailabilityStatus);
    }
}
