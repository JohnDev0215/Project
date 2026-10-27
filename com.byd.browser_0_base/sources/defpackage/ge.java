package defpackage;

import android.util.Log;
import defpackage.fe;
import defpackage.ie;
import defpackage.ke;
import java.util.ArrayList;
import java.util.HashSet;
import org.chromium.base.ThreadUtils;

/* JADX INFO: compiled from: BatteryMonitorFactory.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ge implements a21<fe> {
    public final ke e;
    public final HashSet<he> f = new HashSet<>();
    public boolean g;
    public je h;

    /* JADX INFO: compiled from: BatteryMonitorFactory.java */
    public class a implements ke.c {
        public a() {
        }

        public final void a(je jeVar) {
            Object obj = ThreadUtils.a;
            ge geVar = ge.this;
            geVar.g = true;
            geVar.h = jeVar;
            for (he heVar : new ArrayList(geVar.f)) {
                heVar.g = jeVar;
                heVar.h = true;
                fe.a aVar = heVar.f;
                if (aVar != null) {
                    ((ie.e) aVar).c(jeVar);
                    heVar.f = null;
                    heVar.h = false;
                }
            }
        }
    }

    public ge() {
        a aVar = new a();
        this.g = false;
        this.e = new ke(aVar);
    }

    @Override // defpackage.a21
    public final y11 d() {
        Object obj = ThreadUtils.a;
        HashSet<he> hashSet = this.f;
        if (hashSet.isEmpty()) {
            ke keVar = this.e;
            if (!keVar.e) {
                if (xz.g(xz.a, keVar.c, keVar.b) != null) {
                    keVar.e = true;
                }
            }
            if (!keVar.e) {
                Log.e("cr_BattMonitorFactory", "BatteryStatusManager failed to start.");
            }
        }
        he heVar = new he(this);
        if (this.g) {
            je jeVar = this.h;
            heVar.g = jeVar;
            heVar.h = true;
            fe.a aVar = heVar.f;
            if (aVar != null) {
                ((ie.e) aVar).c(jeVar);
                heVar.f = null;
                heVar.h = false;
            }
        }
        hashSet.add(heVar);
        return heVar;
    }
}
