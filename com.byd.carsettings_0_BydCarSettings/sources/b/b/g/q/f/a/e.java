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
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: compiled from: RadioButton3Card.java */
/* JADX INFO: loaded from: classes2.dex */
public class e extends c<b.b.g.n.i.d> {
    public TextView h;
    public SegmentedButtonGroup i;
    public CommonSettingItem j;
    public b.b.g.n.i.d k;

    /* JADX INFO: compiled from: RadioButton3Card.java */
    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f1528a;

        static {
            int[] iArr = new int[CommonItemId.values().length];
            f1528a = iArr;
            try {
                iArr[CommonItemId.DARK_LIGHT_MODE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    /* JADX INFO: compiled from: RadioButton3Card.java */
    public class b implements SegmentedButtonGroup.f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public WeakReference<e> f1529a;

        public b(e eVar) {
            this.f1529a = new WeakReference<>(eVar);
        }

        @Override // com.byd.base.view.segmentedbutton.SegmentedButtonGroup.f
        public void onPositionChanged(View view, View view2, int i) {
            WeakReference<e> weakReference = this.f1529a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.f1529a.get().G(i);
        }
    }

    public e(Activity activity) {
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
    public b.b.g.n.i.d newP() {
        b.b.g.n.i.d dVar = new b.b.g.n.i.d();
        this.k = dVar;
        return dVar;
    }

    public final void G(int i) {
        CommonSettingItem commonSettingItem = this.j;
        if (commonSettingItem != null && a.f1528a[commonSettingItem.getOriginalId().ordinal()] == 1) {
            this.k.d(i);
        }
    }

    @Override // b.b.g.q.f.a.c, b.b.m.e.e
    public void e(Context context) {
        this.i.setOnPositionChangedListener(new b(this));
    }

    @Override // b.b.g.q.f.a.c, b.b.m.e.h
    public int getLayoutId() {
        return R.layout.card_radio_button_3;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // b.b.g.q.f.a.c, b.b.m.e.e
    public void k(Context context) {
        super.k(context);
        ((b.b.g.n.i.d) mo7getP()).c();
        this.h = (TextView) this.f2102c.findViewById(R.id.tv_title_card_radio_button_3);
        this.i = (SegmentedButtonGroup) this.f2102c.findViewById(R.id.srg_card_radio_button_3);
    }

    @Override // b.b.m.e.e
    public void n(BydData bydData, int i) {
        if (bydData == null || !(bydData instanceof CommonSettingItem)) {
            return;
        }
        CommonSettingItem commonSettingItem = (CommonSettingItem) bydData;
        this.j = commonSettingItem;
        this.h.setText(commonSettingItem.getTitle());
        this.i.setButtonText(this.j.getRbtnTextArr());
        int i2 = a.f1528a[this.j.getOriginalId().ordinal()];
        E(0);
    }

    @Override // b.b.g.q.f.a.c
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(b.b.a.c.a aVar) {
        super.onGetMessage(aVar);
        if (aVar.h() != 230000) {
            return;
        }
        b.b.m.c.a.a(this.f2100a, "onGetMessage:" + aVar);
        if (this.j.getOriginalId() == CommonItemId.DARK_LIGHT_MODE) {
            E(aVar.c());
        }
    }
}
