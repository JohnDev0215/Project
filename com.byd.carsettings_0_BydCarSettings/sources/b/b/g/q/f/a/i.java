package b.b.g.q.f.a;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.byd.base.view.BydSwitch;
import com.byd.carsettings.R;
import com.byd.carsettings.model.CommonSettingItem;
import com.byd.carsettings.ui.pager.common.DynamicColorsDialog;
import com.byd.carsettings.ui.pager.common.MusicDialog;
import com.byd.mvp.model.BydData;
import com.byd.mvp.ui.widget.TintImageView;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: Switch2Card.java */
/* JADX INFO: loaded from: classes2.dex */
public class i extends c<b.b.g.n.i.h> implements MusicDialog.a, DynamicColorsDialog.a {
    public MusicDialog A;
    public TextView h;
    public TextView i;
    public TextView j;
    public TextView k;
    public TintImageView l;
    public TintImageView m;
    public BydSwitch n;
    public BydSwitch o;
    public CommonSettingItem p;
    public b.b.g.n.i.h q;
    public volatile int r;
    public volatile int s;
    public Activity t;
    public View u;
    public View v;
    public int w;
    public int x;
    public Fragment y;
    public DynamicColorsDialog z;

    /* JADX INFO: compiled from: Switch2Card.java */
    public class a implements CompoundButton.OnCheckedChangeListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public WeakReference<i> f1541a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1542b;

        public a(i iVar, int i) {
            this.f1542b = 0;
            this.f1541a = new WeakReference<>(iVar);
            this.f1542b = i;
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            WeakReference<i> weakReference = this.f1541a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.f1541a.get().H(z, this.f1542b);
        }
    }

    public i(Activity activity, Fragment fragment) {
        super(activity);
        this.r = 0;
        this.s = 0;
        this.t = activity;
        this.y = fragment;
    }

    public final String E(Activity activity, int i) {
        String[] stringArray;
        return (activity != null && (stringArray = activity.getResources().getStringArray(R.array.ial_dynamic_colors_array)) != null && stringArray.length > 0 && i < stringArray.length && i >= 0) ? stringArray[i] : "";
    }

    public final String F(Activity activity, int i) {
        String[] stringArray;
        return (activity != null && (stringArray = activity.getResources().getStringArray(R.array.ial_music_rhythm_array)) != null && stringArray.length > 0 && i < stringArray.length && i >= 0) ? stringArray[i] : "";
    }

    @Override // b.b.g.q.f.a.c, b.b.m.e.h
    /* JADX INFO: renamed from: G, reason: merged with bridge method [inline-methods] */
    public b.b.g.n.i.h newP() {
        b.b.g.n.i.h hVar = new b.b.g.n.i.h();
        this.q = hVar;
        return hVar;
    }

    public final void H(boolean z, int i) {
        if (i == 0) {
            this.q.o(z);
            I(this.j, this.l, z, F(this.t, this.w));
            if (z && this.o.isChecked()) {
                this.o.setChecked(false);
                H(false, 1);
                return;
            }
            return;
        }
        if (i != 1) {
            return;
        }
        this.q.m(z);
        I(this.k, this.m, z, E(this.t, this.x));
        if (z && this.n.isChecked()) {
            this.n.setChecked(false);
            H(false, 0);
        }
    }

    public final void I(TextView textView, TintImageView tintImageView, boolean z, String str) {
        Resources resources = textView.getResources();
        tintImageView.d(z ? 1 : 0);
        int color = resources.getColor(R.color.text_color_item_left_dilink_link);
        if (z) {
            textView.setText(str);
        } else {
            color = resources.getColor(R.color.color_invalid);
        }
        textView.setTextColor(color);
        textView.setClickable(z);
        textView.setFocusable(z);
    }

    public final void J() {
        DynamicColorsDialog dynamicColorsDialogU = DynamicColorsDialog.u(this.x);
        this.z = dynamicColorsDialogU;
        dynamicColorsDialogU.setOnOKClickedListener(this);
        Fragment fragment = this.y;
        if (fragment == null || fragment.getFragmentManager() == null) {
            return;
        }
        this.z.show(this.y.getFragmentManager(), "DynamicColorsDialog");
    }

    public final void K() {
        MusicDialog musicDialogU = MusicDialog.u(this.w);
        this.A = musicDialogU;
        musicDialogU.setOnOKClickedListener(this);
        Fragment fragment = this.y;
        if (fragment == null || fragment.getFragmentManager() == null) {
            return;
        }
        this.A.show(this.y.getFragmentManager(), "MusicDialog");
    }

    @Override // com.byd.carsettings.ui.pager.common.DynamicColorsDialog.a
    public void a(int i) {
        this.k.setText(E(this.t, i));
        this.q.n(i);
        this.p.setIndex1(this.s);
    }

    @Override // com.byd.carsettings.ui.pager.common.MusicDialog.a
    public void b(int i) {
        this.j.setText(F(this.t, i));
        this.q.q(i);
        this.p.setIndex(i);
    }

    @Override // b.b.g.q.f.a.c, b.b.m.e.e
    public void e(Context context) {
        this.j.setOnClickListener(this);
        this.k.setOnClickListener(this);
        this.n.setOnCheckedChangeListener(new a(this, 0));
        this.o.setOnCheckedChangeListener(new a(this, 1));
    }

    @Override // b.b.g.q.f.a.c, b.b.m.e.h
    public int getLayoutId() {
        return R.layout.card_2_switch;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // b.b.g.q.f.a.c, b.b.m.e.e
    public void k(Context context) {
        super.k(context);
        ((b.b.g.n.i.h) mo7getP()).i();
        this.u = this.f2102c.findViewById(R.id.ll_0_card_2_switch);
        this.v = this.f2102c.findViewById(R.id.ll_0_card_1_switch);
        this.h = (TextView) this.f2102c.findViewById(R.id.tv_title_0_card_2_switch);
        this.i = (TextView) this.f2102c.findViewById(R.id.tv_title_1_card_2_switch);
        this.j = (TextView) this.f2102c.findViewById(R.id.tv_value_0_card_2_switch);
        this.k = (TextView) this.f2102c.findViewById(R.id.tv_value_1_card_2_switch);
        this.l = (TintImageView) this.f2102c.findViewById(R.id.iv_arrow_0_card_2_switch);
        this.m = (TintImageView) this.f2102c.findViewById(R.id.iv_arrow_1_card_2_switch);
        this.n = (BydSwitch) this.f2102c.findViewById(R.id.switch_0_card_2_switch);
        this.o = (BydSwitch) this.f2102c.findViewById(R.id.switch_1_card_2_switch);
    }

    @Override // b.b.m.e.e
    public void n(BydData bydData, int i) {
        if (bydData == null || !(bydData instanceof CommonSettingItem)) {
            return;
        }
        CommonSettingItem commonSettingItem = (CommonSettingItem) bydData;
        this.p = commonSettingItem;
        this.h.setText(commonSettingItem.getTitle());
        this.i.setText(this.p.getTitle1());
        this.u.setVisibility(this.q.h() ? 0 : 8);
        this.v.setVisibility(this.q.f() ? 0 : 8);
        this.r = this.q.d();
        this.w = this.r;
        boolean zL = this.q.l();
        this.n.setChecked(zL, false);
        I(this.j, this.l, zL, F(this.t, this.r));
        this.s = this.q.c();
        this.x = this.s;
        boolean zJ = this.q.j();
        this.o.setChecked(zJ, false);
        I(this.k, this.m, zJ, E(this.t, this.s));
    }

    @Override // b.b.g.q.f.a.c, android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_value_0_card_2_switch /* 2131298826 */:
                K();
                break;
            case R.id.tv_value_1_card_2_switch /* 2131298827 */:
                J();
                break;
        }
    }

    @Override // b.b.g.q.f.a.c
    public void p(int i, b.b.a.c.a aVar) {
        int iE;
        super.p(i, aVar);
        if (b.b.g.r.e.e(aVar)) {
            iE = this.q.c();
        } else {
            iE = this.q.e(i);
            this.x = iE;
        }
        I(this.k, this.m, this.o.isChecked(), E(this.t, iE));
        DynamicColorsDialog dynamicColorsDialog = this.z;
        if (dynamicColorsDialog == null || !dynamicColorsDialog.isVisible()) {
            return;
        }
        this.z.v(iE);
    }

    @Override // b.b.g.q.f.a.c
    public void q(int i, b.b.a.c.a aVar) {
        super.q(i, aVar);
        boolean zJ = b.b.g.r.e.e(aVar) ? this.q.j() : i == 1;
        this.o.setChecked(zJ, false);
        I(this.k, this.m, zJ, E(this.t, this.x));
    }

    @Override // b.b.g.q.f.a.c
    public void s(int i, b.b.a.c.a aVar) {
        int iG;
        super.s(i, aVar);
        if (b.b.g.r.e.e(aVar)) {
            iG = this.q.d();
        } else {
            iG = this.q.g(i);
            this.w = iG;
        }
        I(this.j, this.l, this.n.isChecked(), F(this.t, iG));
        MusicDialog musicDialog = this.A;
        if (musicDialog == null || !musicDialog.isVisible()) {
            return;
        }
        this.A.v(iG);
    }

    @Override // b.b.g.q.f.a.c
    public void t(int i, b.b.a.c.a aVar) {
        super.t(i, aVar);
        boolean zL = b.b.g.r.e.e(aVar) ? this.q.l() : i == 1;
        this.n.setChecked(zL, false);
        I(this.j, this.l, zL, F(this.t, this.w));
    }
}
