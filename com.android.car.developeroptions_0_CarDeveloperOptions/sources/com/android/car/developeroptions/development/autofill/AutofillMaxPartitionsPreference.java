package com.android.car.developeroptions.development.autofill;

import android.content.Context;
import android.util.AttributeSet;

/* JADX INFO: loaded from: classes.dex */
public final class AutofillMaxPartitionsPreference extends AbstractGlobalSettingsPreference {
    public AutofillMaxPartitionsPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, "autofill_max_partitions_size", 10);
    }
}
