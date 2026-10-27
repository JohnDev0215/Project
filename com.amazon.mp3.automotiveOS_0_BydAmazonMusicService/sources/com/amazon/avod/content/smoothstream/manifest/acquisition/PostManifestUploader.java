package com.amazon.avod.content.smoothstream.manifest.acquisition;

import com.amazon.avod.postmanifest.PostManifestService;
import com.amazon.avod.postmanifest.PostManifestServiceClient;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PostManifestUploader implements ManifestUploader {
    @Override // com.amazon.avod.content.smoothstream.manifest.acquisition.ManifestUploader
    public void upload(@Nonnull List<PostManifestPayload> list) {
        Iterator<PostManifestPayload> it = list.iterator();
        while (it.hasNext()) {
            PostManifestService.getInstance().saveRecord(it.next().toJSON(), PostManifestServiceClient.PostManifestStreamType.MANIFESTS);
        }
        PostManifestService.getInstance().submitAllRecords();
    }
}
