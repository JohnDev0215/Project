package p;

import android.content.SharedPreferences;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class hc2 implements ah2 {
    public final Object c;

    public /* synthetic */ hc2(Object obj) {
        this.c = obj;
    }

    public final cc2 a(JSONObject jSONObject) {
        kc2 ynVar;
        int i = jSONObject.getInt("settings_version");
        if (i != 3) {
            Log.e("FirebaseCrashlytics", "Could not determine SettingsJsonTransform for settings version " + i + ". Using default settings values.", null);
            ynVar = new z90(19);
        } else {
            ynVar = new yn(20);
        }
        return ynVar.g((yn) this.c, jSONObject);
    }

    public final JSONObject b() {
        Throwable th;
        FileInputStream fileInputStream;
        JSONObject jSONObject;
        Log.isLoggable("FirebaseCrashlytics", 3);
        FileInputStream fileInputStream2 = null;
        try {
            try {
                File file = (File) this.c;
                if (file.exists()) {
                    fileInputStream = new FileInputStream(file);
                    try {
                        jSONObject = new JSONObject(zu.G0(fileInputStream));
                        fileInputStream2 = fileInputStream;
                    } catch (Exception e) {
                        e = e;
                        Log.e("FirebaseCrashlytics", "Failed to fetch cached settings", e);
                        zu.q(fileInputStream, "Error while closing settings cache file.");
                        return null;
                    }
                } else {
                    Log.isLoggable("FirebaseCrashlytics", 2);
                    jSONObject = null;
                }
                zu.q(fileInputStream2, "Error while closing settings cache file.");
                return jSONObject;
            } catch (Exception e2) {
                e = e2;
                fileInputStream = null;
            } catch (Throwable th2) {
                th = th2;
                zu.q(null, "Error while closing settings cache file.");
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            zu.q(null, "Error while closing settings cache file.");
            throw th;
        }
    }

    @Override // p.ah2
    public final b33 j(Object obj) throws Throwable {
        JSONObject jSONObjectT;
        FileWriter fileWriter;
        com.google.firebase.crashlytics.internal.settings.a aVar = (com.google.firebase.crashlytics.internal.settings.a) this.c;
        kk0 kk0Var = aVar.f;
        jo1 jo1Var = aVar.b;
        kk0Var.getClass();
        FileWriter fileWriter2 = null;
        try {
            HashMap mapR = kk0.r(jo1Var);
            yn ynVar = (yn) kk0Var.k;
            String str = (String) kk0Var.e;
            ynVar.getClass();
            v4 v4Var = new v4(str, mapR);
            ((Map) v4Var.l).put("User-Agent", "Crashlytics Android SDK/18.3.6");
            ((Map) v4Var.l).put("X-CRASHLYTICS-DEVELOPER-TOKEN", "470fa2b4ae81cd56ecbcda9735803434cec591fa");
            kk0.d(v4Var, jo1Var);
            ((zb0) kk0Var.l).l(3);
            zb0 zb0Var = (zb0) kk0Var.l;
            mapR.toString();
            zb0Var.l(2);
            jSONObjectT = kk0Var.t(v4Var.i());
        } catch (IOException e) {
            if (((zb0) kk0Var.l).l(6)) {
                Log.e("FirebaseCrashlytics", "Settings request failed.", e);
            }
            jSONObjectT = null;
        }
        if (jSONObjectT != null) {
            cc2 cc2VarA = aVar.c.a(jSONObjectT);
            long j = cc2VarA.c;
            hc2 hc2Var = aVar.e;
            hc2Var.getClass();
            Log.isLoggable("FirebaseCrashlytics", 2);
            try {
                try {
                    jSONObjectT.put("expires_at", j);
                } catch (Exception e2) {
                    e = e2;
                }
                try {
                    fileWriter = new FileWriter((File) hc2Var.c);
                } catch (Exception e3) {
                    e = e3;
                    fileWriter = null;
                    Log.e("FirebaseCrashlytics", "Failed to cache settings", e);
                    zu.q(fileWriter, "Failed to close settings writer.");
                    jSONObjectT.toString();
                    Log.isLoggable("FirebaseCrashlytics", 3);
                    String str2 = jo1Var.g;
                    SharedPreferences.Editor editorEdit = aVar.a.getSharedPreferences("com.google.firebase.crashlytics", 0).edit();
                    editorEdit.putString("existing_instance_identifier", str2);
                    editorEdit.apply();
                    aVar.h.set(cc2VarA);
                    ((bj2) aVar.i.get()).b(cc2VarA);
                    return ci1.t(null);
                }
                try {
                    try {
                        fileWriter.write(jSONObjectT.toString());
                        fileWriter.flush();
                    } catch (Throwable th) {
                        th = th;
                        fileWriter2 = fileWriter;
                        zu.q(fileWriter2, "Failed to close settings writer.");
                        throw th;
                    }
                } catch (Exception e4) {
                    e = e4;
                    Log.e("FirebaseCrashlytics", "Failed to cache settings", e);
                }
                zu.q(fileWriter, "Failed to close settings writer.");
                jSONObjectT.toString();
                Log.isLoggable("FirebaseCrashlytics", 3);
                String str22 = jo1Var.g;
                SharedPreferences.Editor editorEdit2 = aVar.a.getSharedPreferences("com.google.firebase.crashlytics", 0).edit();
                editorEdit2.putString("existing_instance_identifier", str22);
                editorEdit2.apply();
                aVar.h.set(cc2VarA);
                ((bj2) aVar.i.get()).b(cc2VarA);
            } catch (Throwable th2) {
                th = th2;
                zu.q(fileWriter2, "Failed to close settings writer.");
                throw th;
            }
        }
        return ci1.t(null);
    }

    public hc2(gu0 gu0Var) {
        this.c = new File((File) gu0Var.b, "com.crashlytics.settings.json");
    }
}
