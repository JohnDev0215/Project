package com.byd.auto_photo;

import android.car.VehicleAreaDoor;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.hardware.bydauto.setting.BYDAutoSettingDevice;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import b.a.d;
import com.byd.bean.FileBean;
import com.byd.util.e;
import com.byd.util.s;
import com.byd.util.w;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseQrCodeShareActivity extends BaseDialogActivity implements View.OnClickListener {
    private ArrayList<FileBean> g;
    protected View h;
    protected View i;
    protected View j;
    private ImageView k;
    protected TextView l;
    protected TextView m;
    protected TextView n;
    protected TextView o;
    private c p;
    private String q;
    private ProgressBar r;
    private int s;
    protected boolean t;
    private b u;
    private TextView v;
    protected ImageView w;
    protected int x;
    private boolean y;
    private boolean z;

    private class b extends BroadcastReceiver {
        private b() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String str;
            c cVar;
            c cVar2;
            Boolean bool;
            String action = intent.getAction();
            w.c("Ap state change receive : " + action);
            BaseQrCodeShareActivity.this.p.removeMessages(2);
            BaseQrCodeShareActivity.this.p.removeMessages(3);
            if (!"android.net.wifi.WIFI_AP_STATE_CHANGED".equals(action)) {
                if ("android.net.wifi.BYD_WIFI_AP_STATE_CHANGED".equals(action)) {
                    BaseQrCodeShareActivity.this.p.sendEmptyMessageDelayed(3, 500L);
                }
                return;
            }
            int intExtra = intent.getIntExtra("wifi_state", 0);
            w.c("AP state change : " + intExtra);
            switch (intExtra) {
                case 10:
                    str = "热点正在关闭";
                    w.c(str);
                    break;
                case 11:
                    w.c("热点已关闭");
                    cVar = BaseQrCodeShareActivity.this.p;
                    cVar2 = BaseQrCodeShareActivity.this.p;
                    bool = Boolean.FALSE;
                    cVar.sendMessageDelayed(cVar2.obtainMessage(2, bool), 500L);
                    break;
                case 12:
                    str = "热点正在打开";
                    w.c(str);
                    break;
                case 13:
                    w.c("热点已打开");
                    cVar = BaseQrCodeShareActivity.this.p;
                    cVar2 = BaseQrCodeShareActivity.this.p;
                    bool = Boolean.TRUE;
                    cVar.sendMessageDelayed(cVar2.obtainMessage(2, bool), 500L);
                    break;
            }
        }
    }

    private static class c extends d<BaseQrCodeShareActivity> {
        public c(BaseQrCodeShareActivity baseQrCodeShareActivity) {
            super(baseQrCodeShareActivity);
        }

        @Override // b.a.d
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void handleMessage(BaseQrCodeShareActivity baseQrCodeShareActivity, Message message) {
            int i = message.what;
            if (i == 1) {
                baseQrCodeShareActivity.r.setVisibility(8);
                baseQrCodeShareActivity.x();
            } else if (i == 2) {
                baseQrCodeShareActivity.u(((Boolean) message.obj).booleanValue());
            } else {
                if (i != 3) {
                    return;
                }
                baseQrCodeShareActivity.H();
            }
        }
    }

    private boolean C(String str) {
        PackageManager packageManager = getPackageManager();
        Intent intent = new Intent();
        intent.setPackage(str);
        intent.setAction("com.byd.systemsettings.action.OPEN_PER_HOTSPOT_SETTING");
        List<ResolveInfo> listQueryBroadcastReceivers = packageManager.queryBroadcastReceivers(intent, 0);
        if (listQueryBroadcastReceivers == null || listQueryBroadcastReceivers.isEmpty()) {
            return false;
        }
        w.c("==>" + listQueryBroadcastReceivers.get(0));
        return true;
    }

    private boolean E(int[] iArr) {
        if (iArr.length == 0) {
            w.c("grantResult length is 0");
            return false;
        }
        for (int i : iArr) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }

    private boolean F() {
        if (s.b(this) == 1) {
            return true;
        }
        return this.t ? s.e(this) : com.byd.share.a.s(this).A();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void H() {
        boolean z;
        int iA = s.a(this);
        w.c("check byd ap state : " + iA);
        if (iA == 13) {
            z = true;
        } else if (iA != 11) {
            return;
        } else {
            z = false;
        }
        u(z);
    }

    private String[] I() {
        LinkedList linkedList = new LinkedList(Arrays.asList(e.q(((PhotoBaseApp) getApplication()).c())));
        linkedList.add(BYDAutoSettingDevice.PERMISSION_BYDAUTO_SETTING_COMMON);
        String[] strArr = new String[linkedList.size()];
        linkedList.toArray(strArr);
        return strArr;
    }

    private void K() {
        if (this.q != null) {
            String strU = com.byd.share.c.w(this).u(this.q);
            w.c("====>ip:" + strU);
            if (strU == null) {
                w.c("create url fail");
            } else {
                this.k.setImageBitmap(com.byd.share.b.a(strU, ViewCompat.MEASURED_STATE_MASK));
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0075  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void N() {
        /*
            r6 = this;
            boolean r0 = r6.z
            if (r0 == 0) goto L5
            return
        L5:
            java.lang.String r0 = com.byd.util.e.w()
            if (r0 != 0) goto L11
            java.lang.String r0 = "toHotpotActivity platform is null"
            com.byd.util.w.c(r0)
            return
        L11:
            android.content.Intent r1 = new android.content.Intent
            r1.<init>()
            java.lang.String r2 = "android.intent.category.DEFAULT"
            r1.addCategory(r2)
            java.lang.String r2 = "com.byd.carsettings"
            boolean r3 = r6.C(r2)
            if (r3 == 0) goto L27
            r6.v(r1, r2)
            return
        L27:
            java.lang.String r2 = "com.byd.systemsettings"
            boolean r3 = r6.C(r2)
            if (r3 == 0) goto L33
            r6.v(r1, r2)
            return
        L33:
            java.lang.String r3 = "Di1"
            boolean r3 = r0.startsWith(r3)
            r4 = 0
            if (r3 != 0) goto L69
            java.lang.String r3 = "Di2"
            boolean r3 = r0.startsWith(r3)
            if (r3 != 0) goto L69
            java.lang.String r3 = "3.0UI"
            boolean r3 = r0.contains(r3)
            if (r3 == 0) goto L4d
            goto L69
        L4d:
            java.lang.String r3 = "Di4.0"
            boolean r3 = r0.startsWith(r3)
            if (r3 != 0) goto L61
            java.lang.String r3 = "3.5UI"
            boolean r0 = r0.contains(r3)
            if (r0 == 0) goto L5e
            goto L61
        L5e:
            java.lang.String r0 = "com.android.settings.WIFI_TETHER_SETTINGS"
            goto L6b
        L61:
            android.content.ComponentName r0 = new android.content.ComponentName
            java.lang.String r3 = "com.byd.systemsettings.hotspot.PerHotspotActivity"
            r0.<init>(r2, r3)
            goto L6e
        L69:
            java.lang.String r0 = "android.intent.action.HOTSPOT_SETTINGS"
        L6b:
            r5 = r4
            r4 = r0
            r0 = r5
        L6e:
            if (r4 == 0) goto L73
            r1.setAction(r4)
        L73:
            if (r0 == 0) goto L78
            r1.setComponent(r0)
        L78:
            r0 = 1
            r6.z = r0     // Catch: java.lang.Exception -> L93
            r6.startActivity(r1)     // Catch: java.lang.Exception -> L93
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L93
            r0.<init>()     // Catch: java.lang.Exception -> L93
            java.lang.String r2 = "to hotpot activity : "
            r0.append(r2)     // Catch: java.lang.Exception -> L93
            r0.append(r1)     // Catch: java.lang.Exception -> L93
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Exception -> L93
            com.byd.util.w.c(r0)     // Catch: java.lang.Exception -> L93
            goto Lb2
        L93:
            r0 = move-exception
            r0.printStackTrace()
            r1 = 0
            r6.z = r1
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "to hotpot exception : "
            r1.append(r2)
            java.lang.String r0 = r0.getMessage()
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            com.byd.util.w.c(r0)
        Lb2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.auto_photo.BaseQrCodeShareActivity.N():void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u(boolean z) {
        x();
        if (z || !com.byd.share.c.w(this).B()) {
            return;
        }
        com.byd.share.c.w(this).F();
    }

    private void v(Intent intent, String str) {
        intent.setPackage(str);
        intent.setAction("com.byd.systemsettings.action.OPEN_PER_HOTSPOT_SETTING");
        intent.addFlags(VehicleAreaDoor.DOOR_HOOD);
        sendBroadcast(intent);
        w.c("send broadcast : " + intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x() {
        if (!this.y) {
            w.c("check connect fail , not permission!");
            return;
        }
        if (!F()) {
            J();
            return;
        }
        if (this.t) {
            boolean zE = s.e(this);
            if (!zE && s.b(this) != 1) {
                J();
                return;
            }
            this.i.setVisibility(8);
            this.h.setVisibility(0);
            this.j.setVisibility(8);
            this.o.setVisibility(zE ? 0 : 4);
        } else if (!M()) {
            return;
        }
        this.i.setVisibility(8);
        this.h.setVisibility(0);
        if (this.q == null) {
            this.q = com.byd.share.c.w(this).t();
            com.byd.share.c.w(this).D(this.q, this.g);
        }
        if (com.byd.share.c.w(this).B()) {
            K();
            return;
        }
        com.byd.share.c.w(this).A();
        if (com.byd.share.c.w(this).z()) {
            com.byd.share.c.w(this).E();
        }
        K();
    }

    private boolean y() {
        Intent intent = getIntent();
        if (!intent.hasExtra("extras_share_beans")) {
            w.c("qr code share -> not data to share");
            return false;
        }
        this.x = intent.getIntExtra("extras_ui_visibility", 0);
        ArrayList<FileBean> parcelableArrayListExtra = intent.getParcelableArrayListExtra("extras_share_beans");
        this.g = parcelableArrayListExtra;
        return parcelableArrayListExtra != null && parcelableArrayListExtra.size() > 0;
    }

    private int z(Configuration configuration) {
        Resources resources;
        int i;
        if (configuration.orientation == 1) {
            resources = getResources();
            i = R.dimen.qr_code_dialog_width_port;
        } else {
            resources = getResources();
            i = R.dimen.qr_code_dialog_width_land;
        }
        return resources.getDimensionPixelSize(i);
    }

    protected int A() {
        return R.layout.activity_qr_code_share;
    }

    protected String B() {
        return getResources().getString(R.string.title_share_qr_code);
    }

    protected boolean D(Context context, String[] strArr) {
        if (Build.VERSION.SDK_INT < 23 || strArr.length == 0) {
            return true;
        }
        for (String str : strArr) {
            if (context.checkSelfPermission(str) != 0) {
                w.c("qr share permission not grant : " + str);
                return false;
            }
        }
        return true;
    }

    protected void G(Configuration configuration) {
    }

    protected void J() {
        TextView textView;
        int i;
        this.i.setVisibility(0);
        this.h.setVisibility(8);
        if (this.t) {
            textView = this.n;
            i = R.string.show_hotpot_info;
        } else {
            textView = this.n;
            i = R.string.refresh;
        }
        textView.setText(i);
    }

    protected void L(String str, String str2) {
        w.c("show wifi info : " + str + " | " + str2);
        View view = this.j;
        if (view == null || this.o == null || this.l == null || this.m == null) {
            w.c("showWifiInfo fail , view is null");
            return;
        }
        view.setVisibility(0);
        this.o.setVisibility(8);
        this.l.setText(str);
        this.m.setText(str2);
    }

    protected boolean M() {
        BYDAutoSettingDevice bYDAutoSettingDevice;
        String strD;
        String strC = null;
        try {
            bYDAutoSettingDevice = BYDAutoSettingDevice.getInstance(getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
            w.c("QrCodeShareActivity BYDAutoSettingDevice init exception : " + e.getMessage());
            bYDAutoSettingDevice = null;
        }
        if (bYDAutoSettingDevice != null) {
            strC = s.c(bYDAutoSettingDevice);
            strD = s.d(bYDAutoSettingDevice);
        } else {
            strD = null;
        }
        if (strC == null || strD == null) {
            J();
            return false;
        }
        L(strC, strD);
        return true;
    }

    @Override // com.byd.auto_photo.BaseDialogSupActivity
    protected int e() {
        return getResources().getDimensionPixelSize(R.dimen.qr_code_dialog_height);
    }

    @Override // com.byd.auto_photo.BaseDialogSupActivity
    protected int f() {
        return z(getResources().getConfiguration());
    }

    @Override // com.byd.auto_photo.BaseDialogSupActivity
    protected void h() {
        setContentView(A());
        this.s = getResources().getConfiguration().orientation;
        this.h = findViewById(R.id.container_qr_code);
        this.k = (ImageView) findViewById(R.id.iv_qr_code);
        this.l = (TextView) findViewById(R.id.tv_wifi_name);
        this.m = (TextView) findViewById(R.id.tv_wifi_psw);
        this.i = findViewById(R.id.container_wifi_disconnect);
        this.j = findViewById(R.id.container_wifi_info);
        this.r = (ProgressBar) findViewById(R.id.progress_check_net);
        TextView textView = (TextView) findViewById(R.id.btn_refresh);
        this.n = textView;
        textView.setOnClickListener(this);
        TextView textView2 = (TextView) findViewById(R.id.btn_to_hotpot);
        this.o = textView2;
        textView2.setOnClickListener(this);
        TextView textView3 = (TextView) findViewById(R.id.tv_dialog_title);
        this.v = textView3;
        textView3.setText(B());
        ImageView imageView = (ImageView) findViewById(R.id.iv_dialog_title_close);
        this.w = imageView;
        imageView.setOnClickListener(this);
        x();
    }

    @Override // com.byd.auto_photo.BaseDialogSupActivity
    protected void k(Configuration configuration) {
        boolean z = this.s != configuration.orientation;
        if (!n(configuration)) {
            h();
        }
        if (z) {
            this.s = configuration.orientation;
            w.c("QrCodeShareActivity onOrientationChanged");
            getWindow().setLayout(z(configuration), e());
            G(configuration);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id != R.id.btn_refresh) {
            if (id == R.id.btn_to_hotpot) {
                N();
                return;
            } else {
                if (id != R.id.iv_dialog_title_close) {
                    return;
                }
                finish();
                return;
            }
        }
        if (this.t) {
            N();
            return;
        }
        if (this.r.getVisibility() != 0) {
            this.r.setVisibility(0);
        }
        this.p.removeMessages(1);
        this.p.sendEmptyMessageDelayed(1, 1500L);
    }

    @Override // com.byd.auto_photo.BaseDialogSupActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        if (!y()) {
            super.onCreate(bundle);
            w.c("qr code share -> data is empty!");
            finish();
            return;
        }
        if (this.x != 0) {
            getWindow().getDecorView().setSystemUiVisibility(this.x);
        }
        this.p = new c(this);
        boolean zW = w();
        this.t = zW;
        if (zW) {
            this.u = new b();
            IntentFilter intentFilter = new IntentFilter("android.net.wifi.WIFI_AP_STATE_CHANGED");
            intentFilter.addAction("android.net.wifi.BYD_WIFI_AP_STATE_CHANGED");
            registerReceiver(this.u, intentFilter);
        }
        String[] strArrI = I();
        this.y = D(this, strArrI);
        super.onCreate(bundle);
        if (this.y) {
            return;
        }
        requestPermissions(strArrI, 1);
    }

    @Override // com.byd.auto_photo.BaseDialogSupActivity, android.app.Activity
    protected void onDestroy() {
        this.q = null;
        super.onDestroy();
        c cVar = this.p;
        if (cVar != null) {
            cVar.removeCallbacksAndMessages(null);
            this.p.release();
            this.p = null;
        }
        b bVar = this.u;
        if (bVar != null) {
            unregisterReceiver(bVar);
            this.u = null;
        }
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (strArr == null || strArr.length == 0 || iArr == null || iArr.length == 0) {
            w.c("qr share -> onRequestPermissionsResult : no grant results");
            return;
        }
        if (E(iArr)) {
            this.y = true;
            x();
        } else {
            w.c("qr share -> permission not grant");
            w.e(this, R.string.permission_denied_and_finish);
            finish();
        }
    }

    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        w.c("QrCodeShareActivity onResume : " + this.z);
        if (this.z) {
            this.z = false;
            x();
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (this.x == 0 || !z) {
            return;
        }
        getWindow().getDecorView().setSystemUiVisibility(this.x);
    }

    protected boolean w() {
        int iP = com.byd.share.a.s(this).p();
        return iP == 1 || iP == 0;
    }
}
