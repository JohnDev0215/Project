package com.amazon.avod.content.urlvending;

import com.amazon.atv.cuepoint.CuepointPlaylist;
import com.amazon.atv.cuepoint.CuepointPlaylistMetadata;
import com.amazon.atv.cuepoint.ManifestFormat;
import com.google.common.base.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class CuepointPlaylistTransformer {
    @Nullable
    public static CuepointPlaylistInfo transform(@Nonnull Optional<CuepointPlaylist> optional) {
        if (!optional.isPresent()) {
            return null;
        }
        CuepointPlaylist cuepointPlaylist = optional.get();
        return new CuepointPlaylistInfo(new CuepointPlaylistMetadataInfo(cuepointPlaylist.additionalMetadata.showCountdownTimer), cuepointPlaylist.encodedManifest, CuepointManifestFormat.lookup(cuepointPlaylist.format.name()), System.currentTimeMillis());
    }

    @Nullable
    public static CuepointPlaylist transform(@Nullable CuepointPlaylistInfo cuepointPlaylistInfo) {
        if (cuepointPlaylistInfo == null) {
            return null;
        }
        CuepointPlaylistMetadata.Builder builder = new CuepointPlaylistMetadata.Builder();
        builder.showCountdownTimer = cuepointPlaylistInfo.getCuepointPlaylistMetadataInfo().getShowCountdownTimer();
        CuepointPlaylist.Builder builder2 = new CuepointPlaylist.Builder();
        builder2.encodedManifest = cuepointPlaylistInfo.getEncodedManifest();
        builder2.additionalMetadata = builder.build();
        builder2.format = ManifestFormat.forValue(cuepointPlaylistInfo.getManifestFormat().name());
        return builder2.build();
    }
}
