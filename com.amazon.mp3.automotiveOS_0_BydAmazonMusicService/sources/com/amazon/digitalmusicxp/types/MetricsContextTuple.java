package com.amazon.digitalmusicxp.types;

import com.amazon.digitalmusicxp.ExpectedFunctionsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MetricsContextTuple.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\n\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\r\u001a\u00020\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\f¨\u0006\u0011"}, d2 = {"Lcom/amazon/digitalmusicxp/types/MetricsContextTuple;", "", "", "toString", "", "hashCode", "other", "", "equals", "key", "Ljava/lang/String;", "getKey", "()Ljava/lang/String;", "value", "getValue", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "DigitalMusicCloudQueueXP_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class MetricsContextTuple {
    private final String key;
    private final String value;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MetricsContextTuple)) {
            return false;
        }
        MetricsContextTuple metricsContextTuple = (MetricsContextTuple) other;
        return Intrinsics.areEqual(this.key, metricsContextTuple.key) && Intrinsics.areEqual(this.value, metricsContextTuple.value);
    }

    public int hashCode() {
        return (this.key.hashCode() * 31) + this.value.hashCode();
    }

    public String toString() {
        return "MetricsContextTuple(key=" + this.key + ", value=" + this.value + ')';
    }

    public MetricsContextTuple(String key, String value) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        this.key = key;
        this.value = value;
        ExpectedFunctionsKt.freeze(this);
    }

    public final String getKey() {
        return this.key;
    }

    public final String getValue() {
        return this.value;
    }
}
