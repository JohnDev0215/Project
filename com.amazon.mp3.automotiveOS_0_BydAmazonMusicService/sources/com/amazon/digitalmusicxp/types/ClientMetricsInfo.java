package com.amazon.digitalmusicxp.types;

import com.amazon.digitalmusicxp.ExpectedFunctionsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ClientMetricsInfo.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f¨\u0006\u000f"}, d2 = {"Lcom/amazon/digitalmusicxp/types/ClientMetricsInfo;", "", "", "toString", "", "hashCode", "other", "", "equals", "contentBlockRef", "Ljava/lang/String;", "getContentBlockRef", "()Ljava/lang/String;", "<init>", "(Ljava/lang/String;)V", "DigitalMusicCloudQueueXP_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class ClientMetricsInfo {
    private final String contentBlockRef;

    public ClientMetricsInfo() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof ClientMetricsInfo) && Intrinsics.areEqual(this.contentBlockRef, ((ClientMetricsInfo) other).contentBlockRef);
    }

    public int hashCode() {
        String str = this.contentBlockRef;
        if (str == null) {
            return 0;
        }
        return str.hashCode();
    }

    public String toString() {
        return "ClientMetricsInfo(contentBlockRef=" + this.contentBlockRef + ')';
    }

    public ClientMetricsInfo(String str) {
        this.contentBlockRef = str;
        ExpectedFunctionsKt.freeze(this);
    }

    public /* synthetic */ ClientMetricsInfo(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str);
    }

    public final String getContentBlockRef() {
        return this.contentBlockRef;
    }
}
