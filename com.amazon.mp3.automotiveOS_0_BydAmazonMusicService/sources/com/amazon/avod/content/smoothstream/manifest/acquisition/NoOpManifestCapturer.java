package com.amazon.avod.content.smoothstream.manifest.acquisition;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class NoOpManifestCapturer implements ManifestCapturerInterface {
    @Override // com.amazon.avod.content.smoothstream.manifest.acquisition.ManifestCapturerInterface
    public void capture(long j, @Nonnull ByteBuffer byteBuffer, @Nullable String str, String str2, @Nonnull String str3, boolean z, @Nullable Map<String, List<String>> map) {
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.acquisition.ManifestCapturerInterface
    public void captureParsedData(boolean z) {
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.acquisition.ManifestCapturerInterface
    public void shutdown() {
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.acquisition.ManifestCapturerInterface
    public void uploadAdManifest() {
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.acquisition.ManifestCapturerInterface
    public void uploadFromException(@Nonnull Exception exc) {
    }
}
