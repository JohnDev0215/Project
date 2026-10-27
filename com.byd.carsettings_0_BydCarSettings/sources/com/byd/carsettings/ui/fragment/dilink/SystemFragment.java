package com.byd.carsettings.ui.fragment.dilink;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.byd.base.view.BydSwitch;
import com.byd.base.view.segmentedbutton.SegmentedButtonGroup;
import com.byd.carsettings.R;
import com.byd.carsettings.model.dilink.BydDate;
import com.byd.carsettings.system.model.BydLanguage;
import com.byd.carsettings.ui.activity.ContainerActivity;
import com.byd.carsettings.ui.dialog.CustomViewDialog;
import com.byd.carsettings.ui.dialog.SimpleDialog;
import com.byd.carsettings.ui.widget.LinearItem;
import com.byd.carsettings.ui.widget.SimpleLinearItem;
import com.byd.carsettings.ui.widget.TintLinearItem;
import com.byd.mvp.mvp.XFragment;
import com.byd.systemsettings.system.PrivacyPolicyDialog;
import com.byd.systemsettings.system.TimeSettingDialog;
import com.byd.systemsettings.system.TimeZoneDialog;
import com.byd.systemsettings.system.unit.UnitSettingsDialog;
import java.lang.ref.WeakReference;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes2.dex */
public class SystemFragment extends XFragment<b.b.g.n.k.g> implements View.OnClickListener, TimeSettingDialog.OnOKClickedListener {
    public SimpleLinearItem A;
    public SimpleLinearItem B;
    public SimpleLinearItem D;
    public SimpleLinearItem E;
    public SimpleLinearItem F;
    public View G;
    public m K;
    public List<b.b.g.m.d.e> L;
    public FragmentManager M;
    public ViewGroup m;
    public ViewGroup n;
    public ViewGroup o;
    public ViewGroup p;
    public View q;
    public BydSwitch r;
    public BydSwitch s;
    public SegmentedButtonGroup t;
    public ScrollView u;
    public TintLinearItem v;
    public LinearLayout w;
    public TextView x;
    public LinearItem y;
    public SimpleLinearItem z;
    public volatile boolean H = true;
    public final int I = 0;
    public final int J = 0;
    public final BroadcastReceiver N = new d();

    public class a extends b.b.a.j.d.a {
        public a() {
        }

        @Override // b.b.a.j.d.a
        public void onNoDoubleClick(View view) {
            if (b.b.m.g.c.a(200L)) {
                b.b.m.c.a.a(SystemFragment.this.f3715a, "----checkMutiClick return-------");
                return;
            }
            UnitSettingsDialog unitSettingsDialog = new UnitSettingsDialog();
            if (SystemFragment.this.M != null) {
                unitSettingsDialog.show(SystemFragment.this.M, UnitSettingsDialog.TAG);
                b.b.m.c.a.a(SystemFragment.this.f3715a, "----unitSettingsDialog.show-------");
            }
        }
    }

    public class b extends b.b.a.j.d.a {
        public b() {
        }

        @Override // b.b.a.j.d.a
        public void onNoDoubleClick(View view) {
            if (b.b.m.g.c.a(200L)) {
                return;
            }
            PrivacyPolicyDialog privacyPolicyDialogNewInstance = PrivacyPolicyDialog.newInstance(2);
            if (SystemFragment.this.M != null) {
                privacyPolicyDialogNewInstance.show(SystemFragment.this.M, "PrivacyPolicyDialog");
            }
        }
    }

    public class c extends b.b.a.j.d.a {
        public c() {
        }

        @Override // b.b.a.j.d.a
        public void onNoDoubleClick(View view) {
            if (b.b.m.g.c.a(200L)) {
                return;
            }
            PrivacyPolicyDialog privacyPolicyDialogNewInstance = PrivacyPolicyDialog.newInstance(1);
            if (SystemFragment.this.M != null) {
                privacyPolicyDialogNewInstance.show(SystemFragment.this.M, "PrivacyPolicyDialog");
            }
        }
    }

    public class d extends BroadcastReceiver {
        public d() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent != null && "android.intent.action.TIMEZONE_CHANGED".equals(intent.getAction())) {
                Calendar calendar = Calendar.getInstance();
                SystemFragment.this.x.setText(b.b.g.p.l.a.b(calendar.getTimeZone(), calendar.getTime()));
            }
        }
    }

    public class e extends b.b.a.j.d.a {
        public e() {
        }

        @Override // b.b.a.j.d.a
        public void onNoDoubleClick(View view) {
            if (b.b.m.g.c.a(200L)) {
                return;
            }
            PrivacyPolicyDialog privacyPolicyDialogNewInstance = PrivacyPolicyDialog.newInstance(3);
            if (SystemFragment.this.M != null) {
                privacyPolicyDialogNewInstance.show(SystemFragment.this.M, "PrivacyPolicyDialog");
            }
        }
    }

    public class f extends b.b.a.j.d.a {
        public f() {
        }

        @Override // b.b.a.j.d.a
        public void onNoDoubleClick(View view) {
            SystemFragment.this.U();
        }
    }

    public class g implements CompoundButton.OnCheckedChangeListener {
        public g() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            b.b.m.c.a.a(SystemFragment.this.f3715a, "--initViews--timeSync--isChecked = " + z);
            SystemFragment.this.a0(z);
        }
    }

    public class h implements CompoundButton.OnCheckedChangeListener {
        public h() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            b.b.m.c.a.a(SystemFragment.this.f3715a, "--initViews--playback--isChecked = " + z);
            ((b.b.g.n.k.g) SystemFragment.this.mo7getP()).y(z);
        }
    }

    public class i implements SegmentedButtonGroup.f {
        public i() {
        }

        @Override // com.byd.base.view.segmentedbutton.SegmentedButtonGroup.f
        public void onPositionChanged(View view, View view2, int i) {
            if (i == 0) {
                SystemFragment.this.b0(true);
            } else {
                if (i != 1) {
                    return;
                }
                SystemFragment.this.b0(false);
            }
        }
    }

    public class j extends b.b.a.j.d.a {
        public j() {
        }

        @Override // b.b.a.j.d.a
        public void onNoDoubleClick(View view) {
            if (b.b.m.g.c.a(200L)) {
                return;
            }
            TimeZoneDialog timeZoneDialogC0 = SystemFragment.this.c0();
            if (SystemFragment.this.M != null) {
                timeZoneDialogC0.show(SystemFragment.this.M, "TimeZoneDialog");
            }
        }
    }

    public class k implements View.OnClickListener {
        public k() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (b.b.m.g.c.a(200L)) {
                return;
            }
            Intent intent = new Intent(SystemFragment.this.getActivity(), (Class<?>) ContainerActivity.class);
            intent.putExtra("action", "sys_language_settings_limit");
            SystemFragment.this.startActivity(intent);
        }
    }

    public class l extends b.b.a.j.d.a {
        public l() {
        }

        @Override // b.b.a.j.d.a
        public void onNoDoubleClick(View view) {
            if (b.b.m.g.c.a(200L)) {
                return;
            }
            SystemFragment.this.X();
        }
    }

    public static class m extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference<SystemFragment> f3199a;

        public /* synthetic */ m(SystemFragment systemFragment, d dVar) {
            this(systemFragment);
        }

        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            WeakReference<SystemFragment> weakReference = this.f3199a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            int i = message.what;
            if (i == 86) {
                this.f3199a.get().f0();
                sendEmptyMessageDelayed(86, 1000L);
            } else {
                if (i != 87) {
                    return;
                }
                this.f3199a.get().g0();
            }
        }

        public m(SystemFragment systemFragment) {
            this.f3199a = new WeakReference<>(systemFragment);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.mvp.mvp.XFragment
    public void A(Context context) {
        ((b.b.g.n.k.g) mo7getP()).s();
        this.m = (ViewGroup) this.f3717c.findViewById(R.id.system_parent);
        this.u = (ScrollView) this.f3717c.findViewById(R.id.scv_system_pager);
        this.q = this.f3717c.findViewById(R.id.ll_time_sync_system_pager);
        this.n = (ViewGroup) this.f3717c.findViewById(R.id.ll_title_time_sync_system_pager);
        this.r = (BydSwitch) this.f3717c.findViewById(R.id.switch_time_sync_system_pager);
        this.p = (ViewGroup) this.f3717c.findViewById(R.id.ll_time_system_system_pager);
        this.t = (SegmentedButtonGroup) this.f3717c.findViewById(R.id.srg_time_system_system_pager);
        this.v = (TintLinearItem) this.f3717c.findViewById(R.id.item_time_system_pager);
        this.w = (LinearLayout) this.f3717c.findViewById(R.id.item_time_zone_system_pager);
        this.x = (TextView) this.f3717c.findViewById(R.id.tv_zone_name);
        this.y = (LinearItem) this.f3717c.findViewById(R.id.item_language_system_pager);
        this.z = (SimpleLinearItem) this.f3717c.findViewById(R.id.item_def_input_method_pager);
        this.A = (SimpleLinearItem) this.f3717c.findViewById(R.id.item_cur_input_setting_method_pager);
        this.B = (SimpleLinearItem) this.f3717c.findViewById(R.id.item_unit_system_pager);
        this.D = (SimpleLinearItem) this.f3717c.findViewById(R.id.item_user_agreement_system_pager);
        this.E = (SimpleLinearItem) this.f3717c.findViewById(R.id.item_privacy_policy_system_pager);
        this.F = (SimpleLinearItem) this.f3717c.findViewById(R.id.item_licenses_system_pager);
        if (getActivity() != null) {
            this.M = getActivity().getSupportFragmentManager();
        }
        this.F.setOnClickListener(new e());
        this.G = this.f3717c.findViewById(R.id.ll_memory_playback_system_pager);
        this.o = (ViewGroup) this.f3717c.findViewById(R.id.ll_title_memory_playback_system_pager);
        this.s = (BydSwitch) this.f3717c.findViewById(R.id.switch_memory_playback_system_pager);
        ((ImageView) this.f3717c.findViewById(R.id.iv_details_memory_playback_system_pager)).setOnClickListener(new f());
        this.r.setOnCheckedChangeListener(new g());
        this.s.setOnCheckedChangeListener(new h());
        this.t.setOnPositionChangedListener(new i());
        this.v.setOnClickListener(this);
        this.w.setOnClickListener(new j());
        this.y.setOnClickListener(new k());
        this.z.setOnClickListener(new l());
        this.A.setOnClickListener(this);
        this.B.setOnClickListener(new a());
        this.D.setOnClickListener(new b());
        this.E.setOnClickListener(new c());
        d dVar = null;
        String strA = b.b.g.p.c.a("ro.build.type", null);
        b.b.m.c.a.d(this.f3715a, "--initViews--versionType: " + strA);
        this.D.setVisibility(0);
        this.v.setVisibility(8);
        if (this.K == null) {
            this.K = new m(this, dVar);
        }
        EventBus.getDefault().register(this);
        e0();
        this.t.setClickTime(50);
    }

    public final void T() {
        this.s.setOnCheckedChangeListener(null);
        this.r.setOnCheckedChangeListener(null);
        this.t.setOnPositionChangedListener(null);
        this.v.setOnClickListener(null);
        this.w.setOnClickListener(null);
        this.y.setOnClickListener(null);
        this.z.setOnClickListener(null);
        this.A.setOnClickListener(null);
        this.B.setOnClickListener(null);
        this.D.setOnClickListener(null);
        this.E.setOnClickListener(null);
        EventBus.getDefault().unregister(this);
        if (getActivity() != null) {
            getActivity().unregisterReceiver(this.N);
        }
    }

    public final void U() {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        SimpleDialog.z(activity.getString(R.string.dialog_title_hint), activity.getString(R.string.hint_msg_memory_playback), activity.getString(R.string.dialog_btn_ok), "", 0).show(getActivity().getSupportFragmentManager(), "SimpleDialog");
    }

    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: V, reason: merged with bridge method [inline-methods] */
    public b.b.g.n.k.g newP() {
        return new b.b.g.n.k.g();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void W() {
        b.b.m.c.a.a(this.f3715a, "----onCurInputMethodSettingOperate---");
        if (getActivity() == null) {
            return;
        }
        ((b.b.g.n.k.g) mo7getP()).B();
    }

    public final void X() {
        b.b.m.c.a.a(this.f3715a, "----onDefInputMethodOperate---");
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        CustomViewDialog.y(4).show(activity.getSupportFragmentManager(), "CustomViewDialog");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void Y() {
        this.s.setChecked(((b.b.g.n.k.g) mo7getP()).o(), false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void Z() {
        boolean zT = ((b.b.g.n.k.g) mo7getP()).t();
        this.r.setChecked(zT, false);
        a0(zT);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void a0(boolean z) {
        b.b.m.c.a.a(this.f3715a, "----onTimeSyncSettingChanged--isChecked = " + z);
        ((b.b.g.n.k.g) mo7getP()).z(z);
        this.v.setInvalid(z);
        b.b.a.h.k.f(this.w, z);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void b0(boolean z) {
        b.b.m.c.a.a(this.f3715a, "----onTimeSystemChanged---is24H = " + z);
        this.H = z;
        ((b.b.g.n.k.g) mo7getP()).A(z);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final TimeZoneDialog c0() {
        String id = ((b.b.g.n.k.g) mo7getP()).n().getID();
        List<b.b.g.m.d.e> listR = ((b.b.g.n.k.g) mo7getP()).r();
        this.L = listR;
        String[] strArr = new String[listR.size()];
        String[] strArr2 = new String[this.L.size()];
        b.b.m.c.a.a(this.f3715a, "----onTimeZoneOperate---list.size = " + this.L.size() + "curZoneID:" + id);
        int i2 = 0;
        for (int i3 = 0; i3 < this.L.size(); i3++) {
            b.b.g.m.d.e eVar = this.L.get(i3);
            b.b.m.c.a.a(this.f3715a, "---onTimeZoneOperate--" + i3 + "--zone = " + eVar);
            if (id != null && id.equals(eVar.c())) {
                i2 = i3;
            }
            strArr[i3] = eVar.d();
            strArr2[i3] = eVar.b();
        }
        return TimeZoneDialog.newTimeZoneDialog(i2, strArr, strArr2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void d0(int i2) {
        if (this.L == null) {
            this.L = ((b.b.g.n.k.g) mo7getP()).r();
        }
        ((b.b.g.n.k.g) mo7getP()).v(this.L.get(i2).c());
        m mVar = this.K;
        if (mVar != null) {
            mVar.sendEmptyMessageDelayed(87, 500L);
        }
    }

    public final void e0() {
        if (getActivity() != null) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
            getActivity().registerReceiver(this.N, intentFilter);
        }
    }

    public final void f0() {
        String strA = b.b.g.r.f.a(this.v.getContext());
        b.b.m.c.a.a(this.f3715a, "----updateTimeView--timeText = " + strA);
        this.v.setRightText(strA);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void g0() {
        Calendar calendar = Calendar.getInstance();
        this.x.setText(b.b.g.p.l.a.b(calendar.getTimeZone(), calendar.getTime()));
        ((b.b.g.n.k.g) mo7getP()).l().b();
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return R.layout.pager_system;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.f3719e || b.b.m.g.c.a(200L)) {
            return;
        }
        FragmentManager fragmentManager = getFragmentManager();
        int id = view.getId();
        if (id == R.id.item_cur_input_setting_method_pager) {
            W();
            return;
        }
        if (id != R.id.item_time_system_pager) {
            return;
        }
        TimeSettingDialog timeSettingDialog = new TimeSettingDialog();
        timeSettingDialog.setOnOKClickedListener(this);
        if (fragmentManager != null) {
            timeSettingDialog.show(fragmentManager, "SystemTimeSettingDialog");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.systemsettings.system.TimeSettingDialog.OnOKClickedListener
    public void onDateSelected(BydDate bydDate) {
        ((b.b.g.n.k.g) mo7getP()).x(bydDate);
    }

    @Override // com.byd.mvp.mvp.XFragment, com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        b.b.m.f.d.d.c(getClass().getName());
        m mVar = this.K;
        if (mVar != null) {
            mVar.removeCallbacksAndMessages(null);
        }
        this.K = null;
        T();
        super.onDestroyView();
    }

    @Override // com.byd.mvp.mvp.XFragment, androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        m mVar = this.K;
        if (mVar != null) {
            mVar.removeCallbacksAndMessages(null);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(b.b.a.c.a aVar) {
        switch (aVar.h()) {
            case 230004:
                ((b.b.g.n.k.g) mo7getP()).w((BydLanguage) aVar.i());
                break;
            case 230005:
                ((b.b.g.n.k.g) mo7getP()).l().b();
                break;
            case 230011:
                d0(aVar.c());
                break;
        }
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void onLtrRudderR(boolean z) {
        if (z) {
            setViewGroupRtl(this.m, true);
            setViewGroupRtl(this.w, false);
            setViewGroupRtl(this.p, false);
            setViewGroupRtl(this.y, false);
            setViewGroupRtl(this.z, false);
            setViewGroupRtl(this.A, false);
            setViewGroupRtl(this.B, false);
            setViewGroupRtl(this.D, false);
            setViewGroupRtl(this.E, false);
            setViewGroupRtl(this.F, false);
        }
        setViewGroupRtl(this.q, true);
        setViewGroupRtl(this.n, false);
        setViewGroupRtl(this.G, true);
        setViewGroupRtl(this.o, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.mvp.mvp.XFragment, com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.y.setRightTextV2(((b.b.g.n.k.g) mo7getP()).m());
        int iQ = ((b.b.g.n.k.g) mo7getP()).q();
        this.H = iQ == 0;
        this.t.setPosition(iQ);
        Calendar calendar = Calendar.getInstance();
        this.x.setText(b.b.g.p.l.a.b(calendar.getTimeZone(), calendar.getTime()));
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void onRtlRudderL(boolean z) {
        if (z) {
            setViewGroupRtl(this.m, false);
            setViewGroupRtl(this.w, true);
            setViewGroupRtl(this.p, true);
            setViewGroupRtl(this.y, true);
            setViewGroupRtl(this.z, true);
            setViewGroupRtl(this.A, true);
            setViewGroupRtl(this.B, true);
            setViewGroupRtl(this.D, true);
            setViewGroupRtl(this.E, true);
            setViewGroupRtl(this.F, true);
        }
        setViewGroupRtl(this.q, false);
        setViewGroupRtl(this.n, true);
        setViewGroupRtl(this.G, false);
        setViewGroupRtl(this.o, true);
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void t(int i2) {
        b.b.m.c.a.a(this.f3715a, "----checkSubindexOnResume--subindex = " + i2);
        H(this.u, i2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.mvp.mvp.XFragment
    public void y(Context context) {
        Z();
        Calendar calendar = Calendar.getInstance();
        TimeZone timeZone = calendar.getTimeZone();
        if ("GMT".equals(timeZone.getID())) {
            ((b.b.g.n.k.g) mo7getP()).v("Asia/Shanghai");
            b.b.m.c.a.a(this.f3715a, "set default TimeZone");
        }
        this.x.setText(b.b.g.p.l.a.b(timeZone, calendar.getTime()));
        int iQ = ((b.b.g.n.k.g) mo7getP()).q();
        this.H = iQ == 0;
        this.t.setPosition(iQ);
        this.y.setRightTextV2(((b.b.g.n.k.g) mo7getP()).m());
        ((b.b.g.n.k.g) mo7getP()).l().b();
        this.A.setLeftText(context.getString(R.string.key_input_settings));
        Y();
    }
}
