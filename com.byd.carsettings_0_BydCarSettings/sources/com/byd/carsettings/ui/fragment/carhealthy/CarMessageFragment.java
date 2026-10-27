package com.byd.carsettings.ui.fragment.carhealthy;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import b.b.g.n.g.a;
import com.byd.carsettings.R;
import com.byd.carsettings.adapter.carhealthy.DtcInformationListAdapter;
import com.byd.mvp.mvp.XFragment;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class CarMessageFragment extends XFragment<a> implements b.b.o.b.a.a {
    public RecyclerView n;
    public ImageView o;
    public TextView p;
    public String[] q;
    public String[] r;
    public final String m = getClass().getSimpleName();
    public List<b.b.g.m.c.a> s = new ArrayList();
    public int[] t = null;
    public int[] u = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};

    @Override // com.byd.mvp.mvp.XFragment
    public void A(Context context) {
        this.n = (RecyclerView) this.f3717c.findViewById(R.id.recyclerview);
        this.o = (ImageView) this.f3717c.findViewById(R.id.iv_dtc_result);
        this.p = (TextView) this.f3717c.findViewById(R.id.tv_dtc_result);
    }

    public void K(int i) {
        if (i < 0 || i > 100) {
            ((TextView) this.f3717c.findViewById(R.id.tv_battery_health)).setText("--%");
            return;
        }
        ((TextView) this.f3717c.findViewById(R.id.tv_battery_health)).setText(i + "%");
    }

    public void L(int i, int i2) {
        int[] iArr = this.t;
        if (iArr == null || i <= 0 || i > iArr.length) {
            return;
        }
        int i3 = i - 1;
        if (iArr[i3] != i2) {
            iArr[i3] = i2;
            M(iArr);
        }
    }

    public void M(int[] iArr) {
        try {
            this.t = iArr;
            if (iArr == null) {
                P();
                return;
            }
            if (iArr.length == 0) {
                P();
                return;
            }
            int length = iArr.length;
            String[] strArr = this.q;
            if (length != strArr.length) {
                P();
                return;
            }
            if (iArr.length <= 0 || iArr.length != strArr.length) {
                P();
                return;
            }
            this.s.clear();
            boolean z = false;
            boolean z2 = false;
            for (int i = 0; i < iArr.length; i++) {
                if (i != 5 && i != 15 && i != 16 && i != 29 && i != 30 && i != 44 && i != 45 && i != 56 && iArr[i] == 1) {
                    int[] iArr2 = this.u;
                    if (iArr2[i] == 0) {
                        z = true;
                    }
                    if (iArr2[i] == 1) {
                        z2 = true;
                    }
                    this.s.add(new b.b.g.m.c.a(iArr2[i], this.q[i]));
                }
            }
            List<b.b.g.m.c.a> list = this.s;
            if (list == null || list.size() <= 0) {
                P();
                return;
            }
            ((DtcInformationListAdapter) this.n.getAdapter()).a(this.s);
            this.o.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.icon_dark_carsetting_fault_48));
            this.p.setText(String.format(getContext().getResources().getString(R.string.vehiclehealth_has_issue), Integer.valueOf(this.s.size())));
            if (z) {
                this.o.setColorFilter(ContextCompat.getColor(getContext(), R.color.carmsg_serious));
                this.p.setTextColor(ContextCompat.getColor(getContext(), R.color.carmsg_serious));
            } else if (z2) {
                this.o.setColorFilter(ContextCompat.getColor(getContext(), R.color.carmsg_warn));
                this.p.setTextColor(ContextCompat.getColor(getContext(), R.color.carmsg_warn));
            } else {
                this.o.setColorFilter(ContextCompat.getColor(getContext(), R.color.carmsg_normal));
                this.p.setTextColor(ContextCompat.getColor(getContext(), R.color.carmsg_normal));
            }
        } catch (Exception e2) {
            P();
            BydLogHelper.logE(e2.getMessage());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void N() {
        this.f3717c.findViewById(R.id.ll_battery_health).setVisibility(((a) mo7getP()).g() ? 0 : 8);
    }

    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: O, reason: merged with bridge method [inline-methods] */
    public a newP() {
        return new a();
    }

    public final void P() {
        ((DtcInformationListAdapter) this.n.getAdapter()).a(new ArrayList());
        this.o.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.icon_dark_carsetting_normal_48));
        this.o.setColorFilter(ContextCompat.getColor(getContext(), R.color.carmsg_dtc_ok));
        this.p.setText(getContext().getResources().getString(R.string.vehiclehealth_no_issue));
        this.p.setTextColor(ContextCompat.getColor(getContext(), R.color.carmsg_dtc_ok));
    }

    @Override // b.b.o.b.a.a
    public int c() {
        return R.layout.pager_car_message;
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return b.b.o.b.b.a.a(this);
    }

    @Override // b.b.o.b.a.a
    public int h() {
        return R.layout.pager_car_message;
    }

    @Override // b.b.o.b.a.a
    public int m() {
        return R.layout.pager_car_message_byd;
    }

    @Override // com.byd.mvp.mvp.XFragment, com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        ((ImageView) this.f3717c.findViewById(R.id.iv_oil_result)).setImageDrawable(null);
        this.n.setAdapter(null);
        super.onDestroyView();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.mvp.mvp.XFragment
    public void y(Context context) {
        this.n.setLayoutManager(new LinearLayoutManager(context));
        this.n.setAdapter(new DtcInformationListAdapter(context));
        this.n.setOverScrollMode(2);
        this.r = getResources().getStringArray(R.array.malfunction_info_notarea_array);
        this.q = getResources().getStringArray(R.array.malfunction_info_area_array);
        ((ImageView) this.f3717c.findViewById(R.id.iv_oil_result)).setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.icon_dark_carhealthy));
        P();
        ((a) mo7getP()).f();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.mvp.mvp.XFragment
    public void z() {
        super.z();
        ((a) mo7getP()).h();
    }
}
