package com.ecarx.xui.adaptapi.bt;

import android.content.Context;
import com.ecarx.xui.adaptapi.bt.a2dp.IA2dp;
import com.ecarx.xui.adaptapi.bt.avrcp.IAvrcp;
import com.ecarx.xui.adaptapi.bt.gattserver.IGattServer;
import com.ecarx.xui.adaptapi.bt.hfp.IHfp;
import com.ecarx.xui.adaptapi.bt.hid.IHid;
import com.ecarx.xui.adaptapi.bt.map.IMap;
import com.ecarx.xui.adaptapi.bt.pbap.IPbap;
import com.ecarx.xui.adaptapi.bt.settings.IBtSettings;
import com.ecarx.xui.adaptapi.bt.spp.ISpp;

/* JADX INFO: loaded from: classes2.dex */
public abstract class Bt extends AdaptAPI {
    public abstract IA2dp getA2dp();

    public abstract IAvrcp getAvrcp();

    public abstract IBtSettings getBtSettings();

    public abstract IGattServer getGattServer();

    public abstract IHfp getHfp();

    public abstract IHid getHid();

    public abstract IMap getMap();

    public abstract IPbap getPbap();

    public abstract ISpp getSpp();

    public static Bt create(Context context) {
        return new BtImpl(context);
    }
}
