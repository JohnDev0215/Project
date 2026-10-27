package org.chromium.chrome.browser.webapps;

import J.N;
import android.content.SharedPreferences;
import android.util.Pair;
import defpackage.a11;
import defpackage.ds3;
import defpackage.ft1;
import defpackage.ku2;
import defpackage.ps3;
import defpackage.rh3;
import defpackage.xz;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.browsing_data.UrlFilterBridge;

/* JADX INFO: loaded from: classes2.dex */
public class WebappRegistry {
    public boolean a;
    public final HashMap b;
    public final SharedPreferences c;
    public final a11 d;

    public interface a {
        void a(ds3 ds3Var);
    }

    public static class b {
        public static final WebappRegistry a = new WebappRegistry();
    }

    public WebappRegistry() {
        ku2 ku2VarT = ku2.t();
        try {
            SharedPreferences sharedPreferences = xz.a.getSharedPreferences("webapp_registry", 0);
            ku2VarT.close();
            this.c = sharedPreferences;
            this.b = new HashMap();
            this.d = new a11();
        } catch (Throwable th) {
            try {
                ku2VarT.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }

    @CalledByNative
    public static void clearWebappHistoryForUrls(UrlFilterBridge urlFilterBridge) {
        for (ds3 ds3Var : b.a.b.values()) {
            if (N.MrY8rM_K(urlFilterBridge.a, urlFilterBridge, ds3Var.b.getString("url", ""))) {
                ds3Var.a();
                SharedPreferences.Editor editorEdit = ds3Var.b.edit();
                editorEdit.remove("last_used");
                editorEdit.remove("url");
                editorEdit.remove("scope");
                editorEdit.remove("last_check_web_manifest_update_time");
                editorEdit.remove("last_update_request_complete_time");
                editorEdit.remove("did_last_update_request_succeed");
                editorEdit.remove("last_update_hash_accepted");
                editorEdit.remove("relax_updates");
                editorEdit.remove("show_disclosure");
                editorEdit.remove("launch_count");
                editorEdit.remove("webapk_uninstall_timestamp");
                editorEdit.apply();
            }
        }
        N.MBBog0Dv(urlFilterBridge.a, urlFilterBridge);
        urlFilterBridge.a = 0L;
    }

    @CalledByNative
    public static void unregisterWebappsForUrls(UrlFilterBridge urlFilterBridge) {
        WebappRegistry webappRegistry = b.a;
        HashMap map = webappRegistry.b;
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            ds3 ds3Var = (ds3) ((Map.Entry) it.next()).getValue();
            if (N.MrY8rM_K(urlFilterBridge.a, urlFilterBridge, ds3Var.b.getString("url", ""))) {
                ds3Var.a();
                ds3Var.b.edit().clear().apply();
                it.remove();
            }
        }
        boolean zIsEmpty = map.isEmpty();
        SharedPreferences sharedPreferences = webappRegistry.c;
        if (zIsEmpty) {
            sharedPreferences.edit().clear().apply();
        } else {
            sharedPreferences.edit().putStringSet("webapp_set", map.keySet()).apply();
        }
        N.MBBog0Dv(urlFilterBridge.a, urlFilterBridge);
        urlFilterBridge.a = 0L;
    }

    public final HashSet a() {
        HashSet hashSet = new HashSet();
        hashSet.addAll(b());
        hashSet.addAll(this.d.g());
        return hashSet;
    }

    public final HashSet b() {
        HashSet hashSet = new HashSet();
        for (ds3 ds3Var : this.b.values()) {
            String string = ds3Var.a.startsWith("webapk-") ? ds3Var.b.getString("scope", "") : "";
            if (!string.isEmpty()) {
                hashSet.add(ft1.b(string).toString());
            }
        }
        return hashSet;
    }

    public final ds3 c(String str) {
        return (ds3) this.b.get(str);
    }

    public final void d(String str) {
        Set<String> stringSet = this.c.getStringSet("webapp_set", Collections.emptySet());
        int i = 0;
        boolean z = str == null || str.isEmpty();
        boolean z2 = z && !this.a;
        if (z && !this.a) {
            this.d.g();
            this.a = true;
        }
        ArrayList arrayList = new ArrayList();
        HashMap map = this.b;
        if (z) {
            Iterator<String> it = stringSet.iterator();
            while (it.hasNext()) {
                String next = it.next();
                if (next == null) {
                    next = "";
                }
                if (!map.containsKey(next)) {
                    arrayList.add(Pair.create(next, new ds3(next)));
                }
            }
        } else if (stringSet.contains(str) && !map.containsKey(str)) {
            arrayList.add(Pair.create(str, new ds3(str)));
        }
        PostTask.d(rh3.a, new ps3(this, i, arrayList, z2));
    }
}
