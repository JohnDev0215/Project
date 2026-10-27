package b.b.g.q.f.b;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import b.b.m.f.d.d;
import com.byd.carsettings.R;
import com.byd.carsettings.adapter.dilink.MusicStyleAdapter;
import com.byd.carsettings.sound.model.MusicStyle;
import com.byd.carsettings.ui.dialog.CustomViewDialog;
import com.byd.carsettings.ui.widget.EqualizerView;
import com.byd.mvp.ui.widget.RecyclerSpace;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: compiled from: GeneralEqualizerPager.java */
/* JADX INFO: loaded from: classes2.dex */
public class m extends b.b.m.e.a<b.b.g.n.j.i> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ViewGroup f1656a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public TextView f1657b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Button f1658c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public EqualizerView f1659d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public RecyclerView f1660e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public List<MusicStyle> f1661f;
    public MusicStyleAdapter g;
    public b.b.g.q.h.c.a h;
    public volatile int i;
    public volatile int j;
    public CustomViewDialog k;

    /* JADX INFO: compiled from: GeneralEqualizerPager.java */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            m.this.p();
        }
    }

    /* JADX INFO: compiled from: GeneralEqualizerPager.java */
    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (m.this.mCallback != null) {
                m.this.mCallback.onBackPress();
            }
            m.this.finish();
            if (m.this.k != null) {
                m.this.k.dismiss();
            }
        }
    }

    /* JADX INFO: compiled from: GeneralEqualizerPager.java */
    public class c implements b.b.m.f.c.a.e {
        public c() {
        }

        @Override // b.b.m.f.c.a.e
        public void onItemClicked(int i) {
            m.this.r(i, false);
        }
    }

    /* JADX INFO: compiled from: GeneralEqualizerPager.java */
    public class d implements d.a {
        public d() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // b.b.m.f.d.d.a
        public void onOKClicked() {
            int iE = ((b.b.g.n.j.i) m.this.mo7getP()).e(m.this.getContext());
            m.this.r(iE, true);
            Map<Integer, Integer> mapC = ((b.b.g.n.j.i) m.this.mo7getP()).c(m.this.getContext(), iE, true);
            b.b.m.c.a.a(m.this.TAG, "----dialogToResetEqualizer---index: " + iE + ", freqBands: " + mapC);
            m.this.f1659d.h(mapC);
        }
    }

    /* JADX INFO: compiled from: GeneralEqualizerPager.java */
    public class e implements b.b.g.q.h.c.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public WeakReference<m> f1666a;

        public e(m mVar) {
            this.f1666a = new WeakReference<>(mVar);
        }

        @Override // b.b.g.q.h.c.a
        public void a(b.b.g.o.h.c cVar) {
            WeakReference<m> weakReference = this.f1666a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.f1666a.get().n(cVar);
        }
    }

    public m(Context context, CustomViewDialog customViewDialog) {
        super(context);
        this.i = 0;
        this.j = 0;
        this.k = customViewDialog;
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return R.layout.dialog_dirac_equalizer;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // b.b.m.e.f
    public void initData(Context context) {
        this.f1657b.setText(context.getString(R.string.equalizer_sound));
        this.f1658c.setText(context.getString(R.string.dilink_reset));
        if (this.f1661f == null) {
            this.f1661f = new ArrayList();
        }
        int iD = ((b.b.g.n.j.i) mo7getP()).d(getContext());
        this.j = iD;
        b.b.m.c.a.a(this.TAG, "initData", "defIndex: " + iD);
        if (this.g == null) {
            String[] stringArray = context.getResources().getStringArray(R.array.music_style_list);
            b.b.m.c.a.a(this.TAG, "initData", "arr: " + Arrays.toString(stringArray));
            int length = stringArray.length;
            for (int i = 0; i < length; i++) {
                this.f1661f.add(new MusicStyle(stringArray[i], false));
            }
            this.f1661f.get(iD).setChecked(true);
            this.g = new MusicStyleAdapter(this.f1661f);
        }
        this.f1660e.setAdapter(this.g);
        this.g.setOnItemClickedListener(new c());
        this.g.notifyDataSetChanged();
        this.f1659d.h(((b.b.g.n.j.i) mo7getP()).c(getContext(), iD, true));
    }

    @Override // b.b.m.e.f
    public void initViews(Context context) {
        this.f1656a = (ViewGroup) this.mRootView.findViewById(R.id.ll_title_dialog_common_title);
        this.f1657b = (TextView) this.mRootView.findViewById(R.id.tv_title_dialog_common_titles);
        View viewFindViewById = this.mRootView.findViewById(R.id.iv_back_dialog_common_title);
        this.f1658c = (Button) this.mRootView.findViewById(R.id.btn_title_right_dialog_common_title);
        this.f1659d = (EqualizerView) this.mRootView.findViewById(R.id.ev_dirac_equalizer);
        this.f1660e = (RecyclerView) this.mRootView.findViewById(R.id.rcv_dirac_equalizer_effect);
        Resources resources = this.mRootView.getResources();
        int dimension = (int) resources.getDimension(R.dimen.dp_4);
        int dimension2 = (int) resources.getDimension(R.dimen.dp_8);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.f1660e.getContext(), 5);
        gridLayoutManager.setOrientation(1);
        this.f1660e.setLayoutManager(gridLayoutManager);
        this.f1660e.setItemAnimator(new DefaultItemAnimator());
        this.f1660e.addItemDecoration(new RecyclerSpace(dimension, dimension2, dimension, dimension2));
        this.f1658c.setVisibility(0);
        this.f1658c.setOnClickListener(new a());
        viewFindViewById.setOnClickListener(new b());
        if (this.h == null) {
            this.h = new e(this);
        }
        this.f1659d.setOnFreqBandChangedCallback(this.h);
        EventBus.getDefault().register(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void n(b.b.g.o.h.c cVar) {
        b.b.m.c.a.a(this.TAG, "----changeFreqBand---freqBand = " + cVar);
        ((b.b.g.n.j.i) mo7getP()).h(cVar);
        s(0);
        ((b.b.g.n.j.i) mo7getP()).f(getContext(), 0);
        Map<Integer, Integer> curFreqBands = this.f1659d.getCurFreqBands();
        b.b.m.c.a.a(this.TAG, "----changeFreqBand---freqBands = " + curFreqBands);
        ((b.b.g.n.j.i) mo7getP()).g(getContext(), curFreqBands);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
    }

    @Override // b.b.m.e.a, b.b.m.e.f
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(b.b.a.c.a aVar) {
    }

    @Override // b.b.m.e.f
    public void onLtrRudderR(boolean z) {
        setViewGroupRtl(this.f1656a, true);
        setViewGroupRtl(this.f1659d, false);
        setViewGroupRtl(this.f1660e, false);
    }

    @Override // b.b.m.e.f
    public void onRtlRudderL(boolean z) {
        setViewGroupRtl(this.f1656a, false);
        setViewGroupRtl(this.f1659d, true);
        setViewGroupRtl(this.f1660e, true);
    }

    public final void p() {
        b.b.m.c.a.a(this.TAG, "----dialogToResetEqualizer---");
        WeakReference<Context> weakReference = this.ref;
        if (weakReference == null || weakReference.get() == null || !(this.ref.get() instanceof Activity)) {
            return;
        }
        Activity activity = (Activity) this.ref.get();
        b.b.g.q.d.u.b.k(activity, activity.getString(R.string.dilink_reset), activity.getString(R.string.msg_dialog_equalizer), activity.getString(R.string.dialog_btn_ok), new d());
    }

    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public b.b.g.n.j.i newP() {
        return new b.b.g.n.j.i();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void r(int i, boolean z) {
        b.b.m.c.a.a(this.TAG, "---onRecyclerViewItemClicked---position: " + i);
        if (this.mRadioBtnCallback != null && !z) {
            this.mRadioBtnCallback.a(i, this.f1661f.get(i).getName());
        }
        s(i);
        ((b.b.g.n.j.i) mo7getP()).i(getContext(), i);
        Map<Integer, Integer> mapC = ((b.b.g.n.j.i) mo7getP()).c(getContext(), i, true);
        if (i == 0) {
            ((b.b.g.n.j.i) mo7getP()).g(getContext(), mapC);
        }
        this.f1659d.h(mapC);
        EventBus.getDefault().post(new b.b.a.c.a(230007, this.f1661f.get(i).getName()));
    }

    public final void s(int i) {
        for (int i2 = 0; i2 < this.f1661f.size(); i2++) {
            if (i2 == i) {
                this.f1661f.get(i).setChecked(true);
            } else {
                this.f1661f.get(i2).setChecked(false);
            }
        }
        this.g.notifyDataSetChanged();
    }
}
