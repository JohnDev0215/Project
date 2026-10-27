package org.chromium.ui;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;

/* JADX INFO: loaded from: classes3.dex */
public class ColorPickerAdvanced extends LinearLayout implements SeekBar.OnSeekBarChangeListener {
    private static final int HUE_COLOR_COUNT = 7;
    private static final int HUE_SEEK_BAR_MAX = 360;
    private static final int SATURATION_COLOR_COUNT = 2;
    private static final int SATURATION_SEEK_BAR_MAX = 100;
    private static final int VALUE_COLOR_COUNT = 2;
    private static final int VALUE_SEEK_BAR_MAX = 100;
    private int mCurrentColor;
    private final float[] mCurrentHsvValues;
    ColorPickerAdvancedComponent mHueDetails;
    private OnColorChangedListener mOnColorChangedListener;
    ColorPickerAdvancedComponent mSaturationDetails;
    ColorPickerAdvancedComponent mValueDetails;

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    public ColorPickerAdvanced(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mCurrentHsvValues = new float[3];
        init();
    }

    public ColorPickerAdvanced(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mCurrentHsvValues = new float[3];
        init();
    }

    public ColorPickerAdvanced(Context context) {
        super(context);
        this.mCurrentHsvValues = new float[3];
        init();
    }

    private void init() {
        setOrientation(1);
        this.mHueDetails = createAndAddNewGradient(R.string.color_picker_hue, 360, this);
        this.mSaturationDetails = createAndAddNewGradient(R.string.color_picker_saturation, 100, this);
        this.mValueDetails = createAndAddNewGradient(R.string.color_picker_value, 100, this);
        refreshGradientComponents();
    }

    public ColorPickerAdvancedComponent createAndAddNewGradient(int i, int i2, SeekBar.OnSeekBarChangeListener onSeekBarChangeListener) {
        View viewInflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.color_picker_advanced_component, (ViewGroup) null);
        addView(viewInflate);
        return new ColorPickerAdvancedComponent(viewInflate, i, i2, onSeekBarChangeListener);
    }

    public void setListener(OnColorChangedListener onColorChangedListener) {
        this.mOnColorChangedListener = onColorChangedListener;
    }

    public int getColor() {
        return this.mCurrentColor;
    }

    public void setColor(int i) {
        this.mCurrentColor = i;
        Color.colorToHSV(i, this.mCurrentHsvValues);
        refreshGradientComponents();
    }

    private void notifyColorChanged() {
        OnColorChangedListener onColorChangedListener = this.mOnColorChangedListener;
        if (onColorChangedListener != null) {
            onColorChangedListener.onColorChanged(getColor());
        }
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        if (z) {
            this.mCurrentHsvValues[0] = this.mHueDetails.getValue();
            this.mCurrentHsvValues[1] = this.mSaturationDetails.getValue() / 100.0f;
            this.mCurrentHsvValues[2] = this.mValueDetails.getValue() / 100.0f;
            this.mCurrentColor = Color.HSVToColor(this.mCurrentHsvValues);
            updateHueGradient();
            updateSaturationGradient();
            updateValueGradient();
            notifyColorChanged();
        }
    }

    private void updateHueGradient() {
        float[] fArr = new float[3];
        float[] fArr2 = this.mCurrentHsvValues;
        fArr[1] = fArr2[1];
        fArr[2] = fArr2[2];
        int[] iArr = new int[7];
        for (int i = 0; i < 7; i++) {
            fArr[0] = i * 60.0f;
            iArr[i] = Color.HSVToColor(fArr);
        }
        this.mHueDetails.setGradientColors(iArr);
    }

    private void updateSaturationGradient() {
        float[] fArr = this.mCurrentHsvValues;
        float[] fArr2 = {fArr[0], 0.0f, fArr[2]};
        fArr2[1] = 1.0f;
        this.mSaturationDetails.setGradientColors(new int[]{Color.HSVToColor(fArr2), Color.HSVToColor(fArr2)});
    }

    private void updateValueGradient() {
        float[] fArr = this.mCurrentHsvValues;
        float[] fArr2 = {fArr[0], fArr[1], 0.0f};
        fArr2[2] = 1.0f;
        this.mValueDetails.setGradientColors(new int[]{Color.HSVToColor(fArr2), Color.HSVToColor(fArr2)});
    }

    private void refreshGradientComponents() {
        int iMax = Math.max(Math.min(Math.round(this.mCurrentHsvValues[1] * 100.0f), 100), 0);
        int iMax2 = Math.max(Math.min(Math.round(this.mCurrentHsvValues[2] * 100.0f), 100), 0);
        this.mHueDetails.setValue(this.mCurrentHsvValues[0]);
        this.mSaturationDetails.setValue(iMax);
        this.mValueDetails.setValue(iMax2);
        updateHueGradient();
        updateSaturationGradient();
        updateValueGradient();
    }
}
