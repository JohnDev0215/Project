package b.b.f.h;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.text.TextUtils;
import b.b.f.a;
import b.b.f.c;
import b.b.f.d;
import b.b.f.f;
import b.b.m.e.g;
import com.byd.carplay.model.CPAADevice;
import com.byd.carplay.model.CarPlayType;
import java.lang.ref.WeakReference;
import java.util.List;
import ts.car.service.androidauto.data.AndroidAutoDevice;
import ts.car.service.carplay.data.CarPlayDevice;

/* JADX INFO: compiled from: CarPlayAAPresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public class a extends g<d> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public f f711a;

    /* JADX INFO: renamed from: b.b.f.h.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CarPlayAAPresenter.java */
    public class C0040a implements b.b.d.e.d {
        public C0040a() {
        }

        @Override // b.b.d.e.d
        public void g(String str, String str2) {
            if (a.this.getV() != null) {
                ((d) a.this.getV()).sendUIMessageDelay(1, 5000L, null);
            }
        }

        @Override // b.b.d.e.d
        public void j(BluetoothDevice bluetoothDevice) {
        }

        @Override // b.b.d.e.d
        public void m(BluetoothDevice bluetoothDevice) {
            if (a.this.getV() != null) {
                ((d) a.this.getV()).sendUIMessageDelay(1, 5000L, null);
            }
        }
    }

    /* JADX INFO: compiled from: CarPlayAAPresenter.java */
    public class b implements a.g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference<a> f713a;

        public /* synthetic */ b(a aVar, a aVar2, C0040a c0040a) {
            this(aVar2);
        }

        @Override // b.b.f.a.g
        public void c(boolean z) {
            WeakReference<a> weakReference = this.f713a;
            if (weakReference == null || weakReference.get() == null || this.f713a.get().getV() == null) {
                return;
            }
            ((d) this.f713a.get().getV()).A(z);
        }

        @Override // b.b.f.a.g
        public void e(int i, AndroidAutoDevice androidAutoDevice) {
            WeakReference<a> weakReference = this.f713a;
            if (weakReference == null || weakReference.get() == null || this.f713a.get().getV() == null) {
                return;
            }
            ((d) this.f713a.get().getV()).z(i, androidAutoDevice);
        }

        public b(a aVar) {
            this.f713a = new WeakReference<>(aVar);
        }
    }

    /* JADX INFO: compiled from: CarPlayAAPresenter.java */
    public class c implements c.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference<a> f715a;

        public /* synthetic */ c(a aVar, a aVar2, C0040a c0040a) {
            this(aVar2);
        }

        @Override // b.b.f.c.i
        public void c(int i, CarPlayDevice carPlayDevice) {
            WeakReference<a> weakReference = this.f715a;
            if (weakReference == null || weakReference.get() == null || this.f715a.get().getV() == null) {
                return;
            }
            ((d) this.f715a.get().getV()).C(i, carPlayDevice);
        }

        @Override // b.b.f.c.i
        public void onCarPlayDisplayStatusChanged(boolean z) {
            WeakReference<a> weakReference = this.f715a;
            if (weakReference == null || weakReference.get() == null || this.f715a.get().getV() == null) {
                return;
            }
            ((d) this.f715a.get().getV()).D(z);
        }

        public c(a aVar) {
            this.f715a = new WeakReference<>(aVar);
        }
    }

    public boolean A(CPAADevice cPAADevice) {
        return this.f711a.x(cPAADevice);
    }

    public boolean B() {
        return this.f711a.z();
    }

    public boolean C() {
        return this.f711a.A();
    }

    public boolean D() {
        return this.f711a.B();
    }

    public boolean E() {
        return this.f711a.C();
    }

    public void F(CPAADevice cPAADevice) {
        if (cPAADevice == null) {
            return;
        }
        b.b.m.c.a.a("CarPlayAAPresenter", ", remove device: " + cPAADevice.getName() + ", type: " + cPAADevice.getType());
        String btMac = cPAADevice.getBtMac();
        if (TextUtils.isEmpty(btMac)) {
            btMac = cPAADevice.getUsbSN();
        }
        if (CarPlayType.CAR_PLAY.equals(cPAADevice.getType())) {
            this.f711a.m(btMac);
        } else if (CarPlayType.ANDROID_AUTO.equals(cPAADevice.getType())) {
            this.f711a.l(btMac);
        }
    }

    public void G() {
        this.f711a.M();
    }

    public void H() {
        this.f711a.N();
    }

    public void init(Context context) {
        f fVarA = f.e.a();
        this.f711a = fVarA;
        C0040a c0040a = null;
        fVarA.G(a.class.getName(), new b(this, this, c0040a));
        this.f711a.I(a.class.getName(), new c(this, this, c0040a));
        this.f711a.H(a.class.getName(), new C0040a());
    }

    public void q(CPAADevice cPAADevice) {
        if (cPAADevice == null) {
            return;
        }
        b.b.m.c.a.a("CarPlayAAPresenter", ", connect device: " + cPAADevice.getName() + ", type: " + cPAADevice.getType());
        if (b.b.b.b.a.o().n() > 0) {
            b.b.m.c.a.a("CarPlayAAPresenter", "当前连接设备正在通话,蓝牙HFP不做断开处理");
        } else {
            b.b.e.b.n(cPAADevice.getBtMac(), null);
        }
        if (CarPlayType.CAR_PLAY.equals(cPAADevice.getType())) {
            this.f711a.k(cPAADevice);
        } else if (CarPlayType.ANDROID_AUTO.equals(cPAADevice.getType())) {
            this.f711a.j(cPAADevice);
        }
    }

    public void r(CPAADevice cPAADevice) {
        if (cPAADevice == null) {
            return;
        }
        this.f711a.K(cPAADevice);
    }

    public String s(CPAADevice cPAADevice) {
        if (cPAADevice == null) {
            return "";
        }
        b.b.m.c.a.a("CarPlayAAPresenter", ", disconnect device: " + cPAADevice.getName() + ", type: " + cPAADevice.getType());
        String btMac = cPAADevice.getBtMac();
        if (CarPlayType.CAR_PLAY.equals(cPAADevice.getType())) {
            this.f711a.o(btMac);
        } else if (CarPlayType.ANDROID_AUTO.equals(cPAADevice.getType())) {
            if (TextUtils.isEmpty(btMac)) {
                btMac = cPAADevice.getUsbSN();
            }
            this.f711a.n(btMac);
        }
        return btMac;
    }

    public List<CPAADevice> t() {
        return this.f711a.q();
    }

    public AndroidAutoDevice u() {
        return this.f711a.r();
    }

    @Override // b.b.m.e.g
    public void unbindV() {
        f fVar = this.f711a;
        if (fVar != null) {
            fVar.O(a.class.getName());
            this.f711a.Q(a.class.getName());
            this.f711a.P(a.class.getName());
        }
        super.unbindV();
    }

    public CarPlayDevice v() {
        return this.f711a.s();
    }

    public AndroidAutoDevice w() {
        return this.f711a.t();
    }

    public CarPlayDevice x() {
        return this.f711a.u();
    }

    public boolean y(CPAADevice cPAADevice) {
        return this.f711a.v(cPAADevice);
    }

    public boolean z(CPAADevice cPAADevice) {
        return this.f711a.w(cPAADevice);
    }
}
