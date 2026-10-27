package com.stingray.companion.model;

import A7.m;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\n\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0005¨\u0006\u0012"}, d2 = {"Lcom/stingray/companion/model/HostInfo;", "", "hostStatus", "Lcom/stingray/companion/model/HostStatus;", "<init>", "(Lcom/stingray/companion/model/HostStatus;)V", "getHostStatus", "()Lcom/stingray/companion/model/HostStatus;", "setHostStatus", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "companion_envProdRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final /* data */ class HostInfo {
    private HostStatus hostStatus;

    public HostInfo(HostStatus hostStatus) {
        m.f(hostStatus, "hostStatus");
        this.hostStatus = hostStatus;
    }

    public static /* synthetic */ HostInfo copy$default(HostInfo hostInfo, HostStatus hostStatus, int i7, Object obj) {
        if ((i7 & 1) != 0) {
            hostStatus = hostInfo.hostStatus;
        }
        return hostInfo.copy(hostStatus);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final HostStatus getHostStatus() {
        return this.hostStatus;
    }

    public final HostInfo copy(HostStatus hostStatus) {
        m.f(hostStatus, "hostStatus");
        return new HostInfo(hostStatus);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof HostInfo) && this.hostStatus == ((HostInfo) other).hostStatus;
    }

    public final HostStatus getHostStatus() {
        return this.hostStatus;
    }

    public int hashCode() {
        return this.hostStatus.hashCode();
    }

    public final void setHostStatus(HostStatus hostStatus) {
        m.f(hostStatus, "<set-?>");
        this.hostStatus = hostStatus;
    }

    public String toString() {
        return "HostInfo(hostStatus=" + this.hostStatus + ")";
    }
}
