package com.byd.carsettings.ui.fragment.dilink;

import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.hardware.bydauto.properties.BYDAutoSysPropertiesDevice;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import b.b.g.r.x;
import com.byd.base.view.BydSwitch;
import com.byd.base.view.segmentedbutton.SegmentedButtonGroup;
import com.byd.carsettings.R;
import com.byd.carsettings.system.model.ClockStyle;
import com.byd.carsettings.ui.dialog.SimpleDialog;
import com.byd.carsettings.ui.widget.AreaableSeekbar;
import com.byd.carsettings.ui.widget.LinearItem;
import com.byd.mvp.mvp.XFragment;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes2.dex */
public class DisplayFragment extends XFragment<b.b.g.n.k.d> {
    public static final int[] m = {R.drawable.img_carsetting_dilink_show_screen_off, R.drawable.img_carsetting_dilink_show_clock1, R.drawable.img_carsetting_dilink_show_clock2, R.drawable.img_carsetting_dilink_show_clock3};
    public BydSwitch A;
    public SegmentedButtonGroup B;
    public LinearItem D;
    public ImageView E;
    public volatile int F;
    public final Runnable G = new c();
    public final ContentObserver H = new d(new Handler());
    public ViewGroup n;
    public ViewGroup o;
    public ViewGroup p;
    public ViewGroup q;
    public ViewGroup r;
    public ViewGroup s;
    public ViewGroup t;
    public ScrollView u;
    public ImageView v;
    public AreaableSeekbar w;
    public TextView x;
    public TextView y;
    public SegmentedButtonGroup z;

    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f3132a;

        public a(int i) {
            this.f3132a = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            int i = this.f3132a;
            DisplayFragment.this.w.setProgress(i);
            if (i > 0 && i < 3) {
                i = 3;
            }
            DisplayFragment.this.x.setText(((int) (i / 2.55f)) + "%");
        }
    }

    public class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f3134a;

        public b(int i) {
            this.f3134a = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            int i = this.f3134a;
            String string = 1 == i ? DisplayFragment.this.getString(R.string.wallpaper_launcher_display) : i == 0 ? DisplayFragment.this.getString(R.string.map_launcher_display) : null;
            LinearItem linearItem = DisplayFragment.this.D;
            if (string == null) {
                string = "";
            }
            linearItem.setRightText(string);
        }
    }

    public class c implements Runnable {
        public c() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Runnable
        public void run() {
            int iN = ((b.b.g.n.k.d) DisplayFragment.this.mo7getP()).n();
            b.b.m.c.a.a(DisplayFragment.this.f3715a, "ContentObserver, Brightness, onChange:" + iN);
            DisplayFragment.this.b0(iN);
            int iQ = ((b.b.g.n.k.d) DisplayFragment.this.mo7getP()).q();
            b.b.m.c.a.a(DisplayFragment.this.f3715a, "ContentObserver, LauncherMode, onChange:" + iN);
            DisplayFragment.this.d0(iQ);
        }
    }

    public class d extends ContentObserver {
        public d(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            if (z || DisplayFragment.this.mo7getP() == 0 || DisplayFragment.this.getContext() == null) {
                return;
            }
            b.b.a.f.h.a().d(DisplayFragment.this.G);
        }
    }

    public class e implements CompoundButton.OnCheckedChangeListener {
        public e() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            b.b.m.c.a.a(DisplayFragment.this.f3715a, "----initViews--linkageSwitch.onCheckedChanged---isChecked= " + z);
            ((b.b.g.n.k.d) DisplayFragment.this.mo7getP()).w(z);
        }
    }

    public class f extends b.b.a.j.d.a {
        public f() {
        }

        @Override // b.b.a.j.d.a
        public void onNoDoubleClick(View view) {
            DisplayFragment.this.Z();
        }
    }

    public class g implements b.b.g.q.h.c.b {
        public g() {
        }

        @Override // b.b.g.q.h.c.b, android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            b.b.m.c.a.a(DisplayFragment.this.f3715a, "----initViews---onProgressChanged---progress = " + i + ", b = " + z);
            DisplayFragment.this.b0(i);
            if (z) {
                DisplayFragment.this.W(i);
            }
        }
    }

    public class h implements SegmentedButtonGroup.f {
        public h() {
        }

        @Override // com.byd.base.view.segmentedbutton.SegmentedButtonGroup.f
        public void onPositionChanged(View view, View view2, int i) {
            DisplayFragment.this.Y(i);
        }
    }

    public class i implements SegmentedButtonGroup.f {
        public i() {
        }

        @Override // com.byd.base.view.segmentedbutton.SegmentedButtonGroup.f
        public void onPositionChanged(View view, View view2, int i) {
            DisplayFragment.this.X(i);
        }
    }

    public class j implements View.OnClickListener {
        public j() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            b.b.m.c.a.a(DisplayFragment.this.f3715a, "----onClick--show ACTION_LAUNCHER_SETTINGS.");
            Intent intent = new Intent();
            intent.setAction("com.byd.ACTION_launcher_settings");
            intent.putExtra("launcher_settings_dialog", 1);
            DisplayFragment.this.getActivity().sendBroadcast(intent);
        }
    }

    public class k implements Runnable {
        public k() {
        }

        @Override // java.lang.Runnable
        public void run() {
            DisplayFragment.this.B.setClickable(true);
        }
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void A(Context context) {
        this.n = (ViewGroup) this.f3717c.findViewById(R.id.display_parent);
        this.o = (ViewGroup) this.f3717c.findViewById(R.id.ll_brightness_adjust_of_media_display);
        this.p = (ViewGroup) this.f3717c.findViewById(R.id.ll_screen_saver_media_display);
        this.q = (ViewGroup) this.f3717c.findViewById(R.id.ll_dark_light_mode_display);
        this.r = (ViewGroup) this.f3717c.findViewById(R.id.ll_multimedia_brightness_linkage_media_display);
        this.s = (ViewGroup) this.f3717c.findViewById(R.id.ll_title_multimedia_brightness_linkage_media_display);
        this.t = (ViewGroup) this.f3717c.findViewById(R.id.item_launcher_setting_display);
        this.y = (TextView) this.f3717c.findViewById(R.id.tv_title_multimedia_brightness_linkage_media_display);
        this.u = (ScrollView) this.f3717c.findViewById(R.id.scv_display_pager);
        this.v = (ImageView) this.f3717c.findViewById(R.id.iv_display_pager);
        this.w = (AreaableSeekbar) this.f3717c.findViewById(R.id.sbar_brightness_pager_display);
        this.x = (TextView) this.f3717c.findViewById(R.id.tv_progress_pager_display);
        this.z = (SegmentedButtonGroup) this.f3717c.findViewById(R.id.srg_screen_saver_media_display);
        SegmentedButtonGroup segmentedButtonGroup = (SegmentedButtonGroup) this.f3717c.findViewById(R.id.srg_night_light_mode_media_display);
        this.B = segmentedButtonGroup;
        segmentedButtonGroup.setTag("DEBUG_TAG");
        this.D = (LinearItem) this.f3717c.findViewById(R.id.item_launcher_setting_display);
        BydSwitch bydSwitch = (BydSwitch) this.f3717c.findViewById(R.id.switch_multimedia_brightness_linkage_media_display);
        this.A = bydSwitch;
        bydSwitch.setOnCheckedChangeListener(new e());
        ImageView imageView = (ImageView) this.f3717c.findViewById(R.id.iv_details_multimedia_brightness);
        this.E = imageView;
        imageView.setOnClickListener(new f());
        this.w.setOnSeekBarChangeListener(new g());
        this.z.setOnPositionChangedListener(new h());
        this.B.setOnPositionChangedListener(new i());
        this.D.setOnClickListener(new j());
        EventBus.getDefault().register(this);
        if (context.getContentResolver() != null) {
            context.getContentResolver().registerContentObserver(Settings.System.getUriFor(BYDAutoSysPropertiesDevice.SCREEN_BRIGHTNESS), true, this.H);
        }
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void D(boolean z) {
        super.D(z);
        b.b.m.c.a.d(this.f3715a, "---loadUIStyleOnDenzaBrand----land: " + z);
        this.q.setVisibility(8);
    }

    public final void U() {
        this.A.setOnCheckedChangeListener(null);
        this.z.setOnPositionChangedListener(null);
        this.B.setOnPositionChangedListener(null);
        this.D.setOnClickListener(null);
        this.E.setOnClickListener(null);
        this.w.setOnSeekBarChangeListener(null);
        EventBus.getDefault().unregister(this);
        if (getContext() == null || getContext().getContentResolver() == null) {
            return;
        }
        getContext().getContentResolver().unregisterContentObserver(this.H);
    }

    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: V, reason: merged with bridge method [inline-methods] */
    public b.b.g.n.k.d newP() {
        return new b.b.g.n.k.d();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void W(int i2) {
        ((b.b.g.n.k.d) mo7getP()).v(i2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void X(int i2) {
        b.b.m.c.a.a(this.f3715a, "----onNightLightChecked---position: " + i2);
        ((b.b.g.n.k.d) mo7getP()).x(i2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void Y(int i2) {
        b.b.m.c.a.a(this.f3715a, "----onScreenSaverChecked------position:" + i2);
        ClockStyle clockStyle = ClockStyle.CLOSE;
        if (i2 != 0) {
            if (i2 == 1) {
                clockStyle = ClockStyle.CLOCK_1;
            } else if (i2 == 2) {
                clockStyle = ClockStyle.CLOCK_2;
            } else if (i2 == 3) {
                clockStyle = ClockStyle.CLOCK_3;
            }
        }
        ((b.b.g.n.k.d) mo7getP()).y(clockStyle);
        e0(i2);
    }

    public final void Z() {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        SimpleDialog.z(activity.getString(R.string.dialog_title_hint), activity.getString(R.string.hint_msg_multimedia_detail), activity.getString(R.string.dialog_btn_ok), "", 0).show(getActivity().getSupportFragmentManager(), "SimpleDialog");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void a0() {
        boolean zT = ((b.b.g.n.k.d) mo7getP()).t();
        b.b.m.c.a.a(this.f3715a, "----updateBrightnessLinkageUI--brightnessLinkEnable = " + zT);
        this.A.setChecked(zT, false);
    }

    public final void b0(int i2) {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.runOnUiThread(new a(i2));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void c0() {
        int i2 = ((b.b.g.n.k.d) mo7getP()).r().value;
        if (i2 < 0 || i2 >= this.z.getButtonChildCount()) {
            return;
        }
        this.z.setPosition(i2);
        e0(i2);
    }

    public final void d0(int i2) {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.runOnUiThread(new b(i2));
        }
    }

    public final void e0(int i2) {
        if (i2 >= 0) {
            int[] iArr = m;
            if (i2 < iArr.length) {
                this.v.setImageResource(iArr[i2]);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void f0() {
        this.F = ((b.b.g.n.k.d) mo7getP()).n();
        b.b.m.c.a.a(this.f3715a, "----updateUIOnDataUpdated--curBrightness = " + this.F);
        b0(this.F);
        c0();
        int i2 = 2;
        try {
            if (getContext() != null) {
                i2 = Settings.Global.getInt(getContext().getContentResolver(), "ui_night_mode");
            }
        } catch (Settings.SettingNotFoundException e2) {
            e2.printStackTrace();
        }
        int iO = ((b.b.g.n.k.d) mo7getP()).o(i2);
        if (iO >= 0 && iO < this.B.getButtonChildCount()) {
            b.b.m.c.a.a(this.f3715a, "----updateUIOnDataUpdated--darkNightIndex: " + iO);
            this.B.setPosition(iO);
        }
        int iQ = ((b.b.g.n.k.d) mo7getP()).q();
        d0(iQ);
        b.b.m.c.a.a(this.f3715a, "---updateUIOnDataUpdated---darkNightIndex = " + iO + ", launcherMode = " + iQ);
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return R.layout.pager_display;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.mvp.mvp.XFragment, com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        ((b.b.g.n.k.d) mo7getP()).z();
        b.b.m.f.d.d.c(getClass().getName());
        U();
        b.b.a.f.h.a().i(this.G);
        super.onDestroyView();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(b.b.a.c.a aVar) {
        if (aVar.h() != 230000) {
            return;
        }
        int iC = aVar.c();
        int position = this.B.getPosition();
        b.b.m.c.a.a(this.f3715a, "---onGetMessage--NIGHT_MODE_CHANGE, modeIndex: " + iC + ", curPosition: " + position);
        if (iC != position) {
            this.B.setPosition(iC);
        }
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void onLtrRudderR(boolean z) {
        if (z) {
            setViewGroupRtl(this.n, true);
            setViewGroupRtl(this.o, false);
            setViewGroupRtl(this.p, false);
            setViewGroupRtl(this.q, false);
            setViewGroupRtl(this.t, false);
            setViewGroupRtl(this.z, false);
            setViewGroupRtl(this.B, false);
        }
        setViewGroupRtl(this.r, true);
        setViewGroupRtl(this.s, false);
    }

    @Override // com.byd.mvp.mvp.XFragment, com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        f0();
        this.B.setClickable(false);
        this.B.postDelayed(new k(), 800L);
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void onRtlRudderL(boolean z) {
        if (z) {
            setViewGroupRtl(this.n, false);
            setViewGroupRtl(this.z, false);
            setViewGroupRtl(this.B, false);
            setViewGroupRtl(this.o, true);
            setViewGroupRtl(this.p, true);
            setViewGroupRtl(this.q, true);
            setViewGroupRtl(this.t, true);
        } else {
            setViewGroupRtl(this.z, false);
            setViewGroupRtl(this.B, false);
        }
        setViewGroupRtl(this.r, false);
        setViewGroupRtl(this.s, true);
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void t(int i2) {
        b.b.m.c.a.a(this.f3715a, "----checkSubindexOnResume--subindex = " + i2);
        H(this.u, i2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.mvp.mvp.XFragment
    public void y(Context context) {
        ((b.b.g.n.k.d) mo7getP()).init(getContext());
        this.v.setImageResource(R.drawable.img_carsetting_dilink_show_default);
        this.D.setVisibility(((b.b.g.n.k.d) mo7getP()).s(context) ? 0 : 8);
        a0();
        this.z.setClickTime(50);
        x.d(this.y);
    }
}
