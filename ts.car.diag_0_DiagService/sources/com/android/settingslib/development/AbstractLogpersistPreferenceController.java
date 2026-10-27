package com.android.settingslib.development;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.SystemProperties;
import androidx.annotation.VisibleForTesting;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import com.android.settingslib.R$array;
import com.android.settingslib.core.lifecycle.b.d;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractLogpersistPreferenceController extends a implements Preference.c, com.android.settingslib.core.lifecycle.a, com.android.settingslib.core.lifecycle.b.b, d, com.android.settingslib.d.b {

    @VisibleForTesting
    static final String ACTUAL_LOGPERSIST_PROPERTY = "logd.logpersistd";

    @VisibleForTesting
    static final String ACTUAL_LOGPERSIST_PROPERTY_BUFFER = "logd.logpersistd.buffer";

    @VisibleForTesting
    static final String SELECT_LOGPERSIST_PROPERTY_SERVICE = "logcatd";
    private ListPreference d;
    private boolean e;
    private final BroadcastReceiver f;

    @Override // androidx.preference.Preference.c
    public boolean a(Preference preference, Object obj) {
        if (preference != this.d) {
            return false;
        }
        p(obj, false);
        return true;
    }

    @Override // com.android.settingslib.core.lifecycle.b.d
    public void l() {
        androidx.localbroadcastmanager.a.a.b(this.f1346c).d(this.f);
    }

    @Override // com.android.settingslib.core.lifecycle.b.b
    public void m(Bundle bundle) {
        androidx.localbroadcastmanager.a.a.b(this.f1346c).c(this.f, new IntentFilter("com.android.settingslib.development.AbstractLogdSizePreferenceController.LOGD_SIZE_UPDATED"));
    }

    protected void n(boolean z) {
        String str;
        SystemProperties.set("persist.logd.logpersistd.buffer", "");
        SystemProperties.set(ACTUAL_LOGPERSIST_PROPERTY_BUFFER, "");
        SystemProperties.set("persist.logd.logpersistd", "");
        SystemProperties.set(ACTUAL_LOGPERSIST_PROPERTY, z ? "" : "stop");
        b.a().b();
        if (z) {
            o();
            return;
        }
        for (int i = 0; i < 3 && (str = SystemProperties.get(ACTUAL_LOGPERSIST_PROPERTY)) != null && !str.equals(""); i++) {
            try {
                Thread.sleep(100L);
            } catch (InterruptedException unused) {
            }
        }
    }

    public void o() {
        char c2;
        if (this.d == null) {
            return;
        }
        String str = SystemProperties.get(ACTUAL_LOGPERSIST_PROPERTY);
        if (str == null) {
            str = "";
        }
        String str2 = SystemProperties.get(ACTUAL_LOGPERSIST_PROPERTY_BUFFER);
        if (str2 == null || str2.length() == 0) {
            str2 = "all";
        }
        if (!str.equals(SELECT_LOGPERSIST_PROPERTY_SERVICE)) {
            c2 = 0;
        } else if (str2.equals("kernel")) {
            c2 = 3;
        } else {
            if (str2.equals("all") || str2.contains("radio") || !str2.contains("security") || !str2.contains("kernel")) {
                c2 = 1;
                break;
            }
            c2 = 2;
            if (!str2.contains("default")) {
                String[] strArr = {"main", "events", "system", "crash"};
                for (int i = 0; i < 4; i++) {
                    if (!str2.contains(strArr[i])) {
                        c2 = 1;
                        break;
                    }
                }
            }
        }
        this.d.Q0(this.f1346c.getResources().getStringArray(R$array.select_logpersist_values)[c2]);
        this.d.u0(this.f1346c.getResources().getStringArray(R$array.select_logpersist_summaries)[c2]);
        if (c2 != 0) {
            this.e = false;
        } else {
            if (this.e) {
                return;
            }
            SystemProperties.set(ACTUAL_LOGPERSIST_PROPERTY, "clear");
            b.a().b();
            this.e = true;
        }
    }

    public void p(Object obj, boolean z) {
        String str;
        String str2;
        if (this.d == null) {
            return;
        }
        String str3 = SystemProperties.get("persist.log.tag");
        if (str3 != null && str3.startsWith("Settings")) {
            obj = null;
            z = true;
        }
        if (obj == null || obj.toString().equals("")) {
            if (z) {
                this.e = false;
            } else if (!this.e && (str = SystemProperties.get(ACTUAL_LOGPERSIST_PROPERTY)) != null && str.equals(SELECT_LOGPERSIST_PROPERTY_SERVICE)) {
                k(this.d);
                return;
            }
            n(true);
            return;
        }
        String str4 = SystemProperties.get(ACTUAL_LOGPERSIST_PROPERTY_BUFFER);
        if (str4 != null && !str4.equals(obj.toString())) {
            n(false);
        }
        SystemProperties.set("persist.logd.logpersistd.buffer", obj.toString());
        SystemProperties.set("persist.logd.logpersistd", SELECT_LOGPERSIST_PROPERTY_SERVICE);
        b.a().b();
        for (int i = 0; i < 3 && ((str2 = SystemProperties.get(ACTUAL_LOGPERSIST_PROPERTY)) == null || !str2.equals(SELECT_LOGPERSIST_PROPERTY_SERVICE)); i++) {
            try {
                Thread.sleep(100L);
            } catch (InterruptedException unused) {
            }
        }
        o();
    }
}
