package com.byd.carsettings.ui.fragment.dilink;

import android.content.Context;
import android.os.SystemProperties;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import b.b.g.n.k.h;
import b.b.g.r.g;
import b.b.g.r.k;
import b.b.g.r.x;
import b.b.m.f.d.d;
import b.b.m.g.c;
import com.byd.carsettings.R;
import com.byd.carsettings.model.dilink.BydDate;
import com.byd.carsettings.ui.dialog.CustomViewDialog;
import com.byd.carsettings.ui.dialog.SimpleDialog;
import com.byd.mvp.common.CarModel$Brand;
import com.byd.mvp.mvp.XFragment;
import com.byd.systemsettings.system.PrivacyPolicyDialog;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes2.dex */
public class VersionManageFragment extends XFragment<h> implements View.OnClickListener {
    public TextView A;
    public TextView B;
    public ViewGroup m;
    public ViewGroup n;
    public ImageView o;
    public View p;
    public View q;
    public TextView r;
    public TextView s;
    public TextView t;
    public TextView u;
    public TextView v;
    public TextView w;
    public TextView x;
    public TextView y;
    public TextView z;

    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ boolean f3200a;

        public a(boolean z) {
            this.f3200a = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            VersionManageFragment.this.o.setVisibility(this.f3200a ? 0 : 8);
        }
    }

    public class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ long f3202a;

        public b(long j) {
            this.f3202a = j;
        }

        @Override // java.lang.Runnable
        public void run() {
            VersionManageFragment.this.Z(this.f3202a);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.mvp.mvp.XFragment
    public void A(Context context) {
        ((h) mo7getP()).l();
        this.m = (ViewGroup) this.f3717c.findViewById(R.id.version_parent);
        this.n = (ViewGroup) this.f3717c.findViewById(R.id.version_sub_parent);
        this.o = (ImageView) this.f3717c.findViewById(R.id.iv_tip);
        this.p = this.f3717c.findViewById(R.id.rl_upgrade_order);
        this.r = (TextView) this.f3717c.findViewById(R.id.tv_order);
        this.q = this.f3717c.findViewById(R.id.ly_media);
        this.s = (TextView) this.f3717c.findViewById(R.id.tv_cancel);
        this.u = (TextView) this.f3717c.findViewById(R.id.tv_upgrade);
        this.v = (TextView) this.f3717c.findViewById(R.id.tv_media_version_details);
        this.w = (TextView) this.f3717c.findViewById(R.id.tv_recover_setting);
        this.x = (TextView) this.f3717c.findViewById(R.id.tv_recover);
        this.y = (TextView) this.f3717c.findViewById(R.id.tv_title_car_version);
        this.z = (TextView) this.f3717c.findViewById(R.id.tv_media);
        this.A = (TextView) this.f3717c.findViewById(R.id.tv_setting_title);
        this.B = (TextView) this.f3717c.findViewById(R.id.tv_recover_title);
        this.t = (TextView) this.f3717c.findViewById(R.id.tv_version_os);
        this.s.setOnClickListener(this);
        this.u.setOnClickListener(this);
        this.v.setOnClickListener(this);
        this.w.setOnClickListener(this);
        this.x.setOnClickListener(this);
        this.y.setOnClickListener(this);
        this.z.setOnClickListener(this);
        this.A.setOnClickListener(this);
        this.B.setOnClickListener(this);
        EventBus.getDefault().register(this);
        x.f(this.A);
        x.f(this.B);
    }

    public final void M() {
        this.s.setOnClickListener(null);
        this.u.setOnClickListener(null);
        this.v.setOnClickListener(null);
        this.w.setOnClickListener(null);
        this.x.setOnClickListener(null);
        this.y.setOnClickListener(null);
        this.z.setOnClickListener(null);
        this.A.setOnClickListener(null);
        this.B.setOnClickListener(null);
        EventBus.getDefault().unregister(this);
    }

    public final String N(Context context, BydDate bydDate) {
        return bydDate.getHour() > 12 ? context.getString(R.string.clock_pm) : context.getString(R.string.clock_am);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final String O(Context context, BydDate bydDate, int i) {
        String string;
        if (i == 0) {
            string = context.getString(R.string.today_text);
        } else if (i == 1) {
            string = context.getString(R.string.tomorrow_text);
        } else if (i == 2) {
            string = context.getString(R.string.the_day_after_tomorrow_text);
        } else if (i > 2) {
            string = String.format(context.getString(R.string.days_later_text), i + "");
        } else {
            string = null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        int hour = bydDate.getHour();
        int minute = bydDate.getMinute();
        if (((h) mo7getP()).m()) {
            stringBuffer.append(string);
            if (g.g(context)) {
                stringBuffer.append(b0(hour));
                stringBuffer.append(":");
                stringBuffer.append(b0(minute));
            } else {
                stringBuffer.append(N(context, bydDate));
                if (hour > 12) {
                    hour -= 12;
                }
                stringBuffer.append(b0(hour));
                stringBuffer.append(":");
                stringBuffer.append(b0(minute));
            }
        } else {
            if (g.g(context)) {
                stringBuffer.append(b0(hour));
                stringBuffer.append(":");
                stringBuffer.append(b0(minute));
            } else {
                if (hour > 12) {
                    hour -= 12;
                }
                stringBuffer.append(b0(hour));
                stringBuffer.append(":");
                stringBuffer.append(b0(minute));
                stringBuffer.append(" ");
                stringBuffer.append(N(context, bydDate));
            }
            if (string != null) {
                if (i <= 2) {
                    stringBuffer.append(" ");
                }
                stringBuffer.append(string);
            }
        }
        return stringBuffer.toString();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final String P(Context context, long j) {
        return ((h) mo7getP()).m() ? !g.g(context) ? g.e(j, "yyyy-MM-dd a hh:mm") : g.e(j, "yyyy-MM-dd HH:mm") : !g.g(context) ? g.e(j, "hh:mm a, MMM.d, yyyy") : g.e(j, "HH:mm, MMM.d, yyyy");
    }

    public final String Q(Context context, BydDate bydDate, BydDate bydDate2, long j) {
        if (bydDate.getMonth() == bydDate2.getMonth()) {
            int day = bydDate.getDay() - bydDate2.getDay();
            b.b.m.c.a.a(this.f3715a, "--getOrderTimeText--0--deltaDay: " + day);
            return day <= 7 ? O(context, bydDate, day) : P(context, j);
        }
        int day2 = (bydDate.getDay() + g.f(bydDate.getYear(), bydDate.getMonth())) - bydDate2.getDay();
        b.b.m.c.a.a(this.f3715a, "--getOrderTimeText--1--deltaDay: " + day2);
        return day2 <= 7 ? O(context, bydDate, day2) : P(context, j);
    }

    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: R, reason: merged with bridge method [inline-methods] */
    public h newP() {
        return new h();
    }

    public final void S() {
        b.b.m.c.a.a(this.f3715a, "--onFactorySettingsRecovery--");
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        SimpleDialog.z(activity.getString(R.string.dialog_title_hint), activity.getString(R.string.msg_factory_settings_recovery), activity.getString(R.string.dialog_btn_ok), getString(R.string.dialog_btn_cancel), 107).show(getFragmentManager(), "SimpleDialog");
    }

    public final void T() {
        b.b.m.c.a.a(this.f3715a, "--onMediaVersionDetailsOperated--");
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        CustomViewDialog.y(9).show(activity.getSupportFragmentManager(), "CustomViewDialog");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void U() {
        b.b.m.c.a.a(this.f3715a, "--onOtaOperated--");
        ((h) mo7getP()).n();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void V() {
        b.b.m.c.a.a(this.f3715a, "--onOtaUpgradeCancel--");
        ((h) mo7getP()).g();
    }

    public final void W() {
        b.b.m.c.a.a(this.f3715a, "--onSettingItemsRecovery--");
        if (getActivity() == null) {
            return;
        }
        CustomViewDialog.y(2).show(getActivity().getSupportFragmentManager(), "CustomViewDialog");
    }

    public void X(boolean z) {
        b.b.m.c.a.a(this.f3715a, "--onUpdateFlag--flag = " + z);
        if (getActivity() == null) {
            return;
        }
        getActivity().runOnUiThread(new a(z));
    }

    public void Y(long j) {
        b.b.m.c.a.a(this.f3715a, "--onUpdateTime--time = " + j);
        if (getActivity() == null) {
            return;
        }
        getActivity().runOnUiThread(new b(j));
    }

    public final void Z(long j) {
        boolean z = j > System.currentTimeMillis();
        this.p.setVisibility(z ? 0 : 8);
        if (z) {
            Context context = this.r.getContext();
            BydDate bydDateB = g.b();
            String strE = g.e(j, "yyyy-MM-dd HH:mm:ss");
            BydDate bydDateA = g.a(strE);
            b.b.m.c.a.a(this.f3715a, "--showOrderText--date: " + bydDateB);
            b.b.m.c.a.a(this.f3715a, "--showOrderText--od: " + strE);
            b.b.m.c.a.a(this.f3715a, "--showOrderText--orderDate: " + bydDateA);
            String strQ = Q(context, bydDateA, bydDateB, j);
            b.b.m.c.a.a(this.f3715a, "--showOrderText--textOrderTime: " + strQ);
            this.r.setText(String.format(context.getString(R.string.version_order), strQ));
        }
    }

    public final void a0() {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        b.b.g.q.d.u.b.p(activity, activity.getString(R.string.msg_recovering_recovery));
    }

    public final String b0(int i) {
        if (i >= 10) {
            return String.valueOf(i);
        }
        return "0" + i;
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return R.layout.pager_version_manage;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel /* 2131298411 */:
                if (!c.c(view, 200L)) {
                    V();
                    break;
                }
                break;
            case R.id.tv_media /* 2131298552 */:
                if (c.b(view, 5000L, 10)) {
                    b.b.m.g.g.g(getActivity(), "byd_pad://secretcode:9520/mainactivity");
                    break;
                }
                break;
            case R.id.tv_media_version_details /* 2131298556 */:
                if (!c.c(view, 200L)) {
                    T();
                    break;
                }
                break;
            case R.id.tv_recover /* 2131298686 */:
                if (!c.c(view, 200L)) {
                    S();
                    break;
                }
                break;
            case R.id.tv_recover_setting /* 2131298687 */:
                if (!c.c(view, 200L)) {
                    W();
                    break;
                }
                break;
            case R.id.tv_recover_title /* 2131298688 */:
                if (c.b(view, 5000L, 10)) {
                    b.b.m.g.g.g(getActivity(), "byd_pad://secretcode:9521/adbactivity");
                    break;
                }
                break;
            case R.id.tv_setting_title /* 2131298727 */:
                if (c.b(view, 5000L, 10)) {
                    b.b.m.g.g.g(getActivity(), "byd_pad://secretcode:9522/canactivity");
                    break;
                }
                break;
            case R.id.tv_title_car_version /* 2131298769 */:
                if (c.b(view, 5000L, 10)) {
                    b.b.m.g.g.g(getActivity(), "byd_pad://engineer_mode:9523/mainActivity");
                    break;
                }
                break;
            case R.id.tv_upgrade /* 2131298824 */:
                if (!c.c(view, 200L)) {
                    int i = SystemProperties.getInt(PrivacyPolicyDialog.SYS_PRIVACY_STATUS, 3);
                    CarModel$Brand carModel$Brand = k.f1817c;
                    String string = getString(R.string.nfc_brand_byd);
                    if (carModel$Brand == CarModel$Brand.DENZA) {
                        string = getString(R.string.nfc_brand_denz);
                    }
                    if (i == 1) {
                        U();
                    } else {
                        SimpleDialog.z(getResources().getString(R.string.dialog_title_hint), getResources().getString(R.string.privacy_not_allow_tips_2, string), getResources().getString(R.string.dialog_btn_ok), "", 0).show(getActivity().getSupportFragmentManager(), "SimpleDialog");
                    }
                    break;
                }
                break;
        }
    }

    @Override // com.byd.mvp.mvp.XFragment, com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        d.c(getClass().getName());
        M();
        super.onDestroyView();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(b.b.a.c.a aVar) {
        if (aVar.h() == 107) {
            b.b.m.c.a.a(this.f3715a, ", onOKClicked--");
            a0();
            ((h) mo7getP()).o();
        }
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void onLtrRudderR(boolean z) {
        if (z) {
            setViewGroupRtl(this.m, true);
            setViewGroupRtl(this.n, false);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.mvp.mvp.XFragment, com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        long jI = ((h) mo7getP()).i();
        b.b.m.c.a.a(this.f3715a, "--onResume--orderTime: " + jI);
        Z(jI);
        ((h) mo7getP()).q();
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void onRtlRudderL(boolean z) {
        if (z) {
            setViewGroupRtl(this.m, false);
            setViewGroupRtl(this.n, true);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.mvp.mvp.XFragment
    public void y(Context context) {
        this.t.setText(((h) mo7getP()).h());
        this.o.setVisibility(((h) mo7getP()).j() ? 0 : 8);
    }
}
