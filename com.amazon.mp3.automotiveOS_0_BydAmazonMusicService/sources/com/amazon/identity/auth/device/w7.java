package com.amazon.identity.auth.device;

import android.content.Context;
import android.util.Log;
import androidx.compose.runtime.CompositionImpl$$ExternalSyntheticBackportWithForwarding0;
import com.amazon.identity.auth.device.attribute.DeviceAttribute;
import com.amazon.identity.auth.device.features.Feature;
import com.amazon.identity.auth.device.framework.IsolatedModeSwitcher;
import com.amazon.identity.auth.device.storage.NonCanonicalDataStorage;
import java.lang.reflect.GenericDeclaration;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class w7 implements v7 {
    public static final String e = "com.amazon.identity.auth.device.w7";
    public static w7 f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final t5 f644a;
    public final i5 b;
    public final z3 c;
    public final AtomicReference<u7> d = new AtomicReference<>(null);

    public w7(Context context) {
        ga.c(e, "Creating new DataStorageFactoryImpl");
        t5 t5VarA = t5.a(context.getApplicationContext());
        this.f644a = t5VarA;
        this.b = (i5) t5VarA.getSystemService("sso_platform");
        this.c = t5VarA.b();
    }

    @Override // com.amazon.identity.auth.device.v7
    public u7 a() {
        GenericDeclaration genericDeclaration;
        u7 u7VarA;
        if (this.d.get() != null) {
            return this.d.get();
        }
        String str = e;
        Log.i(ga.a(str), "Initializing new DataStorage");
        if (!IsolatedModeSwitcher.doesAppSupportRuntimeIsolatedMode(this.f644a)) {
            t5 t5Var = this.f644a;
            DeviceAttribute deviceAttribute = DeviceAttribute.CentralAPK;
            Object objA = h2.c.a(t5Var, deviceAttribute);
            if (!(objA instanceof Boolean)) {
                ga.b(oa.f479a, "Device Attribute %s is not of type boolean.", deviceAttribute.toString());
                throw new RuntimeException("Incorrectly called hasDeviceAttribute in Platform.  Try calling getDeviceAttribute.");
            }
            if (((Boolean) objA).booleanValue() && !t9.f(t5Var) && qe.b(t5Var)) {
                Log.i(ga.a(str), "Creating and using new NonCanonicalDataStorage");
                u7VarA = new NonCanonicalDataStorage(this.f644a);
            } else {
                if (this.b.d() || this.c.a(Feature.IsolateApplication)) {
                    Log.i(ga.a(str), "Creating and using new CentralLocalDataStorage");
                    u7VarA = r7.a(this.f644a);
                } else {
                    Context context = this.b.f382a;
                    String str2 = qe.f519a;
                    if (t9.c(context)) {
                        Log.i(ga.a(str), "Creating and using new CentralAccountManagerDataStorage");
                        t5 t5Var2 = this.f644a;
                        genericDeclaration = q7.class;
                        synchronized (genericDeclaration) {
                            if (q7.h == null || xa.a()) {
                                q7.h = new q7(t5Var2.getApplicationContext());
                            }
                            u7VarA = q7.h;
                        }
                    } else {
                        Log.i(ga.a(str), "Creating and using new DistributedDataStorage");
                        t5 t5Var3 = this.f644a;
                        genericDeclaration = y7.class;
                        synchronized (genericDeclaration) {
                            if (y7.g == null || xa.a()) {
                                y7.g = new y7(t5Var3.getApplicationContext());
                            }
                            u7VarA = y7.g;
                        }
                    }
                }
            }
            CompositionImpl$$ExternalSyntheticBackportWithForwarding0.m(this.d, null, u7VarA);
            return u7VarA;
        }
        Log.i(ga.a(str), "Creating and using RuntimeSwitchableDataStorage");
        t5 t5Var4 = this.f644a;
        genericDeclaration = l8.class;
        synchronized (genericDeclaration) {
            if (l8.f == null) {
                l8.f = new l8(t5.a(t5Var4.getApplicationContext()));
            }
            u7VarA = l8.f;
        }
        CompositionImpl$$ExternalSyntheticBackportWithForwarding0.m(this.d, null, u7VarA);
        return u7VarA;
    }

    @Override // com.amazon.identity.auth.device.v7
    public boolean b() {
        u7 u7VarA = a();
        if (u7VarA instanceof y7) {
            return true;
        }
        if (u7VarA instanceof l8) {
            return ((l8) u7VarA).c;
        }
        return false;
    }
}
