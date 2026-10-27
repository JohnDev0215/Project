package com.android.bluetooth.mapclient;

import android.os.SystemProperties;
import com.android.bluetooth.Utils;
import com.android.bluetooth.mapclient.Bmessage;

/* JADX INFO: loaded from: classes.dex */
class MapUtils {
    private static final String FETCH_MESSAGE_TYPE = "persist.bluetooth.pts.mapclient.fetchmessagetype";
    private static final String SEND_MESSAGE_TYPE = "persist.bluetooth.pts.mapclient.sendmessagetype";
    private static MnsService sMnsService = null;

    MapUtils() {
    }

    static void setMnsService(MnsService service) {
        sMnsService = service;
    }

    static MnsService newMnsServiceInstance(MapClientService mapClientService) {
        MnsService mnsService = sMnsService;
        return mnsService == null ? new MnsService(mapClientService) : mnsService;
    }

    static byte fetchMessageType() {
        if (Utils.isPtsTestMode()) {
            return (byte) SystemProperties.getInt(FETCH_MESSAGE_TYPE, 0);
        }
        return (byte) 0;
    }

    static Bmessage.Type sendMessageType() {
        int messageType;
        if (Utils.isPtsTestMode() && (messageType = SystemProperties.getInt(SEND_MESSAGE_TYPE, -1)) > 0 && messageType < Bmessage.Type.values().length) {
            return Bmessage.Type.values()[messageType];
        }
        return Bmessage.Type.MMS;
    }
}
