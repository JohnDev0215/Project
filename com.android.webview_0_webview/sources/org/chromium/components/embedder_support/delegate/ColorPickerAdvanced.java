package org.chromium.components.embedder_support.delegate;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import defpackage.AbstractC0425Xm;
import defpackage.AbstractC0566bn;
import defpackage.C1463rx;
import defpackage.InterfaceC1903zx;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ColorPickerAdvanced extends LinearLayout implements SeekBar.OnSeekBarChangeListener {
    public C1463rx s;
    public C1463rx t;
    public C1463rx u;
    public InterfaceC1903zx v;
    public int w;
    public final float[] x;

    public ColorPickerAdvanced(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.x = new float[3];
        setOrientation(1);
        this.s = a(AbstractC0566bn.K0, 360, this);
        this.t = a(AbstractC0566bn.L0, 100, this);
        this.u = a(AbstractC0566bn.M0, 100, this);
        b();
    }

    public C1463rx a(int i, int i2, SeekBar.OnSeekBarChangeListener onSeekBarChangeListener) {
        View viewInflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(AbstractC0425Xm.I, (ViewGroup) null);
        addView(viewInflate);
        return new C1463rx(viewInflate, i, i2, onSeekBarChangeListener);
    }

    public final void b() {
        int iMax = Math.max(Math.min(Math.round(this.x[1] * 100.0f), 100), 0);
        int iMax2 = Math.max(Math.min(Math.round(this.x[2] * 100.0f), 100), 0);
        this.s.c(this.x[0]);
        this.t.c(iMax);
        this.u.c(iMax2);
        c();
        d();
        e();
    }

    public final void c() {
        float[] fArr = new float[3];
        float[] fArr2 = this.x;
        fArr[1] = fArr2[1];
        fArr[2] = fArr2[2];
        int[] iArr = new int[7];
        for (int i = 0; i < 7; i++) {
            fArr[0] = i * 60.0f;
            iArr[i] = Color.HSVToColor(fArr);
        }
        this.s.b(iArr);
    }

    public final void d() {
        float[] fArr = this.x;
        float[] fArr2 = {fArr[0], 0.0f, fArr[2]};
        fArr2[1] = 1.0f;
        this.t.b(new int[]{Color.HSVToColor(fArr2), Color.HSVToColor(fArr2)});
    }

    public final void e() {
        float[] fArr = this.x;
        float[] fArr2 = {fArr[0], fArr[1], 0.0f};
        fArr2[2] = 1.0f;
        this.u.b(new int[]{Color.HSVToColor(fArr2), Color.HSVToColor(fArr2)});
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        if (z) {
            this.x[0] = this.s.a();
            this.x[1] = this.t.a() / 100.0f;
            this.x[2] = this.u.a() / 100.0f;
            this.w = Color.HSVToColor(this.x);
            c();
            d();
            e();
            InterfaceC1903zx interfaceC1903zx = this.v;
            if (interfaceC1903zx != null) {
                interfaceC1903zx.a(this.w);
            }
        }
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStopTrackingTouch(SeekBar seekBar) {
    }
}
