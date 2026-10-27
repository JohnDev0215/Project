package com.amazon.avod.content.urlvending;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.common.base.MoreObjects;
import com.google.common.base.Strings;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

/* JADX INFO: loaded from: classes2.dex */
@JsonIgnoreProperties(ignoreUnknown = true)
@Immutable
public class AuditPing {
    private final String mAdReportingAgency;
    private final String mUrl;

    @JsonCreator
    public AuditPing(@JsonProperty("adReportingAgency") @Nullable String str, @JsonProperty(ImagesContract.URL) @Nullable String str2) {
        this.mAdReportingAgency = Strings.nullToEmpty(str);
        this.mUrl = Strings.nullToEmpty(str2);
    }

    @Nonnull
    public String getAdReportingAgency() {
        return this.mAdReportingAgency;
    }

    @Nonnull
    public String getUrl() {
        return this.mUrl;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("adReportingAgency", this.mAdReportingAgency).add(ImagesContract.URL, this.mUrl).toString();
    }
}
