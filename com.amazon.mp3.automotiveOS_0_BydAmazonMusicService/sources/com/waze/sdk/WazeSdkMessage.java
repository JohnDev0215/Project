package com.waze.sdk;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import java.util.HashMap;

/* JADX INFO: loaded from: classes7.dex */
class WazeSdkMessage {
    WazeSdkMessage() {
    }

    static Message openWaze(String str) {
        Message messageObtain = Message.obtain((Handler) null, 101);
        Bundle bundle = new Bundle();
        bundle.putString("token", str);
        messageObtain.setData(bundle);
        return messageObtain;
    }

    static Message requestNavigationData(String str, boolean z) {
        Message messageObtain = Message.obtain((Handler) null, 102);
        Bundle bundle = new Bundle();
        bundle.putString("token", str);
        bundle.putBoolean("request", z);
        messageObtain.setData(bundle);
        return messageObtain;
    }

    static Message sendStats(String str, String str2, HashMap<String, String> map) {
        Message messageObtain = Message.obtain((Handler) null, 106);
        Bundle bundle = new Bundle();
        bundle.putString("token", str);
        bundle.putString("statsEvent", str2);
        bundle.putSerializable("statsParams", map);
        messageObtain.setData(bundle);
        return messageObtain;
    }

    static Message disconnect(String str) {
        Message messageObtain = Message.obtain((Handler) null, 103);
        Bundle bundle = new Bundle();
        bundle.putString("token", str);
        messageObtain.setData(bundle);
        return messageObtain;
    }
}
