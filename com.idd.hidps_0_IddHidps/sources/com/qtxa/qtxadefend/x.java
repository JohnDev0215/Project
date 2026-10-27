package com.qtxa.qtxadefend;

import android.os.RemoteCallbackList;
import com.qtxa.qtcarsecsvc.ISystemCallBack;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
final class x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private RemoteCallbackList<ISystemCallBack> f150a = new RemoteCallbackList<>();

    x(u uVar) {
    }

    void a(ISystemCallBack iSystemCallBack) {
        if (iSystemCallBack != null) {
            this.f150a.register(iSystemCallBack);
        }
    }

    synchronized String b(String str) {
        String str2;
        int iBeginBroadcast = this.f150a.beginBroadcast();
        str2 = null;
        String strCall = null;
        for (int i = 0; i < iBeginBroadcast; i++) {
            try {
                strCall = ((ISystemCallBack) this.f150a.getBroadcastItem(i)).call(str);
            } catch (Exception unused) {
            }
        }
        str2 = strCall;
        this.f150a.finishBroadcast();
        return str2;
    }

    void c(String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("cmd", "deletePackage");
            jSONObject.put("packageName", str);
            b(jSONObject.toString());
        } catch (JSONException unused) {
        }
    }

    void d(String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("cmd", "killPackage");
            jSONObject.put("packageName", str);
            b(jSONObject.toString());
        } catch (JSONException unused) {
        }
    }
}
