package b.b.g.q.f.b;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import b.b.m.f.d.d;
import com.byd.carsettings.R;
import com.byd.carsettings.ui.dialog.CustomViewDialog;
import com.byd.carsettings.ui.widget.SoundFieldView;
import java.lang.ref.WeakReference;
import java.util.Arrays;

/* JADX INFO: compiled from: GeneralSoundFieldPager.java */
/* JADX INFO: loaded from: classes2.dex */
public class n extends b.b.m.e.a<b.b.g.n.j.j> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public View f1668a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ViewGroup f1669b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public TextView f1670c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Button f1671d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public SoundFieldView f1672e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public b.b.g.q.h.c.c f1673f;
    public volatile int g;
    public CustomViewDialog h;
    public volatile int i;
    public volatile int j;

    /* JADX INFO: compiled from: GeneralSoundFieldPager.java */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            n.this.q();
        }
    }

    /* JADX INFO: compiled from: GeneralSoundFieldPager.java */
    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (n.this.mCallback != null) {
                n.this.mCallback.onBackPress();
            }
            n.this.finish();
            if (n.this.h != null) {
                n.this.h.dismiss();
            }
        }
    }

    /* JADX INFO: compiled from: GeneralSoundFieldPager.java */
    public class c implements d.a {
        public c() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // b.b.m.f.d.d.a
        public void onOKClicked() {
            int[] iArrD = ((b.b.g.n.j.j) n.this.mo7getP()).d();
            b.b.m.c.a.a(n.this.TAG, "dialogToResetSoundField", "--onOKClicked--position: " + Arrays.toString(iArrD));
            n.this.i = -1;
            n.this.j = -1;
            n.this.f1672e.l();
        }
    }

    /* JADX INFO: compiled from: GeneralSoundFieldPager.java */
    public class d implements b.b.g.q.h.c.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference<n> f1677a;

        public d(n nVar) {
            this.f1677a = new WeakReference<>(nVar);
        }

        @Override // b.b.g.q.h.c.c
        public void a(int i, int i2) {
            b.b.m.c.a.a(n.this.TAG, "onTouchStop", ", positionX: " + i + ", positionY: " + i2);
            this.f1677a.get().s(i, i2);
        }

        @Override // b.b.g.q.h.c.c
        public void b(int i, int i2) {
            this.f1677a.get().s(i, i2);
        }
    }

    public n(Context context, CustomViewDialog customViewDialog) {
        super(context);
        this.g = 0;
        this.i = -1;
        this.j = -1;
        this.h = customViewDialog;
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return b.b.g.o.i.b.n();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // b.b.m.e.f
    public void initData(Context context) {
        this.f1670c.setText(context.getString(R.string.sound_field_balance_sound));
        this.f1671d.setText(context.getString(R.string.dilink_reset));
        this.f1671d.setVisibility(0);
        int[] iArrC = ((b.b.g.n.j.j) mo7getP()).c();
        b.b.m.c.a.a(this.TAG, "initData", "position: " + Arrays.toString(iArrC));
        this.f1672e.i(iArrC[0], iArrC[1]);
        this.f1668a.setBackgroundResource(b.b.g.o.i.b.m());
    }

    @Override // b.b.m.e.f
    public void initViews(Context context) {
        this.f1669b = (ViewGroup) this.mRootView.findViewById(R.id.ll_title_dialog_common_title);
        this.f1670c = (TextView) this.mRootView.findViewById(R.id.tv_title_dialog_common_titles);
        View viewFindViewById = this.mRootView.findViewById(R.id.iv_back_dialog_common_title);
        this.f1671d = (Button) this.mRootView.findViewById(R.id.btn_title_right_dialog_common_title);
        this.f1668a = this.mRootView.findViewById(R.id.bg_dialog_dirac_sound_field);
        SoundFieldView soundFieldView = (SoundFieldView) this.mRootView.findViewById(R.id.sfv_dirac_sound_field);
        this.f1672e = soundFieldView;
        soundFieldView.setLayoutDirection(0);
        this.f1671d.setOnClickListener(new a());
        viewFindViewById.setOnClickListener(new b());
        if (this.f1673f == null) {
            this.f1673f = new d(this);
        }
        this.f1672e.setOnSoundFieldChanhedCallback(this.f1673f);
        this.f1672e.setRangeOfWidthAndHeight(20, 20);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
    }

    @Override // b.b.m.e.a, b.b.m.e.f
    public void onDestroy() {
        super.onDestroy();
        b.b.m.f.d.d.c(n.class.getName());
    }

    @Override // b.b.m.e.f
    public void onLtrRudderR(boolean z) {
        setViewGroupRtl(this.f1669b, true);
    }

    @Override // b.b.m.e.f
    public void onRtlRudderL(boolean z) {
        setViewGroupRtl(this.f1669b, false);
    }

    public final void q() {
        b.b.m.c.a.a(this.TAG, "dialogToResetSoundField");
        WeakReference<Context> weakReference = this.ref;
        if (weakReference == null || weakReference.get() == null || !(this.ref.get() instanceof Activity)) {
            return;
        }
        Activity activity = (Activity) this.ref.get();
        b.b.g.q.d.u.b.k(activity, activity.getString(R.string.dilink_reset), activity.getString(R.string.msg_dialog_sound_balance), activity.getString(R.string.dialog_btn_ok), new c());
    }

    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: r, reason: merged with bridge method [inline-methods] */
    public b.b.g.n.j.j newP() {
        return new b.b.g.n.j.j();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void s(int i, int i2) {
        if (this.i == i && this.j == i2) {
            return;
        }
        b.b.m.c.a.a(this.TAG, "onSoundFieldPositionOperation", ", positionX: " + i + ", positionY: " + i2);
        this.i = i;
        this.j = i2;
        ((b.b.g.n.j.j) mo7getP()).e(this.i, this.j);
    }
}
