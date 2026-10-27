package com.qtxa.clean.e;

import com.qtxa.security.FileEncrypt;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class a {
    private static a k;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f31b;
    private int c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f30a = 0;
    private List<String> d = new ArrayList();
    private List<String> e = new ArrayList();
    private List<String> f = new ArrayList();
    private Map<String, String> g = new HashMap();
    private List<String> h = new ArrayList();
    private Map<String, Integer> i = new HashMap();
    private Map<String, Integer> j = new HashMap();

    private a() {
        p();
        c.k(toString());
    }

    public static a b() {
        if (k == null) {
            synchronized (a.class) {
                k = new a();
            }
        }
        return k;
    }

    private List<String> c(JSONObject jSONObject, String str) {
        ArrayList arrayList = new ArrayList();
        if (jSONObject != null) {
            JSONArray jSONArray = null;
            try {
                jSONArray = jSONObject.getJSONArray(str);
            } catch (JSONException e) {
                r(e);
            }
            if (jSONArray != null) {
                int length = jSONArray.length();
                for (int i = 0; i < length; i++) {
                    try {
                        String strTrim = jSONArray.getString(i).trim();
                        if (!arrayList.contains(strTrim)) {
                            arrayList.add(strTrim);
                        }
                    } catch (JSONException unused) {
                    }
                }
            }
        }
        return arrayList;
    }

    private boolean d(JSONObject jSONObject, String str, boolean z) {
        if (jSONObject != null) {
            try {
                return jSONObject.getBoolean(str);
            } catch (JSONException e) {
                this.r(e);
            }
        }
        return z;
    }

    private int f(JSONObject jSONObject, String str, int i) {
        if (jSONObject != null) {
            try {
                return jSONObject.getInt(str);
            } catch (JSONException e) {
                this.r(e);
            }
        }
        return i;
    }

    private JSONObject g(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        try {
            return new JSONObject(str);
        } catch (JSONException e) {
            r(e);
            return null;
        }
    }

    private JSONObject h(JSONObject jSONObject, String str) {
        if (jSONObject == null) {
            return null;
        }
        try {
            return jSONObject.getJSONObject(str);
        } catch (JSONException e) {
            this.r(e);
            return null;
        }
    }

    private Map<String, Integer> i(JSONObject jSONObject, String str) {
        HashMap map = new HashMap();
        if (jSONObject != null) {
            JSONObject jSONObject2 = null;
            try {
                jSONObject2 = jSONObject.getJSONObject(str);
            } catch (JSONException e) {
                r(e);
            }
            if (jSONObject2 != null) {
                Iterator<String> itKeys = jSONObject2.keys();
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    try {
                        map.put(next, Integer.valueOf(jSONObject2.getInt(next)));
                    } catch (JSONException e2) {
                        r(e2);
                    }
                }
            }
        }
        return map;
    }

    private Map<String, String> j(JSONObject jSONObject, String str) {
        HashMap map = new HashMap();
        if (jSONObject != null) {
            JSONObject jSONObject2 = null;
            try {
                jSONObject2 = jSONObject.getJSONObject(str);
            } catch (JSONException e) {
                r(e);
            }
            if (jSONObject2 != null) {
                Iterator<String> itKeys = jSONObject2.keys();
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    try {
                        map.put(next, jSONObject2.getString(next));
                    } catch (JSONException unused) {
                    }
                }
            }
        }
        return map;
    }

    private String m(JSONObject jSONObject, String str, String str2) {
        if (jSONObject != null) {
            try {
                return jSONObject.getString(str);
            } catch (JSONException e) {
                this.r(e);
            }
        }
        return str2;
    }

    private void p() {
        String strA = FileEncrypt.b().a("/system/qtxa/qtprivacy/proc_policy.conf");
        if (strA == null || strA.isEmpty()) {
            com.qtxa.hidps.b.b.c("file_c empty");
            return;
        }
        c.k("content:" + strA);
        JSONObject jSONObjectG = g(strA);
        this.f30a = f(jSONObjectG, "version", 0);
        s(jSONObjectG, false, 1);
        v(jSONObjectG);
        u(jSONObjectG);
    }

    private void r(Exception exc) {
        if (com.qtxa.hidps.b.b.f65a) {
            exc.printStackTrace();
        }
    }

    private void s(JSONObject jSONObject, boolean z, int i) {
        this.f31b = d(jSONObject, "auto", z);
        String strM = m(jSONObject, "type_auto", null);
        this.c = 0;
        if (strM != null && !strM.isEmpty()) {
            for (String str : strM.split("\\|")) {
                if ("apk".equals(str.trim())) {
                    this.c |= 1;
                } else if ("native".equals(str.trim())) {
                    this.c |= 2;
                }
            }
        }
        if (this.c == 0) {
            this.c = i;
        }
        t(jSONObject);
    }

    private void t(JSONObject jSONObject) {
        String strM = m(jSONObject, "proc_state", "");
        if ("cache".equals(strM)) {
            return;
        }
        "none".equals(strM);
    }

    private void u(JSONObject jSONObject) {
        this.i = i(jSONObject, "mem_config");
        this.j = i(jSONObject, "cpu_config");
    }

    private void v(JSONObject jSONObject) {
        JSONObject jSONObjectH = h(jSONObject, "whitelist");
        List<String> listC = c(jSONObjectH, "packages");
        this.d = listC;
        listC.add("android");
        this.e = c(jSONObjectH, "processes");
        this.f = c(jSONObject, "force_clean_package");
        Map<String, String> mapJ = j(jSONObject, "audio_process");
        this.g = mapJ;
        if (mapJ.containsKey("auto") && this.g.size() != 1) {
            this.g.clear();
            this.g.put("auto", "auto");
        }
        List<String> listC2 = c(jSONObject, "neverclean");
        this.h = listC2;
        listC2.add("system");
        this.h.add("system_server");
        this.h.add("com.android.defcontainer");
    }

    boolean a() {
        return false;
    }

    int e(String str) {
        return 10;
    }

    int k(String str) {
        return 20;
    }

    int l(String str) {
        if (this.g.containsKey("auto")) {
            return 1;
        }
        String str2 = this.g.get(str);
        if ("ongoing".equals(str2)) {
            return 4;
        }
        return "music".equals(str2) ? 2 : 0;
    }

    boolean n(String str) {
        return this.d.contains(str);
    }

    boolean o(String str) {
        return this.e.contains(str);
    }

    boolean q(String str) {
        return this.h.contains(str);
    }

    public String toString() {
        return "CleanPolicy [version=" + this.f30a + ", mAuto=" + this.f31b + ", mAutoSetting=0x" + Integer.toHexString(this.c) + ",\nwhiteListPkgs=" + this.d.toString() + ",\nwhiteListProcesses=" + this.e.toString() + ",\nclean_package:" + this.f.toString() + ",\naudio_packages:" + this.g.toString() + ",\nneverCleanProcesses:" + this.h.toString() + ",\nmMemConfigs=" + this.i.toString() + ",\nmCpuConfigs=" + this.j.toString() + "]";
    }

    boolean w(String str) {
        return this.f.contains(str);
    }

    public boolean x() {
        return false;
    }
}
