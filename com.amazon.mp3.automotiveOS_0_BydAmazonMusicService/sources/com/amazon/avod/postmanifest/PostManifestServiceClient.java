package com.amazon.avod.postmanifest;

import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface PostManifestServiceClient {

    public enum PostManifestStreamType {
        MANIFESTS,
        LOGS
    }

    void saveRecord(@Nonnull String str, PostManifestStreamType postManifestStreamType);

    void submitAllRecords();
}
