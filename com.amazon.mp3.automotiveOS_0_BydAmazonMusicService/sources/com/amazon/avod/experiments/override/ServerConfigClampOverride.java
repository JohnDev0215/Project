package com.amazon.avod.experiments.override;

import amazon.android.config.ServerConfigBase;
import com.amazon.avod.experiments.WeblabTreatment;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import java.util.Locale;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class ServerConfigClampOverride extends ServerConfigBase {
    @Nonnull
    public Optional<WeblabTreatment> getClamp(@Nonnull String str) {
        Preconditions.checkNotNull(str, "weblabName");
        return WeblabTreatment.fromString(newStringConfigValue(String.format(Locale.US, "Weblab_clamp_%s", str), null).getValue());
    }
}
