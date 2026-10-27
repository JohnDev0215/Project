package com.amazon.avod.experiments;

import com.amazon.avod.metrics.pmet.MetricParameter;
import com.amazon.weblab.mobile.experimental.PlatformWeblabs;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public enum WeblabTreatment implements MetricParameter {
    C(PlatformWeblabs.C),
    T1(PlatformWeblabs.T1),
    T2(PlatformWeblabs.T2),
    T3(PlatformWeblabs.T3),
    T4(PlatformWeblabs.T4),
    T5(PlatformWeblabs.T5);

    private final String mValue;

    WeblabTreatment(@Nonnull String str) {
        this.mValue = (String) Preconditions.checkNotNull(str, "value");
    }

    @Nonnull
    public static Optional<WeblabTreatment> fromString(@Nullable String str) {
        for (WeblabTreatment weblabTreatment : values()) {
            if (weblabTreatment.mValue.equalsIgnoreCase(str)) {
                return Optional.of(weblabTreatment);
            }
        }
        return Optional.absent();
    }

    public String getValue() {
        return this.mValue;
    }

    @Override // com.amazon.avod.metrics.pmet.MetricParameter
    public String toReportableString() {
        return this.mValue;
    }
}
