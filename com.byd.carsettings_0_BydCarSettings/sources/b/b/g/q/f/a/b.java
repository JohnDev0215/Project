package b.b.g.q.f.a;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.widget.SeekBar;
import android.widget.TextView;
import com.byd.base.view.BydSeekBar;
import com.byd.carsettings.R;
import com.byd.carsettings.model.CommonSettingItem;
import com.byd.mvp.model.BydData;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: AtmLampColorsCard.java */
/* JADX INFO: loaded from: classes2.dex */
public class b extends c<b.b.g.n.i.b> {
    public TextView h;
    public BydSeekBar i;
    public b.b.g.n.i.b j;
    public CommonSettingItem k;
    public int l;

    /* JADX INFO: compiled from: AtmLampColorsCard.java */
    public class a implements SeekBar.OnSeekBarChangeListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public WeakReference<b> f1523a;

        public a(b bVar) {
            this.f1523a = new WeakReference<>(bVar);
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            WeakReference<b> weakReference;
            if (!z || (weakReference = this.f1523a) == null || weakReference.get() == null) {
                return;
            }
            this.f1523a.get().H(i);
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
            WeakReference<b> weakReference = this.f1523a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.f1523a.get().I();
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
            WeakReference<b> weakReference = this.f1523a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.f1523a.get().J();
        }
    }

    public b(Activity activity) {
        super(activity);
        this.l = 0;
    }

    @Override // b.b.g.q.f.a.c, b.b.m.e.h
    /* JADX INFO: renamed from: G, reason: merged with bridge method [inline-methods] */
    public b.b.g.n.i.b newP() {
        b.b.g.n.i.b bVar = new b.b.g.n.i.b();
        this.j = bVar;
        return bVar;
    }

    public final void H(int i) {
        b.b.m.c.a.a(this.f2100a, "---onProgressChanged---progress: " + i);
        this.j.e(i);
    }

    public final void I() {
        b.b.m.c.a.a(this.f2100a, "---onStartTrackingTouch---");
    }

    public final void J() {
        int progress = this.i.getProgress();
        b.b.m.c.a.a(this.f2100a, "---onStopTrackingTouch---progress: " + progress);
    }

    @Override // b.b.g.q.f.a.c, b.b.m.e.h
    public int getLayoutId() {
        return R.layout.card_atm_lamp_color;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // b.b.g.q.f.a.c, b.b.m.e.e
    public void k(Context context) {
        super.k(context);
        ((b.b.g.n.i.b) mo7getP()).d();
        this.h = (TextView) this.f2102c.findViewById(R.id.tv_title_card_atm_lamp_color);
        BydSeekBar bydSeekBar = (BydSeekBar) this.f2102c.findViewById(R.id.sbar_card_atm_lamp_color);
        this.i = bydSeekBar;
        bydSeekBar.setOnSeekBarChangeListener(new a(this));
    }

    @Override // b.b.m.e.e
    public void n(BydData bydData, int i) {
        if (bydData == null || !(bydData instanceof CommonSettingItem)) {
            return;
        }
        b.b.m.c.a.a(this.f2100a, "---updateData---data: " + bydData);
        CommonSettingItem commonSettingItem = (CommonSettingItem) bydData;
        this.k = commonSettingItem;
        this.h.setText(commonSettingItem.getTitle());
        this.i.setCorlors(this.k.getMax());
        if (Build.VERSION.SDK_INT >= 26) {
            this.i.setMin(this.k.getMin());
        }
        int iC = this.j.c();
        this.l = iC;
        this.i.setProgress(iC);
    }

    @Override // b.b.g.q.f.a.c
    public void v(int i, b.b.a.c.a aVar) {
        super.v(i, aVar);
        if (b.b.g.r.e.e(aVar)) {
            this.i.setProgress(this.l);
        } else {
            this.i.setProgress(i);
            this.l = i;
        }
    }
}
