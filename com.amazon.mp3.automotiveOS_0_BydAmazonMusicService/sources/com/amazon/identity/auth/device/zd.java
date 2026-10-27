package com.amazon.identity.auth.device;

import android.text.TextUtils;
import android.util.Log;
import com.amazon.identity.auth.device.de;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class zd extends ae {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f708a;

    public zd(String str) {
        if (TextUtils.isEmpty(null)) {
            this.f708a = "PeriodicLoggingMetricsCollector";
        } else {
            this.f708a = null;
        }
    }

    @Override // com.amazon.identity.auth.device.ae
    public de a(String str) {
        return new de.b();
    }

    @Override // com.amazon.identity.auth.device.ae
    public void b(String str) {
        Log.i(this.f708a, "Increment counter : " + str);
    }
}
