package com.amazon.avod.userdownload;

import com.amazon.avod.metrics.pmet.MetricParameter;
import com.amazon.avod.util.Preconditions2;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public enum ProgressMilestone implements MetricParameter {
    NOT_READY_TO_WATCH(0, "Stub"),
    READY_TO_WATCH(1, "RTW"),
    COMPLETED(2, "Done");

    private final int mPersistenceValue;
    private final String mShortQosName;

    ProgressMilestone(int i, @Nonnull String str) {
        this.mPersistenceValue = i;
        this.mShortQosName = str;
    }

    public int getNumericValue() {
        return this.mPersistenceValue;
    }

    @Nonnull
    public String getShortQosName() {
        return this.mShortQosName;
    }

    @Override // java.lang.Enum
    public String toString() {
        return getShortQosName();
    }

    @Nonnull
    public static ProgressMilestone fromNumericValue(int i) {
        for (ProgressMilestone progressMilestone : values()) {
            if (progressMilestone.getNumericValue() == i) {
                return progressMilestone;
            }
        }
        ProgressMilestone progressMilestone2 = NOT_READY_TO_WATCH;
        Preconditions2.failWeakly("Invalid value %d; returning %s", Integer.valueOf(i), progressMilestone2);
        return progressMilestone2;
    }

    @Override // com.amazon.avod.metrics.pmet.MetricParameter
    @Nonnull
    public String toReportableString() {
        return this.mShortQosName;
    }
}
