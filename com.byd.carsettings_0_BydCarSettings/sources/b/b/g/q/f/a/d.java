package b.b.g.q.f.a;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.byd.base.view.segmentedbutton.SegmentedButtonGroup;
import com.byd.carsettings.R;
import com.byd.carsettings.model.CommonItemId;
import com.byd.carsettings.model.CommonSettingItem;
import com.byd.mvp.model.BydData;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: RadioButton2Card.java */
/* JADX INFO: loaded from: classes2.dex */
public class d extends c<b.b.g.n.i.c> {
    public TextView h;
    public SegmentedButtonGroup i;
    public b.b.g.n.i.c j;
    public CommonSettingItem k;

    /* JADX INFO: compiled from: RadioButton2Card.java */
    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f1525a;

        static {
            int[] iArr = new int[CommonItemId.values().length];
            f1525a = iArr;
            try {
                iArr[CommonItemId.ENERGY_FEEDBACK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1525a[CommonItemId.POWER_STEERING_MODE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1525a[CommonItemId.ACTIVE_SUSPENSION.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1525a[CommonItemId.SIMULATION_ENGINE_SOUND.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX INFO: compiled from: RadioButton2Card.java */
    public class b implements SegmentedButtonGroup.f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public WeakReference<d> f1526a;

        public b(d dVar) {
            this.f1526a = new WeakReference<>(dVar);
        }

        @Override // com.byd.base.view.segmentedbutton.SegmentedButtonGroup.f
        public void onPositionChanged(View view, View view2, int i) {
            WeakReference<d> weakReference = this.f1526a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.f1526a.get().G(i);
        }
    }

    public d(Activity activity) {
        super(activity);
    }

    public final void E(int i) {
        if (i < 0 || i >= this.i.getButtonChildCount()) {
            return;
        }
        this.i.setPosition(i);
    }

    @Override // b.b.g.q.f.a.c, b.b.m.e.h
    /* JADX INFO: renamed from: F, reason: merged with bridge method [inline-methods] */
    public b.b.g.n.i.c newP() {
        b.b.g.n.i.c cVar = new b.b.g.n.i.c();
        this.j = cVar;
        return cVar;
    }

    public final void G(int i) {
        int i2 = a.f1525a[this.k.getOriginalId().ordinal()];
        if (i2 == 1) {
            this.j.j(i);
            return;
        }
        if (i2 == 2) {
            this.j.l(i);
        } else if (i2 == 3) {
            this.j.i(i);
        } else {
            if (i2 != 4) {
                return;
            }
            this.j.m(i);
        }
    }

    @Override // b.b.g.q.f.a.c, b.b.m.e.e
    public void e(Context context) {
        this.i.setOnPositionChangedListener(new b(this));
    }

    @Override // b.b.g.q.f.a.c, b.b.m.e.h
    public int getLayoutId() {
        return R.layout.card_radio_button_2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // b.b.g.q.f.a.c, b.b.m.e.e
    public void k(Context context) {
        super.k(context);
        ((b.b.g.n.i.c) mo7getP()).h();
        this.h = (TextView) this.f2102c.findViewById(R.id.tv_title_card_radio_button_2);
        this.i = (SegmentedButtonGroup) this.f2102c.findViewById(R.id.srg_card_radio_button_2);
    }

    @Override // b.b.m.e.e
    public void n(BydData bydData, int i) {
        if (bydData == null || !(bydData instanceof CommonSettingItem)) {
            return;
        }
        CommonSettingItem commonSettingItem = (CommonSettingItem) bydData;
        this.k = commonSettingItem;
        this.h.setText(commonSettingItem.getTitle());
        this.i.setButtonText(this.k.getRbtnTextArr());
        int iE = 0;
        int i2 = a.f1525a[this.k.getOriginalId().ordinal()];
        if (i2 == 1) {
            iE = this.j.e();
        } else if (i2 == 2) {
            iE = this.j.f();
        } else if (i2 == 3) {
            iE = this.j.d();
        } else if (i2 == 4) {
            iE = this.j.g();
        }
        E(iE);
    }

    @Override // b.b.g.q.f.a.c
    public void w(int i, b.b.a.c.a aVar) {
        super.w(i, aVar);
        if (this.k.getOriginalId() == CommonItemId.POWER_STEERING_MODE) {
            int iF = i == 1 ? 0 : 1;
            if (b.b.g.r.e.e(aVar)) {
                iF = this.j.f();
            }
            E(iF);
        }
    }

    @Override // b.b.g.q.f.a.c
    public void x(int i, b.b.a.c.a aVar) {
        super.x(i, aVar);
        if (this.k.getOriginalId() == CommonItemId.ACTIVE_SUSPENSION) {
            int iD = i == 1 ? 0 : 1;
            if (b.b.g.r.e.e(aVar)) {
                iD = this.j.d();
            }
            E(iD);
        }
    }

    @Override // b.b.g.q.f.a.c
    public void y(int i, b.b.a.c.a aVar) {
        super.y(i, aVar);
        if (this.k.getOriginalId() == CommonItemId.ENERGY_FEEDBACK) {
            int iE = i == 2 ? 0 : 1;
            if (b.b.g.r.e.e(aVar)) {
                iE = this.j.e();
            }
            E(iE);
        }
    }

    @Override // b.b.g.q.f.a.c
    public void z(int i, b.b.a.c.a aVar) {
        super.z(i, aVar);
        if (this.k.getOriginalId() == CommonItemId.SIMULATION_ENGINE_SOUND) {
            int iC = this.j.c(i);
            if (b.b.g.r.e.e(aVar)) {
                iC = this.j.g();
            }
            E(iC);
        }
    }
}
