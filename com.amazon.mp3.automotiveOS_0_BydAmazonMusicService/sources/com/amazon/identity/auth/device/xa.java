package com.amazon.identity.auth.device;

import android.util.Log;
import com.amazon.identity.auth.device.api.MAPInit;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class xa {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final boolean f662a;

    static {
        boolean z;
        String name = xa.class.getName();
        try {
            Class.forName("android.test.mock.MockContext");
            Class.forName("com.amazon.identity.auth.unittest.IsRunningInUnitTest");
            Log.e(ga.a(name), "Is running in unit test!");
            z = true;
        } catch (ClassNotFoundException unused) {
            z = false;
        }
        f662a = z;
    }

    public static boolean a() {
        return f662a && !MAPInit.isRunningInFunctionalTest();
    }
}
