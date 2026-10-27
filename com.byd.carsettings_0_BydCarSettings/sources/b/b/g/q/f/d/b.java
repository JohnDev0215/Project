package b.b.g.q.f.d;

import android.content.Context;
import android.view.View;
import com.byd.base.view.segmentedbutton.SegmentedButtonGroup;
import com.byd.carsettings.R;
import com.byd.carsettings.sound.model.DiracMode;

/* JADX INFO: compiled from: DiracLivePager.java */
/* JADX INFO: loaded from: classes2.dex */
public class b extends c {

    /* JADX INFO: compiled from: DiracLivePager.java */
    public class a implements SegmentedButtonGroup.f {
        public a() {
        }

        @Override // com.byd.base.view.segmentedbutton.SegmentedButtonGroup.f
        public void onPositionChanged(View view, View view2, int i) {
            b.this.r(i);
        }
    }

    public b(Context context) {
        super(context);
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return R.layout.subview_sound_dirac_live;
    }

    @Override // b.b.g.q.f.d.c, b.b.g.q.f.d.i, b.b.m.e.f
    public void initViews(Context context) {
        super.initViews(context);
        SegmentedButtonGroup segmentedButtonGroup = (SegmentedButtonGroup) this.mRootView.findViewById(R.id.srg_sound_mode_subview_sound_dirac_live);
        segmentedButtonGroup.setPosition(m(this.f1754f));
        segmentedButtonGroup.setOnPositionChangedListener(new a());
    }

    public void r(int i) {
        if (i == 0) {
            q(DiracMode.LIVE_HIFI);
        } else if (i == 1) {
            q(DiracMode.LIVE_STAGE);
        } else {
            if (i != 2) {
                return;
            }
            q(DiracMode.LIVE_CLOSE);
        }
    }
}
