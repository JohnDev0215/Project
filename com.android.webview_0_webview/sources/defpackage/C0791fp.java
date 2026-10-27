package defpackage;

import android.content.Context;
import android.os.Process;

/* JADX INFO: renamed from: fp, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0791fp {
    public boolean d;
    public final boolean f;
    public int a = -1;
    public boolean b = true;
    public boolean c = true;
    public final Object e = new Object();

    public C0791fp(Context context) {
        boolean z = true;
        boolean z2 = context.checkPermission("android.permission.INTERNET", Process.myPid(), Process.myUid()) == 0;
        synchronized (this.e) {
            this.f = z2;
            if (z2) {
                z = false;
            }
            this.d = z;
        }
    }

    public boolean a() {
        boolean z;
        synchronized (this.e) {
            z = this.b;
        }
        return z;
    }

    public boolean b() {
        boolean z;
        synchronized (this.e) {
            z = this.c;
        }
        return z;
    }

    public boolean c() {
        boolean z;
        synchronized (this.e) {
            z = this.d;
        }
        return z;
    }

    public int d() {
        int i;
        synchronized (this.e) {
            i = this.a;
        }
        return i;
    }

    public void e(boolean z) {
        synchronized (this.e) {
            if (!z) {
                if (!this.f) {
                    throw new SecurityException("Permission denied - application missing INTERNET permission");
                }
            }
            this.d = z;
        }
    }
}
