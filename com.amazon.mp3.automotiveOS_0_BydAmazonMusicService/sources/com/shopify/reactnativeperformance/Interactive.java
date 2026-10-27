package com.shopify.reactnativeperformance;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Interactive.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\t\u001a\u00020\u0002¢\u0006\u0004\b\n\u0010\u000bJ\b\u0010\u0003\u001a\u00020\u0002H\u0016R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\f"}, d2 = {"Lcom/shopify/reactnativeperformance/Interactive;", "", "", "toString", "", "boolValue", "Z", "getBoolValue", "()Z", "stringValue", "<init>", "(Ljava/lang/String;)V", "shopify_react-native-performance_release"}, k = 1, mv = {1, 6, 0})
public final class Interactive {
    private final boolean boolValue;

    public Interactive(String stringValue) {
        Intrinsics.checkNotNullParameter(stringValue, "stringValue");
        if (Intrinsics.areEqual(stringValue, "TRUE")) {
            this.boolValue = true;
            return;
        }
        if (Intrinsics.areEqual(stringValue, "FALSE")) {
            this.boolValue = false;
            return;
        }
        throw new IllegalArgumentException("The only allowed values for the 'interactive' prop are 'TRUE' and 'FALSE', but it was '" + stringValue + "'.");
    }

    public String toString() {
        return this.boolValue ? "TRUE" : "FALSE";
    }
}
