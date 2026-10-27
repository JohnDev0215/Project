package defpackage;

import android.widget.SeekBar;
import org.chromium.base.Callback;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: PageZoomViewBinder.java */
/* JADX INFO: loaded from: classes2.dex */
public final class jv1 implements SeekBar.OnSeekBarChangeListener {
    public final /* synthetic */ PropertyModel e;

    public jv1(PropertyModel propertyModel) {
        this.e = propertyModel;
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public final void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        if (z) {
            PropertyModel.r<Callback<Integer>> rVar = gv1.c;
            PropertyModel propertyModel = this.e;
            ((Callback) propertyModel.j(rVar)).onResult(Integer.valueOf(i));
            ((Callback) propertyModel.j(gv1.d)).onResult(null);
        }
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public final void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public final void onStopTrackingTouch(SeekBar seekBar) {
    }
}
