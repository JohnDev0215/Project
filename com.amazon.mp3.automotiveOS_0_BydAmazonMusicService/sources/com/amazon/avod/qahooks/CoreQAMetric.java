package com.amazon.avod.qahooks;

import com.amazon.avod.qahooks.QALog;
import com.amazon.music.skyfire.models.TetheredMessageKey;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public enum CoreQAMetric implements QALog.QALoggableMetric {
    TIMESTAMP(TetheredMessageKey.timestamp),
    ENABLED("enabled"),
    NONE("none"),
    FEATURE("feature");

    private final String mIdentifier;

    @Override // com.amazon.avod.qahooks.QALog.QALoggableMetric
    @Nonnull
    public QALog.QALoggableMetric getQAMetric() {
        return this;
    }

    CoreQAMetric(String str) {
        this.mIdentifier = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.mIdentifier;
    }
}
