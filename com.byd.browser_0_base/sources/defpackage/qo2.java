package defpackage;

import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.SeekBar;
import androidx.appcompat.R;

/* JADX INFO: compiled from: SkinCompatSeekBarHelper.java */
/* JADX INFO: loaded from: classes3.dex */
public final class qo2 extends oo2 {
    public final SeekBar p;
    public int q;

    public qo2(SeekBar seekBar) {
        super(seekBar);
        this.q = 0;
        this.p = seekBar;
    }

    @Override // defpackage.oo2
    public final void J() {
        super.J();
        int iB = vd1.b(this.q);
        this.q = iB;
        if (iB != 0) {
            SeekBar seekBar = this.p;
            seekBar.setThumb(xo2.a(seekBar.getContext(), this.q));
        }
    }

    @Override // defpackage.oo2
    public final void K(AttributeSet attributeSet, int i) {
        super.K(attributeSet, i);
        TypedArray typedArrayObtainStyledAttributes = this.p.getContext().obtainStyledAttributes(attributeSet, R.styleable.AppCompatSeekBar, i, 0);
        this.q = typedArrayObtainStyledAttributes.getResourceId(R.styleable.AppCompatSeekBar_android_thumb, 0);
        typedArrayObtainStyledAttributes.recycle();
        J();
    }
}
