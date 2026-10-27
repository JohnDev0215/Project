package b.b.g.q.f.b;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import b.b.m.f.d.d;
import com.byd.carsettings.R;
import com.byd.carsettings.adapter.dilink.MusicStyleAdapter;
import com.byd.carsettings.sound.model.MusicStyle;
import com.byd.carsettings.ui.widget.EqualizerView;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: DiracEqualizerPager.java */
/* JADX INFO: loaded from: classes2.dex */
public class i extends b.b.m.e.a<b.b.g.n.j.e> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public TextView f1612a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Button f1613b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public EqualizerView f1614c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public RecyclerView f1615d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public List<MusicStyle> f1616e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public MusicStyleAdapter f1617f;
    public b.b.g.q.h.c.a g;

    /* JADX INFO: compiled from: DiracEqualizerPager.java */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            i.this.l();
        }
    }

    /* JADX INFO: compiled from: DiracEqualizerPager.java */
    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (i.this.mCallback != null) {
                i.this.mCallback.onBackPress();
            }
            i.this.finish();
        }
    }

    /* JADX INFO: compiled from: DiracEqualizerPager.java */
    public class c implements b.b.m.f.c.a.e {
        public c() {
        }

        @Override // b.b.m.f.c.a.e
        public void onItemClicked(int i) {
            i.this.n(i, false);
        }
    }

    /* JADX INFO: compiled from: DiracEqualizerPager.java */
    public class d implements d.a {
        public d() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // b.b.m.f.d.d.a
        public void onOKClicked() {
            i.this.n(((b.b.g.n.j.e) i.this.mo7getP()).f(), true);
            ((b.b.g.n.j.e) i.this.mo7getP()).e();
        }
    }

    /* JADX INFO: compiled from: DiracEqualizerPager.java */
    public class e implements b.b.g.q.h.c.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public WeakReference<i> f1622a;

        public e(i iVar) {
            this.f1622a = new WeakReference<>(iVar);
        }

        @Override // b.b.g.q.h.c.a
        public void a(b.b.g.o.h.c cVar) {
            WeakReference<i> weakReference = this.f1622a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.f1622a.get().k(cVar);
        }
    }

    public i(Context context) {
        super(context);
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return R.layout.dialog_dirac_equalizer;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // b.b.m.e.f
    public void initData(Context context) {
        this.f1612a.setText(context.getString(R.string.equalizer_sound));
        this.f1613b.setText(context.getString(R.string.dilink_reset));
        if (this.f1616e == null) {
            this.f1616e = new ArrayList();
        }
        if (this.f1617f == null) {
            for (String str : context.getResources().getStringArray(R.array.music_style_list)) {
                this.f1616e.add(new MusicStyle(str, false));
            }
            this.f1616e.get(((b.b.g.n.j.e) mo7getP()).d()).setChecked(true);
            this.f1617f = new MusicStyleAdapter(this.f1616e);
        }
        this.f1615d.setAdapter(this.f1617f);
        this.f1617f.setOnItemClickedListener(new c());
        this.f1617f.notifyDataSetChanged();
        ((b.b.g.n.j.e) mo7getP()).c();
    }

    @Override // b.b.m.e.f
    public void initViews(Context context) {
        this.f1612a = (TextView) this.mRootView.findViewById(R.id.tv_title_dialog_common_titles);
        View viewFindViewById = this.mRootView.findViewById(R.id.iv_back_dialog_common_title);
        this.f1613b = (Button) this.mRootView.findViewById(R.id.btn_title_right_dialog_common_title);
        this.f1614c = (EqualizerView) this.mRootView.findViewById(R.id.ev_dirac_equalizer);
        RecyclerView recyclerView = (RecyclerView) this.mRootView.findViewById(R.id.rcv_dirac_equalizer_effect);
        this.f1615d = recyclerView;
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        this.f1613b.setVisibility(0);
        this.f1613b.setOnClickListener(new a());
        viewFindViewById.setOnClickListener(new b());
        if (this.g == null) {
            this.g = new e(this);
        }
        this.f1614c.setOnFreqBandChangedCallback(this.g);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void k(b.b.g.o.h.c cVar) {
        b.b.m.c.a.a(this.TAG, "----changeFreqBand---freqBand = " + cVar);
        ((b.b.g.n.j.e) mo7getP()).g(cVar);
    }

    public final void l() {
        b.b.m.c.a.a(this.TAG, "----dialogToResetEqualizer---");
        WeakReference<Context> weakReference = this.ref;
        if (weakReference == null || weakReference.get() == null || !(this.ref.get() instanceof Activity)) {
            return;
        }
        Activity activity = (Activity) this.ref.get();
        b.b.g.q.d.u.b.k(activity, activity.getString(R.string.dilink_reset), activity.getString(R.string.msg_dialog_equalizer), activity.getString(R.string.dialog_btn_ok), new d());
    }

    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: m, reason: merged with bridge method [inline-methods] */
    public b.b.g.n.j.e newP() {
        return new b.b.g.n.j.e();
    }

    public final void n(int i, boolean z) {
        b.b.m.c.a.a(this.TAG, "---onRecyclerViewItemClicked---position: " + i);
        if (this.mRadioBtnCallback != null && !z) {
            this.mRadioBtnCallback.a(i, this.f1616e.get(i).getName());
        }
        for (int i2 = 0; i2 < this.f1616e.size(); i2++) {
            if (i2 == i) {
                this.f1616e.get(i).setChecked(true);
            } else {
                this.f1616e.get(i2).setChecked(false);
            }
        }
        this.f1617f.notifyDataSetChanged();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
    }
}
