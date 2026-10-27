package com.amazon.avod.content.smoothstream.manifest.acquisition;

import com.google.common.base.Preconditions;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class CompositeManifestUploader implements ManifestUploader {

    @Nonnull
    private final List<ManifestUploader> mManifestUploaders;

    public CompositeManifestUploader(@Nonnull List<ManifestUploader> list) {
        this.mManifestUploaders = (List) Preconditions.checkNotNull(list, "manifestUploaderList");
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.acquisition.ManifestUploader
    public void upload(@Nonnull List<PostManifestPayload> list) {
        Iterator<ManifestUploader> it = this.mManifestUploaders.iterator();
        while (it.hasNext()) {
            it.next().upload(list);
        }
    }
}
