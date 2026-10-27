package com.byd.avm;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener;
import android.hardware.bydauto.gearbox.BYDAutoGearboxDevice;
import android.hardware.input.InputManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.tools.window.BnWindowHelper;
import com.ts.avm.sdk.AvmManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public class MainActivity extends Activity {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static AtomicBoolean f2119b = new AtomicBoolean(false);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private transient AvmManager f2120c;
    private transient CountDownTimer h;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private transient BYDAutoGearboxDevice f2121d = null;
    private transient int e = -1;
    private transient Bundle f = null;
    private transient boolean g = false;
    private transient com.ts.avm.sdk.a i = new a();
    private transient AvmManager.a j = new b();
    private transient AvmManager.b k = new c();
    private final transient AbsBYDAutoGearboxListener l = new d();

    class a implements com.ts.avm.sdk.a {
        a() {
        }

        @Override // com.ts.avm.sdk.a
        public void a() {
            Log.w("AVMActivity", "onServiceConnected: mCurrentAvmState = " + MainActivity.this.e + ", bundle = " + MainActivity.this.f);
            if (MainActivity.this.e == 5 || MainActivity.this.e == 4) {
                return;
            }
            if (MainActivity.this.f != null) {
                MainActivity mainActivity = MainActivity.this;
                mainActivity.j(mainActivity.f);
                return;
            }
            int iG = MainActivity.this.f2120c.g();
            MainActivity.this.n();
            if (iG < 0) {
                Log.i("AVMActivity", "show: failed");
                Toast.makeText(MainActivity.this, "Avm show fail", 0).show();
                MainActivity.this.k();
            }
        }

        @Override // com.ts.avm.sdk.a
        public void b() {
            MainActivity.this.e = -1;
            Log.w("AVMActivity", "onServiceDisConnected: ");
        }

        @Override // com.ts.avm.sdk.a
        public void onError(int i, String str) {
            Log.w("AVMActivity", "onError: " + str);
        }
    }

    class b implements AvmManager.a {
        b() {
        }

        @Override // com.ts.avm.sdk.AvmManager.a
        public void a(int i) {
            Log.w("AVMActivity", "onAvmDockerStatusChanged all finish = " + i);
            if (!com.byd.avm.a.a(MainActivity.this) || !com.byd.avm.a.b(MainActivity.this)) {
                Log.w("AVMActivity", "handlerAvm guide isn't complete");
                return;
            }
            if (i == 1) {
                MainActivity.this.k();
                return;
            }
            if (i == 2) {
                MainActivity.this.k();
                long jUptimeMillis = SystemClock.uptimeMillis();
                MainActivity.this.m(3, 0, 0, jUptimeMillis, jUptimeMillis);
                MainActivity.this.m(3, 1, 0, jUptimeMillis, jUptimeMillis);
                return;
            }
            if (i == 3) {
                MainActivity mainActivity = MainActivity.this;
                mainActivity.o(mainActivity, "com.byd.carsettings");
                MainActivity.this.k();
            } else if (i == 4) {
                MainActivity mainActivity2 = MainActivity.this;
                mainActivity2.o(mainActivity2, "com.byd.hvac");
                MainActivity.this.k();
            } else {
                if (i != 5) {
                    return;
                }
                MainActivity.this.g = true;
                MainActivity.this.k();
            }
        }
    }

    class c implements AvmManager.b {
        c() {
        }

        @Override // com.ts.avm.sdk.AvmManager.b
        public void a(int i, String str) {
            Log.w("AVMActivity", "onStatusChanged: " + i + "; message " + str);
            MainActivity.this.e = i;
            if (i == 3) {
                MainActivity.this.k();
            }
            if (i == 5) {
                MainActivity.this.k();
            }
            if (i == 4) {
                MainActivity.this.k();
            }
        }
    }

    class d extends AbsBYDAutoGearboxListener {
        d() {
        }

        @Override // android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener
        public void onGearChanged(int i) {
            super.onGearChanged(i);
            if (i == 2) {
                BydLogHelper.logD("AbsBYDAutoGearboxListener", "onGearChanged GEAR_R");
                MainActivity.this.k();
            }
        }
    }

    class e extends CountDownTimer {
        e(long j, long j2) {
            super(j, j2);
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            Log.i("AVMActivity", "countDownTimer onFinish mAvmManager : " + MainActivity.this.f2120c);
            if (MainActivity.this.f2120c != null) {
                int iB = MainActivity.this.f2120c.b();
                Log.i("AVMActivity", "countDownTimer state : " + iB);
                if (2 != iB) {
                    MainActivity.this.k();
                }
            }
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j(Bundle bundle) {
        Log.w("AVMActivity", "AVMActivity dealControl bundle= " + bundle);
        if (bundle == null) {
            Log.w("AVMActivity", "dealControl bundle is null");
            k();
            return;
        }
        int i = bundle.getInt("bydavmmode");
        Log.w("AVMActivity", "AVMActivity mode = " + i + ", mAvmManager = " + this.f2120c);
        if (i == 5) {
            AvmManager avmManager = this.f2120c;
            if (avmManager != null) {
                avmManager.j(AvmManager.VoiceEvent.START_AVM);
            }
            n();
            return;
        }
        if (i == 6) {
            AvmManager avmManager2 = this.f2120c;
            if (avmManager2 != null) {
                avmManager2.j(AvmManager.VoiceEvent.STOP_AVM);
            }
            k();
            return;
        }
        AvmManager avmManager3 = this.f2120c;
        if (avmManager3 != null) {
            int iB = avmManager3.b();
            Log.w("AVMActivity", "dealControl default state : " + iB);
            if (2 != iB) {
                k();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        Log.w("AVMActivity", "finishActivity mAvmManager = " + this.f2120c + ", exists : " + f2119b.get());
        if (f2119b.compareAndSet(false, true)) {
            AvmManager avmManager = this.f2120c;
            if (avmManager != null) {
                avmManager.i(this.k);
                this.f2120c.h(this.j);
                this.f2120c.a(this.i);
                this.f2120c = null;
            }
            f2119b.set(false);
        }
        finish();
    }

    private void l() {
        AvmManager avmManagerC = AvmManager.c();
        this.f2120c = avmManagerC;
        avmManagerC.d(this, this.i);
        this.f2120c.f(this.k);
        this.f2120c.e(this.j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        if (this.h == null) {
            this.h = new e(1000L, 1000L);
        }
        this.h.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o(Context context, String str) {
        try {
            Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(str);
            if (launchIntentForPackage != null) {
                launchIntentForPackage.setFlags(270532608);
                context.startActivity(launchIntentForPackage);
            } else {
                BydLogHelper.logD("AppInfoUtil", str + ":intent --> null");
            }
        } catch (Exception e2) {
            BydLogHelper.logW("AppInfoUtil", str + " :" + e2);
        }
    }

    public void m(int i, int i2, int i3, long j, long j2) {
        InputManager.getInstance().injectInputEvent(new KeyEvent(j, j2, i2, i, (i3 & 128) != 0 ? 1 : 0, 0, -1, 0, i3 | 8 | 64, 257), 0);
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        boolean zIsUserAMonkey = ActivityManager.isUserAMonkey();
        this.f2121d = BYDAutoGearboxDevice.getInstance(this);
        Log.w("AVMActivity", "AVMActivity onCreate");
        if (zIsUserAMonkey) {
            Log.w("AVMActivity", "AVMActivity onCreate isMonkey = " + zIsUserAMonkey);
            finish();
            return;
        }
        Intent intent = getIntent();
        if (intent != null) {
            this.f = intent.getBundleExtra(BnWindowHelper.WINDOW_KEY_PARAM);
        }
        getWindow().getDecorView().setSystemUiVisibility(2050);
        Log.w("AVMActivity", "AVMActivity onCreate initAvmManager start");
        l();
        Log.w("AVMActivity", "AVMActivity onCreate isMonkey end");
        getWindow().setBackgroundDrawable(null);
        this.f2121d.registerListener(this.l);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        BYDAutoGearboxDevice bYDAutoGearboxDevice = this.f2121d;
        if (bYDAutoGearboxDevice != null) {
            bYDAutoGearboxDevice.unregisterListener(this.l);
        }
        Log.i("AVMActivity", "onDestroy mAllAppClick = " + this.g);
        if (this.g) {
            Intent intent = new Intent();
            intent.setAction("com.byd.action.card.more.app");
            sendBroadcast(intent);
            this.g = false;
        }
        CountDownTimer countDownTimer = this.h;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.w("AVMActivity", "onNewIntent intent = " + intent);
        if (intent != null) {
            this.f = intent.getBundleExtra(BnWindowHelper.WINDOW_KEY_PARAM);
        }
        j(this.f);
    }
}
