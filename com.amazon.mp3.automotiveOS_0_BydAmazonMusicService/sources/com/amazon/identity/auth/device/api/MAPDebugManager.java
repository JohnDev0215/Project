package com.amazon.identity.auth.device.api;

import android.content.Context;
import android.util.Log;
import com.amazon.identity.auth.device.ga;
import com.amazon.identity.auth.device.t5;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class MAPDebugManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final t5 f220a;

    public MAPDebugManager(Context context) {
        this.f220a = t5.a(context);
    }

    public String getDeviceSnapshot() {
        Log.i(ga.a("com.amazon.identity.auth.device.api.MAPDebugManager"), "GetDeviceSnapshot API called");
        return this.f220a.a().c();
    }
}
