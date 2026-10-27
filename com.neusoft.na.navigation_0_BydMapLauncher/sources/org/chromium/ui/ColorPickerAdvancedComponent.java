package org.chromium.ui;

import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import org.chromium.base.ApiCompatibilityUtils;

/* JADX INFO: loaded from: classes3.dex */
public class ColorPickerAdvancedComponent {
    private int[] mGradientColors;
    private GradientDrawable mGradientDrawable;
    private final View mGradientView;
    private final SeekBar mSeekBar;
    private final TextView mText;

    ColorPickerAdvancedComponent(View view, int i, int i2, SeekBar.OnSeekBarChangeListener onSeekBarChangeListener) {
        this.mGradientView = view.findViewById(R.id.gradient);
        TextView textView = (TextView) view.findViewById(R.id.text);
        this.mText = textView;
        textView.setText(i);
        this.mGradientDrawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, null);
        SeekBar seekBar = (SeekBar) view.findViewById(R.id.seek_bar);
        this.mSeekBar = seekBar;
        seekBar.setOnSeekBarChangeListener(onSeekBarChangeListener);
        seekBar.setMax(i2);
        seekBar.setThumbOffset(ApiCompatibilityUtils.getDrawable(view.getContext().getResources(), R.drawable.color_picker_advanced_select_handle).getIntrinsicWidth() / 2);
    }

    public float getValue() {
        return this.mSeekBar.getProgress();
    }

    public void setValue(float f) {
        this.mSeekBar.setProgress((int) f);
    }

    public void setGradientColors(int[] iArr) {
        int[] iArr2 = (int[]) iArr.clone();
        this.mGradientColors = iArr2;
        this.mGradientDrawable.setColors(iArr2);
        this.mGradientView.setBackground(this.mGradientDrawable);
    }
}
