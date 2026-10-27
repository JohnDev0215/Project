package b.b.g.q.f.a;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import com.byd.base.view.segmentedbutton.SegmentedButtonGroup;
import com.byd.carsettings.R;
import com.byd.carsettings.model.CommonSettingItem;
import com.byd.mvp.model.BydData;
import java.lang.ref.WeakReference;
import java.util.Arrays;

/* JADX INFO: compiled from: SocSettingCard.java */
/* JADX INFO: loaded from: classes2.dex */
public class h extends c<b.b.g.n.i.g> {
    public TextView h;
    public TextView i;
    public SegmentedButtonGroup j;
    public SeekBar k;
    public b.b.g.n.i.g l;
    public CommonSettingItem m;
    public int n;

    /* JADX INFO: compiled from: SocSettingCard.java */
    public class a implements SegmentedButtonGroup.f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public WeakReference<h> f1537a;

        public a(h hVar) {
            this.f1537a = new WeakReference<>(hVar);
        }

        @Override // com.byd.base.view.segmentedbutton.SegmentedButtonGroup.f
        public void onPositionChanged(View view, View view2, int i) {
            WeakReference<h> weakReference = this.f1537a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.f1537a.get().K(i);
        }
    }

    /* JADX INFO: compiled from: SocSettingCard.java */
    public class b implements SeekBar.OnSeekBarChangeListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public WeakReference<h> f1539a;

        public b(h hVar) {
            this.f1539a = new WeakReference<>(hVar);
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            WeakReference<h> weakReference = this.f1539a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.f1539a.get().J(i, z);
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
            WeakReference<h> weakReference = this.f1539a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.f1539a.get().L();
        }
    }

    public h(Activity activity) {
        super(activity);
        this.n = 0;
    }

    @Override // b.b.g.q.f.a.c
    public void B(int i, b.b.a.c.a aVar) {
        super.B(i, aVar);
        if (b.b.g.r.e.e(aVar)) {
            int iC = this.l.c();
            G(iC);
            H(iC == 1);
        } else {
            int i2 = (i != 1 && i == 2) ? 1 : 0;
            this.j.setPosition(i2);
            H(i2 == 1);
        }
    }

    @Override // b.b.g.q.f.a.c
    public void C(int i, b.b.a.c.a aVar) {
        super.C(i, aVar);
        if (Build.VERSION.SDK_INT >= 26) {
            if (b.b.g.r.e.e(aVar)) {
                this.k.setProgress(this.n);
                this.i.setText(this.n + "%");
                return;
            }
            if (i < this.k.getMin()) {
                i = this.k.getMin();
            }
            if (i > this.k.getMax()) {
                i = this.k.getMax();
            }
            this.k.setProgress(i);
            this.i.setText(i + "%");
            this.n = i;
        }
    }

    public final void G(int i) {
        if (i < 0 || i >= this.j.getButtonChildCount()) {
            return;
        }
        this.j.setPosition(i);
    }

    public final void H(boolean z) {
        this.k.setThumb(ContextCompat.getDrawable(getContext(), z ? R.drawable.seekbar_thumb_background_lock : R.drawable.seekbar_thumb_background));
    }

    @Override // b.b.g.q.f.a.c, b.b.m.e.h
    /* JADX INFO: renamed from: I, reason: merged with bridge method [inline-methods] */
    public b.b.g.n.i.g newP() {
        b.b.g.n.i.g gVar = new b.b.g.n.i.g();
        this.l = gVar;
        return gVar;
    }

    public final void J(int i, boolean z) {
        if (z) {
            b.b.m.c.a.d(this.f2100a, "----onProgressChanged----progress: " + i);
            this.i.setText(i + "%");
        }
    }

    public final void K(int i) {
        H(i == 1);
        this.l.g(i);
    }

    public final void L() {
        this.l.h(this.k.getProgress(), this.n);
    }

    @Override // b.b.g.q.f.a.c, b.b.m.e.e
    public void e(Context context) {
        this.k.setOnSeekBarChangeListener(new b(this));
        this.j.setOnPositionChangedListener(new a(this));
    }

    @Override // b.b.g.q.f.a.c, b.b.m.e.h
    public int getLayoutId() {
        return R.layout.card_soc_setting;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // b.b.g.q.f.a.c, b.b.m.e.e
    public void k(Context context) {
        super.k(context);
        ((b.b.g.n.i.g) mo7getP()).f();
        this.h = (TextView) this.f2102c.findViewById(R.id.tv_title_card_soc_setting);
        this.i = (TextView) this.f2102c.findViewById(R.id.tv_progress_card_soc_setting);
        this.j = (SegmentedButtonGroup) this.f2102c.findViewById(R.id.srg_card_soc_setting);
        this.k = (SeekBar) this.f2102c.findViewById(R.id.sbar_card_soc_setting);
    }

    @Override // b.b.m.e.e
    @RequiresApi(api = 26)
    public void n(BydData bydData, int i) {
        if (bydData == null || !(bydData instanceof CommonSettingItem)) {
            return;
        }
        CommonSettingItem commonSettingItem = (CommonSettingItem) bydData;
        this.m = commonSettingItem;
        this.h.setText(commonSettingItem.getTitle());
        this.j.setButtonText(this.m.getRbtnTextArr());
        int iC = this.l.c();
        G(iC);
        int[] iArrE = this.l.e();
        b.b.m.c.a.d(this.f2100a, "----updateData----range: " + Arrays.toString(iArrE));
        this.k.setMin(iArrE[0]);
        this.k.setMax(iArrE[1]);
        int iD = this.l.d();
        this.n = iD;
        if (iD < iArrE[0]) {
            this.n = iArrE[0];
        } else if (iD > iArrE[1]) {
            this.n = iArrE[1];
        }
        H(iC == 1);
        this.k.setProgress(this.n);
        this.i.setText(this.n + "%");
    }
}
