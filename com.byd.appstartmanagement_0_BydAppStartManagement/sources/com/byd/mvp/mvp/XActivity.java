package com.byd.mvp.mvp;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import b.a.d.d.e;
import b.a.d.d.f;
import b.a.d.f.d;
import com.byd.mvp.R$id;
import com.byd.mvp.common.BydLayoutDirection;
import com.byd.mvp.common.CarModel$Brand;
import com.byd.mvp.utils.AppConfig;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.views.window.BydActivity;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes2.dex */
public abstract class XActivity<P extends e> extends BydActivity implements f<P>, View.OnClickListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public P f649b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Activity f650c;
    public b e;
    public volatile BydLayoutDirection k;
    public volatile BydLayoutDirection l;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f651d = getClass().getSimpleName();
    public boolean f = false;
    public volatile int g = 0;
    public volatile State h = State.INIT;
    public volatile int i = -1;
    public volatile int j = -1;
    public CarModel$Brand m = CarModel$Brand.DYNASTY;

    public enum State {
        INIT(0),
        CREATE(1),
        START(2),
        RESUME(3),
        PAUSE(4),
        STOP(5),
        DESTROY(6);

        public int value;

        State(int i) {
            this.value = i;
        }
    }

    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f653a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final /* synthetic */ int[] f654b;

        static {
            int[] iArr = new int[CarModel$Brand.values().length];
            f654b = iArr;
            try {
                iArr[CarModel$Brand.DYNASTY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f654b[CarModel$Brand.DENZA.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[BydLayoutDirection.values().length];
            f653a = iArr2;
            try {
                iArr2[BydLayoutDirection.LTR_Rudder_L.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f653a[BydLayoutDirection.LTR_Rudder_R.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f653a[BydLayoutDirection.RTL_Rudder_L.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f653a[BydLayoutDirection.RTL_Rudder_R.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public static class b extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public WeakReference<XActivity> f655a;

        public b(XActivity xActivity) {
            this.f655a = new WeakReference<>(xActivity);
        }

        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            super.handleMessage(message);
            WeakReference<XActivity> weakReference = this.f655a;
            if (weakReference == null || weakReference.get() == null || 33 != message.what) {
                return;
            }
            this.f655a.get().p(message);
        }
    }

    @Override // b.a.d.d.f
    public void a(P p) {
        this.f649b = p;
    }

    @Override // b.a.d.d.f
    public P b() {
        return this.f649b;
    }

    public void g() {
        Window window = getWindow();
        window.addFlags(Integer.MIN_VALUE);
        window.clearFlags(67108864);
        window.setStatusBarColor(0);
        window.getDecorView().setSystemUiVisibility(1792);
    }

    public int h() {
        return 0;
    }

    public abstract void i(Context context);

    public abstract void j(Context context);

    public void k(CarModel$Brand carModel$Brand) {
        b.a.d.b.a.d("XActivity", "---loadUIStyleOnCarBrand----brand: " + carModel$Brand + ", " + getClass().getName());
        if (carModel$Brand == null) {
            return;
        }
        this.m = carModel$Brand;
        boolean z = this.j == 2;
        int i = a.f654b[carModel$Brand.ordinal()];
        if (i == 1) {
            m(z);
        } else {
            if (i != 2) {
                return;
            }
            l(z);
        }
    }

    public void l(boolean z) {
        BydLogHelper.logV(this.f651d + "----loadUIStyleOnDenzaBrand");
    }

    public void m(boolean z) {
        BydLogHelper.logV(this.f651d + "----loadUIStyleOnDynastyBrand");
    }

    public boolean n() {
        return true;
    }

    public void o(BydLayoutDirection bydLayoutDirection, boolean z, boolean z2, boolean z3) {
        b.a.d.b.a.d("XActivity", "---onBydLayoutDirectionChanged----direction: " + bydLayoutDirection + ", rtl: " + z + ", land: " + z2 + ", rightRudder: " + z3);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R$id.tv_status_in_loading) {
            r();
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(@NonNull Configuration configuration) {
        super.onConfigurationChanged(configuration);
        b.a.d.b.a.a("XActivity", "---onConfigurationChanged----getLayoutDirection: " + configuration.getLayoutDirection());
        v(configuration);
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        this.h = State.CREATE;
        b.a.d.b.a.a("XActivity", "---onCreate---" + toString());
        this.e = new b(this);
        this.m = AppConfig.a();
        b.a.d.f.e.e(this);
        super.onCreate(bundle);
        if (!n()) {
            finish();
            return;
        }
        b.a.d.a.a.a(getClass().getName(), this);
        g();
        this.f650c = this;
        if (c() > 0) {
            setContentView(c());
            d();
        }
        j(this.f650c);
        this.g = b.a.d.d.g.a.a(getClass().getName());
        i(this.f650c);
        v(this.f650c.getResources().getConfiguration());
        k(AppConfig.a());
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        if (h() > 0) {
            getMenuInflater().inflate(h(), menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        this.h = State.DESTROY;
        b.a.d.b.a.a("XActivity", "onDestroy", "Activity = " + toString());
        b.a.d.a.a.b(getClass().getName());
        super.onDestroy();
        P p = this.f649b;
        if (p != null) {
            p.c();
        }
        this.f649b = null;
        b bVar = this.e;
        if (bVar != null) {
            bVar.removeCallbacksAndMessages(null);
        }
        b.a.d.d.g.b.a();
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        this.h = State.PAUSE;
        super.onPause();
    }

    @Override // android.app.Activity
    public void onRestart() {
        super.onRestart();
    }

    @Override // android.app.Activity
    public void onRestoreInstanceState(@NonNull Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        b.a.d.b.a.a("XActivity", "---onRestoreInstanceState---" + getClass().getName());
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.h = State.RESUME;
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        b.a.d.b.a.a("XActivity", "---onSaveInstanceState---" + getClass().getName());
        this.f = true;
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        this.h = State.START;
        super.onStart();
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        this.h = State.STOP;
        super.onStop();
    }

    public void p(Message message) {
        BydLogHelper.logV(this.f651d + "----onHandlerUpdateUI");
    }

    public void q(BydLayoutDirection bydLayoutDirection, boolean z, boolean z2, boolean z3) {
        b.a.d.b.a.d("XActivity", "---onBydLayoutDirectionChanged----direction: " + bydLayoutDirection + ", rtl: " + z + ", land: " + z2 + ", rightRudder: " + z3);
        int i = a.f653a[bydLayoutDirection.ordinal()];
        if (i == 1) {
            s(z2);
        } else if (i == 2) {
            t(z2);
        } else {
            if (i != 3) {
                return;
            }
            u(z2);
        }
    }

    public void r() {
        BydLogHelper.logV(this.f651d + "----onLoadStatusClicked");
    }

    public void s(boolean z) {
        BydLogHelper.logV(this.f651d + "----onLtrRudderL");
    }

    public void t(boolean z) {
        BydLogHelper.logV(this.f651d + "----onLtrRudderR");
    }

    public void u(boolean z) {
        BydLogHelper.logV(this.f651d + "----onRtlRudderL");
    }

    public void v(Configuration configuration) {
        if (d.a()) {
            return;
        }
        int layoutDirection = configuration.getLayoutDirection();
        int i = configuration.orientation;
        if (this.i == layoutDirection && this.j == i) {
            return;
        }
        this.i = layoutDirection;
        this.j = i;
        boolean z = layoutDirection == 1;
        boolean z2 = i == 2;
        boolean zB = AppConfig.b();
        BydLayoutDirection bydLayoutDirection = z ? zB ? BydLayoutDirection.RTL_Rudder_R : BydLayoutDirection.RTL_Rudder_L : zB ? BydLayoutDirection.LTR_Rudder_R : BydLayoutDirection.LTR_Rudder_L;
        b.a.d.b.a.d("XActivity", "---setLayoutDirection----direction: " + bydLayoutDirection + ", " + getClass().getName());
        this.k = bydLayoutDirection;
        q(bydLayoutDirection, z, z2, AppConfig.b());
        if (bydLayoutDirection == this.l) {
            return;
        }
        this.l = bydLayoutDirection;
        o(bydLayoutDirection, z, z2, AppConfig.b());
    }
}
