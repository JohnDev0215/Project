package com.byd.mvp.mvp;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import com.byd.mvp.common.BydLayoutDirection;
import com.byd.mvp.common.CarModel$Brand;
import com.byd.mvp.utils.AppConfig;
import com.bydux.api.common.views.window.BydActivity;
import java.lang.ref.WeakReference;
import m4.e;
import z3.f;
import z3.g;

/* JADX INFO: loaded from: classes2.dex */
public abstract class XActivity<P extends f> extends BydActivity implements g<P>, View.OnClickListener {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public P f2802d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Activity f2803e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public b f2805g;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public volatile BydLayoutDirection f2811m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public volatile BydLayoutDirection f2812n;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f2804f = getClass().getSimpleName();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f2806h = false;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public volatile int f2807i = 0;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public volatile State f2808j = State.INIT;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public volatile int f2809k = -1;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public volatile int f2810l = -1;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public CarModel$Brand f2813o = CarModel$Brand.DYNASTY;

    public enum State {
        INIT(0),
        CREATE(1),
        START(2),
        RESUME(3),
        PAUSE(4),
        STOP(5),
        DESTROY(6);

        public int value;

        State(int i8) {
            this.value = i8;
        }
    }

    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f2814a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final /* synthetic */ int[] f2815b;

        static {
            int[] iArr = new int[CarModel$Brand.values().length];
            f2815b = iArr;
            try {
                iArr[CarModel$Brand.DYNASTY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2815b[CarModel$Brand.DENZA.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[BydLayoutDirection.values().length];
            f2814a = iArr2;
            try {
                iArr2[BydLayoutDirection.LTR_Rudder_L.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f2814a[BydLayoutDirection.LTR_Rudder_R.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f2814a[BydLayoutDirection.RTL_Rudder_L.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f2814a[BydLayoutDirection.RTL_Rudder_R.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public static class b extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public WeakReference<XActivity> f2816a;

        public b(XActivity xActivity) {
            this.f2816a = new WeakReference<>(xActivity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            WeakReference<XActivity> weakReference = this.f2816a;
            if (weakReference == null || weakReference.get() == null || 33 != message.what) {
                return;
            }
            this.f2816a.get().i(message);
        }
    }

    public void a() {
        Window window = getWindow();
        window.addFlags(Integer.MIN_VALUE);
        window.clearFlags(67108864);
        window.setStatusBarColor(0);
        window.getDecorView().setSystemUiVisibility(1792);
    }

    public abstract void b(Context context);

    public abstract void c(Context context);

    public void d(CarModel$Brand carModel$Brand) {
        x3.a.c("XActivity", "---loadUIStyleOnCarBrand----brand: " + carModel$Brand + ", " + getClass().getName());
        if (carModel$Brand == null) {
            return;
        }
        this.f2813o = carModel$Brand;
        boolean z7 = this.f2810l == 2;
        int i8 = a.f2815b[carModel$Brand.ordinal()];
        if (i8 == 1) {
            f(z7);
        } else {
            if (i8 != 2) {
                return;
            }
            e(z7);
        }
    }

    public void e(boolean z7) {
    }

    public void f(boolean z7) {
    }

    public boolean g() {
        return true;
    }

    @Override // z3.g
    public P getRefP() {
        return this.f2802d;
    }

    public void h(BydLayoutDirection bydLayoutDirection, boolean z7, boolean z8, boolean z9) {
        x3.a.c("XActivity", "---onBydLayoutDirectionChanged----direction: " + bydLayoutDirection + ", rtl: " + z7 + ", land: " + z8 + ", rightRudder: " + z9);
    }

    public void i(Message message) {
    }

    public void j(BydLayoutDirection bydLayoutDirection, boolean z7, boolean z8, boolean z9) {
        x3.a.c("XActivity", "---onBydLayoutDirectionChanged----direction: " + bydLayoutDirection + ", rtl: " + z7 + ", land: " + z8 + ", rightRudder: " + z9);
        int i8 = a.f2814a[bydLayoutDirection.ordinal()];
        if (i8 == 1) {
            k(z8);
            return;
        }
        if (i8 == 2) {
            l(z8);
        } else if (i8 == 3) {
            m(z8);
        } else {
            if (i8 != 4) {
                return;
            }
            n(z8);
        }
    }

    public void k(boolean z7) {
    }

    public void l(boolean z7) {
    }

    public void m(boolean z7) {
    }

    public void n(boolean z7) {
        x3.a.b("XActivity", "---onRtlRudderR---Unsupport this situation, and do onRtlRudderL.");
        m(z7);
    }

    public void o(Configuration configuration) {
        if (e.i()) {
            return;
        }
        int layoutDirection = configuration.getLayoutDirection();
        int i8 = configuration.orientation;
        if (this.f2809k == layoutDirection && this.f2810l == i8) {
            return;
        }
        this.f2809k = layoutDirection;
        this.f2810l = i8;
        boolean z7 = layoutDirection == 1;
        boolean z8 = i8 == 2;
        boolean zC = AppConfig.c();
        BydLayoutDirection bydLayoutDirection = z7 ? zC ? BydLayoutDirection.RTL_Rudder_R : BydLayoutDirection.RTL_Rudder_L : zC ? BydLayoutDirection.LTR_Rudder_R : BydLayoutDirection.LTR_Rudder_L;
        x3.a.c("XActivity", "---setLayoutDirection----direction: " + bydLayoutDirection + ", " + getClass().getName());
        this.f2811m = bydLayoutDirection;
        j(bydLayoutDirection, z7, z8, AppConfig.c());
        if (bydLayoutDirection == this.f2812n) {
            return;
        }
        this.f2812n = bydLayoutDirection;
        h(bydLayoutDirection, z7, z8, AppConfig.c());
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        x3.a.a("XActivity", "---onConfigurationChanged----getLayoutDirection: " + configuration.getLayoutDirection());
        o(configuration);
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        this.f2808j = State.CREATE;
        x3.a.a("XActivity", "---onCreate---" + toString());
        this.f2805g = new b(this);
        this.f2813o = AppConfig.a();
        m4.f.f(this);
        super.onCreate(bundle);
        if (!g()) {
            finish();
            return;
        }
        v3.a.a(getClass().getName(), this);
        a();
        this.f2803e = this;
        if (getLayoutId() > 0) {
            setContentView(getLayoutId());
            bindEvent();
        }
        c(this.f2803e);
        this.f2807i = a4.a.a(getClass().getName());
        b(this.f2803e);
        o(this.f2803e.getResources().getConfiguration());
        d(AppConfig.a());
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        this.f2808j = State.DESTROY;
        x3.a.a("XActivity", "onDestroy", "Activity = " + toString());
        v3.a.b(getClass().getName());
        super.onDestroy();
        P p7 = this.f2802d;
        if (p7 != null) {
            p7.unbindV();
        }
        this.f2802d = null;
        b bVar = this.f2805g;
        if (bVar != null) {
            bVar.removeCallbacksAndMessages(null);
        }
        a4.b.a();
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        this.f2808j = State.PAUSE;
        super.onPause();
    }

    @Override // android.app.Activity
    public void onRestart() {
        super.onRestart();
    }

    @Override // android.app.Activity
    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        x3.a.a("XActivity", "---onRestoreInstanceState---" + getClass().getName());
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.f2808j = State.RESUME;
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        x3.a.a("XActivity", "---onSaveInstanceState---" + getClass().getName());
        this.f2806h = true;
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        this.f2808j = State.START;
        super.onStart();
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        this.f2808j = State.STOP;
        super.onStop();
    }

    @Override // z3.g
    public void setRefP(P p7) {
        this.f2802d = p7;
    }
}
