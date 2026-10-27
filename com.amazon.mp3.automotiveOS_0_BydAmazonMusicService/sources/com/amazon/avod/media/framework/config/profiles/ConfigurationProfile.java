package com.amazon.avod.media.framework.config.profiles;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface ConfigurationProfile {
    boolean containsKey(@Nonnull String str);

    @Nullable
    String getConfigValue(@Nonnull String str);

    @Nullable
    String getDeviceProfileName();

    void record(@Nonnull String str, @Nonnull String str2);
}
