package org.chromium.components.browser_ui.accessibility;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.preference.Preference;
import defpackage.by1;
import defpackage.iv1;
import defpackage.n3;
import defpackage.n32;
import defpackage.vt;
import org.chromium.ui.widget.ChromeImageButton;

/* JADX INFO: loaded from: classes2.dex */
public class PageZoomPreference extends Preference implements SeekBar.OnSeekBarChangeListener {
    public int S;
    public SeekBar T;
    public ChromeImageButton U;
    public ChromeImageButton V;
    public TextView W;
    public float X;
    public ImageView Y;
    public LinearLayout.LayoutParams Z;
    public TextView a0;
    public TextView b0;
    public TextView c0;

    public PageZoomPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f17J = R$layout.page_zoom_preference;
    }

    public final void Y(int i) {
        this.W.setText(this.e.getResources().getString(R$string.page_zoom_level, Long.valueOf(Math.round(iv1.b(i) * 100.0d))));
        float fB = (float) iv1.b(i);
        this.a0.setTextSize(2, 16.0f * fB);
        this.b0.setTextSize(2, 14.0f * fB);
        this.c0.setTextSize(2, 12.0f * fB);
        LinearLayout.LayoutParams layoutParams = this.Z;
        int i2 = (int) (this.X * fB);
        layoutParams.width = i2;
        layoutParams.height = i2;
        this.Y.setLayoutParams(layoutParams);
        double dA = iv1.a(i);
        ChromeImageButton chromeImageButton = this.U;
        double[] dArr = n3.k;
        chromeImageButton.setEnabled(dA > dArr[0]);
        this.V.setEnabled(dA < dArr[13]);
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public final void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        Y(i);
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public final void onStopTrackingTouch(SeekBar seekBar) {
        h(Integer.valueOf(seekBar.getProgress()));
    }

    @Override // androidx.preference.Preference
    public final void y(n32 n32Var) {
        super.y(n32Var);
        LinearLayout linearLayout = (LinearLayout) n32Var.u(R$id.page_zoom_view_container);
        int paddingTop = linearLayout.getPaddingTop();
        int paddingBottom = linearLayout.getPaddingBottom();
        linearLayout.setBackground(null);
        linearLayout.setPadding(0, paddingTop, 0, paddingBottom);
        this.a0 = (TextView) n32Var.u(R$id.page_zoom_preview_large_text);
        this.b0 = (TextView) n32Var.u(R$id.page_zoom_preview_medium_text);
        this.c0 = (TextView) n32Var.u(R$id.page_zoom_preview_small_text);
        Context context = this.e;
        this.X = context.getResources().getDimensionPixelSize(R$dimen.page_zoom_preview_image_size);
        this.Y = (ImageView) n32Var.u(R$id.page_zoom_preview_image);
        this.Z = new LinearLayout.LayoutParams(this.Y.getWidth(), this.Y.getHeight());
        TextView textView = (TextView) n32Var.u(R$id.page_zoom_current_value_text);
        this.W = textView;
        textView.setText(context.getResources().getString(R$string.page_zoom_level, 100));
        ChromeImageButton chromeImageButton = (ChromeImageButton) n32Var.u(R$id.page_zoom_decrease_zoom_button);
        this.U = chromeImageButton;
        chromeImageButton.setOnClickListener(new by1(23, this));
        ChromeImageButton chromeImageButton2 = (ChromeImageButton) n32Var.u(R$id.page_zoom_increase_zoom_button);
        this.V = chromeImageButton2;
        chromeImageButton2.setOnClickListener(new vt(25, this));
        SeekBar seekBar = (SeekBar) n32Var.u(R$id.page_zoom_slider);
        this.T = seekBar;
        seekBar.setOnSeekBarChangeListener(this);
        this.T.setMax(250);
        this.T.setProgress(this.S);
        Y(this.S);
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public final void onStartTrackingTouch(SeekBar seekBar) {
    }
}
