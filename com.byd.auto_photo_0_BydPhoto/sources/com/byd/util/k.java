package com.byd.util;

import android.content.Context;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile k f2968a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Context f2969b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private com.byd.event_upload.a f2970c;
    private long d;
    private long e;

    private k(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.f2969b = applicationContext;
        com.byd.event_upload.a aVarD = com.byd.event_upload.a.D(applicationContext);
        this.f2970c = aVarD;
        aVarD.G(null, "BYD_PHOTO");
    }

    private String b(Map<String, Object> map) {
        StringBuilder sb = new StringBuilder("{");
        int size = map.size();
        int i = 0;
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            sb.append("\"");
            sb.append(entry.getKey());
            sb.append("\":");
            Object value = entry.getValue();
            if (value instanceof String) {
                sb.append("\"");
                sb.append(value);
                sb.append("\"");
            } else {
                sb.append(value);
            }
            i++;
            if (i < size) {
                sb.append(",");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    public static k c(Context context) {
        if (f2968a == null) {
            synchronized (k.class) {
                if (f2968a == null) {
                    f2968a = new k(context);
                }
            }
        }
        return f2968a;
    }

    private void f(long j, long j2) {
        HashMap map = new HashMap(4);
        map.put("appStartTime", Long.valueOf(j));
        map.put("appEndTime", Long.valueOf(j2));
        this.f2970c.t("app_st_et", b(map));
    }

    public void a() {
        this.e = 0L;
    }

    public void d() {
        this.f2970c.H();
    }

    public void e() {
        this.f2970c.I();
        this.f2969b = null;
        f2968a = null;
        this.f2970c = null;
    }

    public void g(String str) {
        h(str, null);
    }

    public void h(String str, String str2) {
        StringBuilder sb = new StringBuilder("{");
        sb.append("\"buttonId\":\"");
        sb.append(str);
        sb.append("\"");
        if (str2 != null) {
            sb.append(",\"packageName\":\"");
            sb.append(str2);
            sb.append("\"");
        }
        sb.append("}");
        this.f2970c.t("button_click", sb.toString());
    }

    public void i(String str) {
        if (this.e == 0 || str == null) {
            return;
        }
        HashMap map = new HashMap(5);
        map.put("videoType", str);
        map.put("videoStartTime", Long.valueOf(this.e));
        map.put("videoEndTime", Long.valueOf(System.currentTimeMillis()));
        this.f2970c.t("video_st_et", b(map));
    }

    public void j() {
        this.d = System.currentTimeMillis();
    }

    public void k() {
        f(this.d, System.currentTimeMillis());
    }

    public void l() {
        this.e = System.currentTimeMillis();
    }
}
