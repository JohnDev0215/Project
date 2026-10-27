package OooO00o.OooO00o.OooO0o0.OooO0o0.OooO00o;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import java.util.HashMap;
import java.util.Objects;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.f;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
public final class OooO00o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f496a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final kotlin.a f497b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final kotlin.a f498c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final kotlin.a f499d;
    public final kotlin.a e;
    public final OooOOO f;

    public static final class OooOO0 extends Lambda implements kotlin.jvm.b.a<IntentFilter> {

        /* JADX INFO: renamed from: OooO00o, reason: collision with root package name */
        public static final OooOO0 f500OooO00o = new OooOO0();

        public OooOO0() {
            super(0);
        }

        @Override // kotlin.jvm.b.a
        /* JADX INFO: renamed from: OooO00o, reason: merged with bridge method [inline-methods] */
        public final IntentFilter invoke() {
            return new IntentFilter();
        }
    }

    public static final class OooOO0O extends Lambda implements kotlin.jvm.b.a<HashMap<String, d>> {

        /* JADX INFO: renamed from: OooO00o, reason: collision with root package name */
        public static final OooOO0O f501OooO00o = new OooOO0O();

        public OooOO0O() {
            super(0);
        }

        @Override // kotlin.jvm.b.a
        /* JADX INFO: renamed from: OooO00o, reason: merged with bridge method [inline-methods] */
        public final HashMap<String, d> invoke() {
            return new HashMap<>();
        }
    }

    public static final class OooOOO extends BroadcastReceiver {
        public OooOOO() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                return;
            }
            try {
                UsbDevice usbDevice = (UsbDevice) intent.getParcelableExtra("device");
                d dVar = (d) OooO00o.this.d().get(intent.getAction());
                if (dVar == null) {
                    return;
                }
                dVar.a(context, intent, usbDevice);
            } catch (Exception e) {
                OooO00o.OooO00o.OooO0O0.b.f41a.d("TuningDeviceManager", " mUsbReceiver-> onReceive 异常:" + ((Object) e.getMessage()) + ' ');
            }
        }
    }

    public static final class OooOOO0 extends Lambda implements kotlin.jvm.b.a<PendingIntent> {
        public OooOOO0() {
            super(0);
        }

        @Override // kotlin.jvm.b.a
        /* JADX INFO: renamed from: OooO00o, reason: merged with bridge method [inline-methods] */
        public final PendingIntent invoke() {
            Intent intent = new Intent();
            intent.setAction("com.loostone.tuning.TUNING_DEVICE_PERMISSION");
            return PendingIntent.getBroadcast(OooO00o.this.f496a, 0, intent, 0);
        }
    }

    public static final class OooOOOO extends Lambda implements kotlin.jvm.b.a<UsbManager> {
        public OooOOOO() {
            super(0);
        }

        @Override // kotlin.jvm.b.a
        /* JADX INFO: renamed from: OooO00o, reason: merged with bridge method [inline-methods] */
        public final UsbManager invoke() {
            Object systemService = OooO00o.this.f496a.getSystemService("usb");
            Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.hardware.usb.UsbManager");
            return (UsbManager) systemService;
        }
    }

    public static final class a {
        public a() {
        }

        public /* synthetic */ a(f fVar) {
            this();
        }
    }

    public final class b implements d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ OooO00o f505a;

        public b(OooO00o oooO00o) {
            h.d(oooO00o, "this$0");
            this.f505a = oooO00o;
        }

        @Override // OooO00o.OooO00o.OooO0o0.OooO0o0.OooO00o.OooO00o.d
        public void a(Context context, Intent intent, UsbDevice usbDevice) {
            h.d(intent, "intent");
            if (context == null || usbDevice == null || !OooO00o.OooO00o.OooO0O0.OooO0o0.OooO00o.f18a.o(usbDevice)) {
                return;
            }
            this.f505a.h().requestPermission(usbDevice, this.f505a.f());
        }
    }

    public final class c implements d {
        public c(OooO00o oooO00o) {
            h.d(oooO00o, "this$0");
        }

        @Override // OooO00o.OooO00o.OooO0o0.OooO0o0.OooO00o.OooO00o.d
        public void a(Context context, Intent intent, UsbDevice usbDevice) {
            h.d(intent, "intent");
            if (OooO00o.OooO00o.OooO0O0.OooO0o0.OooO00o.f18a.o(usbDevice)) {
                OooO00o.OooO00o.OooO0o0.OooO00o.OooO0O0.a.f192a.e();
            }
        }
    }

    public interface d {
        void a(Context context, Intent intent, UsbDevice usbDevice);
    }

    public final class e implements d {
        public e(OooO00o oooO00o) {
            h.d(oooO00o, "this$0");
        }

        @Override // OooO00o.OooO00o.OooO0o0.OooO0o0.OooO00o.OooO00o.d
        public void a(Context context, Intent intent, UsbDevice usbDevice) {
            h.d(intent, "intent");
            if (!h.a("com.loostone.tuning.TUNING_DEVICE_PERMISSION", intent.getAction()) || usbDevice == null) {
                return;
            }
            try {
                if (intent.getBooleanExtra("permission", false)) {
                    OooO00o.OooO00o.OooO0O0.b.f41a.b("TuningDeviceManager", "  权限申请成功");
                    OooO00o.OooO00o.OooO0o0.OooO00o.OooO0O0.a.f192a.b(usbDevice);
                } else {
                    OooO00o.OooO00o.OooO0O0.b.f41a.b("TuningDeviceManager", "  权限申请失败");
                }
            } catch (Exception e) {
                OooO00o.OooO00o.OooO0O0.b.f41a.d("TuningDeviceManager", h.i("onReceiver:error ", e.getMessage()));
                e.printStackTrace();
            }
        }
    }

    static {
        new a(null);
    }

    public OooO00o(Context context) {
        h.d(context, "mContext");
        this.f496a = context;
        this.f497b = kotlin.d.b(OooOO0O.f501OooO00o);
        this.f498c = kotlin.d.b(OooOO0.f500OooO00o);
        this.f499d = kotlin.d.b(new OooOOOO());
        this.e = kotlin.d.b(new OooOOO0());
        this.f = new OooOOO();
    }

    public final IntentFilter b() {
        return (IntentFilter) this.f498c.getValue();
    }

    public final void c(String str, d dVar) {
        d().put(str, dVar);
        b().addAction(str);
    }

    public final HashMap<String, d> d() {
        return (HashMap) this.f497b.getValue();
    }

    public final PendingIntent f() {
        Object value = this.e.getValue();
        h.c(value, "<get-mPermissionIntent>(...)");
        return (PendingIntent) value;
    }

    public final UsbManager h() {
        return (UsbManager) this.f499d.getValue();
    }

    public final void j() {
        HashMap<String, UsbDevice> deviceList = h().getDeviceList();
        if (deviceList == null) {
            return;
        }
        for (UsbDevice usbDevice : deviceList.values()) {
            if (OooO00o.OooO00o.OooO0O0.OooO0o0.OooO00o.f18a.o(usbDevice)) {
                h().requestPermission(usbDevice, f());
            }
        }
    }

    public final void k() {
        try {
            c("android.hardware.usb.action.USB_DEVICE_ATTACHED", new b(this));
            c("android.hardware.usb.action.USB_DEVICE_DETACHED", new c(this));
            c("com.loostone.tuning.TUNING_DEVICE_PERMISSION", new e(this));
            this.f496a.registerReceiver(this.f, b());
            j();
            OooO00o.OooO00o.OooO0O0.b.f41a.b("TuningDeviceManager", "init  初始化成功");
        } catch (Throwable th) {
            OooO00o.OooO00o.OooO0O0.b.f41a.b("TuningDeviceManager", h.i("init  crash=", th.getMessage()));
        }
    }

    public final void l() {
        OooO00o.OooO00o.OooO0O0.b.f41a.b("TuningDeviceManager", "  release");
        try {
            this.f496a.unregisterReceiver(this.f);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        OooO00o.OooO00o.OooO0o0.OooO00o.OooO0O0.a.f192a.e();
    }
}
