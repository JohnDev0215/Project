package com.amazon.avod.postmanifest;

import com.amazon.avod.postmanifest.PostManifestServiceClient;
import com.amazon.avod.util.InitializationLatch;
import com.google.common.base.Preconditions;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PostManifestService {
    private final InitializationLatch mInitLatch;
    private PostManifestServiceClient mServiceClient;

    private static final class Holder {
        static final PostManifestService INSTANCE = new PostManifestService();

        private Holder() {
        }
    }

    public static PostManifestService getInstance() {
        return Holder.INSTANCE;
    }

    private PostManifestService() {
        this.mInitLatch = new InitializationLatch(this);
    }

    public void initialize(@Nonnull PostManifestServiceClient postManifestServiceClient) {
        this.mInitLatch.start(30L, TimeUnit.SECONDS);
        this.mServiceClient = (PostManifestServiceClient) Preconditions.checkNotNull(postManifestServiceClient, "postManifestServiceClient");
        this.mInitLatch.complete();
    }

    public void saveRecord(@Nonnull String str, PostManifestServiceClient.PostManifestStreamType postManifestStreamType) {
        this.mInitLatch.checkInitialized();
        this.mServiceClient.saveRecord(str, postManifestStreamType);
    }

    public void submitAllRecords() {
        this.mInitLatch.checkInitialized();
        this.mServiceClient.submitAllRecords();
    }
}
