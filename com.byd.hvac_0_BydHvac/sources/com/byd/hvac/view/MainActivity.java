package com.byd.hvac.view;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import b.a.a.d.g;
import b.a.a.f.f1;
import b.a.a.g.j;
import b.a.a.h.h;
import b.a.a.h.l;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieCompositionFactory;
import com.airbnb.lottie.LottieListener;
import com.byd.hvac.R;
import com.byd.hvac.base.BaseActivity;
import com.byd.hvac.view.MainActivity;
import com.byd.hvac.view.accontrol.AcControlFragment;
import com.byd.hvac.view.electrictemperature.EvFragment;
import com.byd.hvac.view.fragrance.FragranceControlFragment;
import com.byd.hvac.view.green.GreenControlFragment;
import com.byd.hvac.view.seat.SeatHeatVentilationFragment;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.ArrayList;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class MainActivity extends BaseActivity<j> implements g {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String f995b = MainActivity.class.getSimpleName();
    public int A;
    public ConstraintLayout B;
    public int C;
    public FragmentManager D;
    public View E;
    public boolean F;
    public b G;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Fragment f996c;
    public int d = 0;
    public String e;
    public int f;
    public ImageView g;
    public LottieAnimationView h;
    public ImageView i;
    public LottieAnimationView j;
    public TextView k;
    public ConstraintLayout l;
    public ImageView m;
    public LottieAnimationView n;
    public ImageView o;
    public LottieAnimationView p;
    public ImageView q;
    public LottieAnimationView r;
    public ArrayList<ImageView> s;
    public ArrayList<LottieAnimationView> t;
    public ArrayList<Integer> u;
    public ImageView v;
    public ConstraintLayout w;
    public int x;
    public int y;
    public int z;

    /* JADX INFO: compiled from: caradapter-bug */
    public class b extends BroadcastReceiver {
        public b() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.intent.action.CONFIGURATION_CHANGED")) {
                BydLogHelper.logW(MainActivity.f995b, "ConfigurationChangeReceiver ----onReceive = ");
                if (((j) MainActivity.this.f984a).g()) {
                    MainActivity.this.M0(context);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: A0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void B0(View view) {
        t0(1, 0);
        J0(this.u.get(1).intValue(), this.t.get(1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: C0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void D0(View view) {
        t0(2, 0);
        J0(this.u.get(2).intValue(), this.t.get(2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: E0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void F0(View view) {
        t0(3, 0);
        J0(this.u.get(3).intValue(), this.t.get(3));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: G0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void H0(View view) {
        K0();
    }

    public static /* synthetic */ void I0(LottieAnimationView lottieAnimationView, LottieComposition lottieComposition) {
        lottieAnimationView.setComposition(lottieComposition);
        lottieAnimationView.playAnimation();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: y0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void z0(View view) {
        if (TextUtils.isEmpty(this.e) || !this.e.equals("HvacRear")) {
            t0(0, 0);
        } else {
            t0(0, 1);
        }
        J0(this.u.get(0).intValue(), this.t.get(0));
    }

    @Override // com.byd.hvac.base.BaseActivity
    public int G() {
        return R.layout.activity_main;
    }

    public final void J0(int i, final LottieAnimationView lottieAnimationView) {
        LottieCompositionFactory.fromRawRes(this, i).addListener(new LottieListener() { // from class: b.a.a.i.a
            @Override // com.airbnb.lottie.LottieListener
            public final void onResult(Object obj) {
                MainActivity.I0(lottieAnimationView, (LottieComposition) obj);
            }
        });
    }

    public final void K0() {
        PackageManager packageManager = getPackageManager();
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("byd_pad://byd_car_settings:2933/launcher?action=AirConditioner"));
        intent.setComponent(new ComponentName("com.byd.carsettings", "com.byd.dialogservice.DialogService"));
        if (packageManager.queryIntentServices(intent, 0).isEmpty()) {
            return;
        }
        startService(intent);
    }

    public final void L0(int i) {
        BydLogHelper.logD("----index = " + i);
        ImageView imageView = this.v;
        if (imageView != null) {
            if (i == 0) {
                imageView.setBackground(ContextCompat.getDrawable(this, v0(R.mipmap.img_ac_bg)));
            } else {
                imageView.setBackground(ContextCompat.getDrawable(this, R.mipmap.img_app_bg));
            }
        }
    }

    public final void M0(Context context) {
        Configuration configuration = context.getResources().getConfiguration();
        int i = getResources().getConfiguration().uiMode & 48;
        BydLogHelper.logW(f995b, "updateConfigurationChange ----config.orientation = " + configuration.orientation + "  ---  currentNightMode : " + i);
        int i2 = configuration.orientation;
        if (i2 == 2) {
            if (i == 32) {
                ((j) this.f984a).o(1);
                return;
            } else {
                ((j) this.f984a).o(0);
                return;
            }
        }
        if (i2 == 1) {
            if (i == 32) {
                ((j) this.f984a).o(3);
            } else {
                ((j) this.f984a).o(2);
            }
        }
    }

    @Override // com.byd.hvac.base.BaseActivity
    public void V() {
        f1.l0().B3();
        if (((j) this.f984a).k()) {
            b bVar = new b();
            this.G = bVar;
            registerReceiver(bVar, new IntentFilter("android.intent.action.CONFIGURATION_CHANGED"));
        }
        b.a.a.h.a.b(this);
        if (this.f984a != 0 && this.y == 2) {
            BydLogHelper.logW(f995b, "空调控制类型为 EV ，return");
            return;
        }
        if (h.b().a("KEY_SUPPORT_FRAGRANCE")) {
            this.f = h.b().c("KEY_SUPPORT_FRAGRANCE", 0);
            BydLogHelper.logW(f995b, "initData  SharedPreferencesUtils mSupportFragrance= " + this.f);
        } else {
            this.f = ((j) this.f984a).l();
            BydLogHelper.logW(f995b, "initData  mSupportFragrance= " + this.f);
            h.b().d("KEY_SUPPORT_FRAGRANCE", this.f);
        }
        if (h.b().a("KEY_SUPPORT_GREEN")) {
            this.A = h.b().c("KEY_SUPPORT_GREEN", 0);
            BydLogHelper.logW(f995b, "initData  SharedPreferencesUtils mHas4F6= " + this.A);
        } else {
            this.A = ((j) this.f984a).n(2);
            BydLogHelper.logW(f995b, "initData   mHas4F6= " + this.A);
            h.b().d("KEY_SUPPORT_GREEN", this.A);
        }
        ArrayList<ImageView> arrayList = new ArrayList<>();
        this.s = arrayList;
        arrayList.add(this.g);
        ArrayList<LottieAnimationView> arrayList2 = new ArrayList<>();
        this.t = arrayList2;
        arrayList2.add(this.h);
        ArrayList<Integer> arrayList3 = new ArrayList<>();
        this.u = arrayList3;
        arrayList3.add(Integer.valueOf(R.raw.icon_air_conditioning_module));
        x0();
        if (this.A == 1) {
            this.s.add(this.m);
            this.t.add(this.n);
            this.u.add(Integer.valueOf(R.raw.icon_green_clean));
        } else {
            this.s.add(null);
            this.t.add(null);
            this.u.add(0);
            this.B.setVisibility(8);
        }
        if (this.f == 1) {
            this.s.add(this.o);
            this.t.add(this.p);
            this.u.add(Integer.valueOf(R.raw.icon_fragrance));
        } else {
            this.s.add(null);
            this.t.add(null);
            this.u.add(0);
            this.w.setVisibility(8);
        }
        this.s.add(this.q);
        this.t.add(this.r);
        this.u.add(Integer.valueOf(R.raw.icon_set_up));
        t0(this.d, 0);
    }

    @Override // b.a.a.d.g
    public void a(boolean z) {
        BydLogHelper.logW(f995b, "update3dEngineLoadState ----load = " + z);
        M0(this);
    }

    @Override // b.a.a.d.g
    public void b(boolean z) {
        l.d(z ? 4 : 0, this.E);
    }

    @Override // com.byd.hvac.base.BaseActivity
    public void f0() {
        BydLogHelper.logD("initView  :");
        if (h.b().a("KEY_AC_TYPE")) {
            this.y = h.b().c("KEY_AC_TYPE", 1);
        } else {
            this.y = ((j) this.f984a).h();
            h.b().d("KEY_AC_TYPE", this.y);
        }
        this.z = ((j) this.f984a).j();
        this.x = ((j) this.f984a).m();
        this.F = ((j) this.f984a).k();
        if (this.f984a != 0 && this.y == 2) {
            BydLogHelper.logD("空调控制类型为 EV ，return");
            return;
        }
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.fl_layout_not_ev);
        frameLayout.setVisibility(0);
        View viewInflate = this.x == 1 ? this.z == 1 ? LayoutInflater.from(this).inflate(R.layout.fragment_not_ev_right_rudder_denza, (ViewGroup) null) : LayoutInflater.from(this).inflate(R.layout.fragment_not_ev_denza, (ViewGroup) null) : this.z == 1 ? LayoutInflater.from(this).inflate(R.layout.fragment_not_ev_right_rudder, (ViewGroup) null) : LayoutInflater.from(this).inflate(R.layout.fragment_not_ev, (ViewGroup) null);
        frameLayout.addView(viewInflate);
        this.v = (ImageView) findViewById(R.id.iv_main_bg);
        this.g = (ImageView) viewInflate.findViewById(R.id.iv_ac_control_bg);
        this.h = (LottieAnimationView) viewInflate.findViewById(R.id.iv_ac_control);
        this.i = (ImageView) viewInflate.findViewById(R.id.iv_seat_control_bg);
        this.j = (LottieAnimationView) viewInflate.findViewById(R.id.iv_seat_control);
        this.l = (ConstraintLayout) viewInflate.findViewById(R.id.ll_seat_control_container);
        this.k = (TextView) viewInflate.findViewById(R.id.tv_seat_control);
        this.B = (ConstraintLayout) viewInflate.findViewById(R.id.ll_green_control_container);
        this.m = (ImageView) viewInflate.findViewById(R.id.iv_green_control_bg);
        this.n = (LottieAnimationView) viewInflate.findViewById(R.id.iv_green_control);
        this.o = (ImageView) viewInflate.findViewById(R.id.iv_fragrance_control_bg);
        this.w = (ConstraintLayout) viewInflate.findViewById(R.id.ll_fragrance_control_container);
        this.p = (LottieAnimationView) viewInflate.findViewById(R.id.iv_fragrance_control);
        this.q = (ImageView) viewInflate.findViewById(R.id.iv_set_control_bg);
        this.r = (LottieAnimationView) viewInflate.findViewById(R.id.iv_set_control);
        this.E = viewInflate.findViewById(R.id.rl_tab_container);
    }

    @Override // com.byd.hvac.base.BaseActivity
    public void i0() {
        if (this.f984a != 0 && this.y == 2) {
            BydLogHelper.logD("空调控制类型为 EV ，return");
            return;
        }
        this.g.setOnClickListener(new View.OnClickListener() { // from class: b.a.a.i.e
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f266a.z0(view);
            }
        });
        this.i.setOnClickListener(new View.OnClickListener() { // from class: b.a.a.i.f
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f267a.B0(view);
            }
        });
        this.m.setOnClickListener(new View.OnClickListener() { // from class: b.a.a.i.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f264a.D0(view);
            }
        });
        this.o.setOnClickListener(new View.OnClickListener() { // from class: b.a.a.i.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f263a.F0(view);
            }
        });
        this.q.setOnClickListener(new View.OnClickListener() { // from class: b.a.a.i.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f265a.H0(view);
            }
        });
    }

    @Override // com.byd.hvac.base.BaseActivity, com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        this.D = getSupportFragmentManager();
        if (bundle != null) {
            int i = bundle.getInt("mCurrentIndex", 0);
            this.d = i;
            this.f996c = this.D.findFragmentById(i);
            BydLogHelper.logE("onCreate mCurrentFragment = " + this.f996c);
            bundle.clear();
            BydLogHelper.logE("mCurrentIndex :" + this.d);
        }
        super.onCreate(bundle);
        if (this.f984a == 0 || this.y != 2) {
            w0();
            return;
        }
        ((FrameLayout) findViewById(R.id.fl_layout_ev)).setVisibility(0);
        FragmentTransaction fragmentTransactionBeginTransaction = this.D.beginTransaction();
        EvFragment evFragment = new EvFragment();
        if (evFragment.isAdded()) {
            fragmentTransactionBeginTransaction.show(evFragment);
        } else {
            fragmentTransactionBeginTransaction.add(R.id.fl_layout_ev, evFragment, null);
        }
        fragmentTransactionBeginTransaction.commit();
    }

    @Override // com.byd.hvac.base.BaseActivity, com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        ArrayList<LottieAnimationView> arrayList = this.t;
        if (arrayList != null) {
            for (LottieAnimationView lottieAnimationView : arrayList) {
                if (lottieAnimationView != null) {
                    lottieAnimationView.clearAnimation();
                }
            }
        }
        ImageView imageView = this.v;
        if (imageView != null) {
            imageView.setBackground(null);
            this.v = null;
        }
        b bVar = this.G;
        if (bVar != null) {
            unregisterReceiver(bVar);
        }
        f1.l0().G4();
        super.onDestroy();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        BydLogHelper.logE("onNewIntent = ");
        s0(intent);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        bundle.putInt("mCurrentIndex", this.d);
        BydLogHelper.logE("mCurrentIndex :" + this.d);
        super.onSaveInstanceState(bundle);
    }

    public final void s0(Intent intent) {
        String stringExtra = intent.getStringExtra("from");
        int flags = intent.getFlags() & 4194304;
        String str = f995b;
        BydLogHelper.logD(str, "--checkNavibarAction--from: " + stringExtra + ", flag: " + flags);
        if ((intent.getFlags() & 4194304) != 4194304 && "navi_bar".equals(intent.getStringExtra("from"))) {
            finish();
            BydLogHelper.logD(str, "--checkNavibarAction--return: ");
            return;
        }
        BydLogHelper.logD(str, "--checkNavibarAction--handIntent: ");
        if (this.f984a == 0 || this.y != 2) {
            w0();
        } else {
            BydLogHelper.logD("空调控制类型为 EV ，return");
        }
    }

    public final void t0(int i, int i2) {
        BydLogHelper.logE("enterFragment index = " + i + " -- subpage = " + i2 + " -- mCurrentIndex = " + this.d);
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransactionBeginTransaction = supportFragmentManager.beginTransaction();
        StringBuilder sb = new StringBuilder();
        sb.append(i);
        sb.append("");
        Fragment fragmentFindFragmentByTag = supportFragmentManager.findFragmentByTag(sb.toString());
        BydLogHelper.logE("enterFragment fragment = " + fragmentFindFragmentByTag);
        if (fragmentFindFragmentByTag == null) {
            if (i == 1) {
                int i3 = this.C;
                if (i3 == 3 || i3 == 1 || i3 == 2) {
                    fragmentFindFragmentByTag = new SeatHeatVentilationFragment();
                } else {
                    fragmentFindFragmentByTag = u0(supportFragmentManager);
                    i = 0;
                }
            } else if (i != 2) {
                if (i == 3 && this.f == 1) {
                    fragmentFindFragmentByTag = new FragranceControlFragment();
                } else {
                    fragmentFindFragmentByTag = u0(supportFragmentManager);
                    i = 0;
                }
            } else if (this.A == 1) {
                fragmentFindFragmentByTag = new GreenControlFragment();
            } else {
                fragmentFindFragmentByTag = u0(supportFragmentManager);
                i = 0;
            }
        } else if (i != 1) {
            if (i != 2) {
                if (i == 3 && this.f != 1) {
                    fragmentTransactionBeginTransaction.remove(fragmentFindFragmentByTag);
                    fragmentFindFragmentByTag = u0(supportFragmentManager);
                    i = 0;
                }
            } else if (this.A != 1) {
                fragmentTransactionBeginTransaction.remove(fragmentFindFragmentByTag);
                fragmentFindFragmentByTag = u0(supportFragmentManager);
                i = 0;
            }
        } else if (this.C == 0) {
            fragmentTransactionBeginTransaction.remove(fragmentFindFragmentByTag);
            fragmentFindFragmentByTag = u0(supportFragmentManager);
            i = 0;
        }
        BydLogHelper.logE("enterFragment fragment2 = " + fragmentFindFragmentByTag);
        Bundle bundle = new Bundle();
        bundle.putInt("subpage", i2);
        fragmentFindFragmentByTag.setArguments(bundle);
        BydLogHelper.logE("enterFragment mCurrentFragment = " + this.f996c);
        BydLogHelper.logE("enterFragment fragment.equals(mCurrentFragment) = " + fragmentFindFragmentByTag.equals(this.f996c));
        if (!fragmentFindFragmentByTag.equals(this.f996c)) {
            for (int i4 = 0; i4 < this.s.size(); i4++) {
                if (this.s.get(i4) != null) {
                    if (i4 == i) {
                        this.s.get(i4).setImageResource(R.mipmap.img_menu_bg_select);
                    } else {
                        this.s.get(i4).setImageResource(0);
                    }
                }
            }
            if (this.f996c != null) {
                BydLogHelper.logE("enterFragment hide mCurrentFragment = ");
                fragmentTransactionBeginTransaction.hide(this.f996c);
            }
            this.f996c = fragmentFindFragmentByTag;
            if (fragmentFindFragmentByTag.isAdded()) {
                fragmentTransactionBeginTransaction.show(fragmentFindFragmentByTag);
            } else {
                fragmentTransactionBeginTransaction.add(R.id.cc_right_layout, fragmentFindFragmentByTag, String.valueOf(i));
            }
        }
        BydLogHelper.logE("enterFragment transaction commit = ");
        fragmentTransactionBeginTransaction.commit();
        if (i != this.d) {
            this.d = i;
        }
        BydLogHelper.logE("enterFragment mCurrentIndex = " + this.d);
        L0(this.d);
    }

    @NonNull
    public final Fragment u0(FragmentManager fragmentManager) {
        Fragment fragmentFindFragmentByTag = fragmentManager.findFragmentByTag("0");
        if (fragmentFindFragmentByTag != null) {
            return fragmentFindFragmentByTag;
        }
        BydLogHelper.logW(f995b, "getHomeFragment----mElectricAirOutletConfig = " + this.F);
        return new AcControlFragment();
    }

    public final int v0(int i) {
        if (this.z != 1) {
            return i;
        }
        switch (i) {
            case R.layout.activity_main /* 2131492892 */:
                return R.layout.activity_main_right_rudder;
            case R.mipmap.img_ac_bg /* 2131558740 */:
                return R.mipmap.img_ac_bg_right_rudder;
            case R.mipmap.img_ac_bg_denza /* 2131558741 */:
                return R.mipmap.img_ac_bg_denza_right_rudder;
            default:
                return 0;
        }
    }

    public final void w0() {
        this.e = getIntent().getStringExtra("module");
        BydLogHelper.logD("getStringExtra = " + this.e);
        if (!TextUtils.isEmpty(this.e) && this.f984a != 0 && this.y != 2) {
            String str = this.e;
            str.hashCode();
            switch (str) {
                case "Hvac":
                case "HvacFront":
                    t0(0, 0);
                    break;
                case "Hvac.Green":
                    t0(2, 0);
                    break;
                case "HvacRear":
                    t0(0, 1);
                    break;
                case "Hvac.Seat":
                case "Hvac.SeatHeatVentilation":
                    t0(1, 0);
                    break;
                case "Hvac.Fragrance":
                    t0(3, 0);
                    break;
            }
        }
        getIntent().putExtra("module", "");
    }

    public final void x0() {
        BydLogHelper.logD("initSeatName start ");
        this.C = 0;
        if (h.b().a("KEY_SUPPORT_SEAT_TYPE")) {
            this.C = h.b().c("KEY_SUPPORT_SEAT_TYPE", 0);
            BydLogHelper.logW(f995b, "initSeatName  SharedPreferencesUtils seatType = " + this.C);
        } else {
            this.C = ((j) this.f984a).i();
            h.b().d("KEY_SUPPORT_SEAT_TYPE", this.C);
            BydLogHelper.logW(f995b, "initSeatName  seatType = " + this.C);
        }
        int i = this.C;
        if (i == 3) {
            this.j.setImageResource(R.mipmap.icon_ventilation_heating);
            this.u.add(Integer.valueOf(R.raw.icon_ventilation_heating));
            this.k.setText(R.string.seat_heat_ventilation);
            this.s.add(this.i);
            this.t.add(this.j);
        } else if (i == 1) {
            this.j.setImageResource(R.mipmap.icon_seat_heat);
            this.u.add(Integer.valueOf(R.raw.icon_seat_heat));
            this.k.setText(R.string.seat_heat);
            this.s.add(this.i);
            this.t.add(this.j);
        } else if (i == 2) {
            this.j.setImageResource(R.mipmap.icon_seat_ventilation);
            this.u.add(Integer.valueOf(R.raw.icon_seat_ventilation));
            this.k.setText(R.string.seat_ventilation);
            this.s.add(this.i);
            this.t.add(this.j);
        } else {
            this.s.add(null);
            this.t.add(null);
            this.u.add(0);
            this.l.setVisibility(8);
        }
        BydLogHelper.logD("initSeatName end");
    }
}
