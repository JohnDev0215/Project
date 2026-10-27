package com.byd.carsettings.ui.fragment.dilink;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import androidx.fragment.app.FragmentActivity;
import com.byd.carsettings.R;
import com.byd.carsettings.adapter.dilink.AuxiliaryAppListAdapter;
import com.byd.carsettings.ui.activity.AppListActivity;
import com.byd.carsettings.ui.activity.DiskStatisticsActivity;
import com.byd.carsettings.ui.activity.PermissionActivity;
import com.byd.carsettings.ui.widget.SimpleLinearItem;
import com.byd.mvp.mvp.XFragment;
import com.byd.mvp.ui.adapter.BtnType;
import com.byd.mvp.ui.adapter.BydAdapter;
import com.byd.systemsettings.system.SystemSecurityDialog;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class AppManageFragment extends XFragment<b.b.g.n.k.a> implements View.OnClickListener {
    public ViewGroup m;
    public ScrollView n;
    public SimpleLinearItem o;
    public SimpleLinearItem p;
    public SimpleLinearItem q;
    public SimpleLinearItem r;
    public SimpleLinearItem s;
    public SimpleLinearItem t;
    public SimpleLinearItem u;
    public SimpleLinearItem v;

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AppManageFragment.this.p.setClickable(true);
            AppManageFragment.this.q.setClickable(true);
            AppManageFragment.this.r.setClickable(true);
            AppManageFragment.this.u.setClickable(true);
            AppManageFragment.this.v.setClickable(true);
        }
    }

    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AppManageFragment.this.o.setClickable(true);
            AppManageFragment.this.q.setClickable(true);
            AppManageFragment.this.r.setClickable(true);
            AppManageFragment.this.u.setClickable(true);
            AppManageFragment.this.v.setClickable(true);
        }
    }

    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AppManageFragment.this.o.setClickable(true);
            AppManageFragment.this.p.setClickable(true);
            AppManageFragment.this.r.setClickable(true);
            AppManageFragment.this.u.setClickable(true);
            AppManageFragment.this.v.setClickable(true);
        }
    }

    public class d implements Runnable {
        public d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AppManageFragment.this.o.setClickable(true);
            AppManageFragment.this.p.setClickable(true);
            AppManageFragment.this.q.setClickable(true);
            AppManageFragment.this.u.setClickable(true);
            AppManageFragment.this.v.setClickable(true);
        }
    }

    public class e implements b.b.m.f.c.a.d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ List f3126a;

        public e(List list) {
            this.f3126a = list;
        }

        @Override // b.b.m.f.c.a.d
        public void onItemBtnClicked(int i, BtnType btnType) {
            AppManageFragment.this.b0((AccessibilityServiceInfo) this.f3126a.get(i));
        }
    }

    public class f implements BydAdapter.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ List f3128a;

        public f(List list) {
            this.f3128a = list;
        }

        @Override // com.byd.mvp.ui.adapter.BydAdapter.a
        public void a(int i, boolean z, BtnType btnType) {
            b.b.g.p.i.a.c(AppManageFragment.this.getContext(), ((AccessibilityServiceInfo) this.f3128a.get(i)).getComponentName(), z);
        }
    }

    public class g implements Runnable {
        public g() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AppManageFragment.this.o.setClickable(true);
            AppManageFragment.this.p.setClickable(true);
            AppManageFragment.this.q.setClickable(true);
            AppManageFragment.this.r.setClickable(true);
            AppManageFragment.this.v.setClickable(true);
        }
    }

    public class h implements Runnable {
        public h() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AppManageFragment.this.o.setClickable(true);
            AppManageFragment.this.p.setClickable(true);
            AppManageFragment.this.q.setClickable(true);
            AppManageFragment.this.r.setClickable(true);
            AppManageFragment.this.u.setClickable(true);
        }
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void A(Context context) {
        this.m = (ViewGroup) this.f3717c.findViewById(R.id.app_manage_parent);
        this.n = (ScrollView) this.f3717c.findViewById(R.id.scv_app_manage_pager);
        this.o = (SimpleLinearItem) this.f3717c.findViewById(R.id.item_app_app_manage_pager);
        this.p = (SimpleLinearItem) this.f3717c.findViewById(R.id.item_storage_app_manage_pager);
        this.q = (SimpleLinearItem) this.f3717c.findViewById(R.id.item_security_app_manage_pager);
        this.r = (SimpleLinearItem) this.f3717c.findViewById(R.id.item_permission_app_manage_pager);
        this.s = (SimpleLinearItem) this.f3717c.findViewById(R.id.item_privacy_manage_app_manage_pager);
        this.t = (SimpleLinearItem) this.f3717c.findViewById(R.id.item_accessibility_app_manage_pager);
        this.u = (SimpleLinearItem) this.f3717c.findViewById(R.id.item_disable_auto_start_app_manage_pager);
        this.v = (SimpleLinearItem) this.f3717c.findViewById(R.id.item_notification_app_manage_pager);
        this.o.setOnClickListener(this);
        this.p.setOnClickListener(this);
        this.q.setOnClickListener(this);
        this.r.setOnClickListener(this);
        this.s.setOnClickListener(this);
        this.t.setOnClickListener(this);
        this.u.setOnClickListener(this);
        this.v.setOnClickListener(this);
        this.s.setVisibility(8);
        this.t.setVisibility(8);
        this.u.setVisibility(0);
        this.v.setVisibility(0);
    }

    public final void R() {
        this.o.setOnClickListener(null);
        this.p.setOnClickListener(null);
        this.q.setOnClickListener(null);
        this.r.setOnClickListener(null);
        this.s.setOnClickListener(null);
        this.t.setOnClickListener(null);
        this.u.setOnClickListener(null);
        this.v.setOnClickListener(null);
    }

    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: S, reason: merged with bridge method [inline-methods] */
    public b.b.g.n.k.a newP() {
        return new b.b.g.n.k.a();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void T() {
        b.b.m.c.a.a(this.f3715a, "----onAccessibilityOperate---");
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        List<AccessibilityServiceInfo> listC = ((b.b.g.n.k.a) mo7getP()).c();
        b.b.m.c.a.a(this.f3715a, "----onAccessibilityOperate---subAppInfos = " + listC);
        AuxiliaryAppListAdapter auxiliaryAppListAdapter = new AuxiliaryAppListAdapter(getContext(), listC);
        auxiliaryAppListAdapter.setOnItemBtnClickedCallback(new e(listC));
        auxiliaryAppListAdapter.setOnItemBtnCheckedCallback(new f(listC));
        b.b.g.q.d.u.a.q(activity, auxiliaryAppListAdapter);
    }

    public final void U() {
        this.p.setClickable(false);
        this.q.setClickable(false);
        this.r.setClickable(false);
        this.u.setClickable(false);
        this.v.setClickable(false);
        b.b.m.c.a.a(this.f3715a, "----onAppOperate---");
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        activity.startActivity(new Intent(activity, (Class<?>) AppListActivity.class));
        b.b.a.f.h.a().g(new a(), 500L);
    }

    public final void V() {
        b.b.m.c.a.a(this.f3715a, "----onDisableAutoStartOperate---");
        this.o.setClickable(false);
        this.p.setClickable(false);
        this.q.setClickable(false);
        this.r.setClickable(false);
        this.v.setClickable(false);
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClassName("com.byd.appstartmanagement", "com.byd.appstartmanagement.StartupAppManageActivity");
        activity.startActivity(intent);
        b.b.a.f.h.a().g(new g(), 500L);
    }

    public final void W() {
        b.b.m.c.a.a(this.f3715a, "----onNotificationOperate---");
        this.o.setClickable(false);
        this.p.setClickable(false);
        this.q.setClickable(false);
        this.r.setClickable(false);
        this.u.setClickable(false);
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        b.b.g.q.d.u.a.r(activity);
        b.b.a.f.h.a().g(new h(), 500L);
    }

    public final void X() {
        b.b.m.c.a.a(this.f3715a, "----onPermissionOperate---");
        this.o.setClickable(false);
        this.p.setClickable(false);
        this.q.setClickable(false);
        this.u.setClickable(false);
        this.v.setClickable(false);
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        activity.startActivity(new Intent(activity, (Class<?>) PermissionActivity.class));
        b.b.a.f.h.a().g(new d(), 500L);
    }

    public final void Y() {
        b.b.m.c.a.a(this.f3715a, "----onPrivacyManageOperate---");
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        b.b.i.d.E(activity);
    }

    public final void Z() {
        b.b.m.c.a.a(this.f3715a, "----onSecurityOperate---");
        this.o.setClickable(false);
        this.p.setClickable(false);
        this.r.setClickable(false);
        this.u.setClickable(false);
        this.v.setClickable(false);
        if (getActivity() == null) {
            return;
        }
        new SystemSecurityDialog().show(getFragmentManager(), "SystemSecurityDialog");
        b.b.a.f.h.a().g(new c(), 500L);
    }

    public final void a0() {
        b.b.m.c.a.a(this.f3715a, "----onStorageOperate---");
        this.o.setClickable(false);
        this.q.setClickable(false);
        this.r.setClickable(false);
        this.u.setClickable(false);
        this.v.setClickable(false);
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        activity.startActivity(new Intent(activity, (Class<?>) DiskStatisticsActivity.class));
        b.b.a.f.h.a().g(new b(), 500L);
    }

    public final void b0(AccessibilityServiceInfo accessibilityServiceInfo) {
        b.b.m.c.a.a(this.f3715a, "----showAccessibilityDetails---");
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        String string = activity.getString(R.string.title_accessibility_dialog);
        String strLoadDescription = accessibilityServiceInfo.loadDescription(activity.getPackageManager());
        if (TextUtils.isEmpty(strLoadDescription)) {
            strLoadDescription = getString(R.string.accessibility_service_default_description);
        }
        b.b.g.q.d.u.b.m(activity, string, 0, 0, strLoadDescription);
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return R.layout.pager_app_manage;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.item_accessibility_app_manage_pager /* 2131297112 */:
                T();
                break;
            case R.id.item_app_app_manage_pager /* 2131297113 */:
                U();
                break;
            case R.id.item_disable_auto_start_app_manage_pager /* 2131297121 */:
                V();
                break;
            case R.id.item_notification_app_manage_pager /* 2131297131 */:
                W();
                break;
            case R.id.item_permission_app_manage_pager /* 2131297132 */:
                X();
                break;
            case R.id.item_privacy_manage_app_manage_pager /* 2131297133 */:
                Y();
                break;
            case R.id.item_security_app_manage_pager /* 2131297137 */:
                if (!b.b.m.g.c.c(view, 2000L)) {
                    Z();
                } else {
                    b.b.m.c.a.a(this.f3715a, "选项重复点击 return");
                }
                break;
            case R.id.item_storage_app_manage_pager /* 2131297147 */:
                a0();
                break;
        }
    }

    @Override // com.byd.mvp.mvp.XFragment, com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        b.b.m.f.d.d.c(getClass().getName());
        R();
        super.onDestroyView();
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void onLtrRudderR(boolean z) {
        if (z) {
            setViewGroupRtl(this.m, true);
            setViewGroupRtl(this.o, false);
            setViewGroupRtl(this.p, false);
            setViewGroupRtl(this.q, false);
            setViewGroupRtl(this.r, false);
            setViewGroupRtl(this.s, false);
            setViewGroupRtl(this.t, false);
            setViewGroupRtl(this.u, false);
            setViewGroupRtl(this.v, false);
        }
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void onRtlRudderL(boolean z) {
        if (z) {
            setViewGroupRtl(this.m, false);
            setViewGroupRtl(this.o, true);
            setViewGroupRtl(this.p, true);
            setViewGroupRtl(this.q, true);
            setViewGroupRtl(this.r, true);
            setViewGroupRtl(this.s, true);
            setViewGroupRtl(this.t, true);
            setViewGroupRtl(this.u, true);
            setViewGroupRtl(this.v, true);
        }
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void t(int i) {
        b.b.m.c.a.a(this.f3715a, "----checkSubindexOnResume--subindex = " + i);
        H(this.n, i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.mvp.mvp.XFragment
    public void y(Context context) {
        ((b.b.g.n.k.a) mo7getP()).d();
    }
}
