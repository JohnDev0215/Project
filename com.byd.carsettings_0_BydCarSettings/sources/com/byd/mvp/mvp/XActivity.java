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
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import b.b.m.e.g;
import b.b.m.e.h;
import com.byd.mvp.R$id;
import com.byd.mvp.R$string;
import com.byd.mvp.common.BydLayoutDirection;
import com.byd.mvp.common.CarModel$Brand;
import com.byd.mvp.utils.AppConfig;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.views.window.BydActivity;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes2.dex */
public abstract class XActivity<P extends g> extends BydActivity implements h<P>, View.OnClickListener, b.b.m.a.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public P f3703a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Activity f3704b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public c f3706d;
    public volatile BydLayoutDirection j;
    public volatile BydLayoutDirection k;
    public View m;
    public View n;
    public TextView o;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f3705c = getClass().getSimpleName();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f3707e = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public volatile int f3708f = 0;
    public volatile State g = State.INIT;
    public volatile int h = -1;
    public volatile int i = -1;
    public CarModel$Brand l = CarModel$Brand.DYNASTY;

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

    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ EditText f3710a;

        public a(EditText editText) {
            this.f3710a = editText;
        }

        @Override // java.lang.Runnable
        public void run() {
            EditText editText = this.f3710a;
            if (editText != null) {
                b.b.m.g.g.a(editText);
            }
        }
    }

    public static /* synthetic */ class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f3712a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final /* synthetic */ int[] f3713b;

        static {
            int[] iArr = new int[CarModel$Brand.values().length];
            f3713b = iArr;
            try {
                iArr[CarModel$Brand.DYNASTY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3713b[CarModel$Brand.DENZA.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[BydLayoutDirection.values().length];
            f3712a = iArr2;
            try {
                iArr2[BydLayoutDirection.LTR_Rudder_L.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f3712a[BydLayoutDirection.LTR_Rudder_R.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f3712a[BydLayoutDirection.RTL_Rudder_L.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f3712a[BydLayoutDirection.RTL_Rudder_R.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public static class c extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public WeakReference<XActivity> f3714a;

        public c(XActivity xActivity) {
            this.f3714a = new WeakReference<>(xActivity);
        }

        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            super.handleMessage(message);
            WeakReference<XActivity> weakReference = this.f3714a;
            if (weakReference == null || weakReference.get() == null || 33 != message.what) {
                return;
            }
            this.f3714a.get().w(message);
        }
    }

    public void A() {
        this.m.setVisibility(0);
        this.n.setVisibility(0);
        this.o.setText(getString(R$string.status_in_loading));
    }

    public void B(boolean z) {
        BydLogHelper.logV(this.f3705c + "----onLtrRudderL");
    }

    public void C(boolean z) {
        BydLogHelper.logV(this.f3705c + "----onLtrRudderR");
    }

    public void D(boolean z) {
        BydLogHelper.logV(this.f3705c + "----onRtlRudderL");
    }

    public void E(Runnable runnable, long j) {
        c cVar = this.f3706d;
        if (cVar != null) {
            cVar.postDelayed(runnable, j);
        }
    }

    public void F(int i, int i2, Object obj) {
        Message messageObtain = Message.obtain();
        messageObtain.what = i;
        messageObtain.arg1 = i2;
        messageObtain.obj = obj;
        c cVar = this.f3706d;
        if (cVar != null) {
            cVar.sendMessage(messageObtain);
        }
    }

    public void G(int i) {
        this.f3708f = i;
        b.b.m.e.i.a.k(getClass().getName(), i);
    }

    public void H(Configuration configuration) {
        if (b.b.m.g.g.e()) {
            return;
        }
        int layoutDirection = configuration.getLayoutDirection();
        int i = configuration.orientation;
        if (this.h == layoutDirection && this.i == i) {
            return;
        }
        this.h = layoutDirection;
        this.i = i;
        boolean z = layoutDirection == 1;
        boolean z2 = i == 2;
        boolean zD = AppConfig.d();
        BydLayoutDirection bydLayoutDirection = z ? zD ? BydLayoutDirection.RTL_Rudder_R : BydLayoutDirection.RTL_Rudder_L : zD ? BydLayoutDirection.LTR_Rudder_R : BydLayoutDirection.LTR_Rudder_L;
        b.b.m.c.a.d("XActivity", "---setLayoutDirection----direction: " + bydLayoutDirection + ", " + getClass().getName());
        this.j = bydLayoutDirection;
        x(bydLayoutDirection, z, z2, AppConfig.d());
        if (bydLayoutDirection == this.k) {
            return;
        }
        this.k = bydLayoutDirection;
        v(bydLayoutDirection, z, z2, AppConfig.d());
    }

    public void I(View view, boolean z) {
        if (view == null) {
            return;
        }
        view.setLayoutDirection(z ? 1 : 0);
    }

    public void b(EditText editText) {
        E(new a(editText), 50L);
    }

    public void e() {
        Window window = getWindow();
        window.addFlags(Integer.MIN_VALUE);
        window.clearFlags(67108864);
        window.setStatusBarColor(0);
        window.getDecorView().setSystemUiVisibility(1792);
    }

    public int f() {
        return 0;
    }

    @Override // b.b.m.e.h
    public P getRefP() {
        return this.f3703a;
    }

    public abstract void k(Context context);

    public void l(View view, int i) {
        if (view == null) {
            return;
        }
        View viewFindViewById = view.findViewById(i);
        this.m = viewFindViewById;
        this.n = viewFindViewById.findViewById(R$id.pgb_status_in_loading);
        TextView textView = (TextView) this.m.findViewById(R$id.tv_status_in_loading);
        this.o = textView;
        textView.setOnClickListener(this);
    }

    public abstract void n(Context context);

    public void onClick(View view) {
        if (view.getId() == R$id.tv_status_in_loading) {
            z();
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(@NonNull Configuration configuration) {
        super.onConfigurationChanged(configuration);
        b.b.m.c.a.a("XActivity", "---onConfigurationChanged----getLayoutDirection: " + configuration.getLayoutDirection());
        H(configuration);
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        this.g = State.CREATE;
        b.b.m.c.a.a("XActivity", "---onCreate---" + toString());
        this.f3706d = new c(this);
        this.l = AppConfig.a();
        b.b.m.g.h.i(this);
        super.onCreate(bundle);
        if (!u()) {
            finish();
            return;
        }
        b.b.m.a.a.e(getClass().getName(), this);
        e();
        this.f3704b = this;
        if (getLayoutId() > 0) {
            setContentView(getLayoutId());
            bindEvent();
        }
        n(this.f3704b);
        this.f3708f = b.b.m.e.i.a.d(getClass().getName());
        k(this.f3704b);
        H(this.f3704b.getResources().getConfiguration());
        q(AppConfig.a());
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        if (f() > 0) {
            getMenuInflater().inflate(f(), menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        this.g = State.DESTROY;
        b.b.m.c.a.a("XActivity", "onDestroy", "Activity = " + toString());
        b.b.m.a.a.f(getClass().getName());
        super.onDestroy();
        P p = this.f3703a;
        if (p != null) {
            p.unbindV();
        }
        this.f3703a = null;
        c cVar = this.f3706d;
        if (cVar != null) {
            cVar.removeCallbacksAndMessages(null);
        }
        b.b.m.e.i.b.a();
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        this.g = State.PAUSE;
        super.onPause();
    }

    @Override // android.app.Activity
    public void onRestart() {
        super.onRestart();
    }

    @Override // android.app.Activity
    public void onRestoreInstanceState(@NonNull Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        b.b.m.c.a.a("XActivity", "---onRestoreInstanceState---" + getClass().getName());
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.g = State.RESUME;
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        b.b.m.c.a.a("XActivity", "---onSaveInstanceState---" + getClass().getName());
        this.f3707e = true;
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        this.g = State.START;
        super.onStart();
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        this.g = State.STOP;
        super.onStop();
    }

    public boolean p() {
        b.b.m.c.a.a("XActivity", "---isOnForeground----mState: " + this.g);
        return this.g.value >= State.RESUME.value && this.g.value <= State.PAUSE.value;
    }

    public void q(CarModel$Brand carModel$Brand) {
        b.b.m.c.a.d("XActivity", "---loadUIStyleOnCarBrand----brand: " + carModel$Brand + ", " + getClass().getName());
        if (carModel$Brand == null) {
            return;
        }
        this.l = carModel$Brand;
        boolean z = this.i == 2;
        int i = b.f3713b[carModel$Brand.ordinal()];
        if (i == 1) {
            t(z);
        } else {
            if (i != 2) {
                return;
            }
            r(z);
        }
    }

    public void r(boolean z) {
        BydLogHelper.logV(this.f3705c + "----loadUIStyleOnDenzaBrand");
    }

    @Override // b.b.m.e.h
    public void setRefP(P p) {
        this.f3703a = p;
    }

    public void t(boolean z) {
        BydLogHelper.logV(this.f3705c + "----loadUIStyleOnDynastyBrand");
    }

    public boolean u() {
        return true;
    }

    public void v(BydLayoutDirection bydLayoutDirection, boolean z, boolean z2, boolean z3) {
        b.b.m.c.a.d("XActivity", "---onBydLayoutDirectionChanged----direction: " + bydLayoutDirection + ", rtl: " + z + ", land: " + z2 + ", rightRudder: " + z3);
    }

    public void w(Message message) {
        BydLogHelper.logV(this.f3705c + "----onHandlerUpdateUI");
    }

    public void x(BydLayoutDirection bydLayoutDirection, boolean z, boolean z2, boolean z3) {
        b.b.m.c.a.d("XActivity", "---onBydLayoutDirectionChanged----direction: " + bydLayoutDirection + ", rtl: " + z + ", land: " + z2 + ", rightRudder: " + z3);
        int i = b.f3712a[bydLayoutDirection.ordinal()];
        if (i == 1) {
            B(z2);
        } else if (i == 2) {
            C(z2);
        } else {
            if (i != 3) {
                return;
            }
            D(z2);
        }
    }

    public void y() {
        this.m.setVisibility(8);
    }

    public void z() {
        BydLogHelper.logV(this.f3705c + "----onLoadStatusClicked");
    }
}
