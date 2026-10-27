package com.amazon.avod.config.internal;

import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class AppStartupConfigResponseUtil {
    public static long parseExpiryTimeMillis(@Nonnull ImmutableMap<String, String> immutableMap) {
        Preconditions.checkNotNull(immutableMap, "customerConfig");
        long j = SharedAppStartupConfigConstants.DEFAULT_EXPIRY_TIME;
        if (immutableMap.get("expiresIn") == null) {
            return j;
        }
        try {
            return TimeUnit.MINUTES.toMillis(Integer.parseInt(r6));
        } catch (NumberFormatException e) {
            DLog.exceptionf(e, "NumberFormatException when parsing expiry time from app startup config response, using default time %d milliseconds", Long.valueOf(SharedAppStartupConfigConstants.DEFAULT_EXPIRY_TIME));
            return j;
        }
    }
}
