package com.byd.carsettings.ui.fragment.carsettings;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.annotation.Nullable;
import b.b.g.n.h.l;
import b.b.g.n.q.m;
import b.b.g.q.d.i;
import b.b.g.q.d.k;
import b.b.g.q.d.q;
import b.b.g.r.e;
import b.b.g.r.z;
import b.b.o.a.b.d;
import com.byd.base.view.BydSwitch;
import com.byd.carsettings.R;
import com.byd.carsettings.RearViewMirrorActivity;
import com.byd.mvp.mvp.XFragment;
import com.bydux.api.common.tools.log.BydLogHelper;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes2.dex */
public class RearViewMirrorFragment extends XFragment<l> implements b.b.o.b.a.a, View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    public LinearLayout m;
    public RelativeLayout n;
    public RelativeLayout o;
    public RelativeLayout p;
    public BydSwitch q;
    public ImageView r;
    public ImageView s;
    public ImageView t;
    public int u = 36;
    public Context v;
    public b.b.o.a.b.a w;
    public LinearLayout x;
    public i y;
    public Dialog z;

    public class a implements q.b {
        public a() {
        }

        @Override // b.b.g.q.d.q.b
        public void a() {
            m.p0().L1(1);
            RearViewMirrorFragment.this.z.dismiss();
        }
    }

    public class b implements q.a {
        public b() {
        }

        @Override // b.b.g.q.d.q.a
        public void a() {
            RearViewMirrorFragment.this.z.dismiss();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.mvp.mvp.XFragment
    public void A(Context context) {
        this.v = context;
        M(((l) mo7getP()).f());
        L(((l) mo7getP()).c());
        T();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void L(int i) {
        if (i >= 1) {
            this.x = (LinearLayout) this.f3717c.findViewById(R.id.rear_view_inner_container);
            this.o = (RelativeLayout) this.f3717c.findViewById(R.id.rear_view_screen_turn_container);
            this.r = (ImageView) this.f3717c.findViewById(R.id.rear_view_screen_turn_iv);
            this.p = (RelativeLayout) this.f3717c.findViewById(R.id.rear_view_brightness_view_reset_container);
            this.s = (ImageView) this.f3717c.findViewById(R.id.rear_view_brightness_view_reset_iv);
            b.b.a.h.l.K(true, this.x, this.o, this.p);
            b.b.a.h.l.F(this, this.s, this.r);
            if (i == 1) {
                this.n = (RelativeLayout) this.f3717c.findViewById(R.id.rear_view_inner_sw_container);
                this.q = (BydSwitch) this.f3717c.findViewById(R.id.rear_view_inner_sw);
                b.b.a.h.l.K(true, this.n);
                b.b.a.h.l.e(this.n, this.q, ((l) mo7getP()).d() == 2, this);
                b.b.a.h.l.K(((l) mo7getP()).d() == 2, this.o, this.p);
            }
        }
    }

    public final void M(boolean z) {
        LinearLayout linearLayout = (LinearLayout) this.f3717c.findViewById(R.id.rear_view_out_container);
        this.m = linearLayout;
        b.b.a.h.l.K(z, linearLayout);
    }

    public int N(int i, int i2) {
        if (this.w == null) {
            this.w = d.a("mirror");
        }
        return this.w.a(i, i2);
    }

    public final i O(String str) {
        if (this.y == null) {
            this.y = new i(this.v);
        }
        this.y.x(str);
        return this.y;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean P() {
        return ((l) mo7getP()).e() == 2;
    }

    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: Q, reason: merged with bridge method [inline-methods] */
    public l newP() {
        return new l();
    }

    public void R(Context context, int i, boolean z) {
        if (z) {
            b.b.a.h.l.l(this.t, i, context, true, false);
        }
    }

    public void S(Context context, int i, boolean z, boolean z2) {
        if (z) {
            b.b.a.h.l.l(this.t, i, context, true, false);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void T() {
        this.t = (ImageView) this.f3717c.findViewById(R.id.rear_view_image);
        int iA = b.b.o.a.c.a.a(getClass().getSimpleName());
        this.u = iA;
        S(this.v, N(iA, ((l) mo7getP()).d() == 2 ? 1 : 2), true, false);
    }

    public final void U(String str) {
        if ("rear_view_mirror_inner_rest_dialog".equals(str)) {
            V();
        }
    }

    public final void V() {
        this.z = O("rear_view_mirror_inner_rest_dialog").B(b.b.a.i.a.f531a ? R.layout.carsetting_right_dialog_withtime : R.layout.carsetting_dialog_withtime, R.string.vehiclesettings_title_inner_rear_view_mirror, R.string.vehiclesettings_rear_view_mirror_reset_brightness_view_dialog_content, -1, -1, R.string.vehiclesettings_button_confirm, new a(), R.string.vehiclesettings_button_cancel, new b());
    }

    @Override // b.b.o.b.a.a
    public int c() {
        return R.layout.pager_rtl_rear_view_mirror_layout;
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return b.b.o.b.b.a.a(this);
    }

    @Override // b.b.o.b.a.a
    public int h() {
        return R.layout.pager_rear_view_mirror_layout;
    }

    @Override // b.b.o.b.a.a
    public int m() {
        return R.layout.pager_right_rear_view_mirror_layout;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        for (String str : k.b()) {
            if (str.startsWith("rear_view_mirror")) {
                U(str);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (compoundButton.getId() == R.id.rear_view_inner_sw && b.b.a.h.l.a(this.q)) {
            ((l) mo7getP()).g(z ? 1 : 2);
            e.i(300000);
            R(this.v, N(this.u, z ? 1 : 2), true);
            b.b.a.h.l.K(z, this.o, this.p);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() != R.id.rear_view_screen_turn_iv) {
            if (view.getId() == R.id.rear_view_brightness_view_reset_iv) {
                V();
            }
        } else {
            if (P()) {
                z.b(getContext(), R.string.vehiclesettings_rear_view_mirror_instrument_turning_try_again);
                return;
            }
            Intent intent = new Intent();
            intent.setClass(getActivity(), RearViewMirrorActivity.class);
            e.j(300002, 0);
            startActivity(intent);
        }
    }

    @Override // com.byd.mvp.mvp.XFragment, com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override // com.byd.mvp.mvp.XFragment, com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(b.b.a.c.a aVar) {
        if (aVar.h() != 300000) {
            if (aVar.h() == 300003 && m.p0().V0() == 1) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), RearViewMirrorActivity.class);
                startActivity(intent);
                return;
            }
            return;
        }
        BydLogHelper.logD("REAR_VIEW_INNER_TOGGLE_STATE_CHANGED :" + aVar.c());
        int iC = aVar.c();
        if (e.e(aVar) && (iC = ((l) mo7getP()).d()) == 0) {
            iC = 1;
        }
        if (iC == 1 || iC == 2) {
            b.b.a.h.l.f(this.q, iC == 2);
            R(this.v, N(this.u, iC == 2 ? 1 : 2), true);
            b.b.a.h.l.K(iC == 2, this.o, this.p);
        }
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void y(Context context) {
        EventBus.getDefault().register(this);
    }
}
