package com.amazon.digitalmusicxp.types;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ConnectionInfoRequestBody.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0011\b\u0016\u0012\u0006\u0010\t\u001a\u00020\u0002¢\u0006\u0004\b\n\u0010\bR$\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u000b"}, d2 = {"Lcom/amazon/digitalmusicxp/types/ConnectionInfoRequestBody;", "", "", "customerId", "Ljava/lang/String;", "getCustomerId", "()Ljava/lang/String;", "setCustomerId", "(Ljava/lang/String;)V", "id", "<init>", "DigitalMusicCloudQueueXP_release"}, k = 1, mv = {1, 7, 1})
public final class ConnectionInfoRequestBody {
    private String customerId;

    public ConnectionInfoRequestBody(String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        this.customerId = id;
    }
}
