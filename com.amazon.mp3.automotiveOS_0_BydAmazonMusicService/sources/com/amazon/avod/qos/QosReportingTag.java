package com.amazon.avod.qos;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class QosReportingTag<T> {
    private final String mInstanceId;
    private final String mName;
    private final T mValue;

    private QosReportingTag(String str, T t, String str2) {
        this.mName = (String) Preconditions.checkNotNull(str);
        this.mValue = (T) Preconditions.checkNotNull(t);
        this.mInstanceId = str2;
    }

    public static QosReportingTag<String> newStringTag(String str, String str2, String str3) {
        return new QosReportingTag<>(str, str2, str3);
    }

    public static QosReportingTag<Boolean> newBooleanTag(String str, Boolean bool, String str2) {
        return new QosReportingTag<>(str, bool, str2);
    }

    public String getName() {
        return this.mName;
    }

    public boolean isInstanced() {
        return this.mInstanceId != null;
    }

    @Nullable
    public String getInstanceId() {
        return this.mInstanceId;
    }

    public T getValue() {
        return this.mValue;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof QosReportingTag)) {
            return false;
        }
        QosReportingTag qosReportingTag = (QosReportingTag) obj;
        return Objects.equal(this.mValue, qosReportingTag.mValue) && Objects.equal(this.mName, qosReportingTag.mName) && Objects.equal(this.mInstanceId, qosReportingTag.mInstanceId);
    }

    public int hashCode() {
        return Objects.hashCode(this.mName, this.mValue, this.mInstanceId);
    }
}
