package com.amazon.avod.identity;

import com.google.common.base.Optional;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface IdentityShim {
    @Nonnull
    @Deprecated
    Optional<User> getCurrentUser();

    @Nonnull
    Optional<String> getCurrentUserAccountId();

    @Nonnull
    Optional<User> getDevicePrimaryUser();

    @Nonnull
    Map<String, String> getPlaybackSessionContext();

    @Nullable
    String getSharedLicensesSessionContextKey();

    boolean isChild();
}
