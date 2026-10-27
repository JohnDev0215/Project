package b.b.g.q.f.b;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import b.b.m.f.d.d;
import com.byd.carsettings.R;
import com.byd.carsettings.ui.widget.EqualizerView;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: HarmanEqualizerPager.java */
/* JADX INFO: loaded from: classes2.dex */
public class p extends b.b.m.e.a<b.b.g.n.j.k> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public TextView f1684a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public View f1685b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Button f1686c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public EqualizerView f1687d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public b.b.g.q.h.c.a f1688e;

    /* JADX INFO: compiled from: HarmanEqualizerPager.java */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            p.this.k();
        }
    }

    /* JADX INFO: compiled from: HarmanEqualizerPager.java */
    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (p.this.mCallback != null) {
                p.this.mCallback.onBackPress();
            }
            p.this.finish();
        }
    }

    /* JADX INFO: compiled from: HarmanEqualizerPager.java */
    public class c implements d.a {
        public c() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // b.b.m.f.d.d.a
        public void onOKClicked() {
            ((b.b.g.n.j.k) p.this.mo7getP()).c();
        }
    }

    /* JADX INFO: compiled from: HarmanEqualizerPager.java */
    public class d implements b.b.g.q.h.c.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public WeakReference<p> f1692a;

        public d(p pVar) {
            this.f1692a = new WeakReference<>(pVar);
        }

        @Override // b.b.g.q.h.c.a
        public void a(b.b.g.o.h.c cVar) {
            WeakReference<p> weakReference = this.f1692a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.f1692a.get().h(cVar);
        }
    }

    public p(Context context) {
        super(context);
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return R.layout.dialog_harman_equalizer;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void h(b.b.g.o.h.c cVar) {
        b.b.m.c.a.a(this.TAG, "----changeFreqBand---freqBand = " + cVar);
        ((b.b.g.n.j.k) mo7getP()).d(cVar);
    }

    @Override // b.b.m.e.f
    public void initData(Context context) {
    }

    @Override // b.b.m.e.f
    public void initViews(Context context) {
        this.f1684a = (TextView) this.mRootView.findViewById(R.id.tv_title_dialog_common_titles);
        this.f1685b = this.mRootView.findViewById(R.id.iv_back_dialog_common_title);
        this.f1686c = (Button) this.mRootView.findViewById(R.id.btn_title_right_dialog_common_title);
        this.f1687d = (EqualizerView) this.mRootView.findViewById(R.id.ev_dialog_harman_equalizer);
        this.f1686c.setVisibility(0);
        this.f1686c.setOnClickListener(new a());
        this.f1685b.setOnClickListener(new b());
        if (this.f1688e == null) {
            this.f1688e = new d(this);
        }
        this.f1687d.setOnFreqBandChangedCallback(this.f1688e);
    }

    public final void k() {
        b.b.m.c.a.a(this.TAG, "----dialogToResetHarmanEqualizer---");
        WeakReference<Context> weakReference = this.ref;
        if (weakReference == null || weakReference.get() == null || !(this.ref.get() instanceof Activity)) {
            return;
        }
        Activity activity = (Activity) this.ref.get();
        b.b.g.q.d.u.b.k(activity, activity.getString(R.string.dilink_reset), activity.getString(R.string.msg_equalizer_dialog_harman_sound), activity.getString(R.string.dialog_btn_ok), new c());
    }

    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: l, reason: merged with bridge method [inline-methods] */
    public b.b.g.n.j.k newP() {
        return new b.b.g.n.j.k();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
    }
}
