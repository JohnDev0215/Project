package com.engine.tbox.netd;

import android.os.SystemProperties;
import com.engine.tbox.core.utils.LogUtils;

/* JADX INFO: loaded from: classes.dex */
public class NetdClient {
    private static final int CARD_MODE_DOUBLE_BYD = 1;
    private static final int CARD_MODE_OTHER = 4;
    private static final int CARD_MODE_SINGLE_BYD = 2;
    private static final int MSG_NET_LIMIT = 3;
    private static final int MSG_SET_CARD_MODE = 4;
    private static final String PROP_PERSIST_SIM_CARD_TYPE = "persist.sim.card.type";
    private static final String TAG = NetdClient.class.getSimpleName();
    private final DscClientSocket mDscClientSocket = new DscClientSocket();

    public void setCardMode(int mode) {
        this.mDscClientSocket.sendString(4, "setCardMode " + mode);
    }

    public void setSignalApn() {
        LogUtils.w(TAG, "setSignalApn", new Object[0]);
        setCardMode(2);
        SystemProperties.set(PROP_PERSIST_SIM_CARD_TYPE, "2");
    }

    public void setDoubleApn() {
        LogUtils.w(TAG, "setDoubleApn", new Object[0]);
        setCardMode(1);
        SystemProperties.set(PROP_PERSIST_SIM_CARD_TYPE, "1");
    }

    public void setOtherCard() {
        LogUtils.w(TAG, "setOtherCard", new Object[0]);
        setCardMode(4);
        SystemProperties.set(PROP_PERSIST_SIM_CARD_TYPE, "4");
    }

    public void netLimit(boolean enable) {
        StringBuilder sb = new StringBuilder();
        sb.append("netLimit");
        sb.append(" ");
        sb.append(enable ? "enable" : "disable");
        this.mDscClientSocket.sendString(3, sb.toString());
    }
}
