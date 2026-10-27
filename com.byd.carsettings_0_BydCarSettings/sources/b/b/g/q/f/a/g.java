package b.b.g.q.f.a;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.byd.base.view.BydSwitch;
import com.byd.carsettings.R;
import com.byd.carsettings.model.CommonSettingItem;
import com.byd.mvp.model.BydData;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: SingleSwitchCard.java */
/* JADX INFO: loaded from: classes2.dex */
public class g extends b.b.m.e.e<b.b.g.n.i.f> {
    public TextView h;
    public BydSwitch i;
    public CommonSettingItem j;

    /* JADX INFO: compiled from: SingleSwitchCard.java */
    public class a implements CompoundButton.OnCheckedChangeListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public WeakReference<g> f1535a;

        public a(g gVar) {
            this.f1535a = new WeakReference<>(gVar);
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            WeakReference<g> weakReference = this.f1535a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.f1535a.get().r(z);
        }
    }

    public g(Activity activity) {
        super(activity);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // b.b.m.e.e
    public void e(Context context) {
        ((b.b.g.n.i.f) mo7getP()).c();
        this.i.setOnCheckedChangeListener(new a(this));
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return R.layout.card_single_switch;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // b.b.m.e.e
    public void k(Context context) {
        ((b.b.g.n.i.f) mo7getP()).c();
        this.h = (TextView) this.f2102c.findViewById(R.id.tv_title_card_single_switch);
        this.i = (BydSwitch) this.f2102c.findViewById(R.id.switch_card_single_switch);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // b.b.m.e.e
    public void n(BydData bydData, int i) {
        if (bydData == null || !(bydData instanceof CommonSettingItem)) {
            return;
        }
        CommonSettingItem commonSettingItem = (CommonSettingItem) bydData;
        this.j = commonSettingItem;
        this.h.setText(commonSettingItem.getTitle());
        this.i.setChecked(((b.b.g.n.i.f) mo7getP()).d(), false);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
    }

    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public b.b.g.n.i.f newP() {
        return new b.b.g.n.i.f();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void r(boolean z) {
        ((b.b.g.n.i.f) mo7getP()).e(z);
    }
}
