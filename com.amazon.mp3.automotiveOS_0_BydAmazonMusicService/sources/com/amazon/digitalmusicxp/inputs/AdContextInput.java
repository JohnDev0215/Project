package com.amazon.digitalmusicxp.inputs;

import com.amazon.digitalmusicxp.ExpectedFunctionsKt;
import com.amazon.digitalmusicxp.enums.DeviceFamilyEnum;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AdContextInput.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0014\u0010\u0015J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u0019\u0010\u0012\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0012\u0010\n\u001a\u0004\b\u0013\u0010\f¨\u0006\u0016"}, d2 = {"Lcom/amazon/digitalmusicxp/inputs/AdContextInput;", "", "", "toString", "", "hashCode", "other", "", "equals", "userAgent", "Ljava/lang/String;", "getUserAgent", "()Ljava/lang/String;", "Lcom/amazon/digitalmusicxp/enums/DeviceFamilyEnum;", "deviceFamily", "Lcom/amazon/digitalmusicxp/enums/DeviceFamilyEnum;", "getDeviceFamily", "()Lcom/amazon/digitalmusicxp/enums/DeviceFamilyEnum;", "advertiserId", "getAdvertiserId", "<init>", "(Ljava/lang/String;Lcom/amazon/digitalmusicxp/enums/DeviceFamilyEnum;Ljava/lang/String;)V", "DigitalMusicCloudQueueXP_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class AdContextInput {
    private final String advertiserId;
    private final DeviceFamilyEnum deviceFamily;
    private final String userAgent;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AdContextInput)) {
            return false;
        }
        AdContextInput adContextInput = (AdContextInput) other;
        return Intrinsics.areEqual(this.userAgent, adContextInput.userAgent) && this.deviceFamily == adContextInput.deviceFamily && Intrinsics.areEqual(this.advertiserId, adContextInput.advertiserId);
    }

    public int hashCode() {
        int iHashCode = ((this.userAgent.hashCode() * 31) + this.deviceFamily.hashCode()) * 31;
        String str = this.advertiserId;
        return iHashCode + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        return "AdContextInput(userAgent=" + this.userAgent + ", deviceFamily=" + this.deviceFamily + ", advertiserId=" + this.advertiserId + ')';
    }

    public AdContextInput(String userAgent, DeviceFamilyEnum deviceFamily, String str) {
        Intrinsics.checkNotNullParameter(userAgent, "userAgent");
        Intrinsics.checkNotNullParameter(deviceFamily, "deviceFamily");
        this.userAgent = userAgent;
        this.deviceFamily = deviceFamily;
        this.advertiserId = str;
        ExpectedFunctionsKt.freeze(this);
    }

    public final String getUserAgent() {
        return this.userAgent;
    }

    public final DeviceFamilyEnum getDeviceFamily() {
        return this.deviceFamily;
    }

    public final String getAdvertiserId() {
        return this.advertiserId;
    }
}
