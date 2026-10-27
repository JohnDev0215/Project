package com.ecarx.xui.adaptapi.wifiap;

import android.content.Context;
import com.ecarx.xui.adaptapi.AdaptAPI;
import com.ecarx.xui.adaptapi.sign.SignVerify;
import com.ecarx.xui.adaptapi.wifiap.impl.WifiApImpl;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public abstract class WifiAp extends AdaptAPI {
    public abstract int getMaxConnections();

    public abstract IWifiAPHost getWifiAPHost();

    public abstract List<IWifiApClient> getWifiApClients();

    public abstract boolean setMaxConnections(int i);

    public abstract boolean setWifiApClientCallback(IWifiApClientCallback iWifiApClientCallback);

    public abstract boolean unsetWifiApClientCallback(IWifiApClientCallback iWifiApClientCallback);

    public static WifiAp create(Context context) {
        if (SignVerify.instance().appVerify(context)) {
            return new WifiApImpl(context);
        }
        return null;
    }
}
