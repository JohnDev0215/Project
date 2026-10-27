package z2;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Handler;
import android.os.SystemClock;
import h2.AbstractC1343A;
import h2.AbstractC1346c;

/* JADX INFO: loaded from: classes.dex */
public final class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C2759e f24341a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final r f24342b;
    public boolean c;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public long f24344f;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f24347i;
    public int d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f24343e = -9223372036854775807L;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public long f24345g = -9223372036854775807L;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public long f24346h = -9223372036854775807L;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public float f24348j = 1.0f;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public h2.v f24349k = h2.v.f15245a;

    public o(Context context, C2759e c2759e) {
        this.f24341a = c2759e;
        this.f24342b = new r(context);
    }

    /* JADX WARN: Removed duplicated region for block: B:125:0x0238  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00e0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int a(long r23, long r25, long r27, long r29, boolean r31, s9.d r32) {
        /*
            Method dump skipped, instruction units count: 603
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: z2.o.a(long, long, long, long, boolean, s9.d):int");
    }

    public final boolean b(boolean z6) {
        if (z6 && this.d == 3) {
            this.f24346h = -9223372036854775807L;
            return true;
        }
        if (this.f24346h == -9223372036854775807L) {
            return false;
        }
        this.f24349k.getClass();
        if (SystemClock.elapsedRealtime() < this.f24346h) {
            return true;
        }
        this.f24346h = -9223372036854775807L;
        return false;
    }

    public final void c(boolean z6) {
        this.f24347i = z6;
        this.f24349k.getClass();
        this.f24346h = SystemClock.elapsedRealtime() + 5000;
    }

    public final void d(int i7) {
        this.d = Math.min(this.d, i7);
    }

    public final void e() {
        this.c = true;
        this.f24349k.getClass();
        this.f24344f = AbstractC1343A.H(SystemClock.elapsedRealtime());
        r rVar = this.f24342b;
        rVar.d = true;
        rVar.f24364m = 0L;
        rVar.f24366p = -1L;
        rVar.f24365n = -1L;
        p pVar = rVar.f24356b;
        if (pVar != null) {
            q qVar = rVar.c;
            qVar.getClass();
            qVar.f24353m.sendEmptyMessage(2);
            Handler handlerL = AbstractC1343A.l(null);
            DisplayManager displayManager = pVar.f24350a;
            displayManager.registerDisplayListener(pVar, handlerL);
            r.a(pVar.f24351b, displayManager.getDisplay(0));
        }
        rVar.d(false);
    }

    public final void f() {
        this.c = false;
        this.f24346h = -9223372036854775807L;
        r rVar = this.f24342b;
        rVar.d = false;
        p pVar = rVar.f24356b;
        if (pVar != null) {
            pVar.f24350a.unregisterDisplayListener(pVar);
            q qVar = rVar.c;
            qVar.getClass();
            qVar.f24353m.sendEmptyMessage(3);
        }
        rVar.b();
    }

    public final void g(float f10) {
        r rVar = this.f24342b;
        rVar.f24358f = f10;
        C2756b c2756b = rVar.f24355a;
        c2756b.f24270a.c();
        c2756b.f24271b.c();
        c2756b.c = false;
        c2756b.d = -9223372036854775807L;
        c2756b.f24272e = 0;
        rVar.c();
    }

    public final void h(float f10) {
        AbstractC1346c.c(f10 > 0.0f);
        if (f10 == this.f24348j) {
            return;
        }
        this.f24348j = f10;
        r rVar = this.f24342b;
        rVar.f24361i = f10;
        rVar.f24364m = 0L;
        rVar.f24366p = -1L;
        rVar.f24365n = -1L;
        rVar.d(false);
    }
}
