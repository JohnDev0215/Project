package com.amazon.avod.metrics.pmet.internal;

import com.amazon.avod.metrics.pmet.MetricParameter;
import com.amazon.avod.util.Preconditions2;
import com.google.common.base.Preconditions;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PmetMetricUtils {
    public static String appendParametersIfValid(@Nonnull List<Class<? extends MetricParameter>> list, @Nonnull List<MetricParameter> list2, @Nonnull String str) {
        Preconditions.checkNotNull(list, "classes");
        Preconditions.checkNotNull(list2, "parameters");
        Preconditions.checkNotNull(str, "base");
        Preconditions2.checkStateWeakly(list.size() == list2.size(), "Base (%s) expects %s parameters but was formatted with %s", str, Integer.valueOf(list.size()), Integer.valueOf(list2.size()));
        StringBuilder sb = new StringBuilder(str);
        Iterator<Class<? extends MetricParameter>> it = list.iterator();
        Iterator<MetricParameter> it2 = list2.iterator();
        while (it.hasNext() && it2.hasNext()) {
            Class<? extends MetricParameter> next = it.next();
            MetricParameter next2 = it2.next();
            boolean zIsInstance = next.isInstance(next2);
            Preconditions2.checkStateWeakly(zIsInstance, "Metric class mismatch! Expected %s, but got %s for parameter: %s", next.getSimpleName(), next2.getClass().getSimpleName(), next2.toReportableString());
            if (zIsInstance) {
                sb.append(next2.toReportableString());
            }
        }
        return sb.toString();
    }
}
