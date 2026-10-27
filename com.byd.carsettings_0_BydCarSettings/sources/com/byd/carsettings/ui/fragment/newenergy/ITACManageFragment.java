package com.byd.carsettings.ui.fragment.newenergy;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import b.b.a.h.k;
import b.b.g.o.f;
import b.b.g.q.d.l;
import b.b.g.q.d.m;
import b.b.g.r.e;
import b.b.g.r.n;
import b.b.g.r.v;
import com.byd.base.view.BydSwitch;
import com.byd.carsettings.BydApp;
import com.byd.carsettings.R;
import com.byd.mvp.mvp.XFragment;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ITACManageFragment extends XFragment<b.b.g.n.n.d> implements View.OnClickListener, BydSwitch.a, b.b.o.b.a.a {
    public static final String m = ITACManageFragment.class.getSimpleName();
    public static int n;
    public final String o = "ITACManageFragment_label_function_hint";
    public final String p = "ITACManageFragment_label_terrain_hint";
    public final String q = "ITACManageFragment_label_rescue_hint";
    public final String r = "ITACManageFragment_label_drift_hint";
    public int[] s = {R.id.tv_mode_off, R.id.tv_mode_rescue, R.id.tv_mode_terrain, R.id.tv_mode_drift};
    public BydSwitch t;
    public ImageView u;
    public CountDownTimer v;
    public LinearLayout w;

    public class a implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f3256a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ CheckBox f3257b;

        public a(int i, CheckBox checkBox) {
            this.f3256a = i;
            this.f3257b = checkBox;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ((b.b.g.n.n.d) ITACManageFragment.this.mo7getP()).m(this.f3256a);
            int i = this.f3256a;
            if (i == 2) {
                v.d().g("KEY_NEWENERGY_ITAC_AVM_RESCUE", this.f3257b.isChecked());
            } else if (i == 4) {
                v.d().g("KEY_NEWENERGY_ITAC_AVM_DRIFT", this.f3257b.isChecked());
            } else if (i == 3) {
                v.d().g("KEY_NEWENERGY_ITAC_AVM_TERRAIN", this.f3257b.isChecked());
            }
        }
    }

    public class b implements View.OnClickListener {
        public b() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ITACManageFragment.this.v.cancel();
            ITACManageFragment.this.v = null;
            ITACManageFragment iTACManageFragment = ITACManageFragment.this;
            iTACManageFragment.Q(((b.b.g.n.n.d) iTACManageFragment.mo7getP()).e(), false);
        }
    }

    public class c implements View.OnClickListener {
        public c() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (e.c(150028)) {
                return;
            }
            ITACManageFragment iTACManageFragment = ITACManageFragment.this;
            iTACManageFragment.Q(((b.b.g.n.n.d) iTACManageFragment.mo7getP()).e(), false);
        }
    }

    public class d extends CountDownTimer {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ l f3261a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String f3262b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public d(long j, long j2, l lVar, String str) {
            super(j, j2);
            this.f3261a = lVar;
            this.f3262b = str;
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            BydLogHelper.logE("----CountDownTimer----onFinish");
            l lVar = this.f3261a;
            if (lVar != null) {
                lVar.t(this.f3262b);
                this.f3261a.s(true);
            }
            int unused = ITACManageFragment.n = 0;
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
            l lVar;
            StringBuilder sb = new StringBuilder();
            sb.append(j);
            sb.append("----CountDownTimer----");
            float f2 = j / 1000.0f;
            sb.append(Math.round(f2));
            BydLogHelper.logE(sb.toString());
            if (Math.round(f2) != 0 && (lVar = this.f3261a) != null) {
                lVar.t(this.f3262b + "(" + Math.round(f2) + "s)");
            }
            int unused = ITACManageFragment.n = Math.round(f2);
        }
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void A(Context context) {
        this.w = (LinearLayout) this.f3717c.findViewById(R.id.ll_itac);
        this.t = (BydSwitch) this.f3717c.findViewById(R.id.switch_itac);
        this.u = (ImageView) this.f3717c.findViewById(R.id.iv_itac_result);
    }

    public final void N() {
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.byd.avm", "com.byd.avm.MainActivity"));
            intent.setFlags(337641472);
            List<ResolveInfo> listQueryIntentActivities = BydApp.getContext().getPackageManager().queryIntentActivities(intent, 65536);
            if (listQueryIntentActivities == null) {
                BydLogHelper.logE(m, "[iTAC]----[doJumpApp]----AVM:false");
            } else if (listQueryIntentActivities.size() > 0) {
                startActivity(intent);
            } else {
                BydLogHelper.logE(m, "[iTAC]----[doJumpApp]----avm is not install");
            }
        } catch (Exception e2) {
            BydLogHelper.logE(m, e2.getMessage());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void O(int i) {
        int i2 = 0;
        int i3 = 0;
        while (true) {
            boolean z = true;
            if (i3 >= this.s.length) {
                break;
            }
            TextView textView = (TextView) this.f3717c.findViewById(this.s[i3]);
            if (i != this.s[i3]) {
                z = false;
            }
            textView.setSelected(z);
            i3++;
        }
        if (i == R.id.tv_mode_rescue) {
            V(2, 10);
            i2 = 1;
        } else if (i == R.id.tv_mode_terrain) {
            V(3, 10);
        } else if (i == R.id.tv_mode_drift) {
            V(4, 10);
            i2 = 2;
        } else {
            ((b.b.g.n.n.d) mo7getP()).m(1);
            i2 = 3;
        }
        this.u.setImageDrawable(ContextCompat.getDrawable(getContext(), n.f(b.b.g.n.q.b.c().a(), i2)));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.base.view.BydSwitch.a
    public void OnBydSwitchTouch(View view, MotionEvent motionEvent, boolean z) {
        if (view.isEnabled()) {
            ((BydSwitch) view).setChecked(z, false);
            f.d(view, 0);
            if (view.getId() != R.id.switch_itac) {
                return;
            }
            ((b.b.g.n.n.d) mo7getP()).n(z ? 1 : 2);
        }
    }

    public void P(int i) {
        if (i == 0) {
            k.e(this.w);
            this.t.setEnabled(false);
            this.f3717c.findViewById(R.id.iv_itac_hint).setEnabled(false);
        } else {
            k.h(this.w);
            this.t.setEnabled(true);
            this.f3717c.findViewById(R.id.iv_itac_hint).setEnabled(true);
        }
    }

    public void Q(int i, boolean z) {
        int i2 = 3;
        try {
            if (i != 0 && i != 3 && i != 4 && i != 1) {
                BydLogHelper.logE(m, "[iTAC]----[freshiTACMode]----mode:" + i);
                return;
            }
            boolean zB = false;
            ((TextView) this.f3717c.findViewById(this.s[0])).setSelected(i == 0);
            ((TextView) this.f3717c.findViewById(this.s[1])).setSelected(i == 3);
            ((TextView) this.f3717c.findViewById(this.s[2])).setSelected(i == 4);
            ((TextView) this.f3717c.findViewById(this.s[3])).setSelected(i == 1);
            if (i == 3) {
                zB = v.d().b("KEY_NEWENERGY_ITAC_AVM_RESCUE", false);
                i2 = 1;
            } else if (i == 4) {
                i2 = 0;
                zB = v.d().b("KEY_NEWENERGY_ITAC_AVM_TERRAIN", false);
            } else if (i == 1) {
                zB = v.d().b("KEY_NEWENERGY_ITAC_AVM_DRIFT", false);
                i2 = 2;
            }
            this.u.setImageDrawable(ContextCompat.getDrawable(getContext(), n.f(b.b.g.n.q.b.c().a(), i2)));
            if (z && zB) {
                N();
            }
        } catch (Exception e2) {
            BydLogHelper.logE(m, e2.getMessage());
        }
    }

    public void R(int i) {
        this.t.setChecked(i == 0, false);
    }

    public final void S(boolean z) {
        this.f3717c.findViewById(R.id.iv_itac_hint).setOnClickListener(z ? this : null);
        this.f3717c.findViewById(R.id.tv_mode_off).setOnClickListener(z ? this : null);
        this.f3717c.findViewById(R.id.tv_mode_rescue).setOnClickListener(z ? this : null);
        this.f3717c.findViewById(R.id.tv_mode_terrain).setOnClickListener(z ? this : null);
        this.f3717c.findViewById(R.id.tv_mode_drift).setOnClickListener(z ? this : null);
        this.w.setOnClickListener(z ? this : null);
        this.t.setOnInterceptTouchListener(z ? this : null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void T() {
        this.w.setVisibility(((b.b.g.n.n.d) mo7getP()).l() ? 0 : 8);
        this.f3717c.findViewById(R.id.ll_itac_mode).setVisibility(((b.b.g.n.n.d) mo7getP()).h() ? 0 : 8);
        if (!((b.b.g.n.n.d) mo7getP()).h()) {
            this.u.setImageDrawable(ContextCompat.getDrawable(getContext(), n.f(b.b.g.n.q.b.c().a(), 3)));
        }
        this.f3717c.findViewById(R.id.tv_mode_drift).setVisibility(((b.b.g.n.n.d) mo7getP()).j() ? 0 : 8);
        this.f3717c.findViewById(R.id.tv_mode_space).setVisibility(((b.b.g.n.n.d) mo7getP()).j() ? 8 : 4);
    }

    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: U, reason: merged with bridge method [inline-methods] */
    public b.b.g.n.n.d newP() {
        return new b.b.g.n.n.d();
    }

    public final void V(int i, int i2) {
        String str;
        String str2;
        String str3;
        String string;
        String str4;
        String string2;
        try {
            CountDownTimer countDownTimer = this.v;
            if (countDownTimer != null) {
                countDownTimer.cancel();
                this.v = null;
            }
            String string3 = getContext().getResources().getString(R.string.newenergy_memory_mode_open);
            View viewInflate = LayoutInflater.from(getContext()).inflate(R.layout.dialog_itac_desc, (ViewGroup) null);
            CheckBox checkBox = (CheckBox) viewInflate.findViewById(R.id.cb_open_avm);
            if (i == 2) {
                string = getContext().getResources().getString(R.string.itac_rescue_title);
                str4 = "ITACManageFragment_label_rescue_hint";
                string2 = getContext().getResources().getString(R.string.itac_rescue_desc);
                checkBox.setChecked(v.d().b("KEY_NEWENERGY_ITAC_AVM_RESCUE", false));
            } else if (i == 4) {
                string = getContext().getResources().getString(R.string.itac_drift_title);
                str4 = "ITACManageFragment_label_drift_hint";
                string2 = getContext().getResources().getString(R.string.itac_drift_desc);
                checkBox.setChecked(v.d().b("KEY_NEWENERGY_ITAC_AVM_DRIFT", false));
            } else {
                if (i != 3) {
                    str = "";
                    str2 = str;
                    str3 = str2;
                    l lVarH = m.h(getContext(), str, str2, str3, string3 + "(" + i2 + "s)", getContext().getResources().getString(R.string.dialog_btn_cancel), new a(i, checkBox), new b());
                    lVarH.setDismissListener(new c());
                    lVarH.C();
                    lVarH.B(viewInflate);
                    lVarH.s(false);
                    this.v = new d((long) ((i2 * 1000) + 100), 1000L, lVarH, string3).start();
                }
                string = getContext().getResources().getString(R.string.itac_terrain_title);
                str4 = "ITACManageFragment_label_terrain_hint";
                string2 = getContext().getResources().getString(R.string.itac_terrain_desc);
                checkBox.setChecked(v.d().b("KEY_NEWENERGY_ITAC_AVM_TERRAIN", false));
            }
            str = str4;
            str2 = string;
            str3 = string2;
            l lVarH2 = m.h(getContext(), str, str2, str3, string3 + "(" + i2 + "s)", getContext().getResources().getString(R.string.dialog_btn_cancel), new a(i, checkBox), new b());
            lVarH2.setDismissListener(new c());
            lVarH2.C();
            lVarH2.B(viewInflate);
            lVarH2.s(false);
            this.v = new d((long) ((i2 * 1000) + 100), 1000L, lVarH2, string3).start();
        } catch (Exception e2) {
            BydLogHelper.logE(m, e2.getMessage());
        }
    }

    public final void W() {
        m.e(getContext(), "ITACManageFragment_label_function_hint", getContext().getResources().getString(R.string.itac_function_hint), null).C();
    }

    @Override // b.b.o.b.a.a
    public int c() {
        return R.layout.pager_itac_manage;
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return b.b.o.b.b.a.a(this);
    }

    @Override // b.b.o.b.a.a
    public int h() {
        return R.layout.pager_itac_manage;
    }

    @Override // b.b.o.b.a.a
    public int m() {
        return R.layout.pager_itac_manage_byd;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        try {
            for (String str : m.f1480b) {
                byte b2 = -1;
                switch (str.hashCode()) {
                    case 534320299:
                        if (str.equals("ITACManageFragment_label_rescue_hint")) {
                            b2 = 2;
                        }
                        break;
                    case 1048519525:
                        if (str.equals("ITACManageFragment_label_drift_hint")) {
                            b2 = 1;
                        }
                        break;
                    case 1438018438:
                        if (str.equals("ITACManageFragment_label_function_hint")) {
                            b2 = 0;
                        }
                        break;
                    case 1971363161:
                        if (str.equals("ITACManageFragment_label_terrain_hint")) {
                            b2 = 3;
                        }
                        break;
                }
                if (b2 == 0) {
                    W();
                } else if (b2 == 1) {
                    V(4, n);
                } else if (b2 == 2) {
                    V(2, n);
                } else if (b2 == 3) {
                    V(3, n);
                }
            }
        } catch (Exception e2) {
            BydLogHelper.logE(m, e2.getMessage());
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (b.b.m.g.c.a(300L)) {
            return;
        }
        int id = view.getId();
        if (id == R.id.iv_itac_hint) {
            W();
            return;
        }
        if (id != R.id.tv_mode_terrain) {
            switch (id) {
            }
            return;
        }
        if (view.isSelected()) {
            return;
        }
        e.h(150028);
        O(view.getId());
    }

    @Override // com.byd.mvp.mvp.XFragment, com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        CountDownTimer countDownTimer = this.v;
        if (countDownTimer != null) {
            countDownTimer.cancel();
            this.v = null;
        }
        S(false);
        this.u.setImageDrawable(null);
        super.onDestroyView();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.mvp.mvp.XFragment
    public void y(Context context) {
        ((b.b.g.n.n.d) mo7getP()).g();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.mvp.mvp.XFragment
    public void z() {
        super.z();
        S(true);
        ((b.b.g.n.n.d) mo7getP()).o();
    }
}
