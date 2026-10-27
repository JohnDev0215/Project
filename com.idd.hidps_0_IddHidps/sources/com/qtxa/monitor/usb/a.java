package com.qtxa.monitor.usb;

import com.qtxa.qtxadefend.w;
import com.qtxa.report.DefaultDetails;
import com.qtxa.report.JniReportMessage;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class a {

    private static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static a f83a = new a();
    }

    public static a a() {
        return b.f83a;
    }

    public void b(String str) {
        com.qtxa.monitor.c.a.a("LocalUsbManager", "USB_DEVICE_ATTACHED : " + str);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("det", str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        w.i(1306, 3, "USB_DEVICE_ATTACHED:", jSONObject.toString());
    }

    public void c(com.qtxa.monitor.usb.b bVar) {
        com.qtxa.monitor.c.a.a("LocalUsbManager", "notifyUsbStateChange : " + bVar);
        DefaultDetails defaultDetails = new DefaultDetails();
        defaultDetails.details = bVar.toString();
        StringBuilder sb = new StringBuilder();
        sb.append("Event usb state: ");
        sb.append(bVar.f85b == 0 ? "USBCONNECTED" : "USBDISCONNECTED");
        JniReportMessage.java_reportMessage(1, 1406, sb.toString(), 3, defaultDetails);
    }

    private a() {
    }
}
