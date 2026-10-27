package b.b.g.q.f.b;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import b.b.m.f.d.d;
import com.byd.carsettings.R;
import com.byd.carsettings.ui.widget.SoundFieldView;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: DiracSoundFieldPager.java */
/* JADX INFO: loaded from: classes2.dex */
public class j extends b.b.m.e.a<b.b.g.n.j.f> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public TextView f1624a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Button f1625b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public SoundFieldView f1626c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public b.b.g.q.h.c.c f1627d;

    /* JADX INFO: compiled from: DiracSoundFieldPager.java */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            j.this.k();
        }
    }

    /* JADX INFO: compiled from: DiracSoundFieldPager.java */
    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (j.this.mCallback != null) {
                j.this.mCallback.onBackPress();
            }
            j.this.finish();
        }
    }

    /* JADX INFO: compiled from: DiracSoundFieldPager.java */
    public class c implements d.a {
        public c() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // b.b.m.f.d.d.a
        public void onOKClicked() {
            int[] iArrD = ((b.b.g.n.j.f) j.this.mo7getP()).d();
            j.this.f1626c.i(iArrD[0], iArrD[1]);
        }
    }

    /* JADX INFO: compiled from: DiracSoundFieldPager.java */
    public class d implements b.b.g.q.h.c.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference<j> f1631a;

        public d(j jVar) {
            this.f1631a = new WeakReference<>(jVar);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // b.b.g.q.h.c.c
        public void c(int i, int i2) {
            WeakReference<j> weakReference = this.f1631a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            b.b.m.c.a.a(j.this.TAG, "---onPositionChanged---positionX: " + i + ", positionY: " + i2);
            ((b.b.g.n.j.f) this.f1631a.get().mo7getP()).e(i, i2);
        }
    }

    public j(Context context) {
        super(context);
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return R.layout.dialog_dirac_sound_field;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // b.b.m.e.f
    public void initData(Context context) {
        this.f1624a.setText(context.getString(R.string.sound_field_balance_sound));
        this.f1625b.setText(context.getString(R.string.dilink_reset));
        this.f1625b.setVisibility(0);
        int[] iArrC = ((b.b.g.n.j.f) mo7getP()).c();
        this.f1626c.i(iArrC[0], iArrC[1]);
    }

    @Override // b.b.m.e.f
    public void initViews(Context context) {
        this.f1624a = (TextView) this.mRootView.findViewById(R.id.tv_title_dialog_common_titles);
        View viewFindViewById = this.mRootView.findViewById(R.id.iv_back_dialog_common_title);
        this.f1625b = (Button) this.mRootView.findViewById(R.id.btn_title_right_dialog_common_title);
        this.f1626c = (SoundFieldView) this.mRootView.findViewById(R.id.sfv_dirac_sound_field);
        this.f1625b.setOnClickListener(new a());
        viewFindViewById.setOnClickListener(new b());
        if (this.f1627d == null) {
            this.f1627d = new d(this);
        }
        this.f1626c.setOnSoundFieldChanhedCallback(this.f1627d);
        this.f1626c.setRangeOfWidthAndHeight(14, 14);
    }

    public final void k() {
        b.b.m.c.a.a(this.TAG, "----dialogToResetSoundField---");
        WeakReference<Context> weakReference = this.ref;
        if (weakReference == null || weakReference.get() == null || !(this.ref.get() instanceof Activity)) {
            return;
        }
        Activity activity = (Activity) this.ref.get();
        b.b.g.q.d.u.b.k(activity, activity.getString(R.string.dilink_reset), activity.getString(R.string.msg_dialog_sound_balance), activity.getString(R.string.dialog_btn_ok), new c());
    }

    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: l, reason: merged with bridge method [inline-methods] */
    public b.b.g.n.j.f newP() {
        return new b.b.g.n.j.f();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
    }

    @Override // b.b.m.e.a, b.b.m.e.f
    public void onDestroy() {
        super.onDestroy();
        b.b.m.f.d.d.c(j.class.getName());
    }
}
