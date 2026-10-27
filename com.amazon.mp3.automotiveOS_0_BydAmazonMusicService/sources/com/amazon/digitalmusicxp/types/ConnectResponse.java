package com.amazon.digitalmusicxp.types;

import androidx.core.app.NotificationCompat;
import com.amazon.digitalmusicxp.ExpectedFunctionsKt;
import com.amazon.digitalmusicxp.enums.ConnectResponseStatusEnum;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ConnectResponse.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Lcom/amazon/digitalmusicxp/types/ConnectResponse;", "", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/amazon/digitalmusicxp/enums/ConnectResponseStatusEnum;", NotificationCompat.CATEGORY_STATUS, "Lcom/amazon/digitalmusicxp/enums/ConnectResponseStatusEnum;", "getStatus", "()Lcom/amazon/digitalmusicxp/enums/ConnectResponseStatusEnum;", "<init>", "(Lcom/amazon/digitalmusicxp/enums/ConnectResponseStatusEnum;)V", "DigitalMusicCloudQueueXP_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class ConnectResponse {
    private final ConnectResponseStatusEnum status;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof ConnectResponse) && this.status == ((ConnectResponse) other).status;
    }

    public int hashCode() {
        return this.status.hashCode();
    }

    public String toString() {
        return "ConnectResponse(status=" + this.status + ')';
    }

    public ConnectResponse(ConnectResponseStatusEnum status) {
        Intrinsics.checkNotNullParameter(status, "status");
        this.status = status;
        ExpectedFunctionsKt.freeze(this);
    }
}
