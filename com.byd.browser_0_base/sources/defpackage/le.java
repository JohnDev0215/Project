package defpackage;

import android.os.BatteryManager;
import defpackage.ge;
import defpackage.ke;

/* JADX INFO: compiled from: BatteryStatusManager.java */
/* JADX INFO: loaded from: classes3.dex */
public final class le extends i9<je> {
    public final /* synthetic */ je h;
    public final /* synthetic */ ke i;

    public le(ke keVar, je jeVar) {
        this.i = keVar;
        this.h = jeVar;
    }

    @Override // defpackage.i9
    public final je b() {
        ke.b bVar = this.i.d;
        double intProperty = ((double) bVar.a.getIntProperty(4)) / 100.0d;
        BatteryManager batteryManager = bVar.a;
        double intProperty2 = batteryManager.getIntProperty(1);
        double intProperty3 = batteryManager.getIntProperty(3);
        je jeVar = this.h;
        if (jeVar.b) {
            if (jeVar.c == Double.POSITIVE_INFINITY && intProperty3 > 0.0d) {
                jeVar.c = Math.ceil((1.0d - intProperty) * (intProperty2 / intProperty3) * 3600.0d);
            }
        } else if (intProperty3 < 0.0d) {
            jeVar.d = Math.floor((intProperty2 / (-intProperty3)) * intProperty * 3600.0d);
        }
        return jeVar;
    }

    @Override // defpackage.i9
    public final void l(je jeVar) {
        ((ge.a) this.i.a).a(jeVar);
    }
}
