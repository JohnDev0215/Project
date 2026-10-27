package com.amazon.avod.content.smoothstream.manifest.acquisition;

import java.util.List;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface ManifestUploader {
    void upload(@Nonnull List<PostManifestPayload> list);
}
