package ts.car.service.ncm.data;

import com.ts.androidauto.fsdk.common.AndroidAutoConst;

/* JADX INFO: loaded from: classes2.dex */
public final class IfaceInfo {
    public String ifaceName = AndroidAutoConst.DEFAULT_SCREEN_NAME;
    public int ifaceType = -1;
    public IfaceState ifaceState = IfaceState.UNKNOWN;
    public IpConfiguration ipConfig = new IpConfiguration();

    public enum IfaceState {
        UNKNOWN,
        UP,
        DOWN
    }

    public void setIfaceName(String ifaceName) {
        this.ifaceName = ifaceName;
    }

    public void setIfaceType(int ifaceType) {
        this.ifaceType = ifaceType;
    }

    public void setIfaceState(IfaceState ifaceState) {
        this.ifaceState = ifaceState;
    }

    public void setIpConfig(IpConfiguration ipConfig) {
        this.ipConfig = ipConfig;
    }

    public String getIfaceName() {
        return this.ifaceName;
    }

    public int getIfaceType() {
        return this.ifaceType;
    }

    public IfaceState getIfaceState() {
        return this.ifaceState;
    }

    public IpConfiguration getIpConfig() {
        return this.ipConfig;
    }
}
