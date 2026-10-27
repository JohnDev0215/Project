package OooO00o.OooO00o.OooO0o0.OooO0o0.OooO0O0;

import OooO00o.OooO00o.OooO0O0.OooO0o0.OooO00o;
import OooO00o.OooO00o.OooO0o0.OooO0o0.OooO0O0.OooO0OO;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.input.InputManager;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Build;
import com.android.volley.BuildConfig;
import com.blankj.utilcode.util.ToastUtils;
import com.loostone.libtuning.R;
import com.loostone.libtuning.channel.BaseChannelMgr;
import com.loostone.libtuning.channel.byd.BiYaDiAMgr;
import com.loostone.libtuning.channel.byd.data.BiYaDiCarBrand;
import com.loostone.libtuning.channel.byd.old.IDongleListener;
import com.loostone.libtuning.channel.byd.old.PuremicTuning;
import com.micphone.TsBydMicManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import kotlin.Result;
import kotlin.g;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.h;
import kotlin.l;
import kotlin.text.StringsKt__StringsKt;
import kotlin.text.o;

/* JADX INFO: loaded from: classes.dex */
public final class OooO0OO {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f515a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final OooO0O0 f516b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public UsbDevice f517c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final kotlin.a f518d;
    public final kotlin.a e;
    public final kotlin.a f;
    public final kotlin.a g;
    public final kotlin.a h;
    public final kotlin.a i;
    public final OooOo j;
    public HashSet<Integer> k;
    public final f l;
    public final HashSet<String> m;
    public String n;

    public static final class OooOO0 extends Lambda implements kotlin.jvm.b.a<InputManager> {
        public OooOO0() {
            super(0);
        }

        @Override // kotlin.jvm.b.a
        /* JADX INFO: renamed from: OooO00o, reason: merged with bridge method [inline-methods] */
        public final InputManager invoke() {
            Object systemService = OooO0OO.this.f515a.getSystemService("input");
            Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.hardware.input.InputManager");
            return (InputManager) systemService;
        }
    }

    public static final class OooOO0O extends Lambda implements kotlin.jvm.b.a<IntentFilter> {

        /* JADX INFO: renamed from: OooO00o, reason: collision with root package name */
        public static final OooOO0O f520OooO00o = new OooOO0O();

        public OooOO0O() {
            super(0);
        }

        @Override // kotlin.jvm.b.a
        /* JADX INFO: renamed from: OooO00o, reason: merged with bridge method [inline-methods] */
        public final IntentFilter invoke() {
            return new IntentFilter();
        }
    }

    public static final class OooOOO extends Lambda implements kotlin.jvm.b.a<HashMap<String, d>> {

        /* JADX INFO: renamed from: OooO00o, reason: collision with root package name */
        public static final OooOOO f521OooO00o = new OooOOO();

        public OooOOO() {
            super(0);
        }

        @Override // kotlin.jvm.b.a
        /* JADX INFO: renamed from: OooO00o, reason: merged with bridge method [inline-methods] */
        public final HashMap<String, d> invoke() {
            return new HashMap<>();
        }
    }

    public static final class OooOOO0 extends Lambda implements kotlin.jvm.b.a<ArrayList<a.a.d.c.a>> {

        /* JADX INFO: renamed from: OooO00o, reason: collision with root package name */
        public static final OooOOO0 f522OooO00o = new OooOOO0();

        public OooOOO0() {
            super(0);
        }

        @Override // kotlin.jvm.b.a
        /* JADX INFO: renamed from: OooO00o, reason: merged with bridge method [inline-methods] */
        public final ArrayList<a.a.d.c.a> invoke() {
            return new ArrayList<>();
        }
    }

    public static final class OooOo extends BroadcastReceiver {
        public OooOo() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                return;
            }
            try {
                UsbDevice usbDevice = (UsbDevice) intent.getParcelableExtra("device");
                d dVar = (d) OooO0OO.this.C().get(intent.getAction());
                if (dVar == null) {
                    return;
                }
                dVar.a(context, intent, usbDevice);
            } catch (Exception e) {
                OooO00o.OooO00o.OooO0O0.b.f41a.d("DongleEventManager", "------> mUsbReceiver-> onReceive error");
                e.printStackTrace();
            }
        }
    }

    public static final class OooOo00 extends Lambda implements kotlin.jvm.b.a<PendingIntent> {
        public OooOo00() {
            super(0);
        }

        @Override // kotlin.jvm.b.a
        /* JADX INFO: renamed from: OooO00o, reason: merged with bridge method [inline-methods] */
        public final PendingIntent invoke() {
            Intent intent = new Intent();
            intent.setAction("com.loostone.tuning.USB_PERMISSION");
            return PendingIntent.getBroadcast(OooO0OO.this.f515a, 0, intent, Build.VERSION.SDK_INT >= 31 ? 33554432 : 0);
        }
    }

    public static final class Oooo000 extends Lambda implements kotlin.jvm.b.a<UsbManager> {
        public Oooo000() {
            super(0);
        }

        @Override // kotlin.jvm.b.a
        /* JADX INFO: renamed from: OooO00o, reason: merged with bridge method [inline-methods] */
        public final UsbManager invoke() {
            Object systemService = OooO0OO.this.f515a.getSystemService("usb");
            Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.hardware.usb.UsbManager");
            return (UsbManager) systemService;
        }
    }

    public static final class a {
        public a() {
        }

        public /* synthetic */ a(kotlin.jvm.internal.f fVar) {
            this();
        }
    }

    public final class b implements d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ OooO0OO f526a;

        public b(OooO0OO oooO0OO) {
            h.d(oooO0OO, "this$0");
            this.f526a = oooO0OO;
        }

        public static final void b(OooO0OO oooO0OO) {
            h.d(oooO0OO, "this$0");
            oooO0OO.P();
        }

        @Override // OooO00o.OooO00o.OooO0o0.OooO0o0.OooO0O0.OooO0OO.d
        public void a(Context context, Intent intent, UsbDevice usbDevice) {
            h.d(intent, "intent");
            if (context == null || usbDevice == null) {
                return;
            }
            OooO00o.OooO00o.OooO0O0.b bVar = OooO00o.OooO00o.OooO0O0.b.f41a;
            bVar.e("DongleEventManager", h.i(" ------> USB设备接入 ", usbDevice));
            OooO00o oooO00o = OooO00o.f18a;
            boolean zJ = oooO00o.j(usbDevice);
            if (zJ) {
                if ((oooO00o.r(usbDevice.getProductName()) ? this : null) != null) {
                    OooO0OO oooO0OO = this.f526a;
                    bVar.e("DongleEventManager", " ------> 接入 炉石MIC " + ((Object) usbDevice.getProductName()) + "设备");
                    if (oooO0OO.n() == null) {
                        OooO00o.OooO00o.OooO0O0.OooO0o0.b.b(usbDevice, oooO0OO.f515a);
                        oooO0OO.H().requestPermission(usbDevice, oooO0OO.F());
                        return;
                    }
                    return;
                }
            }
            if (zJ && !this.f526a.m(oooO00o.l(usbDevice.getProductName()))) {
                bVar.e("DongleEventManager", "Mic and CarBrand do not match");
                ToastUtils.t(R.string.mic_not_match_car);
                return;
            }
            if (this.f526a.N() && !this.f526a.s(usbDevice)) {
                bVar.e("DongleEventManager", "isBydHasInsideDongle && is not DeviceInsideMic");
                if (zJ) {
                    final OooO0OO oooO0OO2 = this.f526a;
                    OooO00o.OooO00o.OooO0O0.OooO0o0.d.a(new Runnable() { // from class: OooO00o.OooO00o.OooO0o0.OooO0o0.OooO0O0.a
                        @Override // java.lang.Runnable
                        public final void run() {
                            OooO0OO.b.b(oooO0OO2);
                        }
                    });
                    OooO00o.OooO00o.OooO0O0.OooO0o0.b.b(usbDevice, this.f526a.f515a);
                    this.f526a.H().requestPermission(usbDevice, this.f526a.F());
                    return;
                }
                return;
            }
            if (zJ) {
                PuremicTuning.attachDongleEvent = true;
                bVar.e("DongleEventManager", " ------> USB设备接入 炉石设备");
                if (this.f526a.f516b.f() == null) {
                    OooO00o.OooO00o.OooO0O0.OooO0o0.b.b(usbDevice, this.f526a.f515a);
                    this.f526a.H().requestPermission(usbDevice, this.f526a.F());
                }
            }
        }
    }

    public final class c implements d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ OooO0OO f527a;

        public c(OooO0OO oooO0OO) {
            h.d(oooO0OO, "this$0");
            this.f527a = oooO0OO;
        }

        @Override // OooO00o.OooO00o.OooO0o0.OooO0o0.OooO0O0.OooO0OO.d
        public void a(Context context, Intent intent, UsbDevice usbDevice) {
            h.d(intent, "intent");
            OooO00o.OooO00o.OooO0O0.b bVar = OooO00o.OooO00o.OooO0O0.b.f41a;
            bVar.b("DongleEventManager", h.i(" ------> USB设备拔出 ", usbDevice == null ? null : usbDevice.getProductName()));
            if ((OooO00o.f18a.r(usbDevice == null ? null : usbDevice.getProductName()) ? this : null) == null) {
                this.f527a.f516b.j(usbDevice);
                return;
            }
            OooO0OO oooO0OO = this.f527a;
            bVar.b("DongleEventManager", h.i(usbDevice == null ? null : usbDevice.getProductName(), " mic remove"));
            h.b(usbDevice);
            oooO0OO.k(usbDevice, false);
            oooO0OO.x(null);
        }
    }

    public interface d {
        void a(Context context, Intent intent, UsbDevice usbDevice);
    }

    public final class e implements d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ OooO0OO f528a;

        public e(OooO0OO oooO0OO) {
            h.d(oooO0OO, "this$0");
            this.f528a = oooO0OO;
        }

        @Override // OooO00o.OooO00o.OooO0o0.OooO0o0.OooO0O0.OooO0OO.d
        public void a(Context context, Intent intent, UsbDevice usbDevice) {
            h.d(intent, "intent");
            if (h.a("com.loostone.tuning.USB_PERMISSION", intent.getAction())) {
                try {
                    int i = 0;
                    if (!intent.getBooleanExtra("permission", false)) {
                        OooO00o.OooO00o.OooO0O0.b.f41a.b("DongleEventManager", " ------> 权限申请失败");
                        List listE = this.f528a.E();
                        OooO0OO oooO0OO = this.f528a;
                        synchronized (listE) {
                            int size = oooO0OO.E().size();
                            if (size > 0) {
                                while (true) {
                                    int i2 = i + 1;
                                    ((a.a.d.c.a) oooO0OO.E().get(i)).onDonglePermissionDenied();
                                    if (i2 >= size) {
                                        break;
                                    } else {
                                        i = i2;
                                    }
                                }
                            }
                        }
                        return;
                    }
                    OooO00o.OooO00o.OooO0O0.b bVar = OooO00o.OooO00o.OooO0O0.b.f41a;
                    bVar.b("DongleEventManager", " ------> 权限申请成功");
                    if ((OooO00o.f18a.r(usbDevice == null ? null : usbDevice.getProductName()) ? this : null) != null) {
                        OooO0OO oooO0OO2 = this.f528a;
                        bVar.b("DongleEventManager", h.i(usbDevice != null ? usbDevice.getProductName() : null, " permission granted"));
                        h.b(usbDevice);
                        oooO0OO2.k(usbDevice, true);
                        oooO0OO2.x(usbDevice);
                        return;
                    }
                    if (this.f528a.N() && !this.f528a.s(usbDevice)) {
                        bVar.e("DongleEventManager", "startOutsideDongleHid");
                        this.f528a.A(usbDevice);
                        return;
                    }
                    List listE2 = this.f528a.E();
                    OooO0OO oooO0OO3 = this.f528a;
                    synchronized (listE2) {
                        int size2 = oooO0OO3.E().size();
                        if (size2 > 0) {
                            while (true) {
                                int i3 = i + 1;
                                a.a.d.c.a aVar = (a.a.d.c.a) oooO0OO3.E().get(i);
                                h.b(usbDevice);
                                aVar.onDonglePermissionGranted(usbDevice);
                                if (i3 >= size2) {
                                    break;
                                } else {
                                    i = i3;
                                }
                            }
                        }
                    }
                    if (usbDevice != null) {
                        this.f528a.j(usbDevice);
                        return;
                    }
                    return;
                } catch (Exception e) {
                    OooO00o.OooO00o.OooO0O0.b.f41a.d("DongleEventManager", " ------> UsbPermissionHandler->onReceiver:error");
                    e.printStackTrace();
                }
                OooO00o.OooO00o.OooO0O0.b.f41a.d("DongleEventManager", " ------> UsbPermissionHandler->onReceiver:error");
                e.printStackTrace();
            }
        }
    }

    public static final class f implements InputManager.InputDeviceListener {
        public f() {
        }

        @Override // android.hardware.input.InputManager.InputDeviceListener
        public void onInputDeviceAdded(int i) {
            OooO0OO.this.e(i, "onInputDeviceAdded");
        }

        @Override // android.hardware.input.InputManager.InputDeviceListener
        public void onInputDeviceChanged(int i) {
            OooO0OO.this.e(i, "onInputDeviceChanged");
        }

        @Override // android.hardware.input.InputManager.InputDeviceListener
        public void onInputDeviceRemoved(int i) {
            if (OooO0OO.this.k.contains(Integer.valueOf(i))) {
                OooO00o.OooO00o.OooO0O0.b.f41a.e("DongleEventManager", h.i("Puremic Device onInputDeviceRemoved, deviceId is ", Integer.valueOf(i)));
            }
        }
    }

    static {
        new a(null);
    }

    public OooO0OO(Context context, OooO0O0 oooO0O0) {
        h.d(context, "mContext");
        h.d(oooO0O0, "mDeviceManager");
        this.f515a = context;
        this.f516b = oooO0O0;
        this.f518d = kotlin.d.b(OooOOO.f521OooO00o);
        this.e = kotlin.d.b(OooOOO0.f522OooO00o);
        this.f = kotlin.d.b(OooOO0O.f520OooO00o);
        this.g = kotlin.d.b(new Oooo000());
        this.h = kotlin.d.b(new OooOO0());
        this.i = kotlin.d.b(new OooOo00());
        this.j = new OooOo();
        this.k = new HashSet<>();
        this.l = new f();
        this.m = new HashSet<>();
        this.n = BuildConfig.FLAVOR;
    }

    public final void A(UsbDevice usbDevice) {
        if (usbDevice == null) {
            return;
        }
        new OooO00o.OooO00o.OooO0OO.OooO0O0.a().c(usbDevice);
    }

    public final HashMap<String, d> C() {
        return (HashMap) this.f518d.getValue();
    }

    public final List<a.a.d.c.a> E() {
        return (List) this.e.getValue();
    }

    public final PendingIntent F() {
        Object value = this.i.getValue();
        h.c(value, "<get-mPermissionIntent>(...)");
        return (PendingIntent) value;
    }

    public final UsbManager H() {
        return (UsbManager) this.g.getValue();
    }

    public final boolean J() {
        HashMap<String, UsbDevice> deviceList = H().getDeviceList();
        if (deviceList == null) {
            return false;
        }
        for (UsbDevice usbDevice : deviceList.values()) {
            OooO00o oooO00o = OooO00o.f18a;
            h.c(usbDevice, "usbDevice");
            if (oooO00o.j(usbDevice)) {
                return true;
            }
        }
        return false;
    }

    public final void K() {
        try {
            l("android.hardware.usb.action.USB_DEVICE_ATTACHED", new b(this));
            l("android.hardware.usb.action.USB_DEVICE_DETACHED", new c(this));
            l("com.loostone.tuning.USB_PERMISSION", new e(this));
            this.f515a.registerReceiver(this.j, z());
            M();
            L();
            OooO00o.OooO00o.OooO0O0.b.f41a.b("DongleEventManager", "init ------> 初始化成功");
        } catch (Throwable th) {
            OooO00o.OooO00o.OooO0O0.b.f41a.b("DongleEventManager", h.i("init ------> crash=", th.getMessage()));
        }
        try {
            u().registerInputDeviceListener(this.l, null);
            d();
            Result.m23constructorimpl(l.f4084a);
        } catch (Throwable th2) {
            Result.m23constructorimpl(g.a(th2));
        }
    }

    public final void L() {
        HashMap<String, UsbDevice> deviceList = H().getDeviceList();
        if (deviceList == null) {
            return;
        }
        for (UsbDevice usbDevice : deviceList.values()) {
            OooO00o oooO00o = OooO00o.f18a;
            h.c(usbDevice, "usbDevice");
            if (oooO00o.j(usbDevice)) {
                if (!m(oooO00o.l(usbDevice.getProductName()))) {
                    OooO00o.OooO00o.OooO0O0.b.f41a.e("DongleEventManager", "Mic and CarBrand do not match");
                    ToastUtils.t(R.string.mic_not_match_car);
                    return;
                } else if (this.f516b.f() == null || this.f517c == null) {
                    OooO00o.OooO00o.OooO0O0.b.f41a.e("DongleEventManager", h.i(" ------> USB初始化 ", usbDevice));
                    PuremicTuning.attachDongleEvent = false;
                    OooO00o.OooO00o.OooO0O0.OooO0o0.b.b(usbDevice, this.f515a);
                    H().requestPermission(usbDevice, F());
                }
            }
        }
    }

    public final void M() {
        this.m.clear();
        String strB = OooO00o.OooO00o.OooO0O0.OooO0o0.c.f35a.b("sys.ext.vehicle.type");
        this.n = strB;
        OooO00o.OooO00o.OooO0O0.b.f41a.e("DongleEventManager", h.i("micUsageScopeSet, carBrand = ", strB));
        if (o.g("byd", this.n, true)) {
            Collections.addAll(this.m, "Q1", "Q2", "Q3", "DC");
        } else if (o.g(BiYaDiCarBrand.BRAND_DENZA, this.n, true)) {
            Collections.addAll(this.m, "U11", "DC");
        } else {
            Collections.addAll(this.m, "puremic", "byd-mic ls");
        }
    }

    public final boolean N() {
        if (!h.a(OooO00o.f18a.d(OooO00o.OooO00o.OooO0O0.a.f39a.a()), "BIYADI")) {
            return false;
        }
        String strB = OooO00o.OooO00o.OooO0O0.OooO0o0.c.f35a.b(TsBydMicManager.BYD_PROP_INSIDE_MIC);
        OooO00o.OooO00o.OooO0O0.b.f41a.e("DongleEventManager", h.i("hasMic=", strB));
        return h.a("true", strB);
    }

    public final void O() {
        OooO00o.OooO00o.OooO0O0.b.f41a.b("DongleEventManager", " ------> release");
        try {
            this.f515a.unregisterReceiver(this.j);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        try {
            u().unregisterInputDeviceListener(this.l);
            Result.m23constructorimpl(l.f4084a);
        } catch (Throwable th) {
            Result.m23constructorimpl(g.a(th));
        }
    }

    public final void P() {
        IDongleListener dongleListener;
        BaseChannelMgr channelMgr = com.loostone.libtuning.PuremicTuning.Companion.getInstance().getChannelMgr();
        if (!(channelMgr instanceof BiYaDiAMgr) || (dongleListener = ((BiYaDiAMgr) channelMgr).getDongleListener()) == null) {
            return;
        }
        dongleListener.onOutsideDongleRejected();
    }

    public final boolean b() {
        HashMap<String, UsbDevice> deviceList = H().getDeviceList();
        if (deviceList == null) {
            return false;
        }
        Iterator<UsbDevice> it = deviceList.values().iterator();
        while (it.hasNext()) {
            if (s(it.next())) {
                return true;
            }
        }
        return false;
    }

    public final void d() {
        int[] inputDeviceIds = u().getInputDeviceIds();
        h.c(inputDeviceIds, "inputManager.inputDeviceIds");
        for (int i : inputDeviceIds) {
            e(i, "init");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x002f A[Catch: all -> 0x0063, TryCatch #0 {all -> 0x0063, blocks: (B:3:0x0004, B:16:0x005f, B:6:0x0010, B:8:0x001a, B:10:0x0020, B:12:0x0029, B:15:0x005d, B:14:0x002f), top: B:21:0x0004 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void e(int r7, java.lang.String r8) {
        /*
            r6 = this;
            java.lang.String r0 = "DongleEventManager"
            java.lang.String r1 = "it.name"
            android.hardware.input.InputManager r2 = r6.u()     // Catch: java.lang.Throwable -> L63
            android.view.InputDevice r2 = r2.getInputDevice(r7)     // Catch: java.lang.Throwable -> L63
            if (r2 != 0) goto L10
            r7 = 0
            goto L5f
        L10:
            java.lang.String r3 = r2.getName()     // Catch: java.lang.Throwable -> L63
            kotlin.jvm.internal.h.c(r3, r1)     // Catch: java.lang.Throwable -> L63
            java.lang.String r4 = "puremic"
            r5 = 1
            boolean r3 = kotlin.text.f.n(r3, r4, r5)     // Catch: java.lang.Throwable -> L63
            if (r3 != 0) goto L2f
            java.lang.String r3 = r2.getName()     // Catch: java.lang.Throwable -> L63
            kotlin.jvm.internal.h.c(r3, r1)     // Catch: java.lang.Throwable -> L63
            java.lang.String r1 = "byd-mic ls"
            boolean r1 = kotlin.text.f.n(r3, r1, r5)     // Catch: java.lang.Throwable -> L63
            if (r1 == 0) goto L5d
        L2f:
            java.util.HashSet<java.lang.Integer> r1 = r6.k     // Catch: java.lang.Throwable -> L63
            java.lang.Integer r3 = java.lang.Integer.valueOf(r7)     // Catch: java.lang.Throwable -> L63
            r1.add(r3)     // Catch: java.lang.Throwable -> L63
            OooO00o.OooO00o.OooO0O0.b r1 = OooO00o.OooO00o.OooO0O0.b.f41a     // Catch: java.lang.Throwable -> L63
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L63
            r3.<init>()     // Catch: java.lang.Throwable -> L63
            java.lang.String r4 = "Puremic Device "
            r3.append(r4)     // Catch: java.lang.Throwable -> L63
            r3.append(r8)     // Catch: java.lang.Throwable -> L63
            java.lang.String r8 = ", deviceId is "
            r3.append(r8)     // Catch: java.lang.Throwable -> L63
            r3.append(r7)     // Catch: java.lang.Throwable -> L63
            java.lang.String r7 = r3.toString()     // Catch: java.lang.Throwable -> L63
            r1.e(r0, r7)     // Catch: java.lang.Throwable -> L63
            java.lang.String r7 = java.lang.String.valueOf(r2)     // Catch: java.lang.Throwable -> L63
            r1.e(r0, r7)     // Catch: java.lang.Throwable -> L63
        L5d:
            kotlin.l r7 = kotlin.l.f4084a     // Catch: java.lang.Throwable -> L63
        L5f:
            kotlin.Result.m23constructorimpl(r7)     // Catch: java.lang.Throwable -> L63
            goto L6b
        L63:
            r7 = move-exception
            java.lang.Object r7 = kotlin.g.a(r7)
            kotlin.Result.m23constructorimpl(r7)
        L6b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: OooO00o.OooO00o.OooO0o0.OooO0o0.OooO0O0.OooO0OO.e(int, java.lang.String):void");
    }

    public final synchronized void f(OooO00o.OooO00o.OooO0O0.OooO0Oo.OooO00o.OooO00o oooO00o) {
        h.d(oooO00o, "cachedDongleDevice");
        int i = 0;
        int size = E().size();
        if (size > 0) {
            while (true) {
                int i2 = i + 1;
                E().get(i).onDeviceAddedWithHid(oooO00o);
                if (i2 >= size) {
                    break;
                } else {
                    i = i2;
                }
            }
        }
    }

    public final synchronized void g(a.a.d.c.a aVar) {
        h.d(aVar, "callback");
        E().add(aVar);
        OooO00o.OooO00o.OooO0O0.b.f41a.b("DongleEventManager", " ------> 注册Callback成功");
    }

    public final void j(UsbDevice usbDevice) {
        OooO00o.OooO00o.OooO0O0.OooO0Oo.OooO00o.OooO00o oooO00oB;
        if (this.f516b.g(usbDevice) != null || (oooO00oB = this.f516b.b(usbDevice)) == null) {
            return;
        }
        int iU = oooO00oB.u();
        if (iU == 1) {
            p(oooO00oB);
        } else {
            if (iU != 2) {
                return;
            }
            f(oooO00oB);
        }
    }

    public final synchronized void k(UsbDevice usbDevice, boolean z) {
        h.d(usbDevice, "usbDevice");
        int i = 0;
        int size = E().size();
        if (size > 0) {
            while (true) {
                int i2 = i + 1;
                E().get(i).onMicConnectState(usbDevice, z);
                if (i2 >= size) {
                    break;
                } else {
                    i = i2;
                }
            }
        }
    }

    public final void l(String str, d dVar) {
        C().put(str, dVar);
        z().addAction(str);
    }

    public final boolean m(String str) {
        OooO00o.OooO00o.OooO0O0.b.f41a.b("DongleEventManager", "isMicCarBrandMatch, deviceName = " + ((Object) str) + ", carBrand = " + this.n);
        if ((this.n.length() == 0) || str == null) {
            return true;
        }
        if ((str.length() == 0) || o.k(str, "byd-mic ls", true)) {
            return true;
        }
        if ((OooO00o.f18a.r(str) ? this : null) != null) {
            return true;
        }
        Iterator<T> it = this.m.iterator();
        while (it.hasNext()) {
            if (StringsKt__StringsKt.n(str, (String) it.next(), true)) {
                return true;
            }
        }
        return false;
    }

    public final UsbDevice n() {
        return this.f517c;
    }

    public final synchronized void p(OooO00o.OooO00o.OooO0O0.OooO0Oo.OooO00o.OooO00o oooO00o) {
        h.d(oooO00o, "cachedDongleDevice");
        int i = 0;
        int size = E().size();
        if (size > 0) {
            while (true) {
                int i2 = i + 1;
                E().get(i).onDeviceDeniedByHid(oooO00o);
                if (i2 >= size) {
                    break;
                } else {
                    i = i2;
                }
            }
        }
    }

    public final synchronized void q(a.a.d.c.a aVar) {
        h.d(aVar, "callback");
        E().remove(aVar);
        OooO00o.OooO00o.OooO0O0.b.f41a.b("DongleEventManager", " ------> 取消注册Callback成功");
    }

    public final boolean s(UsbDevice usbDevice) {
        if (usbDevice == null) {
            return false;
        }
        OooO00o oooO00o = OooO00o.f18a;
        if (oooO00o.j(usbDevice)) {
            return oooO00o.t(usbDevice.getProductName());
        }
        return false;
    }

    public final InputManager u() {
        return (InputManager) this.h.getValue();
    }

    public final synchronized void v(OooO00o.OooO00o.OooO0O0.OooO0Oo.OooO00o.OooO00o oooO00o) {
        h.d(oooO00o, "cachedDongleDevice");
        int i = 0;
        int size = E().size();
        if (size > 0) {
            while (true) {
                int i2 = i + 1;
                E().get(i).onDeviceRemoved(oooO00o);
                if (i2 >= size) {
                    break;
                } else {
                    i = i2;
                }
            }
        }
    }

    public final void x(UsbDevice usbDevice) {
        this.f517c = usbDevice;
    }

    public final IntentFilter z() {
        return (IntentFilter) this.f.getValue();
    }
}
