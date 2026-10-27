package com.amazon.avod.purchase;

import com.amazon.avod.util.InitializationLatch;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class AssociateTagProviderProxy {
    private AssociateTagProvider mAssociateTagProvider;
    private final InitializationLatch mInitializationLatch = new InitializationLatch(this);

    private static class SingletonHolder {
        private static volatile AssociateTagProviderProxy INSTANCE = new AssociateTagProviderProxy();

        private SingletonHolder() {
        }
    }

    public static AssociateTagProviderProxy getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void initialize(@Nonnull AssociateTagProvider associateTagProvider) {
        this.mInitializationLatch.start(5L, TimeUnit.SECONDS);
        this.mAssociateTagProvider = (AssociateTagProvider) Preconditions.checkNotNull(associateTagProvider, "associateTagProvider");
        this.mInitializationLatch.complete();
    }

    @Nonnull
    public Optional<String> getAssociateTag(@Nonnull AssociateTagRequester associateTagRequester) {
        Preconditions.checkNotNull(associateTagRequester, "requester");
        this.mInitializationLatch.waitOnInitializationUninterruptibly();
        return this.mAssociateTagProvider.getAssociateTag(associateTagRequester);
    }
}
