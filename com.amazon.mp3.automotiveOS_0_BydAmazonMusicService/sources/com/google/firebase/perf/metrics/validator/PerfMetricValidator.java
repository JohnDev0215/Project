package com.google.firebase.perf.metrics.validator;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import com.google.firebase.perf.logging.AndroidLogger;
import com.google.firebase.perf.util.Constants;
import com.google.firebase.perf.v1.PerfMetric;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes6.dex */
public abstract class PerfMetricValidator {
    public abstract boolean isValidPerfMetric();

    @NonNull
    private static List<PerfMetricValidator> getValidators(@NonNull PerfMetric perfMetric, @NonNull Context context) {
        ArrayList arrayList = new ArrayList();
        if (perfMetric.hasTraceMetric()) {
            arrayList.add(new FirebasePerfTraceValidator(perfMetric.getTraceMetric()));
        }
        if (perfMetric.hasNetworkRequestMetric()) {
            arrayList.add(new FirebasePerfNetworkValidator(perfMetric.getNetworkRequestMetric(), context));
        }
        if (perfMetric.hasApplicationInfo()) {
            arrayList.add(new FirebasePerfApplicationInfoValidator(perfMetric.getApplicationInfo()));
        }
        if (perfMetric.hasGaugeMetric()) {
            arrayList.add(new FirebasePerfGaugeMetricValidator(perfMetric.getGaugeMetric()));
        }
        return arrayList;
    }

    public static boolean isValid(@NonNull PerfMetric perfMetric, @NonNull Context context) {
        List<PerfMetricValidator> validators = getValidators(perfMetric, context);
        if (validators.isEmpty()) {
            AndroidLogger.getInstance().debug("No validators found for PerfMetric.");
            return false;
        }
        Iterator<PerfMetricValidator> it = validators.iterator();
        while (it.hasNext()) {
            if (!it.next().isValidPerfMetric()) {
                return false;
            }
        }
        return true;
    }

    @Nullable
    public static String validateTraceName(@Nullable String str) {
        if (str == null) {
            return "Trace name must not be null";
        }
        if (str.length() > 100) {
            return String.format(Locale.US, "Trace name must not exceed %d characters", 100);
        }
        if (!str.startsWith(Attributes.PREDEFINED_ATTRIBUTE_PREFIX)) {
            return null;
        }
        for (Constants.TraceNames traceNames : Constants.TraceNames.values()) {
            if (traceNames.toString().equals(str)) {
                return null;
            }
        }
        if (str.startsWith(Constants.SCREEN_TRACE_PREFIX)) {
            return null;
        }
        return "Trace name must not start with '_'";
    }

    @Nullable
    public static String validateMetricName(@Nullable String str) {
        if (str == null) {
            return "Metric name must not be null";
        }
        if (str.length() > 100) {
            return String.format(Locale.US, "Metric name must not exceed %d characters", 100);
        }
        if (!str.startsWith(Attributes.PREDEFINED_ATTRIBUTE_PREFIX)) {
            return null;
        }
        for (Constants.CounterNames counterNames : Constants.CounterNames.values()) {
            if (counterNames.toString().equals(str)) {
                return null;
            }
        }
        return "Metric name must not start with '_'";
    }

    public static void validateAttribute(@NonNull String str, @NonNull String str2) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("Attribute key must not be null or empty");
        }
        if (str2 == null || str2.length() == 0) {
            throw new IllegalArgumentException("Attribute value must not be null or empty");
        }
        if (str.length() > 40) {
            throw new IllegalArgumentException(String.format(Locale.US, "Attribute key length must not exceed %d characters", 40));
        }
        if (str2.length() > 100) {
            throw new IllegalArgumentException(String.format(Locale.US, "Attribute value length must not exceed %d characters", 100));
        }
        if (!str.matches("^(?!(firebase_|google_|ga_))[A-Za-z][A-Za-z_0-9]*")) {
            throw new IllegalArgumentException("Attribute key must start with letter, must only contain alphanumeric characters and underscore and must not start with \"firebase_\", \"google_\" and \"ga_");
        }
    }
}
