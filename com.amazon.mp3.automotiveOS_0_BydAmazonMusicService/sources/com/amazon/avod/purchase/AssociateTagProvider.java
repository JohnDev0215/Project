package com.amazon.avod.purchase;

import com.google.common.base.Optional;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface AssociateTagProvider {
    @Nonnull
    Optional<String> getAssociateTag(@Nonnull AssociateTagRequester associateTagRequester);
}
