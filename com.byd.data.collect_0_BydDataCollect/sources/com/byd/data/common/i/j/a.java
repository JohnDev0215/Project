package com.byd.data.common.i.j;

import android.text.TextUtils;
import com.byd.data.common.entiry.cloud.config.CountryBean;
import com.byd.data.common.http.ApiRetrofit;
import com.byd.data.common.i.f;
import com.byd.data.common.i.g;
import com.google.gson.Gson;
import f.a0;
import f.e0;
import f.f0;
import f.x;
import java.util.Map;
import java.util.TreeMap;
import org.json.JSONObject;

/* JADX INFO: compiled from: HttpUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f3432a = "HttpUtils" + com.byd.data.common.i.b.f3423a;

    private static void a(TreeMap<String, Object> treeMap, byte b2) {
        String strC = com.byd.data.common.g.a.i().g().c();
        String strB = g.b("uuid");
        if (TextUtils.isEmpty(strC) && TextUtils.isEmpty(strB)) {
            treeMap.put("uuid", null);
        } else if (!TextUtils.isEmpty(strC)) {
            treeMap.put("uuid", strC);
        } else if (TextUtils.isEmpty(strB)) {
            treeMap.put("uuid", null);
        } else {
            treeMap.put("uuid", strB);
        }
        treeMap.put("version", "1.3.1");
        treeMap.put("branch", (byte) 3);
        treeMap.put("transportMode", Byte.valueOf(b2));
    }

    public static a0.a b(a0.a aVar) {
        TreeMap<String, Object> treeMapC = c((byte) 2);
        treeMapC.remove("transport");
        for (Map.Entry<String, Object> entry : treeMapC.entrySet()) {
            aVar.a(entry.getKey(), entry.getValue().toString());
        }
        return aVar;
    }

    public static TreeMap<String, Object> c(byte b2) {
        CountryBean countryBeanG = ApiRetrofit.h().g();
        TreeMap<String, Object> treeMap = new TreeMap<>();
        treeMap.put("iccid", com.byd.data.common.g.a.i().j().y());
        treeMap.put("vin", e());
        com.byd.data.common.server.b bVarE = com.byd.data.common.server.b.e();
        treeMap.put("vehicleCode", Short.valueOf(bVarE.o));
        treeMap.put("countryCode", Short.valueOf(countryBeanG.countryCode));
        treeMap.put(b2 == 2 ? "mccmnc" : "mccMnc", Integer.valueOf(com.byd.data.common.g.a.i().j().z()));
        byte bI = bVarE.i();
        if (b2 == 1) {
            a(treeMap, bI);
        } else if (b2 == 2) {
            treeMap.put("transport", Boolean.valueOf(bI == 1));
            treeMap.put("powerType", Byte.valueOf(bVarE.f3458c));
        }
        if (com.byd.data.common.server.b.e().p()) {
            f.b(f3432a, "postParam =" + new JSONObject(treeMap));
        }
        return treeMap;
    }

    public static x.a d(e0 e0Var) {
        CountryBean countryBeanG = ApiRetrofit.h().g();
        com.byd.data.common.g.a aVarI = com.byd.data.common.g.a.i();
        return e0Var.i().p().b("uuid", aVarI.g().c()).b("iccid", aVarI.j().y()).b("vin", e()).b("countryCode", Short.toString(countryBeanG.countryCode)).b("mccMnc", Integer.toString(com.byd.data.common.g.a.i().j().z())).b("version", "1.3.1").b("devices", "android");
    }

    public static String e() {
        String strI = com.byd.data.common.g.a.i().f().i();
        return (strI == null || strI.startsWith("0000")) ? "LC0CF4CD6P0116644" : strI;
    }

    public static a0.b f(int i2) {
        TreeMap<String, Object> treeMapC = c((byte) 1);
        treeMapC.put("carType", Short.valueOf(com.byd.data.common.server.b.e().o));
        treeMapC.put("count", Integer.valueOf(i2));
        treeMapC.put("fileType", (byte) 1);
        return a0.b.c("MAPPARAM", new Gson().r(treeMapC));
    }

    public static f0 g(byte b2) {
        return f0.d(com.byd.data.common.b.d.b.f3297a, new JSONObject(c(b2)).toString());
    }
}
