package b.b.g;

import b.b.f.c;
import b.b.g.r.l;
import com.byd.carsettings.BydApp;
import java.util.List;
import ts.car.service.androidauto.data.AndroidAutoDevice;
import ts.car.service.carplay.data.CarPlayDevice;

/* JADX INFO: compiled from: DxDataService.java */
/* JADX INFO: loaded from: classes2.dex */
public class a implements b.b.a.e.a {
    @Override // b.b.a.e.a
    public List<CarPlayDevice> a() {
        return c.w(BydApp.getContext()).r();
    }

    @Override // b.b.a.e.a
    public b.b.a.d.a b() {
        return l.c();
    }

    @Override // b.b.a.e.a
    public List<AndroidAutoDevice> c() {
        return b.b.f.a.t(BydApp.getContext()).p();
    }
}
