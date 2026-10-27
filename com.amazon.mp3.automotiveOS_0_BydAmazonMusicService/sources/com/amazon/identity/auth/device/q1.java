package com.amazon.identity.auth.device;

import android.content.Context;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class q1 {
    public static final String b = "com.amazon.identity.auth.device.q1";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f505a;

    public q1(Context context) {
        this.f505a = context;
    }

    public String a() {
        return a("default.cor", "US");
    }

    public String b() {
        return a("default.pfm", "ATVPDKIKX0DER");
    }

    public final f8 c() {
        return new f8(this.f505a, "default_cor_pfm_store", 0);
    }

    public final String a(String str, String str2) {
        String str3 = b;
        ga.a(str3);
        String string = c().f313a.getString(str, null);
        if (string != null) {
            String.format("Returning Device Default Cor/Pfm from Shared Preference for key: %s and value: %s", str, string);
            ga.a(str3);
            return string;
        }
        String.format("Returning Device Default Cor/Pfm from default settings for key: %s and value: %s", str, str2);
        ga.a(str3);
        return str2;
    }
}
