package com.amazon.avod.util;

import com.amazon.avod.metrics.pmet.MetricParameterException;
import com.amazon.bolthttp.ConnectivityTimeoutException;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class ErrorCodeUtils {
    /* JADX WARN: Multi-variable type inference failed */
    @Nonnull
    public static String extractReportableMetric(@Nonnull Throwable th) {
        Preconditions.checkNotNull(th, "throwable");
        Throwable cause = th;
        while (!(cause instanceof ConnectivityTimeoutException)) {
            if (cause instanceof MetricParameterException) {
                return ((MetricParameterException) cause).toReportableString();
            }
            if (cause.getCause() == null) {
                return cause.getClass().getSimpleName();
            }
            cause = cause.getCause();
        }
        return ((ConnectivityTimeoutException) cause).toReportableString();
    }
}
