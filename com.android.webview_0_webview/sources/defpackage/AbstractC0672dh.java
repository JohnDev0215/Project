package defpackage;

import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import android.util.Log;

/* JADX INFO: renamed from: dh, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0672dh {
    public final Context a;
    public final Object b;
    public final String c;
    public final String d;
    public final String e;
    public final boolean f;
    public boolean g;
    public boolean h;
    public Object i;

    /* JADX WARN: Code restructure failed: missing block: B:17:0x00dc, code lost:
    
        r0[r8] = (byte) r4;
        r3 = r3 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x011b, code lost:
    
        r0[r8] = (short) r4;
        r3 = r3 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0151, code lost:
    
        r0[r9] = r5;
        r3 = r3 + 1;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [int[]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public AbstractC0672dh(android.content.Context r13, java.lang.String r14, java.lang.String r15) {
        /*
            Method dump skipped, instruction units count: 399
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.AbstractC0672dh.<init>(android.content.Context, java.lang.String, java.lang.String):void");
    }

    public final boolean a() {
        return e() != null;
    }

    public abstract Object b(C1061kg c1061kg, Context context);

    public abstract void c();

    public final void d() {
        synchronized (this.b) {
            if (this.i == null) {
                return;
            }
            try {
                c();
            } catch (RemoteException e) {
                Log.e(this.c, "Could not finalize native handle", e);
            }
        }
    }

    public final Object e() {
        C1061kg c1061kgA;
        synchronized (this.b) {
            if (this.i != null) {
                return this.i;
            }
            try {
                c1061kgA = C1061kg.c(this.a, C1061kg.j, this.d);
            } catch (C0726eg unused) {
                c1061kgA = AbstractC0727eh.a(this.a, this.e, this.f);
                if (c1061kgA == null && this.f && !this.g) {
                    String strValueOf = String.valueOf(this.e);
                    if (strValueOf.length() != 0) {
                        "Broadcasting download intent for dependency ".concat(strValueOf);
                    } else {
                        new String("Broadcasting download intent for dependency ");
                    }
                    String str = this.e;
                    Intent intent = new Intent();
                    intent.setClassName("com.google.android.gms", "com.google.android.gms.vision.DependencyBroadcastReceiverProxy");
                    intent.putExtra("com.google.android.gms.vision.DEPENDENCIES", str);
                    intent.setAction("com.google.android.gms.vision.DEPENDENCY");
                    this.a.sendBroadcast(intent);
                    this.g = true;
                }
            }
            if (c1061kgA != null) {
                try {
                    this.i = b(c1061kgA, this.a);
                } catch (RemoteException | C0726eg e) {
                    Log.e(this.c, "Error creating remote native handle", e);
                }
            }
            if (!this.h && this.i == null) {
                Log.w(this.c, "Native handle not yet available. Reverting to no-op handle.");
                this.h = true;
            } else if (this.h && this.i != null) {
                Log.w(this.c, "Native handle is now available.");
            }
            return this.i;
        }
    }
}
