package com.amazon.avod.content.urlvending;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

/* JADX INFO: loaded from: classes2.dex */
@JsonIgnoreProperties(ignoreUnknown = true)
@Immutable
public class CuepointPlaylistInfo {
    private final CuepointPlaylistMetadataInfo mCuepointPlaylistMetadataInfo;
    private final String mEncodedManifest;
    private final long mLastCuepointUpdatedTimeCodeInMs;
    private final CuepointManifestFormat mManifestFormat;

    @JsonCreator
    public CuepointPlaylistInfo(@JsonProperty("cuepointPlaylistMetadataInfo") @Nonnull CuepointPlaylistMetadataInfo cuepointPlaylistMetadataInfo, @JsonProperty("encodedManifest") @Nonnull String str, @JsonProperty("manifestFormat") @Nullable CuepointManifestFormat cuepointManifestFormat, @JsonProperty("lastCuepointUpdatedTimeCodeInMs") long j) {
        this.mCuepointPlaylistMetadataInfo = (CuepointPlaylistMetadataInfo) Preconditions.checkNotNull(cuepointPlaylistMetadataInfo, "cuepointPlaylistMetadataInfo");
        this.mEncodedManifest = (String) Preconditions.checkNotNull(str, "encodedManifest");
        this.mManifestFormat = cuepointManifestFormat;
        this.mLastCuepointUpdatedTimeCodeInMs = j;
    }

    @Nonnull
    public CuepointPlaylistMetadataInfo getCuepointPlaylistMetadataInfo() {
        return this.mCuepointPlaylistMetadataInfo;
    }

    @Nonnull
    public String getEncodedManifest() {
        return this.mEncodedManifest;
    }

    @Nullable
    public CuepointManifestFormat getManifestFormat() {
        return this.mManifestFormat;
    }

    public long getLastCuepointUpdatedTimeCodeInMs() {
        return this.mLastCuepointUpdatedTimeCodeInMs;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("cuepointPlaylistMetadata", getCuepointPlaylistMetadataInfo().toString()).add("encodedManifest", getEncodedManifest()).add("manifestFormat", getManifestFormat().toString()).add("lastCuepointUpdatedTimeCodeInMs", getLastCuepointUpdatedTimeCodeInMs()).toString();
    }
}
