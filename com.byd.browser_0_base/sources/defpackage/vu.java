package defpackage;

import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import org.chromium.components.embedder_support.R$drawable;
import org.chromium.components.embedder_support.R$id;

/* JADX INFO: compiled from: ColorPickerAdvancedComponent.java */
/* JADX INFO: loaded from: classes2.dex */
public final class vu {
    public final View a;
    public final SeekBar b;
    public final GradientDrawable c;

    public vu(View view, int i, int i2, SeekBar.OnSeekBarChangeListener onSeekBarChangeListener) {
        this.a = view.findViewById(R$id.gradient);
        ((TextView) view.findViewById(R$id.text)).setText(i);
        this.c = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, null);
        SeekBar seekBar = (SeekBar) view.findViewById(R$id.seek_bar);
        this.b = seekBar;
        seekBar.setOnSeekBarChangeListener(onSeekBarChangeListener);
        seekBar.setMax(i2);
        seekBar.setThumbOffset(c6.c(view.getContext().getResources(), R$drawable.color_picker_advanced_select_handle, 0).getIntrinsicWidth() / 2);
    }

    public final void a(int[] iArr) {
        int[] iArr2 = (int[]) iArr.clone();
        GradientDrawable gradientDrawable = this.c;
        gradientDrawable.setColors(iArr2);
        this.a.setBackground(gradientDrawable);
    }
}
