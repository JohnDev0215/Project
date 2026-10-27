package com.amazon.avod.media.service.prsv2;

import com.amazon.avod.media.service.PlaybackResourceServiceConstants$HdrFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Preconditions;
import java.util.List;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public abstract class StreamingTechnologyBase {
    private final List<String> mBitrateAdaptations;
    private final List<String> mCodecs;
    private final String mDrmKeyScheme;
    private final String mDrmStrength;
    private final String mDrmType;
    private final List<PlaybackResourceServiceConstants$HdrFormat> mDynamicRangeFormats;
    private final List<String> mFragmentRepresentations;
    private final List<String> mFrameRates;

    StreamingTechnologyBase(@Nonnull List<String> list, @Nonnull List<String> list2, @Nonnull String str, @Nonnull String str2, @Nonnull String str3, @Nonnull List<String> list3, @Nonnull List<String> list4, @Nonnull List<PlaybackResourceServiceConstants$HdrFormat> list5) {
        this.mBitrateAdaptations = (List) Preconditions.checkNotNull(list, "bitrateAdaptations");
        this.mCodecs = (List) Preconditions.checkNotNull(list2, "codecs");
        this.mDrmKeyScheme = (String) Preconditions.checkNotNull(str, "drmKeyScheme");
        this.mDrmStrength = (String) Preconditions.checkNotNull(str2, "drmStrength");
        this.mDrmType = (String) Preconditions.checkNotNull(str3, "drmType");
        this.mFragmentRepresentations = (List) Preconditions.checkNotNull(list3, "fragmentRepresentations");
        this.mFrameRates = (List) Preconditions.checkNotNull(list4, "frameRates");
        this.mDynamicRangeFormats = (List) Preconditions.checkNotNull(list5, "dynamicRangeFormats");
    }

    @JsonProperty("bitrateAdaptations")
    @Nonnull
    public List<String> getBitrateAdaptations() {
        return this.mBitrateAdaptations;
    }

    @JsonProperty("codecs")
    @Nonnull
    public List<String> getCodecs() {
        return this.mCodecs;
    }

    @JsonProperty("drmKeyScheme")
    @Nonnull
    public String getDrmKeyScheme() {
        return this.mDrmKeyScheme;
    }

    @JsonProperty("drmStrength")
    @Nonnull
    public String getDrmStrength() {
        return this.mDrmStrength;
    }

    @JsonProperty("drmType")
    @Nonnull
    public String getDrmType() {
        return this.mDrmType;
    }

    @JsonProperty("fragmentRepresentations")
    @Nonnull
    public List<String> getFragmentRepresentations() {
        return this.mFragmentRepresentations;
    }

    @JsonProperty("frameRates")
    @Nonnull
    public List<String> getFrameRates() {
        return this.mFrameRates;
    }

    @JsonProperty("dynamicRangeFormats")
    @Nonnull
    public List<PlaybackResourceServiceConstants$HdrFormat> getDynamicRangeFormats() {
        return this.mDynamicRangeFormats;
    }
}
