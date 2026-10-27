package defpackage;

import android.app.Activity;
import android.app.Dialog;
import android.content.IntentFilter;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener;
import android.hardware.bydauto.gearbox.BYDAutoGearboxDevice;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import defpackage.i02;
import defpackage.on1;
import java.lang.reflect.Method;
import org.chromium.base.ApplicationStatus;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.R$style;
import org.chromium.chrome.browser.ChromeBaseAppCompatActivity;
import org.chromium.chrome.browser.ChromeTabbedActivity;
import org.chromium.chrome.browser.jni.NetworkBlock;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: compiled from: BydPCoordinator.java */
/* JADX INFO: loaded from: classes.dex */
public final class ik implements on1.a {
    public static final String[] q = {BYDAutoGearboxDevice.PERMISSION_BYDAUTO_GEARBOX_COMMON, BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_COMMON, BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET};
    public static ik r;
    public Dialog e;
    public Dialog f;
    public boolean g;
    public Button h;
    public SpannableStringBuilder k;
    public ForegroundColorSpan l;
    public boolean n;
    public b o;
    public int i = 8;
    public int j = 8;
    public Boolean m = null;
    public final a p = new a(Looper.myLooper());

    /* JADX INFO: compiled from: BydPCoordinator.java */
    public class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            final int i2 = 1;
            if (i == 1) {
                Dialog dialog = ik.this.e;
                if (dialog == null || !dialog.isShowing()) {
                    return;
                }
                ik ikVar = ik.this;
                int i3 = ikVar.i - 1;
                ikVar.i = i3;
                if (i3 <= 0) {
                    ik.l();
                    return;
                }
                Button button = ikVar.h;
                if (button != null) {
                    button.setText(ikVar.i());
                }
                sendEmptyMessageDelayed(1, 1000L);
                return;
            }
            final int i4 = 0;
            if (i == 2) {
                final ik ikVar2 = ik.this;
                synchronized (ikVar2) {
                    if (!i02.a(ik.q)) {
                        Log.i("BydPCoordinator  ", "showBydPDialog:  NO Permission PERMISSION_P ");
                        return;
                    }
                    Activity activity = ApplicationStatus.c;
                    if (ik.j(activity)) {
                        Log.e("BydPCoordinator  ", "showBydPDialog, topActivity is null");
                        return;
                    }
                    ik.o();
                    Dialog dialog2 = ikVar2.e;
                    if (dialog2 != null && dialog2.isShowing()) {
                        ikVar2.p.removeCallbacksAndMessages(null);
                        ikVar2.p.sendEmptyMessageDelayed(1, 1000L);
                        Log.i("BydPCoordinator  ", "  mBydPDialog is showing");
                        return;
                    }
                    if (ikVar2.i < 0) {
                        ikVar2.i = 8;
                    }
                    View viewInflate = LayoutInflater.from(activity).inflate(R$layout.dialog_bydp, (ViewGroup) null);
                    Dialog dialog3 = new Dialog(activity, R$style.Dialog_Style);
                    ikVar2.e = dialog3;
                    dialog3.setCancelable(false);
                    ikVar2.e.setContentView(viewInflate);
                    Window window = ikVar2.e.getWindow();
                    window.setGravity(17);
                    WindowManager.LayoutParams attributes = window.getAttributes();
                    DisplayMetrics displayMetrics = activity.getResources().getDisplayMetrics();
                    attributes.width = (Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels) * 4) / 5;
                    attributes.height = -2;
                    window.setAttributes(attributes);
                    Button button2 = (Button) viewInflate.findViewById(R$id.tv_ok);
                    ikVar2.h = button2;
                    button2.setText(ikVar2.i());
                    ikVar2.h.setOnClickListener(new View.OnClickListener() { // from class: fk
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            int i5 = i4;
                            ik ikVar3 = ikVar2;
                            switch (i5) {
                                case 0:
                                    ikVar3.getClass();
                                    ik.l();
                                    break;
                                default:
                                    ikVar3.getClass();
                                    ik.l();
                                    break;
                            }
                        }
                    });
                    ikVar2.e.setOnDismissListener(new gk(0, ikVar2));
                    ikVar2.e.setCanceledOnTouchOutside(false);
                    ikVar2.e.show();
                    ikVar2.p.sendEmptyMessageDelayed(1, 1000L);
                    Log.i("BydPCoordinator  ", "showBydPDialog:  finish");
                    return;
                }
            }
            if (i != 3) {
                if (i != 4) {
                    if (i != 5) {
                        return;
                    }
                    ik.this.m();
                    return;
                }
                ik ikVar3 = ik.this;
                int i5 = ikVar3.j - 1;
                ikVar3.j = i5;
                if (i5 <= 0) {
                    ik.l();
                    return;
                } else {
                    sendEmptyMessageDelayed(4, 1000L);
                    return;
                }
            }
            final ik ikVar4 = ik.this;
            ikVar4.getClass();
            if (!i02.a(ik.q)) {
                Log.i("BydPCoordinator  ", "showConnectWifiDialog:  NO Permission PERMISSION_P ");
                return;
            }
            Activity activity2 = ApplicationStatus.c;
            if (ik.j(activity2)) {
                Log.e("BydPCoordinator  ", "showConnectWifiDialog, topActivity is null");
                return;
            }
            ik.o();
            Dialog dialog4 = ikVar4.e;
            if (dialog4 != null && dialog4.isShowing()) {
                Log.i("BydPCoordinator  ", "mBydPDialog is showing,don't show mConnectWifiDialog");
                return;
            }
            Dialog dialog5 = ikVar4.f;
            if (dialog5 != null && dialog5.isShowing()) {
                Log.i("BydPCoordinator  ", "showConnectWifiDialog, mConnectWifiDialog is showing");
                return;
            }
            View viewInflate2 = LayoutInflater.from(activity2).inflate(R$layout.dialog_connect_wifi, (ViewGroup) null);
            Button button3 = (Button) viewInflate2.findViewById(R$id.btn_cancel);
            Button button4 = (Button) viewInflate2.findViewById(R$id.btn_sure);
            LinearLayout linearLayout = (LinearLayout) viewInflate2.findViewById(R$id.ll_sure_cancel);
            if (n3.Q0()) {
                linearLayout.setLayoutDirection(1);
            } else {
                linearLayout.setLayoutDirection(0);
            }
            Dialog dialog6 = new Dialog(activity2, R$style.Dialog_Style);
            ikVar4.f = dialog6;
            dialog6.setContentView(viewInflate2);
            ikVar4.f.setCancelable(false);
            Window window2 = ikVar4.f.getWindow();
            window2.setGravity(17);
            WindowManager.LayoutParams attributes2 = window2.getAttributes();
            DisplayMetrics displayMetrics2 = activity2.getResources().getDisplayMetrics();
            attributes2.width = (Math.min(displayMetrics2.widthPixels, displayMetrics2.heightPixels) * 4) / 5;
            attributes2.height = -2;
            window2.setAttributes(attributes2);
            ikVar4.f.setCanceledOnTouchOutside(false);
            ikVar4.f.show();
            ikVar4.f.setOnDismissListener(new hk(0, ikVar4));
            button4.setOnClickListener(new by1(i2, kk.b(activity2)));
            button3.setOnClickListener(new View.OnClickListener() { // from class: fk
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    int i52 = i2;
                    ik ikVar32 = ikVar4;
                    switch (i52) {
                        case 0:
                            ikVar32.getClass();
                            ik.l();
                            break;
                        default:
                            ikVar32.getClass();
                            ik.l();
                            break;
                    }
                }
            });
        }
    }

    /* JADX INFO: compiled from: BydPCoordinator.java */
    public class b extends AbsBYDAutoGearboxListener {
        public Boolean a = null;

        public b() {
        }

        @Override // android.hardware.IBYDAutoListener
        public final void isConnected(boolean z) {
            super.isConnected(z);
            Log.w("BydPCoordinator  ", "checkResume:bydAutoType: isBinding     " + z);
            ik ikVar = ik.this;
            if (z) {
                this.a = Boolean.valueOf(ikVar.h());
            } else {
                ikVar.a(null, false);
            }
        }

        @Override // android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener
        public final void onGearChanged(int i) {
            super.onGearChanged(i);
            C0175b.s("onGearChanged: ", i, "BydPCoordinator  ");
            Boolean bool = this.a;
            if (bool == null || !bool.booleanValue()) {
                return;
            }
            ik.this.b(i);
        }

        @Override // android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener
        public final void onGearboxAutoModeLevelChanged(int i) {
            super.onGearboxAutoModeLevelChanged(i);
            C0175b.s("onGearboxAutoModeLevelChanged: ", i, "BydPCoordinator  ");
            Boolean bool = this.a;
            if (bool == null || bool.booleanValue()) {
                return;
            }
            ik.this.b(i);
        }
    }

    /* JADX INFO: compiled from: BydPCoordinator.java */
    public class c implements i02.a {
        public final /* synthetic */ ChromeBaseAppCompatActivity a;

        public c(ChromeBaseAppCompatActivity chromeBaseAppCompatActivity) {
            this.a = chromeBaseAppCompatActivity;
        }
    }

    public ik() {
        int iIntValue;
        this.n = false;
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            Method method = cls.getMethod("getInt", String.class, Integer.TYPE);
            method.setAccessible(true);
            Object objInvoke = method.invoke(cls, "persist.sys.brower_status", -1);
            if (objInvoke instanceof Integer) {
                iIntValue = ((Integer) objInvoke).intValue();
                try {
                    Log.i("BydPCoordinator  ", "getNetForCountry:  status  " + iIntValue);
                } catch (Exception e) {
                    e = e;
                    Log.e("BydPCoordinator  ", "getNetForCountry: " + e.getMessage(), e);
                }
            } else {
                iIntValue = 0;
            }
        } catch (Exception e2) {
            e = e2;
            iIntValue = 0;
        }
        this.n = iIntValue == 1;
        on1 on1VarA = at0.a();
        this.g = on1VarA.d();
        on1VarA.b(new on1.a() { // from class: ek
            @Override // on1.a
            public final void X0() {
                this.e.X0();
            }
        });
        jk jkVar = new jk(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.ethernet.ETHERNET_STATE_CHANGED");
        intentFilter.addAction("android.net.ethernet.STATE_CHANGE");
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        intentFilter.addAction("android.net.wifi.STATE_CHANGE");
        intentFilter.addAction("android.net.wifi.STATE_CHANGE");
        xz.a.registerReceiver(jkVar, intentFilter);
        Log.i("BydPCoordinator  ", "onCreate, init bydP and WIFI dialog end BuildConfig.NEED_GEARBOX_AUTO_MODE_P:true");
    }

    public static synchronized ik g() {
        if (r == null) {
            r = new ik();
        }
        return r;
    }

    public static boolean j(Activity activity) {
        return activity == null || activity.isDestroyed() || activity.isFinishing();
    }

    public static void l() {
        try {
            Log.i("BydPCoordinator  ", "exitApp");
            xz.b(false);
        } catch (Exception e) {
            Log.e("BydPCoordinator  ", "killAppProcess: " + e.getMessage(), e);
        }
    }

    public static void o() {
        Tab tabP;
        Activity activity = ApplicationStatus.c;
        if (j(activity) || !(activity instanceof ChromeTabbedActivity)) {
            return;
        }
        ChromeTabbedActivity chromeTabbedActivity = (ChromeTabbedActivity) activity;
        WebContents webContentsC = null;
        if (chromeTabbedActivity.h2() && (tabP = z7.p(chromeTabbedActivity.D2())) != null) {
            webContentsC = tabP.c();
        }
        if (webContentsC != null) {
            webContentsC.f0();
            Log.i("BydPCoordinator  ", "  stopTab: suspendAllMediaPlayers");
        }
    }

    @Override // on1.a
    public final void X0() {
        a aVar = this.p;
        if (aVar != null) {
            aVar.removeCallbacksAndMessages(null);
        }
        Log.i("BydPCoordinator  ", "onNightModeStateChanged: 深浅模式更改");
        boolean zD = at0.a().d();
        if (this.g == zD || this.i < 0) {
            this.i = 8;
        }
        this.g = zD;
    }

    public final void a(NetworkInfo networkInfo, boolean z) {
        boolean zBooleanValue;
        if (this.n) {
            return;
        }
        a aVar = this.p;
        aVar.removeMessages(3);
        if (!z) {
            networkInfo = ((ConnectivityManager) xz.a.getSystemService("connectivity")).getActiveNetworkInfo();
        }
        if (networkInfo != null) {
            boolean zIsConnected = networkInfo.isConnected();
            int type = networkInfo.getType();
            NetworkInfo.State state = networkInfo.getState();
            Log.i("BydPCoordinator  ", "checkNetworkAvailable, isFromReceiver: " + z + "   isConnected: " + zIsConnected + ", netType: " + type + " networkInfoState:" + state + " isWifiConnected " + this.m);
            if (z) {
                Boolean boolValueOf = Boolean.valueOf(state.equals(NetworkInfo.State.CONNECTED));
                this.m = boolValueOf;
                if (boolValueOf.booleanValue() || type == 9) {
                    f();
                    return;
                }
            } else {
                Boolean bool = this.m;
                if (bool != null) {
                    zBooleanValue = bool.booleanValue();
                } else {
                    zBooleanValue = true;
                    if (type != 1) {
                        zBooleanValue = false;
                    }
                }
                if (zBooleanValue || type == 9) {
                    f();
                    return;
                }
            }
        } else {
            Log.w("BydPCoordinator  ", "checkNetworkAvailable: networkInfo == null  isFromReceiver: " + z);
        }
        Dialog dialog = this.e;
        if (dialog == null || !dialog.isShowing()) {
            aVar.sendEmptyMessageDelayed(3, 2L);
        }
    }

    public final void b(int i) {
        a aVar = this.p;
        if (i == 1) {
            aVar.removeCallbacksAndMessages(null);
            e();
            this.i = 8;
            a(null, false);
            return;
        }
        Dialog dialog = this.e;
        if (dialog == null || !dialog.isShowing()) {
            aVar.sendEmptyMessageDelayed(2, 0L);
        }
    }

    public final void c() {
        try {
            this.p.removeCallbacksAndMessages(null);
            if (this.j != this.i) {
                this.i = 8;
            }
            if (j(ApplicationStatus.c)) {
                Log.e("BydPCoordinator  ", "checkResume, topActivity is null");
                return;
            }
            BYDAutoGearboxDevice bYDAutoGearboxDevice = BYDAutoGearboxDevice.getInstance(xz.a);
            if (this.o != null) {
                h();
                return;
            }
            b bVar = new b();
            this.o = bVar;
            bYDAutoGearboxDevice.registerListener((AbsBYDAutoGearboxListener) bVar);
        } catch (Exception e) {
            Log.e("BydPCoordinator  ", "checkResume:  " + e.getMessage(), e);
        }
    }

    public final void d(Activity activity, boolean z) {
        if (j(activity)) {
            return;
        }
        Activity activity2 = ApplicationStatus.c;
        Log.i("BydPCoordinator  ", "clearDialog: isStop  " + z + "  activity   " + activity + "  lastActivity " + activity2);
        if (j(activity2) || activity.equals(activity2)) {
            a aVar = this.p;
            aVar.removeCallbacksAndMessages(null);
            if (z) {
                this.j = this.i;
                Dialog dialog = this.e;
                if (dialog != null && dialog.isShowing()) {
                    aVar.sendEmptyMessageDelayed(4, 300L);
                }
            }
            e();
            f();
            Log.i("BydPCoordinator  ", "clearDialog: finish");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void e() {
        try {
            try {
                Log.i("BydPCoordinator  ", "dismiss BydPDialog");
                this.e.dismiss();
            } catch (Exception e) {
                Log.w("BydPCoordinator  ", "dismissBydPDialogSafety ： " + e.getMessage());
            }
        } finally {
            this.e = null;
            this.h = null;
        }
    }

    public final void f() {
        try {
            try {
                Log.i("BydPCoordinator  ", "dismiss WifiDialog");
                this.f.dismiss();
            } catch (Exception e) {
                Log.w("BydPCoordinator  ", "dismissConnectWifiDialogSafety ： " + e.getMessage());
            }
        } finally {
            this.f = null;
        }
    }

    public final boolean h() {
        BYDAutoGearboxDevice bYDAutoGearboxDevice = BYDAutoGearboxDevice.getInstance(xz.a);
        int iL = oj2.l();
        boolean zW = oj2.w(iL);
        int gear = zW ? bYDAutoGearboxDevice.getGear() : bYDAutoGearboxDevice.getGearboxAutoModeLevel();
        Log.i("BydPCoordinator  ", " checkResume:bydAutoType=" + iL + " isCarEV=" + zW + "  getGear=" + bYDAutoGearboxDevice.getGear() + "  getGearboxAutoModeLevel=" + bYDAutoGearboxDevice.getGearboxAutoModeLevel());
        if (iL != 0) {
            b(gear);
        } else {
            a(null, false);
        }
        return zW;
    }

    public final SpannableStringBuilder i() {
        String string = xz.a.getString(R$string.p_ok, Integer.valueOf(this.i));
        if (this.k == null) {
            this.k = new SpannableStringBuilder();
        }
        if (this.l == null) {
            int i = R$color.dialog_button_true_text_color;
            String str = kp2.a;
            this.l = new ForegroundColorSpan(po2.a(xz.a, i));
        }
        this.k.clear();
        this.k.append((CharSequence) string);
        int iIndexOf = string.indexOf("(");
        if (iIndexOf < 0) {
            iIndexOf = string.indexOf("（");
        }
        if (iIndexOf >= 0 && iIndexOf < this.k.length()) {
            SpannableStringBuilder spannableStringBuilder = this.k;
            spannableStringBuilder.setSpan(this.l, iIndexOf, spannableStringBuilder.length(), 17);
        }
        return this.k;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0074  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean k() {
        /*
            r5 = this;
            r0 = 1
            r1 = 0
            android.content.Context r2 = defpackage.xz.a     // Catch: java.lang.Exception -> L25
            android.hardware.bydauto.gearbox.BYDAutoGearboxDevice r2 = android.hardware.bydauto.gearbox.BYDAutoGearboxDevice.getInstance(r2)     // Catch: java.lang.Exception -> L25
            int r3 = defpackage.oj2.l()     // Catch: java.lang.Exception -> L25
            if (r3 == 0) goto L3d
            int r3 = defpackage.oj2.l()     // Catch: java.lang.Exception -> L25
            boolean r3 = defpackage.oj2.w(r3)     // Catch: java.lang.Exception -> L25
            if (r3 == 0) goto L1d
            int r2 = r2.getGear()     // Catch: java.lang.Exception -> L25
            goto L21
        L1d:
            int r2 = r2.getGearboxAutoModeLevel()     // Catch: java.lang.Exception -> L25
        L21:
            if (r0 == r2) goto L3d
            r2 = r0
            goto L3e
        L25:
            r2 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "isHasBydP "
            r3.<init>(r4)
            java.lang.String r4 = r2.getMessage()
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            java.lang.String r4 = "BydPCoordinator  "
            android.util.Log.e(r4, r3, r2)
        L3d:
            r2 = r1
        L3e:
            if (r2 != 0) goto L79
            android.content.Context r2 = defpackage.xz.a
            java.lang.String r3 = "connectivity"
            java.lang.Object r2 = r2.getSystemService(r3)
            android.net.ConnectivityManager r2 = (android.net.ConnectivityManager) r2
            android.net.NetworkInfo r2 = r2.getActiveNetworkInfo()
            if (r2 == 0) goto L74
            java.lang.Boolean r5 = r5.m
            if (r5 == 0) goto L59
            boolean r5 = r5.booleanValue()
            goto L62
        L59:
            int r5 = r2.getType()
            if (r5 != r0) goto L61
            r5 = r0
            goto L62
        L61:
            r5 = r1
        L62:
            if (r5 != 0) goto L6f
            int r5 = r2.getType()
            r2 = 9
            if (r5 != r2) goto L6d
            goto L6f
        L6d:
            r5 = r1
            goto L70
        L6f:
            r5 = r0
        L70:
            if (r5 != 0) goto L74
            r5 = r0
            goto L75
        L74:
            r5 = r1
        L75:
            if (r5 == 0) goto L78
            goto L79
        L78:
            r0 = r1
        L79:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ik.k():boolean");
    }

    public final void m() {
        String[] strArr = q;
        if (i02.a(strArr)) {
            NetworkBlock.c();
            c();
            return;
        }
        Activity activity = ApplicationStatus.c;
        if (j(activity) || !(activity instanceof ChromeBaseAppCompatActivity)) {
            return;
        }
        ChromeBaseAppCompatActivity chromeBaseAppCompatActivity = (ChromeBaseAppCompatActivity) activity;
        chromeBaseAppCompatActivity.G = new c(chromeBaseAppCompatActivity);
        if (!i02.a(strArr)) {
            n2.e(100, chromeBaseAppCompatActivity, strArr);
            return;
        }
        NetworkBlock.c();
        c();
        chromeBaseAppCompatActivity.G = null;
    }

    public final void n() {
        Activity activity = ApplicationStatus.c;
        if (j(activity)) {
            return;
        }
        boolean zHasVisibleActivities = ApplicationStatus.hasVisibleActivities();
        boolean zK = k();
        if (zHasVisibleActivities && !zK && (activity instanceof ChromeTabbedActivity)) {
            ChromeTabbedActivity chromeTabbedActivity = (ChromeTabbedActivity) activity;
            chromeTabbedActivity.U2(false);
            chromeTabbedActivity.T2(false);
        }
    }
}
