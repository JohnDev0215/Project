package com.amazon.avod.content.smoothstream.manifest.acquisition;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface ManifestCapturerInterface {
    void capture(long j, @Nonnull ByteBuffer byteBuffer, @Nullable String str, String str2, @Nonnull String str3, boolean z, @Nullable Map<String, List<String>> map);

    void captureParsedData(boolean z);

    void shutdown();

    void uploadAdManifest();

    void uploadFromException(@Nonnull Exception exc);
}
