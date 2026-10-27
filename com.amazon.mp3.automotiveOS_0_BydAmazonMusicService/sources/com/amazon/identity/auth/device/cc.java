package com.amazon.identity.auth.device;

import android.util.Log;
import org.json.JSONObject;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public abstract class cc {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f271a = "com.amazon.identity.auth.device.cc";

    public static synchronized cc a() {
        Log.i(ga.a(f271a), "Jwt Signer Factory is null");
        return null;
    }

    public final String a(String str, JSONObject jSONObject) {
        throw null;
    }
}
