package com.amazon.avod.experiments.override;

import amazon.android.config.ConfigurationValue;
import amazon.android.config.PersistentConfigBase;
import com.amazon.avod.experiments.WeblabTreatment;
import com.google.common.base.Preconditions;
import java.util.Locale;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PersistentOverride extends PersistentConfigBase {
    private static final WeblabTreatment DEFAULT_DEBUG_VALUE = WeblabTreatment.C;
    private final ConfigurationValue<WeblabTreatment> mTreatmentOverride;
    private final ConfigurationValue<Boolean> mTreatmentOverrideEnabled;

    public PersistentOverride(@Nonnull String str, @Nonnull String str2) {
        Preconditions.checkNotNull(str, "weblab");
        Locale locale = Locale.US;
        String str3 = String.format(locale, "Debug_override_enabled_%s_%s", str, str2);
        String str4 = String.format(locale, "Debug_override_treatment_%s_%s", str, str2);
        this.mTreatmentOverrideEnabled = newBooleanConfigValue(str3, false);
        this.mTreatmentOverride = newEnumConfigValue(str4, DEFAULT_DEBUG_VALUE, WeblabTreatment.class);
    }

    public boolean isOverrideEnabled() {
        return this.mTreatmentOverrideEnabled.getValue().booleanValue();
    }

    @Nonnull
    public WeblabTreatment getTreatmentOverride(@Nonnull String str) {
        Preconditions.checkNotNull(str, "name");
        return this.mTreatmentOverride.getValue();
    }
}
