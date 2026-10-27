package org.chromium.android_webview.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import defpackage.AbstractBinderC0303Qq;
import defpackage.AbstractC0267Oq;
import defpackage.AbstractC1077kw;
import defpackage.BinderC1017js;
import defpackage.Is;
import defpackage.Px;
import defpackage.Ts;
import defpackage.Z7;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class CrashReceiverService extends Service {
    public boolean t;
    public final Object s = new Object();
    public final AbstractBinderC0303Qq u = new BinderC1017js(this);

    public static boolean a(int i, ParcelFileDescriptor[] parcelFileDescriptorArr, List list) {
        Px px = new Px(AbstractC0267Oq.a());
        if (parcelFileDescriptorArr == null) {
            return false;
        }
        boolean z = false;
        for (int i2 = 0; i2 < parcelFileDescriptorArr.length; i2++) {
            ParcelFileDescriptor parcelFileDescriptor = parcelFileDescriptorArr[i2];
            if (parcelFileDescriptor != null) {
                try {
                    try {
                        try {
                            File fileB = px.b(parcelFileDescriptor.getFileDescriptor(), new File(Is.a.getCacheDir(), "WebView_Crashes_Tmp"), i);
                            if (fileB == null) {
                                Ts.f("CrashReceiverService", "failed to copy minidump from " + parcelFileDescriptor.toString(), new Object[0]);
                            } else {
                                if (list != null) {
                                    try {
                                        Map map = (Map) list.get(i2);
                                        File file = new File(AbstractC0267Oq.a(), "crash_logs");
                                        if (!file.mkdir() && !file.isDirectory()) {
                                            file = null;
                                        }
                                        c(new File(file, fileB.getName() + "_log.json"), fileB, map);
                                    } catch (IOException e) {
                                        e = e;
                                        z = true;
                                        Ts.f("CrashReceiverService", "failed to copy minidump from " + parcelFileDescriptor.toString() + ": " + e.getMessage(), new Object[0]);
                                    }
                                }
                                z = true;
                            }
                        } catch (IOException e2) {
                            e = e2;
                        }
                    } catch (IOException e3) {
                        e = e3;
                    }
                } finally {
                    b();
                }
            }
        }
        return z;
    }

    public static void b() {
        File[] fileArrListFiles;
        File file = new File(Is.a.getCacheDir(), "WebView_Crashes_Tmp");
        if (!file.isDirectory() || (fileArrListFiles = file.listFiles()) == null) {
            return;
        }
        for (File file2 : fileArrListFiles) {
            if (!file2.delete()) {
                StringBuilder sbF = Z7.f("Couldn't delete file ");
                sbF.append(file2.getAbsolutePath());
                Ts.f("CrashReceiverService", sbF.toString(), new Object[0]);
            }
        }
    }

    public static boolean c(File file, File file2, Map map) {
        try {
            String strG = Px.g(file2.getName());
            if (strG == null) {
                return false;
            }
            long jLastModified = file2.lastModified();
            if (map == null) {
                return false;
            }
            String str = (String) map.get("app-package-name");
            String string = null;
            List listAsList = map.containsKey("variations") ? Arrays.asList(((String) map.get("variations")).split(",")) : null;
            FileWriter fileWriter = new FileWriter(file);
            try {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("crash-local-id", strG);
                    if (jLastModified != -1) {
                        jSONObject.put("crash-capture-time", jLastModified);
                    }
                    if (str != null) {
                        jSONObject.put("app-package-name", str);
                    }
                    if (listAsList != null && !listAsList.isEmpty()) {
                        jSONObject.put("variations", new JSONArray((Collection) listAsList));
                    }
                    string = jSONObject.toString();
                } catch (JSONException unused) {
                }
                fileWriter.write(string);
                return true;
            } finally {
                fileWriter.close();
            }
        } catch (IOException e) {
            Ts.f("CrashReceiverService", "failed to write JSON log entry for crash", e);
            return false;
        }
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Context createConfigurationContext(Configuration configuration) {
        AbstractC1077kw.c();
        return super.createConfigurationContext(configuration);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public AssetManager getAssets() {
        AbstractC1077kw.c();
        return super.getAssets();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        AbstractC1077kw.c();
        return super.getResources();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources.Theme getTheme() {
        AbstractC1077kw.c();
        return super.getTheme();
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.u;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void setTheme(int i) {
        AbstractC1077kw.c();
        super.setTheme(i);
    }
}
