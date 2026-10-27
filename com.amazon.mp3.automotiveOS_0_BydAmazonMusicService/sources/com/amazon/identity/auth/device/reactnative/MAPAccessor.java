package com.amazon.identity.auth.device.reactnative;

import android.content.Context;
import com.amazon.identity.auth.device.a1;
import com.amazon.identity.auth.device.q9;
import com.amazon.identity.auth.device.t5;
import com.amazon.identity.auth.device.yd;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class MAPAccessor {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f534a;
    public final a1 b;

    public MAPAccessor(Context context) {
        this.f534a = context;
        this.b = new a1(context);
    }

    public String getAuthPortalHost(String str) {
        return q9.a((Context) t5.a(this.f534a), str);
    }

    public String getPandaHost(String str) {
        return q9.a(t5.a(this.f534a), str);
    }

    public boolean hasPrimaryRole(String str) {
        return this.b.a(str);
    }

    public void incrementCounterAndRecord(String str, String... strArr) {
        yd.b("RNAndroid:" + str, strArr);
    }
}
