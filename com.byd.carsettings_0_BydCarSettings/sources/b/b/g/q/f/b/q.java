package b.b.g.q.f.b;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import b.b.m.f.d.d;
import com.byd.carsettings.R;
import com.byd.carsettings.ui.widget.SoundFieldView;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: HarmanSoundFieldPager.java */
/* JADX INFO: loaded from: classes2.dex */
public class q extends b.b.m.e.a<b.b.g.n.j.l> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public TextView f1694a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public View f1695b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Button f1696c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public SoundFieldView f1697d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public View f1698e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public b.b.g.q.h.c.c f1699f;
    public int g;

    /* JADX INFO: compiled from: HarmanSoundFieldPager.java */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            q.this.l();
        }
    }

    /* JADX INFO: compiled from: HarmanSoundFieldPager.java */
    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (q.this.mCallback != null) {
                q.this.mCallback.onBackPress();
            }
            q.this.finish();
        }
    }

    /* JADX INFO: compiled from: HarmanSoundFieldPager.java */
    public class c implements d.a {
        public c() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // b.b.m.f.d.d.a
        public void onOKClicked() {
            int[] iArrD = ((b.b.g.n.j.l) q.this.mo7getP()).d();
            q.this.f1697d.i(iArrD[0], iArrD[1]);
        }
    }

    /* JADX INFO: compiled from: HarmanSoundFieldPager.java */
    public class d implements b.b.g.q.h.c.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference<q> f1703a;

        public d(q qVar) {
            this.f1703a = new WeakReference<>(qVar);
        }

        @Override // b.b.g.q.h.c.c
        public void c(int i, int i2) {
            WeakReference<q> weakReference = this.f1703a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            b.b.m.c.a.a(q.this.TAG, "---onPositionChanged---positionX: " + i + ", positionY: " + i2);
            this.f1703a.get().p(i, i2);
        }
    }

    public q(Context context) {
        super(context);
        this.g = 0;
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return R.layout.dialog_harman_sound_fade;
    }

    @Override // b.b.m.e.f
    public void initData(Context context) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // b.b.m.e.f
    public void initViews(Context context) {
        this.f1694a = (TextView) this.mRootView.findViewById(R.id.tv_title_dialog_common_titles);
        this.f1695b = this.mRootView.findViewById(R.id.iv_back_dialog_common_title);
        this.f1696c = (Button) this.mRootView.findViewById(R.id.btn_title_right_dialog_common_title);
        this.f1698e = this.mRootView.findViewById(R.id.ll_bg_dialog_harman_sound_field);
        this.f1697d = (SoundFieldView) this.mRootView.findViewById(R.id.sfv_harman_sound_fade);
        this.f1696c.setVisibility(0);
        this.f1696c.setOnClickListener(new a());
        this.f1695b.setOnClickListener(new b());
        if (this.f1699f == null) {
            this.f1699f = new d(this);
        }
        this.f1697d.setOnSoundFieldChanhedCallback(this.f1699f);
        this.f1697d.setRangeOfWidthAndHeight(20, 20);
        int[] iArrC = ((b.b.g.n.j.l) mo7getP()).c();
        this.f1697d.i(iArrC[0], iArrC[1]);
    }

    public final void l() {
        b.b.m.c.a.a(this.TAG, "----dialogToResetHarmanSoundFade---");
        WeakReference<Context> weakReference = this.ref;
        if (weakReference == null || weakReference.get() == null || !(this.ref.get() instanceof Activity)) {
            return;
        }
        Activity activity = (Activity) this.ref.get();
        b.b.g.q.d.u.b.k(activity, activity.getString(R.string.dilink_reset), activity.getString(R.string.msg_sound_fade_dialog_harman_sound), activity.getString(R.string.dialog_btn_ok), new c());
    }

    @Override // b.b.m.e.f
    public void loadData() {
        super.loadData();
        m(2);
    }

    public final void m(int i) {
        this.g = i;
    }

    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: n, reason: merged with bridge method [inline-methods] */
    public b.b.g.n.j.l newP() {
        return new b.b.g.n.j.l();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void p(int i, int i2) {
        ((b.b.g.n.j.l) mo7getP()).e(i, i2);
        SoundFieldView soundFieldView = this.f1697d;
        if (soundFieldView != null) {
            soundFieldView.i(i, i2);
        }
    }

    public void setTitle(String str) {
        TextView textView = this.f1694a;
        if (textView != null) {
            if (TextUtils.isEmpty(str)) {
                str = "";
            }
            textView.setText(str);
        }
    }
}
