package com.qtxa.qtxadefend;

import android.content.Context;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private JSONObject f135a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f136b;

    o(JSONObject jSONObject) {
        this.f135a = jSONObject;
        this.f136b = jSONObject.getString("cmd");
    }

    String a(String str) {
        return this.f135a.getString(str);
    }

    String b(Context context) throws Throwable {
        BufferedReader bufferedReader = null;
        try {
            String nameForUid = context.getPackageManager().getNameForUid(d("callingUid"));
            if (nameForUid != null && !nameForUid.equals("null")) {
                return nameForUid;
            }
            BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(new FileInputStream("/proc/" + d("callingPid") + "/cmdline")), 1000);
            try {
                String line = bufferedReader2.readLine();
                bufferedReader2.close();
                if (line.indexOf(0) == -1) {
                    try {
                        bufferedReader2.close();
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                    return line;
                }
                String strSubstring = line.substring(0, line.indexOf(0));
                try {
                    bufferedReader2.close();
                } catch (Throwable th2) {
                    th2.printStackTrace();
                }
                return strSubstring;
            } catch (IOException unused) {
                bufferedReader = bufferedReader2;
            } catch (JSONException unused2) {
                bufferedReader = bufferedReader2;
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Throwable th3) {
                        th3.printStackTrace();
                    }
                }
                return "null";
            } catch (Throwable th4) {
                th = th4;
                bufferedReader = bufferedReader2;
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Throwable th5) {
                        th5.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (IOException unused3) {
        } catch (JSONException unused4) {
        } catch (Throwable th6) {
            th = th6;
        }
        if (bufferedReader != null) {
            try {
                bufferedReader.close();
            } catch (Throwable th7) {
                th7.printStackTrace();
            }
        }
        return "null";
    }

    String c() {
        return this.f136b;
    }

    int d(String str) {
        return this.f135a.getInt(str);
    }

    String e() {
        return this.f135a.getString("packageName");
    }

    String f() {
        return this.f135a.getString("packageURI");
    }

    void g(String str, String str2) {
        try {
            this.f135a.put(str, str2);
        } catch (JSONException unused) {
        }
    }

    public String toString() {
        return this.f135a.toString();
    }
}
