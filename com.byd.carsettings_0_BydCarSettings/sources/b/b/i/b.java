package b.b.i;

import android.content.Context;
import android.hardware.bydauto.xcall.BYDAutoXCallDevice;

/* JADX INFO: compiled from: BydXCallDevice.java */
/* JADX INFO: loaded from: classes2.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public BYDAutoXCallDevice f1918a;

    /* JADX INFO: renamed from: b.b.i.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: BydXCallDevice.java */
    public static class C0080b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f1919a = new b();

        public static b a() {
            return f1919a;
        }
    }

    public void a(Context context) {
        this.f1918a = BYDAutoXCallDevice.getInstance(context);
        b.b.m.c.a.a("BydXCallDevice", "---init--- ");
    }

    public b() {
    }
}
