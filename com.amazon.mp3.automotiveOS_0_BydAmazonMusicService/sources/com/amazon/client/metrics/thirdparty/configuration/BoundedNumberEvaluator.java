package com.amazon.client.metrics.thirdparty.configuration;

import com.amazon.dp.logger.DPLogger;

/* JADX INFO: loaded from: classes2.dex */
public class BoundedNumberEvaluator {
    DPLogger log;
    final String mFieldName;
    final long mMax;
    final long mMin;
    final long mValue;

    public BoundedNumberEvaluator(String str, long j, long j2, long j3) {
        DPLogger dPLogger = new DPLogger("BoundedNumberEvaluator");
        this.log = dPLogger;
        this.mMin = j;
        this.mMax = j2;
        if (j3 < j) {
            dPLogger.verbose("fieldName", "value", Long.valueOf(j3), "less than min value", Long.valueOf(j), "field name", "using min value");
            this.mValue = j;
        } else if (j3 > j2) {
            dPLogger.verbose("fieldName", "value", Long.valueOf(j3), "greater than max value", Long.valueOf(j2), "using max value");
            this.mValue = j2;
        } else {
            this.mValue = j3;
        }
        this.mFieldName = str;
    }

    public long getValue() {
        return this.mValue;
    }
}
