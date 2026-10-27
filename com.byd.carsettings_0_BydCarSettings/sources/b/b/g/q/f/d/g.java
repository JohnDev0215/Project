package b.b.g.q.f.d;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import com.byd.base.view.segmentedbutton.SegmentedButtonGroup;
import com.byd.carsettings.R;
import com.byd.carsettings.ui.widget.LinearItem;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: HarmanPager.java */
/* JADX INFO: loaded from: classes2.dex */
public class g extends i {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public LinearItem f1770f;
    public LinearItem g;
    public LinearItem h;
    public SegmentedButtonGroup i;
    public volatile int j;

    /* JADX INFO: compiled from: HarmanPager.java */
    public class a implements SegmentedButtonGroup.f {
        public a() {
        }

        @Override // com.byd.base.view.segmentedbutton.SegmentedButtonGroup.f
        public void onPositionChanged(View view, View view2, int i) {
            g.this.r(i);
        }
    }

    /* JADX INFO: compiled from: HarmanPager.java */
    public class b implements b.b.g.o.g.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String[] f1772a;

        public b(String[] strArr) {
            this.f1772a = strArr;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // b.b.g.o.g.a
        public void a(int i) {
            b.b.m.c.a.a(g.this.TAG, "----onSoundBalanceChanged---index = " + i);
            ((b.b.g.n.p.e) g.this.mo7getP()).A(i);
            g.this.f1770f.setRightText(this.f1772a[i]);
        }
    }

    public g(Context context) {
        super(context);
        this.j = 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // b.b.g.q.f.d.i
    public void b(int i) {
        ((b.b.g.n.p.e) mo7getP()).t();
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return R.layout.subview_sound_harman;
    }

    @Override // b.b.g.q.f.d.i, b.b.m.e.f
    public void initData(Context context) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // b.b.g.q.f.d.i, b.b.m.e.f
    public void initViews(Context context) {
        ((b.b.g.n.p.e) mo7getP()).w();
        this.f1770f = (LinearItem) this.mRootView.findViewById(R.id.item_sound_balance_sound_harman);
        this.g = (LinearItem) this.mRootView.findViewById(R.id.item_sound_fade_sound_harman);
        this.h = (LinearItem) this.mRootView.findViewById(R.id.item_sound_equalizer_sound_harman);
        this.i = (SegmentedButtonGroup) this.mRootView.findViewById(R.id.srg_sound_regulate_sound_harman);
        this.f1770f.setOnClickListener(this);
        this.g.setOnClickListener(this);
        this.h.setOnClickListener(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // b.b.m.e.f
    public void loadData() {
        super.loadData();
        int iT = ((b.b.g.n.p.e) mo7getP()).t();
        if (iT >= 0 && iT < this.i.getChildCount()) {
            this.i.setPosition(iT);
        }
        this.i.setOnPositionChangedListener(new a());
        this.f1770f.setRightText(this.mRootView.getResources().getStringArray(R.array.harman_balance_array)[((b.b.g.n.p.e) mo7getP()).v()]);
    }

    public final void n() {
        b.b.m.c.a.a(this.TAG, "----onHarmanEqualizerOperate---");
        WeakReference<Context> weakReference = this.ref;
        if (weakReference == null || weakReference.get() == null || !(this.ref.get() instanceof Activity)) {
            return;
        }
        Activity activity = (Activity) this.ref.get();
        b.b.g.q.d.u.c.s(activity, activity.getString(R.string.sound_equalizer_harman_sound));
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.item_sound_balance_sound_harman /* 2131297140 */:
                p();
                break;
            case R.id.item_sound_equalizer_sound_harman /* 2131297143 */:
                n();
                break;
            case R.id.item_sound_fade_sound_harman /* 2131297144 */:
                q();
                break;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void p() {
        b.b.m.c.a.a(this.TAG, "----onHarmanSoundBalanceOperate---");
        WeakReference<Context> weakReference = this.ref;
        if (weakReference == null || weakReference.get() == null || !(this.ref.get() instanceof Activity)) {
            return;
        }
        Activity activity = (Activity) this.ref.get();
        String[] stringArray = activity.getResources().getStringArray(R.array.harman_balance_array);
        int iV = ((b.b.g.n.p.e) mo7getP()).v();
        this.f1770f.setRightText(stringArray[iV]);
        b.b.g.q.d.u.c.t(activity, iV, new b(stringArray));
    }

    public final void q() {
        b.b.m.c.a.a(this.TAG, "----onHarmanSoundFadeOperate---");
        WeakReference<Context> weakReference = this.ref;
        if (weakReference == null || weakReference.get() == null || !(this.ref.get() instanceof Activity)) {
            return;
        }
        Activity activity = (Activity) this.ref.get();
        b.b.g.q.d.u.c.u(activity, activity.getString(R.string.sound_fade_harman_sound));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void r(int i) {
        ((b.b.g.n.p.e) mo7getP()).z(i);
    }
}
