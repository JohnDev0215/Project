package defpackage;

import android.util.Log;
import defpackage.fe;
import defpackage.ie;
import java.util.HashSet;
import org.chromium.base.ThreadUtils;

/* JADX INFO: compiled from: BatteryMonitorImpl.java */
/* JADX INFO: loaded from: classes3.dex */
public final class he implements fe {
    public final ge e;
    public fe.a f;
    public je g;
    public boolean h = false;
    public boolean i = true;

    public he(ge geVar) {
        this.e = geVar;
    }

    @Override // defpackage.y11, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        t();
    }

    @Override // defpackage.fe
    public final void g1(ie.e eVar) {
        if (this.f != null) {
            Log.e("cr_BatteryMonitorImpl", "Overlapped call to queryNextStatus!");
            t();
            return;
        }
        this.f = eVar;
        if (this.h) {
            eVar.c(this.g);
            this.f = null;
            this.h = false;
        }
    }

    @Override // defpackage.fx
    public final void q(ki1 ki1Var) {
        t();
    }

    public final void t() {
        if (this.i) {
            ge geVar = this.e;
            geVar.getClass();
            Object obj = ThreadUtils.a;
            HashSet<he> hashSet = geVar.f;
            hashSet.remove(this);
            if (hashSet.isEmpty()) {
                ke keVar = geVar.e;
                if (keVar.e) {
                    xz.a.unregisterReceiver(keVar.c);
                    keVar.e = false;
                }
                geVar.g = false;
            }
            this.i = false;
        }
    }
}
