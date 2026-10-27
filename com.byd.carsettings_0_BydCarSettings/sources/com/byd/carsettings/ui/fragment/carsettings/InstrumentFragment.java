package com.byd.carsettings.ui.fragment.carsettings;

import android.content.Context;
import android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import b.b.a.f.h;
import b.b.a.h.l;
import b.b.g.n.h.k;
import b.b.g.r.e;
import b.b.g.r.s;
import com.byd.base.view.segmentedbutton.SegmentedButtonGroup;
import com.byd.carsettings.R;
import com.byd.carsettings.adapter.carsettings.InstrumentDisplayAdatper;
import com.byd.mvp.mvp.XFragment;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.views.window.DampingScrollView;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes2.dex */
public class InstrumentFragment extends XFragment<k> implements SeekBar.OnSeekBarChangeListener, SegmentedButtonGroup.f, View.OnClickListener, CompoundButton.OnCheckedChangeListener, b.b.o.b.a.a {
    public b.b.o.a.b.a A;
    public AbsBYDAutoBodyworkListener B;
    public RelativeLayout D;
    public TextView E;
    public Button F;
    public SeekBar m;
    public TextView n;
    public Context o;
    public DampingScrollView p;
    public RecyclerView r;
    public SegmentedButtonGroup s;
    public ImageView u;
    public RelativeLayout w;
    public SegmentedButtonGroup y;
    public List<b.b.g.c.a.a.a> q = new ArrayList();
    public InstrumentDisplayAdatper t = new InstrumentDisplayAdatper();
    public int v = 0;
    public int x = 0;
    public Handler z = new a(Looper.myLooper());

    public class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i == 250010) {
                if (InstrumentFragment.this.m != null) {
                    ((k) InstrumentFragment.this.mo7getP()).s(InstrumentFragment.this.m.getProgress());
                }
                e.i(70001);
                return;
            }
            if (i != 250014) {
                return;
            }
            int i2 = ((k) InstrumentFragment.this.mo7getP()).i();
            BydLogHelper.logD(a.class.getSimpleName(), "SGB_INS_NAI_MODE_SET mode : " + i2);
            if (i2 == 2) {
                if (InstrumentFragment.this.y != null) {
                    int position = InstrumentFragment.this.y.getPosition();
                    ((k) InstrumentFragment.this.mo7getP()).r(position <= 1 ? position + 1 : position + 2);
                }
            } else if (InstrumentFragment.this.s != null) {
                ((k) InstrumentFragment.this.mo7getP()).r(InstrumentFragment.this.s.getPosition() + 1);
            }
            e.i(70009);
        }
    }

    public class b extends AbsBYDAutoBodyworkListener {

        public class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ int f3101a;

            public a(int i) {
                this.f3101a = i;
            }

            @Override // java.lang.Runnable
            public void run() {
                InstrumentFragment.this.m0(this.f3101a != 0);
            }
        }

        public b() {
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onHasMessageChanged(int i, int i2) {
            super.onHasMessageChanged(i, i2);
            if (i == 74) {
                BydLogHelper.logD(b.class.getSimpleName(), "HAS_0X026F00 state ：" + i2);
                h.a().c(new a(i2));
            }
        }
    }

    public class c extends GridLayoutManager {
        public c(Context context, int i) {
            super(context, i);
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public boolean canScrollVertically() {
            return false;
        }
    }

    public class d implements InstrumentDisplayAdatper.b {
        public d() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.byd.carsettings.adapter.carsettings.InstrumentDisplayAdatper.b
        public void a(int i) {
            BydLogHelper.logE(d.class.getSimpleName(), "milesRv onItemClick position ：" + i);
            ((k) InstrumentFragment.this.mo7getP()).t(((b.b.g.c.a.a.a) InstrumentFragment.this.q.get(i)).c());
            InstrumentFragment instrumentFragment = InstrumentFragment.this;
            instrumentFragment.o0(((b.b.g.c.a.a.a) instrumentFragment.q.get(i)).a());
            InstrumentFragment.this.g0(16);
            InstrumentFragment instrumentFragment2 = InstrumentFragment.this;
            instrumentFragment2.l0(instrumentFragment2.o, ((b.b.g.c.a.a.a) InstrumentFragment.this.q.get(i)).b(), true, true);
            e.i(70003);
            InstrumentFragment.this.h0(i);
            InstrumentFragment.this.t.k(InstrumentFragment.this.q);
            if (InstrumentFragment.this.r != null) {
                InstrumentFragment.this.r.smoothScrollToPosition(InstrumentFragment.this.t.getItemCount());
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.mvp.mvp.XFragment
    public void A(Context context) {
        this.o = context;
        c0(((k) mo7getP()).m());
        f0();
        e0();
        this.p = (DampingScrollView) this.f3717c.findViewById(R.id.nsw);
        EventBus.getDefault().register(this);
    }

    public final void U() {
        l.s(null, this.m);
        b.b.g.n.q.b.c().j(this.B);
    }

    public final int V(int i, List<b.b.g.c.a.a.a> list) {
        if (list == null || list.size() == 0) {
            b.b.a.h.a.b(this, "list is null or size is 0 ");
            return Y();
        }
        for (b.b.g.c.a.a.a aVar : list) {
            if (aVar.a() == i) {
                b.b.a.h.a.b(this, "find mile image getId" + i);
                return aVar.b();
            }
        }
        b.b.a.h.a.b(this, "not find mile image id");
        return Y();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void W() {
        List<b.b.g.c.a.a.a> list;
        int iH = ((k) mo7getP()).h();
        if ((iH < 1 || iH > 5) && (list = this.q) != null && list.size() >= 1) {
            this.q.get(0).f(true);
            return;
        }
        for (b.b.g.c.a.a.a aVar : this.q) {
            if (((k) mo7getP()).h() == aVar.a()) {
                aVar.f(true);
            } else {
                aVar.f(false);
            }
        }
    }

    public int X(int i, int i2) {
        if (this.A == null) {
            this.A = b.b.o.a.b.d.a("instrument");
        }
        return this.A.a(i, i2);
    }

    public final int Y() {
        return X(this.x, 0);
    }

    public final int Z(int i) {
        return (i < 1 || i > 3) ? Y() : X(this.x, i);
    }

    public final String a0(int i) {
        BydLogHelper.logD(getClass().getSimpleName(), " getStringByType  type : " + i);
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? getString(R.string.vehiclesettings_instrument_miles_total) : getString(R.string.vehiclesettings_instrument_miles_ev) : getString(R.string.vehiclesettings_instrument_miles_hev) : getString(R.string.vehiclesettings_instrument_miles_two) : getString(R.string.vehiclesettings_instrument_miles_one) : getString(R.string.vehiclesettings_instrument_miles_total);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final int b0(int i) {
        BydLogHelper.logD(getClass().getSimpleName(), " getUnitByType  type : " + i);
        return (i == 2 || i == 3) ? ((k) mo7getP()).c() : ((k) mo7getP()).f();
    }

    @Override // b.b.o.b.a.a
    public int c() {
        return R.layout.pager_carsetting_rtl_instrument;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void c0(boolean z) {
        if (z) {
            this.m = (SeekBar) this.f3717c.findViewById(R.id.instrument_back_door_sb);
            this.n = (TextView) this.f3717c.findViewById(R.id.instrument_hight_percent);
            l.s(this, this.m);
            l.K(((k) mo7getP()).m(), (RelativeLayout) this.f3717c.findViewById(R.id.instrument_height));
            l.z((((k) mo7getP()).e() - 1) * 10, this.n, this.o, R.string.vehiclesettings_placehodlers, "%");
            l.q(this.m, ((k) mo7getP()).e());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void d0(boolean z) {
        boolean zL = ((k) mo7getP()).l();
        List<b.b.g.c.a.a.a> list = this.q;
        if (list != null && list.size() != 0) {
            this.q.clear();
        }
        this.q.add(new b.b.g.c.a.a.a(1, 1, 2, R.string.vehiclesettings_instrument_miles_total, X(28, zL ? 1 : 2)));
        if (!zL) {
            this.q.add(new b.b.g.c.a.a.a(2, 4, 2, R.string.vehiclesettings_instrument_miles_hev, X(29, 2)));
            this.q.add(new b.b.g.c.a.a.a(3, 5, 2, R.string.vehiclesettings_instrument_miles_ev, X(29, 2)));
        }
        this.q.add(new b.b.g.c.a.a.a(4, 2, 2, R.string.vehiclesettings_instrument_miles_one, X(29, zL ? 1 : 2)));
        this.q.add(new b.b.g.c.a.a.a(5, 3, 2, R.string.vehiclesettings_instrument_miles_two, X(30, zL ? 1 : 2)));
        List<b.b.g.c.a.a.a> list2 = this.q;
        if (list2 == null || list2.size() <= 0) {
            l.K(false, this.w);
        } else {
            l.K(((k) mo7getP()).n(), this.w);
            W();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void e0() {
        this.r = (RecyclerView) this.f3717c.findViewById(R.id.instrument_miles_display_rv);
        this.w = (RelativeLayout) this.f3717c.findViewById(R.id.instrument_miles_display_container);
        this.D = (RelativeLayout) this.f3717c.findViewById(R.id.instrument_miles_display_clear_container);
        this.E = (TextView) this.f3717c.findViewById(R.id.instrument_miles_display_clear_tv);
        this.F = (Button) this.f3717c.findViewById(R.id.instrument_miles_display_clear_btn);
        l.K(((k) mo7getP()).n(), this.w);
        l.K(((k) mo7getP()).o(), this.D);
        d0(((k) mo7getP()).j() != 2);
        c cVar = new c(getContext(), 2);
        this.t.e(this.o, this.q);
        this.r.setLayoutManager(cVar);
        this.r.setAdapter(this.t);
        this.t.setListener(new d());
        int iE = b.b.g.n.q.b.c().e(74);
        int iH = ((k) mo7getP()).h();
        m0((iH >= 1 && iH <= 5) || iE != 0);
        l.F(this, this.F);
        o0(iH);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void f0() {
        this.s = (SegmentedButtonGroup) this.f3717c.findViewById(R.id.instrument_nav_segmentedButtonGroup);
        this.y = (SegmentedButtonGroup) this.f3717c.findViewById(R.id.instrument_nav_segmentedButtonGroup_without_small);
        int i = ((k) mo7getP()).i();
        l.K(i == 1, this.s);
        l.K(i == 2, this.y);
        RelativeLayout relativeLayout = (RelativeLayout) this.f3717c.findViewById(R.id.instrument_navi_display_container);
        int iD = ((k) mo7getP()).d();
        if (i == 2) {
            BydLogHelper.logD(getClass().getSimpleName(), "getINSNavDisplayMode  displayMode : " + iD);
            if (iD == 4) {
                iD--;
            }
            l.w(this.y, iD > 0 ? iD - 1 : 0);
            l.t(this, this.y);
        } else {
            l.w(this.s, iD > 0 ? iD - 1 : 0);
            l.t(this, this.s);
        }
        l.K(((k) mo7getP()).q(), relativeLayout);
    }

    public void g0(int i) {
        this.x = i;
        b.b.o.a.c.a.b(getClass().getSimpleName(), Integer.valueOf(this.x));
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return b.b.o.b.b.a.a(this);
    }

    @Override // b.b.o.b.a.a
    public int h() {
        return R.layout.pager_instrument;
    }

    public final void h0(int i) {
        for (int i2 = 0; i2 < this.q.size(); i2++) {
            if (i2 == i) {
                this.q.get(i2).f(true);
            } else {
                this.q.get(i2).f(false);
            }
        }
    }

    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: i0, reason: merged with bridge method [inline-methods] */
    public k newP() {
        return new k();
    }

    public final void j0(int i) {
        List<b.b.g.c.a.a.a> list;
        for (b.b.g.c.a.a.a aVar : this.q) {
            if (aVar.a() == i) {
                aVar.f(true);
            } else {
                aVar.f(false);
            }
        }
        if ((i > 5 || i < 1) && (list = this.q) != null && list.size() > 0) {
            this.q.get(0).f(true);
        }
        InstrumentDisplayAdatper instrumentDisplayAdatper = this.t;
        if (instrumentDisplayAdatper != null) {
            instrumentDisplayAdatper.k(this.q);
            RecyclerView recyclerView = this.r;
            if (recyclerView != null) {
                recyclerView.smoothScrollToPosition(this.t.getItemCount());
            }
        }
    }

    public void k0(Context context, int i, boolean z) {
        if (z) {
            l.k(this.u, i, context, false);
        }
    }

    public void l0(Context context, int i, boolean z, boolean z2) {
        if (z) {
            l.k(this.u, i, context, z2);
        }
    }

    @Override // b.b.o.b.a.a
    public int m() {
        return R.layout.pager_carsetting_right_instrument;
    }

    public final void m0(boolean z) {
        InstrumentDisplayAdatper instrumentDisplayAdatper = this.t;
        if (instrumentDisplayAdatper != null) {
            instrumentDisplayAdatper.j(z);
            this.t.k(this.q);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void n0() {
        this.u = (ImageView) this.f3717c.findViewById(R.id.instrument_image);
        int iA = b.b.o.a.c.a.a(getClass().getSimpleName());
        if (iA == 0) {
            l0(this.o, Y(), true, true);
        } else if (iA == 14) {
            k0(this.o, Z(((k) mo7getP()).d() - 1), true);
        } else {
            if (iA != 16) {
                return;
            }
            l0(this.o, V(((k) mo7getP()).h(), this.q), true, true);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void o0(int i) {
        p0(i, ((k) mo7getP()).g(i));
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.instrument_miles_display_clear_btn) {
            int iH = ((k) mo7getP()).h();
            BydLogHelper.logD(getClass().getSimpleName(), "onClick type : " + iH);
            if (iH == 3 || iH == 2) {
                p0(iH, 0.0f);
                e.i(70018);
                b.b.g.n.q.h.m().a(iH);
            }
        }
    }

    @Override // com.byd.mvp.mvp.XFragment, com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        List<b.b.g.c.a.a.a> list = this.q;
        if (list != null) {
            list.clear();
        }
        Handler handler = this.z;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.z = null;
        }
        InstrumentDisplayAdatper instrumentDisplayAdatper = this.t;
        if (instrumentDisplayAdatper != null) {
            instrumentDisplayAdatper.d();
        }
    }

    @Override // com.byd.mvp.mvp.XFragment, com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        U();
        super.onDestroyView();
    }

    @Override // com.byd.mvp.mvp.XFragment, androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(b.b.a.c.a aVar) {
        switch (aVar.h()) {
            case 70001:
                BydLogHelper.logD("message : ON_INSTRUMENT_BRIGHTNESS_CHANGED " + aVar.c());
                int iC = aVar.c();
                if (e.e(aVar) && (iC = ((k) mo7getP()).e()) == 0) {
                    iC = 8;
                }
                if (iC > 11) {
                    iC -= 11;
                }
                if (iC >= 1 && iC <= 11) {
                    this.m.setProgress(iC);
                    l.z((iC - 1) * 10, this.n, this.o, R.string.vehiclesettings_placehodlers, "%");
                    break;
                }
                break;
            case 70003:
                BydLogHelper.logE("message : ON_MILESTYPE_CHANGED " + aVar.c());
                int iC2 = aVar.c();
                if (e.e(aVar)) {
                    iC2 = ((k) mo7getP()).h();
                    BydLogHelper.logD(getClass().getSimpleName(), " getMilesType ：" + iC2);
                    if (iC2 == 0) {
                        iC2 = 1;
                    }
                } else if (iC2 == 0) {
                    BydLogHelper.logE(getClass().getSimpleName(), "from callback getMilesType ：" + iC2);
                }
                m0(iC2 >= 1 && iC2 <= 5);
                j0(iC2);
                if (e.e(aVar)) {
                    l0(this.o, V(iC2, this.q), this.x == 16, true);
                } else {
                    l0(this.o, V(iC2, this.q), true, true);
                    g0(16);
                }
                o0(iC2);
                break;
            case 70009:
                BydLogHelper.logE("message : ON_NAV_DISPLAY_MODE_CHANGED " + aVar.c());
                int iC3 = aVar.c();
                if (e.e(aVar)) {
                    iC3 = ((k) mo7getP()).d();
                }
                if (((k) mo7getP()).i() == 2) {
                    if (iC3 == 4) {
                        iC3--;
                    }
                    this.y.setPositionWithOutCallback(iC3 - 1, false);
                } else {
                    this.s.setPositionWithOutCallback(iC3 - 1, false);
                }
                if (!e.e(aVar)) {
                    l0(this.o, Z(iC3 - 1), true, true);
                    g0(14);
                } else {
                    l0(this.o, Z(iC3 - 1), this.x == 14, true);
                }
                break;
            case 70013:
                BydLogHelper.logE("message : ON_MILEAGEUNIT_CHANGED " + aVar.c());
                o0(((k) mo7getP()).h());
                break;
            case 70018:
                BydLogHelper.logE("message : INSTRUMENT_CLEAR_MILES_MESSAGE " + aVar.c());
                o0(((k) mo7getP()).h());
                break;
            case 70019:
                BydLogHelper.logE("message : INSTRUMENT_MILE_UNIT_CHANGED " + aVar.c());
                o0(((k) mo7getP()).h());
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(b.b.a.j.b.b bVar) {
    }

    @Override // com.byd.base.view.segmentedbutton.SegmentedButtonGroup.f
    public void onPositionChanged(View view, View view2, int i) {
        if (view.getId() == R.id.instrument_nav_segmentedButtonGroup) {
            e.i(70009);
            g0(14);
            l0(this.o, Z(i), true, true);
            e.n(250014, this.z);
            return;
        }
        if (view.getId() == R.id.instrument_nav_segmentedButtonGroup_without_small) {
            e.i(70009);
            g0(14);
            l0(this.o, Z(i), true, true);
            e.n(250014, this.z);
        }
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        if (z) {
            l.r(false, this.p);
            e.i(70001);
            l.z((i - 1) * 10, this.n, this.o, R.string.vehiclesettings_placehodlers, "%");
            e.n(250010, this.z);
        }
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStartTrackingTouch(SeekBar seekBar) {
        e.i(70001);
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStopTrackingTouch(SeekBar seekBar) {
        e.i(70001);
        l.r(true, this.p);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void p0(int i, float f2) {
        String str = getString(R.string.vehiclesettings_instrument_miles_total) + " : ";
        String string = getString(R.string.ev_km_distance_unit_setting_system);
        boolean zO = ((k) mo7getP()).o();
        BydLogHelper.logD(getClass().getSimpleName(), " showClearMilesText  isSup : " + zO + " , type = " + i + " , miles : " + f2);
        if (!zO) {
            l.K(false, this.D);
            return;
        }
        l.K(i == 2 || i == 3, this.F);
        String str2 = a0(i) + " : ";
        if (b0(i) == 2) {
            string = getString(R.string.ev_mi_distance_unit_setting_system);
        }
        TextView textView = this.E;
        if (textView != null) {
            if (i == 3 || i == 2) {
                l.B(textView, str2 + f2 + string);
                return;
            }
            l.B(textView, str2 + ((int) f2) + string);
        }
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void t(int i) {
        s.a(this.p, i, b.b.m.e.i.a.h(getClass().getName()));
        b.b.m.e.i.a.n(getClass().getName());
    }

    @Override // com.byd.mvp.mvp.XFragment
    public void y(Context context) {
        n0();
        this.B = new b();
        b.b.g.n.q.b.c().h(this.B);
    }
}
