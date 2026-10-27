package com.loostone.libtuning.data.bean;

import OooO00o.OooO00o.OooO0O0.OooO0o0.OooO00o;
import OooO00o.OooO00o.OooO0O0.a;
import OooO00o.OooO00o.OooO0Oo.OooO0OO.d.c;
import OooO00o.OooO00o.OooO0Oo.OooO0OO.d.e;
import OooO00o.OooO00o.OooO0Oo.OooO0Oo.f.i;
import OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.OooO0o;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
public final class TuningInfo {
    private String channel;
    private int configVersion;
    private String deviceUuid;
    private String host;
    private String mac;
    private String modelName;
    private String modelUuid;
    private String modelVersion;
    private String tuningBranch;

    public TuningInfo() {
        OooO0o oooO0o = OooO0o.f425a;
        String strA = oooO0o.A().a();
        this.modelName = strA == null ? "byd_default" : strA;
        OooO00o oooO00o = OooO00o.f18a;
        a aVar = a.f39a;
        this.channel = oooO00o.d(aVar.a());
        this.tuningBranch = "third_byd";
        this.host = i.f162a.i();
        this.mac = e.d(aVar.a()).toString();
        Integer numA = oooO0o.i().a();
        this.configVersion = numA == null ? 1 : numA.intValue();
        String cacheValue = getCacheValue("tv", "net_config_version");
        if (cacheValue != null) {
            setConfigVersion(Integer.parseInt(cacheValue));
        }
        this.deviceUuid = getCacheValue(this.mac, "deviceUuid");
        this.modelUuid = getCacheValue(this.mac, "modelUuid");
        this.modelVersion = getCacheValue(this.mac, "modelVersion");
    }

    private final String getCacheValue(String str, String str2) {
        return c.a(a.f39a.a(), h.i(str, str2));
    }

    public final String getChannel() {
        return this.channel;
    }

    public final int getConfigVersion() {
        return this.configVersion;
    }

    public final String getDeviceUuid() {
        return this.deviceUuid;
    }

    public final String getHost() {
        return this.host;
    }

    public final String getMac() {
        return this.mac;
    }

    public final String getModelName() {
        return this.modelName;
    }

    public final String getModelUuid() {
        return this.modelUuid;
    }

    public final String getModelVersion() {
        return this.modelVersion;
    }

    public final String getTuningBranch() {
        return this.tuningBranch;
    }

    public final void setChannel(String str) {
        h.d(str, "<set-?>");
        this.channel = str;
    }

    public final void setConfigVersion(int i) {
        this.configVersion = i;
    }

    public final void setDeviceUuid(String str) {
        this.deviceUuid = str;
    }

    public final void setHost(String str) {
        h.d(str, "<set-?>");
        this.host = str;
    }

    public final void setMac(String str) {
        h.d(str, "<set-?>");
        this.mac = str;
    }

    public final void setModelName(String str) {
        h.d(str, "<set-?>");
        this.modelName = str;
    }

    public final void setModelUuid(String str) {
        this.modelUuid = str;
    }

    public final void setModelVersion(String str) {
        this.modelVersion = str;
    }

    public final void setTuningBranch(String str) {
        h.d(str, "<set-?>");
        this.tuningBranch = str;
    }
}
