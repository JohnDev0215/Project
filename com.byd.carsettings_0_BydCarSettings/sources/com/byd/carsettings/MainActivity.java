package com.byd.carsettings;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.IActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import b.b.a.f.h;
import b.b.g.q.b;
import b.b.g.q.d.m;
import b.b.g.r.i;
import b.b.g.r.k;
import b.b.g.r.r;
import b.b.g.r.u;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieCompositionFactory;
import com.airbnb.lottie.LottieListener;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.SimpleColorFilter;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.value.LottieValueCallback;
import com.byd.carsettings.ui.activity.SearchActivity;
import com.byd.carsettings.ui.fragment.CarHealthyFragment;
import com.byd.carsettings.ui.fragment.CarSettingFragment;
import com.byd.carsettings.ui.fragment.DiLinkFragment;
import com.byd.carsettings.ui.fragment.DiPilotFragment;
import com.byd.carsettings.ui.fragment.NewEnergyFragment;
import com.byd.mvp.mvp.XActivity;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.tools.util.PathInterpolatorUtils;
import com.bydux.api.common.views.window.DampingScrollView;
import java.util.Iterator;
import java.util.Set;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes2.dex */
public class MainActivity extends XActivity<b.b.g.n.c> implements b.b.o.b.a.a {
    public static int[] p = new int[4];
    public static boolean q = true;
    public static int r = -1;
    public LottieValueCallback<ColorFilter> E;
    public LottieValueCallback<ColorFilter> F;
    public Fragment H;
    public long I;
    public IActivityManager J;
    public b.b.g.l.a K;
    public ComponentName L;
    public Runnable N;
    public ViewGroup s;
    public ViewGroup t;
    public ViewGroup u;
    public DampingScrollView v;
    public boolean w;
    public final int[] x = {R.id.rl_menu_1, R.id.rl_menu_2, R.id.rl_menu_3, R.id.rl_menu_4, R.id.rl_menu_5};
    public final int[] y = {R.id.iv_menu_bg_1, R.id.iv_menu_bg_2, R.id.iv_menu_bg_3, R.id.iv_menu_bg_4, R.id.iv_menu_bg_5};
    public final int[] z = {R.id.iv_menu_icon_1, R.id.iv_menu_icon_2, R.id.iv_menu_icon_3, R.id.iv_menu_icon_4, R.id.iv_menu_icon_5};
    public final int[] A = {R.id.tv_menu_1, R.id.tv_menu_2, R.id.tv_menu_3, R.id.tv_menu_4, R.id.tv_menu_5};
    public final int[] B = {R.id.iv_menu_msg_1, R.id.iv_menu_msg_2, R.id.iv_menu_msg_3, R.id.iv_menu_msg_4, R.id.iv_menu_msg_5};
    public final int[] D = {R.raw.dilink, R.raw.dipilot, R.raw.new_energy, R.raw.car_setting, R.raw.car_healthy};
    public final int G = 168932;
    public boolean M = true;

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            b.b.g.r.e.m();
            b.b.m.f.d.d.c(a.class.getName());
            b.b.a.h.f.a();
            b.b.m.c.a.a("MainActivity", "onDestroy");
        }
    }

    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (System.currentTimeMillis() - MainActivity.this.I < 250) {
                b.b.m.c.a.a("MainActivity", "---onNewIntent---onCreate和onNewIntent时间间隔小于250ms,正常打开页面");
                return;
            }
            MainActivity mainActivity = MainActivity.this;
            mainActivity.L = mainActivity.K.e0();
            if (MainActivity.this.L == null || "com.byd.carsettings.MainActivity".equals(MainActivity.this.L.getClassName())) {
                MainActivity.this.finish();
            } else {
                b.b.m.c.a.a("MainActivity", "---onNewIntent---Stack栈顶层不是设置主页,正常打开页面");
            }
        }
    }

    public class c implements View.OnTouchListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f2586a;

        public c(int i) {
            this.f2586a = i;
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            int action = motionEvent.getAction();
            if (action == 0) {
                b.b.g.o.f.d(view, 0);
                MainActivity mainActivity = MainActivity.this;
                mainActivity.e0((ImageView) mainActivity.findViewById(mainActivity.y[this.f2586a]));
                MainActivity mainActivity2 = MainActivity.this;
                mainActivity2.findViewById(mainActivity2.y[this.f2586a]).setAlpha(MainActivity.this.f3708f == this.f2586a ? 1.0f : 0.0f);
                MainActivity mainActivity3 = MainActivity.this;
                mainActivity3.findViewById(mainActivity3.y[this.f2586a]).animate().alpha(0.5f).setDuration(100L).setInterpolator(PathInterpolatorUtils.getUniformSpeed()).start();
            } else if (action == 1) {
                MainActivity.this.Z(this.f2586a, 0);
                MainActivity mainActivity4 = MainActivity.this;
                mainActivity4.findViewById(mainActivity4.y[this.f2586a]).animate().alpha(1.0f).setDuration(100L).setInterpolator(PathInterpolatorUtils.getUniformSpeed()).start();
            } else if (action == 3) {
                MainActivity mainActivity5 = MainActivity.this;
                mainActivity5.findViewById(mainActivity5.y[this.f2586a]).animate().alpha(MainActivity.this.f3708f == this.f2586a ? 1.0f : 0.0f).setDuration(100L).setInterpolator(PathInterpolatorUtils.getUniformSpeed()).start();
            }
            return true;
        }
    }

    public class d implements LottieListener<LottieComposition> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f2588a;

        public d(int i) {
            this.f2588a = i;
        }

        @Override // com.airbnb.lottie.LottieListener
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onResult(LottieComposition lottieComposition) {
            MainActivity mainActivity = MainActivity.this;
            ((LottieAnimationView) mainActivity.findViewById(mainActivity.z[this.f2588a])).playAnimation();
        }
    }

    public class e implements Runnable {
        public e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            boolean unused = MainActivity.q = false;
            b.b.g.r.e.g();
        }
    }

    public class f implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ boolean f2591a;

        public f(boolean z) {
            this.f2591a = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            MainActivity mainActivity = MainActivity.this;
            mainActivity.findViewById(mainActivity.B[0]).setVisibility(this.f2591a ? 0 : 8);
        }
    }

    public final boolean V() {
        Intent intent = getIntent();
        if (!"android.intent.action.MAIN".equals(intent.getAction()) || !a0(intent.getCategories())) {
            return false;
        }
        b.b.m.c.a.a("MainActivity", "---actionFromAppCenter---Action: " + intent.getAction());
        return true;
    }

    public final boolean W() {
        Intent intent = getIntent();
        String action = intent.getAction();
        String stringExtra = intent.getStringExtra("from");
        b.b.m.c.a.a("MainActivity", "--actionFromNavibar--action: " + action + ", from: " + stringExtra);
        return "byd.intent.aciton.carsettings".equals(intent.getAction()) && "navi_bar".equals(stringExtra);
    }

    public final boolean X(Intent intent) {
        if (b.b.m.f.d.d.g()) {
            b.b.m.c.a.c("MainActivity", "--checkNavibarAction--hasNoRespondDialog: true.");
            return false;
        }
        if (intent.getBooleanExtra("do_search", false)) {
            return false;
        }
        String stringExtra = intent.getStringExtra("from");
        if (!p() || !"navi_bar".equals(stringExtra)) {
            return false;
        }
        b.b.m.c.a.c("MainActivity", "--checkNavibarAction--Activity on foreground, from: " + stringExtra);
        return true;
    }

    public final void Y() {
        Z(this.f3708f, 0);
    }

    public final void Z(int i, int i2) {
        b0(i);
        BydLogHelper.logD("enterFragment index = " + i + " -- subpage = " + i2);
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransactionBeginTransaction = supportFragmentManager.beginTransaction();
        Fragment fragmentFindFragmentByTag = supportFragmentManager.findFragmentByTag(i + "");
        if (i == 3 && this.M && fragmentFindFragmentByTag != null && fragmentFindFragmentByTag.isAdded()) {
            fragmentTransactionBeginTransaction.remove(fragmentFindFragmentByTag);
            this.M = false;
            fragmentFindFragmentByTag = null;
        }
        if (i == 0 && fragmentFindFragmentByTag == null) {
            fragmentFindFragmentByTag = new DiLinkFragment();
        }
        if (i == 1 && fragmentFindFragmentByTag == null) {
            fragmentFindFragmentByTag = new DiPilotFragment();
        }
        if (i == 2 && fragmentFindFragmentByTag == null) {
            fragmentFindFragmentByTag = new NewEnergyFragment();
        }
        if (i == 3 && fragmentFindFragmentByTag == null) {
            fragmentFindFragmentByTag = new CarSettingFragment();
        }
        if (i == 4 && fragmentFindFragmentByTag == null) {
            fragmentFindFragmentByTag = new CarHealthyFragment();
        }
        if (fragmentFindFragmentByTag == null) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("subpage", i2);
        fragmentFindFragmentByTag.setArguments(bundle);
        StringBuilder sb = new StringBuilder();
        sb.append("mCurrentFragment = ");
        Fragment fragment = this.H;
        sb.append(fragment != null ? fragment.getClass().getSimpleName() : null);
        sb.append(" ---> targetFragment = ");
        sb.append(fragmentFindFragmentByTag.getClass().getSimpleName());
        BydLogHelper.logD(sb.toString());
        if (!fragmentFindFragmentByTag.equals(this.H)) {
            if (this.H != null) {
                if (!q) {
                    fragmentTransactionBeginTransaction.setCustomAnimations(R.anim.anim_menu_enter, R.anim.anim_menu_exit);
                }
                fragmentTransactionBeginTransaction.hide(this.H);
            } else {
                for (int i3 = 0; i3 <= 4; i3++) {
                    if (i3 != i) {
                        Fragment fragmentFindFragmentByTag2 = supportFragmentManager.findFragmentByTag(i3 + "");
                        if (fragmentFindFragmentByTag2 != null) {
                            if (!q) {
                                fragmentTransactionBeginTransaction.setCustomAnimations(R.anim.anim_menu_enter, R.anim.anim_menu_exit);
                            }
                            fragmentTransactionBeginTransaction.hide(fragmentFindFragmentByTag2);
                        }
                    }
                }
            }
            b.b.m.c.a.c("displayed", "isColdStart = " + q);
            if (!q) {
                fragmentTransactionBeginTransaction.setCustomAnimations(R.anim.anim_menu_enter, R.anim.anim_menu_exit);
            }
            this.H = fragmentFindFragmentByTag;
            if (fragmentFindFragmentByTag.isAdded()) {
                fragmentTransactionBeginTransaction.show(fragmentFindFragmentByTag);
            } else {
                fragmentTransactionBeginTransaction.add(R.id.cc_right_layout, fragmentFindFragmentByTag, String.valueOf(i));
            }
            fragmentTransactionBeginTransaction.commitNow();
            EventBus.getDefault().post(new b.b.g.m.a(1, i, i2));
        }
        if (i != this.f3708f) {
            G(i);
        }
    }

    public final boolean a0(Set<String> set) {
        if (set != null && !set.isEmpty()) {
            Iterator<String> it = set.iterator();
            while (it.hasNext()) {
                if ("android.intent.category.LAUNCHER".equals(it.next())) {
                    return true;
                }
            }
        }
        return false;
    }

    public final void b0(int i) {
        for (int i2 = 0; i2 < this.z.length; i2++) {
            ImageView imageView = (ImageView) findViewById(this.y[i2]);
            LottieAnimationView lottieAnimationView = (LottieAnimationView) findViewById(this.z[i2]);
            TextView textView = (TextView) findViewById(this.A[i2]);
            if (i == i2) {
                LottieCompositionFactory.fromRawRes(this, this.D[i2]).addListener(new d(i));
                lottieAnimationView.addValueCallback(new KeyPath("**"), LottieProperty.COLOR_FILTER, this.E);
                if (imageView.getDrawable() == null) {
                    e0(imageView);
                }
                imageView.animate().alpha(1.0f).setDuration(100L).setInterpolator(PathInterpolatorUtils.getUniformSpeed()).start();
                textView.setTextColor(ContextCompat.getColor(this, b.b.a.h.b.a(this, R.attr.text_color_rbtn_blue)));
            } else {
                lottieAnimationView.addValueCallback(new KeyPath("**"), LottieProperty.COLOR_FILTER, this.F);
                if (imageView.getDrawable() != null) {
                    imageView.animate().alpha(0.0f).setDuration(100L).setInterpolator(PathInterpolatorUtils.getUniformSpeed()).start();
                }
                textView.setTextColor(ContextCompat.getColor(this, b.b.a.h.b.a(this, R.attr.text_color_rbtn_main)));
            }
        }
    }

    @Override // b.b.o.b.a.a
    public int c() {
        return R.layout.activity_main;
    }

    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: c0, reason: merged with bridge method [inline-methods] */
    public b.b.g.n.c newP() {
        return new b.b.g.n.c();
    }

    public void d0(boolean z) {
        b.b.m.c.a.a("MainActivity", "--onUpdateFlag--flag = " + z);
        runOnUiThread(new f(z));
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getActionMasked() != 5 || motionEvent.getActionIndex() < 1) {
            return super.dispatchTouchEvent(motionEvent);
        }
        b.b.m.c.a.a("MainActivity", "actionIndexDownPointer == 1 return false");
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.mvp.mvp.XActivity
    public void e() {
        super.e();
        k.c(getApplicationContext());
        ((b.b.g.n.c) mo7getP()).j();
    }

    public final void e0(ImageView imageView) {
        Activity activity;
        int i;
        if (imageView == null) {
            return;
        }
        int iA = R.drawable.img_1menu_bg_select_p;
        if (!this.w) {
            if (b.b.a.i.a.f531a) {
                activity = this.f3704b;
                i = R.attr.img_1menu_bg_select_h_r;
            } else {
                activity = this.f3704b;
                i = R.attr.img_1menu_bg_select_h;
            }
            iA = b.b.a.h.b.a(activity, i);
        }
        Drawable drawable = null;
        try {
            drawable = ContextCompat.getDrawable(this.f3704b, iA);
        } catch (RuntimeException e2) {
            b.b.m.c.a.c("MainActivity", "--setBgMenu1Selected--e: " + e2);
        }
        if (drawable != null) {
            imageView.setImageDrawable(drawable);
        }
    }

    public final void f0() {
        b.b.m.c.a.a("MainActivity", "---startSearchActivity------");
        Intent intent = new Intent(this, (Class<?>) SearchActivity.class);
        SearchActivity.s0(true);
        startActivityForResult(intent, 86);
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return b.b.o.b.b.a.a(this);
    }

    @Override // b.b.o.b.a.a
    public int h() {
        return R.layout.activity_main;
    }

    @Override // com.byd.mvp.mvp.XActivity
    public void k(Context context) {
        b.b.a.i.a.c(this);
        this.w = context.getResources().getConfiguration().orientation == 1;
        b.b.a.f.e eVarA = h.a();
        e eVar = new e();
        this.N = eVar;
        eVarA.e(eVar, q ? 2000L : 500L);
    }

    @Override // b.b.o.b.a.a
    public int m() {
        return R.layout.activity_main_byd;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.mvp.mvp.XActivity
    public void n(Context context) {
        b.b.m.c.a.a("MainActivity", "---initViews------" + toString());
        this.E = new LottieValueCallback<>(new SimpleColorFilter(ContextCompat.getColor(context, b.b.a.h.b.a(this, R.attr.left_bar_btn_text_color_s))));
        this.F = new LottieValueCallback<>(new SimpleColorFilter(ContextCompat.getColor(context, b.b.a.h.b.a(this, R.attr.left_bar_btn_text_color_n))));
        this.s = (ViewGroup) findViewById(R.id.rl_bg_main);
        findViewById(R.id.ll_search_main).setOnClickListener(this);
        this.v = (DampingScrollView) findViewById(R.id.mDampingScrollView);
        this.t = (ViewGroup) findViewById(R.id.ll_rg_main);
        this.u = (ViewGroup) findViewById(R.id.ll_search_main);
        int i = 0;
        while (true) {
            int[] iArr = this.x;
            if (i >= iArr.length) {
                break;
            }
            findViewById(iArr[i]).setOnTouchListener(new c(i));
            i++;
        }
        findViewById(this.B[0]).setVisibility(((b.b.g.n.c) mo7getP()).i() ? 0 : 8);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        super.onActivityResult(i, i2, intent);
        b.b.m.c.a.a("MainActivity", "onActivityResult", ", requestCode = " + Integer.toHexString(i));
        int iC = b.C0065b.a().c(i2, intent, getIntent());
        if (iC != -1) {
            G(iC);
            e0((ImageView) findViewById(this.y[this.f3708f]));
            findViewById(this.y[this.f3708f]).animate().alpha(1.0f).setDuration(100L).setInterpolator(PathInterpolatorUtils.getUniformSpeed()).start();
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        r.m();
        b.b.m.a.a.c(getClass().getName());
        b.b.m.e.i.a.n(getClass().getName());
    }

    @Override // com.byd.mvp.mvp.XActivity, android.view.View.OnClickListener
    public void onClick(View view) {
        super.onClick(view);
        if (!b.b.m.g.c.c(view, 300L) && view.getId() == R.id.ll_search_main) {
            f0();
        }
    }

    @Override // com.byd.mvp.mvp.XActivity, com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        b.b.m.c.a.g("MainActivity", "onCreate version = 1.18.2-202507110941");
        this.I = System.currentTimeMillis();
        IActivityManager service = ActivityManager.getService();
        this.J = service;
        try {
            b.b.g.l.a aVar = new b.b.g.l.a(service);
            this.K = aVar;
            service.registerTaskStackListener(aVar);
        } catch (RemoteException e2) {
            BydLogHelper.logE(e2.getMessage());
        }
        this.M = u.b(b.b.a.a.a.b().a(), "car_set_first_in", 0) == 0;
        b.C0065b.a().m();
    }

    @Override // com.byd.mvp.mvp.XActivity, com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        h.a().f(this.N);
        h.a().j(new a());
        this.H = null;
        try {
            this.J.unregisterTaskStackListener(this.K);
        } catch (RemoteException e2) {
            BydLogHelper.logE(e2.getMessage());
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        b.b.m.c.a.a("MainActivity", "---onNewIntent---Action: " + intent.getAction());
        b.b.m.c.a.a("MainActivity", "---onNewIntent---Scheme: " + intent.getScheme());
        setIntent(intent);
        if (X(intent)) {
            h.a().j(new b());
        }
    }

    @Override // com.byd.mvp.mvp.XActivity, com.bydux.api.common.views.window.BydActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        b.b.m.c.a.a("MainActivity", "---onPause---");
    }

    @Override // android.view.Window.Callback
    public void onPointerCaptureChanged(boolean z) {
    }

    @Override // com.byd.mvp.mvp.XActivity, android.app.Activity
    public void onRestoreInstanceState(@NonNull Bundle bundle) {
        if (bundle == null) {
            return;
        }
        this.f3708f = b.b.m.e.i.a.d(getClass().getName());
        if (this.H == null) {
            this.H = getSupportFragmentManager().findFragmentByTag(this.f3708f + "");
        }
        b.b.m.c.a.a("MainActivity", "---onRestoreInstanceState---curIndex = " + this.f3708f);
    }

    @Override // com.byd.mvp.mvp.XActivity, com.bydux.api.common.views.window.BydActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        b.b.m.c.a.a("MainActivity", "---onResume---curIndex: " + this.f3708f);
        boolean zX = X(getIntent()) ^ true;
        super.onResume();
        if (zX) {
            int iB = b.C0065b.a().b(this, getIntent());
            p[0] = getIntent().getIntExtra("main_intdex_0", -1);
            p[1] = getIntent().getIntExtra("main_intdex_1", -1);
            p[2] = getIntent().getIntExtra("main_intdex_2", -1);
            p[3] = getIntent().getIntExtra("main_intdex_3", -1);
            b.b.m.c.a.a("MainActivity", "---onResume---curIndex: " + this.f3708f + ", index: " + iB);
            if (iB != -1) {
                G(iB);
            }
            Y();
            getIntent().setAction("");
            b.b.m.c.a.a("MainActivity", "onResume");
            i.h();
        }
    }

    @Override // com.byd.mvp.mvp.XActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        G(this.f3708f);
        m.o();
        b.b.m.c.a.a("MainActivity", "---onSaveInstanceState---curIndex = " + this.f3708f);
    }

    @Override // com.byd.mvp.mvp.XActivity, com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        b.b.m.c.a.a("MainActivity", "---onStop---");
        r = getResources().getConfiguration().orientation;
    }

    @Override // com.byd.mvp.mvp.XActivity
    public void r(boolean z) {
        super.r(z);
    }

    @Override // com.byd.mvp.mvp.XActivity
    public boolean u() {
        if (b.b.m.a.a.d()) {
            if (V()) {
                return false;
            }
            if (!p() && W()) {
                return false;
            }
        }
        return super.u();
    }
}
