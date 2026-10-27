package org.chromium.components.browser_ui.accessibility;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.preference.Preference;
import defpackage.n32;
import java.text.NumberFormat;

/* JADX INFO: loaded from: classes2.dex */
public class TextScalePreference extends Preference implements SeekBar.OnSeekBarChangeListener {
    public float S;
    public float T;
    public TextView U;
    public TextView V;
    public SeekBar W;
    public final NumberFormat X;

    public TextScalePreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.S = 0.5f;
        this.X = NumberFormat.getPercentInstance();
        this.f17J = R$layout.custom_preference;
        this.K = R$layout.preference_text_scale;
    }

    public final void Y() {
        TextView textView = this.U;
        double d = this.S;
        NumberFormat numberFormat = this.X;
        textView.setText(numberFormat.format(d));
        String string = this.e.getResources().getString(R$string.font_size_accessibility_label, numberFormat.format(this.S));
        if (Build.VERSION.SDK_INT >= 30) {
            this.W.setStateDescription(string);
        } else {
            this.W.setContentDescription(string);
        }
        this.V.setTextSize(1, this.T * 12.0f);
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public final void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        if (z) {
            float f = (i * 0.05f) + 0.5f;
            if (f == this.S) {
                return;
            }
            h(Float.valueOf(f));
        }
    }

    @Override // androidx.preference.Preference
    public final void y(n32 n32Var) {
        super.y(n32Var);
        SeekBar seekBar = (SeekBar) n32Var.u(R$id.seekbar);
        this.W = seekBar;
        seekBar.setOnSeekBarChangeListener(this);
        this.W.setMax(Math.round(30.0f));
        this.W.setProgress(Math.round((this.S - 0.5f) / 0.05f));
        this.U = (TextView) n32Var.u(R$id.seekbar_amount);
        this.V = (TextView) n32Var.u(R$id.preview);
        Y();
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public final void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public final void onStopTrackingTouch(SeekBar seekBar) {
    }
}
