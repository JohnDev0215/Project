package com.amazon.avod.postmanifest;

import com.amazon.avod.postmanifest.PostManifestServiceClient;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PostManifestNoOpServiceClient implements PostManifestServiceClient {
    @Override // com.amazon.avod.postmanifest.PostManifestServiceClient
    public void saveRecord(@Nonnull String str, PostManifestServiceClient.PostManifestStreamType postManifestStreamType) {
    }

    @Override // com.amazon.avod.postmanifest.PostManifestServiceClient
    public void submitAllRecords() {
    }
}
