package com.stingray.companion.model;

import A7.m;
import M5.b;
import kotlin.Metadata;
import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B1\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\tHÆ\u0003J=\u0010!\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010%\u001a\u00020\u0006HÖ\u0001J\t\u0010&\u001a\u00020\u0003HÖ\u0001R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\r\"\u0004\b\u0011\u0010\u000fR\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0007\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\r\"\u0004\b\u0017\u0010\u000fR\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001b¨\u0006'"}, d2 = {"Lcom/stingray/companion/model/LobbyInfo;", "", "id", "", "deviceId", "operatorId", "", "clientId", "hostConfig", "Lcom/stingray/companion/model/HostConfig;", "<init>", "(Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Lcom/stingray/companion/model/HostConfig;)V", "getId", "()Ljava/lang/String;", "setId", "(Ljava/lang/String;)V", "getDeviceId", "setDeviceId", "getOperatorId", "()I", "setOperatorId", "(I)V", "getClientId", "setClientId", "getHostConfig", "()Lcom/stingray/companion/model/HostConfig;", "setHostConfig", "(Lcom/stingray/companion/model/HostConfig;)V", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "companion_envProdRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final /* data */ class LobbyInfo {
    private String clientId;
    private String deviceId;
    private HostConfig hostConfig;
    private String id;
    private int operatorId;

    public LobbyInfo(String str, String str2, int i7, String str3, HostConfig hostConfig) {
        m.f(str2, "deviceId");
        m.f(str3, "clientId");
        m.f(hostConfig, "hostConfig");
        this.id = str;
        this.deviceId = str2;
        this.operatorId = i7;
        this.clientId = str3;
        this.hostConfig = hostConfig;
    }

    public static /* synthetic */ LobbyInfo copy$default(LobbyInfo lobbyInfo, String str, String str2, int i7, String str3, HostConfig hostConfig, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = lobbyInfo.id;
        }
        if ((i10 & 2) != 0) {
            str2 = lobbyInfo.deviceId;
        }
        String str4 = str2;
        if ((i10 & 4) != 0) {
            i7 = lobbyInfo.operatorId;
        }
        int i11 = i7;
        if ((i10 & 8) != 0) {
            str3 = lobbyInfo.clientId;
        }
        String str5 = str3;
        if ((i10 & 16) != 0) {
            hostConfig = lobbyInfo.hostConfig;
        }
        return lobbyInfo.copy(str, str4, i11, str5, hostConfig);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getDeviceId() {
        return this.deviceId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getOperatorId() {
        return this.operatorId;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getClientId() {
        return this.clientId;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final HostConfig getHostConfig() {
        return this.hostConfig;
    }

    public final LobbyInfo copy(String id, String deviceId, int operatorId, String clientId, HostConfig hostConfig) {
        m.f(deviceId, "deviceId");
        m.f(clientId, "clientId");
        m.f(hostConfig, "hostConfig");
        return new LobbyInfo(id, deviceId, operatorId, clientId, hostConfig);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LobbyInfo)) {
            return false;
        }
        LobbyInfo lobbyInfo = (LobbyInfo) other;
        return m.a(this.id, lobbyInfo.id) && m.a(this.deviceId, lobbyInfo.deviceId) && this.operatorId == lobbyInfo.operatorId && m.a(this.clientId, lobbyInfo.clientId) && m.a(this.hostConfig, lobbyInfo.hostConfig);
    }

    public final String getClientId() {
        return this.clientId;
    }

    public final String getDeviceId() {
        return this.deviceId;
    }

    public final HostConfig getHostConfig() {
        return this.hostConfig;
    }

    public final String getId() {
        return this.id;
    }

    public final int getOperatorId() {
        return this.operatorId;
    }

    public int hashCode() {
        String str = this.id;
        return this.hostConfig.hashCode() + b.c(this.clientId, AbstractC2641j.b(this.operatorId, b.c(this.deviceId, (str == null ? 0 : str.hashCode()) * 31, 31), 31), 31);
    }

    public final void setClientId(String str) {
        m.f(str, "<set-?>");
        this.clientId = str;
    }

    public final void setDeviceId(String str) {
        m.f(str, "<set-?>");
        this.deviceId = str;
    }

    public final void setHostConfig(HostConfig hostConfig) {
        m.f(hostConfig, "<set-?>");
        this.hostConfig = hostConfig;
    }

    public final void setId(String str) {
        this.id = str;
    }

    public final void setOperatorId(int i7) {
        this.operatorId = i7;
    }

    public String toString() {
        String str = this.id;
        String str2 = this.deviceId;
        int i7 = this.operatorId;
        String str3 = this.clientId;
        HostConfig hostConfig = this.hostConfig;
        StringBuilder sb = new StringBuilder("LobbyInfo(id=");
        sb.append(str);
        sb.append(", deviceId=");
        sb.append(str2);
        sb.append(", operatorId=");
        b.p(sb, i7, ", clientId=", str3, ", hostConfig=");
        sb.append(hostConfig);
        sb.append(")");
        return sb.toString();
    }
}
