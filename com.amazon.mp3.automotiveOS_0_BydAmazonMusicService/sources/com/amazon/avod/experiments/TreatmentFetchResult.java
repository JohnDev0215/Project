package com.amazon.avod.experiments;

import com.amazon.music.find.utils.PageUriUtils;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class TreatmentFetchResult {
    public final boolean isConfigOverridden;
    public final String source;
    public final WeblabTreatment treatment;

    TreatmentFetchResult(@Nonnull WeblabTreatment weblabTreatment, boolean z, @Nonnull String str) {
        this.treatment = (WeblabTreatment) Preconditions.checkNotNull(weblabTreatment, "treatment");
        this.isConfigOverridden = z;
        this.source = (String) Preconditions.checkNotNull(str, PageUriUtils.SOURCE_QUERY_PARAM);
    }

    @Nonnull
    public String toString() {
        return MoreObjects.toStringHelper("").add("treatment", this.treatment).add("isOverride", this.isConfigOverridden).add(PageUriUtils.SOURCE_QUERY_PARAM, this.source).toString();
    }
}
