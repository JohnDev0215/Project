package ts.car.iap.protocol.common;

import com.ts.carplay.linkadapter.iap.data.IapConstants;

/* JADX INFO: loaded from: classes.dex */
public enum WifiChannelType {
    WifiChannelUnknown(-1),
    WifiChannel1(1),
    WifiChannel6(6),
    WifiChannel11(11),
    WifiChannel36(36),
    WifiChannel40(40),
    WifiChannel44(44),
    WifiChannel48(48),
    WifiChannel149(IapConstants.WifiChannelType.WIFI_CHANNEL_149),
    WifiChannel153(153),
    WifiChannel157(IapConstants.WifiChannelType.WIFI_CHANNEL_157),
    WifiChannel161(IapConstants.WifiChannelType.WIFI_CHANNEL_161),
    WifiChannel165(165);

    private int mType;

    WifiChannelType(int type) {
        this.mType = type;
    }

    public int getChannelType() {
        return this.mType;
    }

    public static WifiChannelType fromTypeValue(int type) {
        for (WifiChannelType value : values()) {
            if (value.getChannelType() == type) {
                return value;
            }
        }
        return WifiChannelUnknown;
    }
}
