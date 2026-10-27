package b.b.g.q.f.d;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.CompoundButton;
import b.b.g.q.f.d.i;
import com.byd.base.view.BydSwitch;
import com.byd.carsettings.R;
import com.byd.carsettings.sound.model.DiracMode;
import com.byd.carsettings.ui.widget.LinearItem;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: DiracPager.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class c extends i {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public DiracMode f1754f;
    public LinearItem g;
    public LinearItem h;

    /* JADX INFO: compiled from: DiracPager.java */
    public class a implements b.b.m.f.f.b.a {
        public a() {
        }

        @Override // b.b.m.f.f.b.a
        public void a(int i, String str) {
            b.b.m.c.a.a(c.this.TAG, "----onRadioBtnChecked---index = " + i);
            LinearItem linearItem = c.this.g;
            if (str == null) {
                str = "";
            }
            linearItem.setRightText(str);
        }
    }

    /* JADX INFO: compiled from: DiracPager.java */
    public static /* synthetic */ class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f1756a;

        static {
            int[] iArr = new int[DiracMode.values().length];
            f1756a = iArr;
            try {
                iArr[DiracMode.LIVE_HIFI.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1756a[DiracMode.PERFORMANCE_DYNAMIC.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1756a[DiracMode.LIVE_STAGE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1756a[DiracMode.PERFORMANCE_NICAM.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1756a[DiracMode.LIVE_CLOSE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1756a[DiracMode.PERFORMANCE_CLOSE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public c(Context context) {
        super(context);
    }

    @Override // b.b.g.q.f.d.i
    public void b(int i) {
        BydLogHelper.logV(this.TAG + "----checkSubindexOnResume");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // b.b.g.q.f.d.i
    public void e(CompoundButton compoundButton, boolean z) {
        b.b.m.c.a.a(this.TAG, "----onCheckedChanged---b = " + z);
        int id = compoundButton.getId();
        if (id != R.id.switch_dirac_loudness_sound_pager) {
            if (id != R.id.switch_enable_subview_sound_dirac_simple) {
                return;
            }
            q(z ? DiracMode.SIMPLE_OPEN : DiracMode.SIMPLE_CLOSE);
        } else {
            ((b.b.g.n.p.e) mo7getP()).B(z);
            i.b bVar = this.f1782c;
            if (bVar != null) {
                bVar.a(z);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // b.b.g.q.f.d.i, b.b.m.e.f
    public void initData(Context context) {
        BydSwitch bydSwitch = (BydSwitch) this.mRootView.findViewById(R.id.switch_dirac_loudness_sound_pager);
        bydSwitch.setChecked(((b.b.g.n.p.e) mo7getP()).x(), false);
        bydSwitch.setOnCheckedChangeListener(new i.d(this));
        this.g.setOnClickListener(this);
        this.h.setOnClickListener(this);
        this.g.setRightText(this.mRootView.getContext().getResources().getStringArray(R.array.music_style_list)[((b.b.g.n.p.e) mo7getP()).q()]);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // b.b.g.q.f.d.i, b.b.m.e.f
    public void initViews(Context context) {
        super.initViews(context);
        this.f1754f = ((b.b.g.n.p.e) mo7getP()).n();
        b.b.m.c.a.a(this.TAG, "----initViews---mMode = " + this.f1754f);
        this.g = (LinearItem) this.mRootView.findViewById(R.id.item_dirac_equalizer_sound_pager);
        this.h = (LinearItem) this.mRootView.findViewById(R.id.item_dirac_sound_field_balance_sound_pager);
    }

    public int m(DiracMode diracMode) {
        switch (b.f1756a[diracMode.ordinal()]) {
            case 1:
            case 2:
            default:
                return 0;
            case 3:
            case 4:
                return 1;
            case 5:
            case 6:
                return 2;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void n() {
        b.b.m.c.a.a(this.TAG, "----onEqualizerOperate---");
        WeakReference<Context> weakReference = this.ref;
        if (weakReference == null || weakReference.get() == null || !(this.ref.get() instanceof Activity)) {
            return;
        }
        Activity activity = (Activity) this.ref.get();
        this.g.setRightText(activity.getResources().getStringArray(R.array.music_style_list)[((b.b.g.n.p.e) mo7getP()).q()]);
        b.b.g.q.d.u.c.q(activity, new a());
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.item_dirac_equalizer_sound_pager /* 2131297119 */:
                n();
                break;
            case R.id.item_dirac_sound_field_balance_sound_pager /* 2131297120 */:
                p();
                break;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void p() {
        b.b.m.c.a.a(this.TAG, "----onSoundBalanceOperate---");
        WeakReference<Context> weakReference = this.ref;
        if (weakReference == null || weakReference.get() == null || !(this.ref.get() instanceof Activity)) {
            return;
        }
        Activity activity = (Activity) this.ref.get();
        ((b.b.g.n.p.e) mo7getP()).o();
        b.b.g.q.d.u.c.r(activity);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void q(DiracMode diracMode) {
        b.b.m.c.a.a(this.TAG, "---changeDiracMode---mode = " + diracMode);
        ((b.b.g.n.p.e) mo7getP()).y(diracMode);
        i.a aVar = this.f1781b;
        if (aVar != null) {
            aVar.a(diracMode);
        }
    }
}
