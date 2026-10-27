package b.b.g.q.f.d;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import androidx.fragment.app.FragmentActivity;
import b.b.g.q.f.d.i;
import com.byd.base.view.BydSwitch;
import com.byd.base.view.segmentedbutton.SegmentedButtonGroup;
import com.byd.carsettings.R;
import com.byd.carsettings.sound.model.DiracMode;
import com.byd.carsettings.sound.model.NonBrandLevel;
import com.byd.carsettings.ui.dialog.CustomViewDialog;
import com.byd.carsettings.ui.widget.LinearItem;
import java.lang.ref.WeakReference;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: compiled from: NonBrandPager.java */
/* JADX INFO: loaded from: classes2.dex */
public class h extends i {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f1774f;
    public ViewGroup g;
    public SegmentedButtonGroup h;
    public ViewGroup i;
    public ViewGroup j;
    public BydSwitch k;
    public LinearItem l;
    public LinearItem m;
    public NonBrandLevel n;

    /* JADX INFO: compiled from: NonBrandPager.java */
    public class a implements SegmentedButtonGroup.f {
        public a() {
        }

        @Override // com.byd.base.view.segmentedbutton.SegmentedButtonGroup.f
        public void onPositionChanged(View view, View view2, int i) {
            h.this.n(i);
        }
    }

    /* JADX INFO: compiled from: NonBrandPager.java */
    public class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ b.b.a.c.a f1776a;

        public b(b.b.a.c.a aVar) {
            this.f1776a = aVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f1776a.h() != 230007) {
                return;
            }
            String strG = this.f1776a.g();
            LinearItem linearItem = h.this.l;
            if (strG == null) {
                strG = "";
            }
            linearItem.setRightText(strG);
        }
    }

    /* JADX INFO: compiled from: NonBrandPager.java */
    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            h.this.l.setClickable(true);
        }
    }

    /* JADX INFO: compiled from: NonBrandPager.java */
    public class d implements Runnable {
        public d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            h.this.m.setClickable(true);
        }
    }

    public h(Context context, NonBrandLevel nonBrandLevel) {
        super(context);
        this.f1774f = "NonBrandPager";
        this.n = nonBrandLevel;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // b.b.g.q.f.d.i
    public void e(CompoundButton compoundButton, boolean z) {
        b.b.m.c.a.a(this.f1774f, "onCheckedChanged", "b: " + z);
        int id = compoundButton.getId();
        if (id != R.id.switch_dirac_loudness_sound_pager) {
            if (id != R.id.switch_enable_subview_sound_dirac_simple) {
                return;
            }
            p(z ? DiracMode.SIMPLE_OPEN : DiracMode.SIMPLE_CLOSE);
        } else {
            ((b.b.g.n.p.e) mo7getP()).B(z);
            i.b bVar = this.f1782c;
            if (bVar != null) {
                bVar.a(z);
            }
        }
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return R.layout.subview_sound_non_brand;
    }

    @Override // b.b.g.q.f.d.i, b.b.m.e.f
    public void initData(Context context) {
        this.k.setOnCheckedChangeListener(new i.d(this));
        this.l.setOnClickListener(this);
        this.m.setOnClickListener(this);
        this.h.setOnPositionChangedListener(new a());
    }

    @Override // b.b.g.q.f.d.i, b.b.m.e.f
    public void initViews(Context context) {
        super.initViews(context);
        this.g = (ViewGroup) this.mRootView.findViewById(R.id.ll_sound_effects_non_brand);
        this.h = (SegmentedButtonGroup) this.mRootView.findViewById(R.id.srg_sound_mode_subview_sound_non_brand);
        this.i = (ViewGroup) this.mRootView.findViewById(R.id.ll_dirac_loudness_sound_pager);
        this.j = (ViewGroup) this.mRootView.findViewById(R.id.ll_title_dirac_loudness_sound_pager);
        this.k = (BydSwitch) this.mRootView.findViewById(R.id.switch_dirac_loudness_sound_pager);
        this.l = (LinearItem) this.mRootView.findViewById(R.id.item_dirac_equalizer_sound_pager);
        this.m = (LinearItem) this.mRootView.findViewById(R.id.item_dirac_sound_field_balance_sound_pager);
        EventBus.getDefault().register(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void l() {
        b.b.m.c.a.a(this.f1774f, "onEqualizerOperate");
        WeakReference<Context> weakReference = this.ref;
        if (weakReference == null || weakReference.get() == null || !(this.ref.get() instanceof Activity)) {
            return;
        }
        FragmentActivity fragmentActivity = (FragmentActivity) this.ref.get();
        this.l.setRightText(fragmentActivity.getResources().getStringArray(R.array.music_style_list)[((b.b.g.n.p.e) mo7getP()).u()]);
        this.l.setClickable(false);
        CustomViewDialog.y(6).show(fragmentActivity.getSupportFragmentManager(), "CustomViewDialog");
        b.b.a.f.h.a().g(new c(), 200L);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // b.b.m.e.f
    public void loadData() {
        super.loadData();
        int iU = ((b.b.g.n.p.e) mo7getP()).u();
        boolean zX = ((b.b.g.n.p.e) mo7getP()).x();
        b.b.m.c.a.d(this.f1774f, "loadData", "mLevel: " + this.n + ", defIndex: " + iU, "loudnessEnable: " + zX);
        this.k.setChecked(zX, false);
        String[] stringArray = this.mRootView.getContext().getResources().getStringArray(R.array.music_style_list);
        if (iU >= 0 && iU < stringArray.length) {
            this.l.setRightText(stringArray[iU]);
            this.h.setPosition(iU);
        }
        if (this.n == NonBrandLevel.NO_SOUND_EFFECTS) {
            this.g.setVisibility(8);
        }
    }

    public final void m() {
        b.b.m.c.a.a(this.f1774f, "onSoundBalanceOperate");
        WeakReference<Context> weakReference = this.ref;
        if (weakReference == null || weakReference.get() == null || !(this.ref.get() instanceof Activity)) {
            return;
        }
        this.m.setClickable(false);
        CustomViewDialog.y(5).show(((FragmentActivity) this.ref.get()).getSupportFragmentManager(), "CustomViewDialog");
        b.b.a.f.h.a().g(new d(), 200L);
    }

    public void n(int i) {
        b.b.m.c.a.d(this.f1774f, "onSoundEffectsChecked", "position: " + i);
        if (i == 0) {
            p(DiracMode.LIVE_HIFI);
        } else {
            if (i != 1) {
                return;
            }
            p(DiracMode.LIVE_STAGE);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.item_dirac_equalizer_sound_pager /* 2131297119 */:
                l();
                break;
            case R.id.item_dirac_sound_field_balance_sound_pager /* 2131297120 */:
                m();
                break;
        }
    }

    @Override // b.b.m.e.f
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(b.b.a.c.a aVar) {
        b.b.a.f.h.a().g(new b(aVar), 500L);
    }

    @Override // b.b.m.e.f
    public void onLtrRudderR(boolean z) {
        if (z) {
            setViewGroupRtl(this.l, false);
            setViewGroupRtl(this.m, false);
        }
        setViewGroupRtl(this.g, false);
        setViewGroupRtl(this.i, true);
        setViewGroupRtl(this.j, false);
    }

    @Override // b.b.m.e.f
    public void onRtlRudderL(boolean z) {
        if (z) {
            setViewGroupRtl(this.l, true);
            setViewGroupRtl(this.m, true);
        }
        setViewGroupRtl(this.g, true);
        setViewGroupRtl(this.i, false);
        setViewGroupRtl(this.j, true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void p(DiracMode diracMode) {
        b.b.m.c.a.a(this.f1774f, "changeDiracMode", "mode: " + diracMode);
        ((b.b.g.n.p.e) mo7getP()).y(diracMode);
        i.a aVar = this.f1781b;
        if (aVar != null) {
            aVar.a(diracMode);
        }
    }
}
