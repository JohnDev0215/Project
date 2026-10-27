package com.amazon.music.metrics.mts.event.types;

import com.amazon.music.metrics.mts.event.types.core.MetricValueType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class MetricsEnumUtil {
    private static final Logger logger = LoggerFactory.getLogger(MetricsEnumUtil.class.getSimpleName());

    public static <T extends Enum<T>> T fromStringValue(Class<T> cls, String str) {
        String strName;
        T[] enumConstants = cls.getEnumConstants();
        if (enumConstants == null || enumConstants.length == 0) {
            logger.warn("Invalid enum class {}.", cls.getSimpleName());
            return null;
        }
        boolean z = enumConstants[0] instanceof MetricValueType;
        for (T t : enumConstants) {
            if (z) {
                strName = ((MetricValueType) t).getMetricsValue();
            } else {
                strName = t.name();
            }
            if (str != null) {
                if (str.equals(strName)) {
                    return t;
                }
            } else if (strName == null) {
                return t;
            }
        }
        logger.warn("Invalid enum value {} for class {}, fallback to null", str, cls.getSimpleName());
        return null;
    }
}
