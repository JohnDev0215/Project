package com.amazon.avod.playback.core;

import com.amazon.avod.identity.IdentityShim;
import com.amazon.avod.identity.User;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class NoOpIdentityShim implements IdentityShim {
    private static final User USER = new User(User.Role.ADULT, "xxaivxx", 0);

    @Override // com.amazon.avod.identity.IdentityShim
    @Nullable
    public String getSharedLicensesSessionContextKey() {
        return null;
    }

    @Override // com.amazon.avod.identity.IdentityShim
    public boolean isChild() {
        return false;
    }

    @Override // com.amazon.avod.identity.IdentityShim
    @Nonnull
    @Deprecated
    public Optional<User> getCurrentUser() {
        return Optional.of(USER);
    }

    @Override // com.amazon.avod.identity.IdentityShim
    @Nonnull
    public Optional<User> getDevicePrimaryUser() {
        return Optional.of(USER);
    }

    @Override // com.amazon.avod.identity.IdentityShim
    @Nonnull
    public Map<String, String> getPlaybackSessionContext() {
        return ImmutableMap.of();
    }

    @Override // com.amazon.avod.identity.IdentityShim
    @Nonnull
    public Optional<String> getCurrentUserAccountId() {
        return Optional.of("xxaivxx");
    }
}
