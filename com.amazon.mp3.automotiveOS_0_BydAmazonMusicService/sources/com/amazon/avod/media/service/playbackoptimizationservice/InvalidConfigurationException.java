package com.amazon.avod.media.service.playbackoptimizationservice;

import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
class InvalidConfigurationException extends Exception {
    public InvalidConfigurationException(@Nonnull String str) {
        super((String) Preconditions.checkNotNull(str, "message"));
    }
}
