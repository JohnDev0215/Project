package defpackage;

import android.os.Handler;
import defpackage.a00;
import java.util.ArrayList;
import java.util.List;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: SignalAccumulator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class gm2 {
    public Boolean a;
    public Boolean b;
    public boolean c;
    public boolean d;
    public Runnable e;
    public final List<a00.a> f;
    public final Tab g;
    public final Handler h;

    public gm2(Handler handler, Tab tab, ArrayList arrayList) {
        this.h = handler;
        this.g = tab;
        this.f = arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a() {
        /*
            r3 = this;
            boolean r0 = r3.c
            r1 = 1
            if (r0 != 0) goto L13
            java.lang.Boolean r0 = r3.a
            r2 = 0
            if (r0 == 0) goto L10
            java.lang.Boolean r0 = r3.b
            if (r0 == 0) goto L10
            r0 = r1
            goto L11
        L10:
            r0 = r2
        L11:
            if (r0 == 0) goto L14
        L13:
            r2 = r1
        L14:
            if (r2 == 0) goto L22
            boolean r0 = r3.d
            if (r0 == 0) goto L1b
            goto L22
        L1b:
            r3.d = r1
            java.lang.Runnable r3 = r3.e
            r3.run()
        L22:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.gm2.a():void");
    }
}
