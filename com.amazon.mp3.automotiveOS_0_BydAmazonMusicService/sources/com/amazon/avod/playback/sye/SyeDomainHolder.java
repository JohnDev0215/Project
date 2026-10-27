package com.amazon.avod.playback.sye;

import com.netinsight.sye.syeClient.SyeDomain;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SyeDomainVendingMachine.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\t\n\u0002\b\t\b\u0086\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000e\u001a\u00020\u0002\u0012\u0006\u0010\u0012\u001a\u00020\u0002\u0012\u0006\u0010\u0014\u001a\u00020\u0002\u0012\u0006\u0010\u0016\u001a\u00020\u0004\u0012\u0006\u0010\u001b\u001a\u00020\u001a\u0012\u0006\u0010\u001f\u001a\u00020\u001a¢\u0006\u0004\b!\u0010\"J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0012\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u000f\u001a\u0004\b\u0013\u0010\u0011R\u0017\u0010\u0014\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u000f\u001a\u0004\b\u0015\u0010\u0011R\u0017\u0010\u0016\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R\u0017\u0010\u001b\u001a\u00020\u001a8\u0006¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u0017\u0010\u001f\u001a\u00020\u001a8\u0006¢\u0006\f\n\u0004\b\u001f\u0010\u001c\u001a\u0004\b \u0010\u001e¨\u0006#"}, d2 = {"Lcom/amazon/avod/playback/sye/SyeDomainHolder;", "", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/netinsight/sye/syeClient/SyeDomain;", "syeDomain", "Lcom/netinsight/sye/syeClient/SyeDomain;", "getSyeDomain", "()Lcom/netinsight/sye/syeClient/SyeDomain;", "token", "Ljava/lang/String;", "getToken", "()Ljava/lang/String;", "channelId", "getChannelId", "consumptionId", "getConsumptionId", "dvrDurationMillis", "I", "getDvrDurationMillis", "()I", "", "startUtcMillis", "J", "getStartUtcMillis", "()J", "durationMillis", "getDurationMillis", "<init>", "(Lcom/netinsight/sye/syeClient/SyeDomain;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IJJ)V", "ATVAndroidPlayback_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class SyeDomainHolder {
    private final String channelId;
    private final String consumptionId;
    private final long durationMillis;
    private final int dvrDurationMillis;
    private final long startUtcMillis;
    private final SyeDomain syeDomain;
    private final String token;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SyeDomainHolder)) {
            return false;
        }
        SyeDomainHolder syeDomainHolder = (SyeDomainHolder) other;
        return Intrinsics.areEqual(this.syeDomain, syeDomainHolder.syeDomain) && Intrinsics.areEqual(this.token, syeDomainHolder.token) && Intrinsics.areEqual(this.channelId, syeDomainHolder.channelId) && Intrinsics.areEqual(this.consumptionId, syeDomainHolder.consumptionId) && this.dvrDurationMillis == syeDomainHolder.dvrDurationMillis && this.startUtcMillis == syeDomainHolder.startUtcMillis && this.durationMillis == syeDomainHolder.durationMillis;
    }

    public int hashCode() {
        return (((((((((((this.syeDomain.hashCode() * 31) + this.token.hashCode()) * 31) + this.channelId.hashCode()) * 31) + this.consumptionId.hashCode()) * 31) + Integer.hashCode(this.dvrDurationMillis)) * 31) + Long.hashCode(this.startUtcMillis)) * 31) + Long.hashCode(this.durationMillis);
    }

    public String toString() {
        return "SyeDomainHolder(syeDomain=" + this.syeDomain + ", token=" + this.token + ", channelId=" + this.channelId + ", consumptionId=" + this.consumptionId + ", dvrDurationMillis=" + this.dvrDurationMillis + ", startUtcMillis=" + this.startUtcMillis + ", durationMillis=" + this.durationMillis + ')';
    }

    public SyeDomainHolder(SyeDomain syeDomain, String token, String channelId, String consumptionId, int i, long j, long j2) {
        Intrinsics.checkNotNullParameter(syeDomain, "syeDomain");
        Intrinsics.checkNotNullParameter(token, "token");
        Intrinsics.checkNotNullParameter(channelId, "channelId");
        Intrinsics.checkNotNullParameter(consumptionId, "consumptionId");
        this.syeDomain = syeDomain;
        this.token = token;
        this.channelId = channelId;
        this.consumptionId = consumptionId;
        this.dvrDurationMillis = i;
        this.startUtcMillis = j;
        this.durationMillis = j2;
    }

    public final SyeDomain getSyeDomain() {
        return this.syeDomain;
    }

    public final String getToken() {
        return this.token;
    }

    public final String getChannelId() {
        return this.channelId;
    }

    public final String getConsumptionId() {
        return this.consumptionId;
    }

    public final int getDvrDurationMillis() {
        return this.dvrDurationMillis;
    }

    public final long getStartUtcMillis() {
        return this.startUtcMillis;
    }

    public final long getDurationMillis() {
        return this.durationMillis;
    }
}
