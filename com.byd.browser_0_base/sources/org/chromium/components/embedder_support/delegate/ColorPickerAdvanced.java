package org.chromium.components.embedder_support.delegate;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import defpackage.bs1;
import defpackage.vu;
import org.chromium.components.embedder_support.R$layout;
import org.chromium.components.embedder_support.R$string;

/* JADX INFO: loaded from: classes2.dex */
public class ColorPickerAdvanced extends LinearLayout implements SeekBar.OnSeekBarChangeListener {
    public vu e;
    public vu f;
    public vu g;
    public bs1 h;
    public int i;
    public final float[] j;

    public ColorPickerAdvanced(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.j = new float[3];
        b();
    }

    public final vu a(int i, int i2, SeekBar.OnSeekBarChangeListener onSeekBarChangeListener) {
        View viewInflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R$layout.color_picker_advanced_component, (ViewGroup) null);
        addView(viewInflate);
        return new vu(viewInflate, i, i2, onSeekBarChangeListener);
    }

    public final void b() {
        setOrientation(1);
        this.e = a(R$string.color_picker_hue, 360, this);
        this.f = a(R$string.color_picker_saturation, 100, this);
        this.g = a(R$string.color_picker_value, 100, this);
        c();
    }

    public final void c() {
        float[] fArr = this.j;
        int iMax = Math.max(Math.min(Math.round(fArr[1] * 100.0f), 100), 0);
        int iMax2 = Math.max(Math.min(Math.round(fArr[2] * 100.0f), 100), 0);
        this.e.b.setProgress((int) fArr[0]);
        this.f.b.setProgress(iMax);
        this.g.b.setProgress(iMax2);
        d();
        e();
        f();
    }

    public final void d() {
        float[] fArr = new float[3];
        float[] fArr2 = this.j;
        fArr[1] = fArr2[1];
        fArr[2] = fArr2[2];
        int[] iArr = new int[7];
        for (int i = 0; i < 7; i++) {
            fArr[0] = i * 60.0f;
            iArr[i] = Color.HSVToColor(fArr);
        }
        this.e.a(iArr);
    }

    public final void e() {
        float[] fArr = this.j;
        float[] fArr2 = {fArr[0], 0.0f, fArr[2]};
        fArr2[1] = 1.0f;
        this.f.a(new int[]{Color.HSVToColor(fArr2), Color.HSVToColor(fArr2)});
    }

    public final void f() {
        float[] fArr = this.j;
        float[] fArr2 = {fArr[0], fArr[1], 0.0f};
        fArr2[2] = 1.0f;
        this.g.a(new int[]{Color.HSVToColor(fArr2), Color.HSVToColor(fArr2)});
    }

    public int getColor() {
        return this.i;
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public final void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        if (z) {
            float progress = this.e.b.getProgress();
            float[] fArr = this.j;
            fArr[0] = progress;
            fArr[1] = this.f.b.getProgress() / 100.0f;
            fArr[2] = this.g.b.getProgress() / 100.0f;
            this.i = Color.HSVToColor(fArr);
            d();
            e();
            f();
            bs1 bs1Var = this.h;
            if (bs1Var != null) {
                bs1Var.a(getColor());
            }
        }
    }

    public void setColor(int i) {
        this.i = i;
        Color.colorToHSV(i, this.j);
        c();
    }

    public void setListener(bs1 bs1Var) {
        this.h = bs1Var;
    }

    public ColorPickerAdvanced(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.j = new float[3];
        b();
    }

    public ColorPickerAdvanced(Context context) {
        super(context);
        this.j = new float[3];
        b();
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public final void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public final void onStopTrackingTouch(SeekBar seekBar) {
    }
}
