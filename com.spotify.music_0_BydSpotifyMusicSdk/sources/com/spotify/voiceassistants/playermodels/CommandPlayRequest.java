package com.spotify.voiceassistants.playermodels;

import kotlin.jvm.internal.DefaultConstructorMarker;
import p.c71;
import p.hf2;
import p.u4;
import p.v03;
import p.z61;

/* JADX INFO: loaded from: classes.dex */
@c71(generateAdapter = true)
public final class CommandPlayRequest {
    private final String action;
    private final String deviceTypes;
    private final boolean voiceEnabled;

    public CommandPlayRequest(@z61(name = "action") String str, @z61(name = "device_type") String str2, @z61(name = "voice_enabled") boolean z) {
        v03.j(str, "action");
        v03.j(str2, "deviceTypes");
        this.action = str;
        this.deviceTypes = str2;
        this.voiceEnabled = z;
    }

    public static /* synthetic */ CommandPlayRequest copy$default(CommandPlayRequest commandPlayRequest, String str, String str2, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = commandPlayRequest.action;
        }
        if ((i & 2) != 0) {
            str2 = commandPlayRequest.deviceTypes;
        }
        if ((i & 4) != 0) {
            z = commandPlayRequest.voiceEnabled;
        }
        return commandPlayRequest.copy(str, str2, z);
    }

    public final String component1() {
        return this.action;
    }

    public final String component2() {
        return this.deviceTypes;
    }

    public final boolean component3() {
        return this.voiceEnabled;
    }

    public final CommandPlayRequest copy(@z61(name = "action") String str, @z61(name = "device_type") String str2, @z61(name = "voice_enabled") boolean z) {
        v03.j(str, "action");
        v03.j(str2, "deviceTypes");
        return new CommandPlayRequest(str, str2, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CommandPlayRequest)) {
            return false;
        }
        CommandPlayRequest commandPlayRequest = (CommandPlayRequest) obj;
        return v03.b(this.action, commandPlayRequest.action) && v03.b(this.deviceTypes, commandPlayRequest.deviceTypes) && this.voiceEnabled == commandPlayRequest.voiceEnabled;
    }

    public final String getAction() {
        return this.action;
    }

    public final String getDeviceTypes() {
        return this.deviceTypes;
    }

    public final boolean getVoiceEnabled() {
        return this.voiceEnabled;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v2, types: [int] */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4 */
    public int hashCode() {
        int iB = hf2.b(this.deviceTypes, this.action.hashCode() * 31, 31);
        boolean z = this.voiceEnabled;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        return iB + r1;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("CommandPlayRequest(action=");
        sb.append(this.action);
        sb.append(", deviceTypes=");
        sb.append(this.deviceTypes);
        sb.append(", voiceEnabled=");
        return u4.n(sb, this.voiceEnabled, ')');
    }

    public /* synthetic */ CommandPlayRequest(String str, String str2, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? true : z);
    }
}
