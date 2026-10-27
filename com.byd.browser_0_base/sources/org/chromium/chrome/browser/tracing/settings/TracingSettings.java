package org.chromium.chrome.browser.tracing.settings;

import android.app.NotificationChannel;
import android.os.Bundle;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.d;
import defpackage.c01;
import defpackage.j52;
import defpackage.qj2;
import defpackage.sx1;
import defpackage.te3;
import defpackage.xz;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;
import org.chromium.chrome.R$xml;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.components.browser_ui.notifications.NotificationManagerProxyImpl;

/* JADX INFO: loaded from: classes2.dex */
public class TracingSettings extends d implements te3.b {
    public static final LinkedHashMap n0;
    public Preference h0;
    public Preference i0;
    public ListPreference j0;
    public Preference k0;
    public Preference l0;
    public Preference m0;

    static {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("record-until-full", "Record until full");
        linkedHashMap.put("record-as-much-as-possible", "Record until full (large buffer)");
        linkedHashMap.put("record-continuously", "Record continuously");
        n0 = linkedHashMap;
    }

    public static HashSet M0(int i) {
        HashSet hashSet = new HashSet();
        for (String str : N0()) {
            if (i == str.startsWith("disabled-by-default-")) {
                hashSet.add(str);
            }
        }
        return hashSet;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.Set] */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.util.Set<java.lang.String>] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.util.HashSet] */
    public static Set<String> N0() {
        ?? I = SharedPreferencesManager.getInstance().i("tracing_categories", null);
        if (I == 0) {
            I = new HashSet();
            for (String str : te3.a().d) {
                if (!str.startsWith("disabled-by-default-")) {
                    I.add(str);
                }
            }
        }
        return I;
    }

    public static String O0() {
        return SharedPreferencesManager.getInstance().g("tracing_mode", (String) n0.keySet().iterator().next());
    }

    @Override // androidx.preference.d
    public final void J0(Bundle bundle, String str) {
        D().setTitle("Tracing");
        qj2.a(this, R$xml.tracing_preferences);
        this.h0 = f("default_categories");
        this.i0 = f("non_default_categories");
        this.j0 = (ListPreference) f("mode");
        this.k0 = f("start_recording");
        this.l0 = f("share_trace");
        this.m0 = f("tracing_status");
        this.h0.m().putInt("type", 0);
        this.i0.m().putInt("type", 1);
        ListPreference listPreference = this.j0;
        LinkedHashMap linkedHashMap = n0;
        listPreference.Z = (CharSequence[]) linkedHashMap.keySet().toArray(new String[linkedHashMap.size()]);
        this.j0.a0((String[]) linkedHashMap.values().toArray(new String[linkedHashMap.values().size()]));
        this.j0.setOnPreferenceChangeListener(new c01(28, this));
        this.k0.setOnPreferenceClickListener(new j52(this, 1));
        this.l0.T("Share trace");
        this.l0.setOnPreferenceClickListener(new sx1(3, this));
    }

    public final void P0() {
        NotificationChannel notificationChannelG;
        int i = te3.a().c;
        boolean z = i != 0;
        boolean z2 = i == 1 || !z;
        boolean z3 = i == 5;
        boolean z4 = new NotificationManagerProxyImpl(xz.a).f() && ((notificationChannelG = new NotificationManagerProxyImpl(xz.a).g("browser")) == null || notificationChannelG.getImportance() != 0);
        this.h0.J(z);
        this.i0.J(z);
        this.j0.J(z);
        this.k0.J(z2 && z && z4);
        this.l0.J(z3 && z4);
        if (z) {
            Iterator it = te3.a().d.iterator();
            int i2 = 0;
            int i3 = 0;
            while (it.hasNext()) {
                if (((String) it.next()).startsWith("disabled-by-default-")) {
                    i3++;
                } else {
                    i2++;
                }
            }
            int size = M0(0).size();
            int size2 = M0(1).size();
            this.h0.R(String.format("%s out of %s enabled", Integer.valueOf(size), Integer.valueOf(i2)));
            this.i0.R(String.format("%s out of %s enabled", Integer.valueOf(size2), Integer.valueOf(i3)));
            this.j0.b0(O0());
            this.j0.R((CharSequence) n0.get(O0()));
        }
        if (!z4) {
            this.k0.T("Record trace");
            this.m0.T("Please enable Chrome browser notifications to record a trace.");
        } else if (z2) {
            this.k0.T("Record trace");
            this.m0.T("Traces may contain user or site data related to the active browsing session, including incognito tabs.");
        } else {
            this.k0.T("Recording…");
            this.m0.T("A trace is being recorded. Use the notification to stop and share the result.");
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final void l0() {
        this.H = true;
        te3.a().b.c(this);
    }

    @Override // androidx.fragment.app.Fragment
    public final void m0() {
        this.H = true;
        P0();
        te3.a().b.a(this);
    }

    @Override // te3.b
    public final void p() {
        P0();
    }
}
