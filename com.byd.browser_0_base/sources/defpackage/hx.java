package defpackage;

import android.content.ComponentName;
import android.content.ServiceConnection;
import defpackage.AbstractC0208s;
import defpackage.ol;
import java.util.ArrayList;

/* JADX INFO: compiled from: ConnectionHolder.java */
/* JADX INFO: loaded from: classes.dex */
public final class hx implements ServiceConnection {
    public final Runnable e;
    public final a f;
    public int g;
    public dg3 h;
    public final ArrayList i;
    public Exception j;

    /* JADX INFO: compiled from: ConnectionHolder.java */
    public static class a {
    }

    public hx(l91 l91Var) {
        a aVar = new a();
        this.g = 0;
        this.i = new ArrayList();
        this.e = l91Var;
        this.f = aVar;
    }

    public final ol a() {
        gx gxVar = new gx(0, this);
        ml mlVar = new ml();
        ol<T> olVar = new ol<>(mlVar);
        mlVar.b = olVar;
        mlVar.a = gx.class;
        try {
            String strV = gxVar.v(mlVar);
            if (strV != null) {
                mlVar.a = strV;
            }
        } catch (Exception e) {
            ol.a aVar = olVar.f;
            aVar.getClass();
            if (AbstractC0208s.i.b(aVar, null, new AbstractC0208s.c(e))) {
                AbstractC0208s.c(aVar);
            }
        }
        return olVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0047  */
    @Override // android.content.ServiceConnection
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onServiceConnected(android.content.ComponentName r8, android.os.IBinder r9) {
        /*
            r7 = this;
            hx$a r0 = r7.f
            r0.getClass()
            dg3 r0 = new dg3
            android.support.customtabs.trusted.ITrustedWebActivityService r9 = android.support.customtabs.trusted.ITrustedWebActivityService.Stub.asInterface(r9)
            r0.<init>(r9, r8)
            r7.h = r0
            java.util.ArrayList r8 = r7.i
            java.util.Iterator r9 = r8.iterator()
        L16:
            boolean r0 = r9.hasNext()
            r1 = 1
            if (r0 == 0) goto L51
            java.lang.Object r0 = r9.next()
            ml r0 = (defpackage.ml) r0
            dg3 r2 = r7.h
            r0.d = r1
            ol<T> r3 = r0.b
            r4 = 0
            r5 = 0
            if (r3 == 0) goto L47
            ol$a r3 = r3.f
            r3.getClass()
            if (r2 != 0) goto L36
            java.lang.Object r2 = defpackage.AbstractC0208s.j
        L36:
            s$a r6 = defpackage.AbstractC0208s.i
            boolean r2 = r6.b(r3, r4, r2)
            if (r2 == 0) goto L43
            defpackage.AbstractC0208s.c(r3)
            r2 = r1
            goto L44
        L43:
            r2 = r5
        L44:
            if (r2 == 0) goto L47
            goto L48
        L47:
            r1 = r5
        L48:
            if (r1 == 0) goto L16
            r0.a = r4
            r0.b = r4
            r0.c = r4
            goto L16
        L51:
            r8.clear()
            r7.g = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.hx.onServiceConnected(android.content.ComponentName, android.os.IBinder):void");
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        this.h = null;
        this.e.run();
        this.g = 2;
    }
}
