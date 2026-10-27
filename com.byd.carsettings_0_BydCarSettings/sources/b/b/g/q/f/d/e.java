package b.b.g.q.f.d;

import android.content.Context;
import b.b.g.q.f.d.i;
import com.byd.base.view.BydSwitch;
import com.byd.carsettings.R;
import com.byd.carsettings.sound.model.DiracMode;

/* JADX INFO: compiled from: DiracSimplePager.java */
/* JADX INFO: loaded from: classes2.dex */
public class e extends c {
    public e(Context context) {
        super(context);
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return R.layout.subview_sound_dirac_simple;
    }

    @Override // b.b.g.q.f.d.c, b.b.g.q.f.d.i, b.b.m.e.f
    public void initViews(Context context) {
        super.initViews(context);
        BydSwitch bydSwitch = (BydSwitch) this.mRootView.findViewById(R.id.switch_enable_subview_sound_dirac_simple);
        bydSwitch.setChecked(this.f1754f == DiracMode.SIMPLE_OPEN, false);
        bydSwitch.setOnCheckedChangeListener(new i.d(this));
    }
}
