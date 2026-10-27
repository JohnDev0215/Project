package org.chromium.components.gcm_driver;

import J.N;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import defpackage.d73;
import defpackage.et0;
import defpackage.i9;
import defpackage.ku2;
import defpackage.nr1;
import defpackage.uv;
import defpackage.wr0;
import defpackage.xz;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.task.PostTask;
import org.json.JSONArray;
import org.json.JSONException;

/* JADX INFO: loaded from: classes2.dex */
public class GCMDriver {
    public static GCMDriver c;
    public long a;
    public final et0 b = new et0();

    public class a extends i9<String> {
        public final /* synthetic */ String h;
        public final /* synthetic */ String i;

        public a(String str, String str2) {
            this.h = str;
            this.i = str2;
        }

        @Override // defpackage.i9
        public final String b() {
            String str = this.i;
            String str2 = this.h;
            try {
                et0 et0Var = GCMDriver.this.b;
                et0Var.getClass();
                Bundle bundle = new Bundle();
                bundle.putString("subtype", str2);
                return et0Var.a(bundle, str).getString("registration_id");
            } catch (IOException e) {
                Log.w("cr_GCMDriver", "GCM subscription failed for " + str2 + ", " + str, e);
                return "";
            }
        }

        @Override // defpackage.i9
        public final void l(String str) {
            GCMDriver gCMDriver = GCMDriver.this;
            N.MOEO6cdX(gCMDriver.a, gCMDriver, this.h, str, !r4.isEmpty());
        }
    }

    public class b extends i9<Boolean> {
        public final /* synthetic */ String h;
        public final /* synthetic */ String i;

        public b(String str, String str2) {
            this.h = str;
            this.i = str2;
        }

        @Override // defpackage.i9
        public final Boolean b() {
            String str = this.i;
            String str2 = this.h;
            try {
                et0 et0Var = GCMDriver.this.b;
                et0Var.getClass();
                Bundle bundle = new Bundle();
                bundle.putString("subtype", str2);
                bundle.putString("delete", "1");
                et0Var.a(bundle, str);
                return Boolean.TRUE;
            } catch (IOException e) {
                Log.w("cr_GCMDriver", "GCM unsubscription failed for " + str2 + ", " + str, e);
                return Boolean.FALSE;
            }
        }

        @Override // defpackage.i9
        public final void l(Boolean bool) {
            GCMDriver gCMDriver = GCMDriver.this;
            N.MDziew73(gCMDriver.a, gCMDriver, this.h, bool.booleanValue());
        }
    }

    public GCMDriver(long j) {
        this.a = j;
    }

    @CalledByNative
    public static GCMDriver create(long j) {
        if (c != null) {
            throw new IllegalStateException("Already instantiated");
        }
        c = new GCMDriver(j);
        SharedPreferences sharedPreferences = xz.a.a;
        if (sharedPreferences.getBoolean("has_persisted_messages", false)) {
            ku2 ku2VarT = ku2.t();
            try {
                HashSet hashSet = new HashSet(xz.a.getSharedPreferences("org.chromium.components.gcm_driver.lazy_subscriptions", 0).getStringSet("fcm_lazy_subscriptions", Collections.emptySet()));
                ku2VarT.close();
                sharedPreferences.edit().putStringSet("subscriptions_with_persisted_messages", hashSet).apply();
                sharedPreferences.edit().remove("has_persisted_messages").apply();
            } catch (Throwable th) {
                try {
                    ku2VarT.close();
                } catch (Throwable unused) {
                }
                throw th;
            }
        }
        return c;
    }

    @CalledByNative
    public final void destroy() {
        c = null;
        this.a = 0L;
    }

    @CalledByNative
    public final void register(String str, String str2) {
        new a(str, str2).c(i9.e);
    }

    @CalledByNative
    public final void replayPersistedMessages(String str) {
        wr0[] wr0VarArr;
        HashSet<String> hashSet = new HashSet(xz.a.a.getStringSet("subscriptions_with_persisted_messages", Collections.emptySet()));
        HashSet hashSet2 = new HashSet();
        for (String str2 : hashSet) {
            if (str2.startsWith(str)) {
                hashSet2.add(str2);
            }
        }
        if (hashSet2.isEmpty()) {
            return;
        }
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        Iterator it = hashSet2.iterator();
        while (it.hasNext()) {
            String str3 = (String) it.next();
            try {
                JSONArray jSONArray = new JSONArray(xz.a.getSharedPreferences("org.chromium.components.gcm_driver.lazy_subscriptions", 0).getString(str3, "[]"));
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < jSONArray.length(); i++) {
                    try {
                        wr0 wr0VarA = wr0.a(jSONArray.getJSONObject(i));
                        if (wr0VarA == null) {
                            Log.e("cr_LazySubscriptions", "Persisted GCM Message is invalid. Sender id:" + jSONArray.getJSONObject(i).optString("senderId", null));
                        } else {
                            arrayList.add(wr0VarA);
                        }
                    } catch (JSONException e) {
                        Log.e("cr_LazySubscriptions", "Error when creating a GCMMessage from a JSONObject:" + e.getMessage());
                    }
                }
                wr0VarArr = (wr0[]) arrayList.toArray(new wr0[arrayList.size()]);
            } catch (JSONException unused) {
                Log.e("cr_LazySubscriptions", "Error when parsing the persisted message queue for subscriber:" + str3);
                wr0VarArr = new wr0[0];
            }
            int length = wr0VarArr.length;
            int i2 = 0;
            while (i2 < length) {
                wr0 wr0Var = wr0VarArr[i2];
                Object obj = ThreadUtils.a;
                GCMDriver gCMDriver = c;
                if (gCMDriver == null) {
                    throw new uv("Failed to instantiate GCMDriver.");
                }
                N.M6eL4wmM(gCMDriver.a, gCMDriver, wr0Var.b, wr0Var.a, wr0Var.c, wr0Var.d, wr0Var.e, wr0Var.f);
                i2++;
                wr0VarArr = wr0VarArr;
                it = it;
            }
            Iterator it2 = it;
            xz.a.getSharedPreferences("org.chromium.components.gcm_driver.lazy_subscriptions", 0).edit().remove(str3).apply();
            SharedPreferences sharedPreferences = xz.a.a;
            HashSet hashSet3 = new HashSet(sharedPreferences.getStringSet("subscriptions_with_persisted_messages", Collections.emptySet()));
            if (hashSet3.contains(str3)) {
                hashSet3.remove(str3);
                sharedPreferences.edit().putStringSet("subscriptions_with_persisted_messages", hashSet3).apply();
            }
            it = it2;
        }
        PostTask.c(d73.g, new nr1(2, SystemClock.elapsedRealtime() - jElapsedRealtime));
    }

    @CalledByNative
    public final void unregister(String str, String str2) {
        new b(str, str2).c(i9.e);
    }
}
