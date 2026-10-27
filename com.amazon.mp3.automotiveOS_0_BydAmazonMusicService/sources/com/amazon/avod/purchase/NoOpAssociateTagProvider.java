package com.amazon.avod.purchase;

import com.google.common.base.Optional;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class NoOpAssociateTagProvider implements AssociateTagProvider {
    @Override // com.amazon.avod.purchase.AssociateTagProvider
    @Nonnull
    public Optional<String> getAssociateTag(@Nonnull AssociateTagRequester associateTagRequester) {
        return Optional.absent();
    }
}
